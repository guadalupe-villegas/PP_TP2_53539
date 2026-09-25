// 1. Definimos el paquete según el nuevo diagrama UML
package modelo.actividades;

// 2. Importamos la excepción y Serializable
import exepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// 3. Agregamos "implements Serializable" para la persistencia
public abstract class Actividad implements Serializable {

    protected int id;
    protected String titulo;
    protected int cupoMaximo;

    public final int CUPO_MINIMO = 5;
    protected List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    // 4. Agregamos "throws CupoExcedidoException" en la firma
    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {

        // 5. Verificamos si ya llegamos al límite de cupos
        if (this.inscripciones.size() >= this.cupoMaximo) {
            // Si no hay lugar, "lanzamos" la excepción y cortamos la ejecución del método
            throw new CupoExcedidoException("No hay más cupo disponible para la actividad: " + this.titulo);
        }

        // Si el if no se cumple (hay lugar), el código sigue normalmente
        Inscripcion nueva = new Inscripcion("Confirmada", estudiante);
        this.inscripciones.add(nueva);
        return nueva;
    }

    public void mostrarInscripciones() {
        System.out.println("Inscripciones para: " + this.titulo);
        for (Inscripcion insc : this.inscripciones) {
            System.out.println("- Alumno: " + insc.getEstudiante().getNombre() +
                    " | Legajo: " + insc.getEstudiante().getLegajo());
        }
    }

    public final void mostrarIdentificacion() {
        System.out.println("Actividad: " + this.getTipo() + " | Título: " + this.titulo);
        System.out.println("Costo propio de la actividad: $" + this.calcularCostoMateriales());

        int anotados = this.inscripciones.size();
        System.out.println("Ocupación: " + anotados + "/" + this.cupoMaximo + " cupos ocupados");
    }

    public abstract double calcularCostoMateriales();

    public abstract String getTipo();

    public List<Inscripcion> getInscripciones() {
        return this.inscripciones;
    }

    public String getTitulo() {
        return this.titulo;
    }

}