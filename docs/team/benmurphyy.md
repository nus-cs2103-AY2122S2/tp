---
layout: page
title: Ben Murphy's Project Portfolio Page
---

### Project: UniBook

UniBook is a desktop app for students to manage their university contacts related to their studies in an organized manner, optimized for command-line interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New and enhanced features**: 
  * Revamped Graphical User Interface (GUI) to suit the application
    * Added new views to the GUI to view the new different types of information that can be stored in UniBook
      * Views include: the people view, modules view and groups view of UniBook
      * Implementation required the creation and integration of many classes, along with the complementing design and layout fxml files
    * Added ease-of-use features such as clicking navigation
    * Styled GUI to make it more aesthetically pleasing
  * Added ability to manage university groups to UniBook
    * What is it: A group in UniBook corresponds to a group related to a user's studies in UniBook. It stores a list of members, along with details such as a list of meeting times, which a student can easily view and manage
    * Designed and implemented `Group` and its related classes
  * Added ability to store different types of classes and variables in storage, to meet the needs of the application
    * Justification: As UniBook required a lot of additional classes to be added to AB3, storage had to be heavily modified to support them
    * Implemented all the JSON adapted versions of the model classes in UniBook so that they could be stored easily using Jackson
    * Added support for DateTime objects and class subtyping in Jackson
      * UniBook contains two child classes `Student` and `Professor`, which both inherit from an abstract class `Person`
      * Support had to be added so that storage of the two different child classes could be differentiated
    * Ensured that deserialization works given the bidirectional associations that many classes share
    * Added extensive checks to the JSON storage file before loading into UniBook to reject corrupted JSON files

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=benmurphyy&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Project management**:
  * As Team Lead, I was responsible for:
    * Scheduling and directing meetings
    * Allocating project work and setting deliverables and deadlines, ensuring they were met
    * Setting standards for the team to follow, these include:
      * Command format standards - to ensure consistent commands to enhance ease-of-use
      * Documentation format standards - to ensure consistent documentation
    * Defining interface methods between components of UniBook - to ensure little duplication of code and to minimise bugs
    * Setting up and maintaining the Github project team and repo.
  * Managed releases `1.2`, `1.3.1` and `1.3.2`
  * **Documentation**:
  * User Guide: 
    * Added:
      * Section describing the details of the entities that UniBook can store - Person, Module and Group
        * Detailed the constraints of the fields of each entity clearly in a table
      * Section describing the GUI of UniBook
        * Showed and elaborated on the different views in UniBook - People view, Modules view and Groups view
        * Described the ease-of-use features such as click button navigation from people view
      * Appendix
  * Developer Guide:
    * Added diagrams and descriptions of components of UniBook
    * Added user stories and use cases relating to UI and storage
  * Index page:
    * Changed the index page from AB3 to UniBook by replacing screenshots and descriptions to be on UniBook instead of AB3
* **Community**:
  * Actively reviewed PRs of teammates
    * Total number of PRs reviewed: 49
  * Opened issues for features that needed to be completed, along with bugs noticed 
    * Total number of issues opened: 17
  * Carefully analyzed code written by teammates and reported bugs and suggestions to them where appropriate
  * Was active in, and often initiated, discussions on Github issues, PRs and Telegram group chat
