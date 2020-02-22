package com.meeting.u;

public class student extends user {

    public String custom_name;

    public student(String name, String email, String id){
        this.id = id;
        this.email = email;
        this.name = name;
        score = 4.2f;
        user_type = "Estudiante";
    }

}
