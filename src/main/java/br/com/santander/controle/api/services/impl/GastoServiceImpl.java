package br.com.santander.controle.api.services.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.controle.api.dtos.GastosDto;
import br.com.santander.controle.api.entities.Gastos;
import br.com.santander.controle.api.repositories.GastoRepository;
import br.com.santander.controle.api.services.GastoService;

@Service
public class GastoServiceImpl implements GastoService {

	private static final Logger log = LoggerFactory.getLogger(GastoServiceImpl.class);
	
	@Autowired
	private GastoRepository gastoRepository;
	
	@Override
	public Gastos persistir(Gastos gastos) {
		log.info("Persistindo: {}", gastos);
		return this.gastoRepository.save(gastos);
	}

	@Override
	public void apagar(Gastos gastos) {
		log.info("Apagando: {}", gastos);
		this.gastoRepository.delete(gastos);
	}

	@Override
	public Gastos buscaPorCodigo(String codigo) {
		log.info("Buscando: {}", codigo);
		return this.gastoRepository.findOne(codigo);
	}

	@Override
	public Gastos buscarPorDescricao(String descricao) {
		log.info("Buscando: {}", descricao);
		return this.gastoRepository.findByDescricao(descricao);
	}

	@Override
	public Gastos buscarPorCodigoUsuario(String codigoUsuario) {
		log.info("Buscando: {}", codigoUsuario);
		return this.gastoRepository.findByCodigoUsuario(codigoUsuario);
	}

	@Override
	public List<Gastos> buscarTodosPorCodigoUsuario(String codigoUsuario) {
		log.info("Buscando: {}", codigoUsuario);
		return this.gastoRepository.findAllByCodigoUsuario(codigoUsuario);
	}

	@Override
	public List<Gastos> buscarTodosPorUsuarioData(String codigoUsuario, Date data) {
		log.info("Buscando: {}, {}", codigoUsuario, data);
		return this.gastoRepository.findByCodigoUsuarioAndData(codigoUsuario, data);
	}

	@Override
	public List<Gastos> buscarTodos() {
		log.info("Listando");
		return this.gastoRepository.findAll();
	}
	
	
	@Override
	public List<GastosDto> converterListaGastosDto(List<Gastos> listaGastos) {
		List<GastosDto> listaGastosDto = new LinkedList<GastosDto>();
		for (Gastos gastos: listaGastos) {
			GastosDto gastosDto = new GastosDto();
			gastosDto.setCodigo(gastos.getCodigo());
			gastosDto.setDescricao(gastos.getDescricao());
			gastosDto.setCodigoUsuario(gastos.getCodigoUsuario());
			gastosDto.setData(gastos.getData());
			gastosDto.setValor(gastos.getValor());
			listaGastosDto.add(gastosDto);
		}
		return listaGastosDto;
	}

	@Override
	public GastosDto converterGastosDto(Gastos gastos) {
		GastosDto gastosDto = new GastosDto();
		gastosDto.setCodigo(gastos.getCodigo());
		gastosDto.setDescricao(gastos.getDescricao());
		gastosDto.setCodigoUsuario(gastos.getCodigoUsuario());
		gastosDto.setData(gastos.getData());
		gastosDto.setValor(gastos.getValor());
		return gastosDto;
	}
	
	@Override
	public Gastos converterGastos(GastosDto gastosDto) {
		Gastos gastos = new Gastos();
		gastos.setCodigo(gastosDto.getCodigo());
		gastos.setDescricao(gastosDto.getDescricao());
		gastos.setCodigoUsuario(gastosDto.getCodigoUsuario());
		gastos.setData(gastosDto.getData());
		gastos.setValor(gastosDto.getValor());
		return gastos;
	}
	

}
