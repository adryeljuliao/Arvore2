package com.juliao.adryel.arvore;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentRecyclerArvores extends Fragment {
    private ArrayList<Arvore> listaArvores = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_fragment_recycler_arvores, container, false);
        //logica do fragment
        carrega();
        //cria-se um recycler view para setar o adapter
        RecyclerView recyclerView = v.findViewById(R.id.recyclerviewArvores);
        //pega a viewpage da activity main

        //cria-se um adapter
        RecyclerArvores adapter = new RecyclerArvores(listaArvores, getContext());
        //seta o adapter no recycler view
        recyclerView.setAdapter(adapter);
        //Layout na qual define se os layouts inflados serão ou em grid ou em forma de lista
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        return v;
    }

    public void carrega(){
        listaArvores.add(new Arvore.ArvoreBuilder("Arvore de piaui", 15.5, 15.5, R.drawable.arvore, "5 metros" ).builder());
        listaArvores.add(new Arvore.ArvoreBuilder("Arvore de palmeira", 15.5, 15.5, R.drawable.arvore, "5 metros" ).builder());
        listaArvores.add(new Arvore.ArvoreBuilder("Arvore de teste", 15.5, 15.5, R.drawable.arvore, "5 metros" ).builder());
        listaArvores.add(new Arvore.ArvoreBuilder("Arvore da lala", 15.5, 15.5, R.drawable.arvore, "5 metros" ).builder());
    }

}
