---
layout: page
title: Eugene Chia's Project Portfolio Page
---

### Project: NUSearch

NUSearch is a desktop app for managing NUS staff contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo commands.
    * What it does: allows the user to undo previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    * Credits: Took inspiration from the [memento model](https://medium.com/design-patterns-in-python/memento-pattern-eba610b3b59c)

* **New Feature**: Add optional Telegram field to contacts
  * What it does: allows the user to create contacts with optional Telegram fields
  * Justification: This simulates the use case of faculty members having the choice of including their Telegram handle.
  * Highlights: This enhancement uses the Optional Java class which helps to ensure errors are thrown at appropriate junctures.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=W11-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=eugenechiaay&tabRepo=AY2122S2-CS2103T-W11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.1` - `v1.4` (5 releases) on GitHub.

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage from 72% to 77% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added mock up for UI [/#26](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/26)
        * Added documentation for the features `delete`, `fav` and `tag` [\#30](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/30)
        * Did cosmetic tweaks to existing documentation of features `add`, `edit`, `delete`, `tag`, `undo`, `redo`, `copy-email`, `copy-phone`, `help`, `clear`, `exit` [\#30](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/30)
        * Added screenshots for the features `delete`, `fav`, `help`, `list-fav`, `redo-delete` and `undo-delete` [\#79](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/79)
    * Developer Guide:
        * Added implementation details of the `delete` feature.
        * Added use case for NUSearch [\#39](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/39).
    * Diagrams:
        * Updated CM Class Diagram, Delete Sequence Diagram, Logic Class Diagram, Model Class Diagram, Parser Classes, Storage Class Diagram, UiClassDiagram. [\#92](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/92)
    * README:
        * Updated README for NUSearch [\#34](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/34)

* **Team-Based tasks**:
  * Maintained the issue tracker
  * Managed releases
  * Assigned and delegated issues to members

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#23](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/23), [\#74](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/74), [\#77](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/77), [\#80](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/80)
    * Number of PRs reviewed and approved: 22 [\#23](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/23), [\#29](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/29), [\#36](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/36), [\#37](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/37), [\#38](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/38), [\#74](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/74), [\#77](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/77), [\#80](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/80), [\#87](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/87), [\#88](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/88), [\#89](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/89), [\#90](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/90), [\#91](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/91), [\#93](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/93), [\#98](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/98), [\#99](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/99), [\#103](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/103), [\#154](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/154), [\#157](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/157), [\#160](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/160), [\#162](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/162), [\#166](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/166)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/191), [2](https://github.com/nus-cs2103-AY2122S2/forum/issues/117))
    * Reported bugs and suggestions for other teams in the class: [1](https://github.com/eugenechiaay/ped/issues/1), [2](https://github.com/eugenechiaay/ped/issues/2), [3](https://github.com/eugenechiaay/ped/issues/3), [4](https://github.com/eugenechiaay/ped/issues/4), [5](https://github.com/eugenechiaay/ped/issues/5), [6](https://github.com/eugenechiaay/ped/issues/6), [7](https://github.com/eugenechiaay/ped/issues/7), [8](https://github.com/eugenechiaay/ped/issues/8), [9](https://github.com/eugenechiaay/ped/issues/9), [10](https://github.com/eugenechiaay/ped/issues/10), [11](https://github.com/eugenechiaay/ped/issues/11), [12](https://github.com/eugenechiaay/ped/issues/12), [13](https://github.com/eugenechiaay/ped/issues/13), [14](https://github.com/eugenechiaay/ped/issues/14), [15](https://github.com/eugenechiaay/ped/issues/15)
    
