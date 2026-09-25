package principal;

import modelo.*;
import modelo.actividades.*;
import certificacion.Certificable;
import exepciones.CupoExcedidoException;
import hilos.EnvioTicketsThread; // Importamos nuestro nuevo hilo
import java.util.List;

public class App {
    public static void main(String[] args) {

        Estudiante est1 = new Estudiante("50111", "Nelson Rumán");
        Estudiante est2 = new Estudiante("50222", "Marcos García");
        Estudiante est3 = new Estudiante("50333", "Sofia Fernández");

        EventoUniversitario evento1 = new EventoUniversitario("E001", "Coding Week", 8000.0, false);
        Sala sala1 = new Sala(100, "Auditorio 1");
        evento1.asignarSala(sala1);

        evento1.crearActividad(1, "La IA y la Programación", 50, "Charla");
        evento1.crearActividad(2, "Aprendiendo a programar en Java", 1, "Taller");
        evento1.crearActividad(3, "Patrones de Diseño", 20, "Curso");

        Actividad charla = evento1.getActividades().get(0);
        Actividad taller = evento1.getActividades().get(1);
        Actividad curso = evento1.getActividades().get(2);

        try {
            Inscripcion insc1 = charla.inscribir(est1);
            Inscripcion insc2 = taller.inscribir(est2);
            Inscripcion insc3 = curso.inscribir(est1);

            Inscripcion insc4 = curso.inscribir(est3);

            insc1.confirmarInscripcion();
            insc2.confirmarInscripcion();
            insc3.confirmarInscripcion();
            insc4.confirmarInscripcion();

            System.out.println(">>> Arrancando el hilo secundario de tickets...");
            EnvioTicketsThread hiloTickets = new EnvioTicketsThread(evento1);
            hiloTickets.start();

            evento1.persistirEvento();

            taller.inscribir(est3);

        } catch (CupoExcedidoException e) {
            System.out.println(">>> ERROR DE CUPO CAPTURADO: " + e.getMessage());
        }


        // --- EL HILO PRINCIPAL SIGUE SU CURSO SIN ESPERAR AL OTRO ---
        System.out.println("\n[HILO PRINCIPAL] === RESUMEN DEL EVENTO ===");
        evento1.mostrarDatos();

        System.out.println("\n[HILO PRINCIPAL] === EMISIÓN DE CERTIFICADOS ===");
        for (Actividad act : evento1.getActividades()) {
            if (act instanceof Certificable) {
                Certificable actividadCertificable = (Certificable) act;
                for (Inscripcion insc : act.getInscripciones()) {
                    System.out.println(actividadCertificable.generarCertificado(insc.getEstudiante()));
                }
            }
        }

        System.out.println("\n[HILO PRINCIPAL] === COSTOS Y GENÉRICOS ===");
        List<Charla> listaCharlas = evento1.filtrarActividadesPorTipo(Charla.class);
        List<Taller> listaTalleres = evento1.filtrarActividadesPorTipo(Taller.class);
        List<Curso> listaCursos = evento1.filtrarActividadesPorTipo(Curso.class);

        System.out.println("Costo total en Charlas: $" + evento1.calcularCostoMateriales(listaCharlas));
        System.out.println("Costo total en Talleres: $" + evento1.calcularCostoMateriales(listaTalleres));
        System.out.println("Costo total en Cursos: $" + evento1.calcularCostoMateriales(listaCursos));

        System.out.println("\n[HILO PRINCIPAL] Finalizó sus tareas. Esperando que termine el hilo secundario...");
    }
}