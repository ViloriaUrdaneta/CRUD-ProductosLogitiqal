<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.edutecno.jpacrud.modelo.Producto"%>
<%@page import="com.edutecno.jpacrud.vo.ProductoVO"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="charset=UTF-8">
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.3.0/css/bootstrap.min.css" />
<title>Empresa Logistiqal - Buscar</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark ">
		<div class="navbar">
			<h1 class="navbar-brand">Resultados Busqueda</h1>
		</div>
	</nav>
	<div class="p-3">
		<table border="1" class="table table-hover">
			<thead class="thead-info">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Nombre</th>
					<th scope="col">Precio</th>
					<th scope="col">Stock</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productos}" var="p">
					<tr>
						<td><c:out value="${p.getId_producto()}" /></td>
						<td><c:out value="${p.getNombre()}" /></td>
						<td><c:out value="${p.getPrecio()}" /></td>
						<td><c:out value="${p.getStock()}" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="home" class="btn btn-info">Volver</a>
	</div>

</body>
</html>