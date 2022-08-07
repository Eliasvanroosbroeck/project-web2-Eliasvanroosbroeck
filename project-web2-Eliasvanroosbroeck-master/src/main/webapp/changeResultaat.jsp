<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>change</title>
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
        <div class="gridform2">
            <h2>Verander resultaat van de catalogus</h2>
            <div class="errors">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <div class="gridform1">
            <form method="POST" novalidate action="Servlet?command=changedefinitif&geslacht=${geslacht}&ras=${ras}" >
                <label>${ras}</label>



                <label for="aantal">Aantal:</label>
                <input name="aantal" type="number" id="aantal" value="${aantal}">

                <label for="prijs">Prijs:</label>
                <input name="prijs" type="number" id="prijs" value="${prijs}">

                <input id ="button" class="button" type="submit" value="Submit">
            </form>
        </div>


    </article>
</main>
<footer>
    <p>Copyright &copy; Elias Van Roosbroeck</p>
</footer>
</body>
</html>