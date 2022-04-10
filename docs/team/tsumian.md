---
layout: page
title: Thia Su Mian's Project Portfolio Page
---

### Project: WoofAreYou

WoofAreYou is a desktop administrative manager used by pet daycare owners to aid their day-to-day administrative duties.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Filter**: Added the ability to filter out pets by desired field.
  * What it does: Allows pet daycare owners to filter out pets from the pet list. They can choose to filter by a number
    of different fields such as date, appointment, owner's name and tags of the pets.
  * Justification: This feature improves the efficiency of executing commands in WoofAreYou. If daycare owners have a
    huge list of pets, this feature helps them narrow down their search a lot so that they can perform other administrative
    commands efficiently with a smaller pet list.
  * Highlights: This enhancement is dependent on both existing commands and commands to be added in the future. It required
    in depth analysis of the different fields and commands implemented to adapt this feature appropriately to
    produce the desired result. A lot of thoughts were put into making this feature extendable to accommodate
    potential features of WoofAreYou as we tweak it to suit the different operations of daycare owners.
  * Credits: Inspiration of the implementation was drawn from original `find` command from AB3 and Java's `LocalDate`
    library was used to aid in the implementation.

* **Labels In GUI for diet and Appointment**: Added visual cues for diet and appointments of pets in GUI.
  * What it does: Allows pet daycare owners to identify the special requests of different pets. Appointment labels will
    change color to provide an indication of their urgency based on their dates. Diet labels will appear if the pet has
    any dietary requirements.
  * Justification: This feature acts as a reminder for daycare owners when taking care of pets with special needs and
    requests. WoofAreYou contains a lot of information about pets and this feature emphasises on information that may be
    more important. This information may be urgent and could affect the safety of the pets.
  * Highlights: This enhancement affects the existing features of the GUI and is dependent on existing commands. It needed
    strategic tweaking of the layout of the existing pet card to bring out the desired emphasis. At the same time other
    exisitng information should not be less visible in any way.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tsumian&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.3.1` on GitHub

* **Enhancements to existing features**:
  * Wrote additional tests for exisitng features and new features to increase coverage from 67% to 70%(Pull request [#91](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/91))

* **Documentation**:
  * User Guide:
    * Added documentation for `filter`: [#91](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/91))
    * Did a complete cosmetic tweak to existing documentation for cohesiveness: [#94](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/98/files)
  * Developer Guide:
    * Add documentation for the feature `filter`: [#81](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/81/files)
    * Added implementation details of the `User Stories`: [#13](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/13/files)
    * Added test cases in Appendix and Effort section: [#189](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/189/files)

* **Contribution to team-based tasks**
  * Set up GitHub team organisation/ repository
  * Updating user guide to enhance user-friendliness

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#103](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/103/files/790e59e0ee3ed4c86af623de7412a3141e70625e)
    , [#18](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/80)
  * Reported bugs and suggestions for other teams in the class (example: [1](https://github.com/AY2122S2-CS2103-W16-3/tp/issues/144))
    , [2](https://github.com/AY2122S2-CS2103-W16-3/tp/issues/153), [3](https://github.com/AY2122S2-CS2103-W16-3/tp/issues/167)

* **Tools**:
  * Integrated CodeCov to the team repo
