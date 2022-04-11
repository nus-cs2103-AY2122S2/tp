---
layout: page
title: Ryan Low's Project Portfolio Page
---

### Project: TeachWhat!

TeachWhat! is a desktop address book application used for private tutors in managing their class.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

---

#### Code contributed

[Click here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=Ryan-l98&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=Ryan-L98&tabRepo=AY2122S2-CS2103T-W11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false) 
to see the code contributions I have made.

---

#### Features and enhancments implemented

1. **Assign/Unassign command** 

   * Assign command pull request [#74](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/74)
   * Unassign command pull request [#135](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/135) 
   * What the commands do:
     * Assigns/Unassigns a `Student` from a `Lesson`.
     * Allows users to see a list of `enrolledStudents` in the details of a `lesson`
     * Allows users to see a list of `enrolledLessons` in the details of a `student`

2. **Implement `RecurringLesson` model(Pull request [#82](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/82))**

   * What it does:
       * Extended from the `Lesson` model, `RecurringLesson` models lessons that happen weekly,
         and therefore behaves differently when the date of the lesson has to be retrieved.
   * Justification:
       * As the date of a recurring lesson is not fixed, as it has to be updated to the date of the lesson in the
         current week. Therefore, `RecurringLesson` overrides the `lesson#getDateTimeSlot()`
         and `lesson#isConflictingWithLesson()` to ensure that the correct date is retrieved and the correct date
         is used when compared with another `Lesson`.

3. **Implement `editlesson` command (Pull request [#151](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/151))**

* What it does:
  * Allows users to edit details of lessons.

4. **Implement `Student` and `Lesson` tab (Pull request [#68)](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/68)**
    
   * What it does:
     * splits the list of students and lessons into their respective tabs
   * Justification:
     * AB3 only had one panel where users could view a single list of contacts. Since tutors will need to track
     both their lessons and students, we had to add another panel for them to view their lessons. As opposed to having 
     two panels for students and lessons, our team decided the GUI would look much better by splitting the two lists into
     tabs.

5. **Implement `liststudents` and `listlessons` command (Pull request [#68)](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/68)**

   * What it does:
     * Displays the list of students/lessons.
   * This feature was implemented together with the tabs. The command displays the list of students/lessons
   by toggling to the respective tab, after refreshing to internal list of lessons/students.
  
---

#### Contributions to the UG

1. Drafted out the initial version of the user guide (Pull request [#43](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/43))
2. Updated the user guide to be consistent with features upon every milestone completion
   * Pull request [#83](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/83) for milestone **v1.2**  
   * Pull request [#155](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/155) for milestone **v1.3**

---

#### Contributions to the DG

1. Added [implementation details](https://github.com/jamesyeap/tp/blob/master/docs/DeveloperGuide.md#assign-student-to-lesson) of the `assign` command
   * Included object diagrams to depict the state of the relevant classes at different points of the command execution.
   * Used sequence diagram to show the interaction between entities of the program.
2. Added the details of the [`Storage` component](https://github.com/AY2122S2-CS2103T-W11-3/tp/blob/master/docs/DeveloperGuide.md#storage-component)
3. Added manual testing instructions for `Assign` and `Unassign` command.

---

#### Contributions to team-based tasks

1. Add issues to milestones

---

#### Review/mentoring contributions

1. Suggested improvements in PR review [#57](https://github.com/AY2122S2-CS2103T-W11-3/tp/pull/57)
2. Reviewed and merged [35 pull requests](https://github.com/AY2122S2-CS2103T-W11-3/tp/pulls?q=is%3Apr+reviewed-by%3Aryan-l98+)

--- 
