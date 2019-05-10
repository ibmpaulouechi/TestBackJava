package br.com.santander.controle.api.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.controle.api.entities.Gastos;

@Repository
public interface GastoRepository extends CrudRepository<Gastos, String> {

	Gastos findOne(String codigo);
	
	Gastos findByDescricao(String descricao);
	
	Gastos findByCodigoUsuario(String codigoUsuario);

	List<Gastos> findAllByCodigoUsuario(String codigoUsuario);
	
	List<Gastos> findByCodigoUsuarioAndData(String codigoUsuario, Date data);
	
	List<Gastos> findAll();
	
}
