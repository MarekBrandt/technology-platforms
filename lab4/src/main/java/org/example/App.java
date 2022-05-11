package org.example;

import org.example.entity.Beer;
import org.example.entity.Brewery;
import org.example.repository.BeerRepository;
import org.example.repository.BreweryRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello new project in Maven - Java 17");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4Pu");

        BeerRepository beerRepository = new BeerRepository(emf);
        BreweryRepository breweryRepository = new BreweryRepository(emf);

        Brewery brewery1 = new Brewery("BrowarPG", 40);
        Brewery brewery2 = new Brewery("Browar Eti", 99);
        breweryRepository.add(brewery1);
        breweryRepository.add(brewery2);

        Beer beer1 = new Beer("Merlin", 99, brewery1);
        Beer beer2 = new Beer("Artur", 1,brewery1);
        Beer beer3 = new Beer("Veigar", 27, brewery2);

        beerRepository.add(beer1);
        beerRepository.add(beer2);
        beerRepository.add(beer3);

        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
            showMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Beer name and price:");
                    String name = scanner.next();
                    long price = scanner.nextLong();
                    Beer newBeer = new Beer(name, price, brewery1);
                    beerRepository.add(newBeer);
                    break;
                case 2:
                    System.out.println("Brewery name and value:");
                    name = scanner.next();
                    long value = scanner.nextLong();
                    Brewery newBrewery = new Brewery(name, value);
                    breweryRepository.add(newBrewery);
                    break;
                case 3:
                    System.out.println("Beers with price lower than:");
                    price = scanner.nextLong();
                    for (Beer m: beerRepository.findAllWithPriceLowerThan(price)) {
                        System.out.println(m.toString());
                    }
                    break;
                case 4:
                    System.out.println("From Brewery cheaper than");
                    name = scanner.next();
                    price = scanner.nextLong();
                    for (Beer t: beerRepository.findAllFromBreweryCheaperThan(name, price)) {
                        System.out.println(t.toString());
                    }
                    break;
                case 5:
                    for (Beer t: beerRepository.findAll()) {
                        System.out.println(t.toString());
                    }
                    break;
                case 6:
                    for (Brewery t: breweryRepository.findAll()) {
                        System.out.println(t.toString());
                    }
                    break;
                case 7:
                    System.out.println("Beer name");
                    name = scanner.next();
                    Beer beer = beerRepository.find(name);
                    beerRepository.delete(beer);
                    break;
                case 8:
                    System.out.println("Brewery name");
                    name = scanner.next();
                    Brewery brewery = breweryRepository.find(name);
                    for (Beer b: brewery.getBeers()){
                        //b.toString();
                        beerRepository.delete(b);
                    }
                    brewery.setBeers(null);
                    breweryRepository.delete(brewery);
                    break;
                    case 9:
                    run = false;
                    break;
                default:
                    System.out.println("Wrong value!");
            }
        }


        emf.close();
    }
    public static void showMenu() {
        System.out.println("Menu:");
        System.out.println("1: add beer");
        System.out.println("2: add brewery");
        System.out.println("3: show beers with price < ");
        System.out.println("4: show beers from with price < ");
        System.out.println("5: show all beers ");
        System.out.println("6: show all breweries ");
        System.out.println("7: delete beer ");
        System.out.println("8: delete brewery ");
        System.out.println("9: exit");
    }
}
