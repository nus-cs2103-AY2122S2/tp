---
layout: page
title: Wu HanHui's Project Portfolio Page
---

### Project: TrackBeau

TrackBeau is a **desktop app made for beauty salons to aid them in managing customers' profile, bookings and services.
It is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User
Interface (GUI). If you can type fast, TrackBeau can help you manage your customers, bookings and services faster than
traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to view schedule of a selected week. (PR [#131](https://github.com/AY2122S2-CS2103-F11-3/tp/pull/131), [#137](https://github.com/AY2122S2-CS2103-F11-3/tp/pull/137))
  * What it does: allows the user to view all booking of a week organised by the day of the week.
  * Justification: This feature allows the user to view bookings in an organised manner which help better manage the new bookings and prepare manpower ahead.
  * Implemented 3 commands to navigate the schedule view, `schedule`, `scheduleNext` and `schedulePrevious`.
  * `schedule` display the week which the date fall under.
  * `scheduleNext` and `schedulePrevious` goes back and forth from last viewed week.
  * Created the schedule panel used to display the week view of bookings.
  
* **New features**: Added the ability to delete multiple customer in single delete command. (PR [#73](https://github.com/AY2122S2-CS2103-F11-3/tp/pull/73))
  * What it does: allows the user to specify multiple customer indexes and delete those customers.
  * Justification: There might be multiple customers profiles that need to be removed and deleting them one at a time is slow. The product should provide a convenient way to batch delete.
  * Additional checks added to prevent duplicated indexes in the input.
  
* **Testing**: 
  * Included JUnit test cases for schedule and booking commands. (PR [#212](https://github.com/AY2122S2-CS2103-F11-3/tp/pull/212))
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=hanhuiice&tabRepo=AY2122S2-CS2103-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Documentation**: 
  * User Guide:
    * Added documentation for the schedule management - Section 4.5(PR [#108](https://github.com/AY2122S2-CS2103-F11-3/tp/pull/108))
    * Fix minor mistakes in documentation
  * Developer Guide:
    * Added implementation details for deleting multiple customers and its activity diagram
    * Added implementation detail for schedule commands including activity diagram and sequence diagram
    * Added user stories
    
* **Community**:
  * Helped to review PRs for team
  * Report bugs and functional flows for other team

