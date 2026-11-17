package FlowBank.FlowBank.Config;

import java.time.LocalTime;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component 
public class JwtTokenProvider {
    
    private final SecretKey signingKey;

        public JwtTokenProvider(SecretKey signingKey) {
            this.signingKey = signingKey;
        }
    
    public String gerarToken(String email, String role, Long id) {
        return  Jwts.builder()
            .subject(email)
            .claim("role", role)
            .claim("id",id)
            .issuedAt(issueDate())
            .expiration(calcularTempoExpiracao())
            .signWith(signingKey)
            .compact();
    }

    private Date calcularTempoExpiracao() {
        Date agora = new Date();
           Date expiracao = new Date(agora.getTime() + 600000);
        return expiracao;
    }
    private Date issueDate(){
        return new Date();
    }
     
}
