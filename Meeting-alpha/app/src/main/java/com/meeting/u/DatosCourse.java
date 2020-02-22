package com.meeting.u;

public class DatosCourse {
    private String titulo;
    private String caracteristicas;

    public DatosCourse() {
    }

    public DatosCourse(String titulo, String caracteristicas) {//<Se envían los datos ESTE ES EL CONSTRUCTOR
        this.titulo = titulo;
        this.caracteristicas = caracteristicas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
