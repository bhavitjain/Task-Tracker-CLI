package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                break;
            }

            String[] argsList = line.split(" ");
            processTaskArguments(argsList);
        }
    }

    private static void processTaskArguments(String[] args) {
        String task = args[0].toLowerCase();

        // check if arg[0] is valid task
        if (!Constants.TASK_LIST.contains(task)) {
            System.out.printf(Constants.INVALID_ARGUMENT, task);
            return;
        }

        // Check if the number of arguments matches the expected arguments length for the specified task
        if (!Constants.TASK_ARGUMENTS_LENGTHS_MAP.get(task).contains(args.length)) {
            System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS);
            return;
        }

        switch (task) {
            case Constants.LIST -> TaskHandler.handleListTask(args);
            case Constants.ADD -> TaskHandler.handleAddTask(args[1]);
            case Constants.UPDATE -> TaskHandler.handleUpdateTask(args[1], args[2]);
            case Constants.DELETE -> TaskHandler.handleDeleteTask(args[1]);
            default -> TaskHandler.handleChangeStatusTask(args[1], task);
        }
    }
}
