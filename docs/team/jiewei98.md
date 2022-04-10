---
layout: page
title: Tan Jie Wei's Project Portfolio Page
---

### Project: ClientConnect

ClientConnect is a desktop client address book application for insurance agents to manage and keep track of their clientele seamlessly and efficiently. Custom tailored to suit the needs of insurance agents, ClientConnect surpasses other similar applications by offering features such as grouping of clients by packages, keyboard shortcuts, a gorgeous GUI, and more.

Given below are my contributions to the project.

* **New Feature**:
  * ***undo/redo*** command ([\#40](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/40), [\#47](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/47), [\#48](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/48), [\#65](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/65), [\#159](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/159), [\#168](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/168), [\#178](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/178))
    * Functionality: Allows user to undo any accidental changes made to the AddressBook.
    * Justification: It is tedious to have to add all of a client's details from scratch if the user accidentally deleted a client from the AddressBook. User might not even remember client's details to re-add them back into the AddressBook.
    * Enhanced in v1.3 to allow users to undo an accidental clearing of the AddressBook and can also undo/redo changes made to tags.
    * `UndoRedoStorage` class to store AddressBook states, to allow for undo-ing and redo-ing changes made to the AddressBook
    * Method in `ModelManager` to make a deep copy of AddressBook state for storage in `UndoRedoStorage`.
    * Testing for all relevant classes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jiewei98&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
    * User Guide:
        * Added description and instructions for undo/redo command, supported with clear visuals. ([\#163](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/163))
    * Developer Guide:
        * Under [implementation section](../DeveloperGuide.md#Implementation), added descriptions on how undo/redo works, supported with sequence diagrams. ([\#57](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/57), [\#164](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/164))
        * Updated section on use cases. ([\#18](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/18))
        * Added section on product scope.
        * Added section on user stories.
        * Added section on non-functional requirements.
        * Improved overall styling.

* **Project management**:
    * Assigned & closed issues
    * Reviewed and merged PRs ([\#16](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/16))
    * Submitted all assigned deliverables by given milestone deadlines

* **Community**:
    * Reported [bugs](https://github.com/jiewei98/ped/issues) for other teams
