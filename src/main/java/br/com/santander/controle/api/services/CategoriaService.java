package br.com.santander.controle.api.services;

import java.util.List;

import br.com.santander.controle.api.dtos.CategoriasDto;
import br.com.santander.controle.api.entities.Categorias;

public interface CategoriaService {

	Categorias persistir(Categorias categorias);
	
	void apagar(Categorias categorias);
	
	Categorias buscarPorCodigo(String codigo);

	Categorias buscarPorCodigoUsuario(String codigoUsuario);
	
	Categorias buscarPorCategoria(String categoria);
	
	List<Categorias> buscarTodos();
	
	List<CategoriasDto> converterCategoriasDto(List<Categorias> listCategorias);
	
	CategoriasDto converterCategoriasDto(Categorias categorias);
	
	Categorias converterCategorias(CategoriasDto categoriasDto);

}
