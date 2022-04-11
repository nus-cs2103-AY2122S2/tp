---
layout: page
title: Wee Heng's Project Portfolio Page
---

### Project: HustleBook

HustleBook is a desktop app specially catered towards financial advisors for **managing client details** and **scheduling meetings efficiently** without the need to lift their hands off the keyboard.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

This project is built on top of the **AB3** application that is used for teaching Software Engineering principles.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort clients through the `sort` command.
    * What it does: Allows the user to sort clients by meeting time, name, previous date met and salary.
    * Justification: This feature improves the functionality of the product significantly. This feature allows financial advisors to sort and manage clients for everyday operations. 
    * Highlights: This enhancement required an in-depth analysis of design alternatives such as Priority Queues. The implementation was challenging as it required changes to existing classes like UniquePersonList and an in-depth understanding of `ObservableList` and `FXCollections`.

* **New Feature**: Added the ability to list flagged or unflagged clients.
  * What it does: Allows the user to list clients based on whether they are flagged or not. User can list only flagged clients, only unflagged clients or both.
  * Justification: This feature improves the functionality of HustleBook for users by further finetuning what HustleBook displays especially when there are many clients.
  
* **New Feature**: Added a `Comparator` package for `person`.
  * What it does: Allows `sort` to sort clients based on `Comparator`.
  * Justification: This feature is important for the user to be able to sort by different fields.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=decaxical&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed releases `v1.2` - `v1.3` (2 releases) on GitHub.
    * Participated and ensured productive meetings.
  
* **Documentation**:
    * User Guide:
        * Added documentation for the features `sort` and `list` [\#119](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/119/files) and [\#166](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/166)
    * Developer Guide:
        * Added implementation details of the `sort` and `list` feature.

* **Community**:
    * Reported bugs and suggestions for other teams in the class ([PE-D](https://github.com/Decaxical/ped/issues))
