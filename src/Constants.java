package src;

import java.util.*;

public class Constants {
    private Constants() {

    }

    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String MARK_IN_PROGRESS = "mark-in-progress";
    public static final String MARK_DONE = "mark-done";
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String IN_PROGRESS = "in-progress";
    public static final String DONE = "done";

    public static final String INVALID_COMMAND_FORMAT = "Invalid command format.";
    public static final String INVALID_ARGUMENT = "%s is not a valid argument%n";
    public static final String INVALID_TASK_ID = "Invalid taskId %d%n";
    public static final String IO_EXCEPTION_OCCURRED = "IO Exception Occurred.";
    public static final String TASK_ADDED_SUCCESSFULLY = "Task added successfully (ID: %d)%n";
    public static final String UPDATED_TASK_DESCRIPTION_SUCCESSFULLY = "Successfully updated task description to: \"%s\" for Task ID: %d%n";
    public static final String DELETED_TASK_SUCCESSFULLY = "Task with Task ID: %d deleted successfully%n";
    public static final String UPDATED_TASK_STATUS_SUCCESSFULLY = "Successfully updated task status to: \"%s\" for Task ID: %d%n";
    public static final String LISTING_TASKS_BY_STATUS = "Listing all tasks with status: \"%s\"%n";
    public static final String LISTING_ALL_TASKS = "Listing all tasks";

    public static final List<String> TASK_LIST = List.of(ADD, UPDATE, DELETE, LIST, MARK_IN_PROGRESS, MARK_DONE);
    public static final List<String> ACTIONS_LIST = List.of(MARK_IN_PROGRESS, MARK_DONE);
    public static final List<String> STATUS_LIST = List.of(TODO, IN_PROGRESS, DONE);

    public static final String FILE_NAME = "tasks.json";
}
