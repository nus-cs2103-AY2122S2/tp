---
layout: page
title: Ng Hong Liang's Project Portfolio Page
---

### Project: Tinner

Tinner (anagram of Intern) is a desktop app for managing internship applications, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Tinner allows you to easily sort through and retrieve the relevant information faster than traditional GUI apps.

Given below are my contributions to the project.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jinnhl&breakdown=true)

* **New Feature**: Added the `addRole` feature to add a new role to a specific company.
  * What it does: Allow users to add a role to a specified company along with its relevant information.
  * Justification: This feature is necessary since users might have applied for multiple roles within the same company.
  * Highlights: This change required a nested `RoleManager` in order to handle changes done to the `RoleList` of the company. Besides adding a layer of Facade, `RoleManager` also ensure cases whereby only a ReadOnly list is produced as an added defensive programming practice.

* **New Feature**: Updated `find` feature to find companies and roles.
  * What it does: Allow users to look for specific companies and roles which matches certain keywords.
  * Justification: This feature is necessary since there might be many companies and inside each company, many roles.
  Resulting in a massive list which might not be able to be displayed in its entirety in our UI. Since we are catering
  our application for CLI-centric users, this command must have the capability to look through both company and role storage.
  * Highlights: This enhancement required a major revamp to filtering predicates, and it was challenging as we are basically narrowing three behaviours into one command.

* **Project management**: 
  * Ensured proper integration between all member's commits
  * Ensured proper documentation in UG and DG corresponds to our features and updates.

* **Enhancements to existing features**:
  * Implemented new parser methods for parsing for `Role` features [\#126](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/126)
  * Implemented testing for `find` feature [\#105](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/105)
  * Implemented testing for `reminder` feature [\#191](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/191)
  * Added maximum length enforcement for `CompanyName`, `RoleName` and `Phone` number fields [\#271](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/271)
  * Added validity check for `CompanyName` and `RoleName` keywords in `find` feature [\#305](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/305)


* **Documentation**:
  * User Guide:
    * Added documentation for `addRole` command [\#128](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/128)
    * Added documentation for GUI breakdown [\#196](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/196)
    * Added additional technical terminology [\#315](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/315)
  * Developer Guide:
    * Added Glossary and Non-functional requirements [\#39](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/39)
    * Updated Architecture section [\#166](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/166)
    * Added implementation details and manual testing of the `addRole` and `deleteRole` feature [\#290](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/290), [\#304](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/304)
    * Added use case for `find` feature [\#304](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/304)
  * README:
    * Updated readme to fit the project. [\#36](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/36)


* **Community**: 
  * PRs reviewed (with non-trivial review comments): examples: [\#169](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/169#discussion_r835857964), [\#188](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/188#discussion_r839067096)
  * Reported bugs and suggestions for other teams in class during PE-D [issues](https://github.com/jinnhl/ped/issues)
