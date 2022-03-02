---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding positions : `posadd`

Adds a new open position to HireLah.
Format: `posadd POSITION_NAME n/NUM_OPENINGS [d/DESCRIPTION] [r/REQUIREMENTS]`
* Positions must have a **unique name**.
* Name provided is case-insensitive.
* Number of openings in the position must be **0 or more** 0, 1, 2, …​

Examples:
* `posadd Senior Software Engineer n/3 d/More than 5 years experience r/JavaScript r/HTML r/CSS`

### Edit positions : `posedit`

Edit an existing position in HireLah.
Format: `posedit POSITION_NAME [n/NUM_OPENINGS] [d/DESCRIPTION] [r/REQUIREMENTS]`

* Edits the available position with POSITION_NAME.
* Position name provided is case-insensitive.
* At least one optional field must be provided.
* Existing attributes of the position will be updated to the input value.
* When editing requirements, the existing requirements of the position will be removed. i.e. adding requirements is not cumulative. 
* Requirements can be removed by providing an empty requirement field. i.e. r/

Examples:
* `posedit Senior Frontend Software Engineer n/5`
* `posedit Senior Frontend Software Engineer r/JavaScript r/React`

### Delete positions : `posdel`

Deletes an existing position in HireLah.
Format: `posdel POSITION_NAME`
* Existing position with the specified name is deleted.
* The position name has to match with the position that is to be deleted.
* Position name provided is case-insensitive.

Examples:
* `posdel Senior Frontend Software Engineer`

### List Positions : `poslist`

Lists all existing positions in HireLah.
Format: `poslist`

### Adding Interview : `intvwadd`

Adds a new interview to HireLah.

Format: `intvwadd n/CANDIDATE_INDEX d/DATE r/ROLE`
* Date provided must be in format YYYY-MM-DD HH:MM.
* Role *must* currently exist in position.

Examples:
* `intvwadd n/1 d/2022-01-01 14:00 r/Senior Frontend Software Engineer`

### Editing an Interview : `intvwedit`

Edit an existing interview in HireLah.

Format: `intvwedit CANDIDATE_INDEX ROLE [d/DATE] [r/NEWROLE]`
* Edits the interview with CANDIDATE_INDEX and ROLE.
* At least one optional field must be provided.
* Existing attribute of the interview will be updated to the input value.
* When editing requirements, the existing requirements of the interview will be removed. i.e. 
adding requirements is not cumulative.

Examples:
* `intvwedit 1 Senior Frontend Software Engineer d/2022-01-01 15:00`
* `intvwedit 1 Senior Frontend Software Engineer r/Senior FullStack Developer`

### Deleting an Interview: `intvwdel`

Deletes an existing interview in HireLah.

Format: `intvwdel CANDIDATE_INDEX ROLE`
* Date provided must be in format YYYY-MM-DD HH:MM. 
* Existing interview with the matching CANDIDATE_INDEX and ROLE is deleted. 
* Role provided is case-insensitive.

Examples:
* `intvwdel 1 Senior Frontend Software Engineer`

### Listing interviews: `intvwlist`

Lists all existing interviews for the candidate in HireLah.

Format: `intvwlist CANDIDATE_INDEX`

Examples:
* `intvwlist 1`

### Filter data: `filter`
View different applicants, interviews and positions in HireLah through various filters. It alters the current display of HireLah and changes the index of the relevant data.

Format: `filter DATA_TYPE b/FILTER_TYPE [ARGUMENT]`

* 
* Different data has different filters available, thus requiring different arguments, as listed:

DATA_TYPE | FILTER_TYPE | ARGUMENT(S)           | Description
----|-------------|-----------------------|---
`appl` | `name`      | `n/KEYWORD`           | View applicants whose name contains the keyword
`appl` | `tag`       | `t/TAG1, [t/TAG2, …]` | View applicants who have all the tags specified
`intvw` | `appl`      | `n/NAME`              | View interviews for applicants whose name is specified
`intvw` | `date`      | `d/DATE`              | View interviews happening on the specified date (Date provided must be in format YYYY-MM-DD)
`pos` | `name`      | `n/KEYWORD`           | View positions which has the specified keyword in the position name

Examples:
* `filter appl name n/john`
* `filter appl tag t/school t/friend`
* `filter intvw date d/2022-03-20`

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

