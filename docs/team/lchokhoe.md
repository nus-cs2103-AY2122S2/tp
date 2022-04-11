---
layout: page
title: Lai Chok Hoes's Project Portfolio Page
---

### Project: SoC InternApply

SoC InternApply - SoC InternApply helps CS students to track internship applications they are interested in. It is optimised for users who prefer CLI and have a lot of company applications and their associated tasks to keep track of. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a way for users to get a reminder on any upcoming interviews in the coming week when they start up the product or with a command.
  * What it does: Opens a "Reminder Window" that shows a list of applications whose interviews (if there are any) fall within a week of the current date and time on the users local device. This window will open when the product is launched and/or can be opened with a command.
  * Justifications: This feature improves the utility of the product as users who use this product to track their interview slots for internships also need a quick and easy way to find out what interviews to prepare for next. It also ensures users do not forget about upcoming interviews by reminding them when they launch the product.
  * Highlights: This enhancement provides an incentive for users to make use of our "Interview Slot" feature that allows users to also store the date and time of upcoming interviews. The "launch on start up" action of this feature also opens up the possibility for other actions to be implemented in the same way and with much less hassle since support for this has already been implemented as part of this feature. 
  * Credits: This feature was implemented with reference to the existing `help` command implementation, specifically the mechanism that opens a "Help Window" when the command is called. Also, the code used to generate the application list in the "Main Window" was also referenced for the "Reminder Window".

* **Code contributed**: My code contribution can be found here [[RepoSense](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=lchokhoe&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=lchokhoe&tabRepo=AY2122S2-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)]

* **Project management**:
  * Helped to morph the product into an internship application tracker by: 
    * Renaming the java classes from the AB3 codebase (Pull Requests [#39](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/39), [#40](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/40), [#45](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/45), [#47](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/47), [#66](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/66), [#71](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/71), [#74](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/74))
    * Updating the test cases from the AB3 codebase [#84](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/84)
  * Resolved the following major bugs:
    * `edit` command caused the behavior of the `find` command to be overridden by reseting the list of applications being displayed [#201](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/201)
    * No way for users to remove the `Tag`, `Priority Tag` or `Application Status Tag` from an existing application (Pull Requests [#188](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/188), [#202](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/202))
  * Maintained the issue tracker.
  * Rectified a few merge conflicts.
  * Maintained the test cases to ensure they work after merging multiple pull requests.
  * Helped with managing agenda for weekly team meetings.

* **Enhancements to existing features**:
  * Updated the sample data that is created to showcase how our product could be used to customise user applications [#92](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/92)
  * Updated the sample data generation such that its populated with interview slots that would be relevant depending on the date and time on the user's local device [#130](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/130)
  * Expand the utility of the `edit` command to allow users to edit the `Priority Tag` and `Application Status Tag` of existing applications [#110](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/110)

* **Documentation**:
  * User Guide:
    * Did a general update to remove residual documentation from the AB3 codebase [#92](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/92)
    * Updated the User Guide before a release [#131](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/131)
    * Added documentation for the feature `edit` (Pull requests [#123](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/123), [#204](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/204))
    * Added in additional FAQs in the User Guide.
    * Updated some images used in the User Guide.
  * Developer Guide:
    * Added implementation details of the `reminder` feature [#213](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/213)
    * Added implementation details on the enhancement of the `edit` feature [#213](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/213)
    * Added a section in "Appendix:Instructions for manual testing" for readers to test removal of Tags using `edit` command [#213](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/213)
    * Updated the storage component and its relevant UML diagram [#206](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/206)
    * Worked with the team on User Stories, Use Cases and NFR (Pull requests [#20](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/20), [#21](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/21))

* **Community**:
  * Reported bugs and suggestions for other teams in the class (Example: [1](https://github.com/AY2122S2-CS2103T-W13-1/tp/issues/186))
  * PR's reviewed (for this project): Found [here](https://github.com/AY2122S2-CS2103T-T11-3/tp/issues?q=reviewed-by%3Alchokhoe).
