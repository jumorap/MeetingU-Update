package com.meeting.u;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class ChatActivityView extends AppCompatActivity {
    private RecyclerView vistaChat;
    private EditText escribir;
    private ImageButton boton;
    private ImageButton btnFoto;
    private ImageButton btnDown;

    public AdaptaMensaje adapter;

    private FirebaseDatabase database;
    private DatabaseReference DBReference;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private LocalDateTime localDateTime;

    private static final int PHOTO_SEND = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);

        vistaChat = findViewById(R.id.vistaChat);
        escribir = findViewById(R.id.escribir);
        boton = findViewById(R.id.boton);
        btnFoto = findViewById(R.id.btnFoto);
        btnDown = findViewById(R.id.btnDown);

        database = FirebaseDatabase.getInstance();
        DBReference = database.getReference("Chats");
        databaseReference = DBReference.child("Proof");//Sala chat

        storage = FirebaseStorage.getInstance();

        adapter = new AdaptaMensaje(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        vistaChat.setLayoutManager(l);
        vistaChat.setAdapter(adapter);

        final DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        final Date date = new Date();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                databaseReference.push().setValue(new Mensaje(dateFormat.format(date),LogInActivity.usuario.name,escribir.getText().toString(),"","1"));
                escribir.setText("");
            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Seleccionar foto"),PHOTO_SEND);
            }
        });


        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vistaChat.scrollToPosition(adapter.getItemCount()-1);
            }
        });


        scrollAdapter();
///*
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Mensaje m = dataSnapshot.getValue(Mensaje.class);
                adapter.addMessage(m);
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

        //*/
        scrollSend();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode ==  RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("imgChat");

            //imágenes chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            final DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            final Date date = new Date();

            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> downloadUrl = fotoReferencia.getDownloadUrl();
                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {

                        @Override
                        public void onSuccess(Uri uri) {
                            String u = uri.toString();
                            Mensaje m = new Mensaje(dateFormat.format(date),LogInActivity.usuario.name,"_________________________________",u,"2");
                            databaseReference.push().setValue(m);
                            scrollSend();
                        }

                    });
                }
            });
            scrollSend();
        }
        scrollSend();
    }


    private void scrollAdapter(){
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                scrollSend();
            }
        });
    }

    private void scrollSend(){
        vistaChat.scrollToPosition(adapter.getItemCount()-1);
    }
}
