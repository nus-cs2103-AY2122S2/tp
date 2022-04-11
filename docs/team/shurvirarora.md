---
layout: page
title: Shurvir Arora's Project Portfolio Page
---

### Project: NUSearch

NUSearch is a desktop app for managing NUS contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find contacts via their tag. (tag ...)
    * What it does: allows the user to find all contacts based on the tag they're associated with.
    * Justification: This feature improves the product significantly because a user could find trouble in finding contacts initially and may want to organise their contacts further by assigning a tag to them. As such, with this feature they can filter out contacts based on the tag specified.
    * Highlights: This enhancement affects some existing commands. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added the Role field for each contact.
    * What it does: allows the user to add a role for each contact 
    * Justification: This feature improves the product significantly because a user could now further organise their contacts by specifying the role of each contact. This also makes the application university specific, hence contributing to the context of the application.
    * Highlights: This enhancement affects some existing commands. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added the ability to find contacts matching **any** of the keywords (keywords: Name, Faculty, Role). (find-wide ...)
  * What it does: allows the user to find all contacts that match **any** of the keywords specified.
  * Justification: This feature improves the product significantly because a user could now find contacts not just by Name but also by their Faculty and Role. This also allows users to broaden their search results as contacts that match **any** of the keywords specified will show up in the result list.
  * Highlights: This enhancement affects some existing commands. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added the ability to find contacts matching **all** of the keywords (keywords: Name, Faculty, Role). (find ...)
  * What it does: allows the user to find all contacts that match **all** of the keywords specified.
  * Justification: This feature improves the product significantly because a user could now find contacts not just by Name but also by their Faculty and Role. This also allows users to narrow down their search results as only contacts that match **all** the keywords specified will show up in the result list.
  * Highlights: This enhancement affects some existing commands. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=shurvirarora&breakdown=true)

* **Project management**:
    * Managed releases `v1.1` - `v1.4` (5 releases) on GitHub.

* **Enhancements to existing features**:
    * Improved the Find feature to include Faculty and Role in addition to Name. Users can now find contacts via Name, Role and Faculty.
    * Wrote additional tests for existing features to increase coverage. (Pull requests [\#101](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/101), [\#77](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/77), [\#59](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/59))

* **Documentation**:
    * User Guide:
        * Added `Introduction` section in UG, explaining aim and purpose of NUSearch.
        * Added documentation for the features `list`,`fav`, `unfav`, `list-fav`, `find`, `find-wide` [\#29](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/29/files), [\#154](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/154)
        * Created Command Summary tables comprising `General Commands`, `Basic Commands`, `Favourite Commands`, `Find Commands` and `Copy Commands`. [\#99](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/99), [\#154](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/154)
        * Did cosmetic tweaks to existing documentation of features `add`, `edit`, `delete`, `tag`, `undo`, `redo`, `copy-email`, `copy-phone`, `help`, `clear`, `exit` : [\#35](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/35), [\#40](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/40), [\#154](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/154)
        * Organised structure and commands into sub-categories: `General Commands`, `Basic Commands`, `Favourite Commands`, `Find Commands` and `Copy Commands`. [\#99](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/99)
        * Improved tips and notes for every feature. [\#160](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/160)
        * Added table of acceptable values for `faculty` and `role` fields. [\#160](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/160)
    * Developer Guide:
        * Added implementation details of `how architecture components interact with each other` and `sequence diagram`. [\#90](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/90)
    * Diagrams:
        * Updated Architecture Diagram and Architecture Sequence Diagram. [\#87](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/87)
    * About Us:
        * Update roles and responsibilities of team members [\#53](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/53)

* **Team-Based tasks**:
    * Maintaining the issue tracker
    * Release management
    * Assigning of tasks to members
    

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#75](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/75), [\#98](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/98)
    * Number of PRs reviewed and approved: 15 [\#26](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/26), [\#28](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/28), [\#34](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/34), [\#38](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/38), [\#39](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/39), [\#52](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/52), [\#67](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/67), [\#71](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/71), [\#75](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/75), [\#77](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/77), [\#78](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/78), [\#86](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/86), [\#98](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/98), [\#161](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/161), [\#163](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/163)
    
