package com.example.gestorfinanceiro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    private SQLiteDatabase db;
    private BancoHelper banco;

    public TransacaoDAO(Context context) {
        banco = new BancoHelper(context);
    }

    public void inserir(Transacao transacao) {
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("descricao", transacao.getDescricao());
        values.put("valor", transacao.getValor());
        values.put("data_transacao", transacao.getDataTransacao().toString());
        values.put("id_categoria", transacao.getIdCategoria());
        values.put("tipo_pagamento", transacao.getTipoPagamento());
        values.put("tipo_transacao", transacao.getTipoTransacao());


        db.insert("transacao", null, values);
        db.close();
    }

    public List<Transacao> listarTodas() {
        List<Transacao> listTransacao = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM transacao", null);

        while (cursor.moveToNext()) {
            int id_transacao = cursor.getInt(cursor.getColumnIndexOrThrow("id_transacao"));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
            double valor = cursor.getDouble(cursor.getColumnIndexOrThrow("valor"));
            LocalDate data_transacao = LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow("data_transacao")));
            int id_categoria = cursor.getInt(cursor.getColumnIndexOrThrow("id_categoria"));
            String tipo_pagamento = cursor.getString(cursor.getColumnIndexOrThrow("tipo_pagamento"));
            String tipo_transacao = cursor.getString(cursor.getColumnIndexOrThrow("tipo_transacao"));

            Transacao transacao = new Transacao(id_transacao, descricao, valor, data_transacao, id_categoria, tipo_pagamento, tipo_transacao);
            listTransacao.add(transacao);
        }
        cursor.close();
        db.close();
        return listTransacao;
    }
}