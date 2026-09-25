package modelo; // Asegurate de que esté en el paquete correcto

import java.io.Serializable; // Agregamos este import

// Agregamos "implements Serializable"
public class Estudiante implements Serializable {
    private String legajo;
    private String nombre;

    public Estudiante(String legajo, String nombre) {
        this.legajo = legajo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLegajo() {
        return legajo;
    }
}