---
layout: page
title: Zhengtao's Project Portfolio Page
---

### Project: Harmonia

**Overview**

Harmonia is a command-line-based personal task manager, which helps university students to manage their academic tasks. It is written in Java and has a GUI implemented with JavaFX.

Given below are my contributions to the project.

* **Code Contributed**: [link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=ainsleyj&breakdown=true)

* **Enhancements implemented**:

    1. Modified the `AddCommand` and `AddCommandParser` in Address Book Level 3 (AB3), such that they suit the requirements of Harmonia. Related PR: [#115](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/115), [#138](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/138) and [#264](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/264).

        * Converted the fields from AB3 to the fields in Harmonia. For example, the field `Deadline` and `Description` were added.
        * Changed the parsers and prefixes, to support the `AddCommand`.
        * Renamed some files and methods, to support the functionalities in Harmonia.
        * Refactored the tests related to `AddCommand`.

    2. Modified the `DeleteCommand` and `ListCommand` in AB3, such that they work correctly for the task list. Related PRs: [#122](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/122) and [#123](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/123).

    3. Refactored the messages in Harmonia. Related PRs: [#124](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/124), [#261](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/261), and [#262](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/262).

    4. Enhanced the `find` command, such that it supports:

        * Search by a range of deadline. Related PR: [#177](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/177)
        * Search by name, by keyword matching. Related PRs:
        * Search by description, by keyword matching. Related PRs: [#177](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/177) and [#214](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/214).
        * Search by priority. Related PR: [#198](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/198).
        * Search by completion status. Related PR: [#218](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/218).

    5. Improved the design of `Keyword`, to make it more object-oriented. Related PR: [#343](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/343).
    6. Improved the design of the testing of various attributes related to a task:

        * Added utility functions in Assert.java, to simplify the code in different tests. Related PR: [#316](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/316).
        * Added a new class `TypicalStrings` that collects commonly used test cases. Related PR: [#316](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/316).
        * Refactored `NameTest`, `PriorityTest`, `DeadlineTest`, `DescriptionTest` and `CompletionStatusTest`. Related PR: [#316](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/316).

* **Contributions to the UG**:

    1. Updated part 3.3 - 3.5 (list all tags, delete and find). Related PR: [#192](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/192) and [#210](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/210).

    2. Added glossary to the UG. Related PR: [#210](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/210).

    3. Added a summary of constraints on user inputs to the UG, and refactor some instructions on commands. Related PR: [#263](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/263).

* **Contributions to the DG**:

    1. Added and formatted the use cases 5 - 9. Related PR: [#65](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/65).

    2. Updated the part related to search by date. Related PR: [#166](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/166).

    3. Updated a part of appendix, such that it corresponds to the behavior of Harmonia. Related PR: [#340](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/340).

    4. Added implementation details on the search functionality, and modified the sequence diagram. Related PR: [#348](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/348).

* **Contributions to team-based tasks**:

    1. Added trial release [v1.3.trial](https://github.com/AY2122S2-CS2103T-T09-1/tp/releases/tag/v1.3.trial). Related PR: [#170](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/170).

    2. Submission of files:

        * Week 6: tP UG draft

* **Review/mentoring contributions**:

    1. Actively participated in reviewing PRs:

        [#61](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/61),
        [#100](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/100),
        [#107](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/107),
        [#149](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/149),
        [#150](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/150),
        [#153](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/153),
        [#156](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/156),
        [#158](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/158),
        [#160](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/160),
        [#163](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/163),
        [#167](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/167),
        [#169](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/169),
        [#212](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/212),
        [#278](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/278),
        [#281](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/281),
        [#290](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/290),
        [#293](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/293),
        [#323](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/323),
        [#327](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/327),
        [#342](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/342)
    2. Actively provided suggestions on various functionalities implemented by others.

* **Contributions to community**:

    1. Reported bugs for other teams in class ([9 bugs identified in the PE-D](https://github.com/ainsleyj/ped/issues))
