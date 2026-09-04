package com.example.gestorfinanceiro;

public class Divida {
    private int idDivida;
    private double valor;
    private String descricao;
    private String tipoDivida;
    private int idCategoria;

    public Divida(int idDivida, double valor, String descricao, String tipoDivida, int idCategoria) {
        this.idDivida = idDivida;
        this.valor = valor;
        this.descricao = descricao;
        this.tipoDivida = tipoDivida;
        this.idCategoria = idCategoria;
    }

    public Divida(double valor, String descricao, String tipoDivida, int idCategoria) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipoDivida = tipoDivida;
        this.idCategoria = idCategoria;
    }

    public int getIdDivida() { return idDivida; }
    public void setIdDivida(int idDivida) { this.idDivida = idDivida; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTipoDivida() { return tipoDivida; }
    public void setTipoDivida(String tipoDivida) { this.tipoDivida = tipoDivida; }
    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
}