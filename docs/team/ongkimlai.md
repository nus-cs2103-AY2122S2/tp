---
layout: page
title: Ong Kim Lai's Project Portfolio Page
---

### Project: NUSocials

NUSocials is a desktop address book application for university students who like to maintain a professional contact list. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Feature Enhancement**: Added the ability to delete multiple contacts.
  * What it does: allows the user to delete multiple contacts in a single command.
  * Justification: This feature improves the product as a user can efficiently delete many contacts at once instead of inputting a delete command for each contact that he or she wants to delete.
  * Highlights: This enhancement affects the existing UI layout. It required an in-depth analysis of how the contacts would be displayed during the deletion process.

* **New Feature**: Added the ability to remove tags from existing contacts.
  * What it does: allows the user to remove tags from an existing contact.
  * Justification: This feature improves the product significantly because it allows the user to simply remove a tag, otherwise users have to recreate another contact.

* Updated the UI to fit requirements for new features
  * Added a split-pane to fit 2 panels. (contacts panel and events panel)
  * Designed the events panel.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=ongkimlai&tabRepo=AY2122S2-CS2103T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&zA=ongkimlai&zR=AY2122S2-CS2103T-W11-1%2Ftp%5Bmaster%5D&zACS=81.23391812865498&zS=2022-02-18&zFS=&zU=2022-02-26&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Documentation**:
  * User Guide:
    * Added documentation for `removetag` and `delete` enhancement
    * Updated documentation for exact tag names for `find` and `tag`
    * Added all screenshots found in User Guide
    * Added constraints section for all features
  * Developer Guide:
    * Added user stories for `removetag`, `delete` multiple contacts, viewing events
    * Added use cases for `removetag`, `find`, `delete` multiple contacts, viewing events
    * Added implementation for `delete` multiple contacts and its sequence diagrams
    * Added implementation for `removetag` and its sequence and object diagrams
    * Updated UI component class diagram
    * Cosmetic tweaks to the formatting of use cases
    * Contributed to the NFRs

* **Test Case Implementation**:
  * DeleteCommand
  * DeleteCommandParser
  * TagCommand
  * TagCommandParser
  * RemoveTagCommand
  * RemoveTagCommandParser
  * JsonAdaptedName
  * EditCommand (fixed bugs)

* **Review/mentoring contributions**:
  * Supported a teammate with the steps to implement `showevents`
  * Suggested fixes for a few test cases in `EditCommand` [#92](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/92)

* **Tools**:
  * Integrated Codecov into team repo
  * Change the header to NUSocials
  * Update the links to the Java-CI and CodeCov status-banners
  * Update the link to the project’s GitHub repository
  * Update site-wide settings [#18](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/18)
