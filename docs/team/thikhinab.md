---
layout: page
title: Thikhina's Project Portfolio Page
---

### Project: Harmonia

**Overview**

Harmonia is a command-line-based personal task manager, which helps university students to manage their academic tasks. It is written in Java and has a GUI implemented with JavaFX.

Given below are my contributions to the project.

**Evolve Storage to TaskList:** [#108](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/108)
- Added `TaskListStorage` to implement storage functionality
- Added `JsonSerializableTaskList` to support JSON storage of `TaskList`
- Added `JsonTaskListStorage` to access data stored JSON file
- Added `JsonAdaptedTask` to store Task in `Jackson` friendly format
- Updated `Storage` to implement API for Storage component
- Updated `StorageManager` to manage `TaskList` data in local storage
- Updated `Deadline`, `CompletionStatus` to support its storage

**Add Mark Command:** [#129](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/129)
- Added `MarkCommand` to mark a specified task as complete
- Added `MarkCommandParser` to parse user input along with `mark` command word
- Updated `HarmoniaParser` to parse `mark` command word
- Updated `Model`, `ModelManager`, `TaskList` to support marking of a task
- Updated `UniqueTaskList` with method to check if exactly the same task exists

**Add Unmark Command:** [#133](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/133)
- Added `UnmarkCommand` to mark a specified task as incomplete
- Added `UnmarkCommandParser` to parse user input along with `unmark` command word
- Updated `HarmoniaParser` to parse `unmark` command word
- Updated `Model`, `ModelManager`, `TaskList` and `UniqueTaskList` to abstract out the logic need for both `MarkCommand` and `UnmarkCommand`

**Test MarkCommand and MarkCommandParser:** [#142](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/142)
- Tested `MarkCommand` and `MarkCommandParser`
- Updated `HarmoniaParserTest`

**Test Storage:** [#144](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/144)
- Tested `AddSerializableTaskList`
- Updated `StorageManagerTest` and `JsonAdaptedTask`
- Added and updated JSON files required for testing

**Test JsonUserPrefsStorage and JsonTaskListStorage:** [#149](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/149)
- Tested `JsonTaskListStorage`
- Updated `JsonUserPrefsStorageTest` and JSON files required for testing

**Test UnmarkCommand and UnmarkCommand:** [#150](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/150)
- Tested `UnmarkCommandParser` and `UnmarkCommand`
- Updated `HarmoniaParserTest` and helper classes and files: `TypicalTasks`, `TypicalIndexes`, and `typicalTaskList.json`

**Test Sorting functionality:** [#327](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/327)
- Tested `SortKey`, `SortOrder`, `ComparatorFactory`, `SortCommand`, `SortCommandParser` and `sort` in `HarmoniaParser`

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=thikhinab&breakdown=true)

**Documentation**
- User Guide
  - Added `Mark`, `Unmark`, `Edit`, `Exit`, `Saving Data`, `Command Summary` and made changes to `Notes about Command Format` [#64](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/64)
  - Updated `Edit`, `Sort` and `Edit Data File` and `Command Summary` to reflect the new changes [#189](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/189)
  - Updated `Table of Contents (TOC)` and add `Return to TOC` links. [#193](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/193)
  - Updated `Sort` section and made changed to improve readability [#290](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/290)

- Developer Guide
  - Added section on `Mark/Unmark functionality` which also includes `MarkSequenceDiagram.puml` and `MarkSequenceDiagram.png` [#167](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/167)
  - Added section on `Sorting`, updated `Storage Component`, `Logic` and `UI` sections and improve readability [#296](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/296)

**Team**
- PRs reviewed (with non-trivial comments): [#115](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/115), [#128](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/128), [#261](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/261)
- Provided suggestions on implementations to other team members

**Bugs Fixed**
- Fixed bug on `Unmark` to allow bulk un-marking [#260](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/260)
- Fixed bug on Sorting to case-insensitive [#260](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/260)
- Fixed bugs on Sorting [#280](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/280):
  - Updated `Messages` to be consistent with number of tasks shown
  - Updated `EditCommand` to only show filtered tasks
  - Updated `Model` and `ModelManager` to sort task by `DEFAULT_COMPARATOR` and reset sort
  - Updated `LogicManager` to preserve ordering
- Fixed bug on Storage testing [#300](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/260)


**Community**
- Contributed to forum discussions: [#47](https://github.com/nus-cs2103-AY2122S2/forum/issues/47)
- Requested help which led to useful information in the forum: ([#56](https://github.com/nus-cs2103-AY2122S2/forum/issues/56), [#57](https://github.com/nus-cs2103-AY2122S2/forum/issues/57)
- Reported bugs for other teams in the class ([14 bugs identified in the PE-D](https://github.com/thikhinab/ped/issues))
