---
layout: page
title: User Guide
---
# WoofAreYou

WoofAreYou is a desktop app for pet daycare owners to handle the administrative information of their pets. If you can
type fast, WoofAreYou can get your contact management tasks done faster than traditional GUI apps.
<p align="center">
  <img src="images/Ui.png" alt="WoofForYou sample screenshot"/>
</p>
--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `WoofAreYou.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`add n/Peepee o/peter p/98648252 a/13 Computing Drive, Singapore 117417`** : Adds a pet named `Peepee` to the tracker.

   * **`delete 3`** : Deletes the 3rd pet shown in list.

   * **`find Peepee`** : Returns a list of pets with similar name as keywords and their corresponding information.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features


### Add a pet: `add`

Add a pet to the database.

Format: `add n/NAME_OF_PET o/OWNER_NAME p/PHONE_NUMBER a/ADDRESS [t/TAG]`
* Each particular field is compulsory except for `TAG`.
* `TAG` is an optional field which could be used to indicate the breed of a pet.
* Each particular entered must strictly correspond to its legal prefix.`e.g: p/Address is considered illegal`.
* Phone number **must only contain numbers**.

Examples:
* `add n/Mojo n/John Doe p/98765432 a/523 Woodlands ave 5, #01-01 t/Bulldog`.

### Edit a pet : `edit`

Edits an existing pet in the address book.

Format: `edit INDEX [n/NAME_OF_PET] [o/OWNER_NAME] [p/PHONE_NUMBER] [a/ADDRESS] [t/TAG]`
* Edits the pet at the specified `INDEX`. The index refers to the index number shown in the displayed pet list. The index **must be a positive integer**.
* All the fields are optional but at least one of the fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the pet will be removed i.e. adding of tags is not cumulative.
* You can remove all the pet's tags by typing t/ without specifying any tags after it.

### List all pets : `list`

Shows a list of all pet in the address book.

Format:`list`

### Sort pets: `sort`

Retrieves and return a sorted list of pets. Users can choose to either sort alphabetically by owner name or pet name.

Format: `sort SORT_BY`
* Currently, users can sort the address book in 3 ways, pet name, owners and appointment date.
* The valid `SORT_BY` parameters corresponding to 3 ways mentioned above is `name` , `owner` and `app` respectively.
* The parameters are case-sensitive.
* Only parameter is to be used when the command is used. If none or more than one parameter is used, command will throw an error.
* Since we can only sort the pet list by owner name or pet name, the only commands available currently are `sort /o` and `sort /n`.

Examples:
* `sort owner`

### Find pet details: `find`

Retrieve and return a list of all pet with similar name to keywords and their corresponding details from the database.

Format: `find n/NAME_OF_PET [Keywords]`
* The name of pet is case-insensitive e.g: `find Mojo` will match `find mojo`.
* Only the name is searched.
* Search returns partial name matches e.g.: `find mo` will return Mojo as a result.
* Can search for multiple pet names.

Examples:
* `find Peepee Waffle Bagel`

### Add pets' dietary requirements: `diet` ###

Given a pet ID, add any dietary requirement the pet may have to database.

Format: `diet INDEX d/remark`

* Adds `d/remark` as a dietary requirement for pet with `INDEX`.
* ID is a unique identifier that each pet has in the database.
* Entering `diet INDEX d/` will remove the dietary requirements of pet at `INDEX`.

Examples:
`diet 12 Only feed dry kibble` will store a dietary remark for pet 12 saying "Only feed dry kibble".

### Add / Clear pets' appointment details: `app` ###

Given a pet ID, add or clear a pet appointment details in the database.

**Add Appointment**

Format: `app INDEX dt/[dd-MM-yyyy HH:mm] at/[location]`

* ID is a unique identifier that each pet has in the database.
* Date and time of appointment should be entered together with `dt/` prefix.
* Date and time should strictly follow `dd-MM-yyyy HH:mm` format.
* Date and time should only contain numbers apart from `-` and `:` as per shown above.
* Location of appointment should be entered with `at/` prefix.
* Whitespaces, special characters and alphanumeric characters are allowed for location.
* If both `date/` and `at/` are not present, `app` will be deemed invalid.

Examples:
`app 1 dt/04-03-2022 09:30 at/ NUS Vet Clinic` will store the appointment details for pet 1 as
`Mar-04-2022 9:30 AM at NUS Vet Clinic`.

**Clear Appointment**

Format: `app INDEX clear`

* ID is a unique identifier that each pet has in the database.
* Keyword `clear` will instruct the application to clear the appointment details of pet corresponding to the index.
* `clear` is case-sensitive.
* Whitespaces, special characters and alphanumeric characters are allowed for location.
* If `clear` is not present, `app` will be deemed invalid.

Examples:
`app 1 clear` will clear the appointment details for pet 1 and set it to be an empty field.

### Mark a pet as present: `present`

Mark a pet as present on a given date, with school bus pick-up time and drop-off time (if any).

Format: `present INDEX date/dd-MM-yyyy [pu/HH:mm do/HH:mm]`

* Adds a present attendance entry with the attendance date, pick-up and drop-off time to the pet at the specified `INDEX`.
* The index refers to the index number shown in the current list of pets.
* The index **must be a positive integer** 1, 2, 3, ...
* Date and time **must follow the specified format**

Examples:
* `present 1 date/16-03-2022 pu/08:00 do/19:00` indicates that pet 1 is present for daycare on `16-03-2022`, requires to be picked up at `08:00` and dropped off at `19:00`

### Mark a pet as absent: `absent`

Mark a pet as absent on a given date.

Format: `absent INDEX date/dd-MM-yyyy`

* Adds an absent attendance entry with the attendance date to the pet at the specified `INDEX`.
* The index refers to the index number shown in the current list of pets.
* The index **must be a positive integer** 1, 2, 3, ...
* Date **must follow the specified format**

Examples:
* `absent 1 date/17-03-2022` indicates that pet 1 is absent for daycare on `17-03-2022`

### Undo changes : `undo`

Undoes previous commands that the user has keyed in. 

Format: `undo`
* Users should not be able to undo Clear, Exit, Find, Help and List Commands. 

Examples:
* If the user chooses to delete a pet, `undo` will revert the address book to the state where the pet is not deleted. 

### Filter pet list: `filter`

Filters the pet address book by a specified field.

Format: `filter FIELD/FilterWord`

* Can only filter by date specified, owner's name and tags of pets (choose one out of three, else error will be shown)
* Specified `FIELD/` only consists of: `byDate/`, `byApp/`, `byTags/` and `byOwner/`.
* `FilterWord` if user uses `byDate/` or `byApp/` has to be in `dd-MM-yyyy` format, or `today`. This filters the address book by showing pets present on the given date or pets with appointments on the given date.
* `FilterWord` if user uses `byOwner/` has to be a single word. Usage is similar to `find` except filter allows users to filter out multiple pets with same owner.
* `FilterWord` if user uses `byTags/` can be any length. Show pets that are of the same breed or tag similar to `FilterWord` given.
  * Able to filter with a partial match in `FilterWord`: `Bord`, `Borde Colli`, will match with pets tagged as `Border Collie`

Examples:
* `filter byDate/22-03-2022` show pets present on 22 March 2022.
* `filter byOwner/Lily` shows pets owned by any all Lily(s).
* `filter byTags/Retriever` shows pets with `Retriever` in their tags.

### Delete a pet: `delete`

Deletes the specified pet from the address book.

Format: `delete INDEX`

* Deletes the pet at the specified `INDEX`.
* The index refers to the index number shown in the displayed getId list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` deletes the 2nd pet in the list.

### Clearing all entries : `clear`

Clear all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

## Command summary

| Action      | Format, Examples                                                                                                                                                                                                                |
|-------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**     | `add n/NAME_OF_PET o/OWNER_NAME p/PHONE_NUMBER a/ADDRESS` <br> e.g., `add n/Peepee o/Peter p/98648252 a/13 Computing Drive, Singapore 117417`                                                                                   |
| **Edit**    | `edit INDEX [n/NAME_OF_PET] [o/OWNER_NAME] [p/PHONE_NUMBER] [a/ADDRESS] [t/TAG]` <br> e.g, `edit 1 p/98247076 t/bulldog`                                                                                                        |
| **Delete**  | `delete id` <br> e.g., `delete 3` (where 3 is the id of the pet in the system)                                                                                                                                                  |
| **Find**    | `find n/NAME_OF_PET [Keywords]` <br> e.g., `find PeePee` (returns information of all pets called PeePee)                                                                                                                        |
| **Diet**    | `diet INDEX d/remark` <br> e.g. `diet 12 Only feed dry kibble` (stores remark in pet 12's database)                                                                                                                             |
| **Present** | `present INDEX date/dd-MM-yyyy pu/HH:mm do/HH:mm` <br> e.g., `present 1 date/16-03-2022 pu/08:00 do/17:00` <br>(indicates that pet 1 will be attending daycare on 16 March 2022, requires pick up at 8 am and drop off at 5 pm) |
| **Absent**  | `absent INDEX date/dd-MM-yyyy` <br> e.g., `absent 1 date/17-03-2022` (indicates that pet 1 was absent on 17 March 2022)                                                                                                         |
| **App**     | `app INDEX date/[dd-MM-yyyy HH:mm] at/[location]` e.g., `app 1 date/04-03-2022 09:30 at/ NUS Vet Clinic` <br> `app INDEX clear` e.g., `app 1 clear`                                                                             |
| **Sort**    | `sort SORT_BY` <br> e.g., `sort name`                                                                                                                                                                                           |
| **Filter**  | `filter FIELD/FilterWord` <br> e.g. `filter byDate/22-03-2022` (returns information of all pets present on 22 March 2022)                                                                                                       |
| **List**    | `list`                                                                                                                                                                                                                          |
| **Clear**   | `clear`                                                                                                                                                                                                                         |
| **Exit**    | `exit`                                                                                                                                                                                                                          |
| **Help**    | `help`                                                                                                                                                                                                                          |
| **undo**    | `undo`                                                                                                                                                                                                                          |
