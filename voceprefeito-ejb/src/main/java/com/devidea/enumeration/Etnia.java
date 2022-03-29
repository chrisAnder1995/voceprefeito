package com.devidea.enumeration;

public enum Etnia {

	
	AMARELA("Amarela"),
	BRANCA("Branca"),
	INDIGENA("Indí­gena"),
	NEGRA("Negra"),
	PARDA("Parda"),
	OUTRA("Outra");
	
	private String descricao;
	
	private Etnia(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	


}
