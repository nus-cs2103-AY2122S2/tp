---
layout: page
title: Wei Ming's Project Portfolio Page
---
# Skeleton of the Project Portfolio Page for Wei Ming

### Project: AddressBook Level 3


- **Administrative**:


AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort persons by specified fields.
    * What it does: allows the user to sort all persons by specified fields.
    * Justification: This feature improves the product significantly because a user can order persons based on what their needs. e.g. sort by status to track people by their status.
    * Highlights: This enhancement affects existing attributes of persons to be added in future. It required an in-depth analysis of design alternatives.

* **New Feature**: Added delete modules command that allows the user to navigate to previous commands using up/down keys.
    * What it does: allows the user to delete modules by specifying the module name.
    * Justification: This feature allows user to remove specified modules for a specific person.

* **New Feature**: Added clear modules command that allows the user to easily clear all modules of a person.
    * Justification: Clear all modules taken by a person so user does not have to delete each module individually. 
    
* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `deletemodule`, `clearmodules` and `sort` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
