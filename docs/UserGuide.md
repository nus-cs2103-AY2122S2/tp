---
layout: page
title: User Guide
---

SoC InternApply (SIA) is a **desktop app for managing internship applications, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SIA can get your internship application management tasks done faster than traditional GUI apps.

With SoC InternApply, you can easily add an application, edit it later on if there are any changes needed, find the applications based on keywords and also sort them based on priority, interview date and much more! All while not having to worry about saving or storing your applications as this is done internally and automatically by SoC InternApply.

Ultimately, with SoC InternApply, you can worry less about the administrative tasks and focus more on preparing for the interviews themselves!

## Table of Contents
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## Glossary
1. SIA (SoC InternApply)

[Go To TOC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. You can download it from [this website](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).

2. Download the latest `internapply.jar` from [here](https://github.com/AY2122S2-CS2103T-T11-3/tp/releases).

3. Copy the file to the folder you want to use as the _root folder_ for your SIA (e.g. you can save it in your desktop or downloads folder). <br>
   **Note:** There will be data (your internship applications) stored into this same folder, but it would not take up much space.

4. Double-click the jar file to start the app. The GUI similar to the diagram below should appear in a few seconds. Note how the app contains some sample data. Do note that for Linux OS, you may have to enable double-click to run JAR files first!<br><br>

   ![Ui](images/MainWindowUi.png)

5. Another reminder window should appear automatically similar to the below. Note that this window **would contain any upcoming interviews you have in a weeks time**.<br>
   ![Ui](images/ReminderWindowUi.png)
6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
    * **`add`**`n/Shopee j/Software Engineer Intern p/87438807 e/hr@shopee.sg a/5 Science Park Dr, #06-40 t/SoftwareEngineering pt/LOW ast/NOT_APPLIED` : Adds an application with company named `Shopee` to SIA.

    * **`clear`** : Deletes all applications.

    * **`delete`**`3` : Deletes the 3rd application shown in the current list.

    * **`edit`** `1 d/Thank you for using SIA!` : Update the details to `Thank you for using SIA!` for the first application on the list.

    * **`edit`**`1 idt/17-03-2022 16:00` : Update the interview slot to `17 Mar 2022 16:00` for the first application on the list.

    * **`find`**`n/shopee` : Find all applications that contain the word "Shopee" in its name.

    * **`list`** : List all applications — used after `find` command to show all application.
    
    * **`list`**`name desc` : Sort all applications base on company name in descending order.

    * **`reminder`** : Lists all applications with upcoming interviews within a week from now.

    * **`help`** : Shows a message explaining how to access the help page.
    
    * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

<div markdown="block" class="alert alert-info">

**:information_source: WARNING about modifying local files:**<br>
We are not liable for any data loss, by the user when modifying data stored in the JSON save file.
Edit at your own risk of losing data.


</div>

[Go To TOC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Grab SG`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/Grab SG t/local` or as `n/Grab SG`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/local` or `t/local t/NUS` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `j/Software Engineer j/Data Scientist`, only `j/Data Scientist` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `reminder`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

**:information_source: Notes about duplicate applications:**<br>

* Duplicate applications are not allowed.

* Applications are considered duplicates if they have the same name, job title and optional tags.

* Name, job title and optional tags are case-sensitive.
  e.g. The name `Shopee` is considered different from `shopee`.

* Ordering of optional tags do not matter.
  e.g. `tag1`, `tag2`, `tag3` is considered the same as `tag3`, `tag1`, `tag2`.

* Refer to Q4 in the [FAQ](#faq) for adding multiple applications to the same job.

</div>

[Go To TOC](#table-of-contents)

---

### Adding an application: `add`

Adds an application to SoC InternApply.

**Format:** `add n/NAME_OF_COMPANY j/JOB_TITLE p/PHONE_NUMBER a/ADDRESS e/EMAIL [t/TAG]... [pt/PRIORITY_TAG] [ast/APPLICATION_STATUS_TAG]`

#### Parameters: <br>

  - NAME_OF_COMPANY:
    - Name of company you're applying for.
  - JOB_TITLE:
    - Title of the job you are applying for.

<div markdown="block" class="alert alert-info">

**Input Constraints** For `[j/JOBTITLE]` only alphanumeric inputs are allowed. i.e. Only the characters A-Z, a-z, 0-9. Spaces are also allowed. <br>
  E.G. `j/SoftwareEngineerIntern` is allowed, `t/Software Engineer Intern` is also allowed.

</div>

  - PHONE_NUMBER:
    - Phone number of the company your applying for.<br>

<div markdown="block" class="alert alert-info">

**Input Constraints:**  a minimum of 3 digits must be inputted but most phone numbers would be at least 8 digits long.

</div>

  - ADDRESS:
    - Address of the company your applying for.
  

  - EMAIL:
    - Email of the company your applying for.

#### Optional Parameters:

  - TAG:
    - Optional Tag that you can set to separate applications
    - E.G. t/Application1, t/Application2 <br>

<div markdown="block" class="alert alert-info">

**Input Constraints:** 
1. Only alphanumeric inputs are allowed and cannot be empty. i.e. Only the characters A-Z, a-z, 0-9. <br>
E.G. `t/Based In Singapore` or `t/` is not allowed, `t/BasedInSingapore` is allowed. <br>
2. User input cannot be any of the inputs for `PRIORITY_TAG` and `APPLICATION_STATUS_TAG` to avoid confusion

</div>

  - PRIORITY_TAG:
    - Tag indicating the urgency of the application:
    - `HIGH`,`MEDIUM`,`LOW`
    

  - APPLICATION_STATUS_TAG:
    - Tag indicating the status of the application:
    - `NOT_APPLIED`,`APPLIED`,`INTERVIEWED`,`REJECTED`,`ACCEPTED`<br>

<div markdown="block" class="alert alert-info">
    
**Input Constraints:** `PRIORITY_TAG` and `APPLICATION_STATUS_TAG` are not case-sensitive E.G. `pt/high` and `pt/HIGH` are identical in syntax.

</div>

<div markdown="block" class="alert alert-info">
**:information_source: Notes about duplicate applications:**<br>

An application is considered duplicate if, it's `NAME_OF_COMPANY`, `JOB_TITLE` and `TAG` is identical
</div>

**Example usages and expected outcomes:**
* `add n/Shopee j/Software Engineer Intern p/87438807 e/hr@shopee.sg a/5 Science Park Dr, #06-40 t/SoftwareEngineering `

Feedback message:

```
New application added: Shopee; Job Title: Software Engineer Intern; 
Phone: 87438807; Email: hr@shopee.sg; Address: 5 Science Park Dr, #06-40; 
Interview Slot: Interview date is not set.; Details: To add details, use the edit command; 
Tags: SoftwareEngineering
```

* `add n/Shopee j/Software Engineer Intern p/87438807 e/hr@shopee.sg a/5 Science Park Dr, #06-40 t/SoftwareEngineering pt/HIGH ast/NOT_APPLIED`

Feedback message:
```
This application already exists in InternApply
```

[Go To TOC](#table-of-contents)

---

### Clearing all applications: `clear`

Clears all applications from SoC InternApply.

**Format:** `clear`

[Go To TOC](#table-of-contents)

---

### Deleting an application: `delete`

Deletes the specified application from SoC InternApply.

**Format:** `delete INDEX`

* Deletes the application at the specified `INDEX`.
* The index refers to the index number shown in the displayed application list.
* The index **must be a positive integer** 1, 2, 3, …​
* The index inputted must not exceed 2147483647 and must be a natural number.

**Example usages:**
* `list` followed by `delete 2` deletes the 2nd application.

**Expected outcome:**

The previous 2nd application is removed from the storage and a new list of applications is shown.

[Go To TOC](#table-of-contents)

---

### Editing an application: `edit`

Edits an existing application in SoC InternApply.

**Format:** `edit INDEX [n/NAME] [j/JOB_TITLE] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [idt/INTERVIEW_DATE_TIME] [d/DETAILS] [t/TAG]... [pt/PRIORITY_TAG] [ast/APPLICATION_STATUS_TAG]`

**CAUTION:** The `edit` command will overwrite your existing application data.
<br>
- Edits the application at the specified `INDEX`. The index refers to the index number shown in the displayed application list. The index **must be a positive integer** 1, 2, 3, ...
- At least one of the optional fields must be provided.
- The index inputted must not exceed 2147483647 and must be a natural number.
- Existing values will be updated to the input values.
- You cannot edit an application to become a duplicate of another application. Any attempts will be prevented. Please refer to our Notes about duplicate applications

#### Parameters:

- NAME, JOB_TITLE, PHONE_NUMBER, EMAIL, ADDRESS
  - For the above fields, all restraints from the add command is applicable.
  - E.G. `edit 1 e/SoCStudent@example.com n/NUS Research` Edits the email and name of the 1st application to be `SoCStudent@example.com` and `NUS Research` respectively.
<div markdown="block" class="alert alert-info">
  
**Input Constraints:**
* For `[j/JOBTITLE]` only alphanumeric inputs are allowed. i.e. Only the characters A-Z, a-z, 0-9. Spaces are also allowed. <br>
  E.G. `j/SoftwareEngineerIntern` is allowed, `t/Software Engineer Intern` is also allowed.
* For `[p/PHONE_NUMBER]`a minimum of 3 digits must be inputted but most phone numbers would be at least 8 digits long.

</div>
  
- INTERVIEW_DATE_TIME
  - You can add an interview slot that includes both date and time by using `idt/INTERVIEW_DATE_TIME`
  - The interview date time, `INTERVIEW_DATE_TIME`, must be in the follow format `dd-MM-yyyy HH:mm`.
  - You can remove `INTERVIEW_DATE_TIME` by using `idt/` without specifying anything after it. <br>
  
- DETAILS
  - You can add details to the application by using `d/DETAILS`
  - You can enter new lines in the details input by using `\n`
  - You  can remove `DETAILS` by typing `d/` without any strings following it, which will revert the field back to the default of `To add details, use the edit command`
  - To edit the details of an application, you can follow this format (adding \n to type in a new line): `edit 1 d/Example details \nThis is a newline of the details`<br>e.g.`edit 1 d/This company requires a preliminary coding round.\n I should practice more on HackerRank` will result in this details being added:
```
This company requires a preliminary coding round. 
I should practice more on HackerRank
```

- TAGS, PRIORITY_TAG, APPLICATION_STATUS_TAG
  - You can change the priority or application status of an application by using `pt/High` or `ast/Applied`
  - The tags are not case-sensitive
  - Entering `pt/high` when the application priority tag is `high` is permitted but does not change anything

**Example usages and expected outcomes:**
- `edit 2 j/Intern idt/` Edits the job title of the 2nd application to be `Intern` and clears the existing interview date time.
- `edit 1 t/Singapore ast/APPLIED` Edits the tags and application status tag of the 1st application to Singapore and APPLIED respectively. Since the priority tag is not specified, the 1st application will keep its current priority tag if it had any.


#### Advanced Function

Removing Tags from existing application.

**CAUTION**: While it is possible to use these special Tags while editing other fields of an existing application in SoC InternApply, we highly recommend not doing so unless you know what you are doing. However, if you wish to do so please refer to the description below beforehand.

**Format**: `edit INDEX [t/removetags] [t/removepriority] [t/removestatus]`

- Removes the Tags from an existing application.
- `[t/removetags]` will remove all Optional Tags from an application (i.e. Tags with the prefix `t/`).
- `[t/removepriority]` will remove the Priority Tags from an application (i.e. Tags with the prefix `pt/`).
- `[t/removestatus]` will remove the Application Status Tags from an application (i.e. Tags with the prefix `ast/`).
- These special Tags are not case-sensitive (i.e. typing `t/ReMOveTagS` would also work). 
- These special Tags cannot be used as a valid Optional Tag for your applications.
- These special Tags can be used in any order, and you can either use 1 of them, a pair of them, or all 3 of them.
- These special Tags takes precedence over all other Tag inputs. Refer to the Example usage below for reference.
- You can still use these special Tags even if the respective Tags do not exist for the specified application. Nothing will happen in this case.
- If removing any of the Tags from an application causes it to become a duplicate application the command will be invalid. Please refer to our Notes about duplicate applications

**Example usages and expected outcomes**

- `edit 1 t/removetags t/removepriority` Removes all the Optional Tags and the Priority Tag from the 1st application.
- `edit 1 p/65258719 t/removestatus` Removes the Application Status Tag from the 1st application. Edits the phone number of the 1st application to be `65258719`
- `edit 1 pt/HIGH t/removepriority` Special Tags takes precedence over all other Tag inputs. Removes the Priority Tag from the 1st application. Ignores the `pt/HIGH` input.
- `edit 1 t/removestatus t/Singapore t/ExEmployee` Special Tags takes precedence over all other Tag inputs. Removes the Application Status Tag from the 1st application. Ignores the `t/Singapore t/ExEmployee` inputs.
- `edit 1 t/removestatus` followed by `edit 1 t/removestatus` First removes the Application Status Tag from the 1st application. Then does nothing since there does not exist an Application Status Tag for the 1st application. 

[Go To TOC](#table-of-contents)

---

### Exiting the program: `exit`

Exits the program.

**Format:** `exit`

[Go To TOC](#table-of-contents)

---

### Finding application(s): `find`

Finds existing applications in SoC InternApply.

**Format:** `find [n/NAME] or find [j/JOB_TITLE] or find [t/TAG]... or find [pt/PRIORITY_TAG] or find [ast/APPLICATION_STATUS_TAG]`
- Finds the applications with the fields containing keywords given in the input (partial keywords accepted as well).
- Keywords are case-insensitive.
- Parameters are only expected once (except tags). e.g. `find n/shopee n/grab` is equivalent to `find n/grab`, the last occurrence of the parameter will be taken.
- However, for `n/` and `j`, more than 1 word can be accepted per field i.e. `find n/aftershock pc` will display any application with the name `aftershock` or `pc`. 
- If more than 1 different fields are given, i.e. `find n/shopee j/ML`, only the first field (in the sequence of [n/NAME], [j/JOB_TITLE], [t/TAG]..., [pt/PRIORITY_TAG], [ast/APPLICATION_STATUS_TAG]) will be processed, i.e. `find n/shopee j/ML` is the same as `find n/shopee`, `find j/ML n/shopee` is also the same as `find n/shopee`

**Example usages and expected outcomes:**
- `find n/shopee` finds and displays all applications with "Shopee" in its name.
- `find j/ML` finds and displays all applications with "ML" in its job title.
- `find pt/HIGH` finds and displays all applications with "HIGH" in its priority tag.
- `find ast/applied` finds and displays all applications with "applied" in its application status. (note that it is case-insensitive)
- `find t/overseas` finds and displays all applications with "overseas" in its tags.
- `find t/overseas t/USA` finds and displays all applications with "overseas" or "USA" in its tags.

[Go To TOC](#table-of-contents)


### Viewing help: `help`

This command displays a message explaining how to access the help page.

**Format:** `help`

**Example usages:**

`help`

**Expected outcome:**

A popup window showing a link to the help page, as shown below.
![helpMessage](images/helpMessage.png)

[Go To TOC](#table-of-contents)

---

### Listing all applications (with / without sorting): `list`

#### List all applications (with sorting)

Sorts the list of all applications in SoC InternApply based on the provided parameters.

**Format:** `list [sorting paramter] [order by]`

Sorting parameters:
- `name` : Sort by name of the company in alphabetical order starting with A in ascending order.
- `interview` : Sort by interview date of applications starting with the latest earliest date.
- `priority` : Sort by priority in the following ascending order - (NO_TAG), LOW, MEDIUM, HIGH.
- `status` : Sort by status in the following ascending order - ACCEPTED, REJECTED, INTERVIEWED, APPLIED and NOT_APPLIED, (NO_TAG).

* If there is more than one application have similar fields, the company `name` of the application will be used as tiebreak to sort alphabetically (i.e. Application 1 with company name `AAA` and `HIGH` priority and Application 2 with company name `BBB` and `HIGH` priority are sorted by priority in ascending order — Application 1 will be ranked higher than Application 2).

Order by:
- `asc` : Order by ascending.
- `desc` : Order by descending (reverse order of ascending).

Examples:
* `list interview desc`
* `list name desc`, running this will output:
  <br>
```
Sorted applications by name order by desc.
```

<div markdown="block" class="alert alert-info">

**💡 `list` command with parameters only considers the first two parameters if and only if the first two parameters are valid.**<br>

- `list status desc` — Valid, sorted by `status` in descending order
- `list abc status asc` — Invalid
- `list status asc name desc` — Valid, sorted by `status` in ascending order

</div>

<div markdown="block" class="alert alert-info">

**💡 Upper case and lower case name are consider as same ranking.**

i.e. Application 1 with company name `aaa` will be ranked above Application 2 with company name `BBB` when sorted by `name` in ascending order.<br>

</div>

<div markdown="block" class="alert alert-info">

**⚠️ Applications are originally sorted in a chronological order base on create date and time.**

Do take note that after using the `list` feature to sort, you will not be able to sort the applications in chronological order.<br>

</div>

#### List all applications (without sorting)

The `list` command also works without parameters. It will show the list in the last sorted order used by the user. This is used after using the `find` feature to list out all applications.

Example:
* `list`

```
Listed all application(s).
```

<div markdown="block" class="alert alert-info">

**💡 `list` command without parameters only works if and only if you do not provide parameters.**<br>

</div>

<div markdown="block" class="alert alert-info">

**❗ Sorting will not apply to newly added or newly edited applications.**

Please re-run the `list` command with the respective parameters to sort as you like.<br>

</div>

[Go To TOC](#table-of-contents)

---

### Listing applications with upcoming interviews: `reminder`

Shows a list of applications with upcoming interviews, falling within a week from now, in SoC InternApply.

**Format:** `reminder`

**Example usages:**

`reminder`

**Expected outcome:**

A popup window showing a list of applications with upcoming interviews within a week from now, as shown below.
![Ui](images/ReminderWindowUi.png)

[Go To TOC](#table-of-contents)

---

### Saving the data

SoC InternApply data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Go To TOC](#table-of-contents)

---

### Summary bar

The summary bar is automatically updated on successful execution of any command. 

Example summary bar (highlighted in red):
![SummaryBarExample](images/SummaryBarUI.png)

[Go To TOC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ
**Q1**: How do I know where my internship applications data is stored?<br>
**A**: Please locate where SoC InternApply is being stored on your computer. You will see a folder named `data` found in the root folder of the application (the folder containing SoC InternApply) Inside the `data` folder, you can find a data file named `internapplymemory.json`, which stores all the applications.

**Q2**: How do I transfer my data to another computer?<br>
**A**: Refer to the 1st QnA to locate the data file. Install the app in the other computer. Copy the contents of the data file of SoC InternApply from your previous computer and paste it in the empty data file of SoC InternApply of the new computer. <br>

**Q3**: When I run SIA for the first time, I do not see any of the sample application. How can I get the sample applications to appear in SIA? <br>
**A**: Refer to the 1st QnA to locate the `data` folder. Delete any existing files in the `data` folder and run SoC InternApply again.

**Q4**: I have an existing application to a company that I applied to where I got rejected and I am trying to reapply to the same company with the exact same details. Since SIA cannot have duplicate applications, what should I do? <br>
**A**: <br>
Option 1: Using edit, you can simply reuse the existing application with updated information. <br>
Option 2: If you are looking to keep the existing application and it's information untouched, then we suggest including an additional "t/NthAttempt" to differentiate your new application and the existing application. This will not violate our duplication clause while also allowing you to keep information on your existing application.

**Q5**: How many applications can SIA track?<br>
**A**: 2147483647 applications can be tracked.

**Q6**: When I use `find` followed by `edit` sometimes the application that I edited disappears form the main window. Am I doing something wrong?<br>
**A**: This can happen when you `find` applications with a specific field like `NAME` and then `edit` the `NAME` of an application that you found to something that no longer meets the criteria of the `find` command.<br>
For example, if you call `find n/Grab` followed by `edit 1 n/Shopee` the 1st application will disappear since it is no longer an application with a `NAME` containing `Grab`

**Q7**: Why is the `idt/` not recognised by `add` command?<br>
**A**: `idt/` is intentionally excluded from the `add` command. More often than not, students who apply for an internship do not receive an interview slot from the get go. Taking this into consideration, we decided that users need not include a valid `InterviewSlot` input just as a placeholder value to `add` new applications.

**Q8**: I just used the `list` command to sort my applications. Is it possible to revert sorting order?<br>
**A**: Unfortunately due to the current limitations of our application, you will not be able to revert the sorting order after using `list`. This includes the order in which applications were added into SIA so please do your sorting with caution.

[Go To TOC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
[**Add**](#adding-an-application-add) | `add n/NAME_OF_COMPANY p/PHONE_NUMBER a/ADDRESS j/JOB_TITLE e/EMAIL [t/TAG]... [pt/PRIORITY_TAG] [ast/APPLICATION_STATUS_TAG]` <br><br>  e.g., `add n/Singtel j/UIUX Intern p/62527525 e/singtel@sg.com a/Singtel Group Strategic Communications and Brand, 31 Exeter Road, Comcentre #19-00 ast/APPLIED`
[**Clear**](#clearing-all-applications-clear) | `clear`
[**Delete**](#deleting-an-application-delete) | `delete INDEX`<br> <br> e.g., `delete 3`
[**Edit**](#editing-an-application-edit) | `edit INDEX [n/NAME] [j/JOB_TITLE] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [idt/INTERVIEW_DATE_TIME] [t/TAG]... [pt/PRIORITY_TAG] [ast/APPLICATION_STATUS_TAG]` <br> <br> e.g., `edit 1 n/Grab SG p/65358292 idt/17-03-2022 13:30`<br><br>`edit INDEX [t/removetags] [t/removepriority] [t/removestatus]`<br><br>e.g., `edit 1 t/removetags t/removestatus`
[**Exit**](#exiting-the-program-exit) | `exit`
[**Find**](#finding-applications-find) | `find [n/NAME] or find [j/JOB_TITLE] or find [t/TAG]... or find [pt/PRIORITY_TAG] or find [ast/APPLICATION_STATUS_TAG]`<br> <br> e.g., `find n/shopee`
[**Help**](#viewing-help-help) | `help`
[**List**](#listing-all-applications-with--without-sorting-list) | `list [sorting paramter] [order by]`
[**Reminder**](#listing-applications-with-upcoming-interviews-reminder)| `reminder`

[Go To TOC](#table-of-contents)
