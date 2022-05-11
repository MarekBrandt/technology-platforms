package s184590.marek.application;

import java.util.*;

public class MageSchool {
    private Set<Mage> mages;

    public MageSchool(int type) {
        if (type == 0) {
            mages = new HashSet<>();
        }
        else if (type == 1) {
            mages = new TreeSet<>();
        }
        else if (type == 2) {
            mages = new TreeSet<>(new MageComparator());
        }
    }

    public Map<Mage, Integer> getStatistics(){
        Map<Mage, Integer> statistics = new HashMap<>();
        Set<Mage> mages1 = mages;
        for(Mage mage : mages1){
            statistics.put(mage, 0);
            evaluateStatistics(mage, mage, statistics);
        }
        return statistics;
    }

    private void evaluateStatistics(Mage mage, Mage currentMage, Map<Mage, Integer> statistics){
        for (Mage apprentice : currentMage.getApprentices()){
            statistics.put(mage, statistics.get(mage) + 1);
            if(apprentice.getApprentices().size() > 0){
                evaluateStatistics(mage, apprentice, statistics);
            }
        }
    }

    public void addMage(Mage mage) {
        mages.add(mage);
    }
}
