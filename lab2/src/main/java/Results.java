import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Results {
    private final List<Task> results = new ArrayList<>();

    public synchronized void add(Task result) {
        this.results.add(result);
    }

    public synchronized void print(){
        for(Task t : results) {
            System.out.println(t);
        }
    }

    public void to_file(){
        String path = new File("").getAbsolutePath();
        path = path + "\\src\\main\\resources\\results.txt";

        try{
            FileWriter writer = new FileWriter(path, false);
                for(Task t : results) {
                writer.write(t.toString());
                writer.write(System.lineSeparator());
                }
            writer.close();
        } catch (IOException e){
                    return;
        }

    }
}
