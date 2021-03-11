package com.ipo.example;

public class Contacto {
    private String nombre;
    private String telefono;
    private int tipo; //0:familia; 1:amigo; 2:trabajo
    private String email;
    private String direccion;
    public Contacto(String nom, String tel, int
            tip, String em, String dir){
        nombre= nom;
        telefono= tel;
        tipo=tip;
        email=em;
        direccion=dir;
    }
    public String getNombre(){
        return nombre;
    }
    public String getTelefono(){
        return telefono;
    }
    public int getTipo(){
        return tipo;
    }
    public String getEmail(){
        return email;
    }
    public String getDireccion(){
        return direccion;
    }
}
