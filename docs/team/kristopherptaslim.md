---
layout: page
title: Kristopher Putra Taslim's Project Portfolio Page
---

### Project: ArchDuke

ArchDuke - is a desktop application used for students' contacts and groups management. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=kristopherptaslim&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=KristopherPTaslim&tabRepo=AY2122S2-CS2103-W16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: `AddTask` command (PR [#58](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/58))
  * What it does: Allows users to add task in a group in ArchDuke.
  * Justification: The feature is important in solving the target user's problem which is to manage the group tasks.
  * Highlights: This feature implementation is inspired by how a `Person` can be added to a `Group`. Hence, this introduces a new model called `Task` which is able to be added to an existing `Group`. 

* **Enhancements to existing features**: `Find` command (PR [#74](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/74))
    * What it does: `Find` command now allows user to search for contacts based on their attributes (`Phone`, `Email`, `Academic Major` and `Tags`) instead of just`Name`.
    * Justification: As `Person` has other attributes besides `Name`, it is intuitive to refactor the previous `Find` command to allow for broader search by using other attributes.
    * Highlights: In order to follow the base code, minimal modifications are done by using polymorphism and `AttributeContainsKeywordsPredicate` interface which is implemented by all the other attributes.
* **Testing**:
    * `PhoneContainsKeywordsPredicate`,`EmailContainsKeywordsPredicate`,`AcademicMajorContainsKeywordsPredicate` and `TagsContainsKeywordsPredicate` (PR [#200](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/200))
    * `Task`, `TaskName` and `UniqueTaskList` (PR [#203](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/203))
* **Documentation**:
    * User Guide:
        * Added parts in `find` command (PR [#198](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/198))
    * Developer Guide:
        * Added some Use Cases (PR [#27](https://github.com/AY2122S2-CS2103-W16-3/tp/pull/27))
        * Edited some user stories
        * Wrote the implementation part for `find` feature

    
