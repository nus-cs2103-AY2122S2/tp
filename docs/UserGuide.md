# User Guide

# Reference

Original AB3 User Guide: [link](https://se-education.org/addressbook-level3/UserGuide.html)

# User Guide

RealEstatePro is a desktop app for managing contacts, optimized for real estate agents to manage their clients’ contacts and sales of properties.

## Quick start

1. Ensure you have Java `11` or above installed on your computer.
2. Download the latest `realestatepro.jar` from here **(add link)**.
3. Copy the file to the folder you want to use as the *home folder* for your RealEstatePro.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.
Some example commands you can try:
    - **`list`** : Lists all contacts.
    - **`add**n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` , contact number `98765432`, email `johnd@example.com` and address `street, block 123, #01-01` to the RealEstatePro app.
    - **`delete**3` : Deletes the 3rd contact shown in the current list.
    - **`clear`** : Deletes all contacts.
    - **`exit`** : Exits the app.
6. Refer to the Features below for details of each command.

## Features

Notes about the command format:

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
- Inputting information after `pr/` & `t/` indicates the type of property a user is selling or buying.
e.g. `pr/PROPERTY_REGION, PROPERTY_ADDRESS, PROPERTY_SIZE, PROPERTY_PRICE, t/userType` can be used as `pr/East, Block 123, 2-room, $550000, t/seller` means this user is a seller looking to sell a 2-room property at Block 123 which is located in the East, with a price of $550000.
- Parameters can be in any order.
e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
- If a parameter is expected only once in the command but you
specified it multiple times, only the last occurrence of the parameter
will be taken.
e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS pr/PROPERTY_SIZE, PROPERTY_LOCATION, PROPERTY_PRICE, t/USER_TYPE`

**Tip**: A person can be tagged as either a `Buyer`, or `Seller`.

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01, pr/2-room, East, SGD$200K, t/Buyer`
- `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 pr/Studio, West, SGD100K, t/Seller`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person: `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [pr/PROPERTY_SIZE, PROPERTY_LOCATION, PROPERTY_PRICE] [t/USER_TYPE]`

- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
- You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`

    ![https://se-education.org/addressbook-level3/images/findAlexDavidResult.png](https://se-education.org/addressbook-level3/images/findAlexDavidResult.png)

### Deleting a person: `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

- Deletes the person at the specified `INDEX`.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …

Examples:

- `list` followed by `delete 2` deletes the 2nd person in the address book.
- `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Favourite a person: `favorite`

Favorites the specified person from the application. The user (real estate agent) will be able to view the more compact list of favorited persons in a new window that can be opened up through the ‘Favourite’ menu item.

Format: `favourite INDEX`

- Favorites the person at the specified `INDEX`.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …

### Open Favourite Window:

This opens up a new window that lists Persons that have been favourited.

1)  Navigate to the ‘File’ menu item and click on it.

2) Under it, click on ‘Favourite’

3) The system will pop up the window that contains a list of Persons favourited by the user.

### User onboarding [Coming soon]

### Saving the data

RealEstatePro data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

RealEstatePro data are saved as a JSON file `[JAR file location]/data/realestatepro.json`. Advanced users are welcome to update data directly by editing that data file.

<aside>
❗ **Caution:** If your changes to the data file makes its format invalid, RealEstatePro will discard all data and start with an empty data file at the next run.

</aside>

# FAQ

# Command Summary

| Action | Format, Examples  |
| --- | --- |
| Add | add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS pr/PROPERTY_SIZE, PROPERTY_LOCATION, PROPERTY_PRICE, t/USER_TYPE 
e.g., add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 pr/2-room, East, SGD$200K, t/Buyer    |
| Clear | clear  |
| Delete | delete INDEX 
e.g., delete 3  |
| Edit | edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [pr/PROPERTY_SIZE, PROPERTY_LOCATION, PROPERTY_PRICE] [t/USER_TYPE]    ​
e.g., edit 2 n/James Lee e/jameslee@example.com |
| Find | find KEYWORD [MORE_KEYWORDS] 
e.g., find James Jake  |
| List | list  |
| Help | help  |
| Favourite | favourite INDEX
e.g., favourite 3  |

