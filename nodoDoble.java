/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listas;

public class nodoDoble {
    private int dato;
    private nodoDoble siguiente;
    private nodoDoble anterior;

    public nodoDoble(int dato, nodoDoble siguiente, nodoDoble anterior) {
        this.dato = dato;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public nodoDoble(int dato) {
        this(dato, null, null);
    }

    public int getDato() { return dato; }
    public void setDato(int dato) { this.dato = dato; }
    public nodoDoble getSiguiente() { return siguiente; }
    public void setSiguiente(nodoDoble siguiente) { this.siguiente = siguiente; }
    public nodoDoble getAnterior() { return anterior; }
    public void setAnterior(nodoDoble anterior) { this.anterior = anterior; }
}