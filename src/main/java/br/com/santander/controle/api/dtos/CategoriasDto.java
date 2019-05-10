package br.com.santander.controle.api.dtos;

public class CategoriasDto {
	
	private String codigo;
	private String CodigoUsuario;
	private String categoria;
	
	public CategoriasDto() {
		super();
	}

	public CategoriasDto(String codigo, String codigoUsuario, String categoria) {
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

	@Override
	public String toString() {
		return "CategoriasDto [codigo=" + codigo + ", CodigoUsuario=" + CodigoUsuario + ", categoria=" + categoria + "]";
	}

}
