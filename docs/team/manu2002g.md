---
layout: page
title: Manusha Galappaththi's Project Portfolio Page
---

### Project: NUSocials

NUSocials is a desktop address book application for university students who like to maintain a professional contact list. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit previously added entries
  * What it does: Allows the user to change the details of a previously added contact. This feature initially supported editing tags as well but was later removed.
  * Justification:  Users may make mistakes when entering contact details or tags. We should allow them to rectify this using an edit command
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added the ability to filter the events list
  * What it does: Allows the user to view upcoming, past or all events in the database.
  * Justification: Being able to filter and view events by upcoming/past greatly improved the usefulness of the events feature.

* **New Feature**: Added the ability to find contact entries
  * What it does: Allows the user search for a contact entry in the list according to basic particulars. Functionality for tags was added by another teammate.
  * Justification:  Users may have very large lists of contacts and may want to filter them by a certain tag or detail. We should allow them to do this using a find command.
  * Highlights: This enhancement offers both 'AND' and 'OR' search for the fields searched for. The implementation was challenging as it required good knowledge of java predicates and functional programming.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=manu2002g&tabRepo=AY2122S2-CS2103T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**:
  * User Guide:
    * Added documentation for `edit` and `showevent` features.
  * Developer Guide:
    * Added implementation for `edit`including the sequence diagram
    * Added class diagram describing the predicates used in `find` and `find -s`
    * Contributed to user stories, use cases, non-functional requirements and glossary
  
* **Test Case Implementation**:
  * ShowEventsCommand
  * ShowEventsCommandParser
  * FindCommand 
  * EditCommand


