package s184590.marek.application;

import java.util.Map;
import java.util.Objects;

public class Simulation {
    private static int sortType;
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        sortType = 0;
        if(args.length == 1){
            if(args[0].equals("natural")){
                sortType = 1;
            }
            else if (args[0].equals("alternative")){
                sortType = 2;
            }
        }
        simulation.init();
    }

    public void init() {
        Mage mage1 = new Mage("Marek", 11, 3.82);
        Mage mage2 = new Mage("Bartek", 12, 13.82);
        Mage mage3 = new Mage("Wiktor", 10, 3.2);
        Mage mage4 = new Mage("Mikolaj", 4, 2.1);
        Mage mage5 = new Mage("Andrzej", 2, 8.1);
        Mage mage6 = new Mage("Piotr", 15, 1.0);
        Mage mage7 = new Mage("Jack", 13, 2.1);
        Mage mage8 = new Mage("Buck", 20, 6.12);
        Mage mage9 = new Mage("Michal", 8, 4.21);
        Mage mage10 = new Mage("Martin", 2, 1.01);

        MageSchool school = new MageSchool(sortType);

        school.addMage(mage1);
        school.addMage(mage2);
        school.addMage(mage3);
        school.addMage(mage4);
        school.addMage(mage5);
        school.addMage(mage6);
        school.addMage(mage7);
        school.addMage(mage8);
        school.addMage(mage9);
        school.addMage(mage10);

        mage10.addApprentices(mage2);
        mage10.addApprentices(mage5);
        mage10.addApprentices(mage4);
        mage10.addApprentices(mage7);
        mage10.addApprentices(mage8);
        mage10.addApprentices(mage9);
        mage2.addApprentices(mage1);
        mage1.addApprentices(mage3);
        mage5.addApprentices(mage6);

        Map<Mage, Integer> map = school.getStatistics();
        for(Mage mage: map.keySet()) {
            System.out.print(mage);
            System.out.print(" : ");
            System.out.println(map.get(mage));
        }

        mage10.print("-");
    }
}
