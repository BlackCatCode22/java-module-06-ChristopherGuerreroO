package Zoo;

import java.time.LocalDate;

public class Tiger extends Animal {
    private boolean isAlpha;

    public Tiger(String name, int age, String sex, double weight, String origin, boolean isAlpha) {
        super(name, age, sex, weight, origin);
        this.isAlpha = isAlpha;
        this.setSpecies("tiger");
    }

    public boolean isAlpha() {
        return isAlpha;
    }

    public void setAlpha(boolean alpha) {
        isAlpha = alpha;
    }
}
