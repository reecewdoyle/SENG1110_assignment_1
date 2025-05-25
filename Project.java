import java.util.Scanner;

/**
 * Project class represents a project containing multiple tasks
 * Manages tasks based on project type constraints
 */
public class Project {

    // Unique identifier for the project
    private int projectId;

    // Name of the project
    private String projectName;

    // Type of project: Small, Medium, or Large
    private String projectType;

    // Up to 3 tasks associated with this project
    private Task task1;
    private Task task2;
    private Task task3;

    /**
     * Gets the project ID.
     * @return projectId as int
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the project ID.
     * @param projectId int
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the project name.
     * @return projectName as String
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the project name.
     * @param projectName String
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Gets the project type.
     * @return projectType as String
     */
    public String getProjectType() {
        return projectType;
    }

    /**
     * Sets the project type.
     * @param projectType String (Small, Medium, Large)
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    /**
     * Gets Task 1.
     * @return task1 as Task
     */
    public Task getTask1() {
        return task1;
    }

    /**
     * Sets Task 1.
     * @param task1 Task
     */
    public void setTask1(Task task1) {
        this.task1 = task1;
    }

    /**
     * Gets Task 2.
     * @return task2 as Task
     */
    public Task getTask2() {
        return task2;
    }

    /**
     * Sets Task 2.
     * @param task2 Task
     */
    public void setTask2(Task task2) {
        this.task2 = task2;
    }

    /**
     * Gets Task 3.
     * @return task3 as Task
     */
    public Task getTask3() {
        return task3;
    }

    /**
     * Sets Task 3.
     * @param task3 Task
     */
    public void setTask3(Task task3) {
        this.task3 = task3;
    }

    /**
     * Add task data to memory location.
     * @param scannerInput
     */
    public void addTaskToProject(Scanner scannerInput) {

    // Task description input
    String description;
    do {
        System.out.print("\nEnter task description: ");
        description = scannerInput.nextLine().trim();
        if (description.isEmpty()) {
            System.out.println("Description cannot be empty.");
        }
    } while (description.isEmpty());

    // Task type input and validation
    char taskType = ' ';
    boolean validTaskType = false;

    do {
        System.out.print("\nEnter task type (A = Admin, S = Support, L = Logistics): ");
        String input = scannerInput.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Task type cannot be empty. Please enter A, S, or L.");
            continue;
        }

        input = input.toUpperCase();

        if (input.length() == 1) {
            taskType = input.charAt(0);
            if (taskType == 'A' || taskType == 'S' || taskType == 'L') {
                validTaskType = true;
            } else {
                System.out.println("Invalid task type. Please enter A, S, or L.");
            }
        } else {
            System.out.println("Please enter a single letter: A, S, or L.");
        }
    } while (!validTaskType);

    // Task duration input
    int duration = 0;
    boolean validDuration = false;

    do {
        System.out.print("\nEnter task duration in hours (positive whole number): ");
        if (scannerInput.hasNextInt()) {
            duration = scannerInput.nextInt();
            scannerInput.nextLine();

            if (duration > 0) {
                validDuration = true;
            } else {
                System.out.println("Duration must be greater than 0.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scannerInput.next();
        }
    } while (!validDuration);


    }

    // -------------------------------------------------------------------------
    // MARK TASK AS COMPLETED
    // -------------------------------------------------------------------------

    /**
     * Marks a task as completed within a selected project.
     * Prompts the user to select a project by ID, then a task within that project.
     * Validates all input to ensure only valid IDs are accepted.
     * User can enter -1 at the task ID prompt to return to the main menu.
     */
    private void markTaskAsCompleted() {
    // Check if there are any projects to work with
    if (project1 == null && project2 == null && project3 == null) {
        System.out.println("\nThere are no saved projects to mark off tasks in.");
        return;
    }

    int id = 0;
    boolean projectSelected = false;
    Project workingProject = null;

    // ------------------ Select Project ------------------
    // Prompt user for a valid project ID
    do {
        System.out.print("\nEnter the Project ID where the completed task is located: ");

        if (scannerInput.hasNextInt()) {
            id = scannerInput.nextInt();
            scannerInput.nextLine(); // Clear newline

            if (id > 0) {
                // Check each project slot
                if (project1 != null && project1.getProjectId() == id) {
                    workingProject = project1;
                    projectSelected = true;
                } else if (project2 != null && project2.getProjectId() == id) {
                    workingProject = project2;
                    projectSelected = true;
                } else if (project3 != null && project3.getProjectId() == id) {
                    workingProject = project3;
                    projectSelected = true;
                } else {
                    System.out.println("No project with that ID exists.");
                }
            } else {
                System.out.println("Project ID must be a positive whole number.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scannerInput.next(); // Clear invalid input
        }
    } while (!projectSelected);

    // ------------------ Display Project Info ------------------
    System.out.println("\nSelected Project: " + workingProject.getProjectName());
    System.out.println("Project Type: " + workingProject.getProjectType());

    switch (workingProject.getProjectType()) {
        case "Small":
            System.out.println("This project allows only 1 task.");
            break;
        case "Medium":
            System.out.println("This project allows up to 2 tasks.");
            break;
        case "Large":
            System.out.println("This project allows up to 3 tasks.");
            break;
    }

    // ------------------ Check for Tasks ------------------
    if (workingProject.getTask1() == null &&
        workingProject.getTask2() == null &&
        workingProject.getTask3() == null) {
        System.out.println("\nThis project has no tasks to be marked as completed.");
        return;
    }

    // ------------------ Select Task ------------------
    int taskId = 0;
    boolean taskFound = false;

    do {
        System.out.print("\nEnter the Task ID to be marked as completed (-1 to return to menu): ");

        if (scannerInput.hasNextInt()) {
            taskId = scannerInput.nextInt();
            scannerInput.nextLine(); // Clear newline

            if (taskId == -1) {
                // User chose to exit
                System.out.println("\nReturning to main menu...");
                return;
            }

            if (taskId > 0 && taskId <= 9) {
                // Check each task slot for matching ID
                if (workingProject.getTask1() != null && workingProject.getTask1().getTaskId() == taskId) {
                    workingProject.getTask1().setCompleted(true);
                    System.out.println("\nTask marked as completed.");
                    taskFound = true;
                } else if (workingProject.getTask2() != null && workingProject.getTask2().getTaskId() == taskId) {
                    workingProject.getTask2().setCompleted(true);
                    System.out.println("\nTask marked as completed.");
                    taskFound = true;
                } else if (workingProject.getTask3() != null && workingProject.getTask3().getTaskId() == taskId) {
                    workingProject.getTask3().setCompleted(true);
                    System.out.println("\nTask marked as completed.");
                    taskFound = true;
                } else {
                    System.out.println("No task with that ID exists in this project.");
                }
            } else {
                System.out.println("Task ID must be a number between 1 and 9.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scannerInput.nextLine(); // Clear invalid input
        }
    } while (!taskFound);
    }

    // -------------------------------------------------------------------------
    // REMOVE TASK
    // -------------------------------------------------------------------------

    /**
     * Removes a task from a selected project based on task ID.
     * Prompts the user to choose a project and task to delete.
     * Validates all user inputs and ensures the task exists before removing it.
     * User can enter -1 at the Task ID prompt to return to the menu.
     */
    private void removeTask() {
    // Check if there are any projects to work with
    if (project1 == null && project2 == null && project3 == null) {
        System.out.println("\nThere are no saved projects to remove tasks from.");
        return;
    }

    int id = 0;
    boolean projectSelected = false;
    Project workingProject = null;

    // ------------------ Select Project ------------------
    do {
        System.out.print("\nEnter the Project ID where the task to be deleted is located: ");

        if (scannerInput.hasNextInt()) {
            id = scannerInput.nextInt();
            scannerInput.nextLine();

            if (id > 0) {
                if (project1 != null && project1.getProjectId() == id) {
                    workingProject = project1;
                    projectSelected = true;
                } else if (project2 != null && project2.getProjectId() == id) {
                    workingProject = project2;
                    projectSelected = true;
                } else if (project3 != null && project3.getProjectId() == id) {
                    workingProject = project3;
                    projectSelected = true;
                } else {
                    System.out.println("No project with that ID exists.");
                }
            } else {
                System.out.println("Project ID must be a positive whole number.");
            }
        } else {
            System.out.println("Invalid input. Please enter a whole positive number.");
            scannerInput.next(); // Clear invalid input
        }
    } while (!projectSelected);

    // ------------------ Display Project Info ------------------
    System.out.println("\nSelected Project: " + workingProject.getProjectName());
    System.out.println("Project Type: " + workingProject.getProjectType());

    // Check if any tasks exist in the project
    if (workingProject.getTask1() == null &&
        workingProject.getTask2() == null &&
        workingProject.getTask3() == null) {
        System.out.println("\nThis project has no tasks to remove.");
        return;
    }

    int taskId = 0;
    boolean taskRemoved = false;

    // ------------------ Select Task to Remove ------------------
    do {
        System.out.print("\nEnter Task ID to remove (1-9, or -1 to return to menu): ");

        if (scannerInput.hasNextInt()) {
            taskId = scannerInput.nextInt();
            scannerInput.nextLine(); // Clear newline

            if (taskId == -1) {
                System.out.println("\nReturning to main menu...");
                return;
            }

            if (taskId > 0 && taskId <= 9) {
                // Check each task slot
                if (workingProject.getTask1() != null && workingProject.getTask1().getTaskId() == taskId) {
                    workingProject.setTask1(null);
                    System.out.println("\nTask removed.");
                    taskRemoved = true;
                } else if (workingProject.getTask2() != null && workingProject.getTask2().getTaskId() == taskId) {
                    workingProject.setTask2(null);
                    System.out.println("\nTask removed.");
                    taskRemoved = true;
                } else if (workingProject.getTask3() != null && workingProject.getTask3().getTaskId() == taskId) {
                    workingProject.setTask3(null);
                    System.out.println("\nTask removed.");
                    taskRemoved = true;
                } else {
                    System.out.println("No task with that ID exists.");
                }
            } else {
                System.out.println("Task ID must be a number between 1 and 9.");
            }
        } else {
            System.out.println("Invalid input. Please enter a whole positive number.");
            scannerInput.next(); // Clear invalid input
        }
    } while (!taskRemoved);
    }

}