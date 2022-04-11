---
layout: page
title: Brandon Kheng's Project Portfolio Page
---

### Project: Teaching Assistant's Personal Assistant (TAPA)

TAPA is a desktop contact management application used to manage students' contact and progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=brelkh&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Undo Command (New Feature)**:
  * What it does: Undoes the most recently executed command.
  * Justification: Performing tasks on a CLI can be punishing if a user were to execute the wrong command or execute a command with a typo. In these scenarios, having a command that reverts erroneous changes saves time and reduces frustration.
  * Highlights: This implementation of the undo feature requires storing previous states of TAPA. As such, the `ModelManager` had to be extended, and new classes constructed, to keep track of the state of TAPA after each successfully executed command. Special care also had to be taken to ensure that certain commands (including `clear` and `undo` itself) could not be undone as they would disrupt the tracking of TAPA's states.
  * Implementation: [#120](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/120)

* **History Command (New Feature)**:
    * What it does: Displays a list of previously executed commands.
    * Justification: In the event that the user needs to undo a chain of previously executed commands, but cannot remember when they executed the command, or how many commands they would need to undo, the history command provides an easy way to view their previously executed commands.
    * Highlights: This implementation of the history feature requires keeping track of previously executed commands, as well as updating the list of commands as the user executes new commands. As such, the `ModelManager` had to be extended to cater for these.
    * Implementation: [#120](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/120)
    
* **Confirm Clear Command (Extension)**:
  * What it does: After the user executes the clear command, TAPA requests for the user's confirmation before clearing all details stored in TAPA.
  * Justification: Due to the severity of accidentally clearing all details stored in TAPA, this confirmation feature acts as a safeguard to ensure the user knows the effect of the clear command and intends to clear TAPA.
  * Highlights: This extension of the clear command requires the user to interact with TAPA to confirm their decision. Thus, unlike for most other commands, the UI component of TAPA had to be studied and then extended. Additional thought was required to figure out how to obtain confirmation from the user. In this implementation, the confirmation feature acts as a command to build upon the existing command components, and interacts with the UI component of TAPA to achieve its purpose. Furthermore, the `confirm` input should not be recognised as a command during normal operations. As such, `LogicManager` and `MainWindow` had to be extended to cater for these needs.
  * Implementation: [#61](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/61)

* **Edit Command (Extension)**:
  * What it does: Edits the specified details of a student in TAPA.
  * Justification: As TAPA evolved its use cases from AB3, the edit command had to be updated to suit the current needs of TAPA, and function properly with the new parameters involved.
  * Implementation: [#64](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/64)

* **Enhancements to existing features**:
    * Implemented arrow key navigation in CLI for previously executed commands. ([#114](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/114))
    * Improved GUI
      * Edited `PersonCard` to highlight the most important information. ([#68](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/68))
      * Added text-wrapping to eliminate the need for side-scrolling. ([#63](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/63))
    * Wrote testcases for `clear`, `undo` and `history` features, as well as model components, to increase overall code coverage. ([#264](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/264) and [#271](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/271))

* **Documentation**:
    * User Guide:
        * Added documentation for the `edit`, `clear`, `undo` and `history` features. ([#41](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/41) and [#141](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/141)).
        * Improved user guide based on peer review comments received in CS2101. ([#130](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/130))
    * Developer Guide:
        * Added documentation for the `clear`, `undo` and `history` features. ([#110](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/110) and [#254](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/254)).

* **Contribution to team-based tasks**:
  * Worked with the team to:
    * Ideate and design TAPA.
    * Brainstorm and plan user stories and use cases.
    * Evolve AB3 into TAPA by updating or replacing outdated segments of code.
    * Maintain the issue tracker by assigning and labelling issues.
    * Provide timely updates to our TA.
    * Address and fix PE-D bugs.
    * Edit and format the UG and DG to make them more readable and user-friendly.

* **Community**:
    * PRs reviewed (with non-trivial review comments):
      * [#105](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/105) Filter students by module code
      * [#108](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/108) Update DG for Task Command
      * [#127](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/127) Implement DeleteTask Command
      * [#232](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/232) Update model component of DG
      * [#238](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/238) Add Assign Command and DeleteTask Command to DG
    * Reported a total of 10 bugs and suggestions for other teams in the class (reported [here](https://github.com/brelkh/ped/issues)).
