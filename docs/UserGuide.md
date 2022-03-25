
| Quick Links                         |
|-------------------------------------|
| [Glossary](#Glossary)               |
| [Quick start](#Quick-start)         |
| [Features](#Features)               |
| [Command Summary](#Command-Summary) |


Ultimate DivocTracker is a desktop app for managing COVID-19 contacts in school admin, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Ultimate DivocTracker can get your contact management tasks done faster than traditional GUI apps.

# Glossary
- Quick start
- Features
  - Adding a student: `add`
  - Listing all students: `list`
  - Find students by name: `find`
  - Find student by status:  `findstatus`
  - Find student by class: `findclasscode`
  - Find student by activity: `findactivity`
  - Editing student’s personal details: `edit`
  - Deleting a student: `delete`
  - Exiting the program : `exit`
- Command summary

# Quick start
- Ensure you have Java 11 or above installed in your Computer.
- Download the latest ultimatedivoctracker.jar from <website link>.
- Copy the file to the folder you want to use as the home folder for your Ultimate DivocTracker.
- Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
- Refer to the Features below for details of each command.



# Features
## Adding a person: `add`
Adds a student to the tracking list
- Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS​ c/CLASS s/STATUS act/ACTIVITY [MORE ACTIVITIES]`
- Examples:
  - `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/5A s/NEGATIVE`
  - `add n/Betsy Crowe p/99999999 e/betsycrowe@example.com a/Newgate Prison p/1234567 c/2B s/POSITIVE act/choir dance`

## Listing all persons : `list`
Shows a list of all students in the address book.
- Format: `list`

## Find a student by name: `find`
Find an existing student in the application by their name
- Format: `find NAME`
  - Returns a list of students with the specified `NAME`
  - `NAME` is case-insensitive
  - Searching for the first or last name would return a list of names that corresponds to the search input provided
- Example:
  - `find john`

## Find a student by status: `findstatus`
Find an existing student in the application by their Covid-19 Status
- Format: `findstatus STATUS`
  - Returns a list of students with the specified `STATUS`
  - `STATUS` is case-insensitive
- Example:
  - `findstatus positive`
  - `findstatus negative`

## Find student by class: `findclasscode`
Finds an existing student in the address book by their class.
- Format: `findclasscode CLASS`
  - Returns a list of students with the specified `CLASS`.
  - `CLASS` is case-insensitive
- Example:
  - `findclasscode 4A`

## Find student by class: `findactivity`
Finds an existing student in the address book by the activities they are participating in.
- Format: `findactivity ACTIVITIY`
  - Returns a list of students with the specified `ACTIVITY`.
  - `ACTIVITY` is case-insensitive
- Example:
  - `findactivity badminton`

## Edit a student's details: `edit`
Edits an existing student's details in the address book by the Index provided and the areas that the user wants to edit
- Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS] [s/STATUS] [act/ACTIVITIES]`
  - Index of the person in the list is defined by `INDEX`
  - `INDEX` must be a positive integer, otherwise the command would return an error
  - Only add the fields that the user would like to change
- Examples:
  - John in INDEX 1 is now COVID-19 Positive
    - `edit 1 s/Positive`
  - Mary in INDEX 5 now has a new phone number and email address
    - `edit 5 p/98641865 e/maryjane@yahoo.com`

## Deleting a person : `delete`
Deletes the specified person from the address book.
- Format: `delete INDEX`
  - Deletes the student at the specified `INDEX`.
  - The index refers to the index number shown in the displayed student list.
  - The index must be a **positive integer** 1, 2, 3, ...
- Examples:
  - `list` followed by `delete 2` deletes the 2nd person in the address book.
  - find Betsy followed by `delete 1` deletes the 1st student in the results of the `find` command.


## Exiting the program: `exit`
Exits the program.
Format: `exit`
## Saving the data
AddressBook data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file
AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

----------------

## Command Summary

| Action           | Format, Examples                               |                    
|-----|------------------------------------------------|
| Add a student    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS s/STATUS act/ACTIVITIES`<br/>e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/5A s/NEGATIVE act/badminton` |
| Delete a student | `delete INDEX`<br/>e.g., `delete 3`                      |
| Edit             | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS] [s/STATUS] [act/ACTIVITIES]`<br/>e.g., `edit 2 n/James Lee e/jameslee@example.com`                                                       |
| Find by name     | `find NAME [MORE_NAME]`<br/>e.g., `find James Jake`      |
| Find by status   | `findstatus STATUS`<br/>e.g., `findstatus positive`      |
| Find by class    | `findclasscode CLASS`<br/>e.g., `findclasscode 4A`       |
| Find by activity | `findactivity ACTIVITY`<br/>e.g., `findactivity choir`   |
| List             | `list`                                                   |
| Exit             | `exit`                                                   |
