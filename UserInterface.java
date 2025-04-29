/**
 * UserInterface class handles all user interactions for the Project Management System
 * Provides a menu-driven interface for managing projects and tasks
 */
import java.util.Scanner;

public class UserInterface {
    // Attributes
    private Project project1;
    private Project project2;
    private Project project3;
    private Scanner scannerInput;
    

    public static void main(String[] args) {
        new UserInterface().run();
    }
    
    public void run() {
      project1 = null;
      project2 = null;
      project3 = null;

      scannerInput = new Scanner(System.in);

      // Test Code
      // Project p = new Project();
      // System.out.print("Enter Project ID: ");
      // int id = scannerInput.nextInt();
      // p.setProjectId(id);
      // System.out.println("Project ID " + p.getProjectId());

      // System.out.print("Enter Project Name: ");
      // String projectName = scannerInput.nextLine();
      // p.setProjectName(projectName);
      // System.out.println("Project Name " + p.getProjectName());

      // System.out.print("Enter Project Type: ");
      // String projectType = scannerInput.nextLine();
      // p.setProjectType(projectType);
      // System.out.println("Project Type " + p.getProjectType());

      int choice;
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
          case -1: System.out.println("Thank you for using Project Managment System. Goodbye!"); break;
          default: System.out.println("Invalid choice. Please try again.");
        }
      } while (choice != -1);

      
		// Menu logic here
    }
    private int displayMenu() {
      System.out.println("\n========== PROJECT MANAGEMENT SYSTEM ==========");
      System.out.println("1. Create a new project");
      System.out.println("2. Remove a project");
      System.out.println("3. Add a task to a project");
      System.out.println("4. Mark a task as completed");
      System.out.println("5. Remove a task from a project");
      System.out.println("6. Display all projects details");
      System.out.println("7. Display completed tasks");
      System.out.println("8. Filter tasks by type");
      System.out.println("9. Display project summary");
      System.out.println("-1 Exit");
      System.out.println("Enter your choice: ");
      return scannerInput.nextInt();


    }
    
    private void createProject() {
      if (project1 == null || project2 == null || project3 == null) {
        Project p = new Project();
        // Collect projectId, name, type from user
        // Flag-controlled loop to ensure user enters a valid integer for Project ID
        int id = 0;
        boolean validProjectId = false;
        
        do {
          System.out.print("Enter Project ID: ");
          if (scannerInput.hasNextInt()) {
            id = scannerInput.nextInt();
            scannerInput.nextLine();

            if (id > 0 && id <= 9) {
              if (project1 != null && project1.getProjectId() == id
                ||project2 != null && project2.getProjectId() == id
                ||project3 != null && project3.getProjectId() == id) {

                  System.out.println("Project ID already exists.");
                } else {
                  validProjectId = true; 
                }
            } else {
              System.out.println("Project ID must be a single-digit number between 1 and 9.");
            }
          } else {
            System.out.println("Invalid input. Please enter a positive whole number.");
            scannerInput.nextLine();
          }
        } while (!validProjectId);


        p.setProjectId(id);
        System.out.println("Project ID " + p.getProjectId());
  
        System.out.print("Enter Project Name: ");
        String projectName = scannerInput.nextLine();
        p.setProjectName(projectName);
        System.out.println("Project Name " + p.getProjectName());
  
        System.out.print("Enter Project Type (Small, Medium or Large): ");
        String projectType = scannerInput.nextLine();
        
        // Normalise projectType (e.g., "basic" -> "Basic")
        projectType = projectType.toLowerCase();
        projectType = projectType.substring(0, 1).toUpperCase() + projectType.substring(1);
        
        // Validating that projectType can only be "Small", "Medium", or "Large"
        while (!projectType.equals("Small") && !projectType.equals("Medium") && !projectType.equals("Large")) {
          System.out.println("Invalid project type. Please enter Small, Medium, or Large:");
          projectType = scannerInput.nextLine();
          projectType = projectType.toLowerCase();
          projectType = projectType.substring(0, 1).toUpperCase() + projectType.substring(1);
        }


        p.setProjectType(projectType);
        System.out.println("Project Type " + p.getProjectType());


        if (project1 == null) project1 = p;
        else if (project2 == null) project2 = p;
        else if (project3 == null) project3 = p;
      } else {
        System.out.println("Maximum projects reached!");
      }
    }

    private void removeProject() {
      if (project1 == null && project2 == null && project3 == null) {
        System.out.println("There are no saved Projects to remove.");
      } else {

        int id = 0;
        boolean projectRemoved = false;

        do {
          System.out.print("Enter the Project ID to remove: ");

          if (scannerInput.hasNextInt()) {
            id = scannerInput.nextInt();
            scannerInput.nextLine();
          } else {
            System.out.println("Invalid input. Please enter a positive whole number.");
            scannerInput.next();
          }  

            if (id > 0) {
              if (project1 != null && project1.getProjectId() == id) {
                project1 = null;
                System.out.println("Project removed");
                projectRemoved = true;
              } else if (project2 != null && project2.getProjectId() == id) {
                project2 = null;
                System.out.println("Project removed");
                projectRemoved = true;
              } else if (project3 != null && project3.getProjectId() == id) {
                project3 = null;
                System.out.println("Project removed");
                projectRemoved = true;
            } else {
              System.out.println("Project Id doesn't exist.");
            }
          }
        } while (!projectRemoved);
      }
    }

    private void addTask() {
      if (project1 == null && project2 == null && project3 == null) {
        System.out.println("There are no saved Projects to add a task to.");
      } else {

        int id = 0;
        boolean projectSelected = false;
        Project workingProject = null;


        do {
          System.out.print("Enter the Project ID to add a task to: ");

          if (scannerInput.hasNextInt()) {
            id = scannerInput.nextInt();
            scannerInput.nextLine();

            if (id > 0) {
              if (project1 != null && project1.getProjectId() == id) {
                workingProject = project1;
                System.out.println("Project " + id + " selected");
                projectSelected = true;
              } else if (project2 != null && project2.getProjectId() == id) {
                workingProject = project2;
                System.out.println("Project " + id + " selected");
                projectSelected = true;
              } else if (project3 != null && project3.getProjectId() == id) {
                workingProject = project3;
                System.out.println("Project " + id + " selected");
                projectSelected = true;
              } else {
                System.out.println("No project with that ID exists.");
              }
            } else {
              System.out.println("Project ID must be a positive number.");
            }
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

        int taskId = 0;
        boolean validTaskId = false;

        do {

          System.out.println("Enter Task ID (1-9): ");
          if (scannerInput.hasNextInt()) {

            taskId = scannerInput.nextInt();
            scannerInput.nextLine();

            if (taskId > 0 && taskId <= 9) {
              if (workingProject.getTask1() == null &&
                  workingProject.getTask2() == null &&
                  workingProject.getTask3() == null) {

                  validTaskId = true;
              } else {
                // Check for duplicate Task ID
                if ((workingProject.getTask1() != null && workingProject.getTask1().getTaskId() == taskId) ||
                    (workingProject.getTask2() != null && workingProject.getTask2().getTaskId() == taskId) ||
                    (workingProject.getTask3() != null && workingProject.getTask3().getTaskId() == taskId)) {
                    
                    System.out.println("A Task with that ID already exists in this project. Please choose another.");  
                  } else {
                    validTaskId = true;
                  }
              }
            } else {
              System.out.println("Task ID must be a single-digit number between 1 and 9.");
            }
          } else {
            System.out.println("Invalid input. Please enter a whole positive number.");
            scannerInput.next();
          }

        } while (!validTaskId);      
        
    }
}

      
    
    

    private void markTaskAsCompleted() {}

    private void removeTask() {}

    private void displayProjectDetails() {}

    private void displayCompletedTasks() {}

    private void filterTasksByType() {}

    private void displayProjectSummary() {}
    
}