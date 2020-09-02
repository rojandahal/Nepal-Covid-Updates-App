package com.example.covidnepal.ui.hospitals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HospitalsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HospitalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Hospitals data is Coming soon");
    }

    public LiveData<String> getText() {
        return mText;
    }
}