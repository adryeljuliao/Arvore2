package com.juliao.adryel.arvore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Mostra as Arvores para cadastrar um ocorrencia depois
public class RecyclerArvores_OcorrenciasAdapter extends RecyclerView.Adapter {
    private ArrayList<Arvore> listArvores;
    private ArrayList<Ocorrencia> listOcorrencias;
    private Context context;

    public void add(Arvore arvore){
        listArvores.add(arvore);
        notifyItemInserted(listArvores.size()+1);
    }

    public void clear (){
        listArvores = new ArrayList<>();
        notifyDataSetChanged();
    }

    public RecyclerArvores_OcorrenciasAdapter(ArrayList<Arvore> listArvores, ArrayList<Ocorrencia> listOcorrencias, Context context) {
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

    public Arvore getArvore(int pos){
        return listArvores.get(pos);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_ocorrencias_arvores, parent, false);
        RecyclerArvores_OcorrenciasViewHolder recyclerArvores_ocorrenciasViewHolder = new RecyclerArvores_OcorrenciasViewHolder(v);

        return recyclerArvores_ocorrenciasViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerArvores_OcorrenciasViewHolder recyclerArvores_ocorrenciasViewHolder = (RecyclerArvores_OcorrenciasViewHolder) holder;
        Arvore arvore = listArvores.get(position);
        recyclerArvores_ocorrenciasViewHolder.nome.setText(arvore.getNome());
        recyclerArvores_ocorrenciasViewHolder.especie.setText(arvore.getEspecie());

        Glide.with(recyclerArvores_ocorrenciasViewHolder.img.getContext())
                .load(arvore.getImagem())
                .into(recyclerArvores_ocorrenciasViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return listArvores != null ? listArvores.size() : 0;
    }

    public class RecyclerArvores_OcorrenciasViewHolder extends RecyclerView.ViewHolder{
        final TextView nome;
        final ImageView img;
        final TextView especie;
        public RecyclerArvores_OcorrenciasViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeArvore_ocorrencia);
            img = itemView.findViewById(R.id.profile_image);
            especie = itemView.findViewById(R.id.EspeciaOcorrenciaArvore);
        }
    }
}
