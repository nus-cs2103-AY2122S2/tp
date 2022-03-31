---
layout: page
title: User Guide
---

<img src = "images/user-guide/icon.png" width = "250" alt="Unable to load image! Try again
later.">

## **Table of Contents**
- [Quick start](#quick-start)
  - [Requirement](#requirement)
  - [Setup](#setup)
- [Features](#features)
  - Common Commands:
    - [Viewing Help: `help`](#viewing-help-help)
    - [Adding a Patient: `add`](#adding-a-patient-add)
    - [Listing all Patients: `view`](#listing-all-patients-view)
    - [View Summary of a Patient: `view i/NRIC`](#view-summary-of-a-patient-view-inric)
    - [Editing a Patient: `edit`](#editing-a-patient)
    - [Deleting any Entry: `delete`](#deleting-any-entry-delete)
    - [Find: `find`](#finding-by-keyword)
  - Contact Information:
    - [Adding Contact Information: `add t/contact`](#adding-contact-information-add-tcontact)
    - [Viewing Contact Information: `view t/contact`](#viewing-contact-information-view-tcontact)
    - [Editing Contact Information: `edit`](#editing-contact-information-edit)
  - Medical Information
    - [Adding Medical Information: `add t/medical`](#adding-medical-information-add-tmedical)
    - [Viewing Medical Information: `view t/medical`](#viewing-medical-information-view-tmedical)
    - [Editing Medical Information: `edit`](#editing-medical-information-edit)
  - Consultation Information
    - [Adding Consultation Information: `add t/consultation`](#adding-consultation-information-add-tconsultation)
    - [Viewing Past Consultations: `view t/consultation`](#viewing-past-consultations-view-tconsultation)
    - [Editing Consultation Information: `edit`](#editing-consultation-information-edit)
  - Prescription 
    - [Adding Prescription: `add t/prescription`](#adding-prescription-add-tprescription)
    - [Viewing Prescription: `view t/prescription`](#viewing-prescription-view-tprescription)
    - [Editing Prescription: `edit`](#editing-prescription-edit)
  - Test Result
    - [Adding Test Result: `add t/test`](#adding-test-result-add-ttest)
    - [Viewing Test Result: `view t/test`](#viewing-test-result-view-ttest)
    - [Editing Test Result: `edit`](#editing-test-result-edit)
- [FAQ](#faq)
- [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

### Requirement
- Ensure you have Java 11 or above installed on your computer.
- Download the latest `medbook.jar` from [here](https://github.com/AY2122S2-CS2103T-T11-1/tp/releases).

### Setup
1. Copy the file to the folder you want to use as the home folder for your MedBook.
2. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
3. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [tg/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`  after them can be used multiple times including zero times.<br>
  e.g. `[tg/TAG]… ` can be used as ` ` (i.e. 0 times), `tg/diabetic`, `tg/diabetic tg/hypertension` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing Help: `help`

Shows a message explaining how to access the help page.

<img src="images/helpMessage.png" alt="Unable to load image! Try again later.">

Format: `help`

### Adding a Patient: `add`

Adds a patient to MedBook.

Format: `add i/NRIC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tg/TAG]...`

Examples:
* `add i/S1234567L n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add i/S1234568L n/Jane Doe p/98763488 e/janed@example.com a/311, Clementi Ave 2, #02-25 tg/diabetic tg/hypertension`

### Listing all Patients: `view`

Shows a list of patients in MedBook.

Format: `view`

### View Summary of a Patient: `view i/NRIC`

Shows a summary of a patient's information in MedBook, including personal details, contact, medical information,...

Format: `view i/NRIC`

Examples:
* `view i/S1234567L`

### Editing a Patient: `edit`

Edits an existing patient in MedBook when a list of patients is being displayed.

Format:  `edit INDEX [n/NAME] [r/RELATIONSHIP] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

Examples:
* `view` followed by `edit 1 p/91234567` updates phone number of first patient displayed on the screen.

### Deleting any Entry: `delete`

Deletes a specified entry (patient, contact, medical information,...) on current screen from MedBook.

Format: `delete INDEX`

* We can delete the entry only if the entry is being shown on the display panel.
* For all screens apart from Summary, the index refers to the index number shown in the displayed list.
* The index must be a positive integer 1, 2, 3, …​
* For Summary screen, `delete 1` deletes the patient being viewed; other indices are invalid.

Examples:
* `view t/prescription i/S1234567L` followed by `delete 2` deletes the second prescription of the patient displayed on the screen.
* `view` followed by `delete 1` deletes the first patient displayed on the screen.

### Find: `find`

Finds all entries in the current page whose information contain any of the specified keywords (case-insensitive).

Format: `find KEYWORD [MORE_KEYWORDS]`

Examples:
* `find John`
* `find Betsy Tim`

### Adding Contact Information: `add t/contact`

Adds a patient's emergency contact to MedBook.

Format: `add t/contact i/NRIC n/NAME r/RELATIONSHIP p/PHONE_NUMBER e/EMAIL a/ADDRESS`

Examples:
* `add t/contact i/S1234567L n/Rihanna r/Mother p/80008000 e/rihanna@gmail.com a/COM1`


### Viewing Contact Information: `view t/contact`

Views a patient’s emergency contacts from MedBook.

Format: `view t/contact i/NRIC`

Examples:
* `/view t/contact i/S1234567L`

### Editing Contact Information: `edit`

Edits an existing contact information entry in MedBook when a list of contact information entries is being displayed.
Note that we cannot edit NRIC after creating a contact information entry.

Format:  `edit INDEX [n/NAME] [r/RELATIONSHIP] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

Examples:
* `/view t/contact i/S1234567L` followed by `edit 1 r/Father` updates relationship of contact entry displayed on the screen.

### Adding Medical Information: `add t/medical`
Adds a patient's medical information to MedBook.

Format: `add t/medical i/NRIC [a/AGE] [bt/BLOOD_TYPE] [md/MEDICATION] [ht/HEIGHT] [wt/WEIGHT]
  [il/ILLNESSES] [su/SURGERIES] [fh/FAMILY_HISTORY] [ih/IMMUNIZATION_HISTORY] [gd/GENDER] [et/ETHNICITY]`

Examples:
* `add t/medical i/S1234567L bt/O ht/185 cm`

### Viewing Medical Information: `view t/medical`

Displays medical information of a patient from MedBook. If no NRIC is specified, displays all medical information entries.

Format:  `view t/medical [i/NRIC]`

Examples:
* `view t/medical`
* `view t/medical i/S1234567L`

### Editing Medical Information: `edit`

Edits an existing medical information entry in MedBook when a list of medical information entries is being displayed.
Note that we cannot edit NRIC after creating a medical information entry.

Format:  `edit INDEX [a/AGE] [bt/BLOOD_TYPE] [md/MEDICATION] [ht/HEIGHT] [wt/WEIGHT]
[il/ILLNESSES] [su/SURGERIES] [fh/FAMILY_HISTORY] [ih/IMMUNIZATION_HISTORY] [gd/GENDER] [et/ETHNICITY]`

Examples:
* `view t/medical` followed by `edit 1 bt/B` updates blood type of first medical information entry displayed on the screen.

### Adding Consultation Information: `add t/consultation`

Adds a consultation report of a patient to MedBook.

Format: `add t/consultation i/NRIC dt/DATE tm/TIME dg/DIAGNOSIS fe/FEE nt/NOTES`

Examples:
* `add t/consultation i/S1234567L dt/2021-09-15 tm/18-00 dg/Inflammation in the throat and windpipe, short and shallow breath, laboured breathing. Most likely has Upper Respiratory Infection. fe/54.00 nt/Patient is having fever.`

### Viewing Past Consultations: `view t/consultation`

Views all past consultations of a patient in MedBook.

Format: `view t/consultation i/NRIC`

Examples:
* `view t/consultation i/S1234567L`

### Editing Consultation Information: `edit`

Edits an existing consultation entry in MedBook when a list of consultation entries is being displayed.
Note that we cannot edit NRIC after creating a consultation.

Format:  `edit INDEX [dt/DATE] [tm/TIME] [dg/DIAGNOSIS] [fe/FEE] [nt/NOTES]`

Examples:
* `view t/consultation` followed by `edit 1 dt/19-02-2019 tm/19-00` updates date and time of first consultation entry displayed on the screen.



### Adding Prescription: `add t/prescription`

Adds a medical prescription of a patient to MedBook.

Format: `add t/prescription i/NRIC n/DRUG_NAME dt/DATE s/INSTRUCTION`

Examples:
* `add t/prescription i/S1234567L n/Amoxicillin dt/2021-09-15 s/2 tablets after meal everyday.`

### Viewing Prescription: `view t/prescription`

Views a medical prescription of a patient in MedBook.

Format: `view t/prescription i/NRIC`

Examples:
* `view t/prescription i/S1234567L`

### Editing Prescription: `edit`

Edits an existing prescription entry in MedBook when a list of prescriptions is being displayed.
Note that we cannot edit NRIC after creating a prescription.

Format:  `edit INDEX [n/DRUG_NAME] [dt/DATE] [s/INSTRUCTION]`

Examples:
* `view t/prescription i/S1234567L` followed by `edit 1 n/Amoxicillin` updates the drug name of first prescription entry displayed on the screen.

### Adding Test Result: `add t/test`

Adds a test result taken by a patient in MedBook.

Format: `add t/test i/NRIC dt/DATE mt/MEDICAL_TEST r/RESULT`

Examples:
* `add t/test i/S1234567L dt/2019-09-15 mt/CT Scan r/Brain Cancer`

### Viewing Test Result: `view t/test`

Views all the test results taken by a patient in MedBook.

Format: `view t/test i/NRIC`

Examples:
* `view t/test i/S1234567L`

### Editing Test Result: `edit`

Edits an existing test result entry in MedBook when a list of test results is being displayed.
Note that we cannot edit NRIC after creating a test result.

Format:  `edit INDEX [dt/DATE] [mt/MEDICAL_TEST] [r/RESULT]`

Examples:
* `view t/test i/S1234567L` followed by `edit 1 mt/CT Scan` updates the test type of first test result entry displayed on the screen.


## FAQ
Q: How do I transfer my data to another Computer?
A: Install the app on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MedBook folder.

## Command Summary

| Action | Format Example |
| :----- | :------------- |
| View Help | `help` |
| Add a Patient | `add i/NRIC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tg/TAG]...` |
| List all Patients | `view` |
| View Summary of a Patient | `view i/NRIC` |
| Edit Patient | `edit INDEX [n/NAME] [r/RELATIONSHIP] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`|
| Delete any Entry | `delete INDEX` |
| Find | `find KEYWORD [MORE_KEYWORDS]` |
| Add Contact Information | `add t/contact i/NRIC n/NAME r/RELATIONSHIP p/PHONE_NUMBER e/EMAIL a/ADDRESS` |
| View Contact Information | `view t/contact i/NRIC` |
| Edit Contact Information | `edit [n/NAME] [r/RELATIONSHIP] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` |
| Add Medical Information | `add t/medical i/NRIC [a/AGE] [bt/BLOOD_TYPE] [md/MEDICATION] [ht/HEIGHT] [wt/WEIGHT] [il/ILLNESSES] [su/SURGERIES] [fh/FAMILY_HISTORY] [ih/IMMUNIZATION_HISTORY] [gd/GENDER] [et/ETHNICITY]` |
| View Medical Information | `view t/medical [i/NRIC]` |
| Edit Medical Information | `edit INDEX [a/AGE] [bt/BLOOD_TYPE] [md/MEDICATION] [ht/HEIGHT] [wt/WEIGHT] [il/ILLNESSES] [su/SURGERIES] [fh/FAMILY_HISTORY] [ih/IMMUNIZATION_HISTORY] [gd/GENDER] [et/ETHNICITY]` |
| Add Consultation Information | `add t/consultation i/NRIC dt/DATE tm/TIME n/NOTES` |
| View Past Consultations | `view t/consultation i/NRIC` |
| Edit Consultation Information | `edit INDEX [dt/DATE] [tm/TIME] [dg/DIAGNOSIS] [fe/FEE] [nt/NOTES]` |
| Add Prescription | `add t/prescription i/NRIC n/DRUG_NAME dt/DATE s/INSTRUCTION` |
| View Prescription | `view t/prescription i/NRIC` |
| Edit Prescription | `edit INDEX [n/DRUG_NAME] [dt/DATE] [s/INSTRUCTION]` |
| Add Test Result | `add t/test i/NRIC dt/DATE mt/MEDICAL_TEST r/RESULT` |
| View Test Result| `view t/test i/NRIC` |
| Edit Test Result | `edit INDEX [dt/DATE] [mt/MEDICAL_TEST] [r/RESULT]` |
