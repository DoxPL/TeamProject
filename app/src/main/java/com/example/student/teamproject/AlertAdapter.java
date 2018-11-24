package com.example.student.teamproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertHolder> {

    private Context context;
    private List<Alert> list;

    public AlertAdapter(Context context, List<Alert> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AlertHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.alert_item, null);
        AlertHolder holder = new AlertHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlertHolder alertHolder, int i) {
        Alert alert = list.get(i);
        alertHolder.tvTitle.setText(alert.getName());
        alertHolder.tvDescription.setText(alert.getDescription());
        String strDate = alert.getYear() + "." + alert.getMonth() + "." + alert.getDay() + " " + alert.getHour() + ":" + alert.getMinute();
        alertHolder.tvTime.setText(strDate);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AlertHolder extends RecyclerView.ViewHolder
    {

        ImageView icon;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvTime;

        public AlertHolder(@NonNull View itemView) {
            super(itemView);
            icon =  itemView.findViewById(R.id.ivIcon);
            tvTitle =  itemView.findViewById(R.id.tvTitle);
            tvDescription =  itemView.findViewById(R.id.tvDescr);
            tvTime =  itemView.findViewById(R.id.tvTime);
        }
    }
}
