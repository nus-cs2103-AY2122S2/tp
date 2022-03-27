---
layout: page
title: User Guide
---

## Table of Contents
* [**Introduction**](#introduction)
* [**User guide navigation**](#user-guide-navigation)
* [**Quick start**](#quick-start)
* [**Command structure**](#command-structure)
* [**Features**](#features)
  * [Clearing all shows](#clearing-all-shows)
  * [Listing all shows: `list`](#listing-all-shows-list)
  * [Exiting the program: `exit`](#exiting-the-program-exit)
  * [Viewing help: `help`](#viewing-help-help)
  * [Adding a show: `add`](#adding-a-show-add)
  * [Deleting a show: `delete`](#deleting-a-show-delete)
  * [Editing a show: `edit`](#editing-a-show-edit)
  * [Commenting on a show: `comment`](#commenting-on-a-show-comment)
  * [Finding a show: `find`](#finding-a-show-find)
  * [Saving the data](#saving-the-data)
  * [Sorting the shows](#sorting-the-shows)
* [**FAQ**](#faq)
* [**Command Summary**](#command-summary)
* [**Glossary**](#glossary)

---

## Introduction

Always accessing MyAnimeList or IMDB to record down the shows you watched? Or finding it difficult to search the different websites that stored your show information? Fret not because **Trackermon** is the app just for you! But hold on, you may be wondering what is Trackermon?

Trackermon is a **desktop application** for **tracking and managing shows, optimized for use via a Command Line Interface ([CLI](#glossary))** while still having the **benefits of a Graphical User Interface ([GUI](#glossary))**. Trackermon allows you to track and remember what shows you have watched, are currently watching, or plan to watch.

---

## User guide navigation

Before you continue reading the rest of our user guide, the table below displays some important syntax to facilitate your reading.

| Syntax                                                                    | Description                                            |
|---------------------------------------------------------------------------|--------------------------------------------------------|
| **Bold**                                                                  | Important words to note                                |
| `Git Flavoured Markdown`                                                  | Command word/prefix/parameter                          |
| <div markdown="span" class="alert alert-warning">:bulb: </div>            | A small but useful piece of information                |
| <div markdown="span" class="alert alert-info">:information_source: </div> | Additional information                                 |
| <div markdown="span" class="alert alert-danger">:exclamation: </div>      | Important information to watch out for                 |
| [Optional Parameters]                                                     | Indicates the parameters/prefixes that may be optional |

---

## Quick start

1. Ensure you have `Java 11` or above installed in your Computer.

2. Download the latest version of **Trackermon** [here](https://github.com/AY2122S2-CS2103T-T09-3/tp/releases).

3. Move the file to the folder you want to use as the _home folder_ for **Trackermon**.

4. Double-click the file to start the app. A [GUI](#glossary) containing the annotated 4 main components should show up as below:

5. Start communicating with Trackermon using the command box.

Some example commands you can try:

* **`list`** : Lists all shows.

* **`add`** `n/ReZero s/watching t/Anime` : Adds a show **named** `ReZero` with the **tag** `Anime` to Trackermon as **watching**.

* **`delete`** `3` : Deletes the **3rd show** shown in the current list.

* **`exit`** : Exits the app.

6. For a quick overview of all available commands, please refer to our [command summary](#command-summary).

7. For details of each command, please proceed to the [Commands](#commands) section below.

[INSERT IMAGE HERE]

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Command structure

Let us look at what makes up a command:

| Component    | Description                                                           |
|--------------|-----------------------------------------------------------------------|
| Command Word | The keyword representing the action of the command                    |
| Prefix       | The keyword to recognise command parameters                           |
| Parameters   | Follows directly behind a prefix and contains the corresponding value |

For example, a command to find a show could look like this:

`find n/ Django` 

In the example above , `find` is the **command word** while `n/` is the **prefix** of the `Djanjo` **parameter**. A list of parameters along with their prefixes and descriptions have been included below for your convenience.

| Parameters | Prefix | Description                                                                            |
|------------|--------|----------------------------------------------------------------------------------------|
| KEYWORD    | None   | The input after the command word                                                       |
| INDEX      | None   | The index of the show as shown in the show panel list                                  |
| NAME       | n/     | The name to use for a show                                                             |
| STATUS     | s/     | The three statuses to label for a show are _COMPLETED_, _WATCHING_ and _PLAN-TO-WATCH_ |
| TAG        | t/     | The tag to label a show                                                                |
| COMMENT    | c/     | The comment to describe a show                                                         |
| RATE       | r/     | The rating to give a show                                                              |

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Sex and the City`.

* Items in square brackets are optional.<br>
  e.g `n/<NAME> s/<STATUS> [c/<COMMENT>] [t/TAG]…` can be used as `n/ReZero s/completed c/What a Simp t/Anime` or as `n/ReZero s/completed`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Anime`, `t/Sitcom t/Kdrama` etc.

* A whitespace must be included before every prefix.<br>
  e.g. `n/Knives Out t/Suspense` is acceptable but `n/Knives Outt/Suspense` is not.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME [t/TAG]…​`, `[t/TAG]…​ n/NAME` is also acceptable.

* All **names must be unique** and duplicates will be ignored.<br>
  e.g. if you try to add `n/Inception` into the show list that already contains that show, there will be a message telling you that this show already exists in the list.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/Batman n/Superman`, only `n/Superman` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `list` ) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

* The **index** parameter provided should be a [non-zero unsigned integer](https://en.wikipedia.org/wiki/Integer_(computer_science)) within the allowed range of Java’s [`int`](#glossary) data type. On top of that, the index should be within the bounds of the show list.<br>
  e.g. If there are 5 shows saved in the show list, then the valid index ranges from 1 to 5. 

</div>

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Features

### Clearing all shows

**Description:** Wanting to reset your current show list? Clear all shows in Trackermon's show list!

Format: `clear`

**Example:** `clear`

---

### Listing all shows: `list`

**Description:** Wanting to view all your shows at once? Display a list of shows in Trackermon's show list!

**Format:** `list`

**Example:** `list`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Exiting the program: `exit`

**Description:** Wanting to exit the application? This simple command is what you are looking for!

**Format:** `exit`

**Example:** `exit`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Viewing help: `help`

**Description:** Are you new or confused with the commands? View the command summary and user guide through a quick pop up window!

**Format:** `help`

**Example & Output:** `help`

[INSERT IMAGE AFTER UI IS DONE COMPLETELY]

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Adding a show: `add`

**Description:** Planning to watch a show and want to store its details? Add it to Trackermon's show list!

**Format:** `add n/NAME s/STATUS [c/COMMENT] [t/TAG]…​`

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
A show can have any number of comments or tags (including 0)
</div>

**Example & Output:** `add n/All of us are dead s/plan-to-watch t/Horror`

[INSERT IMAGE AFTER UI IS DONE COMPLETELY]

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Deleting a show: `delete`

**Description:** Wanting to remove an unwanted show? Delete it at the specified index shown in Trackermon's show list!

**Format:** `delete INDEX`

**Example & Output:** `delete 2`

[INSERT IMAGE AFTER UI IS DONE COMPLETELY]

<div markdown="block" class="alert alert-danger"> **:exclamation: Caution:** 
The **index** parameter provided should be a [non-zero unsigned integer](https://en.wikipedia.org/wiki/Integer_(computer_science)) within the allowed range of Java’s [`int`](#glossary) data type. On top of that, the index should be within the bounds of the show list.<br>
  e.g. If there are 5 shows saved in the show list, then the valid index ranges from 1 to 5.

</div>

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Editing a show: `edit`

**Description:** Wanting to modify a show? Edit it at the specified index shown in Trackermon's show list!

**Format:** `edit INDEX [n/NAME] [s/STATUS] [c/COMMENT] [t/TAG]…​`

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
Multiple show [parameters](#command-structure) can be edited at the same time
</div>

**Example & Output:** `edit 2 n/Sailor Moo t/Horror`

[INSERT IMAGE AFTER UI IS DONE COMPLETELY]

<div markdown="block" class="alert alert-danger">

**:exclamation: Caution:**<br>
* **An [index](#command-structure)** to edit must be provided.
* **At least a [prefix](#command-structure) followed by a [parameter](#command-structure)** to edit must be provided.
* Editing to an existing name is **not allowed**.
* Editing a `TAG` parameter will cause all the **previous tags of the show to be deleted**.
* The **index** parameter provided should be a [non-zero unsigned integer](https://en.wikipedia.org/wiki/Integer_(computer_science)) within the allowed range of Java’s [`int`](#glossary) data type. On top of that, the index should be within the bounds of the show list.<br>
  e.g. If there are 5 shows saved in the show list, then the valid index ranges from 1 to 5.

</div>

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---
### Commenting on a show: `comment`

**Description:** Want to write down your comments about a show? Note it down in Trackermon!

**Format:** `comment <INDEX> [c/<COMMENT>]`
* Edit comment of the show at the specified `<INDEX>`.
* The index refers to the index number shown in the displayed show list. (not overall)
* The index **must be a positive integer** 1,2,3,...
* Omitting the `[c/<COMMENT>]` would remove the comment of that specific show.

**Examples:** `comment 2 c/This is a good show!`
* `list` followed by `comment 2 c/Not bad` edits 2nd show's comment in Trackermon to "Not bad".
* `find ghibli` followed by `comment 1` deletes the comment of the 1st show in results of `find` command.

---


### Finding a show: `find`

**Description (General Find):** Searching for a show across all [parameters](#command-structure)? Find shows containing the search words!

**Format (General Find):** `find KEYWORD`

**Example & Output:** `find attack on titan`

[INSERT IMAGE AFTER UI IS DONE COMPLETELY]

<div markdown="block" class="alert alert-info">

**:information_source: Notes about General Find:**<br>
* The `KEYWORD` refers to the input you enter after `find`.
* `find` must be followed with a space before entering the `KEYWORD`.
* The `KEYWORD` **can be a word or number** such as hero, S1,...
* The `KEYWORD` must contain **at least one word** and it **must not be empty**.
* `find attack on titan` displays all the shows in the list that contain the keywords `attack`, `on` or `titan`, whether it is a name, status or tag.

</div>

**Description (Precise Find):** Searching for a show across specific [parameters](#command-structure)? Find shows containing the search words based on [prefix](#command-structure)!

**Format (Precise Find):** `find [n/NAME] [s/STATUS] [t/TAG]…​`

**Example & Output:** `find n/Shutter Island`

[INSERT IMAGE AFTER UI IS DONE COMPLETELY]

**Example & Output:** `find n/Django s/completed t/Action`

[INSERT IMAGE AFTER UI IS DONE COMPLETELY]

<div markdown="block" class="alert alert-info">

**:information_source: Notes about Precise Find:**<br>
* **Within a single [prefix](#command-structure)** and **across multiple [prefixes](#command-structure)**, an [**AND search**](#glossary) is executed across Trackermon's show list and only shows with matching [parameters](#command-structure) will be returned.
* `find n/Shutter Island` displays all the shows in the Trackermon's show list that contain **Shutter** and **Island** in the `NAME` parameter.
* `find n/Django s/completed t/Action` displays all the shows in the Trackermon's show list that contain **Django** in the `NAME` parameter, **completed** in the `STATUS` parameter, and **Action** in the `TAG` parameter.

</div>

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
Find is case-insensitive, and the order in which the keywords are entered is irrelevant. Partial words **will** be matched as well. e.g., `attac` will match `attack`.
</div>

<div markdown="block" class="alert alert-danger">

:exclamation:**Caution:**<br>
* There must be at **least one [prefix](#command-structure) followed by a [parameter](#command-structure)** and it **must not be empty**.

:exclamation:**Multiple of the same prefixes:**<br>
* find `find n/attack n/on n/titan n/S2` does not mean `find n/attack on titan S2`. The former will only find show names that match with **S2** (as mentioned in [features](#features)) while the latter will find all show names that match **attack, on, titan, and S2**. This is only applicable to the `NAME` parameter.
* find `t/Anime t/Action` does not mean `find t/Anime Action`. The former will find show tags that match with **Anime** and **Action** in the `TAG` parameter while the latter will show you that it is an **invalid command format**. This is only applicable to the `TAG` parameter.

</div>

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Saving the data 

Trackermon data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---
### Sorting the shows
[TO CHANGE AFTER REWORK]

Sort shows based on the input prefix. 

Format: `sort [sna/] [snd/] [ssa/] [ssd/] [so/]…​`
* Use sna/ to sort name in ascending order.
* Use snd/ to sort name in descending order.
* Use ssa/ to sort status in ascending order.
* Use ssd/ to sort status in descending order.
* If there are no prefix, it will sort by name in ascending order.
* If both prefixes for ascending and descending are used for the same attribute, it will only sort by ascending.
* If both prefixes for name and status are used, by default, it will sort by name then status.
* Use so/ to sort by status then name.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## FAQ

_Details coming soon ..._

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Command summary

| Action     | Format, Examples                                                                                                                                  |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME s/STATUS [t/TAG]…​` <br> e.g., `n/ReZero s/watching t/Anime`                                                                          |
| **Clear**  | `clear`                                                                                                                                           |
| **Comment** | `comment <INDEX> [c/<COMMENT>]`  eg., `comment 2 c/Bad`                                                                                               |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                               |
| **Edit**   | `edit INDEX [n/NAME] [s/STATUS] [t/TAG]…​` <br> e.g., `n/ReZero s/watching t/Anime`                                                               |
| **Exit**   | `exit`                                                                                                                                            |
| **Find**   | `find KEYWORD`<br> e.g., `find hero`<br><br>`find [n/NAME] [s/STATUS] [t/TAG]…​`<br>e.g., `find n/Shingeki no kyojin s/watching t/Anime t/Seinen` |
| **List**   | `list`                                                                                                                                            |
| **Sort**   | `sort [sna/] [snd/] [ssa/] [ssd/] [so/]`                                                                                                          |
|


[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Glossary

| Term                               | Description                                                                                                                                                                            |
|------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **AND search**                     | AND search finds all of the keywords.  For example, `find n/Shutter Island` returns only results that contain Shutter and Island.                                                      |
| **Command Line Interface (CLI)**   | A Command Line Interface connects a user to a computer program or operating system. Through the CLI, users interact with a system or application by typing in text (commands).         | 
| **Graphical User Interface (GUI)** | A form of user interface that allows users to interact with electronic devices through graphical icons instead of text-based user interfaces, typed command labels or text navigation. |
| **Parameter**                      | Information passed in as part of a command with its type identified by a prefix (e.g. `NAME`)                                                                                          |
| **Prefix**                         | Characters used to identify the following parameter (e.g. `n/` is the prefix for the parameter `NAME`)                                                                                 |
| **JavaFX**                         | JavaFX is a set of Java graphics libraries for creating Java GUI applications                                                                                                          |
| **int**                            | A primitive data type of Java that has the maximum value of 2<sup>31</sup>-1 and the minimum value of -2<sup>31</sup>                                                                  |

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)
