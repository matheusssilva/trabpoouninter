package com.trabpoouninter;

public class Euro extends Moeda{

    public Euro(Double valor) {
        super(valor, "EUR");
    }

    @Override
    public Moeda ToReal() {
        return null;
    }

    @Override
    public String Info() {
        return "Euro";
    }
    
}
