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
  - [Viewing Help](#viewing-help)
  - [Adding a Patient: `add`](#adding-a-patient-add)
  - [Listing all Patients: `view`](#listing-all-patients-view)
  - [Deleting any Information: `delete`](#deleting-any-information-delete)
  - [Adding Contact Information: `add t/contact`](#adding-contact-information-add-tcontact)
  - [Viewing Contact Information: `view t/contact`](#viewing-contact-information-view-tcontact)
  - [Adding Medical Information: `add t/medical`](#adding-medical-information-add-tmedical)
  - [Viewing Medical Information: `view t/medical`](#viewing-medical-information-view-tmedical)
  - [Adding Consultation Information: `add t/consultation`](#adding-consultation-information-add-tconsultation)
  - [Viewing Past Consultations: `view t/consultation`](#viewing-past-consultations-view-tconsultation)
  - [Adding Prescription: `add t/prescription`](#adding-prescription-add-tprescription)
  - [Viewing Prescription: `view t/prescription`](#viewing-prescription-view-tprescription)
  - [Adding Test Result: `add t/test`](#adding-test-result-add-ttest)
  - [Viewing Test Result: `view t/test`](#viewing-test-result-view-ttest)
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
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`  after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]… ` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

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

Format" `add i/NRIC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...`

Examples:
* `add i/S1234567L n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

### Listing all Patients: `view`

Shows a list of patients in MedBook.

Format: `view`

### Deleting any Information: `delete`

Deletes a specified information (contact, medical information,...) from MedBook.

Format: `delete INDEX`

* We can only delete the information only if the display panel is displaying the information.
* The index refers to the index number shown in the displayed list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `view t/prescription i/S1234567L` followed by `delete 2` deletes the second prescription of the patient displayed on the screen.
* `view` followed by `delete 1` deletes the first patient displayed on the screen.

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

### Adding Medical Information: `add t/medical`
Adds a patient's medical information to MedBook.

Format: `add t/medical i/NRIC
[a/AGE]
[bt/BLOOD_TYPE]
[md/MEDICATION]
[ht/HEIGHT]
[wt/WEIGHT]
[il/ILLNESSES]
[su/SURGERIES]
[fh/FAMILY_HISTORY]
[ih/IMMUNIZATION_HISTORY]
[gd/GENDER]
[et/ETHNICITY]`

Examples:
* `add t/medical i/S1234567L bt/O ht/185 cm`

### Viewing Medical Information: `view t/medical`

Displays medical information of a patient from MedBook.

Format:  `view t/medical i/NRIC`

Examples:
* `view t/medical i/S1234567L`

### Adding Consultation Information: `add t/consultation`

Adds a consultation report of a patient to MedBook.

Format: `add t/consultation i/NRIC dt/DATE tm/TIME -n/NOTES`

Examples:
* `add t/consultation i/S1234567L dt/2021-09-15 tm/18-00 n/Inflammation in the throat and windpipe, short and shallow breath, laboured breathing. Most likely has Upper Respiratory Infection.`

### Viewing Past Consultations: `view t/consultation`

Views all past consultations of a patient in MedBook.

Format: `view t/consultation i/NRIC`

Examples:
* `view t/consultation i/S1234567L`

### Adding Prescription: `add t/prescription`

Adds a medical prescription of a patient in MedBook.

Format: `add t/prescription i/NRIC n/DRUG_NAME dt/DATE s/INSTRUCTION`

Examples:
* `add t/prescription i/S1234567L n/Amoxicillin dt/2021-09-15 s/2 tablets after meal everyday.`

### Viewing Prescription: `view t/prescription`

Views a medical prescription of a patient in MedBook.

Format: `view t/prescription i/NRIC`

Examples:
* `view t/prescription i/S1234567L`

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


## FAQ
Q: How do I transfer my data to another Computer?
A: Install the app on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MedBook folder.

## Command Summary

| Action | Format Example |
| :----- | :------------- |
| Add Contact Info | `/create -t contact -i NRIC -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS` |
| View Contact Info | `/view -t contact [-i NRIC] [-n NAME] [-p PHONE_NUMBER] [-e EMAIL]` |
| Delete Contact Info | `/delete -t contact -i NRIC` |
| Add Medical Info | `add t/medical i/NRIC [a/AGE] [bt/BLOOD_TYPE] [md/MEDICATION] [ht/HEIGHT] [wt/WEIGHT] [il/ILLNESSES] [su/SURGERIES] [fh/FAMILY_HISTORY] [ih/IMMUNIZATION_HISTORY] [gd/GENDER] [et/ETHNICITY]` |
| View Medical Info | `/view -t medical [-i NRIC]` |
| Delete Medical Info | `/delete -t medical -i NRIC` |
| Add Consultation Info | `/create -t consultation -i S1234567P [-dt DATE] [-tm TIME] [-n NOTES] [-p PRESCRIPTION] [-tt TESTS TAKEN AND RESULTS]` |
| View Consultation Info | `/view -t consultation  -i S1234567P [-dt DATE][-tm TIME]` |
| Delete Consultation Info | `/delete -t consultation -i S1234567P [-dt DATE] [-tm TIME]` |
