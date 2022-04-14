---
layout: page
title: Benjamin Koh Wei Jie's Project Portfolio Page
---

### Project: Teaching Assistant’s Personal Assistant (TAPA)

TAPA is a desktop contact management application used to manage students' contact and progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=benjykoh&breakdown=true)

* **New Feature**:
  * Added the ability to display the format, and a short description for a specified command. [#43](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/43)
    * What it does: Provides the user with a short description on what the given command does, as well as display the format of the command, allowing the users to learn how to use it.
      * If no command is specified, this feature provides the user with a list of all the existing commands.
    * Justification: This feature allows users to understand how to use and find out more about existing commands.
    * Highlights: This feature was fairly easy to implement, however, it required constant updates whenever a new feature is added into our application.
  
  <div style="page-break-after: always;"></div>

* **New Feature**:
  * Added the ability to sort and display the students in TAPA. [#134](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/134)
    * What it does: Sorts the list of students by the number of incomplete tasks in **descending** order and displays the list.
    * Justification: This features allows the TAs to easily view which students are lacking behind in terms of number of incomplete tasks.
    * Highlights: This feature was tricky to implement as the original implementation updated counters whenever a task is marked as done or undone. But, the counter resets everytime the application is rebooted. Thus, there was a need to ensure that the number of incomplete tasks would be counted everytime the `sort` command is run.

* **New Feature**:
  * Added the ability to mark a specific undone task as done. [#69](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/69)
    * What it does: Change the completion status of a task to done for a particular student.
    * Justification: This feature allows users update the progress of a student's task.

* **New Feature**:
  * Added the ability to mark a specific done task as undone. [#69](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/69)
    * What it does: Change the completion status of a task to undone for a particular student.
    * Justification: This feature allows users update the progress of a student's task.
  
* **Enhancements to existing features**:
  * Modified the `assign` feature to allow assigning tasks to all students whom are taking a particular module. [#102](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/102)
    * The `assign` feature was previously implemented [here](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/53) in v1.2. It was enhanced in v1.3 to allow users to assign tasks to students in the same module more easily.
  * Wrote additional tests for new and existing features to increase code coverage. ([#112](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/112), [#117](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/117), [#196](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/196), [#199](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/199))
  * Morphed AB3 into TAPA by refactoring test cases. ([#210](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/210), [#222](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/222), [#226](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/226))

* **Contribution to team-based tasks**:
  * Shared effort in renaming the product to "TAPA".
  * Shared effort in creating "TAPA" icon.
  * Shared effort in morphing product into "TAPA" by removing unused fields, updating the diagrams in the Developer Guide [#232](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/232), and adding an introduction. [#261](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/261) 
  * Maintained the issue tracker by assigning/labelling some issues.
  * Updated Developer Guide by adding and editing user stories in the `Appendix` section. [#91](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/91), [#93](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/93)
  * Maintained the User Guide and Developer Guide by formatting them and fixing documentation bugs. ([#213](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/213), [#234](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/234), [#258](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/258), [#259](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/259), [#263](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/263))

* **Community**:
  * PRs reviewed (with non-trivial review comments): ([#40](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/40), [#41](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/41), [#144](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/144), [#205](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/205), [#254](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/254))
  * Reported a total of 18 bugs and suggestions for other teams in the class (as seen [here](https://github.com/Benjykoh/ped/issues))

<div style="page-break-after: always;"></div>

* **Documentation**:
  * User Guide:
    * Added documentation for the features `manual`, `unmark`, `mark` [#39](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/39) and `sort`. [#134](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/134)
    * Updated documentation for the feature `assign`. [#102](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/102)
  * Developer Guide:
    * Added implementation details for commands: `manual`, `mark`,`unmark` [#106](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/106) and `sort `. [#215](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/215)
    * Updated implementation details for command: `assign`. [#231](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/231)
    * Added use cases: UC06, UC07, UC14, UC15. [#253](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/253)
    * Added manual test cases for `assign`, `mark`, `unmark`, `sort` and `manual`. [#268](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/268)
  


