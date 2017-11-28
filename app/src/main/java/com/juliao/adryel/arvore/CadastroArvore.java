package com.juliao.adryel.arvore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CadastroArvore extends AppCompatActivity {
    ImageView imagemView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_arvore);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        imagemView = (ImageView) findViewById(R.id.takeFoto);
        ab.setTitle("Cadastro de Árvores");
        ab.setDisplayHomeAsUpEnabled(true);

        FileInputStream fin ;
        try {
            //abre o arquivo chamado FILENAME para LEITURA
            fin = openFileInput("photo.jpg");

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            Bitmap imagem = BitmapFactory.decodeStream(fin,null, options);

            imagemView.setImageBitmap(imagem);

            fin.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
