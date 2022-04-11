---
layout: page
title: Alex Teo's Project Portfolio Page
---

### Project: HackNet

HackNet helps computing students find teammates to do projects with. It allows users to add and find contacts by technical skills, providing them a platform to find the right teammates for each project.

Given below are my contributions to the project.

* **New Feature**:
    * [Filter persons by Skills](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/31)
      * <b>What it does: </b> This new feature allows for the user to filter out persons in HackNet who do not possess the specified skills.
      * <b>Justification: </b> This new feature is one of the features built on top of the Skills feature. It allows the user to easily find persons who are a good fit for his team that requires a certain skill.
      * <b>Highlights: </b> This feature was initially implemented to only accept one skill as its argument, but was updated to accept more than one skill as its argument after the bug was found.
    * [Undo/Redo Command](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/76)
      * <b>What it does: </b> This new feature allows for the user to undo and redo his/her past commands so that they are not required to delete and add a person again in the event that they made a mistake.
      * <b>Justification: </b> This new feature is one of the features implemented to improve the Quality-of-Life of the user. Previously, if a mistake was made, the user had to either edit the person or to delete and add the person again.
This feature allows the user to easily undo their mistake, and redo them if necessary. Together with the command history feature, it allows its users to quickly undo and restore their past command and making the correct changes.
      * <b>Highlights: </b> This feature was especially difficult to implement as a new version of AddressBook with versioning support had to created.
Deep understanding of the internal working of the model was required. Tests with regards to Model, AddressBook and all commands
had to be updated and new tests had to be created for the new class and commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=alexteo98&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

<div style="page-break-after: always;"></div>

* **Project management**:
    * Set up Team Repo
    * Set up milestones (v1.1)

* **Enhancements to existing features / Bug fixes**:
    * [Fix AssertFailure bug in `JsonSerializableAddressBookTest.java`](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/35)
      * Identified previously closed issue regarding test routines, due to missing update on test data files.
    * [Fix bug on Filter feature on multiple skill inputs](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/62)
      * Fixed bug on Filter feature doing nothing when multiple skills are entered as filter criteria. Improved upon the feature to accept multiple skills afterwards.
    * [Fix `NumberFormatException` when parsing large skill proficiencies](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/177)
      * Identified and fixed bug when large integers caused `NumberFormatException` to be thrown when a large value was entered for skill proficiency.

* **Documentation**:
    * User Guide:
        * Add  documentation for `filter`, `undo` and `redo` features.
        * Add warning notes on limitations of features.
    * Developer Guide:
        * Class Diagrams for FilterSkillCommandClass
        * Sequence Diagrams for FilterSkillCommand
        * Sequence Diagrams for UndoCommand

* **Community**:
    * PR reviewed:
[#32](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/32),
[#38](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/38),
[#57](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/57),
[#63](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/63),
[#154](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/154),
[#164](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/164),
[#167](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/167)

