package com.example.nombre.clases;

import android.location.Location;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Encuentro {
    private Date fecha;
    private Location lugar;
    private final Deporte deporte;
    private String descripción;
    private final float nivel;
    private float precio;
    private final Usuario creador;
    private List<Usuario> participantes = new ArrayList<Usuario>();
    private List<Usuario> listaEspera = new ArrayList<Usuario>();
    private int maxPar;
    private boolean femOnly;
    private int duracion;

    public Encuentro(Date fecha, Location lugar, Deporte dep, String desc, float nivel, float precio, Usuario crea, int max, boolean femOnly, int duracion){
        this.fecha = fecha;
        this.lugar = lugar;
        deporte = dep;
        descripción=desc;
        this.nivel = nivel;
        this.precio = precio;
        this.creador = crea;
        maxPar = max;
        this.femOnly = femOnly;
        this.duracion = duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getParticipantesRestantes(){
        return maxPar - participantes.size();
    }

    public int getEsperando(){
        return listaEspera.size();
    }

    public void addEspera(Usuario user){
        listaEspera.add(user);
    }

    public void delEspera(Usuario user){
        listaEspera.remove(user);
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFemOnly(boolean femOnly) {
        this.femOnly = femOnly;
    }

    public void setMaxPar(int maxPar) {
        this.maxPar = maxPar;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void addParticipante(Usuario user) {
        participantes.add(user);
    }
    public void delParticipante(Usuario user){
        participantes.remove(user);
    }
    public Date getFecha() {
        return fecha;
    }
    public Deporte getDeporte() {
        return deporte;
    }
    public float getNivel() {
        return nivel;
    }
    public float getPrecio() {
        return precio;
    }
    public int getMaxPar() {
        return maxPar;
    }
    public List<Usuario> getParticipantes() {
        return participantes;
    }
    public Location getLugar() {
        return lugar;
    }
    public String getDescripción() {
        return descripción;
    }
    public Usuario getCreador() {
        return creador;
    }

    public void setLugar(Location lugar) {
        this.lugar = lugar;
    }
}
