package br.com.santander.controle.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Gastos")
public class Gastos implements Serializable  {

	private static final long serialVersionUID = -5664192193284474715L;

	@Id
	private String codigo;
	private String descricao;
	private BigDecimal valor;
	@Indexed
	private String codigoUsuario;
	@Indexed
	private Date data;
	
	public Gastos() {
		super();

	}

	public Gastos(String codigo, String descricao, BigDecimal valor, String codigoUsuario, Date data) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Gastos [codigo=" + codigo + ", descricao=" + descricao + ", valor=" + valor + ", codigoUsuario="
				+ codigoUsuario + ", data=" + data + "]";
	}
	
}
