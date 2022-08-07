
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>verwijderResultaat</title>
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
        <h2 class="gridh2">Voeg Toe aan Catalogus</h2>
        <div class="gridform1">
            <h3>Verwijderen uit lijst?</h3>
            <p>Weet je zeker dat je ${ras} uit de lijst wilt verwijderen?</p>

            <form action="Servlet?command=deletedefinitif">
                <input type="hidden" name="command" value="deletedefinitif">
                <input type="hidden" name="ras" value="${ras}">
                <input type="hidden" name="geslacht" value="${geslacht}">
                <button type="submit" name="confirm" value="yes">Ga verder</button>
                <button type="submit" name="confirm" value="no">Annuleer</button>
            </form>


        </div>


    </article>
</main>
<footer>
    <p>Copyright &copy; Elias Van Roosbroeck</p>
</footer>
</body>
</html>
