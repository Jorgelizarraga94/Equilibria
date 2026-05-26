package test;

import entidades.Persona;
import logica.AlgoritmoBackTracking;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ABTTest {

    private AlgoritmoBackTracking solver;
    private List<Persona> personasDisponibles;
    private List<String[]> incompatibilidades;
    private int[] requerimientos;


 // Líderes (Necesitamos 1)
 private final Persona liderPerfecto = new Persona("Carlos", "líder de proyecto", 5);
 private final Persona liderBueno = new Persona("Ana", "líder de proyecto", 4);

 // Arquitectos (Necesitamos 2)
 private final Persona arq1 = new Persona("Elena", "arquitecto", 5);
 private final Persona arq2 = new Persona("Marcos", "arquitecto", 4);
 private final Persona arq3 = new Persona("Lucas", "arquitecto", 2);

 // Programadores (Necesitamos 4)
 private final Persona prog1 = new Persona("Sofía", "programador", 5);
 private final Persona prog2 = new Persona("Juan", "programador", 4);
 private final Persona prog3 = new Persona("Pedro", "programador", 3);
 private final Persona prog4 = new Persona("Lucía", "programador", 3);
 private final Persona prog5 = new Persona("Bruno", "programador", 1);

 // Testers (Necesitamos 5)
 private final Persona test1 = new Persona("Mía", "tester", 5);
 private final Persona test2 = new Persona("Facundo", "tester", 4);
 private final Persona test3 = new Persona("Rocío", "tester", 4);
 private final Persona test4 = new Persona("Tomás", "tester", 3);
 private final Persona test5 = new Persona("Mateo", "tester", 2);

 @Before
 public void inicio() {
     personasDisponibles = new ArrayList<>();
     incompatibilidades = new ArrayList<>();
     requerimientos = new int[]{1, 2, 4, 5}; 
 }

    private List<Persona> inicializarYEjecutar() {
        solver = new AlgoritmoBackTracking(personasDisponibles, incompatibilidades, requerimientos);
        return solver.ejecutar();
    }
    private void registrarIncompatibilidad(Persona p1, Persona p2) {
        incompatibilidades.add(new String[]{p1.getNombre(), p2.getNombre()});
    }

    @Test
    public void testEquipoVacioSiNoHayPersonas() {
        List<Persona> resultado = inicializarYEjecutar();
        
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testSeleccionaAlDeMejorCalificacion() {
        personasDisponibles.add(arq1); 
        personasDisponibles.add(arq2);
        personasDisponibles.add(test1); 
        personasDisponibles.add(test2); 
        personasDisponibles.add(test3); 
        personasDisponibles.add(test4); 
        personasDisponibles.add(test5);
        personasDisponibles.add(liderBueno);
        personasDisponibles.add(liderPerfecto);
        personasDisponibles.add(prog1);
        personasDisponibles.add(prog2);
        personasDisponibles.add(prog3);
        personasDisponibles.add(prog4);
        personasDisponibles.add(prog5);

        List<Persona> resultado = inicializarYEjecutar();
        assertEquals(12, resultado.size()); 
        assertTrue(resultado.contains(liderPerfecto));
        assertFalse(resultado.contains(liderBueno));
        assertTrue(resultado.contains(prog1));
        assertFalse(resultado.contains(prog5)); // Bruno quedó afuera por su baja nota
    }

    @Test
    public void testEvitaIncompatibilidadAunPerdiendoPuntaje() {
        personasDisponibles.add(arq1); 
        personasDisponibles.add(arq2);
        personasDisponibles.add(test1); 
        personasDisponibles.add(test2); 
        personasDisponibles.add(test3); 
        personasDisponibles.add(test4); 
        personasDisponibles.add(test5);
        personasDisponibles.add(liderPerfecto); // Carlos (5)
        personasDisponibles.add(prog1); // Sofía (5)
        personasDisponibles.add(prog2); 
        personasDisponibles.add(prog3); 
        personasDisponibles.add(prog4);
        personasDisponibles.add(prog5); // Bruno (1)

        registrarIncompatibilidad(liderPerfecto, prog1);

        List<Persona> resultado = inicializarYEjecutar();

        assertTrue(resultado.contains(liderPerfecto));
        assertTrue(resultado.contains(prog5)); 
        assertFalse(resultado.contains(prog1)); // Sofía queda afuera por incompatibilidad
        assertEquals(12, resultado.size());
    }
    @Test
    public void testEvitaIncompatibilidadEnEquipoGrande() {

        personasDisponibles.add(arq1); 
        personasDisponibles.add(arq2);
        personasDisponibles.add(test1); 
        personasDisponibles.add(test2); 
        personasDisponibles.add(test3); 
        personasDisponibles.add(test4); 
        personasDisponibles.add(test5);
        
        personasDisponibles.add(liderPerfecto);
        personasDisponibles.add(prog1); // Sofía (5)
        personasDisponibles.add(prog2); 
        personasDisponibles.add(prog3); 
        personasDisponibles.add(prog4);

        registrarIncompatibilidad(liderPerfecto, prog1);

        List<Persona> resultado = inicializarYEjecutar();

        assertFalse(resultado.contains(liderPerfecto) && resultado.contains(prog1));
    }

    @Test
    public void testSinSolucionPosibleDevuelveVacio() {
        personasDisponibles.add(liderPerfecto);
        personasDisponibles.add(arq1); 
        personasDisponibles.add(arq2);
        personasDisponibles.add(prog1); 
        personasDisponibles.add(prog2); 
        personasDisponibles.add(prog3); 
        personasDisponibles.add(prog4);
        personasDisponibles.add(test1); 
        personasDisponibles.add(test2); 
        personasDisponibles.add(test3); 
        personasDisponibles.add(test4); 
        personasDisponibles.add(test5);
        registrarIncompatibilidad(liderPerfecto, test1);

        List<Persona> resultado = inicializarYEjecutar();

        assertTrue(resultado.isEmpty()); 
    }

    @Test
    public void testFaltaDePersonalParaCumplirRequerimiento() {
        personasDisponibles.add(liderPerfecto);
        personasDisponibles.add(arq1); 
        personasDisponibles.add(arq2);
        personasDisponibles.add(prog1); 
        personasDisponibles.add(prog2); 
        personasDisponibles.add(prog3); 
        personasDisponibles.add(prog4);
        personasDisponibles.add(test1); 
        personasDisponibles.add(test2); 
        personasDisponibles.add(test3); 
        personasDisponibles.add(test4);

        List<Persona> resultado = inicializarYEjecutar();
        assertTrue(resultado.isEmpty());
    }
}