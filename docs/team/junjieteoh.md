---
layout: page
title: Teoh Jun Jie's Project Portfolio Page
---

### Project: UNite

UNite is a **desktop app for managing contacts specifically designed for people in University**. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

This is Jun Jie, one of the developers behind UNite, and I am excited to share with you some features that I have implemented.

Given below are my contributions to the project.

* **New Feature 1**: Grab Command (Pull request [#97](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/97))
  * What it does: Allow user to grab any attributes of anyone in UNite. The grab resulted will be displayed in UNite and users can copy the displayed data.
  * Justifications: This feature allows user to manage data much more efficiently. Without it, users can only see the contact stored, but unable to copy their value and use it for something else. 
  * Highlights: You can choose to grab the attributes from i) all person in UNite ii) a specific person with given index or iii) any person with the given tag
  * Credits: [Dai Tianle](https://github.com/ddx-510) for implementing iii) in the highlight above.


* **New Feature 2**: Enhanced Person object in UNite (Pull request [#69](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/69), [#76](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/76))
  * What it does: Allow user to store additional attributes in UNite contact book such as Course, Telegram and MatricCard.
  * Justifications: This is necessary because we are branding our app as an app for universities to keep track of contacts. It is crucial to modify UNite such that the above-mentioned attributes are recorded in contact book.


* **New Feature 3**: Implemented pop up window for 'Add Profile' (Pull request [#48](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/48), [#51](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/51))
  * What it does: Allow user to add new profile in UNite through via GUI. 
  * Justifications: This feature improves the product significantly by adding another dimension to our product. Now even if you prefer to click rather than type, there is a choice for you to do so.

* **Enhancements to existing features**:
  * Wrote additional tests for UNite (for newly added class such as Telegram, MatricCard and Course, and newly added commands such as GrabCommand) to increase coverage (Pull request [#90](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/90), [#201](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/201))
  
* **Code contributed**: You can find all the code I have contributed [here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=junjieteoh&tabRepo=AY2122S2-CS2103T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~other&authorshipIsBinaryFileTypeChecked=false).

* **Project management**:
  * Review and approve pull request.
  * Maintaining issue tracker.
  * Managed releases to be published.

* **Documentation**:
    * User Guide:
      * Added documentation for GrabCommand. (Pull request [#102](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/102)) 
      * Added documentation for AddCommand and EditCommand to support the enhanced Person object. (Pull request [#81](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/81))
      * Added documentation for the Person's attributes input rules. (Pull request [#191](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/191))
      
    * Developer Guide:
      * Added implementation details of enhanced Person object. (Pull request [#64](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/64), [#208](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/208))
      * Added implementation details of Grab Command. (Pull request [#205](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/205), [#208](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/208))
      * Added implementation details of Add Command and Edit Command. (Pull request [#210](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/210))
      * Added some test cases for manual testing. (Pull request [#205](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/205))
      * Refactor all the plant uml files in DG update from addressbook to Unite. (Pull request [#205](https://github.com/AY2122S2-CS2103T-W12-2/tp/pull/205)) 
* **Community**:
    * Reported bugs and suggestions for other teams in the class: [PED](https://github.com/junjieteoh/ped/issues)
