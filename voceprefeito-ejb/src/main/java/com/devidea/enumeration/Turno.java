package com.devidea.enumeration;

import lombok.Getter;

public enum Turno {

	MANHA("Manhã"),
	TARDE("Tarde"),
	NOITE("Noite"),
	MADRUGADA("Madrugada");

    @Getter
    private String descricao;

    Turno(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
