package com.example.covidnepal.ui.hospitals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.covidnepal.R;

public class HospitalFragment extends Fragment {

    private HospitalsViewModel hospitalsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hospitalsViewModel =
                ViewModelProviders.of(this).get(HospitalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hospitals, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        hospitalsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}