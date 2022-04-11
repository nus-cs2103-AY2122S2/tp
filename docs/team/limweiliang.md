---
layout: page
title: Lim Wei Liang's Project Portfolio Page
---

### Project: Amigos

Amigos is a desktop application that helps tech-savvy university students manage their friendships by helping them track important details. It is optimized for use via a CLI and has a GUI created with JavaFX. It is written in Java and has 20 kLoC.

Given below are my contributions to the project:

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=limweiliang&breakdown=true)

* **New feature**: Creating and Deleting `Event` objects in Amigos.
  * What it does: Users will be able to create and delete new `Event` objects in Amigos, which encapsulates an event name, date-time, description, and the names of friends tied to that event.
  * Justification: Users will now be able to keep track of their social events, both past and present. This is an important friendship detail to keep track of.
  * Highlights:
    * Created numerous support classes in `Model` and `Storage` in order to represent and store the new `Event` class respectively
    * Had to handle and maintain the newly created relationship between the `Person` and `Event` classes, since `Event` needs to keep track of the names of friends involved. This required deep consideration of the potential designs.
    * Created support classes to help with testing, such as `EventUtil`.

* **New feature**: Searching for specific `Event` objects in Amigos.
  * What it does: Users will be able to search for specific events using some or all of the following criteria: the event name, names of friends attending, and date range.
  * Justification: This is an important feature as it allows users to easily search for the specific events that they are interested in viewing.

* **Enhancement to existing features**:
  * Updated individual friend page to also display upcoming events for that specific friend. 

* **Documentation**:
  * User Guide:
    * Added documentation for the commands `addevent`, `deleteevent`, and `findevent`
  * Developer Guide:
    * Added implementation details and diagrams for `findevent` and representing the `Event`-`Person` dependency
    * Updated `Model` section and diagram

* **Contributions to team-based tasks**:
  * Managed the milestone and release for v1.2b
  * Overall I/C for `Model` component and documentation, reviewed PRs that modified those components (Examples: [\#74](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/74), [\#104](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/104))
