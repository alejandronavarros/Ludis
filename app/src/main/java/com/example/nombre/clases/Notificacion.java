package com.example.nombre.clases;

public class Notificacion {
    private final Byte tipo;
    private final Usuario remitente;

    public Notificacion(Byte tipo, Usuario remitente){
        this.tipo = tipo;
        this.remitente = remitente;
    }

    public Byte getTipo() {
        return tipo;
    }

    public Usuario getRemitente() {
        return remitente;
    }

}
