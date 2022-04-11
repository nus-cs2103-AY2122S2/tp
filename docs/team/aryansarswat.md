---
layout: page
title: Aryan Sarswat's Project Portfolio Page
---

### Project: Amigos

Amigos is a desktop application to help tech-savvy university students manage their friendships by helping them to keep track of important details. It is optimized for use via a Command Line interface while still having the benefits of a Graphical User Interface (GUI). The user enters commands through the GUI which is built using JavaFX. It is written in Java and has over 20kLoC.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=aryansarswat&breakdown=true)

### Summary of Contributions

Given below are my contributions to the project.

1. **New Feature: Tabs**
    * What it does: It enables to user to view different features in Amigos, i.e. there exist different tabs for each feature (Friends Tab, Events Tab, ShowInsights tab)
    * Justification: This allows us to view each aspect of Amigos without the clutter which would arise if we fit all our features into one window.
    * Highlights:
      * Automatically switches tabs based on the command entered for ease of user. Example if `deleteevent 5` is called and the user is in the friends tab, the tab will be automatically switched to the events tab.

2. **New Feature: EditEvents** Added the ability to edit events which are inside Amigos.
    * What it does: Allows the user to change any one of the following attributes in events; event name, event date, event description, friends related to events
    * Justification: It allows the user to edit details of events given that they change, this is alot more efficient than having to delete and event and add a new events with the corrected attributes
    * Highlights:
        * To optimize for command-line convenience, instead of just replacing the entire friends list with a new friends list (like in the implementation of tags), the `af/` and the `rf/` allow for adding and removing friends from an event. This allows for a shorter `edtievent` command.

3. **New Feature: ShowEvents**
    * What it does: Allows the user to view all the events in Amigos, specifically it only shows upcoming events sorted by their dates.
    * Justification: This is an essential feature for the user as they might need see which events are upcoming or even try to plan their day depending on whether they have an upcoming event.
    * Highlights:
      * In the scenario that the user wants to check past events we optimize the command-line interface such that if the flag `-a` is passed it will show **every** event in Amigos even if they have passed.
4. **New Feature: EventCards for GUI**
    * What it does: This is the card which represents events in the GUI.
    * Justification: We designed as such as we wanted to present all the necessary information in a concise yet informative fashion.

5. **Enhancement to existing features**
    * Abstraction of `FriendName`, `LogName`, `EventName` into a common `Name` class, this reduces the amount of repeated code (Pullrequests [\#104](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/104), [\#105](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/105))

* **Contribution to team-based-tasks**
  * Maintained issue tracker for milestone v1.3b

* **Documentation**:
    * User Guide:
        * Added documentation for the features `edtifriend`, `showfriends`, `showfriend` and `showevents` [\#58](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/58)
    * Developer Guide:
        * Added User Stories, Glossary and Non-functional requirements
        * Added Use cases for `addevent`, `edtievent` `showfriends`, `showfriend` and `showevents` [\#44](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/44), [\#57](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/57)
        * Added implementation details for `editevent`, `showevents` and Tab management [\#143](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/143)
    * Created a GUI Test documentation
      * Contains all the various test performed manually on the GUI [\#169](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/169).

* **Community**:
    * PR reviewed (with non-trivial review comments): [#166](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/166), [#136](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/136)

* **Tools**:
  * Wrote python script to generate upwards of 500 entries for names, events to stress test the application

