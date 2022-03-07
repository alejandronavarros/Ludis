package com.example.nombre.clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioNormal extends Usuario{
    private Genero gen;
    private List<Usuario> amigos = new ArrayList<>();
    private Date fechadeNacimiento;


    public UsuarioNormal(String user, String nom, String mail,Genero gen, List<Notificacion> notificacions, List<Usuario> amigos, Date date){
        super(user, nom, mail);
        this.gen = gen;
        fechadeNacimiento = date;
    }
    public UsuarioNormal(String user, String nom, String mail,Genero gen, Date date){
        this(user, nom, mail,gen, new ArrayList<Notificacion>(), new ArrayList<Usuario>(), date);
    }
    public void addAmigo(Usuario user) {
        amigos.add(user);
    }
    public void delAmigo(Usuario user) {amigos.remove(user);}
    public List<Usuario> getAmigos(){
        return  new ArrayList<Usuario>(amigos);
    }
    public Date getFechadeNacimiento() {
        return fechadeNacimiento;
    }
}
