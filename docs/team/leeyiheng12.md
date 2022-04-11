---
layout: page
title: Lee Yi Heng's Project Portfolio Page
---

### Project: ClientConnect

ClientConnect is a desktop client address book application for insurance agents to manage and keep track of their clientele seamlessly and efficiently. Custom tailored to suit the needs of insurance agents, ClientConnect surpasses other similar applications by offering features such as grouping of clients by packages, keyboard shortcuts, a gorgeous GUI, and more.

Given below are my contributions to the project.

* **New Feature**: Added insurance packages as a separate entity, similar to how persons and their details can be stored in the application ([\#69](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/69))
    * What it includes: 
      * The ability to store insurance packages into the application
      * The ability to add, edit, and delete insurance packages
      * The ability to view insurance packages in a new window
    * Justification: 
      * This allows for the application to be more than just one that stores contact information. 
      * This allows for links between people and packages: for instance, if the user adds a person and puts down the name of an insurance package, if that package is not in the application yet, it will be automatically added
    * Highlights:
      * This enhancement required a good understanding of the architecture of the initial AddressBook, as the implementation of the storage and features of the packages in the app is very similar to that of the persons.
  
* **New Feature**: Added CSV file storage support ([\#32](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/32), [\#35](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/35))
  * What it includes:
    * The ability to export clients' details from the application to a CSV file
    * The ability to import clients' details from an exported CSV file into the application
  * Justification:
    * This allows for sharing of contacts between different users, and allows for storing of different copies of contacts, if necessary.

* **New Feature**: Added the storage of past user commands ([\#37](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/37))
  * What it includes:
    * The ability to cycle through previous commands entered by the user, using the up and down arrow keys
  * Credits:
    * Inspired from a similar functionality on the command line
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=leeyiheng12&breakdown=true)

* **Project management**:
    * Aided in the assignment of features and issues throughout the project.

* **Enhancements to existing features**:
    * Enhanced the current `ModelManager` to include the logic and storage of insurance packages.

* **Documentation**:
    * User Guide:
        * Updated User Guide to include more screenshots of ClientConnect, in the initial drafts of the User Guide
        * Added documentation for the features I added, such as adding, editing, deleting, and viewing packages,
        and importing from and exporting to CSV

    * Developer Guide:
        * Added implementation details, which included UML diagrams, for the feature regarding importing from and exporting to CSV
      
  * README.md:
      * Added a basic README for ClientConnect

* **Community**:
    * Reviewed PRs of other team members, some of which concerned important/major features 
      (e.g. [here](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/38))
    * Reported bugs for another project (see [this](https://github.com/AY2122S2-CS2103-F09-2/tp/issues/190) for an example) 
    * Closed over 30 issues on Github Issues (such as [this](https://github.com/AY2122S2-CS2103-W17-3/tp/issues/96)) with comments provided
