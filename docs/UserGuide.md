---
layout: page
title: User Guide
---

ManageEZPZ is a **desktop app for that allows managers or supervisors to manage employees and assign tasks to them.  Optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ManageEZPZ.jar` from [here](https://github.com/AY2122S2-CS2103-F11-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ManageEZPZ.

4. Double-click the file to start the app.

5. Start communicating with ManageEZPZ using the command box.

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listTask`** : Lists all Tasks.

   * **`addEmployee`**`n/John Doe p/98765432 e/johnd@example.com` : Adds a contact named `John Doe` to ManageEZPZ.

   * **`deleteTask`**`3` : Deletes the 3rd Task shown in the Task list.

   * **`clear`** : Deletes everything from ManageEZPZ.
   
   * **`addTodo desc/read book`** : Adds a todo task with a description of `read book` to the Task list.

   * **`tagTask 1 n/John Doe`** : Assigns the first task on the task list to an employee named John Doe. 

   * **`exit`** : Exits the app.
--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addEmployee n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters for adding employees can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Task related Commands must be strictly lower case.

* Task related parameters must be in sequence as shown in the instruction.

* All indexes are int based, as such the maximum value is 2147483647. (2<sup>32</sup>)

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage_new.png)

Format: `help`


### Adding an employee: `addEmployee`

Adds an employee to ManageEZPZ.

Format: `addEmployee n/NAME p/PHONE_NUMBER e/EMAIL`

Examples:
* `addEmployee n/John Doe p/98765432 e/johnd@example.com`
* `addEmployee p/98754123 n/Betsy Crowe e/betsycrowe@example.com`

### Listing all employees : `listEmployee`

Shows a list of all employees in the address book.

#### Format: 
* `listEmployee`


### Finding employees: `findEmployee`

Finds employees that have all of the options as entered.

#### Note:
* Options can be stacked together in any order. 
* No options entered will result in all employees list out like the `listEmployee` command.

#### Format:
* `findEmployee n/NAMES`
  * Finds all employees which has their name contain any of the words in `NAMES`
* `findEmployee p/PHONE_NUMBER`
  * Find employees with the exact phone number
* `findEmployee e/EMAIL`
  * Finds employees with the exact email
  
#### Examples:
* `findEmployee n/Alex Yeoh`
* `findEmployee p/65162727`
* `findEmployee e/dcsdcr@nus.edu.sg`
* `findEmployee n/Alex Yeo p/65162727 e/alexyeo@google.com`

### Deleting an employee: `deleteEmployee`

Deletes the specified employee from the address book.

Format: `deleteEmployee INDEX`

* Deletes the employee at the specified INDEX.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `list` followed by `deleteEmployee 2` deletes the 2nd person in the address book.
* `find Betsy` followed by delete 1 deletes the 1st person in the results of the find command.


### Adding a Task: `addTodo`, `addEvent`, `addDeadline`

Adds a Task into the Task list.

Format:
* `addTodo desc/TASK_DESCRIPTION`
* `addDeadline desc/TASK_DESCRIPTION by/DATE TIME`
* `addEvent desc/TASK_DESCRIPTION at/DATE START_TIME END_TIME`

Examples:
* `addTodo desc/Powerpoint slides for company XYZ`
* `addDeadline desc/Client Proposal Slides by/2022-03-20 1800`
* `addEvent desc/Business meeting at/2022-02-18 1900 2000`

<div markdown="span" class="alert alert-primary">:bulb: **Take Note:**
For deadline and event, the DATE must be in this format: YYYY-MM-DD and the TIME in this format: HHmm (in 24 hr format)

Furthermore, parsing of task is done using the keywords such as "desc/", "by/" & "at/" as such, 
the parsing mechanism would take everything inserted after the keywords.
</div>


### Marking a task as done : `mark`
Mark a task in the Task list as done :

Format: `mark INDEX`

* Marks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed Task list.
* The index **must be a positive integer** 1, 2, 3, …​

### UnMarking a task as done : `unmark`
Mark a task in the Task list as done :

Format: `unmark INDEX`

* UnMarks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed Task list.
* The index **must be a positive integer** 1, 2, 3, …​

### Deleting a Task : `delete`
Deletes the specified Task from the List.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed Task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` deletes the 2nd Task in the Task list.

### Locating Task by multiple options: `findTask`

Finds task(s) based on multiple conditions provided.

#### Note:
* Arguments can be stacked together in any order. 
* Task Type is optional. 
* However, only one task type is allowed.
* Any other invalid options not stated below will be ignored
* No options provided will list down all the tasks like `listTask`

#### Task Type Available:
* `todo/`: Todos
* `deadline/`: Deadlines
* `event/`: Events

#### Options :
* `desc/`: Description of the tasks
* `date/`: Date of the task in YYYY-MM-DD (Only for deadline and event)
* `priority/`: Priority of task. Only `HIGH`, `MEDIUM`, `LOW` and `NONE`
* `assignees/`: The assignees that was assigned to the task (Only one full name of assignee allowed)
* `isMarked/`: Whether the task is marked. Only `true` or `false`.

#### Format:
* `findTask todo/`
  * Find all todos
* `findTask deadline/`
  * Find all deadlines 
* `findTask event/` 
  * Find all events
* `findTask desc/[LIST OF WORDS]`
  * Finds all tasks which contain any of the words in [LIST OF WORDS].
* `findTask date/YYYY-MM-DD`
  * Find all deadlines and events with the date
* `findTask priority/PRIORITY`
  * Find all tasks with the given PRIORITY [HIGH, MEDIUM, LOW, NONE]
* `findTask assignees/Assignee full name`
  * Find all tasks assignee to the stated assignee (in full name)
* `findTask isMarked/BOOLEAN`
  * Find all tasks that is already marked (`true`) or unmarked (`false`)
    * Finds the task with a description that contains all of the following options:
      * Description which contains the word “School”, 
      * date 2022-04-16, 
      * high priority, 
      * assigned to Alex Yeoh 
      * and is marked.

  
#### Example:
  * `findTask desc/homework`
  * `findTask date/2022-04-16` 
  * `findTask desc/work priority/HIGH` 
  * `findTask deadlines/ desc/school date/2022-04-16 priority/HIGH assignees/Alex Yeo isMarked/true`


### Listing all Tasks : `listTask`

Shows a list of all tasks in ManageEZPZ.

Format:

* `listTask`

### Tagging Tasks to Employees: `tagTask`

Assigns a Task to the Employee

Note:
* For `tagTask`, the INDEX must be a positive integer 1,2,3...
* For `tagTask`, the NAME must be a valid Employee Name from ManageEZPZ.

Format: `tagTask INDEX n/NAME`

Example: `tagTask 1 n/Alex Yeoh`

### Untag Tasks from Employees: `untagTask`

Deallocate the Task from Employee.

Note:
* For `untagTask`, the INDEX must be a positive integer 1,2,3...
* For `untagTask`, the NAME must be a valid Employee Name from ManageEZPZ.

Format: `untagTask INDEX n/NAME`

Example: `untagTask 1 n/Alex Yeoh`

### Tag Priority to a Task: `tagPriority`

Assign a Task to a Priority which is enum of “HIGH / MEDIUM / LOW / NONE”

Note:
* For `tagPriority`, the INDEX must be a positive integer 1,2,3...
* For `tagPriority`, the ENUM must be HIGH, MEDIUM, LOW, or NONE.

Format: `tagPriority INDEX priority/ENUM`

Example: 
* `tagPriority 1 priority/HIGH`
* `tagPriority 1 priority/NONE`

### Clearing all entries : `clear`

Clears all entries from the Task list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ManageEZPZ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ManageEZPZ data are saved as a JSON file `[JAR file location]/data/ManageEZPZ.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ManageEZPZ will discard all data and start with an empty data file at the next run.
</div>

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ManageEZPZ home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

###Employee Related Commands
Action | Format, Examples
--------|------------------
**Add Employee** | `addEmployee n/NAME p/PHONE_NUMBER e/EMAIL` <br> e.g., `addEmployee n/James Ho p/22224444 e/jamesho@example.com`
**Edit Employee** | `editEmployee INDEX n/NAME p/PHONE_NUMBER e/EMAIL` <br> e.g., `edit 2 n/James Lee e/jameslee@example.com`
**Delete Employee** | `deleteEmployee INDEX` <br> e.g., `deleteEmployee 3`
**Find Employee** | `findEmployee OPTIONS` <br> `findEmployee n/Alex Yeoh`
**listEmployee** | `listEmployee`

###Task Related Commands
Action | Format, Examples
------------|--------------
**Add Todo Task** | `addTodo desc/TASK_DESCRIPTION` <br> e.g., `addTodo desc/read book`
**Add Deadline Task** | `addDeadline desc/TASK_DESCRIPTION by/DATETIME` <br> e.g., `addDeadline desc/return book by/16-02-2022 1800`
**Add Event Task** | `addEvent desc/TASK_DESCRIPTION at/DATE START_TIME END_TIME` <br> e.g., `addEvent desc/project meeting at/17-02-2022 1900 2000`
**mark Task** | `markTask INDEX` <br> e.g., `markTask 2`
**unmark Task** | `unmarkTask INDEX` <br> e.g., `unmarkTask 2`
**delete Task** | `deleteTask INDEX` <br> e.g., `deleteTask 2`
**find Task** | `findTask OPTIONS` <br> e.g.,`findTask todo/`
**list Task** | `listTasks`
**tag Task** | `tagTask INDEX n/NAME` <br> e.g.,`tagTask 1 n/Alex Yeoh`
**untag Task** |`untagTask INDEX n/NAME` <br> e.g.,`untagTask 1 n/Alex Yeoh`
**tag Priority** | `tagPriority INDEX priority/ENUM` <br> e.g.,`tagPriority 1 priority/HIGH`

###Others
Action | Format
------------|-------------
**Clear** | `clear`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
