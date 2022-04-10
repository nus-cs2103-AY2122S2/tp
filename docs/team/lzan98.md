---
layout: page
title: Ho Lanzan's Project Portfolio Page
---

### Project: TAlent Assistant™

#### Overview ####
TAlent Assistant™ is a **desktop, lightweight and centralized management system** catered to NUS School of Computing professors for managing
the interview scheduling process of candidates applying to be undergraduate Teaching Assistants (TA). It is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI) built using JavaFX.

Given below are my contributions to the project.

* **New Features**:
  * `schedule add`
    * Implemented the `AddScheduleCommandParser` and `AddScheduleCommand` to allow users to schedule interviews with candidates
    in TAlent Assistant™.
    * Created a new model `InterviewSchedule` (and interface `ReadOnlyInterviewSchedule`) to store
    `Interview` objects.
  * `schedule edit`
    * Implemented the `EditScheduleCommandParser` and `EditScheduleCommand` to allow users to edit the DATE_TIME of interviews using 
    the index in the Interview Schedule.
  * `schedule delete`
    * Implemented the `DeleteScheduleCommandParser` and `DeleteScheduleCommand` to allow users to delete interviews using the index
      in the Interview Schedule.
  * `schedule clear`
    * Implemented the `ClearScheduleCommandParser` and `ClearScheduleCommand` to allow users to clear all interviews in the
    interview schedule.
    * Enhanced the feature by updating candidates' interview statuses from `Scheduled` to `Not Scheduled` or `Completed`,
    provided they have an existing interview in the schedule and dependent on the progress of the interview 
    during execution of the command.

* **Enhancement to existing features**:
  * Enhanced the `ClearCommand` to clear both candidates and interviews when command is executed. 


* **Code contributed**: Click on the following 
[RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=lzan98&tabRepo=AY2122S2-CS2103-F11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
to view the detailed breakdown of my code contribution!


* **Project management**: 



* **Documentation**:
  * User Guide:
    * Added documentation for Motivation and Quick Start.
    * Enhanced documentation for the feature `delete`.
    * Added documentation for the new features `schedule add`, `schedule edit`, `schedule delete` and `schedule clear`.
    


  * Developer Guide:
    * Added documentation for the section `Scheduling interviews`.
    * Implemented sequence diagrams for the section `Scheduling interviews`.
    * Updated documentation and class diagram for the section `Storage Component`

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#135](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/135),
  [\#200](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/200), [\#167](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/167) and more.
  
    * Contributed to forum discussions: [\#52](https://github.com/nus-cs2103-AY2122S2/forum/issues/57), [\#142](https://github.com/nus-cs2103-AY2122S2/forum/issues/142), 
  [\#194](https://github.com/nus-cs2103-AY2122S2/forum/issues/194) and more.
