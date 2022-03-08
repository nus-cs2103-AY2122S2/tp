---
layout: page title: User Guide
---

Tinner (Anagram of Intern) is a desktop app for managing internship applications, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Tinner allows you to easily sort through and retrieve relevant information faster than traditional GUI apps.

## Table of Contents

* [Quick start](#quick-start)
* [Features](#features)
    * [Viewing help: `help`](#c-help)
    * [Viewing all companies and roles: `list`](#c-list)
    * [Adding a company: `addCompany`](#c-add-c)
    * [Adding a role: `addRole`](#c-add-c-r)
    * [Deleting a company: `deleteCompany`](#c-delete-c)
    * [Deleting a role: `deleteRole`](#c-delete-c-r)
* [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start <a id="quick-start"></a>

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest tinner.jar.
3. Copy the file to the folder you want to use as the home folder for your Tinner.
4. Double-click the file to start the app. The GUI like below should appear in a few seconds. Note how the app contains some sample data.

![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.  
   Some example commands you can try:
    * **`list`** : Lists all companies and internship roles.
    * **`addCompany`**`n/Google p/98765432 e/hr_google@gmail.com a/70 Pasir Panjang Rd, #03-71 Mapletree Business City II, Singapore 117371` :
      Adds a company named `Google` to Tinner.
    * **`addRole`**`1 n/Software Engineering Intern s/applying b/31-03-2022 23:59 d/Write mobile applications $/5000` :
      Adds a software engineering intern role to the company at index 1.
    * **`deleteCompany`**`1` : Deletes the 1st company shown in the current list.
    * **`deleteRole`**`1 1` : Deletes the 1st role of the 1st company shown in the current list.
    * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`)
  will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the User Guide.

![help message](images/helpMessage.png)

Format: `help`

### Listing all companies : `list` <a id="c-list"></a>

Shows a list of all companies and internship roles in Tinner.

Format: `list`

### Adding a company: `addCompany` <a id="c-add-c"></a>

Adds a company that does not exist in the list.

Format: `addCompany n/COMPANY_NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

Examples:

* `addCompany n/Google p/98765432 e/hr_google@gmail.com a/70 Pasir Panjang Rd, #03-71 Mapletree Business City II, Singapore 117371 `
* `addCompany n/Meta p/91234567 e/hr_meta@meta.com a/9 Straits View, Marina One, Singapore 018937`

### Adding an internship role to an existing company: `addRole` <a id="c-add-c-r"></a>

Adds an internship role to a company that already exists in the list.

Format: `addRole COMPANY_INDEX n/ROLE s/STATUS b/DEADLINE d/DESCRIPTION $/STIPEND`

Examples:

* `addRole 1 n/Data Analyst s/applying b/31-03-2022 23:59 d/Analyse data $/4800 `
* `addRole 3 n/Software Engineer s/applying b/30-04-2022 01:20 d/Frontend web development $/2400 `

### Deleting a Company : `deleteCompany` <a id="c-delete-c"></a>

Deletes the specified company from the list.

Format: `deleteCompany COMPANY_INDEX`

* Deletes the company at the specified `COMPANY_INDEX`.
* The index refers to the index number shown in the displayed company list.
* The index must be a positive integer like 1, 2, 3, …

Examples:

* `list` followed by `deleteCompany 2` deletes the 2<sup>nd</sup> company in the displayed company list.

### Deleting an internship role from company : `deleteRole` <a id="c-delete-c-r"></a>

Deletes the specified role in the specified company.

Format: `deleteRole COMPANY_INDEX ROLE_INDEX`

* Deletes the role at the specified `ROLE_INDEX` of the company at the specified `COMPANY_INDEX`. The indexes refer to the index numbers shown in the displayed company list.
* The indexes must be a positive integer like 1, 2, 3, …

Examples:

* `list` followed by, `deleteRole 1 1` deletes the 1<sup>st</sup> role from the 1<sup>st</sup>
  company in Tinner.

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
**Add company** | `addCompany n/COMPANY_NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` <br><br> e.g.,`addCompany n/Google p/98765432 e/hr_google@gmail.com a/70 Pasir Panjang Rd, #03-71 Mapletree Business City II, Singapore 117371`
**Add role** | `addRole COMPANY_INDEX n/ROLE s/STATUS b/DEADLINE d/DESCRIPTION $/STIPEND` <br><br> e.g.,` addRole 1 n/Data Analyst s/Applying b/31 March 2022 23:59 d/Analyse marketing data $/5000`
**Delete company** | `deleteCompany COMPANY_INDEX `<br><br> e.g.,`deleteCompany 3 `
**Delete role** | `deleteRole COMPANY_INDEX ROLE_INDEX` <br><br> e.g.,`deleteRole 3 1 `
**Help** | `help`
**Exit Tinner** | `exit`
