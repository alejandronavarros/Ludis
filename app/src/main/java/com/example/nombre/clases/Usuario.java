package com.example.nombre.clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public abstract class Usuario {
    private String usuario;
    private String nombre;
    private String email;
    private List<Notificacion> notificaciones;

    public Usuario(String user, String nom, String mail, List<Notificacion> notificaciones){
        this.usuario = user;
        this.nombre = nom;
        this.email = mail;
        this.notificaciones = new ArrayList<Notificacion>(notificaciones);
    }
    public Usuario(String user, String nom, String mail){
        this(user,nom, mail, new ArrayList<Notificacion>());
    }

    public String getEmail() {
        return  email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }
    public void addNotificacion(Notificacion not) {
        notificaciones.add(not);
    }
}
