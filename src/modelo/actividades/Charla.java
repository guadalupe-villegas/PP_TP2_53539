// 1. Ubicamos la clase en el subpaquete "actividades" dentro de "modelo"
package modelo.actividades;

// 2. Importamos Serializable
import java.io.Serializable;

// 3. Agregamos explícitamente "implements Serializable"
public class Charla extends Actividad implements Serializable {
    private String disertante;

    public Charla(int id, String titulo, int cupoMaximo, String disertante) {
        // "super" llama al constructor de la clase abstracta Actividad
        super(id, titulo, cupoMaximo);
        this.disertante = disertante;
    }

    @Override
    public double calcularCostoMateriales() {
        return 0.0; // Las charlas son gratuitas
    }

    @Override
    public String getTipo() {
        return "Charla";
    }
}