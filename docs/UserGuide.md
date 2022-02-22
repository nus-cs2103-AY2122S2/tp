---
layout: page
title: User Guide
---

Tinner (Anagram of Intern) is a desktop app for managing internship applications, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Tinner allows you to easily sort through and retrieve the relevant information faster than traditional GUI apps. benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.
## Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
  * [Viewing help: help](#c-help)
  * [Viewing all companies and roles: list](#c-list) 
  * [Adding a company: add company](#c-add-c) 
  * [Adding a role: add role](#c-add-c-r) 
  * [Deleting a company: delete company](#c-delete-c) 
  * [Deleting a role: delete role](#c-delete-c-r)
* [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start <a id="quick-start"></a>

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest tinner.jar.
3. Copy the file to the folder you want to use as the home folder for your Tinner.
4. Double-click the file to start the app. The GUI like below should appear in a few seconds. Note how the app contains some sample data.

   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.  
   Some example commands you can try:
   * **list** : Lists all companies and internship roles.
   * **add company** `n/Google p/98765432 e/hr_google@gmail.com a/70 Pasir Panjang Rd, #03-71 Mapletree Business City II, Singapore 117371` : Adds a company named `Google` to Tinner.
   * **add role**`1 n/Software Engineering Intern s/Applying b/31 March 2022 23:59 d/Write mobile applications $/5000` : Adds a software engineering intern role to the company at index 1.
   * **delete role** `1 1`: Deletes the 1st role of the 1st company shown in the current list.
   * **delete company**`1` : Deletes the 1st company shown in the current list.
   * **exit** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features <a id="features"></a>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/COMPANY`, `COMPANY` is a parameter which can be used as `add n/Google`.

* Items in square brackets are optional.<br>
  e.g `n/COMPANY [p/PHONE_NUMBER]` can be used as `n/Google P/65427981` or as `n/Google`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/COMPANY [p/PHONE_NUMBER]`, `[p/PHONE_NUMBER] n/COMPANY` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Listing all persons : `list` <a id="c-list"></a>

Shows a list of all persons in the address book.

Format: `list`


### Adding a company: `add company` <a id="c-add-c"></a>

Adding a company that does not exist in the list.

Format: `add company n/COMPANY_NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

Examples:
* `add company n/Google p/98765432 e/hr_google@gmail.com a/70 Pasir Panjang Rd, #03-71 Mapletree Business City II, Singapore 117371 `
* `add company n/Meta p/91234567 e/hr_meta@meta.com a/9 Straits View, Marina One, Singapore 018937 `

### Adding an internship role to an existing company: `add role` <a id="c-add-c-r"></a>

Adding an internship role to a company that already exists in the list.

Format: `add role COMPANY_INDEX n/ROLE s/STATUS b/DEADLINE d/DESCRIPTION $/STIPEND`


Examples:
* `add role 1 n/Data Analyst s/Applying b/31 March 2022 23:59 d/Analyse data $/4800 `
* `add role 3 n/Software Engineer s/Applying b/30 April 2022 01:23 d/Frontend web development $/2400 `

### Deleting a Company : `delete company` <a id="c-delete-c"></a>

Deletes the specified company from the list.

Format: `delete company COMPANY_INDEX`

* Deletes the company at the specified COMPANY_INDEX.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `list` followed by `delete company 2` deletes the 2nd company on the list in Tinner.

### Deleting an internship role from company : `delete role` <a id="c-delete-c-r"></a>

Deletes the specified role in the specified company.

Format: `delete role COMPANY_INDEX ROLE_INDEX`

* Deletes the role at the specified ROLE_INDEX of the company at the specified COMPANY_INDEX. The indexes refer to the index numbers shown in the displayed company list.
* The indexes must be a positive integer 1, 2, 3, …

Examples:
* `list` followed by, `delete role 1 1` deletes the 1st role from the 1st company in Tinner.

### Exiting the program : `exit` <a id="c-exit"></a>

Exits the program.

Format: `exit`

### Saving the data 

Tinner data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------
## Command summary <a id="command-summary"></a>

Action | Format, Examples
--------|------------------
**List companies** | `list`
**Add company** | `add company n/COMPANY_NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` <br><br> e.g.,`add company n/Google p/98765432 e/hr_google@gmail.com a/70 Pasir Panjang Rd, #03-71 Mapletree Business City II, Singapore 117371` 
**Add role** | `add role COMPANY_INDEX n/ROLE s/STATUS b/DEADLINE d/DESCRIPTION $/STIPEND` <br><br> e.g.,` add role 1 n/Data Analyst s/Applying b/31 March 2022 23:59 d/Analyse marketing data $/5000`
**Delete company** | `delete company COMPANY_INDEX `<br><br> e.g.,`delete company 3 `
**Delete role** | `delete role COMPANY_INDEX ROLE_INDEX` <br><br> e.g.,`delete role 3 1 `
**Help** | `help`
**Exit Tinner** | `exit`
