package com.devidea.enumeration;

import lombok.Getter;

public enum TempoGestacao {

	NAO("N�o"),
	TRIMESTRE1("1� Trimestre"),
	TRIMESTRE2("2� Trimestre"),
	TRIMESTRE3("3� Trimestre");

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
