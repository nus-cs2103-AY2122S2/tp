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
    * Added new attribute for the student (Block).  [\#62]()
    * Update existing commands to cater for new attributes. [\#44]()
    * Initially updated HelpWindow by adding the list of possible commands, their respective formats and examples for the User to refer easily. [\#46]()
    * Further updated the UI of the HelpWindow by constraining its size [\#226]()
    * Wrote additional test cases for the Person package. Inclusive of test for the new Block, CovidStatus, Email, Matriculation Number and Faculty test cases. These test cases allows Faculty.java, MatriculationNumber.java, Block.java and CovidStatus.java to achieve >=80% method coverage and >=90% line coverage. [\#75]()
    * Wrote additional test cases for CommandResultTest class to allow for 100% method coverage for CommandResult .[\#137]()
    * Added the ability for the user to access the User Guide directly from the Help Window. [\#118]()
    * Increased sample data size to provide a more comprehensive view of our features for testers. [\#131]()

* **Documentation**:
    * User Guide:
        * Added documentation for the features `help`, `email` [\#96]()
        * Added comparison between CLI and GUI in application introduction [\#129]()
        * Added a Navigation Guide for the User Guide [\#129]()
        * Edited quickstart section to make it more comprehensive for users [\#129]()
        * Edited the application information of Tracey [\#96]()
        * Added screenshots for features `find`, `summarise` and a general application Ui screenshot. [\#115](), [\#129]()
        * Added tables for Correct and Incorrect usages in `add` and
        * Fixed tables in GFMD for Command Summary, Glossary and List of Pre-defined constants. [\#115](), [\#143]()
        * Update table and figure numbering [\#143]()
        * Format User Guide to eliminate grammatical and spelling errors [\#143]()
    * Developer Guide:
        * Added implementation details of the `Add` feature [\#89]()
        * Edited the sequence diagram for Add feature [\#89]() 
        * Add Activity diagram for Add feature [\#89]()
        * Added class diagrams for Add and Clear Feature [\#268]()
        * Added implementation details for Email Feature [\#265]()
        * Added Class, Activity, Sequence diagrams for Email Feature [\#265]() 
        * Added Class, Activity, Sequence diagrams for Help Feature [\#276]()

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#33](), [\#47](), [\#50](), [\#57](), [\#74](), [\#84](), [\#112](), [\#122](), [\#128](), [\#235]()
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3](), [4](), [5](), [6](), [7](), [8](), [9](), [10](), [11]())


* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
