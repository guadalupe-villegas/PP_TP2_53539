package certificacion;

import modelo.Estudiante;

public interface Certificable {
    // En las interfaces, los atributos son constantes (public static final)
    String ENTIDAD_EMISORA = "UTN - Facultad Regional Mendoza";

    // Los métodos en las interfaces solo se declaran, no tienen cuerpo (son abstractos)
    String generarCertificado(Estudiante estudiante);
}