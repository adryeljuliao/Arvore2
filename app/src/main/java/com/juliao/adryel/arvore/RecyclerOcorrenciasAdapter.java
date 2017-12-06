package com.juliao.adryel.arvore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

//Mostra todas as ocorrencias
public class RecyclerOcorrenciasAdapter extends RecyclerView.Adapter{
    private ArrayList<Ocorrencia> listOcorrencias;
    private Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
