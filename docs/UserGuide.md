---
layout: page
title: User Guide
---

HustleBook (HB) is a **desktop app for managing client details and meetings, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HB can get your client management meetings done faster than traditional GUI apps.

* Table of Contents
  * [Quick Start](#quick-start)
  * [Features](#features)
    * [Adding a client: `add`](#adding-a-client-add)
    * [Listing all persons : `list`](#listing-all-persons--list)
  * [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `hustlebook.jar` from [here](https://github.com/AY2122S2-CS2103T-W15-2/tp/releases/).

1. Copy the file to the folder you want to use as the _home folder_ for your HustleBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the HustleBook.

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

* Extraneous parameters for commands that do not take in parameters (such as `cleardemo`, `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a client: `add`

Adds a client to the HustleBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [d/DATA]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client's data can be added in the future
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 d/Salary-3400`



### Adding additional information: `addinfo`

Adds additional information that is relevant to a client

Format: `Format: addinfo n/NAME [INFO] [VALUE]`


Examples:
* `addinfo n/John Doe Preference CryptoCurrencies`
* `addinfo n/Betsy Risk High`

### Listing all persons : `list`

Shows a list of all clients in the HustleBook.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the HustleBook.

* Using **Index**:

  Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

  * Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
  * At least one of the optional fields must be provided.
  * Existing values will be updated to the input values.
  * When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
  * You can remove all the person’s tags by typing `t/` without
      specifying any tags after it.

    Examples:
    * `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
    * `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.


* Using **Name**:

    Format: `edit n/NAME d/NEW_DATE`

  * Edits the meeting date of the person with the specified name.
  * Date has to be in the format **DD-MM-YYYY**.
  
    Examples:
    * `edit n/John Doe d/04-12-2020` Edits the meeting date of the person with the name `John Doe` to `04-12-2020` which is 4th Dec 2020. 

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

Deletes the specified person from the HustleBook.

* Using **Index**:

  Format: `delete INDEX`

  * Deletes the person at the specified `INDEX`.
  * The index refers to the index number shown in the displayed person list.
  * The index **must be a positive integer** 1, 2, 3, …​
  
    Examples:
    * `list` followed by `delete 2` deletes the 2nd person in the address book.
    * `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
    

* Using **Name**: 

  Format: `delete n/NAME`

  * Deletes the person with the specified `NAME`.

### Clearing all entries : `clear`

Clears all entries from the HustleBook.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HustleBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HustleBook data are saved as a JSON file `[JAR file location]/data/hustlebook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HustleBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [d/DATA]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 d/Salary-3400` |
| **List**   | `list`                                                                                                                                                          |
| **Clear**  | `clear`                                                                                                                                                         |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                             |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                     |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                      |
| **Help**   | `help`                                                                                                                                                          |
