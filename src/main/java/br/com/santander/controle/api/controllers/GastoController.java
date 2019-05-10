package br.com.santander.controle.api.controllers;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.controle.api.dtos.GastosDto;
import br.com.santander.controle.api.entities.Gastos;
import br.com.santander.controle.api.response.Response;
import br.com.santander.controle.api.services.GastoService;

@RestController
@RequestMapping("/api/gastos")
@CrossOrigin(origins = "*")
public class GastoController {
	
	private static final Logger log = LoggerFactory.getLogger(GastoController.class);
	
	@Autowired
	private GastoService gastoService;

	public GastoController() {
		super();
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<GastosDto>> buscarPorCodigo(@PathVariable("codigo") String codigo) {
		log.info("Buscando por: {}", codigo);
		Response<GastosDto> response = new Response<GastosDto>();
		Gastos gastos = gastoService.buscaPorCodigo(codigo);
		if (gastos == null) {
			log.info("Não encontrada por: {}", codigo);
			response.getErrors().add("Não encontrada por: " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.gastoService.converterGastosDto(gastos));
		return ResponseEntity.ok(response);
	}

	@GetMapping()
	public ResponseEntity<Response<GastosDto>> listarTodos() {
		log.info("Listando todos");
		Response<GastosDto> response = new Response<GastosDto>();
		List<Gastos> listaGastos = gastoService.buscarTodos();
		if (listaGastos == null) {
			log.info("Nenhum encontrado");
			response.getErrors().add("Nenhum encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		response.setDatas(this.gastoService.converterListaGastosDto(listaGastos));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<GastosDto>> Adicionar(@Valid @RequestBody GastosDto gastosDto,
			BindingResult result) throws ParseException {
		log.info("Adicionando: {}", gastosDto.toString());
		Response<GastosDto> response = new Response<GastosDto>();
		Gastos gastos = this.gastoService.converterGastos(gastosDto);
		gastos = this.gastoService.persistir(gastos);
		response.setData(this.gastoService.converterGastosDto(gastos));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> Apagar(@PathVariable("codigo") String codigo) {
		log.info("Removendo: {}", codigo);
		Response<String> response = new Response<String>();
		Gastos gastos = this.gastoService.buscaPorCodigo(codigo);
		if (gastos == null) {
			log.info("Erro ao remover {} inválido.", codigo);
			response.getErrors().add("Erro ao remover " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		this.gastoService.apagar(gastos);
		return ResponseEntity.ok(new Response<String>());
	}

	

}
