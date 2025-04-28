# SENG1110_assignment_1
Repo for assignment one so I can work in VSCode and document my progress with Github Commits. 

## Program Structure Requirements
    - [ ] Implement a menu-driven console application (TIO)
    - [ ] Users can create and manage up to 3 projects.
    - [ ] Number of tasks allowed per project depends on the project type configured in the Project Class. 
    - [x] Must contain 3 classes.

### Task Class
#### Attributes:
    - [x] taskID (int) - unique identifyer for the task
    - [x] description (String) - description of task
    - [x] completed (boolean) - indicates completion
    - [x] taskType (char) - Represents task nature 
        - 'A' (Administrative), 'L'(Logistics), 'S' (Support)
    - [x] taskDuration (int) - Est or actual time spent in hours

### Project Class
#### Attributes:
    - [x] projectId (int) - unique identifyer for the task
    - [x] projectName (String) - Name of the project
    - [x] task1, task2, task3 (3 Task objects, initialised null)
    - [x] projectType (String) - Controls the max number of tasks allowed:
        - Small = 1 Task
        - Medium = 2 Tasks
        - Large = 3 Tasks

### UserInterface Class
#### Implements the menu-driven system for user interaction. All I/O must be done exclusively within this class. 
#### Attributes:
    - [x] project1, project2, project3 (Up to three seperate Project Objects, intially null)
    - [x] scannerInput (to handle input from the user)

## Functionality Requirements
### Project and Task Managment

#### Creating a Project:
    - [x] User can create up to 3 projects with unique project ids
    - [x] No Duplicate ID's allowed
    - [x] Duplicate ID triggers error message
    - [x] projectType is case-sensitive 
    - [x] "sMall", "SMALL" are normalised to "Small" and treated the same as "Small"
#### Bonus Features (Optional, if I have time)
    - [ ] Display existing project IDs when duplicate is entered
    - [ ] Enforce 3-digit Project ID format


    

#### Adding a Task to a Project:
    - [ ] Tasks can be added to a specific project using projectId.
    - [ ] unique taskID's within project
    - [ ] Duplicate taskID's throw error
    - [ ] taskType ('A', 'S', 'L') is case-sensitive
    - [ ] 'a' is normalised to 'A' and treadted the same as 'A'

#### Removing a Project:
    - [ ] User can delete a project by entering it's projectId
    - [ ] All tasks associated are removed
    - [ ] Invalid ID throws error.

#### Marking a Task as Completed:
    - [ ] User can mark a project's task as completed by entering a valid projectId or taskID. 
    - [ ] Invalid ID throws error.

#### Removing a Task:
    - [ ] User can delete task from project by entering a valid projectId or taskId.
    - [ ] Invalid ID throws error. 

### Project Display
#### Displaying all project details
    - [ ] projectID
    - [ ] name
    - [ ] type
    - [ ] number of task
    - [ ] associated details


Output:

```
Project ID: 125, Project Name: Test, Project Type: Medium, Num :2
Tasks:
* Task ID: 78, Description: task1 desc, Type: L, Duration:
Status: Completed
* Task ID: 50, Description: task2 desc, Type: A, Duration:
of tasks
20,
40,
Status: Pending ----------------------------------------------------------------------- Project ID: 124, Project Name: Test 2, Project Type: Small, Num of
tasks: 1 Tasks:
* ...
```

#### Project Completed Tasks
    - [ ] list only completed tasks within a projectId

Output:
```
Completed Tasks in Project ID 125:
* Task ID: 78, Description: task1 desc, Type: L, Duration: 20
```

#### Filter Tasks by Type
    - [ ] Display filtered by A, S, or L.
    - [ ] case sesnitive

Output:
```
Project ID: 125, Project Name: Test, Project Type: Medium, Num of administrative tasks: 1
Tasks:
* Task ID: 50, Description: task2 desc, Type: A, Duration: 40,
Status: Pending ----------------------------------------------------------------------- ...
```

### Project Task Duration Summary
#### Average Task Type Durations
    - [ ] Calculate and display average time based on task type (A, S, L)

#### Average Task Durations Breakdown by Project
    - [ ] Show seperate averages of task duration for different task types in each created project. 

Output:

```
------------------------Average Task Duration ------------------ Average task duration of all task types across all projects:
* Average task duration of administrative tasks is x hours
* ...
------------------------Breakdown by Project ------------------ Project ID 124:
* Average task duration of administrative tasks is x hours
* ... Project ID 125:
* ...
```

#### Handling No Data:
    - [ ] Must throw error message when there has been no tasks created.

### Capacity Constraints
    - [ ] Max tasks per project type:
        - Small = 1
        - Medium = 2
        - Large = 3

### Menu System:
#### Options:
    - [ ] Create Projects
    - [ ] Remove Projects
    - [ ] Manage Tasks
    - [ ] View Tasks
    - [ ] View Project Task Duration Summary
    - [ ] Exit

#### Input Handling
    - [ ] All string/char inputs are notmalised to standard case ("sMaLl" becomes "Small")
    - [ ] Duplicate project/task ID's are rejected with error message
    - [ ] Non-integer inputs for ID's, unrecognised project/task types, and unrecognised menu options trigger re-prompting
    - [ ] Menu re-displays after each action until the user exits.
    