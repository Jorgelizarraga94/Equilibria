package logica;

import entidades.Persona;
import entidades.Requerimiento;
import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AlgoritmoFuerzaBruta extends SwingWorker<List<Persona>, Void> {

    private List<Persona> personasDisponibles;
    private List<String[]> incompatibilidades;
    private List<Requerimiento> requerimientos;
    private Map<String, Integer> roles;

    private List<Persona> mejorEquipo;
    private int mejorPuntaje = 0;
    
    // Métricas
    private int nodosRecorridos = 0;
    private int casosBaseContados = 0;
    private int podasRealizadas = 0; // Se mantiene por consistencia de estructura, aunque FB no poda.
    private long tiempoMs = 0;

    public AlgoritmoFuerzaBruta(List<Persona> personasDisponibles, List<String[]> incompatibilidades, List<Requerimiento> requerimientos) {
        this.personasDisponibles = new ArrayList<>(personasDisponibles);
        this.incompatibilidades = new ArrayList<>(incompatibilidades);
        this.requerimientos = new ArrayList<>(requerimientos); 
        this.mejorEquipo = new ArrayList<>();
        this.mejorPuntaje = -1;

        // Construir el mapa de índices dinámicamente según los requerimientos recibidos
        this.roles = new HashMap<>();
        for (int i = 0; i < this.requerimientos.size(); i++) {
            String rolNormalizado = this.requerimientos.get(i).getRol().toLowerCase().trim();
            this.roles.put(rolNormalizado, i);
        }
    }

    @Override
    protected List<Persona> doInBackground() throws Exception {
        long inicio = System.currentTimeMillis();
        mejorEquipo = new ArrayList<>();
        mejorPuntaje = -1;
        
        List<Persona> combinacionActual = new ArrayList<>();
        generarCombinaciones(0, combinacionActual);
        this.tiempoMs = System.currentTimeMillis() - inicio;  
        return mejorPuntaje == -1 ? new ArrayList<>() : mejorEquipo;
    }

    private void generarCombinaciones(int indice, List<Persona> combinacionActual) {
        this.nodosRecorridos++;

        if (indice == personasDisponibles.size()) {
            this.casosBaseContados++; 
            evaluarSolucion(combinacionActual);
            return;
        }
        
        // Opción 1: No incluir a la persona actual
        generarCombinaciones(indice + 1, combinacionActual);
        
        // Opción 2: Incluir a la persona actual
        combinacionActual.add(personasDisponibles.get(indice));
        generarCombinaciones(indice + 1, combinacionActual);
        
        // Backtracking puro de Fuerza Bruta (deshacer la inclusión)
        combinacionActual.remove(combinacionActual.size() - 1);
    }

    private void evaluarSolucion(List<Persona> candidatos) {
        if (!cumpleRequerimientos(candidatos) || contieneIncompatibles(candidatos)) {
            return;
        }

        int puntajeActual = 0;
        for (Persona p : candidatos) {
            puntajeActual += p.getCalificacion();
        }

        if (puntajeActual > mejorPuntaje) {
            mejorPuntaje = puntajeActual;
            mejorEquipo = new ArrayList<>(candidatos);
        }
    }

    private boolean cumpleRequerimientos(List<Persona> candidatos) {
        int[] contadorRoles = new int[requerimientos.size()];
        
        for (Persona p : candidatos) {
            Integer idx = roles.get(p.getRol().toLowerCase().trim());
            if (idx != null) {
                contadorRoles[idx]++;
            }
        }
        
        for (int i = 0; i < requerimientos.size(); i++) {
            if (contadorRoles[i] != requerimientos.get(i).getCantidad()) {
                return false;
            }
        }
        return true;
    }

    private boolean contieneIncompatibles(List<Persona> candidatos) {
        List<String> nombresEnEquipo = new ArrayList<>();
        for (Persona p : candidatos) {
            nombresEnEquipo.add(p.getNombre());
        }
        for (String[] par : incompatibilidades) {
            if (nombresEnEquipo.contains(par[0]) && nombresEnEquipo.contains(par[1])) {
                return true;
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
    public long getTiempoMs() { return this.tiempoMs; }
    public int getPodas() { return this.podasRealizadas; }	
}