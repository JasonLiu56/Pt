package com.jxcia.pt.security;


import cn.hutool.core.util.StrUtil;
import com.jxcia.pt.common.Constant;
import com.jxcia.pt.entity.User;
import com.jxcia.pt.security.exception.JwtTokenException;
import com.jxcia.pt.service.impl.UserServiceImpl;
import com.jxcia.pt.utils.JwtUtil;
import com.jxcia.pt.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws AuthenticationException, IOException, ServletException {
        String jwt = request.getHeader(jwtUtil.getHeader());
        if (StringUtils.isEmpty(jwt)) {
            chain.doFilter(request, response);
            return;
        }

        Claims claims = jwtUtil.getClaimsByToken(jwt);
        if (ObjectUtils.isEmpty(claims)) {
            throw new JwtTokenException("token异常");
        }

        if (jwtUtil.isTokenExpired(claims)) {
            throw new JwtTokenException("token 已过期");
        }

        // 从jwt token中 解析出username
        String username = claims.getSubject();

        // 如果redis中不存在，则让登录
        if (!redisUtil.hhasItem(Constant.USER_KEY, username)) {
            throw new JwtTokenException("请先登录");
        }

        // 从redis中获取数据
        User user = (User) redisUtil.hget(Constant.USER_KEY, username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }

}
