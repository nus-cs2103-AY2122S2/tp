---
layout: page
title: Edward Alvin's Project Portfolio Page
---

### Project: InternBuddy

InternBuddy is a desktop app that helps undergraduate students keep track of the companies they’ve applied to for internships as well as any events you’ve arranged with them. It is optimized for CLI users who prefer to type commands for efficiency.

InternBuddy is specialised for fast-typing users which is the main feature of the application. Efficiency and ease of use are two of InternBuddy’s principle when designing the application. It provides a lot of functionalities catered specifically for those looking for internships which come from a real conducted survey.

Given below are my contributions to the project.

* **New Feature**:

* **Code contributed**: I coded almost 1.5k LoC (lines of code) which can be further seen in [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=theprevailingone).

With every feature that I have implemented, I made sure to include the proper test to improve future extendability.

* **Enhancements to existing features**:
  * Allow sorting of entry lists to persist across sessions.
  * Find and List Commands will have their display sorted if previously sorted by a Sort Command.
  * Improve and adjust the error messages/ usage messages given by the commands.

* **New Command Features**:
  * Create the General Sort Command.
    * Extending the General List Command, it will display the list of entries in a sorted manner.
    * The Command accepts the paramater of search type and ordering. Search type dictates whether the entries displayed are archived, unarchived, or both. Ordering dictates the ordering of the entries, either ascending or descending.
  * Create SortCommandParser to parse the Sort Command.
    * A universal sort command parser that can be used to parse into all the sort commands.
    * Extending ListCommandParser, it will parse the Sort Command (which inherits some of the List Command behaviour) and return the appropriate sort command.
  * Create OrderingUtil and Ordering:
    * OrderingUtil is a utility class that helps with converting a Comparator to its reverse or the other way depending on the Ordering.
    * Ordering is an enum that contains the two possible values of the ordering (ascending and descending).
  * Create Sort Company Command
    * A feature that allows the user to sort the list of entries by company name.
  * Create Sort Person Command
    * A feature that allows the user to sort the list of entries by person name.
  * Create Sort Event Command
    * A feature that allows the user to sort the list of entries by event name.

* **Team-based Tasks Contributions and Project Management**:
  * Suggested the idea of Event to handle events relating to internships.
  * Suggested the linking of Person and Event to Company.
  * Help update the UI screenshots for UG and index.md
  * Main handler of the User Guide.

* **Review/mentoring contributions and Project Management**:
  * Created 15 Pull Requests
  * Helped reviewed 8 Pull Requests
  * Provided 16 non trivial comments (as seen [here](https://nus-cs2103-ay2122s2.github.io/dashboards/contents/tp-comments.html))

