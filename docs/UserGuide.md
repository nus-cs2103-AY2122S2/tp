
| Quick Links     |
|-----------------|
| Glossary        |
| User Guide      |
| Quick start     |
| Features        |
| Command Summary |


Ultimate DivocTracker is a desktop app for managing COVID-19 contacts in school admin, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Ultimate DivocTracker can get your contact management tasks done faster than traditional GUI apps.

# Glossary
- Quick start
- Features
  - Adding a student: add
  - Listing all students: list
  - Find students by name: find_by_name
  - Find student by status:  findstatus
  - Find student by class: findclasscode
  - Deleting a student: delete_student
  - Update COVID-19 status: update_status
  - Exiting the program : exit
  - Editing student’s personal details: edit [coming in v1.3]
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
- Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS​ c/CLASS s/STATUS`
- Examples:
  - `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/5A s/NEGATIVE`
  - `add n/Betsy Crowe p/99999999 e/betsycrowe@example.com a/Newgate Prison p/1234567 c/2B s/POSITIVE`

## Listing all persons : `list`
Shows a list of all students in the address book.
- Format: `list`

## Find a student by name: `find_by_name`
Find an existing student in the application by their name
- Format: `find_by_name NAME`
  - Returns a list of students with the specified `NAME`
  - `NAME` is case-insensitive
  - Searching for the first or last name would return a list of names that corresponds to the search input provided
- Example:
  - `find_by_name john`

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

## Updating persons status: `update_status`
Updates persons status to the given status.
- Format: `update_status INDEX STATUS`
  - Index of the person in the list is defined by `INDEX`
  - `INDEX` must be a positive integer, otherwise the command would return an error
  - GUI: Select student’s status via a dropdown menu
  - CLI: enter command `update_status [INDEX] [STATUS]`. Replace `[STATUS]` placeholder with either `Positive, Negative, Close Contact or Untested`.
  - The status is case-insensitive. e.g `positive` will mean `Positive`
  - `STATUS` must either be `Positive, Negative, Close Contact, Untested`
- Example:
  - `update_status 1 Positive` - Updates the person with Index 1 status to Positive

## Deleting a person : `delete_student`
Deletes the specified person from the address book.
- Format: `delete_student INDEX`
  - Deletes the student at the specified `INDEX`.
  - The index refers to the index number shown in the displayed student list.
  - The index must be a **positive integer** 1, 2, 3, ...
- Examples:
  - `list` followed by `delete_student 2` deletes the 2nd person in the address book.
  - find Betsy followed by `delete_student 1` deletes the 1st student in the results of the `find` command.


## Exiting the program: `exit`
Exits the program.
Format: `exit`
## Saving the data
AddressBook data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file
AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

----------------

## Command Summary
| Action           | Format, Examples                                                                                                                                                          |
|------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add a student    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS s/STATUS`<br/>e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/5A s/NEGATIVE` |
| Delete a student | `delete_student INDEX`<br/>e.g., `delete_student 3`                                                                                                                       |
| Edit             | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br/>e.g., `edit 2 n/James Lee e/jameslee@example.com`                                                        |
| Find by name     | `find_by_name NAME [MORE_NAME]`<br/>e.g., `find_by_name James Jake`                                                                                                       |
| Find by status   | `findstatus STATUS`<br/>e.g., `find_by_status positive`                                                                                                                   |
| Find by class    | `findclasscode CLASS`<br/>e.g., `find_by_class 4A`                                                                                                                        |
| List             | `list`                                                                                                                                                                    |
| Update status    | `update_status INDEX STATUS`<br/>e.g., `update_status 54 positive`                                                                                                        |
| Exit             | `exit`                                                                                                                                                                    |
