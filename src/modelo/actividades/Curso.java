package modelo.actividades;

import certificacion.Certificable;
import modelo.Estudiante;
import java.io.Serializable;

// Hereda de Actividad e implementa Certificable y Serializable
public class Curso extends Actividad implements Certificable, Serializable {
    private int nivel; // Atributo específico del curso que pide el diagrama

    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }

    @Override
    public double calcularCostoMateriales() {
        // El enunciado no especifica un monto fijo para el curso,
        // le asignamos un cálculo representativo según el nivel
        return 3000.0 * this.nivel;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    // Cumplimos con el contrato de la interfaz Certificable
    @Override
    public String generarCertificado(Estudiante estudiante) {
        return ">>> CERTIFICADO DE ASISTENCIA <<<\n" +
                "La entidad " + ENTIDAD_EMISORA + " certifica que el alumno " +
                estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")\n" +
                "ha completado exitosamente el Curso: " + this.titulo + " (Nivel " + this.nivel + ").";
    }
}