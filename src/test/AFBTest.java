package test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import entidades.Persona;
import logica.AlgoritmoFuerzaBruta; // Importamos la clase de Fuerza Bruta
import java.util.ArrayList;
import java.util.List;

public class AFBTest {

    private AlgoritmoFuerzaBruta solver;
    private List<Persona> personasDisponibles;
    private List<String[]> incompatibilidades;
    private int[] requerimientos;

    private final Persona liderProfesional = new Persona("Carlos", "líder de proyecto", 5);
    private final Persona liderBueno = new Persona("Ana", "líder de proyecto", 4);
    private final Persona progProfesional = new Persona("Sofía", "programador", 5);
    private final Persona progNormal = new Persona("Juan", "programador", 3);
    private final Persona progNovato = new Persona("Pedro", "programador", 1);
    

    @Before
    public void setUp() {
        personasDisponibles = new ArrayList<>();
        incompatibilidades = new ArrayList<>();
        // Requerimiento base: 1 Líder y 1 Programador
        requerimientos = new int[]{1, 0, 1, 0}; 
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
        personasDisponibles.add(liderBueno);
        personasDisponibles.add(progNovato);
        personasDisponibles.add(progProfesional);

        List<Persona> resultado = inicializarYEjecutar();

        assertTrue(resultado.contains(liderBueno));
        assertTrue(resultado.contains(progProfesional));
        assertFalse(resultado.contains(progNovato));
        assertEquals(2, resultado.size());
    }

    @Test
    public void testEvitaIncompatibilidadAunPerdiendoPuntaje() {
        personasDisponibles.add(liderProfesional);
        personasDisponibles.add(progProfesional);  
        personasDisponibles.add(progNormal); 

        registrarIncompatibilidad(liderProfesional, progProfesional);

        List<Persona> resultado = inicializarYEjecutar();

        // Al igual que Backtracking, FB debe encontrar la solución óptima válida
        assertTrue(resultado.contains(liderProfesional));
        assertTrue(resultado.contains(progNormal));
        assertFalse(resultado.contains(progProfesional));
    }

    @Test
    public void testSinSolucionPosibleDevuelveVacio() {
        personasDisponibles.add(liderProfesional);
        personasDisponibles.add(progProfesional);

        registrarIncompatibilidad(liderProfesional, progProfesional);

        List<Persona> resultado = inicializarYEjecutar();

        assertTrue(resultado.isEmpty()); 
    }

    @Test
    public void testFaltaDePersonalParaCumplirRequerimiento() {
        personasDisponibles.add(liderProfesional);

        List<Persona> resultado = inicializarYEjecutar();

        assertTrue(resultado.isEmpty());
    }
}