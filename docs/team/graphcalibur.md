---
layout: page
title: Graphcalibur's Project Portfolio Page
---

### Project: InternBuddy

InternBuddy is a desktop app that helps undergraduate students keep track of the companies they've
applied to for internships as well as any events you've arranged with them. It is optimized for 
CLI users who prefer to type commands for efficiency.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=graphcalibur&breakdown=true)

* **New Feature**: Added support for multiple kinds of Entries
  * What it does: AddressBook can now hold multiple types of entry lists, where each type of entry stores different attributes compared
  to the others
  * Justification: InternBuddy needed to support Person, Company, and Event lists. To do this without overly duplicating
  code, the program needed to be retooled to be easily extended to support multiple types of entries.
  * Highlights: This required creating a generic Entry class that all entry types would inherit from, as well as overhauling
  the UniquePersonList into a UniqueEntryList so that it can hold and perform operations on any type of Entry. It also
  involved refactoring much of the existing test code.

* **New Feature**: Added support for Companies
  * What it does: AddressBook now has a list of Companies and can perform Add and Edit commands on it.
  * Justification: InternBuddy needed to support a list of Companies as that was one of its key features.
  * Highlights: The existing commands and classes had to be extended to work on the list of Companies as well, which meant
  creating an AddCompany and EditCompany commands. Furthermore, it also needed to be enforced that all Persons/Events must
  refer to an existing Company with their companyName attribute. This involved a lot of overhead to update the Person and Event
  lists whenever a Company gets edited/deleted.

* **New Feature**: Added ability to archive entries
  * What it does: Entries can be archived, which "hides" them from being displayed in the entry lists without deleting
  them entirely. Archived entries can still be searched for but by default they are not shown unless specified.
  * Justification: This feature allows users to hide entries which, while no longer relevant, they may still want to keep
 either for posterity or just in case they need the information again.
  * Highlights: This feature required overhauling the List and Find commands as both commands needed to take in an additional
  parameter so that the user can choose whether to list/find all entries, only unarchived entries, or only archived entries.
  It also required adding an extra attribute to Entries and creating new predicates to apply to the EntryLists.

* **New Feature**: Added Date and Time validation for Date and Time attributes
  * What it does: Date and Time parameters for Adding/Editing entries must now be in the correct format
  * Justification: The program needs to ensure a consistent formatting standard for its Date and Time so that they can
  be displayed in a nicer format and compared with each other.
  * Highlights: This feature required using the LocalDate and LocalTime libraries. There was also a lot of string checking
  involved in order to support the `today` and `today X` format for Dates.

* **New Feature**: Added commands to apply to all displayed entries (for Archive, Delete, and Unarchive)
  * What it does: All displayed entries can be archived/deleted/unarchived with a single command
  * Justification: Deleting/archiving/unarchiving one by one can get very tedious
  * Highlights: This feature mainly involved applying the operation to each element in the list one-by-one until it was done.

* **New Feature**: Added Start Date and End Date parameters for FindEventCommand
  * What it does: FindEventCommand can now take in Start Date and End Date parameters and return events between the two
 dates
  * Justification: It makes it a lot easier to find events within a certain date
  * Highlights: This feature involved refactoring the FindEventCommand to take in these new parameters as well as
  editing the predicates to compare the Dates.

* **Team-Based Tasks**:
  * Posted demo screenshots in the documents
  * Organized Zoom meetings
  * Set up project repository and settings
  * Set up Codecov for the repository
  * Managed release v1.3.1 on GitHub
  * Set up and managed milestones v1.2, v1.3, and v1.4
  * Added many issues to the issue tracker and assigned them labels

* **Reviewing/Mentoring Contributions**
  * PRs reviewed: #17, #19, #20, #28, #32, #33, #34, #37, #41, #43, #44, #45, #47, #48, #74,#75, #81, #86, #89, #143, #148, #152, #155, #159
  * Helped teammates out in understanding the codebase

* **Enhancements to existing features**:
  * Generalized DeleteCommand to apply to any type of list
  * Extended AddressBook, ModelManager, and ParserUtil to support new features
  * Fixed bugs during v1.4

* **Documentation**:
  * User Guide:
    * Updated user guide to include v1.2 and some v1.3 features
    * Add section explaining the restrictions on each parameter type
    * Add section explaining Archive feature of InternBuddy
    * Streamlined wording and fixed errors throughout the guide to make it smoother to read
  * Developer Guide:
    * Added user stories
    * Added NFRs
    * Updated value proposition and target profile
    * Added section on design and implementation of Archive command
    * Updated Design section to match InternBuddy v1.4, including UML diagrams

* **Tests**:
  * Wrote tests for the following files:
    * `ListCompanyCommand`
    * `ListPersonCommand`
    * `ListEventCommand`
    * `ListCommandParser`
    * `Company`
    * `Date`
    * `Location`
    * `Time`
    * `ArchiveCommand`
    * `ArchiveAllCommand`
    * `UnarchiveCommand`
    * `UnarchiveAllCommand`
    * `ArchiveCommandParser`
    * `UnarchiveCommandParser`
    * `DeleteAllCommand`
  * Also contributed to tests for the following files:
    * `FindPersonCommandParser`
    * `FindEventCommandParser`
    * `FindCompanyCommandParser`
    * `AddressBookParser`
    * `EditPersonCommand`
    * `EditEventCommand`
    * `AddPersonCommand`
    * `AddEventCommand`
