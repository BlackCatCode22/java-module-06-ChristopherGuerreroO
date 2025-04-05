package Zoo;

import java.time.LocalDate;

public class Animal {
    private String name;
    private int age;
    private String sex;
    private String color;
    private double weight;
    private String origin;
    private String id;
    private LocalDate birthDate;
    private LocalDate arrivalDate;
    private String species;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }


    public Animal(String name, int age, String sex, double weight, String origin) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getColor() {
        return color;
    }

    public double getWeight() {
        return weight;
    }

    public String getOrigin() {
        return origin;
    }

    public String getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
