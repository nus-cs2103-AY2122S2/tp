---
layout: page
title: Papattarada Apithanangsiri's Project Portfolio Page
---

### Project: ArchDuke

ArchDuke is an application to help students manage student contacts, student groups, and group tasks, optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI) created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: Wrote up to 80% of the total code written in the project. 
My contributions can be accessed via [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=punpun1643&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Punpun1643&tabRepo=AY2122S2-CS2103-W16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false)

* **New Features**: `addgroup` command (PR: [\#46](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/46), 
[\#86](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/86) 
and [\#97](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/97)) and `delgroup` command (PR: [\#65](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/65))
  * What it does: Allows users to create/delete a group in ArchDuke.
  * Justification: This feature is critical to the product, and without it the product would not function.
  The command allows users to create/delete a student group. This is one of the main value propositions of ArchDuke.
  * Highlights: This is the first newly added command in ArchDuke, which requires the in-depth understanding of how 
  the application parses user inputs and execute commands. It requires an understanding of how the application is designed 
  and how various classes and methods interact. It also provides skeleton for other commands to be developed. 
  A change to the structure of the code base was also made in this command implementation.

* **New Feature**: `deassign` command (PR: [\#91](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/91) and [\#114](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/114))
  * What it does: Allows users to deassign a student contact from an existing group.

* **New Features**: `viewcontact` command (PR: [\#92](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/92))
and `viewtask` command (PR: [\#73](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/73) and [\#90](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/90))
  * What it does: Allows users to view student contacts/tasks in an existing group.
  * Justification: This command is mainly to increase the user experience especially for the fast typists. Users could view the contacts/tasks
  in the group without having to scroll through the GUI.
  * Highlights: This feature requires an understanding of data storage and data retrieval from AB3.

* **New Feature**: `deltask` command (PR: [\#69](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/69))
  * What it does: Allows users to remove tasks from the existing group.
  * Justification: This feature is critical to the product, and without it the product would not function as a task management application.

* **Enhancements to existing feature**: `GUI` (PR: [\#51](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/51), [\#102](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/102), 
[\#104](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/104), [\#105](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/105 and [\#111](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/111)))
  * What it does: Revamp the UI of AB3 to ArchDuke.
  * Justification: This implementation changes the UI of the app greatly compared to that of AB3.
  * Highlights: This feature requires an in-depth understanding of the observer design patterns, 
  and the creating and updating of objects. It also requires a basic understanding of JavaFX, FXML, and CSS.

* **Enhancements to existing feature**: `help` command (PR: [\#56](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/56), 
[\#99](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/99) and [\#119](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/119))
  * What it does: Allows users to view in-app command format.
  * Justification: Enhance the user experience greatly as users would not have to copy-paste link to access the user guide.

* **Enhancements to existing feature**: `add` command (PR: [\#43](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/43))

* **Enhancements to existing feature**: `delete` command (PR: [\#103](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/103))
  * What it does: Allows users to remove a student contact from ArchDuke, which also removes all the contact from all pre-existing 
  assigned groups.

* **Enhancements to existing feature**: change to how `Person` is uniquely identified (PR: [\#125](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/125))
  * Justification: Initially in AB3, the person is uniquely identified by his/her name which enforced huge limitations 
  on how the app functions. The `Person` is then changed to uniquely identified by `EMAIL` and/or `PHONE` instead 
  as no two persons can have the same of these attributes. This enhances the usage of the app greatly as it now allows 
  the addition of contacts with same name and different attributes, which is a likely situation in the real-world.

* **Enhancements to existing feature**: modify case insensitivity of `Group` and `Task` (PR: [\#117](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/117))
  * Justification: Initially `Group` and `Task` are case-sensitive which hindered the user experience as it 
  increases the chance of typo errors. The enhancements allow fast typists to be able to use the app in a more convenient way.

* **Project management**:
  * Managed all releases: [v1.3trial](https://github.com/AY2122S2-CS2103-W16-3/tp/releases/tag/v1.3trial), 
  [v1.3](https://github.com/AY2122S2-CS2103-W16-3/tp/releases/tag/v1.3), [v1.3.1](https://github.com/AY2122S2-CS2103-W16-3/tp/releases/tag/v1.3.1) 
  and [v1.3.2](https://github.com/AY2122S2-CS2103-W16-3/tp/releases/tag/v1.3.2)
  * Maintained the issue tracker and milestones for the team repo and created every issue for the team.
  * Recorded all demo screenshots and videos.
  
* **Contributions to team-based tasks**:
  * Set up the GitHub team org/repo.
  * Renamed the product and changed the product icon.
  * Set up tools e.g., GitHub, Gradle, CI/CD.
  * Maintained the issue tracker.
  * Release management.
  * Updated all UG/DG that are not specific to a feature.
  * Automated GitHub Projects for task management.

* **Documentation**:
  * User Guide:
    * (to be updated) Added documentation for the features `delete` and `find` [\#72]()
    * (to be updated) Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * to be added soon

* **Community**:
  * (to be updated) PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * (to be updated) Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * (to be updated) Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * (to be updated) Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * to be added soon

* _{you can add/remove categories in the list above}_
