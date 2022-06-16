package com.example.codescanner.model;

public class Produto {

    private Long id;

    private String nome;

    private double quantidade;

    private String descricao;

    private Apreensao apreensao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Apreensao getApreensao() {
        return apreensao;
    }

    public void setApreensao(Apreensao apreensao) {
        this.apreensao = apreensao;
    }
}
