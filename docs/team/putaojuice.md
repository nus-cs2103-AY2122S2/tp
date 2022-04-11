---
layout: page
title: Chen Yu An's Project Portfolio Page
---
### Project: NUScheduler
NUScheduler is a desktop app for Year 1 Computing students to assist with more efficient management of tasks and contacts,
optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, NUScheduler can schedule your tasks faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Implemented `AddTask` command ([#26](https://github.com/AY2122S2-CS2103-F11-4/tp/pull/26)).
  * What it does: Allow the user to add a task to the application with description and deadline (optional)
  * Justification: Our target audience is year 1 computing students. It helps them to keep track of their assignments
  and any other daily tasks.
  * **Classes created**:
    * `AddTaskCommand`
    * `AddTaskCommandParser`
    * `TaskList`
    * `Task`
  * **Tests written**:
    * `AddTaskCommandTest`
    * `AddTaskCommandParserTest`
    * `TaskListTest`
    * `TaskTest`
  * **Additional info**:
    * `Task` and `TaskList` classes are created to capture the information of tasks and store them as an `ArrayList` in
  `ModelManager`.

* **New Feature**: Implemented `TaskListStorage` ([#74](https://github.com/AY2122S2-CS2103-F11-4/tp/pull/74)).
  * What it does: The system will write the task list to user's local folder in Json format whenever there is a change
  in the task list. The system will read from the saved task list from user's local folder in Json format when starting up
  to load the stored task list from the last access.
  * Justification: This feature allows the user to save their task list and continue where it was left from.
  * **Classes created**:
    * `ReadOnlyTaskList`
    * `JsonAdaptedTask`
    * `JsonSerializableTaskList`
    * `JsonTaskListStorage`
    * `TaskListStorage`
  * **Tests written**:
    * `JsonAdaptedTaskTest`
    * `JsonSerializableTaskListTest`
    * `JsonTaskListStorageTest`
    * `TypicalTask`
  * **Additional info**:
    * This feature requires a good understand of how the application is designed and involves modification to
    `Logic`, `Model` and `Storage`.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=putaojuice&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=putaojuice&tabRepo=AY2122S2-CS2103-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false).

* **Project management**:
  * Managed release `v1.1`, `v1.2`, `v1.3`, `v1.4`.
  * Organised weekly project meeting and set up weekly milestone and direction of project.
  * Reviewed team's pull requests.
  * Wrap up every milestone.
  * Complete administrative matters for the team.
  * Viewed through all PE-D issues and assigned relevant issues to the team members.
  * Released `v1.2`: [View](https://github.com/AY2122S2-CS2103-F11-4/tp/releases/tag/v1.2).
  * Released `v1.3 Trial`: [View](https://github.com/AY2122S2-CS2103-F11-4/tp/releases/tag/v1.3.trial).
  * Released `v1.3`: [View](https://github.com/AY2122S2-CS2103-F11-4/tp/releases/tag/v1.3)
  * Released `v1.4`: [View](https://github.com/AY2122S2-CS2103-F11-4/tp/releases/tag/v1.4)

* **Enhancements to existing features:**
  * Added `tasklist.json`, a separate JSON file to store the tasks added by user so that it does not interfere with
  the original `addressbook.json`
  * Wrote tests for all new features implemented.

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `AddTask`.
    * Help in proofreading user guide.
  * Developer Guide:
    * Contribute non-functional requirement in the developer guide.
    * Contribute to glossary in the developer guide.
    * Added MSS for `AddTask`.
    * Added information about the implementation of the `AddTask` command ([#60](https://github.com/AY2122S2-CS2103-F11-4/tp/pull/60)).
      * Added 2 UML diagrams to aid the explanations.
    * Help in proofreading developer guide.
  * Weekly Team Meeting Minutes:
    * Help in recording meeting minutes.
    * Screenshot `v1.2` and `v1.3` demo.

* **Community**:
  * Participate in forum ([#166](https://github.com/nus-cs2103-AY2122S2/forum/issues/166), [#240](https://github.com/nus-cs2103-AY2122S2/forum/issues/240), [#244](https://github.com/nus-cs2103-AY2122S2/forum/issues/244)).
  * Reported 13 bugs for other team during PE-D: [View](https://github.com/putaojuice/ped/issues).

* **Tools**:
  * Set up the team's Github group and repo.
  * Added CodeCov integration to the repo.
  * Enabled assertions for the project ([#61](https://github.com/AY2122S2-CS2103-F11-4/tp/pull/61)).

* **Statistics**:
  * Reviewed PRs: [View](https://github.com/AY2122S2-CS2103-F11-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aputaojuice)
  * Raised PRs: [View](https://github.com/AY2122S2-CS2103-F11-4/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Aputaojuice)
  * Issues Taken: [View](https://github.com/AY2122S2-CS2103-F11-4/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Aputaojuice)
