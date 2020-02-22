package com.meeting.u;

public class activityjoin extends activity {

    private String id;


    activityjoin(String id, String tipo, String name, String descripción, String place, String hora_incio, String hora_fin, int participantes, String nombre) {
        super(tipo, name, descripción, place, hora_incio, hora_fin, participantes, nombre);

        this.id = id;
    }
}
