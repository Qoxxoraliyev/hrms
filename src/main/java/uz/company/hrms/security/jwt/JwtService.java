package uz.company.hrms.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    private final Key key;

    private JwtService(@Value("${jwt.secret}") String secret){
        this.key= Keys.hmacShaKeyFor(secret.getBytes());
    }

   public String generateAccessToken(String email, String role){
       Map<String, Object> claims=new HashMap<>();
       claims.put("role",role);

       return Jwts.builder()
               .setClaims(claims)
               .setSubject(email)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis()+1000L*60*60*24))
               .signWith(key, SignatureAlgorithm.HS256)
               .compact();
   }

   public String generateRefreshToken(String email,String role){
        Map<String,Object> claims=new HashMap<>();
        claims.put("role",role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000L*60*60*24*7))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
   }

   public String extractEmail(String token){
        return extractClaim(token,Claims::getSubject);
   }

   public String extractRole(String token){
        return extractClaim(token,claims -> claims.get("role",String.class));
   }

   public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
   }

   public <T> T extractClaim(String token, Function<Claims,T> resolver){
        return resolver.apply(parseClaims(token));
   }

   private Claims parseClaims(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e){
            throw new RuntimeException("Invalid token");
        }
   }

   public boolean isValid(String token, UserDetails userDetails){
        String email=extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isExpired(token);
   }



   private boolean isExpired(String token){
        return extractExpiration(token).before(new Date());
   }
}
