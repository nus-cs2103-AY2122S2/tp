---
layout: page
title: User Guide
---

TAPA (Teaching Assistant's Personal Assistant) is a desktop app that allows TAs to better manage their student’s progress,
especially for those
who are teaching multiple classes/modules at the same time. It is optimised for use on a CLI.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have [Java `11`](https://www.oracle.com/java/technologies/downloads/#java11) or above installed in your Computer.

1. Download the latest `TAPA.jar` from [here](https://github.com/AY2122S2-CS2103T-W09-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`i/A0123456Z n/john m/CS2103T p/98765432 t/johnnn e/e0123456@u.nus.edu` : Adds a contact named `John` to TAPA.

   * **`delete`**`3` : Deletes the 3rd entry in TAPA.

   * **`manual`**`add` : Display the user manual of the command `add`.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a student: `add`

Adds a student to TAPA.

Format: `add i/STUDENT_ID n/STUDENT_NAME m/MODULE_CODE [p/PHONE_NUMBER] [h/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS]​`

* The student’s matriculation number, name as well as module code are compulsory fields.
* The phone number, telegram handle, and email address fields are optional and can be excluded.

Example:
* `add i/AXXXXXXXR n/john m/CS2103T p/98765432 t/johnnn e/e0123456@u.nus.edu`
    * A student named John is added to TAPA.

### Deleting a student: `delete`

Deletes a student from TAPA.

Format: `delete STUDENT_INDEX...` (or) `delete i/STUDENT_ID`

* The student corresponding to the index or matriculation number (specified after the `delete` command) will be removed from TAPA.
* An error message will be displayed to the user if the specified index is a negative number or larger than the number of students in TAPA, or there is no student with the specified matriculation number.
* Multiple indices can be inputted in order to delete multiple students.

Example:
* `delete 10`
    * A student named John (whose list index is “10”) is deleted from TAPA.
* `delete 10 20`
    * The students named John and Mary (whose list indices are “10” and “20”) are deleted from TAPA.
* `delete i/A0123456Z`
    * A student named John whose matriculation number is "A0123456Z" is deleted from TAPA.

### Finding a student: `find`

Allows the user to look up the details of a particular student.

Format: `find n/STUDENT_NAME` (or) `find i/STUDENT_ID` (or) `find m/MODULE_CODE`

* The student whose name, student id or module code is specified after the `find` command will appear in the resulting list.

Example:
* `find n/John`
    * Displays the particulars of the students whose names include John.
* `find i/AXXXXXXXR`
    * Displays the particulars of the student with student ID AXXXXXXXR.
* `find m/CS2103T`
    * Displays the particulars of the student with module code CS2103T. Also works for module codes with varying lengths.

### Checking all the tasks that a student has: `task`

Displays all the tasks that are allocated to a particular student.

Format: `task i/STUDENT_ID`

* The completed and uncompleted tasks are separated into 2 different sections.

Example:
* `task i/AXXXXXXXR`
    * Lists out the tasks that student (AXXXXXXXR) has.

### Marking an undone task as done for a student: `mark`

Marks a specific undone task as done for a particular student.

Format : `mark i/STUDENT_ID idx/UNDONE_TASK_INDEX`

* The undone task corresponding to the index or the particular student will be marked as done in the TAPA.
* An error message will be displayed to the user if the specified index is a negative number or larger than the number of tasks for that particular student.
* An error message will be displayed to the user if the task with that specified index for the particular student is already marked as done.

Example:
* `mark i/AXXXXXXXR idx/1`
    * Marks the first undone task for the student with student ID AXXXXXXXR as done.

### Marking a done task as undone for a student: `unmark`

Marks a specific done task as undone for a particular student.

Format : `unmark i/STUDENT_ID idx/DONE_TASK_INDEX`

* The done task corresponding to the index for the particular student will be marked as undone in the TAPA.
* An error message will be displayed to the user if the specified index is a negative number or larger than the number of tasks for that particular student.
* An error message will be displayed to the user if the task with that specified index for the particular student is already marked as undone.

Example:
* `unmark i/AXXXXXXXR idx/1`
    * Marks the first done task for the student with student ID AXXXXXXXR as undone.

### Editing a student's information: `edit`

Edits a student's information in TAPA.

Format: `edit STUDENT_INDEX [i/STUDENT_ID] [n/STUDENT_NAME] [m/MODULE_CODE] [p/PHONE_NUMBER] [h/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS]​`

* The index of the student to be edited is a compulsory field.
* The student’s matriculation number, name, module code, phone number, telegram handle, and email address fields are optional and can be excluded.
* An error message will be displayed to the user if the specified index is a negative number or larger than the number of students in TAPA.

Example:
* `edit 10 m/CS2103T p/98765432 t/johnnn e/e0123456z@u.nus.edu`
    * A student (whose list index is “10”) has their module, phone number, telegram handle and email address edited.

### Deleting all students: `clear`

Clears all students from TAPA.

Format: `clear`

* All students and their corresponding details will be removed from TAPA.
* A message will be displayed if TAPA is already empty and there are no students to be removed.

Example:
* `clear`
    * All students cleared from TAPA.

### Archiving details in the address book: `archive`

Saves a copy of the details currently saved in the address book into a separate file.

Format: `archive`

* A copy of the details currently saved in the address book will be saved to a separate file.
* The file name will be the date and time of the archive operation.

### Listing the student details: `list`

Displays all the students enrolled in a list.

Format: `list`

* Displays the students from the list of students in alphabetical order.
* The students are indexed as 1, 2, 3, ......

Example:
* `list`
    * Displays all the enrolled students in alphabetical order.

### Assigning tasks to a particular student: `assign`

Assigns a task to a particular student.

Format: `assign i/STUDENT_ID tn/TASK_NAME` (or) `assign m/MODULE_CODE tn/TASK_NAME`

Example:
* `assign i/AXXXXXXXR tn/assignment 1`
    * Assigns assignment 1 to student with id AXXXXXXXR.
* `assign m/CS2103T tn/assignment 1`
    * Assigns assignment 1 to students taking module CS2103T.

### Viewing the completion status of a particular task: `progress`

Displays a list of students who are taking the specified module, and have been assigned with a particular task.
The completion status of each student in the list will be displayed as well.

Format: `progress m/MODULE_CODE tn/TASK_NAME`

* The module code and task name are compulsory fields.

Example:
* `progress m/CS2103T tn/assignment 1`
    * Displays all students who are taking "CS2103T" and have been assigned with "assignment 1".
    * For each student in the output list, a "tick" symbol signifies that he/she has already
      completed the assigned task.
    * On the other hand, a "cross" symbol signifies that the student has not complete the assigned task.

### Displaying manual for a command: `manual`

Display the format for a specified command and a short description for a particular command.

Format: `manual [COMMAND_NAME]`

* The format of the command corresponding to the command name will be displayed, along with a short description.
* If there are no inputs for the command name, all the available commands will be displayed.
* An error message will be displayed to the user if the user input a command name that is invalid.

Example
* `manual add`
    * Display the format for the command add, and briefly describes the command.
* `manual`
    * Display all available commands.

### Viewing help : `help`

Shows a pop-up window explaining how to access the user guide.

Format: `help`


### Exiting the program : `exit`

Exits the program.

Format: `exit`


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: What command can I use to view a list of available commands?<br>
**A**: Use the command “manual” to view the list of commands used within TAPA. Alternatively, refer to the Command Summary section below.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action      | Format, Examples
------------|------------------
**Add**     | `add i/MATRICULATION_NO n/STUDENT_NAME m/MODULE_CODE [p/PHONE_NUMBER] [h/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS] ` <br> e.g., `add i/AXXXXXXXR n/john m/CS2103T p/98765432 t/johnnn e/e0123456@u.nus.edu`
**Delete**  | `delete STUDENT_INDEX...` (or) `delete i/STUDENT_ID` <br> e.g., `delete 10`, `delete 10 20`, `delete i/AXXXXXXXR`
**Find**    | `find n/STUDENT_NAME` (or) `find i/STUDENT_ID` <br> e.g., `find n/john`, `find i/AXXXXXXXR`
**Manual**  | `manual [COMMAND_NAME]` <br> e.g., `manual add`, `manual`
**Find**    | `find n/STUDENT_NAME` (or) `find i/STUDENT_ID` (or) `find m/MODULE_CODE` <br> e.g., `find n/john`, `find i/AXXXXXXXR`, `find m/CS2103T`
**Manual**  | `manual COMMAND_NAME` <br> e.g., `manual add`
**Exit**    | `exit`
**Task**    | `task i/STUDENT_ID` <br> e.g., `task i/AXXXXXXXR`
**Mark**    | `mark i/STUDENT_ID idx/UNDONE_TASK_INDEX` <br> e.g., `mark i/AXXXXXXXR idx/1`
**Unmark**  | `unmark i/STUDENT_ID idx/DONE_TASK_INDEX` <br> e.g., `unmark i/AXXXXXXXR idx/1`
**list**    | `list`
**Assign**  | `assign i/STUDENT_ID tn/TASK_NAME` (or) `assign m/MODULE_CODE tn/TASK_NAME` <br> e.g., `task i/AXXXXXXXR tn/assignment 1`, `assign m/CS2103T tn/assignment 2`
**Edit**    | `edit STUDENT_INDEX [i/MATRICULATION_NO] [n/STUDENT_NAME] [m/MODULE_CODE] [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS] ` <br> e.g., `edit 10 m/CS2103T p/98765432 t/johnnn e/e0123456@nus.edu.sg`
**Progress**| `progress m/MODULE_CODE tn/TASK_NAME` <br> e.g., `progress m/CS2103T tn/assignment 1`
**Clear**   | `clear`
**Archive** | `archive`
**Help**    | `help`



