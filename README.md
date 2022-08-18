# CRUD-ProductosLogitiqal
CRUD con conexión a base de datos y vistas JSP con paginación

Aplicación que  permita una la lista de productos mediante una tabla.

La aplicación cumple con los siguientes requisitos:
● Tiene un paginador.
● Se puede elegir la cantidad de registros por página.
● Se puede filtrar el contenido de la tabla por el nombre del producto (Buscar).
● Tiene un diseño agradable.
● Se puede almacenar la siguiente información de los productos: Código único,
nombre único, precio y stock.
● Se puede agregar, quitar y modificar los productos.

Se utiliza JPS, se implementa un paginador y Bootstrap. Contiente un CRUD para la entidad Producto, extendiendo
CrudRepository.

Tiene un input que permita escribir el nombre del producto. De esta forma, se ejecuta una consulta que busque todos los productos que contengan el texto
ingresado en su nombre para, posteriormente, volver a poblar la tabla con los resultados.

