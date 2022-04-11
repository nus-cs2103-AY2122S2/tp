---
layout: page
title: Yi Juan's Project Portfolio Page
---

## Project: ReCLIne

####Overview
ReCLIne is a management application used by recruiters to organise their applicant and job data. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added new attribute ApplicantStatus [\#82](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/82) [\#103](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/103)
    * What it does: Allows the user to update the status of a job application on ReCLIne.
    * Justification: This feature is important as it gives recruiters the ability to keep track of completed, or ongoing recruitment applications.
    * Highlights: This enhancement is built on an original class extended from AB3, and hence requires in-depth analysis of the original AB3 codebase.

* **New Feature**: Added new attribute JobId [\#95](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/95)
    * What it does: Allows the user to keep track of what job listing an applicant has applied for.
    * Justification: This feature is important as it gives recruiters the ability to match applicants to their specific job listing. 
    * Highlights: This enhancement is built on an original class extended from AB3, and hence requires in-depth analysis of the original AB3 codebase.

* **New Feature**: Added new attribute JobStatus [\#117](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/117) [\#129](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/129) [\#130](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/130)
    * What it does: Allows the user to update the status of a job listing on ReCLIne.
    * Justification: This feature is important as it gives recruiters the ability to keep track of job listings that have found suitable candidates, and those which have not yet.
    * Highlights: This enhancement is built on an original class extended from AB3, and hence requires in-depth analysis of the original AB3 codebase.

* **New Feature**: Added new attribute Job Position [\#118](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/118) [\#130](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/130)
    * What it does: Stores the position-type of the job listing: full-time, or part-time
    * Justification: This feature is important as it gives recruiters the ability to keep track of jobs' position-type and match relevant candidates.  
    * Highlights: This enhancement is built on an original class extended from AB3, and hence requires in-depth analysis of the original AB3 codebase.

* **New Feature**: Added the ability to update applicants' application status on ReCLIne. [\#104](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/104) [\#288](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/288)
    * What it does: Allows the user to update the status of a job application on ReCLIne.
    * Justification: This feature is important as it gives recruiters the ability to keep track of completed, or ongoing recruitment applications.
    * Highlights: This feature does not have an existing version on AB3 and hence requires in-depth analysis of the original AB3 codebase.

* **New Feature**: Added the ability to update job listings' status on ReCLIne. [\#142](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/142)
    * What it does: Allows the user delete a job from ReCLIne.
    * Justification: This feature is important as it gives recruiters the ability to remove an irrelevant job from ReCLIne when the job is no longer needed. This keeps the job list tidy and more focused on the important jobs.
    * Highlights: This feature does not have an existing version on AB3 and hence requires in-depth analysis of the original AB3 codebase.

* **New Feature**: Added the ability to sort the applicant list by a given attribute. [\#157](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/157) [\#267](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/267)
    * What it does: Allows the user to sort the applicant list by attributes such as the date they applied, their interview date, or the job they are applying for.
    * Justification: This feature is important as it allows recruiters to easily see which applicants have upcoming interviews or which applicants are applying for the same jobs.
    * Highlights: This feature does not have an existing version on AB3 and hence requires in-depth analysis of the original AB3 codebase.

* **Code contributed**:
  [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=chanyijuan&breakdown=true)

* **Project management**:
    * Manage the issue tracker, ensure that issues are resolved, close completed issues.
    * Took meeting minutes to keep track of weekly deliverables.
    * 

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage from 68% to 73% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `markapplicant`, `markjob` and `sortapplicant`.
    * Developer Guide:
        * Added instructions for manual testing for `markapplicant`, `markjob`, and `sortapplicant` features.

* **Contributions to team-based tasks**:
    * Added test classes to ensure features are working across the platform:
        * Added JUnit testing classes for attributes, commands, and parsers
    * Set up team GitHub team organization
    
* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#125](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/125), [\#71](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/71)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2122S2-CS2103T-T11-3/tp/issues/157), [2](https://github.com/AY2122S2-CS2103T-T11-3/tp/issues/150))
