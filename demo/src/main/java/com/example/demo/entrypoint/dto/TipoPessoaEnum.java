package com.example.demo.entrypoint.dto;

import java.util.Arrays;

public enum TipoPessoaEnum {

    FISICA("PF"),
    JURIDICA("PJ");

    private String tipoPessoa;

    TipoPessoaEnum(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public boolean isPJ(String tipoPessoa) {
        return tipoPessoa.equalsIgnoreCase(JURIDICA.name());
    }

    public static boolean isValid(String tipoPessoa) {

        return Arrays.stream(values())
                .anyMatch(value -> value.name().equalsIgnoreCase(tipoPessoa));
    }
}
