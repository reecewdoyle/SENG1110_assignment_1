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
    
    // projectName

    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // complete all other methods from here.

    // projectId

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    // projectType

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
    
    // Task 1

    public Task getTask1() {
        return task1;
    }

    public void setTask1(Task task1) {
        this.task1 = task1;
    }

    // Task 2

    public Task getTask2() {
        return task2;
    }

    public void setTask2(Task task2) {
        this.task2 = task2;
    }

    // Task 3

    public Task getTask3() {
        return task3;
    }

    public void setTask3(Task task3) {
        this.task3 = task3;
    }
        
    
}