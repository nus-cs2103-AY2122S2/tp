---
layout: page
title: Ryan's Project Portfolio Page
---

### Project: UniBook

UniBook is a desktop app for students to manage their university contacts related to their studies in an organized manner, optimized for command-line interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
UniBook is a brownfield project based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

Given below are my contributions to the project.

* **New Feature**: 
    * `delete [INDEX] p/ e/ t/[TAG] of/` now allows deletion of person attributes by index on people view
    * `delete o/module m/[MODULECODE]` now allows deleting of modules by module code
    * `delete o/group m/[MODULECODE] g/[GROUP]` now allows deleting of groups by name
    * `delete [INDEX] stu/[INDEX]` now allows removing students from a module by index on module view
    * `delete [INDEX] prof/[INDEX]` now allows removing professors from a module by index on module view
    * `delete [INDEX] g/[INDEX]` now allows removing groups from a module by index on module view
    * `delete [INDEX] ke/[INDEX]` now allows removing key events from a module by index on module view
    * `delete [INDEX] stu/[INDEX]` now allows removing a student from a group by index on group view
    * `delete [INDEX] mt/[INDEX]` now allows deleting a meeting time from a group by index on group view
    * Added the Student, Professor, Office, Group classes

* **Enhancements to existing features**:
    * `delete [INDEX]` now deletes a module at INDEX if on module view and deletes a group at INDEX if on group view on top of deleting person at INDEX from people view

* **Implementation Challenges**
    * It was challenging to implement a command with that many cases, the cases in the `DeleteCommandParser` and `DeleteCommand` classes had to be carefully separated to prevent bugs on top of catching errors in the user command
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=ryanwalterlee&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Ensured that I met deadlines despite implementing close to 80% of all new `delete` features in v1.3
    * Contributed to team meetings

* **Documentation**:
    * User Guide
        * Updated the new features that fall under `delete` command
        * Updated the command summary for the `delete` command
    * Developer Guide
        * Created the overall activity diagram of the application
        * Updated the delete command sequence diagram
        * Updated User stories and Use cases relating to `delete` command

* **Community**:
    * Alerted other teammates about bugs in their feature whenever noticed during my own testing
        * For example, I needed to use `list` command to change pages, which sometimes led me to discover bugs
        * For example, I needed to use `add` command to add entities to test `delete`, which also led me to discover bugs
    * Provided help to teammates whenever it seemed like they needed help with implementation through private message or meet up
    * PRs reviewed: 11
    * Links to PRs reviewed with non trivial comments: [#46](https://github.com/AY2122S2-CS2103-W16-1/tp/pull/46), [#96](https://github.com/AY2122S2-CS2103-W16-1/tp/pull/96)
* **Tools**:
