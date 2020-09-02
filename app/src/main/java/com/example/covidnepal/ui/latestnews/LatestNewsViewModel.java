package com.example.covidnepal.ui.latestnews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LatestNewsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LatestNewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("News is Coming Soon");
    }

    public LiveData<String> getText() {
        return mText;
    }
}