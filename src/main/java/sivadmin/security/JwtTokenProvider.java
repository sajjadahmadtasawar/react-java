package sivadmin.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import sivadmin.models.Bruker;

import java.util.Date;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {

        BrukerPrincipal brukerPrincipal = (BrukerPrincipal) authentication.getPrincipal();

        BrukerInfo brukerInfo = new BrukerInfo(brukerPrincipal.getId(), brukerPrincipal.getNavn(), brukerPrincipal.getBrukernavn(), brukerPrincipal.getEpost(), brukerPrincipal.getAuthorities());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setId(Long.toString(brukerPrincipal.getId()))
                .setSubject(brukerPrincipal.getBrukernavn())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .claim("bruker", brukerInfo)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getId());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Ugyldig JWT signatur");
        } catch (MalformedJwtException ex) {
            logger.error("Ugyldig JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("JWT token utl??pt");
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT token st??ttes ikke");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claim er tom.");
        }
        return false;
    }
}
