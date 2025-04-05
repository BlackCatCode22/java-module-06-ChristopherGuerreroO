package Zoo;

public class Bear extends Animal {
    public Bear(String name, int age, String sex, double weight, String origin) {
        super(name, age, sex, weight, origin);
        this.setSpecies("bear");
    }
}
