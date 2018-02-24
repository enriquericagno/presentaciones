
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>

<head>
<title>Lista Editoriales</title>

<link type="text/css" rel="stylesheet"
	href="resources/css/style.css" />
</head>

<input name="listalibros" type="hidden" value="${lista}"/>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Listado Editoriales</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
		
<!-- 			<input type="button" value="Ver Libros" -->
<!-- 				onclick="windows.location.href='showFormForAdd'; return false;" -->
<!-- 				class="add-button"/> -->

			<!--  add our html table here -->

			<table>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Direccion</th>
					<th>Listado</th>
				</tr>

				<!-- loop over and print our customers -->

				
				<c:forEach var="editorial" items="${lista}">
					<c:url var="updateLink" value="/librosxEditorial">
						<c:param name="editorialId" value="${editorial.idEditorial}"></c:param>
					</c:url>
					<br />
					<tr>
						<td>${editorial.idEditorial}</td>
						<td>${editorial.nombre}</td>
						<td>${editorial.direccion}</td>	
						<td> <a href="${updateLink}">Ver Libros</a></td>
						
					
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>
