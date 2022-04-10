---
layout: page
title: Mo Yunbin's Project Portfolio Page
---

- [1. New Features](#new_feature)
- [2. Code contributed](#code_contributed)
- [3. Project management](#project_management)
- [4. Enhancements to existing features](#enhancements)
- [5. Documentation](#documentation)
- [6. Community](#community)

## Project: Mr. Agent

Mr. Agent is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Mr. Agent can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

### <a id="new_feature"></a>1. New Features
* **New Feature**: Added the ability to list/add/edit/delete/find appointments (Pull requests [\#33](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/33), [\#80](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/80), [\#81](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/81))
  * What it does: allows the target users (insurance agents) to manage their appointments with clients on the app by saving/editing appointment details including text description and date and time. Also, users can search for appointments using keyword(s).
  * Justification: This feature makes the product much more complete because appointment is an important part of daily work of the target user group that needs to be noted down and referred to frequently.
  * Highlights: This enhancement is made extensible such that it is easy to add more attributes to describe an appointment if necessary, which does not require massive change to the code base.

* **New Feature**: Added the ability to sort appointments (Pull request [\#90](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/90))
  * What it does: allows users to sort the appointment list in ascending/descending order by date and time.
  * Justification: Appointments are usually time-sensitive, it is important to provide a quick way for user to prioritize and visualize more urgent appointments.
  * Highlights: This enhancement is implemented such that more sorting options could be made available easily with minor modification, if any ordering of the list is deemed to be meaningful for the users.

### <a id="code_contributed"></a>2. Code contributed
* [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=yunbinmo&breakdown=true)
* [Pull Request](https://github.com/AY2122S2-CS2103-F09-3/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Ayunbinmo)

### <a id="project_management"></a>3. Project Management
  * Set up code coverage tool for the project.
  * Contributed incrementally and iteratively by following the [forking workflow](https://nus-cs2103-ay2122s2.github.io/website/se-book-adapted/chapters/revisionControl.html#forking-flow) strictly
  * Helped to test and debug other components of the application

### <a id="enhancements"></a>4. Enhancements to existing features
  * Set up the new GUI layout according to specification (Pull requests [\#37](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/37), [\#45](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/45))
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#102](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/102), [\#105](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/105))

### <a id="documentation"></a>5. Documentation
  * User Guide:
    * Added documentation for the appointment features, including list/add/delete/find/edit/sort `Appointment` command [\#113](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/113)
    * Did cosmetic tweaks to existing documentation for cleaner view [\#118](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/118)
  * Developer Guide:
    * Add user stories for the appointment feature. [\#26](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/26)
    * Updated implementation details of the `Model` and `Appointment` component [\#101](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/101)
    * Added implementation details of the sort/find appointment command [\#112](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/112) 
    

### <a id="community"></a>6. Community:
  * PRs reviewed (with non-trivial review comments): [\#89](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/89)
  * Contributed to forum discussions (examples: [#141](https://github.com/nus-cs2103-AY2122S2/forum/issues/141), [#58](https://github.com/nus-cs2103-AY2122S2/forum/issues/58))
