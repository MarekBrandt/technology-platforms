public class Manager implements Runnable{
    private final Results results;
    private final Tasks tasks;

    public Manager(Tasks tasks, Results results) {
        this.results = results;
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Task task = tasks.get();
                //Thread.sleep(2000);
                results.add(task.execute());
            } catch (InterruptedException ie) {
                break;
            }
        }
    }
}
