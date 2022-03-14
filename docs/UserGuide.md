---
layout: page
title: User Guide
---

TeachWhat! is a **desktop app for tutors to manage their schedule, students, lessons, and 
income**, optimized for use via a Command Line Interface (CLI) while still having the 
benefits of a Graphical User Interface (GUI). 
If you can type fast, TeachWhat! can get your tuition management tasks done faster than 
traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TeachWhat.jar` from [here](https://github.com/AY2122S2-CS2103T-W11-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your LessonBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 
Note how the app contains some sample data.<br> ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listlessons`** : Lists all lessons.

   * **`liststudents`** : Lists all students.

   * **`student <STUDENT_ID>`** : Displays the details of the student with the provided `STUDENT_ID`.
   
   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source:Notes about the command format:**

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `addstudent -n <STUDENT_NAME>`, `<STUDENT_NAME>` is a parameter which can be used as `addstudent -n John Doe`.

* Parameters can be in any order.
  e.g. if the command specifies `-n <LESSON_NAME> -d <DATE/START_DATE> -t <START_TIME>`, `-d <DATE / START_DATE> -t <START_TIME> -n <lesson_NAME>` is also acceptable.

* Where `STUDENT_ID` and `LESSON_ID` are mentioned, they are with referring to the index of the student or lesson in the viewable lists.
</div>

---

### Adding a student

Adds a student to TeachWhat!

Format: `addstudent -n <STUDENT_NAME>`

Example: `addstudent -n sammy boyo`

---

### Deleting a student

Deletes a student from TeachWhat!

Format: `rmstudent <STUDENT_ID>`

Example: `rmstudent 13`

---

### Adding a lesson

Adds a lesson to TeachWhat!

Format: `addlesson <LESSON_TYPE> -n <LESSON_NAME> -s <SUBJECT> -a <ADDRESS_OF_LESSON> -d <DATE_OF_LESSON> -t <STARTING_TIME> -h <DURATION_OF_LESSON_IN_HOURS> -m <DURATION_OF_LESSON_IN_MINUTES>`

Supported Types: Recurring `-r` and Temporary

Example: `addlesson -r -n Trial lesson for Henry -s Biology -a Blk 11 Ang Mo Kio Street 74, #11-04 -d 27-02-2022 -t 18:00 -h 1 -m 50`

Adds a recurring lesson with the following attributes:
* name: Biology group 1
* subject: Biology
* where the lesson is conducted: Blk 11 Ang Mo Kio Street 74, # 11-04
* on date: 27 February 2022
* starting at: 6pm
* duration: 1 hour and 50 minutes
>**Note:** If no type is given then the lesson is assumed to be temporary.

---

### Deleting a lesson

Deletes the specified lesson from the list of lessons.

Format:  `rmlesson <LESSON_ID>`

Deletes the lesson with the specified `LESSON_ID`.
The `LESSON_ID` corresponds to the number in the displayed lesson list.
The `LESSON_ID` must be a positive integer.

Example:
`listlessons` to list the lessons and display their `LESSON_ID`, 
followed by `rmlesson 5` to delete the lesson with the id 5.

---

### Assigning a student to a lesson

Assigns the specified student to the specified lesson.

Format: `assign -s <STUDENT_ID> -l <LESSON_ID>`

Example: `liststudents` followed by `assign -s 5 -l 11` assigns the student with `STUDENT_ID` of **5**
to the lesson with `LESSON_ID` of **11**.

---

### Viewing all lessons

Format: `listlessons`

Example: `listlessons` would display a list of all the lessons that a tutor has.

---

### Viewing all students

Format: `liststudents`

Example: `liststudents` would display a list of all the students that a tutor has.

---

### Viewing a lesson
Displays the details for the lesson with the specified lesson ID.

Format: `lesson <LESSON_ID>`

Example: `lesson 3` displays the details of the lesson with `LESSON_ID` of **3**.

---

### Viewing a student

Displays the details for the student with the specified student ID.

Format: `student <STUDENT_ID>`

Examples: `student 3` displays the details of the student with `STUDENT_ID` of **3**.

___

### Exit

Closes TeachWhat! window.
  
Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous TeachWhat! folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Format, Examples                                                                                                                                                                                                                                                                                                              |
|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Student**    | `addstudent <STUDENT_NAME>` <br> e.g. `addstudent sammy boyo`                                                                                                                                                                                                                                                                 |
| **Add lesson**     | `addlesson <LESSON_TYPE> -n <LESSON_NAME> -s <SUBJECT> -a <ADDRESS_OF_LESSON> -d <DATE_OF_LESSON> -t <STARTING_TIME> -h <DURATION_OF_LESSON_IN_HOURS> -m <DURATION_OF_LESSON_IN_MINUTES>` <br> e.g. `addlesson -n Trial lesson for Henry -s Biology -a Blk 11 Ang Mo Kio Street 74, #11-04 -d 27-02-2022 -t 18:00 -h 1 -m 50` |
| **Delete Student** | `rmstudent <STUDENT_ID>`<br> e.g. `rmstudent 13`                                                                                                                                                                                                                                                                              |
| **Delete lesson**  | `rmlesson <LESSON_ID>`<br> e.g.`rmlesson 5`                                                                                                                                                                                                                                                                                   |
| **Assign Student** | `assign -s <STUDENT_ID> -l <LESSON_ID>`<br> e.g.`assign -s 5 -l 11`                                                                                                                                                                                                                                                           |
| **List Students**  | `liststudents`                                                                                                                                                                                                                                                                                                                |
| **List lesson**    | `listlessons`                                                                                                                                                                                                                                                                                                                 |
| **View Student**   | `student <STUDENT_ID>` <br> e.g. `student 5`                                                                                                                                                                                                                                                                                  |
| **View lesson**    | `lesson <LESSON_ID>`<br> e.g. `lesson 2`                                                                                                                                                                                                                                                                                      |
| **Exit**           | `exit`                                                                                                                                                                                                                                                                                                                        |
