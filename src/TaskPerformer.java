package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskPerformer {

    private TaskPerformer() {
    }


    /**
     * Method to check and create a new JSON file if it doesn't exist
     *
     * @throws IOException IOException
     */
    private static void checkAndCreateFile() throws IOException {
        File file = new File(Constants.FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]"); // Initialize with empty JSON array
            }
        }
    }

    /**
     * Method to read tasks from the JSON file
     *
     * @return list of tasks
     * @throws IOException IOException
     */
    private static List<Task> readTasksFromFile() throws IOException {
        List<Task> tasks = new ArrayList<>();
        checkAndCreateFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_NAME))) {
            String line = reader.readLine();
            if (line != null && line.length() > 2) { // Check for non-empty JSON array
                String[] taskStrings = line.substring(1, line.length() - 1).split("},");
                for (String taskString : taskStrings) {
                    taskString = taskString.endsWith("}") ? taskString : taskString + "}";
                    tasks.add(parseTask(taskString));
                }
            }
        }
        return tasks;
    }

    /**
     * Helper method to parse a task from JSON string
     *
     * @param jsonString jsonString
     * @return {@link Task}
     */
    private static Task parseTask(String jsonString) {
        jsonString = jsonString.replaceAll("[{}]", ""); // Remove JSON characters
        String[] fields = jsonString.split(", \"");
        for (int i = 0; i < fields.length; i++)
            fields[i] = fields[i].replaceAll("\"", "");

        int id = Integer.parseInt(fields[0].split(": ")[1]);
        String description = fields[1].split(": ")[1];
        String status = fields[2].split(": ")[1];
        String createdAt = fields[3].split(": ")[1];
        String updateAt = fields[4].split(": ")[1];
        return new Task(id, description, status, createdAt, updateAt);
    }

    /**
     * Method to write tasks to the JSON file
     *
     * @param tasks task list
     * @throws IOException IOException
     */
    private static void writeTasksToFile(List<Task> tasks) throws IOException {
        checkAndCreateFile();

        try (FileWriter writer = new FileWriter(Constants.FILE_NAME)) {
            writer.write("[");
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toJson());
                if (i < tasks.size() - 1) writer.write(", ");
            }
            writer.write("]");
        }
    }

    private static int getNextId(List<Task> tasks) {
        return tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
    }

    /**
     * Adds new task in json and prints its id
     *
     * @param description description
     */
    public static void addTask(String description) {
        try {
            List<Task> tasks = readTasksFromFile();
            int newId = getNextId(tasks);
            String status = Constants.TODO;
            String currentTimestamp = LocalDateTime.now().toString();
            Task newTask = new Task(newId, description, status, currentTimestamp, currentTimestamp);
            tasks.add(newTask);
            writeTasksToFile(tasks);
            System.out.printf(Constants.TASK_ADDED_SUCCESSFULLY, newId);
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }

    private static Integer getIndex(List<Task> tasks, int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                return i;
            }
        }

        return null;
    }

    /**
     * updates the task description of a task with the given task ID.
     *
     * @param taskId      task id
     * @param description description
     */
    public static void updateTaskDescription(int taskId, String description) {
        try {
            List<Task> tasks = readTasksFromFile();
            Integer index = getIndex(tasks, taskId);

            if (index == null) {
                System.out.printf(Constants.INVALID_TASK_ID, taskId);
            } else {
                tasks.get(index).setDescription(description);
                tasks.get(index).setUpdatedAt(LocalDateTime.now().toString());
                writeTasksToFile(tasks);
                System.out.printf(Constants.UPDATED_TASK_DESCRIPTION_SUCCESSFULLY, description, taskId);
            }
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }

    /**
     * deletes task for a given id
     *
     * @param taskId task id
     */
    public static void deleteTask(int taskId) {
        try {
            List<Task> tasks = readTasksFromFile();
            Integer index = getIndex(tasks, taskId);

            if (index == null) {
                System.out.printf(Constants.INVALID_TASK_ID, taskId);
            } else {
                tasks.remove(index.intValue());
                writeTasksToFile(tasks);
                System.out.printf(Constants.DELETED_TASK_SUCCESSFULLY, taskId);
            }
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }

    /**
     * Updates the status of a task with the given task ID.
     *
     * @param taskId task id
     * @param status status
     */
    public static void updateTaskStatus(int taskId, String status) {
        try {
            List<Task> tasks = readTasksFromFile();
            Integer index = getIndex(tasks, taskId);

            if (index == null) {
                System.out.printf(Constants.INVALID_TASK_ID, taskId);
            } else {
                tasks.get(index).setStatus(status);
                tasks.get(index).setUpdatedAt(LocalDateTime.now().toString());
                writeTasksToFile(tasks);
                System.out.printf(Constants.UPDATED_TASK_STATUS_SUCCESSFULLY, status, taskId);
            }
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }

    /**
     * list all task from json file
     */
    public static void listAllTasks() {
        try {
            System.out.println(Constants.LISTING_ALL_TASKS);
            List<Task> tasks = readTasksFromFile();
            tasks.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }

    /**
     * lists all task with the given status.
     *
     * @param status status
     */
    public static void listTaskByStatus(String status) {
        try {
            System.out.printf(Constants.LISTING_TASKS_BY_STATUS, status);
            List<Task> tasks = readTasksFromFile();
            tasks.stream().filter(task -> Objects.equals(task.getStatus(), status)).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }
}
