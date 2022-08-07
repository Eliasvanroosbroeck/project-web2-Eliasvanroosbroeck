<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>zoek</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<header>
    <div class="banner">
        <h1>Dier en Plezier</h1>
    </div>
    <nav>
        <ul>

            <li><a href="Servlet?command=home">Home</a></li>
            <li><a href="Servlet?command=voegToe">Voeg Toe</a></li>
            <li><a href="Servlet?command=overzicht">Overzicht</a></li>
            <li class="active"><a href="Servlet?command=zoek">Zoek</a></li>
            <li><a href="Servlet?command=overzichtfiets">fietsen</a></li>
            <li><a href="Servlet?command=formfiets">Zoek fiets</a></li>
        </ul>
    </nav>

</header>
<main>
    <article class="grid-container">
        <div class="gridform2">
            <h2>Zoek in de Catalogus</h2>
            <div class="errors">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <div class="gridform1">
            <h3>Vind een dier</h3>
            <p>Geef de naam van het ras dat je wil zoeken: </p>
            <form class ="gridform1" action="Servlet?command=zoek" novalidate >
                <p>
                    <label for="naam">Ras</label>
                    <input type="text" id="naam" name="ras">

                    <label for="geslacht">Geslacht?</label>
                    <select id="geslacht" name="geslacht">
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </select>
                </p>
                <input type="hidden" name="command" value="zoekRas">
                <p>
                    <input class="button" type="submit" value="Zoek">
                </p>
            </form>
        </div>


    </article>
</main>
<footer>
    <c:if test="${cookie.cookiegevonden!=null && cookie.cookiegevonden.value!='0' }">
        <p> u deed al ${cookie.cookiegevonden.value} succesvolle zoekopdrachten   <a href="Servlet?command=reset">reset</a> </p>

    </c:if>
    <c:if test="${cookie.cookiegevonden==null || cookie.cookiegevonden.value=='0' }">
        <p>Copyright &copy; Elias Van Roosbroeck </p>
    </c:if>


</footer>
</body>
</html>
