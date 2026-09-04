package com.example.gestorfinanceiro;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class NovaTransacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_transacao);

        Spinner spinnercategoria = findViewById(R.id.spinnerCategoria);
        CategoriaDAO dao = new CategoriaDAO(this);
        List<Categoria> lista_categorias = dao.listarTodas();

        List<String> lista_nome_categoria = new ArrayList<>();
        lista_nome_categoria.add("Selecione a categoria");

        for (Categoria categoria : lista_categorias){
            lista_nome_categoria.add(categoria.getNome());
        }

        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<>(
                this,
                R.layout.item_spineer,
                android.R.id.text1,
                lista_nome_categoria
        );
        adapterCategoria.setDropDownViewResource(R.layout.item_spineer);
        spinnercategoria.setAdapter(adapterCategoria);

        Spinner spinnerTipoPagamento = findViewById(R.id.spinnerTipoPagamento);
        String[] tiposPagamento = {"PIX", "Dinheiro", "Cartão"};

        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(
                this,
                R.layout.item_spineer,
                android.R.id.text1,
                tiposPagamento
        );
        adapterTipo.setDropDownViewResource(R.layout.item_spineer);
        spinnerTipoPagamento.setAdapter(adapterTipo);

        Spinner spinnerTipoTranscao = findViewById(R.id.spinnerTipoTransacao);
        String[] tiposTransacao = {"PAGAMENTO", "RECEBIMENTO"};

        ArrayAdapter<String> adapterTipoTransacao = new ArrayAdapter<>(
                this,
                R.layout.item_spineer,
                android.R.id.text1,
                tiposTransacao
        );
        adapterTipoTransacao.setDropDownViewResource(R.layout.item_spineer);
        spinnerTipoTranscao.setAdapter(adapterTipoTransacao);

        EditText editValor = findViewById(R.id.editValor);
        EditText editDescricao = findViewById(R.id.editDescricao);
        Button btnSalvarTransacao = findViewById(R.id.btnSalvarTransacao);

        btnSalvarTransacao.setOnClickListener(v -> {
            String valorString = editValor.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();

            if (valorString.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int posicaoCategoria = spinnercategoria.getSelectedItemPosition();
            if (posicaoCategoria == 0 || lista_categorias.isEmpty()) {
                Toast.makeText(this, "Selecione uma categoria válida", Toast.LENGTH_SHORT).show();
                return;
            }

            double valor = Double.parseDouble(valorString);

            Categoria categoriaSelecionada = lista_categorias.get(posicaoCategoria - 1);
            int idCategoria = categoriaSelecionada.getId_categoria();

            String tipoPagamento = spinnerTipoPagamento.getSelectedItem().toString();
            String tipoTransacao = spinnerTipoTranscao.getSelectedItem().toString();

            Transacao novaTransacao = new Transacao(descricao, valor, LocalDate.now(), idCategoria, tipoPagamento, tipoTransacao);
            TransacaoDAO transacaoDAO = new TransacaoDAO(this);
            transacaoDAO.inserir(novaTransacao);

            Toast.makeText(this, "Transação salva com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}