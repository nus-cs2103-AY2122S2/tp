---
layout: page
title: User Guide
---

TeachWhat! is a **desktop app for tutors to manage their schedule, students, classes, and 
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

3. Copy the file to the folder you want to use as the _home folder_ for your ClassBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 
Note how the app contains some sample data.<br> ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`ls -c`** : Lists all classes.

   * **`ls -s`** : Lists all students.

   * **`add -c -r programming 101 -d 2022-02-17 -t 1700`** : 
   Adds a recurring class programming 101 every Thursday at 5pm from 17th Feb 2022.

   * **`save TeachWhat/store.txt`** : Saves students and classes into the store.txt text file.

   * **`quit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source:Notes about the command format:**

* Words in `[UPPER_CASE]` are the parameters to be supplied by the user.<br>
  e.g. in `add -s [STUDENT_NAME]`, `[STUDENT_NAME]` is a parameter which can be used as `add -s John Doe`.

* Parameters must be in order.<br>
  e.g. if the command specifies `-d [DATE] -t [TIME]`, `-t [TIME] -d [DATE]` is not acceptable.

</div>

---

### Adding a student

Adds a student to TeachWhat!

Format: `add -s [STUDENT_ID]`

Example: `add -s sammy boyo`

---

### Deleting a student

Deletes a student from TeachWhat!

Format: `rm -s [STUDENT_ID]`

Example: `rm -s 13`

---

### Adding a class

Adds a class to TeachWhat!

Format: `add -[TYPE OF CLASS] [CLASS_NAME] -d [DATE / START_DATE] -t [START_TIME] -p [DURATION_OF_LESSON]`

Supported Types: Recurring `-cr` and Temporary `-c`

Example: `add -cr programming 101 -d 2022-02-17 -t 1700 -p 2`
Adds a recurring class programming 101 every Thursday at 5pm from 17th Feb 2022.

>**Note:** If no type is given then the class is assumed to be temporary.
> <br> e.g. To add a temporary class programming 101 on Thursday at 5pm on 17th Feb 2022,
> do: <br> `add -c programming 101 -d 2022-02-17 -t 1700`

---

### Deleting a class

Deletes the specified class from the list of classes.

Format:  `rm -c [CLASS_ID]`

Deletes the class with the specified `CLASS_ID`.
The `CLASS_ID` corresponds to the number in the displayed class list.
The `CLASS_ID` must be a positive integer.

Example:
`ls -c` to list the classes and display their `CLASS_ID`, 
followed by `rm -c 5` to delete the class with the id 5.

---

### Assigning a student to a class

Assigns the specified student to the specified class.

Format: `add -s -c [STUDENT_ID] [CLASS_ID]`

Example: `ls -s` followed by `add -s -c 5 11` assigns the student with `STUDENT_ID` of **5**
to the class with `CLASS_ID` of **11**.

---

### Viewing all classes

Format: `ls -c`

Example: `ls -c` would display a list of all the classes that a tutor has.

---

### Viewing all students

Format: `ls -s`

Example: `ls -s` would display a list of all the students that a tutor has.

---

### Viewing a class
Displays the details for the class with the specified class ID.

Format: `class [CLASS_ID]`

Example: `class 3` displays the details of the class with `CLASS_ID` of **3**.

---

### Viewing a student

Displays the details for the student with the specified student ID.

Format: `student [STUDENT_ID]`

Examples: `student 3` displays the details of the student with `STUDENT_ID` of **3**.

___

### Setting a save file location
Sets the file to retrieve from and save data to, to the specified directory. 
If the file does not exist, a new file will be created.

Format: `save [RELATIVE_PATH]`

Example: `save /TeachWhat/store.txt` sets the save file to be 
`store.txt` in the directory `TeachWhat`.

---

### Exit

Closes TeachWhat! window.
  
Format: `quit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous TeachWhat! folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Format, Examples                                                                                               |
|--------------------|----------------------------------------------------------------------------------------------------------------|
| **Add Student**    | `add -s [STUDENT_NAME]` <br> e.g., `add -s sammy boyo`                                                         |
| **Add Class**      | `add -c -[TYPE] [CLASS_NAME] -d [DATE] -t [TIME]` <br> e.g., `add -c -r programming 101 -d 2022-02-17 -t 1700` |
| **Delete Student** | `rm -s [STUDENT_ID]`<br> e.g. `rm -s 13`                                                                       |
| **Delete Class**   | `rm -c [CLASS_ID]`<br> e.g.`rm -c 5`                                                                           |
| **Assign Student** | `add -s -c [STUDENT_ID] [CLASS_ID]`<br> e.g.`add -s -c 5 11`                                                   |
| **List Students**  | `ls -s`                                                                                                        |
| **List Class**     | `ls -c`                                                                                                        |
| **View Student**   | `student [STUDENT_ID]` <br> e.g. `student 5`                                                                   |
| **View Class**     | `class [CLASS_ID]`<br> e.g. `class 2`                                                                          |
| **Save**           | `save [RELATIVE_PATH]` <br> e.g. `/TeachWhat/store.txt`                                                        |
| **Exit**           | `quit`                                                                                                         |
