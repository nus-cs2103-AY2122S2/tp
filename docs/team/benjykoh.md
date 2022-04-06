---
layout: page
title: Benjamin Koh Wei Jie's Project Portfolio Page
---

### Project: Teaching Assistant’s Personal Assistant (TAPA)

TAPA is a desktop contact management application used to manage students' contact and progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: **Manual Command** (Pull Requests [#43](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/43), [#84](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/84), [#112](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/112))
  * What it does: Display the format and a short description for a specified command.
  * Justification: This feature allows users to find out more about existing commands, and helps them understand how to use these commands.
  * Highlights: This feature was fairly easy to implement, however, it required constant updates whenever a new feature is added into our application.

* **New Feature**: **Mark Command** (Pull Requests [#69](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/69), [#77](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/77), [#117](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/117))
  * What it does: Marks a specific undone task as done for a particular student.
  * Justification: This feature allows users update the progress of a student's task.
  * Highlights: 

* **New Feature**: **Unmark Command** (Pull Requests [#69](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/69), [#77](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/77), [#117](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/117))
  * What it does: Marks a specific done task as undone for a particular student.
  * Justification: This feature allows users update the progress of a student's task.
  * Highlights:

* **New Feature**: **Sort Command** (Pull Requests [#134](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/134), [#199](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/199))
  * What it does: Sorts and displays the students in TAPA by the number of undone tasks in **descending** order.
  * Justification: This features allows the TAs to view which students are lacking behind in terms of number of incomplete tasks.
  * Highlights: This feature was slightly tricky to implement as the original implementation relied on counters whenever a task is marked as complete or incomplete. However, the counter resets everytime the application is rebooted. Hence, there was a need to ensure that the number of incomplete tasks would be counted everytime the `sort` command is run, instead of relying on counters.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=benjykoh&breakdown=true)

* **Project management**:
  * Shared effort in renaming the product to "TAPA"
  * Shared effort in creating "TAPA" icon
  * Shared effort in morphing product into "TAPA" by removing unused fields
  * Kept track of issues and team's progress
  * Updated Developer Guide by adding and editing user stories

* **Enhancements to existing features**: **Assign Command** (Pull Requests [#102](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/102), [#196](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/196))
  * What it does: Assigns a task to all students whom are taking a particular module.
  * Justification: This feature allows TAs to assign a task to all students in the module, rather than assigning the tasks one by one.
  * Highlights: This feature was slightly tricky to implement as we had to consider the possibility of the task being already assigned to some students.

* **Documentation**:
  * User Guide:
    * to be added soon
  * Developer Guide:
    * Added and updated user stories (Pull Requests [#91](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/91), [#93](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/93))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#40](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/40), [#41](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/41), [#144](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/144), [#205](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/205)
  * Reported bugs and suggestions for other teams in the class [1](https://github.com/Benjykoh/ped/issues/13), [2](https://github.com/Benjykoh/ped/issues/10), [3](https://github.com/Benjykoh/ped/issues/3)

* **Tools**:
  * to be added soon
