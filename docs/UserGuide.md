---
layout: page
title: User Guide
---

### Table of Contents
* [**Introduction**](#introduction)
* [**User guide navigation**](#user-guide-navigation)
* [**Quick Start**](#quick-start)
* [**Commands**](#commands)
* [**Features**](#features)
  * [Adding a show: `add`](#adding-a-show-add)
  * [Listing all shows: `list`](#listing-all-shows-list)
  * [Requesting help URL: `help`](#requesting-help-url-help)
  * [Finding a show: `find`](#finding-a-show-find)
  * [Deleting a show: `delete`](#deleting-a-show-delete)
  * [Editing a show: `edit`](#editing-a-show-edit)
  * [Exiting the program: `exit`](#exiting-the-program-exit)
  * [Saving the data](#saving-the-data)
  * [Sorting the data](#sorting-the-data)
* [**FAQ**](#faq)
* [**Command Summary**](#command-summary)
* [**Glossary**](#glossary)

---

## Introduction

Always accessing MyAnimeList or IMDB to record down the shows you watched? Or finding it difficult to search the different websites that stored your show information? Fret not because **Trackermon** is the app just for you! But hold on, you may be wondering what is Trackermon?

Trackermon is a **desktop application** for **tracking and managing shows, optimized for use via a Command Line Interface (CLI)** while still having the **benefits of a Graphical User Interface ([GUI](#glossary))**. Trackermon allows you to track and remember what shows you have watched, are currently watching, or plan to watch.

---

## User guide navigation

Before you continue reading the rest of our user guide, the table below displays some important syntax to facilitate your reading.

| Syntax                                                                    | Description                                            |
|---------------------------------------------------------------------------|--------------------------------------------------------|
| **Bold**                                                                  | Important words to note                                |
| `GFM`                                                                     | Command word/prefix/parameter                          |
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

## Commands

Let us look at what makes up a command:

| Component    | Description                                                           |
|--------------|-----------------------------------------------------------------------|
| Command Word | The keyword representing the action of the command                    |
| Prefix       | The keyword to recognise command parameters                           |
| Parameters   | Follows directly behind a prefix and contains the corresponding value |

For exmaple, a command to find a show could look like this:

`find` `n/` `Django` 

In the example above , `find` is the **command word** while `n/` is the **prefix** of the `Djanjo` **parameter**. A list of parameters along with their prefixes and descriptions have been included below for your convenience.

| Component | Prefix | Description                                           |
|-----------|--------|-------------------------------------------------------|
| INDEX     | None   | The index of the show as shown in the show panel list |
| NAME      | n/     | The name to use for a show                            |
| STATUS    | s/     | The status to label for a show                        |
| TAG       | t/     | The tag to label a show                               |
| COMMENT   | c/     | The comment to describe a show                        |
| RATE      | r/     | The rating to give a show                             |

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `add n/<NAME>`, `<NAME>` is a parameter which can be used as `add n/Sex and the City`.

* Items in square brackets are optional.<br>
  e.g `n/<NAME> s/<STATUS> [t/<TAG>]` can be used as `n/ReZero s/completed t/Anime` or as `n/ReZero s/completed`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/<TAG>]…​` can be used as ` ` (i.e. 0 times), `t/Anime`, `t/Sitcom t/Kdrama` etc.

* A whitespace must be included before every prefix.<br>
  e.g. `n/Knives Out t/Suspense` is acceptable but `n/Knives Outt/Suspense` is not.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/<NAME> [t/<TAG>]`, `[t/<TAG>] n/<NAME>` is also acceptable.

* All **names must be unique** and duplicates will be ignored.<br>
  e.g. if you try to add `n/Inception` into the show list that already contains that show, there will be a message telling you that this show already exists in the list.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/Batman n/Superman`, only `n/Superman` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `list` ) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

* The **index** parameter provided should be a [non-zero unsigned integer](https://en.wikipedia.org/wiki/Integer_(computer_science)) within the allowed range of Java’s `int` data type. On top of that, the index should be within the bounds of the show list.<br>
  e.g. If there are 5 shows saved in the show list, then the valid index ranges from 1 to 5. 

</div>

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Features

### Adding a show: `add`

Adds a new show to Trackermon. Note that the name of the show can only contain alphanumeric characters.

Format: `add n/<NAME> s/<STATUS> [t/<TAG>]…​`

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/All of us are dead s/completed`
* `add n/All of us are dead s/completed t/Kdrama`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Listing all shows: `list`

Shows a list of all shows in Trackermon.

Format: `list`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Requesting help URL: `help`

Shows a URL that redirects the user to Trackermon's user guide.

Format: `help`

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Finding a show: `find`

**General Find**: Finds shows in Trackermon by matching the user's input across the name, status and tag parameters.

Format: `find <KEYWORD>`
* Find shows with the specified `<KEYWORD>`.
* The keyword refers to the input entered by the user after `find`.
* The keyword **can be a word or number** such as hero, S1,...
* There must be at **least one keyword** and it **must not be empty**.

Examples:
* `find shingeki` displays all the shows in the list that contain the keyword `shingeki` whether it is a name, status or tag.[put image here after ui update]
* `find shingeki no kyojin` displays all the shows in the list that contain the keyword `shingeki` **or** `no` **or** `kyojin` whether it is a name, status or tag.[put image here after ui update]

**Precise Find** Finds shows in Trackermon by matching the user's input across name, status and tag parameters with prefixes.

Format: `find n/[NAME] s/[STATUS] t/[TAG]`
* **Within a single prefix**, the find command will execute an **AND** search across Trackermon's show list and return all shows that match all keywords that are input by the user in the specific prefix.
* **Across multiple prefixes**, the find command will execute an **AND** search across Trackermon's show list and return all shows that match all the keywords across all prefixes.
* There must be at **least one prefix field** and it **must not be empty**.

Examples:
* `find n/shingeki s/watching t/seinen` displays all the shows in the Trackermon's show list that contain **shingeki** in NAME, **watching** in STATUS, and **seinen** in TAG. [put image here after ui update]
* `find n/Shutter Island s/watching` displays all the shows in the Trackermon's show list that contains **Shutter Island** in NAME, and **watching** in STATUS. [put image here after ui update]

<div markdown="span" class="alert alert-warning">:bulb: **Tip:**
Find is case-insensitive, and the order in which the keywords are entered is irrelevant. Partial words **will** be matched as well. e.g., `shing` will match `shingeki`.
</div>

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
find `find n/shingeki n/no n/2` does not mean `find n/shingeki no 2` The former will only find show names that match with **2**(as mentioned in [features](#features)) while the latter will find all show names that match **shingeki, no and 2**. This applies across the name and tag parameters.
</div>

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Deleting a show: `delete`

Deletes the specified show from Trackermon.

Format: `delete <INDEX>`
* Deletes the show at the specified `<INDEX>`.
* The index refers to the index number shown in the displayed show list. (not overall)
* The index **must be a positive integer** 1,2,3,..

Examples:
* `list` followed by `delete 2` removes 2nd show in Trackermon.
* `find ghibli` followed by `delete 1` removes 1st show in results of `find` command.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---
### Editing a show: `Edit`

Edit the specified show from Trackermon.

Format: `edit <INDEX> [n/<NAME>] [s/<STATUS>] [t/<TAG>]…​`
* Edit the show at the specified `<INDEX>`.
* The index refers to the index number shown in the displayed show list. (not overall)
* The index **must be a positive integer** 1,2,3,..
* At least one field to edit must be provided
* Editing to an existing name is not allowed

Examples:
* `list` followed by `edit 2 n/Sailor Moo` edit 2nd show's name in Trackermon to Sailor Moo.
* `find ghibli` followed by `edit 1 n/Cowman s/completed t/awesome` edits 1st show in results of `find` command.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Exiting the program: `exit`

Exits the program. 

Format: `exit`

* Displays error message and exits the program after 3 seconds.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

### Saving the data 

Trackermon data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---
### Sorting the data

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

| Action     | Format, Examples                                                                                                                                      |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/<NAME> s/<STATUS> [t/<TAG>]…​` <br> e.g., `n/ReZero s/watching t/Anime`                                                                        |
| **Delete** | `delete <INDEX>`<br> e.g., `delete 3`                                                                                                                 |
| **Edit**   | `edit <INDEX> [n/<NAME>] [s/<STATUS>] [t/<TAG>]…​` <br> e.g., `n/ReZero s/watching t/Anime`                                                           |
| **Exit**   | `exit`                                                                                                                                                |
| **Find**   | `find <KEYWORD>`<br> e.g., `find hero`<br><br>`find [n/NAME] [s/STATUS] [t/<TAG>]…​`<br>e.g., `find n/Shingeki no kyojin s/watching t/Anime t/Seinen` |
| **List**   | `list`                                                                                                                                                |
| **Sort**   | `sort [sna/] [snd/] [ssa/] [ssd/] [so/]` |


[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)

---

## Glossary

| Term                               | Description                                                                                                                                                                            |
|------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Command Line Interface (CLI)**   | A Command Line Interface connects a user to a computer program or operating system. Through the CLI, users interact with a system or application by typing in text (commands).         | 
| **Graphical User Interface (GUI)** | A form of user interface that allows users to interact with electronic devices through graphical icons instead of text-based user interfaces, typed command labels or text navigation. |
| **Parameter**                      | Information passed in as part of a command with its type identified by a prefix (e.g. `NAME`)                                                                                          |
| **Prefix**                         | Characters used to identify the following parameter (e.g. `n/` is the prefix for the parameter `NAME`)                                                                                 |
| **JavaFX**                         | JavaFX is a set of Java graphics libraries for creating Java GUI applications                                                                                                          |
| **int**                            | A primitive data type of Java that has the maximum value of (2^31)-1 and the minimum value of -(2^31)                                                                                  |

[return to top <img src="images/toc-icon.png" width="25px">](#table-of-contents)
