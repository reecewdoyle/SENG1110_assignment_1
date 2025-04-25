/**
 * Task class represents a single task within a project
 * Contains attributes for task identification, description, completion status, type, and duration
 */
public class Task {
    // Attributes
    private int taskId;
    private String description;
    private boolean completed;
    private char taskType;
    private int taskDuration;
    
    
    // Getters and Setters

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    // complete all other methods from here.
}