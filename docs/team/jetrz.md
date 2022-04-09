---
layout: page
title: Joshua Teo's Project Portfolio Page
---

### Project: ClientConnect

ClientConnect is a desktop client address book application for insurance agents to manage and keep track of their clietntele seamlesssly and efficiently. Custom tailored to suit the needs of insurance agents, ClientConnect surpasses other similar applications by offering features such as grouping of clients by packages, keyboard shortcuts, a gorgeous GUI, and more.

Given below are my contributions to the project.

* **New Feature**:
  * ***clip*** command 
    * Functionality: Allows efficient copying of a client's information onto the user's clipboard.
    * Justification: It is tedious to manually copy out all of a client's details as they can be long at times.
    * Enhanced in v1.3 to allow users to specify either name or index for the client they wish to copy.
    * `NameExistsPredicate` class to compare & filter client list with provided `Name`, for facilitation of this command.
    * Testing for all relevant classes.  
  * ***prioList*** command 
    * Functionality: Allows user to sort and display their client list by the priority level of their tags.
    * Justification: Makes it easy for the user to view which clients should be prioritised.
    * `TagPriorityComparator` class to compare `Persons` and their tag priority level, for facilitation of this command.
    * Method in `Model` to sort client list with a given comparator.
    * Testing for all relevant classes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jetrz&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
  * User Guide:
    * Added description and instructions for clip and prioList command, supported with clear visuals.
  * Developer Guide:
    * Under [implementation section](../DeveloperGuide.md#Implementation), added descriptions on how clip and prioList works, supported with sequence diagrams.
    * Added section on product scope.
    * Added section on user stories.
    * Added section on non-functional requirements.
    * Improved overall styling.
  
* **Project management**:
  * Assigned & closed issues
  * Reviewed and merged PRs
  * Created and assigned tags
  * Submitted all assigned deliverables by given milestone deadlines