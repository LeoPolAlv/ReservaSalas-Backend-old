package net.atos.reservas.reservaSalas.auth.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.atos.reservas.reservaSalas.models.entity.User;

@Data
@AllArgsConstructor
public class UserAuth implements UserDetails {

	
	private String username;
	private String password;
	private String email;
	private String rol;
		
	private Collection<? extends GrantedAuthority> authorities;

	public static UserAuth build(User usuario) {
		// Convertimos la clase Rol a la clase GrantedAuthority
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(usuario.getRol()));

		return new UserAuth(usuario.getDasUser(), usuario.getPassword(),usuario.getEmail(), usuario.getRol(),
				authorities);

	}
	
	@Override
	public String getPassword() {
		return  this.password  ;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
