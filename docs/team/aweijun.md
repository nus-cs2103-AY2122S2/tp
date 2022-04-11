---
layout: page
title: aweijun's Project Portfolio Page
---

### Project: Trackermon

Trackermon is a desktop application for tracking and managing shows, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Trackermon helps people track and remember what shows they have watched, are currently watching, or plan to watch.
Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=aweijun&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)

* **New Feature**: Suggest a random show from the current displayed list of shows through the `suggest` command  [[\#185]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/185).
  * What it does: returns the user a random show from the currently displayed list of shows.
  * Justification: This feature is a good addition to Trackermon. The feature helps indecisive users to decide on what they want to watch.

* **New Feature**: Allows user to rate shows through the addition of `Rating` class [[\#175]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/175).
  * What it does: allows users to give a show a rating.
  * Justification: This feature is an important addition to Trackermon. The feature allows users to keep track of whether a show is good or bad.
  * Highlights: This enhancement would affect the existing commands, however implementation was relatively simple.

* **New Feature**: Allows user to note down status shows through the addition of `Status` class [[\#60]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/60).
  * What it does: allows users to note down the watch status of the Show.
  * Justification: This feature is an important addition to Trackermon. The feature allows users to keep track of watch status fo the shows.

* **Enhancements to existing features**:
  * Designed mock-up of user interface.
    * Details : Created the nock-up of the user interface on figma for [JonathanHoshi](https://github.com/JonathanHoshi).
    * Issues: [[\#18]](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/18)
    * PRs: [[\#44]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/44)
  * Refactor and morph AB3 code into a working product for Trackermon:
    * Details: 
      * Morph Person class to Show Class.
      * Modified Model Class to fit Trackermon.
      * Modified ModelManager Class to fit Trackermon.
      * Morph ReadOnlyAddressBook to ReadOnlyShowList.
      * Morph AddressBook to ShowList.
      * Morph UniquePersonList to UniqueShowList.
      * Modified ReadOnlyUserPrefs to fit Trackermon.
      * Morphed DuplicatedPersonException and ShowNotFoundException.
    * Issues:[[\#54]](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/54)
    * PRs:[[\#60]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/60)
  * Morph and implement Remark feature into the Comment feature for Trackermon
    * Details: Morph Remark to Comment Class.
    * PRs:[[\#133]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/133)

* **Project management**:
  * Managed issues relating to Model's components, etc. 
  * Perform testing on windows and macOS to test for bugs across different operating system.
  * Tested for bugs and fixed bugs related to components.
    * Rating Bug: [[/#261]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/261)
    * Tag Bug: [[/#264]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/264)
    * Add Error Message Bug: [[/#262]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/262)
    * Suggest Bug: [[/#260]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/260)
    * Comment Bug: [[/#305]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/305)
* **Documentation**:
  * User Guide: 
    * Added documentation for features `suggest` [[\#194]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/194).
  * Developer Guide:
    * Updated ModelClassDiagram to fit Trackermon [[\#100]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/100).
    * Updated BetterModelClassDiagram to fit Trackermon [[#100]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/100).
    * Added implementation details of the Status feature [[\#183]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/183).
    * Added implementation details of the Suggest feature [[\#194]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/194).

* **Team**:
  * Setting up GitHub repo
  * Set up site-wide settings

* **Community**:
  * PRs reviewed (with non-trival review comments): [[\#166]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/166#discussion_r835801455),
  [[\#176]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/176#discussion_r835937502),
  [[\#108]](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/108#discussion_r828101674)
  * Contributed to issue discussion: [[\#63]](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/63), [[\#283]](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/283)
  * Reported bugs and suggestions:  [[\#283]](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/283), [[\#314]](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/314)

