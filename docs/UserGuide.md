---
layout: page
title: User Guide
---
## Introduction

ModuleMate finder (MMF) is a **desktop application for managing contacts and finding ModuleMates**. You can easily keep track of what modules your friends are/will be taking, so you can contact them to form groups.  
If you can type fast, you can get your contact management tasks done fast, but even if you are not a slow typist -- fret not, you can still use it!


- [Quick Start](#quick-start)
- [Features](#features)
- [FAQ](#faq)
- [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------
## Shortcut to Specific Features

This section lists down all the features available in MMF. You can click on any of them to jump to the features.

- [Help](#viewing-help--help)
- [List](#listing-all-contacts--list)
- [Add Contact](#adding-a-contact--add)
- [Add Module(s) to Contact](#adding-modules-to-a-contact--addmodule)
- [Add Comment to Contact](#adding-a-comment-for-a-contact--comment)
- [Add Status to a Contact](#add-a-status-for-a-contact--status)
- [Copy](#copy-contacts-in-list--copy)
- [Clear](#clearing-all-entries--clear)
- [Clear all Modules from Contact](#clearing-all-modules-for-a-contact--clearmodules)
- [Delete Contact](#deleting-a-contact--delete)
- [Delete Module(s) from Contact](#deleting-a-module--deletemodule)
- [Edit](#editing-a-contact--edit)
- [Find](#locating-a-contact-find)
- [Filter](#locating-a-contact-by-their-module-filter)
- [Sort](#sorting-contacts-in-list-sort)
- [Archive](#archiving-contacts-archive)
- [Unarchive](#unarchiving-contacts-archive)
- [Switch](#switching-between-default-and-archives-switch)
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

   * **`clear`** : Deletes all contacts. (You are recommended to clear all placeholder contacts before you start using ModuleMate Finder)

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

ModuleMate Finder is a desktop app that allows contacts to find people taking the same modules as them, easily and efficiently

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

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Listing all contacts : `list`

Shows a list of all contacts in ModuleMate Finder.

Format: `list`

### Adding a Contact : `add`

Adds a contact to ModuleMate Finder.
 
Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS​`

Examples:
* `add n/Bob p/87654321 e/bob@u.nus.edu a/123, Clementi Ave 16, #01-321`

Additionally, if you were to simply use `add`, it would open up a new window to allow you to systematically add a new contact
![add window](images/addWindow.png)  
Then, simply fill up the fields as guided in the window. You can then press the `ENTER` key to submit the fields when complete, or press the `Submit` button.

### Adding Module(s) to a Contact : `addmodule`

Adds module(s) to an existing contact

Format: `addmodule INDEX m/MODULE [m/MODULE]...`

* Adds modules represented by each module code `m/MODULE` to a contact at index `INDEX`
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `addmodule 2 m/CS1231` Adds a module, `CS1231` to the 2nd contact
* `addmodule 2 m/CS1231 m/CS2103T` Adds two modules, `CS1231` and `CS2103T` to the 2nd contact

_**See below for an example image**_

### Adding a comment for a contact : `comment`

Adds a comment for the specified contact in ModuleMateFinder.

Format: `comment INDEX c/COMMENT`

* Adds a comment for the contact at the specified `INDEX`.
* `INDEX` must be a **positive integer** 1, 2, 3, ...
* Any existing comments for a contact will be overwritten by the new input.
* If used with an **empty comment** (i.e. `comment 1 c/`), the command will be treated as a **delete
  command** and removes the comment of the specified contact.

Examples:
* `comment 2 c/Good at math.` will add the comment `Good at math` to the 2nd contact.
* `comment 3` will delete the comment for the 3rd contact.

_**See below for an example image**_

### Adding a status for a contact : `status`

Sets a contact's status as favourite or blacklisted.

Format: `status INDEX s/STATUS`
- Gives a status to the contact at specified `INDEX`
- Status can either be a `blacklist` or `favourite`, a contact can have no status tagged.
- `INDEX` must be a **positive integer** 1, 2, 3, ...
- If used with an **empty status field** (i.e. `status 1 s/`), the command will be treated as a **delete
  command** and removes the status of the specified contact.

Examples:
- `status 1 s/blacklist` tags the 1st contact in ModuleMate Finder as blacklisted.
- `status 2 s/favourite` tags the 2nd contact in ModuleMate Finder as favourite.
- `status 2 s/` will untag the 2nd contact in ModuleMate Finder, leaving them with no `Status`

Annotated image of what a `contact` with `Status`, `Module`, and `Comment`
![Example of a contact with Status and Comment](images/annotated_contact.png)

### Copy contacts in list : `copy`

Easily copy a contact's information into your clipboard, for easy pasting into a communication app of your choice.
For example, you can copy a contact's email and then paste it into your email client.

Format: `copy [INDEX] [n/] [p/] [e/] [a/] [s/] [m/] [c/] [f/]​`

* Copy contacts using specified field names.​
* If no fields are specified, **all fields will be copied**.
* Choose INDEX to copy a specific contact.
* If no INDEX is specified, **all contacts will be copied**.
* Choice of format is default, csv and json.
* **Default simply displays attribute line by line**, while csv format separates attributes via a `|` delimiter.
* JSON format is a JSON object with attribute names as keys and attribute values as values, similar to application's save file.
* Order of field names determines the order of attributes in the output.
* Choice of format is **default**, **csv** and **json**.
  
| Format  | Description                                               | Example                                                                                                           |
|---------|-----------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| default | Displays attributes separated by a newline                | mary<br/>91282770<br/>coast residences                                                                            |
| csv     | Displays attributes separated by a delimiter <code>&#124; | mary  &#124; 91282770 &#124; coast residences                                                                     |    
| json    | Displays information in json format                       | {<br/>  &nbsp;"name" : "mary",<br/>  &nbsp;"phone" : "91282270",<br/>  &nbsp;"address" : "coast residences"<br/>} | 


Examples:
* `copy 1 n/ p/ e/ f/json`  will copy name, phone and email of first contact in JSON format.
* `copy f/csv` will copy the entire list in csv format.
* `copy 1 e/` will copy the email of the first contact. You may then go to your mailing app and email the contact!

### Clearing all entries : `clear`

**Clears all entries** from the contact list.

Format: `clear`

Examples:
- `clear` wipes all data from ModuleMate Finder.


<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Typing this command will cause you to lose all data. Use with caution!
</div>


### Clearing all modules for a contact : `clearmodules`

**Clears all modules** based on the given index from ModuleMate Finder.

Format: `clearmodules INDEX`

* Deletes all modules from the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
- `clearmodules 5` wipes all modules for contact in index 5.

### Deleting a contact : `delete`

Deletes the specified contact from ModuleMate Finder.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in ModuleMate Finder.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

### Deleting a module : `deletemodule`

Deletes the specified module from contact in ModuleMate Finder.

Format: `deletemodule INDEX m/MODULE [m/MODULE]...`

* Deletes modules for the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* The modules will be deleted only if the contact has the specified modules.
* **One or more** modules must be specified.

Examples:
* `list` followed by `deletemodule 2 m/CS3230` deletes the module CS3230 for the 2nd contact in ModuleMate Finder.
* `find Betsy` followed by `deletemodule 1 m/CS2102 m/CS2040S` deletes the specified modules for the 1st contact in the results of the `find` command.

### Editing a contact : `edit`

Edits an existing contact in ModuleMate Finder.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* **At least one** of the optional fields must be provided.
* Existing values will be updated to the input values.
* **Modules cannot be edited** through the `edit` command.
  * Instead, use `deletemodule` to remove the modules first, then `addmodule` to add the modules to a contact.

Additionally, if one were to simply use `edit`, it would open up a new window to allow you to systematically edit a chosen contact  
![edit window](images/edit_window.png)  
Then, simply fill up the fields as guided in the window.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
* `edit 5 n/Bob a/Kent Ridge Drive` Edits the name and address of the 5th contact to be `Bob` and `Kent Ridge Drive` 
  respectively.

### Locating a contact: `find`

Finds a contact by the given keyword.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g. `hans` will match `Hans`
- Only the given **keyword** is searched
- Words not matching the given keyword is ignored
- Only **full keywords will be matched** e.g. `Pol` will not match `Police`
- contacts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`

### Locating a contact by their module: `filter`

Finds a contact by the given module code. 

Format: `filter MODULE`


- The search is key insensitive
- Only the **given keyword** is searched
- Words not matching the given keyword is ignored
- Valid module code have 2-3 prefix letters followed by 4 digits and one optional letter.

Examples:
* `filter CS3230`  will find all contacts with the module CS3230

![Filter example](./images/filterExample.png)



### Sorting contacts in list: `sort`

Sort all people within contact list.

Format: `sort [n/ASC|DESC] [p/ASC|DESC] [e/ASC|DESC] [a/ASC|DESC] [s/ASC|DESC] [m/ASC|DESC] [c/ASC|DESC]​`

* Sorts list with specified field(s). For any two contacts, latter fields will only be considered if preceding fields are equal.​
* Order of fields is important and there must be **at least one field**.
* Parameters determine whether field is sorted on ascending or descending order.
* Parameters are optional. If included, it must be either `asc`, `desc` or an empty string `""`. 
* Empty string `""` is ascending by default.
* Parameters are case-insensitive. 


| Field   | Prefix | Specification of sort                   | Example (Ascending)                                            |                                         
|---------|--------|-----------------------------------------|----------------------------------------------------------------|
| Name    | `n/`   | Sorted lexicographically                | `Alice` is followed by 'Bob'                                   |
| Phone   | `p/`   | Sorted numerically                      | `1` is followed by `9`                                         |
| Email   | `e/`   | Sorted lexicographically                | `alice@gmail.com` is followed by `bob@gmail.com`               |
| Address | `a/`   | Sorted lexicographically                | `apple residences` is followed by `banana residences`          |
| Status  | `s/`   | Sorted lexicographically                | `""` is followed by `blacklist` is followed by `favourite `    |                
| Module  | `m/`   | Sorted numerically by number of modules | A contact with 1 modules is followed by contact with 5 modules |
| Comment | `c/`   | Sorted numerically by length of comment | `a` is followed by `aaaaaaaaaaaaaaaaaa`                        |


Examples:
* `sort n/asc p/desc`  will sort the list by name in ascending order first. If two contacts have the same name, then sort by phone number in descending order.
* `sort n/` will sort the list by name in ascending order by default.

### Archiving contacts: `archive`

You can archive contacts into a separate contact list. Archiving a contact can keep ModuleMate Finder more organised by hiding individuals (such as those whom have graduated) away from your contact list.

format: `archive INDEX`
* Archives the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `archive 2` archives the 2nd contact in ModuleMate Finder.
* `find Betsy` followed by `archive 1` archives the 1st contact in the results of the `find` command.

<div markdown="span" class="alert alert-warning">:exclamation: **Alert:**
You can only use this command when in the **default contact list**. Otherwise, you will get an error that tells you to use the correct command
</div>


### Unarchiving contacts: `unarchive`

You can also bring contacts back from the archives if necessary.

format: `unarchive INDEX`
* _Unarchives_ the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `unarchive 5` _unarchives_ the 5th contact in ModuleMate Finder.
* `find Lizzy` followed by `unarchive 1` _unarchives_ the 1st contact in the results of the `find` command.

<div markdown="span" class="alert alert-warning">:exclamation: **Alert:**
You can only use this command when in the **archives contact list**. Otherwise, you will get an error that tells you to use the correct command
</div>

### Switching between the default and archived contact list: `switch`

Switch between the default contact list or the archived contact list in ModuleMate Finder.

format: `switch`
* Alternatively, you may use <kbd>F10</kbd> or `File -> Switch...` in the menu to perform the switch

Examples:
* `switch` changes the view to the other contact list. If you are in `Default` mode, then it changes to `Archives`, and vice-versa.
* Pressing <kbd>F10</kbd> on your keyboard or `File -> Switch...` in the menu also does the same as the above.

### Undo a command : `undo`

Undoes the most recent command. 

At the moment, the command that can be undone are the following:   

- `add`
- `addmodule`
- `archive`
- `unarchive`
- `clear`
- `clearmodules`
- `comment`
- `delete`
- `deletemodule`
- `edit`
- `sort`
- `status`
- `switch`

Format: `undo`

Examples:

- `delete 5`  
`undo`

After deleting a contact at index 5, `undo` will reverse the delete command and bring the contact back at index 5.

### Redo a command : `redo`

Restores most recent command that was undone using `undo`. If you use a command that is not undo or redo, 
while in the midst of undoing, there will no longer be commands to redo.

Format: `redo`

Examples:

- `edit 3 n/Adam`  
`undo`  
`redo`

 After editing a contact's name at index 3 from `George` to `Adam` and using `undo` to reverse the contact's name back to `George`, using `redo` will restore the contact's name back to `Adam`.
### Exiting the program : `exit`

Exits the program.

Format: `exit`


### Saving the data

ModuleMateFinder data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ModuleMateFinder data are saved as a JSON file `[JAR file location]/data/ModuleMateFinder.json`. If you are an advanced user, you are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ModuleMateFinder will discard all data and start with an empty data file at the next run.
</div>



--------------------------------------------------------------------------------------------------------------------

## FAQ

Q: Are all modules offered by NUS available in ModuleMateFinder

A: As long as the module offered can be found in NUSmod, it will be available on ModuleMateFinder as well.  

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action            | Format                                                                                               | Examples                               |
|-------------------|------------------------------------------------------------------------------------------------------|----------------------------------------|
| **List**          | `list`                                                                                               | `list`                                 |
| **Add**           | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`                                                        | `add n/Bob p/87654321 e/bob@u.nus.edu` |
| **Add Module**    | `addmodule INDEX m/MODULE`                                                                           | `addmodule 4 m/CS2100`                 |
| **Delete**        | `delete INDEX`                                                                                       | `delete 3`                             |
| **Delete Module** | `deletemodule INDEX m/MODULE [m/MODULE]...`                                                          | `deletemodule 1 m/CS1231 m/CS2102`     |
| **Edit**          | `edit index [n/NAME] [c/CODE] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`                                | `edit 1 n/Alice`                       |
| **Clear**         | `clear`                                                                                              | `clear`                                |
| **Clear Modules** | `clearmodules INDEX`                                                                                 | `clearmodules 3`                       |
| **Status**        | `status INDEX s/STATUS`                                                                              | `status 2 s/favourite`                 |
| **Find**          | `find KEYWORD [MORE_KEYWORDS]`                                                                       | `find James Jake`                      |
| **Filter**        | `filter MODULE`                                                                                      | `filter CS3230`                        |
| **Sort**          | `sort [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/status] [m/MODULE] [c/COMMENT]`                    | `sort n/asc p/ a/asc`                  |
| **Undo**          | `undo`                                                                                               | `undo`                                 |
| **Redo**          | `redo`                                                                                               | `redo`                                 |
| **Copy**          | `copy [INDEX] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/STATUS] [m/MODULE] [c/COMMENT] [f/FORMAT]` | `copy 3 n/ e/ f/csv`                   |
| **Add Comment**   | `comment INDEX c/COMMENT`                                                                            | `comment 1 c/Good at math`             |


