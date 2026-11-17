package FlowBank.FlowBank.Config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component 
public class JwtAuthFilter extends OncePerRequestFilter {
   
   private final SecretKey signingKey;
    private final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);
   
    
  


    public JwtAuthFilter(SecretKey signingKey) {
        this.signingKey = signingKey;
    }


    @Override 
    public void  doFilterInternal(HttpServletRequest request,
         HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
            String authHeader = request.getHeader("Authorization");
            if(authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = authHeader.substring(7);
            
            if(!isTokenValid(token)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
            }
            String email = getEmail(token);
            String role = getRole(token);
            
            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
            var authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
    
    
       


        private boolean isTokenValid(String token) {
            try {
                Jwts.parser()
                        .verifyWith(signingKey)
                        .build().parseSignedClaims(token).getPayload();
                
                return true;
            } catch (Exception e ) {
                log.error("Invalid JWT token", e);
                return false;
            }
        }

       private String getEmail(String token){
         return getClaims(token).getSubject();
       }

       private String getRole(String token) {
        return getClaims(token).get("role", String.class);}

       private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

}
}