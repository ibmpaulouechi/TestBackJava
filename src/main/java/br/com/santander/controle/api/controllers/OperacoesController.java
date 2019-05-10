package br.com.santander.controle.api.controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.controle.api.dtos.CategoriasDto;
import br.com.santander.controle.api.dtos.GastosDto;
import br.com.santander.controle.api.entities.Categorias;
import br.com.santander.controle.api.entities.Gastos;
import br.com.santander.controle.api.response.Response;
import br.com.santander.controle.api.services.CategoriaService;
import br.com.santander.controle.api.services.GastoService;

@RestController
@RequestMapping("/api/operacoes")
@CrossOrigin(origins = "*")
public class OperacoesController {
	
	private static final Logger log = LoggerFactory.getLogger(OperacoesController.class);
	
	@Autowired
	private GastoService gastoService;

	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping(value = "/integracao")
	public ResponseEntity<Response<GastosDto>> IntegracaoGastosCartao(@Valid @RequestBody GastosDto gastosDto,
			BindingResult result) throws ParseException {
		log.info("Integracao Gastos Cartao: {}", gastosDto.toString());
		Response<GastosDto> response = new Response<GastosDto>();
		Gastos gastos = this.gastoService.converterGastos(gastosDto);
		gastos = this.gastoService.persistir(gastos);
		response.setData(this.gastoService.converterGastosDto(gastos));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "codigoUsuario/{codigoUsuario}")
	public ResponseEntity<Response<GastosDto>> ListagemGastos(@PathVariable("codigoUsuario") String codigoUsuario) {
		log.info("Listando Gatos {}", codigoUsuario);
		Response<GastosDto> response = new Response<GastosDto>();
		if (codigoUsuario == null) {
			log.info("Erro ao listar Gastos {} inválido.", codigoUsuario);
			response.getErrors().add("Erro ao listar Gastos {} inválido. " + codigoUsuario);
			return ResponseEntity.badRequest().body(response);
		}
		List<Gastos> listaGastos = gastoService.buscarTodosPorCodigoUsuario(codigoUsuario);
		if (listaGastos == null) {
			log.info("Nenhum encontrado");
			response.getErrors().add("Nenhum encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		response.setDatas(this.gastoService.converterListaGastosDto(listaGastos));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "codigoUsuarios/{codigoUsuarios}/data/{data}")
	public ResponseEntity<Response<GastosDto>> FiltroGastos(@PathVariable("codigoUsuarios") String codigoUsuario, @PathVariable("data") Date data) {
		log.info("Listando Gatos {}", codigoUsuario);
		Response<GastosDto> response = new Response<GastosDto>();
		if (codigoUsuario == null) {
			log.info("Erro ao listar Gastos {} inválido.", codigoUsuario);
			response.getErrors().add("Erro ao listar Gastos {} inválido. " + codigoUsuario);
			return ResponseEntity.badRequest().body(response);
		}
		List<Gastos> listaGastos = gastoService.buscarTodosPorUsuarioData(codigoUsuario, data);
		if (listaGastos == null) {
			log.info("Nenhum encontrado");
			response.getErrors().add("Nenhum encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		response.setDatas(this.gastoService.converterListaGastosDto(listaGastos));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/categorizacao")
	public ResponseEntity<Response<CategoriasDto>> CategorizacaoGastos(@Valid @RequestBody CategoriasDto categoriasDto,
			BindingResult result) throws ParseException {
		log.info("Categorizacao Gastos : {}", categoriasDto.toString());
		Response<CategoriasDto> response = new Response<CategoriasDto>();
		Categorias categorias = this.categoriaService.converterCategorias(categoriasDto);
		categorias = this.categoriaService.persistir(categorias);
		response.setData(this.categoriaService.converterCategoriasDto(categorias));
		return ResponseEntity.ok(response);
	}

	@GetMapping()
	public ResponseEntity<Response<CategoriasDto>> SugestaoCategorias() {
		log.info("Sugestao Categorias");
		Response<CategoriasDto> response = new Response<CategoriasDto>();
		List<Categorias> listaCategorias = categoriaService.buscarTodos();
		if (listaCategorias == null) {
			log.info("Nenhum encontrado");
			response.getErrors().add("Nenhum encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		response.setDatas(this.categoriaService.converterCategoriasDto(listaCategorias));
		return ResponseEntity.ok(response);
	}


}
