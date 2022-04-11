---
layout: page
title: User Guide
---

ReadyBakey is a **desktop app that manages orders and customer contact information, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It assists small bakeries by consolidating all the necessary requirements and information for successful order management.

# Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
   * [Viewing help : help](#help)
   * Person Commands
     * [Adding a customer: addp](#addp)
     * [Delete a customer: deletep](#deletep)
     * [Listing all customers: listp](#listp)
     * [Editing a customer's information : editp](#editp)
     * [Locating customers by specific attribute: findp](#findp)
   * Order Commands
     * [Adding an order : addo](#addo)
     * [Delete an order: deleteo](#deleteo)
     * [Listing all orders: listo](#listo)
     * [Editing an order's information : edito](#edito)
     * [Locating orders by specific attribute: findo](#findo)
     * [Mark orders as Complete: marko](#marko)
     * [Unmark orders as incomplete: unmarko](#unmarko)
     * [Listing all incomplete orders: incompleteo](#incompleteo)
   * [Clearing all Customers and Orders: clear](#clear)
   * [Exiting the program : exit](#exit)
   * [Using Natural Dates](#natural-dates)
   * [Saving the data](#saving-data)
   * [Editing the data file](#editing-data)
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start <a name="quick-start"></a>

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `readybakey.jar` from [here](https://github.com/AY2122S2-CS2103-F09-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ReadyBakey.

4. Open a command window in that folder

5. Run the command `java -jar readybakey.jar`  (i.e., run the command in the same folder as the jar file) 

6. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

7. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

  * **`listo`** : Lists all orders.

  * **`addp`**`n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25` : Adds a customer named `John Doe` to ReadyBakey’s contact list together with the necessary phone number, email and address.

  * **`deletep`**`3` : Deletes the 3rd customer from ReadyBakey’s contact list.

  * **`clear`** : Clears all data in ReadyBakey.

  * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features <a name="features"></a>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Command keywords i.e. `addo` `editp`, has to be in lowercase.
  e.g. `addo ...` is an acceptable keyword, but `ADDO..` is not acceptable.

* Prefixes used in command input has to be in lowercase.
  e.g. `add n/NAME...`, uses `n/` but `N/` is not acceptable.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `listo`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` <a name="help"></a>

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a customer: `addp` <a name="addp"></a>

Adds a customer to ReadyBakey’s contact list
* A customer's name and phone number must not have appeared in ReadyBakey's customer list. If it is not unique, you 
  will not be allowed to add the customer.

Format: `addp n/NAME p/PHONE e/EMAIL a/ADDRESS [r/REMARK] [t/TAG]…`
* Names must be between 2 and 50 characters long and must only contain alphanumeric characters and spaces.
* The length of the `PHONE` must be between 3 and 15 numbers.
  * It does not allow for spaces or dashes as well.
* The length of the `EMAIL` must be between 6 and 50 characters.
  * It should be in the format `local-part@domain` and adhere to the following constraints:
    1. The local-part should only contain alphanumeric characters and these special characters (+_.-), excluding
       the parentheses. The local-part may not start or end with any special characters.
      1. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by
         periods.
    2. The domain name must:
      - end with a domain label at least 2 characters long
      - have each domain label start and end with alphanumeric characters
      - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
* The length of the `ADDRESS` must be between 6 (inclusive) and 70 (inclusive) characters.
  * E.g. Keying in `a/short1` is acceptable but not `a/short`.
* The length of the `REMARK` must be less than or equal to 70 characters.
* The length of a tag must be less than or equal to 70 characters.
  * The tag prefix cannot be left empty E.g.`t/`, an error will be thrown to the user.

Examples:
* `addp n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 r/Allergic to Peanuts t/friends t/owesMoney`

### Delete a customer: `deletep` <a name="deletep"></a>
Removes a customer from ReadyBakey’s contact list 

Format: `deletep INDEX`
* Deletes the customer at the specified INDEX.
* The index refers to the index number shown in the displayed customer list.
* The index must be a positive integer 1, 2, 3, …​
* Only customers with no orders can be deleted.

Examples:
* `deletep 2`

### Listing all customers: `listp` <a name="listp"></a>
Shows a list of all customers in ReadyBakey’s contact list

Format: `listp`

Examples:
* `listp`

### Editing a customer's information : `editp` <a name="editp"></a>

Edits an existing customer in ReadyBakey's contact list.

Format: `editp INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK] [t/TAG]…`

* Edits the customer at the specified `INDEX`. The index refers to the index number shown in the displayed customer list. 
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Names must be between 2 and 50 characters long and must only contain alphanumeric characters and spaces.
* The length of the `PHONE` must be 3 to 15 numbers
* The length of the `EMAIL` must be between 6 and 50 characters.
  * It should be in the format `local-part@domain` and adhere to the following constraints:
    1. The local-part should only contain alphanumeric characters and these special characters, excluding
       the parentheses, (+_.-). The local-part may not start or end with any special characters.
    1. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by
       periods.
    2. The domain name must:
    - end with a domain label at least 2 characters long
    - have each domain label start and end with alphanumeric characters
    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
* The length of the `ADDRESS` must be between 6 and 70 characters
* The length of the `REMARK` must be less than or equal to 70 characters
* The length of a tag must be less than or equal to 70 characters.
  * If the tag prefix is left empty E.g. `t/`, it will remove all tags that are currently associated to the person
  * If any tags are provided, existing tags will be removed and replaced by the tags provided

Examples:
*  `editp 1 p/83456789 e/declan@example.com` Edits the phone number and email address of the 1st customer to be `83456789` and `declan@example.com` respectively.
*  `editp 2 n/Declan` Edits the name of the 2nd customer to be `Declan`.

### Locating customers by specific attribute: `findp` <a name="findp"></a>

Finds customer(s) whose name(s) contain any of the given keywords.

Format: `findp [ATTRIBUTE] KEYWORD [MORE_KEYWORDS]`

* The supported attributes are `n/`, `p/`, `e/`, `a/`, `r/`
* The search is case-insensitive. e.g `gerald` will match `Gerald`
* The order of the keywords does not matter. e.g. `Gerald Tan` will match `Tan Gerald`
* Only the attribute specified is searched.
  * Multiple attributes searching at the same time is not allowed. e.g. `findp n/Gerald r/Allergic`
* Multiple `[KEYWORDS]` provided will be split up by whitespace and searched individually
  * `findp n/Declan Gerald` will search for both `Declan` and `Gerald`
* Only full words will be matched e.g. `Gerald` will not match `Geralds`
* Customers matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Gerald Tan` will return `Gerald Lim`, `Gerald Lee`

Examples:
* `findp n/Gerald` returns `gerald` and `Gerald Tan`
* `findp n/alex david` returns `Alex Yeoh`, `David Li`
* `findp p/90029382` returns `90029382`
* `findp e/alex@abc.com` returns `alex@abc.com`
* `findp a/serangoon` returns `Blk 1 Serangoon Street 5`
* `findp r/Allergic` returns `Allergic to Tomatoes`<br>

### Adding an order : `addo` <a name="addo"></a>

Adds an order to ReadyBakey’s order list.

Format: `addo p/PHONE d/DETAILS c/DELIVERYDATETIME m/COLLECTION_TYPE [r/REMARK]…`

Examples:
* `addo p/98765432 d/1: Jerry Favourite Cheese Cake c/25-12-2022 15:30 m/Delivery r/Add Cheese`
* `p/PHONE` must be a phone number that is already stored in ReadyBakey's person list
* `c/DELIVERYDATETIME` accepts dates in the past for record keeping purposes
  * It accepts datetimes in the form of `dd-mm-yyyy HH:mm`. e.g. `01-01-2022 10:30`.
  * It can also accept natural dates with time. e.g. `Monday 10:30` or `Mon 10:30`. Natural dates are not
    case-sensitive.
* `d/DETAILS` accept values in the form [quantity]:[description]. It can also take in multiple details
  * A single Order accepts a single detail and up to 5 maximum details.
  * [quantity] is an integer that belongs to a range between 1 to 99.
  * [description] cannot exceed the maximum length of 30 characters.
  * `addo p/98765432 r/Add Cheese d/1:Chocolate Cake d/5:Banana Cake c/25-12-2022 15:30 m/Delivery`
* `m/COLLECTION_TYPE` only accepts [delivery OR pickup] (case-insensitive)

### Delete an order: `deleteo` <a name="deleteo"></a>

Removes an order from ReadyBakey

Format: `deleteo INDEX` 

* Deletes the order at the specified INDEX
* The index refers to the index number shown in the displayed orders list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `deleteo 2`

### Listing all orders: `listo` <a name="listo"></a>

Shows a list of all orders in ReadyBakey

Format: `listo`

Examples:
* `listo`

### Editing an order's information : `edito` <a name="edito"></a>

Edits an existing customer in ReadyBakey's contact list.

Format: `edito INDEX [c/DELIVERYDATETIME] [m/COLLECTION_TYPE] [r/REMARKS] [d/DETAILS]…`

* Edits the order at the specified `INDEX`. The index refers to the index number shown in the displayed order list.
  * The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* It accepts datetimes in the form of `dd-mm-yyyy HH:mm`. e.g. `01-01-2022 10:30`.
* It can also accept natural dates with time. e.g. `Monday 10:30` or `Mon 10:30`. Natural dates are not
  case-sensitive.
* It accepts dates in the past for record keeping purposes

Examples:
* `edito 1 d/1: black forest cake`
  * Edits the details of the 1st order to be `1: black forest cake`.
* `edito 1 d/1: black forest cake d/2: Chocolate Cake`
  * Edits the details of the 1st order to be `1: black forest
    cake` and `2: Chocolate Cake`.
    * In this case, to add two details to the order, each `d/` is for one detail.
* `edito 2 r/Two candles`
  * Edits the 2nd order's remarks to be `Two candles`.
* `edito 2 m/Delivery`
  * Edits the 2nd order's collection type to be `Delivery`.
* `edito 3 c/Monday 10:30`
  * Edits the 3rd order's collection time to be `Monday, 04 Apr 2022, 10:30`.
* `edito 3 c/04-04-2022 10:30`
  * Edits the 3rd order's collection time to be `Monday, 04 Apr 2022, 10:30`.
* `edito 1 d/1: black forest cake d/1: Cheese cake c/04-04-2022 10:30 m/Delivery r/Two candles`
  * Edits the first order to have order details with `1: black forest cake` and `1: Cheese cake`.
  * The order delivery date is also now edited to be `Monday, 04 Apr 2022, 10:30`.
  * Collection type is changed to `Delivery`.
  * Detail remarks is also changed to `Two candles`

### Locating orders by specific attribute: `findo` <a name="findo"></a>

Finds order(s) whose specific attribute contain any of the given keywords.

Format: `findo [ATTRIBUTE] KEYWORD [MORE_KEYWORDS]`

* The supported attributes are 
  * Order's customer's name: `n/`
  * Order's customer's phone number:`p/`
  * Order's details: `d/`
  * Order's collection type: `m/`
  * Order's remarks: `r/`
  * Finding orders based on `c/DELIVERYDATETIME` is not supported in this command because the command `incompleteo` serves this purpose 
* Only the attribute specified is searched.
  * Multiple attributes searching at the same time is not allowed. e.g. `findo n/Gerald d/Cake`
  * For findo `d/[keyword]`, the keyword should only be the description of the detail and not the quantity
    * Valid Example: `findo d/Cake`
    * Invalid Example: `findo d/1:Cake`
* The search is case-insensitive. e.g `cake` will match `Cake`
* The order of the keywords does not matter. e.g. `banana cake` will match `cake banana`
* Only full words will be matched e.g. `Cake` will not match `Cakes`
* Multiple `[KEYWORDS]` provided will be split up by whitespace and searched individually
  * `findo n/Declan Bob` will search for both `Declan` and `Bob`
* Orders matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Banana Cake` will return `Banana Leaf`, `Strawberry Cake`

Examples:
* `findo n/Declan` returns `declan` and `Declan Lee`
* `findo p/90029382` returns `90029382`
* `findo d/cake` returns `Cake` and `Banana Cake`
* `findo m/delivery` returns `Delivery`
* `findo r/birthday` returns `Birthday` <br>

### Mark orders as Complete: `marko` <a name="marko"></a>

Marks an order as complete in ReadyBakey

Format: `marko INDEX`

* Marks the order at the specified `INDEX`
* The`INDEX` refers to the index number shown in the displayed orders list.
* The`INDEX` must be a positive integer 1, 2, 3, ...
* If a complete order is marked again, it will remain as marked and no error will be thrown.

Examples:
* `marko 1`

### Unmark orders as incomplete: `unmarko` <a name="unmarko"></a>

Unmarks an order as incomplete in ReadyBakey

Format: `unmarko INDEX`

* Unmarks the order at the specified `INDEX`
* The `INDEX` refers to the index number shown in the displayed orders list.
* The `INDEX` must be a positive integer 1, 2, 3, ….
* If an incomplete order is unmarked again, it will remain as unmarked and no error will be thrown.

Examples:
* `unmarko 1`

### Listing all incomplete orders: `incompleteo` <a name="incompleteo"></a>

Shows a list of all incomplete orders in ReadyBakey before and during a given date and time

Format: `incompleteo DELIVERYDATETIME`
* Orders that are incomplete before and during `DELIVERYDATETIME` will be displayed
* It accepts datetimes in the form of `dd-mm-yyyy HH:mm`. e.g. `01-01-2022 10:30`.
* It can also accept natural dates with time. e.g. `Monday 10:30` or `Mon 10:30`. Natural dates are not
  case-sensitive.

Examples:
* `incompleteo 25-12-2022 15:30`
* `incompleteo Monday 15:30`

### Clearing all Customers and Orders: `clear` <a name="clear"></a>

Removes all customers and orders stored in ReadyBakey

Format: `clear`

Examples:
* `clear`

### Exiting the program : `exit` <a name="exit"></a>

Exits the program.

Format: `exit`

Examples:
* `exit`

### Using Natural Dates <a name="natural-dates"></a>

In order to provide a quicker method of referring to near dates, ReadyBakey allows users to use natural dates to refer to the directly upcoming days of the week.
The closest upcoming date that corresponds to the natural date's day input will be used. The time in HH:MM must still be provided by the user, along with the natural date.

This can be used in any command where dates are required, such as `incompleteo` and `addo`.

Format: `Day HH:mm`

All potential natural dates that can be used:
* `Mon HH:mm`
* `Monday HH:mm`
* `Tues HH:mm`
* `Tue HH:mm`
* `Tuesday HH:mm`
* `Weds HH:mm`
* `Wed HH:mm`
* `Wednesday HH:mm`
* `Thurs HH:mm`
* `Thur HH:mm`
* `Thursday HH:mm`
* `Fri HH:mm`
* `Friday HH:mm`
* `Sat HH:mm`
* `Saturday HH:mm`
* `Sun HH:mm`
* `Sunday HH:mm`

Example of natural date:
* `Monday 10:30` or `Mon 10:30` will create a datetime in the form of `dd-mm-yyyy HH:mm` that falls on the closest Monday, where `HH:mm` is `10:30`
* `Sunday 09:30` or `Sun 09:30`will create a datetime in the form of `dd-mm-yyyy HH:mm` that falls on the closest Sunday, where `HH:mm` is `09:30`
* If the current time is `7th April 2022 09:30`, which is a Thursday, inputting `Thurs 08:30` will return `14th April
  2022` as the closest Thursday.
  * Reason: `08:30` has already passed the current time, hence ReadyBakey will look for a future date that is a
    Thursday instead.
* If the current time is `7th April 2022 09:30`, which is a Thursday, inputting `Thurs 10:30` will return `7th April 2022` as the closest Thursday.
  * Reason: `10:30` has not passed the current time, hence ReadyBakey will look at the current day as the closest
    Thursday.

Example of natural dates in action:
* `incompleteo Monday 15:30`
* `addo p/98765432 d/1: Jerry Favourite Cheese Cake c/Tuesday 15:30 m/Delivery r/Add Cheese`

:exclamation: Note: You cannot specify which Natural Date you want. ReadyBakey automatically assigns the closest
Natural Date and it will be shown in the Response Box after the command is executed. (i.e. You cannot specify that
you want the date to be the Monday or any natural date 10 weeks from now)

### Saving the data <a name="saving-data"></a>

ReadyBakey data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file <a name="editing-data"></a>

ReadyBakey data are saved as a JSON file `[JAR file location]/data/readybakey.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ReadyBakey will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ <a name="faq"></a>

**Q**: Is an order tied to a specific customer in the customer list?<br>
**A**: Yes, an order is tied to the specific customer as per the customers’ ID.

--------------------------------------------------------------------------------------------------------------------

## Command summary <a name="command-summary"></a>

| Action      | Format                                                                           | Example                                                                                                          |
|-------------|----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| **addp**    | `addp n/NAME p/PHONE e/EMAIL a/ADDRESS [r/REMARK] [t/TAG]…`                      | `addp n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 r/Allergic to Peanuts t/owesMoney` |
| **deletep** | `deletep INDEX`                                                                  | `deletep 2`                                                                                                      |
| **listp**   | `listp`                                                                          | `listp`                                                                                                          |
| **editp**   | `editp INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK] [t/TAG]…`       | `editp 1 p/12345678 n/John Doey a/NUS r/loves chocolate t/member`                                                |
| **findp**   | `findp [ATTRIBUTE_PREFIX] KEYWORD [MORE_KEYWORDS]...`                            | `findp n/Gerald`                                                                                                 |
| **addo**    | `addo p/PHONE_NUM r/REMARK d/DETAILS c/DELIVERYDATETIME m/COLLECTION_TYPE`       | `addo p/87654321 r/no candles d/1:Chocolate Cake c/27-12-2022 12:30 m/Delivery`                                  |
| **deleteo** | `deleteo INDEX`                                                                  | `deleteo 2`                                                                                                      |
| **listo**   | `listo`                                                                          | `listo`                                                                                                          |
| **marko**   | `marko INDEX`                                                                    | `marko 1`                                                                                                        |
| **unmarko** | `unmarko INDEX`                                                                  | `unmarko 1`                                                                                                      |
| **edito**   | `edito INDEX [c/DELIVERYDATETIME] [m/COLLECTION_TYPE] [r/REMARKS] [d/DETAILS]…​` | `edito 1 r/Add Cheese d/1: Jerry Favourite Cheese Cake d/2: Chocolate Cake c/25-12-2022 15:30 m/Delivery`        |
| **findo**   | `findo [ATTRIBUTE_PREFIX] KEYWORD [MORE_KEYWORDS]...`                            | `findo n/Gerald Declan`                                                                                          |
| **clear**   | `clear`                                                                          | `clear`                                                                                                          |
| **exit**    | `exit`                                                                           | `exit`                                                                                                           |
