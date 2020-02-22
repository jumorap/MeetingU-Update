package com.meeting.u;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.print.PrinterId;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Course extends AppCompatActivity {

    private RecyclerView vista;

    private AdapterActivity adapter;

    private FirebaseDatabase databaseA;
    private DatabaseReference databaseReferenceA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        vista = (RecyclerView) findViewById(R.id.vista);

        databaseA = FirebaseDatabase.getInstance();
        databaseReferenceA = databaseA.getReference("Nombre Actividad");//Activity name HAY QUE RECIBIR ESTO DE LOS DATOS QUE HAYA INGRESADO EL USUARIO

        adapter = new AdapterActivity(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        vista.setLayoutManager(l);
        vista.setAdapter(adapter);

        //BOTON ACTUALIZAR Y ENVÍO LOS DATOS
        /*
        botonEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new DatosCourse(DATOS A ENVIAR AL CONSTRUCTOR DE DATOSCOURSE))
            }
        });

         */


        databaseReferenceA.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Cuando añade datos a la base de datos, los actualiza en Recycler View (la vista del cliente)
                DatosCourse m = dataSnapshot.getValue(DatosCourse.class);
                adapter.addActivity(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
