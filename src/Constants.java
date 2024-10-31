package src;

import java.util.*;

public class Constants {
    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String MARK_IN_PROGRESS = "mark-in-progress";
    public static final String MARK_DONE = "mark-done";
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String IN_PROGRESS = "in-progress";
    public static final String DONE = "done";
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments provided";
    public static final String INVALID_AN_TASK_ID = "%s is invalid an task id. task id must be a integer%n";
    public static final String INVALID_ARGUMENT = "%s is a invalid argument%n";

    public static final List<String> TASK_LIST = List.of(ADD, UPDATE, DELETE, MARK_IN_PROGRESS, MARK_DONE, LIST);
    public static final List<String> STATUS_LIST = List.of(TODO, IN_PROGRESS, DONE);
    public static final Map<String, Set<Integer>> TASK_ARGUMENTS_LENGTHS_MAP = new HashMap<>() {{
        put(ADD, new HashSet<>(List.of(2)));
        put(UPDATE, new HashSet<>(List.of(3)));
        put(DELETE, new HashSet<>(List.of(2)));
        put(MARK_IN_PROGRESS, new HashSet<>(List.of(2)));
        put(MARK_DONE, new HashSet<>(List.of(2)));
        put(LIST, new HashSet<>(List.of(1, 2)));
    }};

}
