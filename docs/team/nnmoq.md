---
layout: page
title: Nikhil's Project Portfolio Page
---
<style type="text/css">
  body{
  font-size: 12pt;
}
</style>

### Project: TeachWhat!
TeachWhat! is a desktop address book application used for private tutors in managing their class. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.<br>
### Summary of Contributions
#### Code contributed
[tP Code Dashboard](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&zA=nnmoq&zR=AY2122S2-CS2103T-W11-3%2Ftp%5Bmaster%5D&zACS=201.4071329319129&zS=2022-02-18&zFS=&zU=2022-04-07&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)
#### Enhancements implemented
* **Student Functionality**<br>
   * What it does
     * Allows user to Add/Delete/Edit a `Student`.
   * (Pull requests [#71](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/71) and [#146](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/146))
* **Added support for optional prefixes in `Student`**<br>
    * What it does
      * All prefixes except for `Name` and `Phone` are optional.
    * Justification
      * The user does not need to know the address or email of every student.
    * (Pull requests [#71](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/71))
* **Command Shortcuts**<br>
   * What it does
     * Provides support for shortcut command words.
   * Justification
     * This makes typing faster, instead of typing `addstudent`, the user can simply use `as`.
   * (Pull requests [#145](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/145))
* **Help Page**<br>
   * What it does
     * Displays a table with the description, command word, command shortcut for each command.
     * A hyperlink to TeachWhat! user guide.
   * Justification
     * It makes it easier for the user to refer to command words and their functionality using the table instead of 
        opening the user guide.
     * The user can simply click on the hyperlink to open it instead of copying it. This saves the user time.
   * (Pull requests [#158](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/158))

<div style="page-break-after: always;"></div>

#### Contributions to the UG
* Created the initial format and draft of the user guide on our Team's shared google docs.
* Added the command shortcuts to user guide.
* Updated the user guide to use consistent wording.<br>
#### Contributions to the DG
* Added functionality and use-cases for `AddStudent` command.
* Added Sequence Diagram for `AddStudent` command.
* Updated Logic Component section
* Updated Architecture Sequence Diagram
* Added testcase examples for deleting a student
* Added Help feature and class diagram
* Added Command Shortcuts<br>
#### Contributions to team-based tasks
* Created and assigned tasks to milestones 1.3 and 1.4
* Created 1.3.1 release and uploaded its jar file<br>
#### Review/mentoring contributions
* Made multiple good suggestions when reviewing pull requests such as [#92](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/92#issuecomment-1072136978)
* Debated with [james](https://github.com/jamesyeap) about how command shortcuts were to be implemented and justified my use of `Fallthroughs` over creating a list to hold all the command words for every command because we decided to not implement user specified keybinds.
* Spotted bugs made that were not caught such as [#249](https://github.com/AY2122S2-CS2103T-W11-3/tp/issues/249) and [#73](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/73/files) and provided suggestions to fix them.
