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
			<a href="/list">List All IVR</a>
		</h2>
	</center>
	<div align="center">
		<div align="center">
			<c:if test="${IVR_ModalClass != null}">
				<form action="update" method="post">
			</c:if>
			<c:if test="${IVR_ModalClass == null}">
				<form action="insert" method="post">
			</c:if>
			<table border="1" cellpadding="5">
				<caption>
					<h2>
						<c:if test="${IVR_ModalClass != null}">
                        Edit Book
                    </c:if>
						<c:if test="${IVR_ModalClass == null}">
                        Add New Book
                    </c:if>
					</h2>
				</caption>

				<tr>
					<th>Locales:</th>
					<td><input type="text" name="title" size="45"
						value="<c:out value='${IVR_ModalClass.locales}' />" />
					</td>
				</tr>
				<tr>
					<th>EntryMsg:</th>
					<td><input type="text" name="author" size="45"
						value="<c:out value='${IVR_ModalClass.description}' />" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save" /></td>
				</tr>
			</table>
			</form>
		</div>
	</div>

</body>
</html>