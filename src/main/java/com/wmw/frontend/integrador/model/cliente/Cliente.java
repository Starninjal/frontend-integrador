package com.wmw.frontend.integrador.model.cliente;

import java.util.Objects;

import com.wmw.frontend.integrador.dto.ClienteDTO;

public class Cliente {
	private Long id;
	
	private String nome;
	
	private TipoPessoa tipoPessoa;
	
	private String documento;

	private String telefone;
	
	private String email;
	
	public Cliente() {
		
	}
	
	public Cliente(ClienteDTO clienteDTO) {
		this.id = clienteDTO.getId();
		this.documento = clienteDTO.getDocumento();
		this.email = clienteDTO.getEmail();
		this.nome = clienteDTO.getNome();
		this.telefone = clienteDTO.getTelefone();
		this.tipoPessoa = TipoPessoa.valueOf(clienteDTO.getTipoPessoa());
	}


	public Cliente(String nome, TipoPessoa tipoPessoa, String documento, String telefone, String email) {
		this.nome = nome;
		this.tipoPessoa = tipoPessoa;
		this.documento = documento;
		this.telefone = telefone;
		this.email = email;
	}
	
	public Cliente(Long id, String nome, TipoPessoa tipoPessoa, String documento, String telefone, String email) {
		this(nome, tipoPessoa, documento, telefone, email);
		this.id = id;
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

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
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


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.getNome()) &&
                tipoPessoa == cliente.getTipoPessoa() &&
                Objects.equals(documento, cliente.getDocumento()) &&
                Objects.equals(telefone, cliente.getTelefone()) &&
                Objects.equals(email, cliente.getEmail());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente ID: ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Telefone: ").append(telefone).append("\n");
        sb.append("Email: ").append(email + "\n\n");
        return sb.toString();
    }
    
    
}
