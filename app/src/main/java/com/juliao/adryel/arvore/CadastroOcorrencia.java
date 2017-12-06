package com.juliao.adryel.arvore;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroOcorrencia extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerArvores_OcorrenciasAdapter adapter;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mArvoresDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ocorrencia);

        ActionBar ab = getSupportActionBar();

        ab.setTitle("Lista de árvores");
        ab.setDisplayHomeAsUpEnabled(true);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mArvoresDatabaseReference = mFirebaseDatabase.getReference().child("arvore");

        //INFLAR UM RECYCLER
        recyclerView = findViewById(R.id.cadastroOcorrenciaArvore);
        //pega a viewpage da activity main

        //cria-se um adapter
        adapter = new RecyclerArvores_OcorrenciasAdapter(null, null, getApplicationContext());
        //seta o adapter no recycler view
        recyclerView.setAdapter(adapter);
        //Layout na qual define se os layouts inflados serão ou em grid ou em forma de lista
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        recyclerView.addOnItemTouchListener(new MyRecyclerView(getApplicationContext(), recyclerView, new MyRecyclerView.ItemTouch() {
            @Override
            public void clickSimples(View view, int position) {
                Intent intentDetalhes = new Intent(getApplicationContext(), CadastroDefinitivoOcorrencia.class);
                Bundle arvoreBundle = new Bundle();

                Arvore a = adapter.getArvore(position);

                arvoreBundle.putString("nome", a.getNome());
                arvoreBundle.putString("descricao", a.getDescricao());
                arvoreBundle.putString("especie", a.getEspecie());
                arvoreBundle.putString("altura", a.getAltura());
                arvoreBundle.putDouble("latitude", a.getLatitude());
                arvoreBundle.putDouble("longitude", a.getLongitude());
                arvoreBundle.putString("nomeUsuario", a.getNomeUsuario());
                arvoreBundle.putString("imagem", a.getImagem());

                intentDetalhes.putExtras(arvoreBundle);

                startActivity(intentDetalhes);
            }
        }));

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Arvore arvore = dataSnapshot.getValue(Arvore.class);
                adapter.add(arvore);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        mArvoresDatabaseReference.addChildEventListener(mChildEventListener);
    }
}
