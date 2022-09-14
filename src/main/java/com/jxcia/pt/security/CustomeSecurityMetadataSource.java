package com.jxcia.pt.security;

import com.jxcia.pt.common.Constant;
import com.jxcia.pt.entity.Menu;
import com.jxcia.pt.entity.Role;
import com.jxcia.pt.service.MenuService;
import com.jxcia.pt.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomeSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisUtil redisUtil;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestURI = ((FilterInvocation)object).getRequest().getRequestURI();

        List<Menu> allMenu = null;
        // 如果menu在redis中存在直接从redis中读取数据
        if (redisUtil.hasKey(Constant.MENU_KEY)) {
            allMenu = (List<Menu>) redisUtil.get(Constant.MENU_KEY);
        } else {
            // 从数据库中查询
            allMenu = menuService.getAllMenu();
            // 存储到redis
            redisUtil.set(Constant.MENU_KEY, allMenu);
        }

        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getPattern(), requestURI)) {
                String[] roles = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
