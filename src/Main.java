package src;

import java.util.List;
import java.util.Scanner;

public class Main {
    static List<String> taskList = List.of(Constants.ADD, Constants.UPDATE, Constants.DELETE, Constants.MARK_IN_PROGRESS, Constants.MARK_DONE, Constants.LIST);
    static List<String> statusList = List.of(Constants.TODO, Constants.IN_PROGRESS, Constants.DONE);


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
        if (args.length <= 1 || args.length > 3) {
            System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS);
            return;
        }

        String task = args[0].toLowerCase();

        if (!taskList.contains(task)) {
            System.out.println(task + Constants.INVALID_ARGUMENT);
            return;
        }

        try {
            switch (task) {
                case Constants.LIST -> {
                    if (args.length != 2) {
                        System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS);
                        return;
                    }

                    String status = args[1];
                    if (!statusList.contains(status)) {
                        System.out.println(status + Constants.INVALID_ARGUMENT);
                        return;
                    }
                    listTaskByStatus(status);
                }
                case Constants.ADD -> {
                    if (args.length != 2) {
                        System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS);
                        return;
                    }
                    String description = args[1];
                    addTask(description);
                }
                case Constants.UPDATE -> {
                    if (args.length != 3) {
                        System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS);
                        return;
                    }
                    int taskId = Integer.parseInt(args[1]);
                    String description = args[2];
                    updateTask(taskId, description);
                }
                case Constants.DELETE -> {
                    if (args.length != 2) {
                        System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS);
                        return;
                    }
                    int taskId = Integer.parseInt(args[1]);
                    deleteTask(taskId);
                }
                default -> {
                    if (args.length != 2) {
                        System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS);
                        return;
                    }
                    int taskId = Integer.parseInt(args[1]);
                    changeTaskStatus(taskId, task);
                }
            }
        } catch (NumberFormatException ignore) {
            System.out.println(args[1] + Constants.INVALID_AN_TASK_ID);
        }
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
