package com.jxcia.pt.security;

import com.jxcia.pt.common.Constant;
import com.jxcia.pt.security.exception.CaptchaException;
import com.jxcia.pt.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if ("/login".equals(url) && request.getMethod().equals("POST")) {
            try {
                validate(request);
            } catch (CaptchaException e) {
                loginFailureHandler.onAuthenticationFailure(request, response, e);
            } catch (Exception ignored) {

            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        String key = request.getParameter("userKey");

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(key)) {
            throw new CaptchaException("验证码为空");
        }

        if (!code.equals(redisUtil.hget(Constant.CAPTCHA_KEY, key))) {
            throw new CaptchaException("验证码错误");
        }

        // 从redis中删除
        redisUtil.hdel(Constant.CAPTCHA_KEY, key);
    }

}
