<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Compte Utilisateur</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/public/style.css"/>" />
<c:import url="/public/menu.jsp" />
</head>
<body>
	<div id="corps">
		<h2>
			Bienvenue Mr
			<c:out value="${ utilisateur.nom }"></c:out>
		</h2>

		<form method="post" action="<c:url value="/Deconnexion"/>">
			<fieldset>
				<legend>Mon espace utilisateur</legend>
				<ul>
					<li>Votre email est: <c:out value="${ utilisateur.email }"></c:out></li>
					<li>Vous êtes connecté en tant que: <c:out
							value="${ utilisateur.poste }"></c:out></li>
				</ul>
			</fieldset>
			<input type="submit" value="Deconnexion" />
		</form>
	</div>
	<div>
		<h2>Voici les utilisateurs</h2>
		<table>
			<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Rôle</th>
			</tr>
			
			<c:forEach items="${ListeUtilisateur}" var="current">
				<tr>
					<td><c:out value="${current.getNom()}" />
					<td><c:out value="${current.getEmail()}" />
					<td><c:out value="${current.getPoste()}" />
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>