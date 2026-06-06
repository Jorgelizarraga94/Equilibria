package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import entidades.Incompatibilidad;
import entidades.Persona;
import entidades.Requerimiento;

public class LogicaEquilibria {
	private ReporteEjecucion reporteBT;
	private ReporteEjecucion reporteFB;
	private ReporteEjecucion reporteAH;
	Map<Long, Persona> personas = new HashMap<>();
	private List<Requerimiento> requerimientos = new ArrayList<>();
	private List<Incompatibilidad> incompatibilidades = new ArrayList<>();
	List<Persona> resultadoFB;
    List<Persona> resultadoAH;
	List<Persona> resultadoBT;

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

	public void agregarIncompatibilidad(Persona persona1, Persona persona2) {
		incompatibilidades.add(new Incompatibilidad(persona1, persona2));
		
	}

	public List<Incompatibilidad> getIncompatibilidades() {
		return incompatibilidades;
	}

	public void eliminarIncopatibilidad(int indice) {
		incompatibilidades.remove(indice);
	}
	
	public void agregarRequerimientos(String rol, int cantidadRequerimiento) {
		
		requerimientos.add(new Requerimiento(rol, cantidadRequerimiento));
	}
	

	public List<Requerimiento> getRequerimientos() {
		return requerimientos;
	}

	public void calcularEquipo(String algoritmoSeleccionado, Object[][] datosTabla, Consumer<ReporteEjecucion> interfazResultado) {
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
                interfazResultado.accept(new ReporteEjecucion(new ArrayList<>(), 0, 0, 0, 0, 0));
                return;
            }
            reqs[i] = Integer.parseInt(valor.toString().trim());
        }
        
        resultadoFB = new ArrayList<>();
        resultadoBT = new ArrayList<>();
        resultadoAH = new ArrayList<>();
        
        AlgoritmoHeuristico solverAH = new AlgoritmoHeuristico(deLaGuiPersonas, deLaGuiIncompatibilidades, requerimientos);
        resultadoAH.addAll(solverAH.ejecutar());
        reporteAH = new ReporteEjecucion(
            resultadoAH, 
            solverAH.getMejorPuntaje(), 
            solverAH.getTiempoMs(), 
            solverAH.getNodos(), 
            solverAH.getCasosBase(), 
            0
        );

        CountDownLatch latch = new CountDownLatch(2);

        AlgoritmoFuerzaBruta workerFB = new AlgoritmoFuerzaBruta(deLaGuiPersonas, deLaGuiIncompatibilidades, requerimientos) {
            @Override
            protected void done() {
                try {
                    resultadoFB.addAll(get());
                    AlgoritmoFuerzaBruta self = (AlgoritmoFuerzaBruta) this;
                    reporteFB = new ReporteEjecucion(
                        resultadoFB, 
                        self.getMejorPuntaje(), 
                        self.getTiempoMs(), 
                        self.getNodos(), 
                        self.getCasosBase(), 
                        0
                    );
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        };

        AlgoritmoBackTracking workerBT = new AlgoritmoBackTracking(deLaGuiPersonas, deLaGuiIncompatibilidades, requerimientos) {
            @Override
            protected void done() {
                try {
                    resultadoBT.addAll(get());
                    AlgoritmoBackTracking self = (AlgoritmoBackTracking) this;
                    reporteBT = new ReporteEjecucion(
                        resultadoBT, 
                        self.getMejorPuntaje(), 
                        self.getTiempoMs(), 
                        self.getNodos(), 
                        self.getCasosBase(), 
                        self.getPodas()
                    );
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        };

        workerFB.execute();
        workerBT.execute();

        SwingWorker<ReporteEjecucion, Void> orquestador = new SwingWorker<ReporteEjecucion, Void>() {
            @Override
            protected ReporteEjecucion doInBackground() throws Exception {
                latch.await();
                String algoritmo = algoritmoSeleccionado.trim();
                
                if ("BackTracking".equalsIgnoreCase(algoritmo)) {
                    return reporteBT;
                } else if ("FuerzaBruta".equalsIgnoreCase(algoritmo)) {
                    return reporteFB;
                } else if ("Heuristica".equalsIgnoreCase(algoritmo) || "Heurística".equalsIgnoreCase(algoritmo) || "AlgoritmoHeuristico".equalsIgnoreCase(algoritmo)) {
                    return reporteAH;
                } else {
                    return reporteAH;
                }
            }
            
            @Override
            protected void done() {
                try {
                    interfazResultado.accept(get());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    interfazResultado.accept(new ReporteEjecucion(new ArrayList<>(), 0, 0, 0, 0, 0));
                }
            }
        };
        
        orquestador.execute();
    }

    public ReporteEjecucion getReporte(String algoritmo) {
        if (algoritmo == null) return null;
        
        String limpio = algoritmo.trim();

        if ("BackTracking".equalsIgnoreCase(limpio)) {
            return this.reporteBT;
        } else if ("FuerzaFruta".equalsIgnoreCase(limpio) || "FuerzaBruta".equalsIgnoreCase(limpio)) {
            return this.reporteFB;
        } else if ("Heuristica".equalsIgnoreCase(limpio) || "AlgoritmoHeuristico".equalsIgnoreCase(limpio)) {
            return this.reporteAH;
        }

        return null;
    }

	


	
}
