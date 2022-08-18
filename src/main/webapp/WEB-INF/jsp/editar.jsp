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
<title>JSP CRUD</title>
</head>
<body>

	<nav class="navbar navbar-dark bg-dark ">
		<!-- Navbar content -->
		<h1 class="navbar-brand">EDITAR PRODUCTO</h1>
	</nav>
	
	<div class= "container-fluid">
	
	<br>
	<h3>${mensaje}</h3>
	<br>

	<div class="card" style="width: 18rem;">
		<div class="card-body">

			<form action="editar" method="post">
				<table>
					<tr>
						<td><input type="hidden" class="form-control" type="text"
							name="id_producto" placeholder="id_producto"
							value="${VO.getId_producto()}" /></td>
					</tr>
					<div class="mb-3">
						<label class="form-label">Nombre</label>
						<input class="form-control" type="text" name="nombre"
							placeholder="Nombre" value="${VO.getNombre()}" />
					</div>
					<div class="mb-3">
						<label class="form-label">Precio</label>
						<br>
						<input class="form-control" type="number"
							placeholder="Precio" name="Precio" value="${VO.getPrecio()}" />
					</div>
					<div class="mb-3">
						<label class="form-label">Stock</label>
						<br>
						<input class="form-control" type="number"
							placeholder="Stock" name="Stock" maxlength="99999999"
							value="${VO.getStock()}" />
					</div>
					<tr>
						<td colspan="2"><input type="submit" value="Cambiar"  class="btn m-2 btn-info"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	</div>


</body>
</html>
