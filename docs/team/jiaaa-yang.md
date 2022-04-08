---
layout: page
title: Lye Jia Yang's Project Portfolio Page
---

### Project: HackNet

HackNet helps computing students find teammates to do projects with. It allows users to add and find contacts by technical skills, providing them a platform to find the right teammates for each project.

Given below are my contributions to the project.

* **New Feature**:
  * Sort function
    * Added support for sorting command, which allows users to view their contacts in order of decreasing proficiency of a specified skill.
    * To make the function extensible, I also implemented a custom Comparator class `PersonBySkillProficiencyComparator`, so only changes to this class is required for future changes, such as sorting by multiple skills.
  * Mark/unmark function:
    * Added support to mark and unmark contacts, providing users a way to simulate possible team ups.
    * This also included a suitable change to the GUI of the JavaFX application, which highlights marked contacts for ease of use for users.


* **Enhancements to existing features**:
  * Address to GitHub username:
    * Changed the fields of address from AB3 to a GitHub username field to better suit our target users.
    * I also made the field a clickable hyperlink in the application, which opens up the GitHub profile webpage of the contact.
  * Add support for sorted list:
    * Upgraded the original `FilteredList` in AB3 to support sorting by wrapping it with a `SortedList`, while supplying APIs to filter or sort, or do both operations on the list to display in the application.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jiaaa-yang&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


* **Documentation**:
  * User Guide:
    * Added steps to show alternative way to start application when double-clicking doesn't work
    * Grouped functionality into sections (Contact Management, Team Forming, Utility) for better navigation
    * Add explanations for `sort`, `team`, `unteam`, and `show` commands, with relevant graphic aids
  * Developer Guide:
    * Documented my implementation on the team forming feature:
      * Added a class diagram for `MakeTeamCommand`
      * Added a sequence diagram for the execution of a `MakeTeamCommand`
    * Added main use cases (UC01 - UC04)


* **Team Based tasks**:
  * Added target user and brief summary for User Guide
  * Created multiple milestones (v1.1, v1.2)
  * Authored JAR file releases (v1.2, v1.3, v1.3.1)
  * Added demo screenshots for v1.3


* **Review/Mentoring**:
  * Identified bug in team member's pull request before merging ([Navigate user input history](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/69))
  * Suggested changes to maintain code quality ([Rename tags to teams](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/48))
  * Caught logical errors and unwanted changes before merging ([Edit to batchedit](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/84))


* **Beyond the Team**:
  * Identified feature flaw in other team's product during PED ([Schedule conflict](https://github.com/AY2122S2-CS2103-F09-1/tp/issues/211))
  * Identified bug in other team's product during PED ([View function bug](https://github.com/AY2122S2-CS2103-F09-1/tp/issues/215))
