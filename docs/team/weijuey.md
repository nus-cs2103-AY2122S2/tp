---
layout: page
title: Yoong Wei Jue's Project Portfolio Page
---

### Project: d'Intérieur

d'Intérieur is a desktop app for interior designers to manage their contacts and projects. The designer interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 26 kLoC.

Interior designers can use d'Intèrieur to improve contact management and take on the needs of an ever-growing client base, so that they can focus on what matters most - delivering quality service for clients.

Given below are my contributions to the project.

* **New Feature**: Added notes feature
  * What it does: allows the user to add and delete notes to each contact, to be referenced in the future.
  * Justification: This feature is flexible in utility, allowing the user to save any information they deem important, thus enhancing the product.
  * Highlights: Implementing the feature required understanding how modification of internal data is done. Integrating a variably-sized UI element also required careful consideration of pros and cons of different solutions.
  * Credits: UI design decisions were discussed and finalised as a team.

* **New Feature**: Added detailed view feature
  * What it does: allows the user to view a contact in detail, which the app will switch to a more focused view.
  * Justification: This feature enables the user to do more focused work with one particular contact, especially because some information is only fully available to view by using this feature. It can allow adding as many new information to a contact as desired.
  * Highlights: The feature involved multiple components of the application and adding new interfaces to each of them to interact, thus it required a good understanding of their current interactions. Adding new capabilities to current commands also required understanding their existing behaviour.
  * Credits: Idea to communicate to MainWindow the panel view to show was first implemented by Ezekiel for his Images feature, and was adapted from his implementation. Final design of the detailed contact view was also refined by Ezekiel. Normal command execution path of new features were first implemented by team members.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=weijuey&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (5 releases) on GitHub
  * Added target user profile, value proposition and glossary to Developer Guide

* **Enhancements to existing features**:
  * Update existing commands to work in the new detailed view feature mentioned above, with simpler command formats.

* **Documentation**:
  * User Guide:
    * Added documentation for the new features `note` and `view` [\#28](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/28), [\#93](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/93)
    * Reorganised commands to sections and added table of contents for more flexible linking to sections. [\#183](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/183)
    * Added information to each existing commands on compatibility with new detailed view feature, and update command summary table to capture the information succinctly. [\#99](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/99)
  * Developer Guide:
    * Added implementation details and diagrams for the `note` and `view` features. [#72](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/72), [\#203](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/203)
    * Update existing class diagrams to match new architecture. [\#203](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/203)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#44](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/44), [\#74](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/74), [\#85](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/85), [\#92](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/92)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/weijuey/ped/issues/1), [2](https://github.com/weijuey/ped/issues/3), [3](https://github.com/weijuey/ped/issues/5)

