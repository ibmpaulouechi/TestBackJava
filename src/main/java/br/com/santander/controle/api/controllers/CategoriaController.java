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

import br.com.santander.controle.api.dtos.CategoriasDto;
import br.com.santander.controle.api.entities.Categorias;
import br.com.santander.controle.api.response.Response;
import br.com.santander.controle.api.services.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
	
	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);
	
	@Autowired
	private CategoriaService categoriaService;

	public CategoriaController() {
		super();
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<CategoriasDto>> buscarPorCodigo(@PathVariable("codigo") String codigo) {
		log.info("Buscando por: {}", codigo);
		Response<CategoriasDto> response = new Response<CategoriasDto>();
		Categorias categorias = categoriaService.buscarPorCodigo(codigo);
		if (categorias == null) {
			log.info("Não encontrada por: {}", codigo);
			response.getErrors().add("Não encontrada por: " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.categoriaService.converterCategoriasDto(categorias));
		return ResponseEntity.ok(response);
	}

	@GetMapping()
	public ResponseEntity<Response<CategoriasDto>> listarTodos() {
		log.info("Listando todos");
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
		
	@PostMapping
	public ResponseEntity<Response<CategoriasDto>> Adicionar(@Valid @RequestBody CategoriasDto categoriasDto,
			BindingResult result) throws ParseException {
		log.info("Adicionando: {}", categoriasDto.toString());
		Response<CategoriasDto> response = new Response<CategoriasDto>();
		Categorias categorias = this.categoriaService.converterCategorias(categoriasDto);
		categorias = this.categoriaService.persistir(categorias);
		response.setData(this.categoriaService.converterCategoriasDto(categorias));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> Apagar(@PathVariable("codigo") String codigo) {
		log.info("Removendo: {}", codigo);
		Response<String> response = new Response<String>();
		Categorias categorias = this.categoriaService.buscarPorCodigo(codigo);
		if (categorias == null) {
			log.info("Erro ao remover {} inválido.", codigo);
			response.getErrors().add("Erro ao remover " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		this.categoriaService.apagar(categorias);
		return ResponseEntity.ok(new Response<String>());
	}	
	
}
