---
layout: page
title: Saravanan Anuja Harish's Project Portfolio Page
---

### Project: Teaching Assistant’s Personal Assistant (TAPA)

TAPA is a desktop contact management application used to manage students' contact and progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=harish-coding&breakdown=true)
    

* **New Feature**:
  * Added the ability to assign tasks to a single person by using their studentId and task name. [#53](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/53)
    * What it does: The command adds the task to the list of tasks assigned to the person with the given studentId.
    * Justification: This would give TA the ability to add tasks to individual students.
    * Highlights: The `Task` and `TaskList` classes were also created. A `Task` object contains the task name, and information about whether the task was done. A `TaskList` object contains a list of tasks that are assigned to a person.

* **New Feature**:
  * Added the ability to delete tasks that were previously assigned to a single person. [#127](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/127)
    * What it does: The command deletes a task assigned to a person, by using studentId and the index of the task.
    * Justification: This would give the TA the ability to delete tasks if they assigned tasks incorrectly.
    * Highlights: I made use of the `TaskList` class and added a new method to delete the task.

* **New Feature**:
  * Added the ability to delete tasks that were previously assigned to all the persons taking the same module. [#127](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/127)
    * What it does: The command deletes the task assigned to students taking a certain module. The module code and task name should be specified.
    * Justification: Before this feature was implemented, if a TA had incorrectly assigned a task, they would have to delete the task for each student taking the module using their student code, and the index of that particular task for each student. With this feature, the TA could simply use this command to delete a task for all the students who are taking this module and have been assigned with this task.
    * Highlights: I modified the implementation of `DeleteTask` that is executed by specifying studentId and index, to include this command.


* **Enhancements to existing features**:
    * Modified the list command to display the students in alphabetical order, sorted by their name. This feature allows the TA to view all the students the TA is assigned to for this semester. [#49](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/49)


* **Contribution to team-based tasks**:
  * Joint effort in renaming the product to "TAPA".
  * Maintained the issue tracker by assigning/labelling some issues.
  * Joint effort in morphing product into "TAPA" by extending relevant features.
  * Updated DG user stories to include list, deleteTask and assign commands description, implementation and use cases.

* **Documentation**:
    * User Guide:
        * Added documentation for `assign` command. [#40](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/40)
        * Modified documentation for `list` command. [#40](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/40)
        * Added constraints regarding telegram handle. [#205](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/205)
        * Added `deleteTask` Command description, format, and examples. [#127](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/127)
    * Developer Guide:
        * Added `deleteTask` command description, implementation and puml diagrams. [#227](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/227)
        * Added `assign` command description, implementation and puml diagrams. [#228](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/228)
        * Updated Use cases to include deleteTask command, and assign command. [#238](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/238)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#47](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/47), [#50](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/50), [#52](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/52), [#129](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/129), [#189](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/189), [#230](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/230), [#231](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/231).
    * Reported a total of 7 bugs and suggestions for other teams in the class (as seen [here](https://github.com/harish-coding/ped/issues)).

