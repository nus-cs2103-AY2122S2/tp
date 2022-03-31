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
    * Changing the theme of the UI
    * Saving the data: save
    * Loading data from user-specified file: load
    * Clearing all data: clear
  * FAQ
  * Command Summary

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `MyGM.jar` from [here](https://github.com/AY2122S2-CS2103-F09-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your MyGM.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](images/Ui.png)
The GUI is split into two categories: Players and Schedules. <br>
![Ui_Players](images/UiPlayers.png) <br>

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`add`**`P/ n/John Doe p/98765432 e/johnd@example.com h/183 w/70 j/24 t/PG` : Adds a player named `John Doe` to MyGM.
   
   * **`add`**`S/ n/training r/shooting training d/01/01/2023 1800` : Adds a schedule called `training` to MyGM.
   
   * **`delete`**`P/John Doe` : Deletes John Doe from MyGM.

   * **`clear`** : Deletes all players, lineup and schedule.

   * **`exit`** : Exits the app.

6. Refer to the Features below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `n/NAME`, `NAME` is a parameter which can be used as `n/John Doe`.
* Items in square brackets are optional.
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/PG` or as `n/John Doe`.
* Items with …​ after them can be used multiple times including zero times.
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/SF`, `t/PF t/C` etc.
* Commands are case sensitive. `Add` is considered as invalid commands, the correct command should be in lower case.
* Parameters are case sensitive. `John Doe` and `joHN dOE` are considered as different person.
* Parameters can be in any order.
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER, p/PHONE_NUMBER n/NAME` is also acceptable.
* If a parameter is expected only once in the command but you have specified it multiple times, only the last occurrence of the parameter will be taken.
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.
  e.g. if the command specifies `help 123`, it will be interpreted as help.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a player/ lineup/ schedule: `add`

Adds a player/ lineup/ schedule to MyGM.

**To add a player:**
Format: `add P/ n/NAME j/JERSY_NUMBER w/WEIGHT h/HEIGHT p/PHONE_NUMBER e/EMAIL_ADDRESS [t/TAG]…​`

* Adds a player with the specified attributes to the player list in MyGM.
* The first character of every word in `NAME` should be capitalized. For example:`John Doe`

Examples:
* `add P/ n/John Doe j/3 w/69 h/188 p/98765432 e/johnd@example.com t/PG` Adds a player by the name of John Doe, jersey number of 3, position of PG, weight of 69kg, height of 188cm, handphone number of 98765432
and email of johnd@example.com to the player list.`

![AddPlayer_SS](images/AddPlayer_SS.png)

**To add a lineup:**
Format: `add L/ n/LINEUP_NAME`
* Adds a lineup with the specified `LINEUP_NAME` inside MyGM.

Examples:
* `add L/ n/starting five` adds a lineup by the name of `starting five` inside MyGM.

![AddLineup_SS](images/AddLineup_SS.png)

**To add a schedule:**
Format: `add S/ n/SCHEDULE_NAME r/DESCRIPTION d/DATETIME`
* Adds a schedule with the schedule name `SCHEDULE_NAME` description of `DESCRIPTION` and the date time of `DATETIME` inside MyGM.
* `DATETIME` must be in a dd/mm/yyyy hhmm format.

Examples:
* `add S/ n/competition r/first game of national competition d/20/04/2024 2200` adds a schedule with name `competition`, description of `first game of national competition` that is held on `20/04/2024 2200`.

![AddSchedule_SS](images/AddSchedule_SS.png)

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
* `delete L/Starting 5` will delete the lineup `Starting 5` from MyGM.

**To delete a schedule:**
Format: `delete T/ i/INDEX_SCHEDULE`
* Delete the i-th schedule of MyGM.
  Example:
  *`delete i/1` will delete schedule `1` from MyGM.

### Putting a player to a lineup: `put`

Puts a player to a specific team or to a specific lineup.

Format: `put P/PLAYER L/LINEUP`
* Adds a player to a specific lineup.
* Displays error if either the specified PLAYER or LINEUP does not exist.
* Each player can join multiple lineups.

* Example:
* `put P/John Doe L/starting five` Puts John Doe into the lineup named starting five

* ![Put_SS](images/Put_SS.png)

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

**To view schedules:**<br>

Format: `view S/[KEYWORDS]`
* Displays the schedule containing `KEYWORDS`, cases ignored.
* If no `KEYWORDS` is provided, the list of all active schedules which happen at future dates will be displayed.

Format: `view S/ a/all`
* Displays all schedules added.
* If no `all` is provided, error message will be displayed.

Format: `view S/ a/archive`
* Displays archived schedules only.
* If no `archive` is provided, error message will be displayed.

Examples:
* `view i/1` Displays the information on `Lakaka`'s 1st schedule.

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

* Edit the details of a player from the player list
* If any fields are specified, it will change accordingly
* Multiple fields can be changed at once

Example:
* `edit P/James Soften p/8888888` will change the phone number of player James Soften to 88888888

**To edit a lineup:**

Format: `edit L/LINEUP n/NEW_LINEUP_NAME`

* Edit the lineup name of lineup to a new lineup name

Example:
* `edit L/Starting5 n/Worst5` will change name of the lineup Starting5 of team Lakers to Worst5

**To edit a schedule:**

Format: `edit S/INDEX_SCHEDULE [n/NEW_NAME] [r/NEW_DESC] [d/NEW_DATE]`

* Edit the details of the i-th schedule of a team
* If any fields are specified, it will be changed accordingly

Example:
* `edit S/1 n/finals r/nba finals d/06/06/2022 2100` will edits the first schedule

### Clearing all entries : `clear`

Clears all data from MyGM.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Changing the theme of the UI: `theme`

Changes to either light mode or dark mode. MyGM is defaulted to dark mode on start up.

Format: `theme T/THEME`

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

MyGM data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

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
| **Add**    | `add P/ n/NAME j/JERSY_NUMBER w/WEIGHT h/HEIGHT p/PHONE_NUMBER e/EMAIL_ADDRESS [t/TAG]…​`<br>e.g. `add P/ n/John Doe j/3 w/69 h/188 p/98765432 e/johnd@example.com t/PG`<br>`add L/ n/LINEUP_NAME`<br>e.g. `add L/ n/starting five`<br>`add S/ n/SCHEDULE_NAME r/DESCRIPTION d/DATETIME`<br>e.g. `add S/ n/competition r/first game of national competition d/20/04/2024 2200` |
| **Delete** | `xxx`<br>e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                         |
| **View**   | `view L/[LINEUP]`<br>e.g. `view L/starting five`<br>`view P/[PLAYER]`<br>e.g. `view P/Blue Blue`<br>`view S/ i/[INDEX]`<br>e.g. `view S/ i/1`                                                                                                                                                                                                                              |
| **Put**    | `put P/PLAYER L/LINEUP`<br> e.g.`put P/John Doe L/starting five`                                                                                                                                                                                                                                                                                                                                                        |
| **Mark**   | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                        |
| **Unmark** | `unmark i/INDEX_SCHEDULE P/PLAYER [P/PLAYER]`<br> e.g. `unmark i/1 P/John Doe P/James P/Durant`                                                                                                                                                                                                                                                                            |
| **Filter** | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                        |
| **Edit**   | `edit P/PLAYER [n/NAME] [p/PHONE_NUMBER] [w/WEIGHT] [h/HEIGHT] [j/JERSY_NUMBER]`<br> e.g. `edit P/John Doe a/22`<br>`edit L/LINEUP_NAME n/NEW_LINEUP_NAME`<br> e.g. `edit L/HAHA n/HEIHEI`<br>`edit i/INDEX_SCHEDULE [n/DESCRIPTION] [d/DATETIME]`<br> e.g. `add S/ i/1 n/competition d/22/02/2022 0900`                                                                   |
| **Find**   | `find P/PLAYER`<br>e.g. `find P/Wu Lala`<br>`find L/LINEUP`<br>e.g. `find L/Oo la la`                                                                                                                                                                                                                                                                                      |
| **Theme**  | `theme T/THEME`<br> e.g.`theme T/light`                                                                                                                                                                                                                                                                                                                                    |
| **Save**   | `save PATH`<br> e.g.`save details/team.txt`                                                                                                                                                                                                                                                                                                                                |
| **Load**   | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                        |
| **Clear**  | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                        |
| **Help**   | `help`                                                                                                                                                                                                                                                                                                                                                                     |
