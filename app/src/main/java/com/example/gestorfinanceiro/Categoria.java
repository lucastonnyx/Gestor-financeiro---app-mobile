package com.example.gestorfinanceiro;

public class Categoria {

    private int id_categoria;
    private String nome;


    public Categoria(int id_categoria, String nome) {
        this.id_categoria = id_categoria;
        this.nome = nome;

    }

    public Categoria(String nome) {
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public int getId_categoria() {
        return id_categoria;
    }







}