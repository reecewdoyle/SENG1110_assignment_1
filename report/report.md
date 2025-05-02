# Project Management System – SENG1110 Assignment 1 Report

**Student Name:** Reece Doyle  
**Student Number:** c3033554  
**Course:** SENG1110 – Object Oriented Programming  
**Assignment:** Programming Assignment 1 - Semester 1, 2025  
**Due Date:** 2 May

---

## Design and Planning

After completing the **HowToStartAssign1.pdf** tutorial, it was pretty clear what was required of this TIO. Build a method for each of the cases in the Menu switch statement. 

I created a GitHub Repo to allow for an iterative desgin approach that could be rolled back as required, but also so I could track error handling through commits.  

---

## Development Process

### Setup
Once the Git was setup, Project and Task classes were complete, I put the blank methods in the run() of the UserInterface class and worked on them in order.

```java
private void createProject() {}
private void removeProject() {}
private void addTask() {}
// etc.....
```
### Task Tracking
To track progress quickly, I created a README with all contraints and requirements of the assignment with boxes I could check off. 

```markdown
#### Removing a Project:
    - [x] User can delete a project by entering it's projectId
    - [ ] All tasks associated are removed
```

### Testing/Debugging
I created Seed data method that would be poplulated the program with dummy data at startup, and a manual testing sequence that revealed every possible error message. This allowed me quickly debug without having to populate the program from scratch every iteration. I commited after every feature or fix.

```java
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
}
```

---

## Time Tracking

_Provide an honest overview of how long you spent on various parts of the assignment – e.g., planning, writing code, testing, documentation, screenshots, debugging._

---

## Errors and Debugging

_Reflect on the kinds of bugs you encountered. Did they come from poor design decisions? Typos? Logic errors? Which ones were hardest to find?_

---

## Object-Oriented Class Relationships

_Explain the structure between Task, Project, and UserInterface. Talk about how UserInterface manages the program, how Project holds Tasks, and how these classes interact without using advanced OOP features._

---

## Program Screenshots

_Include images showing key program features. You can add each image below a heading such as:_

### Creating a Project  
_(Insert screenshot and brief caption)_

### Adding and Completing Tasks  
_(Insert screenshot and caption)_

### Filtering by Task Type  
_(Insert screenshot and caption)_

### Project Summary Report  
_(Insert screenshot and caption)_

---

## Use of Disallowed Topics

_If you didn’t use anything outside Weeks 1–7, just say so clearly:_

> No disallowed topics were used in this program. All implementation was completed using techniques from Weeks 1–7 only.

---

Let me know if you want a skeleton with placeholders for each screenshot too.