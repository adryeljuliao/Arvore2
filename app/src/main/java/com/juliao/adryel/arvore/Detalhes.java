package com.juliao.adryel.arvore;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detalhes extends AppCompatActivity{
    private ImageView imageView;
    private TextView nome;
    private TextView detalhes;
    private TextView especie;
    private TextView altura;

    Arvore a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        imageView = findViewById(R.id.imageView);
        nome = findViewById(R.id.nomearvore_detalhes);
        especie = findViewById(R.id.especie);
        altura = findViewById(R.id.altura);
        detalhes = findViewById(R.id.detalhes);

        ActionBar ab = getSupportActionBar();

        ab.setTitle("Detalhes da Árvore");
        ab.setDisplayHomeAsUpEnabled(true);

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
            detalhes.setText(a.getDescricao());
            altura.setText(a.getAltura());
            especie.setText(a.getEspecie());
            Glide.with(imageView.getContext())
                    .load(a.getImagem())
                    .into(imageView);
        }
    }

    public void location(View v){
        Intent intent = new Intent(getApplicationContext(), Mapa.class);
        Bundle arvoreBundle = new Bundle();

        arvoreBundle.putString("nome", a.getNome());
        arvoreBundle.putString("descricao", a.getDescricao());
        arvoreBundle.putString("especie", a.getEspecie());
        arvoreBundle.putString("altura", a.getAltura());
        arvoreBundle.putDouble("latitude", a.getLatitude());
        arvoreBundle.putDouble("longitude", a.getLongitude());
        arvoreBundle.putString("nomeUsuario", a.getNomeUsuario());
        arvoreBundle.putString("imagem", a.getImagem());

        intent.putExtras(arvoreBundle);

        startActivityForResult(intent, 7777);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 7777){
            Bundle bundleArvore = data.getExtras();

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
            detalhes.setText(a.getDescricao());
            altura.setText(a.getAltura());
            especie.setText(a.getEspecie());
            Glide.with(imageView.getContext())
                    .load(a.getImagem())
                    .into(imageView);
        }
    }
}
