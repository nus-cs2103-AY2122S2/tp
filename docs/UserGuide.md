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


### Adding a module/student/professor: `add`

Adds a module/student/professor to the UniBook depending on the value defined in `o/OPTION`.

Format: `add o/OPTION...`  
OPTION values:  
1. module  
Format: `add o/module n/MODULENAME m/MODULECODE`  
This adds a Module with name: Discrete Mathematics and code: CS1231S to the UniBook.  


2. student  
Format: `add o/student n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​ [m/MODULE]…​`  
This adds a Student to the UniBook, it also adds the student into the student list of the corresponding Module objects (CS1231S and CS2103).  


3. professor  
Format: `add o/professor n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ [m/MODULE]…​`  
This adds a Professor to the UniBook, it also adds the professor into the professor list of the corresponding Module objects (CS1231S and CS2100).

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student/professor can have any number of tags and modules (including 0)
</div>

Examples:  
`add o/module n/Discrete Mathematics m/CS1231S` adds a module "Discrete Mathematics" with module code CS1231S to the UniBook  
`add o/student n/Johnston p/98765432 e/johnston@gmail.com t/friend m/CS1231S m/CS2103` adds a student named Johnston to the UniBook  
`add o/professor n/Aaron Tan p/98723432 e/aarontan@gmail.com a/COM2 01-15 t/smart m/CS1231S m/CS2100`  adds a professor named Aaron Tan to the UniBook  

### Listing entries: `list`

Lists entries in UniBook according to a specified listing criteria.

Format: `list o/OPTION [m/MODULE] [ty/TYPE]`, `list v/VIEW`

* Lists all entries in the UniBook based on the user specified option and criteria.
* If no argument is provided, UniBook simply lists every entry depending on the currently active view.

OPTION values:

* `module` -  lists all contacts that are participants of a module which is specified by entering 
`/m <MODULECODE>`.

* `type` - lists all contacts by their contact type, specified by entering `ty/<TYPE>` where
`<TYPE>` is either `professors` or `students`.

* [NOT READY] `group` - lists all contacts that are participants of that group

VIEW values:
* `people` - switches to people view, automatically lists all persons
* `modules` - switches to modules view, automatically list all modules

Examples:

* `list` displays every entry depending on currently active view.

* `list o/module m/CS2103` lists all contacts related to the module CS2103

* `list o/type ty/professors` lists all professors

* `list o/module m/CS2103 ty/professors` lists all professors of the module CS2103

* `list o/module m/CS2103 ty/students` lists all students of the module CS2103

* `list v/modules` switches the UniBook to `modules` view.

  
* [NOT READY] `list o/group W16-T1` lists all contacts that are related to the group W16-T1

### Editing a person : `edit`

Edits an existing person in UniBook.

Format: `edit INDEX o/OPTION [m/MODULE] [n/NAME] [p/PHONE] [e/EMAIL] [nm/NEWMODULE]`

* Edits the entity type defined by `o/OPTION`. This is a compulsory field.
* Before choosing to edit module or person, user can see which `INDEX` to edit by changing the UI to show the relevant list. User will not be allowed to edit if not on the correct page. 
  * `list v/modules` : To display list of modules with respective indexes
  * `list v/people` : To display list of persons with respective indexes 
* 2 values for `OPTION`:
    * `module`: Edits the module specified by compulsory field `INDEX`. Optional fields `[n/NAME] [m/MODULECODE]` to specify the new name or module code of the module.
    * `person`: Edits the person at the specified by the compulsory field `INDEX`. The index refers to the index number shown in the most recent list of contacts viewable on the GUI. The index must be a positive integer 1, 2, 3, …
* Existing values will be updated to the input values.

Examples:
*  `edit 1 o/person p/91234567 e/prof@example.com nm/CS2103` Edits the phone number and email address of the 1st person to be `91234567` and `prof@example.com` respectively. Adds module CS2103 to the list of modules that this person is taking. 
*  `edit 1 o/module m/CS2103 n/Software Engineering  ` Edits the module code of the 1st module to code `CS2103` and name as `Software Engineering`. Edits the all instances of modules in each person taking the module. 

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

`delete [INDEX]`
* Deletes the person or module at that index
* The GUI will display the index before the person or module

`delete m/MODULECODE [o/OPTION]`

* Deletes the module with the specified `MODULECODE`.
* Only can be used on module view
* The module must already exist in the system.
* Option is optional, and can take the following values: 
  - `ALL` -> Delete module and persons if the only module that person has matches the modulecode provided.
  - `MOD` -> Delete only the module
* If the option field is left blank, the `MOD` setting is assumed.    

Example:
* `delete m/CS2107` removes the "CS2107" module only.

[NOT READY] `delete m/MODULECODE g/GROUPCODE [o/OPTION]`
* Deletes the subgroup specified by `GROUPCODE`, within the module specified by `MODULECODE`.
* Option is optional, and can take the following values:
    - `ALL` -> Delete everything associated with the group, including profiles.
    - `GROUP` -> Delete only the relevant subgroup
* If the option field is left blank, the `GROUP` setting is assumed.
* Both the module and the subgroup must already exist in the system.

Example:
* `delete m/CS2107 g/T04` removes the T04 subgroup from the CS2107 module.

[NOT READY] `delete n/STUDENTNAME`
* Deletes the student profile specified by `STUDENTNAME`. If there are multiple matches,
a prompt to specify which profile to delete will appear.
  
Example: `delete n/Alan Tan` will remove the profile associated with Alan Tan. If there are multiple
profiles associated with this name, a list will appear prompting you to specify which profile to delete.

[NOT READY] `delete n/PROFNAME`
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
