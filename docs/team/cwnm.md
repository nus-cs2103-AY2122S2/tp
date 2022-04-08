---
layout: page
title: Jun Heng's Project Portfolio Page
---

### Project: AgenSee

Given below are my contributions to the project.

* **New Feature**: Find Buyer and Seller command
    * An enhancement to the current buyer and seller command, including more fields such as house type and location
    * Split Find into both Buyer and Seller specific commands

* **New Feature**: Clear buyer, seller and all command
    * An enhancement to the current clear command, clears either list, or both lists.

* **New Feature**: List buyer, seller command
    * An enhancement to list command, can list each list seperately

* **Code contributed**: [RepoSense link]()
    * `findBuyerCommand` class
    * `findSellerCommand` class
    * `findSellerCommandParser` class
    * `findBuyerCommandParser` class
    * Predicate class for buyer location, house type and name
    * Predicate class for seller location, house type and name
    * Addition of clear-b, clear-s and clear-all and the corresponding classes
    * Addition of list-b, list-s and the corresponding classes
    * Addition of intial seller class and initial AddSeller methods
    * Addition of intial version of edit-b 
    * Refractoring of the project from "Person" to "Client", including classes, files, variables and methods
    * Fixed FXML bugs that caused the JAR application to start with Nullpointerexception
    * Associated test cases for the classes
    * Edited files to conform to checkstyle

* **Project management**:
    * Managed releases `v1.2` - `v1.3` (3 releases) on GitHub
    * Added a few issues

* **Documentation**:
    * User Guide:
        * Added documentation for the features `find-b` and `find-s`
        * Added documentation for the features `clear-b` and `clear-s` and `clear-all`
        * Added documentation for the features `list-b` and `list-s`
    * Developer Guide:
        * Added implementation details of the `find-b` and `find-s` feature.

* **Contributions to team tasks**:
    * Set up weekly meeting times accounting for our individual schedules
    * Set up meeting agenda for weekly meetings
    * Added weekly reminders for deadlines on tasks for that week, especially for time sensitive team tasks, this includes soft deadline such as code for demo
    * Built and released JAR for our team (v1.2, v1.3.trial, v1.3)
    * Submission of progress report

