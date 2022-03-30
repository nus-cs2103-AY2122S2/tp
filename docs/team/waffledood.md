---
layout: page
title: Haikal Yusuf's Project Portfolio Page
---

### Project: RealEstatePro

RealEstatePro is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to mark a client as a buyer or seller.
  * What it does: allows the user to indicate a client as a buyer or seller. The user does not have to explicitly input "buyer" or "seller" when creating a new client, RealEstatePro will be able to infer based on the client's details.
  * Justification: This feature improves the product significantly because a user will need to input lesser details.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands & had a lot of regressions in the main and test code, as this feature was initially refactored from the Tag feature.
  * Credits: this feature is completely original, with no reference materials.

* **New Feature**: Added a remind command that allows the user to indicate clients they wish to be reminded of.
  * What it does: Allows the client to set reminders for themselves to contact a client/clients.
  * Justification: This feature improves the product as the client will take note of which clients to follow up with, and will be constantly reminded when the app is active, or after they just launched the app.
  * Highlights: This feature involves the usage of Threads and was quite tricky to implement because improper handling or usage of Threads could result in performance issues, and the app not behaving properly (closing the app with the Thread still running did not fully close the app & forcing the app to shutdown via IntelliJ resulted in an exit code error).
  * Credits: this feature was implemented with the help of these online resources: [Java Timer](https://www.baeldung.com/java-timer-and-timertask)

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `v1.2` (1 release) on GitHub

* **Enhancements to existing features**:
  * Updated the UserType color scheme (Pull request [\#74](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/74))
  * Updated AddressBook to resemble RealEstatePro (Pull request [#58](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/58))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `help`, `add`, `list` and `edit` (Howard, Cindy & myself authored the document on Notion, Jiet helped uploade the User Guide on our behalf) [\#34](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/34)
  * Developer Guide:
    * Added implementation details of the `UserType` feature.
    * Added implementation details of the `remind` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#45](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/45), [\#47](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/47), [\#51](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/51), [\#52](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/52), [\#79](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/79)
  * Contributed to forum discussions: [\#23](https://github.com/nus-cs2103-AY2122S2/forum/issues/23), [\#86](https://github.com/nus-cs2103-AY2122S2/forum/issues/86), [\#121](https://github.com/nus-cs2103-AY2122S2/forum/issues/121), [\#139](https://github.com/nus-cs2103-AY2122S2/forum/issues/139), [\#169](https://github.com/nus-cs2103-AY2122S2/forum/issues/169), [\#181](https://github.com/nus-cs2103-AY2122S2/forum/issues/181), [\#186](https://github.com/nus-cs2103-AY2122S2/forum/issues/186), [\#187](https://github.com/nus-cs2103-AY2122S2/forum/issues/187), [\#235](https://github.com/nus-cs2103-AY2122S2/forum/issues/235)
  * Reported bugs and suggestions of the tP: [\#73](https://github.com/AY2122S2-CS2103-W16-4/tp/issues/73), [\#75](https://github.com/AY2122S2-CS2103-W16-4/tp/issues/75), [\#82](https://github.com/AY2122S2-CS2103-W16-4/tp/issues/82)

* **Tools**:
  * Integrated a third party library (ControlsFX) to the project ([\#42]())

