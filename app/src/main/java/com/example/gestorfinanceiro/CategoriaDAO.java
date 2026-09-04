package com.example.gestorfinanceiro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private SQLiteDatabase db;
    private BancoHelper banco;

    public CategoriaDAO(Context context) {
        banco = new BancoHelper(context);
    }

    public void inserir(Categoria categoria) {
        db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", categoria.getNome());

        db.insert("categoria", null, values);
        db.close();
    }

    public List<Categoria> listarTodas() {
        List<Categoria> listCategoria = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM categoria", null);

        while (cursor.moveToNext()) {
            int id_categoria = cursor.getInt(cursor.getColumnIndexOrThrow("id_categoria"));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));

            Categoria categoria = new Categoria(id_categoria, nome);
            listCategoria.add(categoria);
        }
        cursor.close();
        db.close();
        return listCategoria;
    }

    public void deletar(int id_categoria) {
        db = banco.getWritableDatabase();
        db.delete("categoria", "id_categoria = ?", new String[]{String.valueOf(id_categoria)});
        db.close();
    }
}