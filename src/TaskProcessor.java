package src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskProcessor {
    private TaskProcessor() {
    }

    /**
     * Processes the input line based on the command type:
     * - If the line starts with 'add', calls 'handleAddTask' method.
     * - If the line starts with 'update', calls 'handleUpdateTask' method.
     * - If the line starts with 'delete', calls 'handleDeleteTask' method.
     * - If the line starts with 'list', calls 'handleListTask' method.
     * - Otherwise, calls 'handleActionTask' method.
     *
     * @param line input line
     */
    public static void processInputs(String line) {
        if (line.startsWith(Constants.ADD)) {
            handleAddTask(line);
        } else if (line.startsWith(Constants.UPDATE)) {
            handleUpdateTask(line);
        } else if (line.startsWith(Constants.DELETE)) {
            handleDeleteTask(line);
        } else if (line.startsWith(Constants.LIST)) {
            handleListTask(line);
        } else {
            handleActionTask(line);
        }
    }

    /**
     * Handles the addition of a new task based on the provided input line.
     *
     * @param line the input line containing the command to add a task
     */
    private static void handleAddTask(String line) {
        String addPattern = "^add \"([^\"]+)\"$";
        Matcher addPatternMatcher = Pattern.compile(addPattern).matcher(line);

        if (addPatternMatcher.matches()) {
            String description = addPatternMatcher.group(1);
            TaskPerformer.addTask(description);
        } else {
            System.out.println(Constants.INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Handles updating a task based on the provided input line.
     *
     * @param line input line containing task update information
     */
    private static void handleUpdateTask(String line) {
        String updatePattern = "^update (\\d+) \"([^\"]+)\"$";
        Matcher updatePatternMatcher = Pattern.compile(updatePattern).matcher(line);

        if (updatePatternMatcher.matches()) {
            int taskId = Integer.parseInt(updatePatternMatcher.group(1));
            String description = updatePatternMatcher.group(2);
            TaskPerformer.updateTaskDescription(taskId, description);
        } else {
            System.out.println(Constants.INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Handles the deletion of a task based on the provided input line.
     *
     * @param line the input line containing the command for deleting a task
     */
    private static void handleDeleteTask(String line) {
        String deletePattern = "^delete (\\d+)$";
        Matcher deletePatternMatcher = Pattern.compile(deletePattern).matcher(line);

        if (deletePatternMatcher.matches()) {
            int taskId = Integer.parseInt(deletePatternMatcher.group(1));
            TaskPerformer.deleteTask(taskId);
        } else {
            System.out.println(Constants.INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Handles the processing of the "list" or "list status" command based on the provided input line.
     * Invokes the appropriate method from TaskPerformer to list all tasks or tasks with a specific status.
     *
     * @param line the input line containing the command and optional status
     */
    private static void handleListTask(String line) {
        String listPattern = "list";
        String listByStatusPattern = "^list (\\S+)$";

        Matcher listPatternMatcher = Pattern.compile(listPattern).matcher(line);
        Matcher listByStatusPatternMatcher = Pattern.compile(listByStatusPattern).matcher(line);

        if (listPatternMatcher.matches()) {
            TaskPerformer.listAllTasks();
        } else if (listByStatusPatternMatcher.matches()) {
            String status = listByStatusPatternMatcher.group(1);

            if (!Constants.STATUS_LIST.contains(status)) {
                System.out.printf(Constants.INVALID_ARGUMENT, status);
                return;
            }

            TaskPerformer.listTaskByStatus(status);
        } else {
            System.out.println(Constants.INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Handles the action task based on the provided line input.
     *
     * @param line the input line containing the action and task ID
     */
    private static void handleActionTask(String line) {
        String actionPattern = "^(\\S+) (\\d+)$";

        Matcher actionPatternMatcher = Pattern.compile(actionPattern).matcher(line);

        if (actionPatternMatcher.matches()) {
            String action = actionPatternMatcher.group(1);
            int taskId = Integer.parseInt(actionPatternMatcher.group(2));

            if (!Constants.ACTIONS_LIST.contains(action)) {
                System.out.printf(Constants.INVALID_ARGUMENT, action);
                return;
            }

            if (action.equals(Constants.MARK_IN_PROGRESS))
                TaskPerformer.updateTaskStatus(taskId, Constants.IN_PROGRESS);
            else
                TaskPerformer.updateTaskStatus(taskId, Constants.DONE);
        } else {
            System.out.println(Constants.INVALID_COMMAND_FORMAT);
        }
    }
}
