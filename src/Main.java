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
            performOperation(argsList);
        }
    }

    private static void performOperation(String[] args) {
        String task = args[0].toLowerCase();

        if (!Constants.TASK_LIST.contains(task)) {
            System.out.println(task + Constants.INVALID_ARGUMENT);
            return;
        }

        if (!Constants.TASK_ARGUMENTS_LIST_MAP.get(task).contains(args.length)) {
            System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS);
            return;
        }

        try {
            switch (task) {
                case Constants.LIST -> {
                    if (args.length == 1){
                        listAllTasks();
                        return;
                    }

                    String status = args[1];
                    if (!Constants.STATUS_LIST.contains(status)) {
                        System.out.println(status + Constants.INVALID_ARGUMENT);
                        return;
                    }
                    listTaskByStatus(status);
                }
                case Constants.ADD -> {
                    String description = args[1];
                    addTask(description);
                }
                case Constants.UPDATE -> {
                    int taskId = Integer.parseInt(args[1]);
                    String description = args[2];
                    updateTask(taskId, description);
                }
                case Constants.DELETE -> {
                    int taskId = Integer.parseInt(args[1]);
                    deleteTask(taskId);
                }
                default -> {
                    int taskId = Integer.parseInt(args[1]);
                    changeTaskStatus(taskId, task);
                }
            }
        } catch (NumberFormatException ignore) {
            System.out.println(args[1] + Constants.INVALID_AN_TASK_ID);
        }
    }

    private static void listAllTasks() {
    }


    private static void changeTaskStatus(int taskId, String task) {
    }

    private static void deleteTask(int taskId) {
    }

    private static void addTask(String description) {
    }

    private static void updateTask(int taskId, String description) {
    }

    private static void listTaskByStatus(String status) {
    }
}
