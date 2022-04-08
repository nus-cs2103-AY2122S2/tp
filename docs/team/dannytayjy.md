---
layout: page
title: Tay Jun Yang's Project Portfolio Page
---

## Project: ManageEZPZ

ManageEZPZ is a desktop application that allows managers or supervisors to manage employees and assign tasks to them. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

--------------------------------------------------------------------------------------------------------------------

### New Features

**Mark Task** : `markTask` ([#87](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/87), [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170))
* What it does : The feature marks the task identified by the index number used in the displayed task list as done.

**Unmark Task** : `unmarkTask` ([#87](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/87), [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170))
* What it does : The feature unmarks the task identified by the index number used in the displayed task list, which changes the status back to not done.

**Delete Task** : `deleteTask` ([#89](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/89), [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170), [#259](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/259))
* What it does : The feature deletes the task identified by the index number used in the displayed task list. The number of assigned tasks of the employees who were assigned to the deleted task will be decreased by 1.

**Copying Employee Details to Computer Clipboard** ([#181](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/181))
* What it does : The feature enables the user to right-click on an employee to copy the full name, phone number and email to Computer Clipboard.
* Justification : The feature allows the user to tag a task to an employee or untag a task from an employee easily by pasting the full name of the employee in the command text box.

--------------------------------------------------------------------------------------------------------------------

### Enhancements

**Graphical User Interface (GUI) of Main Window** ([#123](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/123), [#142](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/142), [#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170), [#181](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/181), [#247](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/247))
* What it does :
  * The GUI displays the employee list and task list.
      * In employee list, details of each employee (`name`, `phone`, `email`, `numOfTasks`) are listed.
      * In task list, details of each task (`description`, `type`, `assignees`, `priority`, `isDone`) are listed.
* Justification :
  * This enhancement is important as all other features implemented by my teammates must be reflected correctly in the employee list and task list.
* Highlights :
  * This enhancement requires me to research on JavaFX extensively as I need to understand JavaFx properties and build layouts in fxml code with the help of the Scene Builder software. I will also need to have knowledge on CSS as CSS is used to set the style and alignment of JavaFx containers and controls.
  * This enhancement ensures that the employee list and task list displayed in the GUI are updated synchronously whenever any changes to the data is made.
  * This enhancement ensures that all features implemented by my teammates are working as expected.

**Edit Employee Command** : `editEmployee` ([#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170), [#179](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/179))
* Modified based on the Edit Person feature of the original AddressBook-Level3 code base
* What it does :
  * The feature edits the details of the employee identified by the index number used in the displayed employee list.
  * All tasks that are assigned to the edited employee will be updated to reflect the new changes of the employee.

**Delete Employee Command** : `deleteEmployee` ([#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170), [#182](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/182))
* Modified based on the Delete Person feature of the original AddressBook-Level3 code base
* What it does :
  * The feature deletes the employee identified by the index number used in the displayed employee list.
  * All tasks that are assigned to the deleted employee will be updated to remove the employee from the respective tasks.

**Edit Task Command** : `editTask`
* Modified the feature implemented by team member [Aaron Loke](https://github.com/AY2122S2-CS2103-F11-1/tp/blob/master/docs/team/aaron-ljx.md)
* What it does :
  * The feature edits the details of the task identified by the index number used in the displayed task list.
  * The details that can be updated are `description`, `date` and time (either `time` for Deadline and `startTime` and `endTime` for Event).
  * The details that remain unchanged after editing the task are `type`, `assignees`, `priority` and `isDone`.
* Modifications made to original implementation :
  * Tweaked implementation ([#179](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/179), [#182](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/182), [#185](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/185))
    * to handle edge cases such as IndexOutOfBounds for invalid task index of the displayed task list.
    * to ensure that `type`, `assignees`, `priority` and `isDone` remain unchanged and are not discarded after editing the task.
    * to ensure that exception messages are thrown correctly and appropriately.
  * Guided team member Aaron on the approach of resolving the bugs so that the advertised behaviour in the UG will be consistent with the actual behaviour ([#256](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/256)).

**Tag a Task to an Employee Command** : `tagTask`
* Modified the feature implemented by team member [Alfred Koh](https://github.com/AY2122S2-CS2103-F11-1/tp/blob/master/docs/team/alfredkohhh.md)
* What it does :
  * The feature assigns the specified task to an employee.
* Modifications made to original implementation : ([#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170))
  * The implementation has been tweaked to ensure that the GUI is updated synchronously to show that the task is assigned to the employee in the displayed employee list.
  * The number of assigned tasks of the employee is increased by 1 and the employee's full name is reflected under the assignees of the task.

**Untag a Task from an Employee Command** : `untagTask`
* Modified the feature implemented by team member [Alfred Koh](https://github.com/AY2122S2-CS2103-F11-1/tp/blob/master/docs/team/alfredkohhh.md)
* What it does :
  * The feature deallocates the specified task from an employee.
* Modifications made to original implementation : ([#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170))
  * The implementation has been tweaked to ensure that the GUI is updated synchronously to show that the task is deallocated from the employee in the displayed employee list.
  * The number of assigned tasks of the employee is decreased by 1 and the employee's full name is removed from the assignees of the task.

**Tag a Priority to a Task Command** : `tagPriority`
* Modified feature implemented by team member [Alfred Koh](https://github.com/AY2122S2-CS2103-F11-1/tp/blob/master/docs/team/alfredkohhh.md)
* What it does :
  * The feature assigns the specified task with a `priority` of either `HIGH`, `MEDIUM`, `LOW` or `NONE`.
* Modifications made to original implementation : ([#170](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/170))
  * The implementation has been tweaked to ensure that the GUI is updated synchronously to show that the task is assigned with the specified `priority`.

**Validation Checks and Error (Exception) Messages** ([#179](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/179), [#185](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/185), [#247](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/247), [#259](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/259))
* Highlights :
  * This enhancement requires me to ensure that all validation checks are handled properly.
  * This enhancement requires me to ensure that all error messages shown to the user upon executing the wrong commands are correct and understandable, so that the user can recover from their errors and continue using the application.
  * This enhancement covers the features including but not limited to `addTodo`, `addDeadline`, `addEvent`, `deleteEmployee`, `deleteTask`, `editEmployee`, `editTask`, `markTask`, `unmarkTask`, `tagTask`, `untagTask` and `tagPriority`.

**GUI of Help Window** ([#247](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/247))
* Updated the GUI of the Help Window to replace the user guide URL and showcase the feature of copying employee details to Computer Clipboard.

<img alt="help message" height="490" src="../images/helpMessage_new.png" width="500"/>

--------------------------------------------------------------------------------------------------------------------

### Code Contributed

* [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=dannytayjy&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

--------------------------------------------------------------------------------------------------------------------

### Icons for GUI

**Created Icons** ([#142](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/142), [#181](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/181))

| Name            | Icon                                                                                      |
|-----------------|-------------------------------------------------------------------------------------------|
| Employee        | <img height="30" src="../../src/main/resources/images/employee_icon.png" width="30"/>     |
| Phone           | <img height="30" src="../../src/main/resources/images/phone_icon.png" width="30"/>        |
| Email           | <img height="30" src="../../src/main/resources/images/email_icon.png" width="30"/>        |
| Priority Low    | <img height="30" src="../../src/main/resources/images/priorities_low.png" width="23"/>    |
| Priority Medium | <img height="30" src="../../src/main/resources/images/priorities_medium.png" width="23"/> |
| Priority High   | <img height="30" src="../../src/main/resources/images/priorities_high.png" width="23"/>   |
| Priority Urgent | <img height="30" src="../../src/main/resources/images/priorities_urgent.png" width="23"/> |

**Modified Icons** ([#142](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/142))

| Name | Original Icon                                                                    | Modified Icon                                                                                                                      |
|------|----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| Task | <a href="https://www.flaticon.com/premium-icon/task_3774569">feen (Flaticon)</a> | <img height="30" src="../../src/main/resources/images/task_icon.png" width="30"/> <br> Filled up colours and changed stroke colour |

--------------------------------------------------------------------------------------------------------------------

### Project Management

* To be updated soon

--------------------------------------------------------------------------------------------------------------------

### Documentation

**User Guide**

* Added full details for the features `editEmployee`, `deleteEmployee`, `markTask`, `unmarkTask` and `deleteTask` ([#169](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/169), [#259](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/259))
* Added more details for the features `tagTask`, `untagTask` and `tagPriority` ([#169](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/169))
* Standardized the formatting of all commands and update some missing details in other commands ([#169](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/169))
* Updated the table formatting for Command Summary ([#169](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/169))

**Developer Guide**

* Added Non-Functional Requirements and Glossary details ([#20](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/20))
* Updated implementation details of the `UI` component ([#129](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/129))
* Added implementation details of the `Logic` component for `markTask`, `unmarkTask` and `deleteTask` features, including the class diagrams and screenshots of the application executing the feature ([#129](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/129))
* **To be updated soon:**
  * Double check Non-Functional Requirements and Glossary details
  * Update implementation details of the `Logic` component for `markTask`, `unmarkTask` and `deleteTask` features and the screenshots of the application executing the feature
  * Add implementation details of the `Logic` component for `editEmployee` and `deleteEmployee` features, including the class diagrams and screenshots of the application executing the feature

--------------------------------------------------------------------------------------------------------------------

### Community

* Refactored project's Java package to manageezpz : [#60](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/60)
* Updated the user guide URL in the Help Window : [#247](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/247)
* PRs reviewed (with non-trivial review comments) : [#108](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/108), [#145](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/145), [#156](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/156), [#161](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/161), [#257](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/257)
* Reported bugs and suggestions for other team in the same CS2103/T class : [PE Dry Run](https://github.com/dannytayjy/ped/issues)

--------------------------------------------------------------------------------------------------------------------

### Tools

* IntelliJ IDEA
* JavaFX Scene Builder
* Adobe Photoshop