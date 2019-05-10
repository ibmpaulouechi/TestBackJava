package br.com.santander.controle.api.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.santander.controle.api.entities.Usuarios;
import br.com.santander.controle.api.security.JwtUserFactory;
import br.com.santander.controle.api.services.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios usuarios = usuarioService.buscaPorEmail(username);
		if (usuarios != null) {
			return JwtUserFactory.create(usuarios);
		}			

		throw new UsernameNotFoundException("Email não encontrado.");
	}

	
	
}
