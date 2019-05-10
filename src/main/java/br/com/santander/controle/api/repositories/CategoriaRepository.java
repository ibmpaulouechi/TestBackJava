package br.com.santander.controle.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.santander.controle.api.entities.Categorias;

public interface CategoriaRepository extends CrudRepository<Categorias, String> {

	Categorias findOne(String codigo);
	
	Categorias findByCodigoUsuario(String codigoUsuario);
	
	Categorias findByCategoria(String categoria);
	
	List<Categorias> findAll();
	
}
