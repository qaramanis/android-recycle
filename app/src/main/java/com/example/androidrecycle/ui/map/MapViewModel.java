package com.example.androidrecycle.ui.map;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;

public class MapViewModel extends ViewModel{
    private final MutableLiveData<String> mText;
    public ObservableField<LatLng>  mMapLatLng = new ObservableField<>();
    public MapViewModel(){

        mText = new MutableLiveData<>();

        mText.setValue("This is Map fragment");
    }


    public LiveData<String> getText(){return mText;}
}