---
layout: page
title: User Guide
---

Expense Expert is a **desktop app for keeping track of your expenses, and managing your budget, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Expense Expert can get your expense management done faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ExpenseExpert.jar` from [here](https://github.com/AY2122S2-CS2103T-W09-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Expense Expert.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all the expenses you have recorded.

   - **`add`** `d/Lunch at VivoCity $/12.95 c/Food` : Adds a new expense of $12.95 for Lunch at Vivocity to your list of expenses

   - **`delete`** `3` : Deletes the 3rd expense shown in the current list.

   - **`quit`** : Exits the app for you.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add d/DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `add d/Dinner at ECP`.

- Items in square brackets are optional.<br>
  e.g `d/DESCRIPTION [c/CATEGORY]` can be used as `d/Dinner at ECP c/Food` or as `d/Dinner at ECP`.

- Parameters can be in any order.<br>
  e.g. if the command specifies `d/DESCRIPTION $/AMOUNT`, `$/AMOUNT d/DESCRIPTION` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `$/12.99 $/13.50`, only `$/13.50` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `quit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Adding an expense : `add`

Adds an expense to the list.

Format: `add d/EXPENSE_TITLE a/AMOUNT c/CATEGORY`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An expense can have any number of tags (including 0)
</div>

Examples:

- `add n/Dinner at Privé a/35 t/Extra expenditure`
- `add n/Groceries a/50 t/Essentials`

### Listing all expenses : `list`

Shows a list of all expenses recorded in Expense Expert.

Format: `list`

### Editing an expense : `edit`

Edits an existing expense in the list. You only need to specify the index and the syntax of the field you want to edit. Use `list` before executing the command to identify the index of the expense you want to edit.

Format: `edit INDEX [d/DESCRIPTION] [a/AMOUNT] [c/CATEGORY]`

Examples:

- `edit 1 a/40`
- `edit 2 d/Groceries from Sheng Shiong`

### Deleting an expense : `delete`

Deletes the specified expense from the list. Use `list` before executing the command to identify the index of the expense you want to delete.

Format: `delete INDEX`

Examples:

- `delete 1`

### Exiting the program : `quit`

Exits the program.

Format: `quit`

### Saving the data

Expense Expert data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Expense Expert data are saved as a JSON file `[JAR file location]/data/ExpenseExpert.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Expense Expert will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Expense Expert home folder.

---

## Command summary

| Action     | Format, Examples                                                                                             |
| ---------- | ------------------------------------------------------------------------------------------------------------ |
| **Add**    | `add d/DESCRIPTION $/AMOUNT [c/CATEGORY]` <br> e.g., `d/Lunch at VivoCity $/12.95 c/Food`                    |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                          |
| **Edit**   | `edit INDEX [d/DESCRIPTION] [$/AMOUNT] [c/CATEGORY]`<br> e.g.,`edit 2 d/Lunch at Harbourfront Centre $/6.50` |
| **List**   | `list`                                                                                                       |
| **Help**   | `help`                                                                                                       |
| **Quit**   | `quit`                                                                                                       |
