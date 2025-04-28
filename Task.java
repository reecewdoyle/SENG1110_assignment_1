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
    
    // description

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    // complete all other methods from here.

    // taskId

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    // completed

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // taskType

    public char getTaskType() {
        return taskType;
    }

    public void setTaskType(char taskType) {
        this.taskType = taskType;
    }

    // taskDuration

    public int getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    } 
}