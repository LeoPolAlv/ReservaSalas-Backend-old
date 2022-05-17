package net.atos.reservas.reservaSalas.auth.jwt;

//import java.security.SignatureException;
import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import net.atos.reservas.reservaSalas.auth.entity.UserAuth;

/**
 * Clase que genera el token y valida que este bien formado y no este expirado
 */

@Component
public class JwtProvider {

	// Implementamos un logger para ver cual metodo da error en caso de falla
		private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

		
		// Variable que se usa para la firma de seguridad
		private String secret = "ATOSRESERVAS";
		
		private String rol = "roles";

		// tiempo de expiración serial (1 hora) tiempo en seg
		private int expiration = 3600;

		public String generateToken(Authentication authentication) {
			UserAuth usuarioMain = (UserAuth) authentication.getPrincipal();
			//logger.info("UsuarioMain: " + usuarioMain);
			return Jwts
					.builder()
					.setSubject(usuarioMain.getUsername())
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.claim(rol, usuarioMain.getRol()) //añadimos el rol del usuario al token
					.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
					.signWith(SignatureAlgorithm.HS512, secret)
					.compact();
		}

		// subject --> Nombre del usuario
		public String getNombreUsuarioFromToken(String token) {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		}

		public Boolean validateToken(String token) {
			try {
				Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
				return true;
			} catch (MalformedJwtException e) {
				logger.error("Token mal formado");
			} catch (UnsupportedJwtException e) {
				logger.error("Token no soportado");
			} catch (ExpiredJwtException e) {
				logger.error("Token expirado");
			} catch (IllegalArgumentException e) {
				logger.error("Token vacio");
			} catch (SignatureException e) {
				logger.error("Fallo con la firma");
				throw new SignatureException("Fallo con la firma");
			}
			return false;
		}
		
}
