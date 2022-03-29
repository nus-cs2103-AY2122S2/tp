---
layout: page
title: User Guide
---

HireLah is a desktop app that helps **recruiters to manage talent and job candidates** by tracking every step of the hiring process, 
from offering positions to scheduling interviews with candidates. It is optimised for Command Line Interface (CLI) users while still offering a GUI, so that power users can accomplish tasks much quicker by using commands


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `HireLah.jar` from [here](https://github.com/AY2122S2-CS2103-W17-4/tp/releases).

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

* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>


## Add
General command to add different types into HireLah.

Format: `add -TYPE`
* TYPE must take the form of `a`, `i`, `p`
* -a will add an applicant
* -i will add an interview
* -p will add a position

### Adding an Applicant: `add -a`

Adds a new applicant to HireLah

Format: `add -a n/APPLICANT_NAME ag/AGE g/GENDER p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An applicant can have any number of tags (including 0)
</div>

* Age provided must be at least two digits eg: “23”
* Gender must be M/F

Examples:
* `add -a n/Benedict ag/20 g/M p/98123456 e/ben@gmail.com a/12 Kent Ridge Drive, 119243`
* `add -a n/Max ag/15 g/M p/97123456 e/max@yahoo.com a/12 Kent Ridge Drive, 119243 t/Data Analyst`

### Adding Interview : `add -i`

Adds a new interview to HireLah.

Format: `add -i APPLICANT_INDEX d/DATE p/POSITION_INDEX`
* Date provided must be in format YYYY-MM-DD HH:MM.
* The index refers to the index number shown in the last displayed Applicant
  list and Position list.
* Index provided must be positive.

Examples:
* `add -i 1 d/2022-01-01 14:00 p/2`

### Adding positions : `add -p`

Adds a new open position to HireLah.
Format: `add -p POSITION_NAME n/NUM_OPENINGS [d/DESCRIPTION] [r/REQUIREMENTS]`
* Positions must have a **unique name**.
* Name provided is case-insensitive.
* Number of openings in the position must be **0 or more** 0, 1, 2, …​

Examples:
* `add -p Senior Software Engineer n/3 d/More than 5 years experience r/JavaScript r/HTML r/CSS`

## Edit
General command to edit different types into HireLah.

Format: `edit -TYPE`
* TYPE must take the form of `a`, `i`, `p`
* -a will edit an applicant
* -i will edit an interview
* -p will edit a position

### Editing an Applicant : `edit -a`

Edits an existing Applicant in HireLah.

Format: `edit -a INDEX [n/APPLICANT_NAME] [ag/AGE] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the Applicant at the specified `INDEX`. The index refers to the index number shown in the displayed Applicant
  list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the applicant will be removed i.e adding of tags is not cumulative.
* You can remove all the Applicant’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit -a 1 n/Belle ag/43 g/F p/81234567` Edits the name, age, gender and phone number of the 1st applicant
   to be `Belle`, `1960-03-04`, `F` and `81234567` respectively.
*  `edit -a 2 e/belle@yahoo.com a/13 Computing Drive 612345 t/` Edits the email and address of the 2nd applicant to be
   `belle@yahoo.com` and `13 Computing Drive 612345` respectively, and clears all existing tags.

### Editing an Interview : `edit -i`

Edit an existing interview in HireLah.

Format: `edit -i INTERVIEW_INDEX [d/DATE] [p/POSITION_INDEX]`
* Edits the interview at the specified INTERVIEW_INDEX. The interview index refers to the index number shown in the 
  last displayed interview list.
* At least one optional field must be provided.
* The position index refers to the index number shown in the last displayed interview list.
* Existing attribute of the interview will be updated to the input value.
* When editing requirements, the existing requirements of the interview will be removed. i.e.
  adding requirements is not cumulative.

Examples:
* `edit -i 1 d/2022-01-01 15:00`
* `edit -i 3 d/2022-01-01 15:00 p/1`


### Edit positions : `edit -p`

Edit an existing position in HireLah.
Format: `edit -p POSITION_NAME [n/NUM_OPENINGS] [d/DESCRIPTION] [r/REQUIREMENTS]`

* Edits the available position with POSITION_NAME.
* Position name provided is case-insensitive.
* At least one optional field must be provided.
* Existing attributes of the position will be updated to the input value.
* When editing requirements, the existing requirements of the position will be removed. i.e. adding requirements is not cumulative.
* Requirements can be removed by providing an empty requirement field. i.e. r/

Examples:
* `edit -p Senior Frontend Software Engineer n/5`
* `edit -p Senior Frontend Software Engineer r/JavaScript r/React`

## Delete
General command to delete different data type in HireLah.

Format: `delete -TYPE`
* TYPE must take the form of `a`, `i`, `p`
* -a will delete an applicant
* -i will delete an interview
* -p will delete a position

### Deleting an Applicant : `del -a`

Deletes the specified Applicant from HireLah.

Format: `delete -a CANDIDATE_INDEX`

* Deletes the Applicant at the specified `CANDIDATE_INDEX`.
* The index refers to the index number shown in the displayed Applicant list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete -a 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete -a 1` deletes the 1st person in the results of the `find` command.

### Deleting an Interview: `del -i`

Deletes an existing interview in HireLah.

Format: `delete -i INTERVIEW_INDEX`
* Deletes the Interview at the specified `INTERVIEW_INDEX`.
* The index refers to the index number shown in the displayed Applicant list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete -i 3`


### Delete positions : `del -p`

Deletes an existing position in HireLah.

Format: `delete -p ROLE`
* Existing position with the specified name is deleted.
* The position name has to match with the position that is to be deleted.
* Position name provided is case-insensitive.

Examples:
* `delete -p Senior Frontend Software Engineer`

## List
General command to list different data type in HireLah.

Format: `list -TYPE`
* TYPE must take the form of `a`, `i`, `p`
* -a will list all applicants
* -i will list all interview
* -p will list all position

### List Applicants: `list -a`
Lists all existing applicants. Automatically toggles view to the applicant tab on the GUI.

Format: `list -a`

### Listing interviews: `list -i`

Lists all existing interviews in HireLah. Automatically toggles view to the interview tab on the GUI.

Format: `list -i`

Examples:
* `list -i 1`
### List Positions : `list -p`

Lists all existing positions in HireLah. Automatically toggles view to the position tab on the GUI.

Format: `list -p`


### Filter data: `filter`
View different applicants, interviews and positions in HireLah through various filters. It alters the current display of HireLah and changes the index of the relevant data.

Format: `filter DATA_TYPE b/FILTER_TYPE [ARGUMENT]`

* Different data has different filters available, thus requiring different arguments, as listed:

| DATA_TYPE | FILTER_TYPE | ARGUMENT(S)           | Description                                                                                  |
|-----------|-------------|-----------------------|----------------------------------------------------------------------------------------------|
| `appl`    | `name`      | `n/KEYWORD`           | View applicants whose name contains the keyword                                              |
| `appl`    | `tag`       | `t/TAG1, [t/TAG2, …]` | View applicants who have all the tags specified                                              |
| `intvw`   | `appl`      | `n/NAME`              | View interviews for applicants whose name is specified                                       |
| `intvw`   | `date`      | `d/DATE`              | View interviews happening on the specified date (Date provided must be in format YYYY-MM-DD) |
| `pos`     | `name`      | `n/KEYWORD`           | View positions which has the specified keyword in the position name                          |

Examples:
* `filter appl name n/john`
* `filter appl tag t/school t/friend`
* `filter intvw date d/2022-03-20`

### Sort data: `sort`

Arranges applicants, interview and positions in HireLah according to their properties. 
It alters the current display of HireLah and changes the index of the relevant data.

Format: `sort DATA_TYPE SORT_ORDER`

User can specify the order of the sorted data by typing `ASC` (for ascending) 
or `DSC` (for descending) in the `REVERSE` part.

| DATA_TYPE | Sorting properties | Description                                            |
|-----------|--------------------|--------------------------------------------------------|
| `APPL`    | Name               | Sort and view applicants based on their name           |
| `INTVW`   | Date               | Sort and view interviews based on their occurring date |
| `POS`     | Name               | Sort and view positions based on the position name     |

Examples:
* `sort APPL ASC`
* `sort POS DSC`

### Viewing help: `help`

Lists all the command keywords with their general descriptions

Format: `help`

_**For a more detail description about a specific `command`, you can type in the following:**_

Format: `help COMMAND`

* Full description and format of the command will be displayed

* Command name is not case-sensitive

Examples:
* `help add` 
* `help del`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Upon exiting HireLah, the data in the application will automatically be saved, including the positions, applicants, and interviews. There is no need to save manually.



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: How do I schedule an interview for a new applicant?<br>
**A**: You will need to first create a new applicant in HireLah, and ensure that the applied position exists in the system, else you will need to create the position as well. To schedule an interview, simply create a new interview with the applicant and the position.

**Q**: Can I add an applicant without any interviews scheduled?<br>
**A**: Yes, you can simply add a new applicant in HireLah without adding any interviews.

**Q**: I have a position that is only open for one applicant, do I still have to add the position?<br>
**A**: Yes, you will need to add the position as well even if it is only used once.

**Q**: A position has been filled, what do I do with the position created in the app?<br>
**A**: You can either mark the position as not open, or delete the position from HireLah according to your requirement and preference.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HireLah home folder.

