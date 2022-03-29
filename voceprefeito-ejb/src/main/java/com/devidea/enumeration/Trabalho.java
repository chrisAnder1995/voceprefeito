package com.devidea.enumeration;

public enum Trabalho {

	
	TRABALHO_FORMALMENTE   ("Trabalhando Formalmente"),          
	FAZENDO_BICO 		  ("Trabalho Fazendo Bicos") ,
	EMPREENDEDOR   		  ("Sou Empreendendor") ,  
	DESEMPREGADO       	  ("Sou Desempregador") ,      
	APOSENTADO         	  ("Sou Aposentado") ,        
	ESTAGIARIO    		  ("Sou Estudante/Estagiario") ;  
	
	private String descricao;
	
	private Trabalho(String descricao) {
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
