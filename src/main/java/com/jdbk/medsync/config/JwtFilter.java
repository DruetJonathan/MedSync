package com.jdbk.medsync.config;

import com.jdbk.medsync.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtUtil util;

    public JwtFilter(UserDetailsService userDetailsService, JwtUtil util) {
        this.userDetailsService = userDetailsService;
        this.util = util;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        //"Bearer ebguzagblvkjzlefkjdbzhvlzejfnb"
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            String[] authorizations = authorization.split(" ");
            String type = authorizations[0];
            String token = authorizations[1];

            if (type.equals("Bearer") && !token.equals("")) {
                String username = this.util.getUsername(token);
                UserDetails user = this.userDetailsService.loadUserByUsername(username);

                if (this.util.isValid(token)) {
                    UsernamePasswordAuthenticationToken upt = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(upt);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}

