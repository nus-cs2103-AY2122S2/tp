---
layout: page
title: User Guide
---

Welcome to the User Guide for **HackNet**, where we will guide you through all you need to know to get started!

**HackNet** is a desktop application that can help you manage contact details of potential team members to work on a technical project or participate in a Hackathon with. It has a simple Graphical User Interface (GUI) which displays details of your potential teammates, and works mainly via a Command Line Interface (CLI) through text commands.

## Table of Contents
* [Quick Start](#quick-start)
* [Overview](#overview)
* [Features](#features)
    * [Viewing help: help](#viewing-help-help)
    * [Contact Management](#contact-management)
      * [Adding a person: add](#adding-a-person-add)
      * [Editing details for existing contact: edit](#editing-any-number-of-persons-edit)
      * [Deleting a person: delete](#deleting-a-person-delete)
      * [Remove all contacts: clear](#clearing-all-entries-clear)
    * [Team Forming](#team-forming)
      * [List all contacts: list](#listing-all-persons-list)
      * [Find person by name: find](#locating-persons-by-name-find)
      * [Filter person by technical skills: filter](#locating-persons-by-skills-filter)
      * [Sort person by technical skill: sort](#sorting-persons-by-skill-proficiency-sort)
      * [Add/remove person as potential teammate: team/unteam](#markunmark-contact-as-potential-teammates-teamunteam)
      * [Show past teammates: filterteam](#filter-for-past-teammates-filterteam)
      * [Show potential teammates: show](#show-all-potential-teammates-show)
    * [Utility](#utility)
      * [Undo](#undo-last-command-undo)
      * [Redo](#redo-last-command-redo)
      * [Navigating User Input History](#navigating-user-input-history--)
    * [Exiting HackNet](#exiting-the-program-exit)
* [Data](#data)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `HackNet.jar` from [here](https://github.com/AY2122S2-CS2103T-W13-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for HackNet.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

    <div markdown="block" class="alert alert-info">

    **:information_source: If double-clicking doesn't work:**<br>

    This could be a problem with the Windows OS. You can alternatively start **HackNet** by:
    * Starting a terminal session on your computer
    * Change the directory to the folder containing the file using `cd path/to/HackNet_home_folder`
    * Start HackNet using `java -jar HackNet.jar`

    </div>

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com g/johndoe123` : Adds a contact named `John Doe` to HackNet.

   * **`delete`**` 3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>
## Overview

**HackNet** is a small desktop application, and contains simple components working together to form a powerful product.

![Ui](images/UI-annotated.png)

* Input field: This is where you enter your commands to **HackNet**.
* Result box: This is where **HackNet** tells you whether your command succeeded, or to provide guidance on why your command failed.
* Personal details: This contains all the details of your contacts:
  * Past teams: These are tags you can add onto your contacts, whom have worked with you on technical projects in the past
  * Technical skills: These are skill labels to help you keep track of technical proficiency of your contacts. It is color coded such that a brighter green tag indicates a higher proficiency in that skill
  * GitHub username: This is the [GitHub](https://github.com) username of your contact. It is also a hyperlink that will open up the GitHub profile of that contact in your browser
* Potential teammates: These are contacts you marked as potential teammates, which are part of the dream team you are forming for the next Hackathon. **HackNet** highlights such contacts to make them stand out.

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
    * e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
    * e.g. `n/NAME [t/TEAM]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can have multiple values including 0.<br>
    * e.g. `[t/TEAM…]​` can be used as ` ` (i.e. 0 times), `t/`, `t/friend, family` etc.

* Parameters can be in any order.<br>
    * e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
    * e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `undo`, `redo`, `exit` and `clear`) will be ignored.<br>
    * e.g. if the command specifies `list 123`, it will be interpreted as `list`.

* For the team field, maximum length of a team name is 20 characters.

* For skill field, Skill name have to be followed by an underscore `_` and Skill proficiency level that ranges from 0 to 100 with 0 being the lowest proficiency level. Maximum length of a skill name is 10 characters.
    * e.g. `[s/SKILLNAME_SKILLPROFICENCY…]​` as `s/Java_90`

* Team and skill names for team and skill field can only be consisted of alphanumeric characters, spaces, and some special characters including `-`, `#`, and `+`. The names cannot start or end with spaces and such spaces will be automatically ignored.

</div>
<div style="page-break-after: always;"></div>

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help [TOPIC]`

Simply calling `help` will bring up a prompt linking to the user guide.

![help message](images/helpMessage.png)

Passing in a relevant keyword as the `TOPIC` parameter such as `add` will bring up a brief description and usage
of the topic.

![help add message](images/helpAdd.png)

<div style="page-break-after: always;"></div>

## Contact Management

This section contains commands that can help you manage the details of your contacts within **HackNet**.

### Adding a person: `add`

Adds a person to HackNet.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL g/GITHUB_USERNAME [t/TEAM…]​ [s/SKILLNAME_SKILLPROFICENCY…]​`

* HackNet can store multiple contacts with the same `Name` but will reject inputs that contain any `Email`, `Github Username` or `Phone Number` fields that already exists in HackNet.<br>
* Teams and skills in `[t/TEAM…]` and `[s/SKILLNAME_SKILLPROFICENCY…]` must be separated by a comma. Refer to the examples below.
* If multiple duplicate skill names are entered, HackNet will only take the skill with the highest proficiency.
* A person can have any number of teams or skills (including 0)<br>

<div markdown="block" class="alert alert-info">

**:information_source: Notes regarding adding a person:**<br>

* Consecutive white spaces right after `t/` and `s/` are ignored (results in a contact added without teams or skills).<br>
* There is currently no way of viewing the exact number that you entered.
* The skill proficiency will only be a visual guide in a shade of green (bright green for high proficiency and dark green for low proficiency).<br>
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com g/johndoe123`
* `add n/Betsy Crowe e/betsycrowe@example.com g/betsycoder p/1234567 t/gmail plugin, Sublime Text dev s/`
* `add n/John Doe p/98765432 e/johnd@example.com g/johndoe123 s/C_90, C_2, C_22` will result in `C_90` as it has the highest value.

### Editing any number of person(s): `edit`

#### Single edit

Edits the details of specific person in HackNet.

Format: `edit INDEX [o/OPTION] [n/NAME] [p/PHONE] [e/EMAIL] [g/GITHUB_USERNAME] [t/TEAM…]​ [s/SKILLNAME_SKILLPROFICENCY…]​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. 
* At least one of the optional fields must be provided besides `[o/OPTION]`.
* Team and skill values in `[t/TEAM…]` and `[s/SKILLNAME_SKILLPROFICENCY…]` must be separated by a comma. Any excess commas after values will be ignored. Refer to the examples below.
* Spaces before and after commas are ignored, so `t/team a, team b` will be treated the same as `t/team a,team b`. The same applies to `[s/SKILLNAME_SKILLPROFICENCY…]`.
* Existing values will be updated to the input values.
* If multiple duplicate skill names are entered, HackNet will only take the skill with the highest proficiency.

<div markdown="block" class="alert alert-info">
**:information_source: `[o/OPTION]`:**<br>

* `o/` declares the option parameters for `edit`.
* When `r` is used as an option, reset mode is activated and the `Team` and `Skill` fields of the contact is overwritten by the arguments in `t/` and `s/`.
* Without `o/r`, the `Team` and `Skill` arguments specified in the command by `t/` and `s/` are appended to the contact's existing teams and skills.
</div>

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 o/r n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing teams.
* `edit 2 t/HackNet s/` Appends the team `HackNet` to the 2nd person and keep the current skills.
* `edit 1 s/C_90, C_2, C_11` will result in the 1st person in the list having skill `C` with proficiency `90` as it is the highest value.

<div style="page-break-after: always;"></div>
#### Batch edit

Edits the details of multiple contacts in HackNet. 

Format: `edit INDEX… [o/OPTION] [t/TEAM…] [s/SKILLNAME_SKILLPROFICENCY…]`

* `edit` command behaves as batch edit only when at least two indices are provided for `INDEX…`. Otherwise, `edit` behaves as single edit.
* Edit the `Team` and `Skill` field of multiple contacts specified by the indexes simultaneously.
* Syntax for passing multiple values for `Team` and `Skill` is the same as single edit. Values should be separated by a comma and whitespaces before and after commas, and any excess commas after the values will be ignored.
* Indices must be separated by a whitespace as opposed to teams and skills. All index **must be a positive integer that is in the contact list** `1, 2, 3, …`
* If multiple duplicate skill names are entered, HackNet will only take the skill with the highest proficiency.
* In the case that same index is present multiple times for `INDEX…`, HackNet will still successfully execute the edit command as long as the index is valid.
* If any other fields such as `[n/NAME]` that is only valid for single edit is provided, it will be silently ignored.

Examples:
* `edit 2 3 s/ t/GoogleProject, Hackathon2022` Does not change the skills of 2nd and 3rd person in the list, and appends`GoogleProject` and `Hackathon2022` to the list of teams they belong to.
* `edit 1 2 3 o/r s/Java_100, Python_80, t/` Edits the skills of the 1st, 2nd and 3rd person to be `java` and `python` only with proficiency of 100 and 80. The existing teams are cleared as well.

<div style="page-break-after: always;"></div>

### Deleting a person: `delete`

Deletes the specified person from HackNet.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries: `clear`

Clears all existing contacts from HackNet.

Format: `clear`

## Team Forming

This section contains commands that will aid you to find people with the technical skills you need to form your dream team.

### Listing all persons: `list`

Shows a list of all persons in HackNet.

Format: `list`

### Locating persons by name: `find`

Finds persons whose *names* contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex melvin` returns `Alex Teo`, `Melvin Chan`<br>
  ![result for 'find alex melvin'](images/findAlexMelvinResult.png)

### Locating persons by skills: `filter`

Finds persons that have the specified skills.

Format: `filter KEYWORD`

* The search is case-insensitive. e.g `java` will match `Java`
* Only full words will be matched e.g. `C` will not match `C#`
* This command accepts multiple skills to be filtered,
e.g. `filter c java` will filter all those with `C` <b><u>AND</u></b> `Java`.

Examples:
* `filter C` returns `Alex Teo`, `Lye Jia Yang` and `Toh Zhan Qing`
* `filter java` returns `Junha Park`<br>
  ![result for 'filter C'](images/filterCResult.png)

### Sorting persons by skill proficiency: `sort`

Sort persons in descending level of proficiency of specified skill.

Format: `sort SKILL`

* Only persons with the specified `SKILL` will be listed
* The search is case-insensitive. e.g `java` will match `Java`
* Only full words will be matched e.g. `C` will not match `C#`
* The list will be shown in descending level of skill proficiency
* This command only accepts <b><u>ONE</u></b> skill as the sorting criteria.

Examples:
* `sort Python` shows persons with `Python` as a skill in descending order of proficiency, from brighter to darker shades of green.

### Mark/unmark contact as potential teammates: `team/unteam`

Mark/unmark contacts as potential teammates for the team you are currently forming. A marked contact will be highlighted for clarity.

Format:
* `team INDEX`
* `unteam INDEX`
    * Marks/unmarks the person at `INDEX`as potential teammate
    * The index refers to the index number shown in the displayed person list
    * The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `team 1` marks the first person as a potential teammate

![result for 'team 1'](images/teamCommandResultBefore.png)

![result for 'team 1'](images/teamCommandResultAfter.png)

### Filter for past teammates: `filterteam`

Shows a list of past teammates (contacts that have been tagged with /t) in HackNet. Note that this does not show contacts marked as potential teammates using the `team` command above.

Format: `filterteam`

### Show all potential teammates: `show`

Shows the list of all persons marked as potential teammates

Format: `show`

## Utility

This section contains some commands/shortcuts that can improve your work efficiency in using **HackNet**.

### Undo last command: `undo`

Undo the last command that changes HackNet. <br>
Only these commands that changed HackNet can be undone:
* Add
* Delete
* Edit
* Team
* Unteam
* Clear

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the undo command:**<br>

* The commands executed will be stored in history, please use the `undo` command to restore them.
* Only the commands as listed above can be undone.
* If a different command is executed after a command is undone,
the undone command will be removed from history and can no longer be redone.
* e.g `Add person 1 -> Add person 2 -> undo -> Add person 3` will have the same persons and history as
  `Add person 1 ->  Add person 3`
* <b>IMPORTANT!</b> All commands in history will be removed when the application exits.
</div>

### Redo last command: `redo`

Redo the command that was previously undone.
Only these commands that changes HackNet can be redone:
* Add
* Delete
* Edit
* Team
* Unteam
* Clear

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the redo command:**<br>

* This command will revert HackNet back to the state before the last command is executed.
* Only the commands specified above can be redone.
* If a command is executed and there are commands in the history,
those commands that are not redone will be removed from history.
* <b>IMPORTANT!</b> All commands in history will be removed when the application exits.

</div>

### Navigating User Input History: `↑`, `↓`

Allows user to quickly retrieve their previous inputs from current session by using the up and down arrow keys.

Format: `↑`, `↓`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

<div style="page-break-after: always;"></div>

## Data

This section contains information about how we save your data across sessions.

### Saving the data

HackNet data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HackNet data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HackNet will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HackNet home folder.

**Q**: Why does HackNet not show the proficiency of individual's skills?<br>
**A**: This is because how everyone perceives their own skill proficiency is only a rough gauge and the numbers may not accurately reflect in real life. Therefore, to reduce bias, HackNet distinguishes between different level of skill proficiencies with different hues of green, with brighter green as more proficient in that particular skill.

**Q**: I am colour blind and cannot differentiate between skill proficiency colours. What should I do?<br>
**A**: You can use the feature `sort` to list out contacts with that particular skill in descending order. (eg. `sort Java`)

**Q**: What is the difference between **past teammates** and **potential teammates**?<br>
**A**: Past teammates are contacts that you have worked on a project together with, and appear as a _blue_ tag in **HackNet**. Potential teammates are contacts you want to work with, whom you mark in **HackNet** with a highlighted _turquoise_ colour.

**Q**: What is the difference between `list`, `show` and `filterteam`?<br>
**A**: `list` displays _all_ of your contacts while `show` will only display contacts that you have marked with `team`. Lastly, `filterteam` will display those contacts that you have marked with `/t` (the blue tags)

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help [TOPIC]`
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL g/GITHUB_USERNAME [t/TEAM…]​ [s/SKILLNAME_SKILLPROFICENCY…]​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com g/jameshooo t/hackathon2021, hackathon2022 s/java_70`
**Edit** | `edit INDEX [INDEX…] [o/OPTION] [n/NAME] [p/PHONE] [e/EMAIL] [g/GITHUB_USERNAME] [t/TEAM…]​ [s/SKILLNAME_SKILLPROFICENCY…]​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Mark/unmark contacts** | `team`, `unteam` <br> e.g., `team 1`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Clear** | `clear`
**List** | `list`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter SKILL`<br> e.g. `filter c`
**Sort** | `sort SKILL`<br> e.g., `sort Python`
**Filter Past Teammates** | `filterteam`
**Show potential teammates** | `show`
**Undo** | `undo`
**Redo** | `redo`
**Navigate User Input History** | `↑`, `↓`
**Exit HackNet** | `exit`
