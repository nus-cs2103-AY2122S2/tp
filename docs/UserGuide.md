---
layout: page
title: User Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

IBook is an inventory recording system for storekeepers to manage incoming and outgoing products in a store.

--------------------------------------------------------------------------------------------------------------------

## 2. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ibook.jar` from [here](https://github.com/AY2122S2-CS2103T-T09-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your iBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all products.

   * **`add n:Maggie c:noodles e:01/01/2022 p:3.00 d:Maggie noodles`** : Adds a product named `Maggie` to iBook.

   * **`delete`** `3` : Deletes the 3rd product shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. About
### 3.1 Structure of the document

This document is structured in a chronological manner so that you would be able to follow through this guide while using the product. If you feel lost at any point in time, you can always refer to the Table of Contents.

### 3.2 Reading the document

This subsection would introduce you to the different symbols, syntax and technical terms that are used throughout this guide.
It is important to read this section before proceeding further to avoid getting confused!

#### 3.2.1 Special symbols

**Additional Information**

Text that appear in an information box indicates additional information that may be useful to know.

<div markdown="block" class="alert alert-info">

:information_source: **Information:**
Example additional information.

</div>

**Caution**

Text that appear in a caution box should be followed carefully, else unintended consequences might arise.

<div markdown="block" class="alert alert-warning">

:exclamation: **Caution:**
Example warnings.

</div>

<div style="page-break-after: always;"></div>

**Tip**

Text that appear in a tip box are useful for improving your experience with iBook.

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**
Example tip.

</div>

#### 3.2.2 Sections of the Application Window

The application window is divided into a command box, results window as well as a table that includes all the products.
![Ui](images/Ui.png)

#### 3.2.3 Navigating around

The main mode of navigation in iBook is through the Command Line Interface (CLI). You can enter commands into the command box and press `Enter` to execute them. The results window would then display the results from executing the command. The table would also update accordingly based on the command ran.

Alternatively, you can also interact with the application through buttons, such as the `Add Product` button, where a popup would be displayed for you to enter the different fields once it is clicked.

#### 3.2.4 Command Format

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n:NAME`, `NAME` is a parameter which can be used as `add n:Maggie`.

* Items in square brackets are optional.<br>
  e.g `n:NAME [t:TAG]` can be used as `n:Maggie c:noodle` or as `n:Maggie`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n:NAME c:CATEGORY`, `c:CATEGORY n:NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `exit`, `list`, `expired`, `out-of-stock`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

#### 3.2.5 Command Parameters

| Parameter      | Description                                               |
|:---------------|:----------------------------------------------------------|
| `NAME`         | Name of the product/item.                                 |
| `CATEGORY`     | Category of the product.                                  |
| `EXPRIRY_DATE` | Expiry date of the item.                                  |
| `PRICE`        | Price of the product. A valid price is a positive number. |
| `DESCRIPTION`  | Description of the product.                               |

--------------------------------------------------------------------------------------------------------------------

## 4. Features

### 4.1 Product Commands

#### 4.1.1 Listing all products : `list`

Shows a list of all products in the application.

Format: `list`

#### 4.1.2 Adding a product : `add`

Adds a new product to the application.

Click the add product button on the left of command input to add a new product.

*Alternatively*, by using command,

Format: `add n:NAME c:CATEGORY e:EXPRIRY_DATE p:PRICE d:DESCRIPTION`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Only a single product would be added at a time
</div>

Examples:
* `add n:Maggie c:noodles e:01/01/2022 p:3.00 d:Maggie noodles`

#### 4.1.3 Updating products : `update`

Updates the product at the specified INDEX.

Click the yellow edit icon on the right side of each product to update the product.

*Alternatively*, by using command,

Format: `update INDEX [TAG:NEW_VALUE ...]`

* Updates the product at the specified `INDEX`. The index refers to the index number shown in the displayed product list. The index must be **a positive integer**(1, 2, 3, …)
* At least one of the optional fields must be provided.
* Existing values will be updated to the new values.

Examples:
* `update 2 p:14.99` Updates the price of the 2nd product to be `14.99`.
* `update 3 c:bread d:ABC brand` Updates the category of the 3rd product to `bread` and its description to `ABC brand`.

#### 4.1.4 Deleting products : `delete`

Deletes the product at a specified INDEX.

Click the red delete icon on the right side of each product to delete the product.

*Alternatively*, by using command,

Format: `delete INDEX`

* Deletes the product at the specified `INDEX`.
* The index refers to the index number shown in the displayed product list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd product in the iBook.

*Alternatively*, we are able to delete all items that match one or more (tag, value) pairs.

Format: `delete [TAG:VALUE ...]`

* Deletes all products that match the (tag, value) pair.
* Must include at least one (tag, value) pair.

Example: `delete n:Bread`

#### 4.1.5 Finding certain products : `find`

Finds products that fit a certain filter in the application.

Format: `find [TAG:VALUE ...]`

Tags and their values: `n:NAME` `c:CATEGORY` `p:PRICE` `d:DESCRIPTION`

Examples:

`find n:Water` lists all products that has Water as name.

`find n:Bread c:Food` lists all products that has Bread as name and category as Food.

`find c:Food` lists all products that has category as Food.

#### 4.1.6 Looking for expired certain products : `expired`

Finds products that contain expired items.

Format: `expired`

#### 4.1.7 Looking for products that are out of stock : `out-of-stock`

Lists products that are out of stock.

Format: `out-of-stock`

### 4.2 Item Commands *[coming soon]*

### 4.3 Miscellaneous Commands

#### 4.3.2 Exiting the program : `exit`

Exits the program.

Format: `exit`

## 5. Storage

### 5.1 Saving the data

iBook's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 5.2 Editing the data file

iBook's data are saved as a JSON file `[JAR file location]/data/ibook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, iBook will discard all data and start with an empty data file at the next run.
</div>

### 5.3 Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 6. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous iBook home folder.

--------------------------------------------------------------------------------------------------------------------

## 7. Command summary

### 7.1 Product

| Action           | Format, Examples                                                                                                                      |
|------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| **Add**          | `add n:NAME c:CATEGORY e:EXPRIRY_DATE p:PRICE d:DESCRIPTION` <br> e.g., `add n:Maggie c:noodles e:01/01/2022 p:3.00 d:Maggie noodles` |
| **List**         | `list`                                                                                                                                |
| **Update**       | `update INDEX [TAG:NEW_VALUE ...]` <br> e.g.,`update 2 n:Apple`                                                                       |
| **Delete**       | `delete INDEX`<br> e.g., `delete 3`                                                                                                   |
| **Find**         | `find [TAG:VALUE]` <br> e.g., `find n:Maggie` `find c:noodles` <br>`find n:Chocolate Bread p:3.00`                                    |
| **Expired**      | `expired`                                                                                                                             |
| **Out of Stock** | `out-of-stock`                                                                                                                        |

### 7.2 Item *[Coming soon]*

### 7.3 Miscellaneous

| Action   | Format, Examples |
|:---------|:-----------------|
| **Exit** | `exit`           |
