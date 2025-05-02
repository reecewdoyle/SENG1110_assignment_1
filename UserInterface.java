/**
 * UserInterface class handles all user interactions for the Project Management System.
 * Provides a menu-driven interface for managing projects and tasks.
 */
import java.util.Scanner;

public class UserInterface {

    // -------------------------------------------------------------------------
    // Instance Variables
    // -------------------------------------------------------------------------
    
    private Project project1;
    private Project project2;
    private Project project3;
    private Scanner scannerInput;

    // -------------------------------------------------------------------------
    // Main Method - Entry Point
    // -------------------------------------------------------------------------

    /**
     * Entry point for the program. Creates a UserInterface object and runs the program.
     */
    public static void main(String[] args) {
        new UserInterface().run();
    }

    // -------------------------------------------------------------------------
    // Main Program Loop
    // -------------------------------------------------------------------------

    /**
     * Initializes projects and scanner, loads seed data, and handles user menu input.
     */
    public void run() {
        // Initialize project slots
        project1 = null;
        project2 = null;
        project3 = null;

        scannerInput = new Scanner(System.in);

        // Optional starter data
        seedDummyData();

        int choice;

        // Loop until user selects -1 to exit
        do {
            choice = displayMenu();
            switch (choice) {
                case 1: createProject(); break;
                case 2: removeProject(); break;
                case 3: addTask(); break;
                case 4: markTaskAsCompleted(); break;
                case 5: removeTask(); break;
                case 6: displayProjectDetails(); break;
                case 7: displayCompletedTasks(); break;
                case 8: filterTasksByType(); break;
                case 9: displayProjectSummary(); break;
                case -1:
                    System.out.println("Thank you for using Project Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != -1);
    }

    // -------------------------------------------------------------------------
    // Menu Display Method
    // -------------------------------------------------------------------------

    /**
     * Displays the main menu and returns the user's selected option.
     * @return The menu option selected by the user.
     */
    private int displayMenu() {
        System.out.println("\n========== PROJECT MANAGEMENT SYSTEM ==========");
        System.out.println("1. Create a new project");
        System.out.println("2. Remove a project");
        System.out.println("3. Add a task to a project");
        System.out.println("4. Mark a task as completed");
        System.out.println("5. Remove a task from a project");
        System.out.println("6. Display all project details");
        System.out.println("7. Display completed tasks");
        System.out.println("8. Filter tasks by type");
        System.out.println("9. Display project summary");
        System.out.println("-1 Exit");
        System.out.print("Enter your choice: ");
        return scannerInput.nextInt();
    }

// -------------------------------------------------------------------------
// SEED DATA INITIALISATION
// -------------------------------------------------------------------------

/**
 * Loads dummy data into the system for testing and demonstration purposes.
 * Creates 3 sample projects (Small, Medium, Large), each with preset tasks.
 */
private void seedDummyData() {
  // --- Project 1: Small ---
  Project p1 = new Project();
  p1.setProjectId(101);
  p1.setProjectName("Apollo");
  p1.setProjectType("Small");

  Task t1 = new Task();
  t1.setTaskId(1);
  t1.setDescription("Initial planning");
  t1.setTaskType('A'); // Admin
  t1.setTaskDuration(3);
  t1.setCompleted(true);

  p1.setTask1(t1); // Small project only gets 1 task

  // --- Project 2: Medium ---
  Project p2 = new Project();
  p2.setProjectId(102);
  p2.setProjectName("Hermes");
  p2.setProjectType("Medium");

  Task t2 = new Task();
  t2.setTaskId(2);
  t2.setDescription("Setup network");
  t2.setTaskType('L'); // Logistics
  t2.setTaskDuration(5);
  t2.setCompleted(false);

  Task t3 = new Task();
  t3.setTaskId(3);
  t3.setDescription("Admin briefing");
  t3.setTaskType('A'); // Admin
  t3.setTaskDuration(2);
  t3.setCompleted(true);

  p2.setTask1(t2);
  p2.setTask2(t3);

  // --- Project 3: Large ---
  Project p3 = new Project();
  p3.setProjectId(103);
  p3.setProjectName("Zeus");
  p3.setProjectType("Large");

  Task t4 = new Task();
  t4.setTaskId(4);
  t4.setDescription("Logistics sync");
  t4.setTaskType('L');
  t4.setTaskDuration(6);
  t4.setCompleted(false);

  Task t5 = new Task();
  t5.setTaskId(5);
  t5.setDescription("System config");
  t5.setTaskType('S'); // Support
  t5.setTaskDuration(4);
  t5.setCompleted(false);

  Task t6 = new Task();
  t6.setTaskId(6);
  t6.setDescription("Admin approvals");
  t6.setTaskType('A');
  t6.setTaskDuration(3);
  t6.setCompleted(true);

  p3.setTask1(t4);
  p3.setTask2(t5);
  p3.setTask3(t6);

  // Assign projects to the top-level project slots
  project1 = p1;
  project2 = p2;
  project3 = p3;

  System.out.println("Dummy data loaded.");
}

// -------------------------------------------------------------------------
// CREATE NEW PROJECT
// -------------------------------------------------------------------------

/**
 * Prompts the user to create a new project with a unique ID, name, and type.
 * Validates input and assigns the project to the next available project slot.
 * Allows a maximum of three projects in the system at any time.
 */
private void createProject() {
  // Check if there's room for a new project
  if (project1 == null || project2 == null || project3 == null) {
      Project p = new Project();

      // ----------------- Project ID Input -----------------
      int id = 0;
      boolean validProjectId = false;

      do {
          System.out.print("Enter Project ID (1-999): ");
          if (scannerInput.hasNextInt()) {
              id = scannerInput.nextInt();
              scannerInput.nextLine(); // Consume leftover newline

              if (id > 0 && id <= 999) {
                  // Check ID is not a duplicate
                  if ((project1 != null && project1.getProjectId() == id) ||
                      (project2 != null && project2.getProjectId() == id) ||
                      (project3 != null && project3.getProjectId() == id)) {
                      System.out.println("Project ID already exists.");
                  } else {
                      validProjectId = true;
                  }
              } else {
                  System.out.println("Project ID must be a positive whole number (1–999).");
              }
          } else {
              System.out.println("Invalid input. Please enter a positive whole number (1–999).");
              scannerInput.next(); // Clear invalid token
          }
      } while (!validProjectId);

      p.setProjectId(id);
      System.out.println("Project ID " + p.getProjectId());

      // ----------------- Project Name Input -----------------
      System.out.print("Enter Project Name: ");
      String projectName = scannerInput.nextLine().trim();
      while (projectName.isEmpty()) {
          System.out.println("Project name cannot be empty. Please enter a valid name:");
          projectName = scannerInput.nextLine().trim();
      }
      p.setProjectName(projectName);
      System.out.println("Project Name " + p.getProjectName());

      // ----------------- Project Type Input -----------------
      System.out.print("Enter Project Type (Small, Medium or Large): ");
      String projectType = scannerInput.nextLine().trim();

      // Ensure project type input is not empty before normalising and validating
      while (projectType.isEmpty()) {
        System.out.println("Project type cannot be empty. Please enter Small, Medium, or Large:");
        projectType = scannerInput.nextLine().trim();
      } 

      // Normalize case
      projectType = projectType.toLowerCase();
      projectType = projectType.substring(0, 1).toUpperCase() + projectType.substring(1);

      // Validate type
      while (!projectType.equals("Small") && !projectType.equals("Medium") && !projectType.equals("Large")) {
          System.out.println("Invalid project type. Please enter Small, Medium, or Large:");
          projectType = scannerInput.nextLine().trim();
          projectType = projectType.toLowerCase();
          projectType = projectType.substring(0, 1).toUpperCase() + projectType.substring(1);
      }

      p.setProjectType(projectType);
      System.out.println("Project Type " + p.getProjectType());

      // ----------------- Assign Project -----------------
      if (project1 == null) project1 = p;
      else if (project2 == null) project2 = p;
      else if (project3 == null) project3 = p;
  } else {
      System.out.println("Maximum projects reached!");
  }
}

// -------------------------------------------------------------------------
// REMOVE PROJECT
// -------------------------------------------------------------------------

/**
 * Removes a project from the system based on user-provided ID.
 * Validates user input and only removes if the ID matches an existing project.
 * Entering -1 will return to the main menu without deleting anything.
 */
private void removeProject() {
  // Check if there are any projects to remove
  if (project1 == null && project2 == null && project3 == null) {
      System.out.println("There are no saved Projects to remove.");
  } else {
      int id = 0;
      boolean projectRemoved = false;

      // Loop until a valid project is removed or user exits
      do {
          System.out.print("Enter the Project ID (1-999) to remove (-1 to return to menu): ");

          // Validate input is an integer
          if (scannerInput.hasNextInt()) {
              id = scannerInput.nextInt();
              scannerInput.nextLine(); // Consume leftover newline

              // Allow user to return to menu
              if (id == -1) {
                  System.out.println("Returning to main menu...");
                  return;
              }

              // Handle positive IDs
              if (id > 0) {
                  if (project1 != null && project1.getProjectId() == id) {
                      project1 = null;
                      System.out.println("Project removed.");
                      projectRemoved = true;
                  } else if (project2 != null && project2.getProjectId() == id) {
                      project2 = null;
                      System.out.println("Project removed.");
                      projectRemoved = true;
                  } else if (project3 != null && project3.getProjectId() == id) {
                      project3 = null;
                      System.out.println("Project removed.");
                      projectRemoved = true;
                  } else {
                      System.out.println("Project ID doesn't exist.");
                  }
              } else {
                  // Handle zero or negative input (excluding -1)
                  System.out.println("Project ID must be a positive whole number (1-999).");
              }
          } else {
              // Handle non-integer input
              System.out.println("Invalid input. Please enter a positive whole number (1-999).");
              scannerInput.next(); // Clear invalid token
          }
      } while (!projectRemoved);
  }
}

// -------------------------------------------------------------------------
// ADD TASK TO PROJECT
// -------------------------------------------------------------------------

/**
 * Adds a new task to a selected project.
 * Ensures the project exists, has space for tasks, and all inputs are valid.
 */
private void addTask() {
  if (project1 == null && project2 == null && project3 == null) {
      System.out.println("There are no saved Projects to add a task to.");
      return;
  }

  int id = 0;
  boolean projectSelected = false;
  Project workingProject = null;

  // Prompt user to select a project by ID
  do {
      System.out.print("Enter the Project ID to add a task to: ");

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
          System.out.println("Invalid input. Please enter a positive whole number.");
          scannerInput.next(); // Clear invalid token
      }
  } while (!projectSelected);

  System.out.println("Selected Project: " + workingProject.getProjectName());
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

  // Task ID input and validation
  int taskId = 0;
  boolean validTaskId = false;

  do {
      System.out.print("Enter Task ID (1-9): ");

      if (scannerInput.hasNextInt()) {
          taskId = scannerInput.nextInt();
          scannerInput.nextLine();

          if (taskId > 0 && taskId <= 9) {
              boolean exists = false;

              if (workingProject.getTask1() != null && workingProject.getTask1().getTaskId() == taskId) exists = true;
              if (workingProject.getTask2() != null && workingProject.getTask2().getTaskId() == taskId) exists = true;
              if (workingProject.getTask3() != null && workingProject.getTask3().getTaskId() == taskId) exists = true;

              if (exists) {
                  System.out.println("A Task with that ID already exists in this project.");
              } else {
                  validTaskId = true;
              }
          } else {
              System.out.println("Task ID must be between 1 and 9.");
          }
      } else {
          System.out.println("Invalid input. Please enter a number.");
          scannerInput.next();
      }
  } while (!validTaskId);

  // Task description input
  String description;
  do {
      System.out.print("Enter task description: ");
      description = scannerInput.nextLine().trim();
      if (description.isEmpty()) {
          System.out.println("Description cannot be empty.");
      }
  } while (description.isEmpty());

  // Task type input and validation
  char taskType = ' ';
  boolean validTaskType = false;

  do {
      System.out.print("Enter task type (A = Admin, S = Support, L = Logistics): ");
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
      System.out.print("Enter task duration in hours (positive whole number): ");
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

  // Create and assign task
  Task newTask = new Task();
  newTask.setTaskId(taskId);
  newTask.setDescription(description);
  newTask.setTaskType(taskType);
  newTask.setTaskDuration(duration);
  newTask.setCompleted(false);

  if (workingProject.getTask1() == null) {
      workingProject.setTask1(newTask);
  } else if (workingProject.getTask2() == null) {
      workingProject.setTask2(newTask);
  } else if (workingProject.getTask3() == null) {
      workingProject.setTask3(newTask);
  } else {
      System.out.println("Error: No available task slots in this project.");
  }
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
      System.out.println("There are no saved projects to mark off tasks in.");
      return;
  }

  int id = 0;
  boolean projectSelected = false;
  Project workingProject = null;

  // ------------------ Select Project ------------------
  // Prompt user for a valid project ID
  do {
      System.out.print("Enter the Project ID where the completed task is located: ");

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
  System.out.println("Selected Project: " + workingProject.getProjectName());
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
      System.out.println("This project has no tasks to be marked as completed.");
      return;
  }

  // ------------------ Select Task ------------------
  int taskId = 0;
  boolean taskFound = false;

  do {
      System.out.print("Enter the Task ID to be marked as completed (-1 to return to menu): ");

      if (scannerInput.hasNextInt()) {
          taskId = scannerInput.nextInt();
          scannerInput.nextLine(); // Clear newline

          if (taskId == -1) {
              // User chose to exit
              System.out.println("Returning to main menu...");
              return;
          }

          if (taskId > 0 && taskId <= 9) {
              // Check each task slot for matching ID
              if (workingProject.getTask1() != null && workingProject.getTask1().getTaskId() == taskId) {
                  workingProject.getTask1().setCompleted(true);
                  System.out.println("Task marked as completed.");
                  taskFound = true;
              } else if (workingProject.getTask2() != null && workingProject.getTask2().getTaskId() == taskId) {
                  workingProject.getTask2().setCompleted(true);
                  System.out.println("Task marked as completed.");
                  taskFound = true;
              } else if (workingProject.getTask3() != null && workingProject.getTask3().getTaskId() == taskId) {
                  workingProject.getTask3().setCompleted(true);
                  System.out.println("Task marked as completed.");
                  taskFound = true;
              } else {
                  System.out.println("No task with that ID exists in this project.");
              }
          } else {
              System.out.println("Task ID must be a number between 1 and 9.");
          }
      } else {
          System.out.println("Invalid input. Please enter a number.");
          scannerInput.next(); // Clear invalid input
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
      System.out.println("There are no saved projects to remove tasks from.");
      return;
  }

  int id = 0;
  boolean projectSelected = false;
  Project workingProject = null;

  // ------------------ Select Project ------------------
  do {
      System.out.print("Enter the Project ID where the task to be deleted is located: ");

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
  System.out.println("Selected Project: " + workingProject.getProjectName());
  System.out.println("Project Type: " + workingProject.getProjectType());

  // Check if any tasks exist in the project
  if (workingProject.getTask1() == null &&
      workingProject.getTask2() == null &&
      workingProject.getTask3() == null) {
      System.out.println("This project has no tasks to remove.");
      return;
  }

  int taskId = 0;
  boolean taskRemoved = false;

  // ------------------ Select Task to Remove ------------------
  do {
      System.out.print("Enter Task ID to remove (1-9, or -1 to return to menu): ");

      if (scannerInput.hasNextInt()) {
          taskId = scannerInput.nextInt();
          scannerInput.nextLine(); // Clear newline

          if (taskId == -1) {
              System.out.println("Returning to main menu...");
              return;
          }

          if (taskId > 0 && taskId <= 9) {
              // Check each task slot
              if (workingProject.getTask1() != null && workingProject.getTask1().getTaskId() == taskId) {
                  workingProject.setTask1(null);
                  System.out.println("Task removed.");
                  taskRemoved = true;
              } else if (workingProject.getTask2() != null && workingProject.getTask2().getTaskId() == taskId) {
                  workingProject.setTask2(null);
                  System.out.println("Task removed.");
                  taskRemoved = true;
              } else if (workingProject.getTask3() != null && workingProject.getTask3().getTaskId() == taskId) {
                  workingProject.setTask3(null);
                  System.out.println("Task removed.");
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

// -------------------------------------------------------------------------
// DISPLAY PROJECT DETAILS
// -------------------------------------------------------------------------

/**
 * Displays all saved projects and their associated task details.
 * If no projects are saved, a message is shown instead.
 * For each project, tasks are listed with ID, description, type, duration, and status.
 */
private void displayProjectDetails() {
  // If no projects exist, notify user and exit
  if (project1 == null && project2 == null && project3 == null) {
      System.out.println("There are no saved projects to display.");
      return;
  }

  // ------------------ Project 1 ------------------
  if (project1 != null) {
      System.out.println("---------------------------------------------------");
      System.out.println("Project ID: " + project1.getProjectId());
      System.out.println("Project Name: " + project1.getProjectName());
      System.out.println("Project Type: " + project1.getProjectType());
      System.out.println("Tasks:");

      // Task 1
      if (project1.getTask1() != null) {
          System.out.println("* Task ID: " + project1.getTask1().getTaskId()
              + ", Description: " + project1.getTask1().getDescription()
              + ", Type: " + project1.getTask1().getTaskType()
              + ", Duration: " + project1.getTask1().getTaskDuration());
          System.out.println("  Status: " + (project1.getTask1().getCompleted() ? "Completed" : "Pending"));
      }

      // Task 2
      if (project1.getTask2() != null) {
          System.out.println("* Task ID: " + project1.getTask2().getTaskId()
              + ", Description: " + project1.getTask2().getDescription()
              + ", Type: " + project1.getTask2().getTaskType()
              + ", Duration: " + project1.getTask2().getTaskDuration());
          System.out.println("  Status: " + (project1.getTask2().getCompleted() ? "Completed" : "Pending"));
      }

      // Task 3
      if (project1.getTask3() != null) {
          System.out.println("* Task ID: " + project1.getTask3().getTaskId()
              + ", Description: " + project1.getTask3().getDescription()
              + ", Type: " + project1.getTask3().getTaskType()
              + ", Duration: " + project1.getTask3().getTaskDuration());
          System.out.println("  Status: " + (project1.getTask3().getCompleted() ? "Completed" : "Pending"));
      }

      // No tasks check
      if (project1.getTask1() == null && project1.getTask2() == null && project1.getTask3() == null) {
          System.out.println("No tasks stored for this project.");
      }
  }

  // ------------------ Project 2 ------------------
  if (project2 != null) {
      System.out.println("---------------------------------------------------");
      System.out.println("Project ID: " + project2.getProjectId());
      System.out.println("Project Name: " + project2.getProjectName());
      System.out.println("Project Type: " + project2.getProjectType());
      System.out.println("Tasks:");

      // Task 1
      if (project2.getTask1() != null) {
          System.out.println("* Task ID: " + project2.getTask1().getTaskId()
              + ", Description: " + project2.getTask1().getDescription()
              + ", Type: " + project2.getTask1().getTaskType()
              + ", Duration: " + project2.getTask1().getTaskDuration());
          System.out.println("  Status: " + (project2.getTask1().getCompleted() ? "Completed" : "Pending"));
      }

      // Task 2
      if (project2.getTask2() != null) {
          System.out.println("* Task ID: " + project2.getTask2().getTaskId()
              + ", Description: " + project2.getTask2().getDescription()
              + ", Type: " + project2.getTask2().getTaskType()
              + ", Duration: " + project2.getTask2().getTaskDuration());
          System.out.println("  Status: " + (project2.getTask2().getCompleted() ? "Completed" : "Pending"));
      }

      // Task 3
      if (project2.getTask3() != null) {
          System.out.println("* Task ID: " + project2.getTask3().getTaskId()
              + ", Description: " + project2.getTask3().getDescription()
              + ", Type: " + project2.getTask3().getTaskType()
              + ", Duration: " + project2.getTask3().getTaskDuration());
          System.out.println("  Status: " + (project2.getTask3().getCompleted() ? "Completed" : "Pending"));
      }

      // No tasks check
      if (project2.getTask1() == null && project2.getTask2() == null && project2.getTask3() == null) {
          System.out.println("No tasks stored for this project.");
      }
  }

  // ------------------ Project 3 ------------------
  if (project3 != null) {
      System.out.println("---------------------------------------------------");
      System.out.println("Project ID: " + project3.getProjectId());
      System.out.println("Project Name: " + project3.getProjectName());
      System.out.println("Project Type: " + project3.getProjectType());
      System.out.println("Tasks:");

      // Task 1
      if (project3.getTask1() != null) {
          System.out.println("* Task ID: " + project3.getTask1().getTaskId()
              + ", Description: " + project3.getTask1().getDescription()
              + ", Type: " + project3.getTask1().getTaskType()
              + ", Duration: " + project3.getTask1().getTaskDuration());
          System.out.println("  Status: " + (project3.getTask1().getCompleted() ? "Completed" : "Pending"));
      }

      // Task 2
      if (project3.getTask2() != null) {
          System.out.println("* Task ID: " + project3.getTask2().getTaskId()
              + ", Description: " + project3.getTask2().getDescription()
              + ", Type: " + project3.getTask2().getTaskType()
              + ", Duration: " + project3.getTask2().getTaskDuration());
          System.out.println("  Status: " + (project3.getTask2().getCompleted() ? "Completed" : "Pending"));
      }

      // Task 3
      if (project3.getTask3() != null) {
          System.out.println("* Task ID: " + project3.getTask3().getTaskId()
              + ", Description: " + project3.getTask3().getDescription()
              + ", Type: " + project3.getTask3().getTaskType()
              + ", Duration: " + project3.getTask3().getTaskDuration());
          System.out.println("  Status: " + (project3.getTask3().getCompleted() ? "Completed" : "Pending"));
      }

      // No tasks check
      if (project3.getTask1() == null && project3.getTask2() == null && project3.getTask3() == null) {
          System.out.println("No tasks stored for this project.");
      }
  }

  System.out.println("---------------------------------------------------");
}

// -------------------------------------------------------------------------
// DISPLAY COMPLETED TASKS
// -------------------------------------------------------------------------

/**
 * Displays only completed tasks for a specific project by projectId.
 * Prompts the user to enter a valid project ID, validates it, and checks the selected project
 * for any tasks marked as completed. If completed tasks exist, they are displayed in a formatted
 * list. If none are found, an appropriate message is shown.
 * Entering -1 returns to the main menu without displaying anything.
 */
private void displayCompletedTasks() {
  // Check if there are any saved projects to search through
  if (project1 == null && project2 == null && project3 == null) {
      System.out.println("There are no saved projects to check for completed tasks.");
      return;
  }

  Project selectedProject = null;
  int id = 0;
  boolean projectFound = false;

  // Prompt user until a valid project ID is entered or they choose to return
  do {
      System.out.print("Enter the Project ID to view completed tasks (-1 to return to menu): ");

      if (scannerInput.hasNextInt()) {
          id = scannerInput.nextInt();
          scannerInput.nextLine();

          // Allow user to return to menu
          if (id == -1) {
              System.out.println("Returning to main menu...");
              return;
          }

          // Match input ID to a saved project
          if (project1 != null && project1.getProjectId() == id) {
              selectedProject = project1;
              projectFound = true;
          } else if (project2 != null && project2.getProjectId() == id) {
              selectedProject = project2;
              projectFound = true;
          } else if (project3 != null && project3.getProjectId() == id) {
              selectedProject = project3;
              projectFound = true;
          } else {
              System.out.println("No project with that ID exists.");
          }
      } else {
          System.out.println("Invalid input. Please enter a valid whole number.");
          scannerInput.next(); // Clear invalid input
      }
  } while (!projectFound);

  System.out.println("Completed Tasks in Project ID " + selectedProject.getProjectId() + ":");

  boolean foundTask = false;

  // Check and display completed Task 1 (if exists)
  if (selectedProject.getTask1() != null && selectedProject.getTask1().getCompleted()) {
      System.out.println("* Task ID: " + selectedProject.getTask1().getTaskId()
          + ", Description: " + selectedProject.getTask1().getDescription()
          + ", Type: " + selectedProject.getTask1().getTaskType()
          + ", Duration: " + selectedProject.getTask1().getTaskDuration());
      foundTask = true;
  }

  // Check and display completed Task 2 (if exists)
  if (selectedProject.getTask2() != null && selectedProject.getTask2().getCompleted()) {
      System.out.println("* Task ID: " + selectedProject.getTask2().getTaskId()
          + ", Description: " + selectedProject.getTask2().getDescription()
          + ", Type: " + selectedProject.getTask2().getTaskType()
          + ", Duration: " + selectedProject.getTask2().getTaskDuration());
      foundTask = true;
  }

  // Check and display completed Task 3 (if exists)
  if (selectedProject.getTask3() != null && selectedProject.getTask3().getCompleted()) {
      System.out.println("* Task ID: " + selectedProject.getTask3().getTaskId()
          + ", Description: " + selectedProject.getTask3().getDescription()
          + ", Type: " + selectedProject.getTask3().getTaskType()
          + ", Duration: " + selectedProject.getTask3().getTaskDuration());
      foundTask = true;
  }

  // Display message if no completed tasks found
  if (!foundTask) {
      System.out.println("No completed tasks found in this project.");
  }
}

// -------------------------------------------------------------------------
// FILTER TASKS BY TYPE
// -------------------------------------------------------------------------

/**
 * Prompts the user to enter a task type (A, S, or L),
 * then loops through all saved projects and displays tasks
 * that match the selected type.
 * If no matching tasks are found, a message is displayed.
 */
private void filterTasksByType() {
  if (project1 == null && project2 == null && project3 == null) {
      System.out.println("There are no saved projects to filter tasks from.");
      return;
  }

  // Clear buffer in case of leftover newlines
  scannerInput.nextLine();

  char type = ' ';
  boolean validType = false;

  // Prompt user for task type until valid input is given
  do {
      System.out.print("Enter task type to filter by (A = Admin, S = Support, L = Logistics): ");
      String input = scannerInput.nextLine().trim().toUpperCase();

      if (input.length() == 1) {
          type = input.charAt(0);
          if (type == 'A' || type == 'S' || type == 'L') {
              validType = true;
          } else {
              System.out.println("Invalid type. Please enter A, S, or L.");
          }
      } else {
          System.out.println("Please enter a single character: A, S, or L.");
      }
  } while (!validType);

  boolean foundType = false;

  // Check tasks in project1
  if (project1 != null) {
      if (project1.getTask1() != null && project1.getTask1().getTaskType() == type) {
          System.out.println("From project: " + project1.getProjectName());
          System.out.println("* Task ID: " + project1.getTask1().getTaskId()
              + ", Description: " + project1.getTask1().getDescription()
              + ", Type: " + project1.getTask1().getTaskType()
              + ", Duration: " + project1.getTask1().getTaskDuration());
          System.out.println("  Status: " + (project1.getTask1().getCompleted() ? "Completed" : "Pending"));
          foundType = true;
      }
      if (project1.getTask2() != null && project1.getTask2().getTaskType() == type) {
          System.out.println("From project: " + project1.getProjectName());
          System.out.println("* Task ID: " + project1.getTask2().getTaskId()
              + ", Description: " + project1.getTask2().getDescription()
              + ", Type: " + project1.getTask2().getTaskType()
              + ", Duration: " + project1.getTask2().getTaskDuration());
          System.out.println("  Status: " + (project1.getTask2().getCompleted() ? "Completed" : "Pending"));
          foundType = true;
      }
      if (project1.getTask3() != null && project1.getTask3().getTaskType() == type) {
          System.out.println("From project: " + project1.getProjectName());
          System.out.println("* Task ID: " + project1.getTask3().getTaskId()
              + ", Description: " + project1.getTask3().getDescription()
              + ", Type: " + project1.getTask3().getTaskType()
              + ", Duration: " + project1.getTask3().getTaskDuration());
          System.out.println("  Status: " + (project1.getTask3().getCompleted() ? "Completed" : "Pending"));
          foundType = true;
      }
  }

  // Check tasks in project2
  if (project2 != null) {
      if (project2.getTask1() != null && project2.getTask1().getTaskType() == type) {
          System.out.println("From project: " + project2.getProjectName());
          System.out.println("* Task ID: " + project2.getTask1().getTaskId()
              + ", Description: " + project2.getTask1().getDescription()
              + ", Type: " + project2.getTask1().getTaskType()
              + ", Duration: " + project2.getTask1().getTaskDuration());
          System.out.println("  Status: " + (project2.getTask1().getCompleted() ? "Completed" : "Pending"));
          foundType = true;
      }
      if (project2.getTask2() != null && project2.getTask2().getTaskType() == type) {
          System.out.println("From project: " + project2.getProjectName());
          System.out.println("* Task ID: " + project2.getTask2().getTaskId()
              + ", Description: " + project2.getTask2().getDescription()
              + ", Type: " + project2.getTask2().getTaskType()
              + ", Duration: " + project2.getTask2().getTaskDuration());
          System.out.println("  Status: " + (project2.getTask2().getCompleted() ? "Completed" : "Pending"));
          foundType = true;
      }
      if (project2.getTask3() != null && project2.getTask3().getTaskType() == type) {
          System.out.println("From project: " + project2.getProjectName());
          System.out.println("* Task ID: " + project2.getTask3().getTaskId()
              + ", Description: " + project2.getTask3().getDescription()
              + ", Type: " + project2.getTask3().getTaskType()
              + ", Duration: " + project2.getTask3().getTaskDuration());
          System.out.println("  Status: " + (project2.getTask3().getCompleted() ? "Completed" : "Pending"));
          foundType = true;
      }
  }

  // Check tasks in project3
  if (project3 != null) {
      if (project3.getTask1() != null && project3.getTask1().getTaskType() == type) {
          System.out.println("From project: " + project3.getProjectName());
          System.out.println("* Task ID: " + project3.getTask1().getTaskId()
              + ", Description: " + project3.getTask1().getDescription()
              + ", Type: " + project3.getTask1().getTaskType()
              + ", Duration: " + project3.getTask1().getTaskDuration());
          System.out.println("  Status: " + (project3.getTask1().getCompleted() ? "Completed" : "Pending"));
          foundType = true;
      }
      if (project3.getTask2() != null && project3.getTask2().getTaskType() == type) {
          System.out.println("From project: " + project3.getProjectName());
          System.out.println("* Task ID: " + project3.getTask2().getTaskId()
              + ", Description: " + project3.getTask2().getDescription()
              + ", Type: " + project3.getTask2().getTaskType()
              + ", Duration: " + project3.getTask2().getTaskDuration());
          System.out.println("  Status: " + (project3.getTask2().getCompleted() ? "Completed" : "Pending"));
          foundType = true;
      }
      if (project3.getTask3() != null && project3.getTask3().getTaskType() == type) {
          System.out.println("From project: " + project3.getProjectName());
          System.out.println("* Task ID: " + project3.getTask3().getTaskId()
              + ", Description: " + project3.getTask3().getDescription()
              + ", Type: " + project3.getTask3().getTaskType()
              + ", Duration: " + project3.getTask3().getTaskDuration());
          System.out.println("  Status: " + (project3.getTask3().getCompleted() ? "Completed" : "Pending"));
          foundType = true;
      }
  }

  if (!foundType) {
      System.out.println("No tasks of type " + type + " were found.");
  }
}
    
// -------------------------------------------------------------------------
// DISPLAY PROJECT SUMMARY
// -------------------------------------------------------------------------

/**
 * Displays a summary report of task durations across all saved projects.
 *
 * Report includes:
 * - Average duration of each task type (Admin, Support, Logistics) across all projects.
 * - A breakdown of task durations by individual project.
 *
 * Task types:
 * - A = Admin
 * - S = Support
 * - L = Logistics
 */
private void displayProjectSummary() {
  // Check if any tasks exist across all projects
  if ((project1 == null || (project1.getTask1() == null && project1.getTask2() == null && project1.getTask3() == null)) &&
      (project2 == null || (project2.getTask1() == null && project2.getTask2() == null && project2.getTask3() == null)) &&
      (project3 == null || (project3.getTask1() == null && project3.getTask2() == null && project3.getTask3() == null))) {
      System.out.println("No created tasks to report.");
      return;
  }

  // Totals for all projects combined
  int totalAdmin = 0, countAdmin = 0;
  int totalSupport = 0, countSupport = 0;
  int totalLogistics = 0, countLogistics = 0;

  // Tally durations and counts for project1
  if (project1 != null) {
      Task t1 = project1.getTask1();
      Task t2 = project1.getTask2();
      Task t3 = project1.getTask3();

      if (t1 != null) {
          if (t1.getTaskType() == 'A') { totalAdmin += t1.getTaskDuration(); countAdmin++; }
          if (t1.getTaskType() == 'S') { totalSupport += t1.getTaskDuration(); countSupport++; }
          if (t1.getTaskType() == 'L') { totalLogistics += t1.getTaskDuration(); countLogistics++; }
      }
      if (t2 != null) {
          if (t2.getTaskType() == 'A') { totalAdmin += t2.getTaskDuration(); countAdmin++; }
          if (t2.getTaskType() == 'S') { totalSupport += t2.getTaskDuration(); countSupport++; }
          if (t2.getTaskType() == 'L') { totalLogistics += t2.getTaskDuration(); countLogistics++; }
      }
      if (t3 != null) {
          if (t3.getTaskType() == 'A') { totalAdmin += t3.getTaskDuration(); countAdmin++; }
          if (t3.getTaskType() == 'S') { totalSupport += t3.getTaskDuration(); countSupport++; }
          if (t3.getTaskType() == 'L') { totalLogistics += t3.getTaskDuration(); countLogistics++; }
      }
  }

  // Tally durations and counts for project2
  if (project2 != null) {
      Task t1 = project2.getTask1();
      Task t2 = project2.getTask2();
      Task t3 = project2.getTask3();

      if (t1 != null) {
          if (t1.getTaskType() == 'A') { totalAdmin += t1.getTaskDuration(); countAdmin++; }
          if (t1.getTaskType() == 'S') { totalSupport += t1.getTaskDuration(); countSupport++; }
          if (t1.getTaskType() == 'L') { totalLogistics += t1.getTaskDuration(); countLogistics++; }
      }
      if (t2 != null) {
          if (t2.getTaskType() == 'A') { totalAdmin += t2.getTaskDuration(); countAdmin++; }
          if (t2.getTaskType() == 'S') { totalSupport += t2.getTaskDuration(); countSupport++; }
          if (t2.getTaskType() == 'L') { totalLogistics += t2.getTaskDuration(); countLogistics++; }
      }
      if (t3 != null) {
          if (t3.getTaskType() == 'A') { totalAdmin += t3.getTaskDuration(); countAdmin++; }
          if (t3.getTaskType() == 'S') { totalSupport += t3.getTaskDuration(); countSupport++; }
          if (t3.getTaskType() == 'L') { totalLogistics += t3.getTaskDuration(); countLogistics++; }
      }
  }

  // Tally durations and counts for project3
  if (project3 != null) {
      Task t1 = project3.getTask1();
      Task t2 = project3.getTask2();
      Task t3 = project3.getTask3();

      if (t1 != null) {
          if (t1.getTaskType() == 'A') { totalAdmin += t1.getTaskDuration(); countAdmin++; }
          if (t1.getTaskType() == 'S') { totalSupport += t1.getTaskDuration(); countSupport++; }
          if (t1.getTaskType() == 'L') { totalLogistics += t1.getTaskDuration(); countLogistics++; }
      }
      if (t2 != null) {
          if (t2.getTaskType() == 'A') { totalAdmin += t2.getTaskDuration(); countAdmin++; }
          if (t2.getTaskType() == 'S') { totalSupport += t2.getTaskDuration(); countSupport++; }
          if (t2.getTaskType() == 'L') { totalLogistics += t2.getTaskDuration(); countLogistics++; }
      }
      if (t3 != null) {
          if (t3.getTaskType() == 'A') { totalAdmin += t3.getTaskDuration(); countAdmin++; }
          if (t3.getTaskType() == 'S') { totalSupport += t3.getTaskDuration(); countSupport++; }
          if (t3.getTaskType() == 'L') { totalLogistics += t3.getTaskDuration(); countLogistics++; }
      }
  }

  // Print combined averages across all projects
  System.out.println("------------------------ Average Task Duration ------------------");

  if (countAdmin > 0) {
      System.out.println("* Average task duration of administrative tasks is " + (totalAdmin / countAdmin) + " hours");
  } else {
      System.out.println("* No administrative tasks found.");
  }

  if (countSupport > 0) {
      System.out.println("* Average task duration of support tasks is " + (totalSupport / countSupport) + " hours");
  } else {
      System.out.println("* No support tasks found.");
  }

  if (countLogistics > 0) {
      System.out.println("* Average task duration of logistics tasks is " + (totalLogistics / countLogistics) + " hours");
  } else {
      System.out.println("* No logistics tasks found.");
  }

  // Project-specific breakdown for project1
  if (project1 != null) {
      System.out.println("---------------- Project " + project1.getProjectId() + " ----------------");

      int admin = 0, countA = 0;
      int support = 0, countS = 0;
      int logistics = 0, countL = 0;

      Task t1 = project1.getTask1();
      Task t2 = project1.getTask2();
      Task t3 = project1.getTask3();

      if (t1 != null) {
          if (t1.getTaskType() == 'A') { admin += t1.getTaskDuration(); countA++; }
          if (t1.getTaskType() == 'S') { support += t1.getTaskDuration(); countS++; }
          if (t1.getTaskType() == 'L') { logistics += t1.getTaskDuration(); countL++; }
      }
      if (t2 != null) {
          if (t2.getTaskType() == 'A') { admin += t2.getTaskDuration(); countA++; }
          if (t2.getTaskType() == 'S') { support += t2.getTaskDuration(); countS++; }
          if (t2.getTaskType() == 'L') { logistics += t2.getTaskDuration(); countL++; }
      }
      if (t3 != null) {
          if (t3.getTaskType() == 'A') { admin += t3.getTaskDuration(); countA++; }
          if (t3.getTaskType() == 'S') { support += t3.getTaskDuration(); countS++; }
          if (t3.getTaskType() == 'L') { logistics += t3.getTaskDuration(); countL++; }
      }

      if (countA > 0) {
          System.out.println("* Admin average: " + (admin / countA) + " hours");
      } else {
          System.out.println("* No admin tasks found.");
      }

      if (countS > 0) {
          System.out.println("* Support average: " + (support / countS) + " hours");
      } else {
          System.out.println("* No support tasks found.");
      }

      if (countL > 0) {
          System.out.println("* Logistics average: " + (logistics / countL) + " hours");
      } else {
          System.out.println("* No logistics tasks found.");
      }
  }

  // Project-specific breakdown for project3
  if (project3 != null) {
      System.out.println("---------------- Project " + project3.getProjectId() + " ----------------");

      int admin = 0, countA = 0;
      int support = 0, countS = 0;
      int logistics = 0, countL = 0;

      Task t1 = project3.getTask1();
      Task t2 = project3.getTask2();
      Task t3 = project3.getTask3();

      if (t1 != null) {
          if (t1.getTaskType() == 'A') { admin += t1.getTaskDuration(); countA++; }
          if (t1.getTaskType() == 'S') { support += t1.getTaskDuration(); countS++; }
          if (t1.getTaskType() == 'L') { logistics += t1.getTaskDuration(); countL++; }
      }
      if (t2 != null) {
          if (t2.getTaskType() == 'A') { admin += t2.getTaskDuration(); countA++; }
          if (t2.getTaskType() == 'S') { support += t2.getTaskDuration(); countS++; }
          if (t2.getTaskType() == 'L') { logistics += t2.getTaskDuration(); countL++; }
      }
      if (t3 != null) {
          if (t3.getTaskType() == 'A') { admin += t3.getTaskDuration(); countA++; }
          if (t3.getTaskType() == 'S') { support += t3.getTaskDuration(); countS++; }
          if (t3.getTaskType() == 'L') { logistics += t3.getTaskDuration(); countL++; }
      }

      if (countA > 0) {
          System.out.println("* Admin average: " + (admin / countA) + " hours");
      } else {
          System.out.println("* No admin tasks found.");
      }

      if (countS > 0) {
          System.out.println("* Support average: " + (support / countS) + " hours");
      } else {
          System.out.println("* No support tasks found.");
      }

      if (countL > 0) {
          System.out.println("* Logistics average: " + (logistics / countL) + " hours");
      } else {
          System.out.println("* No logistics tasks found.");
      }
    }
  }
}  