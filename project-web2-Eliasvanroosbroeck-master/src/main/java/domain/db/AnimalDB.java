package domain.db;

import domain.model.Animal;

import java.util.ArrayList;

public class AnimalDB {
    private int sequence = 0;
    private final ArrayList <Animal> animalList = new ArrayList<>();

    public AnimalDB() {
        this.add(new Animal("Dobberman",2,"M",500));
        this.add(new Animal("Pommeriaan",2,"F",700));
        this.add(new Animal("Pommeriaan",3,"M",600));
        this.add(new Animal("Pitbull",5,"M",650));
        this.add(new Animal("Wippet",13,"F",400));
    }

    public void add (Animal animals){
        if (animals == null) throw new IllegalArgumentException("kan niet leeg zijn");
        //if (find(animals.getId())!=null) throw new IllegalArgumentException("already exists");
        this.sequence++;
        animalList.add(animals);
    }

    public boolean existinDB (Animal animals) {
        for (Animal a : animalList){
            if (a.getDier().equalsIgnoreCase(animals.getDier()) && a.getGeslacht().equals(animals.getGeslacht())) {
                return true;
            }
        }return false;
    }
    public ArrayList<Animal> getAnimallist(){
        return animalList;
    }
    public String getGrootsteStock() {
        if (animalList.size() == 0)
            return "Er zijn momenteel geen dieren aanwezig.";
        Animal grootsteStock = animalList.get(0);
        for (Animal a : animalList) {
            if (a.getAantal() > grootsteStock.getAantal())
                grootsteStock = a;
        }
        return "De grootst aanwezig stock zijn: " + grootsteStock.getGeslacht() + " " + grootsteStock.getDier();
    }
    public Animal find(String animal, String geslacht){
        if( animal.isEmpty() || animal.trim().isEmpty()) throw new IllegalArgumentException("Het ras mag niet leeg zijn");
        for (Animal a : animalList){
            if (a.getDier().equalsIgnoreCase(animal) && a.getGeslacht().equals(geslacht)) {
                return a;
            }
        }
        return null;
    }
    public void delete(String animal, String geslacht){
        animalList.removeIf(a -> a.getDier().equalsIgnoreCase(animal) && geslacht.equals(a.getGeslacht()));
    }
    public void change (String animal, String geslacht, int aantal, int prijs){
        if( animal.isEmpty() || animal.trim().isEmpty()) throw new IllegalArgumentException("Het ras mag niet leeg zijn");
        if( aantal <= 0 ) throw new IllegalArgumentException("Het aantal moet groter dan 0 zijn");
        if( prijs <= 0) throw new IllegalArgumentException("prijs moet hoger dan 0 euro zijn");
        for (Animal a : animalList){
            if (a.getDier().equalsIgnoreCase(animal) && a.getGeslacht().equals(geslacht)) {
                a.setAantal(aantal);
                a.setDier(animal);
                a.setGeslacht(geslacht);
                a.setPrijs(prijs);
            }
        }
    }
}