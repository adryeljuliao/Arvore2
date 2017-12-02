package com.juliao.adryel.arvore;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView flag, latitude,longitude,nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        ActionBar ab = getSupportActionBar();

        ab.setTitle("Localização da Árvore");
        ab.setDisplayHomeAsUpEnabled(true);

        mMapView = (MapView) findViewById(R.id.map);

        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        //View v = inflater.inflate(R.layout.activity_detalhes, container, false);
//        mView = inflater.inflate(R.layout.activity_mapa, container, false);
//        //logica do fragment
////        nome = getActivity().findViewById(R.id.nome1);
////        longitude = getActivity().findViewById(R.id.longitude);
////        latitude = getActivity().findViewById(R.id.latitude);
////        flag =  getActivity().findViewById(R.id.flag);
//
//        return mView;
//    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//

//        }
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getApplicationContext());
        mGoogleMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //if(flag.getText().toString().equals("true")) {
//            googleMap.addMarker(new MarkerOptions().position(new LatLng(
//                    Double.parseDouble(latitude.getText().toString()),
//                    Double.parseDouble(longitude.getText().toString()))).title(nome.getText().toString()));

        CameraPosition liberty = CameraPosition.builder().target(new LatLng(
                -5.885085, -35.364344)).zoom(16).bearing(0).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(liberty));
//        }else{
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-5.885085, -35.364344)).title("EAJ"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-5.885085, -35.364344),16));
//        }
    }


}
