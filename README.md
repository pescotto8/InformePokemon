Descripción



Este proyecto consiste en una aplicación desarrollada en Java cuya función es leer información de Pokémon almacenada en un archivo JSON y generar automáticamente un informe de texto con distintas estadísticas. Durante la ejecución del programa se analizan los datos de todos los Pokémon y se obtiene información relevante como niveles medios, PS medios y clasificación por tipos.



Funcionalidades implementadas

Lectura de los datos desde el archivo pokemon.json.

Cálculo del número total de Pokémon registrados.

Obtención del nivel medio de todos los Pokémon.

Cálculo de los PS medios.

Búsqueda del Pokémon con mayor nivel.

Búsqueda del Pokémon con más PS.

Agrupación de Pokémon por tipo.

Conteo de Pokémon de cada tipo.

Generación automática de un informe en el archivo pokemon.txt.

Estructura de los datos



Cada Pokémon almacenado en el archivo JSON contiene la siguiente información:



id: identificador único.

nombre: nombre del Pokémon.

tipo: tipo principal del Pokémon.

nivel: nivel actual.

ps: puntos de salud.

Ejemplo

JSON

\[

{

"id": 1,

"nombre": "Bulbasaur",

"tipo": "PLANTA",

"nivel": 12,

"ps": 45

}

]

Funcionamiento



El programa comienza cargando todos los Pokémon desde el archivo pokemon.json. Una vez cargados los datos, se realizan diferentes operaciones para calcular estadísticas generales y agrupar los Pokémon según su tipo.



Finalmente, toda la información obtenida se guarda en el archivo pokemon.txt, generando un informe completo y fácil de consultar.



Ejecución del programa

Colocar el archivo pokemon.json en la carpeta del proyecto.

Ejecutar la clase Main.

El programa procesará automáticamente los datos.

Se generará el archivo pokemon.txt con el informe resultante.

Salida generada



El informe incluye:



Número total de Pokémon.

Nivel medio.

PS medios.

Pokémon con mayor nivel.

Pokémon con más PS.

Número de Pokémon por tipo.

Listado completo de Pokémon agrupados por tipo.



De esta forma es posible obtener una visión general de todos los datos almacenados en el archivo JSON de manera sencilla y organizada.



Conclusión



Con este proyecto he practicado el manejo de archivos JSON en Java, el uso de colecciones y Streams para procesar información, así como la generación de archivos de texto con resultados obtenidos a partir del análisis de datos. Además, el proyecto permite organizar la información de los Pokémon de forma clara y generar informes automáticamente.



Autor



Pablo Escotto Pérez

