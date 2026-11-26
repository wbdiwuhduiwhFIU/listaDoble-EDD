/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listas;

public class Main {
    
    public static void main(String[] args) {
        listaDoble lista = new listaDoble();

        System.out.println("--- 1. PRUEBA INSERTAR ORDENADO ---");
        lista.insertarOrdenado(40);
        lista.insertarOrdenado(10);
        lista.insertarOrdenado(30);
        lista.insertarOrdenado(50);
        lista.insertarOrdenado(20);
        System.out.println(lista.imprimirAdelante());
        System.out.println(lista.imprimirAtras());

        System.out.println("\n--- 2. PRUEBA INSERTAR EN POSICIÓN ---");
        lista.insertarEnPosicion(0, 5);   // Inicio
        lista.insertarEnPosicion(3, 25);  // Medio
        lista.insertarEnPosicion(7, 60);  // Final
        System.out.println(lista.imprimirAdelante());

        try {
            lista.insertarEnPosicion(20, 99);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Validación correcta: " + e.getMessage());
        }

        System.out.println("\n--- 3. PRUEBA ELIMINAR POR VALOR ---");
        System.out.println("Eliminar 25 (medio): " + lista.eliminarPorValor(25));
        System.out.println("Eliminar 5 (cabeza): " + lista.eliminarPorValor(5));
        System.out.println("Eliminar 60 (cola): " + lista.eliminarPorValor(60));
        System.out.println("Eliminar 999 (no existe): " + lista.eliminarPorValor(999));
        System.out.println(lista.imprimirAdelante());
        System.out.println(lista.imprimirAtras());

        System.out.println("\n--- 4. PRUEBA ELIMINAR EN POSICIÓN ---");
        System.out.println("Eliminado en pos 2: " + lista.eliminarEnPosicion(2));
        System.out.println(lista.imprimirAdelante());

        System.out.println("\n--- 5. PRUEBA ELIMINAR ÚLTIMO ---");
        System.out.println("Eliminar último: " + lista.eliminarUltimo());
        System.out.println("Eliminar último: " + lista.eliminarUltimo());
        System.out.println(lista.imprimirAdelante());
        System.out.println(lista.imprimirAtras());
    }
}
