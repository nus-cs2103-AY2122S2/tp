---
layout: page
title: Jia Ming's Project Portfolio Page
---

### Project: NUSearch

NUSearch is a desktop app for managing NUS contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the Faculty field for each contact.
  * What it does: allows the user to add a faculty for each contact
  * Justification: This feature improves the product significantly because a user could now further organise their contacts by specifying the faculty of each contact. This also makes the application university specific, hence contributing to the context of the application.
  * Highlights: This enhancement affects some existing commands. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  
* **New Feature**: Added the ability to copy email of contacts.
  * What it does: allows the user to copy the email of contacts.
  * Justification: This feature improves the product significantly because a user could now copy email of a contact just by a short command and then paste it efficiently on third party application should they require it.
  * Highlights: This enhancement affects some parts of the model and it required an in-depth analysis of design alternatives. The implementation was challenging as it required imports from java.awt package which was unfamiliar to the team.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=W11-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=SimJM&tabRepo=AY2122S2-CS2103T-W11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.1` - `v1.4` (5 releases) on GitHub.

* **Enhancements to existing features**:
    * Updated the GUI color scheme and interface (Pull requests [\#80](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/80), [\#103](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/103))
      * Justification: This improves the user interface significantly because it binds the culture of the university into NUSearch, allowing users to have sense of their school while using NUSearch.
      * Highlight: The font used for NUSearch is specially chosen to give off a classy yet bookish vibes.
    * Updated the User Inferface of NUSearch Help Window (Pull requests [\#67](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/67))
      * What it does: This allows the user to learn about basic commands without going to the User Guide
      * Justification: This improves the user experience significantly because a user could conveniently access guides for commands with just a command `help`.
      * Highlight: This improvement is accompanied by a scrollable window which allows the user scroll through for help related to commands. 

* **Documentation**:
    * User Guide:
        * Modified documentation for adaptation from AB-3 to NUSearch [\#52](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/52)
        * Added documentation for the Quick start:  features and future features [\#28](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/28)
    * Developer Guide:
        * Added use case for NUSearch [\#38](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/38).
        * Added implementation details of `Components Manager`. [\#91](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/91)
    * Diagrams:
        * Updated Delete Sequence Diagram and Logic Class Diagram. [\#91](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/91)

* **Team-Based tasks**:
  * Maintaining the issue tracker
  * Release management
  * Assigning of tasks to members

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#72](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/72), [\#159](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/159)
    * Number of PRs reviewed and approved: 22 [\#27](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/27), [\#29](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/29), [\#33](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/33), [\#39](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/39), [\#40](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/40), [\#53](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/53), [\#54](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/54), [\#59](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/59), [\#72](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/72), [\#73](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/73), [\#79](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/79), [\#92](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/92), [\#94](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/94), [\#97](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/97), [\#98](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/98), [\#101](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/101), [\#105](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/105), [\#155](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/155), [\#156](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/156), [\#159](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/159), [\#165](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/165), [\#167](https://github.com/AY2122S2-CS2103T-W11-4/tp/pull/167)
    * Reported bugs and suggestions for other teams in the class (total: 12): [1](https://github.com/SimJM/ped/issues/1), [2](https://github.com/SimJM/ped/issues/2), [3](https://github.com/SimJM/ped/issues/3), [4](https://github.com/SimJM/ped/issues/4), [5](https://github.com/SimJM/ped/issues/5), [6](https://github.com/SimJM/ped/issues/6), [7](https://github.com/SimJM/ped/issues/7), [8](https://github.com/SimJM/ped/issues/8), [9](https://github.com/SimJM/ped/issues/9), [10](https://github.com/SimJM/ped/issues/10), [11](https://github.com/SimJM/ped/issues/11), [12](https://github.com/SimJM/ped/issues/12))
    
