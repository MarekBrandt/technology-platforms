package s184590.marek.application;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Mage implements Comparable<Mage>{
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Mage(String name, int level, double power) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = new TreeSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && Double.compare(mage.power, power) == 0 && name.equals(mage.name) && Objects.equals(apprentices, mage.apprentices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, power, apprentices);
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';
    }

    @Override
    public int compareTo(Mage o) {
        int result = name.compareTo(o.name);
        if (result == 0) {
            result = level - o.level;
            if(result == 0) {
                double result2 = power - o.power;
                if(result2 < 0) {
                    result = -10;
                }
                else if (result2 > 0) {
                    result = 10;
                }
            }
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void addApprentices(Mage apprentices) {
        this.apprentices.add(apprentices);
    }

    public void print(String separator){
        System.out.print(separator);
        System.out.println(this);
        for (Mage mage : apprentices) {
            mage.print(separator +separator.charAt(0));
        }

    }

    public Set<Mage> getApprentices() {
        return apprentices;
    }
}
