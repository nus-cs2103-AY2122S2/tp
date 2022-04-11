---
layout: page
title: Aditi Gupta's Project Portfolio Page
---

### Project: LibTask

LibTask is a desktop application used by librarians to keep track of books, overdue books, and contact details of patrons who have borrowed them and those who requested them. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC. This project is based on AddressBook - Level 3. Given below are my contributions to the project.

* **New Feature**:
  * Added ID attribute to patron. [\#60](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/60)
    * What it does:
      * Allows library users to register their student ID with LibTask.
    * Justification:
      * Assists communication and management of student book loans by librarians if they can link library users to their student/staff IDs.
    * Highlights:
      * This addition was challenging as it required several checks and constraints to be placed on the format of NUS student/staff IDs.
      * Several precautions needed to be taken during implementation such as ensuring that no patron feature links were broken and data integrity was maintained in writing and reading information to and from json files.  
  * Added patron overdue feature to LibTask's existing patron features with tests. [\#102](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/102)
    * What it does:
      * Allows users to view a list of all patrons with overdue books.
    * Justification:
      * Improves book processing by enhancing communication between libraries and library users. Increases LibTask's efficiency and ease of navigation by allowing users to minimise command executions.
    * Highlights:
      * This addition to LibTask's existing patron features was challenging to implement due to the following reasons:
        1. Feature required double filtering of data (LibTask's patron list had to be filtered based on the statuses of every book borrowed by each patron).
        2. Implementation of this feature had to be done similar to how other patron and book features had been implemented to maintain similar levels of abstraction (hard SLAP) and code quality.
        3. Implementation of the patron overdue feature required sound knowledge of features added by other teammates to reduce code redundancies.

* **Code and PR Review contributions**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=variance&sortWithin=totalCommits&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=aditi2313&tabRepo=AY2122S2-CS2103T-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false), [tp Comments Dashboard](https://nus-cs2103-ay2122s2.github.io/dashboards/contents/tp-comments.html)

* **Project management**:
  * Suggested development of a library management software during initial project ideation stages.
  * Proposed LibTask functionalities and features
  * Reviewed and approved PRs for merging
  * Created issues for PRs

* **Enhancements to existing features**:
  * Updated AB-3 person to patron through removal of person address. [\#60](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/60)
  * Adaptation of person features to match updated patron attributes. [\#60](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/60)
  * Heavy refactoring of person associated classes to accommodate for patron. [\#60](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/60), [\#64](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/64)
  * Added test cases for patron features. [\#102](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/102)
  * Edited existing test cases for original person features to accommodate for patron. [\#70](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/70)

* **Documentation**:
  * User Guide:
    * Drafted tutorial steps 1, 2 and 3. [\#211](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/211), [\#232](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/232)
    * Documentation (with screenshots) for new patron overdue feature [\#116](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/116)
    * Enhancements in documentation (with screenshots) for patron and book features. [\#116](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/116), [\#148](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/148)

  * Developer Guide:
    * Product scope with target user profile and value proposition [\#211](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/211)
    * Non-functional requirements [\#211](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/211)
    * Implementation and design considerations for patron overdue feature [\#211](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/211)
    * Activity and Sequence diagrams for patron overdue feature [\#211](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/211)
    * Appendix: Instructions for manual testing for patron overdue feature [\#211](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/211)
    * Appendix: Instructions for manual testing enhancements to all book and patron features. [\#232](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/232)
    * Glossary additions [\#218](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/218), [\#220](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/220), [\#242](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/242), [\#244](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/244)

* **Review/mentoring contributions**:
  * PR reviewed (with non-trivial review comments): [\#20](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/20), [\#60](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/60), [\#63](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/63), [\#69](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/69), [\#72](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/72), [\#73](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/73), [\#75](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/75), [\#101](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/101), [\#108](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/108), [\#112](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/112), [\#113](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/113), [\#127](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/127), [\#129](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/129), [\#134](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/134), [\#136](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/136), [\#138](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/138), [\#139](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/139), [\#146](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/146), [\#205](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/205), [\#207](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/207), [\#208](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/208), [\#209](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/209), [\#214](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/214), [\#215](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/215), [\#222](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/222), [\#224](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/224), [\#228](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/228), [\#229](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/229), [\#235](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/235), [\#239](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/239), [\#240](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/240), [\#246](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/246), [\#251](https://github.com/AY2122S2-CS2103T-W14-1/tp/pull/251).

* **Community**:
  * Bugs reported for other teams can be found [here](https://github.com/aditi2313/ped/issues).

* **Tools**:
  * Assertions, Gradle, Intellij
