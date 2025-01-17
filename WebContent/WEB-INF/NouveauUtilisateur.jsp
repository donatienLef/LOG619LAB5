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
		<form method="post" action="<c:url value="/Home/NouveauUtilisateur"/>">
			<fieldset>
				<legend>Inscription</legend>
				<p>Vous pouvez vous inscrire via ce formulaire.</p>

				<label for="email">Adresse email <span class="requis">*</span></label>
				<input type="email" id="email" name="email"
					value="<c:out value=""/>" size="20"
					maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>
				<br /> <label for="motdepasse">Mot de passe <span
					class="requis">*</span></label> <input type="password" id="motdepasse"
					name="motdepasse" value="" size="20" maxlength="20" /> <span
					class="erreur">${form.erreurs['motdepasse']}</span> <br /> <label
					for="confirmation">Confirmation du mot de passe <span
					class="requis">*</span></label> <input type="password" id="confirmation"
					name="confirmation" value="" size="20" maxlength="20" /> <span
					class="erreur">${form.erreurs['confirmation']}</span> <br /> <label
					for="nom">Nom d'utilisateur</label> <input type="text" id="nom"
					name="nom" value="<c:out value=""/>" size="20"
					maxlength="20" /> <span class="erreur">${form.erreurs['nom']}</span>
				<br /> 
				<label
					for="poste">Rôle</label>
				<SELECT name="poste" id="poste" size="1">
					<OPTION selected="selected">Client residentiel
					<OPTION>Client affaire
					<OPTION>Administrateur
				</SELECT><br />
				 <input type="submit" value="Ajouter Utilisateur" class="sansLabel" /> <br />

				<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

			</fieldset>
		</form>
	</div>

</body>
</html>