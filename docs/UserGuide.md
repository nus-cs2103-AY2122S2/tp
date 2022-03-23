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
* `TAG` is an optional field.
* Each particular entered must strictly correspond to its legal prefix.`e.g: p/Address is considered illegal`.
* Phone number **must only contain numbers**.

Examples:
* `add n/Mojo n/John Doe p/98765432 a/523 Woodlands ave 5, #01-01 t/Bulldog`.

### Sort pets: `sort`

Retrieves a sorted list of pets. Users can choose to either sort them by owner name or pet name.

Format: `sort [FIRST_LETTER_OF_COLUMN]`
* Since we can only sort the pet list by owner name or pet name, the only commands available currently are `sort /o` and `sort /n`.

Examples:
* `sort /o`


### Find pet details: `find`

Retrieve and return a list of all pet with similar name to keywords and their corresponding details from the database.

Format: `find n/NAME_OF_PET [Keywords]`
* The name of pet is case-insensitive e.g: `find Mojo` will match `find mojo`.
* Only the name is searched.
* Search returns partial name matches e.g.: `find mo` will return Mojo as a result.

Examples:
* `find Peepee Waffle Bagel`

### Adding pets' dietary requirements: `diet` ###

Given a pet ID, add any dietary requirement the pet may have to database.

Format: `diet INDEX d/remark`

* Adds `d/remark` as a dietary requirement for pet with `INDEX`.
* ID is a unique identifier that each pet has in the database.

Examples:
`diet 12 Only feed dry kibble` will store a dietary remark for pet 12 saying "Only feed dry kibble".

### Adding / Clearing pets' appointment details: `app` ###

Given a pet ID, add or clear a pet appointment details in the database.

**Add** Format: `app INDEX dt/[dd-MM-yyyy HH:mm] at/[location]`

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

**Clear** Format: `app INDEX clear`

* ID is a unique identifier that each pet has in the database.
* Keyword `clear` will instruct the application to clear the appointment details of pet corresponding to the index.
* `clear` is case-sensitive.
* Whitespaces, special characters and alphanumeric characters are allowed for location.
* If `clear` is not present, `app` will be deemed invalid.

Examples:
`app 1 clear` will clear the appointment details for pet 1 and set it to be an empty field.


### Viewing pets’ pick-up and drop-off time: `time`

Views pets’ pick-up and drop-off time

Format: `time INDEX`

* Views pick-up and drop-off time of the pet at the specified `INDEX`.
* The index refers to the index number shown in the displayed getId list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `time 12` returns pet 12’s pickup and dropoff times.

### Adding pet’s attendanceEntry, pick-up and drop-off time: `attd`

Add pet’s attendanceEntry date, pick-up and drop-off time

Format: `attd INDEX att/dd-MM-yyyy pu/HH:mm do/HH:mm`

* Adds attendanceEntry date, pick-up and drop-off time of the pet at the specified `INDEX`.
* The index refers to the index number shown in the displayed getId list.
* The index **must be a positive integer** 1, 2, 3, …​
* Date and time must follow the specified format

Examples:
* `attd 1 att/16-03-2022 pu/08:00 do/19:00` indicates that pet 1 is coming for daycare on `16-03-2022`, requires to be picked up at `08:00` and dropped off at `19:00`

### Deleting a pet: `delete`

Deletes the specified pet from the address book.

Format: `delete INDEX`

* Deletes the pet at the specified `INDEX`.
* The index refers to the index number shown in the displayed getId list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` deletes the 2nd pet in the list.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Command summary

| Action             | Format, Examples                                                                                                                                                                                                  |
|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**            | `add n/NAME_OF_PET o/OWNER_NAME p/PHONE_NUMBER a/ADDRESS` <br> e.g., `add n/Peepee o/Peter p/98648252 a/13 Computing Drive, Singapore 117417`                                                                     |
| **Delete**         | `delete id` <br> e.g., `delete 3` (where 3 is the id of the pet in the system)                                                                                                                                    |
| **Find**           | `find n/NAME_OF_PET [Keywords]` <br> e.g., `find PeePee` (returns information of all pets called PeePee)                                                                                                          |
| **Diet**           | `diet INDEX d/remark` <br> e.g. `diet 12 Only feed dry kibble` (stores remark in pet 12's database)                                                                                                               |
| **Add Attendance** | `attd INDEX att/dd-MM-yyyy pu/HH:mm do/HH:mm` <br> e.g. `attd 1 att/16-03-2022 pu/08:00 do/17:00` (indicates that pet 1 will be attending daycare on 16 March 2022, requires pick up at 8 am and drop off at 5 pm |
| **Time**           | `time id `<br> e.g.,`pickup 3 0900 1200` (where 3 is the id of the pet in the system)                                                                                                                             |
| **App**            | `app INDEX date/[yyyy-MM-dd HH:mm] at/[location]` <br> e.g., `app 1 date/2022-03-04 09:30 at/ NUS Vet Clinic`                                                                                                     |
| **Exit**           | `exit`                                                                                                                                                                                                            |
