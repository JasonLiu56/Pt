package com.jxcia.pt.security;

import cn.hutool.json.JSONUtil;
import com.jxcia.pt.common.Constant;
import com.jxcia.pt.common.Result;
import com.jxcia.pt.entity.User;
import com.jxcia.pt.utils.JwtUtil;
import com.jxcia.pt.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 设置响应内容类型
        response.setContentType("application/json;charset=UTF-8");
        // 获取response的输出流
        ServletOutputStream outputStream = response.getOutputStream();
        // 生成jwt token
        String jwtToken = jwtUtil.generateToken(authentication.getName());
        response.setHeader(jwtUtil.getHeader(), jwtToken);
        // 缓存到redis
        redisUtil.hset(Constant.USER_KEY, authentication.getName(), authentication.getPrincipal(), jwtUtil.getExpire());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Result result = Result.succ("登录成功");
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

}
