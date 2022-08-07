<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>index</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<header>
    <div class="banner">
        <h1>Dier en Plezier</h1>
    </div>
    <nav>
        <ul>

            <li class="active"><a href="Servlet?command=home">Home</a></li>
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
        <h2 class="gridh2">Home</h2>
        <figure class="gridfigure">
            <img src="./Images/puppy.jpg" alt="Puppy picture">
        </figure>
        <p class="gridpara">De grenzeloze liefde die je voor een huisdier koestert, daar weten wij alles van. Hier kunnen
            echte dierenvrienden terecht voor het aanschaffen van huisdieren. </p>

        <div class="logbook">
            <H3>Recente aanpassingen</H3>
            <c:if test="${logbookras == null}">
                <p> U heeft nog geen items gewijzigd.</p>
            </c:if>

            <c:if test="${logbookras != null }">
                <div class="gridcreate">


                    <div class="een">
                        <H4>Ras</H4>
                        <c:forEach var="ras" items="${logbookras}">
                            <p>${ras}</p>
                        </c:forEach>
                    </div>


                    <div class="twee">
                        <H4>Aantal</H4>
                        <c:forEach var="aantal" items="${logbookaantal}">
                            <p class="twee">${aantal}</p>
                        </c:forEach>
                    </div>

                    <div class="drie">
                        <H4>Geslacht</H4>
                        <c:forEach var="gender" items="${logbookgender}">
                            <p class="drie">${gender}</p>
                        </c:forEach>
                    </div>

                    <div class="vier">
                        <H4>Prijs</H4>
                        <c:forEach var="prijs" items="${logbookprijs}">
                            <p class="vier">${prijs}</p>
                        </c:forEach>

                    </div>
                </div>

            </c:if>

        </div>

        <p class="gridp"> ${grootsteStock}</p>
    </article>
</main>
<footer>
    <p>Copyright &copy; Elias Van Roosbroeck</p>
</footer>
</body>
</html>