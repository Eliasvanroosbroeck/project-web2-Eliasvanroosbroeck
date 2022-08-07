package domain.model;
import domain.db.DomainException;

public class Fiets {
    private int id;
    private String naam;
    private String merk;
    private int bouwjaar;
    private int prijs;
    private int kilometers;


    public Fiets(String naam, String merk, int bouwjaar, int kilometers, int prijs) {
        this.naam = naam;
        this.merk = merk;
        this.bouwjaar = bouwjaar;
        this.kilometers = kilometers;
        this.prijs = prijs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty())
            throw new DomainException("De naam van de fiets mag niet leeg zijn");
        this.naam = naam;
    }

        public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        if (merk == null || merk.isEmpty())
            throw new DomainException("Het merk mag niet leeg zijn");
        this.merk = merk;
    }


    public int getBouwjaar() {
        return bouwjaar;
    }

    public void setBouwjaar(int bouwjaar) {
        if (bouwjaar < 0)
            throw new DomainException("Het bouwjaar moet een positief getal zijn");
        this.bouwjaar = bouwjaar;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        if (prijs < 0)
            throw new DomainException("de prijs moet een positief getal zijn");
        this.prijs = prijs;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        if (kilometers <= 0)
            throw new DomainException("Het aantal afgelegde kilometers moet een positief getal zijn");
        this.kilometers = kilometers;
    }

    @Override
    public String toString() {
        return getNaam() + " van " + getMerk() + " (" + getBouwjaar() + ") afgelegde kilometers: " + getKilometers() + ".";
    }
}
