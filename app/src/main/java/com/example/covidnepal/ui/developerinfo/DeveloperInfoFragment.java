package com.example.covidnepal.ui.developerinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.covidnepal.R;

public class DeveloperInfoFragment extends Fragment implements View.OnClickListener {

    private ImageView fb;
    private ImageView insta;
    private ImageView twitter;
    private ImageView github;
    private String url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_developerinfo, container, false);

        fb = root.findViewById(R.id.facebook);
        insta = root.findViewById(R.id.insta);
        twitter = root.findViewById(R.id.twitter);
        github = root.findViewById(R.id.github);

        fb.setOnClickListener(this);
        insta.setOnClickListener(this);
        twitter.setOnClickListener(this);
        github.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.facebook:
                url = getString(R.string.fb_id);
                startViewIntent(url);
                break;
            case R.id.insta:
                url = getString(R.string.insta_link);
                startViewIntent(url);
                break;
            case R.id.twitter:
                url = getString(R.string.twitter_link);
                startViewIntent(url);
                break;
            case R.id.github:
                url = getString(R.string.github_link);
                startViewIntent(url);
                break;
        }
    }

    private void startViewIntent(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
