---
layout: page
title: Tan Chin Kiat's Project Portfolio Page
---

### Project: Tracey

Tracey is a desktop app for managing health statuses of NUS Hall Residents. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**New Features**:
  * Added the `filter` command.
    * **What it does**: Allows the user to display a list of a students filtered by a certain set of criteria. By inputting a covid status, faculty, and/or block, the user can retrieve a list of students which fit the desired criteria.
    * **Justification**: This feature largely improves the functionality of the app as it helps the user to retrieve the details of students who fit a desired set of criteria.
    * **Highlights**: It greatly increases the speed and convenience at which the user can isolate a group of students with a certain covid status, instead of viewing the entire list of students in the address book. The addition of this feature required thorough understanding of the structure and logic of the AddressBook3 (AB3).

  * Added `undo` and `redo` commands.
    * **What it does**: Allows the user to use `undo` to undo a single `add`, `delete`, or `edit` command. An `undo` can also be reversed by a `redo`.
    * **Justification**: This feature significantly improves the user experience as they are able to undo their own mistakes, providing a safety net on which they can use the app.
    * **Highlights**: The implementation of these 2 features involved altering the AddressBook class and Model interface. Additional fields and methods had to be added in order to store the relevant states of the address book so that these states can be retrieved and used to replace the current state when `undo` or `redo` is called. This was challenging as it required a deep understanding of how the different components of Tracey interacted with one another.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tanchinkiat99&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Set up issues tracker on GitHub to manage project tasks.
    * Managed releases `v1.3` - `v1.3b` (2 releases) on GitHub.
    * Reviewed and approved pull requests from teammates.
    * Helped to coordinate submissions and admin tasks.
    * Enforced documentation standards to ensure that all documentation of our code is well-organized and standardized.

* **Enhancements to existing features**:
    * Added methods in Person class to check if Person object matches a given covid status, faculty or block (Pull request [\#50](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/50))
    * Wrote additional tests for `filter` to ensure method coverage is at 100% for both function and parser class (Pull requests [\#52](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/52), [\#84](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/84))
    * Fixed bug in several features including `filter` and `edit` (Pull requests [\#133](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/133), [\#257](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/257), [\#258](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/258))
    * Changed implementation for `edit` to prevent the disruption of the uniqueness of certain values in the address book (Pull request [\#258](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/258))
    * Refined `undo` and `redo` to prevent consecutive usage (Pull requests [\#239](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/239))
    * Updated single word command checker to reduce nesting (Pull request [\#136](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/136))
    * Enhanced `clear` function to reset all states of the address book (Pull request [\#305](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/305))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `filter`, `undo` and `redo`: [\#139](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/139)
        * Did grammatical tweaks to all existing documentation: [\#139](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/139)
        * Added a glossary to contain table of technical terms and table of commands and their constraints: [\#139](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/139), [\#142](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/142)
        * Updated documentation of `edit` command to suit new implementation: [\#258](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/258)
    * Developer Guide:
        * Added implementation details of the `filter` feature.
        * Updated and standardized format of use cases across features.
        * Added manual testing cases for `filter`, `redo` and `undo`.
        * Organized and numbered all sections.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#57](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/57),
      [\#60](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/60),
      [\#86](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/86),
      [\#100](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/100),
      [\#134](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/134),
      [\#137](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/137),
      [\#138](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/138),
      [\#140](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/140),
      [\#143](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/143),
      [\#259](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/259),
      [\#268](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/268),
      [\#269](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/269),
      [\#272](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/272),
      [\#273](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/273),
      [\#284](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/284),
      [\#294](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/294),
      [\#295](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/295),
      [\#296](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/296),
      [\#299](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/299),
      [\#300](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/300),
      [\#302](https://github.com/AY2122S2-CS2103T-T12-3/tp/pull/302)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/tanchinkiat99/ped/issues/1),
      [2](https://github.com/tanchinkiat99/ped/issues/2),
      [3](https://github.com/tanchinkiat99/ped/issues/3),
      [4](https://github.com/tanchinkiat99/ped/issues/4),
      [5](https://github.com/tanchinkiat99/ped/issues/5),
      [6](https://github.com/tanchinkiat99/ped/issues/6),
      [7](https://github.com/tanchinkiat99/ped/issues/7),
      [8](https://github.com/tanchinkiat99/ped/issues/8),
      [9](https://github.com/tanchinkiat99/ped/issues/9),
      [10](https://github.com/tanchinkiat99/ped/issues/10))

* **Tools**:
    * Integrated a new Github plugin (CircleCI) to the team repo
