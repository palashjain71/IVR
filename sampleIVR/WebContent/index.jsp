<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IVR</title>
</head>
<body>

	<center>
		<h1>IVR Management</h1>
		<h2>
			<a href="/new">List All IVR</a>
		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Locales with Entry Message</h2>
			</caption>
			<tr>
				<th>Locales</th>
				<th>Entry Message</th>
			</tr>
			<c:forEach var="IVR_ModalClass" items="${listBook}">
				<tr>
					<td><c:out value="${IVR_ModalClass.locales}" /></td>
					<td><c:out value="${IVR_ModalClass.description}" /></td>
					<td><a
						href="/edit?id=<c:out value='${IVR_ModalClass.locales}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/delete?id=<c:out value='${IVR_ModalClass.locales}' />">Delete</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/delete?id=<c:out value='${IVR_ModalClass.locales}' />">Add_New_Locales</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>



</body>
</html>