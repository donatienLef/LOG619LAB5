<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Compte Utilisateur</title>
    <link type="text/css" rel="stylesheet"
          href="<c:url value="/public/style.css"/>"/>
    <c:import url="/public/menu.jsp"/>
</head>
<body>
<div>
    <form method="post" action="<c:url value="/Home/Administration"/>">
        <fieldset>
            <legend>Administration</legend>

            <label for="mdpPattern">mdpPattern<span class="requis">*</span></label>
            <input type="text" id="mdpPattern" name="mdpPattern"
                   value="${configuration.getMdpPattern()}" size="20"
                   maxlength="60"/><br/>
            <label for="nbeTentativeMax">nbeTentativeMax<span class="requis">*</span></label>
            <input type="text" id="nbeTentativeMax" name="nbeTentativeMax"
                   value="${configuration.getNbeTentativeMax()}" size="20"
                   maxlength="60"/><br/>
            <label for="nbeMinutesEntreTentative">nbeMinutesEntreTentative<span class="requis">*</span></label>
            <input type="text" id="nbeMinutesEntreTentative" name="nbeMinutesEntreTentative"
                   value="${configuration.getNbeMinutesEntreTentative()}" size="20"
                   maxlength="60"/><br/>
            <label for="blocageIsPossible">blocageIsPossible<span class="requis">*</span></label>
            <input type="text" id="blocageIsPossible" name="blocageIsPossible"
                   value="${configuration.getBlocageIsPossible()}" size="20"
                   maxlength="60"/><br/>
            <label for="changePasswordAfterNTentative">changePasswordAfterNTentative<span
                    class="requis">*</span></label>
            <input type="text" id="changePasswordAfterNTentative" name="changePasswordAfterNTentative"
                   value="${configuration.getChangePasswordAfterNTentative()}" size="20"
                   maxlength="60"/><br/>
            <label for="changePasswordAfterForget">changePasswordAfterForget<span class="requis">*</span></label>
            <input type="text" id="changePasswordAfterForget" name="changePasswordAfterForget"
                   value="${configuration.getChangePasswordAfterForget()}" size="20"
                   maxlength="60"/>
            <label for="changePasswordPeriod">changePasswordPeriod<span class="requis">*</span></label>
            <input type="text" id="changePasswordPeriod" name="changePasswordPeriod"
                   value="${configuration.getChangePasswordPeriod()}" size="20"
                   maxlength="60"/>


            <br/> <input type="submit" value="Set Configurations" class="sansLabel"/>
            <br/>


        </fieldset>
    </form>
</div>
</body>
</html>