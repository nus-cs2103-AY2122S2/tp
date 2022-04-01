---
layout: page
title: User Guide
---

TAddressBook (TAB) is a **desktop app made for CS2030S Lab Teaching Assistants (TA) to keep track of students Lab assignments, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

If you can type fast, TAB can get your Lab management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TAddressBook.jar` from [here](https://github.com/AY2122S2-CS2103-F10-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all students.

   * **`add`**`n/James Ho e/jamesho@email.com g/jamesH tl/jamesho i/A0123456T` : Adds a student named `James Ho` to the TAddressBook.

   * **`delete`**`3` : Deletes the 3rd student shown in the current list.

   * **`clear`** : Deletes all students.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#feature-list) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Feature List
- [Manage students](#student-related-features)
    - [Add, edit, delete students](#adding-a-student--add)
    - [List students](#listing-all-students--list)
    - [Find students](#locating-students-by-name--find)
    - [Filter students based on status of lab tags](#filter-by-status-of-individual-labs--filter)
    - [View student details](#view-student-details--view)

- [Manage labs](#lab-related-features)
    - [Add labs](#adding-a-lab--labadd)
    - [Submit labs](#submitting-a-lab--labsub)
    - [Grade labs](#grading-a-lab--labgrad)
    - [Edit labs](#editing-individual-labs--labedit)
    - [Remove labs](#removing-a-lab-labrm)

### System-related features

#### Viewing help : `help`
Shows a message explaining how to access the help page.

Format: `help`

#### Exiting the program : `exit`
Exits the program.

Format: `exit`

#### Saving the data
TAddress Book data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file
TAddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to edit that data file.

We recommend that users be **extra careful** when editing data of the `MasterLabList` as well as any labNumber of individual `Student`'s `Lab`s.

If the data loaded is different from the data JSON, refer to [FAQ Q2](#faq).

### Student-related features

#### Adding a student : `add`
Adds a student to the address book with the necessary attributes.

Format: `add n/NAME e/EMAIL g/GITHUB tl/TELEGRAM_HANDLE i/STUDENT_ID [t/TAG]...`

#### Listing all students : `list`
Lists all the students

Format: `list`

#### Editing a student : `edit`
Edits an existing student in the TAddress book. At least one of the optional fields must be provided.

Format: `edit INDEX [n/NAME] [e/EMAIL] [g/GITHUB] [tl/TELEGRAM_HANDLE] [i/STUDENT_ID] [t/TAG]...`

#### Locating students by name : `find`
Finds students whose names contain any of the given keywords. At least one keyword must be specified.

Format: `find KEYWORD [MORE_KEYWORDS]`

#### Deleting a student : `delete`
Deletes the specified student from the TAddress book. INDEX must be a positive integer 1, 2, 3...

Format: `delete INDEX`

#### Clearing all entries : `clear`
Clears all entries from the TAddress book.

Format: `clear`

#### Filter (by status of individual labs) : `filter`
Filters students based on the status of their lab tags. LAB_NUMBER must be a positive integer.

Multiple filters can be applied in a conjunctional manner by executing the filter command multiple times

Using the `list` command clears all filters that are currently applied

LAB_STATUS:
* Unsubmitted = u
* Submitted = s
* Graded = g

Format: `filter l/LAB_NUMBER s/LAB_STATUS`

#### View student details : `view`
View a student's details from the TAddressBook. INDEX must be a positive integer 1, 2, 3...

Format: `view INDEX`

### Lab-related features

#### Adding a Lab : `labadd`
Adds a Lab to every student. Shows up as a `LabLabel` on each student’s entry. By default, the `LabLabel` will be colored red for “UNSUBMITTED”.
The `LabLabel` can subsequently be colored differently to represent different statuses e.g. “UNSUBMITTED” = red, "SUBMITTED" = yellow, "GRADED" = green.
LAB_NUMBER must be a positive integer.

Format: `labadd l/LAB_NUMBER`

#### Submitting a Lab : `labsub`
Changes the status of the specified lab for the student with the specified INDEX from UNSUBMITTED to SUBMITTED.

Format: `labsub INDEX l/LAB_NUMBER`

#### Grading a Lab : `labgrad`
Changes the status of the specified lab for the student with the specified INDEX to GRADED and records down the marks given for the Lab. LAB_MARK should be a non-negative integer.

Format: `labgrad INDEX l/LAB_NUMBER m/LAB_MARK`

#### Editing individual Labs : `labedit`
Edits the status or marks of the specified Lab for the student with the specified INDEX. LAB_STATUS must be one of u/s/g (UNSUBMITTED/SUBMITTED/GRADED).
Only valid combinations of LAB_STATUS and LAB_MARK will be accepted (e.g. if LAB_MARK is provided, LAB_STATUS must be 'g').

Format: `labedit INDEX l/LAB_NUMBER [s/LAB_STATUS] [m/LAB_MARK]`

#### Removing a Lab: `labrm`
Removes a specified lab from every student in the TAddressBook. LAB_NUMBER must correspond to an existing Lab in the TAddressBook.

Format: `labrm l/LAB_NUMBER`

--------------------------------------------------------------------------------------------------------------------
## FAQ

**Q1**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAddressBook home folder.

**Q2**: Why is the data loaded different from my data JSON?<br>
**A**: Please note the following defensive behavior related to the data JSON file:
1. All `Student`s will have their `LabList` aligned with the `MasterLabList` when the data file is loaded in. This means that any `Lab`s that a `Student` is missing will be added in with the default `LabStatus` of `UNSUBMITTED`.
   Any `Lab`s that the `Student` has that is not in the `MasterLabList` will be ignored.
2. If a `Student` has a lab that has a missing or invalid `LabStatus`, it is loaded as an `UNSUBMITTED` lab or `GRADED` if `labMark` is present and valid.
3. If a `Student` has a lab with a missing or invalid `labMark` but has a `LabStatus` of `GRADED`,  it is loaded as an `UNSUBMITTED` lab with `Unknown` mark.

If the TAddressBook starts up with blank data, but the user expects there to be data, it means that there are  formatting issues in the data JSON due to editing by the user.
In which case, if the user wants to fix the data JSON, the user should exit the app without using any commands that can modify data.

--------------------------------------------------------------------------------------------------------------------

## Command summary


| Action         | Format, Examples                                                                                                                                        |
|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**        | `add n/NAME e/EMAIL g/GITHUB tl/TELEGRAM_HANDLE i/STUDENT_ID [t/TAG]...` <br> e.g., `add n/James Ho e/jamesho@email.com g/jamesH t/jamesho i/A0123456T` |
| **Clear**      | `clear`                                                                                                                                                 |
| **Delete**     | `delete INDEX`<br> e.g., `delete 3`                                                                                                                     |
| **Edit**       | `edit INDEX [n/NAME] [e/EMAIL] [g/GITHUB] [tl/TELEGRAM_HANDLE] [i/STUDENT_ID] [t/TAG]...`<br> e.g.,`edit 2 n/James Lee g/jamesHo`                       |
| **Filter**     | `filter l/LAB_NUMBER s/LAB_STATUS`<br> e.g., `filter l/1 s/u`                                                                                           |
| **Find**       | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                              |
| **View**       | `view INDEX`                                                                                                                                            |
| **Add lab**    | `labadd l/LAB_NUMBER`                                                                                                                                   |
| **Submit lab** | `labsub INDEX l/LAB_NUMBER`                                                                                                                             |
| **Grade lab**  | `labgrad INDEX l/LAB_NUMBER m/LAB_MARK`                                                                                                                 |
| **Edit lab**   | `labedit INDEX l/LAB_NUMBER [s/LAB_STATUS] [m/LAB_MARK]`                                                                                                |
| **Remove lab** | `labrm l/LAB_NUMBER`                                                                                                                                    |
| **List**       | `list`                                                                                                                                                  |
| **Help**       | `help`                                                                                                                                                  |
| **Exit**       | `exit`                                                                                                                                                  |
