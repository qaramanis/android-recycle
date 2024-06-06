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

    //TODO fix markers
    public void onMapReady(GoogleMap googleMap) {
        LatLng nea_smyrni = new LatLng(37.948307, 23.714685);
        googleMap.addMarker(new MarkerOptions()
                .position(nea_smyrni)
                .title("Peiraias"));

        LatLng peiraias = new LatLng(37.943308, 23.648676);
        googleMap.addMarker(new MarkerOptions()
                .position(peiraias)
                .title("Peiraias"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(nea_smyrni));

    }

}