---
layout: page
title: Lin Da's Project Portfolio Page
---

### Project: MyGM

MyGM is a desktop app for high school basketball team trainers to manage players’ data and schedules, optimized for use
via a Command Line Interface (CLI). The user will interact with using a CLI and has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find players and lineups based on criteria. [\#151](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/151)
    * What it does: Allows user to specify certain optional criteria (name, lineup name, weight, height, position) to filter the existing players and lineups in MyGM.
    * Justification: This features allows navigability within the application, specifically given the variety of attributes that a player has, users would want to quickly find the existence of players based on different searching criteria. This is useful for users, especially coaches when they decide the players that are deemed to be suitable to play together in a lineup.
    * Highlights: This implementation requires modification of `ViewCommand` and `ViewCommandParser` together with the creation of various predicate classes such as `TagContainsKeywordsPredicate`, `HeightContainsKeywordsPredicate` and `WeightContainsKeywordsPredicate`. A lot of consideration was given on the **key** optional searching criteria to include as well as to make this feature functional as dependency on many classes such as `ModelManager` was considered.

* **New Feature**: Added the ability to sort the displayed players in MyGM in both ascending and descending order of the sorting criteria. [\#153](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/153)
    * What it does: Allows user to specify sorting criteria (jersey number, height, weight) to sort the currently displayed players in MyGM.
    * Justification: The displayed players can be many and usually player's physical attributes such as height and weight are of concern. Therefore, a sorting command allows the user, especially coach to identify the top few players in the sorting criteria. Furthermore, it allows more options for users to read the data more efficiently on top of the default alphabetical ordering of the players.
    * Highlights: Typically sorting involves sorting the specified criteria in ascending order. In view of this, thought was given to the case if users would also like to sort based on descending order. In addition, sorting involves specifying different comparators as well as modification of classes such as `UniquePersonList` and `ModelManager`, as such, extra caution was given to avoid messing up the existing code base.

* **New Feature**: Added the basic CRUD (Create, Read, Update, Delete) functionality for schedule. [\#126](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/126)
    * What it does: Allows user to add training schedules or key events that are unique to the current basketball team. 
    * Justification: While the main purpose of MyGM is to manage players data (physical attributes and contact details), having a schedule allows user to quickly jot down the upcoming events such as competition, trainings for the team.
    * Highlights: Creating the basic CRUD functionality is a challenge on its own and involves the modification of key classes such as `AddressBook`, `Model`, `ModelManger`, `UniqueScheduleList` as well as the individual parser for the CRUD commands. These features have to be quickly implemented to allow more time for my teammates [Tian Xiao](https://github.com/AY2122S2-CS2103-F09-1/tp/blob/master/docs/team/snoidetx.md) and [Fan Jue](https://github.com/AY2122S2-CS2103-F09-1/tp/blob/master/docs/team/fyimu.md) to work on more advanced features that require these functionalities. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=DALIN-Prog&tabRepo=AY2122S2-CS2103-F09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.2`, `v1.3 Trial`, `v1.3`, `v1.4` (4 releases) on GitHub
    * Creating milestones
    * Assigning issues

* **Enhancements to existing features**:
    * Include relevant attributes such as height, weight, jersey number for players and include regular expressions to check its validity. (Pull requests [\#40](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/40), [\#47](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/47))
    * Make tag more restrictive. [\#81](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/81)
      * Justification: Tag in MyGM serves as tagging a position to a player. The original tag from AB3 was too generic and flexible, making it unsuitable to our application.
    * Combined the original `list` and `find` commands in AB3 and put it under the `view` command. [\#69](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/69)
      * Justification: Reduces the number of distinct commands since find and list both serves as 'Read' functionality.
    * Add test cases for `AddCommand` [\#128](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/128)

* **Documentation**:
    * User Guide:
        * Added the usage of `view` command. [\#164](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/164)
        * Add screenshots for `view` and `sort` commands.  [\#176](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/176)
    * Developer Guide:
        * Updating the user stories [\#118](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/118)
        * Added the implementation details of view. [\#127](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/127)

* **Community**:
    * Full list of PRs reviewed: [PRs](https://github.com/AY2122S2-CS2103-F09-1/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+is%3Aclosed).
    * Reported bugs and suggestions for other teams during PE-D (examples: [1](https://github.com/DALIN-Prog/ped/issues/1), [3](https://github.com/DALIN-Prog/ped/issues/3), [7](https://github.com/DALIN-Prog/ped/issues/7)).
