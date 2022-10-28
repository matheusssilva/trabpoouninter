package com.trabpoouninter;

public class Dolar extends Moeda{

    public Dolar(Double valor) {
        super(valor, "USD");
    }

    @Override
    public Moeda ToReal() {
        return null;
    }

    @Override
    public String Info() {
        return "Dolar Americano";
    }
    
}
