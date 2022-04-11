---
layout: page
title: Tan Wei En's Project Portfolio Page
---

### Project: NUSearch

NUSearch is a desktop app for managing NUS contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to save frequently used contacts to a favourites list.
    * What it does: This feature allows the user to select contacts to be saved to a separate favourites list.
    * Justification: This feature improves the product significantly because it provides users easy access to contacts that they may frequently use, or contacts that deem important.
    * Highlights: The implementation of this feature required improving the existing attribute fields of contacts, which affected existing commands and test cases. Many existing commands had to be improved to accommodate this new feature, and test cases had to be supplemented.

* **New Feature**: Added the ability to delete frequently used contacts from a favourites list.
  * What it does: This feature allows the user to delete selected contacts from their favourites list.
  * Justification: This feature improves the product significantly because it allows users to make amendments to their favourites list, improving the functionality of the product.
  * Highlights: The implementation of this feature required improving the existing attribute fields of contacts, which affected existing commands and test cases. Many existing commands had to be improved to accommodate this new feature, and test cases had to be supplemented.

* **New Feature**: Added the ability for users to display a list of their favourite contacts.
  * What it does: This feature allows the user to display a list of their favourite contacts. The user can then execute commands on this list of favourites (eg edit, delete).
  * Justification: This feature allows users to view a list of their favourite contacts, granting users access to contacts that they may frequently use, or contacts that they deem are important. This function reduces the clutter from unimportant or unused contacts on the user’s screen and increases the ease of managing contacts.
  * Highlights: The implementation of this feature required an in-depth analysis of design alternatives as there were many potential ways to implement the feature. The implementation too was challenging as it required improving the existing attribute fields of contacts, which affected existing commands and test cases. Many existing commands had to be improved to accommodate this new feature, and test cases had to be supplemented.

* **New Feature**: Added the ability for users to copy the phone number of contacts to their clipboard.
  * What it does: This feature allows the user to copy the phone number of selected contacts to their clipboard.
  * Justification: This feature improves the product significantly because it allows the user to copy the phone number of a contact easily, which can then be pasted to other applications or text fields. 
  * Highlights: This enhancement affects some parts of the model and it required an in-depth analysis of design alternatives. The implementation was challenging as it required imports from an external java.awt package which was unfamiliar to the team.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=zoom&zFR=false&zA=tanweien&zR=AY2122S2-CS2103T-W11-4%2Ftp%5Bmaster%5D&zACS=198.13128430296376&zS=2022-02-18&zFS=&zU=2022-04-08&zMG=false&zFTF=commit&zFGS=groupByRepos)

* **Project management**:
    * Managed releases `v1.1` - `v1.4` (5 releases) on GitHub

* **Enhancements to existing features**:
    * Improved the `role` feature by allowing users to only select from a limited selection of pre-determined roles to reduce bugs: [\#165](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/165)
    * Improved the `faculty` feature by allowing users to only select from a limited selection of pre-determined faculties to reduce bugs: [\#165](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/165)
    * Improved how the `role` and `faculty` fields are displayed in the UI: [\#165](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/165)
    * Wrote additional tests for existing features to increase coverage: [\#74](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/74), [\#97](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/97), [\#165](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/165)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `fav`, `unfav`, `list-fav`, `copy-email` and `copy-phone` [\#98](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/98)
        * Improved on Frequently Asked Questions: [\#33](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/33)
        * Added a command summary table for existing commands: [\#33](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/33)
    * Developer Guide:
        * Updated `Design` and `Architecture` sections in the Developer Guide: [\#88](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/88)
        * Updated non-functional requirements in the Developer Guide: [\#36](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/36)
    * Diagrams:
        * Updated BetterModelClass Diagram: [\#88](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/88), [\#89](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/89)
        * Updated ComponentManagers Diagram: : [\#88](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/88), [\#89](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/89)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#164](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/164)
    * Reported bugs and suggestions for other teams in the class: [1](https://github.com/tanweien/ped/issues/1), [2](https://github.com/tanweien/ped/issues/2), [3](https://github.com/tanweien/ped/issues/3), [4](https://github.com/tanweien/ped/issues/4), [5](https://github.com/tanweien/ped/issues/5), [6](https://github.com/tanweien/ped/issues/6), [7](https://github.com/tanweien/ped/issues/7), [8](https://github.com/tanweien/ped/issues/8)
    * Reviewed and approved PRs: [\#22](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/22), [\#30](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/30), [\#35](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/35), [\#38](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/38), [\#164](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/164), [\#168](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/168), [\#169](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/169)

* **Team-based tasked**:
    * Maintained the issue tracker
    * Managed and published releases
    * Assigned tasks to members
  
