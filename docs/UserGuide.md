
| Layout | Page |
| --- | --- |
| User Guide | Link |
| Quick start | Link |
| Features | Link |
| Command Summary | Link |


Ultimate DivocTracker is a desktop app for managing COVID-19 contacts in school admin, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Ultimate DivocTracker can get your contact management tasks done faster than traditional GUI apps.

# Glossary
- Quick start
- Features
  - Adding a student: add
  - Listing all students: list
  - Find students by name: find_by_name
  - Find student by status:  find_by_status
  - Find student by class: find_by_class
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
  
##Find a student by name: `find_by_name`
Find an existing student in the application by their name
- Format: `find_by_name NAME`
  - Returns a list of students with the specified `NAME`
  - `NAME` is case-insensitive
  - Searching for the first or last name would return a list of names that corresponds to the search input provided
- Example:
  - `find_by_name john`

##Find a student by status: `find_by_status`
Find an existing student in the application by their Covid-19 Status
- Format: `find_by_status STATUS`
  - Returns a list of students with the specified `STATUS`
  - `STATUS` is case-insensitive
- Example:
  - `find_by_status positive`
  - `find_by_status negative`
