package com.example.androidrecycle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {


    private final MutableLiveData<String> paperAmountString = new MutableLiveData<>();
    private final MutableLiveData<Integer> paperAmountInteger = new MutableLiveData<>();

    public void setPaperAmountString(String value) {paperAmountString.setValue(value);}
    public LiveData<String> getPaperAmountString() {return paperAmountString;}
    public void setPaperAmountInteger(int value){paperAmountInteger.setValue(value);};
    public MutableLiveData<Integer> getPaperAmountInteger() {return paperAmountInteger;}



    private final MutableLiveData<String> glassAmountString = new MutableLiveData<>();
    private final MutableLiveData<Integer> glassAmountInteger = new MutableLiveData<>();

    public void setGlassAmountString(String value) {glassAmountString.setValue(value);}
    public LiveData<String> getGlassAmountString() {return glassAmountString;}
    public void setGlassAmountInteger(int value){glassAmountInteger.setValue(value);};
    public MutableLiveData<Integer> getGlassAmountInteger() {return glassAmountInteger;}



    private final MutableLiveData<String> aluminumAmountString = new MutableLiveData<>();
    private final MutableLiveData<Integer> aluminumAmountInteger = new MutableLiveData<>();

    public void setAluminumAmountString(String value) {aluminumAmountString.setValue(value);}
    public LiveData<String> getAluminumAmountString() {return aluminumAmountString;}
    public void setAluminumAmountInteger(int value){aluminumAmountInteger.setValue(value);};
    public MutableLiveData<Integer> getAluminumAmountInteger() {return aluminumAmountInteger;}



    private final MutableLiveData<String> editTextValue = new MutableLiveData<>();
    public void setEditTextValue(String value) {editTextValue.setValue(value);}
    public LiveData<String> getEditTextValue() {return editTextValue;}

}
