package br.com.santander.controle.api.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import br.com.santander.controle.api.enums.PerfilEnum;

@RedisHash("Usuarios")
public class Usuarios implements Serializable {

	private static final long serialVersionUID = 3241494029658453404L;
	
	@Id
	private String codigo;
	private String nome;
	@Indexed
	private String login;
	@Indexed
	private String senha;
	@Indexed
	private String email;
	private PerfilEnum perfil;
	
	public Usuarios() {
		super();
	}

	public Usuarios(String codigo, String nome, String login, String senha, String email, PerfilEnum perfil) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.perfil = perfil;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Usuarios [codigo=" + codigo + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", email="
				+ email + ", perfil=" + perfil + "]";
	}
		
}
