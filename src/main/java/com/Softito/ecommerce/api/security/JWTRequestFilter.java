package com.Softito.ecommerce.api.security;

import com.Softito.ecommerce.model.User;
import com.Softito.ecommerce.model.dao.UserDao;
import com.Softito.ecommerce.service.JWTService;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDao userDao;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);//Bearer kısmnını at
            try {
                String username = jwtService.getUsername(token);
                Optional<User> opUser = userDao.findByUsername(username);
                if (opUser.isPresent()){
                    User user = opUser.get();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JWTDecodeException ex){
            }

        }
        filterChain.doFilter(request, response);
    }
}
