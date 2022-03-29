package com.devidea.enumeration;

public enum EstadoCivil {

	
	SOLTEIRO("Solteira(o)"),
	CASADO("Casada(o)"),
	DIVORCIADO("Divorciada(o)"),
	VIUVO("Viúva(o)");
	
	private String descricao;
	
	EstadoCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}


	@Override
	public String toString() {
		return this.descricao;
	}


}
