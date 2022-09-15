package com.jxcia.pt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxcia.pt.common.Constant;
import com.jxcia.pt.dto.req.LoginReq;
import com.jxcia.pt.security.exception.CaptchaException;
import com.jxcia.pt.utils.RedisUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            log.error("认证方法不支持: {}", request.getMethod());
            throw new AuthenticationServiceException("认证方法不支持: " + request.getMethod());
        }

        // 解析json参数
        LoginReq loginReq = new ObjectMapper().readValue(request.getInputStream(), LoginReq.class);
        String username = loginReq.getUsername();
        System.out.println(loginReq.getPassword());
        // 前端使用Base64加密，后端解码
        String password = new String(Base64.decodeBase64(loginReq.getPassword().getBytes()));

        String code = loginReq.getCode();
        String userKey = loginReq.getUserKey();

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        try {
            validate(code, userKey);
        } catch (CaptchaException e) {
            loginFailureHandler.onAuthenticationFailure(request, response, e);
        } catch (Exception ignored) {

        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void validate(String code, String userKey) throws Exception {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(userKey)) {
            throw new CaptchaException("验证码为空");
        }

        if (!code.equals(redisUtil.hget(Constant.CAPTCHA_KEY, userKey))) {
            throw new CaptchaException("验证码错误");
        }

        // 从redis中删除
        redisUtil.hdel(Constant.CAPTCHA_KEY, userKey);
    }

}
