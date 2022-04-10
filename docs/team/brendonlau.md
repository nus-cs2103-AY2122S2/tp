---
layout: page
title: Brendon Lau's Project Portfolio Page
---

### Project: Tinner

Tinner (Anagram of Intern) is a desktop app for managing internship applications, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Tinner allows you to easily sort through and retrieve the relevant information faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the `editRole` feature to edit a specific role in a company
* What it does: allows the user to edit any fields or remove optional fields in `Role` by specifying the company and role indexes
* Justification: This feature is necessary for the user to update essential fields such as status, set or disable reminder and rectify any mistakes
* Highlight: This change requires a major change in `Model` internal components and additional classes in `Logic` internal components

* **New Feature**: Added the `deleteRole` feature to delete a specific role in a company
* What it does: allows the user to delete a `Role` by specifying the company and role indexes
* Justification: This feature is necessary for the user to delete their internship applications 
* Highlight: This change requires a major change in `Model` internal components and additional classes in `Logic` internal components

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=brendonlau&breakdown=true)

* **Project management**:
  * Managed all releases {v1.2 to v1.4.1} (4 releases) on GitHub
  * Facilitate weekly meeting with updated agendas
  * Keep track of the tasks required to be completed by the team
  * Keep track of milestones such as making sure issues are completed or carry over to the next milestone

* **Enhancements to existing features**:
  * Implemented colour-coding of role tags according to role statuses to allow users to gather information 
  pertaining to the roles at a glance [\#181](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/181), [\#174](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/174/files)
  * Updated `Model` as a Facade class for clients to interact with its internal components and relevant test cases that
  interacted with it [\#146](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/146)
  * Updated the constraints for the comparison of `Company` and `Role` [\#133](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/133), [\#288](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/288) 
  
* **Documentation**:
  * User Guide:
    * Converted documentation from Words to markdown file [\#19](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/19/files) 
    * Added documentation for `editRole` and `deleteRole` features [\#56](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/56), [\#139](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/139/files)
    * Added demo for `reminder`, `find`, `editRole`, `editCompany` and `favourite` features [\#200](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/200), [\#263](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/263)  
    * Update index.md for v1.3 [\#190](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/190)
    
  * Developer Guide:
    * Updated UI component section [\#151](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/151)
    * Added `editRole` for implementation section and 4 features for the testing section [\#152](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/152), [\#261](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/261)
    * Added use cases for `editRole` and `editCompany` features [\#284](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/284)     
    * Added draft for Effort section [\#301](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/301)

* **Community**:
  * PRs reviewed (with non-trivial review comments): examples, [\#271](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/271), [\#194](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/194), [\#167](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/167), [\#183](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/183)
  * Reported bugs and suggestions for other teams in the class: [1](https://github.com/AY2122S2-CS2103T-T17-2/tp/issues/240#issuecomment-1094183862)


