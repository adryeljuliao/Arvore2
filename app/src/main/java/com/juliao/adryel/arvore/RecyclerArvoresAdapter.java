package com.juliao.adryel.arvore;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerArvoresAdapter extends RecyclerView.Adapter implements Filterable {
    private ArrayList<Arvore> listArvores;
    private Context context;

    private ArrayList<Arvore> filterListArvores;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ArvoresViewHolder arvoresViewHolder = (ArvoresViewHolder) holder;
        Arvore arvore = listArvores.get(position);
        arvoresViewHolder.nome.setText(arvore.getNome());
        arvoresViewHolder.especie.setText(arvore.getEspecie());

        Glide.with(arvoresViewHolder.img.getContext())
                .load(arvore.getImagem())
                .into(arvoresViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return listArvores != null ? listArvores.size() : 0;
    }

    public Arvore getArvore(int pos){
        return listArvores.get(pos);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                Log.i("TANIRO", "string = " + charString);

                if (charString.isEmpty()) {
                    filterListArvores = listArvores;
                } else {

                    ArrayList<Arvore> filteredList = new ArrayList<>();

                    for (Arvore arvore : listArvores) {

                        if (arvore.getNome().toLowerCase().contains(charString) || arvore.getEspecie().toLowerCase().contains(charSequence)) {

                            filteredList.add(arvore);
                        }
                    }

                        filterListArvores = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterListArvores;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listArvores = (ArrayList<Arvore>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ArvoresViewHolder extends RecyclerView.ViewHolder{
        final LinearLayout linearLayout;
        final TextView nome;
        final ImageView img;
        final TextView especie;
        public ArvoresViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome_arvore);
            img = itemView.findViewById(R.id.foto_arvore);
            especie = itemView.findViewById(R.id.especie_arvore);
            linearLayout = itemView.findViewById(R.id.linearLayoutArvore);
        }
    }
}
