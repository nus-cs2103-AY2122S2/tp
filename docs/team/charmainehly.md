---
layout: page
title: Charmaine Ho's Project Portfolio Page
---

### Project: TAlent Assistant™
#### Overview 
TAlent Assistant™ is a **desktop, lightweight and centralized management system** catered to NUS School of Computing professors for managing
the interview scheduling process of candidates applying to be undergraduate Teaching Assistants (TA). It is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI) built using JavaFX.

### Summary of Contributions

**Code contributions:** Click on the following
[RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=week&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=charmainehly&tabRepo=AY2122S2-CS2103-F11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
to view the detailed breakdown.

**Enhancements implemented:**
* **UI for GUI window panels (new feature, [PR #150](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/150/files)):**
Main challenges were in implementing resizeable bottom split panes, fixing alignment of details in displayed cells for interview schedule,
and wrapping/alignment for scrollable focus panel to accommodate expanding grid pane and potentially long remarks added.
* **`sort` command (new feature, [PR #76](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/76)):** One of the major difficulties was in
handling the sorting with the immutable observable list in play - new methods were created in e.g. `Logic`, `ModelManager` and `AddressBook`.
Implementation had to be later fixed (utilising a copy of the original data structure) to preserve integrity of existing data in system.
* **`view` command (new feature, [PR #167](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/167)):** Key challenge was in
integrating with existing model/classes relating to scheduling interviews and method implementations to ensure
proper ordering of scheduled interviews (earliest to latest) upon any updating or refresh of the view.
* **`find` command (extension of AB3):** Enhancement to allow for search across
multiple fields. Key challenge lay in maintaining implementation as new entities were added and abstracting predicate
classes to ensure ease of scalability. Subsequent bugs on functionality needed to be fixed to ensure the correct search
string for each attribute field is used.
* **`remark` command (referenced from AB3):** 
Implementation followed the tutorial. Key challenge was in integrating feature functionality and test cases during a much
later iteration. Required modification, significant refactoring and adding new code to sync updates to the saved json files for candidates
and corresponding interviews, as well as fixing UI component.
* **Miscellaneous contributions:** E.g. implementing office hours check for scheduling interviews

**Contributions to the UG:**
* Addition of GUI display layout section (under `Navigating the Display`)
* `find`, `remark` and `sort` command details (under `Features` >> `Managing Candidates`)
* `view` command details (under `Features` >> `Scheduling Interviews`)

**Contributions to the DG:**
* Addition of user stories, use cases, manual test cases and feature implementation (where necessary) for `find`, `remark`, `sort` and `view`

**Contributions to team-based tasks:**
* **Code-base contributions:**
  * Enhanced application description and reorganised command summary section in UG ([PR #230](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/230))
  * Full refactor of `persons` to `candidates` across directories and code ([PR #149](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/149))
  * Standardised feedback message format across commands, as well as removed traces of AB3 from logging and documentation

* **Testing and de-bugging:**
  * Contributed to identified bugs in v1.3b found [here](https://github.com/AY2122S2-CS2103-F11-2/tp/issues/240)
  * Fixed bug relating to `focus` panel refreshing and displaying wrong candidate when previous candidate in the focus panel and displayed filtered list is edited

* **Project management and issue tracking:**
  * Contributed towards the setup of team's git repository and Readme.md
  * Organised and updated team user stories excel found [here](https://docs.google.com/spreadsheets/d/1Qx6gL3KLV65z9QHPbaAWWYKH0HmyyJ_DtZmphLeiBDw/edit#gid=0)
  * Helped to maintain and update GitHub's issue tracker and assignees as and when needed


**Review/mentoring contributions:**
* **Some PR reviews/discussion threads involving non-trivial comments/suggestions:**
  * Suggested revised format of DG for features section ([PR #122](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/122#issuecomment-1077432512)) <br>
  * Raised bug on identifier previously used for candidates ([PR #161](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/161))
  * Commented suggestions on fixing of identified bugs ([view discussion thread](https://github.com/AY2122S2-CS2103-F11-2/tp/issues/240))
* **Some contributions during discussions offline:**
  * Suggestions on fixing implementation of duplicate candidate checks for `edit` ([PR #241](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/241))
  * Suggestions on implementation to bring up the candidate's details to focus panel and utilising modulo to fix bug in generating colour for profile picture ([PR #297](https://github.com/AY2122S2-CS2103-F11-2/tp/pull/297/files))

**Contributions beyond the project team:**
  * Directing others to previously addressed error [#92](https://github.com/nus-cs2103-AY2122S2/forum/issues/92)
  * Contribution to discussions [#244](https://github.com/nus-cs2103-AY2122S2/forum/issues/244) and [#257](https://github.com/nus-cs2103-AY2122S2/forum/issues/257)
  * Helping to debug other team's tp - 16 issues reported that can be found [here](https://github.com/charmainehly/ped/issues)
