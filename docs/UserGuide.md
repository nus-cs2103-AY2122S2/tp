---
layout: page
title: User Guide
---

TAssist is a **desktop app for managing students and their participation in lessons, optimized for use via a Command Line Interface** (CLI). If you are a TA  who prefer CLI to GUI while having a GUI to view the student data, TAssist is the app for you.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TAssist.jar` from [here](https://github.com/AY2122S2-CS2103T-T13-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAssist.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`list student`** and pressing Enter will list all students added.<br>
   Some example commands you can try:

   * **`list student`** : Lists all students.

   * **`add student`**`id/E0123456 n/John Doe p/98765432 e/johnd@example.com` : Adds a student named `John Doe` to the TAssist.

   * **`delete student`**`3` : Deletes the 3rd student shown in the listing of the entity.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters will be ignored.<br>
  e.g. if the command specifies `list student 123`, it will be interpreted as `list student`.

</div>

### Adding entries

#### Adding a student: `add student`

Adds a student to TAssist.

Format: `add student id/STUDENT_ID n/NAME p/PHONE_NUMBER e/EMAIL`

Examples:
* `add student id/E0123456 n/John Doe p/98765432 e/johnd@example.com`

#### Adding a module: `add module`

Adds a module to TAssist.

Format: `add module n/MODULE_NAME c/MODULE_CODE a/ACADEMIC_YEAR`

* `ACADEMIC_YEAR` is represented by the year (last 2 digits) and a semester.
* Semester value ranges from S1 to S8. The table below shows the representation of each value:

| Key | Semester               |
|-----|------------------------|
| S1  | Semester 1             |
| S2  | Semester 2             |
| S3  | Special Term (Part 1)  |
| S4  | Special Term (Part 2)  |
| S5  | Mini-Semester 1A       |
| S6  | Mini-Semester 1B       |
| S7  | Mini-Semester 2A       |
| S8  | Mini-Semester 2B       |

Examples:
* `add module n/Software Engineering Project c/CS2103T a/21S1`

#### Adding a class group: `add class`

Adds a class group to TAssist.

Format: `add class id/CLASSGROUP_ID t/CLASSGROUP_TYPE m/MODULE_INDEX`

* TAssist supports the following class group types: lab, recitation, sectional and tutorial.

Examples:
* `add class id/T13 t/tutorial m/1` creates a new class group that is tied to the 1st module shown when `list module` is run. 

### Listing entries

#### Listing all students : `list student`

Shows a list of all students.

Format: `list student`

#### Listing all modules : `list module`

Shows a list of all modules.

Format: `list module`

#### Listing all students : `list class`

Shows a list of all class groups.

Format: `list class`

### Deleting entries

#### Deleting a student : `delete student`

Deletes the specified student from TAssist.

Format: `delete student INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list student` followed by `delete student 2` deletes the 2nd student in TAssist.

#### Deleting a module : `delete module`

Deletes the specified module from TAssist.

Format: `delete module INDEX`

* Deletes the module at the specified `INDEX`.
* The index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list module` followed by `delete module 2` deletes the 2nd module in TAssist.

#### Deleting a class group : `delete class`

Deletes the specified class group from TAssist.

Format: `delete class INDEX`

* Deletes the class group at the specified `INDEX`.
* The index refers to the index number shown in the displayed class groups list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list class` followed by `delete class 2` deletes the 2nd class group in TAssist.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TAssist data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAssist data are saved as a JSON file `[JAR file location]/data/tassist.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TAssist will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAssist home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

<table>
<tbody>
    <tr>
        <th colspan="2">Action</th>
        <th>Format, Examples</th>
    </tr>
    <tr>
        <td rowspan="3">Add</td>
        <td>student</td>
        <td>
            <ul>
                <li>syntax: <code>add student id/STUDENT_ID n/NAME p/TELEGRAM e/EMAIL</code></li>
                <li>e.g., <code>add student id/E0123456 n/John Doe p/98765432 e/johnd@example.com</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>module</td>
        <td>
            <ul>
                <li>syntax: <code>add module n/MODULE_NAME c/MODULE_CODE a/ACADEMIC_YEAR</code></li>
                <li>e.g., <code>add module n/Software Engineering Project c/CS2103T a/21S1</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>class group</td>
        <td>
            <ul>
                <li>syntax: <code>add class id/CLASSGROUP_ID t/CLASSGROUP_TYPE m/MODULE_INDEX</code></li>
                <li>e.g., <code>add class id/T13 t/tutorial m/1</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="3">Delete</td>
        <td>student</td>
        <td>
            <ul>
                <li>syntax: <code>delete student INDEX</code></li>
                <li>e.g., <code>delete student 2</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>module</td>
        <td>
            <ul>
                <li>syntax: <code>delete module INDEX</code></li>
                <li>e.g., <code>delete module 2</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>class group</td>
        <td>
            <ul>
                <li>syntax: <code>delete class INDEX</code></li>
                <li>e.g., <code>delete class 2</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="3">List</td>
        <td>students</td>
        <td>
            <ul>
                <li>syntax: <code>list student</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>modules</td>
        <td>
            <ul>
                <li>syntax: <code>list module</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>class groups</td>
        <td>
            <ul>
                <li>syntax: <code>list class</code></li>
            </ul>
        </td>
    </tr>
</tbody>
</table>
