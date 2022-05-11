package s184590.marek.application;

import java.util.Comparator;

public class MageComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage o1, Mage o2) {
        int result = o1.getLevel() - o2.getLevel();
        if (result == 0) {
            result = o1.getName().compareTo(o2.getName());
            if(result == 0) {
                double result2 = o1.getPower() - o2.getPower();
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
}
