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

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#feature-list) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Feature List
- [Manage students](#student-related-features)
    - [Add, edit, delete students](#adding-a-student--add-coming-soon)
    - [List students](#listing-all-students--list)
    - [Find students](#locating-students-by-name--find)
    - [Filter students based on status of lab tags](#filter-by-status-of-individual-labs--filter-labx-lab-status-coming-soon)
- [Manage labs](#lab-related-features)
    - [Add labs](#adding-a-lab--labadd-labx-coming-soon)
    - [Change status of labs](#markingunmarking-individual-labs-as-graded-labgrade-student-index-labx-coming-soon)

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

#### Adding a student : `add` [coming soon]
Adds a student to the address book with the necessary attributes.

Format: `add n/NAME g/GITHUB t/TELEGRAM_HANDLE s/StudentID`

#### Listing all students : `list`
Lists all the students

Format: `list`

#### Editing a student : `edit` [coming soon]
Edits an existing student in the TAddress book. At least one of the optional fields must be provided.

Format: `edit INDEX [n/NAME] [g/GITHUB] [t/TELEGRAM_HANDLE] [s/StudentID]`

#### Locating students by name : `find`
Finds students whose names contain any of the given keywords. At least one keyword must be specified.

Format: `find KEYWORD [MORE_KEYWORDS]`

#### Deleting a student : `delete`
Deletes the specified person from the TAddress book. INDEX must be a positive integer 1, 2, 3...

Format: `delete INDEX`

#### Clearing all entries : `clear`
Clears all entries from the TAddress book.

Format: `clear`

#### Filter (by status of individual labs) : `filter [Labx] [Lab status]` [coming soon]
Filters students based on the status of their lab tags. STATUS e.g. unsubmitted = U, graded = G, etc

Format: `filter l/LAB s/STATUS`

### Lab-related features

#### Adding a Lab : `labAdd [Labx]` [coming soon]
Adds a Lab to every student. Shows up as a tag on each student’s entry.
By default, the tag will be colored grey for “unsubmitted”. The tags can subsequently be colored different colors to represent different statuses e.g. unsubmitted = grey, submitted = yellow, graded = green, overdue = red.

Format: `labAdd l/LAB`

#### Marking/unmarking individual Labs as “submitted” `labSubmit [Student Index] [Labx]` [coming soon]
Changes the status for the student with the specified INDEX from “unsubmitted” to “submitted”.  MARK can be either M or U (mark or unmark)

Format: `labSubmit i/INDEX l/LAB m/MARK`

#### Marking/unmarking individual Labs as “graded” `labGrade [Student Index] [Labx]` [coming soon]
Changes the status for the student with the specified INDEX from “submitted” to “graded”.
MARK can be either M or U (mark or unmark)

Format: `labGrade i/INDEX l/LAB m/MARK`

--------------------------------------------------------------------------------------------------------------------
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
