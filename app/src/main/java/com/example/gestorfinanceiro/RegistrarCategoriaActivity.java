package com.example.gestorfinanceiro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarCategoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_categoria);


        EditText editNomeCategoria = findViewById(R.id.editNomeCategoria);
        Button btnSalvarCategoria = findViewById(R.id.btnSalvarCategoria);


        btnSalvarCategoria.setOnClickListener(v -> {
            String nome = editNomeCategoria.getText().toString().trim();


            if (nome.isEmpty()) {
                Toast.makeText(this, "Digite o nome da categoria", Toast.LENGTH_SHORT).show();
                return;
            }


            Categoria novaCategoria = new Categoria(nome);
            CategoriaDAO categoriaDAO = new CategoriaDAO(this);
            categoriaDAO.inserir(novaCategoria);

            Toast.makeText(this, "Categoria registrada no banco!", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}