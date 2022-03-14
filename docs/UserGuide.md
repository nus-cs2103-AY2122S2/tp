---
layout: page
title: User Guide
---

TAlent Assistant™ is a **desktop, lightweight and centralized management system** catered to professors for managing
the process of hiring undergraduate/graduate Teaching Assistants (TA). They will be able to access the TAs’ application
data easily and review qualifications or availability for scheduling interviews. It is **optimized for use via a
Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, this application will be able to help you manage all things under the hood of the TA initiative faster than traditional GUI applications.


* Table of Contents

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TAlentAssistant.jar` from [here](https://github.com/AY2122S2-CS2103-F11-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAlent Assistant™.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all TAs.

   * **`add id/E0123456 n/John Doe p/87654321 c/Computer Science`** Adds a new TA into the system.

   * **`delete A0123456H`** : Deletes the TA with ID A0213456H from the system.

   * **`find k/Jane f/name`** : Searches for all TAs with name containing “Jane/jane”.

   * **`help`** : List all commands in the system.

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

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Lists the application commands that are available in the system.

Format: `help`

### Adding a Teaching Assistant: `add`

Adds a TA into the system.

Format: `add id/STUDENT_ID n/NAME p/PHONE c/COURSE`

* `STUDENT_ID` is sensitive, will be validated.

Examples:
* `add id/E0123456 n/John Doe p/87654321 c/Computer Science` adds a new TA with Student ID, E0123456, named John Doe.

### Listing all Teaching Assistants : `list`

Displays all TAs found in the system.

Format: `list`

<!-- For Edit TA in the future
### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
-->

### Locating applicants by keyword search: `find`

Finds and lists TAs whose attribute fields contain any of the given keywords.

Format: `find k/KEYWORD [k/MORE_KEYWORDS] f/ATTRIBUTE_FIELD`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The search will return a list of all TAs containing any of the specified keyword(s) in the specified attribute field.
* For `f/all`, the search will find keywords across all attribute fields of the TA records.
* Only full keywords will be matched
  e.g. `k/jane doe f/name` will not match TAs with name `jane koe` or just `jane`
* TAs matching at least one full keyword (in the specified attribute field) will be returned i.e. OR search,
  e.g. `k/Jane k/Doe f/name` will return TAs with name e.g. `Jane Koe`, `John Doe`

Examples:
* `find k/Jane f/name` returns TAs with name e.g. `jane` and `jane doe`
* `find k/Computer Science f/course` returns TAs with the course field e.g. recorded as `computer science`
* `find k/Jane k/Tan f/name` returns TAs with name e.g. `Jane`, `tan` and `John Tan`


### Deleting a TA : `delete`

Deletes the specified TA from the system.

Format: `delete STUDENT_ID`

* Deletes the specified TA with specified `STUDENT_ID`.
* The `STUDENT_ID` **must belong to a TA** in the system.
* Otherwise, the system will prompt a message, stating that the record does not exist.

Examples:
* `delete A0213456H` deletes the TA with STUDENT_ID A0123456H, if the record exists.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                            |
|------------|-----------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add id/STUDENT_ID n/NAME p/PHONE c/COURSE`<br> e.g., `add id/E0123456 n/John Doe p/87654321 c/Computer Science`            |
| **Clear**  | `clear`                                                                                                                     |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                         |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com` |
| **Find**   | `find k/[KEYWORD] [k/MORE_KEYWORDS] f/[ATTRIBUTE_FIELD]`<br> e.g., `find k/Jane k/Doe f/name`                               |
| **List**   | `list`                                                                                                                      |
| **Help**   | `help`                                                                                                                      |
