package com.devidea.enumeration;

import lombok.Getter;

public enum ViolenciaCategoria {

	FISICA("F�sica"),
	PSICOLOGICA("Psicol�gica"),
	PATRIMONIAL("Patrimonial"),
	SEXUAL("Sexual"),
	MORAL("Moral");

    @Getter
    private String descricao;

    ViolenciaCategoria(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
