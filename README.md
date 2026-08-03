## Equilibria - Sistema de Conformación de Equipos Óptimos

## Desarrolladores
Este proyecto fue desarrollado por:
* **Lucero Juan**
* **Dino Martin**
* **Lizarraga Jorge**


## 📝 Introducción

**Equilibria** es una aplicación de escritorio desarrollada en Java, diseñada para resolver el problema de conformación de equipos de trabajo óptimos dentro de una *Software Factory*. El sistema administra un conjunto de personas disponibles, caracterizadas por su nombre, rol, calificación de desempeño e incompatibilidades mutuas. El objetivo principal es maximizar la suma total de las calificaciones del equipo resultante, cumpliendo estrictamente con los requerimientos de roles y evitando incorporar personas incompatibles entre sí.

Para abordar el problema, la aplicación implementa y compara tres estrategias algorítmicas diferentes: **Fuerza Bruta**, **Backtracking** y **Heurística Golosa (Greedy)**.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java
* **Interfaz Gráfica:** Swing (Arquitectura modular basada en paneles independientes)
* **Concurrencia/Hilos:** `SwingWorker` y `CountDownLatch` para la ejecución en segundo plano de algoritmos pesados
* **Pruebas Unitarias:** JUnit

---

## 📐 Estructura del Proyecto

El código fuente sigue el principio de separación de responsabilidades y está organizado en los siguientes paquetes:

* `principal`: Contiene la clase encargada de inicializar la aplicación y desplegar la interfaz de usuario.
* `entidades`: Define los objetos del modelo de negocio (`Persona`, `Requerimiento`, `Incompatibilidad`).
* `logica`: Aloja las reglas de negocio, la gestión central de datos (`LogicaEquilibria`) y los algoritmos de resolución.
* `gui`: Aloja el contenedor principal (`VentanaPrincipal`) que orquesta y coordina las secciones.
    * `gui.PanelPersona`: Controla los paneles de gestión, alta y baja de integrantes.
    * `gui.PanelCalculo`: Paneles destinados a disparar los algoritmos y visualizar métricas.
    * `gui.VentanasEmergentes`: Modales auxiliares para la carga limpia de datos.
* `test`: Contiene el conjunto de pruebas unitarias implementadas.

---

## 🧬 Componentes Clave del Modelo

### Persona
Representa a cada empleado elegible. Posee un identificador único autoincremental, nombre, rol asignado, fotografía y una calificación que mide su rendimiento computacional para la maximización del beneficio.

### Requerimiento
Establece la cantidad mínima de personal necesaria para un rol determinado en el equipo final.

### Incompatibilidad
Define de manera binaria una restricción estricta: dos personas asociadas a una incompatibilidad bajo ningún concepto pueden formar parte del mismo equipo simultáneamente.

---

## 🧠 Algoritmos de Búsqueda Implementados

El sistema destaca por permitir evaluar y comparar el rendimiento de las siguientes soluciones[:

### 1. Fuerza Bruta
Explora el espacio completo de soluciones generando todas las combinaciones posibles de personas. Para cada combinación, evalúa si cumple las restricciones de requerimientos e incompatibilidades, guardando la de mayor puntaje.
* **Complejidad:** Exponencial.
* **Limitación:** En pruebas con más de 25 personas el cálculo puede superar los 10 segundos, por lo que se ejecuta mediante un hilo secundario para evitar congelar la interfaz gráfica.

### 2. Backtracking
Optimiza la búsqueda exhaustiva mediante la técnica de **podas tempranas**. Si durante la construcción de una rama el algoritmo detecta que se superaron los requerimientos del rol o que se ha incluido a personas incompatibles, descarta inmediatamente esa línea de ejecución. Esto reduce sustancialmente el número de nodos explorados comparado con Fuerza Bruta.

### 3. Heurística Golosa (Greedy)
Diseñado para dar respuestas inmediatas en escenarios de alta densidad de datos. Ordena de mayor a menor a las personas basándose en su calificación e intenta incorporarlas secuencialmente respetando los requerimientos de vacantes disponibles y restricciones de incompatibilidad.
* **Ventaja:** Tiempos de ejecución extremadamente bajos y eficientes.
* **Desventaja:** Al ser una heurística, no garantiza encontrar la solución matemáticamente óptima, aunque entrega aproximaciones muy cercanas.

---

## 📊 Métricas de Comparación

Al resolver una instancia de equipo, la aplicación genera un objeto `ReporteEjecucion` que expone en pantalla las siguientes métricas estadísticas por cada algoritmo:
* Tiempo total de ejecución (en milisegundos).
* Cantidad de nodos totales recorridos.
* Cantidad de casos base evaluados.
* Cantidad de podas realizadas (aplicable a Backtracking).

---

<img width="1379" height="789" alt="Equilibria imagen" src="https://github.com/user-attachments/assets/e5e15803-823d-4908-87a5-d7fe653f807b" />

