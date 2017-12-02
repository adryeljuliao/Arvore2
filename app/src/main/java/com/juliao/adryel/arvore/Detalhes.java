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

        Arvore a = new Arvore();

        Intent recebe = getIntent();
        Bundle bundleArvore = recebe.getExtras();

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

        Log.i("TESTE", a.toString());

    }

    public void location(View v){
//        cont++;
//        FragmentMapa fragmentMapa = new FragmentMapa();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentLayout, fragmentMapa);
//        ActionBar ab = getSupportActionBar();
//        ab.setTitle("Localização");
//        fab.setVisibility(View.GONE);
//        transaction.commit();

        Intent intent = new Intent(getApplicationContext(), Mapa.class);
        startActivity(intent);

    }
    private void findWidigets(){


    }

}
