# SENG1110_assignment_1
Repo for assignment one so I can work in VSCode and document my progress with Github Commits. 

## Program Structure Requirements
    - [x] Implement a menu-driven console application (TIO)
    - [x] Users can create and manage up to 3 projects.
    - [x] Number of tasks allowed per project depends on the project type configured in the Project Class. 
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

#### Adding a Task to a Project:
    - [x] Tasks can be added to a specific project using projectId.
    - [x] unique taskID's within project
    - [x] Duplicate taskID's throw error
    - [x] taskType ('A', 'S', 'L') is case-sensitive
    - [x] 'a' is normalised to 'A' and treadted the same as 'A'

#### Removing a Project:
    - [x] User can delete a project by entering it's projectId
    - [x] All tasks associated are removed
    - [x] Invalid ID throws error.

#### Marking a Task as Completed:
    - [x] User can mark a project's task as completed by entering a valid projectId or taskID. 
    - [x] Invalid ID throws error.

#### Removing a Task:
    - [x] User can delete task from project by entering a valid projectId or taskId.
    - [x] Invalid ID throws error. 

### Project Display
#### Displaying all project details
    - [x] projectID
    - [x] name
    - [x] type
    - [x] number of task
    - [x] associated details


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
    - [x] list only completed tasks within a projectId

Output:
```
Completed Tasks in Project ID 125:
* Task ID: 78, Description: task1 desc, Type: L, Duration: 20
```

#### Filter Tasks by Type
    - [x] Display filtered by A, S, or L.
    - [x] case sesnitive

Output:
```
Project ID: 125, Project Name: Test, Project Type: Medium, Num of administrative tasks: 1
Tasks:
* Task ID: 50, Description: task2 desc, Type: A, Duration: 40,
Status: Pending ----------------------------------------------------------------------- ...
```

### Project Task Duration Summary
#### Average Task Type Durations
    - [x] Calculate and display average time based on task type (A, S, L)

#### Average Task Durations Breakdown by Project
    - [x] Show seperate averages of task duration for different task types in each created project. 

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
    - [x] Must throw error message when there has been no tasks created.

### Capacity Constraints
    - [x] Max tasks per project type:
        - Small = 1
        - Medium = 2
        - Large = 3

### Menu System:
#### Options:
    - [x] Create Projects
    - [x] Remove Projects
    - [x] Manage Tasks
    - [x] View Tasks
    - [x] View Project Task Duration Summary
    - [x] Exit

#### Input Handling
    - [x] All string/char inputs are notmalised to standard case ("sMaLl" becomes "Small")
    - [x] Duplicate project/task ID's are rejected with error message
    - [x] Non-integer inputs for ID's, unrecognised project/task types, and unrecognised menu options trigger re-prompting
    - [x] Menu re-displays after each action until the user exits.
    


---------------------------------------------------------------------------------
## 📝 Assignment 1 Feedback – SENG1110 (Semester 1, 2025)

**Student Name**: Reece Doyle  
**Marker**: Baraa  
**Total Mark**: **80 / 100**

---

### 📦 Program Correctness (75 Marks)

#### ✅ Task Class (10/10)
| Criteria             | Comments                                                   | Marks |
|----------------------|------------------------------------------------------------|-------|
| Attributes           | All required private variables present                     | 3/3   |
| Methods              | Constructor initialises all fields; all getters/setters correct | 4/4   |
| Design Compliance    | Full encapsulation; taskType normalized to uppercase       | 3/3   |

#### ⚠️ Project Class (12/15)
| Criteria             | Comments                                                   | Marks |
|----------------------|------------------------------------------------------------|-------|
| Attributes           | All defined as private                                     | 3/3   |
| Methods              | `addTask`, `removeTask`, `markTaskAsCompleted` should be moved from `UserInterface` to `Project` | 3/6   |
| Design Compliance    | Capacity limits enforced without arrays; task handling correct | 6/6   |

#### ⚠️ UserInterface Class (16/20)
| Criteria             | Comments                                                   | Marks |
|----------------------|------------------------------------------------------------|-------|
| Attributes           | All required attributes (`project1-3`, `scanner`) present  | 2/2   |
| Menu System          | All menu options function as expected                      | 5/5   |
| Input Validation     | Duplicate IDs, invalid input, re-prompts handled correctly | 5/5   |
| Design Compliance    | Should not access `Task` directly (e.g., `getTask1()...`)  | 4/8   |

---

### ⚙️ Core Functionality (35 Marks)

| Feature                        | Comments                                          | Marks |
|--------------------------------|--------------------------------------------------|-------|
| Add/Remove Projects            | Max 3 projects; unique IDs; removal cleans tasks | 7/7   |
| Add/Remove Tasks               | Capacity rules followed; unique per-project IDs  | 6/6   |
| Mark Tasks Completed           | Updates status; handles invalid IDs              | 3/3   |
| Display Projects               | Shows all details with correct formatting        | 5/5   |
| Filter Tasks by Type           | Case-insensitive, project headers shown          | 6/6   |
| 🔻 Duration Summary            | Incomplete or not working correctly              | 4/8   |

---

### 📄 PDF Report (15 Marks)

| Criteria                        | Comments                                          | Marks |
|--------------------------------|--------------------------------------------------|-------|
| Content Completeness           | Time logs/screenshots lacking detail             | 4/8   |
| Class Relationships            | Project–Task (composition) fine; UI–Task access incorrect | 2/7   |

---

### ✅ Code Quality & Comments (5 Marks)

| Criteria             | Comments                          | Marks |
|----------------------|-----------------------------------|-------|
| Readability          | Clear variable/method names, UI prompts | 3/3   |
| Documentation        | Inline comments explain logic     | 2/2   |

---

### ❌ Deductions

| Reason                          | Description                    | Penalty |
|---------------------------------|--------------------------------|---------|
| Disallowed Topics               | Arrays, exceptions used        | -20     |
| Compilation/Runtime Errors      | N/A                            | -       |
| Incorrect Filenames             | N/A                            | -       |
| Late Submission                 | N/A                            | -       |

---

### 🧠 Marker Summary

- Your logic and structure are solid, and the project mostly meets expectations.
- Main issue: task-handling logic (add/remove/complete) should be **in the `Project` class**, not `UserInterface`.
- There is **direct access to `Task` objects** from the UI, which breaks encapsulation.
- `displayProjectSummary()` is incomplete or not functioning as expected.
- Report content was light on screenshots and incorrectly described class relationships.

---

## 🔧 TODO – Post-Feedback Fixes (May 2025)

- [ ] Move `addTask`, `removeTask`, and `markTaskAsCompleted` methods into `Project.java`
- [ ] Refactor `UserInterface.java` to **delegate task management to `Project`**
- [ ] Fix `displayProjectSummary()` to:
  - [ ] Include all three projects
  - [ ] Display correct averages per task type
  - [ ] Ensure consistent formatting
- [ ] Confirm no direct access to `Task` objects from UI
- [ ] Commit changes and link to lecturer via Canvas comment
