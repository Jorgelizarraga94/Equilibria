package logica;

import javax.swing.SwingWorker;
import entidades.Persona;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AlgoritmoHeuristico extends SwingWorker<List<Persona>, Void> {
    private List<Persona> personasDisponibles;
    private Set<String> mapaIncompatibilidades;
    private Map<String, Integer> requerimientos;
    private List<Persona> mejorEquipo;
    private int mejorPuntaje;
    private int nodosRecorridos;
    private int casosBaseContados;
    private long tiempoMs;

    public AlgoritmoHeuristico(List<Persona> personas, List<String[]> incompatibilidades, int[] reqs) {
        this.personasDisponibles = new ArrayList<>(personas);
        this.mejorEquipo = new ArrayList<>();
        this.mejorPuntaje = -1;
        this.nodosRecorridos = 0;
        this.casosBaseContados = 0;
        this.tiempoMs = 0;
        this.requerimientos = new HashMap<>();
        this.requerimientos.put("lider", reqs[0]);
        this.requerimientos.put("arquitecto", reqs[1]);
        this.requerimientos.put("programador", reqs[2]);
        this.requerimientos.put("tester", reqs[3]);
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
        // Convertimos la diferencia de nanosegundos a milisegundos (con decimales)
        double tiempoDoubleMs = (finNano - inicioNano) / 1_000_000.0;
        
        // Redondeamos o guardamos como un long aproximado (al menos marcará 1 ms si tomó algo de tiempo, o podés adaptar tu ReporteEjecucion para que acepte double)
        this.tiempoMs = Math.round(tiempoDoubleMs); 
        if (this.tiempoMs == 0 && (finNano - inicioNano) > 0) {
            this.tiempoMs = 1; // Forzamos un mínimo de 1 ms si la CPU computó pasos pero dio menos de 0.5 ms
        }
        
        return mejorEquipo;
    }

    public List<Persona> ejecutar() {
        Collections.sort(personasDisponibles, new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
                return Integer.compare(p2.getCalificacion(), p1.getCalificacion());
            }
        });

        List<Persona> equipoConstruido = new ArrayList<>();
        Map<String, Integer> asignadosPorRol = new HashMap<>();
        asignadosPorRol.put("líder de proyecto", 0);
        asignadosPorRol.put("arquitecto", 0);
        asignadosPorRol.put("programador", 0);
        asignadosPorRol.put("tester", 0);
        for (Persona p : personasDisponibles) {
            nodosRecorridos++;
            
            String rol = p.getRol().toLowerCase().trim();
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
        
        for (String rol : requerimientos.keySet()) {
            if (asignadosPorRol.get(rol) < requerimientos.get(rol)) {
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