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
   

* **New Feature**: Added the ability to switch between two AddressBooks.
  - **What it does:** Allows the user to switch between two AddressBooks (Default and Archived) via the `switch` command. Switching to an AddressBook not only allows the user to view data in that particular AddressBook, but "be" in it, i.e. all other operations and commands performed by the user such as `add`, `edit` and `delete` etc will be performed with regard to the data in the newly switched AddressBook.
  - **Justification:** This feature improves the product by allowing the user to organise their contacts better by hiding and stashing away contacts that they will not be interacting with for a period of time, but would not want to delete as that contact's information might be needed in the future for other purposes. Examples of contacts that fall under this category include graduated contacts that the user definitely would not be group mates with, or even severely blacklisted contacts that the user wants to hide from their default AddressBook. The `switch` feature falls under a bigger feature set umbrella comprising `archive`, `unarchive` and `switch` that allows this user need to be met.
  - **Highlights:** This feature is part of a larger feature set allowing the user to `archive` and `unarchive` contacts, and `switch` between AddressBooks. It was relatively challenging and thought-provoking as it required a deep and proper analysis of the product's architectural design. I had to decide if the Model would hold only one reference to a single `AddressBook` and then swap out this single reference to the corresponding Default or Archived AddressBook (more OOP design), or let Model hold two `AddressBook` references `addressbook` and `archivedBook`. 
                     <br><br>These decisions were not trivial as the "more OOP" approaches often required even more complex implementations as we could not easily and directly access and load AddressBooks from the storage into the Model, and reflect these changes in the UI. Having only a single reference to `AddressBook` in the Model also presented difficulties in determining which AddressBook we were in, as by OOP encapsulation the Model does not know the presence of the alternate AddressBook. 
                     <br><br>The implementation was also non-trivial and required heavy modifications across the Logic, Model, Storage and UI aspects, in order to propagate changes through these different components. Even after adopting the "less OOP" design, I still faced the issues outlined in the preceding paragraph due to Logic, Model, Storage and UI aspects all being involved in the implementation of this feature.
  - **Credits:** Due to the gargantuan nature of this feature set from the design phase down to the actual implementation, my group mates Wei Jie and Wei Ming were kind to help me and expand upon my base implementation. They helped me with bug-fixes improving correctness of the features and enhancements of underlying code design to better fit OOP's decoupling of components principle.


* **New Feature**: Added the ability to archive and unarchive a contact.
  - **What it does:** Allows the user to archive a contact from their default AddressBook, and unarchive an archived contact from their archived AddressBook back into the default AddressBook.
  - **Justification:** This feature improves the product by allowing the user to organise their contacts better by hiding and stashing away contacts that they will not be interacting with for a period of time, but would not want to delete as that contact's information might be needed in the future for other purposes. Examples of contacts that fall under this category include graduated contacts that the user definitely would not be group mates with, or even severely blacklisted contacts that the user wants to hide from their default AddressBook. The `archive` and `unarchive` features falls under a bigger feature set umbrella comprising `archive`, `unarchive` and `switch` that allows this user need to be met.
  - **Highlights:** This feature is part of a larger feature set allowing the user to `archive` and `unarchive` contacts, and `switch` between AddressBooks. It was relatively challenging and thought-provoking as it required a deep and proper analysis of the product's architectural design. I had to decide if the Model would hold only one reference to a single `AddressBook` and then swap out this single reference to the corresponding Default or Archived AddressBook (more OOP design), or let Model hold two `AddressBook` references `addressbook` and `archivedBook`.
    <br><br>These decisions were not trivial as the "more OOP" approaches often required even more complex implementations as we could not easily and directly access and load AddressBooks from the storage into the Model, and reflect these changes in the UI. Having only a single reference to `AddressBook` in the Model also presented difficulties in determining which AddressBook we were in, as by OOP encapsulation the Model does not know the presence of the alternate AddressBook.
    <br><br>The implementation was also non-trivial and required heavy modifications across the Logic, Model, Storage and UI aspects, in order to propagate changes through these different components. Even after adopting the "less OOP" design, I still faced the issues outlined in the preceding paragraph due to Logic, Model, Storage and UI aspects all being involved in the implementation of this feature.
  - **Credits:** Due to the gargantuan nature of this feature set from the design phase down to the actual implementation, my group mates Wei Jie and Wei Ming were kind to help me and expand upon my base implementation. They helped me with bug-fixes improving correctness of the features and enhancements of underlying code design to better fit OOP's decoupling of components principle.



* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=fantablack&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)

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
