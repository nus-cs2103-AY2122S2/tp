---
layout: page
title: User Guide
---

TrackBeau is a **desktop app for managing customer profile, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TrackBeau can get your customer management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `trackbeau.jar` from [here](https://github.com/AY2122S2-CS2103-F11-3/tp).

1. Copy the file to the folder you want to use as the _home folder_ for your TrackBeau.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will show you profiles of all customers.<br>
   Some example commands you can try:

   * **`list`** : Shows a list of all customers in the application.
   * **`show`** : Shows individual customer profile.
   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [e/EMAIL]` can be used as `n/John Doe e/johnd@example.com` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[sep/SERVICE_PREFERENCE]…​` can be used as ` ` (i.e. 0 times), `sep/massage`, `sep/facial sep/massage` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a customer: `add`

Adds a customer to the application.

Format: `add n/NAME p/PHONE_NUMBER a/ADDRESS [e/EMAIL] [stp/STAFF_PREFERENCE]…​ [sep/SERVICE_PREFERENCE]…​ [h/HAIR_TYPE] [s/SKIN_TYPE] [al/ALLERGY]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A customer can have any number of preferred staffs, preferred services and allergies (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 a/John street, block 123, #01-01 e/johnd@example.com`
* `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 al/Aloe Vera sep/facial sep/massage`

### Listing all customers : `list`

Shows a list of all customers in the application.

Format: `list`

### Listing individual customer's profile: `show`

Display all information about an individual customer.

Format: `show INDEX`

* Shows the customer profile at the specified INDEX.
* Index refers to the index number shown in the displayed customer list.
* Index must be a positive integer 1, 2, 3, …

Examples:
* `show 2` returns the profile of the 2nd customer in the application.

### Editing a customer : `edit`

Edits an existing customer in the application.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [stp/STAFF_PREFERENCE]…​ [sep/SERVICE_PREFERENCE]…​ [h/HAIR_TYPE] [s/SKIN_TYPE] [al/ALLERGY]…​`

* Edits the customer at the specified `INDEX`. The index refers to the index number shown in the displayed customer list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing staff preferences, service preferences or allergies, the existing staff preferences, service preferences or allergies of the customer will be removed i.e adding of staff preferences, service preferences or allergies is not cumulative.
* You can remove all the customer’s staff preferences, service preferences or allergies by typing `stp/`, `sep/` or `al/` without
    specifying any staff preferences, service preferences or allergies after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st customer to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower al/` Edits the name of the 2nd customer to be `Betsy Crower` and clears all existing allergies.

### Listing customers' profile by name: `find`

Finds customers whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `john` will match `john`
* Only the name is searched.
* Only full words will be matched e.g. `john` will not match `joh`

Examples:
* `find John` returns `john` and `John Doe`

### Deleting a customer : `delete`

Deletes the specified customer from the application.

Format: delete INDEX
* Deletes the customer profile at the specified INDEX.
* The index refers to the index number shown in the displayed customer list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `delete 2` : Removes the 2nd customer from the application.

### Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TrackBeau data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TrackBeau data are saved as a JSON file `[JAR file location]/data/trackbeau.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TrackBeau will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TrackBeau home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                    |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER a/ADDRESS [e/EMAIL] [stp/STAFF_PREFERENCE]…​ [sep/SERVICE_PREFERENCE]…​ [h/HAIR_TYPE] [s/SKIN_TYPE] [al/ALLERGY]…​`<br>e.g., `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 al/Aloe Vera sep/facial sep/massage` |
| **Clear**  | `clear`                                                                                                                                                                                                                                                             |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                 |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [stp/STAFF_PREFERENCE]…​ [sep/SERVICE_PREFERENCE]…​ [h/HAIR_TYPE] [s/SKIN_TYPE] [al/ALLERGY]…​`<br>e.g.,`edit 1 p/91234567 e/johndoe@example.com`                                                       |
| **Exit**   | `exit`                                                                                                                                                                                                                                                              |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find John`                                                                                                                                                                                                                |
| **Help**   | `help`                                                                                                                                                                                                                                                              |
| **List**   | `list`                                                                                                                                                                                                                                                              |
| **Show**   | `show INDEX`<br> e.g., `show 3`                                                                                                                                                                                                                                     |
