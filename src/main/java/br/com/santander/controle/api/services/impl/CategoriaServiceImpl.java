package br.com.santander.controle.api.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.controle.api.dtos.CategoriasDto;
import br.com.santander.controle.api.entities.Categorias;
import br.com.santander.controle.api.repositories.CategoriaRepository;
import br.com.santander.controle.api.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Categorias persistir(Categorias categorias) {
		log.info("Persistindo: {}", categorias);
		return this.categoriaRepository.save(categorias);
	}

	@Override
	public void apagar(Categorias categorias) {
		log.info("Apagando: {}", categorias);
		this.categoriaRepository.delete(categorias);
	}

	@Override
	public Categorias buscarPorCodigo(String codigo) {
		log.info("Buscando: {}", codigo);
		return this.categoriaRepository.findOne(codigo);
	}

	@Override
	public Categorias buscarPorCodigoUsuario(String codigoUsuario) {
		log.info("Buscando: {}", codigoUsuario);
		return this.categoriaRepository.findByCodigoUsuario(codigoUsuario);
	}

	@Override
	public Categorias buscarPorCategoria(String categoria) {
		log.info("Buscando: {}", categoria);
		return this.categoriaRepository.findByCategoria(categoria);
	}

	@Override
	public List<Categorias> buscarTodos() {
		log.info("Listando");
		return this.categoriaRepository.findAll();
	}

	@Override
	public List<CategoriasDto> converterCategoriasDto(List<Categorias> listCategorias) {
		List<CategoriasDto> listaCategorias = new LinkedList<CategoriasDto>();
		for (Categorias categorias : listCategorias) {
			CategoriasDto categoriasDto = new CategoriasDto();
			categoriasDto.setCodigo(categorias.getCodigo());
			categoriasDto.setCodigoUsuario(categorias.getCodigoUsuario());
			categoriasDto.setCategoria(categorias.getCategoria());
			listaCategorias.add(categoriasDto);
		}
		return listaCategorias;
	}

	@Override
	public CategoriasDto converterCategoriasDto(Categorias categorias) {
		CategoriasDto categoriasDto = new CategoriasDto();
		categoriasDto.setCodigo(categorias.getCodigo());
		categoriasDto.setCodigoUsuario(categorias.getCodigoUsuario());
		categoriasDto.setCategoria(categorias.getCategoria());
		return categoriasDto;
	}

	@Override
	public Categorias converterCategorias(CategoriasDto categoriasDto) {
		Categorias categorias = new Categorias();
		categorias.setCodigo(categoriasDto.getCodigo());
		categorias.setCodigoUsuario(categoriasDto.getCodigoUsuario());
		categorias.setCategoria(categoriasDto.getCategoria());
		return categorias;
	}

}
