---
layout: page
title: Kerby Soh's Project Portfolio Page
---

### Project: Tinner

Tinner (Anagram of Intern) is a desktop app for managing internship applications, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Tinner allows you to easily sort through and retrieve the relevant information faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Revamped the `find` command to search for specific companies and/or specific roles.
  * What it does: Allows the user to find specific companies and/or roles upon supplying relevant role and/or company keywords. Previously, users could only use this command to search for a specific company.
  * Justification: This feature is necessary for the user to track and manage specific internship applications, allowing users to achieve more flexibility.
  * Highlights: This enhancement required a major revamp to filtering predicates, and it was challenging as there were many edge cases to consider.

* **New Feature**: Added the `favourite`, `unfavourite` and `listFavourite` commands to favourite and unfavourite specific companies, and to view favourited companies.
  * What it does: allows the user to mark specific companies as their favourite, following which these companies will have a star indicated beside their name in the Graphical User Interface (GUI). Then, users can unfavourite these companies and view all favourited companies in a separate view.
  * Justification: This feature is necessary for users to highlight specific companies that they are more interested in, and view these companies separately from the rest.
  * Highlights: This feature required major changes to components in the `Storage` class.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=kerbysoh&breakdown=true)

* **Project management**:
  * Set up project team organisation, repository and the project website.

* **Enhancements to existing features**:
  * Adapted code from Address Book 3 to suit the context of Tinner (Pull requests [\#43](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/43), [\#47](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/47))
  * Implemented a new RoleManager class to handle role related operations (Pull request [\#49](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/49))
  * Updated the GUI to display roles and role tags (Pull requests [\#69](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/69), [\#72](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/72))
  * Wrote additional tests for existing features to increase coverage from 66% to 69% (Pull requests [\#274](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/274), [\#282](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/282), [\#286](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/286))

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `find` (Pull request [\#127](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/127))
    * Added documentation for the features `favourite`, `unfavourite` and `listFavourite` (Pull request [\#137](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/137))
    * Added Command Restrictions and Frequently Asked Questions (FAQs) sections: (Pull request [\#197](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/197))
  * Developer Guide:
    * Added documentation for the Model component (Pull request [\#153](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/153))
    * Added implementation details of the `find` feature (Pull request [\#155](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/155))
    * Added Acknowledgements, Setting Up and DevOps sections (Pull request [\#205](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/205))

* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull requests [\#142](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/142), [\#196](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/196), [\#200](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/200))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/kerbysoh/ped/issues/15), [2](https://github.com/kerbysoh/ped/issues/13), [3](https://github.com/kerbysoh/ped/issues/9))
  * Participated in internal PE-D with other teams in tutorial group G11 ([Issues](https://github.com/AY2122S2-CS2103T-T17-2/tp/issues/240))
  


