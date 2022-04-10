---
layout: page
title: Nikhil's Project Portfolio Page
---

## Project: TeachWhat!

TeachWhat! is a desktop address book application used for private tutors in managing their class. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. 

It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

## Overview

## Summary of Contributions
[Click here](https://github.com/AY2122S2-CS2103T-W11-3/tp/commits?author=nnmoq)

### Code contributed
[Click here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&zA=nnmoq&zR=AY2122S2-CS2103T-W11-3%2Ftp%5Bmaster%5D&zACS=201.4071329319129&zS=2022-02-18&zFS=&zU=2022-04-07&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

### Enhancements implemented
1. Student Functionality
   (Pull requests [#71](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/71) and [#146](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/146))
   - What it does
     - Allows user to Add/Delete/Edit a `Student`.
2. Added support for optional prefixes in `Student`
   (Pull requests [#71](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/71)
    - What it does
     - All prefixes except for `Name` and `Phone` are optional.
   - Justification
     - The user does not need to know the address or email of every student.
3. Command Shortcuts
   (Pull requests [#145](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/145)
   - What it does
     - Provides support for shortcut command words.
   - Justification
     - This makes typing faster, instead of typing `addstudent`, the user can simply use `as`.
4. Help Page
   (Pull requests [#158](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/158)
   - What it does
     - Displays a table with the description, command word, command shortcut for each command.
     - A hyperlink to TeachWhat! user guide.
   - Justification
     - It makes it easier for the user to refer to command words and their functionality using the table instead of 
        opening the user guide.
     - The user can simply click on the hyperlink to open it instead of copying it. This saves the user time.

### Contributions to the UG
      
1. Created the initial format and draft of the user guide on our Team's shared google docs.
2. Added the command shortcuts to user guide.
3. Updated the user guide to use consistent wording.

### Contributions to the DG

1. Added functionality and use-cases for `AddStudent` command.
2. Added Sequence Diagram for `AddStudent` command.
3. Updated Logic Component section
4. Updated Architecture Sequence Diagram
5. Added testcase examples for deleting a student
6. Added Help feature and class diagram
7. Added Command Shortcuts

### Contributions to team-based tasks
1. Created and assigned tasks to milestones 1.3 and 1.4
2. Created 1.3.1 release and uploaded its jar file

### Review/mentoring contributions
1. Made multiple good suggestions when reviewing PRs [#92](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/92#issuecomment-1072136978), 
2. Debated with [james](https://github.com/jamesyeap) about how command shortcuts were to be implemented and justified my use of `Fallthroughs` over creating a `list` to hold all the command words for every command because we decided to not implement user specified keybinds.
3. Spotted bugs made that were not caught such as [#249](https://github.com/AY2122S2-CS2103T-W11-3/tp/issues/249) and [#73](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/73/files) and provided suggestions to fix them.
