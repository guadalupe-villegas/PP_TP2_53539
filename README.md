# Trabajo Práctico N° 2 - Paradigmas de la Programación

**Universidad:** Universidad Tecnológica Nacional - Facultad Regional Mendoza (UTN FRM)
**Alumna:** Guadalupe Abril Villegas Barboza
**Legajo:** 53539

---

## Descripción del Proyecto
Este repositorio contiene el código fuente correspondiente al Trabajo Práctico 2, el cual es una refactorización y expansión del sistema de **Gestión de Eventos Universitarios** iniciado en el TP1.

El sistema permite administrar eventos, asignar salas, crear actividades (Charlas, Talleres y Cursos), inscribir estudiantes controlando los cupos disponibles, y generar la emisión de tickets y certificados de asistencia.

## Conceptos y Tecnologías Implementadas (TP1 + TP2)

El proyecto está desarrollado en Java, estructurado de forma modular mediante paquetes (`modelo`, `modelo.actividades`, `excepciones`, `certificacion`, `hilos`), e incluye la aplicación práctica de los siguientes conceptos del paradigma Orientado a Objetos:

1. **Herencia y Polimorfismo (Base TP1):**
    - Jerarquía de clases donde `Charla`, `Taller` y `Curso` heredan de la clase abstracta `Actividad`.
2. **Manejo de Excepciones:**
    - Creación y captura de la excepción personalizada `CupoExcedidoException` para evitar inscripciones cuando no hay disponibilidad.
3. **Persistencia de Objetos:**
    - Implementación de la interfaz `Serializable` y uso de flujos de entrada/salida para guardar el estado del evento en un archivo binario (`evento.dat`).
4. **Interfaces:**
    - Diseño de la interfaz `Certificable` para emitir diplomas exclusivamente en los Cursos y Talleres. Las charlas son excluidas lógicamente mediante el operador `instanceof`.
5. **Genéricos y Comodines (Wildcards):**
    - Uso de un método parametrizado (`<T extends Actividad>`) para filtrar colecciones en tiempo de ejecución.
    - Uso de comodines (`List<? extends Actividad>`) para abstraer el cálculo de costos independientemente del tipo de actividad.
6. **Clases Anidadas y Concurrencia (Hilos):**
    - Implementación de la clase anidada `TicketDeAcceso` dentro de `Inscripcion`.
    - Creación del proceso concurrente `EnvioTicketsThread` extendiendo de `Thread`, el cual simula el envío de accesos en segundo plano interactuando en simultáneo con las impresiones del hilo principal.

## Ejecución del Proyecto (IntelliJ IDEA)
Para evaluar el proyecto:
1. Clonar este repositorio vía HTTPS.
2. Abrir la carpeta raíz como un proyecto en IntelliJ IDEA.
3. Ejecutar la clase principal `principal.App`.
4. La salida por consola demostrará secuencialmente: el manejo del error de cupos, la emisión de certificados válidos, los cálculos de costos y la impresión concurrente de los tickets de acceso.