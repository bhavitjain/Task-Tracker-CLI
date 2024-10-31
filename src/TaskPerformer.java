package src;

public class TaskPerformer {
    private TaskPerformer() {
    }

    public static void listAllTasks() {
        System.out.println("Listing all tasks");
    }

    public static void changeTaskStatus(int taskId, String task) {
        if (task.equals(Constants.MARK_IN_PROGRESS)){
            System.out.printf("marking task %d in progress%n", taskId);
        } else {
            System.out.printf("marking task %d done%n", taskId);
        }
    }

    public static void deleteTask(int taskId) {
        System.out.printf("Deleting task with ID %d%n", taskId);
    }

    public static void addTask(String description) {
        System.out.printf("Adding task with description: %s%n", description);
    }

    public static void updateTask(int taskId, String description) {
        System.out.printf("Updating task %d with new description: %s%n", taskId, description);
    }

    public static void listTaskByStatus(String status) {
        System.out.printf("Listing tasks with status: %s%n", status);
    }
}
