---
layout: page
title: User Guide
---

TAlent Assistant™ is a **desktop, lightweight and centralized management system** catered to professors for managing
the process of hiring undergraduate/graduate Teaching Assistants (TA). They will be able to access the candidates’ application
data easily and review qualifications or availability for scheduling interviews. It is **optimized for use via a
Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, this application will be able to help you manage all things under the hood of the TA initiative faster than traditional GUI applications.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TAlentAssistant.jar` from [here](https://github.com/AY2122S2-CS2103-F11-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAlent Assistant™.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all candidates.

    * **`add id/E0123456 n/John Doe p/87654321 c/Computer Science yr/2 avail/1,2,3`** Adds a new candidate into the system.
   
    * **`edit [INDEX] c/Computer Science yr/3 avail/1 ...`** Edits the specified candidate in the system.

    * **`delete [INDEX]`** : Deletes the candidate with ID A0213456H from the system.

    * **`find k/Jane f/name`** : Searches for all candidate with name containing “Jane/jane”.

    * **`sort s/name`** : Sorts all candidate by name in descending alphabetical order.

    * **`help`** : List all commands in the system.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/82341234 p/86785678`, only `p/86785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Lists the application commands that are available in the system.

Format: `help`

### Adding a candidate: `add`

Adds a candidate into the system.

Format: `add id/STUDENTID n/NAME p/PHONE c/COURSE yr/SENIORITY avail/AVAILABILITY`

* `STUDENTID` is sensitive and it will be validated.
* `NAME` should only contain alphabets, `A-Z` or `a-z`.
* `PHONE` should only be a local number. i.e. Starting number of Singapore's common numbers - 6, 8, 9
* `COURSE` should only be Computing courses. e.g. Business Analytics, Computer Engineering, Computer Science, Information Security, Information Systems
* `SENIORITY` is a number range from 1 to 4.
* `AVAILABILITY` is an input to represent the available days. e.g. `1,2,3` corresponds to available on `Monday`, `Tuesday`, `Wednesday`

Examples:
* `add id/E0123456 n/John Doe p/87654321 c/Computer Science yr/2 avail/1,2,3` adds a new candidate with Student ID, E0123456, named John Doe.

### Editing a candidate: `edit`

Edits a candidate in the system.

Format: `edit INDEX c/COURSE yr/YEAR [ATTRIBUTE_FIELD/VALUE]...`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the edit format:**<br>

`ATTRIBUTE_FIELD` can take on the following values `id`, `name`, `phone`, `course`, `yr`, `as`, `avail`

* `as` is short for `ApplicationStatus`.

</div>

* Edits the candidate at the specified `INDEX`. The index refers to the index number shown in the displayed candidate list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `edit 1 n/Jane Doe yr/3 avail/1` Edits the name, year and availability of the 1st candidate to be Jane Doe, Year 3, Monday only respectively.
* `edit 2 c/Business Analytics` Edits the course of the 2nd candidate to be `Business Analytics`.

### Listing all candidates : `list`

Displays all candidates found in the system.

Format: `list`

### Finding candidates by keyword(s) search: `find`

Finds and lists candidates whose attribute field(s) contain(s) any of the given keyword(s).

Format: `find k/KEYWORD [k/MORE_KEYWORDS]... f/ATTRIBUTE_FIELD`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the edit format:**<br>

`ATTRIBUTE_FIELD` can take on the following values
`course`, `email`, `name`, `phone`, `candidate`, `studentid`

</div>

* The keyword search is case-insensitive. e.g `hans` will match `Hans`
* The attribute field is case-insensitive. e.g. `NAME` is equivalent to `name`
* The search will return a list of all candidates containing any of the specified keyword(s) in the specified attribute field.
* For `f/candidate`, the search will find keywords across all attribute fields of the candidate records.
* Only full keywords will be matched
  e.g. `k/jane doe f/name` will not match candidates with name `jane koe` or just `jane`
* Candidates matching at least one full keyword (in the specified attribute field) will be returned i.e. OR search,
  e.g. `k/Jane k/Doe f/name` will return candidates with name e.g. `Jane Koe`, `John Doe`

Examples:
* `find k/Jane f/name` returns candidates with name e.g. `jane` and `jane doe`
* `find k/Computer Science f/course` returns candidates with the course field i.e. `computer science`
* `find k/Jane k/Tan f/name` returns candidates with name e.g. `Jane`, `tan` and `John Tan`

### Sorting candidates by attribute field: `sort`

Returns a list of candidates sorted by the specified attribute field.

Format: `sort s/ATTRIBUTE_FIELD`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the edit format:**<br>

`ATTRIBUTE_FIELD` can take on the following values
`course`, `email`, `name`, `phone`, `candidate`, `studentid`

</div>

* The attribute field is case-insensitive. e.g. `NAME` is equivalent to `name`
* The search will return a list of all candidates sorted in ascending order
  (i.e. A-Z, 0-9) with regard to the specified attribute field.

Examples:
Let's reference a default sample list of unique candidates with attribute fields stated as (`name`, `studentid`).
1. (`Ben`, `E23456789`)
2. (`Alice`, `E0234567`)
3. (`Charlie`, `E0123456`)

* `sort s/name` returns candidates sorted by name in the following order:
1. (`Alice`, `E0234567`)
2. (`Ben`, `E23456789`)
3. (`Charlie`, `E0123456`)

* `sort s/studentid` returns candidates sorted by name in the following order:
1. (`Charlie`, `E0123456`)
2. (`Alice`, `E0234567`)
3. (`Ben`, `E23456789`)


### Deleting a candidate : `delete`

Deletes the specified candidate from the system.

Format: `delete INDEX`

* Deletes the candidate at specified `INDEX`.
* The index refers to the index number shown in the displayed candidate list
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `list` followed by delete 2 deletes the 2nd candidate in the candidate list.
* `find k/bernice k/alex f/name` followed by delete 1 deletes the 1st candidate in the results of the find command.

### Scheduling a candidate for an interview : `schedule` [Work-In-Progress]

Schedules the specified candidate for an interview.

Format: `schedule INDEX /at DATE TIME`

* Schedules the candidate at the specified `INDEX` for an interview on given `DATE` and `TIME`.
* The index refers to the index number shown in the displayed candidate list.
* The index must be a positive integer 1, 2, 3, …​
* `DATE` and `TIME` must be specified in the format `dd/MM/yyyy` and `HH:mm` respectively.
* `DATE` and `TIME` must not be earlier than the current date and time.

Examples:
* `list` followed by `schedule 2 /at 20/09/2022 15:00` schedules the second candidate in TAlent Assistant™
  for an interview on 20 September 2022, 3PM.


### Bringing a Candidate's Information to the Center Panel : `focus` [Work-In-Progress]

View more details about the Candidate in the middle panel.

Format: `focus INDEX`

* Additional details of the Candidate will be displayed on the center panel in the application.
* To switch to another Candidate's information, user will just need to type the new command
* and the new index of the candidate.

### Clearing all entries : `clear`

Clears all entries from the system.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TAlent Assistant™ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAlent Assistant™ data are saved as a JSON file `[JAR file location]/data/talentassistant.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TAlent Assistant™ will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAlent Assistant™ home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action       | Format, Examples                                                                                                                                                 |
|--------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**      | `add id/STUDENTID n/NAME p/PHONE c/COURSE yr/SENIORITY avail/AVAILABILITY`<br> e.g., `add id/E0123456 n/John Doe p/87654321 c/Computer Science yr/2 avail/1,2,3` |
| **Clear**    | `clear`                                                                                                                                                          |
| **Delete**   | `delete INDEX`<br> e.g., `delete 3`                                                                                                                              |
| **Edit**     | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [yr/YEAR]…​`<br> e.g.,`edit 2 n/James Lee p/98765432 yr/4`                                                                 |
| **Find**     | `find k/KEYWORD [k/MORE_KEYWORDS]... f/ATTRIBUTE_FIELD`<br> e.g., `find k/Jane k/Doe f/name`                                                                     |
| **Sort**     | `sort s/ATTRIBUTE_FIELD`<br> e.g., `sort s/name`                                                                                                                 |
| **Schedule** | `schedule INDEX /at DATE TIME` <br> e.g., `schedule 2 /at 20/09/2022 15:00`                                                                                      |
| **List**     | `list`                                                                                                                                                           |
| **Help**     | `help`                                                                                                                                                           |
