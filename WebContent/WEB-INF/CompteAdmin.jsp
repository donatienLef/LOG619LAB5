<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Compte Utilisateur</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css"/>" />
<c:import url="/inc/menu.jsp" />
<script>
	alert("bravo");
	if (
<%request.getSession().getAttribute("Message");%>
	!= null) {
		alert("bravo");
	} else {
		alert("echec");
	}
</script>
</head>
<body>
	<div id="corps">
		<h2>
			Bienvenue Mr
			<c:out value="${ utilisateur.nom }"></c:out>
		</h2>
		<c:if test="${Message != null}">
		<h2>
		Message Important: ${Message}
		</h2>
		</c:if>
		

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

</body>
</html>