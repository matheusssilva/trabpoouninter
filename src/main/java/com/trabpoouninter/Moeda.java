package com.trabpoouninter;

public abstract class Moeda {
    private Double valor;
    private String sigla;

    public Double getValor() {
        return valor;
    }

    public String getSigla() {
        return sigla;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Moeda(Double valor, String sigla) {
        this.valor = valor;
        this.sigla = sigla;
    }

    /**
     * Métodos abstratos que devem ser sobrescritos nas classes derivadas.
     */

    public abstract Moeda ToReal();
    public abstract String Info();
}
