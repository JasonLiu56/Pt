package com.jxcia.pt.security;


import com.jxcia.pt.common.Constant;
import com.jxcia.pt.entity.User;
import com.jxcia.pt.security.exception.JwtTokenException;
import com.jxcia.pt.utils.JwtUtil;
import com.jxcia.pt.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader(jwtUtil.getHeader());
        if (StringUtils.isEmpty(jwt)) {
            chain.doFilter(request, response);
            return;
        }

        String username = null;
        try {
            username = this.validate(jwt);
        } catch (JwtTokenException e) {
            jwtAuthenticationEntryPoint.commence(request, response, e);
            return;
        }

        // 从redis中获取数据
        User user = (User) redisUtil.hget(Constant.USER_KEY, username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }

    private String validate(String jwt) throws JwtTokenException {
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

        return username;
    }

}
