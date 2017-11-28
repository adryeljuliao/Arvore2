package com.juliao.adryel.arvore;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CadastroOcorrencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ocorrencia);

        ActionBar ab = getSupportActionBar();

        ab.setTitle("Cadastro de Ocorrências");
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
