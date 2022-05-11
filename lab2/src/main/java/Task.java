import java.util.ArrayList;
import java.util.List;

public class Task {
    private final long value;
    private final List<Long> dzielniki = new ArrayList<>();

    public Task(Long value) {
        this.value = value;
    }

    public Task execute() {
        for (long i = 1; i <= value; i++) {
            if (value % i == 0) {
                dzielniki.add(i);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "value=" + value +
                ", dzielniki=" + dzielniki +
                '}';
    }
}
