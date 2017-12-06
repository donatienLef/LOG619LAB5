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
    <form method="post" action="<c:url value="${TARGET}"/>">
        <fieldset>
            <legend>Identification</legend>
            <label for="password">Mot de passe</label> <input type="password"
                                                                id="password" name="password" value="" size="30" maxlength="250" />
            <span class="erreur">${form.erreurs['motdepasse']}</span><br /> <span
                class="erreur">${form.erreurs['compte']}</span><br/>
        </fieldset>
        <input type="submit" value="Valider" />

    </form>
</div>

</body>
</html>