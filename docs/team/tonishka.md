---
layout: page
title: Tonishka Singh's Project Portfolio Page
---

## Project Reache

_Reache_ is a desktop app that helps busy working professionals manage their large list
of contacts by providing an easy-to-use interface to store contacts and organize
meetings. It is optimised for use via a **Command Line Interface (CLI)** and is especially suited for
fast typers.

### Summary of Contribution

* **Code Contributed**: [RepoSense Link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tonishka&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18) <br><br>

* **Enhancements Implemented**:
    * Enhanced `find` command so that all fields are indexable, not just names. `find` command now takes an optional field argument. [#62](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/62)
    * Implemented two new search methods in `StringUtil` to match the internal `Map` and `Set` implementation of fields. [#62](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/62)
    * Updated tests after the implementation of multiple fields for phones, addresses and emails. [#40](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/40), [#47](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/47)
    * Updated `list` command to display all contacts in lexicographical order of names. [#74](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/74)
    * Created and updated `ReacheTheme.css`. [#86](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/86)
    * Created `Agenda`, `Meeting`, `MeetingPlace`, `MeetingTime`, `MeetingsList` classes in `model` to model meetings. [#107](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/107)
    * Simplified feedback message from commands for better readability and understandability [#115](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/115)
    * Created classes for `meetings` feature commands. [#116](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/116)
    * Updated tests for `find` feature. [#125](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/125)
    * Formatted date time in `meetings` in a user-friendly manner. [#135](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/135)
    * Added check for expired meetings. [#139](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/139)
    * Updated test for meetings. [#210](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/210), [#219](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/219), [#240](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/240)
      <br><br>
* **Contributions to UG**:
    * Added details about `find` command under the `Finding Contacts` section. [#77](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/77/files), [#84](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/84), [#158](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/158)
    * Added tutorials in UG to facilitate on-boarding of first time users. [#139](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/139), [#221](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/221)
      <br><br>
* **Contributions to DG**:
    * Added **6 Use Cases** [#22](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/22/files)
    * Added two Class Diagrams for `Model` component to match new implementation [#235](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/235), [#267](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/267)
    * Added `find feature` under `Implementation` along with its design considerations, activity diagram and sequence diagram [#97](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/97), [#105](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/105)
    * Added `meet feature` under `Implementation` along with its design considerations and sequence diagram [#262](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/262)
    * Added `update feature` under `Implementation` along with its design considerations and sequence diagram [#262](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/262)
    * Added instructions for manual testing for `find`, `update`, and `cancel` commands [#281](https://github.com/AY2122S2-CS2103T-W12-4/tp/pull/281)
      <br><br>
* **Contributions to Team-based Tasks**:
    * Created demo videos for `v1.2` and `v1.3`
    * Fixed `PE-D` bugs (issues [#170](https://github.com/AY2122S2-CS2103T-W12-4/tp/issues/170), [#179](https://github.com/AY2122S2-CS2103T-W12-4/tp/issues/179), [#180](https://github.com/AY2122S2-CS2103T-W12-4/tp/issues/180) [#183](https://github.com/AY2122S2-CS2103T-W12-4/tp/issues/183))
      <br><br>
* **Contributions beyond the Project team**:
    * Opened [24 issues](https://github.com/tonishka/ped/issues) for [UniGenda](https://github.com/AY2122S2-CS2103T-W09-1/tp/releases) during `PE-D`
      <br><br>
* **Contributions to the Developer Guide (extracts)**:

```markdown
#### Design Considerations

#### Aspect: What happens when user does not specify a field

**Note:** 
For evaluating the usefulness of the alternatives these are the assumptions made as to why the user does not specify
the field: 
a) they forgot, 
b) they do not want to restrict their search to one field, or 
c) they do not remember which field they want to search.

**Alternative 1 (Current Choice):** Search all fields for the keyword 
- Pros:
  - This is the most intuitive approach.
  - For all above mentioned scenarios a-c, this alternative is will produce the most useful result.
- Cons:
  - If there is a lot of data it will take more time to search all fields for every person.
  - Requires the most complex implementation among all alternatives.
  - Performs a lot of unnecessary comparisons (`alex` will never match any phone number, likewise `659347563` will
    never match any name).

**Alternative 2:** Use name as the default search field
- Pros: Simple implementation. Since searching people by their name is the most probable and intuitive use of this command, this is likely to produce a useful result.
- Cons: Useless for scenario b) and c).

**Alternative 3:** Produce a command syntax error and ask user to enter field
- Pros: Simple implementation. Useful in scenario a) above.
- Cons: Useless for scenario b) and c).
```

* **Contributions to the User Guide (extracts)**:

```markdown
### 2.3 Getting Started with Reache

If you are a first time user of Reache, we highly recommend following these two step-by-step tutorials to get familiar 
with the product. 

In the [contacts tutorial](#231-tutorial-1-managing-contacts-with-reache), you will learn how to add your contact information to Reache. 

In the [meetings tutorial](#232-tutorial-2-managing-meetings-with-reache), you will learn how to schedule and track your meetings using Reache.

Experienced users should skip this section.

#### 2.3.1 Tutorial 1: Managing Contacts with Reache

In this tutorial we will be adding your personal contact information to Reache.

1. Launch Reache from the folder in which it is located by double clicking on it. You will see the window shown below with some sample contacts. <br>
![Sample Contacts](images/Tutorial/sample_contacts_screen.png)
   Since this is the first time you are using Reache, it loads the sample contacts as there are no actual contacts stored. Let’s start storing some real contacts in it. <br><br>
```
