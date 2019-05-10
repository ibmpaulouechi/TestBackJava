package br.com.santander.controle.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.santander.controle.api.entities.Usuarios;
import br.com.santander.controle.api.enums.PerfilEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(Usuarios usuarios) {
		return new JwtUser(usuarios.getCodigo(), usuarios.getEmail(), usuarios.getSenha(),
				mapToGrantedAuthorities(usuarios.getPerfil()));
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}

}
