---
Layout: Page
Title: Ka Shing's Project Portfolio Page
---

### Project: TrackBeau

TrackBeau is a **desktop app made for beauty salons to aid them in managing customers' profile, bookings and services.
It is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User
Interface (GUI). If you can type fast, TrackBeau can help you manage your customers, bookings and services faster than
traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find customers, services and bookings using a keyword.
  * What it does: Allows the user to filter and view customer, services and bookings with keywords.
  * Justification: Feature is crucial for staff members to find specific customers, services and bookings to view, edit or delete their details.
  * Implemented 3 commands to find customers, services and bookings, `findc`, `finds` and `findb`.
  * `findc` shows a customer if a keyword in the data field matches.
  * `finds` shows a service if a keyword in the data field matches.
  * `findb` shows a booking if a keyword in the data field matches.

* **New Feature**: Added the ability to manage bookings in the beauty salon.
  * What it does: Allows the user to create, edit, delete, and view bookings.
  * Justification: Feature is crucial for staff members to keep track of the salon's schedule.
  * Implemented 4 commands for bookings, `listb`, `addb`, `editb` and `deleteb`.
  * `listb` display all bookings created.
  * `addb` creates a new booking with specified parameters.
  * `editb` edits an existing booking's parameter.
  * `deleteb` deletes specified bookings.

* **Code contributed**:
[RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=kashing555&tabRepo=AY2122S2-CS2103-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* Pull Request:
    * #35: Commands in User Guide
    * #36: Update Instructions for manual testing and user stories in Developer Guide
    * #71: Removed addressbook from helpwindow
    * #72: added search by name, phone number, skin type and hairtype
    * #77 & #99: Additional Find Keywords and find Tests
    * #101 & #126: Added Bookings Functions with services and customers

* **Enhancements to existing features**:
  * Revamp the find feature increase the ways to search for the user.

* **Testing**:
  * Included JUnit test cases for find booking command.

* **Documentation**:
  * User Guide:
    * Added sections 4.2.5, 4.3.1, 4.3.2, 4.3.3, 4.3.4, 4.3.5, 4.3.6 and 5.3.
    * Updated sections 5.2.
  * Developer Guide:
    * Added user stories
    * Added Instructions for manual testing for Bookings. Added `addb`, `editb, findb` and `deleteb`
    * Added Find customer feature in the Implementation section.
    * Restructured use cases and added UC05, UC10, UC15

* **Community**:
  * Reviewed and merged PRs for TP team.
  * Bug reports and suggestions for other TP teams during practical examination dry run. [repository: ped](https://github.com/kashing555/ped/issues)
