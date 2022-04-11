---
layout: page
title: User Guide
---

TAlent Assistant™ is a **desktop, lightweight and centralized management system** catered to NUS School of Computing professors for managing
the interview scheduling process of candidates applying to be undergraduate Teaching Assistants (TA). 
Professors will be able to access the candidates’ application data easily and review their general availability for 
scheduling interviews during office hours. TAlent Assistant™ is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a 
Graphical User Interface (GUI). If you can type fast, this application will be able to help you manage all things under the hood of the TA initiative 
faster than traditional GUI applications.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TAlentAssistant.jar` from [here](https://github.com/AY2122S2-CS2103-F11-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAlent Assistant™.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/startupui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`add id/A0123456B n/John Doe p/87654321 e/E0123456@u.nus.edu c/Computer Science yr/2 avail/1,2,3`** Adds a new candidate into the system.

    * **`list`** : Lists all candidates.

    * **`delete 1`** : Deletes the first candidate displayed in the system.

    * **`find k/Alex f/name`** : Searches for all candidates with name containing “alex" (case-insensitive).

    * **`sort s/name`** : Sorts all candidates by name in ascending alphabetical order (i.e. A-Z).
   
    * **`schedule add candidate/1 at/05-05-2022 10:00`** : Schedules the first candidate for an interview at 5 May 2022 10AM.

    * **`help`** : List all commands in the system.
   
    * **`exit`** : Closes and exits the system.
    
1. Refer to the [Features](#features) section below for details of all the available commands.

--------------------------------------------------------------------------------------------------------------------

## Navigating the Display

<div markdown="block" class="alert alert-info">

**:information_source: Notes about GUI display layout:**<br>

* **CommandBox Input** : Top panel for user to type in commands.

* **Feedback Panel** : Middle panel to show the feedback message after execution of command.

* **Candidates List** : Bottom leftmost panel displays the list of candidates in the system, alongside some key information.

* **Candidate Profile** : Bottom middle panel brings up a focused view of the candidate's profile when the related `focus` command is entered.

* **Scheduled Interviews** : Bottom rightmost panel displays the list of scheduled interviews.

The three bottom panels can be resized based on user preferences, by dragging the shared vertical borders.

Features relating to the display of information within these panels are described below.

</div>

![Ui](images/Ui.png)

--------------------------------------------------------------------------------------------------------------------

## Features
* [Viewing help](#viewing-help)
* [Managing candidates](#managing-candidates)
* [Scheduling interviews](#scheduling-interviews)
* [Miscellaneous commands](#miscellaneous-commands)
* [Processing data](#processing-data)

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/82341234 p/86785678`, only `p/86785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Optional parameters for commands are represented with `[` and `]`.<br>
  e.g. `[ATTRIBUTE_FIELD/VALUE]`.

* The following table lists some common abbreviations used in the command prefixes or `ATTRIBUTE_FIELD`s.

| Abbreviation(s)     | Refer(s) to        |
|---------------------|--------------------|
| `is` or `intstatus` | interview status   |
| `as` or `appstatus` | application status |
| `yr`                | seniority          |
| `avail`             | availability       |

**:information_source: Notes about the entities' values:**<br>

* A candidate's `course` can only be either of the 5 computing courses in NUS School of Computing i.e. Business Analytics, Computer Engineering, Computer Science, Information Security or Information Systems.
  * Reason being, our **Target User Profile** is restricted to NUS SoC.

* A candidate's `seniority` ranges as such [1, 4], i.e. 1, 2, 3 or 4. It will be displayed as `COMX` where X is [1, 4].
  * To address seniority of students in double-degree programmes, it has been discussed that `COM5` has not been seen in NUS SoC system. Thus, the restriction of the range [1, 4].

**:information_source: Notes about duplicate candidate:**<br>

* A candidate is said to be a duplicate when any of the following input fields `STUDENT_ID`, `EMAIL` or `PHONE` exists in the system.
* e.g. The system contains `candidate A` with the following details:
  * `STUDENT_ID`: A0234567B
  * `EMAIL`: E0123456@u.nus.edu
  * `PHONE`: 87438807 <br>
Adding or editing `candidate B` with any of these 3 fields as `candidate A` constitutes as a duplicate candidate. 
</div>

## Viewing help

### Viewing help window: `help`

Lists the application commands that are available in the system.

Format: `help`

## Managing candidates

### Adding a candidate: `add`

Adds a candidate into the system.

Format: `add id/STUDENT_ID n/NAME p/PHONE e/EMAIL c/COURSE yr/SENIORITY avail/AVAILABILITY`

All fields of a `candidate` below should not be blank and will be validated.

* `STUDENT_ID` is format-sensitive with ease of case sensitivity i.e. Allowing lower caps, and in this format `AXXXXXXXX` e.g. `A0123456B`.
  * The first character has to be `A` or `a`.
  * Followed by 7 digits.
  * The last character with any alphabet is allowed.
* `NAME` should only contain alphanumeric characters and spaces.
* `EMAIL` is format-sensitive, and should only be in this format `EXXXXXXX@u.nus.edu`.
* `PHONE` is format-sensitive, and should only be a local Singapore number. i.e. Starting number of Singapore's common phone/telephone numbers - 6, 8, 9.
* `COURSE` should only be Computing courses. e.g. Business Analytics, Computer Engineering, Computer Science, Information Security, Information Systems.
  * The first character of each word in the course i.e. `C` in `Computer` and `S` in `Science` for `Computer Science` is case-sensitive.
* `SENIORITY` is a single integer ranging from 1 to 4. i.e. 1 to 4 inclusive.
  * This attribute field serves as a gauge for the SoC professor (user) on which year the candidate is in to better understand how far along they are in their undergraduate studies. It follows the faculty classification from `COM1` to `COM4`, and does not differentiate beyond `COM4` following the faculty system classification.
* `AVAILABILITY` is an input to represent the available days. e.g. `1,2,3` corresponds to an availability of `Monday`, `Tuesday` and `Wednesday`. It accepts a number range from only 1 to 5.
  * Each available day represented by the corresponding integer number should be comma-separated. It does not matter how the days are ordered in the user input.

Examples:
* `add id/A0123456B n/John Doe p/87654321 e/E0123456@u.nus.edu c/Computer Science yr/2 avail/1,2,3` adds a new candidate with Student ID, **A0123456B**, named John Doe.

### Editing a candidate: `edit`

Edits a candidate in the system.

Format: `edit INDEX PREFIX/NEW_VALUE [PREFIX/NEW_VALUE]…​`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the edit format:**<br>

`PREFIX` can take on the following values `id`, `name`, `email`, `phone`, `course`, `yr`, `as`, `avail`

</div>

* Edits the candidate at the specified `INDEX`. The index refers to the index number shown in the displayed candidate list. The index **must be a positive integer** 1, 2, 3, …​ .
* At least one field must be provided and the rest are optional fields.
* Existing values will be updated to the input values.
* `as` prefix short for Application Status should only be either `Pending`, `Accepted` or `Rejected`.

Examples:
* `edit 1 n/Jane Doe yr/3 avail/1` Edits the name, year and availability of the 1st candidate to be Jane Doe, Year 3, Monday only respectively.
* `edit 2 c/Business Analytics` Edits the course of the 2nd candidate to be `Business Analytics`.
* `edit 3 as/Accepted` Edits the application status of the 3rd candidate to `Accepted`.

### Listing all candidates : `list`

Displays all candidates found in the system.

Format: `list`

### Finding candidates by keyword(s) search: `find`

Finds and lists candidates whose attribute field(s) contain(s) any of the given keyword(s).

Format: `find k/KEYWORD [k/MORE_KEYWORDS]…​ [f/ATTRIBUTE_FIELD]`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the find format:**<br>

`ATTRIBUTE_FIELD` can take on the following values
`all`, `avail`, `appstatus`, `course`, `email`, `intstatus`, `name`, `phone`, `seniority`, `studentid`, `remark`

* A candidate's`seniority` may match any case variation of `COM1`, `COM2`, `COM3` or `COM4`.
* A candidate's `avail` may match any case variation of days in the format of `MON`, `TUE`, `WED`, `THU` or `FRI`.

</div>

* The keyword search is case-insensitive. e.g `hans` will match `Hans`.
* The attribute field is case-insensitive. e.g. `NAME` is equivalent to `name`.
* The search will return a list of all candidates containing any of the specified keyword(s) in the specified attribute field.
* For `f/all`, the search will find keywords across all attribute fields of the candidate records.
* Only full keywords will be matched
  e.g. `k/jane doe f/name` will not match candidates with name `jane koe` or just `jane`.
* Candidates matching at least one full keyword (in the specified attribute field) will be returned i.e. OR search,
  e.g. `k/Jane k/Doe f/name` will return candidates with name `Jane Koe`, `John Doe`.
* If multiple `ATTRIBUTE_FIELD`s are provided, only the last field will be used.
* If no `ATTRIBUTE_FIELD` is provided, the search will be conducted across all fields by default.

Examples:
* `find k/Jane f/name` returns candidates with name e.g. `jane` and `jane doe`.
* `find k/Computer k/science f/course` returns candidates with the course field i.e. `Computer Science` and `Computer Engineering`.
* `find k/Jane k/Tan f/name` returns candidates with name e.g. `Jane`, `tan` and `John Tan`.

### Sorting candidates by attribute field: `sort`

Returns a list of candidates sorted by the specified attribute field in ascending order (A-Z, 0-9).

Format: `sort s/ATTRIBUTE_FIELD`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the sort format:**<br>

`ATTRIBUTE_FIELD` can take on the following values
`appstatus`, `course`, `intstatus`, `name`, `seniority`, `studentid`

</div>

* The attribute field is case-insensitive. e.g. `NAME` is equivalent to `name`.
* The search will return a list of all candidates sorted in ascending order.
  (i.e. A-Z, 0-9) with regard to the specified attribute field.

Examples:
Let's reference a default sample list of unique candidates with attribute fields stated as (`name`, `studentid`):
1. (`Ben`, `A5588565L`)
2. (`Alice`, `A2344567B`)
3. (`Charlie`, `A0188565L`)

`sort s/name` returns candidates sorted by name in the following order:
1. (`Alice`, `A2344567B`)
2. (`Ben`, `A5588565L`)
3. (`Charlie`, `A0188565L`)

`sort s/studentid` returns candidates sorted by student id in the following order:
1. (`Charlie`, `A0188565L`)
2. (`Alice`, `A2344567B`)
3. (`Ben`, `A5588565L`)


### Updating a candidate's remark: `remark`

Updates the `remark` field of a candidate to the user input keyed in.

Format: `remark INDEX [r/REMARK]`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the remark format:**<br>

`INDEX` must be valid within the range of candidates in the system and non-negative.

</div>

* To remove the remark of the first candidate displayed in the system, the user can simply key in `remark 1` or `remark 1 r/`, 
which will update the remark of the candidate to be empty.

Examples:
* `remark 1 r/a good candidate` Updates the candidate's remark field to reflect 'a good candidate'.
* `remark 1 r/` Removes the candidate's remark field to reflect ``.

### Deleting a candidate : `delete`

Deletes the specified candidate from the system.

Format: `delete INDEX`

* Deletes the candidate at specified `INDEX`.
* The index refers to the index number shown in the displayed candidate list
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `list` followed by delete 2 deletes the 2nd candidate in the candidate list.
* `find k/bernice k/alex f/name` followed by delete 1 deletes the 1st candidate in the results of the find command.

<div markdown="block" class="alert alert-info">

**:information_source: Note:** Deleting a candidate from the system deletes his or her scheduled interview (if any) as well.

</div>

### Bringing a Candidate's Information to the Center Panel : `focus`

View more details about the Candidate in the middle panel.

Format: `focus INDEX`

* Additional details of the Candidate will be displayed on the center panel in the application.
* To switch to another Candidate's information, user can enter a new `focus` command and the new `index` of the candidate.
* If a `Candidate` is in focus and the user decides to `delete` the candidate, the middle panel will be cleared. 


  ![Ui](images/focusui.png)


## Scheduling interviews

<div markdown="block" class="alert alert-info">

**:information_source: Note:** 

* The duration of an interview is fixed at 30 minutes.
* Interviews that have expired will automatically be deleted from the interview schedule upon a restart of the application. An interview is considered
to be expired once its duration has fully passed. (e.g. If an interview is scheduled at 5PM, the interview is deemed to have expired from 5:30PM onwards).
* The `Interview Status` of a candidate is automatically set to `Scheduled` upon a successful scheduling of interview. 
Once the candidate's scheduled interview has expired, the `Interview Status` will be set to `Completed` upon a restart of the application.

</div>

### Scheduling a candidate for interview: `schedule add`

Schedules the specified candidate for an interview.

Format: `schedule add candidate/INDEX at/DATE_TIME`

* Schedules the candidate at the specified `INDEX` for an interview on given `DATE_TIME`.
* The candidate index refers to the index number shown in the displayed candidate list.
* The candidate index must be a positive integer 1, 2, 3, …​
* `DATE_TIME` must be specified in the format `dd-MM-yyyy HH:mm`.
* `DATE_TIME` must not be earlier than the present date and time.
* Interviews must be scheduled within the office hours, defined as Monday to Friday, 8AM - 6PM (i.e. The last interview for the day allowed is at 5:30PM).
* Attempts to schedule an interview within the duration of another interview will result in an error.
(e.g. Interview A starts at 10AM on a given day. Scheduling an interview from 9:31AM up to 10:29AM is prohibited).

Examples:
* `list` followed by `schedule add candidate/2 at/05-05-2022 10:00` schedules the second candidate in the candidate list
  for an interview on 5 May 2022 10AM.

### Rescheduling an interview: `schedule edit`

Reschedules the specified interview to a new date and time.

Format: `schedule edit SCHEDULE_INDEX at/DATE_TIME`

* Reschedules the interview at the specified `SCHEDULE_INDEX` to the given new `DATE_TIME`.
* The schedule index refers to the index number shown in the displayed interview schedule.
* The schedule index must be a positive integer 1, 2, 3, …​
* `DATE_TIME` must be specified in the format `dd-MM-yyyy HH:mm`.
* `DATE_TIME` must not be earlier than the present date and time.

Examples:
* `view all` followed by `schedule edit 2 at/06-06-2022 15:00` reschedules the second interview in the interview schedule
  to 6 June 2022 3PM.

### Deleting an interview: `schedule delete`

Deletes the specified interview.

Format: `schedule delete SCHEDULE_INDEX`

* Deletes the interview at the specified `SCHEDULE_INDEX`.
* The schedule index refers to the index number shown in the displayed interview schedule.
* The schedule index must be a positive integer 1, 2, 3, …​

Examples:
* `view all` followed by `schedule delete 2` deletes the second interview in the interview schedule.

<div markdown="block" class="alert alert-info">

**:information_source: Note:** Deleting an interview will automatically trigger the associated candidate's 
`Interview Status` to `Not Scheduled`, regardless of the state of the deleted interview (upcoming, ongoing or expired).

</div>

### Viewing scheduled interviews `view`

Returns the list of scheduled interviews within the specified time period.

Format: `view TIME_PERIOD`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the view format:**<br>

`TIME_PERIOD` can take on the following values `all`, `today`, `week`, `month`.

</div>

* The attribute field is case-insensitive. e.g. `ALL` is equivalent to `all`.
* Scheduled interviews are automatically sorted from earliest to latest.

Examples:
* `view all` returns all scheduled interviews still in system whether in the past or upcoming.
* `view today` returns all scheduled interviews on the same date as the current time.
* `view week` returns all upcoming scheduled interviews within the next 7 days.
* `view month` returns all upcoming scheduled interviews within the next month period.

### Clearing interview schedule `schedule clear`

Clears the list of interviews in the schedule.

Format: `schedule clear`

<div markdown="block" class="alert alert-info">

**:information_source: Note:** The interview status of candidates who have an
upcoming interview in the schedule will be reset from `Scheduled` to `Not Scheduled`. However, if a candidate has
an interview which has just expired (and TAlent Assistant™ has not been refreshed) upon the execution of the command,
his or her interview status will be updated to `Completed`.

</div>

## Miscellaneous commands

### Clearing all entries : `clear`

Clears all entries (candidates and interviews) from the system.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Processing data

### Saving the data

TAlent Assistant™ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAlent Assistant™ candidate and interview data are saved as JSON files `[JAR file location]/data/talentassistant.json`
and `[JAR file location]/data/interviewlist.json` respectively. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**

If your changes to the data file makes its format invalid*, TAlent Assistant™ will discard all data and start with an empty data file at the next run. It
is highly advised that you do not modify the JSON files unless you are aware of what you are doing.

When making changes to a candidate's details in talentassistant.json, the corresponding candidate's details in interviewlist.json (if applicable)
must also be updated. Likewise, if an interview is deleted from interviewlist.json, the interview status of the candidate should be updated according
to your desired outcome (scheduled or not completed).

*Format is considered to be invalid when either one of the JSON files are missing, or if the JSON files are edited to contain duplicate candidates
and/or (conflicting) interviews.
</div>

--------------------------------------------------------------------------------------------------------------------

## New features to be added (Coming Soon!)

* The following features are being considered for future versions of TAlent Assistant™:
  * A new feature to pinpoint the exact interview in conflict (if any) when a user attempts to schedule an interview
  * A new feature to allow the user to schedule interviews of flexible duration is
    under consideration for future versions of TAlent Assistant™.
  * Extension of the `find` command to enable searching across multiple fields.
  * Extension of the `view` command to enable user to bring up a list of scheduled interview for a customised date range.
  * Extension of the `sort` command to enable user to customise the sorting order (ascending or descending) as well as sorting by availabilities.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAlent Assistant™ home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Commands in this section have been organised based on the expected scope of behaviour.

### Managing candidates

| Action     | Format, Examples                                                                                                                                                                                |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add id/STUDENT_ID n/NAME p/PHONE e/EMAIL c/COURSE yr/SENIORITY avail/AVAILABILITY`<br> e.g., `add id/A0123456B n/John Doe p/87654321 e/E0123456@u.nus.edu c/Computer Science yr/2 avail/1,2,3` |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                             |
| **Edit**   | `edit INDEX [PREFIX/VALUE] [MORE_PREFIX/VALUE]…​`<br> e.g.,`edit 2 n/James Lee p/98765432 yr/4`                                                                                                 |
| **Find**   | `find k/KEYWORD [k/MORE_KEYWORDS]…​ [f/ATTRIBUTE_FIELD]`<br> e.g., `find k/Jane k/Doe f/name`                                                                                                   |
| **Sort**   | `sort s/ATTRIBUTE_FIELD`<br> e.g., `sort s/name`                                                                                                                                                |
| **Remark** | `remark INDEX [r/REMARK]`<br> e.g., `remark 1 r/a good candidate`                                                                                                                               |
| **Focus**  | `focus INDEX`<br> e.g., `focus 1`                                                                                                                                                               |

### Scheduling interviews

| Action                        | Format, Examples                                                                                      |
|-------------------------------|-------------------------------------------------------------------------------------------------------|
| **Schedule interview**        | `schedule add candidate/INDEX /at DATE_TIME`<br> e.g., `schedule add candidate/2 at/05-05-2022 10:00` |
| **Reschedule interview**      | `schedule edit SCHEDULE_INDEX at/DATE_TIME`<br> e.g., `schedule edit 1 at/06-06-2022 15:00`           |
| **Delete interview**          | `schedule delete SCHEDULE_INDEX`<br> e.g., `schedule delete 1`                                        |
| **Clear all interviews**      | `schedule clear`                                                                                      |
| **View scheduled interviews** | `view TIME_PERIOD`<br> e.g., `view all`, `view today`                                                 |

### Miscellaneous commands

| Action    | Format, Examples |
|-----------|------------------|
| **Clear** | `clear`          |
| **Exit**  | `exit`           |
| **Help**  | `help`           |
