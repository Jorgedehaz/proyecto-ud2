# Indice 


# Introducción

Para este supuesto hemos elegido utilizar una BBDD creada a partir de la [API Game of Thrones Character Api](https://thronesapi.com/api). la cual contiene 3 trablas distinas. Una con las lista de personajes de la serie Juego de Tronos identificados por distintos campos por los que podremos realizar las consultas en la palicación. Estos son : id, nombre, apellido, título, familia, imagen, imagenURL. A mayores tenemos la tabla Fortress en la que tenemos los campos name, house y ruler, esta última relacionada con la id del personaje que gobierne el Fortress. A su vez tendremos una tabla llamada Families , en la que tenemos los campos familyName y region. En ella el campo familyName hará referencia a family en Characters y a house en Fortress. 

A mayores incluimos una BBDD usuarios que nos servirá para el Login a la aplicación. Esto se desarollará en el apartado [Login](#Extras)

![Esquema](https://github.com/Jorgedehaz/proyecto-ud2/blob/main/imagenes/Esquema.png)
 
Para comprobar el funcionamiento hemos realizamos 5 consultas distintas de selección, actualización y borrado.

![Consulta 1]()

![Consulta 2]()

![Consulta 3]()

![Consulta 4]()

![Consulta 5]()


# Manual  técnico para desarrolladores

Para la creación de nuestro Proyecto en JavaFX comenzamos eligiendo el JDK y la versión de JavaFX en la que vamos a trabajar. En nuestro caso la 17.0.6.

La aplicación consta de 4 ventanas principales (login.fxml, consultas_tabla.fxml , nuevo_personaje.fxml y exportar_documento.fxml) y una emergente, por lo que en el "Main" de nuestra aplicación cargaremos por defecto la ventana login.fxml y le daremos
una resolución de 640x400. Para el fin de nuestra aplicación no necesitariamos ocupar toda la pantalla, pero en caso de que fuese neceario pondriamos el parametro .setResizable(true) y hariamos cambios en los .fxml para que se 
adaptase la interfaz correctamente. 




# Manual de usuario con juego de pruebas

Para comenzar a utilizar la aplicación se debe realizar un log in con uno de los usuarios válidos con su correspondiente contraseña.

![ Imagen loginbien ](res/imagenes/Loginbien.png)

En caso de que alguno de los dos campos sea incorrecto aparecerá un mensaje indicando que el nombre o la contraseña son incorrectos.

![ Imagen loginmal ](res/imagenes/Errorlogin.png)

Una vez terminado el log in se accede a la pantalla de búsquedas donde se podrá crear personajes nuevos con el botón *Nuevo Personaje* o búsquedas de personaje con consultas que van desde 1 hasta los 6 campos distintos: id, nombre, apellido, tíotulo, nombre completo y familia .
En caso de que haya varios personajes que coincidan con la búsqueda realizada estos apareceran en una tabla.

Búsqueda por id:

![ Imagen id ](res/imagenes/ConsultaID.png)

Búsqueda por nombre:

![ Imagen nom ](res/imagenes/Consultanombre.png)

Búsqueda por apellido:

![ Imagen apel ](res/imagenes/Consultaapellido.png)

Busqueda por varios campos:

![ Imagen varios ](res/imagenes/Bsuqedavarioscampos.png)

En caso de que ningún dato coincida con la búsqueda realizada, la tabla aparecerá vacía:

![ Imagen errbus ](res/imagenes/Bsuqedaerronea.png)

Una vez realizada la búsqueda se podrán seleccionar los personajes de la tabla para cargar todos sus datos en los campos y modificarlos si queremos *Modificar Personaje*, o *Borrar Personaje* en el caso de que queramos eliminarlo de la base de datos:

![ Imagen ned ](res/imagenes/NedFoto.png)

![ Imagen rob ](res/imagenes/RobFoto.png)

Cuando se haya terminado la búsqueda se puede pulsar el botón *Exportar Tabla* para ir a la siguiente pantalla, donde se podrá elegir el nombre del archivo .json que queramos exportar con los resultados de la consulta.

En caso de que no se haya proporcionado un nombre de documento aparecerá un mensaje en pantalla indicándolo y no se realizará la exportación.

![ Imagen errexport ](res/imagenes/Errorexportar.png)

Cuando la exportación se realice correctamente aparecerá esta ventana emergente confirmando la exportación. Los documentos se guardarán en la carpeta *docs*.

![ Imagen exportcorrecto ](res/imagenes/ExportarCorrecto.png)

Por último si se desea seguir realizando búsquedas se pulsará el botón *Volver* para ir a la pantalla de búsquedas.

# Reparto de tareas

Se han repartido las tareas en función del tiempo que se le ha dedicado a cada una de forma que este fuera equitativo:

Jorge ha implementado las funcionalidades de búsqueda por los distintos campos y mostrar los resultados mediante una tabla, la exportación de datos a XML, la verificación de usuarios y el login, botones de cambio de pantalla y mensajes emergentes y ha realizado el modelo de las pantallas de login y búsquedas.

Miguel ha implementado las funcionalidades de exportación a binario, texto y json, la interacción con la tabla para recibir la imagen de cada personaje, botones de cambio de pantalla, ventanas y mensajes emergentes. Ha realizado el modelo de la pantalla de exportación de documentos y ha añadido estilos a las distintas pantallas.

# Extras realizados

**Login**

A mayores del proceso y el código explicado en el *Manual técnico para desarrolladores* , hemos diseñado una pantalla de Login en la que introduciremos usuario y contraseña almacenados e un archivo .properties.

**Login ocultando pass**

![Loginpass](res/imagenes/Loginbien.png)

En caso de no introducir bien algún parámetro nos mostrará un mensaje de error por pantalla. 

**Login error**

![ErrorLogin](res/imagenes/Errorlogin.png)

El código usado para implementar esta función ha sido el siguiente. 

**Método Login**

![MetodoLogin](res/imagenes/Login.png)

**Método de Verificación**

![Verificacion](res/imagenes/Verificacion.png)

**Uso Offline**

Como ya inficamos también en el *Manual técnico para desarrolladores*, nuestra aplicación realiza las consultas contra un archivo JSON dentro de la propia aplicación , por lo que no requiere de conexión a internet para realizar 
las consultas. (Leer creación del método "Initialize")
# Extras 
# Propuestas de mejora

Entre las mejoras que se podrían implementar estan:

- Añadir un mensaje que indique que no se han encontrado coincidencias al realizar la búsqueda.
- Actualmente los datos se extraen de un json obtenido de la API y que se ha guardado en la carpeta *resources*, la mejora sería implementar que la búsqueda se realizara directamente contra la API.
- Encriptar los datos del fichero properties para el log in.

# Conclusiones

En un principio el proyecto fue avanzando lentamente debido a la falta de conocimiento de JavaFX de los integrantes del grupo, sin embargo al entender como funciona se comezó a trabajar de manera más agil permitiendo alcanzar los requerimientos del proyecto dentro del plazo de entrega.

# Dedicación temporal

A este proyecto cada uno de los integrantes le ha dedicado un total de 20 horas divididos entre los distintos días de la semana de la siguiente manera.

![ Imagen horas trabajo ](res/imagenes/Horas_trabajo.png)
