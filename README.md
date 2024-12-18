# Task-Tracker-CLI

A simple task tracker application built using Java. This application allows you to manage tasks by adding, updating, deleting, listing, and changing their statuses. Tasks are stored in a JSON file in the current directory, ensuring persistence between application runs.

## Features

- **Add Task**: Add a new task with a description.
- **Update Task**: Update the description of an existing task.
- **Delete Task**: Delete a task by its unique ID.
- **Change Task Status**: Mark tasks as "In Progress" or "Done".
- **List Tasks**: List all tasks or filter tasks by their status.

## Requirements

- **Java 11 or higher**

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/bhavitjain/Task-Tracker-CLI
cd Task-Tracker-CLI
```

### Compile the Project
In your terminal, navigate to the project directory and run:

```bash
javac src/*.java
```
This will compile all .java files in the src directory.

### Running the Project
To start the project, run the following command:

```bash
java src.Main <command>
```

### Usage
The application accepts various commands for task management. Here’s how to use each command:

1. Add a Task
    ```
    add "Task description"
    ```

2. Update a Task
    ```
    update <task_id> "Updated description"
    ```

3. Delete a Task
    ```
    delete <task_id>
    ```
   
4. Change Task Status
    ```
    mark-in-progress <task_id>
    mark-done <task_id>
    ```

5. List All Tasks
    ```
    list
    ```

6. List Tasks by Status
    ```
    list <status>
    ```

### File Structure
- ```src/Main.java```: Entry point of the application.
- ```src/TaskPerformer.java```: Processes the command line arguments
- ```src/TaskProcessor.java```: Handles the main task-related logic.
- ```src/Task.java```: Defines the Task object structure.
- ```src/Constants.java```: Holds application constants.
- ```tasks.json```: The JSON file storing all tasks.

### Example Commands
```
add "Finish homework"
update 1 "Complete math homework"
delete 2
mark-in-progress 1
mark-done 1
list
list done
```

### Notes
The tasks.json file will be automatically created in the current directory if it does not already exist.
Each task has a unique ID that persists across application runs.

Sample solution for the [task-tracker-cli](https://roadmap.sh/projects/task-tracker) challenge from [roadmap.sh](https://roadmap.sh/).