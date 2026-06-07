package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Persona;
import entidades.Requerimiento;

public class AlgoritmoBackTracking extends javax.swing.SwingWorker<List<Persona>, Void> {

    private List<Persona> personasDisponibles;
    private List<String[]> incompatibilidades;
    private List<Requerimiento> requerimientos;
    private Map<String, Integer> roles;

    private List<Persona> mejorEquipo;
    private int mejorPuntaje;

    // Métricas
    private int nodosRecorridos;
    private int casosBaseContados;
    private int podasRealizadas;
    private long tiempoMs;

    public AlgoritmoBackTracking(List<Persona> personasDisponibles, List<String[]> incompatibilidades, List<Requerimiento> requerimientos) {
    	
        this.personasDisponibles = new ArrayList<>(personasDisponibles);
        this.incompatibilidades = new ArrayList<>(incompatibilidades);
        this.requerimientos = new ArrayList<>(requerimientos);
        this.mejorEquipo = new ArrayList<>();
        this.mejorPuntaje = -1;

        this.nodosRecorridos = 0;
        this.casosBaseContados = 0;
        this.podasRealizadas = 0;

        this.roles = new HashMap<>();
        for (int i = 0; i < this.requerimientos.size(); i++) {
            String rolNormalizado = this.requerimientos.get(i).getRol().toLowerCase().trim();
            this.roles.put(rolNormalizado, i);
        }
    }

    @Override
    protected List<Persona> doInBackground() throws Exception {
    	
    	Thread.sleep(1500); // Simulación de carga para ver el calculando... en la UI
        long inicio = System.currentTimeMillis();
        mejorEquipo = new ArrayList<>();
        mejorPuntaje = -1;

        List<Persona> combinacionActual = new ArrayList<>();
        
        // El tamaño del vector depende dinámicamente de la cantidad de requerimientos
        int[] rolesActuales = new int[requerimientos.size()];
        buscarEquipo(0, combinacionActual, rolesActuales);
        this.tiempoMs = System.currentTimeMillis() - inicio;
        return mejorPuntaje == -1 ? new ArrayList<>() : mejorEquipo;
    }

    private void buscarEquipo(int indice, List<Persona> combinacionActual, int[] rolesActuales) {
        this.nodosRecorridos++;
        
        if (cumpleTodosRequerimientos(rolesActuales)) {
            this.casosBaseContados++;
            evaluarSolucion(combinacionActual);
            return;
        }
        
        if (superaAlgunRequerimiento(rolesActuales)) {
            this.podasRealizadas++;
            return;
        }
        
        if (indice == personasDisponibles.size()) {
            this.casosBaseContados++;
            return;
        }
        
        // Exclusión: Camino donde NO agregamos a la persona actual
        buscarEquipo(indice + 1, combinacionActual, rolesActuales);

        // Inclusión: Intentar agregar a la persona actual
        Persona candidata = personasDisponibles.get(indice);
        Integer rIdx = roles.get(candidata.getRol().toLowerCase().trim());
        
        // Validamos si el rol de la persona es requerido en esta ejecución
        if (rIdx != null && rolesActuales[rIdx] < requerimientos.get(rIdx).getCantidad() && !esIncompatible(candidata, combinacionActual)) {
            combinacionActual.add(candidata);
            rolesActuales[rIdx]++;

            buscarEquipo(indice + 1, combinacionActual, rolesActuales);

            // Backtracking (Deshacer cambio)
            combinacionActual.remove(combinacionActual.size() - 1);
            rolesActuales[rIdx]--;
        } else {
            this.podasRealizadas++; 
        }
    }

    private void evaluarSolucion(List<Persona> candidatos) {
        int puntajeActual = 0;
        for (Persona p : candidatos) {
            puntajeActual += p.getCalificacion();
        }

        if (puntajeActual > mejorPuntaje) {
            mejorPuntaje = puntajeActual;
            mejorEquipo = new ArrayList<>(candidatos);
        }
    }

    private boolean cumpleTodosRequerimientos(int[] rolesActuales) {
        for (int i = 0; i < requerimientos.size(); i++) {
            if (rolesActuales[i] != requerimientos.get(i).getCantidad()) {
                return false;
            }
        }
        return true;
    }

    private boolean superaAlgunRequerimiento(int[] rolesActuales) {
        for (int i = 0; i < requerimientos.size(); i++) {
            if (rolesActuales[i] > requerimientos.get(i).getCantidad()) {
                return true;
            }
        }
        return false;
    }

    private boolean esIncompatible(Persona p, List<Persona> equipoActual) {
        for (Persona integrante : equipoActual) {
            for (String[] par : incompatibilidades) {
                if ((par[0].equals(p.getNombre()) && par[1].equals(integrante.getNombre())) ||
                    (par[1].equals(p.getNombre()) && par[0].equals(integrante.getNombre()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Persona> ejecutar() {
        try {
            return this.doInBackground();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int getMejorPuntaje() { return this.mejorPuntaje; }
    public int getNodos() { return this.nodosRecorridos; }
    public int getCasosBase() { return this.casosBaseContados; }
    public int getPodas() { return this.podasRealizadas; }
    public long getTiempoMs() { return this.tiempoMs; }
}
