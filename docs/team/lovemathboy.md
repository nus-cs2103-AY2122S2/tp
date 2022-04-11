---
layout: page
title: Huang Xing Chen's Project Portfolio Page
---

### Project: AddressBook π (Abπ)

AddressBook pi (Abπ) is a **360° all-rounded desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI). Abπ is catered towards fast-typers and individuals who want an organized address book with key features including efficient filtering of contacts based on various attributes (name, email, tags, etc.), copying of all emails in the address book and a memo section that allows you to note down notable details about a person.

The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 9k LoC.

**Summary of contributions**

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=lovemathboy&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Features and Enhancements implemented:**
  * Copyemails command (PR [\#65](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/65)):
    * What it does: Allows the user to copy all emails in a filtered list to clipboard.
    * Justification: This feature allows the user to quickly contact a specific list of contacts in their address book (such as sending out invites to friends for a party), significantly cutting down the time required to manually type in all email addresses.
  * Revamp the User Interface – Person Display Panel (PR [\#173](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/173))
    * What it does: Displays detailed information about a contact. 
    * Justification: Lengthy text inside a Person Card gets cut-off, hence a scrollable panel to display the details of a person will allow users to view the full details of a person.
    * Highlights: Due to the implementation of "predictive display" where the display will automatically update on several commands, this resulted in several bugs. Debugging this feature was difficult, as there were a lot of bugs involving undoes and redoes not updating the display properly. In addition, designing this feature was difficult as it required an interaction between logic (commands updating the display), model (person object to be displayed), and the UI itself.
      <br>
  <div style="page-break-after: always;"></div>

  * View command (PR [\#188](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/188))
    * What it does: Manually updates the Person Display Panel
    * This command goes hand in hand with the previous feature.
      <br>
* **Documentation**:
  * User Guide:
    * Added documentation for `copyemails` and `view` commands (PR [\#40](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/40), [\#190](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/190))
    * Added several images to illustrate command usage and UI (PR [\#294](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/294))
  * Developer Guide:
    * Added implementation details of the Person Display Panel (PR [\#307](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/307))
* **Contributions to team-based tasks**:
  * Enabled assertions for team repo.
  * Main code quality checker and debugger.
  * Worked on the Abπ demo video.
    <br>
* **Community**:
  * PRs reviewed (with non-trivial review comments): (PR [\#27](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/27), [\#28](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/28), [\#61](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/61), [\#132](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/132), [\#248](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/248), [\#264](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/264))
  * [All other PRs reviewed](https://github.com/AY2122S2-CS2103T-T17-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Alovemathboy)
  * Reported bugs and suggestions for other teams in the class during [PE-D](https://github.com/lovemathboy/ped/issues) and an [internal bug-finding session](https://github.com/AY2122S2-CS2103T-T17-1/tp/issues/273)
