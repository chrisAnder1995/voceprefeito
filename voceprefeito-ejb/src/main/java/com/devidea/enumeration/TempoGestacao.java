package com.devidea.enumeration;

import lombok.Getter;

public enum TempoGestacao {

	NAO("Não"),
	TRIMESTRE1("1º Trimestre"),
	TRIMESTRE2("2º Trimestre"),
	TRIMESTRE3("3º Trimestre");

    @Getter
    private String descricao;

    TempoGestacao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
