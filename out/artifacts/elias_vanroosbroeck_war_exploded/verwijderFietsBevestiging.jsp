<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Verwijder bevestiging</title>
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
        <h2 class="gridh2">Werwijder uit Catalogus</h2>
        <div class="gridform1">

            <p>Wil je de fiets met naam ${naam} en merk ${merk} uit de lijst verwijderen?</p>

            <button><a href="Servlet?fietsid=${id}&confirm=yes&command=verwijderfietsdefintief">Verwijder</a></button>
            <button><a href="Servlet?fietsid=${id}&confirm=no&command=verwijderfietsdefintief">Cancel</a></button>


        </div>
    </article>
</main>

</body>
</html>
