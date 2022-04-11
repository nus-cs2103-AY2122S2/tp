---
layout: page
title: Malcom Tan's Project Portfolio Page
---

### Project: Tracey

Tracey is a desktop app for managing health statuses of NUS Hall Residents. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * Added the ability to copy email from the Tracey database.
      * What it does: allows the user to use the `email` command or click on the `Show Email` button to open a Window containing the list of emails Tracey database. After which, the administrator can click on the copy email button to copy this set of emails to their clipboard.
      * Justification: This feature improves the product significantly because an administrator might want to contact these set of students via email and can do so easily by clicking on the copy email button. They can then proceed to their email domain of choice and easily paste this into the recipient address box.
      * Highlights: This enhancement addresses user story #15. This enhancement introduces a new Stackpane component UI to be used for further features such as Resize Display and Import.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=dannydakota&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed releases `v1.3` - `v1.3b` (2 releases) on GitHub.
    * Created Github Team Repo and created team PR to main repo.
    * Set up labels to be used in the issue tracker.
    * Establish CodeCov to track our application.
    * Set up team project website.

* **Enhancements to existing features**:
    * Added new attribute for the student (Block).  [\#62](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/62)
    * Update existing commands to cater for new attributes. [\#44](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/44)
    * Initially updated HelpWindow by adding the list of possible commands, their respective formats and examples for the User to refer easily. [\#46](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/46)
    * Further updated the UI of the HelpWindow by constraining its size. [\#226](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/226)
    * Wrote additional test cases for the Person package. Inclusive of test for the new Block, CovidStatus, Email, Matriculation Number and Faculty test cases. These test cases allows Faculty.java, MatriculationNumber.java, Block.java and CovidStatus.java to achieve >=80% method coverage and >=90% line coverage. [\#75](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/75)
    * Wrote additional test cases for CommandResultTest class to allow for 100% method coverage for CommandResult.[\#137](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/137)
    * Added the ability for the user to access the User Guide directly from the Help Window. [\#118](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/118)
    * Increased sample data size to provide a more comprehensive view of our features for testers. [\#131](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/131)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `help`, `email`. [\#96](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/96)
        * Added comparison between CLI and GUI in application introduction. [\#129](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/129)
        * Added a Navigation Guide for the User Guide. [\#129](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/129)
        * Edited quickstart section to make it more comprehensive for users. [\#129](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/129)
        * Edited the application information of Tracey. [\#96](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/96)
        * Added screenshots for features `find`, `summarise` and a general application Ui screenshot. [\#115](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/115), [\#129](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/129)
        * Added tables for Correct and Incorrect usages in `add` and
        * Fixed tables in GFMD for Command Summary, Glossary and List of Pre-defined constants. [\#115](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/115), [\#143](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/143)
        * Update table and figure numbering. [\#143](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/143)
        * Format User Guide to eliminate grammatical and spelling errors. [\#143](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/143)
    * Developer Guide:
        * Added implementation details of the `Add` feature. [\#89](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/89)
        * Edited the sequence diagram for Add feature. [\#89](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/89) 
        * Added Activity diagram for Add feature. [\#89](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/89)
        * Added class diagrams for Add and Clear Feature. [\#268](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/268)
        * Added implementation details for Email Feature. [\#265](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/265)
        * Added Class, Activity, Sequence diagrams for Email Feature. [\#265](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/265) 
        * Added Class, Activity, Sequence diagrams for Help Feature. [\#276](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/276)
        * Added Class, Activity, Sequence diagrams for Delete Feature. [\#296](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/296)
        * Added Class, Activity, Sequence diagrams for Exit Feature. [\#296](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/296)
        * Added manual testing guidelines in the DG. [\#296](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/296])
        * Developer Guide formatting. [\#308](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/308), 

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#33](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/33), [\#47](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/47), [\#50](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/50), [\#57](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/57), [\#74](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/74), [\#84](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/84), [\#112](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/112), [\#122](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/122), [\#128](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/128), [\#235](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/235), [\#242](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/242), [\#273](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/273), [\#266](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/266), [\#273](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/273), [\#274](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/274), [\#275](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/273), [\#277](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/277), [\#299](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/299)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/DannyDakota/ped/issues/1), [2](https://github.com/DannyDakota/ped/issues/2), [3](https://github.com/DannyDakota/ped/issues/3), [4](https://github.com/DannyDakota/ped/issues/4), [5](https://github.com/DannyDakota/ped/issues/5), [6](https://github.com/DannyDakota/ped/issues/6), [7](https://github.com/DannyDakota/ped/issues/7), [8](https://github.com/DannyDakota/ped/issues/8), [9](https://github.com/DannyDakota/ped/issues/9), [10](https://github.com/DannyDakota/ped/issues/10), [11](https://github.com/DannyDakota/ped/issues/11))
    
