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
      scannerInput = new Scanner(System.in);
      Project p = new Project();
      System.out.print("Enter Project ID: ");
      int id = scannerInput.nextInt();
      p.setProjectId(id);
      System.out.println("Project ID " + p.getProjectId());
      
		// Menu logic here
    }
    
}