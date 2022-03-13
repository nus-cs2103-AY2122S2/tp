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

1. Download the latest `MyGM.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

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


### Adding a player/ team/ lineup/ schedule: `add`

Adds a player/ team/ lineup/ schedule to MyGM.

**To add a player:**  
Format: `add P/ n/NAME a/AGE j/JERSY_NUMBER w/WEIGHT h/HEIGHT p/PHONE_NUMBER e/EMAIL_ADDRESS`

* Adds a player with the specified attributes to the player list in MyGM.

Examples:
* `add P/ n/John Doe a/17 j/3 w/69 h/188 p/98765432 e/johnd@example.com` Adds a player by the name of John Doe, 
who is age of 17, jersey number of 3, weight of 69kg, height of 188cm, handphone number of 98765432
and email of johnd@example.com to the player list.`

**To add a team:**  
Format: `add T/ n/TEAM_NAME [P/PLAYER]…​`
* Adds a team with the specified team name in MyGM.
* If `n/TEAM_NAME` and `P/PLAYER` are specified, a team with the specified `TEAM_NAME` and the specified `PLAYER`
  added into team `TEAM_NAME`.
* Multiple `PLAYER` can be specified.
* The `PLAYER` specified **must already exist** in MyGM.

Examples:
* `add T/ n/Huskers` Creates a team by the name of `Huskers` only.
* `add T/ n/Huskers  P/John Doe P/James` Doe Creates a team by the name of `Huskers` with players `John Doe` and `James Doe`
added to team `Huskers`.

**To add a lineup:**  
Format: `add L/ n/LINEUP_NAME T/TEAM [P/PLAYER]…​`
* Adds a lineup with the specified `LINEUP_NAME` inside `TEAM`.
* If `n/LINEUP_NAME`, `T/TEAM` and `P/PLAYER` are specified, a lineup with the specified `LINEUP_NAME` 
with the specified `PLAYER` added to this lineup will also be added to `TEAM`.
* Multiple `PLAYER` can be specified but it will be **capped at 5**.
* Both the team `TEAM` and the `PLAYER` specified **must already exist** in MyGM.

Examples:
* `add L/ n/starting five T/Balluba` adds a lineup by the name of `starting five` inside team `Balluba`.
* `add L/ n/starting five T/Balluba P/James P/Curry P/Harden P/Durant P/Embiid` adds a lineup by the name of `starting five`
inside team `Balluba`. Players `James`, `Curry`, `Harden`, `Durant` and `Embiid` are also added to the lineup `starting five`.

**To add a schedule:**  
Format: `add S/ T/TEAM n/DESCRIPTION d/DATETIME`
* Adds a schedule with the description of `DESCRIPTION` and the date time of `DATETIME` inside the specified team `TEAM`.
* `TEAM` specified **must already exists** in MyGM
* `DATETIME` must be in a date time format.

Examples:
* `add S/ n/Yabuda d/competition d/22/02/2022 0900` adds a schedule with the description of `competition` that is held on `22/02/2022 0900` inside team `Yabuda`.

### deleting a player/ team/ lineup/ schedule :  `delete`
deletes a player/ team/ lineup/ schedule from MyGM

**To delete a player:**
Format: `delete P/PLAYER [T/TEAM] [L/LINEUP]`
* Deletes the player from the player list.
* If only `T/TEAM` is specified, delete the player from the team.
* If `T/TEAM` and `L/LINEUP` are specified, delete the player from the lineup.

Example:
*`delete P/James Soften T/Netts` will delete player `James Soften` from team `Netts`.

**To delete a team:**
Format: `delete T/TEAM`
* Deletes the team.

Example:
*`delete T/Lokers` will delete team `Lokers`.

**To delete a lineup:**
Format: `delete T/TEAM L/LINEUP`
* Delete the lineup in the team.

Example:
* `delete T/Lokers L/starting five` will delete the lineup `starting five` from team `Lokers`.

**To delete a schedule:**
Format: `delete T/TEAM i/INDEX_SCHEDULE`
* Delete the i-th schedule of the team.
  Example:
  *`delete T/Lokers i/1` will delete schedule `1` from team `Lokers`.

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

### Putting a player to a team/ lineup: `put`

Puts a player to a specific team or to a specific lineup.

**To put a player into a team:** <br>
Format: `put P/PLAYER T/TEAM`
* Puts a player to a specific team for managing purposes.
* Displays error if either the input PLAYER or TEAM does not exist.
* Each player can only belong to one team at a time. Displays errors if the input player already has a team.

Example:
* `put P/John Doe T/Hulluka` Puts Player John Doe into the team named Hulluka

**To put a player to a lineup:** <br>
Format: `put P/PLAYER L/LINEUP`
* Adds a player to a specific lineup within his team for training purposes.
* Displays error if either the specified PLAYER or LINEUP does not exist.
* Displays error if the input player does not belong to any team.
* Each player can join multiple lineups within the team he belongs to.

* Example:
* `put P/John Doe L/starting five` Puts John Doe into the lineup named starting five

### Mark the attendance of players: `mark`

Mark the attendance of players in a team for a specific training date or competition.

Format: `mark T/TEAM i/INDEX_SCHEDULE P/PLAYER [P/MORE_PLAYERS]`
* Mark all players specified PLAYER_NAME as present for the event with index number INDEX_SCHEDULE on the schedule.
* The index must be a positive integer 1, 2, 3…

Example:
* `mark T/Jiayous i/1 P/Budaha P/john` Marks Budaha Arda and John Doe as present for the event with schedule index 1.

### Mark a player as absent: `unmark`

Mark the attendance of players in a team as absent for a specific training date or competition.

Format: `unmark T/TEAM i/INDEX_SCHEDULE P/PLAYER [P/MORE_PLAYERS]​`
* Mark all players with the specified PLAYER_NAME as absent for the event with index number INDEX_SCHEDULE on the schedule.
* The index **must be a positive integer 1, 2, 3.**

Example:
* `unmark T/Jiayous i/1 P/Budaha P/john` marks Budaha Arda and John Doe as absent for the event with schedule index 1.

### Tagging a player by their position: `tag`

Tag a player by their position. Position includes: Point Guard (PG), Shooting Guard(SG), Small Forward(SF), 
Power Forward(PF) and Centre(C).

Format: `tag P/PLAYER t/TAG…​`
* Tags the specified `PLAYER` with the position of `TAG`
* The specified `PLAYER` can be tagged with a multiple `TAG`
* The `PLAYER` specified **must already exist** in MyGM.

Examples:
* `tag P/LBJ t/SF tags` the player `LBJ` with the position of `SF` only.
* `tag P/LBJ t/PG t/SG t/PF` tags the player `LBJ` with the position of `PG`, `SF` and `PF`.

### Viewing the summary: `view`

Views the summarised information of a team, lineup or player.

**To view a team:**<br>

Format: `view T/[TEAM]`
* Displays the aggregated data of the team of the specified `TEAM`. The summary contains information such as all players in the team, number of players in each position.
* If no `TEAM` is provided, the summary of all existing teams will be provided.
* The specified `TEAM` must be **valid** to be viewed.

Examples:
* `view T/Lakaka` Displays the team summary of `Lakaka`.
* `view T/` Displays all existing teams.

**To view a lineup:**<br>

Format: `view T/TEAM L/[LINEUP]`
* Displays the summary of the specified `LINEUP` in the specified `TEAM`.
* If no `LINEUP` is provided, the summary of all lineups in the specified `TEAM` will be displayed.
* The specified `LINEUP` must be valid to be viewed (i.e. it exists in the specified `TEAM`).

Examples:
* `view T/Lakaka L/Starting Five` Displays the lineup summary of `Start Five` lineup in `Lakaka`.
* `view T/Lakaka L/` Displays the lineup summary of all lineups in `Lakaka`.

**To view a player:**<br>

Format: `view P/[PLAYER]`
* Displays the information of the specified `PLAYER`.
* If no `PLAYER` is provided, the information of all existing players in the system will be displayed.

Examples:
* `view P/Kelvin Darent` Displays the information of `Kelvin Darent`.
* `view P/` Displays all players in the system.

**To view a schedule:**<br>

Format: `view T/TEAM i/[INDEX]`
* Displays the schedule of the specified `TEAM` numbered with the specified `INDEX`.
* If no `INDEX` is provided, the list of all schedules of the `TEAM` will be displayed.

Examples:
* `view T/Lakaka i/1` Displays the information on `Lakaka`'s 1st schedule.

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



### Deleting a person : `delete`

Delete the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Filtering players by position: `filter`

Filter all players with the specified tag(s).

Format: `filter [T/TEAM] t/TAG [t/TAGS]`
* Display all the players with the specific tag(s) from a particular team.
* If T/TEAM is not specified, players with these tags from all teams will be displayed.

Example:
* `filter T/Sandama t/PF` Displays all the players with the tag PF in the team Sandama.


### Edit a player/team/lineup/schedule information : `edit`

Update the details of a player, team, lineup or schedule

**To edit a player:**

Format: `edit P/NAME [n/NAME] [p/PHONE_NUMBER] [a/AGE] [w/WEIGHT] [h/HEIGHT] [j/JERSY_NUMBER]`

* edit the details of a player from the player list
* If any fields are specified, it will change accordingly
* Multiple fields can be changed at once

Example:
* `edit P/James Soften p/8888888` will change the phone number of player James Soften to 88888888

**To edit a team:**

Format: `update T/TEAM_NAME n/NEW_TEAM_NAME`

* edit the team name of team TEAM_NAME to NEW_TEAM_NAME

Example:
* `edit T/Lokers n/Lakers` will change team Lokers to team Lakers

**To edit a lineup:**

Format: `edit T/TEAM_NAME L/LINEUP n/NEW_LINEUP_NAME`

* Edit the lineup name of lineup LINEUP of team TEAM_NAME to NEW_LINEUP_NAME

Example:
* `edit T/Lakers L/Starting5 n/Worst5` will change name of the lineup Starting5 of team Lakers to Worst5

**To edit a schedule:**

Format: `edit T/TEAM i/INDEX_SCHEDULE [n/DESCRIPTION] [d/DATETIME]`

* Edit the details of the i-th schedule of a team
* If any fields are specified, it will be changed accordingly

Example:
* `edit T/Wizards i/1 n/Training` will change the description of first event in the schedule for team Wizards to Training

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

| Action     | Format, Examples                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add P/ n/NAME a/AGE j/JERSY_NUMBER w/WEIGHT h/HEIGHT p/PHONE_NUMBER e/EMAIL_ADDRESS`<br>e.g. `add P/ n/John Doe a/17 j/3 w/69 h/188 p/98765432 e/johnd@example.com`<br> `add T/ n/TEAM_NAME [P/PLAYER]…​`<br> e.g. `add T/ n/Huskers  P/John Doe  P/James Doe`<br>`add L/ n/LINEUP_NAME T/TEAM [P/PLAYER]…​`<br>e.g. `add L/ n/starting five T/Balluba P/James P/Curry P/Harden P/Durant P/Embiid`<br>`add S/ T/TEAM n/DESCRIPTION d/DATETIME`<br>e.g. `add S/ T/Yabuda n/competition d/22/02/2022 0900` |
| **Delete** | `xxx`<br>e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| **View**   | `view T/[TEAM]`<br>e.g. `view T/Huskers`<br>`view T/TEAM L/[LINEUP]`<br>e.g. `view T/Huskers L/starting five`<br>`view P/[PLAYER]`<br>e.g. `view P/Blue Blue`<br>`view T/TEAM i/[INDEX]`<br>e.g. `view T/Lakaka i/1`                                                                                                                                                                                                                                                                                      |
| **Tag**    | `tag P/PLAYER t/TAG…​`<br>e.g. `tag P/LBJ t/SF`                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| **Put**    | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| **Mark**   | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| **Unmark** | `unmark T/TEAM i/INDEX_SCHEDULE P/PLAYER [P/PLAYER]`<br> e.g. `unmark T/Huskers i/1 P/John Doe P/James P/Durant`                                                                                                                                                                                                                                                                                                                                                                                          |
| **Filter** | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| **Update** | `update P/PLAYER [n/NAME] [p/PHONE_NUMBER] [a/AGE] [w/WEIGHT] [h/HEIGHT] [j/JERSY_NUMBER]`<br> e.g. `update P/John Doe a/22`<br>`update T/TEAM n/NEW_TEAM_NAME`<br>e.g. `update T/Huskers n/Huskies`<br>`update T/TEAM_NAME L/LINEUP_NAME n/NEW_LINEUP_NAME`<br> e.g. `update T/Lakers L/HAHA n/HEIHEI`<br>`update S/TEAM_NAME i/INDEX_SCHEDULE [n/DESCRIPTION] [d/DATETIME]`<br> e.g. `add S/Yabuda i/1 n/competition d/22/02/2022 0900`                                                                 |
| **Find**   | `find P/PLAYER`<br>e.g. `find P/Wu Lala`<br>`find L/LINEUP`<br>e.g. `find L/Oo la la`                                                                                                                                                                                                                                                                                                                                                                                                                     |
| **Save**   | `save PATH`<br> e.g.`save details/team.txt`                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| **Load**   | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| **Clear**  | `xxx`<br> e.g.`xxx`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| **Help**   | `help`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
