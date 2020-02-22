package com.meeting.u;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderActivity extends RecyclerView.ViewHolder {
//PERTENECE A COURSE.JAVA
    private TextView titulo;
    private TextView caracteristicas;

    public HolderActivity(@NonNull View itemView) {
        super(itemView);

        titulo = (TextView) itemView.findViewById(R.id.title);
        caracteristicas = (TextView) itemView.findViewById(R.id.charac);
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public TextView getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(TextView caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
