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
[\#104](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/104), [\#105](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/105) and [\#111](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/111))
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

* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [\#62](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/62#discussion_r827346201)
  , [\#62](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/62#discussion_r827349826), 
  [\#74](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/74#discussion_r831356836), 
  [\#58](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/58#discussion_r826721361)
  * Helped team members to debug their code and explained the rationale behind it through text messages and video recording.
  
* **Documentation**:
  * User Guide:
    * The UG was written solely by me. Added documentation for the features `help`, `add`, `delete`, 
    `find`, `addgroup`, `delgroup`, `assign`, `deassign`, `viewcontact`, `addtask`, `deltask`, `viewtask`.
    * Added all screenshots in the UG.
    * Wrote all the FAQs.
    * Wrote all notes, tips, and limitations.
    
  * Developer Guide:
    * Edit diagram for UI component.
    * Edit diagram and explanation for Model component.
    * Edit diagram for Storage component.
    * Write explanations, design considerations, and make diagrams implementation these features: `delete`, `addgroup`, `delgroup`, 
    `addtask`, `deltask` , `viewtask`, `assign`.
    * Wrote and defined product scope including: target user profile and value proposition.
    * Wrote the user stories.
    * Wrote and modified all 15 use cases.
    * Wrote and defined all Non-Functional Requirements.
    * Wrote and defined all terms in the glossary.
    * Wrote all instructions for manual testing including these features: `list`, `clear`, `exit`, `help`, `add`, `delete`,
    `find`, `addgroup`, `delgroup`, `assign`, `deassign`, `viewcontact`, `addtask`, `deltask`, `viewtask` and instructions on saving data.
    
* **Community**:
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/227), 
  [2](https://github.com/nus-cs2103-AY2122S2/forum/issues/212), 
  [3](https://github.com/nus-cs2103-AY2122S2/forum/issues/171), 
  [4](https://github.com/nus-cs2103-AY2122S2/forum/issues/68#issuecomment-1026547436),
  [5](https://github.com/nus-cs2103-AY2122S2/forum/issues/255#issuecomment-1090190968))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Punpun1643/ped/issues/9), 
  [2](https://github.com/Punpun1643/ped/issues/13), 
  [3](https://github.com/Punpun1643/ped/issues/15))
