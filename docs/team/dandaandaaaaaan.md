---
layout: page
title: Daniel's Project Portfolio Page
---
### Project: ContacX

ContacX is for professionals who have many clients, current and prospective, who may possibly work with sensitive personal information and documents related to each of those clients. They have busy schedules with many appointments & meetings, and must be able to find available time slots in their schedules quickly.

ContacX will allow for efficient management of a large list of contacts, allowing the mental workload of remembering people and related scheduled tasks to be offloaded. It will also provide an efficient way of efficiently organizing resources, while having minimal downtime with seamless transition from existing solutions.

Given below are my contributions to the project.

* **New Feature**: Added the import and export features for `.csv`
    * What it does: Allows the user to import contacts from a Microsoft Excel compatible format, as well as export it back into the same format. Import CSV allows for customising of column numbers for each field.
    * Justification: As our target audience is professionals, it is very common that they will have contacts in Excel in some form. By designing an Import CSV command with flexibility of column numbers, professionals can easily migrate their existing contacts with minimal modifications.

* **Code contributed**:
    * Functionality, documentation and tests for both `Import CSV` and `Export CSV` functions
      * Includes components in `Storage`, `Logic`, `Model` etc.

* **Project management**:
    * Code reviews, workflow establishment
    * Creation of releases, ensuring passing CI

* **Enhancements to existing features**:
    * Modified the constraints for `Phone` to allow country codes within the numbers. It is common that other contact management apps use this, and can cause problems when importing, so we decided to allow it.

* **Documentation**:
    * User Guide:
        * Added documentation for import/export CSV related functionalities
          * `importcsv`
          * `exportcsv`
    * Developer Guide:
        * Added sequence diagram and explaination of how the `Import CSV` and `Export CSV` functionalities work

* **Community**:
    * `to be added soon`

* **Tools**:
    * `to be added soon`
