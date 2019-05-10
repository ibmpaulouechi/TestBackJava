package br.com.santander.controle.api.services;

import java.util.List;

import br.com.santander.controle.api.dtos.UsuariosDto;
import br.com.santander.controle.api.entities.Usuarios;

public interface UsuarioService {

	Usuarios persistir(Usuarios usuarios);
	
	void apagar(Usuarios usuarios);
	
	Usuarios buscarPorCodigo(String codigo);
	
	Usuarios buscarPorNome(String nome);
	
	Usuarios buscarPorLogin(String login);
	
	Usuarios buscaPorEmail(String email);
	
	Usuarios buscarPorLoginSenha(String login, String senha);
	
	List<Usuarios> buscarTodos();
	
	List<UsuariosDto> converterListaUsuarioDto(List<Usuarios> listaUsuarios);
	
	UsuariosDto converterUsuarioDto(Usuarios usuarios);
	
	Usuarios converterUsuario(UsuariosDto usuariosDto);
	
}
