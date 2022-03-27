---
layout: page
title: hsiaojietng's Project Portfolio Page
---

### Project: RealEstatePro

RealEstatePro is a desktop buyer/seller client management application used for ease of organizing client's contact and relevant information. The user, a real estate agent, interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a favourite command.
  * What it does: Allows the user to favourite a client, thus the client appears in a filtered list of favourited clients in another personalized window.
  * Justification: This feature improves the product significantly because it allows the user to have a list of clients that the user thinks are potentially always going to be contacted, thus being favourited by him/her. Having another list simplifies the need to remember them and find them through their information (eg. Name).
  * Highlights: This enhancement does not affect existing commands but the existing GUI and user experience. It did require an in-depth analysis of design alternatives as the team decided on how to obtain the list of favourited clients from the existing list.

* **New Feature**: Added a Favourites window command.
  * What it does: Allows the user open up a new window that displays the compacted list of favourited client.
  * Justification: This feature improves the product significantly because it allows the user to view the list of clients that the user thinks are potentially always going to be contacted, thus being favourited by him/her.
  * Highlights: This enhancement does not affect existing commands but the existing GUI and user experience.

* **New Feature**: Added a Statistics command/window.
  * What it does: Allows the user open up a new window that displays the visualization of the data of sellers' properties & buyers' preference based on their region.
  * Justification: This feature improves the product significantly because it allows the user to visualize data of his/her client and thus allow him/her to make better business decisions (exp. Find more clients in popular regions).
  * Highlights: This enhancement does not affect existing commands but the existing GUI and user experience.
  * Credits: @flairekq (https://github.com/nus-cs2103-AY2122S2/forum/issues/233)

* **Project management**:
  * Acted as repository master for the first iteration
    * Merged PRs into respective branches after reviewing changes
    * Provided effective reviews for PRs

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete`, `find`, `favourite` & `Favourites window`[\#47](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/47), `Statistics command/window` [\#94](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/94)
  * Developer Guide:
    * Added user stories for RealEstatePro
    * * Added documentation for the features `favourite` & `Favourites window`[\#47](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/47), `stats` & `Statistics window` [\#94](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/94)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#5](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/5), [\#11](https://github.com/AY2122S2-CS2103-W16-4/tp/pull/11/)
  * Created Ui mockup for RealEstatePro

* **Tools**:
  * Introduced Trello for brainstorming process of User Stories

