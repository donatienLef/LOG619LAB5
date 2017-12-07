<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Identification</title>
    <link type="text/css" rel="stylesheet"
          href="<c:url value="/public/style.css"/>"/>
    <c:import url="/public/menu.jsp"/>
</head>
<body>
<div id="formulaire">
    <form method="post" action="<c:url value="/ChangePassword"/>">
        <fieldset>
            <legend>Nouveau mot de passe</legend>
            <label for="motdepasse">Mot de passe</label> <input type="password"
                                                                id="password" name="motdepasse" value="" size="30"
                                                                maxlength="250"/>
            <span class="erreur">${form.erreurs['motdepasse']}</span><br/>
            <label for="motdepasse2">Confirmer Mot de passe</label> <input type="password"
                                                                id="password2" name="motdepasse2" value="" size="30"
                                                                maxlength="250"/><span
                class="erreur">${form.erreurs['compte']}</span><br/>
        </fieldset>
                <input type="submit" value="Valider"/> <input type="reset"
                                                              value="Remettre à zéro"/> <br/>

    </form>
</div>

</body>
</html>