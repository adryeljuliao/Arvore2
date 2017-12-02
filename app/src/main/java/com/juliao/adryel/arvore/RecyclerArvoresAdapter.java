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

public class RecyclerArvoresAdapter extends RecyclerView.Adapter {
    private ArrayList<Arvore> listArvores;
    private Context context;

    public void add(Arvore arvore){
        listArvores.add(arvore);
        notifyItemInserted(listArvores.size()+1);
    }

    public void clear (){
        listArvores = new ArrayList<>();
        notifyDataSetChanged();
    }

    public RecyclerArvoresAdapter(ArrayList<Arvore> listArvores, Context context) {
        if (listArvores != null){
            this.listArvores = listArvores;
        }else{
            this.listArvores = new ArrayList<>();
        }
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_arvores, parent, false);
        ArvoresViewHolder arvoresViewHolder = new ArvoresViewHolder(v);

        return arvoresViewHolder;
    }

    //metodo para setar os valores das variaveis nos widgets
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArvoresViewHolder arvoresViewHolder = (ArvoresViewHolder) holder;
        Arvore arvore = listArvores.get(position);
        arvoresViewHolder.nome.setText(arvore.getNome());

//        arvoresViewHolder.img.setImageBitmap(arvore.getImagem());
        Glide.with(arvoresViewHolder.img.getContext())
                .load(arvore.getImagem())
                .into(arvoresViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return listArvores != null ? listArvores.size() : 0;
    }

    public class ArvoresViewHolder extends RecyclerView.ViewHolder{
        final TextView nome;
        final ImageView img;
        public ArvoresViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome_arvore);
            img = itemView.findViewById(R.id.foto_arvore);
        }
    }


}
