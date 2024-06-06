package com.example.androidrecycle.user.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> paperAmountString = new MutableLiveData<>();
    private final MutableLiveData<Integer> paperAmountInteger = new MutableLiveData<>();

    private final MutableLiveData<String> glassAmountString = new MutableLiveData<>();
    private final MutableLiveData<Integer> glassAmountInteger = new MutableLiveData<>();

    private final MutableLiveData<String> aluminumAmountString = new MutableLiveData<>();
    private final MutableLiveData<Integer> aluminumAmountInteger = new MutableLiveData<>();




    public void setPaperAmountString(String value) {paperAmountString.setValue(value);}
    public LiveData<String> getPaperAmountString() {return paperAmountString;}
    public void setPaperAmountInteger(int value){paperAmountInteger.setValue(value);};
    public MutableLiveData<Integer> getPaperAmountInteger() {return paperAmountInteger;}




    public void setGlassAmountString(String value) {glassAmountString.setValue(value);}
    public LiveData<String> getGlassAmountString() {return glassAmountString;}
    public void setGlassAmountInteger(int value){glassAmountInteger.setValue(value);};
    public MutableLiveData<Integer> getGlassAmountInteger() {return glassAmountInteger;}




    public void setAluminumAmountString(String value) {aluminumAmountString.setValue(value);}
    public LiveData<String> getAluminumAmountString() {return aluminumAmountString;}
    public void setAluminumAmountInteger(int value){aluminumAmountInteger.setValue(value);};
    public MutableLiveData<Integer> getAluminumAmountInteger() {return paperAmountInteger;}

    private final MutableLiveData<String> editTextValue = new MutableLiveData<>();
    public void setEditTextValue(String value) {editTextValue.setValue(value);}
    public LiveData<String> getEditTextValue() {return editTextValue;}

    private final MutableLiveData<List<Integer>> points = new MutableLiveData<>(new ArrayList<>());
    private static final int FIXED_SIZE = 4;

    public SharedViewModel() {
        List<Integer> initialPoints = new ArrayList<>(FIXED_SIZE);
        for (int i = 0; i < FIXED_SIZE; i++) {
            initialPoints.add(0);
        }
        points.setValue(initialPoints);
    }

    public void updatePoints(int index, int value) { //0 for paper, 1 for glass, 2 for aluminum,3 for total
        List<Integer> currentPoints = points.getValue();
        if (currentPoints != null && index >= 0 && index < FIXED_SIZE) {
            currentPoints.set(index, value);
            points.setValue(currentPoints);
        }
    }

    public MutableLiveData<List<Integer>> getPoints() {
        return points;
    }



}
