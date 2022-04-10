---
layout: page
title: Darshan's Project Portfolio Page
---

## Project: ReadyBakey

### Overview

ReadyBakey is a desktop app for small bakers to manage their orders.  The user interacts with it 
using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org/).

Given below are my contributions to the project.

### Summary of contributions:

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=darshan152&breakdown=true)

* **Enhancements**:
  1. Added Mark & Unmark of Order
     * What it does: Allows user to denote whether an order is complete or incomplete
     * Justification: The feature provides a way for users to differentiate an order that is already done from one that still needs to be done
  2. Implemented the linkage between order and person
        * Added the internal logic of the application to allow for a linkage to be made between an order and a person
        * This allowed for a single source of the data meaning that there would not be discrepancies in the details
        * Modified the way that the UI was displayed in order to allow for the full details to be shown
        * Implemented a UUID for each person in order to maintain the link to an order even if his details changed
  3. Added dummy data for the application for order as well as modified the existing person's dummy data to contextualise it to a baker's context
  4. Added a restriction for the phone number of each person to be unique
  5. Added a restriction to disallow deletion of person if there are orders still linked to them
  6. Refactor EditCommand to EditPersonCommand to allow for addition of Order commands
  7. Added improvements to Tags to ensure display on the app works as expected

* **Documentation**:
  * User Guide
    * Removed `clearo` command that was not implemented
    * Added original `addp`, `listp` and `deletep` commands
    * Updated information on Tags to accurately reflect implementation
    * Fixing formatting errors and minor mistakes
  * Developer Guide
    * Added mark order sequence diagram as well as implementation details
    * Updated add order command implementation
    * Updated delete person sequence diagram
    * Updated the UI class diagram
* **Project management and Contributions to team-based tasks**
  * Managed release V1.2.1
    * Updated screenshots
    * Uploading JAR file to github
  * Maintaining issue tracker
  * Contributed to discussions on direction of the product - how each specific feature should behave
* **Review/mentoring contributions:**:
  * Reviewed PRs of peers
  * Helped fix merge issues in other team members branches [PR link](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/126)
  * Helped identify opportunities to implement more defensive programming [PR link](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/134)

