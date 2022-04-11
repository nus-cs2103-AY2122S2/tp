---
layout: page
title: Snoidetx's Project Portfolio Page
---
### Project: MyGM

MyGM is a desktop app for high school basketball team trainers to manage players’ contacts and data, optimized for use
via a Command Line Interface (CLI). The user will interact with using a CLI and has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Updated the parsers to fit the updated commands.
  * What it does: Allows the new commands to be parsed by the system.
  * Justification: This feature facilitates the parsing of user commands to `Command` instances so that they can be executed.
  * Highlights: This feature maintains the structure of the whole project.

* **New Feature**: Added the ability to delete players and lineups.
  * What it does: Allows the user to remove an existing player or existing lineup from the system.
  * Justification: This feature allows the user to remove players or lineups where he deems necessary.
  * Highlights: This feature is one of the main features of the whole project, according to CRUD princple.

* **New Feature**: Added the ability to put players into existing lineups.
  * What it does: Allows the user to put an existing player into an existing lineup.
  * Justification: This feature helps the user to put players into lineups, which fulfils the main function of lineups.
  * Highlights: This feature is one of the main features of the whole project. It facilitates the management of lineups.

* **New Feature**: Added the ability to edit an existing lineup.
  * What it does: Allows the user to change the name of an existing lineup.
  * Justification: This feature helps the user to rename a lineup without affecting the players inside.
  * Highlights: This feature increases the customizability of the system.

* **New Feature**: Added the ability to view aggregated data of the club.
  * What it does: Allows the user to view the distribution of players by position in the club and refer to suggestions given by the app.
  * Justification: This feature allows the user to recruit players of specified positions if that position is short of players.
  * Highlights: The enhancement increases the readability of the application and further helps the user to manage a basketball club.

* **New Feature**: Added the ability to view all schedules of the current month in a calendar.
  * What it does: Allows the user to view all schedules of the current month in a calendar, where today and the dates with schedules are highlighted.
  * Justification: In this way, users can know how many schedules he still has in the current month by glancing at the calendar.
  * Highlights: The enhancement increases the readability of the application and further helps the user to manage a basketball club.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=snoidetx&tabRepo=AY2122S2-CS2103-F09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases v1.1 - v1.4 on GitHub

* **Enhancements to existing features**:
  * Refactored the entire GUI display according to v1.3b prototype (Pull requests [#147](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/147), [#131](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/131))
  * Added `MyGM` and `UniquePlayerList` models to achieve better OOP in v1.2b (Pull request [#68](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/68))
  * Updated `DeleteCommand` to function with `AddressBook` in v1.3 (Pull request [#84](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/84))
  * Removed redundant files and refactored related files (Pull request [#90](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/90))
  * Fixed the issue where GUI does not respond to certain commands (Pull request [#96](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/96), [#100](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/100))
  
* **Documentation**:
  * User Guide:
    * Added documentation for the `view` and `find` features [#21](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/21)
  
  * Developer Guide:
    * Added target user profile and value proposition [#20](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/20)
    * Updated target user profile and value proposition in v1.3b [#113](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/113)
    * Updated implementation details of `delete player`, `delete lineup` and `edit lineup` [#125](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/125)

* **Community**:
  * Commented, reviewed and merged PRs.
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/159))
  * Reported bugs for other teams during PE-D.
