---
layout: page
title: User Guide
---

AgentSee is a **desktop app for property agents to manage clients.** It optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AgentSee can get your client management tasks done faster than traditional GUI apps.
* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed in your Computer. If you do not have it installed, download it [here](https://www.oracle.com/java/technologies/downloads/#java11).

2. Download the latest application .jar file from our GitHub [here](https://github.com/AY2122S2-CS2103T-T11-2/tp/releases).

3. Copy the .jar file to any folder which you wish to store the AgentSee application in. It is recommended that the folder is **empty** before copying in the .jar file.

4. Double-click the file to start the app. A GUI similar to the one below should appear in a few seconds. Note that sample data will be automatically added when running the .jar file for the first time.<br>
   ![Ui](images/Ui.png)

5. Input a command in the command box and press Enter on your keyboard to execute it.
   - E.g. `add-b n/David p/62353535` 

6. Refer to the [Features](#features) section further below for full details of possible commands to input.

--------------------------------------------------------------------------------------------------------------------

## Features

The Features section will be split into 3 subsections for:
1. General commands
2. Buyer-specific commands
3. Seller-specific commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are inputs to be supplied by the user.<br>
  e.g. In `addbuyer n/NAME`, `NAME` is an input such as `addbuyer n/Chok Hoe`.

* Items in square brackets are optional inputs.<br>
  e.g In `n/NAME [t/TAG]`, the user can input `n/Chok Hoe t/funny` or simply `n/Chok Hoe`.

* Inputs with `…`​ after them can be used more than once.<br>
  e.g. In `[t/TAG]…​`, the user can input `t/friend` or `t/friend t/family` etc.

* Input can be in any order.<br>
  e.g. Even if the command specifies `n/NAME p/PHONE_NUMBER` in the documentation, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If an input is expected only once in the command, but you specified it multiple times, only the **last occurrence** of the parameter will be taken.<br>
  e.g. If you specify `addbuyer n/Chok Hoe p/12341234 p/56785678`, only `p/56785678` will be taken.

* Additional input for single-word commands (such as `help`, `list-s`, `exit` and `clear-b`) will be ignored.<br>
  e.g. If the input specifies `help 123`, it will be interpreted as `help`.

* **Property** refers to different things dependent on whether it is used under a Buyer-specific or Seller-specific command.
  * For Buyer-specific commands, it refers to the **general property they are looking to buy**. E.g. "A HDB in Serangoon for $500,000 to $600,000".
  * For Seller-specific commands, it refers to the **exact property they are looking to sell**. E.g. "A HDB at Blk 333, Kent Ridge Drive #03-3333 for $700,000 to $710,000".
  * Both property types include a **Location**, **Price range** and **House type**. E.g. In the 1st example, HDB is the house type, Serangoon is the location, and $500,000 to $600,000 (inclusive) is the price range.
  * However, Seller-specific property has an **Address** defining the exact address their property is located. E.g. In the 2nd example, address is Blk 333, Kent Ridge Drive #03-3333.
* The **displayed seller list** & **displayed buyer list** are the sellers and buyers shown on the UI of the application respectively. They do not refer to the entire list of buyers & sellers.
* If we do refer to the whole list of buyers or sellers, we will just use **buyer list** or **seller list** respectively.

</div>

### `help`

Show a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### `exit`

Exit the program.

Format: `exit`

[back to start of section](#features)

### `list-b`

Show the buyer list.

Format: `list-b`

### `addbuyer`

Add a buyer to the buyer list.

Format: `addbuyer n/NAME p/PHONE_NUMBER [t/TAG]...`

* The order of inputs can be in any order.
* The `NAME` and `PHONE_NUMBER` cannot be empty. E.g. `n/` or `n/` followed by only spaces or tabs.

Examples:
* `addbuyer n/Yu Qi p/98765432` adds a new buyer with name `Yu Qi` and phone number `98765432`
* `addbuyer n/Janald p/12345678 t/friend t/criminal` adds a new buyer with name `Janald`, phone number of `12345678`, and tags of `friend` and `criminal`

### `add-ptb`

Add a new property for the specified buyer.

Format: `add-ptb INDEX l/LOCATION pr/PRICE_RANGE h/HOUSE_TYPE`

* Adds a new property that the buyer at `INDEX` is hoping to buy. The index refers to the index number shown in the displayed buyer list. The index **must be a positive whole number** 1, 2, 3, …​
* The order of the inputs can be in any order.
* None of the inputs can be empty. E.g. Typing `l/` instead of `l/Bishan` will result in an error.
* The `PRICE_RANGE` is specified in the following format: `lower,upper`.
* The `PRICE_RANGE` must be a valid **positive whole number** with `lower` being less than or equal to `upper`.
* The `HOUSE_TYPE` can be defined as any of the following:
  * `any`
  * `apartment`
  * `bungalow`
  * `condominium` or `condo`
  * `colonia`
  * `hdb` or `hdb_flat`
  * `semi-detached` or `semi-d` or `semidetached` or `semid`
  * `mansion`
* Any other `HOUSE_TYPE` will not be accepted.
* `LOCATION` can be any non-empty input, but do use appropriate locations for your own utility. E.g. `Bishan` or `Marymount`

Examples:
* `add-ptb 1 l/Bishan pr/400000,500000 h/hdb` means that 1st buyer in the displayed buyer list wishes to buy a HDB in Bishan for any price from $400,000 to $500,000. 

### `appt-b`

Create an appointment with a certain buyer.

Format: `appt-b INDEX time/TIME`

* Create an appointment with the buyer at the specified `INDEX`. The index refers to the index number shown in the displayed buyer list. The index **must be a positive whole number** 1, 2, 3, …​
* The input `TIME` is in a `yyyy-mm-dd-hh-mm` format of: the year, month, day of the month, hour and minute. Use the example below for reference.
* The time entered must be a time in the future.

Examples:
* `appt-b 1 time/2023-01-01-12-12` sets a new appointment for the 1st buyer on the displayed buyer list on 1st January 2023 and 12:12pm.

### `edit-b`

Edit an existing buyer in the displayed buyer list.

Format: `edit-b INDEX [n/NAME] [p/PHONE] [t/TAG]…​ [time/APPOINTMENT] [h/HOUSE_TYPE] [l/LOCATION] [pr/PRICE_RANGE]` 

* Edit the buyer at the specified `INDEX`. The index refers to the index number shown in the displayed buyer list. The index **must be a positive whole number** 1, 2, 3, …​
* At least one of the optional inputs must be provided.
* The order of the inputs can be in any order.
* Existing values will be updated to the input values.
* When editing tags, all the existing tags of the buyer will be **removed**, and replaced with the specified tags in the input. Hence, you can remove all the buyer’s tags by typing `t/` without specifying any tags after it.
* The house-related inputs (`pr/`, `l/`, `h/`) cannot be edited until a Property is added (See how to add one here).

Examples:
* `edit-b 1 n/Chua` Edits only the name of the 1st buyer to be `Chua`.
* `edit-b 2 n/Betsy Crower t/` Edits the name of the 2nd buyer to be `Betsy Crower` and clears all existing tags.

### `find-b`

Find buyers whose selected **field** contain any of the given keywords.


Format: `find-b field/KEYWORD1 [MORE_KEYWORDS]`

* The **fields**` are:
  * name `n/`
  * phone `p/`
* The search is **case-insensitive**. E.g `find-b n/hans` will match buyers with `Hans` and `HanS` in their name.
* The **order** of the keywords does not matter. e.g. `find-b n/Hans Bo` is equivalent to `find-b n/Bo Hans`.
* All partial words will be matched e.g. `Han` keyword will match `Hans` and `Han`.
* However, if the keyword is larger than the item itself, it will not match e.g. `Hans` keyword will not match `Han` in name
* Buyers matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find-b n/John` displays buyers whose names are `john` and `John Doe`.

### `delete-b`

Delete the specified buyer from the displayed buyer list.

Format: `delete-b INDEX`

* Deletes the buyer at the specified `INDEX`. The index refers to the index number shown in the displayed buyer list. The index **must be a positive whole number** 1, 2, 3, …​
* The displayed list refers to the buyer list shown after a `list-b`, `sort-b` or `find-b` was previously executed.

Examples:
* `list-b` followed by `delete-b 2` deletes the 2nd buyer in the entire buyer list.
* `find-b Betsy` followed by `delete-b 1` deletes the 1st buyer in the result of the `find-b` command.

### `clear-b`

Clear all buyers from the saved buyer list.

Format: `clear-b`

### `sort-b`

Sort all the buyers according to the orders specified.

Format: `sort-b [o/FIELDS]`

* The `FIELDS` are:
  * `appointment` time
  * `name`

* If the input is omitted, `sort-b` will simply sort by the **name** of the buyer in **descending alphabetical order**.
  * E.g. Alex, Bob, Cartman
* `appointment` will sort by the appointment time, from the earliest appointment first.

Examples:
* `sort-b`
* `sort-b o/name`
* `sort-b o/appointment`

[back to start of section](#features)

### `list-s`

Show the seller list.

Format: `list-s`

### `addseller`

Add a seller to the seller list.

Format: `addseller n/NAME p/PHONE_NUMBER [t/TAG]...`

* The order of inputs can be in any order.
* The `NAME` and `PHONE_NUMBER` cannot be empty. E.g. `n/` or `n/` followed by only spaces or tabs.

Examples:
* `addseller n/Yu Qi p/98765432` adds a new seller with name `Yu Qi` and phone number `98765432`
* `addseller n/Janald p/12345678 t/friend t/criminal` adds a new seller with name `Janald`, phone number of `12345678`, and tags of `friend` and `criminal`

### `add-pts`

Add a new property for the specified seller.

Format: `add-pts INDEX l/LOCATION pr/PRICE_RANGE h/HOUSE_TYPE a/ADDRESS`

* Adds a new property that the seller at `INDEX` is hoping to sell. The index refers to the index number shown in the displayed seller list. The index **must be a positive whole number** 1, 2, 3, …​
* The order of the inputs can be in any order.
* None of the inputs can be empty. E.g. Typing `l/` instead of `l/Bishan` will result in an error.
* The `PRICE_RANGE` is specified in the following format: `lower,upper`.
* The `PRICE_RANGE` must be a valid **positive whole number** with `lower` being less than or equal to `upper`.
* The `HOUSE_TYPE` can be defined as any of the following:
  * `any`
  * `apartment`
  * `bungalow`
  * `condominium` or `condo`
  * `colonia`
  * `hdb` or `hdb_flat`
  * `semi-detached` or `semi-d` or `semidetached` or `semid`
  * `mansion`
* Any other `HOUSE_TYPE` will not be accepted.
* `LOCATION` can be any non-empty input, but do use appropriate locations to maximize your own utility. E.g. `Bishan` or `Marymount`
* `ADDRESS` can be any non-empty input, but do use appropriate address to maximize your own utility. E.g. `Blk 343, Ajax Ave 1121`

  Examples:
* `add-pts 1 l/Ajax pr/800000,900000 h/condo a/Ajax Ave 1, 02-100` means that 1st seller in the displayed seller list wishes to sell a condominium in Bishan for any price from $800,000 to $900,000.

### `appt-s`

Create an appointment with a certain seller.

Format: `appt-s INDEX time/TIME`

* Create an appointment with the seller at the specified `INDEX`. The index refers to the index number shown in the displayed seller list. The index **must be a positive whole number** 1, 2, 3, …​
* The input `TIME` is in a `yyyy-mm-dd-hh-mm` format of: the year, month, day of the month, hour and minute. Use the example below for reference.
* The time entered must be a time in the future.

Examples:
* `appt-s 1 time/2023-01-01-12-12` sets a new appointment for the 1st seller on the displayed seller list on 1st January 2023 and 12:12pm.

### `edit-s`

Edit an existing seller in the displayed seller list.

Format: `edit-s INDEX [n/NAME] [p/PHONE] [t/TAG]…​ [time/APPOINTMENT] [h/HOUSE_TYPE] [l/LOCATION] [pr/PRICE_RANGE] [a/ADDRESS]`

* Edit the seller at the specified `INDEX`. The index refers to the index number shown in the displayed seller list. The index **must be a positive whole number** 1, 2, 3, …​
* At least one of the optional inputs must be provided.
* The order of the inputs can be in any order.
* Existing values will be updated to the input values.
* When editing tags, all the existing tags of the buyer will be **removed**, and replaced with the specified tags in the input. Hence, you can remove all the buyer’s tags by typing `t/` without specifying any tags after it.
* The house-related inputs (`pr/`, `l/`, `h/`, `a/`) cannot be edited until a Property is added (See how to add one here).

Examples:
* `edit-s 1 n/Chua` Edits only the name of the 1st seller to be `Chua`.
* `edit-s 2 n/Betsy Crower t/` Edits the name of the 2nd seller to be `Betsy Crower` and clears all existing tags.


### `find-s`

Find sellers whose selected **field** contain any of the given keywords.


Format: `find-s field/KEYWORD1 [MORE_KEYWORDS]`

* The **fields**` are:
  * name `n/`
  * phone `p/`
* The search is **case-insensitive**. E.g `find-s n/hans` will match buyers with `Hans` and `HanS` in their name.
* The **order** of the keywords does not matter. e.g. `find-bs n/Hans Bo` is equivalent to `find-s n/Bo Hans`.
* All partial words will be matched e.g. `Han` keyword will match `Hans` and `Han`.
* However, if the keyword is larger than the item itself, it will not match e.g. `Hans` keyword will not match `Han` in name
* Buyers matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find-s n/John` displays buyers whose names are `john` and `John Doe`.

### `delete-s`

Delete the specified seller from the displayed seller list.

Format: `delete-s INDEX`

* Deletes the seller at the specified `INDEX`. The index refers to the index number shown in the displayed seller list. The index **must be a positive whole number** 1, 2, 3, …​
* The displayed list refers to the seller list shown after a `list-b`, `sort-b` or `find-b` was previously executed.

Examples:
* `list-s` followed by `delete-s 2` deletes the 2nd buyer in the entire seller list.
* `find-s Betsy` followed by `delete-s 1` deletes the 1st buyer in the result of the `find-s` command.

### `clear-s`

Clear all sellers from the saved seller list.

Format: `clear-s`

### `sort-s`

Sort all the sellers according to the orders specified.

Format: `sort-s [o/FIELDS]`

* The `FIELDS` are:
  * `appointment`
  * `name`

* If the input is omitted, `sort-s` will simply sort by the **name** of the seller in **descending alphabetical order**.
  * E.g. Alex, Bob, Cartman
* `appointment` will sort by the appointment time, from the earliest appointment first.

Examples:
* `sort-s`
* `sort-s o/name`
* `sort-s o/appointment`

[back to start of section](#features)

## Saving the data

AgentSee data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file

AgentSee data are saved as a JSON file `[JAR file location]/data/agensee.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AgentSee will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the json data in your previous AgentSee home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Exit** | `exit`
**List Buyers** | `list-b`
**Add Buyer** | `addbuyer n/NAME p/PHONE_NUMBER [t/TAG]…​` <br> e.g., `addbuyer n/James Ho p/22224444`
**Add Buyer Property** | `add-ptb l/LOCATION pr/PRICE_RANGE h/HOUSE_TYPE` <br> e.g., `add-ptb l/Bishan pr/100000,200000 h/hdb`
**Make Appointment for Buyer** | `appt-b INDEX time/TIME` <br> e.g., `appt-b 1 time/2022-10-10-12-12`
**Edit Buyer** | `edit-b INDEX [n/NAME] [p/PHONE] [t/TAG]…​ [time/APPOINTMENT] [h/HOUSE_TYPE] [l/LOCATION] [pr/PRICE_RANGE]`  <br> e.g., `edit-b 2 n/James Ho p/22224444 `
**Find Buyer** | `find-b field/KEYWORD1 [MORE_KEYWORDS]` <br> e.g., `find-b n/James Jake`
**Delete Buyer** | `delete-b INDEX`<br> e.g., `delete-b 3`
**Clear Buyers** | `clear-b`
**Sort Buyers** | `sort-b [o/FIELDS]` <br> e.g., `sort-b o/name`
**List Sellers** | `list-s`
**Add Seller** | `addseller n/NAME p/PHONE_NUMBER [t/TAG]…​` <br> e.g., `addseller n/James Ho p/22224444`
**Add Seller Property** | `add-pts a/ADDRESS l/LOCATION pr/PRICE_RANGE h/HOUSE_TYPE` <br> e.g., `add-pts a/Blk 343, Rika Ave 1 #09-1231 l/Bishan pr/100000,200000 h/hdb`
**Make Appointment for Seller** | `appt-s INDEX time/TIME` <br> e.g., `appt-s 1 time/2022-10-10-12-12`
**Edit Seller** | `edit-s INDEX [n/NAME] [p/PHONE] [t/TAG]…​ [time/APPOINTMENT] [h/HOUSE_TYPE] [l/LOCATION] [pr/PRICE_RANGE] [a/ADDRESS]`  <br> e.g., `edit-s 2 n/James Ho p/22224444 `
**Find Seller** | `find-s field/KEYWORD1 [MORE_KEYWORDS]` <br> e.g., `find-s n/James Jake`
**Delete Seller** | `delete-s INDEX`<br> e.g., `delete-s 3`
**Clear Sellers** | `clear-s`
**Sort Sellers** | `sort-s [o/FIELDS]` <br> e.g., `sort-s o/name`


[Back to top](#quick-start)
