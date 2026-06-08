package test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import entidades.Persona;
import entidades.Requerimiento;
import logica.AlgoritmoFuerzaBruta;
import java.util.ArrayList;
import java.util.List;

public class AFBTest {

    private AlgoritmoFuerzaBruta solver;
    private List<Persona> personasDisponibles;
    private List<String[]> incompatibilidades;
    private List<Requerimiento> requerimientos;

 // Líderes (Necesitamos 1)
 private final Persona liderPerfecto = new Persona("Carlos", "Lider", 5);
 private final Persona liderBueno = new Persona("Ana", "Lider", 4);

 // Arquitectos (Necesitamos 2)
 private final Persona arq1 = new Persona("Elena", "Arquitecto", 5);
 private final Persona arq2 = new Persona("Marcos", "Arquitecto", 4);

 // Programadores (Necesitamos 4)
 private final Persona prog1 = new Persona("Sofía", "Programador", 5);
 private final Persona prog2 = new Persona("Juan", "Programador", 4);
 private final Persona prog3 = new Persona("Pedro", "Programador", 3);
 private final Persona prog4 = new Persona("Lucía", "Programador", 3);
 private final Persona prog5 = new Persona("Bruno", "Programador", 1);

 // Testers (Necesitamos 5)
 private final Persona test1 = new Persona("Mía", "Tester", 5);
 private final Persona test2 = new Persona("Facundo", "Tester", 4);
 private final Persona test3 = new Persona("Rocío", "Tester", 4);
 private final Persona test4 = new Persona("Tomás", "Tester", 3);
 private final Persona test5 = new Persona("Mateo", "Tester", 2);
    

 @Before
 public void inicio() {
     personasDisponibles = new ArrayList<>();
     incompatibilidades = new ArrayList<>();
     requerimientos = new ArrayList<>();
     requerimientos.add(new Requerimiento("Lider", 1));
     requerimientos.add(new Requerimiento("Arquitecto", 2 ));
     requerimientos.add(new Requerimiento("Programador", 4 ));
     requerimientos.add(new Requerimiento("Tester", 5 ));
 }

    private List<Persona> inicializarYEjecutar() {
        solver = new AlgoritmoFuerzaBruta(personasDisponibles, incompatibilidades, requerimientos);
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