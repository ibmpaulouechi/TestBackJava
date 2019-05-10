package br.com.santander.controle.api.services;

import java.util.Date;
import java.util.List;

import br.com.santander.controle.api.dtos.GastosDto;
import br.com.santander.controle.api.entities.Gastos;

public interface GastoService {
	
	Gastos persistir(Gastos gastos);

	void apagar(Gastos gastos);
	
	Gastos buscaPorCodigo(String codigo);

	Gastos buscarPorDescricao(String descricao);
	
	Gastos buscarPorCodigoUsuario(String codigoUsuario);

	List<Gastos> buscarTodos();

	List<Gastos> buscarTodosPorCodigoUsuario(String codigoUsuario);
	
	List<Gastos> buscarTodosPorUsuarioData(String codigoUsuario, Date data);
	
	List<GastosDto> converterListaGastosDto(List<Gastos> listaGastos);
	
	GastosDto converterGastosDto(Gastos gastos);
	
	Gastos converterGastos(GastosDto gastosDto);
	
}
