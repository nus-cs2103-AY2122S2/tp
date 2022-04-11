---
layout: page
title: Sumanth's Project Portfolio Page
---

## Project: TAilor

TAilor is a desktop app for managing contacts powered by Java. The user interacts with it using a CLI-like interface, 
and it has a GUI created with JavaFX. TAilor tries to simplify the life of teaching assistants by either automating
mundane administrative tasks, or by providing efficient ways to accomplish those tasks.

### Given below are my contributions to the project:

- **Code contributed:** [RepoSense Link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=s7manth&breakdown=true)

* **Enhancements implemented**:
    * **Feature**: Implementing mailing commands [#84](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/84),
      [#102](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/102) <br>
      Implemented mailing functionality that enables users to launch the default mail client on the system 
      from TAilor. Since, a major part of teaching assistants' job is to communicate with students, this feature 
      focuses on that by making it easier to mail students by triggering commands. To achieve this functionality,
      three sub-features have been implemented.

        * **Sub-Feature**: `mail-index` command
            * What it does:
            Allows the user to launch mail application to mail individual student from the contact list based
            on the index displayed by TAilor.
            
            * Time spent on this (estimate):
            100mins

        * **Sub-Feature**: `mail-x` command
            * What it does:
            Allows the user to launch mail application to mail multiple students at the same time. This command
            takes

            * Time spent on this (estimate):
            150mins

        * **Sub-Feature**: `mail-all` command
            * What it does:
            Allows the user to launch mail application to mail everyone in the contact list. This caters for
            bulk emailing, PSA etc.

            * Time spent on this (estimate):
            60mins
    
    * **Feature**: Extending `help` command's functionality [#108](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/108)
        * What it does:
        Implemented the extension of `help` command that allows the user to understand the usage of specific commands. 
        In addition to the traditional `help` command invoking a popup window with a link to the user guide, 
        the extended `help` command can be used to refer to the syntax and expected usage of the other commands.
      
        * Justification:
        This feature allows the user to look up how each command works without constantly referring to the user
        guide and hence saves time. It was inspired from the `--help` flag that is present in most of the modern day CLI 
        tools.
          
        * Time spent on this (estimate):
        100mins
        
    * **Utility Classes**: `StringBuilderUtil` and `MailUtil`
        * What it does:
        Implemented utility classes that would allow for developers in the future to make use of convenient bulk string 
        concatenation and mailing utility to launch external mail clients on systems.

        * Justification:
        `StringBuilderUtil` allows for bulk appending of strings to produce formatted output in an easier and faster
        manner. `MailUtil` separates the concern of interacting with external mail clients from Java Applications.

        * Time spent on this (estimate):
        120mins

* **Testing**:
  * Implemented test cases for `help`, `mail-all`, `mail-index`, and `mail-x` commands
    [#128](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/128)

* **Enhancements to existing features**:
    * Performed code refactoring aiding the migration from AddressBook to TAilor 
      [#66](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/66),
      [#69](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/69)
    * Bugfixes : Pull Requests [#221](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/221), 
      [#225](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/225)
  

* **Documentation**:
    * User Guide (UG):
        * Reviewed UG for v1.1, added necessary details and took care of the formatting.
        * Updated UG for specific parts modified/implemented/extended by me. In particular:
            * Added usage instructions for `help` and mailing commands. 
            [#107](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/107),
            [#118](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/118),
            [#133](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/133),
            * Added test cases for manual testing for those features. [#240](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/240)
        * Included cautionary notes and assumptions regarding setting default mail clients
          on systems to avoid unexpected functionality. [#222](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/222)
    * Developer Guide (DG):
        * Changed some "Address Book" into "Contact List" to better represent the project.
        * Added user stories to the DG during v1.1 [#56](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/56)
        * Updated DG for parts modified/implemented/extended by me. In particular : 
            * Added implementation details for the mailing features and `help` command extension. 
              [#135](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/135),
              [#240](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/240)
            * Highlighted the execution of the various commands through UML sequence diagrams.

* **Contribution to team-based tasks**:
    * Suggested and reinforced the git workflow for the team to follow.
    * Transferred user stories into Github Issues to track and maintain on the team repo.

* **Project management**:
    * Provided notes and suggestions as to why certain design considerations are to be made while implementing
      features.

* **Community**:
    * PRs reviewed : [#75](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/75), 
      [#88](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/88),
      [#94](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/94),
      [#95](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/95),
      [#98](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/98),
      [#100](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/100),
      [#103](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/103),
      [#114](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/114),
      [#110](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/110),
      [#119](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/119),
      [#123](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/123),
      [#114](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/114),
      [#124](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/124),
      [#136](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/136),
      [#137](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/137),
      [#217](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/217)
    * Contributed to team discussions in the meetings.

