---
layout: page
title: Nicolle's Project Portfolio Page
---

### Project: Harmonia

**Overview**

Harmonia is a command-line-based personal task manager, which helps university students to manage their academic tasks. It is written in Java and has a GUI implemented with JavaFX.

Given below are my contributions to the project.

**Evolved AddressBook into Harmonia:**
- Modified `EditCommand` and `EditCommandParser` by replacing the fields in AB3 (`phone`, `email`, `address`) with fields used in Harmonia (`description`, `deadline`) [#125](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/125)
- Modified `FindCommand` and `ClearCommand` to suit Harmonia [#121](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/121)
- Replaced sample data for AB3 with sample data for Harmonia

**Enhancement to the `find` feature:** [#156](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/156)
- Added support to search by tags

**Enhancement to the `help` feature:** [#188](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/188)
- What it does: Gives users the option to open the user guide using their default browser
- Justification: This enhancement provides convenience for users as they can choose to view the user guide by clicking the button instead of copying and pasting the url into another browser window
- Credits: Code to open url using default browser adapted from [here](https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java)

**Enhancement to the `list` feature:** [#196](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/196)
- What it does: Allows user to view a list of tags currently used in the task list
- Justification: This enhancement complements other features whereby users can view the currently used tags in the task list before executing the other commands such as `add`, `edit` and `find`

**Conducted Testing:**
- Modified test utilities [#130](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/130)
- Modified tests for `edit`, `find` and `clear` commands [#137](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/137)
- Modified test for `LogicManager`
- Wrote additional tests for `TagList` and `list` command [#274](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/274)
- Updated tests for `add` and `edit` commands to include the `Priority` field
- Wrote additional tests for `delete` command to include deletion of multiple indexes [#299](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/299)

**Code contributed:** [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=nicollegann&breakdown=true)

**Project management:**
- Managed release [`v1.2`](https://github.com/AY2122S2-CS2103T-T09-1/tp/releases/tag/v1.2)
- Helped to wrap up and close milestone `v1.3`

**Documentation:**
- User Guide [#186](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/186), [#200](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/200), [#276](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/276)
  - Added documentation for sections Getting Started and Command format
  - Updated documentation for the `edit` feature
  - Updated documentation for the `list t/` feature
  - Added documentation for the `clear` feature
  - Conducted cosmetic tweaks to improve consistency of documentation in terms of formatting and language used
- Developer Guide
  - Updated documentation for Architecture in the design section [#295](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/295)
  - Added implementation details for search by tags [#163](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/163)
  - Added implementation details for list tags [#320](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/320)
  - Updated proposed implementation of undo/redo feature
  - Added documentation for use cases [#61](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/61)

**Team:**
- Updated the README [#59](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/59)
- Identified and fixed bugs: [#204](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/204), [#322](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/322), [#326](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/326)
- PRs reviewed (with non-trivial review comments): [#142](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/142), [#183](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/183), [#191](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/191), [#212](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/212)

**Community:**
- Reported bugs for other teams in class [9 bugs identified in the PE-D](https://github.com/nicollegann/ped/issues)
