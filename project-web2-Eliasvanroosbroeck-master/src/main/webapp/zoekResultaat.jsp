<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Animal" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>zoekResultaat</title>
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
            <li><a href="Servlet?command=zoek">Zoek</a></li>
            <li><a href="Servlet?command=overzichtfiets">fietsen</a></li>
            <li><a href="Servlet?command=formfiets">Zoek fiets</a></li>
        </ul>
    </nav>

</header>
<main>
    <article class="grid-container">
        <h2 class="gridh2">Zoek Resultaat</h2>
        <div class="gridform1">
            <h3>Bevestiging</h3>
        <c:if test="${resultaat != null}">
            <p>We vonden volgend ras met naam ${ras}:</p>
            <ul class="zoekResultaatList">
                <li>Naam: ${dier}</li>
                <li>Soort: ${geslacht}</li>
                <li>Aantal: ${aantal}</li>
            </ul>

        </c:if>
        <c:if test="${resultaat == null}">
            <p>We hebben het ras met naam ${zoekopdracht} voor je opgezocht.</p>
            <p>Helaas, we konden dit ras niet vinden. </p>
        </c:if>

            <a href="Servlet?command=overzicht">Keer terug naar het overzicht</a>
        </div>


    </article>
</main>
<footer>
    <p>Copyright &copy; Elias Van Roosbroeck</p>
</footer>
</body>
</html>
