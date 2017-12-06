package com.juliao.adryel.arvore;

import java.util.ArrayList;

public class Ocorrencia {
    private String nome;
    private String descricao;
    private String observaocao;
    private String nomeUsuario;

    public Ocorrencia() {
    }

    public Ocorrencia(String nome, String descricao, String observaocao, String nomeUsuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.observaocao = observaocao;
        this.nomeUsuario = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservaocao() {
        return observaocao;
    }

    public void setObservaocao(String observaocao) {
        this.observaocao = observaocao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
