package com.example.gestorfinanceiro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class VerCategoriasActivity extends AppCompatActivity {

    private CategoriaDAO categoriaDAO;
    private ListView listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_categorias);

        categoriaDAO = new CategoriaDAO(this);
        listaCategorias = findViewById(R.id.listaCategorias);

        carregarLista();
    }

    private void carregarLista() {
        List<Categoria> categorias = categoriaDAO.listarTodas();
        CategoriaAdapter adapter = new CategoriaAdapter(categorias);
        listaCategorias.setAdapter(adapter);
    }

    private class CategoriaAdapter extends ArrayAdapter<Categoria> {
        public CategoriaAdapter(List<Categoria> categorias) {
            super(VerCategoriasActivity.this, 0, categorias);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_categoria_lista, parent, false);
            }

            Categoria categoria = getItem(position);
            TextView textoRegistro = convertView.findViewById(R.id.textoRegistroCategoria);
            Button btnDeletar = convertView.findViewById(R.id.btnDeletarCategoria);

            textoRegistro.setText(categoria.getNome());

            btnDeletar.setOnClickListener(v -> {
                categoriaDAO.deletar(categoria.getId_categoria());
                carregarLista();
            });

            return convertView;
        }
    }
}