package com.juliao.adryel.arvore;

import java.util.List;

public class Arvore {
    private String nome;
    private String descricao;
    private double latitude;
    private double longitude;
    private String imagem;//string URL PARA O FIREBASE STOREGE
    private String altura;
    private String especie;
    private String nomeUsuario;
    private List<Ocorrencia> listaOcorrencia;

    public Arvore (){
    }

    public Arvore(String nome, String descricao, double latitude,
                  double longitude, String imagem, String altura,
                  String especie, String nomeUsuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imagem = imagem;
        this.altura = altura;
        this.especie = especie;
        this.nomeUsuario = nomeUsuario;
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


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Arvore{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", imagem='" + imagem + '\'' +
                ", altura='" + altura + '\'' +
                ", especie='" + especie + '\'' +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                '}';
    }
}
