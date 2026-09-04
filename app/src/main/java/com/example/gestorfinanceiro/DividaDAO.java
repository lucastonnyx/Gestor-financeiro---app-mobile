package com.example.gestorfinanceiro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DividaDAO {

    private SQLiteDatabase db;
    private BancoHelper banco;

    public DividaDAO(Context context){
        banco = new BancoHelper(context);
    }

    public void inserir(Divida divida){
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("valor", divida.getValor());
        values.put("descricao", divida.getDescricao());
        values.put("tipoDivida", divida.getTipoDivida());
        values.put("idCategoria", divida.getIdCategoria());

        db.insert("divida", null, values);
        db.close();
    }

    public List<Divida> listarTodas() {
        List<Divida> listDivida = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM divida", null);

        while (cursor.moveToNext()) {
            int idDivida = cursor.getInt(cursor.getColumnIndexOrThrow("idDivida"));
            double valor = cursor.getDouble(cursor.getColumnIndexOrThrow("valor"));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
            String tipoDivida = cursor.getString(cursor.getColumnIndexOrThrow("tipoDivida"));
            int idCategoria = cursor.getInt(cursor.getColumnIndexOrThrow("idCategoria"));

            Divida divida = new Divida(idDivida, valor, descricao, tipoDivida, idCategoria);
            listDivida.add(divida);
        }
        cursor.close();
        db.close();
        return listDivida;
    }

    public void deletar(int idDivida) {
        db = banco.getWritableDatabase();
        db.delete("divida", "idDivida = ?", new String[]{String.valueOf(idDivida)});
        db.close();
    }
}