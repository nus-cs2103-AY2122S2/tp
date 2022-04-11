---
layout: page
title: Ho Lanzan's Project Portfolio Page
---

### Project: TAlent Assistant™

#### Overview ####
TAlent Assistant™ is a **desktop, lightweight and centralized management system** catered to NUS School of Computing professors for managing
the interview scheduling process of candidates applying to be undergraduate Teaching Assistants (TA). It is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI) built using JavaFX.

### Summary of Contributions
**Code contributed**: Click on the following
  [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=lzan98&tabRepo=AY2122S2-CS2103-F11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
  to view the detailed breakdown of my code contribution!

#### Enhancements implemented:
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

* **Enhancement to existing features**:
  * Enhanced the `ClearCommand` to clear both candidates and interviews when command is executed.

#### Contributions to the UG:
* Added documentation for Motivation and Quick Start.
* Enhanced documentation for the feature `delete`.
* Added documentation for the new features `schedule add`, `schedule edit`, `schedule delete` and `schedule clear`.

#### Contributions to the DG:
* Added documentation and sequence diagrams for the section `Scheduling interviews`.
* Updated documentation and class diagram for the section `Storage Component`
* Added use cases for clear, `schedule add`, `schedule edit`, `schedule delete` and `schedule clear`.
* Added manual testing documentation for `schedule add` and `schedule delete`.

#### Contributions to team-based tasks:
* **Testing and de-bugging:**
  * Contribution to identified bugs in v1.3b found [here](https://github.com/AY2122S2-CS2103-F11-2/tp/issues/240)
  * Fixed bug related to invalid user input for interview DATE_TIME (#)
  * Fixed bug where user encounters a conflicting interview error when rescheduling the same candidate (#)
* **Project management**:
  * Managed GitHub issues/deadlines whenever/wherever necessary.
  * Managed release of v1.3.trial on GitHub.
  * Authored 47 PRs and reviewed 75 PRs.

#### Review/mentoring contributions:
* **PR reviews/discussion threads involving non-trivial comments/suggestions**
  * Suggested removal of redundant abstraction layers in implementation of availability checking
    [\#135](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/135).
  * Identified unnecessary declared and returned variables
    [\#200](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/200).
  * Suggested redundant declaration of variables due to relation of superclass and subclasses
    [\#167](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/167).

* **Contributions beyond the project team**:
    * Solved issues faced by others: e.g. [\#13](https://github.com/nus-cs2103-AY2122S2/forum/issues/13#issuecomment-1017380970),
      [\#57](https://github.com/nus-cs2103-AY2122S2/forum/issues/57), [\#142](https://github.com/nus-cs2103-AY2122S2/forum/issues/142)
    * Contributed to forum discussions: e.g. [\#28](https://github.com/nus-cs2103-AY2122S2/forum/issues/28#issuecomment-1019963524),
      [\#243](https://github.com/nus-cs2103-AY2122S2/forum/issues/243), [\#194](https://github.com/nus-cs2103-AY2122S2/forum/issues/194)
    * Reported a total of [10 issues](https://github.com/lzan98/ped) during PED.
  