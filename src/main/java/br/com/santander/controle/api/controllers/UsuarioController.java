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

import br.com.santander.controle.api.dtos.UsuariosDto;
import br.com.santander.controle.api.entities.Usuarios;
import br.com.santander.controle.api.response.Response;
import br.com.santander.controle.api.services.UsuarioService;
import br.com.santander.controle.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;

	public UsuarioController() {
		super();
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<UsuariosDto>> buscarPorCodigo(@PathVariable("codigo") String codigo) {
		log.info("Buscando por: {}", codigo);
		Response<UsuariosDto> response = new Response<UsuariosDto>();
		Usuarios usuario = usuarioService.buscarPorCodigo(codigo);
		if (usuario == null) {
			log.info("Não encontrada por: {}", codigo);
			response.getErrors().add("Não encontrada por: " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.usuarioService.converterUsuarioDto(usuario));
		return ResponseEntity.ok(response);
	}

	@GetMapping()
	public ResponseEntity<Response<UsuariosDto>> listarTodos() {
		log.info("Listando todos");
		Response<UsuariosDto> response = new Response<UsuariosDto>();
		List<Usuarios> listaUsuario = usuarioService.buscarTodos();
		if (listaUsuario == null) {
			log.info("Nenhum encontrado");
			response.getErrors().add("Nenhum encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		response.setDatas(this.usuarioService.converterListaUsuarioDto(listaUsuario));
		return ResponseEntity.ok(response);
	}
		
	@PostMapping
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<UsuariosDto>> Adicionar(@Valid @RequestBody UsuariosDto usuariosDto,
			BindingResult result) throws ParseException {
		log.info("Adicionando: {}", usuariosDto.toString());
		Response<UsuariosDto> response = new Response<UsuariosDto>();
		Usuarios usuarios = this.usuarioService.converterUsuario(usuariosDto);
		usuarios.setSenha(PasswordUtils.gerarBCrypt(usuarios.getSenha()));
		usuarios = this.usuarioService.persistir(usuarios);
		response.setData(this.usuarioService.converterUsuarioDto(usuarios));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> Apagar(@PathVariable("codigo") String codigo) {
		log.info("Removendo: {}", codigo);
		Response<String> response = new Response<String>();
		Usuarios usuarios = usuarioService.buscarPorCodigo(codigo);
		if (usuarios == null) {
			log.info("Erro ao remover {} inválido.", codigo);
			response.getErrors().add("Erro ao remover " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		this.usuarioService.apagar(usuarios);
		return ResponseEntity.ok(new Response<String>());
	}
	
}
