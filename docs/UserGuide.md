---
layout: page
title: User Guide
---

## Table of Contents
* [**Introduction**](#introduction)
* [**User guide navigation**](#user-guide-navigation)
* [**Quick start**](#quick-start)
* [**User interface**](#user-interface)
* [**Command structure**](#command-structure)
* [**Features**](#features)
  * [Viewing help: `help`](#viewing-help-help)
  * [Adding a show: `add`](#adding-a-show-add)
  * [Deleting a show: `delete`](#deleting-a-show-delete)
  * [Editing a show: `edit`](#editing-a-show-edit)
  * [Listing all shows: `list`](#listing-all-shows-list)
  * [Clearing all shows: `clear`](#clearing-all-shows-clear)
  * [Finding a show: `find`](#finding-a-show-find)
    * [General find](#general-find)
    * [Precise find](#precise-find)
  * [Sorting the shows `sort`](#sorting-the-shows-sort)
  * [Suggesting a show `suggest`](#suggest-a-show-suggest)
  * [Import a show: `import`](#importing-a-show-import)
  * [Exporting a show: `export`](#exporting-a-show-export)
  * [Exiting the program: `exit`](#exiting-the-program-exit)
* [**FAQ**](#faq)
* [**Command Summary**](#command-summary)
* [**Glossary**](#glossary)

---

## Introduction

Always accessing MyAnimeList or IMDB to record down the shows you watched? Or finding it difficult to search the different websites that stored your show information? Fret not because **Trackermon** is the app just for you! But hold on, you may be wondering what is Trackermon?

Trackermon is a **desktop application** for **tracking and managing shows, optimized for use via a Command Line Interface ([CLI](#glossary))** while still having the **benefits of a Graphical User Interface ([GUI](#glossary))**. Trackermon allows you to track and remember what shows you have watched, are currently watching, or plan to watch. You can even review these shows!

---

## User guide navigation

Before you continue reading the rest of our user guide, the table below displays some important syntax to facilitate your reading.

| Syntax                                                                    | Description                                             |
|---------------------------------------------------------------------------|---------------------------------------------------------|
| **Bold**                                                                  | Important words to note                                 |
| `Git Flavoured Markdown`                                                  | Command word/prefix/parameter                           |
| <div markdown="span" class="alert alert-warning">:bulb: </div>            | A small but useful piece of information                 |
| <div markdown="span" class="alert alert-info">:information_source: </div> | Additional information                                  |
| <div markdown="span" class="alert alert-danger">:exclamation: </div>      | Important information to watch out for                  |
| [Optional Parameters]                                                     | Indicates the parameters/prefixes that may be optional  |
| \<Instructions for Parameters\>                                           | Represents certain requirements you will need to follow |
| {Multiple Optional Parameters}                                            | At least one parameter must be provided                 |

---

## Quick start

1. Ensure you have `Java 11` or above installed in your Computer.

2. Download the latest version of **Trackermon** [here](https://github.com/AY2122S2-CS2103T-T09-3/tp/releases).

3. Move the file to the folder you want to use as the _home folder_ for **Trackermon**.

4. Double-click the file to start the app. The layout of Trackermon's [GUI](#glossary) is shown in the [section below](#user-interface).
   * Do note that for Linux OS, you may have to [enable double-click to run JAR files](https://askubuntu.com/a/270175) first! <br><br>
   
5. For a quick overview of all available commands, please refer to our [command summary](#command-summary).

6. For details of each command, please proceed to the [command-structure](#command-structure) section.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## User interface

<img src="images/Trackermon_UI_Info.png">

The table below briefly describes each of the **4 main components**.

| Component      | Description                                  |
|----------------|----------------------------------------------|
| Show List      | A panel to display a list of saved shows     |
| Show Details   | A panel to display a specific show's details |
| Result Display | A panel to display command results           |
| Command Box    | A panel to enter commands                    |

You can start communicating with Trackermon using the command box. Some example commands you can try are:

* **`list`** : Lists all shows.

* **`add`** `n/ReZero s/watching t/Anime` : Adds a show **named** `ReZero` with the **tag** `Anime` to Trackermon as **watching**.

* **`delete`** `3` : Deletes the **3rd show** shown in the current list.

* **`exit`** : Exits the app.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Command structure

Let us look at what makes up a command:

| Component    | Description                                                                   |
|--------------|-------------------------------------------------------------------------------|
| Command Word | The word representing the action of the command                               |
| Prefix       | The identifier used to recognise command parameters                           |
| Parameters   | Follows directly behind a prefix and contains the information provided by you |

For example, a command to find a show could look like this:

`find n/ Django` 

In the example above , `find` is the **command word** while `n/` is the **prefix** of the `Djanjo` **parameter**. A list of parameters along with their prefixes and descriptions have been included below for your convenience.

| Parameters | Prefix | Description                                                                         |
|------------|--------|-------------------------------------------------------------------------------------|
| KEYWORD    | None   | The input after the command word without a prefix                                   |
| INDEX      | None   | The index of the show as shown in the show list                                     |
| NAME       | n/     | The name to use for a show                                                          |
| STATUS     | s/     | The status to label a show<br> They are _COMPLETED_, _WATCHING_ and _PLAN-TO-WATCH_ |
| TAG        | t/     | The tag to label a show                                                             |
| COMMENT    | c/     | The comment to describe a show                                                      |
| RATING     | r/     | The rating to give a show                                                           |

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Sex and the City`.

* Items in square brackets are optional.<br>
  e.g `n/NAME s/STATUS [r/RATING] [c/COMMENT] [t/TAG]…` can be used as `n/ReZero s/completed r/5 c/What a Simp t/Anime` or as `n/ReZero s/completed`.

* Items with `…`​ after them can be used multiple times.<br>
  e.g. `[t/TAG]…​` can be used as `t/Anime`, `t/Sitcom t/Kdrama` etc.

* A whitespace must be included before every prefix.<br>
  e.g. `n/Knives Out t/Suspense` is acceptable but `n/Knives Outt/Suspense` is not.

* All **show names must be unique**.<br>
  e.g. if you try to add `n/Stranger Things` into the show list that already contains that show, there will be a message telling you that this show already exists in the list. You could name it as `n/Stranger Things S2` instead.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME [t/TAG]…​`, `[t/TAG]…​ n/NAME` is also acceptable.

* The **index** parameter you provide should be a positive whole-number. On top of that, it should be within the bounds of the show list.<br>
  e.g. If there are 5 shows saved in the show list, then the valid index ranges from 1 to 5.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/Batman n/Superman`, only `n/Superman` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `list` ) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Features

### Viewing help: `help`

**Description:** Are you new or confused with the commands? View the command summary and user guide through a quick pop up window!

**Format:** `help`

**Example & Output:** `help`

<img src="images/HelpWindow.png">

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Adding a show: `add`

**Description:** Planning to watch a show and want to store its details? Add it to Trackermon's show list!

**Format:** `add n/NAME s/STATUS [r/RATING] [c/COMMENT] [t/TAG]…​`

<div markdown="block" class="alert alert-danger">
**:exclamation: Caution:**<br>
* All **show names must be unique**.<br>
  e.g. if you try to add `n/Stranger Things` into the show list that already contains that show, there will be a message telling you that this show already exists in the list. You could name it as `n/Stranger Things S2` instead.

</div>

**Example & Output:** `add n/All of us are dead s/plan-to-watch t/Horror`

<img src="images/AddImage.png">

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Deleting a show: `delete`

**Description:** Wanting to remove an unwanted show? Delete it at the specified index shown in Trackermon's show list!

**Format:** `delete INDEX`

<div markdown="block" class="alert alert-danger"> **:exclamation: Caution:** 
* Once a show is deleted, you cannot retrieve it back!
* The **index** parameter you provide should be a positive whole-number. On top of that, it should be within the bounds of the show list.<br>
  e.g. If there are 5 shows saved in the show list, then the valid index ranges from 1 to 5. 

</div>

**Example & Output:** `delete 2`

<img src="images/DeleteImage.png">

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Editing a show: `edit`

**Description:** Wanting to modify a show? Edit it at the specified index shown in Trackermon's show list!

**Format:** `edit INDEX {[n/NAME] [s/STATUS] [r/RATING] [c/COMMENT] [t/TAG]…​}`

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
Multiple show [parameters](#command-structure) can be edited at the same time
</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the Rating and Comment feature:** <br>
* Reset the rating of a show: `edit INDEX r/0`
* Removing a comment: `edit INDEX c/`

</div>

<div markdown="block" class="alert alert-danger">

**:exclamation: Caution:**<br>
* **An [index](#command-structure)** to edit must be provided.
* **At least a [prefix](#command-structure) followed by a [parameter](#command-structure)** to edit must be provided.
* All **show names must be unique**.<br>
  e.g. if you try to add `n/Stranger Things` into the show list that already contains that show, there will be a message telling you that this show already exists in the list. You could name it as `n/Stranger Things S2` instead.
* Editing a `TAG` parameter will cause all the **previous tags of the show to be deleted**.
* The **index** parameter you provide should be a positive whole-number. On top of that, it should be within the bounds of the show list.<br>
  e.g. If there are 5 shows saved in the show list, then the valid index ranges from 1 to 5.

:exclamation:**Adding, Editing and Deleting a Tag:** <br>
* Doing any of these can result in your tags being deleted if you do not specify existing tags you want to keep. <br>
* Adding a Tag:
  * `edit INDEX <Specify all tags in show> t/NEW_TAG`
* Deleting a Tag:
  * `edit INDEX <Specify all tags in show except the tag you want to delete>`
* Editing a Tag:
  * `edit INDEX <Specify all other tags in show> t/EDITED_TAG`

</div>

**Example & Output:** `edit 2 n/Sailor Moo t/Horror`

<img src="images/EditImage.png">

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Listing all shows: `list`

**Description:** Wanting to view all your shows at once? Display a list of shows in Trackermon's show list!

**Format:** `list`

**Example:** `list`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Clearing all shows: `clear`

**Description:** Wanting to reset your current show list? Clear all shows in Trackermon's show list!

**Format:** `clear`

**Example:** `clear`
<div markdown="block" class="alert alert-danger">

:exclamation:**Caution:** Clear will delete the current list! Your list data will be lost!
</div>

---

### Finding a show: `find`
#### General find

**Description (General Find):** Searching for a show across all [parameters](#command-structure)? Find shows containing the search words!

**Format (General Find):** `find KEYWORD`

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
Find is case-insensitive, and the order in which the keywords are entered is irrelevant. Partial words **will** be matched as well. e.g., `attac` will match `attack`.
</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about General Find:**<br>
* An [**OR search**](#glossary) is executed across Trackermon's show list and all shows with matching [parameters](#command-structure) will be returned.
* The `KEYWORD` refers to the input you enter after `find`.
* `find` must be followed with a space before entering the `KEYWORD`.
* The `KEYWORD` **can be a word or number** such as hero, S1,...
* The `KEYWORD` must contain **at least one word** and it **must not be empty**.
* `find attack on titan` displays all the shows in the list that contain the keywords `attack`, `on` or `titan`, whether it is a name, status or tag.

</div>

**Example & Output:** `find attack on titan`

<img src="images/General_Find_UI.png">

#### Precise find

**Description (Precise Find):** Searching for a show across specific [parameters](#command-structure)? Find shows containing the search words based on [prefix](#command-structure)!

**Format (Precise Find):** `find {[n/NAME] [s/STATUS] [r/RATING] [t/TAG]…​}`

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
Find is case-insensitive, and the order in which the keywords are entered is irrelevant. Partial words **will** be matched as well. e.g., `attac` will match `attack`.
</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about Precise Find:**<br>
* **Within a single [prefix](#command-structure)** and **across multiple [prefixes](#command-structure)**, an [**AND search**](#glossary) is executed across Trackermon's show list and only shows with matching [parameters](#command-structure) will be returned.
* `find n/Shutter Island` displays all the shows in the Trackermon's show list that contain **Shutter** and **Island** in the `NAME` parameter.
* `find n/Django s/completed r/3 t/Action` displays all the shows in the Trackermon's show list that contain **Django** in the `NAME` parameter, **completed** in the `STATUS` parameter, **3** in the `RATING` parameter and **Action** in the `TAG` parameter.

</div>

<div markdown="block" class="alert alert-danger">

:exclamation:**Caution:**<br>
* There must be at **least one [prefix](#command-structure) followed by a [parameter](#command-structure)** and it **must not be empty**.

:exclamation:**Multiple of the same prefixes:**<br>
* `find n/attack n/on n/titan n/S2` does not mean `find n/attack on titan S2`. The former will only find show names that match with **S2** (as mentioned in [features](#command-structure)) while the latter will find all show names that match **attack, on, titan, and S2**. This is only applicable to the `NAME` parameter.
* `find t/Anime t/Action` does not mean `find t/Anime Action`. The former will find show tags that match with **Anime** and **Action** in the `TAG` parameter while the latter will show you that it is an **invalid command format**. This is only applicable to the `TAG` parameter.
* `find r/4 5` does not mean `find r/4 r/5`. The former will find all show ratings that match a 4 star rating and 5 star rating while the latter will find all shows with a 5 star rating. This is only applicable to the `RATING` parameter.

</div>

**Example & Output:** `find n/Shutter Island`

<img src="images/Precise_Find_UI.png">

**Example & Output:** `find r/5`

<img src="images/Precise_Find_UI_2.png">

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Sorting the shows `sort`

Your show list is in a mess! Do you need a way to organise your list?
Sort can help reorder the list!!

<div markdown="block" class="alert alert-danger">

:exclamation: **Caution:** You cannot undo the sort once you have applied it!
</div>

**Format:** `sort [n/ORDER] [s/ORDER] [t/ORDER] [r/ORDER] [so/SEQUENCE]`

<div markdown="block" class="alert alert-warning">

:bulb: **Tip:** `Sort` will sort according to the [prefixes](#command-structure).<br>
* Use `n/` if you want to sort by name.
* Use `s/` if you want to sort by status.
* Use `t/` if you want to sort by tag.
* Use `r/` if you want to sort by rating.
* For the above prefixes, you have to specify the `ORDER` right after the prefix.
* `ORDER` is the order you want to sort the list by! Type `asc` to sort by ascending or type `dsc` to sort by descending!
* If two or more of the above prefixes are being used, sort will prioritise sorting by name, then status, followed by rating, and finally tags.
* Use `so/` if you want to reorder the priority and state the `SEQUENCE`
* List the `SEQUENCE` by listing out the full name of the prefix used in the order you want.
* For example: you want to sort by name, then status, followed by rating, and finally tags, `SEQUENCE` will be "name status rating tag"
* `SEQUENCE` input is case-insensitive.
* If you are confused, don't worry! Just do not use any prefix, we will help you sort it by name in ascending order!
</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about Sort:**<br>
* By not entering any prefixes, it will sort by name in ascending order.
* Enter the right amount of prefixes! For example: if you only sort by status, use only `s/`. 
* The `ORDER` must be asc or dsc!
* The `SEQUENCE` must contain the full name of all the sort conditions used!
* For `SEQUENCE`, if the full name of a sort condition is being used multiple times, it will only consider the first instance of the full name. For example: "RatingTagRating" will sort by rating then tag.
* If the same prefix is being used multiple times, only the last prefix will be considered by the program! For example: `sort n/asc n/dsc` will sort name by descending order.
</div>

**Example & Output:** `sort`

<img src="images/Sort_UI.png">

**Example & Output:** `sort t/dsc r/asc`

<img src="images/Sort_UI2.png">

**Example & Output:** `sort t/dsc r/asc so/tagrating`

<img src="images/Sort_UI3.png">

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Suggest a Show: `suggest`

**Description:** Want a random show? This suggests a random show from the displayed list!

**Format:** `suggest`

**Example & Output:** `suggest`

<img src="images/SuggestImage.png">

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Importing a show: `import`

**Description:** Want to easily import Trackermon data from other devices? Just use our import function!

**Format:** `import`

<div markdown="block" class="alert alert-danger">

:exclamation:**Caution:** Import will override the current list if successful! Your previous list data will be lost!
</div>

**Example & Output:** `import`
- Step 1. Navigate to the Trackermon data you want to import using your OS' native [GUI](#glossary)!
- Step 2. Select the file and click "Open"
  - Do note that only JSON files can be imported!
- Step 3. Trackermon will import the selected file and display the imported show list!
  - If there was an error importing the file, Trackermon will display an error message!

<img src="images/Import.png">

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Exporting a show: `export`

**Description:** Want to easily export Trackermon data to other devices? Just use our export function!

**Format:** `export`

**Example & Output:** `export`
- Step 1. Navigate to the location you wish to export Trackermon data to using your OS' native [GUI](#glossary)!
- Step 2. Click "Save"
- Step 3. Trackermon will export the Trackermon data as a JSON file to your selected location. It's that easy!
  - If there was an error exporting the file, Trackermon will display an error message!

<img src="images/Export.png">

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Exiting the program: `exit`

**Description:** Wanting to exit the application? This simple command is what you are looking for!

**Format:** `exit`

**Example:** `exit`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## FAQ
* **Q:** Where is the data of Trackermon saved?<br>
**A:** Trackermon data is saved in the hard disk automatically after any command that changes the data! There is no need for you to save manually.<br><br>

* **Q:** How is my data being saved in Trackermon?<br>
**A:** Your data will be stored as a JSON file called `trackermon.json`! You can find it in the "data" folder in Trackermon's _home folder_.<br><br>

* **Q:** How do I transfer my data to another computer?<br>
  **A:** 
  * Firstly, `export` your data to an external storage device. 
  * Next, on your other Computer, simply start up Trackermon and `import` the data from the previous step!
  * Congratulations! You've just transferred your data across different computers!

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Command summary

| Action           | Format, Examples                                                                                                           |
|------------------|----------------------------------------------------------------------------------------------------------------------------|
| **Help**         | `help`                                                                                                                     |
| **Add**          | `add n/NAME s/STATUS [r/RATING] [t/TAG]…​` <br> e.g., `n/ReZero s/watching r/3 t/Anime`                                    |
| **Delete**       | `delete INDEX`<br> e.g., `delete 3`                                                                                        |
| **Edit**         | `edit INDEX [n/NAME] [s/STATUS] [r/RATING] [c/COMMENT] [t/TAG]…​` <br> e.g., `n/ReZero s/watching r/3 c/Good show t/Anime` |
| **List**         | `list`                                                                                                                     |
| **Clear**        | `clear`                                                                                                                    |
| **General Find** | `find KEYWORD`<br> e.g., `find hero`<br>                                                                                   |
| **Precise Find** | `find [n/NAME] [s/STATUS] [r/RATING] [t/TAG]…​`<br>e.g., `find n/Shingeki no kyojin s/watching r/5 t/Anime t/Seinen`       |
| **Sort**         | `sort [n/ORDER] [s/ORDER] [r/ORDER] [t/ORDER] [so/SEQUENCE]`                                                               | 
| **Suggest**      | `suggest`                                                                                                                  |
| **Import**       | `import`                                                                                                                   |
| **Export**       | `export`                                                                                                                   |
| **Exit**         | `exit`                                                                                                                     |

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Glossary

| Term                               | Description                                                                                                                                                                          |
|------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **AND search**                     | AND search finds all of the keywords.  For example, `find n/Shutter Island` returns only results that contain Shutter and Island.                                                    |
| **OR search**                      | OR search finds one keyword or the other.  For example, `find Shutter Island` returns all results that contain Shutter or Island.                                                    |
| **Command Line Interface (CLI)**   | A Command Line Interface connects a you to a computer program or operating system. Through the CLI, you can interact with a system or application by typing in text (commands).      | 
| **Graphical User Interface (GUI)** | A form of user interface that allows you to interact with electronic devices through graphical icons instead of text-based user interfaces, typed command labels or text navigation. |


[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)
