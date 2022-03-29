package com.devidea.enumeration;

public enum Sexo {

	
	Masculino ("Masculino") ,
	Feminino ("Feminino") ,
	Outro ( "Outro");
	
	private String descricao;
	
	private Sexo(String descricao) {
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
