---
layout: page
title: Justin Ng's Project Portfolio Page
---

### Project: ReCLIne

ReCLIne is a profile management desktop application that can be used by recruiters. It serves as a centralised location for recruiters to organise their applicant and job data.
Users can interact with ReCLIne using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10kLoC.

Given below are my contributions to the project.

* **New Feature**: Added Object class Applicant. [\#79](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/79) [\#255](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/255)
    * What it does: Allows the users to create an Applicant object that will simulate as a real Applicant with attributes such as his Name, NRIC, Qualification.
    * Justification: This feature is important for the users to be able to create a new Applicant that will be used by Applicant commands such as AddApplicant and EditApplicant. It is also in this class where the method to determine if 2 applicants are duplicates is written.
    * Highlights: This enhancement was challenging in partner with EditApplicant as it is needed to determine if 2 applicants are duplicates or not. On one hand, when editing an applicant, the edited applicant and old applicant should only be the same if they have the same NRIC, Phone number and Email address. 
      However, on the other hand, when comparing an applicant to other applicants in the applicant list, an applicant should be considered a duplicate the moment either its NRIC, Phone number or Email address is the same as other applicants in the list.
    * This feature is adapted from the Person class from AB3.
    
* **New Feature**: Added an applicant list where all applicants in that list are unique from each other [\#79](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/79/files)
    * What it does: Used by AB3 to store applicants. Ensures that each applicant is unique from each other
    * Justification: This feature is important for the application as it the list that stores all the information of applicants to be used across all applicant command classes. Any addition of new applicants, any changes to an applicant, and any deletion of applicant will all require the use of this feature.
    * This feature is adapted from the UniquePersonList attribute in AB3. It now stores unique applicants.
    
* **New Feature**: Added a job list where all jobs in that list are unique from each other [\#135](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/135/files)
    * What it does: Used by AB3 to store jobs. Ensures that each job is unique from each other
    * Justification: This feature is important for the application as it the list that stores all the information of jobs to be used across all job command classes. Any addition of new jobs, any changes to a job, and any deletion of job will all require the use of this feature.
    * This feature is adapted from the UniquePersonList attribute in AB3. It now stores unique jobs.

* **New Feature**: Added the ability to edit applicants that exist in ReCLIne [\#90](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/90) [\#140](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/140) 
    * What it does: Allows the users to edit the details of an applicant that is currently stored in ReCLIne
    * Justification: This feature is important for the application as it gives the users the ability to edit the applicant's information and keep it up to date, without the hassle of having to delete the applicant and add him all over again whenever a change is needed.
    * Highlights: The enhancement was challenging as it requires an in-depth understanding of how the many layers of AB3 interacted with each other. Editing the information was challenging as there was a need for each applicant in the list to be unique, no 2 applicants in ReCLIne should have the same NRIC, Phone number or Email address. 
      Thus, there was a need for rigorous checking within the command to endure that applicants will always be unique to one another even after editing the applicant's information.
    * This feature is adapted from the Edit command from AB3. It used the same command design pattern, a parser and a command class. However, the class that is created is changed to Applicant, and had to have checks to ensure that an edited applicant is not a duplicate of other applicants that exists in the list.

* **New Feature**: Added the ability to edit jobs that exist in ReCLIne [\#145](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/145)
    * What it does: Allows the users to edit the details of a job that is currently stored in ReCLIne
    * Justification: This feature is important for the application as it gives the users the ability to edit the jobs's information and keep it up to date, without the hassle of having to delete the job and add it all over again whenever a change is needed.
    * Highlights: The enhancement was challenging as it requires an in-depth understanding of how the many layers of AB3 interacted with each other. Editing the information was challenging as there was a need for each job in the list to be unique, no 2 jobs in ReCLIne should have the same Job Title and Company Name.
      Thus, there was a need for rigorous checking within the command to endure that jobs will always be unique to one another even after editing the job's information
    * This feature is adapted from the Edit command from AB3. It used the same command design pattern, a parser and a command class. However, the class that is created is changed to Job, and had to have checks to ensure that an edited job is not a duplicate of other jobs that exists in the list.

* **New Feature**: Added the ability for users to find an applicant in ReCLIne [\#149](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/149)
    * What is does: Allows the user to find applicant with relevant inputted keyword
    * Justification: If users have 100s of applicant profiles in ReCLIne, this feature allows users to be able to find applicants with a specific name easily. This is important as it will be a hassle to have to scroll through all applicants in order to find a particular applicant of intrerest.
    * This feature is adapted from the Find command from AB3. It just finds applicants in the applicant list instead.

* **New Feature**: Added the ability to list all Applicants in ReCLIne. [\#149](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/149)
    * What it does: allows the user to see all the Applicants in the application after he executes the FindApplicant command.
    * Justification: This feature is important as the user will only be able to see the Applicant he searches for after using the FindApplicant command. To see all the applicants again, the user will use this ListApplicant command for the application to list all Applicants.
    * Credits: This feature is adapted from the List command of AB3. It just changes the filteredlist back to the full observable list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=justinnje&breakdown=true)

* **Project management**:
    * Managed the issue tracker, opening new issues when necessary and closing them when completed.
    * Managed release `v1.2.1` (1 release) on GitHub

* **Enhancements to existing features**:
    * Added more valid and invalid attributes in CommandTestUtil to be used for testing of commands [\#145](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/145/files)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `editapplicant`, `editjob`, `listapplicant` and `findapplicant` [\#159](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/159/files)
        * Edited Command Summary [\#169](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/169/files)
        * Added flag summary [\#260](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/260) 
        * Overall maintenance of User Guide [\#112](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/112/files) [\#169](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/169/files) [\#251](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/251/files) [\#255](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/255)
    * Developer Guide:
        * Added implementation for `editapplicant` [\#119](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/119) [\#127](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/127)
        * Added User Stories [\#119](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/119)
        * Added Manual Testing instructions for `editapplicant` and `editjob` [\#304](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/304/files)

* **Contribution to team-based tasks**:
    * Code quality: Looks after code quality, ensures adherence to coding standards, etc
    * Created Test Classes that would be used across application for testing [\#79](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/79/files) [\#90](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/90/files) [\#145](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/145/files)

* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [\#98](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/98)
      [\#109](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/109)
      [\#118](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/118)
      [\#126](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/126)
      [\#158](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/158)
      [\#165](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/165)
      


