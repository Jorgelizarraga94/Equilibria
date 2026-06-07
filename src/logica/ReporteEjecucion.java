package logica;

import java.util.List;
import entidades.Persona;

public class ReporteEjecucion {
    private List<Persona> equipoGanador;
    private int puntajeMaximoObtenido;
    private long tiempoDeEjecucionMs;
    private int nodosRecorridos;
    private int casosBaseContados;
    private int podasRealizadas;

    // Constructor
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

    // GETTERS
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