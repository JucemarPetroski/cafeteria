
package com.senac.aula_api.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;

public class CPF {
    private final String cpf;

    @JsonValue
    public String getNumero() {
        return cpf;
    }

    public CPF(String cpf) {
        if (cpf == null || !isCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        this.cpf = cpf.replaceAll("[^0-9]", "");
    }

    @Override
    public String toString() {
        return this.cpf;
    }

    public static boolean isCPF(String cpf) {
        if (cpf == null) return false;
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;
        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito > 9) primeiroDigito = 0;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito > 9) segundoDigito = 0;

            return Character.getNumericValue(cpf.charAt(9)) == primeiroDigito &&
                   Character.getNumericValue(cpf.charAt(10)) == segundoDigito;
        } catch (Exception e) {
            return false;
        }
    }
}
