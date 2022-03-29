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

Lists entries in UniBook according to a specified listing criteria. Behaves differently depending
on currently active view.

**Changing UniBook views**

This command applies for all `views`.

Format: `list o/view v/<VIEWTYPE>`

* Changes the view of the `UniBook` to the specified view type. Available arguments for 
`VIEWTYPE` are `people`/`groups`/`modules`.
* Example: `list o/view v/modules` switches the `UniBook` to the `modules` view.  

**Commands while in the `People` view**

These commands apply while in the `People` view.

Format: `list`

* Lists all people in the UniBook.

Format: `list type/<PERSONTYPE>`

* Lists all people of a specific type. Available arguments for `PERSONTYPE` are `students`/`professors`.
* Example: `list type/professors` lists every `Professor` in the `UniBook`.

Format: `list o/module m/<MODULECODE>`

* Lists all people in a given `Module`.
* Example: `list o/module m/CS2103` displays all people who are in the module `CS2103`.

Format: `list o/module m/<MODULECODE> type/<PERSONTYPE>`

* Lists all people in a given `Module` who are of a specific type.
* Example: `list o/module m/cs2103 type/students` lists all `students` who are in the module `CS2103`.

Format: `list o/group m/<MODULECODE> g/<GROUPNAME>`

* Lists all students who are in a given `Group` of a given `Module`.
* Example: `list o/group m/CS2103 g/W16-1` lists all students in group `W16-1` of module `CS2103`.

**Commands while in the `Modules` view**

These commands apply while in the `Modules` view.

Format: `list`

- Lists all modules.

Format: `list m/<MODULECODE>`

- Lists a module with a given module code.
- Example: `list m/CS2103` lists the module `CS2103`.

Format: `list n/<KEYWORD>`

- Lists modules which have module names containing the given keyword.
- Example: `list n/Software` will display all modules that contain `Software` in their module name.

Format: `list ke/<KEYEVENT>`

- Lists modules which has a specific type of key event. Acceptable arguments for `KEYEVENT` are 
  `EXAM/QUIZ/ASSIGNMENT_DUE/ASSIGNMENT_RELEASE`.
- Example: `list ke/EXAM` will display all modules that have key event(s) of type `EXAM`.

Format: `list dt/<YYYY-MM-DD>`

- Lists modules which has any type of key event(s) falling on a given date.
- Example: `list dt/2022-05-04` displays all modules with any key event(s) falling on `May 4th 2022`.

Format: `list dt/<YYYY-MM-DD> ke/<KEYEVENT>`

- Lists all modules which has a specific type of key event falling on a given date. Acceptable arguments for `KEYEVENT` are
  `EXAM/QUIZ/ASSIGNMENT_DUE/ASSIGNMENT_RELEASE`.
- Example: `list dt/2022-05-04 ke/QUIZ` displays all modules with key event(s) `Quiz` falling on `May 4th 2022`.

Format: `list dt/<YYYY-MM-DD> n/<KEYWORD>`

- Lists all modules which have a module name containing a given name and any type of key event(s) 
  falling on a given date.
- Example: `list dt/2022-05-04 n/Network` displays all modules that have `Network` in their module name and
have key events falling on `May 4th 2022`.
  
Format: `list n/<KEYWORD> ke/<KEYEVENT>`
- Lists all modules which has a module containing a given name and a specific type of key event.
  Acceptable arguments for `KEYEVENT` are
  `EXAM/QUIZ/ASSIGNMENT_DUE/ASSIGNMENT_RELEASE`.
- Example: `list n/Software ke/ASSIGNMENT_DUE` displays all modules that have `Software` in their module name and
have key event(s) of type `ASSIGNMENT_DUE`.
  
Format: `list n/<KEYWORD> ke/<KEYEVENT> dt/<YYYY-MM-DD>`
- Lists all modules which has a module containing a given name and a specific type of key event falling
on a given date. Acceptable arguments for `KEYEVENT` are `EXAM/QUIZ/ASSIGNMENT_DUE/ASSIGNMENT_RELEASE`.
- Example: `list n/Software ke/ASSIGNMENT_DUE dt/2022-05-04` displays all modules that have `Software` 
  in their module name and have key event(s) of type `ASSIGNMENT_DUE` which fall on `May 4th 2022`.
  
Format: `list o/group g<GROUPNAME>`
- If the module list currently has `1` module showing, UniBook switches to `Groups` view automatically and displays
the specific group with the given group name, from the given module.
- Otherwise, UniBook switches to `Groups` view automatically and displays all groups with the given name.
- Example: Assume module `CS2103` along with multiple other modules have a group with group name `W16-1`. 
  When the command `list o/group g/W16-1` is run, if only `CS2103` was visible then the specific `W16-1` in `CS2103` 
  is displayed in the group view. Otherwise all groups with name `W16-1` from all modules are displayed.
  
**Commands while in the `Groups` view**

These commands apply while in the `Groups` view.

Format: `list`
- Lists all groups.

Format: `list g/<GROUPNAME>`
- Lists all groups with a given group name.
- Example: `list g/W16-1` lists all groups with the name `W16-1`.

Format: `list g/<GROUPNAME> m/<MODULECODE>`
- Lists all groups with a given group name in a specific module.
- Example: `list g/W16-1 m/CS2103` lists group `W16-1` of `CS2103`.

Format: `list mt/<YYYY-MM-DD>`
- Lists all groups with a meeting times falling on a given date.
- Example: `list mt/2022-05-04` lists all groups that have meetings on `May 4th 2022`.


### Editing a person : `edit`

Edits an existing person in UniBook.

Format: `edit INDEX o/OPTION [m/MODULE] [n/NAME] [p/PHONE] [e/EMAIL] [a/OFFICE] [nm/NEWMODULE] [g/GROUP] [mt/INDEX DATETIME]`

* Edits the entity type defined by `o/OPTION`. This is a compulsory field.
  * Options available are person, module, keyevent or group.
* All indexes must be positive integers.
* Existing values will be updated to the input values.    
* Before choosing to edit module or person, user can see which `INDEX` to edit by changing the UI to show the relevant list. User will not be allowed to edit if not on the correct page. 
  * `list o/view v/modules` : To display list of modules with respective indexes
  * `list o/view v/people` : To display list of persons with respective indexes 
  * `list o/view v/groups` : To display list of groups with respective indexes 
* Only certain options can be edited on each page, otherwise an error will be printed on the console to tell users to change to the correct page. On the
  * `module` page: Able to edit module, keyevent or group fields
  * `group`  page: Able to edit keyevent or group fields
  * `person` page: Able to edit person
    
**Commands while on `People` view**

At least one optional field must be edited in order for person to be successfully edited.

Format: `edit INDEX o/person [n/NAME] [p/PHONE] [e/EMAIL] [a/OFFICE]`
* Edits the `name`, `phone` and/or `email` fields of a person at `INDEX`.
* The `office` field can only be edited if the person is a professor.  
* Example: `edit 1 o/person n/Alexa` changes the name of the first person on the list to Alexa.

Format: `edit INDEX o/person [m/MODULE] [g/GROUPNAME]`
* Adds person at `INDEX` to the group named `GROUPNAME` in the stated `MODULE`.
* Both `[m/MODULE]` and `[g/GROUPNAME]` fields are compulsory for successful edit.
* Example: `edit 1 o/person m/CS2103 g/T2` adds the first person to the `group` named T2 in the CS2103 `module`.

Format: `edit INDEX o/person [nm/NEWMOD]`
* Adds person at `INDEX` to the stated `MODULE`.
* Example: `edit 1 o/person nm/CS2103` adds the first person to the CS2103 `module`.

**Commands while on `Modules` view**

At least one optional field must be edited in order for module to be successfully edited.

Format: `edit INDEX o/module [n/NAME] [m/MODCODE]`
* Edits the `name` and/or `modcode` fields of a module at `INDEX`.
* Example: `edit 1 o/module n/Software Engineering m/CS2103` changes the name and module code of the first module on the list to Software Engineering and CS2103 respectively.

Format: `edit INDEX o/group m/MODULE [g/GROUPNAME] [mt/INDEX DATETIME]`
* Edits the `groupname` and/or `meetingtimes` of the group at `INDEX` of the `module`.
* `DATETIME` must be in `YYYY-MM-DD HH:mm` format.  
* Example: `edit 1 o/group m/CS2103 g/T2 mt/2 2020-12-12 16:45` edits the first group's name and second index of meeting time of the CS2103 `module` to T2 and 12th December 2022 4:45pm respectively.

Format: `edit INDEX o/keyevent ke/INDEX [type/TYPE] [dt/DATETIME]`
* Edits  `type` and/or `datetime` of the keyevent at module at `INDEX`.
* Example: `edit 1 o/keyevent ke/2 type/exam dt/2020-12-12 16:45` adds the second key event's type and date time in the first module in the list to exam and 12th December 2022 4:45pm respectively.

**Commands while on `Groups` view**

At least one optional field must be edited in order for module to be successfully edited.

Format: `edit INDEX o/group m/MODULE [g/GROUPNAME] [mt/INDEX DATETIME]`
* Edits the `groupname` and/or `meetingtimes` of the group at `INDEX` of the `module`.
* `DATETIME` must be in `YYYY-MM-DD HH:mm` format.
* Example: `edit 1 o/group m/CS2103 g/T2 mt/2 2020-12-12 16:45` edits the first group's name and second index of meeting time of the CS2103 `module` to T2 and 12th December 2022 4:45pm respectively.

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
* Deletes the person at that index
* Only can be used on Person view

`delete m/MODULECODE [o/OPTION]`

* Deletes the module with the specified `MODULECODE`.
* Only can be used on module view
* The module must already exist in the system.
* [NOT READY] Option is optional, and can take the following values: 
  - `ALL` -> Delete everything associated with the module, including profiles.
  - `MOD` -> Delete only the module
  - `PROF` -> Delete the Professor associated with the module.
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
**Edit** | `edit INDEX o/OPTION [m/MODULE] [n/NAME] [p/PHONE] [e/EMAIL] [nm/NEWMODULE] [g/GROUP] [mt/INDEX DATETIME] `<br> e.g. `edit o/person 1 p/91234567 e/prof@email.com` <br> e.g. `edit o/module m/CS2103 n/Software Engineering`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list [o/LISTING_CRITERIA CRITERIA_INFORMATION]` <br>e.g., `list o/module CS2103`
**Help** | `help`
