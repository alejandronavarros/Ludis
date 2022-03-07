package com.example.nombre.clases;

public class UserActual {
    private static Usuario user;

    public static void setUser(Usuario user) {
        UserActual.user = user;
    }

    public static Usuario getUser() {
        return user;
    }
    public static void delUser() {
        user = null;
    }
}
