package br.com.santander.controle.api.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.controle.api.dtos.UsuariosDto;
import br.com.santander.controle.api.entities.Usuarios;
import br.com.santander.controle.api.repositories.UsuarioRepository;
import br.com.santander.controle.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService  {

	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
			
	@Override
	public Usuarios persistir(Usuarios usuarios) {
		log.info("Persistindo: {}", usuarios);
		return this.usuarioRepository.save(usuarios);
	}

	@Override
	public void apagar(Usuarios usuarios) {
		log.info("Apagando: {}", usuarios);
		this.usuarioRepository.delete(usuarios);
	}

	@Override
	public Usuarios buscarPorCodigo(String codigo) {
		log.info("Buscando: {}", codigo);
		return this.usuarioRepository.findOne(codigo);
	}

	@Override
	public Usuarios buscarPorNome(String nome) {
		log.info("Buscando: {}", nome);
		return this.usuarioRepository.findByNome(nome);
	}

	@Override
	public Usuarios buscarPorLogin(String login) {
		log.info("Buscando: {}", login);
		return this.usuarioRepository.findByLogin(login);
	}

	@Override
	public Usuarios buscaPorEmail(String email) {
		log.info("Buscando: {}", email);
		return this.usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuarios buscarPorLoginSenha(String login, String senha) {
		log.info("Buscando: {}, {}", login, senha);
		return this.usuarioRepository.findByLoginAndSenha(login, senha);
	}

	@Override
	public List<Usuarios> buscarTodos() {
		log.info("Listando");
		return this.usuarioRepository.findAll();
	}
	
	@Override
	public List<UsuariosDto> converterListaUsuarioDto(List<Usuarios> listaUsuarios) {
		List<UsuariosDto> listaUsuariosDto = new LinkedList<UsuariosDto>();
		for (Usuarios usuarios: listaUsuarios) {
			UsuariosDto usuariosDto = new UsuariosDto();
			usuariosDto.setCodigo(usuarios.getCodigo());
			usuariosDto.setNome(usuarios.getNome());
			usuariosDto.setLogin(usuarios.getLogin());
			usuariosDto.setSenha(usuarios.getSenha());
			usuariosDto.setEmail(usuarios.getEmail());
			usuariosDto.setPerfil(usuarios.getPerfil());
			listaUsuariosDto.add(usuariosDto);
			
		}
		return listaUsuariosDto;
	}

	@Override
	public UsuariosDto converterUsuarioDto(Usuarios usuarios) {
		UsuariosDto usuariosDto = new UsuariosDto();
		usuariosDto.setCodigo(usuarios.getCodigo());
		usuariosDto.setNome(usuarios.getNome());
		usuariosDto.setLogin(usuarios.getLogin());
		usuariosDto.setSenha(usuarios.getSenha());
		usuariosDto.setEmail(usuarios.getEmail());
		usuariosDto.setPerfil(usuarios.getPerfil());
		return usuariosDto;
	}
	
	@Override
	public Usuarios converterUsuario(UsuariosDto usuariosDto) {
		Usuarios usuarios = new Usuarios();
		usuarios.setCodigo(usuariosDto.getCodigo());
		usuarios.setNome(usuariosDto.getNome());
		usuarios.setLogin(usuariosDto.getLogin());
		usuarios.setSenha(usuariosDto.getSenha());
		usuarios.setEmail(usuariosDto.getEmail());
		usuarios.setPerfil(usuariosDto.getPerfil());
		return usuarios;
	}

}
