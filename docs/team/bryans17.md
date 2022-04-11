---
layout: page
title: Bryan Seah's Project Portfolio Page
---
### Project: TAddressBook

**TAddressBook** is a student project based on [AddressBook Level 3](https://github.com/se-edu/addressbook-level3). It is a desktop application created with the aim of helping CS2030S Teaching Assistants (TAs) keep track of student's lab assignments.

Given below are my contributions to the project.
- **New Feature:** Add Lab [#67](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/67)
  - What it does:
    - Adds a new `Lab` with an Integer `labNumber` between 0 and 20 inclusive and default `LabStatus` of `UNSUBMITTED`, to the `MasterLabList` as well as to all `Student`s in the list.
    - The Ui automatically updates to show the newly added `Lab`.
  - Justification:
    - This is the main functionality of TAddressBook which allows CS2030S TAs to add labs the TA wants to keep track of.
  - Highlights:
    - This feature involved adding a new component to the UI to show the Labs which is binded to an `ObservableList<Lab>` for automatic update of the UI when the Lab changes.

- **New Feature:** Remove Lab [#72](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/72)
  - What it does:
    - Removes a  `Lab`, with an Integer `labNumber` between 0 and 20 inclusive, from the `MasterLabList` as well as from all `Student`s in the list.
    - The Ui automatically updates to show the deleted `Lab`.
  - Justification:
    - This allows CS2030S TAs to delete labs that they accidentally added or no longer want to keep track of.
  - Highlights:
    - This feature takes advantage of the fact that the `LabList` of all students are aligned with the `MasterLabList` as they are sorted by increasing lab number, as we only need to find the index of the Lab we want to delete in the `MasterLabList` and we can then just remove the Lab at that index in all the students' `LabList` without doing another search.

- **Code contributed:**
  - Code contributed can be found at this [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=bryans17&tabRepo=AY2122S2-CS2103-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false).
- **Project management:**
  - Managed release of v1.2 and v1.3 and issue tracker.
  - Add screenshots of product for v1.2 and v1.3 on team project document.
  - Manage some team weekly tasks.
- **Enhancements to existing features:**
  - Updated Attributes of Students
    - Added `GithubUsername` and `Telegram` attributes to `Student` and updated tests related to `Student` and it's attributes.
    - [#45](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/45), [#49](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/49).
  - Updated `AddCommand` and `EditCommand` to take into account Labs: [#67](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/67).
  - Wrote tests for new and old classes:
    - e.g.
      - increased code coverage by 0.58% in [#45](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/45).
      - increased code coverage by 0.50% in [#49](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/49).
      - increased code coverage by 3.98% in [#77](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/77).
  - Refactor JSON data serialization and deserialization for the new Lab data: [#93](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/93).
  - Fix bugs from PE-D: [#118](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/118), [#119](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/119).
- **Documentation:**
  - User Guide:
    - Added documentation for `labadd`, `labrm`.
    - Updated documentation for editing JSON data and changed AB3 stuff to TAddressBook.
    - [#15](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/15), [#35](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/35), [#93](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/93).
  - Developer Guide:
    - Add UC3, UC4, and UC6: [#29](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/29), [#129](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/129).
    - Add implementation details of `AddLabCommand`, `Lab` component, and update existing UML diagrams to match our code base: [#121](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/121).
- **Community:**
  - Reviewed PRs of other group members, some examples: [#50](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/50), [#70](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/70), [#71](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/71), [#87](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/87), [#90](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/90), [#91](https://github.com/AY2122S2-CS2103-F10-1/tp/pull/91).
  - Reported 9 bugs for another group in [PE-D](https://github.com/bryans17/ped/issues).

