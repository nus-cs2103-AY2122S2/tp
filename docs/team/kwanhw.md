---
layout: page
title: Kwan Hao Wei's Project Portfolio Page
---

### Project: ContaX

ContaX is an integrated solution designed for busy professionals who work with many clients and frequently interact with other people. It assists those who have packed schedules in efficiently scheduling appointments, while also being able to respond to volatile changing schedules.

ContaX allows efficient management of a large list of contacts together with a schedule, providing an integrated solution for tracking work-related information. Built with efficiency in mind, ContaX promises a seamless transition from existing solutions for minimal downtime.

Given below are my contributions to the project.

* **New Feature**: Added ability to create and manage tags
    * What it does: Allows the user to create, view, edit and delete tags in the application
    * Justification: As part of the target user profile, this a key feature that allows the user to organise the contacts in the address book.

* **New Feature**: Added ability to find persons who contain a specified tag
  * What it does: Allows the user to manage tags in the address book.
  * Justification: This improves the usability of the application, as it provides more options for the user to search for contacts.

* **New Feature**: Tabbed User Interface
  * What it does: Allows the user to view persons, appointments and tags simultaneously using tabbed panels
  * Justification: This improves the usability of the application, as the user need can easily switch between tabs to search for information.

* **Code contributed**:
  * 3500+ LoC contributed to the project. See [Reposense](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=kwanhw).
  * Test cases for the tags subsystem

* **Project management**:
    * Review pull requests submitted by the team

* **Enhancements to existing features**:
    * Extraction of Tags into its own list for management
    * Abstraction of `Tag` by moving the name attribute as its own class
    * Enhancement of `Person` related commands to support cascading on tag modification

* **Documentation**:
    * User Guide:
        * Added documentation for all tag related functionalities, including:
          * `addtag`
          * `listtags`
          * `edittag`
          * `deletetag`
          * `findbytag`
    * Developer Guide:
        * Added section for tag management
        * Added section for tag editing
        * Added section for tag inflation and serialisation
        * Added use cases for persons
        * Added use cases for tags

* **Community**:
    * Reviewed PRs within the team ([90+ comments](https://nus-cs2103-ay2122s2.github.io/dashboards/contents/tp-comments.html) across [50+ PRs](https://github.com/AY2122S2-CS2103-W17-1/tp/pulls?q=is%3Apr++is%3Aclosed+reviewed-by%3Akwanhw))
    * Reviewed a total of [7 issues](https://github.com/KwanHW/ped/issues) during PE dry run
