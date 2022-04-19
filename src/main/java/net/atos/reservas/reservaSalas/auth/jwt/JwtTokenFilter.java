package net.atos.reservas.reservaSalas.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import net.atos.reservas.reservaSalas.auth.service.UserAuthService;

public class JwtTokenFilter extends OncePerRequestFilter {

	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	UserAuthService userAuthService;
	
	/*
	 * Se obtiene el usuario que viene del token. Lo autenticamos y se lo pasamos al
	 * contexto de autenticacion
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getToken(req);
			logger.info("Estamos en doFilter");
			/*
			 * if (token == null ){ logger.info("token null"); filterChain.doFilter(req,
			 * res); }
			 */

			if (token != null && jwtProvider.validateToken(token)) {
				String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
				logger.info("Nombre Usuario: " + nombreUsuario);
				UserDetails userDetails = userAuthService.loadUserByUsername(nombreUsuario);
				logger.info("UserDetails: " + userDetails);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
				// filterChain.doFilter(req, res);
			} else {
				filterChain.doFilter(req, res);
				return;
			}
		} catch (Exception e) {
			logger.error("fail en el método doFilter " + e.getMessage());
		}

		filterChain.doFilter(req, res);
	}
	
	// Metodo para obtener el token, sin el prefijo 'Bearer'
		private String getToken(HttpServletRequest request) {
			String header = request.getHeader("Authorization");
			logger.error("header: " + header);
			// Si no es nulo y comienza por el prefijo 'Bearer'
			if (header != null && header.startsWith("Bearer"))
				return header.replace("Bearer ", "");

			return null;
		}

}
