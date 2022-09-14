package com.jxcia.pt.security;

import cn.hutool.json.JSONUtil;
import com.jxcia.pt.common.Constant;
import com.jxcia.pt.common.Result;
import com.jxcia.pt.security.exception.JwtTokenException;
import com.jxcia.pt.utils.JwtUtil;
import com.jxcia.pt.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        // 获取jwtToken
        String jwt = request.getHeader(jwtUtil.getHeader());

        // jwt token为空
        if (ObjectUtils.isEmpty(jwt)) {
            throw new JwtTokenException("token为空");
        }

        Claims claims = jwtUtil.getClaimsByToken(jwt);
        if (ObjectUtils.isEmpty(claims)) {
            throw new JwtTokenException("token异常");
        }

        if (jwtUtil.isTokenExpired(claims)) {
            throw new JwtTokenException("token 已过期");
        }

        // 删除redis中的缓存数据
        if (redisUtil.hhasItem(Constant.USER_KEY, claims.getSubject())) {
            redisUtil.hdel(Constant.USER_KEY, claims.getSubject());
        }

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        Result result = Result.succ("退出成功");
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

}
