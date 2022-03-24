---
layout: page
title: hsiaojietng's Project Portfolio Page
---

### Project: RealEstatePro

RealEstatePro is a desktop buyer/seller client management application used for ease of organizing client's contact and relevant information. The user, a real estate agent, interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added a favourite command.
  * What it does: Allows the user to favourite a client, thus the client appears in a filtered list of favourited clients in another personalized window.
  * Justification: This feature improves the product significantly because it allows the user to have a list of clients that the user thinks are potentially always going to be contacted, thus being favourited by him/her. Having another list simplifies the need to remember them and find them through their information (eg. Name).
  * Highlights: This enhancement does not affect existing commands but the existing GUI and user experience. It did require an in-depth analysis of design alternatives as the team decided on how to obtain the list of favourited clients from the existing list.

* **New Feature**: Added a Favourites window command.
  * What it does: Allows the user open up a new window that displays the compacted list of favourited client.
  * Justification: This feature improves the product significantly because it allows the user to view the list of clients that the user thinks are potentially always going to be contacted, thus being favourited by him/her.
  * Highlights: This enhancement does not affect existing commands but the existing GUI and user experience.

* **Project management**:
  * Acted as repository master for the first iteration
    * Merged PRs into respective branches after reviewing changes
    * Provided effective reviews for PRs

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete`, `find`, `favourite`, `Favourites window`[\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added user stories for RealEstatePro

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#5](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/5), [\#11](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/11/)
  * Created Ui mockup for RealEstatePro
  * Contributed to forum discussions:

* **Tools**:
  * Introduced Trello for brainstorming process of User Stories

