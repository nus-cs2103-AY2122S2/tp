---
layout: page
title: Justine's Project Portfolio Page
---

### Project: LinkyTime

**LinkyTime** is a desktop application for NUS Computer Science students to manage their online meeting links. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete existing meetings in the list.
    * What it does: Allows the user to remove a meeting in the list and all associated informaion by specifying the
      index.
    * Justification: This feature is fundamental to our product, as there needs to be a way to remove meetings from the
      meeting list to prevent the list from growing indefinitely. Users can also delete any meetings that they may have
      incorrectly added.

* **New Feature**: Added the ability to find and display meetings using keywords.
    * What it does: Allows the user to find meetings in the list that match all given keywords.
    * Justification: This feature is required for users to quickly identify relevant meetings in the list that contains
      desired keywords. Users will not have to manually search through the entire list.
    * Highlights: This feature required an in depth analysis and observation of users' desired behaviour from such a
      functionality. Between 2 different behaviours that are both valid for such a feature, I implemented the version
      that most closely aligns with users' expectations.

* **New Feature**: Added the ability to open a meeting's link through the application, in the device browser.
    * What it does: Allows the user to directly open a meeting's URL through the application using a simple command and
      the meeting's index.
    * Justification: This feature is needed to greatly enhance the application's usability as it
      significantly speeds up the process of joining a meeting, which is a fundamental feature of the application.
      Without this feature, the user will have to manually copy the link from the application, open their browser, paste
      the link and open it. This is especially troublesome as links are normally very long.
    * Highlights: This feature called for a careful deliberation over the various libraries available that assist with
      accessing desktop capabilities (such as opening default browsers). I had to do extensive research to identify the
      shortcomings of each library and select the one that works with all versions of the 3 OSes that LinkyTime is made
      for.

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=AY2122S2-CS2103T-T13-3%2Ftp&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=justinekoh&tabRepo=AY2122S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project Management**:
    * Maintained team [weekly meeting notes](https://docs.google.com/document/d/1blOVPpajNMHmHRSajK4t9cl0r2PwMiO2j7FF4Xy-pO8/edit?usp=sharing).

* **Enhancements to existing features**:
    * Added a force delete functionality for module delete
      command [\#122](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/122), with test cases. [\#199](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/199)
    * Wrote comprehensive unit and integration test cases for `delete meeting`, `find meeting` and `open meeting` features. [\#63](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/63), [\#86](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/86), [\#94](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/94)
    * Wrote comprehensive test cases for `Meeting`, `MeetingUrl` and `UniqueMeetingList` classes of the application `Model`. [\#202](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/202)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete meeting` and `find meeting`
          . [\#28](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/28)
          , [\#86](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/86)
        * Added documentation for the `open` feature. [\#123](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/123)
        * Added documentation for `force delete module`
          command. [\#123](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/123)
    * Developer Guide:
        * Added implementation details of the `delete meeting`, `find meeting` and `open meeting`.
          feature. [\#111](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/111), [\#112](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/112), [\#211](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/211)
        * Added UML Sequence Diagrams for `delete meeting`, `find meeting` and `open meeting` features. [\#211](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/211)
        * Added UML Sequence Diagrams for `delete module` feature. [\#211](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/211)
        * Updated UML Activity Diagram for `delete module` feature. [\#211](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/211)
        * Collaborated with teammates to update Use Cases and User Stories. [\#10](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/10)
    * Demonstration video:
        * Showcased the `delete meeting`, `find meeting` and `open meeting` features of the product.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#85](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/85), [\#128](https://github.com/AY2122S2-CS2103T-T13-3/tp/pull/128)
    * Reported bugs and suggestions for other teams in the class (examples: [ped-repo](https://github.com/justinekoh/ped/issues))
    * All code reviews available [here](https://github.com/AY2122S2-CS2103T-T13-3/tp/pulls?q=is%3Apr+reviewed-by%3Ajustinekoh).
