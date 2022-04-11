---
layout: page
title: Snoidetx's Project Portfolio Page
---
### Project: MyGM

MyGM is a desktop app for high school basketball team trainers to manage players’ contacts and data, optimized for use
via a Command Line Interface (CLI). The user will interact with using a CLI and has a GUI created with JavaFX.

Given below are my contributions to the project:

* **New Feature**: Added the ability to delete players and lineups.
  * What it does: Allows the user to remove an existing player or existing lineup from the system using `DeleteCommand`.
  * Justification: This feature allows the user to remove players or lineups that are no longer necessary. It ensures that all the players and lineups existing in the system are deemed valid and necessary by the user. This feature is one of the cornerstones of the whole project, being one fundamental feature according to the CRUD principle.
  * Highlights: This feature requires a deep understanding and analysis of the entire structure of the system, since there are dependency and relationship between `Person`, `Lineup` and other classes. The implementation was challenging because it requires entire update to the existing `DeleteCommand` in AddressBook.

* **New Feature**: Added the ability to put players into existing lineups.
  * What it does: This feature allows the user to put players into a lineup using `PutCommand`. 
  * Justification: This feature helps the user to put players into lineups, which fulfils the main function of lineups. This sets up a connection between players and lineups, making the whole application useful to manage players and lineups. This feature is one of the cornerstones of the whole project, since it ensures the core function of the application.
  * Highlights: This feature requires a deep understanding and analysis of the entire structure of the system, since there are dependency and relationship between `Person`, `Lineup` and other classes, which are all required to be considered during the actual implementation.

* **New Feature**: Added the ability to edit an existing lineup.
  * What it does: This feature allows the user to change the name of an existing lineup using `EditCommand`.
  * Justification: This feature helps the user to rename a lineup without affecting the players inside. It improves the flexability of our application, since now users can make changes to the lineup names after the lineup is created. 
  * Highlights: This feature requires a deep understanding and analysis of the entire structure of the system, since `lineupName` is used as an identifier of each lineup, hence simply changing the name will not work and changes to other classes are also required. Moreover, this feature increases the customizability of the system.

* **New Feature**: Added the ability to view aggregated data of the club.
  * What it does: Allows the user to view the distribution of players by position in the club and refer to suggestions given by the app.
  * Justification: This feature allows the user to recruit players of specified positions if that position is short of players. This is particularly important in a basketball club managing system since a basketball club needs to have a balanced distribution of player for each position.
  * Highlights: The enhancement increases the readability of the application and further helps the user to manage a basketball club. It requires a deep understanding of UI, Logic, and Model component since this function requires to read from the Model, process in Logic and display in UI.

* **New Feature**: Added the ability to view all schedules of the current month in a calendar.
  * What it does: Allows the user to view all schedules of the current month in a calendar, where today and the dates with schedules are highlighted.
  * Justification: In this way, users can know how many schedules he still has in the current month by glancing at the calendar. This increases the value of our application as a management application, since it eases the process of managing all the schedules.
  * Highlights: The enhancement increases the readability of the application and further helps the user to manage a basketball club. It requires a deep understanding of UI, Logic, and Model component since this function requires to read from the Model, process in Logic and display in UI.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=snoidetx&tabRepo=AY2122S2-CS2103-F09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases v1.1 - v1.4 on GitHub

* **Enhancements to existing features**:
  * Refactored the entire GUI display according to v1.3b prototype (Pull requests [#147](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/147), [#131](https://github.com/AY2122S2-CS2103-F09-1/tp/pull/131))
  * Updated all parsers to fit the updated commands. This allows all the commands to be parsed so as to be executed. This ensures that further features can be added to the system and the whole system can be tested.
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
