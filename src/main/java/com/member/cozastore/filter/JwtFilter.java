package com.member.cozastore.filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.member.cozastore.util.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerValue = request.getHeader("Authorization");
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            String token = headerValue.substring(7);
            String data = jwtHelper.parserToken(token);
            System.out.println("kiem tra: " + data);
            if (data != null & !data.isEmpty()) {
                Type listType = new TypeToken<ArrayList<SimpleGrantedAuthority>>(){}.getType();
                List<SimpleGrantedAuthority> roles =  gson.fromJson(data, listType);
//                List<GrantedAuthority> roles = new ArrayList<>();
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
//                roles.add(grantedAuthority);

                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("","",roles);

                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(user);

            }
        } else {
            System.out.println("nội dung header không hợp lệ");
        }
        filterChain.doFilter(request,response);
    }
}
