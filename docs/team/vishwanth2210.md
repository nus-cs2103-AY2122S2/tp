---
layout: page
title: Vishwanth's Project Portfolio Page
---

### Project: HustleBook

HustleBook is a desktop address book application aimed towards *financial advisors* to help them ***store*** and ***manage client details***. 
The user interacts with this application using Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created using JavaFX. It is written in Java, and has about 15 kLoC.

This project is built on top of the **AB3** application that is used for teaching Software Engineering principles.

Given below are my contributions to the project.

### Contributions
* **New Feature**: Added the ability to undo and redo previous commands.
    * What it does: allows the user to undo all previous commands one at a time. All preceding undo commands can be reversed by using the redo command.
    * Justification: This feature allows users to rectify any mistakes made through the use of a single command. This helps improve the users productivity and makes this product more user-friendly. 
    * Highlights: This command was challenging in the aspect on figuring out how to make this work with minimal changes but compatible with all commands. 
    * Credits: *This implementation is loosely based on AB3's future implementation recommendation for undo/redo to save the entire HustleBook data*
    * Implemented the necessary JUnit test cases. [\#250](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/250)


* **New Feature**: Added a `salary` attribute to allow users to store a clients' salary information and its necessary JUnit test cases. 


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=vishwanth2210&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


* **Project management**:
    * Set up the Github team organisation.
    * Updated the product icon.
    * Renamed the product to HustleBook and all instances of Addressbook to HustleBook.
    * Created and released `v1.2` - `v1.4` (5 releases) on GitHub.
    * Updated gradle file to enable assertions and update the name of the jar file generated to HustleBook.
    * Updated necessary diagrams to match current implementation [\#252](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/252), [\#236](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/236)
    * Updated user stories


* **Enhancements to existing features**:
    * Updated the GUI to better display the `salary`, `prevMeet` and `nextMeet` attributes (Pull requests [\#124](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/124), [\#125](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/125))
    * Created a `getPersonListIndex` method to allow commands to use `name` instead of `index` to find the desired client to work on. Implemented this on the first iteration of `edit` and `delete` command (Pull requests [\#64](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/64), [\#71](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/71))
    * Modified test cases to ensure tests for `edit` and `delete` works with the change from `index` to `name` (Pull requests [\#64](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/64), [\#71](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/71))


* **Documentation**:
    * User Guide:
        * Added documentation for the features `undo` and `redo` 
    * Developer Guide:
        * Added implementation details of the `salary` attribute.
        * Added implementation details of `undo` command.


* **Community**:
    * Reviewed many PRs
    * Reported bugs and suggestions for other teams in the class
