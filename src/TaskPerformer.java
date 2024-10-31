package src;

public class TaskPerformer {
    private TaskPerformer() {
    }

    public static void addTask(String description) {
        System.out.printf("Adding task with description: %s%n", description);
    }

    public static void updateTask(int taskId, String description) {
        System.out.printf("Updating task %d with new description: %s%n", taskId, description);
    }

    public static void deleteTask(int taskId) {
        System.out.printf("Deleting task with ID %d%n", taskId);
    }

    public static void updateTaskStatus(int taskId, String status) {
        System.out.printf("changing task %d status to %s %n", taskId, status);
    }

    public static void listAllTasks() {
        System.out.println("Listing all tasks");
    }

    public static void listTaskByStatus(String status) {
        System.out.printf("Listing tasks with status: %s%n", status);
    }
}
