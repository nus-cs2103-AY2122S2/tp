---
layout: page
title: Loy Yoke Yue's Project Portfolio Page
---

### Project: UniBook

UniBook is a desktop app for students to manage their university contacts in smart organized manner, optimized for command-line interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Enhance Feature: Enhanced the ability to add/update objects in the UniBook.**
  * What it does: allows the user to add module/group/student/professor/meeting/event to the UniBook by using the Add Command.
  * Justification: This feature improves the product significantly because a user can now add various information to the app.
  * Highlights: It was challenging to implement the `add o/student` command as I had to parse module codes and consecutive group names in order. It required a creative solution design. 
  * Implemented all the enhancements of the Add Command.
    * `add o/module`
    * `add o/group`
    * `add o/student`
    * `add o/professor`
    * `add o/meeting`
    * `add o/event`
  * Thoroughly checked all user inputs to display the relevant error messages such that users are able to rectify their mistake easily.
  * Added a few tests for Add Command.

* **New Feature: Implemented method to parse DateTime in user input**
  * What it does: Parses the user's input of DateTime
  * Justification: This is a common method which some other commands require.
  * Highlights: Java's LocalDateTime automatically accepted boundary values near the end of a month. This required the use of Java's ResolverStyle class.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=yokester98&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Refactored the AB3 code to assist with morphing the product into UniBook.
    * Spotted and advised teammate to remove redundant code.

* **Documentation**:
    * User Guide:
      * Section describing Add Command
        * Added explanation, format and examples for the various Add Commands.
      * Command Summary
        * Added the Add Commands to the summary and included links to the examples.
    * Developer Guide:
      * Added the Add Command sequence diagram and description.
      * Added user stories and use cases related to Add Command.

* **Community**:
    * Reviewed PRs of teammates
      * Total number of PRs reviewed: 18
      * Examples: [#81](https://github.com/AY2122S2-CS2103-W16-1/tp/pull/81) [#90](https://github.com/AY2122S2-CS2103-W16-1/tp/pull/90) [#101](https://github.com/AY2122S2-CS2103-W16-1/tp/pull/101)
    * Tested PRs and reported bugs to teammates.
    * Contributed to discussions on Telegram group, assisted teammates with their code and was open to receiving feedbacks.
