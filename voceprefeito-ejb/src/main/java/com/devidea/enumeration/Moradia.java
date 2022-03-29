package com.devidea.enumeration;

public enum Moradia {

	
	PROPRIA("Pr�pria"),
	ALUGADA("Alugada"),
	CEDIDA("Cedida"),
	OCUPADA("Ocupada"),
	BENEFICIO_SOCIAL("Benef�cio Social"),
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
