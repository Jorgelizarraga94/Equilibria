package logica;

import entidades.Persona;
import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgoritmoFuerzaBruta extends SwingWorker<List<Persona>, Void> {

    private List<Persona> personasDisponibles;
    private List<String[]> incompatibilidades;
    private int[] requerimientos;

    private List<Persona> mejorEquipo;
    private int mejorPuntaje=0;
    private int nodosRecorridos = 0;
    private int casosBaseContados = 0;
    private int podasRealizadas = 0;
    private long tiempoMs = 0;
    
    private static final Map<String, Integer> ROL_INDEX = new HashMap<>();
    static {

        ROL_INDEX.put("líder de proyecto", 0);
        ROL_INDEX.put("arquitecto", 1);
        ROL_INDEX.put("programador", 2);
        ROL_INDEX.put("tester", 3);
        ROL_INDEX.put("líder", 0);
        ROL_INDEX.put("lider", 0);
    }

    public AlgoritmoFuerzaBruta(List<Persona> personasDisponibles, List<String[]> incompatibilidades, int[] requerimientos) {
        this.personasDisponibles = new ArrayList<>(personasDisponibles);
        this.incompatibilidades = new ArrayList<>(incompatibilidades);
        this.requerimientos = requerimientos;
        this.mejorEquipo = new ArrayList<>();
        this.mejorPuntaje = -1;
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
        generarCombinaciones(indice + 1, combinacionActual);
        combinacionActual.add(personasDisponibles.get(indice));
        generarCombinaciones(indice + 1, combinacionActual);
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
        int[] contadorRoles = new int[4];
        for (Persona p : candidatos) {
            // AQUÍ MODIFICAS LA LÍNEA:
            Integer idx = ROL_INDEX.get(p.getRol().toLowerCase().trim());
            
            if (idx != null) contadorRoles[idx]++;
        }
        for (int i = 0; i < requerimientos.length; i++) {
            if (contadorRoles[i] != requerimientos[i]) return false;
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
    public int getMejorPuntaje() {
    	return this.mejorPuntaje; 
    	}
    public int getNodos() {
    	return this.nodosRecorridos; 
    	}
    public int getCasosBase() { 
    	return this.casosBaseContados; 
    	}
    public long getTiempoMs() {
    	return this.tiempoMs; 
    	}
}