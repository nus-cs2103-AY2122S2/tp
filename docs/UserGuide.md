---
layout: page
title: User Guide
---

McKitchener is a **desktop app for managing recipes, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, McKitchener can retrieve recipes you're looking for faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

---

## Features

### Create recipe
Create a recipe by specifying its name, ingredients, and steps to prepare the dish.<br>
Usage: `create recipe n/ <name of recipe> i/ <ingredients separated by commas> s/ <steps separated by commas>` <br>
Example:
- `create recipe n/ Aglio Olio i/ spaghetti 56 grams, garlic 3 cloves, bacon 3 slices, olive oil 110 ml, salt ?, pepper ? s/ Cook the spaghetti until al dante, Saute the chopped garlic, Toss spaghetti in the sauce, taste and season with salt and black pepper`
  - This creates a recipe for "Aglio Olio" with 56 grams of spaghetti, 3 cloves of garlic, 3 slices of bacon, 110 ml of olive oil and a non-fixed amount of salt, and pepper (to taste) with the steps of:
    1. Cook the spaghetti until al dante,
    2. Saute the chopped garlic,
    3. Toss the spaghetti in the sauce,
    4. Taste and season with salt and black pepper.

### List recipes
Displays a list of recipes that the user has stored locally. If this is the first time that the user runs the application, sample recipes will be shown instead. Recipes shown here would be a shortened version of the recipe and only display its name and tags (type of cuisine). For the full details of the recipe, refer to [`view`](###view-recipe) <br>
Note: The numbers associated with the recipes would be used for other commands.<br>
Usage: `list`

### View recipe
View the contents of an existing stored recipe based on the number it is associated with in the `list`. <br>
Usage: `view <recipe number>` <br>
Example:
- view 1
    - This would display the full contents of the first recipe in the list which includes its ingredients and steps to prepare the dish.

### Delete recipe
Removes a stored recipe based on the number it is associated with in the `list` <br>
Usage: `delete <recipe number>` <br>
Example:
- delete 1
    - This would delete the first entry in the list of recipes.

### Store recipe
Store the recipe in a human-readable text file on the user's computer and is modifiable through a text editor if the user is familiar with the format (JSON). <br>
Usage: (Automatically updates text file upon each modifying (writing) operation).

### Load recipe
Load existing recipes from the text file on the user's computer. Modifications to this file would be displayed accordingly as long as the format is followed. <br>
Usage: (Automatically loads the recipes upon launching the application).

<<<<<<< HEAD
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

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

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

The Recipe data are saved in the hard disk automatically after executing commands that change the data. There is no need to save manually.

### Editing the data file

The recipe data are saved as a JSON file `[JAR file location]/data/recipe.json`. 
Advanced users, you are definitely welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------
=======
---
>>>>>>> upstream/master

## FAQ
(To be filled)

---

## Command Summary
(To be filled)

| Action                     | Command format                                                                                          |
|----------------------------|---------------------------------------------------------------------------------------------------------|
| add a new recipe           | `create recipe n/ <name of recipe> i/ <ingredients separated by commas> s/ <steps separated by commas>` |
| list all available recipes | `list`                                                                                                  |
| view specific recipe       | `view <recipe number from list>`                                                                        |
| delete recipe              | `delete <recipe number from list>`                                                                      |


