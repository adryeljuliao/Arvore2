package com.juliao.adryel.arvore;

/**
 * Created by adryel.juliao on 22/11/2017.
 */

public class Arvore {

    private String nome;
    private double latitude;
    private double longitude;
    private int imagem;
    private String altura;
    private String especie;

    public Arvore(ArvoreBuilder builder) {
        this.nome = builder.nome;
        this.altura = builder.altura;
        this.especie = builder.especie;
        this.imagem = builder.imagem;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }


    public static class ArvoreBuilder{
        private String nome;
        private double latitude;
        private double longitude;
        private int imagem;
        private String altura;
        private String especie;

        public ArvoreBuilder(String nome, double latitude, double longitude, int imagem, String altura) {
            this.nome = nome;
            this.latitude = latitude;
            this.longitude = longitude;
            this.imagem = imagem;
            this.altura = altura;
        }

        public ArvoreBuilder comEspecie(String especie){
            this.especie = especie;
            return this;
        }

        public Arvore builder(){
            return new Arvore(this);
        }

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNome() {
        return nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getImagem() {
        return imagem;
    }

    public String getAltura() {
        return altura;
    }

    public String getEspecie() {
        return especie;
    }
}
