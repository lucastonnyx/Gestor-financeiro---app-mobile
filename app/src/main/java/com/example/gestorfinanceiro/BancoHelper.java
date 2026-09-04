package com.example.gestorfinanceiro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "GestorFinanceiro.db";
    private static final int VERSAO = 1;

    public BancoHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCategoria = "CREATE TABLE categoria (" +
                "id_categoria INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL)";
        db.execSQL(sqlCategoria);

        String sqlTransacao = "CREATE TABLE transacao (" +
                "id_transacao INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT DEFAULT 'GENERIC', " +
                "valor REAL NOT NULL, " +
                "data_transacao TEXT NOT NULL, " +
                "id_categoria INTEGER, " +
                "tipo_transacao TEXT DEFAULT 'Não informado'," +
                "tipo_pagamento TEXT DEFAULT 'Não informado', " +
                "FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria))";
        db.execSQL(sqlTransacao);

        String CREATE_TABLE_DIVIDA = "CREATE TABLE divida (" +
                "idDivida INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "valor REAL, " +
                "descricao TEXT, " +
                "tipoDivida TEXT, " +
                "idCategoria INTEGER, " +
                "FOREIGN KEY(idCategoria)REFERENCES categoria(idCategoria))";

        db.execSQL(CREATE_TABLE_DIVIDA);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS transacao");
        db.execSQL("DROP TABLE IF EXISTS categoria");
        db.execSQL("DROP TABLE IF EXISTS divida");
        onCreate(db);
    }
}
