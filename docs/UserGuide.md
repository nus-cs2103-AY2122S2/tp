---
layout: page
title: User Guide
---

Amigos is a desktop application to help tech-savvy university students manage their friendships by helping them to keep
track of important details. It is optimized for use via a **Command Line interface** while still having the benefits of a 
Graphical User Interface (GUI).


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

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a friend: `addfriend`

Adds a new friend to the address book. A friend has:
* ***(compulsory)*** name
* *(optional)* phone number
* *(optional)* email
* *(optional)* address
* *(optional)* description

Format: `addfriend n/{NAME}  p/{PHONE_NUMBER} e/{EMAIL}  a/{ADDRESS} d/{DESCRIPTION}`

* Note that NAME is minimally compulsory. `p/`, `em/`, `a/` and `d/` flags and their arguments are optional.
* Note that there can be no duplicate friends having the same name.

Examples:
* `addfriend n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/Physics Major, Sarah’s friend. Met at Freshman Dinner.`
* `addfriend n/John Doe`


### Deleting a friend : `deletefriend`

Deletes a friend in the address book

Format: `deletefriend n/{NAME}`

* NAME field must be provided unless all friends are being deleted then just the -a flag is needed.

Examples:
* `deletefriend n/John Doe`
* `deletefriend -a`


### Editing a friend : `editfriend`

Edits an existing friend in the address book.

Format: `editfriend cn/{CURRENT_NAME} nn/{NEW_NAME}  np/{NEW_PHONE_NUMBER} ne/{NEW_EMAIL} na/{NEW_ADDRESS} nd/{NEW_DESCRIPTION}`

* Edits an existing friend in the address book. Field cn/{CURRENT_NAME} is compulsory to identify the existing friend.
* At least one of the optional fields must be provided.
* Existing values will be overwritten to the input values.


Examples:
* `editfriend cn/John Doe na/John street, block 456, #01-01 ne/johndoe@example.com` edits the address
and email of John Doe to be `John street, block 456, #01-01` and `johndoe@example.com` respectively.

## Command summary

| Action            | Format, Examples                                                                                                                                                                                                                      |
|-------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Friend**    | `addfriend n/NAME  [p/PHONE_NUMBER] [e/EMAIL]  [a/ADDRESS] [d/DESCRIPTION]` <br> e.g., `addfriend n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/Physics Major, Sarah’s friend. Met at Freshman Dinner` |
| **Delete Friend** | `deletefriend n/NAME` <br> `deletefriend -a` <br> e.g., `deletefriend n/John Doe`                                                                                                                                                     |
| **Edit a friend** | `editfriend cn/CURRENT_NAME [nn/NEW_NAME]  [np/NEW_PHONE_NUMBER] [ne/NEW_EMAIL] [na/NEW_ADDRESS] [nd/NEW_DESCRIPTION]`<br> e.g., `editfriend cn/John Doe na/John street, block 456, #01-01 ne/johndoe@example.com`                    |
