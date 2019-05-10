package br.com.santander.controle.api.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Categorias")
public class Categorias implements Serializable {
	
	private static final long serialVersionUID = 7748997959628737905L;
	
	@Id
	private String codigo;
	@Indexed
	private String CodigoUsuario;
	@Indexed
	private String categoria;
	
	public Categorias() {
		super();
	}

	public Categorias(String codigo, String codigoUsuario, String categoria) {
		super();
		this.codigo = codigo;
		CodigoUsuario = codigoUsuario;
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoUsuario() {
		return CodigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		CodigoUsuario = codigoUsuario;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Categorias [codigo=" + codigo + ", CodigoUsuario=" + CodigoUsuario + ", categoria=" + categoria + "]";
	}
	
}
