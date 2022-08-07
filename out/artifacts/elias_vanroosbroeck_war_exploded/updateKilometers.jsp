<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Nieuw aantal kilometers</title>
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
                <li><a href="Servlet?command=overzichtfiets">fietsen</a></li>
                <li><a href="Servlet?command=formfiets">Zoek fiets</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <article class="grid-container">
            <h2 class="gridh2">Pas de afgelegde kilometers aan</h2>
            <c:if test="${errors != null}">
                <div class="errors">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <div class="gridform1">

                <form method="POST" novalidate action="Servlet?command=updatefiets&fietsid=${fietsid}">
                    <label for="aantal">Nieuw aantal: </label>
                    <input name="aantal" id="aantal" type="number" value="${km}">
                    <input class="button" type="submit" value="Pas  aan">
                </form>
            </div>
        </article>
    </main>

</div>
<footer>
    <p>Copyright &copy; Elias Van Roosbroeck</p>
</footer>
</body>
</html>
