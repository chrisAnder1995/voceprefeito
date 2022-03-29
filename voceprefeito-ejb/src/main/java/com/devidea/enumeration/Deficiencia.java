package com.devidea.enumeration;

public enum Deficiencia {

	NENHUMA("Nenhuma"),
	AUDITIVA("Auditiva"),
	FISICA("Física"),
	MENTAL("Mental"),
	VISUAL("Visual"),
	MULTIPLA("Múltipla");
	
	private String descricao;
	
	private Deficiencia(String descricao) {
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
