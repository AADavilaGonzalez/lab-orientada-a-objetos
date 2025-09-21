# Laboratorio de Programacion Orientada a Objetos
Aldo Davila 1994122 | GPO: 031

## Estructura
El repositorio consite en proyectos independientes gestionados mediante
la herramienta Gradle. Se didive en las 11 practicas requeridas, el PIA
y un proyecto utils donde se alacena el codigo en comun que se reutilza
en los demas proyectos.

## Compilacion y Ejecucion
Como se utilizo Gradle para gestionar los paquetes, es conveniente
utilizar esta herramienta para compilar y ejecutar los proyectos del
laboratorio.

Utilice uno de los siguientes scripts para ejectuar los programas en
base a su sistema operativo:
* Linux o MacOS -> gradlew
* Windows -> gradlew.bat

Para crear una distribucion ejectuable de un proyeto utilice:
`./gradlew :'proyecto':installDist`
ej. `./gradle :practica1:installDist`

Para ejecutar la distribucion corra el siguente script:
`./<proyecto>/build/install/<proyecto>/bin/<proyecto>`
ej. `./practica1/build/install/practica1/bin/practica1`

Tambien, si se encuentra en un entorno unix con acceso a BASH puede usar
el script `./run <nombre_del_proyecto>` para ejecutar los proyectos
de forma mas conveniente
