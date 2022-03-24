---
layout: page
title: User Guide
---

MyGM is a **desktop app for high school basketball team trainers to manage players’ contacts and data, optimized for use
via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, MyGM can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}
  * Quick Start
  * Features
    * Adding a player/ team/ lineup/ schedule: add
    * Deleting a player/ team/ lineup/ schedule: delete
    * Filtering players by position: filter
    * Marking the attendance of player: mark
    * Unmarking the attendance of players: unmark
    * Tagging players by their position: tag
    * Viewing the summary: view
    * Finding a lineup or player: find
    * Putting a player to a team/ lineup: put
    * Updating a player/ team/ lineup/ schedule: update
    * Saving the data: save
    * Loading data from user-specified file: load
    * Clearing all data: clear
  * FAQ
  * Command Summary

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `MyGM.jar` from [here](https://github.com/AY2122S2-CS2103-F09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MyGM.

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

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
* Items in square brackets are optional.
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/PG` or as `n/John Doe`.
* Items with …​ after them can be used multiple times including zero times.
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/SF`, `t/PF t/C` etc.
* Parameters can be in any order.
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER, p/PHONE_NUMBER n/NAME` is also acceptable.
* If a parameter is expected only once in the command but you have specified it multiple times, only the last occurrence of the parameter will be taken.
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
  e.g. if the command specifies `help 123`, it will be interpreted as help.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a player/ lineup/ schedule: `add`

Adds a player/ lineup/ schedule to MyGM.

**To add a player:**
Format: `add P/ n/NAME j/JERSY_NUMBER w/WEIGHT h/HEIGHT p/PHONE_NUMBER e/EMAIL_ADDRESS`

* Adds a player with the specified attributes to the player list in MyGM.

Examples:
* `add P/ n/John Doe a/17 j/3 w/69 h/188 p/98765432 e/johnd@example.com` Adds a player by the name of John Doe, who is age of 17, jersey number of 3, weight of 69kg, height of 188cm, handphone number of 98765432
and email of johnd@example.com to the player list.`

**To add a lineup:**
Format: `add L/ n/LINEUP_NAME [P/PLAYER]…​`
* Adds a lineup with the specified `LINEUP_NAME` inside MyGM.
* If `n/LINEUP_NAME` and `P/PLAYER` are specified, a lineup with the specified `LINEUP_NAME` with the specified `PLAYER` added to this lineup.
* Multiple `PLAYER` can be specified but it will be **capped at 5**.
* Tthe `PLAYER` specified **must already exist** in MyGM.

Examples:
* `add L/ n/starting five` adds a lineup by the name of `starting five` inside MyGM.
* `add L/ n/starting five P/James P/Curry P/Harden P/Durant P/Embiid` Players `James`, `Curry`, `Harden`, `Durant` and `Embiid` are also added to the lineup `starting five`.

**To add a schedule:**
Format: `add S/ r/DESCRIPTION d/DATETIME`
* Adds a schedule with the description of `DESCRIPTION` and the date time of `DATETIME` inside MyGM.
* `DATETIME` must be in a date time format.

Examples:
* `add S/ d/competition d/22/02/2022 0900` adds a schedule with the description of `competition` that is held on `22/02/2022 0900`.

### deleting a player/ lineup/ schedule :  `delete`
deletes a player/ lineup/ schedule from MyGM

**To delete a player:**
Format: `delete P/PLAYER [L/LINEUP]`
* Deletes the player from the player list.
* Id `L/LINEUP` is specified, delete the player from the lineup.

Example:
*`delete P/James Soften` will delete player `James Soften`.

**To delete a lineup:**
Format: `delete L/LINEUP`
* Delete the lineup.

Example:
* `delete L/starting five` will delete the lineup `starting five` from MyGM.

**To delete a schedule:**
Format: `delete T/ i/INDEX_SCHEDULE`
* Delete the i-th schedule of MyGM.
  Example:
  *`delete i/1` will delete schedule `1` from MyGM.

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

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

### Putting a player to a lineup: `put`

Puts a player to a specific team or to a specific lineup.

**To put a player to a lineup:** <br>
Format: `put P/PLAYER L/LINEUP`
* Adds a player to a specific lineup.
* Displays error if either the specified PLAYER or LINEUP does not exist.
* Each player can join multiple lineups.

* Example:
* `put P/John Doe L/starting five` Puts John Doe into the lineup named starting five

### Mark the attendance of players: `mark`

Mark the attendance of players in a team for a specific training date or competition.

Format: `mark i/INDEX_SCHEDULE P/PLAYER [P/MORE_PLAYERS]`
* Mark all players specified PLAYER_NAME as present for the event with index number INDEX_SCHEDULE on the schedule.
* The index must be a positive integer 1, 2, 3…

Example:
* `mark i/1 P/Budaha P/john` Marks Budaha Arda and John Doe as present for the event with schedule index 1.

### Mark a player as absent: `unmark`

Mark the attendance of players in a team as absent for a specific training date or competition.

Format: `unmark i/INDEX_SCHEDULE P/PLAYER [P/MORE_PLAYERS]​`
* Mark all players with the specified PLAYER_NAME as absent for the event with index number INDEX_SCHEDULE on the schedule.
* The index **must be a positive integer 1, 2, 3.**

Example:
* `unmark i/1 P/Budaha P/john` marks Budaha Arda and John Doe as absent for the event with schedule index 1.

### Viewing the summary: `view`

Views the summarised information of lineup/ player/ schedule.

**To view a lineup:**<br>

Format: `view L/[LINEUP]`
* The summarised information of a lineup only include the name, the position of the player and the number of slots (out of 5) filled.
* Displays the summarised information of the specified `LINEUP` in the specified ``.
  - All the players in the specified `LINEUP` will be displayed.
* If no `LINEUP` is provided, the summarised information of all lineups in the specified `` will be displayed.
  - All the `LINEUP` in the specified team will be displayed.
* The specified `LINEUP` and specified `` must be **valid** to be viewed.

Examples:
* `view L/Starting Five` Displays the summarised information of lineup `Start Five`
* `view L/` Displays the summarised information of all lineups

**To view a player:**<br>

Format: `view P/[PLAYER]`
* The summarised information of a player includes all its attributes.
* Displays the summarised information of the specified `PLAYER`.
  - Only the specified player will be displayed.
* If no `PLAYER` is provided, the summarised information of all existing players in the system will be displayed.

Examples:
* `view P/Kelvin Darent` Displays the information of `Kelvin Darent`.
* `view P/` Displays all players in the system.

**To view a schedule:**<br>

Format: `view i/[INDEX]`
* Displays the schedule of the specified `` numbered with the specified `INDEX`.
* If no `INDEX` is provided, the list of all schedules of the `` will be displayed.

Examples:
* `view i/1` Displays the information on `Lakaka`'s 1st schedule.

### Finding a lineup or player: `find`

Finds the specified lineup or player.

####To find a lineup:
Format: `find L/LINEUP`
* Finds the specified `LINEUP` and displays its information.

Examples:
* `find L/STARTING FIVE` If the user forgets which team `STARTING FIVE` belongs to, they can use this command to find it.

####To find a player:
Format: `find P/PLAYER`
* Finds the specified `PLAYER` and displays his/her information.

Examples:
* `find P/Brown James` If the user forgets which team and lineup `Brown James` belongs to, they can use this command to find him.

### Filtering players by position: `filter`

Filter all players with the specified tag(s).

Format: `filter [T/] t/TAG [t/TAGS]`
* Display all the players with the specific tag(s) from a particular team.
* If T/ is not specified, players with these tags from all teams will be displayed.

Example:
* `filter T/Sandama t/PF` Displays all the players with the tag PF in the team Sandama.


### Edit a player/ lineup/ schedule information : `edit`

Update the details of a player, team, lineup or schedule

**To edit a player:**

Format: `edit P/NAME [n/NAME] [p/PHONE_NUMBER] [a/AGE] [w/WEIGHT] [h/HEIGHT] [j/JERSY_NUMBER]`

* edit the details of a player from the player list
* If any fields are specified, it will change accordingly
* Multiple fields can be changed at once

Example:
* `edit P/James Soften p/8888888` will change the phone number of player James Soften to 88888888

**To edit a lineup:**

Format: `edit L/LINEUP n/NEW_LINEUP_NAME`

* Edit the lineup name of lineup LINEUP of team _NAME to NEW_LINEUP_NAME

Example:
* `edit T/Lakers L/Starting5 n/Worst5` will change name of the lineup Starting5 of team Lakers to Worst5

**To edit a schedule:**

Format: `edit i/INDEX_SCHEDULE [n/DESCRIPTION] [d/DATETIME]`

* Edit the details of the i-th schedule of a team
* If any fields are specified, it will be changed accordingly

Example:
* `edit S/ i/1 n/Training` will change the description of first event in the schedule for team Wizards to Training

### Clearing all entries : `clear`

Clears all data from MyGM.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MyGM data is saved in the hard disk (in a default file) automatically after any command that changes the data. There is no need to save manually. But users can still call the save function to export the current data.

Format: `save PATH`

* Data will be exported to the specified PATH.
* This does not change the default save file path

Example:
* `save ./Documents/data.txt` will save the data in ./Documents/data.txt

### Loading data from user-specified file: `load`

Loads data from a user-specified file to the system.

Format: `load PATH`

* Load all the data from the path specified by the user.
* Display error if the path given is invalid.

Example:
* `load ./Documents/data.txt`


### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                                                                                                                           |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add P/ n/NAME j/JERSY_NUMBER w/WEIGHT h/HEIGHT p/PHONE_NUMBER e/EMAIL_ADDRESS`<br>e.g. `add P/ n/John Doe j/3 w/69 h/188 p/98765432 e/johnd@example.com`<br>`add L/ n/LINEUP_NAME [P/PLAYER]…​`<br>e.g. `add L/ n/starting five  P/James P/Curry P/Harden P/Durant P/Embiid`<br>`add S/ n/NAME r/DESCRIPTION d/DATETIME`<br>e.g. `add S/ r/competition d/22/02/2022 0900` |
| **Delete** | `xxx`<br>e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                         |
| **View**   | `view L/[LINEUP]`<br>e.g. `view L/starting five`<br>`view P/[PLAYER]`<br>e.g. `view P/Blue Blue`<br>`view S/ i/[INDEX]`<br>e.g. `view S/ i/1`                                                                                                                                                                                                                              |
| **Put**    | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                        |
| **Mark**   | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                        |
| **Unmark** | `unmark i/INDEX_SCHEDULE P/PLAYER [P/PLAYER]`<br> e.g. `unmark i/1 P/John Doe P/James P/Durant`                                                                                                                                                                                                                                                                            |
| **Filter** | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                        |
| **Edit**   | `edit P/PLAYER [n/NAME] [p/PHONE_NUMBER] [w/WEIGHT] [h/HEIGHT] [j/JERSY_NUMBER]`<br> e.g. `edit P/John Doe a/22`<br>`edit L/LINEUP_NAME n/NEW_LINEUP_NAME`<br> e.g. `edit L/HAHA n/HEIHEI`<br>`edit i/INDEX_SCHEDULE [n/DESCRIPTION] [d/DATETIME]`<br> e.g. `add S/ i/1 n/competition d/22/02/2022 0900`                                                                   |
| **Find**   | `find P/PLAYER`<br>e.g. `find P/Wu Lala`<br>`find L/LINEUP`<br>e.g. `find L/Oo la la`                                                                                                                                                                                                                                                                                      |
| **Save**   | `save PATH`<br> e.g.`save details/team.txt`                                                                                                                                                                                                                                                                                                                                |
| **Load**   | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                        |
| **Clear**  | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                        |
| **Help**   | `help`                                                                                                                                                                                                                                                                                                                                                                     |
