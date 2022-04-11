---
layout: page
title: Alvin Tay Ming Hwee's Project Portfolio Page
---

### Project: AddressBook π (Abπ)

AddressBook pi (**Abπ**) is a **360° all-rounded desktop app for managing contacts**, optimized for use via a Command Line Interface (CLI). Abπ is catered towards fast-typers and individuals who want an organized address book with key features including efficient filtering of contacts based on various attributes (name, email, tags, etc.), copying of all emails in the address book and a memo section that allows you to note down notable details about a person. 

The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10k LoC.

**Summary of contributions**

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=atmh&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Features and Enhancements implemented:**

  * Undo and redo command (PR [\#94](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/94)):
    * What it does: Allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This feature affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation was challenging as it required changes to existing commands.
    * Credits: https://se-education.org/addressbook-level3/DeveloperGuide.html#proposed-undoredo-feature
  <br><br>
  * Duplicate detection feature (PR [\#51](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/51), [\#238](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/238), [\#264](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/264))
    * What it does: Prevents duplicate entries with the same name, phone and email when using add and edit commands.
    * Justification: This feature improves the product by helping users manage duplicates by preventing duplicated contacts with the same name, phone and email.
    * Highlights: This feature affects existing and future person attributes on how uniqueness is determined, and needs to be integrated with the existing commands. Duplicate detection is not trivial, detecting only exact string matches is not enough. It was meticulously implemented to behave closely to how these attributes are in the real world. For example, "John &#160;&#160;&#160; Doe" and "john doe" are likely to be the same person, even though there are differences in white spaces and capitalization. The implementation was tedious and challenging as it required changes in many of the existing classes.
  <br><br>
  * Case insensitivity for all person attributes (PR [\#238](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/238))
    * Related to duplicate detection feature.
  <br><br>
  * Trim extra spaces between words (PR [\#238](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/238))
    * Related to duplicate detection feature.
  <br><br>
  * Memo feature (PR [\#50](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/50), [\#53](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/53), [\#63](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/63), [\#111](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/111), [\#121](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/121))
    * What it does: Allows the user to store miscellaneous information about a person.
    * Justification: This feature improves the product by giving users an optional data field to store any information pertaining to a contact. 
    * Highlights: This feature was integrated into existing add and edit commands, adhering to OOP principles such as DRY and Single Responsibility Principle. The UI also needed to be updated to be seamless, such that if memo is empty, the memo row in the UI must not appear. A limit was also imposed to protect from unnecessarily long inputs.
  <br><br>
  * ContactedDate feature (PR [\#133](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/133))
    * What it does: Allows the user to keep track of the last time that they contacted a person.
    * Justification: This feature improves the product by giving users an optional data field to store the last contacted date of a contact.
    * Highlights: This feature was integrated into existing add and edit commands, adhering to OOP principles such as DRY and Single Responsibility Principle. It was tricky to protect from edge cases. Proper date validation was required to ensure that the date given is a valid date following the dd-mm-yyyy format and is not a future date. Moreover, the date validation needs to properly handle leap dates and non-existent dates such as 30th February.
  <br><br>
  * Deletetag command (PR [\#178](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/178))
    * What it does: Allows the user delete one or more tags of a contact.
    * Justification: This feature improves the product by giving users the flexibility to delete one or more tags of a person. The alternative previously was the edit command, but edit overwrites all existing tags, which could be inconvenient for users. 
    * Highlights: This feature required careful implementation to ensure that it adheres to the code base design and OOP principles. This feature also indirectly affected duplicate detection, on what is considered as the same tag. Certain user inputs, such as the deletion of both valid and invalid tag in one command execution had to be handled properly as well. 
  <br><br>
  * Prevent extraneous parameters for commands that do not take in parameters (PR [\#150](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/150))
    * What it does: Prevents commands that do not take in parameters from executing when the user input has extra/trailing characters after the command word.
    * Justification: This feature improves the product by protecting the user from accidental invocations of the wrong command. E.g. if you want to delete the first person and mistakenly call `clear 1` instead of `delete 1`, it will now be interpreted as an invalid command to protect users from accidentally clearing the entire address book unintentionally. The proper format to execute clear is just `clear`.
    * Highlights: This feature required additional checks for all existing commands that take no parameters.
  <br><br>
  * Updated `Person#toString()`to better represent tags when displayed (PR [\#184](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/184))
  <br><br>
  * Contributed significantly to improving test coverage (PR [\#50](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/50), [\#58](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/58), [\#88](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/88), [\#94](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/94), [\#114](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/114), [\#133](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/133), [\#150](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/150), [\#163](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/163), [\#178](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/178), [\#238](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/238))
  <br><br>

<div style="page-break-after: always;"></div>

* **Documentation**:
  * User Guide:
    * Added documentation for `Memo`, `ContactedDate`, `undo`, `redo`, `deletetag`, preventing duplicates, ignoring case sensitivity, trimming extra spaces, preventing extraneous parameters, `add` and `edit` (PR [\#25](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/25), [\#51](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/51), [\#53](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/53), [\#63](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/63), [\#94](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/94), [\#133](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/133), [\#144](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/144), [\#168](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/168), [\#180](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/180), [\#234](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/234), [\#238](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/238), [\#239](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/239), [\#240](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/240))
    * Updated FAQ (PR [\#152](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/152))
    * Updated Quick start (PR [\#275](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/275))
    * Updated details about max integer and chronological ordering of contacts ([\#283](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/283))
    * Improved UserGuide formatting (PR [\#157](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/157), [\#159](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/159), [\#161](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/161), [\#180](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/180), [\#275](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/275), [\#283](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/283), [\#318](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/318))
  
  * Developer Guide:
    * Added implementation details of the `undo` and `redo` feature (PR [\#93](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/93))
    * Added design consideration details of the `Memo` and `ContactedDate` feature (PR [\#144](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/144))
    * Added implementation details for the duplicate detection feature (PR [\#267](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/267))
    * Updated UML diagrams ModelClassDiagram and BetterModelClassDiagram (PR [\#93](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/93), [\#109](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/109))
    * Added instructions for manual testing (PR [\#278](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/278))
    * Updated non-functional requirement and glossary (PR [\#291](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/291), [\#296](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/296))
    * Improved DeveloperGuide formatting (PR [\#165](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/165), [\#313](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/313), [\#326](https://github.com/AY2122S2-CS2103T-T17-4/tp/issues/326))
    <br><br>
    
* **Contributions to team-based tasks**:
  * Setting up the GitHub organisation and team repo.
  * Setting up codecov for the team to track code coverage.
  * Scheduling and tracking.
  * Maintaining the issue tracker.
  * Update `AboutUs.md` with team details and roles (PR [\#11](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/11))
  * Update Abπ logo (PR [\#266](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/266))
  * Update `README.md` (PR [\#285](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/285))
  * Update `index.md` (PR [\#287](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/287))
  * Managed milestones `v1.1` - `v1.4b` (7 milestones) on GitHub ([link](https://github.com/AY2122S2-CS2103T-T17-4/tp/milestones?state=closed))
  * Managed releases `v1.2` - `v1.4` (6 releases) on GitHub ([link](https://github.com/AY2122S2-CS2103T-T17-4/tp/releases))
  <br><br>

* **Community**:
  * PRs reviewed (with non-trivial review comments): (PR [\#61](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/61), [\#73](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/73), [\#77](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/77), [\#103](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/103), [\#123](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/123), [\#130](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/130), [\#132](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/132), [\#166](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/166), [\#174](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/174), [\#247](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/247), [\#250](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/250))
  * All other PRs reviewed: [Link to 50+ PRs reviewed](https://github.com/AY2122S2-CS2103T-T17-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aatmh)
  * Reported bugs and suggestions for other teams in the class ([1](https://github.com/atmh/ped/issues/1), [2](https://github.com/atmh/ped/issues/2), [3](https://github.com/atmh/ped/issues/3), [4](https://github.com/atmh/ped/issues/4), [5](https://github.com/atmh/ped/issues/5), [6](https://github.com/atmh/ped/issues/6), [7](https://github.com/atmh/ped/issues/7), [8](https://github.com/atmh/ped/issues/8), [9](https://github.com/atmh/ped/issues/9), [10](https://github.com/atmh/ped/issues/10), [11](https://github.com/atmh/ped/issues/11), [12](https://github.com/atmh/ped/issues/12), [13](https://github.com/atmh/ped/issues/13), [14](https://github.com/atmh/ped/issues/14), [15](https://github.com/atmh/ped/issues/15), [16](https://github.com/atmh/ped/issues/16), [17](https://github.com/atmh/ped/issues/17), [18](https://github.com/atmh/ped/issues/18), [and 4 others](https://github.com/AY2122S2-CS2103T-T17-1/tp/issues/273))
