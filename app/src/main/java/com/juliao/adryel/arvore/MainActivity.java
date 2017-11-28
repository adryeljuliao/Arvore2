package com.juliao.adryel.arvore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton proximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        final FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        final FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        final TextView textView = (TextView) findViewById(R.id.text11);
        final TextView textView2 = (TextView) findViewById(R.id.text22);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setVisibility(View.GONE);
                fab1.setVisibility(View.VISIBLE);
                fab2.setVisibility(View.VISIBLE);
                fab3.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                fab3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fab.setVisibility(View.VISIBLE);
                        fab1.setVisibility(View.GONE);
                        fab2.setVisibility(View.GONE);
                        fab3.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                    }
                });

                fab1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), CadastroArvore.class);
                        startActivity(i);
                    }
                });

                fab2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), CadastroOcorrencia.class);
                        startActivity(i);
                    }
                });

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        proximo = (ImageButton) findViewById(R.id.proximo);

        FragmentPrincipal fragmentPrincipal = new FragmentPrincipal();

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentLayout, fragmentPrincipal);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, fragmentPrincipal);
        transaction.commit();
    }

    public void proximo(View v){
        //metodo de start do fragment Detalhas
        switch (v.getId()){
            case R.id.proximo:
                FragmentDetalhes fragmentDetalhes = new FragmentDetalhes();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentLayout, fragmentDetalhes);
                transaction.commit();
                break;
        }
    }

    public void procurar(View v){
        //metodo que busca as arvores
    }


    @Override
    public void onBackPressed() {
        int cont = getFragmentManager().getBackStackEntryCount();

        if(cont == 0){
            super.onBackPressed();
        }else{
            getFragmentManager().popBackStack();
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_camera) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.listaarvores) {
            // Handle the camera action
        } else if (id == R.id.listaocorrencias) {

        } else if (id == R.id.sair) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
