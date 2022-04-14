---
layout: page
title: Terng Yan Long's Project Portfolio Page
---

### Project: Teaching Assistant’s Personal Assistant (TAPA)

TAPA is a desktop contact management application used to manage students' contact and progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=korochii&breakdown=true)

* **New Feature**:
  * Added the ability to archive existing data. ([#44](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/44))
    * What it does: Saves a copy of the details currently saved in TAPA into a separate .json file
    * Justification: This feature increases the user's efficiency, as he/she will no longer need to traverse through the file directories in order to make a copy of the current .json file.
    * Highlights: The implementation for this feature was slightly challenging as it deals with the storage model (unlike the other commands), and new classes such as FileUtil and JsonUtil have to be utilised.

* **New Feature**:
  * Added the ability to check a particular student's list of tasks. ([#59](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/59))
    * What it does: Displays all the tasks that are allocated to a particular student.
    * Justification: Users will no longer have to scroll through the list of students (which can be quite long). In addition, unlike the find command, this feature classifies the list of tasks into separate groups (based on completion status), for easier viewing.

* **New Feature**:
  * Added the ability to view the completion status of a particular task. ([#101](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/101))
    * What it does: Displays a list of students who are taking the specified module, and have been assigned with a particular task. The completion status of each student in the list will be displayed as well.
    * Justification: Users will no longer have to scroll through the list of students, and "extract out" the students who are taking the particular module and has the particular task.

* **Enhancements to existing features**:
  * Modified the feature which allows the user to add a student into TAPA. ([#31](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/31))
    * In TAPA, we intend to have different fields for the `Person` model. Hence, the `add` command have to be adjusted according to the new fields that we have created (some of which are optional).
  * Update the GUI such that the result display window is more visible to the user. ([#97](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/97))
  * Wrote additional tests for new and existing features which increased code coverage.
    ([#92](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/92),
    [#190](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/190),
    [#191](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/191),
    [#192](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/192),
    [#193](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/193),
    [#202](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/202),
    [#203](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/203))

* **Contribution to team-based tasks**:
  * Set up the GitHub team org and repo.
  * Worked with the rest of the team in renaming the product.
  * Morphed the original AB3 into TAPA, by refactoring the entire codebase as well as testcases. ([#31](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/31), [#92](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/92))
  * Set up tools such as Gradle and Codecov.
  * Maintained the issue tracker by creating relevant milestones and assigning/labelling some issues.
  * Managed releases `v1.2.1` - `v1.3.1` (3 releases) on Github
  * Update the User Guide with content that are not specific to a feature such as the description, quick start and FAQ sections. ([#30](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/30))
  * Update the Developer Guide with content that are not specific to a feature, such as in the `Appendix` section. ([#28](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/28))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `help`([#42](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/42)),
      `archive`([#30](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/30)),
      `progress`([#101](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/101))
      and `task`([#30](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/30)).
    * Modified the existing documentation for the features `add`. ([#30](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/30))
  * Developer Guide:
    * Added implementation details for the `add`([#107](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/107)),
      `archive`([#115](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/115)),
      `progress`([#115](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/115))
      and `task`([#108](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/108)) commands.
    * Added/updated use cases: UC01([#255](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/255)), UC05([#250](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/250)), UC09([#250](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/250)), UC12([#250](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/250))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#32](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/32),
    [#39](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/39),
    [#53](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/53),
    [#56](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/56),
    [#61](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/61),
    [#91](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/91),
    [#106](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/106),
    [#120](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/120),
    [#134](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/134)
  * Reported a total of 13 bugs and suggestions for other teams in the class (as seen [here](https://github.com/Korochii/ped/issues)).

