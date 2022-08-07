<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mijn fietsen</title>
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
            <li class="active"><a href="Servlet?command=formfiets">Zoek fiets</a></li>
        </ul>
    </nav>
</header>
<main>
    <article class="grid-container">
        <div class="gridform2">
            <h2>Toon de fiets met merk</h2>
            <c:if test="${errors != null}">
                <div class="errors">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>


        <form method="POST" novalidate action="Servlet?command=zoekfiets">
            <label name="merklabel" for="merk">Merk:</label>
            <input name="merk" type="text" id="merk" value="${merk}">
            <input class="button" type="submit" value="Zoek">
        </form>
        </div>
        <c:if test="${leeg != null}">
            <p class="gridform3">niets gevonden.</p>
        </c:if>

        <c:if test="${gevonden != null}">
            <table class="gridform3">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Naam fiets</th>
                    <th>Merk</th>
                    <th>Bouwjaar</th>
                    <th>Aantal km</th>
                    <th>prijs (in €)</th>
                </thead>
                <tbody>
                <tr>
                        <c:forEach var="fiets" items="${gevonden}">
                <tr>
                    <td>${fiets.id}</td>
                    <td>${fiets.naam}</td>
                    <td>${fiets.merk}</td>
                    <td>${fiets.bouwjaar}</td>
                    <td>${fiets.kilometers}</td>
                    <td>${fiets.prijs}</td>
                </tr>
                </c:forEach>

                </tbody>
            </table>
        </c:if>

    </article>

</main>
</body>
</html>