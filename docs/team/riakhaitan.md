---
layout: page
title: Ria's Project Portfolio Page
---

### Project: Expense Expert

Expense Expert is a desktop expense tracking application used for tracking one's personal expenses. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about ~10 kLoC.

Given below are my contributions to the project.

**New Feature**:
  - **Edit the amount of money a person owes**

    - What it does: allows the user to edit the amount of money owed by an existing person.
    - Justification: this feature makes it easier to manage the people who owe money.
    - This feature was facilitated by the 'update' command.
    - The command's parsing and testing was handled as well.
  
**Testing**:
  - **Added/refined testing for the following classes:**
    - PaidPersonWhoOweCommand
    - PaidPersonWhoOwedCommandParser
    - EditPersonCommand
    - EditPersonCommandDescriptor
    - EditPersonCommandParser
    - EditCommand
    - DeleteCommand
    - AddCommand
    

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=riakhaitan&breakdown=true)
  

**Enhancements to existing features**: 
- **Added the date field to the expenses**
  - This enhancement enabled the user to keep a better track of the expenses with 
  the help of the date field, through which they will know the details of a particular expense better.


**Documentation**:

- User Guide:
  - Added pictures of UI
  - Editing an Expense
  - Deleting an Expense
  - Adding an Expense
- Developer Guide:
  - Implementation details (with sequence diagram)
    - `Update Person`
  - Added multiple use cases
  - Added user stories


**Community**:

  - PRs reviewed (with non-trivial review comments): [#94](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/94), [#97](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/97), [#101](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/101), [#104](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/104), [#110](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/110), [#159](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/159), [#163](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/163), [#165](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/165), [#166](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/166), [#173](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/173), [#183](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/183), [#187](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/187)
  - Reported 8 bugs for other teams in the class (see [here](https://github.com/riakhaitan/ped/tree/main/files))
    
