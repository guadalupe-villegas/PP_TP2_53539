package modelo; // Agregamos el paquete según el diagrama del TP2

import java.io.Serializable; // Importamos la interfaz

// Implementamos Serializable
public class Sala implements Serializable {
    private int id;
    private String nombre;

    public Sala(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}