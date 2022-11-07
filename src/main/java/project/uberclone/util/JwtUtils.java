package project.uberclone.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import project.uberclone.exception.jwt.InvalidAlgorithmException;
import project.uberclone.exception.jwt.InvalidSessionTokenException;

import java.util.ArrayList;

import static com.auth0.jwt.HeaderParams.ALGORITHM;

@Component
public class JwtUtils {

    public static Algorithm ALGORITHM;


    public static UsernamePasswordAuthenticationToken getFrom(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);

            String username = decodedJWT.getSubject();
           /* String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            Collection<SimpleGrantedAuthority> authorities =
                    stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());*/

            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        } catch (JWTVerificationException e) {
            throw new InvalidSessionTokenException();
        } catch (IllegalArgumentException e) {
            throw new InvalidAlgorithmException();
        }
    }
}
