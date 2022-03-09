---
layout: page
title: User Guide
---

NUSocials is a **desktop app for university students to maintain a professional contact list, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). The value of the app is to facilitate a convenient way for university students to manage their professional networks with fellow acquaintances.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `nusocials.jar` from [here]() [coming soon].

1. Copy the file to the folder you want to use as the _home folder_ for NUSocials.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/fred p/99998888 e/fred@example.com a/fred street, block 123, #01-01` : Adds a contact named `fred` to the Address Book.

   * **`tag`** `2 edu/computer science m/CS2040S` : Tags the 2nd contact shown in the current list with a Computer Science degree and CS2040S module.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.
    
   * **`delete`**`3 5` : Deletes the 3rd and 5th contact shown in the current list.
    
   * **`find`**`n/fred` : Finds persons that match the name 'fred'.
    
   * **`find -s`**`n/fred m/cs2040s edu/computer science` : Finds persons that match the name 'fred' AND takes the module 'cs2040s' AND is studying 'computer science'.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are mandatory parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Kim Lai`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Kim Lai t/friend` or as `n/Kim Lai`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[m/MODULES]…​` can be used as ` ` (i.e. 0 times), `m/CS2040S`, `m/CS2030S m/CS2100` etc.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12345678 p/87654321`, onbly `p/87654321` will be taken.
* 
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* All commands are case-sensitive.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message]()

Format: `help`


### Adding a person: `add` [coming in V1.2]
Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

Examples:
* `add n/Kim Lai p/12345678 e/kimlai222@example.com a/KL street, block 123, #01-01`

### Tagging a person: `tag` [coming in V1.2]
Tags additional information to an existing contact.

Format: `tag INDEX [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`

* Tags the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the prefixes must be provided.
* If tagging more than one value for a field, separate each value with a `,`.
* Input tag values will be accumulated to the existing tags.

Examples:
* `tag 1 i/abc-company m/CS2100,CS2030S` Tags the internship company and 2 modules to the 1st person.

### Listing all persons : `list` [coming in V1.2]
Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit` [coming in V1.2]

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* You can remove the person’s respective tags by typing `i/`, `m/`, `c/`, `edu/` without
  specifying any tags after it.

Examples:
* `edit 1 p/91234567 e/KL123@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `KL123@example.com` respectively.
* `edit 2 m/CS2040s` Clears all module tags of the 2nd person and tag `CS2040s` as a module.
* `edit 2 edu/ m/` Clears all education and module tags of the 2nd person.

### Locating persons: `find` [coming in v1.2]

Finds persons that match any of the given fields.

Format: `find [n/NAME]…​ [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`

* The search is case-insensitive. e.g `hans` will match `Hans`
* At least one of the optional fields must be provided.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one of the field will be returned (i.e. `OR` search).
  e.g. `n/Hans m/cs2040s` will return `Hans`, `Bo Yang` (i.e. Bo Yang is tagged with cs2040s)

Examples:
* `find n/John` returns `john` 
* `find i/Shopee m/cs2040s cs2030s` returns `Alex Yeoh` (i.e Alex Yeoh is tagged with Shopee), `David Li` (i.e. David Li is tagged with cs2040s, cs2030s)<br>
  ![result for 'find i/Shopee m/cs2040s cs2030s']()

### Locating specific persons: `find -s` [coming in v1.2]

Finds persons that match all given fields.

Format: `find -s [n/NAME]…​ [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`

* The search is case-insensitive. e.g `hans` will match `Hans`
* At least one of the optional fields must be provided.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Only persons matching all fields will be returned (i.e. `AND` search).
  e.g. `n/Bo Yang m/cs2040s` will return `Bo Yang` (i.e. Bo Yang is tagged with cs2040s)

Examples:
* `find -s n/John Doe` returns `John Doe`
* `find -s n/David Li m/cs2040s cs2030s` returns `David Li` (i.e. David Li is tagged with cs2040s, cs2030s)<br>
  ![result for 'find -s n/David Li m/cs2040s cs2030s']()
 
### Deleting a person : `delete` [coming in V1.2]

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

Alternate Format: `delete INDEX…​`

* Deletes multiple persons at the specified `INDEX`'s.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* Each index **must be separated by a whitespace**

Examples:
* `list` followed by `delete 2 5 7` deletes the 2nd, 5th and 7th person in the address book.

### Clearing all entries : `clear`

Clears all entries from NUSocials.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

NUSocials data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/nusocials.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, NUSocials will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665`
**Tag** | `tag INDEX [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`<br> e.g.,`tag 1 m/CS2105`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3` <br> `delete INDEX…​INDEX` <br> e.g. `delete 1 3 5`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`<br> e.g.,`edit 2 n/Fred e/fred111@example.com`
**Find** | `find [n/NAME]…​ [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`<br> e.g., `find n/john edu/computer science`  
**Find -s** | `find -s [n/NAME]…​ [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`<br> e.g., `find -s n/john i/bytedance edu/computer science`
**List** | `list`
**Help** | `help`
