---
layout: page
title: Sim Si Leng's Project Portfolio Page
---

### Project: UNite

UNite is a desktop contact book application designed for university student's, teaching assistants and school admins. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented pop up window for 'Tags' [#43](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/43)
  * What it does: Allows user to operate tag related features using GUI
  * Justification: This feature is useful when users enabled GUI and hope to operate on tags using mouse
  * Highlights: Users can delete multiple tags in one go through using this pop up window

* **New Feature**: Enable/disable mouse UX [#106](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/106)
  * What it does: Allows user to enable or disable mouse interaction. When disabled, the buttons that opens up the pop up
    windows will disappear.
  * Justification: This feature is useful when users only want to operate UNite through CLI

* **New Feature**: Remark tags [#94](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/94)
  * What it does: Allows user to operate tag related features using GUI
  * Justification: This feature is useful when users enabled GUI and hope to operate on tags using mouse
  * Highlights: Made changes to data format and storage because a new field is added into tag.

* **New Feature**: Filter feature [#61](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/61)
  * What it does: Allows user to filter the contact list by a tag
  * Justification: This feature improves the product since it adds a new method for the users to search for a contact,
    or to simply view a category in UNite.

* **New Feature**: clear empty tags [#114](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/114)
  * What it does: Allows user to clear all tags that are attached to none of the profiles 
  * Justification: This feature is useful when users are updating or renewing contact list. It especially applies to the
    focused user base (school admins, teaching assistants etc.) because their tags might need to be changed every semester.
    This feature allows them to clear all empty tags at once, instead of deleting them one by one.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=pnutzz-0207&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=zoom&zA=pnutzz-0207&zR=AY2122S2-CS2103T-W12-2%2Ftp%5Bmaster%5D&zACS=30.23076923076923&zS=2022-02-18&zFS=W12&zU=2022-03-02&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false&tabAuthor=junjieteoh&tabRepo=AY2122S2-CS2103T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&zFT=docs)


* **Project management**:
  * Review pull requests
  * Organize issue tracker
  
* **Enhancements to existing features**:
  * Added function that count number of persons attached to a tag to make it display in UI. [#104](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/104)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `list tag`, `tag`, `detag` and `showTag` [\#25](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/25)

  * Developer Guide:
    * Added test cases and user stories [#29](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/29)
    * Explained implementation of filter feature [#67](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/67)
  
