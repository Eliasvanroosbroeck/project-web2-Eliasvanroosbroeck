<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Animal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.db.AnimalDB" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>overzicht</title>
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
            <li class="active"><a href="Servlet?command=overzicht">Overzicht</a></li>
            <li><a href="Servlet?command=zoek">Zoek</a></li>
            <li><a href="Servlet?command=overzichtfiets">fietsen</a></li>
            <li><a href="Servlet?command=formfiets">Zoek fiets</a></li>
        </ul>
    </nav>

</header>
<main>
    <article class="grid-container">
        <h2 class="gridh2">Overzicht Inventaris</h2>
        <table class="table">
            <tr>
                <th>Dier</th>
                <th>Aantal</th>
                <th>Geslacht</th>
                <th>Prijs</th>
                <th>Pas aan</th>
                <th>Verwijder</th>
            </tr>


            <tbody>
            <c:forEach var="ras" items="${animallist}">
            <tr>
                <td>${ras.dier}</td>
                <td>${ras.aantal}</td>
                <td>${ras.geslacht}</td>
                <td>${ras.prijs}</td>
                <td><a id="PasAan${ras.dier}${ras.geslacht}" href="Servlet?ras=${ras.dier}&geslacht=${ras.geslacht}&command=change">Wijzig</a></td>
                <td><a id="Verwijder${ras.dier}${ras.geslacht}" href="Servlet?ras=${ras.dier}&geslacht=${ras.geslacht}&command=delete">Verwijder</a></td>
            </tr>
            </c:forEach>

            </tbody>



        </table>

        <p class="gridp"> <%= (request.getAttribute("grootsteStock"))%></p>
    </article>
</main>
<footer>
    <p>Copyright &copy; Elias Van Roosbroeck</p>
</footer>
</body>
</html>
