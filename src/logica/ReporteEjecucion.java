package logica;

import java.util.List;

import entidades.Persona;

/**
 * Clase contenedora que encapsula las métricas de rendimiento 
 * de la ejecución de un algoritmo y el equipo resultante.
 */
public class ReporteEjecucion {
    // El resultado final que arrojó el algoritmo
    private List<Persona> equipoGanador;
    private int puntajeMaximoObtenido;

    // Métricas exclusivas del comportamiento y rendimiento del algoritmo
    private long tiempoDeEjecucionMs;
    private int nodosRecorridos;
    private int casosBaseContados;
    private int podasRealizadas;

    // Constructor completo
    public ReporteEjecucion(List<Persona> equipoGanador, int puntajeMaximoObtenido, 
                            long tiempoDeEjecucionMs, int nodosRecorridos, 
                            int casosBaseContados, int podasRealizadas) {
        this.equipoGanador = equipoGanador;
        this.puntajeMaximoObtenido = puntajeMaximoObtenido;
        this.tiempoDeEjecucionMs = tiempoDeEjecucionMs;
        this.nodosRecorridos = nodosRecorridos;
        this.casosBaseContados = casosBaseContados;
        this.podasRealizadas = podasRealizadas;
    }

    // --- GETTERS ---
    // (No agregamos Setters porque es una buena práctica que este reporte sea inmutable una vez creado)

    public List<Persona> getEquipoGanador() {
        return equipoGanador;
    }

    public int getPuntajeMaximoObtenido() {
        return puntajeMaximoObtenido;
    }

    public long getTiempoDeEjecucionMs() {
        return tiempoDeEjecucionMs;
    }

    public int getNodosRecorridos() {
        return nodosRecorridos;
    }

    public int getCasosBaseContados() {
        return casosBaseContados;
    }

    public int getPodasRealizadas() {
        return podasRealizadas;
    }

    // Opcional: Un método toString por si necesitás testear por consola que todo se esté cargando bien
    @Override
    public String toString() {
        return "ReporteEjecucion{" +
                "Puntaje=" + puntajeMaximoObtenido +
                ", Tiempo=" + tiempoDeEjecucionMs + " ms" +
                ", Nodos=" + nodosRecorridos +
                ", Casos Base=" + casosBaseContados +
                ", Podas=" + podasRealizadas +
                '}';
    }
}