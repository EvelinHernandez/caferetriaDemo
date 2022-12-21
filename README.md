# caferetriaDemo prueba tecnica - Descripcion

Software para una cafetería, que permite almacenar y gestionar el inventario de los productos.
Este software permite crear, editar, eliminar y listar todos los productos registrados en el sistema.

Adicionalmente, se cuenta con un módulo que permite realizar la venta de un producto. El software actualiza el campo de stock del producto vendido y 
registra en una tabla la venta realizada.

Tambien cuenta con 2 consultas directas en base de datos:
• Una consulta que permite conocer cuál es el producto que más stock tiene.
• Una consulta que permite conocer cuál es el producto más vendido.


## Libreria y convenciones
* El backend de este proyecto esta creado con Java 1.8, Spring Boot y el frontend con Thymeleaf 
* La base de datos es Mysql y se debe crear llamada cafeteria ya que una vez se corra el proyecto este automaticamente genera las tablas necesarias
* Puerto del proyecto 8082


## Datos para correr el proyecto
* Tener el jdk 1.8 instalado y mysql 
* Instalar el gestor de dependencias maven https://maven.apache.org/download.cgi
* Instalar el entorno para ejecutar el proyecto llamado Spring Tools for eclipse link: https://spring.io/tools
* Antes de ejecutar el proyecto es necesario crear una bd en mysql llamada 'cafeteria'
* Para ejecutar se debe abrir la carpeta donde se haya clonado el proyecto en Spring Tools y 
  en la clase CaferetriaDemoApplication.java clic derecho y clic en RunAs > spring boot app
* Para validar que funciona correctamente deberia ver la pagina inicial en http://localhost:8082/index

## Opcional
Descargar Cafeteria_postman_CargarCSVProductos.json e importarlo en postman para ejecutar el endpoint 'localhost:8082/cargarCSV' 
este endpoint carga un archivo CSV llamado 'Productos.csv' ubicado en la raiz del proyecto el cual se puede editar en excel para 
gregar varios productos a la vez en la bd.
