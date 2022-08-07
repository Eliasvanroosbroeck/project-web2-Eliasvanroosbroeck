<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bekijk alle fietsen</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div id="container">
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
            <li class="active"><a href="Servlet?command=overzichtfiets">fietsen</a></li>
            <li><a href="Servlet?command=formfiets">Zoek fiets</a></li>
        </ul>
    </nav>
    </header>

    <main>
        <article class="grid-container">
        <h2 class="gridh2">Bekijk alle fietsen</h2>

            <c:if test="${verwijderdnaam != null}">
            <div class="errors">
                <p >U heeft zopas de fiets met naam ${verwijderdnaam} verwijderd. <a  href="Servlet?command=restore">Verwijderen ongedaan maken.</a></p>
            </div>
            </c:if>


        <c:if test="${fietsen == null}">
            <p class="table">Er zijn geen fietsen.</p>
        </c:if>

        <c:if test="${fietsen != null }">


        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Naam fiets</th>
                <th>Merk</th>
                <th>Bouwjaar</th>
                <th>aantal km</th>
                <th>prijs (in €)</th>
                <th>Verwijder fiets</th>
                <th>Update km</th>
            </thead>
            <tbody>
            <c:forEach var="fiets" items="${fietsen}">
                <tr>
                    <td>${fiets.id}</td>
                    <td>${fiets.naam}</td>
                    <td>${fiets. merk}</td>
                    <td>${fiets.bouwjaar}</td>
                    <td>${fiets.kilometers}</td>
                    <td>${fiets.prijs}</td>
                    <td><a href="Servlet?fietsid=${fiets.id}&command=verwijderfiets">Verwijder</a></td>
                    <td><a href="Servlet?fietsid=${fiets.id}&command=updatekm">Update</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
        </c:if>


            <c:if test="${cook != null && cook != 'removed'}">
                <p class="para_2"> De laatst gewijzigde fiets was : ${cook}</p>
            </c:if>

            <c:if test="${cook == 'removed' }">
                <p class="para_2"> De fiets met ID : ${fietsidcook} bestaat niet meer.</p>
            </c:if>



        </article>

    </main>
</div>
<footer>

    <p>Copyright &copy; Elias Van Roosbroeck</p>
</footer>
</body>
</html>