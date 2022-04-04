---
layout: page
title: Daniel's Project Portfolio Page
---
### Project: ContacX

ContaX is for professionals who have many clients, current and prospective, who may possibly work with sensitive personal information and documents related to each of those clients. They have busy schedules with many appointments & meetings, and must be able to find available time slots in their schedules quickly.

ContaX will allow for efficient management of a large list of contacts, allowing the mental workload of remembering people and related scheduled tasks to be offloaded. It will also provide an efficient way of efficiently organizing resources, while having minimal downtime with seamless transition from existing solutions.

Given below are my contributions to the project.

* **New Feature**: Added the import and export features for `.csv`
    * What it does: Allows the user to import contacts from a Microsoft Excel compatible format, as well as export it back into the same format. Import CSV allows for customising of column numbers for each field.
    * Justification: As our target audience is professionals, it is very common that they will have contacts in Excel in some form. By designing an Import CSV command with flexibility of column numbers, professionals can easily migrate their existing contacts with minimal modifications.
* **New Feature**: Internal parser to use a markdown-like syntax to style text components
    * What it does: Allows for styled command execution results, through Bold/Italic/Monospaced text, to make error messages nicer and easier to read. e.g. a String of `**Bold**` will display as **Bold**, similar to in Markdown. 
    * Justification: In order to provide feedback to users in a more organised manner, especially since error messages of a command can contain many components.

* **Code contributed**:
    * 2000+ LoC contributed to the project
    * Functionality, documentation and tests for both `Import CSV` and `Export CSV` functions
      * Includes components in `Storage`, `Logic`, `Model` etc.
    * Functionality, documentation and tests of Markdown-like test styler
      * Includes components mainly in `UI`
      
* **Project management**:
    * Code reviews, workflow establishment
    * Creation of releases, ensuring passing CI

* **Enhancements to existing features**:
    * Modified the constraints for `Phone` to allow country codes within the numbers. It is common that other contact management apps use this, and can cause problems when importing, so we decided to allow it.
    * Modified all existing error messages to have a specific format. i.e. `monospaced` for the command words, *italics* for parameters, etc.
    * Modified existing `json` import to be more forgiving, i.e. skipping any invalid entries instead of completely wiping records if there exists an invalid entry. 

* **Documentation**:
    * User Guide:
        * Added documentation for import/export CSV related functionalities
          * `importcsv`
          * `exportcsv`
    * Developer Guide:
        * Added sequence diagram and explanation of how the `Import CSV` and `Export CSV` functionalities work
        * Added sequence diagram and explanation of how the Markdown-like text styler works

* **Community**:
    * `to be added soon`

* **Tools**:
    * `to be added soon`
