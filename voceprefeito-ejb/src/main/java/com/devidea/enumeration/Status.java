package com.devidea.enumeration;

import lombok.Getter;

@Getter
public enum Status {

    NOVA_DENUNCIA("Nova Denúncia"),
    NOVA_SUSPEITA("Nova Suspeita"),
    EM_ATENDIMENTO("Em Atendimento"),
    SUSPEITA_DESCARTADA("Suspeita Descartada"),
    FECHADO("Fechado");

    private String description;

    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return description;
    }
}