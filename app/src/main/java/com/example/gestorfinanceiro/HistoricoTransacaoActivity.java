package com.example.gestorfinanceiro;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.Locale;

public class HistoricoTransacaoActivity extends AppCompatActivity {

    private TransacaoDAO transacaoDAO;
    private CategoriaDAO categoriaDAO;
    private List<Categoria> listaCategorias;
    private ListView listaHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_transacao);

        transacaoDAO = new TransacaoDAO(this);
        categoriaDAO = new CategoriaDAO(this);
        listaCategorias = categoriaDAO.listarTodas();
        listaHistorico = findViewById(R.id.listaHistorico);

        carregarLista();
    }

    private void carregarLista() {
        List<Transacao> transacoes = transacaoDAO.listarTodas();
        TransacaoAdapter adapter = new TransacaoAdapter(transacoes);
        listaHistorico.setAdapter(adapter);
    }

    private class TransacaoAdapter extends ArrayAdapter<Transacao> {
        public TransacaoAdapter(List<Transacao> transacoes) {
            super(HistoricoTransacaoActivity.this, 0, transacoes);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_transacao_lista, parent, false);
            }

            Transacao t = getItem(position);

            View quadroCor = convertView.findViewById(R.id.quadroCorTransacao);
            TextView textValor = convertView.findViewById(R.id.textValor);
            TextView textCategoria = convertView.findViewById(R.id.textCategoria);
            TextView textData = convertView.findViewById(R.id.textData);
            TextView textPagamento = convertView.findViewById(R.id.textPagamento);
            TextView textDescricao = convertView.findViewById(R.id.textDescricao);

            String nomeCategoria = "Desconhecida";
            for (Categoria c : listaCategorias) {
                if (c.getId_categoria() == t.getIdCategoria()) {
                    nomeCategoria = c.getNome();
                    break;
                }
            }

            String valorFormatado = String.format(Locale.US, "%.2f", t.getValor());

            if (t.getTipoTransacao().equals("RECEBIMENTO")) {
                textValor.setText("Valor: R$ +" + valorFormatado);
                quadroCor.setBackgroundColor(Color.parseColor("#4CAF50"));
            } else {
                textValor.setText("Valor: R$ -" + valorFormatado);
                quadroCor.setBackgroundColor(Color.parseColor("#F44336"));
            }

            textCategoria.setText("| Categoria: " + nomeCategoria);
            textData.setText("Data: " + t.getDataTransacao());
            textPagamento.setText("| Pagamento: " + t.getTipoPagamento());
            textDescricao.setText("Descrição: " + t.getDescricao());

            return convertView;
        }
    }
}