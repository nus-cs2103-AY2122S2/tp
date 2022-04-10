---
layout: page
title: Tan Chin Kiat's Project Portfolio Page
---

### Project: Tracey

Tracey is a desktop app for managing health statuses of NUS Hall Residents. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**New Features**:
  * Added a filter command that allows the user to retrieve certain entries using a filter.
    * What it does: Allows the user to use the `filter` command to display a list of a certain group of students. By inputting a certain covid status, faculty, and/or block, the user can retrieve a list of students which fit the desired criteria.
    * Justification: This feature largely improves the functionality of the app as it helps the user to retrieve the details of students who fit a desired set of criteria.
    * Highlights: It greatly increases the speed and convenience at which the user can isolate a group of students with a certain covid status, instead of viewing the entire list of students in the address book.

  * Added an undo and redo command that allows the user to reverse actions.
    * What it does: Allows the user to use `undo` to undo a single wrong action. An `undo` can also be reduced by a `redo`.
    * Justification: This feature significantly improves the user experience as they are able to undo their own mistakes.
    * Highlights: Most commands can be reversed with `undo`, and redone with `redo`.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tanchinkiat99&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Set up issues tracker on GitHub to manage project tasks.
    * Managed releases `v1.3` - `v1.3b` (2 releases) on GitHub.
    * Reviewed and approved pull requests from teammates.
    * Helped to coordinate submissions and admin tasks.

* **Enhancements to existing features**:
    * Added function in Person class to check if Person object matches a given covid status (Pull request [\#50]())
    * Wrote additional tests for filter feature to ensure method coverage is at 100% for both function and parser class (Pull requests [\#52](), [\#84]())
    * Fixed bug in several features including filter and edit (Pull requests [\#133](), [\#257](), [\#258]())
    * Changed implementation for edit (Pull request [\#258]())
    * Refined undo and redo to prevent consecutive usage (Pull requests [\#239]())
    * Updated single word command checker to reduce nesting (Pull request [\#136]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `filter`, `undo` and `redo`: [\#139]()
        * Did grammatical tweaks to all existing documentation: [\#139]()
        * Added a glossary to contain table of technical terms and table of commands and their constraints: [\#139](), [\#142]()
        * Updated documentation of `edit` command to suit new implementation: [\#258]()
    * Developer Guide:
        * Added implementation details of the `filter` feature.
        * Updated and standardized format of use cases across features.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#57](), [\#60](), [\#86](), [\#100](), [\#134](), [\#137](), [\#138](), [\#140](), [\#143](), [\#259]()
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3](), [4](), [5](), [6](), [7](), [8](), [9](), [10]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
