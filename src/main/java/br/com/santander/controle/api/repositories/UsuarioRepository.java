package br.com.santander.controle.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.santander.controle.api.entities.Usuarios;

public interface UsuarioRepository extends CrudRepository<Usuarios, String> {
	
	Usuarios findOne(String codigo);

	Usuarios findByNome(String nome);

	Usuarios findByLogin(String login);
	
	Usuarios findByEmail(String email);
	
	Usuarios findByLoginAndSenha(String login, String senha);
	
	List<Usuarios> findAll();
	
}
