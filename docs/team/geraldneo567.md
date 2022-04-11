---
layout: page
title: Gerald Neo's Project Portfolio Page
---

### Project: UniBook

UniBook is a desktop app for students to manage their university contacts in smart organized manner, optimized for command-line interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New and enhanced features**:
    * Added Module functionality
      * Justification: As the UniBook is targeting University Students, it is necessary to have a representation for modules.
      * Created Module class consisting of a Module Name, Module Code, Groups and Key Events.
      * Created Key Event class with easy extendability
        * Current Key Event Types include Assignment Start/Due, Exam and Quiz.
        * More can easily be added if necessary.
      * Added Module List class that works with underlying UniBook model.
    * Enhanced list command functionality
      * Justification: UniBook has multiple view unlike AB3, so the original List Command was inadequate.
      * Modified the list command to enable switching views between Groups, People and Modules view of the UniBook.
      * Altered parsing of commands to work with varying number of optional inputs, along with testing.
      * Created view-specific commands that work differently based on the currently active view.
      * Increased ability for users to specify listing criteria and narrow down more entries in a finer fashion.
        * Ability to specify people by their Modules, Types or Groups
        * Ability to specify modules by their name, module code, or key event dates
        * Ability to specify groups by their name, module code or meeting dates
      * Improved error messages and gracefully handle more edge cases/erroneous input.
    * Storage functionality
      * Justification: The original AB3 storage has to be modified to support the newly added features like Groups.
      * Implemented a Jackson-friendly JSON Module class to enable saving of Module data. 
      * Added basic sample data to assist in storage tests and have a default-populated UniBook.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=geraldneo567&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `1.2.1` and `1.3`.
  * Helped check and ensure certain team deliverables were met on time.
  * Created JAR files for respective releases
  
* **Documentation**:
  * User Guide:
    * Section describing List Command
      * Added explanation, format and examples for the various listing commands.
    * Command Summary
      * Added list commands to the summary, including links to the examples.
  * Developer Guide:
    * Modified List Command sequence diagram to accurately reflect UniBook instead of AB3.
    * Added user stories and use cases relating to the List Command

* **Community**:
  * Actively reviewed PRs of teammates
    * Total number of PRs reviewed: 33
  * Opened issues for features relating to each milestone, along with bugs
    * Total number of issues opened: 11
  * Analyzed code and where possible, gave suggestions and remarks.
  * Was responsive in Telegram and open to helping teammates & receiving feedback.
