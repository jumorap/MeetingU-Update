package com.meeting.u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivity extends RecyclerView.Adapter<HolderActivity> {
    @NonNull
    private List<DatosCourse> listActivity = new ArrayList<>();
    private Context c;

    public AdapterActivity(@NonNull Context c) {
        this.c = c;
    }

    public void addActivity(DatosCourse m){
        listActivity.add(m);
        notifyItemInserted(listActivity.size());
    }

    @Override
    public HolderActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.course_view,parent,false);
        return new HolderActivity(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderActivity holder, int position) {
        holder.getTitulo().setText(listActivity.get(position).getTitulo());//<Se añaden los datos que se añadieron a la DB
        holder.getCaracteristicas().setText(listActivity.get(position).getCaracteristicas());
    }

    @Override
    public int getItemCount() {
        return listActivity.size();
    }
}
