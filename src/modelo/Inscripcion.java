package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private TicketDeAcceso ticket; // Nuevo atributo para guardar el ticket

    public Inscripcion(String estado, Estudiante estudiante) {
        this.fecha = LocalDate.now();
        this.estado = estado;
        this.estudiante = estudiante;
    }

    public Estudiante getEstudiante() { return estudiante; }
    public TicketDeAcceso getTicket() { return ticket; }
    public String getEstado() { return estado; }

    public void confirmarInscripcion() {
        this.estado = "Confirmada";
        this.ticket = new TicketDeAcceso("TKT-" + this.estudiante.getLegajo(), "QR-12345");
    }

    public class TicketDeAcceso implements Serializable {
        private String nroTicket;
        private String codigoQR;

        public TicketDeAcceso(String nroTicket, String codigoQR) {
            this.nroTicket = nroTicket;
            this.codigoQR = codigoQR;
        }

        public void enviarTicket(String nombreActividad) {
            System.out.println("[HILO DE TICKETS] -> Enviando ticket " + this.nroTicket +
                    " al alumno " + estudiante.getNombre() +
                    " para la actividad: " + nombreActividad); // Lo sumamos al texto
        }
    }
}