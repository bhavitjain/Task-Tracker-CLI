package src;

public class TaskHandler {
    private TaskHandler() {
    }

    public static void handleChangeStatusTask(String stringTaskId, String task) {
        try {
            int taskId = Integer.parseInt(stringTaskId);
            TaskPerformer.changeTaskStatus(taskId, task);
        } catch (NumberFormatException ignore) {
            System.out.printf(Constants.INVALID_AN_TASK_ID, stringTaskId);
        }
    }

    public static void handleDeleteTask(String stringTaskId) {
        try {
            int taskId = Integer.parseInt(stringTaskId);
            TaskPerformer.deleteTask(taskId);
        } catch (NumberFormatException ignore) {
            System.out.printf(Constants.INVALID_AN_TASK_ID, stringTaskId);
        }
    }

    public static void handleUpdateTask(String stringTaskId, String description) {
        try {
            int taskId = Integer.parseInt(stringTaskId);
            TaskPerformer.updateTask(taskId, description);
        } catch (NumberFormatException ignore) {
            System.out.printf(Constants.INVALID_AN_TASK_ID, stringTaskId);
        }
    }

    public static void handleAddTask(String description) {
        TaskPerformer.addTask(description);
    }

    public static void handleListTask(String[] args) {
        if (args.length == 1) {
            TaskPerformer.listAllTasks();
            return;
        }

        String status = args[1];
        if (!Constants.STATUS_LIST.contains(status)) {
            System.out.println(status + Constants.INVALID_ARGUMENT);
            return;
        }
        TaskPerformer.listTaskByStatus(status);
    }
}
