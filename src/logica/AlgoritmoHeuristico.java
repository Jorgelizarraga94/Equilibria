package logica;

import javax.swing.SwingWorker;
import entidades.Persona;
import entidades.Requerimiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.SwingWorker;

public class AlgoritmoHeuristico extends SwingWorker<List<Persona>, Void> {
    private List<Persona> personasDisponibles;
    private Set<String> mapaIncompatibilidades;
    private Map<String, Integer> requerimientos; // Estructura dinámica clave-valor
    private List<Persona> mejorEquipo;
    private int mejorPuntaje;
    private int nodosRecorridos;
    private int casosBaseContados;
    private long tiempoMs;

    public AlgoritmoHeuristico(List<Persona> personas, List<String[]> incompatibilidades, List<Requerimiento> reqs) {
        this.personasDisponibles = new ArrayList<>(personas);
        this.mejorEquipo = new ArrayList<>();
        this.mejorPuntaje = -1;
        this.nodosRecorridos = 0;
        this.casosBaseContados = 0;
        this.tiempoMs = 0;
        
        // Inicializamos y cargamos el mapa dinámicamente con los requerimientos recibidos
        this.requerimientos = new HashMap<>();
        for (Requerimiento r : reqs) {
            String rolNormalizado = normalizarRol(r.getRol());
            this.requerimientos.put(rolNormalizado, r.getCantidad());
        }

        this.mapaIncompatibilidades = new HashSet<>();
        for (String[] inc : incompatibilidades) {
            String p1 = inc[0];
            String p2 = inc[1];
            if (p1.compareTo(p2) < 0) {
                mapaIncompatibilidades.add(p1 + "___" + p2);
            } else {
                mapaIncompatibilidades.add(p2 + "___" + p1);
            }
        }
    }

    @Override
    protected List<Persona> doInBackground() throws Exception {
        long inicioNano = System.nanoTime();
        
        mejorEquipo = ejecutar();
        
        long finNano = System.nanoTime();
        double tiempoDoubleMs = (finNano - inicioNano) / 1_000_000.0;
        
        this.tiempoMs = Math.round(tiempoDoubleMs); 
        if (this.tiempoMs == 0 && (finNano - inicioNano) > 0) {
            this.tiempoMs = 1; 
        }
        
        return mejorEquipo;
    }

    public List<Persona> ejecutar() {
        // Ordenamos los candidatos de mayor a menor calificación (Estrategia Greedy / Golosa)
        Collections.sort(personasDisponibles, new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
                return Integer.compare(p2.getCalificacion(), p1.getCalificacion());
            }
        });

        List<Persona> equipoConstruido = new ArrayList<>();
        Map<String, Integer> asignadosPorRol = new HashMap<>();
        
        // Inicializamos los contadores de asignación basados en los requerimientos reales
        for (String rol : requerimientos.keySet()) {
            asignadosPorRol.put(rol, 0);
        }

        for (Persona p : personasDisponibles) {
            nodosRecorridos++;
            
            String rol = normalizarRol(p.getRol());
            
            // Si el rol de esta persona no está en los requerimientos del equipo, se la ignora
            if (!requerimientos.containsKey(rol)) {
                continue;
            }

            int actuales = asignadosPorRol.getOrDefault(rol, 0);
            int requeridos = requerimientos.getOrDefault(rol, 0);
            
            if (actuales >= requeridos) {
                continue;
            }

            boolean esIncompatible = false;
            for (Persona seleccionado : equipoConstruido) {
                String nom1 = p.getNombre();
                String nom2 = seleccionado.getNombre();
                String clave = nom1.compareTo(nom2) < 0 ? nom1 + "___" + nom2 : nom2 + "___" + nom1;
                
                if (mapaIncompatibilidades.contains(clave)) {
                    esIncompatible = true;
                    break;
                }
            }

            if (!esIncompatible) {
                equipoConstruido.add(p);
                asignadosPorRol.put(rol, actuales + 1);
            }
        }

        casosBaseContados++;
        boolean requisitosCumplidos = true;
        int puntajeTotal = 0;
        
        // Verificación basada únicamente en las llaves del mapa de requerimientos
        for (String rol : requerimientos.keySet()) {
            if (asignadosPorRol.getOrDefault(rol, 0) < requerimientos.get(rol)) {
                requisitosCumplidos = false;
                break;
            }
        }

        if (requisitosCumplidos) {
            for (Persona p : equipoConstruido) {
                puntajeTotal += p.getCalificacion();
            }
            this.mejorPuntaje = puntajeTotal;
            return equipoConstruido;
        }
        
        this.mejorPuntaje = 0;
        return new ArrayList<>();
    }

    /**
     * Helper para unificar criterios de strings de roles y evitar discrepancias (ej: "líder de proyecto" vs "lider")
     */
    private String normalizarRol(String rol) {
        if (rol == null) return "";
        String r = rol.toLowerCase().trim();
        if (r.equals("líder de proyecto") || r.equals("líder") || r.equals("lider")) {
            return "lider"; // Estandarizamos al string que desees usar como clave unificada
        }
        return r;
    }

    // Getters para UI / Métricas
    public int getMejorPuntaje() { return this.mejorPuntaje; }
    public int getNodos() { return this.nodosRecorridos; }
    public int getCasosBase() { return this.casosBaseContados; }
    public long getTiempoMs() { return this.tiempoMs; }
}