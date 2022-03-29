package com.devidea.enumeration;

import lombok.Getter;

public enum BuscaServico {
	ESPONTANEA("Espont�nea"),
	ENCAMINHADA("Encaminhada");

    @Getter
    private String descricao;

    BuscaServico(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
