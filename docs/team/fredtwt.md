---
layout: page
title: Frederick Tang's Project Portfolio Page
---

### Project: NUSocials

NUSocials is a desktop address book application for university students who like to maintain a professional contact list. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to tag additional information to an existing contact entry. [(#35)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/35)
    * What it does: allows the user to tag additional information to an existing contact.
    * Justification: This feature improves the product significantly because a user can tag important information to their own contacts for future references.
    * Highlights: This enhancement affects the existing UI layout. It required an in-depth analysis of how the tagged information should be displayed alongside with their respective contacts.

* **New Feature**: Added the ability to add events to the address book. [(#64, ](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/64) [#73, ](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/73) [#82)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/82)
    * What it does: allows the user to add events and tag them to contacts that are participating.
    * Justification: This feature improves the product significantly because a user can keep track of upcoming events they have with their contacts.
    * Highlights: This enhancement affects the existing UI layout. It required an in-depth analysis of how the event information should be displayed alongside the contact list in the application.

* **New Feature**: Added the ability to cancel events and delete them from the address book. [(#76)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/76)
    * What it does: allows the user to cancel events and delete them from the address book.
    * Justification: This feature improves the product significantly because a user can remove unwanted events and reduce clutter in their event list.
    * Highlights: This enhancement requires an in-depth analysis of how the UI layout of the event list should be altered after an event has been deleted.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=fredtwt&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=fredtwt&tabRepo=AY2122S2-CS2103T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features:**
  * Removed the tag functionality from add command [(#33)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/33)
  * Created a tag command that can tag specific fields to existing contact entries
  * Enhanced the cancel event command to handle multiple cancellations at once
  * Contributed to the logic for `find` command to handle the following cases: [(#75, ](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/75) [#95)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/95)
    * basic particulars' prefixes can only be used once for each field
    * search inputs can take in multiple strings now  (i.e edu/computer science)

* **Test cases implemented:**
  * EventCommand [(#157)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/157)
  * EventCommandParser [(#161)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/161)
  * CancelEventCommand [(#158)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/158)
  * CancelEventCommandParser [(#161)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/161)
  * All the event field types [(#158)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/158)
  * JsonAdaptedEvent [(#161)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/161)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `event`, `tag`, `cancelevent`.
        * Did cosmetic tweaks on the documentation for existing features `list`, `add`, `delete`.
        * Did cosmetic tweaks on the command summary to reflect NUSocials command format.

    * Developer Guide:
      * Added user stories for the features `add`, `tag`, `event`, `cancelevent`. [(#87)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/87)
      * Added use cases for the features `add`, `tag`, `event`, `cancelevent`. [(#87)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/87)
      * Modified the `ModelClassDiagram` and `StorageClassDiagram`. [(#87)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/87)
      * Added `TagSequenceDiagram`, `CancelEventSequenceDiagram`, `EventSequenceDiagram`. [(#167)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/167)
      * Added the implementation design details for `tag`, `event`, `cancelevent`. [(#67, ](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/67) [#88, ](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/88) [#90)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/90)
      * Added manual testing instructions for `tag`, `event`, `cancelevent`. [(#171)](https://github.com/AY2122S2-CS2103T-W11-1/tp/pull/171)
      * Contributed to the NFRs and glossary.
