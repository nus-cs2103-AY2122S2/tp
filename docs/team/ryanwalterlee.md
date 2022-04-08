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
    * Added the Student and Professor class to model the different types of people in the Unibook

* **Enhancements to existing features**:
    * `delete [INDEX]` now deletes a module at INDEX if on module view and deletes a group at INDEX if on group view on top of deleting person at INDEX from people view

* **Implementation**
    * Readability of code is highly prioritised, giving meaningful variable names and splitting long complicated lines into short simple lines
    * Even though, the command now had many features, I ensured to reduce the use of nested if-else to maintain the same level of code whenever possible. This will increase the readability of my code that can help my teammates spot bugs in my code more easily should they find one.
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=ryanwalterlee&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Ensured that I met deadlines despite implementing close to 80% of the new features in v1.3
    * Tested my code before making a PR to ensure less time is wasted by teammates testing my code
    * Contributed to discussions about deciding how and what features should be implemented, how documentation can be done, and other issues
    * Quick to respond to messages on Telegram group should issues arise


* **Documentation**:
    * User Guide
        * Come up with the overall design and theme for the user guide
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
    * A severity-high issue was found on my feature during PE-D. I later discovered the reason and explained to my team about the reasons for the bug and some possible changes. This led to the team changing their parts of the Unibook which collectively caused this bug. The outcome is that the product is now more functional and less bug prone.
* **Tools**:
