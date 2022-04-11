---
layout: page
title: Sophie's Project Portfolio Page
---

### Project: UniBook

UniBook is a desktop app for students to manage their university contacts related to their studies in an organized manner, optimized for command-line interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
UniBook is a brownfield project based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

Given below are my contributions to the project.

* **New Feature**:
    * `edit INDEX o/person [n/NAME] [p/PHONE] [e/EMAIL] [of/OFFICE] [t/TAG]` now allows editing of person attributes by index on people view
    * `edit INDEX o/person [m/MODULE] [g/GROUPNAME]` now allows adding of a person to a group of a module on people view
    * `edit INDEX o/person [nm/NEWMOD]` now allows adding person to a new module on people view
    * `edit INDEX o/module [n/NAME] [m/MODCODE]` now allows editing module name and/or module code on module view
    * `edit INDEX o/group m/MODULE [g/GROUPNAME] [mt/INDEX DATETIME]` now allows editing of a group in a module's group name and/or meeting times on module view
    * `edit INDEX o/keyevent ke/INDEX [type/TYPE] [dt/DATETIME]` now allows editing of key event types and/or date time of key events from a module by index on module view
    * `edit INDEX o/group m/MODULE [g/GROUPNAME] [mt/INDEX DATETIME]` now allows editing of a group in a module's group name and/or meeting times on group view

* **Enhancements to existing features**:
    * Able to edit different fields of person/module/group/key event on people, module, group view respectively.
    * Compulsory fields vary depending on `o/OPTION` tag provided
* **Implementation Challenges**
    * It was challenging to implement the edit command for the different classes, since a descriptor to copy over the original and changed fields had to be created for each in order to make a successful edit. 
    * Checking for which type of object to edit and checking for the different possible combinations of tags to see whether it is valid
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=sophiien&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Prioritisation of `edit` features by implementing the most important ones first
    * Contributed to team meetings

* **Documentation**:
    * User Guide
        * Updated the new features and format that fall under `edit` command
        * Updated the command summary for the `edit` command
    * Developer Guide
        * Updated the edit command sequence diagram
        * Updated User stories and Use cases relating to `edit` command

* **Community**:
    * Provided help and suggestions to teammates after reviewing PR
    * Links to PRs reviewed with non trivial comments: [#83](https://github.com/AY2122S2-CS2103-W16-1/tp/pull/83)
* **Tools**:
