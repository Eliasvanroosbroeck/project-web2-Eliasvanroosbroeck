package ui.controller;

import domain.db.DomainException;
import domain.db.FietsenDB;
import domain.model.Animal;
import domain.db.AnimalDB;
import domain.model.Fiets;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private AnimalDB animalDB = new AnimalDB();
    private FietsenDB fietsenDB = new FietsenDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String destination;
        if (command == null) {
            command = "";
        }

        logbookread(request);

        switch (command) {
            case "overzicht":
                destination = overzicht(request);
                break;
            case "voegToe":
                destination = voegToe(request);
                break;
            case "add":
                destination = add(request);
                break;
            case "zoek":
                destination = zoek(request);
                break;
            case "zoekRas":
                destination = zoekRas(request, response);
                break;
            case "delete":
                destination = delete(request);
                break;
            case "change":
                destination = change(request);
                break;
            case "changedefinitif":
                destination = changedefinitif(request);
                break;
            case "deletedefinitif":
                destination = deletedefinitif(request);
                break;
            case "reset":
                destination = reset(request, response);
                break;
            //toegevoegd herexames
            case "overzichtfiets":
                destination = overzichtfiets(request);
                break;
            case "formfiets":
                destination = formfiets(request);
                break;
            case "updatekm":
                destination = updatekm(request);
                break;
            case "updatefiets":
                destination = updatefiets(request, response);
                break;
            case "verwijderfiets":
                destination = verwijderfiets(request);
                break;
            case "verwijderfietsdefintief":
                destination = verwijderfietsdefintief(request);
                break;
            case "zoekfiets":
                destination = zoekfiets(request);
                break;
            case "cookiforward":
                destination = cookieforward(request,response);
                break;
            case "restore":
                destination = restore(request);
                break;
            default:
                destination = home(request);
                break;
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String reset(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookieWithKey(request, "cookiegevonden");
        int count = 0;

        if (cookie != null) {
            int cgevonden = Integer.parseInt(cookie.getValue());
            count = cgevonden;
            count -= count;
        }
        String count_in_string = count + "";

        Cookie c = new Cookie("cookiegevonden", count_in_string);
        response.addCookie(c);
        request.setAttribute("cookiegevonden", count_in_string);
        return home(request);
    }

    private String deletedefinitif(HttpServletRequest request) {
        String ras = request.getParameter("ras");
        String gender = request.getParameter("geslacht");
        String confirmation = request.getParameter("confirm");
        if (confirmation.equals("yes")) {
            animalDB.delete(ras, gender);
            return overzicht(request);
        } else {
            return home(request);
        }

    }

    private String change(HttpServletRequest request) {
        String ras = request.getParameter("ras");
        String gender = request.getParameter("geslacht");
        request.setAttribute("ras", ras);
        request.setAttribute("geslacht", animalDB.find(ras, gender).getGeslacht());
        request.setAttribute("aantal", animalDB.find(ras, gender).getAantal());
        request.setAttribute("prijs", animalDB.find(ras, gender).getPrijs());
        return "changeResultaat.jsp";
    }

    private String delete(HttpServletRequest request) {
        String ras = request.getParameter("ras");
        String gender = request.getParameter("geslacht");
        request.setAttribute("ras", ras);
        request.setAttribute("geslacht", gender);
        return "verwijderResultaat.jsp";
    }

    private String zoekRas(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        String zoekRas = request.getParameter("ras");
        String zoekGeslacht = request.getParameter("geslacht");
        request.setAttribute("zoekopdracht", zoekRas);

        try {
            animalDB.find(zoekRas, zoekGeslacht);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            return "zoek.jsp";
        }
        request.setAttribute("resultaat", animalDB.find(zoekRas, zoekGeslacht));
        if (animalDB.find(zoekRas, zoekGeslacht) != null) {
            request.setAttribute("dier", animalDB.find(zoekRas, zoekGeslacht).getDier());
            request.setAttribute("geslacht", animalDB.find(zoekRas, zoekGeslacht).getGeslacht());
            request.setAttribute("aantal", animalDB.find(zoekRas, zoekGeslacht).getAantal());
            createCookie(request, response);
        }
        return "zoekResultaat.jsp";

    }

    private String zoek(HttpServletRequest request) {
        return "zoek.jsp";
    }

    private String add(HttpServletRequest request) {
        ArrayList<String> errors = new ArrayList<>();
        Animal animal = new Animal();
        setNaam(animal, request, errors);
        setAantal(animal, request, errors);
        setPrijs(animal, request, errors);
        setGeslacht(animal, request, errors);

        if (errors.isEmpty() && !animalDB.existinDB(animal)) {
            animalDB.add(animal);
            return overzicht(request);
        } else {
            if (animalDB.existinDB(animal)) {
                errors.add("Dier bestaat reeds in de Database");
            }
            request.setAttribute("errors", errors);
            return "voegToe.jsp";
        }
    }

    private String changedefinitif(HttpServletRequest request) {
        ArrayList<String> errors = new ArrayList<>();
        String ras = request.getParameter("ras");
        String gender = request.getParameter("geslacht");
        request.setAttribute("geslacht", gender);

        Animal animalfound = animalDB.find(ras, gender);

        Animal animal = new Animal();
        setNaam(animal, request, errors);
        setAantal(animal, request, errors);
        setPrijs(animal, request, errors);
        setGeslacht(animal, request, errors);

        if (errors.isEmpty()) {
            setNaam(animalfound, request, errors);
            setAantal(animalfound, request, errors);
            setPrijs(animalfound, request, errors);
            setGeslacht(animalfound, request, errors);

            HttpSession session = request.getSession();
            session.setAttribute("ras", ras);
            logbookadd(request, ras, animalfound.getAantal(), gender, animalfound.getPrijs());
            return overzicht(request);
        } else {
            request.setAttribute("errors", errors);
            return change(request);
        }
    }

    private String voegToe(HttpServletRequest request) {
        return "voegToe.jsp";
    }

    private String home(HttpServletRequest request) {
        request.setAttribute("grootsteStock", animalDB.getGrootsteStock());
        return "index.jsp";
    }

    private String overzicht(HttpServletRequest request) {
        request.setAttribute("animallist", animalDB.getAnimallist());
        request.setAttribute("grootsteStock", animalDB.getGrootsteStock());
        return "overzicht.jsp";
    }

    private void setNaam(Animal animal, HttpServletRequest request, ArrayList<String> errors) {
        String ras = request.getParameter("ras");
        try {
            animal.setDier(ras);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setAantal(Animal animal, HttpServletRequest request, ArrayList<String> errors) {
        String aantal = request.getParameter("aantal");
        try {
            int aantalDieren = Integer.parseInt(aantal);
            animal.setAantal(aantalDieren);
        } catch (NumberFormatException parseIntIssue) {
            errors.add("Aantal moet positief zijn");
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setGeslacht(Animal animal, HttpServletRequest request, ArrayList<String> errors) {
        String geslacht = request.getParameter("geslacht");
        try {
            animal.setGeslacht(geslacht);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setPrijs(Animal animal, HttpServletRequest request, ArrayList<String> errors) {
        String prijs = request.getParameter("prijs");
        try {
            int prijsRas = Integer.parseInt(prijs);
            animal.setPrijs(prijsRas);
        } catch (NumberFormatException parseIntIssue) {
            errors.add("Prijs moet positief zijn");
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void createCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookieWithKey(request, "cookiegevonden");
        int count = 1;

        if (cookie != null) {
            int cgevonden = Integer.parseInt(cookie.getValue());
            count = cgevonden;
            count++;
        }

        String count_in_string = count + "";


        Cookie c = new Cookie("cookiegevonden", count_in_string);
        response.addCookie(c);
        request.setAttribute("cookiegevonden", count_in_string);
    }

    private Cookie getCookieWithKey(HttpServletRequest request, String key) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }

    private void logbookadd(HttpServletRequest request, String ras, int aantal, String gender, int prijs) {
        HttpSession session = request.getSession();
        if (session.getAttribute("logsras") == null) session.setAttribute("logsras", new ArrayList<String>());
        if (session.getAttribute("logsaantal") == null) session.setAttribute("logsaantal", new ArrayList<Integer>());
        if (session.getAttribute("logsgender") == null) session.setAttribute("logsgender", new ArrayList<String>());
        if (session.getAttribute("logsprijs") == null) session.setAttribute("logsprijs", new ArrayList<Integer>());
        ArrayList<String> logsras = (ArrayList<String>) session.getAttribute("logsras");
        ArrayList<Integer> logsaantal = (ArrayList<Integer>) session.getAttribute("logsaantal");
        ArrayList<String> logsgender = (ArrayList<String>) session.getAttribute("logsgender");
        ArrayList<Integer> logsprijs = (ArrayList<Integer>) session.getAttribute("logsprijs");
        logsras.add(ras);
        logsaantal.add(aantal);
        logsgender.add(gender);
        logsprijs.add(prijs);


    }

    private void logbookread(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<String> logsras = (ArrayList<String>) session.getAttribute("logsras");
        ArrayList<Integer> logsaantal = (ArrayList<Integer>) session.getAttribute("logsaantal");
        ArrayList<String> logsgender = (ArrayList<String>) session.getAttribute("logsgender");
        ArrayList<Integer> logsprijs = (ArrayList<Integer>) session.getAttribute("logsprijs");
        request.setAttribute("logbookras", logsras);
        request.setAttribute("logbookaantal", logsaantal);
        request.setAttribute("logbookgender", logsgender);
        request.setAttribute("logbookprijs", logsprijs);
    }

    //herexamen extra deel

    private String cookieforward(HttpServletRequest request, HttpServletResponse response ) {
        return overzichtfiets(request);
    }

    private String overzichtfiets(HttpServletRequest request) {
        Cookie cookie = getCookieWithKey(request, "id");
        HttpSession session = request.getSession();
        Object sessionID = session.getAttribute("fiets");
        request.setAttribute("id", sessionID);

        request.setAttribute("cook",null);
        if (cookie != null) {
            int value = Integer.parseInt(cookie.getValue());
            try {
                request.setAttribute("cook", fietsenDB.getFietsById(value).getMerk());
            } catch (DomainException e) {
                request.setAttribute("fietsidcook", value);
                request.setAttribute("cook", "removed");

            }
        }

        request.setAttribute("fietsen", fietsenDB.getAlleFietsen());
        if (fietsenDB.getAlleFietsen().size() == 0) {
            request.setAttribute("fietsen", null);
        }

        return "overzichtFietsen.jsp";
    }

    private String formfiets(HttpServletRequest request) {
        return "form_vindFiets.jsp";
    }

    private String updatekm(HttpServletRequest request) {
        String fietsid = request.getParameter("fietsid");
        int id = Integer.parseInt(fietsid);
        int km = fietsenDB.getFietsById(id).getKilometers();
        request.setAttribute("km", km);
        request.setAttribute("fietsid", id);
        return "updateKilometers.jsp";
    }

    private String updatefiets(HttpServletRequest request, HttpServletResponse response) {
        String fietsid = request.getParameter("fietsid");
        String kms = request.getParameter("aantal");
        ArrayList<String> errors = new ArrayList<>();
        try {
            int id = Integer.parseInt(fietsid);
            int km = Integer.parseInt(kms);
            fietsenDB.pasKilometersAan(id, km);
            createCookie2(request, response, fietsid);
            return cookieforward(request,response);

        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            int id = Integer.parseInt(fietsid);
            int km = Integer.parseInt(kms);
            request.setAttribute("km", km);
            request.setAttribute("fietsid", id);
            return "updateKilometers.jsp";
        } catch (NumberFormatException parseIntIssue) {
            errors.add("Aantal moet positief zijn");
            int id = Integer.parseInt(fietsid);
            int km = Integer.parseInt(kms);
            request.setAttribute("km", km);
            request.setAttribute("fietsid", id);
            return "updateKilometers.jsp";
        }


    }

    private String verwijderfiets(HttpServletRequest request) {
        String fietsid = request.getParameter("fietsid");
        int id = Integer.parseInt(fietsid);
        request.setAttribute("id", fietsid);
        request.setAttribute("naam", fietsenDB.getFietsById(id).getNaam());
        request.setAttribute("merk", fietsenDB.getFietsById(id).getMerk());
        return "verwijderFietsBevestiging.jsp";
    }

    private String verwijderfietsdefintief(HttpServletRequest request) {
        String fietsid = request.getParameter("fietsid");
        int id = Integer.parseInt(fietsid);
        String confirmation = request.getParameter("confirm");
        if (confirmation.equals("yes")) {
            String naam = fietsenDB.getFietsById(id).getNaam();
            Fiets fiets = fietsenDB.getFietsById(id);
            fietsenDB.verwijderFiets(id);
            request.setAttribute("verwijderdnaam", naam);
            sessionadd(request, fiets);
            return overzichtfiets(request);
        } else {
            return overzichtfiets(request);
        }

    }

    private String zoekfiets(HttpServletRequest request) {
        String fietsmerk = request.getParameter("merk");
        ArrayList<String> errors = new ArrayList<>();
        try {
            request.setAttribute("gevonden", fietsenDB.getFietsenVanMerk(fietsmerk));

            return "form_vindFiets.jsp";
        } catch (DomainException e) {
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            request.setAttribute("merk", fietsmerk);
            request.setAttribute("leeg", "empty");
            return "form_vindFiets.jsp";
        }
    }

    private void createCookie2(HttpServletRequest request, HttpServletResponse response, String fietsid) {
        Cookie cookie = new Cookie("id", fietsid);
        response.addCookie(cookie);
        request.setAttribute("id", fietsid);
    }

    private void sessionadd(HttpServletRequest request, Fiets fiets) {
        HttpSession session = request.getSession();
        session.setAttribute("fiets", fiets);
    }

    private String restore(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Fiets fiets = (Fiets) session.getAttribute("fiets");
        session.removeAttribute("fiets");
        fietsenDB.add(fiets);
        return overzichtfiets(request);

    }
}

