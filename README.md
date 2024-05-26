# Hoja de Trabajo 10
David Dominguez 23712  

Universidad del Valle de Guatemala  
Facultad de Ingeniería  
Departamento de Ciencias de la Computación  
CC2003 – Algoritmos y Estructura de Datos  

## Descripción del Proyecto
Hoja de trabajo sobre grafos y algoritmo de Floyd para ruta más corta.  

El proyecto Guategrafo es una aplicación Java que implementa el algoritmo de Floyd para encontrar las rutas más cortas en un grafo ponderado que representa las conexiones entre ciudades en Guatemala. Permite cargar datos de un archivo que contiene la información del grafo, calcular las rutas más cortas, encontrar el centro del grafo y realizar modificaciones en las conexiones entre ciudades.

## Requerimientos de Software

- Java Development Kit (JDK) 8 o superior
- Entorno de desarrollo Java compatible (por ejemplo, IntelliJ IDEA, Eclipse)

Para el extra en python
- Python 3.x  
- NetworkX  

## Modulos, librerías y todos los import

El proyecto utiliza las siguientes clases y librerías:

- `Main.java`: Clase principal que maneja la ejecución del programa.
- `Graph.java`: Clase que representa el grafo y proporciona métodos para agregar y eliminar vértices y aristas.
- `FloydAlgorithm.java`: Clase que implementa el algoritmo de Floyd para encontrar las rutas más cortas en el grafo.
- `FloydResult.java`: Clase que representa el resultado del algoritmo de Floyd, incluyendo las distancias más cortas y los predecesores.

El proyecto utiliza las siguientes importaciones:

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.*;  


## Instalación

1. Clona este repositorio en tu máquina local.
   
3. Abre el proyecto en tu entorno de desarrollo Java preferido.

## Uso

1. Ejecuta la clase `Main.java`.
2. Selecciona una de las opciones del menú para cargar el grafo desde un archivo, calcular rutas más cortas, encontrar el centro del grafo o realizar modificaciones en las conexiones entre ciudades.

## Funcionalidades

- Cargar un grafo desde un archivo de texto.
- Calcular las rutas más cortas entre dos ciudades.
- Encontrar el centro del grafo.
- Modificar las conexiones entre ciudades, agregando nuevas conexiones o eliminando conexiones existentes.

## Diagrama de Clases UML
![image](https://github.com/DavidDominguez-11/HDT10_Floyd/assets/84152698/c8c291b1-c198-4a98-b7e6-1502c53b4db1)


## Estructura del Proyecto
  
![image](https://github.com/DavidDominguez-11/HDT10_Floyd/assets/84152698/784ed61a-55f1-467b-b72f-99ceea5d489f)
  
En esta estructura:  

Los archivos principales Java están ubicados en demo/src/main/java/com/example/.  
Las pruebas JUnit están ubicadas en demo/src/test/java/com/example/.  
El archivo adicional en Python está ubicado en demo/src/main/python/.  
El archivo de datos del grafo (guategrafo.txt) está ubicado en la raíz del proyecto (demo/).  
