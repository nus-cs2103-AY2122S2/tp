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
[Click here](https://github.com/AY2122S2-CS2103T-W11-3/tp/commits?author=Kidsnd274) 
for a list of commits I've contributed.

### Code contributed
[Click here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Kidsnd274&tabRepo=AY2122S2-CS2103T-W11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
to see the code I've contributed.

### New features implemented
1. Implemented an InfoPanel that can view Lesson or Student details.
   (Pull request [#63](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/63))
   - What it does: Allows the user to view more detailed information about a Lesson or Student.
   - Justification: As more fields are added to both Lessons and Students, having a dedicated section in the UI to view 
   more detailed information is needed.
   - Reorganized the bottom half of the UI with two sections, split using a slider.
   - Designed new InfoPanel for both Lesson and Student
2. Added `lesson` and `student` view commands to view the details of a Lesson or Student in the InfoPanel.
   (Pull request [#62](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/62))
   - This is an extension of the InfoPanel feature
   - What it does: Allows users to specify which Lesson or Student they want to view using the index on the displayed 
   list.
3. Implemented a user input history function (Pull request 
[#150](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/150))
   - What it does: Allows users to view previously entered commands using the arrow keys
   - Justification: Since CLIs have command history as a basic feature, adding it to TeachWhat not only improves user
   experience but is also fitting as we are trying to target CLI users.
   - Highlights: Retyping an entire command can be troublesome, especially when executing something similar. This
   enhancement would allow someone to easily refer to and edit their previously entered command.

<div style="page-break-after: always;"></div>

### Enhancements to existing features
1. Added improvements to the user interface
    - Made UI elements resizable according to the window size. 
   (Pull request [#80](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/80))
    - On top of implementing the InfoPanel, the bottom half of the UI was also reorganized with two sections, split
   using a slider.
    - Fixed and made some UI prompts and messages more clear.
2. Improved clear command (Pull request [#166](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/166))
    - Clears Lesson Book and Student Book
    - Added confirmation function so that users won't accidentally delete their entire database

### Contributions to the Developer Guide
1. Added the UI design component section of the developer guide. (Pull request 
[#131](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/131))
   - Updated the UML diagram of the UI component
   - Added a section on how the InfoPanel and ListPanel classes are structured.
2. Added implementation details on the `lesson` and `student` view commands. (Pull request
[#138](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/138))
   - Added sequence diagrams to show how the input is parsed and how the command is executed.
   - Added a step-by-step example of how the entire command execution process is done.
3. Added table of contents and cleaned up formatting
4. Added manual testing instructions for: (Pull request 
[#266](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/266))
   - First launch and saving window preferences
   - Adding lesson and student details

### Contributions to the User Guide
1. Updated user stories (Pull request [#49](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/49))
2. Added documentation for the View Previous Commands feature. 
(Pull request [#162](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/162))
3. Improved and fixed instructions for multiple commands in the User Guide. (Pull request 
[#245](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/245))
4. Added more clarification for the notes in the command format (Pull requests 
[#168](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/168) and 
[#245](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/245))
5. Updated the screenshot of the UI
6. Improved the formatting of the user guide, by making some elements more prominent. (Pull request 
[#262](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/262))

### Contributions to team-based tasks
 - Worked on fixing bugs that were found during the PE Dry Run with the team

### Review/mentoring contributions
 - Assisted in fixing a UI layout issue in this [pull request #84](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/84)
 - Helped with fixing merge-conflicts in some occasions.
