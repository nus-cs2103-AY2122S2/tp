---
layout: page
title: Ho Lanzan's Project Portfolio Page
---

### Project: TAlent Assistant™

#### Overview ####
TAlent Assistant™ is a **desktop, lightweight and centralized management system** catered to NUS School of Computing professors for managing
the interview scheduling process of candidates applying to be undergraduate Teaching Assistants (TA). It is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI) built using JavaFX.

#### Summary of Contributions ####
* #### Code contributed: 
  * Click on this [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=lzan98&tabRepo=AY2122S2-CS2103-F11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
    to view the breakdown of my code contribution!

* #### Enhancements implemented:
  * **New Features**: `schedule add`, `schedule edit`, `schedule delete`, `schedule clear`
    * Added the feature to schedule, reschedule, delete and clear interviews. ([\#96](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/96), 
    [\#179](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/179), [\#194](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/194), [\#251](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/251))
    * Justification: An important feature for the value proposition of our application. Modelled after 
    `AB3`'s `add`, `edit`, `delete`, `clear` commands for consistency. Created a new model `InterviewSchedule` (and interface `ReadOnlyInterviewSchedule`)
    to store `Interview` objects.
    * Enhanced the `schedule clear` command to update the candidate's interview status according to the state of the
    interview upon execution (i.e. `Not Scheduled` if interview is upcoming or in progress, `Completed` if the interview
    has expired and user has not refreshed the application)

  * **Enhancement to existing features**:
    * Enhanced the `ClearCommand` to clear both candidates and interviews when command is executed.

* #### Contributions to the UG:
  * Added documentation for Motivation and Quick Start.
  * Enhanced documentation for the feature `delete`.
  * Added documentation for the new features `schedule add`, `schedule edit`, `schedule delete` and `schedule clear`.

* #### Contributions to the DG:
  * Added documentation and sequence diagrams for the section `Scheduling interviews`.
  * Updated documentation and class diagram for the section `Storage Component`
  * Added use cases for clear, `schedule add`, `schedule edit`, `schedule delete` and `schedule clear`.
  * Added manual testing documentation for `schedule add` and `schedule delete`.

* #### Contributions to team-based tasks:
  * **Testing and de-bugging:**
    * Contribution to identified bugs in v1.3b found [here](https://github.com/AY2122S2-CS2103-F11-2/tp/issues/240)
    * Fixed bug related to invalid user input for interview DATE_TIME ([\#365](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/365))
    * Fixed bug preventing users from rescheduling the same candidate
      ([\#384](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/384))
  * **Project management**:
    * Managed GitHub issues/deadlines whenever/wherever necessary.
    * Managed release of v1.3.trial on GitHub.
    * Authored 37 PRs and reviewed 75 PRs.

* #### Review/mentoring contributions:
  * **PR reviews/discussion threads involving non-trivial comments/suggestions**:
    * Suggested removal of redundant abstraction layers in implementation of availability checking
      ([\#135](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/135)).
    * Identified unnecessary declared and returned variables
      ([\#200](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/200)).
    * Suggested redundant declaration of variables due to relation of superclass and subclasses
      ([\#167](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/167)).

  * **Contributions beyond the project team**:
      * Solved issues faced by others: e.g. [\#13](https://github.com/nus-cs2103-AY2122S2/forum/issues/13#issuecomment-1017380970),
        [\#57](https://github.com/nus-cs2103-AY2122S2/forum/issues/57), [\#142](https://github.com/nus-cs2103-AY2122S2/forum/issues/142)
      * Contributed to forum discussions: e.g. [\#28](https://github.com/nus-cs2103-AY2122S2/forum/issues/28#issuecomment-1019963524),
        [\#243](https://github.com/nus-cs2103-AY2122S2/forum/issues/243), [\#194](https://github.com/nus-cs2103-AY2122S2/forum/issues/194)
      * Reported a total of [10 issues](https://github.com/lzan98/ped) during PED.
      
