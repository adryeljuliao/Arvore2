package com.juliao.adryel.arvore;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by adryel.juliao on 22/11/2017.
 */

public class RecyclerArvores extends RecyclerView.Adapter {
    private ArrayList<Arvore> listArvores = new ArrayList<>();
    private Context context;

    public RecyclerArvores(ArrayList<Arvore> listArvores, Context context) {
        this.listArvores = listArvores;
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
//        arvoresViewHolder.img.setImageResource(arvore.getImagem());

//        PopupMenu popupMenu = new PopupMenu(context, arvoresViewHolder.txtOpcaoMenu);
//        popupMenu.inflate(R.menu.menu_card);
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_localizacao:
//                        Toast.makeText(context, "Localizacao", Toast.LENGTH_LONG).show();
//                        break;
//                    default:
//                        break;
//                }
//
//                return false;
//            }
//        });
//        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return listArvores != null ? listArvores.size() : 0;
    }

    public class ArvoresViewHolder extends RecyclerView.ViewHolder{
        final TextView nome;
//        final ImageView img;
        public ArvoresViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome_arvore);
//            img = itemView.findViewById(R.id.img_arvore);

        }
    }


}
