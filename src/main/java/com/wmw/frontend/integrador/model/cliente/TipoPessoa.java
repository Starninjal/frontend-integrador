package com.wmw.frontend.integrador.model.cliente;

public enum TipoPessoa {
	FISICA("FISICA"),
	JURIDICA("JURIDICA");
	
	TipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	private String tipoPessoa;
}
