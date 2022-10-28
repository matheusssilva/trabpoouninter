package com.trabpoouninter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CotacaoService { // Objeto responssável por buscar cotação das moedas na internet.

    private final String URLAPI = "https://economia.awesomeapi.com.br/json/last/"; // Parte fixa da url da API

    private Moeda origeMoeda, destinMoeda;

    public CotacaoService(Moeda origeMoeda, Moeda destinMoeda) {
        this.origeMoeda = origeMoeda;
        this.destinMoeda = destinMoeda;
    }

    public Moeda getDestinMoeda() {
        return destinMoeda;
    }

    public void setDestinMoeda(Moeda destinMoeda) {
        this.destinMoeda = destinMoeda;
    }

    public Moeda getOrigeMoeda() {
        return origeMoeda;
    }

    public void setOrigeMoeda(Moeda origeMoeda) {
        this.origeMoeda = origeMoeda;
    }

    /*Função que busca a cotação na internet */
    public Moeda getCotacaMoeda() {

        String urlCotacao = URLAPI.concat(this.origeMoeda.getSigla()).concat("-" + this.destinMoeda.getSigla()); // URL da API
        
        try {
            URL servUrl = new URL(urlCotacao);
            HttpURLConnection con = (HttpURLConnection) servUrl.openConnection();
            
            if (con.getResponseCode() != 200){
                throw new RuntimeException("Erro HTTP código : " + con.getResponseCode());
            }
            
            BufferedReader resp = new BufferedReader(new InputStreamReader((con.getInputStream()))); // Buffer de leitura da resposta
            StringBuilder sb = new StringBuilder(); // String com o conteúdo do buffer de resposta
            String line;

            while ((line = resp.readLine()) != null) { // Percorre o Json retornado
                sb.append(line); // Adiciona cada linha do buffer na string
            }

            JsonObject jo = new Gson().fromJson(sb.toString(), JsonObject.class); // Converte a string json em JsonObject.

            // Obtem o valor do elemento json desejado
            Double val = jo.get(this.origeMoeda.getSigla() 
                + this.destinMoeda.getSigla()).getAsJsonObject().get("bid").getAsDouble();

            destinMoeda.setValor(val); // Adiciona o valor obtido na moeda de destino

        } catch (Exception e) {
            System.err.println("Erro ao tentar conectar no servidor: " + e);
        }
        return destinMoeda;
    }
}