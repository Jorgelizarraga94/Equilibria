# Equilibria - Sistema de Conformación de Equipos Óptimos

## Programación III — UNGS (TP3)
---

## Desarrolladores
Este proyecto fue desarrollado por:
* **Lucero Juan**
* **Dino Martin**
* **Lizarraga Jorge**

* **Cátedra:** Programación 3 (Proyecto Universitario)

## 📝 Introducción

[cite_start]**Equilibria** es una aplicación desarrollada en Java diseñada para resolver el problema de conformación de equipos de trabajo óptimos dentro de una *Software Factory*[cite: 1]. [cite_start]El sistema administra un conjunto de personas disponibles, caracterizadas por su nombre, rol, calificación de desempeño e incompatibilidades mutuas[cite: 2, 3]. [cite_start]El objetivo principal es maximizar la suma total de las calificaciones del equipo resultante, cumpliendo estrictamente con los requerimientos de roles solicitados y evitando incorporar personas incompatibles entre sí[cite: 3, 7].

[cite_start]Para abordar el problema, la aplicación implementa y compara tres estrategias algorítmicas diferentes: **Fuerza Bruta**, **Backtracking** y **Heurística Golosa (Greedy)**[cite: 8, 63].

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java
* [cite_start]**Interfaz Gráfica:** Swing (Arquitectura modular basada en paneles independientes) [cite: 13, 15]
* [cite_start]**Concurrencia/Hilos:** `SwingWorker` y `CountDownLatch` para la ejecución en segundo plano de algoritmos pesados [cite: 42, 43, 56]
* [cite_start]**Pruebas Unitarias:** JUnit [cite: 26]

---

## 📐 Estructura del Proyecto

[cite_start]El código fuente sigue el principio de separación de responsabilidades y está organizado en los siguientes paquetes[cite: 13]:

* [cite_start]`principal`: Contiene la clase encargada de inicializar la aplicación y desplegar la interfaz de usuario[cite: 25].
* [cite_start]`entidades`: Define los objetos del modelo de negocio (`Persona`, `Requerimiento`, `Incompatibilidad`)[cite: 17].
* [cite_start]`logica`: Aloja las reglas de negocio, la gestión central de datos (`LogicaEquilibria`) y los algoritmos de resolución[cite: 23, 39].
* [cite_start]`gui`: Aloja el contenedor principal (`VentanaPrincipal`) que orquesta y coordina las secciones[cite: 14, 18].
    * [cite_start]`gui.PanelPersona`: Controla los paneles de gestión, alta y baja de integrantes[cite: 20].
    * [cite_start]`gui.PanelCalculo`: Paneles destinados a disparar los algoritmos y visualizar métricas[cite: 19].
    * [cite_start]`gui.VentanasEmergentes`: Modales auxiliares para la carga limpia de datos[cite: 22].
* [cite_start]`test`: Contiene el conjunto de pruebas unitarias implementadas[cite: 26].

---

## 🧬 Componentes Clave del Modelo

### Persona
Representa a cada empleado elegible. [cite_start]Posee un identificador único autoincremental, nombre, rol asignado, fotografía y una calificación que mide su rendimiento computacional para la maximización del beneficio[cite: 2, 28, 31].

### Requerimiento
[cite_start]Establece la cantidad mínima de personal necesaria para un rol determinado en el equipo final[cite: 32].

### Incompatibilidad
[cite_start]Define de manera binaria una restricción estricta: dos personas asociadas a una incompatibilidad bajo ningún concepto pueden formar parte del mismo equipo simultáneamente[cite: 3, 35].

---

## 🧠 Algoritmos de Búsqueda Implementados

[cite_start]El sistema destaca por permitir evaluar y comparar el rendimiento de las siguientes soluciones[cite: 9]:

### 1. Fuerza Bruta
[cite_start]Explora el espacio completo de soluciones generando todas las combinaciones posibles de personas ($2^N$)[cite: 52]. [cite_start]Para cada combinación, evalúa si cumple las restricciones de requerimientos e incompatibilidades, guardando la de mayor puntaje[cite: 53, 54].
* [cite_start]**Complejidad:** Exponencial[cite: 10].
* [cite_start]**Limitación:** En pruebas con más de 25 personas el cálculo puede superar los 10 segundos, por lo que se ejecuta mediante un hilo secundario para evitar congelar la interfaz gráfica[cite: 11, 42].

### 2. Backtracking
[cite_start]Optimiza la búsqueda exhaustiva mediante la técnica de **podas tempranas**[cite: 61]. [cite_start]Si durante la construcción de una rama el algoritmo detecta que se superaron los requerimientos del rol o que se ha incluido a personas incompatibles, descarta inmediatamente esa línea de ejecución[cite: 61]. [cite_start]Esto reduce sustancialmente el número de nodos explorados comparado con Fuerza Bruta[cite: 62].

### 3. Heurística Golosa (Greedy)
[cite_start]Diseñado para dar respuestas inmediatas en escenarios de alta densidad de datos[cite: 66]. [cite_start]Ordena de mayor a menor a las personas basándose en su calificación e intenta incorporarlas secuencialmente respetando los requerimientos de vacantes disponibles y restricciones de incompatibilidad[cite: 63, 65].
* [cite_start]**Ventaja:** Tiempos de ejecución extremadamente bajos y eficientes[cite: 66].
* [cite_start]**Desventaja:** Al ser una heurística, no garantiza encontrar la solución matemáticamente óptima, aunque entrega aproximaciones muy cercanas[cite: 67].

---

## 📊 Métricas de Comparación

[cite_start]Al resolver una instancia de equipo, la aplicación genera un objeto `ReporteEjecucion` que expone en pantalla las siguientes métricas estadísticas por cada algoritmo[cite: 9, 45]:
* [cite_start]Tiempo total de ejecución (en milisegundos)[cite: 9, 45].
* [cite_start]Cantidad de nodos totales recorridos[cite: 9, 45].
* [cite_start]Cantidad de casos base evaluados[cite: 9, 45].
* [cite_start]Cantidad de podas realizadas (aplicable a Backtracking)[cite: 9, 45, 61].

---

## 🛠️ Problemas Encontrados y Soluciones

* [cite_start]**Rutas de Imágenes en Tablas de Swing:** Originalmente, las imágenes de los integrantes se renderizaban en la interfaz como una cadena de texto plana con la ruta del archivo[cite: 70]. [cite_start]Se solucionó redefiniendo el tipo de dato de la columna e implementando la clase `ImageIcon` con un reescalado dinámico para pintar la imagen correctamente dentro de la celda[cite: 71, 72].
* [cite_start]**Representación Operativa de Requerimientos:** Se optimizó la comunicación interna traduciendo los requerimientos de la interfaz gráfica a mapas dinámicos indexados por roles normalizados (`roles.put(rol.toLowerCase().trim(), cantidad)`) para agilizar las operaciones lógicas de los algoritmos[cite: 59, 73].
