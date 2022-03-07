---
layout: page
title: User Guide
---

UniBook is a **desktop app for students to manage their university contacts in smart organized manner,** optimized for command-line interface (CLI) while still having the benefits of a Graphical User Interface (GUI).  
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `unibook.jar` from [here](https://github.com/AY2122S2-CS2103-W16-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for UniBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`o/student n/John Doe p/98765432 e/johnd@example.com` : Adds a student named `John Doe` to UniBook.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`edit`**`o/person 1 p/91234567 e/prof@email.com` : Edits the 1st contact's phone number and email shown in the current list.

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

Adds a person to the UniBook.

Format: `add o/OPTION n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS][m/MODULE]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of modules (including 0)
</div>

* Adds the entity type defined by `o/OPTION` to UniBook, where `n/NAME` is its name. These are the only two compulsory fields.
* 3 values for `o/OPTION`:
  * `o/module`: add module to UniBook, `n/name` specifies module name, while `m/MODULE` is compulsory to specify the module code.
  * `o/professor`: add professor to UniBook, compulsory `n/name` to specify their name, optional field `[m/MODULE]` to specify what module(s) that professor is handling.
  * `o/student`: add student to UniBook, compulsory field `n/name` to specify their name, and optional field `[m/MODULE]` to specify what module(s) that student is enrolled in.

Examples:
* `add o/module n/Software Engineering m/cs2103` add the module with code 'cs2103' and name 'Software Engineering' to UniBook
* `add o/professor n/John Doe p/98765432 e/johnd@example.com a/COM2-02-02 m/cs2103` add the professor 'John Doe' to UniBook
* `add o/student n/Betsy Crowe e/betsycrowe@example.com m/cs2103 m/cs2100` add a student 'Betsy Crowe' to UniBook

### Listing entries: `list`

Lists entries in UniBook according listing criteria.

Format: list [o/LISTING_CRITERIA CRITERIA_INFORMATION]…

* lists all contacts in UniBook in ordered alphabetically by name if no optional field is provided
* if [o/LISTING_CRITERIA CRITERIA_INFORMATION] is provided, then only contacts with fields (defined by LISTING\_CRITERIA) matching the CRITERIA\_INFORMATION provided will be shown to user
* listing criteria stack - so multiple listing criteria will further narrow the listing shown to user

LISTING_CRITERIA values:

* "module" -  lists all contacts that are participants of that module

* "group" - lists all contacts that are participants of that group

* "type" - lists all contacts by their contact type (professor or student)

Examples:

* `list o/module CS2103` lists all contacts related to the module CS2103

* `list o/group W16-T1` lists all contacts that are related to the group W16-T1

* `list o/type professors` lists all professors

* `list o/module CS2103 o/type professors` lists all professors of the module CS2103

* `list o/module CS2104 o/type students` lists all students of the module CS2103


### Editing a person : `edit`

Edits an existing person in UniBook.

Format: `edit o/OPTION [INDEX] [m/MODULE] [n/NAME] [p/PHONE] [e/EMAIL] [nm/NEWMODULE]`

* Edits the entity type defined by `o/OPTION`. This is a compulsory field.
* Depending on the entity type, certain fields will be compulsory.
* 2 values for `OPTION`:
    * `module`: Edits the module specified by compulsory field `m/MODULE`. Optional fields `[n/NAME] [nm/NEWMODULE]` to specify the new name or module code of the module.
    * `person`: Edits the person at the specified by the compulsory field INDEX. The index refers to the index number shown in the most recent list of contacts viewable on the GUI. The index must be a positive integer 1, 2, 3, …
* Existing values will be updated to the input values.

Examples:
*  `edit 1 p/91234567 e/prof@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `prof@example.com` respectively.
*  `edit o/module m/CS2103 n/Software Engineering  ` Edits the module code of the module with code `CS2103` to be named as `Software Engineering` and have a new code `CS2103T`

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

### Deleting specific entries : `delete`

Removes the specified modules, module subgroup, student or professor profile from the system.

Format:

`delete m/MODULECODE [o/OPTION]`

* Deletes the module with the specified `MODULECODE`.
* The module must already exist in the system.
* Option is optional, and can take the following values: 
  - `ALL` -> Delete everything associated with the module, including profiles.
  - `MOD` -> Delete only the module
  - `PROF` -> Delete the Professor associated with the module.
* If the option field is left blank, the `MOD` setting is assumed.    

Example:
* `delete m/CS2107` removes the "CS2107" module only.

`delete m/MODULECODE g/GROUPCODE [o/OPTION]`
* Deletes the subgroup specified by `GROUPCODE`, within the module specified by `MODULECODE`.
* Option is optional, and can take the following values:
    - `ALL` -> Delete everything associated with the group, including profiles.
    - `GROUP` -> Delete only the relevant subgroup
* If the option field is left blank, the `GROUP` setting is assumed.
* Both the module and the subgroup must already exist in the system.

Example:
* `delete m/CS2107 g/T04` removes the T04 subgroup from the CS2107 module.

`delete n/STUDENTNAME`
* Deletes the student profile specified by `STUDENTNAME`. If there are multiple matches,
a prompt to specify which profile to delete will appear.
  
Example: `delete n/Alan Tan` will remove the profile associated with Alan Tan. If there are multiple
profiles associated with this name, a list will appear prompting you to specify which profile to delete.

`delete n/PROFNAME`
* Deletes the professor profile specified by `PROFNAME`. If there are multiple matches, a prompt
to specify which profile to delete will appear.
  
Example: `delete n/Martin Henz` will remove the profile associated with Martin Henz. If there are multiple 
profiles associated with this name, a list will appear prompting you to specify which profile to delete.

### Clearing all entries : `clear`


Clears all entries from UniBook.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

UniBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

UniBook data are saved as a JSON file `[JAR file location]/data/unibook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, UniBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous UniBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add o/OPTION n/NAME [p/PHONE_NUMBER] [e/EMAIL] [m/MODULE]… ​` <br> e.g., `add o/module n/Software Engineering m/cs2103`<br> e.g., `add o/module n/Computer Organisation m/cs2100`<br> e.g., `add o/professor n/James Ho p/22224444 e/jamesho@example.com a/123 Clementi Rd S123466 m/cs2103`<br> e.g., `add o/student n/Peter Ho p/81234567 e/peterho@u.nus.edu m/cs2103 m/cs2100`
**Clear** | `clear`
**Delete** | `delete m/MODULECODE [o/OPTION]` <br> e.g. `delete m/CS2103` <br> e.g. `delete m/CS2103 o/PROF` <br> `delete m/MODULECODE g/GROUPCODE [o/OPTION]` <br> e.g. `delete m/CS2105 g/G04` <br> e.g. `delete m/CS2105 g/04 o/ALL` <br> `delete n/STUDENTNAME` <br> e.g. `delete n/Alan Tan` <br> `delete n/PROFNAME` <br> e.g. `delete n/Ooi Wei Tsang`
**Edit** | `edit o/OPTION [INDEX] [m/MODULE] [n/NAME] [p/PHONE] [e/EMAIL] `<br> e.g. `edit o/person 1 p/91234567 e/prof@email.com` <br> e.g. `edit o/module m/CS2103 n/Software Engineering`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list [o/LISTING_CRITERIA CRITERIA_INFORMATION]` <br>e.g., `list o/module CS2103`
**Help** | `help`
