---
layout: page
title: Fan Jue's Project Portfolio Page
---

### Project: MyGM

MyGM is a desktop app for high school basketball team trainers to manage players’ contacts and data, optimized for use
via a Command Line Interface (CLI). The user will interact with using a CLI and has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to create new lineups and delete existing lineups.
    * What it does: Allows the user to add a new lineup to the system using `AddCommand`. Added lineups can be removed from the system by using the `DeleteCommand`.
    * Justification: This feature helps the user to create lineups to assign players to (in put command) and remove lineups that are no longer relevant. It helps users to regulate and classify players. This feature is one of the cornerstones of the whole project, and it makes the application more specifically tailored to the needs of target users by introducing `Lineup`.
    * Highlights: It required an in-depth analysis of design alternatives. A lot of consideration was given to the dependency relationship and navigability between `Person` and `Lineup`. The implementation too was challenging as it required changes to all existing commands such as `AddCommand` and `DeleteCommand`.

* **New Feature**: Added the ability to remove player from lineup.
  * What it does: Allows the user to remove an existing player from a specific lineup after the player being put into it.
  * Justification: This feature allows the user to adjust the lineups of each player where he deems necessary. It increases the flexibility of the application. By granting the opportunity to explore more possibilities of lineup combinations, this functionality further helps the user to manage a basketball team.
  * Highlights: The enhancement requires modification of a range of classes such as `Model`, `DeleteCommandParser`, and `DeleteCommand`.

* **New Feature**: Added the ability to view all schedules or archived schedules.
  * What it does: Allows the user to view all historically added schedules or expired schedules.
  * Justification: Firstly modified `Schedule` to be displayed in chronological order by default, allowing users to view the older schedules before newer schedules. This new feature makes the application display only active schedules by default for more convenient reference so that users can directly view the most recent future schedule. Outdated data are automatically archived, while users can still check all schedules or expired schedules by using this feature.
  * Highlights: Initial display of schedules followed lexicological order and all schedules were displayed at all times. Thus thought was given to improving the relatability and usefulness of schedules. This enhancement required modifications of classes such as `UniqueScheduleList` and `AddCommand` as well as addition of new classes such as `ScheduleComparator`.

* **New Feature**: Added the ability to view schedules on a specific date.
  * What it does: allows the user to check all the schedules on a specific date.
  * Justification: Since multiple schedules are allowed for one day, users can use this feature to check if there is still free period of time on a specific date before adding more schedules onto it.
  * Highlights: Modified and created a range of classes such as `ViewCommandParser`, `ViewCommand`, and `ScheduleOnThisDatePredicate`.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=FYimu&tabRepo=AY2122S2-CS2103-F09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2`, `v1.3`, `v1.3b`, `v1.4` on GitHub
  * Creating, assigning, and closing issues

* **Enhancements to existing features**:
  * Updated the GUI display according to v1.2a prototype (Pull requests [#70](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/70), [#89](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/89))
  * Updated `AddCommand` to detect errors including maximum capacity reached, duplicate Jersey number, and duplicate player (Pull request [#83](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/83))
  * Updated `AddCommand` and `DeleteCommand` to function with `AddressBook` (Pull request [#88](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/88))
  * Removed redundant files and refactored related files (Pull requests [#95](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/95), [#154](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/154))
  * Updated response messages and implementation of `AddCommandParser` (Pull request [#97](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/97/files))
  * Updated `JsonAdaptedPersonTest` to include lineups for testing purposes (Pull request [#120](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/120/files))
  * Created `LineupBuilder` and updated `PersonBuilder` for testing purposes (Pull request [#129](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/129))
  * Added tests for `DeleteCommand` and `EditCommand` (Pull requests [#129](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/129), [#136](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/136/files))
  * Updated the display of `players` and `schedules` in alphabetical order of name and chronological order of happening date respectively (Pull request [#139](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/139))
  * Updated default display of `Person` to follow lexicological order (Pull request [#139](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/139))
  * Fixed functionality bugs for commands (Pull request [#244](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/244))
* **Documentation**:
    * User Guide:
      * Added documentation for the `put`, `mark`, `delete`, `filter`, `load` and `edit` features [#23](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/23/files)
      * Added documentation for the `view schedule` feature [#141](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/141/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
      * Updated documentation for all commands to remove outdated information [#167](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/167/files)
    * Developer Guide:
      * Added NFRs and glossary [#19](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/19/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b), [#264](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/264)
      * Added implementation details of the `add player` feature [#97](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/97/files)
      * Modified details of the `delete player` feature [#97](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/97/files)
      * Added implementation details of `add lineup`, `add schedule`, `delete lineup` and `delete player from lineup` features [#124](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/124/files)
      * Added implementation details of `view schedule` [#141](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/141/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
      * Updated implementation details of `view schedule` [#167](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/167/files)
      * Added implementation details of `add player`, `add schedule`, `delete schedule`, `edit player`, `edit schedule`, `clear`, `view lineup` [#264](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/264)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#92](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/92), [#100](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/100), [#153](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/153)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/93), [2](https://github.com/nus-cs2103-AY2122S2/forum/issues/226))
  * Reported bugs for other teams during PE-D (examples: [3](https://github.com/FYimu/ped/issues/3), [6](https://github.com/FYimu/ped/issues/6), [8](https://github.com/FYimu/ped/issues/8))
  * Full list of PRs reviewed: [PRs](https://github.com/AY2122S2-CS2103-F09-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
