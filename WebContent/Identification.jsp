<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Identification</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/public/style.css"/>" />
<c:import url="/public/menu.jsp" />
</head>
<body>
	<div id="formulaire">
		<form method="post" action="<c:url value="/Identification"/>">
			<fieldset>
				<legend>Identification</legend>
				<label for="email">Adresse Email<span class="requis">*</span></label>
				<input type="text" id="email" name="email" value="" size="30"
					maxlength="30" /> <span class="erreur">${form.erreurs['email']}</span><br />
				<label for="motdepasse">Mot de passe</label> <input type="password"
					id="password" name="motdepasse" value="" size="30" maxlength="250" />
				<span class="erreur">${form.erreurs['motdepasse']}</span><br /> <span
					class="erreur">${form.erreurs['compte']}</span><br/>
				<span class="erreur">Tentative n°${Tentative }</span>
			</fieldset>
			<c:choose>
				<c:when test="${TentativeError!=null}">
		
				</c:when>
				<c:otherwise>
					<input type="submit" value="Valider" /> <input type="reset"
				value="Remettre à zéro" /> <br />
				</c:otherwise>
			</c:choose>
			
		</form>
					<p>Tentative error: ${TentativeError}</p>
		
	</div>

</body>
</html>