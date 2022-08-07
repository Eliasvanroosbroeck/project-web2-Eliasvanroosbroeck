package domain.db;

import domain.model.Fiets;
import java.util.ArrayList;
import java.util.List;

public class FietsenDB {
    private int sequence = 0;
    private final List<Fiets> fietsen = new ArrayList<>();

    public FietsenDB() {
        this.add(new Fiets("Giovanni", "Giant", 2018, 8300,650));
        this.add(new Fiets("Cedric", "Cube", 2015, 25500,750));
        this.add(new Fiets("Gerda", "Giant", 2020, 9578,850));
        this.add(new Fiets("Benny", "Bianchi", 2021, 1578,850));
        this.add(new Fiets("Patrick", "Pinarello", 2020, 17000,600));
        this.add(new Fiets("Bieke", "Bianchi", 2022, 1254,1400));
    }

    public void add(Fiets fiets) {
        if (fiets == null)
            throw new DomainException("Geen geldige fiets om toe te voegen");
        this.sequence++;
        fiets.setId(sequence);
        fietsen.add(fiets);
    }

    public List<Fiets> getAlleFietsen() {
        return fietsen;
    }

    public Fiets getFietsMetMeesteKilometers() {
        if (fietsen.size() == 0)
            return null;
        Fiets fietsMetMeesteKilometers = fietsen.get(0);
        for (Fiets fiets : fietsen) {
            if (fiets.getKilometers() > fietsMetMeesteKilometers.getKilometers())
                fietsMetMeesteKilometers = fiets;
        }
        return fietsMetMeesteKilometers;
    }

    public void pasKilometersAan(int id, int kilometer) {
        getFietsById(id).setKilometers(kilometer);
    }

    public Fiets getFietsById(int id) {
        for (Fiets fiets : fietsen)
            if (fiets.getId() == id)
                return fiets;
        throw new DomainException("Er is geen fiets met gegeven id");
    }

    public List<Fiets> getFietsenVanMerk(String merk) {
        if (merk == null || merk.trim().length() < 3)
            throw new DomainException("Het te zoeken merk moet minstens 3 karakters bevatten");
        List<Fiets> result = new ArrayList<>();
        for (Fiets fiets : getAlleFietsen()) {
            if (fiets.getMerk().toLowerCase().contains(merk.toLowerCase()))
                result.add(fiets);
        }
        if (result.size() > 0)
            return result;
        throw new DomainException("Geen fietsen gevonden van het merk " + merk);
    }

    public void verwijderFiets(int id) {
        fietsen.remove(getFietsById(id));
    }


    @Override
    public String toString() {
        String out = "";
        for (Fiets fiets : getAlleFietsen()
        ) {
            out += fiets.toString() + "\n";
        }
        return out;
    }
}
