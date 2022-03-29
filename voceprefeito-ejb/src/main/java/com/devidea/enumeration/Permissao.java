package com.devidea.enumeration;

import lombok.Getter;

public enum Permissao {
			
	ACESSO_SUSPEITA("Acesso de Supeita"),
	ATENDENTE("Atendente"),
	ASSISTENTE_SOCIAL("Assistente Social"),
	ADVOGADA("Advogada"),
	ACESSO_TOTAL("Acesso Total");

    @Getter
    private String descricao;

    Permissao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}