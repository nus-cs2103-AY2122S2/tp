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

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all students.

   * **`add`**`n/James Ho e/jamesho@email.com g/jamesH t/jamesho i/A0123456T` : Adds a student named `James Ho` to the Address Book.

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
    - [Filter students based on status of lab tags](#filter-by-status-of-individual-labs--filter-coming-soon)
- [Manage labs](#lab-related-features)
    - [Add labs](#adding-a-lab--labadd)
    - [Change status of labs](#setting-the-status-of-individual-labs--labstat-coming-soon)

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

### Student-related features

#### Adding a student : `add`
Adds a student to the address book with the necessary attributes.

Format: `add n/NAME e/EMAIL g/GITHUB t/TELEGRAM_HANDLE i/STUDENT_ID [t/TAG]...`

#### Listing all students : `list`
Lists all the students

Format: `list`

#### Editing a student : `edit`
Edits an existing student in the TAddress book. At least one of the optional fields must be provided.

Format: `edit INDEX [n/NAME] [e/EMAIL] [g/GITHUB] [t/TELEGRAM_HANDLE] [i/STUDENT_ID] [t/TAG]...`

#### Locating students by name : `find`
Finds students whose names contain any of the given keywords. At least one keyword must be specified.

Format: `find KEYWORD [MORE_KEYWORDS]`

#### Deleting a student : `delete`
Deletes the specified person from the TAddress book. INDEX must be a positive integer 1, 2, 3...

Format: `delete INDEX`

#### Clearing all entries : `clear`
Clears all entries from the TAddress book.

Format: `clear`

#### Filter (by status of individual labs) : `filter` [coming soon]
Filters students based on the status of their lab tags. STATUS must be U/S/G (unsubmitted/submitted/graded)

Format: `filter l/LAB s/STATUS`

### Lab-related features

#### Adding a Lab : `labadd`
Adds a Lab to every student. Shows up as a tag on each student’s entry. By default, the tag will be colored red for “unsubmitted”. 
The tags can subsequently be colored different colors to represent different statuses e.g. unsubmitted = red, submitted = yellow, graded = green.
LAB must be a positive integer.

Format: `labadd l/LAB`

#### Setting the status of individual Labs : `labstat` [coming soon]
Changes the status of the specified lab for the student with the specified INDEX to the specified status. STATUS must be U/S/G (unsubmitted/submitted/graded)

Format: `labstat INDEX l/LAB s/STATUS`

--------------------------------------------------------------------------------------------------------------------
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME e/EMAIL g/GITHUB t/TELEGRAM_HANDLE i/STUDENT_ID [t/TAG]...` <br> e.g., `add n/James Ho e/jamesho@email.com g/jamesH t/jamesho i/A0123456T`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [e/EMAIL] [g/GITHUB] [t/TELEGRAM_HANDLE] [i/STUDENT_ID] [t/TAG]...`<br> e.g.,`edit 2 n/James Lee g/jamesHo`
**Filter** | `filter l/LAB s/STATUS`<br> e.g., `filter l/1 s/u`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Add lab** | `labadd l/LAB`
**Edit lab status** | `labstat INDEX l/LAB s/STATUS`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
