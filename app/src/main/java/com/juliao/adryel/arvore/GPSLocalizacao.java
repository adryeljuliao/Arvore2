package com.juliao.adryel.arvore;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

public class GPSLocalizacao extends Service implements LocationListener {

    private final Context context;
    boolean isGPSAtivado = false;
    boolean isNetWorkAtivado = false;
//    boolean canGetLocalizacao = false;

    Location localizacao;
    protected LocationManager gerenciadorLocalizacao;


    public GPSLocalizacao(Context context) {
        this.context = context;
    }

    public Location getLocalizacao() {
        try {

            gerenciadorLocalizacao = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSAtivado = gerenciadorLocalizacao.isProviderEnabled(gerenciadorLocalizacao.GPS_PROVIDER);
            isNetWorkAtivado = gerenciadorLocalizacao.isProviderEnabled(gerenciadorLocalizacao.GPS_PROVIDER);

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSAtivado && localizacao == null) {
                    gerenciadorLocalizacao.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this);
                    if(gerenciadorLocalizacao != null){
                        localizacao = gerenciadorLocalizacao.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    }
                }
                if (isNetWorkAtivado && localizacao == null) {
                    gerenciadorLocalizacao.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, this);
                    if(gerenciadorLocalizacao != null){
                        localizacao = gerenciadorLocalizacao.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    }
                }
            }

        } catch (Exception ex) {

        }
        return localizacao;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}