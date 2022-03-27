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

2. Download the latest `NUSocials.jar` from [here](https://github.com/AY2122S2-CS2103T-W11-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for NUSocials.

4. To start the app:
   - Windows: Double-click on `NUSocials.jar`.
   - MacOS: On terminal, navigate to the directory containing `NUSocials.jar` and run `java -jar NUSocials.jar`.
   
5. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![img.png](images/UiSampleAddressBook.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`**<br>Lists all contacts and upcoming events.

    * **`add`**`n/fred p/99998888 e/fred@example.com a/fred street, block 123, #01-01`<br>Adds a contact named `fred` to the Address Book.

    * **`tag`** `2 edu/computer science m/CS2040S`<br>Tags the 2nd contact shown in the current list with a Computer Science degree and CS2040S module.

    * **`event`** `1 name/Lunch appointment info/at Hai Di Lao d/2023-05-15 t/13:00`<br>Creates an event named `Lunch appointment` and associate it with the 1st contact.

    * **`find`**`n/fred`<br>Find any person whose name contains 'fred'.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are mandatory parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Kim Lai`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [edu/EDUCATION]` can be used as `n/Kim Lai edu/computer science` or as `n/Kim Lai`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[m/MODULES]…​` can be used as ` ` (i.e. 0 times), `m/CS2040S`, `m/CS2030S m/CS2100` etc.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12345678 p/87654321`, only `p/87654321` will be taken.
  
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If an `INDEX` is used, it **must be a positive integer** (i.e. 1, 2, 3…​)

* All commands are case-sensitive.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/help_message.png)

Format: `help`

### Listing all persons and upcoming events: `list`
Shows a list of all persons and upcoming events in the address book.

Format: `list`

* All existing persons and upcoming events are automatically rendered when the application is launched.
* The different tags are listed as follows: yellow for education, blue for modules, orange for CCAs and red for internships.
* Personal details are listed in the following order: Phone number, Address, Email.
* Upcoming events are automatically sorted in chronological order.

### Adding a person: `add`
Adds a person to NUSocials. 

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

Example:
* `add n/Kim Lai p/12345678 e/kimlai222@example.com a/KL street, block 123, #01-01`
Adds a person with the following fields:
  - Name: Kim Lai
  - Phone Number: 12345678
  - Email: kimlai222@example.com
  - Address: KL street, block 123, #01-01

### Tagging a person: `tag`
Tags additional information to an existing contact.

Format: `tag INDEX [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`

* Tags the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list. 
* At least one of the prefixes must be provided.
* If a prefix is used, the input after must not be blank.
* Input tag values will be accumulated to the existing tags.

Example:
* `tag 1 i/abc-company m/CS2100 m/CS2030S`<br>
Tags the internship company and 2 modules to the 1st person in the currently shown contact list.

### Removing specific tags from person: `removetag`
Removes the specific tags of an existing contact.

Format: `removetag INDEX [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`

* Removes the tags from the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list. 
* At least one of the prefixes must be provided.
* If a prefix is used, the input after must not be blank.
* All arguments for tags provided must be an exact match to existing tags.

Example:
* `removetag 1 i/abc-company m/CS2100 m/CS2030S`<br>
Removes the internship company and 2 modules tags from the 1st person in the currently shown contact list.

### Editing a person : `edit`

Edits an existing person's details in NUSocials.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`

* Edits the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Example:
* `edit 1 p/91234567 e/KL123@example.com`<br>Edits the phone number and email address of the 1st person to `91234567` and `KL123@example.com` respectively.

### Adding an event: `event`
Adds an event into NUSocials.

Format: `event INDEX…​ name/EVENT NAME info/EVENT DETAILS d/DATE t/TIME`

* Tags the participating persons to the events based on the specified `INDEX…​`. 
* The index refers to the index number shown in the displayed person list.
* All fields must be provided.
* Argument provided for `DATE` has to be in the format of `yyyy-MM-dd`.
* Argument provided for `TIME` has to be in the format of `HH:mm`.
* Arguments for `DATE` and `TIME` has to be valid (i.e Date and Time specified must be after the current date and time)
* `EVENT NAME` has a limit of 100 characters.
* `EVENT DETAILS` has a limit of 300 characters.

Example:
* `event 1 2 name/lunch appointment info/Having lunch at Hai Di Lao VivoCity d/2022-10-20 t/12:15`<br>
Creates the Event and adds into NUSocials.

### Cancelling an event : `cancelevent`

Deletes the specified event from the address book.

Format: `cancelevent INDEX`

* Deletes the event at the specified `INDEX`.
* The index refers to the index number shown in the displayed event list.

Examples:
* `list` followed by `cancelevent 2` deletes the 2nd event in the currently shown event list.

Alternate Format: `cancelevent INDEX…​`

* Deletes multiple events at the specified `INDEX`'s.
* The index refers to the index number shown in the displayed event list.
* Each index **must be separated by a whitespace**

Examples:
* `list` followed by `cancelevent 2 5 7` deletes the 2nd, 5th and 7th events in the currently shown event list.

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the currently shown contact list.
* `find n/Betsy` followed by `delete 1` deletes the 1st person from the resulting list of the `find` command.

Alternate Format: `delete INDEX…​`

* Deletes multiple persons at the specified `INDEX`'s.
* The index refers to the index number shown in the displayed person list.
* Each index **must be separated by a whitespace**

Examples:
* `list` followed by `delete 2 5 7` deletes the 2nd, 5th and 7th person in the currently shown list.

### Locating persons: `find`

Finds persons that match any of the given fields and tags.

Format: `find [n/NAME]…​ [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`

* At least one of the optional fields must be provided.
* The search is case-insensitive. e.g `hans` will match `Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one of the fields or tags will be returned (i.e. `OR` search).
* All arguments for tags provided must be an exact match to existing tags.

Examples:
* `find m/cs2030s m/cs2040s` returns anyone tagged with either `cs2030s` or `cs2040s` or both
* `find n/Hans m/cs2100` will return `Hans` and `Bo Yang` (i.e. Bo Yang is tagged with cs2100)
* `find i/Shopee m/cs2040s m/cs2030s` returns `Alex Yeoh` (i.e Alex Yeoh is tagged with Shopee), `David Li` (i.e. David Li is tagged with cs2040s, cs2030s)<br>
  ![result for 'find i/Shopee m/cs2040s cs2030s'](images/findShopeeCS2040sCS2030sResult.png)

### Locating specific persons: `find -s`

Finds persons that match all given fields and tags.

Format: `find -s [n/NAME]…​ [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`

* At least one of the optional fields must be provided.
* The search is case-insensitive. e.g `hans` will match `Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`
* Only persons matching all fields and tags will be returned (i.e. `AND` search).
* All arguments for tags provided must be an exact match to existing tags.

Examples:
* `find -s n/Bo Yang m/cs2040s` will return `Bo Yang` (i.e. Bo Yang is tagged with cs2040s)
* `find -s i/Shopee m/cs2040s m/cs2030s` returns `Alex Yeoh` (i.e. Alex Yeoh is tagged with cs2040s, cs2030s and Shopee)<br>
  ![result for 'find -s i/Shopee m/cs2040s cs2030s'](images/find-sShopeeCS2040sCS2030s.png)

### Locating an event: `find -e`

Finds an event that matches any of the given details

Format: `find -e [name/EVENT NAME]…​ [info/INFORMATION]…​ [part/PARTICPANT]…​ [dt/DATE AND TIME]…​`

* At least one of the optional fields must be provided.
* The search is case-insensitive. e.g `lunch` will match `Lunch`
* Only full words will be matched e.g. `lun` will not match `lunch`
* Events matching at least one of the field will be returned (i.e. `OR` search).

Examples:
* `find -e name/lunch part/alex` returns all events with `lunch` in its name and all events involving Alex<br>

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
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665`
**Tag** | `tag INDEX [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`<br> e.g.,`tag 1 m/CS2105 m/CS2106`
**Removetag** | `removetag INDEX [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​` <br> e.g.,`removetag 1 c/Bouldering m/CS2105 m/CS2106`
**Event** | `event INDEX…​ name/EVENT NAME info/INFORMATION d/DATE t/TIME` <br> e.g., `event 1 name/Dinner Date info/Having Dinner at Bread Street Kitchen by Gordon Ramsay d/2022-12-20 t/20:15`
**Cancelevent** | `cancelevent INDEX…​` <br> e.g.,`cancelevent 1 2 3`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3` <br> `delete INDEX…​INDEX` <br> e.g. `delete 1 3 5`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`<br> e.g.,`edit 2 n/Fred e/fred111@example.com`
**Find** | `find [n/NAME]…​ [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`<br> e.g., `find n/john edu/computer science`
**Find -s** | `find -s [n/NAME]…​ [i/INTERNSHIP]…​ [m/MODULES]…​ [c/CCA]…​ [edu/EDUCATION]…​`<br> e.g., `find -s n/john i/bytedance edu/computer science`
**Find -e** | `find -e [name/EVENT NAME]…​ [info/INFORMATION]…​ [part/PARTICIPANT]…​ [dt/DATE AND TIME]…​`<br> e.g., `find -e name/Dinner info/Candice's birthday dt/2022-05-12 19:30`
**List** | `list`
**Help** | `help`
