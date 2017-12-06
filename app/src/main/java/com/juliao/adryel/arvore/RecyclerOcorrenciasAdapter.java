package com.juliao.adryel.arvore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Mostra todas as ocorrencias
public class RecyclerOcorrenciasAdapter extends RecyclerView.Adapter{
    private ArrayList<Arvore> listArvores;
    private ArrayList<Ocorrencia> listOcorrencias;
    private Context context;

    public void add(Ocorrencia ocorrencia){
        listOcorrencias.add(ocorrencia);
        notifyItemInserted(listOcorrencias.size()+1);
    }

    public void clear (){
        listOcorrencias = new ArrayList<>();
        notifyDataSetChanged();
    }

    public RecyclerOcorrenciasAdapter(ArrayList<Arvore> listArvores, ArrayList<Ocorrencia> listOcorrencias, Context context) {
        if (listArvores != null){
            this.listArvores = listArvores;
        }else{
            this.listArvores = new ArrayList<>();
        }

        if (listOcorrencias != null){
            this.listOcorrencias = listOcorrencias;
        }else{
            this.listOcorrencias = new ArrayList<>();
        }
        this.context = context;
    }

    public Ocorrencia getOcorrencia(int pos){
        return listOcorrencias.get(pos);
    }

    public Arvore getArvore(int pos){
        return listArvores.get(pos);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_lista_ocorrencia, parent, false);
        RecyclerOcorrenciasViewHolder recyclerOcorrenciasViewHolder = new RecyclerOcorrenciasViewHolder(v);

        return recyclerOcorrenciasViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerOcorrenciasViewHolder recyclerOcorrenciasViewHolder = (RecyclerOcorrenciasViewHolder) holder;
        Ocorrencia ocorrencia = listOcorrencias.get(position);
        recyclerOcorrenciasViewHolder.nome.setText(ocorrencia.getNome());
        recyclerOcorrenciasViewHolder.especie.setText(ocorrencia.getDescricao());

//        Glide.with(recyclerOcorrenciasViewHolder.img.getContext())
//                .load(arvore.getImagem())
//                .into(recyclerOcorrenciasViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return listOcorrencias != null ? listOcorrencias.size() : 0;
    }

    public class RecyclerOcorrenciasViewHolder extends RecyclerView.ViewHolder{
        final TextView nome;
//        final ImageView img;
        final TextView especie;
        public RecyclerOcorrenciasViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeArvore_ocorrencia);
//            img = itemView.findViewById(R.id.profile_image);
            especie = itemView.findViewById(R.id.EspeciaOcorrenciaArvore);
        }
    }
}
