package com.meeting.u;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity {

    private String tipo;
    private String name;
    private String descripción;
    private String place;
    private String hora_incio;
    private String hora_fin;
    private int nparticipantes;
    private int disponibles;
    private String[] participantes;



    activity(String tipo, String name, String descripción, String place, String hora_incio, String hora_fin, int participantes, String nombre){

        this.tipo = tipo;
        this.name = name;
        this.descripción = descripción;
        this.place = place;
        this.hora_incio = hora_incio;
        this.hora_fin = hora_fin;
        nparticipantes = participantes;
        disponibles = participantes - 1;
        this.participantes = new String[participantes];
        this.participantes[nparticipantes - (disponibles + 1)] = nombre;

    }

    protected void toDB(){

        // Registrar actividad en la Base de Datos

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myDB = database.getReference("Activities");
        DatabaseReference myRef = myDB.child(tipo);
        DatabaseReference activity = myRef.push();

        activity.child("name").setValue(name);
        activity.child("description").setValue(descripción);
        activity.child("place").setValue(place);
        activity.child("hora_inicio").setValue(hora_incio);
        activity.child("hora_fin").setValue(hora_fin);
        activity.child("amount_participants").setValue(String.valueOf(nparticipantes));
        activity.child("availables").setValue(String.valueOf(disponibles));

        DatabaseReference actparticipantes = activity.child("participants");

        for (int i = 0; i < participantes.length; i++){

            actparticipantes.child(String.valueOf(i)).setValue(participantes[i]);
        }


    }


}
