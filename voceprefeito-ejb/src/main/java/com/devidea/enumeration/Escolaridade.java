package com.devidea.enumeration;

public enum Escolaridade {

	
	SEM_ESCOLARIDADE          ("Sem escolaridade"),
	FUNDAMENTAL_INCOMPLETO("Fundamental Incompleto"),
	FUNDAMENTAL_COMPLETO  ("Fundamental Completo"),
	MEDIO_INCOMPLETO      ("Médio Incompleto"),
	MEDIO_COMPLETO        ("Médio Completo"),
	SUPERIOR_INCOMPLETO   ("Superior Incompleto"),
	SUPERIOR_COMPLETO     ("Superior Completo");
	
	private String descricao;
	
	private Escolaridade(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}

	
	


}
