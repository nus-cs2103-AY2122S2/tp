---
layout: page
title: CinnamonBun User Guide
---

CinnamonBun is a **desktop app for managing client information, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

---

# CinnamonBun - As good as it sounds.

Are you a small business owner or freelancer? Want an easy way to store your client's information, but there's no free and easy solution available?
No worries, CinnamonBun has got you covered! CinnamonBun enables you to keep track of your clients and their transaction records.

As a Command-line Interface (CLI) app, you'll never have to move your hands away from the keyboard to get all your tasks done.
If you can type fast, the CLI will allow you to execute your tasks quickly. While this may be daunting for a new user, it only takes a short time to get accustom to CinnamonBun.

This guide aims to be the one-stop shop to get you from noob to expert in record speed.

---

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `CinnamonBun.jar` from [here](https://github.com/AY2122S2-CS2103T-W09-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for CinnamonBun.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press `Enter` to execute it. For example, typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all clients.
   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a client named `John Doe` to CinnamonBun.
   * **`delete`**`3` : Deletes the 3rd client shown in the current list.
   * **`clear`** : Deletes all client records.
   * **`undo`** : Undo the last executed command.
   * **`exit`** : Exits the app.
    
6. Refer to the [Commands](#Commands) below for details of each command.

---

## Graphical User Interface (GUI) Overview

Coming soon

---

## Commands

### Command Syntax

Commands in CinnamonBun can be broken down into several components.

`COMMAND_WORD PREAMBLE PREFIX/ARGUMENT...`

| Component      | Description                                                                                                                          |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------|
 | `COMMAND_WORD` | Trigger for the command to be executed.                                                                                              |
 | `PREAMBLE`     | Text before the first valid parameter prefix in the command. Certain commands may require a preamble.                                | 
| `PREFIX`       | Commands may have one more or arguments. The prefix specifies which arguments to map to the parameters. All prefixes end with a `/`. |
 | `ARGUMENT`     | The argument to pass to the command.                                                                                                 |

<div class="alert alert-primary">:bulb: **Attention**

* Commands and their arguments are case-sensitive
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### User Guide Syntax

* Words in `UPPER_CASE` are the arguments to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is an argument which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used zero or more times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family t/colleague` etc.

### Viewing help: `help`

Opens a window that includes a link to this user guide.

![help message](images/helpMessage.png)

Format: `help`

### Adding a client: `add`

Adds a client to CinnamonBun.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div class="alert alert-info">:information_source: **Info**

A client can have many fields, including both optional and compulsory ones.

| Field    | Prefix   | Constraints                                                                                                                                | Compulsory         | Example                                   |
|----------|----------|--------------------------------------------------------------------------------------------------------------------------------------------|--------------------|-------------------------------------------|
| Name     | `n/`     | Alphanumeric and spaces only.                                                                                                              | :heavy_check_mark: | `n/Regina Lee`                            |
| Email    | `e/`     | Must be in *username@domain.suffix* format and unique to each client.<br/><br/> Alphanumeric and special characters, *+_.-* only.          | :heavy_check_mark: | `n/lovelee@mailinator.com`                |
| Phone    | `p/`     | Minimum 3 numeric characters.                                                                                                              | :heavy_check_mark: | `p/81312224`                              |
| Address  | `a/`     | No constraints.                                                                                                                            | :heavy_check_mark: | `a/123 Sesame Street`                     |
| Birthday | `b/`     | Must be in *YYYY-MM-DD* format and a valid date.                                                                                           |                    | `b/2022-03-12`                            |
| Remark   | 'r/`     | No constraints.                                                                                                                            |                    | `r/Foreman of Project Zero Dawn.`         |       
| Tags     | `t/`     | Alphanumeric only.<br/><br/> No spaces allowed.<br/><br/> Multiple tags are allowed per client.                                            |                    | `t/Frequentclient t/AppointmentOverdue`   | 

</div>

Examples:
* `add n/Andy Lau p/98765432 e/andy_lau@example.com a/Pasir Ris Grove, Block 73, #02-38, Singapore 518206`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Sort client list: `sort`

Sorts the client list based on the order of the fields specified.

Format: `sort [n/] [desc] [p/] [desc] [e/] [desc] [a/] [desc] [r/] [desc] [b/] [desc]`  

<div class="alert alert-info">:information_source: **Info**

* Fields that can be sorted:
  * Name `n/`
  * Phone number `p/`
  * Email `e/`
  * Address `a/`
  * Birthday `b/`
  * Remark `r/`
* At least one of the above fields must be specified.
* Specifying `desc` it after a field means that particular field is to be sorted in descending order. By default, they are sorted in ascending order.
* The fields are to be specified in their prefix. They can be specified in any order, however, priority will be given
  based on the order specified.
* Clients with null values in the fields to be sorted will have lesser priority. 
  * For example `sort n/ b/`, 'Alice' will be at the top of the list. However, if there are multiple clients with the same name 'Alice',
  the client whose birthday field is empty, will be sorted to the bottom of the other clients named 'Alice'.

</div>

Examples:
* `sort n/ desc p/`: Clients will be sorted in descending order of their name. Clients with the same name will be then sorted 
based on their phone numbers in ascending order.
* `sort b/ a/`: Clients will be sorted in ascending order of their birthday. Clients with the same birthday will then be sorted
based on their addresses in ascending order.

### Show all clients : `list`

Shows a list of all clients in CinnamonBun.

Format: `list`

### Editing a client : `edit`

Edits an existing person in CinnamonBun.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK] [t/TAG]…​`

<div class="alert alert-info">:information_source: **Info**

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
  * Note: Editing email value to an existing email in the addressBook is not allowed.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* You can also remove a person's remarks by typing `r/` without anything else.

</div>

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds clients whose name, phone, email, address or tags contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

<div class="alert alert-info">:information_source: **Info**

* The search is case-insensitive. e.g. `bob` will match `Bob`
* The order of the keywords does not matter. e.g. `Hans Bob` will match `Bob Hans`
* The name, phone, email, address and tags are searched.
* Only full words will be matched e.g. `Bob` will not match `Bobs`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bob` will return `Hans Zimmer`, `Bob Morrison`

</div>

Examples:
* `find Bob` returns `bob` and `Bob Doe`
* `find 65123456` returns clients with the phone number `65123456`
* `find kent ridge` returns clients with an address containing `kent` or `ridge`
* `find manager hr` returns clients with the `manager` or `hr` tags

### Deleting a person : `delete`

Deletes a customer in CinnamonBun.

Format: `delete INDEX`

<div class="alert alert-info">:information_source: **Info**

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

</div>

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Deleting all filtered people : `deleteFiltered`

Deletes the clients filtered after using the `find` function.

Format: `deleteFiltered`

Examples:
* `find Bob` followed by `deleteFiltered` deletes all people named Bob. Alternatively, `find Bob | deleteFiltered` does the same thing.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

---

## Special Features

### Command Chains
Multiple commands can be chained by separating each command with the `|` character.
Each command is run sequentially from first to last.
Invalid commands and special commands `help` and `exit` will break the chain and stop further command execution.  

e.g `add n/John Doe e/johndoe@email.com | edit 5 p/999 | delete 2`

### Command History
You can navigate through your command history by using the up and down arrow keys.
The command history works similarly to the Linux bash terminal.

---

## Save Data

CinnamonBun's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty `data/` file it creates with the file that contains the data of your previous CinnamonBun home folder.

**Q**: Why is this named CinnamonBun?<br>
**A**: Because we like Cinnamon Buns.

---

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Sort**| `sort [n/] [desc] [p/] [desc] [e/] [desc] [a/] [desc] [r/] [desc] [b/] [desc]` <br> e.g., `sort n/ desc p/`, `sort b/ a/`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**DeleteFiltered** | `deleteFiltered`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/remark] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
