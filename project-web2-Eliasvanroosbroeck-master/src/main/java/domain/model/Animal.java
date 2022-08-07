package domain.model;
// object klasse
public class Animal {
    private String dier;
    private int aantal;
    private String geslacht;
    private int prijs;

    public Animal(String dier, int aantal, String geslacht, int prijs) {
        setAantal(aantal);
        setDier(dier);
        setGeslacht(geslacht);
        setPrijs(prijs);
    }

    public Animal(){
    }

    public String getDier() {
        return dier;
    }
    public int getAantal() {
        return aantal;
    }
    public String getGeslacht() {
        return geslacht;
    }
    public int getPrijs() {
        return prijs;
    }


    public void setDier(String dier) {
        if (dier.trim().isEmpty()) throw new IllegalArgumentException("Het ras mag niet leeg zijn");
        this.dier = dier;
    }
    public void setAantal(int aantal) {
        if (aantal < 1) throw new IllegalArgumentException("aantal kan niet lager dan 1 zijn");
        this.aantal = aantal;
    }
    public void setGeslacht(String geslacht) {
        if (geslacht.trim().isEmpty() || geslacht == null) throw new IllegalArgumentException("geslacht mag niet leeg zijn");
        this.geslacht = geslacht;
    }
    public void setPrijs(int prijs) {
        if (prijs <0) throw new IllegalArgumentException("prijs moet hoger dan 0 zijn");
        this.prijs = prijs;
    }
}
