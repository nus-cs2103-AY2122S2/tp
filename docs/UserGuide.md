---
layout: page 
title: User Guide
---
<img src = "images/user-guide/icon.png" width = "250" alt="Unable to load image! Try again later.">


MedBook is a **desktop app fir health monitoring system for healthcare professionals via a Command Line Interface** (
CLI)  that simplifies tracking a patient’s medical details and scheduling appointments. MedBook delivers a seamless
workflow for doctors and healthcare professionals to search for or update patients' medical information, billing and
appointments through a simple and easy-to-use platform.


* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

### Requirement

- Ensure you have Java 11 or above installed on your computer.
- Download the latest medbook.jar from [here](https://github.com/AY2122S2-CS2103T-T11-1/tp/releases).

### Setup

1. Copy the file to the folder you want to use as the home folder for your MedBook.
2. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
3. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
=======
## 2.0 Features

### 2.1 Create Contact Information: / create -t contact

Adds a patient's contact to the Medbook

Format: / create -t contact -i NRIC -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS

Examples:
* /create -t contact -i S12345678P -n John Doe -p 80008000 -e johndoe@gmail.com -a COM1


<img src = "images/user-guide/feature2_1.png" width = "350" alt="Unable to load image! Try again later.">


### 2.2 View Contact Information: /view -t contact

Views a patient’s details from the MedBook

Format: /view -t contact [-i NRIC] [-n NAME] [-p PHONE_NUMBER] [-e EMAIL]

Note: Optional fields allow users to have a more refined search.

Examples:
* /view -t contact to show all patients’ contact information
* /view -t contact -i S12345678P to view the contact information of the patient with this NRIC
* /view -t contact -n John Smith to view all contact information of patients with this name

<img src = "images/user-guide/feature2_2.png" width = "350" alt="Unable to load image! Try again later.">

### 2.3 Delete Contact Information: /delete -t contact

Deletes a patient from the MedBook

Format: /delete -t contact -i NRIC

Note: Only NRIC can be used to uniquely identify the contact owner.

Examples:
* /delete -t contact -i S12345678P
