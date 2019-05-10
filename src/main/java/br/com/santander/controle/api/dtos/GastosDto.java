package br.com.santander.controle.api.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class GastosDto {
	
	private String codigo;
	private String descricao;
	private BigDecimal valor;
	private String codigoUsuario;
	private Date data;
	
	public GastosDto() {
		super();

	}

	public GastosDto(String codigo, String descricao, BigDecimal valor, String codigoUsuario, Date data) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.codigoUsuario = codigoUsuario;
		this.data = data;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GastosDto [codigo=" + codigo + ", descricao=" + descricao + ", valor=" + valor + ", codigoUsuario="
				+ codigoUsuario + ", data=" + data + "]";
	}

}
