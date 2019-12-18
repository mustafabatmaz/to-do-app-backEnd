package com.mustafa.ToDo.Security;

import com.mustafa.ToDo.Entity.User;
import com.mustafa.ToDo.Service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.mustafa.ToDo.Security.SecurityConstants.*;
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JWTAuthorizationFilter implements Filter {
    @Autowired
    private UserService userService;
    private @Autowired HttpServletRequest request;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        List<String> publicPages = Arrays.asList("/auth/login", "/auth/register");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader(HEADER_STRING);

        if("OPTIONS".equals(request.getMethod())){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        boolean isAuth = true;
        try {
            String username = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            User user = userService.findUsername(username)
                    .orElseThrow(() -> new RuntimeException("Username unknow token"));
            request.setAttribute("user", user);

        } catch (Exception e) {
            isAuth = false;
        }
        if(isAuth || publicPages.contains(request.getRequestURI())){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            response.getWriter().println("Unauthorized");
            response.setStatus(401);
            response.flushBuffer();
        }
    }

    @Override
    public void destroy() {

    }
}
