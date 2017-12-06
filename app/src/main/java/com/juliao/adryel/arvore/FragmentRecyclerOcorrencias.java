package com.juliao.adryel.arvore;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentRecyclerOcorrencias extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mArvoresDatabaseReference;
    private RecyclerView recyclerView;
    private RecyclerOcorrenciasAdapter adapter;
    private ChildEventListener mChildEventListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment_recycler_ocorrencias, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mArvoresDatabaseReference = mFirebaseDatabase.getReference().child("ocorrencia");

        recyclerView = v.findViewById(R.id.recyclerviewOcorrencias);
        //pega a viewpage da activity main

        //cria-se um adapter
        adapter = new RecyclerOcorrenciasAdapter(null, null, getContext());
        //seta o adapter no recycler view
        recyclerView.setAdapter(adapter);
        //Layout na qual define se os layouts inflados serão ou em grid ou em forma de lista
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        recyclerView.addOnItemTouchListener(new MyRecyclerView(getActivity(), recyclerView, new MyRecyclerView.ItemTouch() {

            @Override
            public void clickSimples(View view, int position) {
                Intent intentDetalhesOcorrencia = new Intent(getActivity(), DetalhesOcorrencia.class);
                Bundle ocorrenciaBundle = new Bundle();

                Ocorrencia ocorrencia = adapter.getOcorrencia(position);

                ocorrenciaBundle.putString("nomeOcorrencia", ocorrencia.getNome());
                ocorrenciaBundle.putString("descricaoOcorrencia", ocorrencia.getDescricao());
                ocorrenciaBundle.putString("obsOcorrencia", ocorrencia.getObservaocao());
                ocorrenciaBundle.putString("nomeUsuarioOcorrencia", ocorrencia.getNomeUsuario());

                intentDetalhesOcorrencia.putExtras(ocorrenciaBundle);

                startActivity(intentDetalhesOcorrencia);
            }
        }));

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Ocorrencia ocorrencia = dataSnapshot.getValue(Ocorrencia.class);
                adapter.add(ocorrencia);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mArvoresDatabaseReference.addChildEventListener(mChildEventListener);

        return v;
    }

    public RecyclerOcorrenciasAdapter getAdapter() {
        return this.adapter;
    }
}
