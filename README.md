# Servicios de proyecto prueba meli (Xmen-magneto) Desafio: Nivel 3#

Servicios REST para el proyecto xmen-magneto

### Requerimientos ###

* OpenJDK 11.x LTS
* Apacha Maven 3.3.x o superior

### Introducción ###

Los servicios son desarrollados utilizando Spring Boot + Maven. Las pruebas unitarias fueron realizadas utilizando JUnit5 y mockito. 

### Instrucciones de compilación ###

* **mvn clean install:** esta tarea ejecuta todas las tareas necesarias de compilación, ejecución de pruebas unitarias y verificación de cobertura.

También se pueden ejecutar las siguientes tareas de manera separada:

* **mvn clean compile:** para verificar que el código compila correctamente y pasa las validaciones de código estático.
* **mvn clean compile test:** para ejecutar las pruebas unitarias y verificar que todas pasan satisfactoriamente.
* **mvn clean compile verify:** para verificar que las pruebas unitarias cumplen la cobertura correspondiente.

### Pruebas unitarias ###

Las pruebas unitarias se encuentran en /src/test y fueron realizadas con JUnit 5 y Mockit. Es **importante** tener en cuenta que la inyección de dependencias de Spring Boot se realiza a través de constructores y no a través de la anotación @Autowired para que se puedan inyectar mocks en las pruebas unitarias, si esto no se realiza, las objectos de tipo Mock no serían inyectadas y se generaran errores al ejecutar las pruebas unitarias.

### Verificación de código estático ###

Al ejecutar la tarea maven **mvn clean compile** en la carpeta /target se puede visualizar el archivo checkstyle-result.xml para ver las clases y los errores de código estatico para realizar los correspondientes ajustes. 

### Verificación de cobertura ###

Al ejecutar la tarea maven **mvn clean compile test verify** en la carpeta /target/site/jacoco se puede visualizar el archivo index.html para revisar los caminos de los algoritmos que se estan validando correctamente en las pruebas unitarias.

### Generación de "Fat Jar"###

Para generar el archivo Jar con todo lo necesario para ejecutar el proyecto sin requerir de un servidor de aplicaciones, ejecutar la siguiente tarea maven:

**mvn clean compile package**

El archivo Jar generado quedara ubicado en la carpeta /target.

### Deploy en google cloud###

El API se encuentra desplegado en google cloud.

La url de acceso para el primer endpoint es POST https://spring-xmen.rj.r.appspot.com/cancaba/xmen/api/v1/mutant/
La url de acceso para el segundo endpoint es GET https://spring-xmen.rj.r.appspot.com/cancaba/xmen/api/v1/mutant/stats

La base de datos utilizada es postgres (version 12), también alojada en google cloud.
 