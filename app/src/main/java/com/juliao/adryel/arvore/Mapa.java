package com.juliao.adryel.arvore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mGoogleMap;
    MapView mMapView;
    private TextView nome;

    ActionBar ab;

    Bundle bundleArvore;
    Arvore a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        ab = getSupportActionBar();

        ab.setTitle("Localização da Árvore");
        ab.setDisplayHomeAsUpEnabled(false);

        nome = findViewById(R.id.nomeArvaoreMapa);

        a = new Arvore();
        Intent recebe = getIntent();
        bundleArvore = recebe.getExtras();

        a.setNome(bundleArvore.getString("nome"));
        a.setAltura(bundleArvore.getString("altura"));
        a.setDescricao(bundleArvore.getString("descricao"));
        a.setEspecie(bundleArvore.getString("especie"));
        a.setImagem(bundleArvore.getString("imagem"));
        a.setLatitude(bundleArvore.getDouble("latitude"));
        a.setLongitude(bundleArvore.getDouble("longitude"));
        a.setEspecie(bundleArvore.getString("especie"));
        a.setNomeUsuario(bundleArvore.getString("nomeUsuario"));

        nome.setText(a.getNome().toString());
        mMapView = (MapView) findViewById(R.id.map);

        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        mGoogleMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition liberty = CameraPosition.builder().target(new LatLng(a.getLatitude(), a.getLongitude())).zoom(16).bearing(0).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(liberty));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(a.getLatitude(), a.getLongitude())).title(a.getNome()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a.getLatitude(), a.getLongitude()),16));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        Bundle arvoreBundle = new Bundle();

        arvoreBundle.putString("nome", a.getNome());
        arvoreBundle.putString("descricao", a.getDescricao());
        arvoreBundle.putString("especie", a.getEspecie());
        arvoreBundle.putString("altura", a.getAltura());
        arvoreBundle.putDouble("latitude", a.getLatitude());
        arvoreBundle.putDouble("longitude", a.getLongitude());
        arvoreBundle.putString("nomeUsuario", a.getNomeUsuario());
        arvoreBundle.putString("imagem", a.getImagem());

        i.putExtras(arvoreBundle);

        setResult(RESULT_OK, i);

        finish();

        super.onBackPressed();
    }
}
