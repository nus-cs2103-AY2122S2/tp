---
layout: page
title: Howard Wong's Project Portfolio Page
---

### Project: RealEstatePro

RealEstatePro is a desktop application for managing your client details for real estate agents. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). With RealEstatePro managing your clients will be breeze by using the various features such as reminders, client matching and many more!

Given below are my contributions to the project.

* **New Feature**: Added the ability to upload and view images.
  * What it does: allows the user to upload multiple images at once from the user's file system to be associated with clients.
  * Justification: This feature improves the product significantly because a user can associate images depicting how the client's flat looks like or the map to the client's flat.
  * Highlights: This enhancement affects existing commands. The implementation was challenging as it required interaction with the user's file system which provided more aspects to take note of such as file format and file path.  The implementation was also challenging as it required additions and changes to existing commands.

* **New Feature**: Added Keyboard shortcuts to existing commands that required opening of another window within the app.
  * What it does: Allows the user to open the `favourite`, `stats` and `reminder` windows through key press.
  * Justification: Serves as a quality of life feature and allows the user more options to open the windows apart from just commands.

* **Enhanced Help Window**:
  * What it does: Improved on the existing help window to display additional information that the user will likely need regularly in one place apart from just the URL to the user guide.
  * Justification: It allows the user to access information that he would frequently need from within the app without having to open the user guide in order to check the information instead
  * Highlights: Had to find a way to allow the contents of the pane that shows the help information to change on button press which was challenging due to the lack of experience with JavaFX.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=howardwhw2&tabRepo=AY2122S2-CS2103-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project Management**:
  * Managed release `v1.4`(1 release) on GitHub
  * Helped Manage labeling of issues.

* **Documentation**:
  * User Guide:
    * Added and updated documentation for the features `upload`, `viewimage` and `help`) [\#71](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/71) [\#95](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/95)
    * Added Table of contents, standardised formatting  and reordered content in the user guide to improve readability.[\#186](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/186)
  * Developer Guide:
    * Updated Ui Architecture diagram [\#189](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/189)
    * Added implementation details of the `upload` and `viewimage` feature.[\#98](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/98) [\#189](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/189)
    * Added use cases and manual testing `add`, `edit`, `delete`, `upload` and `viewimage` [\#189](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/189)
    * Added Table of contents, some user stories and further elaboration for value proposition [\#203](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/203)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#47](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/47), [\#94](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/94), [\#96](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/96), [\#102](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/102) [\#108](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/108)
  * Reported bugs: [\#105](https://github.com/AY2122S2-CS2103-W16-4/tp/issues/105)

* **Tools**:
  * Introduced Diagrams.net as the team's tool for diagrams.
