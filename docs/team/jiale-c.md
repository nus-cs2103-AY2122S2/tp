---
layout: page
title: Chee Jia Le's Project Portfolio Page
---

### Project: Tracey

Tracey is a desktop app for managing health statuses of NUS Hall Residents. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to summarise the database.
  * **What it does**: This allows the user to see trends of different groups within the hall and how they are dealing with Covid. Charts are used to deliver these trends categorised by housing blocks and by faculty. In each block, users can also see how its group of students are dealing with the different types of Covid statuses too.
  * **Justification**: This feature allows the user to make rational and prompt decisions to contain the Covid outbreak if a certain region of its premises is having widespread infection.
  * **Highlights**: The feature will calculate the percentage of students that are Covid positive in each block so that users do not need to do extra calculations.

* **New Feature**: Added the overall revamp and upgrades of the pie chart window to fit to the needs of a typical hall master user. Colour coded charts to be consistent between Covid statuses and between charts.
  * **What it does**: This allows the user to visualise the trends executed by the `summarise` command to aid better understanding of Covid statistics.
  * **Justification**: This feature makes the user experience of the GUI more pleasurable with diagrams and figures. Plain words and numbers can be hard for some users to have a quick understanding of the issues that Covid may have caused.The charts provide users a better viewing experience. Additional use of javafx bar charts and pie charts helps in the visuals for seamless implementation to our application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jiale-c&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=jiale-c&tabRepo=AY2122S2-CS2103T-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed submission releases `v1.2` - `v1.2b` (2 releases) on GitHub.
  * Managed submissions meant for LumiNUS.
  * Created issues trackers for members to complete for final milestones.
  * Peer reviewed team members PR and provided suggestions.
  
* **Enhancements to existing features**:
  * Added new categories for representing data (Pull request [\#86](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/86)).
  * Added defensive programming elements into the code (Pull request [\#86](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/86)).
  * Added colour and chart formatting so that it suits the data types of student types and categories (i.e. axes representing number of students are type casted to be integers instead of double) (Pull request [\#232](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/232)).
  * Further updated the UI of the PieChartWindow by constraining its size and forcing it to close when the main application exits (Pull requests [\#234](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/234), [\#232](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/232)).
  * Wrote test cases for the summarise command. Inclusive of test for Block type, Covid Status type and Faculty type. These test cases allowed the increase in coverage from 71% to 73% (Pull requests [\#58](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/58)).
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `summrise`, `import`, `clear`, `edit`, `find` (Pull requests [\#150](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/150), [\#128](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/128), [\#114](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/114))
    * Reformatted User Guide to have a better reading flow (Pull request [\#128](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/128))
    * Added most of the diagrams for the different features and for the QnA (Pull requests [\#114](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/114), [\#150](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/150), [\#240](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/240))
    * Added a collapsable section for the explanation of CLI. (Pull request [\#150](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/150))
    * Edited the Question and Answer information of the user guide (Pull request [\#114](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/114))
    * Added information on what happens if duplicate inputs were used on Tracey (Pull request [\#240](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/240))
    * Refined the table of contents (Pull request [\#128](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/128))
  * Developer Guide:
    * Added the target user profiles and value propositions (Pull request [\#20](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/20))
    * Added implementation details of the `summarise`, `exit`, `clear` and `find` features (Pull requests [\#90](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/90), [\#100](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/100))
    * Added user stories with its priority according to how important it is to implement (Pull request [\#20](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/20))
    * Added use cases for Tracey (Pull request [\#28](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/28))
    * Added UML sequence, class and activity diagrams for `summarise`, `exit`, `clear`, `list`, `find` and PieChartWindow (Pull requests [\#90](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/90), [\#100](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/100))
  
* **Community**:
    * PRs reviewed (with non-trivial review comments): (Pull requests [\#33](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/33), [\#57](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/57), [\#62](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/62), [\#75](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/75), [\#118](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/118), [\#130](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/130), [\#140](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/140), [\#238](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/238), [\#261](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/261))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/jiale-c/ped/issues/1), [2](https://github.com/jiale-c/ped/issues/2), [3](https://github.com/jiale-c/ped/issues/3), [4](https://github.com/jiale-c/ped/issues/4), [5](https://github.com/jiale-c/ped/issues/5))

