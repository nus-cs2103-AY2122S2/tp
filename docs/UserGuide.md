---
layout: page
title: User Guide
---

ArchDuke is a **desktop app for managing student contacts and groups, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ArchDuke can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

# Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `archduke.jar` from [here](https://github.com/AY2122S2-CS2103-W16-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ArchDuke.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all student contacts.

   * **`add`**`n/John Doe p/12345678 e/johndoe@u.nus.edu a/Y2 Computer Science [t/optional]` : Adds a contact named `John Doe` to ArchDuke.

   * **`clear`** : Deletes all student contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

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

## General Features

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Listing all persons : `list`

Shows a list of all student contacts in ArchDuke.

Format: `list`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ArchDuke data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

## Student Contact Management

### Add student contact information: `add`

Adds a student contact information to the address book. `add` must be followed by the student’s name, 
phone number, email, and academic major. Tag is optional.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ACADEMIC MAJOR [t/TAG]`

<div markdown="block" class="alert alert-info">

**:information_source: Tip:**<br>

A student contact can have any number of tags (including 0)

</div>

Example: 

* `add n/John Doe p/12345678 e/johndoe@u.nus.edu a/Computer Science [t/optional]`

Expected outcome:

* Student’s contact information is visible in ArchDuke GUI.

## Student Group Management

### Create a group: `addgroup`

Creates a group in ArchDuke.

Format: `addgroup g/GROUP_NAME`

Example: 

* `addgroup g/CS2103-W16-3`

Expected outcome: 

* Creates a group with the name CS2103-W16-3 to ArchDuke.

### Delete a group: `delgroup`

Deletes a group from ArchDuke. The group must **already exist** in ArchDuke.

Format: `delgroup g/GROUP_NAME`

Example: 

* `delgroup g/CS2103-W16-3`

Expected outcome: 

* Deletes the group with the name CS2103-W16-3 from ArchDuke.

### Assign a student to a group: `assign`

Assigns a student to an existing group in ArchDuke. `assign` is followed by the index at which the student is 
in the ArchDuke contact list and the group name in which the student would be assigned.

Format: `assign INDEX g/GROUP_NAME`

* Assigns the student at index `INDEX` to the group called `GROUP_NAME`

Example:

* `assign 1 g/CS2103-W16-3`

Expected outcome:

* Assigns the specified student to the group.

## Student Group Task Management

### Add a task in a group: `addtask`

Adds a task to the specified group. `addtask` must be followed by a task name and a group name. 
The group must **already exist** in ArchDuke.

Format: `addtask t/TASK_NAME g/GROUP_NAME`

* Adds the task called `TASK_NAME` to the group called `GROUP_NAME`

Example:

* `addtask t/v1.2 user guide g/CS2103-W16-3`

Expected outcome:

* Adds the task to the specified group in ArchDuke. The task appears inside the group.

### Delete a task in a group: `deltask`

Deletes a task from the specified group. `deltask` must be followed by a task name and a group name. 
The group must **already exist** in ArchDuke.

Format: `deltask t/TASK_NAME g/GROUP_NAME`

* Deletes the task called `TASK_NAME` from the group called `GROUP_NAME`

Example: 

* `deltask t/v1.2 user guide g/CS2103-W16-3`

Expected outcome:

* Deletes the specified task from the specified group.

### Displays the tasks in a group: `viewtask`

Displays the tasks from the specified group. `viewtask` must be followed by a group name. 
The group must already exist in ArchDuke.

Format: `viewtask g/GROUP_NAME`

* Views all task in the group called `GROUP_NAME`

Example:

* `viewtask g/CS2103-W16-3`

Expected outcome:

* Displays all the tasks from the specified group.

--------------------------------------------------------------------------------------------------------------------

## FAQ (Coming soon...)

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file 
that contains the data of your previous ArchDuke home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

### General features

Action | Format, Examples
--------|------------------
**View help** | `help`
**List all student contacts** | `list`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Exit** | `exit`

### Student contact management

Action | Format, Examples
--------|------------------
**Add student contact information** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ACADEMIC MAJOR [t/TAG]` <br> e.g., `add n/John Doe p/12345678 e/johndoe@u.nus.edu a/Computer Science [t/optional]`

### Student group management

Action | Format, Examples
--------|------------------
**Create a group** | `addgroup g/GROUP_NAME`<br> e.g., `addgroup g/CS2103-W16-3`
**Delete a group** | `delgroup g/GROUP_NAME`<br> e.g.,`delgroup g/CS2103-W16-3`
**Assign a student to a group** | `assign INDEX g/GROUP_NAME`<br> e.g.,`assign 1 g/CS2103-W16-3`

### Student group task management

Action | Format, Examples
--------|------------------
**Add a task in a group** | `addtask t/TASK_NAME g/GROUP_NAME`<br> e.g., `addtask t/v1.2 user guide g/CS2103-W16-3`
**Delete a task in a group** | `deltask t/TASK_NAME g/GROUP_NAME`<br> e.g.,`deltask t/v1.2 user guide g/CS2103-W16-3`
**Displays the tasks in a group** | `viewtask g/GROUP_NAME`<br> e.g.,`viewtask g/CS2103-W16-3`
