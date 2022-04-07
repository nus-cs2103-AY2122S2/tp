---
layout: page
title: Toh Zhan Qing's Project Portfolio Page
---

### Project: HackNet

HackNet helps computing students find teammates to do projects with. It allows users to add and find contacts by technical skills, providing them a platform to find the right teammates for each project.

Given below are my contributions to the project.

* **New Feature**: Skill feature
  * _What it does_: Stores a skill name together with a proficiency value which indicates how competent the individual is with the particular skill.
  * _Justification_: This feature is one of the unique selling point of HackNet and most of the other functions build on top of this feature. It allows the user to keep track of the expertise of individuals.
  * _Highlights_: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands. To improve abstraction, I had to implement the class `SkillSet` to store multiple `Skill`s and a `JsonAdaptedSkill` to store the information in Json format.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tzhan98&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=tzhan98&tabRepo=AY2122S2-CS2103T-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Set up milestones
  * manage release `v1.2.1` Trial release of HackNet
  * Account for bugs in trial PE and submit bug progress report

* **Enhancements to existing features**:
  * Enhanced `Help` Command to search for a topic to get help in.
  * Enhanced `Skill` to display different hues of green to indicate how proficient the user is.

* **Bug fixes**:
  * Fix input validation of regex to exclude values from `Skill proficiency` [#136](https://github.com/AY2122S2-CS2103T-W13-3/tp/issues/136)
  * Fix bug that causes duplicates in `Skill`s tag and hence `Sort` feature to have unexpected output [#130](https://github.com/AY2122S2-CS2103T-W13-3/tp/issues/130) 
  * Add more topics that `help` is able to search for such as `filterteam` [#107](https://github.com/AY2122S2-CS2103T-W13-3/tp/issues/107)
  * Add input validation for `Github username` such that it complies with actual Github username rules [#147](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/147)
  * Change HackNet to allow for duplicate `name` fields but reject any command that try to add/edit a duplicate or existing `Github username`, `phone number` or `email` [#154](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/154)

* **Documentation**:
  * User Guide:
    * Add documentation for [Help feature](https://github.com/AY2122S2-CS2103T-W13-3/tp/blob/master/docs/UserGuide.md#viewing-help-help)
    * Add documentation for [Skills feature](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/39/files)
    * [Fix UG errors](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/140)
  * Developer Guide:
    * [Update DG to include sequence diagrams and class diagrams of skills](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/59)

* **Community**:
  * PR reviewed [#62](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/62), [#73](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/73), [#79](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/79), [#141](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/141)
  