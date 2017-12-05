<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Bienvenue sur le LAB 5</h1>

<div id="navigation">
	<a id="homeButton"><img src="inc/fichiers/ETS.png"
		alt="Photoshop" /></a>


	<h2>
		Menu</br> </br>
	</h2>

	<ul class="sansPuce">
		<c:choose>
			<c:when test="${Message!=null}">
				<script>
					alert("Vous avez un message important");
				</script>
			</c:when>
		</c:choose>
		<c:choose>
			<%-- Si aucun utilisateur n'existe en session, affichage d'un message par défaut. --%>
			<c:when test="${ empty sessionScope.utilisateur }">
				<li><a href="<c:url value="/Identification"/>"
					class="boutton_nav">Identification</a></li>
				<li><a href="<c:url value="/Inscription"/>" class="boutton_nav">Inscription</a></li>
			</c:when>
			<%-- Sinon, affichage du tableau. --%>
			<c:otherwise>
				<c:choose>

					<c:when test="${ utilisateur.poste == 'Client résidentiel' }">
						<%-- Selon le rôle. --%>
						<li><a href="<c:url value="/Home"/>" class="boutton_nav">Mon
								espace</a></li>
										<li><a href="<c:url value="#"/>" class="boutton_nav">Change Password</a></li>
								
					</c:when>
					<c:when test="${ utilisateur.poste == 'Client affaire' }">
						<li><a href="<c:url value="/Home"/>" class="boutton_nav">Mon
								espace</a></li>
						<li><a href="<c:url value="#"/>" class="boutton_nav">Liste
								clients résidentiels</a></li>
						<li><a href="<c:url value="#"/>" class="boutton_nav">Liste
								clients d'affaire</a></li>
										<li><a href="<c:url value="#"/>" class="boutton_nav">Change Password</a></li>
								
					</c:when>
					<c:when test="${ utilisateur.poste == 'Administrateur' }">
						<li><a href="<c:url value="/Home"/>" class="boutton_nav">Mon
								espace</a></li>
						<li><a href="<c:url value="/Home/ListeUtilisateur"/>" class="boutton_nav">Liste
								clients résidentiels</a></li>
						<li><a href="<c:url value="#"/>" class="boutton_nav">Liste
								clients d'affaire</a></li>
						<li><a href="<c:url value="/Home/NouveauUtilisateur"/>"
							class="boutton_nav">Ajouter un utilisateur</a></li>
						<li><a href="<c:url value="/Home/Administration"/>"
							class="boutton_nav">Administration</a></li>
								<li><a href="<c:url value="#"/>" class="boutton_nav">Change Password</a></li>
						
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</ul>

</div>
