---
layout: page
title: Alfred's Project Portfolio Page
---

### Project: ManageEZPZ

ManageEZPZ is a desktop application that allows managers or supervisors to manage employees and assign tasks to them. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.
Given below are my contributions to the project.

* **New Feature**:
  * Implemented JsonAdaptedTask. In which, handled, maintained and enhanced all Storage related matters for ManageEZPZ.
    * What it does: The creation of `JsonAdaptedTask` allows ManageEZPZ to have a centralised area to handle all Task-Storage matters.
  * Completed full implementation of add Todo Task, Command, Logic, Storage as a full template for team to follow. [#72](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/72) 
  * Implemented Storage functionality for Mark and Unmark Tasks. [#83](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/83)
  * Completed full implementation of Tag Task Command, Logic, Storage. [#108](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/108)
  * Completed full implementation of Untag Task Command, Logic, Storage. [#111](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/111)
  * Completed full implementation of Tag Priority Command, Logic, Storage. [#131](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/131)
  * Implemented tracking number of Tasks assigned to Employee [#146](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/146)
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=alfredkohhh&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Ensured the team's completion of weekly deliverables (Handled with Wei Jie)
  

* **Enhancements to existing features**:
  * Refactored existing add command to addEmployee command. [#67](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/67)
  * Fix and standardise all AddTask commands, added 'type' field for Task model. [#75](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/75)
    * Justification: By having a 'type' field, it allows for better identification and loading of Tasks.
  * Enhanced storage to handle different fields for the different types of Tasks. [#77](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/77)
    * Justification: With the different types of Task having different fields, it is important for the JsonAdaptedTask to handle and load the different fields of each Task.
  * Added error-prevention for AddDeadlineTaskParser, AddEventTaskCommandParser for the length of arguments. [#94](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/94)

  <div style="page-break-after: always;"></div>

  * Enhanced Logic for DeleteEmployee, deleting an Employee will untag all tasks that employee is assigned to, then proceeds to delete. [#141](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/141)
    * Justification: Deleting an employee should also update all associations related to that employee.
  * Supported Storage by adding Defensive Programming logic to counter Json being modified. [#249](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/249)
    * Justification: In cases where the Number of Tasks / other fields are being edited, we should check if the loaded details matches with our current state.

* **Documentation**:
  * Developer Guide:
    * Added Use-Cases. [#22](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/22), 
                       [#97](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/97)
    * Polished Storage description, added Storage Class-Diagram. [#113](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/113)
    * Update AddTask and TagTask descriptions. [#130](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/130)
    
  * User Guide:
    * Rectify and polished UG based on v1.3 PE-D Issues. [#248](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/248)
    * Added tagTask, untagTask, tagPriority. [#162](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/162)
    
* **Community**:
  * PR Reviewed & Merged: [#64](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/64), 
                 [#73](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/73), 
                 [#87](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/87), 
                 [#89](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/89),
                 [#96](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/96),
                 [#107](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/107),
                 [#123](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/123),
                 [#145](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/145)

