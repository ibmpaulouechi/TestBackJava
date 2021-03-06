package br.com.santander.controle.api.dtos;

import br.com.santander.controle.api.enums.PerfilEnum;

public class UsuariosDto {
	
	private String codigo;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private PerfilEnum perfil;
	
	public UsuariosDto() {
		super();
	}

	public UsuariosDto(String codigo, String nome, String login, String senha, String email, PerfilEnum perfil) {
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

	@Override
	public String toString() {
		return "UsuariosDto [codigo=" + codigo + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", email="
				+ email + ", perfil=" + perfil + "]";
	}

}
