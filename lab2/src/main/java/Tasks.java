import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private final List<Task> tasks = new ArrayList<>();
    public synchronized Task get() throws InterruptedException{
        while (tasks.isEmpty()){
            wait();
        }
        return tasks.remove(0);
    }
    public synchronized void put(Long value) {
        tasks.add(new Task(value));
        notifyAll();
    }
}
