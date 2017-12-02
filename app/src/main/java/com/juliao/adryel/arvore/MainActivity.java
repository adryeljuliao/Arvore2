package com.juliao.adryel.arvore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int COD_CAMERA = 24;
    private static final int CODIGO_LOGAR = 55;

    String FILENAME = "photo_internal.jpg";
    NavigationView navigationView;

    //fireAtuth
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    FloatingActionButton fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;
    TextView textView;
    TextView textView2;

    int cont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //instacia do firebase auth
        mFirebaseAuth = FirebaseAuth.getInstance();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        textView = (TextView) findViewById(R.id.text11);
        textView2 = (TextView) findViewById(R.id.text22);
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
                        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i, COD_CAMERA);
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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.listaarvores);

        FragmentRecyclerArvores fragmentRecyclerArvores = new FragmentRecyclerArvores();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentLayout, fragmentRecyclerArvores);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, fragmentRecyclerArvores);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Lista de Árvores");
        transaction.addToBackStack(null);
        transaction.commit();

        cont = 0;

        //intancia do listener firebase auth
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            //metodo chamado quando muda o estado da aplicação (quando o usuario está logado ou n)
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //pegar os dados do usuario
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //se existir um usuario pego os dados dele,
                if (user != null) {
                    //logado
                    //Toast.makeText(MainActivity.this, "Logado", Toast.LENGTH_SHORT).show();
                    //onSignInInitialize(user.getDisplayName());
                } else {
                    //não-logado
                    //onSignOutCleanUp();

                    //chama o fluxo de login
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .build(),
                            CODIGO_LOGAR);
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == COD_CAMERA && resultCode == RESULT_OK){
            if(data != null){
                Bundle extras = data.getExtras();
                Bitmap foto = (Bitmap) extras.get("data");

                FileOutputStream fOut = null;
                try {
                    fOut = openFileOutput(FILENAME, Context.MODE_PRIVATE);;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                foto.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                try {
                    fOut.flush();
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getApplicationContext(), CadastroArvore.class);
                startActivity(i);
            }
        } else if (requestCode == CODIGO_LOGAR) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Bem-vindo", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                finish();
            }
        }
    }

    public void limpar(View v){
        //limpa o FloatingActionButton
        fab.setVisibility(View.VISIBLE);
        fab1.setVisibility(View.GONE);
        fab2.setVisibility(View.GONE);
        fab3.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
    }

    public void proximo(View v){
        //metodo de start do fragment Detalhas
        switch (v.getId()){
            case R.id.proximo:
                cont ++;
                FragmentDetalhes fragmentDetalhes = new FragmentDetalhes();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentLayout, fragmentDetalhes);
                ActionBar ab = getSupportActionBar();
                fab.setVisibility(View.GONE);
                ab.setTitle("Detalhes");
                transaction.commit();
                break;
        }
    }

    public void confirmarCadastroArvore(View v){
        Toast.makeText(getApplicationContext(),"Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
    }

    public void location(View v){
        cont++;
        FragmentMapa fragmentMapa = new FragmentMapa();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, fragmentMapa);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Localização");
        fab.setVisibility(View.GONE);
        transaction.commit();
    }

    public void ajuda(View v){
        Toast.makeText(getApplicationContext(), "Teste1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START) ) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(cont == 0){
            finish();
        }else {
            cont = 0;

            navigationView.setCheckedItem(R.id.listaarvores);

            FragmentRecyclerArvores fragmentRecyclerArvores = new FragmentRecyclerArvores();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentLayout, fragmentRecyclerArvores);
            ActionBar ab = getSupportActionBar();
            ab.setTitle("Lista de Árvores");
            fab.setVisibility(View.VISIBLE);
            transaction.commit();
        }
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
        if (id == R.id.action_busca) {
            Toast.makeText(getApplicationContext(), "Buscar", Toast.LENGTH_SHORT).show();
            fab.setVisibility(View.VISIBLE);
//            AutoCompleteTextView autoCompleteFrutas = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
//            adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FRUTAS);
//            autoCompleteFrutas.setAdapter(adaptador);
//
//            autoCompleteFrutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    //Toast.makeText(MainActivity.this, ((TextView) view).getText().toString() +"item="+i+"col="+l   , Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this,
//                            adaptador.getItem(i).toString(),
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.listaarvores) {
            cont = 0;

            FragmentRecyclerArvores fragmentRecyclerArvores = new FragmentRecyclerArvores();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentLayout, fragmentRecyclerArvores);
            ActionBar ab = getSupportActionBar();
            ab.setTitle("Lista de Árvores");
            fab.setVisibility(View.VISIBLE);
            transaction.commit();

        } else if (id == R.id.listaocorrencias) {
            cont ++;

            FragmentRecyclerOcorrencias fragmentRecyclerOcorrencias = new FragmentRecyclerOcorrencias();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentLayout, fragmentRecyclerOcorrencias);
            ActionBar ab = getSupportActionBar();
            ab.setTitle("Lista de Ocorrências");
            fab.setVisibility(View.VISIBLE);
            transaction.commit();
        } else if (id == R.id.ajuda) {
            cont ++;

            FragmentAjuda fragmentAjuda = new FragmentAjuda();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentLayout, fragmentAjuda);
            ActionBar ab = getSupportActionBar();
            ab.setTitle("Ajuda");
            fab.setVisibility(View.VISIBLE);
            transaction.commit();
        } else if(id == R.id.sair){
            Toast.makeText(getApplicationContext(), "Até logo!", Toast.LENGTH_SHORT).show();
            AuthUI.getInstance().signOut(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

//        FragmentRecyclerOcorrencias fragmentRecyclerOcorrencias = new FragmentRecyclerOcorrencias();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentLayout, fragmentRecyclerOcorrencias);
//        ActionBar ab = getSupportActionBar();
//        ab.setTitle("Lista de Ocorrências");
//        transaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //remove o listener auth
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
        //detachDatabaseReadListener();
        //mMessageAdapter.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //nao basta o listener está instanciado, tem q atachar ele no onresume
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}
