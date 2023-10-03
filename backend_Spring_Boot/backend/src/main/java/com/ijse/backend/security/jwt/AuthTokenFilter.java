package com.ijse.backend.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ijse.backend.security.UserDetailsServiceImpl;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//This class is used to filter all requests 
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    //--------------------[start - method to filter requests]---------------------------------------------------------
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException, java.io.IOException {

            try {
                String jwt = parseJwt(request);

                if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
                    String username = jwtUtils.getUsernameFromJwtToken(jwt);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                logger.error("Cannot set user authentication: {}", e);
            }

            filterChain.doFilter(request, response);
        }
    //--------------------[End - method to filter requests]---------------------------------------------------------


    //-----------[Start : extract token from Bearer token]----------------------------------------------
    private String parseJwt(HttpServletRequest request) {
        //fetch authorization header from request
        String headerAuth = request.getHeader("Authorization");

        //check whether jwt token is a bearer token| "Bearer " : Bearer token starts with "Bearer ".
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            //extract jwt token without "Bearer " 
            return headerAuth.substring(7);
        }
        return null;
    }
    //-----------[End : extract token from Bearer token]-------------------------------------------------
}
