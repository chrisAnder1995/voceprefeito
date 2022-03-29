package com.devidea.enumeration;

public enum Parentesco {

	MAEPAI("Mãe/Pai"),
	IRMAO("Irmã(ão)"),
	CONJUGE("Cônjuge"),
	FILHO("Filha(o)"),
	NETO("Neta(o)"),
	VIUVO("Viúva(o)"),
	COMPANHEIRO("Companheira(o)"),
	SENTENCA_JUDICIAL("Sentença Judicial"),
	AVO("Avó(ô)"), 
	OUTRO("Outro");

	private String descricao;

	Parentesco(String descricao) {
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
