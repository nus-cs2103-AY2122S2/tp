---
layout: page
title: User Guide
---

CinnamonBun is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CinnamonBun can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `CinnamonBun.jar` from [here]() Work in progress, no jar yet.

1. Copy the file to the folder you want to use as the _home folder_ for your CinnamonBun Address Book.

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

* Multiple commands can be chained by separating each command with the '|' character. Each command is run sequentially from first to last. Invalid commands and special commands `help` and `exit` will break the chain and stop further command execution.<br>
  e.g `add n/John Doe e/johndoe@email.com | edit 5 p/999 | delete 2`
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

### Sort clients list: `sort`

Sorts the client list based on the order of the fields specified.

Fields that can be sorted:
* Name [n/]
* Phone number [p/]
* Email [e/]
* Address [a/]
* Remark [r/]
* Birthday [b/]

Format: `sort [n/] [desc] [p/] [desc] [e/] [desc] [a/] [desc] [r/] [desc] [b/] [desc]` <br>
`desc` keyword: Specifying it after a field means that particular field is to be sorted in descending order.

Examples:
* `sort n/ desc p/`: Clients will be sorted in descending order of their name. Clients with the same name will be then sorted 
based on their phone numbers in ascending order.
* `sort b/ a/`: Clients will be sorted in ascending order of their birthday. Clients with the same birthday will then be sorted
based on their addresses in ascending order.

Notes:
* The fields are to be specified in their prefix. They can be specified in any order, however, priority will be given
  based on the order specified.
* Clients with null values in the fields to be sorted will have lesser priority. 
  * For example `sort n/ b/`, 'Alice' will be at the top of the list. However, if there are multiple clients with the same name 'Alice',
  the client who's birthday field is empty, will be sorted to the bottom of the other clients named 'Alice'.

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
  * Note: Editing email value to an existing email in the addressBook is not allowed.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* You can also remove a person's remarks by typing `r/` without anything else.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds clients whose name, phone, email, address or tags contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `bob` will match `Bob`
* The order of the keywords does not matter. e.g. `Hans Bob` will match `Bob Hans`
* The name, phone, email, address and tags are searched.
* Only full words will be matched e.g. `Bob` will not match `Bobs`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bob` will return `Hans Zimmer`, `Bob Bob`

Examples:
* `find Bob` returns `bob` and `Bob Doe`
* `find 65123456` returns clients with the phone number `65123456`
* `find kent ridge` returns clients with an address containing `kent` or `ridge`
* `find manager hr` returns clients with the `manager` or `hr` tags

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Deleting all filtered people : `deleteFiltered`

Deletes the specified people from the address book.

Format: `deleteFiltered`

* Deletes the people filtered after using the find function.

Examples:
* `find Bob` followed by `deleteFiltered` deletes all people named Bob.

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

**Q**: Why is this named CinnamonBun?<br>
**A**: Because we like Cinnamon Buns.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Sort**| `sort [n/] [desc] [p/] [desc] [e/] [desc] [a/] [desc] [r/] [desc] [b/] [desc]` <br> e.g., `sort n/ desc p/`, `sort b/ a/`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**DeleteFiltered** | `deleteFiltered`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/remark] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`

