---
layout: page
title: Joshua Teo's Project Portfolio Page
---

### Project: ClientConnect

ClientConnect is a desktop client address book application for insurance agents to manage and keep track of their clientele seamlessly and efficiently. Custom tailored to suit the needs of insurance agents, ClientConnect surpasses other similar applications by offering features such as grouping of clients by packages, keyboard shortcuts, a gorgeous GUI, and more.

Given below are my contributions to the project.

* **New Feature**:
  * ***clip*** command ([\#34](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/34), [\#70](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/70))
    * Functionality: Allows efficient copying of a client's information onto the user's clipboard.
    * Justification: It is tedious to manually copy out all of a client's details as they can be long at times.
    * Enhanced in v1.3 to allow users to specify either name or index for the client they wish to copy.
    * `NameExistsPredicate` class to compare & filter client list with provided `Name`, for facilitation of this command.
    * Testing for all relevant classes.  
  * ***prioList*** command  ([\#64](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/64))
    * Functionality: Allows user to sort and display their client list by the priority level of their tags.
    * Justification: Makes it easy for the user to view which clients should be prioritised.
    * `TagPriorityComparator` class to compare `Persons` and their tag priority level, for facilitation of this command.
    * Method in `Model` to sort client list with a given comparator.
    * Testing for all relevant classes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jetrz&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
  * User Guide:
    * Added description and instructions for clip and prioList command, supported with clear visuals. ([\#162](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/162))
  * Developer Guide:
    * Under [implementation section](../DeveloperGuide.md#Implementation), added descriptions on how clip and prioList works, supported with sequence diagrams. ([\#54](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/54), [\#162](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/162))
    * Updated section on use cases. ([\#176](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/176))
    * Added section on product scope. ([\#16](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/16))
    * Added section on user stories. ([\#16](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/16))
    * Added section on non-functional requirements. ([\#16](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/16))
    * Improved overall styling.
  
* **Project management**:
  * Assigned & closed issues
  * Reviewed and merged PRs ([\#32](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/32), [\#65](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/65))
  * Created and assigned tags
  * Submitted all assigned deliverables by given milestone deadlines

* **Community**:
  * Reported [bugs](https://github.com/jetrz/ped/issues) for other teams
