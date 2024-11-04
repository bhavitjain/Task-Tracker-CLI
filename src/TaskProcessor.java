package src;

import static src.Constants.INVALID_ARGUMENT;
import static src.Constants.INVALID_COMMAND_FORMAT;

public class TaskProcessor {
    private TaskProcessor() {
    }

    /**
     * Handles the addition of a new task based on the provided input line.
     *
     * @param args the command to add a task
     */
    public static void handleAddTask(String[] args) {
        if (args.length == 2) {
            TaskPerformer.addTask(args[1]);
        } else {
            System.out.println(INVALID_COMMAND_FORMAT);
        }

    }

    /**
     * Handles updating a task based on the provided input line.
     *
     * @param  args the command to update task
     */
    public static void handleUpdateTask(String[] args) {
        try {
            if (args.length == 3) {
                int taskId = Integer.parseInt(args[1]);
                TaskPerformer.updateTaskDescription(taskId, args[2]);
            } else {
                System.out.println(INVALID_COMMAND_FORMAT);
            }
        } catch (NumberFormatException e) {
            System.out.printf(INVALID_ARGUMENT, args[1]);
        }
    }

    /**
     * Handles the deletion of a task based on the provided input line.
     *
     * @param args the command for deleting a task
     */
    public static void handleDeleteTask(String[] args) {
        try {
            if (args.length == 2) {
                int taskId = Integer.parseInt(args[1]);
                TaskPerformer.deleteTask(taskId);
            } else {
                System.out.println(INVALID_COMMAND_FORMAT);
            }
        } catch (NumberFormatException e) {
            System.out.printf(INVALID_ARGUMENT, args[1]);
        }
    }

    /**
     * Handles the processing of the "list" or "list status" command based on the provided input line.
     * Invokes the appropriate method from TaskPerformer to list all tasks or tasks with a specific status.
     *
     * @param args args the command and optional status
     */
    public static void handleListTask(String[] args) {
        if (args.length == 1) {
            TaskPerformer.listAllTasks();
        } else if (args.length == 2) {
            if (!Constants.STATUS_LIST.contains(args[1])) {
                System.out.printf(INVALID_ARGUMENT, args[1]);
                return;
            }

            TaskPerformer.listTaskByStatus(args[1]);
        } else {
            System.out.println(INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Handles the action task based on the provided line input.
     *
     * @param args the action and task ID
     */
    public static void handleActionTask(String[] args) {
        try {
            if (args.length == 2) {
                int taskId = Integer.parseInt(args[1]);
                String action = args[0];

                if (!Constants.ACTIONS_LIST.contains(action)) {
                    System.out.printf(INVALID_ARGUMENT, action);
                    return;
                }

                if (action.equals(Constants.MARK_IN_PROGRESS)) {
                    TaskPerformer.updateTaskStatus(taskId, Constants.IN_PROGRESS);
                    return;
                }

                TaskPerformer.updateTaskStatus(taskId, Constants.DONE);
            } else {
                System.out.println(INVALID_COMMAND_FORMAT);
            }
        } catch (NumberFormatException e) {
            System.out.printf(INVALID_ARGUMENT, args[1]);
        }
    }
}
