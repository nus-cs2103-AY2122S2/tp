---
layout: page
title: Chan Wei Jie's Project Portfolio Page
---

### Project: ManageEZPZ

ManageEZPZ is a desktop application that allows managers or supervisors to manage employees and assign tasks to them. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **Enhancement**: Creation of the model component for `Task` as a baseline for the use by other teammates. [#64](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/64), [#81](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/81)
  * What it does : The creation of the `Task` component allows for smoother creation of the different types of Task related commands.
  * Justification : This enhancement is important as the different type of Task related command such as adding, deleting, editing and even finding. All relies on the methods created in the `Task` model.  
  * Highlights : This enhancement required me to constantly update the methods that are required by my teammates as the methods in the `Task` Model changes as more commands are being added. As well as the importance of abstraction principles in the implementation of the `Task` class.


* **Enhancement**: Creation of Enum class `Priority`.
  * What it does : The Enum class `Priority` allows the different types of Task to be assigned with a Priority. [#145](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/145)
  * Justification : This enhancement is important as the tagPriority command relies on Tasks having priorities.
  * Highlights : Creating a separate enum class for `Priority` makes the `Task` class more readable as opposed to clustering them together.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=chanweijie&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


* **Project management**:
  * Managed milestones `v1.1` - `v1.4` on GitHub. 
  * Ensured the team's completion of weekly deliverables and its deadlines.
  * Managed releases `v1.2` - `v1.4` on GitHub.
  * Delegated team members what functionalities each of us had to implement to prevent any conflict and provide a better workflow.

<div style="page-break-after: always;"></div>

* **Enhancements to existing features**:
  * Changed the Employee class to not have `address` & `tag` fields. [#62](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/62)
  * Removed the `address` and `tagged` fields in the affected json storage files. [#70](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/70) 
  * Updating `Description` to only validate empty descriptions. [#104](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/104)
  * Updating `Task` class to be abstract so that it can be inherited by its sub-classes like `todo`/`event`/`deadline`. [#156](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/156)
  * Refactored the `editcommand` to `editEmployeeCommand`. [#167](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/167)
  

* **Documentation**:
  * User Guide:
      * Added Commands to be implemented in v1.2. [#21](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/21)
      * Added back the `delete` command for Person but renamed it as `deleteEmployee`. [#103](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/103)
  * Developer Guide :
      * Added implementation details of the `Task` Model, including two class diagrams. [#120](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/120), [#127](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/127)
      * Added appendix instructions for manual testing & appendix for Effort. [#286](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/286)

* **Community**:
  * Setting up the GitHub team org & repo.
  * Necessary general code enhancements such as renaming the product: [#175](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/175), [#176](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/176)
  * PRs reviewed (with non-trivial review comments): [#73](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/73), [#30](https://github.com/AY2122S2-CS2103-F11-1/tp/pull/30)
  * Reported bugs and suggestions for other teams: [Dry run PE](https://github.com/ChanWeiJie/ped/issues)
  

