---
layout: page
title: Yong Rui's Project Portfolio Page
---

### Project: Ultimate Divoc Tracker

This project is based on the AddressBook-Level3 project created by the SE-EDU initiative. This application is a variation of the above original project, tailored for school administrators to track COVID-19 cases amongst students in schools.

Given below are my contributions to the project.

* **New Feature**: Status attribute for persons (Pull request [\#32](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/32))
  * What it does: Gives each person in the list a status attribute denoting their COVID-19 status.
  * Justification: Track COVID-19 status of each person
  * Highlights: Status can be `"Positive"`, `"Negative"` or `"Close-Contact"`
  * Credits: *{Collaborators: Yong Rui, Zi Foong}*

* **New Feature**: Command to find persons by Class Code (Pull request [\#56](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/56))
  * What it does: Find a list of persons that match the Class Code given.
  * Justification: Allows admins and users to find all persons in the same class
  * Highlights: Given Class Code can be part of the full Class Code
  * Credits: *{}*

* **New Feature**: Command to find persons by Activity (Pull request [\#83](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/83))
  * What it does: Find a list of persons that match the Activity given.
  * Justification: Allows admins and users to find all persons in the same activity groups.
  * Highlights: Can find specific Activity from multiple Activities tied to the same person.
  * Credits: *{Collaborators: Yong Rui, Zi Foong}*

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `--` - `--` (-- releases) on GitHub

* **Enhancements to existing features**:
  * Fixed multiple checkstyle errors (Pull request [\#49](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/49))
  * Added JUnit tests for Finding persons by Class Code and its related classes (Pull request [\#75](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/75))
  * Added JUnit tests for Finding persons by Activity and its related classes (Pull request [\#86](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/86))
  * -- (Pull requests [\#--](), [\#--]())

* **Documentation**:
  * User Guide:
    * Added glossary for user guide ([commit](https://github.com/AY2122S2-CS2103T-T12-1/tp/commit/54c68ab0ecae3cd1a5237a74df7cb7264c24b692))
    * Added quick start for user guide ([commit](https://github.com/AY2122S2-CS2103T-T12-1/tp/commit/54c68ab0ecae3cd1a5237a74df7cb7264c24b692))
    * Added documentation for the features `add` and `list` ([commit](https://github.com/AY2122S2-CS2103T-T12-1/tp/commit/54c68ab0ecae3cd1a5237a74df7cb7264c24b692))
      * Fixed formatting errors ([commit](https://github.com/AY2122S2-CS2103T-T12-1/tp/commit/9bb104d5c2c16abdd577221d100916e6f9f8edc1))
    * Added more introduction information [\#104](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/104)
    * Added link to download application .jar file [\#104](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/104)
    * Added installation notes [\#104](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/104)
    * Added more information to certain commands' sections [\#104](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/104)
    * Added tips and warnings to multiple sections [\#104](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/104)
    * Added feature notes [\#104](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/104)
    * Added links to multiple parts of the markdown [\#104](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/104)
    * Updated and change user guide to have more information and be more user friendly to read [\#109](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/109)
    * Updated user guide to resolve issues from Practical Exam Dry Run [\#166](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/166)
      * Solves: #152, #150, #148, #147, #145, #141, #136, #135, #124
      * Added information about status and how it automatically changes [\#166](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/166)
      * Updated find by name section to include tips to find multiple students at the same time [\#166](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/166)
      * Added information about editing activities replacing the activities rather than adding [\#166](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/166)
      * Added information in add student section about the restrictions of each input [\#166](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/166)
      * Added information for clear command [\#166](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/166)
      * Added warning for clear command [\#166](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/166)
    * Updated user guide to resolve issues from Practical Exam Dry Run [\#171](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/171)
      * Solves: #159, #157, #143, #137, #128, #127
      * Added information about GUI aspect ratio [\#171](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/171)
      * Added alternate ways to start the app from command line [\#171](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/171)
      * Added notes on UDT only tracking COVID within the app, not externally [\#171](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/171)
      * Added information on find command finding a person not listed inside the app [\#171](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/171)
      * Added information on limitations of automation of status changes by the app [\#171](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/171)
  * Developer Guide:
    * Added use cases for developer guide [\#27](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/27)
    * Added skeleton for DG additions [\#90](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/90)
    * Added information for Find By Class Code, Find By Activity [\#96](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/96)
    * Updated design considerations for Find By Class Code, Find By Activity, and Find By Status [\#96](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/96)
    * Updated implementation information for Status, Class Code, Activity and Find By Status [\#96](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/96)
    * Added followed steps for Find By Class Code, Find By Activity, and Find By Status [\#96](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/96)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#32](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/32) [\#63](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/63) [\#105](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/105) [\#106](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/106) [\#164](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/164) [\#181](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/181)
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the -- feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * -- ([\#--]())
  * --
