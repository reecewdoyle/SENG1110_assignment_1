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
      seedDummyData();

      int choice;
      do {
        choice = displayMenu();
        switch (choice) {
          case 1: createProject(); break;
          case 2: removeProject(); break;
          case 3: addTask(); break;
          case 4: markTaskAsCompleted(); break;
          case 5: removeTask(); break;
          case 6:
            if (project1 != null || project2 != null || project3 != null) {
              if (project1 != null) displayProjectDetails(project1);
              if (project2 != null) displayProjectDetails(project2);
              if (project3 != null) displayProjectDetails(project3);
            } else {
              System.out.println("There are no saved projects to display.");
            }
              break; 
        
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
          System.out.print("Enter Project ID (1-999): ");
          if (scannerInput.hasNextInt()) {
            id = scannerInput.nextInt();
            scannerInput.nextLine();

            if (id > 0 && id <= 999) {
              if (project1 != null && project1.getProjectId() == id
                ||project2 != null && project2.getProjectId() == id
                ||project3 != null && project3.getProjectId() == id) {

                  System.out.println("Project ID already exists.");
                } else {
                  validProjectId = true; 
                }
            } else {
              System.out.println("Project ID must be a positive whole number (1-999).");
              
            }
          } else {
            System.out.println("Invalid input. Please enter a positive whole number (1-999).");
            scannerInput.next();
          }
        } while (!validProjectId);


        p.setProjectId(id);
        System.out.println("Project ID " + p.getProjectId());
  
        System.out.print("Enter Project Name: ");
        String projectName = scannerInput.nextLine().trim();

        while (projectName.isEmpty()) {
          System.out.println("Project name cannot be empty. Please enter a valid name:");
          projectName = scannerInput.nextLine().trim();
        }
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

// ------------------------------------------------------------------------------ //
        // SEED DATA//
// ------------------------------------------------------------------------------ //
            private void seedDummyData() {
              Project p1 = new Project();
              p1.setProjectId(101);
              p1.setProjectName("Alpha");
              p1.setProjectType("Small");

              Task t1 = new Task();
              t1.setTaskId(1);
              t1.setDescription("Setup environment");
              t1.setTaskType('A');
              t1.setTaskDuration(5);
              t1.setCompleted(true);

              p1.setTask1(t1);

              Project p2 = new Project();
              p2.setProjectId(202);
              p2.setProjectName("Beta");
              p2.setProjectType("Medium");

              Task t2 = new Task();
              t2.setTaskId(2);
              t2.setDescription("Write documentation");
              t2.setTaskType('S');
              t2.setTaskDuration(3);
              t2.setCompleted(false);

              Task t3 = new Task();
              t3.setTaskId(3);
              t3.setDescription("Prepare training session");
              t3.setTaskType('L');
              t3.setTaskDuration(4);
              t3.setCompleted(true);

              p2.setTask1(t2);
              p2.setTask2(t3);

              Project p3 = new Project();
              p3.setProjectId(303);
              p3.setProjectName("Gamma");
              p3.setProjectType("Large");

              Task t4 = new Task();
              t4.setTaskId(4);
              t4.setDescription("Logistics planning");
              t4.setTaskType('L');
              t4.setTaskDuration(6);
              t4.setCompleted(false);

              Task t5 = new Task();
              t5.setTaskId(5);
              t5.setDescription("Admin onboarding");
              t5.setTaskType('A');
              t5.setTaskDuration(2);
              t5.setCompleted(false);

              p3.setTask1(t4);
              p3.setTask2(t5);

              project1 = p1;
              project2 = p2;
              project3 = p3;

              System.out.println("Dummy data loaded.");
            }








// ------------------------------------------------------------------------------ //
        // SEED DATA//
// ------------------------------------------------------------------------------ //






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
              System.out.println("Project ID must be a positive whole number.");
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
        
        
        String description = "";
        do {
            System.out.print("Enter task description: ");
            description = scannerInput.nextLine().trim();
        
            if (description.isEmpty()) {
                System.out.println("Description cannot be empty. Please enter something meaningful.");
            }
        } while (description.isEmpty());
        
        char taskType = ' ';
        boolean validTaskType = false;

        do {
          System.out.print("Enter task type (A = Admin, S = Support, L = Logistics): ");
          String input = scannerInput.nextLine().trim().toUpperCase();

          if (input.length() == 1) {
            taskType = input.charAt(0);

            if (taskType == 'A' || taskType == 'S' || taskType == 'L') {
              validTaskType = true;
            } else {
              System.out.println("Invalid task type. Please enter A, S, or L.");
            }
          } else {
            System.out.println("Please enter a single character: A, S, or L.");
          }
        } while (!validTaskType);

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
              System.out.println("Duration must be a positive number greater than 0.");
            }
          } else {
            System.out.println("Invalid input. Please enter a whole number.");
            scannerInput.next();
          }
        } while (!validDuration);

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
    }

    private void markTaskAsCompleted() {
      if (project1 == null && project2 == null && project3 == null) {
        System.out.println("There is no saved projects to mark off tasks in.");
      } else {
        
        int id = 0;
        boolean projectSelected = false;
        Project workingProject = null;        

        do {
          System.out.println("Enter the Project ID where the completed task is located.");
          
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
              System.out.println("Project ID must be a positive whole number.");
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
        
        if (workingProject.getTask1() == null &&
            workingProject.getTask2() == null &&
            workingProject.getTask3() == null) {

            System.out.println("This project has no tasks to be marked as completed.");
        } else {
          
          int taskId = 0;
          boolean taskFound = false;

          do {
            System.out.println("Enter the Task ID to be marked as completed: ");

            if (scannerInput.hasNextInt()){
              taskId = scannerInput.nextInt();
              scannerInput.nextLine();

              if (taskId > 0 && taskId <= 9) {
                if (workingProject.getTask1() != null && workingProject.getTask1().getTaskId() == taskId) {
                    workingProject.getTask1().setCompleted(true);
                    System.out.println("Task marked as completed");
                    taskFound = true;
                } else if (workingProject.getTask2() != null && workingProject.getTask2().getTaskId() == taskId) {
                    workingProject.getTask2().setCompleted(true);
                    System.out.println("Task marked as completed");
                    taskFound = true;
                } else if (workingProject.getTask3() != null && workingProject.getTask3().getTaskId() == taskId) {
                  workingProject.getTask3().setCompleted(true);
                  System.out.println("Task marked as completed");
                  taskFound = true;
                } else {
                  System.out.println("No task with that ID exists in this project.");
                }

              } else {
                System.out.println("Task ID must be a positive number.");
              }
            } else {
              System.out.println("Invalid input. Please input a whole number between 1-9.");
              scannerInput.next();
            }
          } while (!taskFound);
        }
      }
    }

    private void removeTask() {
      if (project1 == null && project2 == null && project3 == null) {
        System.out.println("There is no saved projects to remove tasks from.");
      } else {
        
        int id = 0;
        boolean projectSelected = false;
        Project workingProject = null;        

        do {
          System.out.println("Enter the Project ID where the task to be deleted is located.");
          
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
              System.out.println("Project ID must be a positive whole number.");
            }
          } else {
            System.out.println("Invalid input. Please enter a whole positive number.");
            scannerInput.next();
          }
        } while (!projectSelected);

        System.out.println("Selected Project: " + workingProject.getProjectName());
        System.out.println("Project Type: " + workingProject.getProjectType());

        if (workingProject.getTask1() == null &&
            workingProject.getTask2() == null &&
            workingProject.getTask3() == null) {
            System.out.println("This project has no tasks to remove.");
        } else {

          int taskId = 0;
          boolean taskRemoved = false;

          do {

            System.out.println("Enter Task ID (1-9): ");
            if (scannerInput.hasNextInt()) {

              taskId = scannerInput.nextInt();
              scannerInput.nextLine();

              if (taskId > 0 && taskId <= 9) {
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
                }
              } else {
                System.out.println("No task with that ID exists.");
              }
            } else {
              System.out.println("Invalid input. Please enter a whole positive number.");
            scannerInput.next();
            } 
          } while (!taskRemoved);
        }
      }
    }

    private void displayProjectDetails(Project p) {
      System.out.println("---------------------------------------------------");
      System.out.println("Project ID: " + p.getProjectId());
      System.out.println("Project Name: " + p.getProjectName());
      System.out.println("Project Type: " + p.getProjectType());

      // Count how many tasks are not null.
      int taskCount = 0;
      if (p.getTask1() != null) taskCount++;
      if (p.getTask2() != null) taskCount++;
      if (p.getTask3() != null) taskCount++;
      System.out.println("Number of Tasks: " + taskCount);
      System.out.println("Tasks:");

      if (taskCount == 0) {
        System.out.println("No tasks stored for this project.");
      } else {
        if (p.getTask1() != null) displayTaskDetails(p.getTask1());
        if (p.getTask2() != null) displayTaskDetails(p.getTask2());
        if (p.getTask3() != null) displayTaskDetails(p.getTask3());
      }
      System.out.println("---------------------------------------------------");
    }
    
    private void displayTaskDetails(Task t) {
      if (t != null) {
          System.out.println("* Task ID: " + t.getTaskId() +
                             ", Description: " + t.getDescription() +
                             ", Type: " + t.getTaskType() +
                             ", Duration: " + t.getTaskDuration());
          if (t.getCompleted()) {
              System.out.println("  Status: Completed");
          } else {
              System.out.println("  Status: Pending");
          }
      }
  }

  private void displayCompletedTasks() {
    if (project1 == null && project2 == null && project3 == null) {
        System.out.println("There are no saved projects to check for completed tasks.");
    } else {
        boolean foundTask = false;

        if (project1 != null) {
            if (project1.getTask1() != null && project1.getTask1().getCompleted()) {
                displayTaskDetails(project1.getTask1());
                foundTask = true;
            }
            if (project1.getTask2() != null && project1.getTask2().getCompleted()) {
                displayTaskDetails(project1.getTask2());
                foundTask = true;
            }
            if (project1.getTask3() != null && project1.getTask3().getCompleted()) {
                displayTaskDetails(project1.getTask3());
                foundTask = true;
            }
        }

        if (project2 != null) {
            if (project2.getTask1() != null && project2.getTask1().getCompleted()) {
                displayTaskDetails(project2.getTask1());
                foundTask = true;
            }
            if (project2.getTask2() != null && project2.getTask2().getCompleted()) {
                displayTaskDetails(project2.getTask2());
                foundTask = true;
            }
            if (project2.getTask3() != null && project2.getTask3().getCompleted()) {
                displayTaskDetails(project2.getTask3());
                foundTask = true;
            }
        }

        if (project3 != null) {
            if (project3.getTask1() != null && project3.getTask1().getCompleted()) {
                displayTaskDetails(project3.getTask1());
                foundTask = true;
            }
            if (project3.getTask2() != null && project3.getTask2().getCompleted()) {
                displayTaskDetails(project3.getTask2());
                foundTask = true;
            }
            if (project3.getTask3() != null && project3.getTask3().getCompleted()) {
                displayTaskDetails(project3.getTask3());
                foundTask = true;
            }
        }

        if (!foundTask) {
            System.out.println("No completed tasks found in any project.");
        }
    }
}

    private void filterTasksByType() {}

    private void displayProjectSummary() {}
    
}