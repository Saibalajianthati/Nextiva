import java.util.*;

// Class to represent a task with its properties
class Task {
    int id;                  // Unique identifier for the task
    int duration;            // Duration to complete the task
    List<Integer> dependencies; // List of task IDs that must be completed before this task
    int est;                 // Earliest Start Time
    int eft;                 // Earliest Finish Time
    int lst;                 // Latest Start Time
    int lft;                 // Latest Finish Time

    public Task(int id, int duration) {
        this.id = id;
        this.duration = duration;
        this.dependencies = new ArrayList<>();
        this.est = 0;
        this.eft = 0;
        this.lst = Integer.MAX_VALUE;
        this.lft = Integer.MAX_VALUE;
    }
}

public class ProjectScheduler {
    private static final int INF = Integer.MAX_VALUE;
    private Map<Integer, Task> tasks;       // Map to store tasks by their ID
    private Map<Integer, List<Integer>> graph; // Graph representation of task dependencies
    private Map<Integer, Integer> indegree; // In-degree of each task
    private int startTask;                // The ID of the starting task

    public ProjectScheduler() {
        this.tasks = new HashMap<>();
        this.graph = new HashMap<>();
        this.indegree = new HashMap<>();
    }

    // Method to add a task to the project
    public void addTask(int id, int duration) {
        Task task = new Task(id, duration);
        tasks.put(id, task);
        graph.putIfAbsent(id, new ArrayList<>());
        indegree.put(id, 0);
    }

    // Method to define a dependency between tasks
    public void addDependency(int taskId, int dependencyId) {
        // Add dependency
        tasks.get(taskId).dependencies.add(dependencyId);
        graph.get(dependencyId).add(taskId);
        // Update in-degree of task
        indegree.put(taskId, indegree.get(taskId) + 1);
    }

    // Method to set the starting task of the project
    public void setStartTask(int taskId) {
        this.startTask = taskId;
    }

    // Method to calculate the Earliest Start Time (EST) and Earliest Finish Time (EFT) of each task
    public void calculateESTEFT() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startTask);
        Task start = tasks.get(startTask);
        start.est = 0;
        start.eft = start.duration;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            Task currentTask = tasks.get(current);

            for (int neighbor : graph.get(current)) {
                Task neighborTask = tasks.get(neighbor);
                // Update EST and EFT for the neighbor task
                neighborTask.est = Math.max(neighborTask.est, currentTask.eft);
                neighborTask.eft = neighborTask.est + neighborTask.duration;
                // Reduce in-degree and add to queue if in-degree becomes zero
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
    }

    // Method to calculate the Latest Start Time (LST) and Latest Finish Time (LFT) of each task
    public void calculateLSTLFT() {
        // Find the maximum EFT value
        int maxEFT = tasks.values().stream().mapToInt(task -> task.eft).max().orElse(0);

        // Initialize LFT and LST for all tasks
        for (Task task : tasks.values()) {
            task.lft = maxEFT;
            task.lst = task.lft - task.duration;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int id : tasks.keySet()) {
            if (indegree.get(id) == 0) {
                queue.add(id);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            Task currentTask = tasks.get(current);

            for (int neighbor : graph.get(current)) {
                Task neighborTask = tasks.get(neighbor);
                // Update LFT and LST for the neighbor task
                neighborTask.lft = Math.min(neighborTask.lft, currentTask.lst);
                neighborTask.lst = neighborTask.lft - neighborTask.duration;
                // Reduce in-degree and add to queue if in-degree becomes zero
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
    }

    // Method to print the earliest and latest completion times
    public void printResults() {
        int earliestCompletion = tasks.values().stream().mapToInt(task -> task.eft).max().orElse(0);
        int latestCompletion = tasks.values().stream().mapToInt(task -> task.lft).min().orElse(0);

        System.out.println("Earliest time all tasks will be completed: " + earliestCompletion);
        System.out.println("Latest time all tasks will be completed: " + latestCompletion);
    }

    // Main method to handle input and execute the scheduling
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProjectScheduler scheduler = new ProjectScheduler();

        System.out.println("Enter the number of tasks:");
        int numTasks = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        // Adding tasks
        for (int i = 0; i < numTasks; i++) {
            System.out.println("Enter task ID and duration for task " + (i + 1) + ":");
            int id = scanner.nextInt();
            int duration = scanner.nextInt();
            scheduler.addTask(id, duration);
            scanner.nextLine();  // Consume the newline
        }

        System.out.println("Enter the number of dependencies:");
        int numDependencies = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        // Adding dependencies
        for (int i = 0; i < numDependencies; i++) {
            System.out.println("Enter dependency (taskID dependentOnTaskID):");
            int taskId = scanner.nextInt();
            int dependencyId = scanner.nextInt();
            scheduler.addDependency(taskId, dependencyId);
            scanner.nextLine();  // Consume the newline
        }

        System.out.println("Enter the starting task ID:");
        int startTaskId = scanner.nextInt();
        scheduler.setStartTask(startTaskId);

        // Calculating EST, EFT, LST, and LFT
        scheduler.calculateESTEFT();
        scheduler.calculateLSTLFT();
        
        // Printing the results
        scheduler.printResults();

        scanner.close();
    }
}
