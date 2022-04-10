---
layout: page
title: Lye Jia Yang's Project Portfolio Page
---

### Project: HackNet

HackNet helps computing students find teammates to do technical projects with. It allows users to add and find contacts by technical skills, providing them a platform to find the right teammates for each project.

Given below are my contributions to the project.

* **New Feature**:
  * Sort feature
    * **What it does**: Allow users to sort contacts by proficiency level of specified skill.
    * **Justifications**: This feature allows users to find contacts strongest at a skill, which is essential to forming a good team for a technical project.
    * **Highlights**: To make this feature extensible, I implemented a custom Comparator class `PersonBySkillProficiencyComparator`. This allows future changes, such as sorting by multiple skills, to only require changes to this class.
  * Simulating team feature:
    * **What it does**: Allow users to mark/unmark contacts as potential teammates, and show them later.
    * **Justifications**: This feature allows users to simulate potential teams, allowing them to mark contacts as they filter and search for suitable ones. This helps them form their team as they search, forming the core of HackNet.
    * **Highlights**: I implemented two commands, `ShowCommand` and `MakeTeamCommand` for this feature. In particular, `MakeTeamCommand` made use of an enumeration to decide to mark or unmark contacts, removing duplication. This feature also included a GUI change, where marked contacts are highlighted in a shade of turquoise, improving the user experience.

* **Enhancements to existing features**:
  * Address to GitHub username:
    * Changed the fields of address from AB3 to a GitHub username field to better suit our target users. This required large refactoring changes in both functional and test code to ensure existing functionality do not regress.
    * I also made the field a clickable hyperlink with suitable CSS styling in the application, which opens up the GitHub profile webpage of the contact.
  * Add support for sorted list:
    * Upgraded the original `FilteredList` in AB3 to support sorting by wrapping it with a `SortedList`, while supplying APIs to filter or sort, or do both operations on the list to display in the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jiaaa-yang&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
  * User Guide:
    * Added steps to show alternative way to start application when double-clicking doesn't work
    * Added overview section, which explains important components of our application using an annotated diagram
    * Grouped functionality into sections (Contact Management, Team Forming, Utility) for better navigation
    * Add explanations for `sort`, `team`, `unteam`, and `show` commands, with relevant graphic aids
  * Developer Guide:
    * Documented my implementation on the team forming feature:
      * Added a class diagram for `MakeTeamCommand`
      * Added a sequence diagram for the execution of a `MakeTeamCommand`
    * Added main use cases (UC01 - UC04)

* **Team Based tasks**:
  * Updated README.md to fit new product
  * Created multiple milestones (v1.1, v1.2)
  * Authored JAR file releases (v1.2, v1.3, v1.3.1)
  * Added demo screenshots for v1.3

* **Review/Mentoring**:
  * Identified bug in team member's pull request before merging ([Navigate user input history](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/69))
  * Suggested changes to maintain code quality ([Rename tags to teams](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/48))
  * Caught logical errors and unwanted changes before merging ([Edit to batchedit](https://github.com/AY2122S2-CS2103T-W13-3/tp/pull/84))
