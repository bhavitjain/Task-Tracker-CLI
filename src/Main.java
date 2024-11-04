package src;

import java.util.Objects;

import static src.Constants.ADD;
import static src.Constants.DELETE;
import static src.Constants.INVALID_COMMAND_FORMAT;
import static src.Constants.LIST;
import static src.Constants.TASK_LIST;
import static src.Constants.UPDATE;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0 || !TASK_LIST.contains(args[0])) {
            System.out.println(INVALID_COMMAND_FORMAT);
        } else if (Objects.equals(args[0], LIST)) {
            TaskProcessor.handleListTask(args);
        } else if (Objects.equals(args[0], ADD)) {
            TaskProcessor.handleAddTask(args);
        } else if (Objects.equals(args[0], UPDATE)) {
            TaskProcessor.handleUpdateTask(args);
        } else if (Objects.equals(args[0], DELETE)) {
            TaskProcessor.handleDeleteTask(args);
        } else {
            TaskProcessor.handleActionTask(args);
        }
    }
}
