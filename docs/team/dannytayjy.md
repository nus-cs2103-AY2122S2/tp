---
layout: page
title: Tay Jun Yang's Project Portfolio Page
---

### Project: ManageEZPZ

ManageEZPZ is a desktop application that allows managers or supervisors to manage employees and assign tasks to them. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

### New Features

**Mark Task** (PRs : [#87](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/87))
* What it does :
  * Marks the task identified by the index number used in the displayed task list as done.

**Unmark Task** (PRs : [#87](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/87))
* What it does :
  * Unmarks the task identified by the index number used in the displayed task list, which changes the status back to not done.

**Delete Task** (PRs : [#89](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/89))
* What it does :
  * Deletes the task identified by the index number used in the displayed task list.
  * Decreases the number of assigned tasks of the employees who were assigned to the task by 1.

**Copying Employee Details to Computer Clipboard** (PRs : [#181](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/181))
* What it does :
  * Allow users to right-click on an employee to copy name, phone number and email to Computer Clipboard.

### Enhancements

1. **Graphical User Interface, GUI** (PRs : [#123](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/123), [#142](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/142), [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170), [#181](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/181), [#247](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/247))
   * What it does :
     * Displays the employee list and task list.
       * In employee list, details of each employee (name, phone number, email, number of assigned tasks) are listed.
       * In task list, details of each task (description, type, assignees, priority, completion status) are listed.
   * Justification :
     * This enhancement is important as all other features implemented by my teammates must be reflected correctly in the employee list and task list.
   * Highlights :
     * This enhancement requires me to do research on JavaFX extensively as I need to understand JavaFx properties and build layouts in Scene Builder.
     * This enhancement ensures that the employee list and task list displayed in the GUI are updated synchronously whenever any changes to the data is made.
     * This enhancement ensures that all features implemented by my teammates are working as expected.


2. **Error and Exception Messages** (PRs : [#179](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/179), [#185](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/185), [#247](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/247))
   * Highlights :
     * This enhancement requires me to ensure that all error messages shown to the user are correct and understandable so that they can recover from their errors and continue using the application.


3. **Edit Employee, modified feature from Edit Person of the original AddressBook-Level3** (PRs : [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170), [#179](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/179))
   * What it does :
     * Edits the details of the employee identified by the index number used in the displayed employee list.
     * Edits the details of all the tasks that are assigned to the said employee to reflect the updated changes of the employee.


4. **Delete Employee, modified feature from Delete Person of the original AddressBook-Level3** (PRs : [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170), [#182](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/182))
   * What it does :
     * Deletes the employee identified by the index number used in the displayed employee list.
     * Removes the employee from the tasks that has the employee assigned.


5. **Edit Task, modified feature implemented by team member [Aaron Loke](https://github.com/AY2122S2-CS2103-F11-1/tp/blob/master/docs/team/aaron-ljx.md)**
   * What it does :
     * Edits the details of the task identified by the index number used in the displayed employee list.
     * Details that can be updated are description, date and time.
     * Details that remain unchanged after editing the task are task type, assignees, priority and completion status.
   * Modifications made to original implementation :
     * Tweaked implementation (PRs : [#179](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/179), [#182](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/182), [#185](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/185))
       * to handle edge cases such as IndexOutOfBounds for invalid task index.
       * to ensure that task type, assignees, priority and completion status remain unchanged after editing the task.
       * to ensure that exception messages are thrown correctly.
     * Resolved bugs as advertised behaviour in the UG is not consistent with the actual behaviour. (PRs : [#251](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/251))


6. **Tag Task to an Employee, modified feature implemented by team member [Alfred Koh](https://github.com/AY2122S2-CS2103-F11-1/tp/blob/master/docs/team/alfredkohhh.md)**
   * What it does :
     * Assigns the specified task to an employee.
   * Modifications made to original implementation : (PRs : [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170), [#247](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/247))
     * Tweaked implementation to ensure that the GUI reflects that the task is assigned to the employee.
     * Tweaked implementation to increase the number of assigned tasks of the employee.


6. **Untag Task to an Employee, modified feature implemented by team member [Alfred Koh](https://github.com/AY2122S2-CS2103-F11-1/tp/blob/master/docs/team/alfredkohhh.md)**
   * What it does :
     * Deallocates the specified task from an employee.
   * Modifications made to original implementation : (PRs : [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170), [#247](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/247))
     * Tweaked implementation to ensure that the GUI reflects that the task is deallocated from the employee.
     * Tweaked implementation to decrease the number of assigned tasks of the employee.


7. **Tag a Priority to a Task, modified feature implemented by team member [Alfred Koh](https://github.com/AY2122S2-CS2103-F11-1/tp/blob/master/docs/team/alfredkohhh.md)**
   * What it does :
     * Assigns the specified task with a priority of either HIGH, MEDIUM, LOW or NONE.
   * Modifications made to original implementation : (PRs : [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170))
     * Tweaked implementation to ensure that the GUI reflects that the task is assigned the specified priority.

  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=dannytayjy&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


* **Project management**:
  * To be updated soon


* **Enhancements to existing features**:
  * To be updated soon


* **Documentation**:
  * User Guide:
    * To be updated soon
  * Developer Guide:
    * To be updated soon


* **Community**:
  * To be updated soon


* **Tools**:
  * IntelliJ IDEA
  * JavaFX Scene Builder
  * Adobe Photoshop
