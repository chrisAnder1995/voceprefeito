package com.devidea.enumeration;

public enum Parentesco {

	MAEPAI("M�e/Pai"),
	IRMAO("Irm�(�o)"),
	CONJUGE("C�njuge"),
	FILHO("Filha(o)"),
	NETO("Neta(o)"),
	VIUVO("Vi�va(o)"),
	COMPANHEIRO("Companheira(o)"),
	SENTENCA_JUDICIAL("Senten�a Judicial"),
	AVO("Av�(�)"), 
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
