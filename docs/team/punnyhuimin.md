---
layout: page
title: Hui Min's Project Portfolio Page
---

### Project: ReadyBakey

ReadyBakey is an application built for busy home bakers or small bakeries.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added EditOrder command `edito` [\#107](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/107)
  * _What it does_: allows the user to that edits an order's details, remarks, delivery date time, and collection type.
  * _Justification_: This feature improves the product significantly because a user can rectify any changes made by 
    their customer without deleting and adding a new order. 
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=punnyhuimin&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=punnyhuimin&tabRepo=AY2122S2-CS2103-F09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  1. CSS changes to 
     * Address bugs such as blue boxes populating the application. (Pull Requests: [\#130](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/130), 
        [\#120](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/120))
     * Updated scrollbar look. [\#120](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/120)
     * Changed the color of the details in orders list. [\#166](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/166)
     * Changed the color of the HelpWindow [\#123](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/123)
  2. Added natural date capability to enhance existing datetime capability. (Pull Requests: [\#141](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/141),
     [\#234](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/234))
     * _What it does_: allows the user to make use of natural dates (i.e. Monday, Mon, Tues) to input as dates for the
       AddOrder and EditOrder commands. This is built alongside the DeliveryDateTime capability, which only takes in
       dates in the form of `dd-MM-yyyy`.
     * _Justification_: This feature improves the product significantly by speeding up the users' ability to type in the
       date without having to look at their calendar to find the day and month that the order is to be completed.
     * _Credits_: Used a formula from [https://coderanch.com/t/385117/java/date-Monday](https://coderanch.com/t/385117/java/date-Monday) that gets the next date based 
       on specified NaturalDate input.
  3. Update ExitCommand [\#96](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/96)
     * _What it does_: It sets a 3-second timer before closing the application
     * _Justification_: This provides a smoother closure of the application compared to being closed abruptly. There 
       was also a message in the response box that cannot be read by the user once the exit command is passed. 
  4. Wrote additional tests for dates, increasing the code coverage by 0.04% [\#141](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/141)
  5. Added character limits for person prefix inputs [\#179](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/179)

* **Contributions to team based tasks**:
  * Set up Github team repository, team organisation, and Gradle for repository.
  * Added a new logo for ReadyBakey. [\#110](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/110) 
  * [UI draft](https://www.figma.com/file/g10mAn5vFDXQXABQPh9U9M/ReadyBakey-UI-mockup?node-id=0%3A1) image made with 
    Figma to plan out how the final product will look like.
  * Set up Github Projects to track User Stories completed and in progress.
  * Refactor ListCommand to ListPersonCommand. [\#85](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/85)
  * Update `index.md` to match ReadyBakey. [\#158](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/158)
  * Releasing of JAR file [v1.3.2](https://github.com/AY2122S2-CS2103-F09-4/tp/releases/tag/v1.3.2)
  * Updating App Version in MainApp.java [\#183](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/183)
  * Updating UI image. (Pull Requests: [\#181](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/181), 
    [\#70](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/70))
  * Update target user profile and value proposition in Developer Guide. 
    [\#81](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/81/files)

* **Contributions to Developer Guide**:
  * Added user story and use case for editing customers' phone number, name, email address, exiting ReadyBakey, and 
    address. [\#73](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/73)
  * Added EditOrderSequenceDiagram and EditOrder design considerations. [\#140](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/140)
  * Edited Storage Component and StorageClassDiagram to include Orders. [\#238](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/238)

* **Contributions to User Guide**:
  * Created [v1.2 Documentation on Google Docs](https://docs.google.com/document/d/1XVM0yKcbUT28I7p_NQd5p5lgvAUocCPFuJ3BB7s63lk/edit#heading=h.mvpxa98zj810)
  * Updated [v1.3 Documentation on Google Docs](https://docs.google.com/document/d/1tTXY-lm5M15URXhf_RbOpxJCVG8-GQD86Q2zDqTp1tM/edit)
  * Updated `editp`, `edito`, `findp`, and `clear` in User Guide (Pull Requests: [\#43](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/43),
    [\#99](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/99))
  * Updated usage of Natural Dates for delivery date time. [\#174](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/174)
  * Updated information about character limits for person prefix inputs. [\#180](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/180)
  * Fixed up various formatting and phrasing errors. (Pull Requests: [\#174](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/174),
    [\#99](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/99))

* **Review/mentoring contributions**:
  * Helped catch JavaFX issue with Mac [\#50](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/50#discussion_r815455829)
  * PRs reviewed with comments:
    [\#46](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/46#discussion_r814508758),
    [\#50](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/50#discussion_r815458156),
    [\#83](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/83#discussion_r820242099),
    [\#98](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/98#discussion_r825582867),
    [\#119](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/119#discussion_r830622022),
    [\#131](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/131#discussion_r832902592),
    [\#152](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/152#discussion_r838490864),
    [\#178](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/178#discussion_r839780713)

* **Contributions beyond the project team**:
  * Added on to tip about [setting up PlantUML for Mac](https://github.com/nus-cs2103-AY2122S2/forum/issues/232)
