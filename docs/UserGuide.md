---
layout: page
title: User Guide
---

Trackermon is a **desktop app** for **tracking and managing shows, optimized for use via a
Command Line Interface (CLI)** while still having the **benefits of a Graphical User Interface (GUI)**.
The app allows the user to track and remember what shows they have watched or currently watching.
They can easily look up the list of shows if they need to.

### Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a show: `add`](#adding-a-show-add)
  * [Listing a show: `list`](#listing-all-shows-list)
  * [Requesting help URL: `help`](#requesting-help-url-help)
  * [Finding a show: `find`](#finding-a-show-find)
  * [Deleting a show: `delete`](#deleting-a-show-delete)
  * [Exiting the program: `exit`](#exiting-the-program-exit)
  * [Saving the data](#saving-the-data)
  * [Better Find [coming in v1.3]](#better-find-coming-in-v13)
  * [FAQ](#faq)
  * [Command Summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have `Java 11` or above installed in your Computer.

2. Download the latest version of **Trackermon** [here](https://github.com/AY2122S2-CS2103T-T09-3/tp/releases).

3. Move the file to the folder you want to use as the _home folder_ for **Trackermon**.

4. Double-click the file to start the app.

5. Start communicating with Trackermon using the command box.


Some example commands you can try:

* **`list`** : Lists all shows.

* **`add`** `n/ReZero s/watching t/Anime` : Adds a show **named** `ReZero` with the **tag** `Anime` to Trackermon as **watching**.

* **`delete`** `3` : Deletes the **3rd show** shown in the current list.

* **`exit`** : Exits the app.


[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `add n/<NAME>`, `<NAME>` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/<NAME> s/<STATUS> [t/<TAG>]` can be used as `n/ReZero s/completed t/Anime` or as `n/ReZero s/completed`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/<TAG>]…​` can be used as ` ` (i.e. 0 times), `t/Anime`, `t/Sitcom t/Kdrama` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/<NAME> [t/<TAG>]`, `[t/<TAG>] n/<NAME>` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/You n/Me`, only `n/Me` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `list` ) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

---

### Adding a show: `add`

Adds a new show to Trackermon. Note that the name of the show can only contain alphanumeric characters.

Format: `add n/<NAME> [s/<STATUS>] [t/<TAG>]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/All of us are dead t/Kdrama`
* `add n/All of us are dead s/completed t/Kdrama`
* `add n/All of us are dead`

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

---

### Listing all shows: `list`

Shows a list of all shows in Trackermon.

Format: `list`

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

---

### Requesting help URL: `help`

Shows a URL that redirects the user to Trackermon's user guide.

Format: `help`

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

---

### Finding a show: `find`

Finds the specified show in Trackermon.

Format: `find <KEYWORD>`
* Finds the show with the specified `<KEYWORD>`.
* The keyword refers to the input entered by the user after `find`.
* The keyword **can be a word or number** such as hero, S1,..

Examples:
* `find` followed by `attack` displays all the shows in the list that contain the keyword `attack`.
* `find attack` followed by `delete 1` removes 1st show in results of `find` command.

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

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

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

---

### Exiting the program: `exit`

Exits the program. 

Format: `exit`

* Displays error message and exits the program after 3 seconds.

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

---

### Saving the data 

Trackermon data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

---

### Better Find `[coming in v1.3]`

_Details coming soon ..._

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

---

## FAQ

_Details coming soon ..._

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)

---

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/<NAME> s/<STATUS> [t/<TAG>]…​` <br> e.g., `n/ReZero s/watching t/Anime`
**Delete** | `delete <INDEX>`<br> e.g., `delete 3`
**List** | `list`
**Exit** | `exit`

[<img src="images/toc-icon.jpg" width="50px">](#table-of-contents)