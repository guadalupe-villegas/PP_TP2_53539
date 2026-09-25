package modelo;
import modelo.actividades.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.io.Serializable;

public class EventoUniversitario implements Serializable {

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    // La lista ahora guardará objetos que heredan de Actividad (Charlas y Talleres)
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        cantidadEventos++;
        this.actividades = new ArrayList<>();
    }

    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        cantidadEventos++;
        this.actividades = new ArrayList<>();
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    // Modificado: Ahora recibe el tipo de actividad a crear (Charla o Taller)
    public void crearActividad(int id, String titulo, int cupo, String tipo) {
        if (tipo.equalsIgnoreCase("Charla")) {
            this.actividades.add(new Charla(id, titulo, cupo, "Disertante a confirmar"));
        } else if (tipo.equalsIgnoreCase("Taller")) {
            this.actividades.add(new Taller(id, titulo, cupo, true));
        } else if (tipo.equalsIgnoreCase("Curso")) {
            this.actividades.add(new Curso(id, titulo, cupo, 1)); // Agregamos el Curso con nivel 1
        }
    }

    // Modificado: Suma los materiales y agrega el 21% de impuestos
    public double calcularCostoEstimado() {
        if (this.gratuito) {
            return 0.0;
        } else {
            double costoActividades = 0.0;
            // Recorremos las actividades para sumar sus costos (Polimorfismo)
            for (Actividad act : this.actividades) {
                costoActividades += act.calcularCostoMateriales();
            }
            // (costoBase + costo de todas las actividades) * 1.21
            return (this.costoBase + costoActividades) * 1.21;
        }
    }

    public void mostrarDatos() {
        System.out.println("ID: " + this.id + " | Título: " + this.titulo);
        if (this.sala != null) {
            System.out.println("Sala asignada: " + this.sala.getNombre());
        }
        System.out.println("Costo Estimado Final: $" + this.calcularCostoEstimado());
        System.out.println("-----------------------------------");
    }

    public String getTitulo() {
        return this.titulo;
    }

    public boolean persistirEvento() {
        String nombreArchivo = this.id + ".dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(this);
            return true;
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar: " + e.getMessage());
            return false;
        }
    }

    public EventoUniversitario recuperarEvento(String id) {
        String nombreArchivo = id + ".dat";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (EventoUniversitario) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo del evento: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error de clase: " + e.getMessage());
        }
        return null;
    }

    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> listaFiltrada = new ArrayList<>(); // Instanciamos la nueva lista

        for (Actividad act : this.actividades) {
            // isInstance verifica si la actividad actual coincide con el tipo buscado
            if (tipo.isInstance(act)) {
                // cast convierte de forma segura la actividad al tipo específico
                listaFiltrada.add(tipo.cast(act));
            }
        }
        return listaFiltrada;
    }

    public double calcularCostoMateriales(List<? extends Actividad> actividadesFiltradas) {
        double costoTotal = 0.0;
        for (Actividad act : actividadesFiltradas) {
            costoTotal += act.calcularCostoMateriales();
        }
        return costoTotal;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }


}