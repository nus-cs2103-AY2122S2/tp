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
<div style="page-break-after: always;"></div>

## Quick start

1. Ensure you have [Java `11`](https://www.oracle.com/java/technologies/downloads/#java11) or above installed in your Computer.

2. Download the latest `TAPA.jar` file from [here](https://github.com/AY2122S2-CS2103T-W09-4/tp/releases).

3. Copy the `TAPA.jar` file to the folder you want to use as the _home folder_ for TAPA.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the desired command in the command box and press the "Enter" key on your keyboard to execute it. For example, typing **`help`** and pressing "Enter" will open the help window.<br>
   <br>
Here are some example commands you can try:

   * **`list`** : Lists all students in TAPA.

   * **`add`**`i/A0123456Z n/john m/CS2103T p/98765432 t/johnnn e/e0123456@u.nus.edu` : Adds a student named `John` to TAPA.

   * **`delete`**`3` : Deletes the 3rd entry in TAPA.

   * **`manual`**`add` : Display the user manual for the command `add`.

   * **`clear`** : Deletes all students from TAPA.

   * **`exit`** : Exits the app.

7. Refer to the [Features](#features) section below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the "Format" section for each command:**<br>

* Words in `UPPER_CASE` are parameters to be supplied by the user.<br>
  Example: For the command `edit STUDENT_INDEX`, `STUDENT_INDEX` is a parameter that must be supplied by the user.<br>
  Thus, the user should input the command `edit 10`, where "10" is the `STUDENT_INDEX` supplied by the user.

* Parameters in square brackets are optional parameters.<br>
  Example: For the command `edit STUDENT_INDEX [p/PHONE_NUMBER] [m/MODULE_CODE]`, the user must supply the `STUDENT_INDEX` parameter in the input, whereas the `PHONE_NUMBER` and `MODULE_CODE` parameters are optional.<br>
  Thus, the user can input the command `edit 10 p/98765432` (`MODULE_CODE` not specified) or `edit 10 p/98765432 m/CS2103` (`MODULE_CODE` specified).

* Parameters with `…`​ after them can be used multiple times.<br>
  Example: For the command `delete STUDENT_INDEX…​`, the user can input `delete 10` (one `STUDENT_INDEX` parameter) or `delete 10 11 12 13` (multiple `STUDENT_INDEX` parameters).

* Parameters can be inputted in any order.<br>
  Example: For the command `add i/STUDENT_ID n/NAME m/MODULE_CODE`, the user can input `add i/A0123456B n/John Doe m/CS2103` (`NAME` followed by `MODULE_CODE`) or `add i/A0123456B m/CS2103 n/John Doe` (`MODULE_CODE` followed by `NAME`).

* If a parameter is expected only once in the command, but the user specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  Example: For the command `find n/NAME`, if the user inputs the command `find n/John n/Mary`, only `n/Mary` will be interpreted by TAPA.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  Example: For the command `help`, if the user inputs `help help 123`, the input will be interpreted as `help`.

* The parameter `TELEGRAM_HANDLE` should contain 5 to 32 characters. The characters can either be alphanumeric or underscore. However, the first and last character of the handle should not be an underscore.

</div>

### Adding a student: `add`

Adds a student to TAPA.

**Format**: `add i/STUDENT_ID n/STUDENT_NAME m/MODULE_CODE [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS]​`

* The student’s student ID (matriculation number), name as well as module code are compulsory fields.
* The phone number, telegram handle, and email address fields are optional and can be excluded.

<div markdown="block" class="alert alert-info">
> :warning: **Warning!:**

<br>

* The student's student ID (matriculation number) has to be unique.
* An error message will be displayed to the user if the specified student ID already exists in TAPA.

    <div markdown="span" class="alert alert-info">:information_source:
    **Note**: The name of the added student will be converted to Title Case.
    </div>

**Example**:
* `add i/AXXXXXXXR n/john m/CS2103T p/98765432 t/johnnn e/e0123456@u.nus.edu`
    * A student named John is added to TAPA.

<br>

### Deleting a student: `delete`

Deletes a student from TAPA.

**Format**: `delete STUDENT_INDEX...` (or) `delete i/STUDENT_ID`

* The student corresponding to the index or matriculation number (specified after the `delete` command) will be removed from TAPA.
* An error message will be displayed to the user if the specified index is a negative number or larger than the number of students in TAPA, or there is no student with the specified matriculation number.
* Multiple indices can be inputted in order to delete multiple students.

**Example**:
* `delete 10`
    * A student named John (whose list index is “10”) is deleted from TAPA.
* `delete 10 20`
    * The students named John and Mary (whose list indices are “10” and “20”) are deleted from TAPA.
* `delete i/A0123456Z`
    * A student named John whose matriculation number is "A0123456Z" is deleted from TAPA.

<br>

### Deleting all students taking a particular module: `deleteModule`

Deletes all students taking a particular module from TAPA.

**Format**: `deleteModule m/MODULE_CODE`

* All students who are taking the module with the module code specified after the `deleteModule` command will be removed from TAPA.
* An error message will be displayed to the user if there are no students taking the specified module.

**Example**:
* `deleteModule m/CS2100`
    * All students who are specified as taking CS2100 are deleted from TAPA.

<br>

### Finding a student: `find`

Allows the user to look up the details of a particular student.

**Format**: `find n/STUDENT_NAME` (or) `find i/STUDENT_ID` (or) `find m/MODULE_CODE`

* The student whose name, student id or module code is specified after the `find` command will appear in the resulting list.

**Example**:
* `find n/John`
    * Displays the particulars of the students whose names include John.
* `find i/AXXXXXXXR`
    * Displays the particulars of the student with student ID AXXXXXXXR.
* `find m/CS2103T`
    * Displays the particulars of the student with module code CS2103T. Also works for module codes with varying lengths.

<br>

### Checking all the tasks that a student has: `task`

Displays all the tasks that are allocated to a particular student.

**Format**: `task i/STUDENT_ID`

* The completed and uncompleted tasks are separated into 2 different sections.
* An error message will be displayed to the user if there is no student with the specified matriculation number.

**Example**:
* `task i/AXXXXXXXR`
    * Lists out the tasks that student (AXXXXXXXR) has.

<br>

### Marking an undone task as done for a student: `mark`

Marks a specific undone task as done for a particular student.

**Format**: `mark i/STUDENT_ID idx/UNDONE_TASK_INDEX`

* The undone task corresponding to the index or the particular student will be marked as done in the TAPA.
* An error message will be displayed to the user if the specified index is a negative number or larger than the number of tasks for that particular student.
* An error message will be displayed to the user if the task with that specified index for the particular student is already marked as done.

**Example**:
* `mark i/AXXXXXXXR idx/1`
    * Marks the first task in the task list for the student with student ID AXXXXXXXR as done.

<br>

### Marking a done task as undone for a student: `unmark`

Marks a specific done task as undone for a particular student.

**Format**: `unmark i/STUDENT_ID idx/DONE_TASK_INDEX`

* The done task corresponding to the index for the particular student will be marked as undone in the TAPA.
* An error message will be displayed to the user if the specified index is a negative number or larger than the number of tasks for that particular student.
* An error message will be displayed to the user if the task with that specified index for the particular student is already marked as undone.

**Example**:
* `unmark i/AXXXXXXXR idx/1`
    * Marks the first task in the task list for the student with student ID AXXXXXXXR as undone.

<br>

### Editing a student's information: `edit`

Edits a student's information in TAPA.

**Format**: `edit STUDENT_INDEX [i/STUDENT_ID] [n/STUDENT_NAME] [m/MODULE_CODE] [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS]​`

* The index of the student to be edited is a compulsory field.
* The student’s matriculation number, name, module code, phone number, telegram handle, and email address fields are optional and can be excluded.
* An error message will be displayed to the user if the specified index is a negative number or larger than the number of students in TAPA.

**Example**:
* `edit 10 m/CS2103T p/98765432 t/johnnn e/e0123456z@u.nus.edu`
    * A student (whose list index is “10”) has their module, phone number, telegram handle and email address edited.

<br>

### Deleting all students: `clear`

Clears all students from TAPA.

**Format**: `clear`

* All students and their corresponding details will be removed from TAPA.
* TAPA will request for the user's confirmation before clearing all students.
* A message will be displayed if TAPA is already empty and there are no students to be removed.

**Example**:
* `clear`
    * All students cleared from TAPA.

<br>

### Archiving details in the address book: `archive`

Saves a copy of the details currently saved in TAPA into a separate file.

**Format**: `archive`

* A copy of the details currently saved in TAPA will be saved to a separate file.
* The file name will be the date and time of the archive operation.
* This file will be saved in the same directory as the original `.json` data file.

<br>

### Listing the student details: `list`

Displays all the students enrolled in a list.

**Format**: `list`

* Displays the students from the list of students in alphabetical order.
* The students are indexed as 1, 2, 3, ......

**Example**:
* `list`
    * Displays all the enrolled students in alphabetical order.

<br>

### Assigning tasks to a particular student: `assign`

Assigns a task to a particular student.

**Format**: `assign i/STUDENT_ID tn/TASK_NAME` (or) `assign m/MODULE_CODE tn/TASK_NAME`

**Example**:
* `assign i/AXXXXXXXR tn/assignment 1`
    * Assigns assignment 1 to student with id AXXXXXXXR.
* `assign m/CS2103T tn/assignment 1`
    * Assigns assignment 1 to students taking module CS2103T.

<div markdown="block" class="alert alert-info">
> :warning: **Warning!:**

<br>

* As `MODULE_CODE` is case-sensitive, the user should ensure that the capitalisation of the module should be correct, or else the task would not be assigned properly.

  <div markdown="span" class="alert alert-info">:information_source:
    **Note**: The name of the assigned task will be converted to Title Case.
    </div>

<br>

### Viewing the completion status of a particular task: `progress`

Displays a list of students who are taking the specified module, and have been assigned with a particular task.
The completion status of each student in the list will be displayed as well.

**Format**: `progress m/MODULE_CODE tn/TASK_NAME`

* The module code and task name are compulsory fields.

**Example**:
* `progress m/CS2103T tn/assignment 1`
    * Displays all students who are taking "CS2103T" and have been assigned with "assignment 1".
    * For each student in the output list, a :heavy_check_mark: "tick" symbol signifies that he/she has already
      completed the assigned task.
    * On the other hand, a :x: "cross" symbol signifies that the student has not complete the assigned task.

<br>

### Deleting previously assigned task: `deleteTask`

Deletes a task from a particular student's list of tasks.

**Format**: `deleteTask i/STUDENT_ID idx/INDEX` (or) `deleteTask m/MODULE_CODE tn/TASK_NAME`

* An error message will be displayed to the user if the specified index is a negative number or larger than the number of tasks for that particular student.
* An error message will be displayed if the student with the given student ID does not exist.
* An error message will be displayed if none of the students taking the module had previously been assigned the task with the given task name.
* An error message will be displayed if none of the students are taking a module with the given module code.

**Example**:
* `deleteTask i/AXXXXXXXR idx/3`
    * Deletes task at index 3 from the student's list of assigned task, provided that a task exists at that index.
* `deleteTask m/cs2030 tn/Assignment 1`
    * Deletes Assignment 1 that was previously assigned to any of the students taking CS2030 module.

<br>

### Viewing previously executed commands: `history`

Displays a list of previous commands that were executed successfully.

<div markdown="block" class="alert alert-info">
**:information_source: Quick Tip!:**<br>

* Aside from the `history` command, you can also use the :arrow_up_small: Up and :arrow_down_small: Down arrow keys on your keyboard to navigate through your previously executed commands.

</div>

**Format**: `history`

* Displays the list of previously executed commands in chronological order (from the earliest command to most recent command).
* The commands are displayed exactly as they were inputted by the user.
* The commands are indexed as 1, 2, 3, ......

<br>

### Undoing the previous command: `undo`

Reverts the changes made by the previously executed command.

**Format**: `undo`

<div markdown="block" class="alert alert-info">
> :warning: **Warning!:**

<br>

* The effects of the [`clear` command](https://ay2122s2-cs2103t-w09-4.github.io/tp/UserGuide.html#deleting-all-students-clear) and the [`undo` command](https://ay2122s2-cs2103t-w09-4.github.io/tp/UserGuide.html#undoing-the-previous-command-undo) cannot be undone!

</div>

* Reverts the changes of the previously executed command, and removes the command from history.

**Example**:
* `undo`
    * Displays the command that has been undone.
    * The changes made by the previously executed command are undone.
    * The undone command is removed from the history of commands.

<br>

### Sorting the list of students by the number of undone tasks: `sort`

Sorts and displays the students in TAPA by the number of undone tasks in **descending** order.

**Format**: `sort`

* Displays the students from the list of students by the number of undone tasks in **descending** order
* The students are indexed as 1, 2, 3, ......

**Example**:
* `sort`
    * Displays all the enrolled students by the number of undone tasks in **descending** order

<br>

### Displaying manual for a command: `manual`

Display the format and a short description for a specified command.

**Format**: `manual [COMMAND_NAME]`

* The format of the command corresponding to the command name will be displayed, along with a short description.
* If there are no inputs for the command name, all the available commands will be displayed.
* An error message will be displayed to the user if the user input a command name that is invalid.

**Example**:
* `manual add`
    * Display the format for the command add, and briefly describes the command.
* `manual`
    * Display all available commands.

<br>

### Viewing help : `help`

Shows a pop-up window explaining how to access the user guide.

**Format**: `help`

<br>

### Exiting the program : `exit`

Exits the program.

**Format**: `exit`

<br>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I get started with TAPA?<br>
**A**: You can refer to the [Quick Start](https://ay2122s2-cs2103t-w09-4.github.io/tp/UserGuide.html#quick-start) section of this guide to start using TAPA as soon as possible!

**Q**: How can I view a list of available commands within TAPA?<br>
**A**: You can input the command "`manual`" to view the list of commands used within TAPA. Alternatively, you can refer to the [Command Summary](https://ay2122s2-cs2103t-w09-4.github.io/tp/UserGuide.html#command-summary) section below.

**Q**: Who developed this amazing app?<br>
**A**: You can find more details about our team on TAPA's [About Us](https://ay2122s2-cs2103t-w09-4.github.io/tp/AboutUs.html) page!
--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command summary

You can think of this Command Summary as a cheatsheet to using TAPA.
Each action that TAPA can perform is listed next to the format you should use to execute that command, as well as an example of how the command can be used.

For example, say you are using TAPA and would like to see the manual description for the `add` command.

As seen in the Command Summary below, you can input `manual add` to view a short description of the `add` command.

If you would like to learn more about a specific command, you can read more about it in the [Features](https://ay2122s2-cs2103t-w09-4.github.io/tp/UserGuide.html#features) section above!

Action              | Command Format with Examples
--------------------|------------------
**Add**             | `add i/MATRICULATION_NO n/STUDENT_NAME m/MODULE_CODE [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS] ` <br> Example: `add i/AXXXXXXXR n/john m/CS2103T p/98765432 t/johnnn e/e0123456@u.nus.edu`
**Delete**          | `delete STUDENT_INDEX...` (or) `delete i/STUDENT_ID` <br> Example: `delete 10` (or) `delete 10 20` (or) `delete i/AXXXXXXXR`
**Delete Module**   | `deleteModule m/MODULE_CODE` <br> Example: `deleteModule m/CS2100`
**Find**            | `find n/STUDENT_NAME` (or) `find i/STUDENT_ID` (or) `find m/MODULE_CODE` <br> Example: `find n/john` (or) `find i/AXXXXXXXR` (or) `find m/CS2103T`
**Task**            | `task i/STUDENT_ID` <br> Example: `task i/AXXXXXXXR`
**Mark**            | `mark i/STUDENT_ID idx/UNDONE_TASK_INDEX` <br> Example: `mark i/AXXXXXXXR idx/1`
**Unmark**          | `unmark i/STUDENT_ID idx/DONE_TASK_INDEX` <br> Example: `unmark i/AXXXXXXXR idx/1`
**Edit**            | `edit STUDENT_INDEX [i/MATRICULATION_NO] [n/STUDENT_NAME] [m/MODULE_CODE] [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS] ` <br> Example: `edit 10 m/CS2103T p/98765432 t/johnnn e/e0123456@nus.edu.sg`
**Clear**           | `clear`
**Archive**         | `archive`
**List**            | `list`
**Assign**          | `assign i/STUDENT_ID tn/TASK_NAME` (or) `assign m/MODULE_CODE tn/TASK_NAME` <br> Example: `task i/AXXXXXXXR tn/assignment 1` (or) `assign m/CS2103T tn/assignment 2`
**Progress**        | `progress m/MODULE_CODE tn/TASK_NAME` <br> Example: `progress m/CS2103T tn/assignment 1`
**Delete Task**     | `deleteTask i/STUDENT_ID idx/INDEX` (or) `deleteTask m/MODULE_CODE tn/TASK_NAME` <br> Example: `deleteTask i/AXXXXXXXR idx/3` (or) `deleteTask m/CS2030 tn/Assignment 1`
**History**         | `history`
**Undo**            | `undo`
**Sort**            | `sort`
**Manual**          | `manual [COMMAND_NAME]` <br> Example: `manual` (or) `manual add`
**Help**            | `help`
**Exit**            | `exit`




