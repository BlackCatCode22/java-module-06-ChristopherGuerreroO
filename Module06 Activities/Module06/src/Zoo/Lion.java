package Zoo;

import java.time.LocalDate;

public class Lion extends Animal {
    private boolean isAlpha;

    public Lion(String name, int age, String sex, double weight, String origin, boolean isAlpha) {
        super(name, age, sex, weight, origin);
        this.isAlpha = isAlpha;
    }

    public boolean isAlpha() {
        return isAlpha;
    }

    public void setAlpha(boolean alpha) {
        isAlpha = alpha;
    }
}
