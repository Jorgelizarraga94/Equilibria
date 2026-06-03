package logica;

import entidades.Persona;
import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgoritmoBackTracking extends SwingWorker<List<Persona>, Void> {

    private List<Persona> personasDisponibles;
    private List<String[]> incompatibilidades;
    private int[] requerimientos;

    private List<Persona> mejorEquipo;
    private int mejorPuntaje;

    private static final Map<String, Integer> ROL_INDEX = new HashMap<>();
    static {
        ROL_INDEX.put("líder de proyecto", 0);
        ROL_INDEX.put("arquitecto", 1);
        ROL_INDEX.put("programador", 2);
        ROL_INDEX.put("tester", 3);
    }

    public AlgoritmoBackTracking(List<Persona> personasDisponibles, List<String[]> incompatibilidades, int[] requerimientos) {
        this.personasDisponibles = new ArrayList<>(personasDisponibles);
        this.incompatibilidades = new ArrayList<>(incompatibilidades);
        this.requerimientos = requerimientos;
        this.mejorEquipo = new ArrayList<>();
        this.mejorPuntaje = -1;
    }

    @Override
    protected List<Persona> doInBackground() throws Exception {
        mejorEquipo = new ArrayList<>();
        mejorPuntaje = -1;

        List<Persona> combinacionActual = new ArrayList<>();
        int[] rolesActuales = new int[4];
        
        buscarEquipo(0, combinacionActual, rolesActuales);

        return mejorPuntaje == -1 ? new ArrayList<>() : mejorEquipo;
    }

    private void buscarEquipo(int indice, List<Persona> combinacionActual, int[] rolesActuales) {
        if (cumpleTodosRequerimientos(rolesActuales)) {
            evaluarSolucion(combinacionActual);
            return;
        }

        if (indice == personasDisponibles.size() || superaAlgonRequerimiento(rolesActuales)) {
            return;
        }

        buscarEquipo(indice + 1, combinacionActual, rolesActuales);

        Persona candidata = personasDisponibles.get(indice);
        Integer rIdx = ROL_INDEX.get(candidata.getRol());

        if (rIdx != null && rolesActuales[rIdx] < requerimientos[rIdx] && !esIncompatible(candidata, combinacionActual)) {
            combinacionActual.add(candidata);
            rolesActuales[rIdx]++;

            buscarEquipo(indice + 1, combinacionActual, rolesActuales);

            combinacionActual.remove(combinacionActual.size() - 1);
            rolesActuales[rIdx]--;
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
        for (int i = 0; i < requerimientos.length; i++) {
            if (rolesActuales[i] != requerimientos[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean superaAlgonRequerimiento(int[] rolesActuales) {
        for (int i = 0; i < requerimientos.length; i++) {
            if (rolesActuales[i] > requerimientos[i]) {
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
}
