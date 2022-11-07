package project.uberclone.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import project.uberclone.model.dto.LoggedUserDto;
import project.uberclone.security.CustomAuthenticationToken;
import project.uberclone.security.CustomUserDetailsService;
import project.uberclone.util.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/login")){
            filterChain.doFilter(request,response);
        }
        else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = authorizationHeader.replace("Bearer ", "");
                    SecurityContextHolder.getContext().setAuthentication(JwtUtils.getFrom(token));
                    UsernamePasswordAuthenticationToken authToken = JwtUtils.getFrom(token);
                    LoggedUserDto loggedUserDto =
                            (LoggedUserDto)
                                    customUserDetailsService.loadUserByUsername(authToken.getPrincipal().toString());
                    CustomAuthenticationToken customAuthenticationToken =
                            new CustomAuthenticationToken(loggedUserDto, loggedUserDto.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(customAuthenticationToken);

                    filterChain.doFilter(request, response);
                } catch (Exception e){
                    response.setHeader("error", e.getMessage());
                }
            }
            else {
                filterChain.doFilter(request,response);
            }
            }
        }
}
