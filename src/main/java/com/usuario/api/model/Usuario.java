package com.usuario.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "USERS")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518114442559274399L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IN_CODIGO", nullable = false)
	private Integer codigo;
	
	@NotEmpty
	@Column(name = "NV_PRIMER_NOMBRE", nullable = false)
	private String primerNombre;
	
	@NotEmpty
	@Column(name = "NV_SEGUNDO_NOMBRE", nullable = false)
	private String segundoNombre;

	@NotEmpty
	@Column(name = "NV_LOGIN", nullable = false)
	private String login;

	@NotEmpty
	@Column(name = "NV_EMAIL", nullable = false)
	private String email;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
