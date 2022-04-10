---
layout: page
title: Declan's Project Portfolio Page
---

# Project: ReadyBakey

ReadyBakey is a desktop app for small bakers.  The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**Code Contribution**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=declanleeler&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=declanleeler&tabRepo=AY2122S2-CS2103-F09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

**Enhancements Implemented**
1. Data toggling of order / person data
   * What it does: Displays only one list of data (order or person) at a time and toggles between lists based on the user's command.
   * Justification: Declutters the UI by only showing one list that is relevant to the user's interests. Immediate toggling to show relevant data is also important without a specific command to do so - any command related to orders will show order data, and any command related to persons will show person data. 
   * Highlights: This feature affects all CommandResults as they now need to be assigned a type to indicate which data list to display to the user. 
         It was challenging as it required understanding how the different components worked together to display the initial Persons list, before coming up with a solution to tweak this setup to include another list of data,
         and the functionality to toggle between them.
   

2. Sorted list functionality in Model
   * What it does: Allows for the sorting of persons or orders through a comparator wherever required. 
   * Justification: There are various features, exiting and potential, that could leverage on a sorting function to enhance their results. Specifically, this was required in the FindIncompleteOrders command. 
   * Highlights: Challenging to implement as it introduced SortedList as a return type when previously the application only worked with FilteredList.
      This meant relying on polymorphism to use the parent interface, ObservableList, in place of where previously FilteredList was expected.

   
3. Find and sort by date incomplete orders before a given date
   * What it does: Highlights and sorts by date incomplete orders before a given date. 
   * Justification: The user can use this simple command to get important information that pertains to punctual order delivery. 
   * Highlights: Challenging to implement as it required using both FilteredLists and SortedLists.
   

4. CSS changes to enhance the application's theme

**UG Contributions**
1. Added feature guides for`addo`, `listo`, `deleteo`, `incompleteo`, and natural dates
2. Resolved bugs related to Quick Start, `findo`, and command case-sensitivity requirement
3. Update User Guide's naming, formatting, and organisation of features

**DG Contributions**
1. DataTogglingActivityDiagram
2. FindIncompleteOrdersSequenceDiagram

**Team-based Tasks**
1. Renamed product to Readybakey to better suit the new target user profile
2. Maintained issue tracker
3. Release management for V1.1
4. Informal demo of V1.2 [(Google doc link)](https://docs.google.com/document/d/1XVM0yKcbUT28I7p_NQd5p5lgvAUocCPFuJ3BB7s63lk/edit#heading=h.4y8l5hhmuf6g)
   * Added screenshots of features
   * Added explanations of features
5. Informal demo of V1.3 [(Google doc link)](https://docs.google.com/document/d/1tTXY-lm5M15URXhf_RbOpxJCVG8-GQD86Q2zDqTp1tM/edit)
   * Updated guide for some features based on the changes made in V1.3
6. Update UG for V1.3
   * Went through features to update their user guide based on the changes made in V1.3

**Review/mentoring contributions**
1. [Caught violation of SLAP](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/234)
2. [Helped debug cause of test failures](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/117)
3. [Caught naming error](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/235)
4. [Clarified Natural Date's capabilities](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/141)

**Contributions beyond the project team**
1. Participation in [PE-D](https://github.com/declanleeler/ped/issues) to help [HackNet](https://github.com/AY2122S2-CS2103T-W13-3/tp) spot bugs in V1.3
2. Helped classmate who had issues launching jar application [(Forum issue here)](https://github.com/nus-cs2103-AY2122S2/forum/issues/156#issuecomment-1040412152)
3. Helped classmate who had issues with Gradle Checkstyles [(Forum issue here)](https://github.com/nus-cs2103-AY2122S2/forum/issues/95#issuecomment-1029092057)
