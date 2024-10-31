package src;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskPerformer {

    private TaskPerformer() {
    }


    // Method to check and create a new JSON file if it doesn't exist
    private static void checkAndCreateFile() throws IOException {
        File file = new File(Constants.FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]"); // Initialize with empty JSON array
            }
        }
    }

    // Method to read tasks from the JSON file
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

    // Helper method to parse a task from JSON string
    private static Task parseTask(String jsonString) {
        jsonString = jsonString.replaceAll("[{}\"]", ""); // Remove JSON characters
        String[] fields = jsonString.split(",");
        int id = Integer.parseInt(fields[0].split(": ")[1]);
        String description = fields[1].split(": ")[1];
        String status = fields[2].split(": ")[1];
        String createdAt = fields[3].split(": ")[1];
        String updateAt = fields[4].split(": ")[1];
        return new Task(id, description, status, createdAt, updateAt);
    }

    // Method to write tasks to the JSON file
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
        return tasks.stream().mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }

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
            }
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }

    public static void deleteTask(int taskId) {
        try {
            List<Task> tasks = readTasksFromFile();
            List<Task> updatedTasks = tasks.stream().filter(task -> task.getId() != taskId).toList();
            writeTasksToFile(updatedTasks);
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }

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
            }
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }

    public static void listAllTasks() {
        try {
            List<Task> tasks = readTasksFromFile();
            tasks.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }

    public static void listTaskByStatus(String status) {
        try {
            List<Task> tasks = readTasksFromFile();
            tasks.stream().filter(task -> Objects.equals(task.getStatus(), status)).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(Constants.IO_EXCEPTION_OCCURRED);
        }
    }
}
