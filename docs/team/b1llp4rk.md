---
layout: page
title: B1LLP4RK's Project Portfolio Page
---

### Project: HackNet

HackNet - HackNet is a desktop address book application used for finding teammates to do team projects with. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is a product of brownfield project of turning AB3 into an addressbook program targeted to computer science students trying to form a project team.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=b1llp4rk&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**:
    * Enable editing multiple persons with `edit` command[batch edit](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/79)
      * **What it does:** This feature allows user to edit multiple persons in HackNet at once, for teams and skills fields.
      * **Justification:** A student who uses HackNet might need to edit multiple persons at once. For example, the student might have learned about a team project that 5 of his friends are working on. After adding friends that were not present in the HackNet data, the student would prefer one command to `edit` the team info for all five of them, instead of having to type the `edit` command five times for each persons.
      * **Highlights:** [`batch edit`](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/79) command was created initially to enable editing multiple persons. However later the command and its features were [assimilated to `edit` command](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/84). The decision was made as an attempt to minimize the types of commands that user has to learn.

    * Establish reset and [default mode](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/82)
      * **What it does:** Reset and default modes can be chosen by the user when using the `edit` command. When using the reset mode, the team and skills value that the user provides as parameter of `edit` completely replaces the previous values for teams and skills of the person(s) that the user is editing. In default mode, the team and skills value that the user provides gets appended to the pre-existing teams and skills of the person(s) being edited.
      * **Justification:** Before establishing the two modes, the behaviour of `edit` was equivalent to the reset mode. Assume a case where a user has a person in the HackNet data that has multiple skills, and wants to add one more skill to the person. In that case, the user has to type all to skills that the person data had in HackNet again, only to add just one more skill. Establishing another behaviour defined to be default mode now allows user to pass in only the new skill as the parameter for edit. 
      * **Highlights:** Reset mode is activated only when the user deliberately indicates `r` option, and otherwise `edit` command operates in default mode. This design decision was made based on the fact that default mode would be used more often to add a new skill or team of a person, and that often used feature must be short to type. Reset mode and default mode applies to both when editing multiple persons and only one person.

    * Enable comma separation of multiple values for teams and skills
      * **What it does:** The user has to use a comma to separate multiple values of teams or skills, for `add` and `edit` command.
      * **Justification:** The previous design of passing in multiple values of teams and skills required user to start each value with `t/` or `s/`. This is very inconvinient compared to comma, especially when having to pass a large number of teams or skills. This feature was first implemented on [`edit`](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/79) command, and was later expanded to [`add`](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/87) command.
      * **Highlights:** Consecutive whitespaces before and after the commas are ignored. This is an attempt to take into account that normal use of comma is followed by a space, and to be generous towards mistakes where multiple spaces are used instead of one space.

* **Contributions to the UG**:
  * to be added soon

* **Contributions to the DG**:
  * to be added soon

* **Contributions to the team-based tasks**:
  * to be added soon

* **Review/mentoring contributions**:
  * to be added soon

* **Contributions beyond the project team**:
    * to be added soon


