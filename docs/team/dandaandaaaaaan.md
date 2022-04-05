---
layout: page
title: Daniel's Project Portfolio Page
---
### Project: ContaX

ContaX is an integrated solution designed for busy professionals who work with many clients and frequently interact with other people. It assists those who have packed schedules in efficiently scheduling appointments, while also being able to respond to volatile changing schedules.

ContaX allows efficient management of a large list of contacts together with a schedule, providing an integrated solution for tracking work-related information. Built with efficiency in mind, ContaX promises a seamless transition from existing solutions for minimal downtime.

Given below are my contributions to the project.

* **New Feature**: Added the import and export features for `.csv`
    * What it does: Allows the user to import contacts from a Microsoft Excel compatible format, as well as export it back into the same format. Import CSV allows for customising of column numbers for each field.
    * Justification: As our target audience is professionals, it is very common that they will have contacts in Excel in some form. By designing an Import CSV command with flexibility of column numbers, professionals can easily migrate their existing contacts with minimal modifications.
* **New Feature**: Internal parser to style text components with a Markdown-like syntax
    * What it does: Allows for dynamically generated styled text, through Bold/Italic/Monospaced text, mainly used in command usage messages. e.g. a String of `**Bold**` will display as **Bold**, similar to in Markdown. 
    * Justification: Allows developers to easily create styled text as feedback to users in a familiar syntax. Some text components can get very long, especially command usage messages. Through the use of this parser, information can be presented to the users in a more organised manner, making it easier to read.

* **Code contributed**:
    * 2000+ LoC contributed to the project. See [Reposense](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=dandaandaaaaaan) for more details.
    * Functionality, documentation and tests for both `Import CSV` and `Export CSV` functions
      * Includes components in `Storage`, `Logic`, `Model` etc.
    * Functionality, documentation and tests of Markdown-like test styler
      * Includes components mainly in `UI`
      
* **Project management**:
    * Established the overall team workflow.
    * Ensuring code quality through reviews.
    * Creating and Managing the lifecycle of releases, including version number maintenance in code.

* **Enhancements to existing features**:
    * Modified the constraints for `Phone` to allow country codes within the numbers. It is common that other contact management apps use this, and can cause problems when importing, so we decided to allow it.
    * Modified all existing command usage messages to have a specific format. i.e. `monospaced` for the command words, *italics* for parameters, etc.
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
    * Provided [140+ comments](https://nus-cs2103-ay2122s2.github.io/dashboards/contents/tp-comments.html) in PRs and Issues
    * Reviewed [90+ PRs](https://github.com/AY2122S2-CS2103-W17-1/tp/pulls?q=is%3Apr++is%3Aclosed+reviewed-by%3Adandaandaaaaaan) within the team.
    * Reported a total of [10 issues](https://github.com/dandaandaaaaaan/ped/issues) during the PE dry run
