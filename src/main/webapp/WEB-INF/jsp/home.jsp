<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.edutecno.jpacrud.modelo.Producto"%>
<%@page import="com.edutecno.jpacrud.vo.ProductoVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.3.0/css/bootstrap.min.css" />
<title>JSP CRUD Logitiqal</title>
</head>
<body>

	<nav class="navbar navbar-dark bg-dark ">
		<!-- Navbar content -->
		<h1 class="navbar-brand">Productos Logitiqal</h1>

		<!-- Botón agregar producto -->
		<a class="nav-item text-end" href="agregarForm">Agregar producto</a> <br
			 />

		<!-- Buscar por nombre -->

		<div>
			<form action="buscar" method="post">
				<table>
					<tr>
						<td><input style="max-width: 300px;" class="form-control"
							type="text" name="nombre" placeholder="Buscar por nombre">
						</td>
						<td><input type="submit" class="btn m-2 btn-info"
							value="Buscar Producto" /></td>
					</tr>
				</table>
			</form>
		</div>

	</nav>
	
	<div class= "container-fluid">
	
	<br>
	<h3 class="text-start">Estado de la operación: ${mensaje}</h3>
	<br>
	
	<!-- Tabla -->
	<table border="1" class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Stock</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${VO.productos}" var="p">
				<tr>
					<td>${p.getId_producto()}</td>
					<td>${p.getNombre()}</td>
					<td>${p.getPrecio()}</td>
					<td>${p.getStock()}</td>
					<td><a href="editarForm?id_producto=${p.getId_producto()}"
						class="btn btn-primary btn-sm">Editar</a> <a
						href="eliminar?id_producto=${p.getId_producto()}"
						class="btn btn-primary btn-sm">Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- Paginación -->
	<div>
		<ul class="pagination pagination-lg justify-content-center">
			<c:forEach items="${paginas}" var="pagina">
				<li class="page-item ${paginaActual == pagina ? 'disabled' : ''}">
					<a class="page-link" href="home?rowPerPag=${rowPerPag}&p=${pagina}"
					tabindex="-1">${pagina}</a>
				</li>
			</c:forEach>
		</ul>
		<form action="/" method="get">
			<table>
				<tr>
					<td><input style="max-width: 200px;" class="form-control"
						type="number" name="rowPerPag" placeholder="${rowPerPag}">
					</td>
					<td><input type="submit" class="btn m-2 btn-info"
						value="Aplicar" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	</div>
</body>
</html>
