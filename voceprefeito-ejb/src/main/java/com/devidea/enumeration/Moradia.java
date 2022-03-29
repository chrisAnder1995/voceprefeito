package com.devidea.enumeration;

public enum Moradia {

	
	PROPRIA("Própria"),
	ALUGADA("Alugada"),
	CEDIDA("Cedida"),
	OCUPADA("Ocupada"),
	BENEFICIO_SOCIAL("Benefício Social"),
	TERCEIROS("Terceiros"),
	OUTROS("Outros");
	
	private String descricao;
	
	private Moradia(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
