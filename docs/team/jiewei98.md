---
layout: page
title: Tan Jie Wei's Project Portfolio Page
---

### Project: ClientConnect

ClientConnect is a desktop client address book application for insurance agents to manage and keep track of their clientele seamlessly and efficiently. Custom tailored to suit the needs of insurance agents, ClientConnect surpasses other similar applications by offering features such as grouping of clients by packages, keyboard shortcuts, a gorgeous GUI, and more.

Given below are my contributions to the project.

* **New Feature**:
  * ***undo/redo*** command
    * Functionality: Allows user to undo any accidental changes made to the AddressBook.
    * Justification: It is tedious to have to add all of a client's details from scratch if the user accidentally deleted a client from the AddressBook. User might not even remember client's details to re-add them back into the AddressBook.
    * Enhanced in v1.3 to allow users to undo an accidental clearing of the AddressBook.
    * `UndoRedoStorage` class to store AddressBook states, to allow for undo-ing and redo-ing changes made to the AddressBook
    * Method in `ModelManager` to make a deep copy of AddressBook state for storage in `UndoRedoStorage`.
    * Testing for all relevant classes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jiewei98&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Assigned & closed issues
    * Reviewed and merged PRs
    * Created and assigned tags
    * Submitted all assigned deliverables by given milestone deadlines

* **Documentation**:
    * User Guide:
        * Added description and instructions for undo/redo command, supported with clear visuals.
    * Developer Guide:
        * Under [implementation section](../DeveloperGuide.md#Implementation), added descriptions on how undo/redo works, supported with sequence diagrams.
        * Added section on product scope.
        * Added section on user stories.
        * Added section on non-functional requirements.
        * Improved overall styling.
