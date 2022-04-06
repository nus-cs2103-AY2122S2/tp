---
layout: page
title: Samuel Tee's Project Portfolio Page
---
## Project: TeachWhat!
TeachWhat! is a desktop address book application used for private tutors in managing their class. The user interacts
with it using a CLI, and it has a GUI created with JavaFX.

It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

## Summary of Contributions
[Click here](https://github.com/AY2122S2-CS2103T-W11-3/tp/commits?author=Kidsnd274) for a list of commits I've contributed.

### Code contributed
[Click here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Kidsnd274&tabRepo=AY2122S2-CS2103T-W11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
to see the code I've contributed.

### New features implemented
1. Implemented an InfoPanel that can view Lesson or Student details.
   - Reorganized the UI with two sections, split using a slider
   - Designed new InfoPanel for both Lesson and Student
   - Wrote code that allowed a Lesson or Student to be passed to the UI to display their information.
2. Added `lesson` and `student` view commands to view the details of a Lesson or Student in the InfoPanel.
   - Extension of the InfoPanel feature
   - Allows users to specify which Lesson or Student they want to view using the index on the displayed list.
3. Implemented a user input history function [(PR)](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/150)
   - Allows users to view previously entered commands using the arrow keys
   - <kbd>↑</kbd> key to go back in history and
   - <kbd>↓</kbd> key to go forward in history
   - ADD MORE TECHNICAL DETAILS HERE

### Enhancements to existing features
1. Added improvements to the user interface
    - Made UI elements resizable according to the window size.
2. Improved clear command [(PR)](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/166)
    - Clears Lesson Book and Student Book
    - Added confirmation function so that users won't accidentally delete their entire database

### Contributions to the Developer Guide
1. Added the UI design component section of the developer guide.
2. Added implementation details on the `lesson` and `student` view commands.

### Contributions to the User Guide
1. Added the documentation for the feature of viewing previous commands.

### Contributions to team-based tasks
- Assisted in fixing a UI layout issue in this [pull request](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/84)

### Review/mentoring contributions
To be added
