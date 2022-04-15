---
layout: page
title: Joeie's Project Portfolio Page
---

### Project: Harmonia

**Overview**
Harmonia is a command-line-based personal task manager, which helps university students to manage their academic tasks. It is written in Java and has a GUI implemented with JavaFX.  

Given below are my contributions to the project.

**Refactoring the code from AddressBook to Harmonia:** ([#116](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/116#issue-1163459822), [#128](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/128#issue-1166192852), [#139](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/139#issue-1169282871))
- Modified `Logic`
- Modified `LogicManager`
- Modified `MainWindow`
- Refactored `PersonListPanel` to `TaskListPanel`
- Refactored `PersonCard` to `TaskCard`
- Modified `MainWindow.fxml`
- Refactored `PersonListCard.fxml` to `TaskListCard.fxml`
- Refactored `PersonListPanel.fxml` to `TaskListPanel.fxml`
- Removed unused files from `AddressBook` that were irrelevant to `Harmonia`

**Added Mass Operations:** ([#139](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/139#issue-1169282871), [#191](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/191#issue-1183178506))
- What it does: an enhancement that allows users to mark, unmark and delete multiple indexes in a single command.
- Justification: This enhancement increases the efficiency-levels of our users, who are individuals who want to get their task management done quickly.
- Highlight: Added this enhancement to the existing `Mark`, `Unmark` and `Delete` commands.
- Added new class `MassOpsParser` to process the multiple indexes in the user input.
- Updated `MarkCommand`
- Updated `UnmarkCommand`
- Updated `MarkCommandParser`
- Updated `UnmarkCommandParser`
- Updated `DeleteCommand`
- Updated `DeleteCommandParser`

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=joeietao&breakdown=true)

**Project management**
- Managed releases [`v1.3.1`](https://github.com/AY2122S2-CS2103T-T09-1/tp/releases/tag/v1.3.1), [`v1.3.2`](https://github.com/AY2122S2-CS2103T-T09-1/tp/releases/tag/v1.3.2) (2 releases) on Github

**Documentation**
- User Guide
  - Added documentation for the Quick Start section ([#74](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/74#issue-1153043764))
  - Added table of contents in the beginning ([#91](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/91#issue-1153876080))
  - Updated documentation for the Features section (`Add`, `List`, `Mark`, `Unmark`) ([#183](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/183#issue-1182479119), [#199](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/199#issue-1185714418))
  - Replaced images of mass marking and mass unmarking to better explain the features ([#216](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/216#issue-1188232576))
- Developer Guide
  - Added documentation for `MassOperations` ([#171](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/171#issue-1179857765), [#323](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/323#issue-1199047041))
    - Added documentation for mass marking, unmarking and deleting
  - Added documentation for `Appendix: Instructions for manual testing`
    - Added documentation for `add`, `edit`, `list`, `list t/`, `mark`, `unmark`, `help`

**Testing**
- Updated `TestUtil` ([#128](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/128#issue-1166192852))
- Wrote tests for: ([#319](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/319#issue-1198752665))
  - `MarkCommand`
  - `UnmarkCommand`
  - `MarkCommandParser`
  - `UnmarkCommandParser`
  - `MassOpsParserTest`
  - `HarmoniaTest` (`mark`, `unmark`, `delete`)


**Team**
- Fixed bugs: [#160](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/160#issue-1179281197), [#191](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/191#issue-1183178506), [#272](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/272#issue-1193391761), [#281](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/281#issue-1195887094)
- Actively helped with reviewing PRs, PRs reviewed (with non-trivial review comments): [#200](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/200#issue-1185955092), [#207](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/207#issue-1186631238), [#196](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/196#issue-1184502012)

**Community**
- Reported bugs for other teams in class ([13 bugs identified in the PE-D](https://github.com/joeietao/ped/issues))
