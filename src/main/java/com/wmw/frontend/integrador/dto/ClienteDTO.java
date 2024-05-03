package com.wmw.frontend.integrador.dto;

import com.wmw.frontend.integrador.model.cliente.Cliente;
import com.wmw.frontend.integrador.model.cliente.TipoPessoa;

public class ClienteDTO {
	private Long id;

	private String nome;

	private String tipoPessoa;

	private String documento;

	private String telefone;

	private String email;
	
	public ClienteDTO(String nome, TipoPessoa tipoPessoa, String documento, String telefone, String email) {
		this.nome = nome;
		this.tipoPessoa = tipoPessoa.toString();
		this.documento = documento;
		this.telefone = telefone;
		this.email = email;
	}
	
	public ClienteDTO(String telefone, String email) {
		this.telefone = telefone;
		this.email = email;
	}

	public ClienteDTO(Long id, String nome, TipoPessoa tipoPessoa, String documento, String telefone, String email) {
		this(nome, tipoPessoa, documento, telefone, email);
		this.id = id;
	}

	public static ClienteDTO fromCliente(Cliente cliente) {
		return new ClienteDTO(
				cliente.getId(),
				cliente.getNome(),
				cliente.getTipoPessoa(),
				cliente.getDocumento(),
				cliente.getTelefone(),
				cliente.getEmail());
	}

	public ClienteDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ClienteDTO [id=" + id + ", nome=" + nome + ", tipoPessoa=" + tipoPessoa + ", documento=" + documento
				+ ", telefone=" + telefone + ", email=" + email + "]";
	}
	
	

}
