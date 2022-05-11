import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int no_of_threads = Integer.parseInt(args[0]);
        final Results results = new Results();
        final Tasks tasks = new Tasks();
        final List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < no_of_threads; i++) {
            Thread t = new Thread(new Manager(tasks, results));
            threads.add(t);
            t.start();
        }

        // pass the path to the file as a parameter
        String path = new File("").getAbsolutePath();
        path = path + "\\src\\main\\resources\\test 2 watki.txt";
        File file = new File(path);
        Scanner sc = new Scanner(file);

        long value;
        while (sc.hasNextLine()){
            value = Long.parseLong(sc.nextLine());
            tasks.put(value);
        }

        sc.close();

        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        options();
        while(run){
            switch (scanner.next()) {
                case "a" -> {
                    System.out.println("Enter number: ");
                    tasks.put(scanner.nextLong());
                    options();
                }
                case "p" -> {
                    results.print();
                    options();
                }
                case "q" -> run = false;
                default -> System.out.println("Try again!");
            }
        }
        results.print();
        results.to_file();
        scanner.close();
        for(Thread t:threads){
            t.interrupt();
        }

    }

    public static void options(){
        System.out.println("Choose");
        System.out.println("a. Add task");
        System.out.println("p. Print");
        System.out.println("q. Quit");
    }
}
