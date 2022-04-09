---
layout: page
title: Bryan's Project Portfolio Page
---
# Project Portfolio Page for Bryan

### Project: ModuleMateFinder

ModuleMateFinder is a desktop address-book-like application used to keep track of your friends' contacts, as well as the modules they are taking. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add modules to a contact.
   - **What it does:** Allows the user to add `Module` to a `Person`
   - **Justification:** This feature improves the product significantly because the addition of modules to a contact is the initial touch-point in allowing all future operations involving modules. Finding module-mates, the main value proposition of the product, revolves around the management and querying of modules taken by one's contact list. Thus, the addition of modules to a contact is the first step in making this possible in the first place. This is a core feature of the product.
   - **Highlights:** This feature required an understanding of how to add a new command, along with its own parser. It also involved understanding of a `Person`'s structure and how to add new fields to it. Considerations also had to be made on the architectural design of Module; whether it should be a standalone `List of Modules` that each `Person` references, or instead a field that each `Person` has.
   - **Credits:** The way `Module` is stored and associated with a `Person` is adapted from `Tags` in AB3. However, the adding mechanism is designed and implemented differently.
   

* **New Feature**: Added a hyistor command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]()
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
