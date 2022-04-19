package net.atos.reservas.reservaSalas.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.atos.reservas.reservaSalas.auth.jwt.JwtEntryPoint;
import net.atos.reservas.reservaSalas.auth.jwt.JwtTokenFilter;
import net.atos.reservas.reservaSalas.auth.service.UserAuthService;


@Configuration
@EnableWebSecurity
//con prePostEnabled se usa para indicar a q metodos puede acceder solo el admin
//Los metodos que no lleven anotación pueden acceder el admin como un generic user
//@preauthorized solo puede acceder el admin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserAuthService userAuthSevice;
	
	// Devuelve el mensaje de no autorizado
	@Autowired
	JwtEntryPoint jwtEntryPoint;

	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
	    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	    authenticationProvider.setUserDetailsService(userAuthSevice);
	    authenticationProvider.setPasswordEncoder(passwordEncoder());
	    return authenticationProvider;
	}

	/*@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }*/
	
	/**
	 * Encripta el pasword
	 * 
	 * @return pasword encriptado
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public UserDetailsService userDetailsService() {
      return new UserAuthService();
    };
    
    @Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthSevice).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(getAuthenticationProvider());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Desactivamos cookies ya que enviamos un token cada vez que hacemos una petición
		http.cors().and().csrf().disable()
		    .authorizeRequests().antMatchers("/auth/**").permitAll()
		                        .anyRequest().authenticated()
		                        .and()
		                        .formLogin()
	                        	.and()
		                        .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
		                        .and()
		                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
