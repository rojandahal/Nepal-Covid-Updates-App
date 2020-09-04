package com.example.covidnepal.ui.districtdetails;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidnepal.R;
import com.example.covidnepal.data.GetAllStatsData;
import com.example.covidnepal.model.DistrictInfo;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DistrictDetailsAdapter extends RecyclerView.Adapter<DistrictDetailsAdapter.ViewHolder> {

    private Context context;
    private Map<Integer, DistrictInfo> districtInfoHashMap;
    private Map<Integer,DistrictInfo> tempMap;
    private List<Integer> idList;
    private NumberFormat nf;
   // private static final String TAG = "Adapter";

    public DistrictDetailsAdapter(Context context) {
        this.context = context;
        nf = NumberFormat.getInstance();
        districtInfoHashMap = GetAllStatsData.getInstance().districtInstance();
        tempMap = new HashMap<>(districtInfoHashMap);
        idList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_district_activity,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        int pos = position+1;

        if(position == 75)
            pos = 482;
        if(position == 76)
            pos = 542;

        if(districtInfoHashMap.containsKey(pos)) {
            holder.count.setText(String.valueOf(position+1));
            DistrictInfo dInfo = districtInfoHashMap.get(pos);
            //Log.d(TAG, "onBindViewHolder: ID: " + dInfo.getId());
            assert dInfo != null;
            holder.districtName.setText(dInfo.getTitle());
            holder.dCases.setText(MessageFormat.format("Cases: {0}", nf.format(dInfo.getCases())));
            holder.dActive.setText(MessageFormat.format("Active : {0}", nf.format(dInfo.getActive())));
            holder.dRecovered.setText(MessageFormat.format("Recovered : {0}", nf.format(dInfo.getRecovered())));
            holder.dDeaths.setText(MessageFormat.format("Deaths : {0}", nf.format(dInfo.getDeaths())));
        }
    }

    @Override
    public int getItemCount() {
        return districtInfoHashMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView districtName;
        public TextView dCases;
        public TextView dActive;
        public TextView dRecovered;
        public TextView dDeaths;
        public TextView count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            for(Map.Entry entry: districtInfoHashMap.entrySet()){
                idList.add(districtInfoHashMap.get(entry.getKey()).getId());
            }

            districtName = itemView.findViewById(R.id.districtName);
            dCases = itemView.findViewById(R.id.district_cases);
            dActive = itemView.findViewById(R.id.district_active);
            dRecovered = itemView.findViewById(R.id.district_recovered);
            dDeaths = itemView.findViewById(R.id.district_death);
            count = itemView.findViewById(R.id.count);
        }
    }
}
