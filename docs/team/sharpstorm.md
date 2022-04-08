---
layout: page
title: Zong Han's Project Portfolio Page
---

### Project: ContaX

ContaX is an integrated solution designed for busy professionals who work with many clients and frequently interact with other people. It assists those who have packed schedules in efficiently scheduling appointments, while also being able to respond to volatile changing schedules.

ContaX allows efficient management of a large list of contacts together with a schedule, providing an integrated solution for tracking work-related information. Built with efficiency in mind, ContaX promises a seamless transition from existing solutions for minimal downtime.

Given below are my contributions to the project.

* **New Feature**: Added the ability to create and manage Appointments in a Schedule
  * *What it does*: Allows users to create, view, edit and delete Appointments.
  * *Justification*: Since the target user profile focuses on busy professionals, this is a key feature that the entire application is built around, allowing users to offload the management of their busy schedules to ContaX.
  * *Highlights* The Schedule is designed to be sorted chronologically, and features a highly defensive design that is further detailed in the Developer Guide. This feature necessitated adding code across all 4 major components, and also involved adding a significant amount of test code complimenting the entire system.

* **New Feature**: Added the ability to find Appointments within a period
  * *What it does*: Allows users to filter appointments within a certain date range.
  * *Justification*: The ability to filter Appointments vastly improves the usability of the application when there are a lot of them in the schedule, allowing the user to quickly narrow down to a smaller subset of Appointments that he/she is interested in.

* **New Feature**: Added the ability to find available slots in the Schedule between dates
  * *What it does*: Allows users to quickly find all available slots in the Schedule within a certain date range.
  * *Justification*: Having this feature greatly simplifies the process of scheduling new Appointments, and shows the user all available options at a glance, allowing him/her to make an informed decision when arranging new Appointments.
  * *Highlights*: This feature necessitated a huge architectural design change within the Appointment models to implement the interleaved display of Appointments and the available slots onto the same list in the GUI, and involved a rewrite of the Appointments subsystem from iteration `1.2b`.

* **New Feature**: Added natural date and time parsing
  * This feature provides users with a more flexible method of inputting date and times, making the application more intuitive for both novice and experienced users.
  
* **Enhancements to existing features**:
  * Added basic UI result display mode switching (PR [#136](https://github.com/AY2122S2-CS2103-W17-1/tp/pull/136), [#137](https://github.com/AY2122S2-CS2103-W17-1/tp/pull/137))
  * Added UI List Row Recycling (PR [#233](https://github.com/AY2122S2-CS2103-W17-1/tp/pull/233))
  * Update Clear command to also clear appointments (PR [#252](https://github.com/AY2122S2-CS2103-W17-1/tp/pull/252))

* **Code contributed**:
  * `12000+` LoC contributed to the project. See [Reposense](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=sharpstorm) for more details.
  * Built the backbone for the Appointments subsystem, including code for each of the `Storage`, `Ui`, `Logic` and `Model` components.
  * Restructured and Unified the UI result display implementation to allow different types of models to be displayed in the UI using a single system.
  * Test cases for the Appointments subsystem, as well as overall test case maintenance of the `model` package.

* **Project management**:
  * Served as the **overall project lead** in organizing meetings and working with the entire team to ensure that integration of components proceed smoothly.
  * Served as the **project schedule manager** to manage internal deadlines across all team members to ensure that the project milestone deadlines can be met.
  * Upkeep of code quality and coverage across all members.

* **Team Based Tasks**
  * Upkeep shared team components in the Developer and User Guides, including User Stories and Product Scope.
  * Assisted in the management of the team's issue tracker.

* **Documentation**:
  * User Guide:
    * Added documentation for all appointment related functionalities, including:
      * `addappt`, `editappt`, `deleteappt`, `listappt`, `apptbetween`, `freebetween`
    * Improved `addperson` and `editperson` to include parameter constraints
    * Added section for [Common Date and Time Formats](https://ay2122s2-cs2103-w17-1.github.io/tp/UserGuide.html#common-date-and-time-syntax) accepted by the application
    * Added section for [Global Application Input Constraints](https://ay2122s2-cs2103-w17-1.github.io/tp/UserGuide.html#global-input-constraints)
  * Developer Guide:
    * Added implementation and architectural design details of the `Appointment` models
    * Added implementation details of the `Appointment` filtering feature
    * Added implementation details for `Schedule` inflation and serialization
    * Added implementation and architectural design details for Date Time Input Parsing
    * Added implementation details for UI result display mode switching
    * Added use cases for Appointments

* **Community**:
  * Reviewed a significant amount of PRs within the team ([347 comments](https://nus-cs2103-ay2122s2.github.io/dashboards/contents/tp-comments.html) across [60+ PRs](https://github.com/AY2122S2-CS2103-W17-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Asharpstorm)).
  * Reported a total of [27 issues](https://github.com/sharpstorm/ped/issues) during the PE dry run.
