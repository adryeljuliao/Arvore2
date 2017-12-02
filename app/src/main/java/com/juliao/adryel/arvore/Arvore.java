package com.juliao.adryel.arvore;

import java.util.List;

public class Arvore {
    private String nome;
    private double latitude;
    private double longitude;
    private String imagem;//string URL PARA O FIREBASE STOREGE
    private String altura;
    private String especie;
    private String nomeUsuario;
    private List<Ocorrencia> listaOcorrencia;

    public Arvore (){

    }

    public Arvore(ArvoreBuilder builder) {
        this.nome = builder.nome;
        this.altura = builder.altura;
        this.especie = builder.especie;
        this.imagem = builder.imagem;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.nomeUsuario = builder.nomeUsuario;
        this.listaOcorrencia = builder.listaOcorrencia;
    }


    public static class ArvoreBuilder{
        private String nome;
        private double latitude;
        private double longitude;
        private String imagem;
        private String altura;
        private String especie;
        private String nomeUsuario;
        private List<Ocorrencia> listaOcorrencia;

        public ArvoreBuilder(String nome, double latitude, double longitude, String imagem, String altura) {
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

        public ArvoreBuilder comOcorrencia(List<Ocorrencia> listaOcorrencia){
            this.listaOcorrencia = listaOcorrencia;
            return this;
        }

        public ArvoreBuilder comUsuario(String nomeUsuario){
            this.nomeUsuario = nomeUsuario;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getAltura() {
        return altura;
    }

    public String getEspecie() {
        return especie;
    }

    public List<Ocorrencia> getListaOcorrencia() {
        return listaOcorrencia;
    }

    public void setListaOcorrencia(List<Ocorrencia> listaOcorrencia) {
        this.listaOcorrencia = listaOcorrencia;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
