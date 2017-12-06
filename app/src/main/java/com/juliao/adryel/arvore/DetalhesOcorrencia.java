package com.juliao.adryel.arvore;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetalhesOcorrencia extends AppCompatActivity {
    private TextView nome;
    private TextView detalhes;
    private TextView obs;
    private Ocorrencia ocorrencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_ocorrencia);

        ActionBar ab = getSupportActionBar();

        ab.setTitle("Detalhes da Ocorrência da Árvore");
        ab.setDisplayHomeAsUpEnabled(true);

        nome = findViewById(R.id.nome_ocorrencia);
        detalhes = findViewById(R.id.detalhes_ocorrencia);
        obs = findViewById(R.id.obs_ocorrencia);

        ocorrencia = new Ocorrencia();
        Intent recebe = getIntent();
        Bundle bundleOcorrencia = recebe.getExtras();

        if (bundleOcorrencia!=null){
            ocorrencia.setNome(bundleOcorrencia.getString("nomeOcorrencia"));
            ocorrencia.setDescricao(bundleOcorrencia.getString("descricaoOcorrencia"));
            ocorrencia.setObservaocao(bundleOcorrencia.getString("obsOcorrencia"));
            ocorrencia.setNomeUsuario(bundleOcorrencia.getString("nomeUsuarioOcorrencia"));

            nome.setText(ocorrencia.getNome());
            detalhes.setText(ocorrencia.getDescricao());
            obs.setText(ocorrencia.getObservaocao());
        }
    }
}
