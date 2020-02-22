package com.meeting.u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //public static user userm = LogInActivity.usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.new_activity).setOnClickListener(this);
        findViewById(R.id.activities).setOnClickListener(this);
        findViewById(R.id.profile).setOnClickListener(this);



    }





    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.new_activity){
            Intent goToNewActivity = new Intent(this, newActivityActivity.class);
            startActivity(goToNewActivity);
        }else if(i == R.id.activities){
            Intent goToActivities = new Intent(this, ChatActivityView.class);
            startActivity(goToActivities);
        }else if(i == R.id.profile){
            Intent goToProfile = new Intent(this, Profile.class);
            startActivity(goToProfile);

        }

    }
}
