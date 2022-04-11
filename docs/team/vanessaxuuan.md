---
layout: page
title: Khor Vanessa's Project Portfolio Page
---

### Project: Teaching Assistant Contact Helper (TACH)

TACH helps CS Teaching Assistants tutoring multiple modules & classes by keeping track of their students and monitoring their progress on their tutorials. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=vanessaxuuan&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **New Feature**: added `addtg` feature [(#60)](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/60)
  - What it does: allows users to add tutorial group(s) to an existing student.
  - Rationale: this provides greater flexibility for the users as they will be able to add tutorial groups that they have missed out on when the student was being created initially, instead of recreating the student.
* **Enhancements to existing features**:
  - Extended `find` feature to allow partial word/phrase matching. [(#172)](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/172)
    - e.g. `find ja` returns `javier` and `jan`.
  - Changed `find` feature to search for students whose names match ALL keywords given instead of ANY keywords. [(#77)](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/77)
    - e.g. `find Alex Ko` returns `Alex ko` and `Alex koh` but not `Alex` or `Alexius ko`.
  - Enhanced `findtg` feature to remove extra whitespace(s). [(#169)](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/169)
    - e.g. Both `findtg CS2101<space>G08` and `findtg CS2101<space><space>G08` returns the tutorial group `CS2101 G08`.
  - Cosmetic updates of GUI: text wrapping and padding. ([#79](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/79), [#171](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/171))
* **Contributions to team-based tasks**:
    - Set up demo for v1.2.
    - PR reviews (with non-trivial comments). [(#34](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/34), [#57](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/57), [#154](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/154)) 
* **Project management**:
  - Created our discussion group on Discord.
  - Note takings during project meetings.
  - Managed submissions for User Guide drafts and (CS2101) peer review forms.
* **Documentation**:
  * *User Guide*
    * Updated the **Commands** section. [(#91)](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/91/files)
      * Added documentation for `addtg`.
      * Added diagrams for `list`, `add`, `deletetgall`, `findtg` and `edit`.
    * Added Glossary.
    * Updated FAQ. 
    * Added Quick Start instructions for MacOs users
  * *Developer Guide*
    * Added Target User Profile.
    * Added Glossary.
    * Added documentation and UML diagrams for the feature `addtg`. [(#76)](https://github.com/AY2122S2-CS2103T-W15-3/tp/pull/76)
