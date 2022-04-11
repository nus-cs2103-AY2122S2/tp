---
layout: page
title: David Limantara's Project Portfolio Page
---

### Project: d'Intérieur

d'Intérieur is a desktop address book application, designed with interior designers in mind. The designer interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 26 kLoC.

Interior designers can use d'Intèrieur to improve contact management and take on the needs of an ever-growing client base, so that they can focus on what matters most - delivering quality service for clients.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add address as an optional field.
    * Justification: This feature was done because it is not critical for an interior designer to know a client's address.

* **New Feature**: Added a high importance flag icon which appears beside the contact name
  * What it does: Each contact will have an unlit flag beside the contact name by default. When activated, it turns red.
  * Justification: This feature is used to indicate which contacts are of high importance, whereby there are some important concerns that the interior designers should take note of for each particular contact.
  * Highlights: This feature should be used in tandem with the `note` feature for best results as the `note` feature can be used to write down key concerns that the interior designers should take note of for a particular contact.

* **New Feature**: Added a list important contacts command.
  * What it does: It acts like a filter to only display contacts who have the red flag lit up beside their name .
  * Justification: This feature will help interior designers pay special attention to those contacts listed so that their needs or concerns will be met.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=xsaints19x&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (5 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the help window by adding a link to the command summary (Pull request [\#111](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/111))
    * Added an alert to inform users that either links has been copied successfully for the help window (Pull request [\#111](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/111))

* **Documentation**:
    * User Guide:
        * Updated description for the commands `add`, `impt`, `impts` and `help`: [\#29](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/29), [\#88](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/88), [\#110](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/110), [\#196](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/196)
        * Updated command summary for the commands `add`, `impt` and `impts`: [\#29](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/29), [\#88](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/88)
        * Updated UG throughout to standardise styling and emphasis on key points: [\#196](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/196)
    * Developer Guide:
        * Added description for the enhanced `add` command and added code snippet
        * Added implementation details and UML diagram for the `high importance flag` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#20](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/20), [\#99](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/99)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/244), [2](https://github.com/nus-cs2103-AY2122S2/forum/issues/111))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/xSaints19x/ped/issues/1), [2](https://github.com/xSaints19x/ped/issues/2), [3](https://github.com/xSaints19x/ped/issues/3))
