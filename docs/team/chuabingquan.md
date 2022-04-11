---
layout: page
title: Bing Quan's Project Portfolio Page
---

### Project: LinkyTime

**LinkyTime** is a desktop application for NUS Computer Science students to manage their online meeting links. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability for meetings to be recurring on a weekly basis.
    * What it does: A recurring meeting will always reflect the ongoing/upcoming date and time of its next recurrence.
    * Justification: As modules in NUS recurs weekly, users shouldn't have to manually re-create such recurring meetings on a weekly basis as well.
    * Highlights: This feature required an in-depth analysis of design alternatives to avoid issues with keeping the storage in sync with the next recurring date and time of a meeting.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=chuabingquan&tabRepo=AY2122S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Migrated the model and logic layer to work with `Meeting` objects (Pull requests [\#48](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/48))
    * Migrated the storage layer to serialize/deserialize LinkyTime user data (Pull requests [\#64](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/64))
    * Accepted and parsed meeting date and time from `String` to `LocalDateTime` (Pull requests [\#92](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/92)).
    * Updated the GUI to reflect the latest date and time of all recurring meetings upon the execution of any command (Pull requests [\#92](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/92)).
    * Updated logic to only show ongoing/recurring meetings by default (Pull requests [\#119](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/119)).
    * Wrote test cases for `MeetingDateTime` (Pull requests [\#205](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/205)).

* **Project Management**:
  * Maintained team [weekly meeting notes](https://docs.google.com/document/d/1blOVPpajNMHmHRSajK4t9cl0r2PwMiO2j7FF4Xy-pO8/edit?usp=sharing).

* **Documentation**:
    * User Guide:
        * Collaborated with teammates on the user guide in VSCode live share [\#123](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/123).
    * Developer Guide:
        * Collaborated with teammates on the developer guide in VSCode live share [\#10](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/10).
        * Added activity diagram and implementation details for recurring meetings [\#108](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/108).
        * Migrated AB3 diagrams to LinkyTime ones [\#208](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/208).
        * Described notable features like "Opening of Meeting Links" and "List and Archive" [\#213](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/213).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#82](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/82#pullrequestreview-910379147) [\#83](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/83#pullrequestreview-911378194), [\#112](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/112#discussion_r834584643), [\#202](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/202#discussion_r846625004)
  * Full list of [PRs reviewed](https://github.com/AY2122S2-CS2103T-T13-3/tp/pulls?q=is%3Apr+reviewed-by%3AChuaBingQuan+).

