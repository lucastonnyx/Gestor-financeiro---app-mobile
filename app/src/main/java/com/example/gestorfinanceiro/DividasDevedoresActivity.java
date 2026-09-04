package com.example.gestorfinanceiro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class DividasDevedoresActivity extends AppCompatActivity {

    private DividaDAO dividaDAO;
    private CategoriaDAO categoriaDAO;
    private List<Categoria> listaCategorias;
    private ListView listaMinhasDividas;
    private ListView listaDevedores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dividas_devedores);

        dividaDAO = new DividaDAO(this);
        categoriaDAO = new CategoriaDAO(this);
        listaCategorias = categoriaDAO.listarTodas();

        Spinner spinnerCategoria = findViewById(R.id.spinnerCategoriaDivida);
        List<String> nomesCategorias = new ArrayList<>();

        nomesCategorias.add("-");

        for (Categoria c : listaCategorias) {
            nomesCategorias.add(c.getNome());
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(
                this,
                R.layout.item_spineer,
                android.R.id.text1,
                nomesCategorias
        );
        adapterSpinner.setDropDownViewResource(R.layout.item_spineer);
        spinnerCategoria.setAdapter(adapterSpinner);

        EditText editValor = findViewById(R.id.editValorDivida);
        EditText editDescricao = findViewById(R.id.editDescricaoDivida);
        RadioGroup radioGroup = findViewById(R.id.radioGroupTipoDivida);
        Button btnSalvar = findViewById(R.id.btnSalvarDivida);

        listaMinhasDividas = findViewById(R.id.listaMinhasDividas);
        listaDevedores = findViewById(R.id.listaDevedores);

        btnSalvar.setOnClickListener(v -> {
            String valorStr = editValor.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();
            int checkedId = radioGroup.getCheckedRadioButtonId();

            if (valorStr.isEmpty() || descricao.isEmpty() || checkedId == -1) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (listaCategorias.isEmpty()) {
                Toast.makeText(this, "Cadastre uma categoria", Toast.LENGTH_SHORT).show();
                return;
            }

            int pos = spinnerCategoria.getSelectedItemPosition();

            if (pos == 0) {
                Toast.makeText(this, "Selecione uma categoria válida", Toast.LENGTH_SHORT).show();
                return;
            }

            double valor = Double.parseDouble(valorStr);
            int idCategoria = listaCategorias.get(pos - 1).getId_categoria();

            String tipo = (checkedId == R.id.radioDevo) ? "DEVO" : "ME_DEVEM";

            Divida novaDivida = new Divida(valor, descricao, tipo, idCategoria);
            dividaDAO.inserir(novaDivida);

            editValor.setText("");
            editDescricao.setText("");
            radioGroup.clearCheck();
            spinnerCategoria.setSelection(0);

            carregarListas();
        });

        carregarListas();
    }

    private void carregarListas() {
        List<Divida> todasDividas = dividaDAO.listarTodas();
        List<Divida> minhasDividas = new ArrayList<>();
        List<Divida> devedores = new ArrayList<>();

        for (Divida d : todasDividas) {
            if (d.getTipoDivida().equals("DEVO")) {
                minhasDividas.add(d);
            } else {
                devedores.add(d);
            }
        }

        DividaAdapter adapterMinhas = new DividaAdapter(minhasDividas);
        listaMinhasDividas.setAdapter(adapterMinhas);

        DividaAdapter adapterDevedores = new DividaAdapter(devedores);
        listaDevedores.setAdapter(adapterDevedores);
    }

    private class DividaAdapter extends ArrayAdapter<Divida> {
        public DividaAdapter(List<Divida> dividas) {
            super(DividasDevedoresActivity.this, 0, dividas);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_divida_lista, parent, false);
            }

            Divida divida = getItem(position);
            TextView textoRegistro = convertView.findViewById(R.id.textoRegistroDivida);
            Button btnDeletar = convertView.findViewById(R.id.btnDeletarDivida);

            String nomeCategoria = "Desconhecida";
            for (Categoria c : listaCategorias) {
                if (c.getId_categoria() == divida.getIdCategoria()) {
                    nomeCategoria = c.getNome();
                    break;
                }
            }

            String display = divida.getDescricao() + "\nValor: R$ " + divida.getValor() + "\nCategoria: " + nomeCategoria;
            textoRegistro.setText(display);

            btnDeletar.setOnClickListener(v -> {
                dividaDAO.deletar(divida.getIdDivida());
                carregarListas();
            });

            return convertView;
        }
    }
}