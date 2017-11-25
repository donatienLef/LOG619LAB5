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
		<form method="post" action="<c:url value="/Home/Administration"/>">
			<fieldset>
				<legend>Administration</legend>

				<label for="mdpPattern">mdpPattern<span class="requis">*</span></label>
				<input type="text" id="mdpPattern" name="mdpPattern"
					value="${configuration.getMdpPattern()}" size="20"
					maxlength="60" /><br />
				<label for="nbeTentativeMax">nbeTentativeMax<span class="requis">*</span></label>
				<input type="text" id="nbeTentativeMax" name="nbeTentativeMax"
					value="${configuration.getNbeTentativeMax()}" size="20"
					maxlength="60" /><br />
				<label for="nbeMinutesEntreTentative">nbeMinutesEntreTentative<span class="requis">*</span></label>
				<input type="text" id="nbeMinutesEntreTentative" name="nbeMinutesEntreTentative"
					value="${configuration.getNbeMinutesEntreTentative()}" size="20"
					maxlength="60" /><br />
				<label for="blocageIsPossible">blocageIsPossible<span class="requis">*</span></label>
				<input type="text" id="blocageIsPossible" name="blocageIsPossible"
					value="${configuration.getBlocageIsPossible()}" size="20"
					maxlength="60" /><br />
				<label for="changePasswordAfterNTentative">changePasswordAfterNTentative<span class="requis">*</span></label>
				<input type="text" id="changePasswordAfterNTentative" name="changePasswordAfterNTentative"
					value="${configuration.getChangePasswordAfterNTentative()}" size="20"
					maxlength="60" /><br />
				<label for="changePasswordAfterForget">changePasswordAfterForget<span class="requis">*</span></label>
				<input type="text" id="changePasswordAfterForget" name="changePasswordAfterForget"
					value="${configuration.getChangePasswordAfterForget()}" size="20"
					maxlength="60" />     
					
				<br /> <input type="submit" value="Set Configurations" class="sansLabel" />
				<br />

				
			</fieldset>
		</form>
	</div>
	<div>
		<h1>Configurations</h1>
		<p>Protect BruteForce</p>
		<p>
			<input id="checkBox" type="checkbox">
		<p>Password change</p>
		<select name="Policy" onBlur="f_papper();">
			<option value="">---SELECT---</option>
			<option value="Periodique"
				<%if ("value1".equals("bonjour")) {
				System.out.print("selected='selected'");
			}%>>Periodique</option>
			<option value="After issue"
				<%if ("value1".equals("bonjour")) {
				System.out.print("selected='selected'");
			}%>>After
				issue</option>
		</select>
		<p>Password Complexity</p>
		<p>Complexité du mot de passe (longueur, composé d’au moins une
			minuscule et une majuscule, d’un caractère spécial et d’un chiffre,
			etc.)</p>
	</div>
</body>
</html>