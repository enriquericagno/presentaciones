<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>

<head>
<title>Lista Libros x Editorial</title>

<link type="text/css" rel="stylesheet" href="resources/css/style.css" />
</head>



<body>

	<div id="wrapper">
		<div id="header">
			<h2>Listado Libros</h2>
		</div>
	</div>
			<input type="button" value="Volver"
				onclick="history.back()"
				class="add-button"/>
	<div id="container">

		<div id="content">

			<table>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Nombre Completo</th>
					<th>Descripcion</th>
					<th>Autor</th>
					<th>Fecha de Edicion</th>
				</tr>

				<c:forEach var="libro" items="${listalibros}">

					<br />
					<tr>
						<td>${libro.idLibro}</td>
						<td>${libro.nombre}</td>
						<td>${libro.nombrelargo}</td>
						<td>${libro.descripcion}</td>
						<td>${libro.autor}</td>
						<td>${libro.fechaedicion}</td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>

<!-- 	<button type="button" name="back" onclick="history.back()">back</button> -->


</body>

</html>