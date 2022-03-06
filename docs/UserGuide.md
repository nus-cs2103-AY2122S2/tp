---
layout: page 
title: User Guide
---

<img src = "images/user-guide/icon.png" width = "250" alt="Unable to load image! Try again 
later.">
## Table of Contents
- [Table of Contents](#table-of-contents)
- [Quick start](#quick-start)
  - [Requirement](#requirement)
  - [Setup](#setup)
- [Features](#features)
  - [Create Contact Information: `/create -t contact`](#create-contact-information-create--t-contact)
  - [View Contact Information: `/view -t contact`](#view-contact-information-view--t-contact)
  - [Delete Contact Information: `/delete -t contact`](#delete-contact-information-delete--t-contact)
  - [Create Medical Information: `/create -t medical`](#create-medical-information-create--t-medical)
  - [View Medical Information `/view -t medical`](#view-medical-information-view--t-medical)
  - [Delete Medical Information: `/delete -t medical`](#delete-medical-information-delete--t-medical)
  - [Create Consultation Information: `/create -t consultation`](#create-consultation-information-create--t-consultation)
  - [View Past Consultations: `/view -t consultation`](#view-past-consultations-view--t-consultation)
  - [Delete Consultation Information: `/delete -t consultation`](#delete-consultation-information-delete--t-consultation)
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

### Create Contact Information: `/create -t contact`

Adds a patient's contact to the Medbook

Format: `/create -t contact -i NRIC -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS`

Examples:
* `/create -t contact -i S12345678P -n John Doe -p 80008000 -e johndoe@gmail.com -a COM1`

<img src = "images/user-guide/feature2_1.png" width = "350" alt="Unable to load image! Try again later.">

### View Contact Information: `/view -t contact`

Views a patient’s details from the MedBook

Format: `/view -t contact [-i NRIC] [-n NAME] [-p PHONE_NUMBER] [-e EMAIL]`

Note: Optional fields allow users to have a more refined search.

Examples:
* `/view -t contact` to show all patients’ contact information
* `/view -t contact -i S12345678P` to view the contact information of the patient with this NRIC
* `/view -t contact -n John Smith` to view all contact information of patients with this name

<img src = "images/user-guide/feature2_2.png" width = "350" alt="Unable to load image! Try again later.">

### Delete Contact Information: `/delete -t contact`

Deletes a patient from the MedBook

Format: `/delete -t contact -i NRIC`

Note: Only NRIC can be used to uniquely identify the contact owner.

Examples:
* /delete -t contact -i S12345678P

### Create Medical Information: `/create -t medical`
Adds a patient's medical information to the MedBook.

Format: `/create -t medical -i S12345678P [-a AGE] [-bt BLOOD_TYPE] [-md MEDICATION]...`

Optional fields and associated flags:
- Age `-a`
- Blood type `-bt`
- Medication `-md`
- Height `-ht`
- Weight `-wt`
- List of illnesses `-il`
- List of surgeries `-su`
- Family history `-fh`
- Immunization history `-ih`
- Gender `-gd`
- Ethnicity `-et`

Examples:
* `/create -t medical -i S12345678P -bt O -ht 185 cm`

<img src = "images/user-guide/feature2_4.png" width = "350" alt="Unable to load image! Try again later.">

### View Medical Information `/view -t medical`
Displays medical information of a patient from the MedBook. If no NRIC number is included, displays a list of current medical information records.

Format:  `/view -t medical [-i NRIC]`

Examples:
* `/view -t medical`
* `/view -t medical -i S12345678P`

<img src = "images/user-guide/feature2_5.png" width = "350" alt="Unable to load image! Try again later.">

### Delete Medical Information: `/delete -t medical`
Deletes all medical information of a patient from the MedBook

Format: `/delete -t medical -i NRIC`

Examples:
* `/delete -t medical -i S12345678P`

### Create Consultation Information: `/create -t consultation`
Adds a consultation report of a patient to the MedBook.

Format: `/create -t consultation -i S12345678P [-dt DATE] [-tm TIME] [-n NOTES] [-p PRESCRIPTION] [-tt TESTS TAKEN AND RESULTS]`

NOTE: [-dt DATE][-tm TIME] are in the form dd-MM-yyyy, HH-mm (24 hour) respectively.

Examples:  
* `/create -t consultation -i S12345678P -dt 15-09-2021 -tm 18-00 -n Inflammation in the throat and windpipe, short and shallow breath, laboured breathing. Most likely has Upper Respiratory Infection. -p Augmentin Antibiotics 625mg - twice a day; Paracetamol 500mg - twice a day. -tt Stethoscope. Found short and laboured breathing.`

### View Past Consultations: `/view -t consultation`
View all past consultations in the MedBook that fit search parameters. All fields are optional.

Format: /view -t consultation  -i S12345678P [-dt DATE][-tm TIME]

NOTE:  [-dt DATE][-tm TIME] are in the form dd-MM-yyyy, HH-mm (24 hour) respectively.

Examples:
* `/view -t consultation`
  * Shows all the consultations for all patients
* `/view -t consultation -i S12345678P`
  * Shows all the consultations for patient with id S12345678P
* `/view -t consultation -i S12345678P -dt -09-2021`
  * Shows all consultations for patients with id S12345678P that occurred in Sept 2021
* `/view -t consultation -dt 15-09-2021 -tm 18-00`
  * Shows consultation that occurred on Sept 15 2021 1800hrs
    
<img src = "images/user-guide/feature2_8_1.png" width = "350" alt="Unable to load image! Try again later.">
<img src = "images/user-guide/feature2_8_2.png" width = "350" alt="Unable to load image! Try again later.">

### Delete Consultation Information: `/delete -t consultation`
Deletes a consultation of a patient from the MedBook.

Format: `/delete -t consultation -i S12345678P [-dt DATE] [-tm TIME]`

Examples:
* `/delete -t consultation -i S12345678P  -dt 15-09-2021 -tm 18-00`

## FAQ
Q: How do I transfer my data to another Computer?  
A: Install the app on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MedBook folder.  

## Command Summary
| Action                   | Format Example                                                                                                         |
| ------------------------ | ---------------------------------------------------------------------------------------------------------------------- |
| Add Contact Info         | /create -t contact -i NRIC -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS                                                 |
| View Contact Info        | /view -t contact [-i NRIC] [-n NAME] [-p PHONE_NUMBER] [-e EMAIL]                                                      |
| Delete Contact Info      | /delete -t contact -i NRIC                                                                                             |
| Add Medical Info         | /create -t medical -i S12345678P [-a AGE] [-bt BLOOD_TYPE] [-md MEDICATION]...                                         |
| View Medical Info        | /view -t medical [-i NRIC]                                                                                             |
| Delete Medical Info      | /delete -t medical -i NRIC                                                                                             |
| Add Consultation Info    | /create -t consultation -i S12345678P [-dt DATE] [-tm TIME] [-n NOTES] [-p PRESCRIPTION] [-tt TESTS TAKEN AND RESULTS] |
| View Consultation Info   | /view -t consultation  -i S12345678P [-dt DATE][-tm TIME]                                                              |
| Delete Consultation Info | /delete -t consultation -i S12345678P [-dt DATE] [-tm TIME]                                                            |
