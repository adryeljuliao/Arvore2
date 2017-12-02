package com.juliao.adryel.arvore;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Detalhes extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        ActionBar ab = getSupportActionBar();

        ab.setTitle("Detalhes da Árvore");
        ab.setDisplayHomeAsUpEnabled(true);
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

}
