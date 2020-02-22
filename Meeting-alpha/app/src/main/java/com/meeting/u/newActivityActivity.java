package com.meeting.u;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class newActivityActivity extends AppCompatActivity implements View.OnClickListener{

    private String type;
    private EditText mName;
    private EditText mDescription;
    private EditText mPlace;
    private EditText mParticipantes;
    private EditText mInicio;
    private EditText mFinal;
    private ImageView mAcademia;
    private ImageView mDeportiva;
    private ImageView mOcio;
    private ImageView mOtro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);

        findViewById(R.id.inicio_time).setOnClickListener(this);
        findViewById(R.id.final_time).setOnClickListener(this);
        findViewById(R.id.academia).setOnClickListener(this);
        findViewById(R.id.deportiva).setOnClickListener(this);
        findViewById(R.id.ocio).setOnClickListener(this);
        findViewById(R.id.otro).setOnClickListener(this);
        findViewById(R.id.crear).setOnClickListener(this);

        mName = findViewById(R.id.activityname);
        mDescription = findViewById(R.id.description);
        mPlace = findViewById(R.id.place);
        mParticipantes = findViewById(R.id.nparticipantes);
        mInicio = findViewById(R.id.inicio_time);
        mFinal = findViewById(R.id.final_time);
        mAcademia = findViewById(R.id.academia);
        mDeportiva = findViewById(R.id.deportiva);
        mOcio = findViewById(R.id.ocio);
        mOtro = findViewById(R.id.otro);
        type = "";




    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int i = v.getId();
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        if(i == R.id.academia){

            type = "academia";
            mAcademia.setImageResource(R.drawable.academia);
            mDeportiva.setImageResource(R.drawable.deportiva_50);
            mOcio.setImageResource(R.drawable.ocio_50);
            mOtro.setImageResource(R.drawable.otra_50);

        }else if (i == R.id.deportiva){

            type = "deportiva";
            mAcademia.setImageResource(R.drawable.academia_50);
            mDeportiva.setImageResource(R.drawable.deportiva);
            mOcio.setImageResource(R.drawable.ocio_50);
            mOtro.setImageResource(R.drawable.otra_50);

        }else if (i == R.id.ocio){

            type = "ocio";
            mAcademia.setImageResource(R.drawable.academia_50);
            mDeportiva.setImageResource(R.drawable.deportiva_50);
            mOcio.setImageResource(R.drawable.ocio);
            mOtro.setImageResource(R.drawable.otra_50);

        }else if (i == R.id.otro){

            type = "otra";
            mAcademia.setImageResource(R.drawable.academia_50);
            mDeportiva.setImageResource(R.drawable.deportiva_50);
            mOcio.setImageResource(R.drawable.ocio_50);
            mOtro.setImageResource(R.drawable.otra);

        }else if (i == R.id.crear){

            if((type != "") && (toString(mName) != "") && toString(mDescription) != "" && (toString(mPlace) != "")
                    && (toString(mInicio) != "") && (toString(mFinal) != "") && validNumber(mParticipantes)) {

                cActivity();
                Toast.makeText(getBaseContext(), "Actividad Creada", Toast.LENGTH_SHORT).show();
                finish();

            }else{

                Toast.makeText(getBaseContext(), "Revisa tus entradas", Toast.LENGTH_SHORT).show();
            }

        }else if(i == R.id.inicio_time){

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mInicio.setText(String.format("%02d:%02d", hourOfDay, minute));

                }
            }, hour, minutes, false);
            timePickerDialog.show();

        }else if(i == R.id.final_time){

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mFinal.setText(String.format("%02d:%02d", hourOfDay, minute));

                }
            }, hour, minutes, false);
            timePickerDialog.show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void cActivity(){

        activity activity = new activity(type, mName.getText().toString(), mDescription.getText().toString(), mPlace.getText().toString(),
                mInicio.getText().toString(), mFinal.getText().toString(),
                Integer.parseInt(mParticipantes.getText().toString()), LogInActivity.usuario.name);
        activity.toDB();
    }

    protected String toString(EditText s){
        return s.getText().toString();
    }

    protected boolean validNumber(EditText num){
        boolean check = false;
        try {
            int number = Integer.parseInt(toString(num));
            if (1 < number && number <= 15 ){check = true;}
        } catch (NumberFormatException e) {
        }
        return check;
    }

}