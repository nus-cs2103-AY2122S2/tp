---
layout: page
title: User Guide
---
--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

### Deleting a show - `delete`

Deletes a show in Trackermon.

Format: `delete <INDEX>`
* Deletes the show at the specified `<INDEX>`.
* The index refers to the index number shown in the displayed show list. (not overall)
* The index **must be a positive integer** 1,2,3,..

Examples:
* `list` followed by `delete 2` removes 2nd show in Trackermon
* `find ghibli` followed by `delete 1` removes 1st show in results of `find` command

</div>

## Command summary

Action | Format, Examples
--------|------------------
**Delete** | `delete INDEX`<br> e.g., `delete 3`
