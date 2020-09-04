package com.example.covidnepal.ui.agegroupinfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidnepal.R;
import com.example.covidnepal.data.GetAllStatsData;
import com.example.covidnepal.model.AgeGroupInfo;


import java.text.NumberFormat;
import java.util.Map;

public class AgeGroupAdapter extends RecyclerView.Adapter<AgeGroupAdapter.ViewHolder> {

    //private static final String TAG = "Age Group Adapter";
    private Context context;
    private Map<Integer, AgeGroupInfo> ageGroupInfoMap;
    private NumberFormat nf;

    public AgeGroupAdapter(Context context) {
        this.context = context;
        ageGroupInfoMap = GetAllStatsData.getInstance().ageGroupInstance();
        nf = NumberFormat.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scroll_age_group_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Log.d(TAG, "onBindViewHolder: " + position);
        if(ageGroupInfoMap.containsKey(position)) {
            AgeGroupInfo info = ageGroupInfoMap.get(position);
            assert info != null;
            holder.ageTitle.setText(info.getAgeGroup());
            holder.ageCases.setText(nf.format(info.getCases()));
            holder.ageActive.setText(nf.format(info.getActive()));
            holder.ageRecovered.setText(nf.format(info.getRecovered()));
            holder.ageDeaths.setText(nf.format(info.getDeaths()));
        }
    }

    @Override
    public int getItemCount() {
        return ageGroupInfoMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView ageCases;
        private TextView ageActive;
        private TextView ageRecovered;
        private TextView ageDeaths;
        private TextView ageTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ageCases = itemView.findViewById(R.id.ageGroup_cases_number);
            ageActive = itemView.findViewById(R.id.ageGroup_active_number);
            ageRecovered = itemView.findViewById(R.id.ageGroup_recovered_number);
            ageDeaths = itemView.findViewById(R.id.ageGroup_deaths_number);
            ageTitle = itemView.findViewById(R.id.textView_ageGroup);
        }
    }
}
