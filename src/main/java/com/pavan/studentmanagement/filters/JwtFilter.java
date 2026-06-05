package com.pavan.studentmanagement.filters;

import com.pavan.studentmanagement.service.JwtService;
import com.pavan.studentmanagement.service.StudentDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final StudentDetailService studentDetailService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            final String requestTokenHeader=request.getHeader("Authorization");
            if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
            {
                filterChain.doFilter(request,response);
                return;
            }

            String token=requestTokenHeader.split("Bearer ")[1];

            String userName=jwtService.getUserNameFromToken(token);

            if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                User user= (User) studentDetailService.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken(user,null,null);

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request) //ip details
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            filterChain.doFilter(request,response);
        } catch (Exception e){
            handlerExceptionResolver.resolveException(request,response,null,e);
        }
    }

}
