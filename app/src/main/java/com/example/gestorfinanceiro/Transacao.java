package com.example.gestorfinanceiro;

import java.time.LocalDate;

public class Transacao {

    private int idTransacao;
    private String descricao;
    private double valor;
    private LocalDate dataTransacao;
    private int idCategoria;
    private String tipoPagamento;

    private String tipoTransacao;

    public Transacao(int idTransacao, String descricao, double valor, LocalDate dataTransacao, int idCategoria, String tipoPagamento, String tipoTransacao) {
        this.idTransacao = idTransacao;
        this.descricao = descricao;
        this.valor = valor;
        this.dataTransacao =  dataTransacao;
        this.idCategoria = idCategoria;
        this.tipoPagamento = tipoPagamento;
        this.tipoTransacao = tipoTransacao;
    }

    public Transacao(String descricao, double valor, LocalDate dataTransacao, int idCategoria, String tipoPagamento, String tipoTransacao) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.idCategoria = idCategoria;
        this.tipoPagamento = tipoPagamento;
        this.tipoTransacao = tipoTransacao;
    }


    public int getIdTransacao() {return idTransacao;
    }
    public String getDescricao() {return descricao;}

    public double getValor() {return valor;}

    public LocalDate getDataTransacao() {return dataTransacao;}

    public int getIdCategoria() {return idCategoria;}

    public String getTipoPagamento() {return tipoPagamento;}


    public String getTipoTransacao() { return tipoTransacao;}
}