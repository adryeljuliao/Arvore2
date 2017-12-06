package com.juliao.adryel.arvore;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroDefinitivoOcorrencia extends AppCompatActivity {
    Arvore a;

    private ImageView imageView;
    private TextView nome;
    private TextView nomeOcorrencia;
    private TextView detalhesOcorrencia;
    private TextView obsOcorrencia;

    String nomeUsuario;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mOcorrenciaDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_definitivo_ocorrencia);

        ActionBar ab = getSupportActionBar();

        ab.setTitle("Cadastro de Ocorrências");
        ab.setDisplayHomeAsUpEnabled(true);

//        imageView = findViewById(R.id.imageView);
        nome = findViewById(R.id.ocorrenciaNomeArvore);
//        especie = findViewById(R.id.especie);
//        altura = findViewById(R.id.altura);
//        detalhes = findViewById(R.id.detalhes);
        nomeOcorrencia = findViewById(R.id.textnome11);
        detalhesOcorrencia = findViewById(R.id.textDescricao22);
        obsOcorrencia = findViewById(R.id.textObs33);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mOcorrenciaDatabaseReference = mFirebaseDatabase.getReference().child("ocorrencia");

        a = new Arvore();

        Intent recebe = getIntent();
        Bundle bundleArvore = recebe.getExtras();

        if (bundleArvore!=null){
            a.setNome(bundleArvore.getString("nome"));
            a.setAltura(bundleArvore.getString("altura"));
            a.setDescricao(bundleArvore.getString("descricao"));
            a.setEspecie(bundleArvore.getString("especie"));
            a.setImagem(bundleArvore.getString("imagem"));
            a.setLatitude(bundleArvore.getDouble("latitude"));
            a.setLongitude(bundleArvore.getDouble("longitude"));
            a.setEspecie(bundleArvore.getString("especie"));
            a.setNomeUsuario(bundleArvore.getString("nomeUsuario"));

            nome.setText(a.getNome());
            nomeUsuario = a.getNomeUsuario();

        }
    }

    public void cadastroOcorrencia(View v){
        Ocorrencia ocorrencia = new Ocorrencia(nomeOcorrencia.getText().toString(), detalhesOcorrencia.getText().toString(),
                obsOcorrencia.getText().toString(), nomeUsuario);

//        a.getListaOcorrencia().add(ocorrencia);

        mOcorrenciaDatabaseReference.push().setValue(ocorrencia);

        finish();
    }

}
