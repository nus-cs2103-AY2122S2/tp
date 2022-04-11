---
layout: page
title: Shafik's Project Portfolio Page
---

### Project: HustleBook

HustleBook is a desktop app specially catered towards financial advisors for **managing client details** and **scheduling meetings efficiently** without the need to lift their hands off the keyboard.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to schedule and cancel meetings with clients through `meet` command.
    * What it does: allows the user to schedule a meeting date and time with a client. Users can also cancel meetings with the client.
    * Justification: This feature improves the product significantly because the user has to schedule meetings with clients and more often than not, clients cancel meetings on them. 
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added `previous date met` field for clients.
* **New Feature**: Added `info` field for clients.
* **New Feature**: Added `scheduled meeting` field for clients.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=ad-nap&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)

* **Project management**:
    * Managed releases `v1.3` - `v1.4` (2 releases) on GitHub

* **Enhancements to existing features**:
    * Modified `add` and `edit` command to handle the new fields to allow users to add the relevant fields to track their clients better.
    * Handled `previous date met` and `info` fields to have default values when not specified when adding clients into HustleBook.
    * Changed the case-sensitivity of `Name` to case-insensitive for a more practical determination of duplicate clients in HustleBook.
    * Prevent the ability to schedule meetings if that meeting date and time clashes with current meetings.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `meet` and `flag` [\#118](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/118)
        * Added documentation for the new fields `previous date met`, `info` and `scheduled meeting`: [\#70](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/70)
    * Developer Guide:
        * Added implementation details of the new `info` and `prev date met` fields. [96\#](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/96)
        * Added implementation details of the `meet` feature. [\#tbc]()

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#50](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/50), [\#64](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/64), [\#65](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/65)
  , [\#71](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/71), [\#102](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/102), [\#117](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/117), [\#147](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/147), [\#157](https://github.com/AY2122S2-CS2103T-W15-2/tp/pull/157)
    * Reported bugs and suggestions for other teams in the class ([PE-D](https://github.com/AD-NAP/ped/issues))
