---
layout: page
title: GuanZong's Project Portfolio Page
---

### Project: LinkyTime

**LinkyTime** is a desktop application for NUS Computer Science students to manage their online meeting links. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the `medit` command that allows the user to edit a module specified by the module index (With test cases included). (Pull request [\#120](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/120))
    * What it does: Allows the user change the module at the specified index without deleting and then adding it.
    * Justification: This feature improves the product as provides convenience to the user. If this feature is not implemented, if the user needs to change a module's name, the user would have to delete all meetings associated with the module before deleting the module, just to add a new module to reflect the changes and then add all the associated meetings back into LinkyTime.
    * Credits: *AB3's EditCommand*
  
* **New Feature**: Added `add` command that allows the user to add a meeting (With test cases included). (Pull request [\#59](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/59))
    * What it does: Allows the user to add a meeting into LinkyTime.
    * Justification: This is a basic functionality that is required for LinkyTime.
    * Credits: *AB3's AddCommand*

* **New Feature**: Added `edit` command that allows the user to edit a meeting (With test cases included). (Pull request [\#100](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/100))
    * What it does: Allows the user to edit a meeting in LinkyTime.
    * Justification: This would allow the user to edit a certain field of a meeting instead of having them delete then add a meeting again in order to make changes.
    * Credits: *AB3's EditCommand*
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=AY2122S2-CS2103T-T13-3%2Ftp&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=Gnoznaug&tabRepo=AY2122S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Maintained team [weekly meeting notes](https://docs.google.com/document/d/1blOVPpajNMHmHRSajK4t9cl0r2PwMiO2j7FF4Xy-pO8/edit?usp=sharing).
    * Created communication channels for team to use. 
    * Closed redundant PR after reviewing. [\#183](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/183)

* **Enhancements to existing features**:
    * Implemented MeetingDuration for MeetingEntry. (Pull request [\#82](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/82))
    * Fixed test cases that broke due to refactoring, which increased the code coverage by 5.48%. (Pull request [\#186](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/186))
    * Implemented feature for the module list to be perpetually sorted in alphabetical order. (Pull request [\#109](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/109))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add`,`edit` and `medit`. (Pull requests [\#70](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/70), [\#100](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/100), [\#123](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/123))
        * Added a summary table for the potential errors that the user might face. (Pull request [\#178](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/178))
        * Added documentation for the new parameter `MeetingDuration` of a meeting. (Pull request [\#102](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/102))
    * Developer Guide:
        * Added implementation details of the `add` feature. (Pull request [\#91](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/91))
        * Did cosmetic tweaks to existing documentation of use cases. (Pull request [\#12](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/12))
        * Created the sequence diagram reference frame for `EditModuleCommand`. (Pull request [\#194](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/194))
        * Added manual testing instructions for the adding and editing of meetings. (Pull request [\#196](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/196))
        * Added to the effort appendix, describing the effort we put in to implement the `Meeting` and `Module` models. (Pull request [\#216](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/216))
        * Collaborated with teammates on the DG using VSCode live share. (Pull request [\#10](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/10))
    * Created demo video segments for add meeting and edit meeting.
    
* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#108](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/108#discussion_r834411234), [\#94](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/94#discussion_r835257091), [\#211](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/211#discussion_r846765970)

