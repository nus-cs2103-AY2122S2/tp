---
layout: page
title: Albertsutz's Project Portfolio Page
---
### Project: InternBuddy

InternBuddy is a desktop app that helps undergraduate students keep track of the companies they've
applied to for internships as well as any events you've arranged with them. It is optimized for
CLI users who prefer to type commands for efficiency.

Efficiency and ease of use are two of InternBuddy's principle when
designing the application. It provides a lot of functionalities catered specifically for those looking for internships
which come from a real conducted survey.

Given below are my contributions to the project.

* **Code contributed**: I personally coded around 5k LoC(lines of code) which can be found in [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=albertsutz).
This includes `Documentation`, `Feature Code` (around >= 1.9k LoC), and `Unit Tests` (around >= 2.8k LoC)

With every feature that I have implemented, I made sure to include the proper test to improve future extendability.

* **Enhancements to existing features**:
  * Find Person Command and Find Person Command Parser
    * A feature to find people in InternBuddy by the combination of its name, companyName, and tags. This is an enhanced version of the initial command from ab3
      as it provides more attributes to be searched.
    * Created PersonContainsKeywordsPredicate - an all-in-one person predicate used in FindPersonCommand
  * List Person Command 
    * A feature to list all people in InternBuddy.
  * Add better colour coding to archived and tags to enhance InternBuddy UI
  * Make sure that all `Label` text wraps around and is not truncated.
* **New Command Features Implemented**:
  * Create Event Class
    * Event class has multiple additional attributes, in particular Date, Time, and Location. Thus, I contributed to code these additional 3 attributes.
      * Create Date Class - Date attribute of the Event entry
      * Create Time Class - Time attribute of the Event entry
      * Create Location Class - Location attribute of the Event entry
  * Find Event Command and Find Event Command Parser
    * A feature to filter events in InternBuddy by the combination of its name, companyName, date, time, location, and tags.
    * Created EventContainsKeywordsPredicate - an all-in-one event predicate used in FindEventCommand
  * Find Company Command and Find Company Command Parser
    * A feature to filter companies in InternBuddy by the combination of its name and tags.
    * Created CompanyContainsKeywordsPredicate - an all-in-one company predicate used in FindCompanyCommand
  * List Event Command
  * List Company Command
  * Add Event Command
    * As I have created the Event Command, I also contributed to the AddEventCommand to let users add Events.
  * Comprehensive Automated Tests:
    * `Add`, `Find`, and `Edit` command for `Event` and `Company` along with their `CommandParser`
* **Team-based Tasks Contributions and Project Management**:
  * Put forward the idea of creating an app that manage internship applications
  * Update GitHub Repository `README.md` and `Index.md` contents
  * Remove all occurrences of ab3 in User Guide to InternBuddy(in the initial contributions)
  * Found and implemented major hotfixes right before v1.3.0
* **Review/mentoring contributions and Project Management**
  * Created 23 Pull Requests and Provided over 21 non trivial comments which can be seen in this [reposense](https://nus-cs2103-ay2122s2.github.io/dashboards/contents/tp-comments.html)
  * Contributed by reviewing most team members' pull request (with 5+ non trivial reviews)
  * Managed Releases v1.2.0 and v1.3.0
* **Community**
  * Contributed to submitting issues to other team which can be found [here](https://github.com/albertsutz/ped)
    Notice that the issues submitted often contains multiple bugs in 1 issue.
* **Effort**
  * The new features that I have implemented took a lot of effort and are essential to the application. This is because my implementation deals with
    basic commands that can make or break the application. With each command come careful planning and comprehensive manual testing to make sure that
    the code does not break. Furthermore, as most of my implementation deals with new `Entry` type (`Event` and `Company`), they are not easily copy-pasted
    from any sources. 
  * These testing are made exactly to make sure that the tests were accurate and covered a lot of the functions. In particular, 
    from this testing, there were some medium level bugs found which would be detrimental to the application.
  * The added `Predicate`, in particular `CompanyContainsKeywordsPredicate`, `EventContainsKeywordsPredicate`, and `PersonContainsKeywordsPredicate`
    bundles multiple `Predicate` together. The design also make sure that all `find` commands are extendable when the `Entry`'s attribute are changed.
* **User Guide Contribution**
  * Initial contributions to User Guide
  * Update `Quick Start`
  * Created `Overview of InternBuddy`
  * Contributes in `Introduction`
  * Contributes in `Structure of InternBuddy`
  * Contributes in `liste`, `editc` commands
* **Developer Guide Contribution**
  * Added Implementation for `add` feature
    * This includes all three implementation of `add event`, `add company`, and `add person`.
  * Added Implementation for `find` feature
    * This includes all three implementation of `find event`, `find company`, and `find person`.
  * Added Manual testing for multiple features:
    * `add company`, `add person`, and `add event` manual tests
    * `find company`, `find person`, and `find event` manual tests
    * `edit company`, `edit person` and `edit event` manual tests
  * UML contributed:
    * Updated [StorageClassDiagram](../images/StorageClassDiagram.png)
    * Create [AddXYZSequenceDiagram](../images/AddXYZSequenceDiagram.png)
    * Create [AddXYZActivityDiagram](../images/AddXYZActivityDiagram.png)
    * Create [AddCompanySequenceDiagram1](../images/AddCompanySequenceDiagram1.png)
    * Create [AddEventSequenceDiagram1](../images/AddEventSequenceDiagram1.png)
    * Create [AddPersonSequenceDiagram1](../images/AddPersonSequenceDiagram1.png)
    * Create [FindXYZSequenceDiagram](../images/FindXYZSequenceDiagram.png)
    * Create [FindXYZActivityDiagram](../images/FindXYZActivityDiagram.png)
