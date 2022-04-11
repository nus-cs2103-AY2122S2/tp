---
layout: page
title: Bryan Zheng's Project Portfolio Page
---

### Project: RealEstatePro

RealEstatePro is a desktop application for managing your client details for real estate agents. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). With RealEstatePro managing your clients will be a breeze by using the various features such as reminders, client matching and many more!

Given below are my contributions to the project.

* **New Feature**: Added the ability to represent real estate listings using new classes: `Property`, `Region`, `Size`, `Price`.
  * What it does: Represents the real estate listing of a client using the `Property` class which can be held by a `Person` object.
  * Justification: This feature is required for the basic functionality of the application.
  * Highlights: This feature was challenging to implement as it required significant changes to existing commands, and their unit tests.

* **New Feature**: Added the ability to sort clients.
  * What it does: Allows the user to sort clients according to one or more of their attributes. The sorting order for each attribute can also be reversed individually. This feature is integrated with the find feature, meaning that it is possible to sort the filtered list obtained after using the `find` command.
  * Justification: This feature improves the product significantly because a user can have a large list of clients, and the application should provide a convenient way to organize them.
  * Highlights: This feature was difficult to test as there are a large number of possible sorting orders.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=w16-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=Ashuh&tabRepo=AY2122S2-CS2103-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Maintained the issue tracker

* **Documentation**:
  * User Guide:
    * Added documentation for the command format used to specify a `Property`: [\#50](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/50)
    * Added documentation for the `sort` feature: [\#67](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/67)
    * Improved readability of user guide by using alerts: [\#187](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/187)
  * Developer Guide:
    * Added implementation details of the `Property` feature.
    * Added implementation details of the `sort` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#74](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/74), [\#96](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/96), [\#43](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/43), [\#186](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/186), [\#94](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/94)
  * Reported bugs in team member features (examples: [1](https://github.com/AY2122S2-CS2103-W16-4/tp/issues/68))
  * Helped team members with issues related to their features: [\#55](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/55)
