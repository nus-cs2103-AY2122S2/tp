---
layout: page
title: Ivor's Project Portfolio Page
---

### Project: NUScheduler

NUScheduler is a desktop app for Year 1 Computing students to assist with more efficient management of tasks and contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUScheduler can schedule your tasks faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Implemented `DeleteTask` command.
    * What it does: Allow the user to delete a particular task based on its ID in the Tasklist
    * Justification: Our target audience is year 1 computing students. It helps them to remove tasks that are not relevant anymore,
  which helps to keep the program neat.
    * **Classes created**:
    * `DeleteTaskCommand`
    * `DeleteTaskCommandParser`
    * **Tests Written**:
        * `DeleteTaskCommandTest`
        * `DeleteTaskCommandParserTest`
        <div style="page-break-after: always;"></div>
    * **Additional info**:
        * Updated ParserUtil method of parseNumber to work with DeleteTaskCommand
        * Added relevant activity and sequence diagrams into Developer Guide

* **New Feature**: Implemented `UpdateTask` command.
    * What it does: Allow the user to update a particular task attributes based on its ID in the Tasklist
    * Justification: Our target audience is year 1 computing students. It helps them to update task that details have changed to ensure
  the program always reflects the latest details.
    * **Classes created**:
    * `UpdateTaskCommand`
    * `UpdateTaskCommandParser`
    * **Tests Written**:
        * `UpdateTaskCommandTest`
        * `UpdateTaskCommandParserTest`
    * **Additional info**:
        * Added additional class `UpdateTaskDescriptor` in `UpdateTaskCommand` to assist functionality
        * Added relevant activity and sequence diagrams into Developer Guide

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=Ivor&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)

* **Project management**:
    * Managed release `v1.1`, `v1.2`, `v1.3`, `v1.4`.
    * Participated in weekly project meetings and set up weekly milestones with the team.
    * Reviewed pull requests.

* **Enhancements to existing features**:
    * Wrote additional tests to increase coverage for `DeleteTask`

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `DeleteTask`.
        * Added documentation for the feature `UpdateTask`.
        * Added visuals in UG to enhance readability and understandability.
        * Fixed User Guide bugs.
    * Developer Guide:
        * Contribute to glossary in the developer guide.
        * Added user story for `DeleteTask` feature.
        * Added MSS for `DeleteTask`.
        * Add sequence diagram for the feature `DeleteTask`.
        * Add activity diagram for the feature `DeleteTask`.
        * Added user story for `UpdateTask` feature.
        * Added MSS for `UpdateTask`.
        * Add sequence diagram for the feature `UpdateTask`.
        * Add activity diagram for the feature `UpdateTask`.

* **Community**:
    * PRs reviewed with comments to submitted PRs
    * Reported 9 bugs during [PE-D](https://github.com/ivorcmx/ped/issues)
    * Submitted forum post [#240](https://github.com/nus-cs2103-AY2122S2/forum/issues/240).

* **Statistics**:
    * Reviewed PRs: [24](https://github.com/AY2122S2-CS2103-F11-4/tp/pulls?q=type%3Apr+reviewed-by%3Aivorcmx)
    * Raised PRs: [20](https://github.com/AY2122S2-CS2103-F11-4/tp/pulls/@me)
    * Issues Taken: [45](https://github.com/AY2122S2-CS2103-F11-4/tp/issues?q=assignee%3A%40me+is%3Aclosed)
