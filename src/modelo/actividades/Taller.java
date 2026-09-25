package modelo.actividades;

import certificacion.Certificable; // Importamos la interfaz
import modelo.Estudiante;
import java.io.Serializable;

// Agregamos "Certificable" a la lista de implementaciones
public class Taller extends Actividad implements Serializable, Certificable {
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        if (this.requiereNotebook) {
            return 5000.0;
        } else {
            return 2000.0;
        }
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    // Agregamos el método obligatorio de la interfaz
    @Override
    public String generarCertificado(Estudiante estudiante) {
        return ">>> CERTIFICADO DE ASISTENCIA <<<\n" +
                "La entidad " + ENTIDAD_EMISORA + " certifica que el alumno " +
                estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")\n" +
                "ha completado exitosamente el Taller: " + this.titulo + ".";
    }
}