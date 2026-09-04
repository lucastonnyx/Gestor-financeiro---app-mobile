package com.example.gestorfinanceiro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestorfinanceiro.NovaTransacaoActivity;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        MaterialCardView cardVerCategorias = findViewById(R.id.cardVerCategorias);
        MaterialCardView cardRegistrarCategorias = findViewById(R.id.cardRegistrarCategorias);
        MaterialCardView cardNovaTransacao = findViewById(R.id.cardNovaTransacao);
        MaterialCardView cardHistorico = findViewById(R.id.cardHistorico);
        MaterialCardView cardDividasDevedores = findViewById(R.id.cardDividasDevedores);





        cardVerCategorias.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VerCategoriasActivity.class);
            startActivity(intent);
        });

        cardRegistrarCategorias.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrarCategoriaActivity.class);
            startActivity(intent);
        });

        cardNovaTransacao.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.example.gestorfinanceiro.NovaTransacaoActivity.class);
            startActivity(intent);
        });

        cardHistorico.setOnClickListener(v -> {
            Intent intent = new Intent (MainActivity.this, com.example.gestorfinanceiro.HistoricoTransacaoActivity.class);
            startActivity(intent);
        });

        cardDividasDevedores.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, com.example.gestorfinanceiro.DividasDevedoresActivity.class);
            startActivity(intent);
        });

    }
}
