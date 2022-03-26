---
layout: page
title: User Guide
---

ModuleMateFinder (MMF) is a **desktop app for managing contacts in your educational organization, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MMF can get your contact management tasks done faster than traditional GUI apps.

- [Quick Start](#quick-start)
- [Features](#features)
- [FAQ](#faq)
- [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------
## Quick Jump to Specific Features

This section lists down all the features available in MMF. You can click on any of them to jump to the features.

- [Help](#viewing-help--help)
- [List](#listing-all-persons--list)
- [Add Contact](#adding-a-contact--add)
- [Add Module to Contact](#adding-modules-to-a-contact--addmodule)
- [Add Comment to Contact](#adding-a-comment-for-a-contact--addcomment)
- [Add Status to a Contact](#set-a-status-for-a-person--status)
- [Copy](#copy-command)
- [Clear](#clearing-all-entries--clear)
- [Clear all Modules from Contact](#clearing-all-modules-for-a-person--clearmodules)
- [Delete Contact](#deleting-a-person--delete)
- [Delete Module from Contact](#deleting-a-module--deletemodule)
- [Edit](#editing-a-person--edit)
- [Find](#locating-a-person-find)
- [Filter](#locating-a-person-by-their-module-filter)
- [Sort](#sorting-contacts-in-list-sort)
- [Undo](#undo-a-command--undo)
- [Redo](#redo-a-command--redo)
- [Exit](#exiting-the-program--exit)

-----------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ModuleMateFinder.jar` from [here](https://github.com/AY2122S2-CS2103T-T13-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ModuleMateFinder.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to ModuleMate Finder.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

ModuleMate Finder is a desktop app that allows students to find people taking the same modules as them, easily and efficiently

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`, `NAME`, `PHONE_NUMBER`, `EMAIL` and `ADDRESS`  are parameters which cannot be left empty. 

* Items in square brackets are optional.<br>
  e.g. `n/NAME [m/MODULE]` can be used as `n/John Doe m/CS2103` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[m/MODULE]…​` can be used as ` ` (i.e. 0 times), `m/CS2103`, `m/CS2103 m/CS2100` etc.

* Parameters has to be in order.<br>
  e.g. if the command specifies `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`, then it must follow strictly that format.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Listing all persons : `list`

Shows a list of all persons in ModuleMate Finder.

Format: `list`

### Adding a Contact : `add`

Adds a contact to ModuleMate Finder.
 
Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS​`

Examples:
* `add n/Bob p/87654321 e/bob@u.nus.edu a/123, Clementi Ave 16, #01-321`

Additionally, if one were to simply use `add`, it would open up a new window to allow users to systematically add a new contact
![add window](images/addWindow.png)  
Then, simply fill up the fields as guided in the window. Users can then press the `ENTER` key to submit the fields when complete, or press the `Submit` button.

### Adding Module(s) to a Contact : `addmodule`

Adds module(s) to an existing contact

Format: `addmodule INDEX m/MODULE [m/MODULE]...`

* Adds modules represented by each module code `m/MODULE` to a contact at index `INDEX`
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `addmodule 2 m/CS1231` Adds a module, `CS1231` to the 2nd person
* `addmodule 2 m/CS1231 m/CS2103T` Adds two modules, `CS1231` and `CS2103T` to the 2nd person

### Adding a comment for a contact : `comment`

Adds a comment for the specified person in ModuleMateFinder.

Format: `comment INDEX c/COMMENT`

* Adds a comment for the person at the specified `INDEX`.
* `INDEX` must be a **positive integer** 1, 2, 3, ...
* `COMMENT` cannot be blank.
* Any existing comments for a person will be overwritten by the new user input.
* If used without the `c/` prefix, the command will be treated as a delete command and removes the comment of the
  specified person.

Examples:
* `comment 2 c/Good at math.` will add the comment `Good at math` to the 2nd person.
* `comment 3` will delete the comment for the 3rd person.

### Add a status for a Person : `status`
Sets a person's status as favourite or blacklisted.

Format: `status INDEX s/STATUS`
- Gives a status to the person at specified `INDEX`
- Status can either be a `blacklist` or `favourite`, a person can have no status tagged.
- `INDEX` must be a **positive integer** 1, 2, 3, ...

Examples:
- `status 1 s/blacklist` tags the 1st person in ModuleMate Finder as blacklisted.
- `status 2 s/favourite` tags the 2nd person in ModuleMate Finder as favourite.
- `status 2 s/` will untag the 2nd person in ModuleMate Finder, leaving them with no `Status`

### Copy contacts in list: `copy`

Copy the people within address book. Attach a clipboard to the side of CLI to copy data.

Format: `copy [INDEX] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/STATUS] [m/MODULE] [f/FORMAT]​`

* Copy persons using specified field names.​
* If no fields are specified, all fields will be copied.
* Choose INDEX to copy a specific person.
* If no INDEX is specified, all persons will be copied.
* Choice of format is default, csv and json.
* Default simply displays attribute line by line, while csv format separates attributes via a `|` delimiter.
* JSON format is a JSON object with attribute names as keys and attribute values as values, similar to application's save file.
* Order of field names determines the order of attributes in the output.

Examples:
* `copy 1 n/ p/ e/ f/json`  will copy name, phone and email of first person in JSON format.
* `copy f/csv` will copy the entire list in csv format.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

Examples:
- `clear` wipes all data from ModuleMate Finder.


### Clearing all modules for a person : `clearmodules`

Clears all modules based on the given index from ModuleMate Finder.

Format: `clearmodules INDEX`

Examples:
- `clearmodules 5` wipes all modules for person in index 5.

### Deleting a person : `delete`

Deletes the specified person from ModuleMate Finder.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in ModuleMate Finder.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Deleting a module : `deletemodule`

Deletes the specified module from contact in ModuleMate Finder.

Format: `deletemodule INDEX m/MODULE...`

* Deletes modules for the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The modules will be deleted only if the person has the specified modules.

Examples:
* `list` followed by `deletemodule 2 m/CS3230` deletes the module CS3230 for the 2nd person in ModuleMate Finder.
* `find Betsy` followed by `deletemodule 1 m/CS2102 m/CS2040S` deletes the specified modules for the 1st person in the results of the `find` command.



### Editing a person : `edit`

Edits an existing person in ModuleMate Finder.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Modules cannot be edited through the `edit` command.

Additionally, if one were to simply use `edit`, it would open up a new window to allow users to systematically edit a chosen contact  
![edit window](images/edit_window.png)  
Then, simply fill up the fields as guided in the window.


Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 5 n/Bob a/Kent Ridge Drive` Edits the name and address of the 5th person to be `Bob` and `Kent Ridge Drive` 
  respectively.

### Locating a person: `find`

Finds a person by the given flag and keyword.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g. `hans` will match `Hans`
- Only the given flag + keyword is searched
- Keyword not matching the indicator is ignored
- Only full keywords will be matched e.g. `CS323` will not match `CS3230`
-Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`

### Locating a person by their module: `filter`

Finds a person by the given module code. 

Format: `filter MODULE`

- Only the given flag + keyword is searched
- Keyword not matching the indicator is ignored
- Valid module code have 2-3 prefix letters followed by 4 digits and one optional letter.

Examples:
* `filter CS3230`  will find all persons with the module CS3230

### Sorting contacts in list: `sort`

Sort all people within address book.

Format: `sort [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/STATUS] [m/MODULE]​`

* Sorts list with specified field(s). For any two persons, latter fields will only be considered if preceding fields are equal.​
* Order of fields is important and there must be at least one field.
* Parameters determine whether field is sorted on ascending or descending order.
* Parameters are optional must be either "asc", "desc" or an empty string "". Empty string "" is ascending by default.
* Parameters are case-insensitive. 

Examples:
* `sort n/asc p/desc`  will sort the list by name in ascending order first. If two persons have the same name, then sort by phone number in descending order.
* `sort n/` will sort the list by name in ascending order by default.

### Undo a command : `undo`

Undoes the most recent command. 

Format: `undo`

Examples:
- After deleting a contact at index 5, `undo` will reverse the delete command and bring the contact back at index 5.

### Redo a command : `redo`

Restores most recent command that was undone using `undo`.

Format: `redo`

Examples:
- After editing a contact's name at index 3 from `George` to `Adam` and using `undo` to reverse the contact's name
back to `George`, using `redo` will restore the contact's name back to `Adam`.

### Exiting the program : `exit`

Exits the program.

Format: `exit`


### Saving the data

ModuleMateFinder data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ModuleMateFinder data are saved as a JSON file `[JAR file location]/data/ModuleMateFinder.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ModuleMateFinder will discard all data and start with an empty data file at the next run.
</div>



### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

Q: Are all modules offered by NUS available in ModuleMateFinder

A: As long as the module offered can be found in NUSmod, it will be available on ModuleMateFinder as well.  

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action            | Format                                                                                                        | Examples                               |
|-------------------|---------------------------------------------------------------------------------------------------------------|----------------------------------------|
| **List**          | `list`                                                                                                        | `list`                                 |
| **Add**           | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`                                                                 | `add n/Bob p/87654321 e/bob@u.nus.edu` |
| **Add Module**    | `addmodule INDEX m/MODULE`                                                                                    | `addmodule 4 m/CS2100`                 |
| **Delete**        | `delete INDEX`                                                                                                | `delete 3`                             |
| **Delete Module** | `deletemodule index m/MODULE`...                                                                              | `deletemodule 1 m/CS1231`              |
| **Edit**          | `edit index [n/NAME] [c/CODE] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` **brackets indicate optional            | `edit 1 n/Alice`                       |
| **Clear**         | `clear`                                                                                                       | `clear`                                |
| **Clear Modules** | `clearmodules INDEX`                                                                                          | `clearmodules 3`                       |
| **Status**        | `status INDEX s/STATUS`                                                                                       | `status 2 s/favourite`                 |
| **Find**          | `find KEYWORD [MORE_KEYWORDS]`                                                                                | `find James Jake`                      |
| **Filter**        | `filter MODULE`                                                                                               | `filter CS3230`                        |
| **Sort**          | `sort [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/status] [m/MODULE] [o/ORDER]`  **brackets indicate optional | `sort n/ p/ o/desc`                    |
| **Undo**          | `undo`                                                                                                        | `undo`                                 |
| **Redo**          | `redo`                                                                                                        | `redo`                                 |
| **Copy**          | `copy [INDEX] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/STATUS] [m/MODULE] [f/FORMAT]`                      | `copy 3 n/ e/ f/csv`                   |
| **Add Comment**   | `comment INDEX c/COMMENT`                                                                                     | `comment 1 c/Good at math`             |


