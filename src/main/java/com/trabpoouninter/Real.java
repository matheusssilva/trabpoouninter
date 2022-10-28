package com.trabpoouninter;
public class Real extends Moeda{

    public Real(Double valor) {
        super(valor, "BRL");
    }

    @Override
    public Moeda ToReal() {
        return null;
    }

    @Override
    public String Info() {
        return "Real Brasileiro";
    }
    
}
