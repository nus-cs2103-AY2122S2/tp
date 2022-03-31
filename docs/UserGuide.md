---
layout: page
title: User Guide
---

IBook is an inventory recording system for storekeepers to manage incoming and outgoing products in a store.
It is designed for fast use through **command line interface**(CLI), complemented by a **graphical user interface**(GUI)
for convenience use.

--------------------------------------------------------------------------------------------------------------------

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ibook.jar` from [here](https://github.com/AY2122S2-CS2103T-T09-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your iBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all products.

   * **`add n:Maggie Mee c:noodles e:2022-01-01 p:3.00 d:curry flavour`** : Adds a product named `Maggie Mee` to iBook.

   * **`delete`** `3` : Deletes the 3rd product shown in the displayed list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 2. About
### 2.1 Structure of the document

This document is structured in a chronological manner so that you are able to follow through this guide while using the product. If you feel lost at any point in time, you can always refer to the Table of Contents.

### 2.2 Reading the document

This subsection will introduce you to the different symbols, syntax and technical terms that are used throughout this guide.
It is important to read this section before proceeding further to avoid getting confused!

#### 2.2.1 Special symbols

**Additional Information**

Text that appears in an information box indicates additional information that may be useful to know.

<div markdown="block" class="alert alert-info">

:information_source: **Information:**
Example additional information.

</div>

**Caution**

Text that appears in a caution box should be followed carefully, else unintended consequences might arise.

<div markdown="block" class="alert alert-warning">

:exclamation: **Caution:**
Example warnings.

</div>

**Tip**

Text that appears in a tip box are useful for improving your experience with iBook.

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**
Example tip.

</div>

<div style="page-break-after: always;"></div>

#### 2.2.2 Sections of the Application Window

The application window is divided into a command box, results window as well as a table that includes all the products.
![Ui](images/Ui.png)

#### 2.2.3 Navigating around

The main mode of navigation in iBook is through the Command Line Interface (CLI). You can enter commands into the command box and press `Enter` to execute them. The results window would then display the results from executing the command. The table would also update accordingly based on the command ran.

Alternatively, you can also interact with the application through buttons, such as the `Add Product` button, where a popup would be displayed for you to enter the different fields once it is clicked.

#### 2.2.4 Command Format

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are additional user inputs supplied to a command.<br>
  e.g. in `add n:NAME`, `NAME` is an input which can be supplied as `add n:Maggie Mee`.

* Inputs in square brackets are optional.<br>
  e.g. if the command specifies `n:NAME [t:TAG]`, both `n:Maggie Mee c:noodle` and `n:Maggie Mee` are acceptable.

* Inputs of a command can be in any order.<br>
  e.g. if the command specifies `n:NAME c:CATEGORY`, `c:CATEGORY n:NAME` is also acceptable.

* Extra inputs for commands that do not take in any (such as `exit`, `list`, `expired`, `out-of-stock`) will be ignored.<br>
  e.g. the command `exit 123` will be interpreted as `exit`.

</div>

#### 2.2.5 Command inputs

| Input            | Description                                                            |
|:-----------------|:-----------------------------------------------------------------------|
| `NAME`           | Name of the product.                                                   |
| `CATEGORY`       | Category of the product.                                               |
| `PRICE`          | Price of the product. A valid price is a positive number.              |
| `DESCRIPTION`    | Description of the product.                                            |
| `EXPRIRY_DATE`   | Expiry date of the item.                                               |
| `QUANTITY`       | Quantity of the item.                                                  |
| `DISCOUNT_RATE`  | Percentage of discount given to an item once it nears the expiry date. |
| `DISCOUNT_START` | Days before the expiry date to start the discount.                     |

--------------------------------------------------------------------------------------------------------------------

## 3. Features

### 3.1 Product Commands

#### 3.1.1 Listing all products : `list`

Shows the full list of all products in iBook.

Format: `list`

#### 3.1.2 Adding a product : `add` 

Adds a new product to iBook.

Click the <img align="center" src = "images/ui-icons/add-product.png" alt="Add Product" height = "25"/>  button on the 
left of command input to add a new product.

A pop-up window will appear, allowing you to fill in the details for name, expiry date, price, description. 
Optionally, you can also fill in the category, discount rate and discount start. 

After filling in the required fields, click 
<img align="center" src = "images/ui-icons/add-icon.png" alt="Add Product" height = "25"/> 

*Alternatively*, by using command,

Format: `add n:NAME c:CATEGORY e:EXPRIRY_DATE p:PRICE d:DESCRIPTION dr:DISCOUNT_RATE ds:DISCOUNT_START`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Only a single product would be added at a time
</div>

Examples:
* `add n:Maggie Mee c:noodles e:2022-01-01 p:3.00 d:curry flavour dr:25 ds:10`

#### 3.1.3 Updating products : `update`

Updates the product at the specified INDEX.

Click <img align="center" src = "images/ui-icons/edit-color.png" alt="edit button" height = "25"/> on the right side of each product to update the product.

*Alternatively*, by using command,

Format: `update INDEX [TAG:NEW_VALUE ...]`

* Updates the product at the specified `INDEX`. The index refers to the index number shown in the displayed product list. The index must be **a positive integer** (1, 2, 3, …)
* At least one of the optional fields must be provided.
* Existing values will be updated to the new values.

Examples:
* `update 2 p:14.99` Updates the price of the 2nd product to be `14.99`.
* `update 3 c:bread d:ABC brand` Updates the category of the 3rd product to `bread` and its description to `ABC brand`.

#### 3.1.4 Deleting products : `delete`

Deletes the product at a specified INDEX.

Format: `delete INDEX`

* Deletes the product at the specified `INDEX`.
* The index refers to the index number shown in the displayed product list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd product in the iBook.

*Alternatively*, click the <img align="center" src = "images/ui-icons/trash-2-color.png" alt="Edit" height = "25"/> on the right side of each product to delete the product.

<div markdown="block" class="alert alert-primary">

:bulb: You can delete all items that match one or more (tag, value) pairs.

Format: `delete [TAG:VALUE ...]`

* Deletes all products that match the (tag, value) pair.
* Must include at least one (tag, value) pair.

Example: `delete n:Bread`

</div>

#### 3.1.5 Finding certain products : `find`

Finds products that fit certain filters given by the user.

Format: `find [TAG:VALUE ...]`

Tags and their values: `n:NAME` `c:CATEGORY` `p:PRICE` `d:DESCRIPTION`

Examples:

`find n:Water` lists all products that contains "Water" in its name.

`find n:Bread c:Food` lists all products that contains "Bread" in its name and category as Food.

`find c:Food` lists all products that has category as Food.

#### 3.1.6 Looking for products having expired items : `expired`

Finds products that contain expired items.

Format: `expired`

*Alternatively*, click on the menu bar `Actions` > `Find expired`

#### 3.1.7 Looking for products that are out of stock : `out-of-stock`

Lists products that are out of stock.

*Alternatively*, click on the menu bar `Actions` > `Find out of stock items`

Format: `out-of-stock`

#### 3.1.8 Updating all products : `update-all`

Updates all products in the displayed list.

Format: `update-all [TAG:VALUE ...]`

Examples:

`update-all c:fruits` updates all products in current displayed list to have category `fruits`.

`update-all p:5.00` updates all products in current displayed list to have price `5.00`.

#### 3.1.9 Deleting all products : `delete-all`

Deletes all products in the displayed list.

Format: `delete-all`

### 3.2 Item Commands

#### 3.2.1 Adding an item to a product : `add-item`

#### 3.2.2 Updating an item of a product : `update-item`

Updates an item of a specified product in iBook.

Format: `update-item INDEX-INDEX [TAG:VALUE ... ]`

* The first index refers to the index of product.
* The second index refers to the index of item.
* At least one (tag, value) pair must be provided.

Examples:

`update-item 1-2 q:10` updates the 2nd item of the 1st product in the displayed list to have quantity `10`.

`update-item 2-1 e:2022-08-01` updates the 1st item of the 2nd product in the displayed list to have expiry date `01 Aug 2022`.

#### 3.2.3 Deleting an item from a product : `delete-item`

Deletes the item at a specified INDEX.

Format: `delete-item INDEX`

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed product list.
* The index **must be a positive integer pair** 1-1, 3-2, 2-1, …

Examples:
* `list` followed by `delete 2` deletes the 2nd product in the iBook.

*Alternatively*, click the <img align="center" src = "images/ui-icons/trash-2-color.png" alt="Edit" height = "25"/> on the right side of each product to delete the product.

#### 3.2.4 Finding items that are expiring soon: `remind`

Lists items that are expiring within a certain number of days

Format: `remind NUMBER_OF_DAYS`

Examples: `remind 10` lists items that are expiring 10 days from now.

### 3.3 Miscellaneous Commands

#### 3.3.1 Clearing all data : `clear`

Clears all data in iBook.

Format: `clear`

#### 3.3.2 Undoing most recent changes : `undo`

Undoes the most recent changes(actions involving add/update/delete product/item) made to iBook.

Format: `undo`

#### 3.3.3 Redoing most recent undone changes : `redo`

Redoes the most recent undone changes made to iBook.

Format: `undo`

#### 3.3.2 Exiting the program : `exit`

Exits iBook.

Format: `exit`

## 4. Storage

### 4.1 Saving the data

iBook's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 4.2 Editing the data file

iBook's data are saved as a JSON file `[JAR file location]/data/ibook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, iBook will discard all data and start with an empty data file at the next run.
</div>

### 4.3 Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 5. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous iBook home folder.

--------------------------------------------------------------------------------------------------------------------

## 6. Glossary

| Term               | Meanings                                                         |
|--------------------|------------------------------------------------------------------|
| **Main stream OS** | `Windows, Linux, Unix, MacOS`                                    |
| **Products**       | `Goods that are unique in name, price, category and description` |
| **Items**          | `Copies of products that have different expiry dates`            |
| **To be added**    | `to-be-added`                                                    |

--------------------------------------------------------------------------------------------------------------------

## 7. Command summary

### 7.1 Product Commands

| Action           | Format, Examples                                                                                                                                                                         |
|------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**          | `add n:NAME c:CATEGORY e:EXPRIRY_DATE p:PRICE d:DESCRIPTION dr:DISCOUNT_RATE  ds:DISCOUNT_START` <br> e.g., `add n:Maggie Mee c:noodles e:01/01/2022 p:3.00 d:curry flavour dr:50 ds:10` |
| **List**         | `list`                                                                                                                                                                                   |
| **Update**       | `update INDEX [TAG:NEW_VALUE ...]` <br> e.g.,`update 2 n:Apple`                                                                                                                          |
| **Delete**       | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                      |
| **Find**         | `find [TAG:VALUE]` <br> e.g., `find n:Maggie` `find c:noodles` <br>`find n:Chocolate Bread p:3.00`                                                                                       |
| **Expired**      | `expired`                                                                                                                                                                                |
| **Out of Stock** | `out-of-stock`                                                                                                                                                                           |

### 7.2 Item Commands

| Action     | Format, Examples             |
|------------|------------------------------|
| **Remind** | `remind DAYS` <br> remind 10 |


### 7.3 Miscellaneous Commands

| Action    | Format, Examples |
|:--------- |:-----------------|
| **Clear** | `clear`          |
| **Undo**  | `undo`           |
| **Redo**  | `redo`           |
| **Exit**  | `exit`           |
