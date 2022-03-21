package com.example.nombre.clases;

public class Global {
    private static Usuario user;
    private static Deporte dep;

    public static void setUser(Usuario user) {
        Global.user = user;
    }

    public static void setDep(Deporte dep) {
        Global.dep = dep;
    }

    public static Deporte getDep() {
        return dep;
    }
    public static void delDep() {
        dep = null;
    }
    public static Usuario getUser() {
        return user;
    }
    public static void delUser() {
        user = null;
    }
}
