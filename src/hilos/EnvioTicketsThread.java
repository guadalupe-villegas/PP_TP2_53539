package hilos;

import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.actividades.Actividad;

public class EnvioTicketsThread extends Thread {
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("[HILO DE TICKETS] Iniciando proceso en segundo plano...");

        for (Actividad act : evento.getActividades()) {
            for (Inscripcion insc : act.getInscripciones()) {
                if (insc.getTicket() != null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Error en el hilo de tickets.");
                    }
                    // Le pasamos el título de la actividad actual al método
                    insc.getTicket().enviarTicket(act.getTitulo());
                }
            }
        }
        System.out.println("[HILO DE TICKETS] Finalizó el envío de todos los tickets.");
    }
}