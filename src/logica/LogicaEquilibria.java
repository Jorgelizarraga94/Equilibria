package logica;

import java.util.ArrayList;
import java.util.HashMap;

//Una lista de personas

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import javax.swing.SwingWorker;

import entidades.Incompatibilidad;
import entidades.Persona;

//Una lista de personas incompatibles entre si (si es que hay alguna)


//Una lista de equipos que me dio luego de llamar a generar equipo (calcular backtracking o heuristica)


//Un MAP de requirimientos (String) y cantidad (int) necesaria de personas con ese requerimiento (ejemplo: "Programador" -> 2, "Diseñador" -> 1, etc.)
//Para llamarla en el algoritomo backtracking, para saber cuantas personas con cada requerimiento necesito para generar el equipo.



public class LogicaEquilibria {
	Map<Long, Persona> personas = new HashMap<>();
	//List<Persona> personas; //Lista de personas disponibles para formar equipos
	Map<String, Integer> requerimientos; //Requerimientos necesarios para formar un equipo
	//List<Equipo> equiposGenerados; //Lista de equipos generados luego de llamar a generar equipo (calcular backtracking o heuristica)
	private List<Incompatibilidad> incompatibilidades = new ArrayList<>(); //Lista de incompatibilidades entre personas (si es que hay alguna)
	//Lo manejo con la clase incompatibilidad, que tiene dos personas, y si esas dos personas estan en el mismo equipo, ese equipo no es valido
	List<Persona> resultadoFB;
	

	List<Persona> resultadoBT;
	//Creo que se deberia hacer lo mismo con la lista de equipos
	//Crear una nueva clase que sea equipos y esta tenga los requerimientos necesarios para formar ese equipo
	//luego el algoritomo tendra que hacer lo suyo con esa informacion, para generar los equipos que cumplan con esos requerimientos.
	
	public void agregarPersona(String nombre, String rol, int calificación, String foto) {
		Persona persona = new Persona(nombre, rol, calificación, foto);
		personas.put(persona.getId(), persona);
	}
	
	public void eliminarPersona(Long id) {
		personas.remove(id);
	}

	public Map<Long, Persona> getPersonas() {
		return personas;
	}

	public List<Persona> getResultadoFB() {
		return resultadoFB;
	}

	public List<Persona> getResultadoBT() {
		return resultadoBT;
	}
	//Para agregar dos personas incompatibles entre si, se agrega a la lista de incompatibilidades una nueva
	public void agregarIncompatibilidad(Persona persona1, Persona persona2) {
		incompatibilidades.add(new Incompatibilidad(persona1, persona2));
		
	}
	//Para obtener la lista de incompatibilidades, se devuelve la lista de incompatibilidades
	public List<Incompatibilidad> getIncompatibilidades() {
		return incompatibilidades;
	}

	public void eliminarIncopatibilidad(int indice) {
		incompatibilidades.remove(indice);
	}
	
	//Agregar funcion de eliminar 
	//Agregar funcion de agregar
	//Llamado a generar equipo (calcular backtracking o heuristica)
	//
	
	
	
	public void calcularEquipo(String algoritmoSeleccionado, Object[][] datosTabla, Consumer<List<Persona>> interfazResultado) {
        List<Persona> deLaGuiPersonas = new ArrayList<>(this.personas.values());
        
        List<String[]> deLaGuiIncompatibilidades = new ArrayList<>();
        for (Incompatibilidad inc : this.incompatibilidades) {
            deLaGuiIncompatibilidades.add(new String[]{
                inc.getPersona1().getNombre(),
                inc.getPersona2().getNombre()
            });
        }
        
        int[] reqs = new int[4];
        for (int i = 0; i < 4; i++) {
            Object valor = datosTabla[i][1];
            if (valor == null) {
                interfazResultado.accept(new ArrayList<>());
                return;
            }
            reqs[i] = Integer.parseInt(valor.toString().trim());
        }
        
        resultadoFB = new ArrayList<>();
        resultadoBT = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(2);
        
        SwingWorker<List<Persona>, Void> workerFB = new AlgoritmoFuerzaBruta(deLaGuiPersonas, deLaGuiIncompatibilidades, reqs) {
            @Override
            protected void done() {
                try {
                    resultadoFB.addAll(get());
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        };
        
        SwingWorker<List<Persona>, Void> workerBT = new AlgoritmoBackTracking(deLaGuiPersonas, deLaGuiIncompatibilidades, reqs) {
        	long tiempoInicioBT = System.currentTimeMillis();
            @Override
            protected void done() {
                try {
                    resultadoBT.addAll(get());
                    long tiempoFinBT = System.currentTimeMillis();
    	            long tiempoTotal = tiempoFinBT - tiempoInicioBT;
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        };
        
        workerFB.execute();
        workerBT.execute();
        
        SwingWorker<List<Persona>, Void> orquestador = new SwingWorker<List<Persona>, Void>() {
            @Override
            protected List<Persona> doInBackground() throws Exception {
                latch.await();
                if ("BackTracking".equals(algoritmoSeleccionado)) {
                    return resultadoBT;
                } else if("Fuerza Bruta".equals(algoritmoSeleccionado)){
                    return resultadoFB;
                }
                else {
                	throw new RuntimeException("Heuristica no programada");
                }
            }
            
            @Override
            protected void done() {
                try {
                    interfazResultado.accept(get());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    interfazResultado.accept(new ArrayList<>());
                }
            }
        };
        
        orquestador.execute();
    }
	// En los atributos de tu panel/controlador:
	private ReporteEjecucion reporteBT;
	private ReporteEjecucion reporteFB;

	// ... Dentro de calcularEquipo ...

	long tiempoInicioBT = System.currentTimeMillis();

	SwingWorker<List<Persona>, Void> workerBT = new AlgoritmoBackTracking(deLaGuiPersonas, deLaGuiIncompatibilidades, reqs) {
	    @Override
	    protected void done() {
	        try {
	            List<Persona> equipoEncontrado = get();
	            long tiempoFinBT = System.currentTimeMillis();
	            long tiempoTotal = tiempoFinBT - tiempoInicioBT;
	            
	            // Le pedimos al algoritmo las métricas de su ejecución interna
	            // Nota: Asumo que tu clase AlgoritmoBackTracking tiene getters para estas variables
	            int nodos = this.getNodosContados();
	            int casosBase = this.getCasosBaseContados();
	            int podas = this.getPodasContadas();
	            int puntaje = this.getMejorPuntajeEncontrado();
	            
	            // Guardamos el reporte completo del algoritmo en el atributo del controlador
	            reporteBT = new ReporteEjecucion(equipoEncontrado, puntaje, tiempoTotal, nodos, casosBase, podas);
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            latch.countDown();
	        }
	    }
	};
}
