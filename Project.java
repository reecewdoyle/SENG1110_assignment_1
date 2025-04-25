/**
 * Project class represents a project containing multiple tasks
 * Manages tasks based on project type constraints
 */
public class Project {
    // Attributes
    private int projectId;
    private String projectName;
    private Task task1;
    private Task task2;
    private Task task3;
    private String projectType;  // "Small", "Medium", or "Large"
    
    
    // Getters and Setters
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // complete all other methods from here.
    
}