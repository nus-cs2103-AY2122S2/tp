---
layout: page
title: Sean Leong's Project Portfolio Page
---

### Project: ReCLIne

ReCLIne is a management application used by recruiters to organise their applicant and job data. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to store Applicants, Jobs and Unique Job id in a JSON file. [\#96](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/96), [\#144](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/144/files)
  * What it does: Allows the user to save Applicants, Jobs, and the current unique id for new Jobs.
  * Justification: This feature is important for the application to save application information in the appropriate data file.
  * Highlights: This enhancement was challenging as it required an in-depth understanding of how data from the model and logic classes converts to a JSON format. Requires an understanding of how the data from individual models are gathered and saved in a single JSON file. Finally, it requires and understanding of how the data from the Json file converts back to a model. Given the number of attribute classes in both Applicants and Jobs, it also required extensive testing to ensure all attributes could be converted to JSON file, and converted back into the correct models. The implementation required additions in the JSON storage classes, creation of new Storage classes JsonAdaptedJob and JsonAdaptedApplicant, as well as changes in the model classes.
  * Credits: This feature builds on the storage system of AB3. It uses the same design pattern and methods as JsonAdaptedPerson for JsonAdaptedApplicant and JsonAdaptedJob, but adapted for Applicant and Jobs respectively. 

* **New Feature**: Added the ability to assign Unique ID to Jobs on creation of Job. [\#139](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/139/files)
  * What it does: Assigns a unique Id to Jobs when they are created.
  * Justification: This feature is important as there can be multiple identical Job openings, as companies might require more than 1 individual to do a job. Hence, a Unique Job Id is important to differentiate between Jobs.
  * Highlights: This enhancement requires an understanding of how data is saved from the AddressBook model into the JSON file, as well as how to retrieve stored data from the model. Since there was no prior implementation of this in AB3, I came up with the way to store a single integer in the application, and then to increment and save the new ID to the data file whenever a new Job is added.
  * Credits: This feature was designed on my own. It builds on the current data storage file and addressbook to store a single integer representing the unique ID in the addressbook, which will be incremented and saved to the data file whenever a new JobID is assigned.

* **New Feature**: Added new Predicate for application to search the job list by job id. [\#152](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/152/files)
  * What it does: allows the user to search for Jobs by the JobID.
  * Justification: This feature is important for the user to find the desired Job quickly without having to scan the whole job list.

* **New Feature**: Added the ability to add new Jobs. [\#139](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/139/files)
  * What it does: allows the user to add new Jobs to ReCLIne.
  * Justification: This feature is important for the user to create new Jobs.
  * Highlights: This enhancement was challenging as it requires knowledge of the valid inputs for all the different attributes in a Job. The compulsory attributes when adding a Job had to be chosen, and for the remaining attributes, I had to modify the attribute classes such that they were able to have a "PENDING" state.
  * Credits: This feature is adapted from the Add command of AB3. It uses the same command design pattern, a parser and a command class. However, the class that is created is changed to Applicant.

* **New Feature**: Added the ability to add new Applicants. [\#75](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/75), [\#76](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/76), [\#91](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/91)
    * What it does: allows the user to add new Applicants to ReCLIne.
    * Justification: This feature is important for the user to create new Applicants.
    * Highlights: This enhancement was challenging as it requires knowledge of the valid inputs for all the different attributes in an Applicant. The compulsory attributes when adding an Applicant had to be chosen, and for the remaining attributes, I had to modify the attribute classes such that they were able to have a "PENDING" state.
    * Credits: This feature is adapted from the Add command of AB3. It uses the same command design pattern, a parser and a command class. However, the class that is created is changed to Applicant.

* **New Feature**: Added new attribute class Nric. [\#58](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/58)
  * What it does: allows the user to save the NRIC of an Applicant.
  * Justification: This feature is important for the user to create new Applicants.

* **New Feature**: Added the ability to list all Jobs in ReCLIne. [\#150](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/150)
  * What it does: allows the user to see all the Jobs in the application after he executes the find command.
  * Justification: This feature is important as the user will only be able to see the Job he searches for after using the find command. To see all the jobs again, the user will use this ListJob command for the application to list all Jobs.
  * Credits: This feature is adapted from the List command of AB3. It just changes the filteredlist back to the full observable list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=seanleong339&breakdown=true)

* **Project management**:
    * Manage the issue tracker, ensure that issues are resolved, close completed and irrelevant issues, and close completed milestones.
    * Managed release `v1.3.1` (1 release) on GitHub
    * Lead weekly meetings on project deliverables and tasks for the week.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `addapplicant` and `addjob` [\#72]()
        * Added section on editing the data file
        * Added new FAQ on how to transfer data to a new computer.
        * Added the Glossary.
    * Developer Guide:
        * Added implementation details of the `addapplicant` feature, as well as the UML diagrams in that section.
        * Added documentation on how the storage components of ReCLIne store data, as well as the UML diagrams in that section.
        * Added instruction for manual testing for adding an applicant an adding a job, as well as dealing with data file issues.


* **Contribution to team-based tasks**:
  * Code quality: Looks after code quality, ensures adherence to coding standards, etc
  * Write and manage the Applicant and Job classes, coordinate the group to ensure all the attribute classes can work together, and make the appropriate changes to the attribute class methods: [\#250](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/250), [\#248](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/248), [\#98](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/98), [\#116](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/116)
  * Remove traces of AB3 from User Guide [\#56]("https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/56/files")


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#77](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/77), [\#79](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/79), [\#82](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/82), [\#86](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/86), [\#89](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/89), [\#154](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/154), [\#157](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/157).
