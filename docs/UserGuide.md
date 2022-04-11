---
layout: page
title: User Guide
---

HRConnect is a desktop application for managing the contacts of job applicants. <br> <br>
It can also be used to keep track of: <br>
* stage progress of each applicant during the application process
* interviews with each applicant
* miscellaneous tasks that need to be completed

![combined](images/alltogether.PNG)

<div style="page-break-after: always;"></div>

<h1>Table Of Contents</h1>
* Table of Contents
{:toc}

<div style="page-break-after: always;"></div>

# Quick Start
1. Ensure you have `Java 11` or above installed in your computer.
2. Download the latest `HRConnect.jar` from <a href="https://github.com/AY2122S2-CS2103T-W11-2/tp/releases">here</a>
3. Copy the file to the folder you want to use as the *home folder* for your HRConnect.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. <br> ![Ui](images/Ui.png)
5. Type your command into the command box and press `Enter` to execute it.<br> Some sample commands to try:
    * `add [p] n/Bob Tan p/98765876 e/bot@gmail.com a/262 Serangoon Central Drive 1-125
      j/Software Developer s/INPROGRESS`: Adds a new applicant, Bob Tan, to the applicant list
    * `list [p]`: Lists all applicants
    * `delete [i] 1`: Deletes interview index 1 from the interview list
    * `clear [i]`: Clear all interviews
    * `exit`: Exits the app
6. Refer to [Features](#features) section directly below for details on each command.

<div style="page-break-after: always;"></div>

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format regarding Features:**<br>

* Items in angle brackets are optional.<br>
  e.g n/NAME <p/PHONE_NUMBER> can be used as n/John Doe p/PHONE_NUMBER or as n/John Doe.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.

* Items with ellipsis (...) after them can be used multiple times including zero times.<br>
  e.g. <a/ADDRESS>... can be used as   (i.e. 0 times), a/ADDRESS, a/ADDRESS etc.

* Parameters can be supplied in any order.<br>
  e.g. if the command requires n/NAME p/PHONE_NUMBER, <br> p/PHONE_NUMBER n/NAME is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify p/12341234 p/56785678, only p/56785678 will be taken.

</div>
&nbsp;
<div style="page-break-after: always;"></div>

## General Features
----------

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format regarding General Features:**<br>

* Extraneous parameters for commands that do not take in parameters (e.g. `help` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing Help: `help`
Shows a message explaining how to access the help page and the basic flags.

Format: `help`

&nbsp;

### Exiting the program: `exit`
Exits the program.

Format: `exit`

<div style="page-break-after: always;"></div>

## Applicant Features
----------

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the Applicant List:**<br>

* Applicant list is sorted in order in which the applicant was added into the list.<br>
  e.g If Alex Tan was added before Alex Wong, Alex Tan would be listed before Alex Wong.

</div>

### Adding a new job applicant: `add [p]`
Adds a new job applicant to the HRConnect.

Format: `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS j/JOB_POSITION s/STAGE`

* Duplicate applicants **cannot** be added. Applicants are considered to be duplicates if they share the **same name (case-insensitive)**. <br>
e.g. `Alex Tan` and `alex tan` are duplicate applicants.
* If the `NAME` input contain empty spaces, it will be trimmed and each applicant will be considered as a duplicate even if their names differ in the amount of empty spaces. Let `(space)` denote an empty space. <br>
e.g. `Alex(space)Tan` and `Alex(space)(space)Tan` are duplicate applicants. <br>
e.g. `Alex(space)Tan` and `AlexTan` are NOT duplicate applicants as `AlexTan` does not have any empty spaces.

:bulb: View constraints on flags and parameters [here](#flags-and-parameters-format-constraints)

<div style="page-break-after: always;"></div>

Example:<br>
`add [p] n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 j/Software Developer s/INPROGRESS`

![add-applicant](images/add-applicant.png)

&nbsp;
### Editing a job applicant: `edit [p]`
Edits an existing job applicant in the HRConnect

:exclamation: **Take note: Cannot edit an applicant if he/she has an interview scheduled.**

Format: `edit [p] INDEX <n/NAME> <p/PHONE_NUMBER> <e/EMAIL> <a/ADDRESS> <j/JOB_POSITION> <s/STAGE>`

* Edits the applicant at the specified `INDEX`. `INDEX` refers to the numerical position of the **applicant in the applicant list**. `INDEX` **must** be a positive integer 1, 2, 3, …​
* At least **one** of the optional fields must be provided.
* Existing values will be updated to the input values. If input value is same as existing value, success message will still be shown.

:bulb: View constraints on flags and parameters [here](#flags-and-parameters-format-constraints)
<div style="page-break-after: always;"></div>

Example:<br>
`edit [p] 2 n/Amanda Tan j/Software Developer s/INPROGRESS` <br><br>

Before
![edit](images/before-edit-contact-3.png)

After
![edit](images/after-edit-contact-3.png)

<div style="page-break-after: always;"></div>

### Deleting job applicant: `delete [p]`
Deletes an existing job applicant from the HRConnect.

:exclamation: **Take note: Cannot delete an applicant if he/she has an interview scheduled.**

Format: `delete [p] INDEX`

* Deletes the applicant at the specified `INDEX`. `INDEX` refers to the numerical position of the **applicant in the applicant list**.`INDEX` **must** be a positive integer 1, 2, 3, …​

Example:<br>
`delete [p] 1`

&nbsp;
### Listing all job applicants: `list [p]`
Shows a list of all job applicants in the HRConnect.

Format: `list [p]`

&nbsp;
### Clearing all job applicants: `clear [p]`
Clears all job applicants from the HRConnect.

:exclamation: **Take note: Cannot clear applicant list if interview list is not empty.**

Format: `clear [p]`

* Clear command does not throw an error even if applicant list is already empty and success message will still be shown.

<div style="page-break-after: always;"></div>

### Finding job applicant(s) by search terms: `find [p]`
Finds job applicants with data containing any of the specified search terms.

Use `g/` flags to find job applicants whose data contain **all** the search terms.

:bulb: Tip: Use multiple `g/` flags as an **OR** command (e.g. `find [p] g/n/alex g/j/software developer g/s/INPROGRESS`)

Format: `find [p] g/SEARCH_TERM <SEARCH_TERM>... <g/SEARCH_TERM <SEARCH_TERM>...>...`

* `find [p]` **is required** to start with a `g/` flag.
* Finding job applicants `[p]` **only** accepts `g/`, `n/`, `p/`, `e/`, `a/`, `j/`, and `s/` flags.
* If a SEARCH_TERM without a flag (e.g. `find [p] g/alex`) is used, an error will be raised.
* SEARCH_TERMs are still required to follow the format as defined as by their respective flags.

:bulb: View constraints on flags and parameters [here](#flags-and-parameters-format-constraints)

Examples:<br>
`find [p] g/s/ACCEPTED g/n/John Doe` is logically equivalent to <br>
`find [p] s/ACCEPTED OR n/John Doe`  <br> <br>
![find](images/find-applicant-OR-example.png) <br> <br>
<div style="page-break-after: always;"></div>

`find [p] g/j/Software Developer s/REJECTED` is logically equivalent to <br>
`find [p] j/Software Developer AND s/REJECTED` <br> <br>
![find](images/find-applicant-AND-example.png) <br> <br>
`find [p] g/j/Software Developer s/REJECTED g/n/John Doe` is logically equivalent to <br>
`find [p] (j/Software Developer AND s/REJECTED) OR n/John Doe` <br> <br>
![find](images/find-applicant-AND-OR-example.png) <br> <br>

<div style="page-break-after: always;"></div>

## Interview Features
----------

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the Interview List:**<br>

* Interview list is sorted in date time chronological order.<br>
  e.g An interview scheduled on 29 March 2020 is listed before an interview scheduled on 2 April 2020.
* Interview list can contain interviews with date and time before the current date and time. <br>
  e.g. If current date time is 4 April 2022, interview list can still contain interview with date 30 March 2022.
* Interview list can contain interviews with applicants in any stages. <br>
  e.g. A rejected applicant can still be scheduled for an interview.

</div>

### Adding a new interview slot for a job applicant: `add [i]`
Adds a new job interview slot to the HRConnect.

:exclamation: **Take note: Only can add an interview for an applicant that is present in applicant list.**

Format: `add [i] INDEX d/DATE t/TIME`

* `INDEX` refers to the numerical position of the **applicant in the applicant list**. `INDEX` **must** be a positive integer 1, 2, 3, …​<br>
* `TIME` refers to the start time of the interview. Duration of each interview is not fixed so a minute time gap between interviews is also allowed.
* Duplicate interviews **cannot** be added. Interviews are considered to be duplicates if they share the **same date AND time**. <br>
e.g. `add [i] 1 d/2021-06-25 t/17:30` and `add [i] 2 d/2021-06-25 t/17:30` are adding duplicate interviews. <br>
e.g. `add [i] 1 d/2021-06-25 t/17:30` and `add [i] 1 d/2021-06-30 t/12:30` are **NOT** adding duplicate interviews as the same applicant can have more than 1 interview as long as it is not the same date and time.

:bulb: View constraints on flags and parameters [here](#flags-and-parameters-format-constraints)

Example:<br>
`add [i] 1 d/2021-06-25 t/17:30`

![add-interview](images/add-interview.png)

&nbsp;
### Editing an existing interview slot: `edit [i]`
Edits an existing interview slot in the HRConnect

Format: `edit [i] INDEX <d/DATE> <t/TIME>`

* Edits the interview at the specified `INDEX`. `INDEX` refers to the numerical position of the **interview in the interview list**. `INDEX` **must** be a positive integer 1, 2, 3, …​
* `TIME` refers to the start time of the interview. Duration of each interview is not fixed so a minute time gap between interviews is also allowed.
* At least **one** of the optional fields must be provided. 
* Existing values will be updated to the input values. If input value is same as existing value, success message will still be shown.

:bulb: View constraints on flags and parameters [here](#flags-and-parameters-format-constraints)
<div style="page-break-after: always;"></div>

Example: <br>
`edit [i] 1 d/2021-12-30 t/10:30` <br><br>

Before
![edit-interview](images/edit-interview-1.PNG)

After
![edit-interview](images/edit-interview.png)

<div style="page-break-after: always;"></div>

### Deleting an interview slot: `delete [i]`
Deletes an existing interview slot in the HRConnect.

Format: `delete [i] INDEX`

* Deletes the interview at the specified `INDEX`. `INDEX` refers to the numerical position of the **interview in the interview list**. `INDEX` **must** be a positive integer 1, 2, 3, …​

Example:<br>
`delete [i] 1`

&nbsp;
### Listing all scheduled interviews: `list [i]`
Shows a list of all scheduled interviews in the HRConnect.

Format: `list [i]`

&nbsp;
### Clearing all interviews: `clear [i]`
Clears all interviews from the HRConnect.

Format: `clear [i]`

* Clear command does not throw an error even if interview list is already empty and success message will still be shown.

<div style="page-break-after: always;"></div>

### Finding scheduled interview slot(s) by search terms: `find [i]`
Finds interview slots with data containing any of the specified search terms.

Use `g/` flags to find interview slot(s) with data containing **all** the search terms.

:bulb: Hint: Use multiple `g/` flags to simulate an **OR** command (e.g. `find [i] g/n/alex g/d/2022-01-20 g/t/15:03`)

Format: `find [i] g/SEARCH_TERM <SEARCH_TERM>... <g/SEARCH_TERM <SEARCH_TERM>...>...`

* `find [i]` **is required** to start with a `g/` flag.
* Finding interviews `[i]` **only** accepts `g/`, `n/`, `d/`, `t/`, and `j/` flags.
* If a SEARCH_TERM without a flag (e.g. `find [i] g/alex`) is used, an error will be raised.
* SEARCH_TERMs are still required to follow the format as defined as by their respective flags.

:bulb: View constraints on flags and parameters [here](#flags-and-parameters-format-constraints)

Examples:<br>
`find [i] g/n/Amanda Tan g/j/Software Developer g/t/10:10` is logically equivalent to <br>
`find [i] n/Amanda Tan OR j/Software Developer OR t/10:10` <br> <br>
![find](images/find-interview-OR-example.PNG) <br> <br>
<div style="page-break-after: always;"></div>

`find [i] g/n/Amanda Tan j/Software Developer t/10:10` is logically equivalent to <br>
`find [i] n/Amanda Tan AND j/Software Developer AND t/10:10` <br> <br>
![find](images/find-interview-AND-example.PNG) <br> <br>
`find [i] g/n/Amanda Tan j/Software Developer g/t/10:10` is logically equivalent to 
`find [i] (n/Amanda Tan AND g/j/Software Developer) OR t/10:10` <br> <br>
![find](images/find-interview-AND-OR-example.PNG) <br> <br>

<div style="page-break-after: always;"></div>

## Task Features
----------

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the Task List:**<br>

* Task list is sorted in date time chronological order.<br>
  e.g An task scheduled on 29 March 2020 is listed before a task scheduled on 2 April 2020.
* Task list can contain tasks with date and time before the current date and time. <br>
  e.g. If current date time is 4 April 2022, task list can still contain task with date 30 March 2022.

</div>

### Adding a new task to the miscellaneous task list: `add [t]`
Adds a new task to the HRConnect.

Format: `add [t] h/HEADER d/DATE t/TIME i/INFORMATION` 

* `DATE` and `TIME` refers to the due date and time of the task.
* Duplicate tasks **cannot** be added. Tasks are considered to be duplicates if they share the **same header (case-insensitive) AND date AND time**. <br>
e.g. `add [t] h/Update interview list d/2021-06-25 t/17:30 i/Update half of the interview list` and `add [t] h/Update interview list d/2021-06-25 t/17:30 i/Update the entire interview list` are adding duplicate interviews.
* If the `HEADER` input contain empty spaces, it will be trimmed and each header will be considered as a duplicate even if their header differ in the amount of empty spaces. Let `(space)` denote an empty space. <br>
e.g. `Update(space)interview(space)list` and <br>
`Update(space)(space)interview(space)list` are duplicate headers. <br>
e.g. `Update(space)interview(space)list` and `Updateinterviewlist` are NOT duplicate headers as `Updateinterviewlist` does not have any empty spaces.

:bulb: View constraints on flags and parameters [here](#flags-and-parameters-format-constraints)

<div style="page-break-after: always;"></div>

Example:<br>
`add [t] h/Add interview slots d/2022-04-01 t/17:30 i/Add all interviews happening in the following week`

![add-task](images/add-task.PNG)

&nbsp;
### Editing an existing task: `edit [t]`
Edits an existing task in the HRConnect

Format: `edit [t] INDEX <h/HEADER> <d/DATE> <t/TIME> <i/INFORMATION>` 

* `DATE` and `TIME` refers to the due date and time of the task.
* Edits the task at the specified `INDEX`. `INDEX` refers to the numerical position of the **task in the task list**. `INDEX` **must** be a positive integer 1, 2, 3, …​
* At least **one** of the optional fields must be provided.
* Existing values will be updated to the input values. If input value is same as existing value, success message will still be shown.

:bulb: View constraints on flags and parameters [here](#flags-and-parameters-format-constraints)
<div style="page-break-after: always;"></div>

Example: <br>
`edit [t] 1 d/2021-12-30 t/10:30` <br><br>

Before
![edit-task-2](images/edit-task-2.PNG)

After
![edit-task](images/edit-task.PNG)

<div style="page-break-after: always;"></div>

### Deleting a task: `delete [t]`
Deletes an existing task in the HRConnect.

Format: `delete [t] INDEX`

* Deletes the task at the specified `INDEX`. `INDEX` refers to the numerical position of the **task in the task list**. `INDEX` **must** be a positive integer 1, 2, 3, …​

Example:<br>
`delete [t] 1`

&nbsp;
### Listing all tasks: `list [t]`
Shows a list of all tasks in the HRConnect.

Format: `list [t]`

&nbsp;
### Clearing all tasks: `clear [t]`
Clears all tasks from the HRConnect.

Format: `clear [t]`

* Clear command does not throw an error even if task list is already empty and success message will still be shown.

<div style="page-break-after: always;"></div>

### Finding task(s) by search terms: `find [t]`
Find tasks with data containing any of the specified search terms.

Use `g/` flags to find task(s) with data containing **all** the search terms.

:bulb: Hint: Use multiple `g/` flags to simulate an **OR** command (e.g. `find [t] g/h/update g/t/10:10`)

Format: `find [t] g/SEARCH_TERM <SEARCH_TERM>... <g/SEARCH_TERM <SEARCH_TERM>...>...`

* `find [t]` **is required** to start with a `g/` flag.
* Finding tasks `[t]` **only** accepts `g/`,`h/`, `d/`, `t/`, and `i/` flags.
* If a SEARCH_TERM without a flag (e.g. `find [t] g/update`) is used, an error will be raised.
* SEARCH_TERMs are still required to follow the format as defined as by their respective flags.

:bulb: View constraints on flags and parameters [here](#flags-and-parameters-format-constraints)

Examples:<br>
`find [t] g/d/2022-03-04 g/h/Update interview list g/t/10:10` is logically equivalent to <br>
`find [t] d/2022-03-04 OR h/Update interview list OR t/10:10` <br> <br>
![find](images/find-task-OR-example.PNG) <br> <br>
<div style="page-break-after: always;"></div>

`find [t] g/d/2022-03-06 h/Update interview list t/09:00` is logically equivalent to <br>
`find [t] d/2022-03-06 AND h/Update interview list AND t/09:00` <br> <br>
![find](images/find-task-AND-example.PNG) <br> <br>
`find [t] g/d/2022-03-06 h/Update interview list g/t/10:10` is logically equivalent to <br>
`find [t] (d/2022-03-06 AND h/Update interview list) OR t/10:10` <br> <br>
![find](images/find-task-AND-OR-example.PNG) <br> <br>

<div style="page-break-after: always;"></div>

## Storage
----------
### Saving the data
HRConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

&nbsp;
### Editing the data file
HRConnect data are saved as a JSON file `PATH_TO_JAR_FILE/data/HRConnect.json`. Advanced users are welcome to update data directly by editing that data file.

:exclamation: **Caution:** If your changes to the data file makes its format invalid, the HRConnect will discard all data and start with an empty data file at the next run. <br><br>
:exclamation: **Caution:** Manual modification of the JSON file is **NOT** a supported feature. Inconsistent data resulting from manual modification of the data file should **NOT** be considered as unexpected behaviour. <br>
Example: Manually editing the `NAME` of applicant in the applicant list of the data file but not on interview list will lead to inconsistent data vice versa and any unusual behaviour caused by this should **NOT** be seen as a bug.

&nbsp;
<div style="page-break-after: always;"></div>

### Importing the data file: `import`

Imports all **job applicants** data from a *csv* save file generated from this HRConnect.

File structure for csv file (tab delimited):

name | phone number | email | address | job position | stage

Notes:
1. Filepath can be relative or absolute.
2. No duplicates are allowed to be imported into the HRConnect.
   1. Data in the HRConnect has a higher priority than data in the save file.
3. Save files needs to end with `.csv` in order for the HRConnect to recognise the save file.
4. The csv save file can have at most 1 newline at the end of file for it to be considered valid.
5. If any error is found, **none** of the data in the save file will be imported into the HRConnect.

Format: `import FILEPATH`

Example:<br>
`import C:\Users\YOUR_USERNAME\Desktop\data.csv`

&nbsp;
<div style="page-break-after: always;"></div>

### Exporting to a csv data file: `export`

Exports all **job applicants** data from the HRConnect into a *csv* save file.

File structure for csv file (tab delimited):

name | phone number | email | address | job position | stage

Notes:
1. Filepath of specified CSV file can be relative or absolute.
2. Specifying the same csv file name and path will overwrite the data inside the specified csv file.
3. Csv file **must** have .csv as a file extension. 
4. If any error is found while executing the command,
**none** of the data from the HRConnect will be exported into the specified csv file.

:exclamation: **Important:** Some invalid naming conventions for the specified csv file might be allowed 
on different OS. E.g. `my:Data.csv` is a valid name on MacOS but not on WindowsOS.

Format: `export FILEPATH`

Absolute filepath example for WindowsOS: `export C:\Users\YOUR_USERNAME\Desktop\myData.csv` <br>
Relative filepath example for WindowsOS: `export .\myData.csv` <br><br>
Absolute filepath example for MacOS: `export  /Users/YOUR_USERNAME/Downloads/myDataFile.csv` <br>
Relative filepath example for MacOS: `export  ./myDataFile.csv`

:exclamation: **Important:** Some file paths might be recognised as valid/invalid depending on the OS.  

:exclamation: **Important:** Data in exported csv file will look different depending on the application used to view the
file even though the data is seperated by tabs. 

<div style="page-break-after: always;"></div>

# Summary
## Flags and Parameters Format Constraints

| Flags | Parameters   | Format Constraints                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
|------:|--------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|    a/ | ADDRESS      | Addresses can take any values, and it should not be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
|    d/ | DATE         | Date should be in the format YYYY-MM-dd                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
|    e/ | EMAIL        | Emails should be of the format local-part@domain and adhere to the following constraints: <br/> <br/> 1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters. <br/> <br/> 2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods. <br/> <br/> The domain name must: <br/> - end with a domain label at least 2 characters long <br/> - have each domain label start and end with alphanumeric characters <br/> - have each domain label consist of alphanumeric characters, separated only by hyphens, if any. |
|    g/ | SEARCH_TERM  | Only used in the `find` command. Used in combination with the other flags on this list.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
|    h/ | HEADER       | Header should only contain alphanumeric characters and spaces, and it should not be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
|    i/ | INFORMATION  | Information should only contain alphanumeric characters and spaces, and it should not be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|    j/ | JOB_POSITION | Job should only contain alphanumeric characters and spaces, and it should not be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
|    n/ | NAME         | Names should only contain alphanumeric characters and spaces, and it should not be blank                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
|    p/ | PHONE_NUMBER | Phone numbers should only contain numbers, and it should be at least 3 digits long                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
|    s/ | STAGE        | Stage should be only INPROGRESS or ACCEPTED or REJECTED (case-sensitive)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
|    t/ | TIME         | Time should be in the format HH:MM                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |


## Section Types

| Type | Format                                            |
|-----:|:--------------------------------------------------|
|  [i] | Applies the current command to the interview list |
|  [p] | Applies the current command to the applicant list |
|  [t] | Applies the current command to the task list      |

<div style="page-break-after: always;"></div>

## Command Summary

**General Command Summary** <br><br>

| Action | Format |
|:------:|:------:|
|  Help  | `help` |
|  Exit  | `exit` |

<br>

**Applicant Command Summary** <br><br>

|                Action | Format                                                                                                                                                            |
|----------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|     Add Job Applicant | `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS j/JOB_POSITION s/STAGE`                                                                                          |
|    Edit Job Applicant | `edit [p] INDEX <n/NAME> <p/PHONE_NUMBER> <e/EMAIL> <a/ADDRESS> <j/JOB_POSITION> <s/STAGE>` (`INDEX` refers to numerical position of applicant in applicant list) |
|  Delete Job Applicant | `delete [p] INDEX` (`INDEX` refers to numerical position of applicant in applicant list)                                                                          |
|   List Job Applicants | `list [p]`                                                                                                                                                        |
|  Clear Job Applicants | `clear [p]`                                                                                                                                                       |
| Find Job Applicant(s) | `find [p] g/SEARCH_TERM <SEARCH_TERM>... <g/SEARCH_TERM <SEARCH_TERM>...>...`                                                                                     |

<div style="page-break-after: always;"></div>

**Interview Command Summary** <br><br>

|            Action | Format                                                                                                   |
|------------------:|:---------------------------------------------------------------------------------------------------------|
|     Add Interview | `add [i] INDEX d/DATE t/TIME` (`INDEX` refers to numerical position of applicant in applicant list)      |
|    Edit Interview | `edit [i] INDEX <d/DATE> <t/TIME>` (`INDEX` refers to numerical position of interview in interview list) |
|  Delete Interview | `delete [i] INDEX` (`INDEX` refers to numerical position of interview in interview list)                 |
|   List Interviews | `list [i]`                                                                                               |
|  Clear Interviews | `clear [i]`                                                                                              |
| Find Interview(s) | `find [i] g/SEARCH_TERM <SEARCH_TERM>... <g/SEARCH_TERM <SEARCH_TERM>...>...`                            |

<div style="page-break-after: always;"></div>

**Task Command Summary** <br><br>

|       Action | Format                                                                                                                    |
|-------------:|:--------------------------------------------------------------------------------------------------------------------------|
|     Add Task | `add [t] h/HEADER d/DATE t/TIME i/INFORMATION`                                                                            |
|    Edit Task | `edit [t] INDEX <h/HEADER> <d/DATE> <t/TIME> <i/INFORMATION>` (`INDEX` refers to numerical position of task in task list) |
|  Delete Task | `delete [t] INDEX` (`INDEX` refers to numerical position of task in task list)                                            |
|   List Tasks | `list [t]`                                                                                                                |
|  Clear Tasks | `clear [t]`                                                                                                               |
| Find Task(s) | `find [t] g/SEARCH_TERM <SEARCH_TERM>... <g/SEARCH_TERM <SEARCH_TERM>...>...`                                             |

<br>

**Storage Command Summary** <br><br>

|        Action        |                                                                                                                                                           Format                                                                                                                                                            |
|:--------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|        Import        |                                                                                                                                                      `import FILEPATH`                                                                                                                                                      |
|        Export        |                                                                                                                                                      `export FILEPATH`                                                                                                                                                      |
