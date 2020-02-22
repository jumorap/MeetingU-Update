package com.meeting.u;

public class Mensaje {

    private String hora;
    private String nombre;
    private String mensaje;
    private String urlFoto;
    private String type_mensaje;

    public Mensaje() {
    }

    public Mensaje(String hora, String nombre, String mensaje, String urlFoto, String type_mensaje) {
        //public Mensaje(String nombre, String mensaje, String urlFoto, String type_mensaje) {

        mensaje = mensaje.trim();

        if (!mensaje.equals("")){
            this.hora = hora;
            this.nombre = nombre;
            this.mensaje = mensaje;
            this.urlFoto = urlFoto;
            this.type_mensaje = type_mensaje;
        }else{
            //
        }
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getType_mensaje() {
        return type_mensaje;
    }

    public void setType_mensaje(String type_mensaje) {
        this.type_mensaje = type_mensaje;
    }
}