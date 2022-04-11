---
layout: page
title: Shafik's Project Portfolio Page
---

### Project: HustleBook

HustleBook is a desktop app specially catered towards financial advisors for **managing client details** and **scheduling meetings efficiently** without the need to lift their hands off the keyboard.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to schedule and cancel meetings with clients through `meet` command.
    * What it does: Allows the user to schedule a meeting date and time with a client. Users can also cancel meetings with the client.
    * Justification: This feature improves the product significantly because the user has to schedule meetings with clients and more often than not, clients cancel meetings on them. 
    * Highlights: This enhancement is useful as other meeting details can be added through the `meet` command. As of now, it supports scheduling and cancelling meetings. Furthermore, there is also a prevention measure to ensure that no two meetings clash.

* **New Feature**: Added `previous date met` field for clients.
    * What it does: Allows the user to track the previous date they met up with their clients.
    * Justification: This feature improves the product significantly because the user would benefit by keeping a close bond with their clients. This ensures that they do not neglect any of their clients.
    * Highlights: This enhancement affects existing commands and commands to be added in the future as the `add` and `edit` command now has to support this new field. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and considerations of default values.

* **New Feature**: Added `info` field for clients.
    * What it does: Allows the user to track clients' information. 
    * Justification: This feature improves the product significantly because the user would need to keep track of their client's info such as their financial goals and interest. Since different client might have different types of information to track, all these can be tracked in `Info` field.
    * Highlights: This enhancement affects existing commands and commands to be added in the future as the `add` and `edit` command now has to support this new field. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and considerations of default values.

* **New Feature**: Added `scheduled meeting` field for clients.
  * What it does: Allows the user to keep track of meetings that they scheduled with their clients. 
  * Justification: This feature improves the product significantly because the user can now keep track of their meetings with each client.
  * Highlights: This enhancement goes together with the `meet` command to ensure that each client's meetings are tracked. 


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=ad-nap&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)

* **Project management**:
    * Managed releases `v1.3` - `v1.4` (2 releases) on GitHub

* **Enhancements to existing features**:
    * Modified `add` and `edit` command to handle the new fields to allow users to add the relevant fields to track their clients better.
    * Handled `previous date met` and `info` fields to have default values when not specified when adding clients into HustleBook.
    * Changed the case-sensitivity of `Name` to case-insensitive for a more practical determination of duplicate clients in HustleBook.
    * Prevent the ability to schedule meetings if that meeting date and time clashes with current meetings.
    * Wrote additional test cases to increase coverage.

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
