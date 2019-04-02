package com.example.proyectoapp;

public class Imagenes {

    private String nombre;
    private String tipo;
    private int valor;
    private int foto;

    public Imagenes(){}

    public Imagenes(String nombre, String tipo, int valor, int foto) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
