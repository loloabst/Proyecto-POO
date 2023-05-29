package org.example;

import org.example.controlador.ControladorEstado;
import org.example.vista.VentanaEdo;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        VentanaEdo view = new VentanaEdo("Estados de México MVC y JDBC");
        // Crea una instancia de la clase VentanaEdo y la asigna a la variable 'view'
        ControladorEstado controller = new ControladorEstado(view);
        // Crea una instancia de la clase ControladorEstado y la asigna a la variable 'controller', se conectan.
    }
}