---
layout: page
title: JB Kim's Project Portfolio Page
---

## Project: Networkers

Networkers is a desktop app for managing contacts for network technicians, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Networkers can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

### Summary of my contributions

- **New Feature**: Added a feature in which the users can add a Procedure to an existing Client.
  - Justification: Users may wish to record what Procedures (such as installing modem, connecting devices) they have done, or will be done to the Client.
  - Highlights: Making use of `Model` component to delegate the update, thereby adhering to the OOP principle.

- **New Feature**: Added a feature in which the users can list down all the Procedures scheduled on a specified date.
  - Justification: Users may wish to know what Procedures they have performed on a particular date, or also generate a to-do list for future Procedures.
  - Highlights: Making use of `Pair` container to connect each Procedure to an associated Client, and `Stream` feature for iteration.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jbkim1999&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zA=jbkim1999&zR=AY2122S2-CS2103T-W13-1%2Ftp%5Bmaster%5D&zACS=237.07692307692307&zS=2022-02-18&zFS=jbkim1999&zU=2022-04-07&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false&zFT=docs~functional-code~test-code&since=2022-02-18)

- **Enhancements implemented:**
  - Added a `Plan` attribute to the Client (e.g. the name of the plan and bandwidth that a Client has subscribed for).
    - Modifications were made, especially on the test cases, to handle the regressions caused.

- **Contributions to the UG:**
  - Added documentations for `AddProc`, `ListProcOn` commands
  - Revised general formats of the UG
    - Divided the feature section into Format, Note, and Example subsections for enhanced readability.

- **Contributions to the DG:**
  - Reflected the architectural design (such as diagrams) changes
    - Renamed some classes involving `Person` to `Client`.
    - Added in classes as a result of having additional `Procedure` class.
  - Described the implementation of `ListProcOnCommand`
    - Made use of class and sequence diagram to illustrate the enhancement.

- **Contributions to team-based tasks**
  - Conducted product demo for iteration v1.2 and v1.3

- **Review/mentoring contributions:**
  - PRs reviewed (with non-trivial review comments): 
    - [#73](https://github.com/AY2122S2-CS2103T-W13-1/tp/pull/73), [#88](https://github.com/AY2122S2-CS2103T-W13-1/tp/pull/88), [#99](https://github.com/AY2122S2-CS2103T-W13-1/tp/pull/99)
    - ... and more (can be found under [tP comments dashboard](https://nus-cs2103-ay2122s2.github.io/dashboards/contents/tp-comments.html))

- **Contributions beyond the project team:**
  - Forum participation count as commenter: [9](https://github.com/nus-cs2103-AY2122S2/forum/issues?q=commenter%3Ajbkim1999)
  - Forum participation count as author: [4](https://github.com/nus-cs2103-AY2122S2/forum/issues?q=author%3Ajbkim1999)
