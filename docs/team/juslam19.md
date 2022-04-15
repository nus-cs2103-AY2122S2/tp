---
layout: page
title: Justin Lam's Project Portfolio Page
---

### Project: MedBook

MedBook is a **desktop app health monitoring system for healthcare professionals** that simplifies tracking patient’s medical information and hospital records via a Command Line Interface (CLI). MedBook delivers a seamless workflow for doctors and healthcare professionals to search for or update patients' emergency contacts, medical information, medical tests, consultations and prescriptions through a simple and easy-to-use platform.

Given below are my contributions to the project.

* **New Feature**: Patient's Consultation
  * What it does: It allows the user to add consultations for each patient.
  * Justification: This features extend the product functionality as a health monitoring system to store patients' consultations. Medical professionals can see past consultation information of patients, such as diagnosis, fees, and additional notes.
  * Credits: The feature was built on top of [AddressBook Level 3 (AB3)](https://github.com/se-edu/addressbook-level3). Some part of the codes were adapted and modified.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=juslam19&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements Implemented:**
  1. App User Interface
      1. Made modifications to the application to ensure better User Experience. Pull request: [#116](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/116)
          * Implemented the wrapping of text in the app's respective elements, so text will not be truncated/ run off-screen.
          * Added ability for result display to grow in size as window size increased, to take advantage of screen real-estate.
          * Returned ability to resize app after a PR removed that.
      2. Added functionality to messages shown in result display
         1. Standardised format of all types of messages in result display. Pull requests: [#116](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/116), [#225](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/225), [#234](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/234)
             * For example: For the incorrect format of `add` command message, I ensure that for every type (patient, contact, consultation, medical, prescription, test) use the same format -- the same error message, followed by the proper format and parameter of the command, followed by an example of the command.
             * A similar approach is used for every message (e.g. duplicate, does not exist, etc.) that is present in each type (patient, contact, consultation, medical, prescription, test).
             * This is repeated for all the commands, which include but are not limited to: `add`, `view`, `edit`, `delete`.
         2. Added patient name to result display messages for `view t/TYPE i/NRIC` commands, for clarification to user. Pull request: [#116](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/116)
         3. Ensured catching of errors to be caught and shown on result display, for clarification to user. Pull requests: [#225](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/225)
  2. Added increased functionality to the application
      * Added scroll bar and its styling. Pull request: [#116](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/116)
        * Added scroll bar with incremental arrow buttons. 
        * Added scroll bar buttons' visual response to hover and press.
  3. Contributed to standardising command behavior
     * Ensured `edit t/medical INDEX` command followed other types' `edit` command -- only valid if at least one optional field is filled out. Pull request: [#240](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/240)
  4. Added Dummy data when data file unavailable
      * Added dummy consultation entries to populate MedBook when existing data file is not detected, indicating newly downloaded application or reset of existing MedBook application. Pull request: [#259](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/259)
  5. Code Quality
      * Wrote test cases to increase code coverage from 30.40% to 32.00%. Pull request: [#259](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/259)

* **Contributions to the UG:**
  * Added patients' consultation section. Pull request: [#130](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/130)
  * Modified others' sections if there is incorrect documentation. Pull request: [#226](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/226)

* **Contributions to the DG:**
  * Added description of how consultation commands works, and UML diagram of the Consultation command logic. Pull request: [#90](https://github.com/AY2122S2-CS2103T-T11-1/tp/pull/90)

* **Contributions to Team-Based Tasks:**
  * Contributed to product concept brainstorming.
  * Contributed to brainstorming of user stories.
  * Contributed to adapting of AB3 UG to the MedBook product.
  * Contributed to the updating of AB3 DG sections (product scope, user stories, use cases, NFRs, etc.) to the MedBook product.
  * Attended most meetings, implemented teammates' comments.

* **Review/mentoring Contributions:**
  * Provide reviews and comments to PR made by teammates.
  * PRs reviewed: [#59, #64, #95, #119, #123, #132, #133, #134, #224, #227, #233](https://github.com/AY2122S2-CS2103T-T11-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)

* **Contributions Beyond the Project Team:**
    * Reported bugs and suggestions for other team.
    * Issues reported: [#2, #3, #4, #5, #6, #7, #8, #9, #10](https://github.com/juslam19/ped/issues)


