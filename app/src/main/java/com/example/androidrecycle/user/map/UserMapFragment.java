package com.example.androidrecycle.user.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidrecycle.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UserMapFragment extends Fragment implements OnMapReadyCallback{

    private FusedLocationProviderClient fusedLocationClient;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getParentFragmentManager().beginTransaction().add(R.id.UserFragmentContainerView, mapFragment).commit();
        mapFragment.getMapAsync(this);
        return inflater.inflate(R.layout.fragment_user_map, container, false);
    }

    public void onMapReady(GoogleMap googleMap) {
        LatLng nea_smyrni = new LatLng(37.948307, 23.714685);
        googleMap.addMarker(new MarkerOptions()
                .position(nea_smyrni)
                .title("Nea Smyrni"));

        LatLng agias_sofias = new LatLng(40.633380, 22.946208);
        googleMap.addMarker(new MarkerOptions()
                .position(agias_sofias)
                .title("Agia Sofia"));

        LatLng peiraias = new LatLng(37.943308, 23.648676);
        googleMap.addMarker(new MarkerOptions()
                .position(peiraias)
                .title("Peiraias"));

        LatLng aristotelous = new LatLng(40.632773, 22.940978);
        googleMap.addMarker(new MarkerOptions()
                .position(aristotelous)
                .title("Aristotelous"));

        LatLng deth = new LatLng(40.626973, 22.953769);
        googleMap.addMarker(new MarkerOptions()
                .position(deth)
                .title("Deth"));

        LatLng pamak = new LatLng(40.625438, 22.960464);
        googleMap.addMarker(new MarkerOptions()
                .position(pamak)
                .title("Panephsthmio Makedonias"));




        googleMap.animateCamera(CameraUpdateFactory.zoomTo(5));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(nea_smyrni));

    }

}