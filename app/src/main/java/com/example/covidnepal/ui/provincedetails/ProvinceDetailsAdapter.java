package com.example.covidnepal.ui.provincedetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidnepal.R;
import com.example.covidnepal.data.GetAllStatsData;
import com.example.covidnepal.model.ProvinceInfo;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Map;

public class ProvinceDetailsAdapter extends RecyclerView.Adapter<ProvinceDetailsAdapter.ViewHolder> {

    private Context context;
    private Map<Integer, ProvinceInfo> provinceInfoMap;
    private NumberFormat numberFormat;

    public ProvinceDetailsAdapter(Context context) {
        this.context = context;
        provinceInfoMap = GetAllStatsData.getInstance().provinceInstance();
        numberFormat = NumberFormat.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.province_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position += 1;
        ProvinceInfo provinceInfo = provinceInfoMap.get(position);
        assert provinceInfo != null;
        holder.provinceName.setText(MessageFormat.format("Province {0}", provinceInfo.getId()));
        holder.pCases.setText(numberFormat.format(provinceInfo.getCases()));
        holder.pActive.setText(numberFormat.format(provinceInfo.getActive()));
        holder.pRecovered.setText(numberFormat.format(provinceInfo.getRecovered()));
        holder.pDeaths.setText(numberFormat.format(provinceInfo.getDeaths()));

    }

    @Override
    public int getItemCount() {
        return provinceInfoMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView provinceName;
        public TextView pCases;
        public TextView pActive;
        public TextView pRecovered;
        public TextView pDeaths;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            provinceName = itemView.findViewById(R.id.provinceName);
            pCases = itemView.findViewById(R.id.province_cases_number);
            pActive = itemView.findViewById(R.id.province_active_number);
            pRecovered = itemView.findViewById(R.id.world_recovered_number);
            pDeaths = itemView.findViewById(R.id.province_deaths);
        }
    }
}
