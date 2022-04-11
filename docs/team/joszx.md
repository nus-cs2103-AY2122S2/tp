---
layout: page
title: Seah Zhi Xuan's Project Portfolio Page
---

### Project: SoC InternApply

SoC InternApply - SoC InternApply helps CS students to track internship applications they are interested in. It is optimised for users who prefer CLI and have a lot of company applications and their associated tasks to keep track of. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented the summary bar
  * What it does: The summary bar keeps track of relevant statistics such as total number of applications or applications with the `HIGH` priority tag for easy viewing.
  * Justification: This feature improves the usability of the app by providing an easy to view summary of all applications in SIA.
  * Highlights: This enhancement required the creation of models for the summary bar such as of `SummaryList` and `SummaryBox` as well as its corresponding UI classes and elements. Much of the difficulty came from creating the relevant models from scratch, how they interact, store and fit in the current model architecture as well as creating and integrating the summary bar GUI into our application.
  * Credits: Inspired from [repo](https://github.com/AY2122S1-CS2103T-W17-2/tp)
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=joszx&breakdown=true)

* **Project management**:
  * Monitor TP dashboard progress.
  * Maintaining the issue tracker.
  * Fix general bugs in UG/DG.

* **Enhancement to existing feature**: Added the `JobTitle` field to `Application`
  * Added the `JobTitle` class.
  * Modified add and edit command to function for `JobTitle` field.
  * Updated GUI, storage, sample data, relevant test cases and test data to include `JobTitle` field.
  
* **Documentation**:
  * User Guide:
    * Updated the default UG to SIA: [#19](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/19)
    * Added the `edit` command: [#86](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/86/files)
    * Added in notes for duplicate applications: [#191](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/191)
    * Fixed format, ordering and typo issues: [#187](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/187)
  * Developer Guide
    * Fixed format/ordering issues: [#119](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/119)
    * Added summary bar feature and relevant images: [#115](https://github.com/AY2122S2-CS2103T-T11-3/tp/pull/115)
    * Updated the model component and UML diagram.
    * Worked with the team on user stories, use cases, NFRs.

* **Community**:
  * [PRs reviewed](https://github.com/AY2122S2-CS2103T-T11-3/tp/issues?q=reviewed-by%3Ajoszx)
  * Reported bugs and suggestions for other teams (examples: [1](https://github.com/AY2122S2-CS2103T-T17-2/tp/issues/199), [2](https://github.com/AY2122S2-CS2103T-T17-2/tp/issues/200))

