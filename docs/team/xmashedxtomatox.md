---
layout: page
title: Chen Xiang's Project Portfolio Page
---

### Project: LinkyTime

**LinkyTime** is a desktop application for NUS Computer Science students to manage their online meeting links. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added `list` command to show all upcoming meetings.
    * What it does: allows the user to view all upcoming meetings.
    * Justification: This feature is fundamental to our product because it allows the user to reset the filter of the meeting list back to default after running other commands. Without this command, users would be unable to get an unfiltered view of all upcoming meetings.
    * Credits: *AB3's listCommand*

* **New Feature**: Added `archive` command to show all completed meetings.
    * What it does: allows the user to view all completed meetings.
    * Justification: This feature is for user convenience in case the user wants to look up any details of past meetings. The program will retain past meeting information until explicitly told to delete them.
    * Credits: *AB3's listCommand* 
  
* **New Feature**: Added auto sort functionality for meeting list
    * What it does: Automatically sorts the meeting list by chronological order
    * Justification: This feature is for user convenience as it displays the meetings in an intuitive format. Users tend to care more about meetings that are occurring soon, as such it is more convenient to already have them sorted in that order.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=xMashedxTomatox&tabRepo=AY2122S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Refactored AB3 base code and test code to work with LinkyTime (Pull requests [\#62](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/62), [\#85](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/85))
    * Updated the base GUI to work with LinkyTime data (Pull request [\#60](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/60))
    * Wrote additional tests for existing features (Pull requests [\#78](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/78), [\#187](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/187), [\#188](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/188))
    * Added GUI for users to differentiate list and archive (Pull request [\#127](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/127))
    * Removed legacy or obsolete AB3 content (Pull request [\#218](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/218))

* **Project Management**:
    * Maintained team [weekly meeting notes](https://docs.google.com/document/d/1blOVPpajNMHmHRSajK4t9cl0r2PwMiO2j7FF4Xy-pO8/edit?usp=sharing)
    * Closed redundant issues after closing PRs (Pull requests [\#56](https://github.com/AY2122S2-CS2103T-T13-3/tp/issues/56), [\#80](https://github.com/AY2122S2-CS2103T-T13-3/tp/issues/80))
    * Added several [issues](https://github.com/AY2122S2-CS2103T-T13-3/tp/issues?q=is%3Aissue+is%3Aclosed+author%3AxMashedxTomatox) after weekly group discussions
    * Suggested change in workflow which sped up the overall team progress
  
* **Documentation**:
    * User Guide:
        * Added documentation for the features `list` (Pull request [\#25](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/25))
        * Added the `Using LinkyTime` section (Pull request [\#123](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/123))
    * Developer Guide:
        * Added implementation details of the `list` and `archive` feature (Pull request [\#197](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/197))
        * Collaborated with teammates on the DG using VSCode live share (Pull request [#10](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/10))
        * Added general implementation details of Commands that uses parser and Commands that don't (Pull request [\#197](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/197))
        * Updated logic component diagram (Pull request [\#197](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/197))
        * Added manual testing steps for list, archive and auto-sort meetings (Pull request [\#200](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/200))
        * Added section for gui, unit test and auto-sort meetings in effort appendix (Pull request [\#210](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/210))
    * Created demo video segments for list, archive and auto-sort meetings
    * Compiled teammates' demo video segments for the final demo video

* **Community**:
    * PRs reviewed (with non-trivial review comments): (Pull requests [\#90](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/90), [\#111](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/111))



