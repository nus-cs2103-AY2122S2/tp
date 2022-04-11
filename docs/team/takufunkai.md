---
layout: page
title: Ezekiel Toh's Project Portfolio Page
---

### Project: d'Intérieur

d'Intérieur is a desktop app for interior designers to manage their contacts and projects. The designer interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 26 kLoC.

Interior designers can use d'Intèrieur to improve contact management and take on the needs of an ever-growing client base, so that they can focus on what matters most - delivering quality service for clients.

Given below are my contributions to the project.

* **New Feature**: Added images feature that allows user to add, delete and view images of a contact.
  * What it does: Allows the user to add, delete, and view images associated with each of their contacts.
  * Justification: Interior designers frequently work with images. Their clients' preferences, project designs and
    other details are often best represented through an image and not merely through words. Having these images
    can greatly improve their productivity, organization and help with attention to detail.
  * Highlights: In addition to understanding the fundamentals of the application such as how a contact's information is
    encapsulated and the interactions between the UI, Logic and Model, this feature requires understanding of how data
    should be saved and retrieved locally, as well as handling situations of corrupted/missing images. Displaying images
    in a responsive and user-friendly manner, as well as being able to click on the images, requires a decent level of 
    understanding of the UI.
  * Credits: Final look and design of the UI was discussed and agreed on as a team.
  
* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.
  * What it does: Allows the user to cycle through old and latest input histories using their up/down keys.
  * Justification: It is common for certain commands to be repeatedly inputted with slight modifications to the command.
    For example, a designer might want to favourite a bunch of users in quick succession. They can just press the up
    key, and replace the index value with the correct value, saving them the time to retype `fav ` repeatedly.
  * Highlights: Implementing the feature would require understanding of how the command input and processing flow works,
    beginning from how commands are being received by the UI and how they are being sent to the logic for processing.
    To save the history, it had to pass from the UI to the logic and then to the model, requiring understanding of the
    UI, logic and model's implementations. Pros and cons had to be weighed as well as to how best to encapsulate the 
    history of a user. Understanding of how keystrokes can be recorded and kick-in a method through the UI was also required.

* **New Feature**: Added favourites feature to set contacts as favourites and to filter them.
  * What it does: Allows the user to set and unset their contacts as favourites, and to list these favourite contacts.
  * Justification: Certain sets of contacts may be referenced repeatedly during different phases of a project. This
    feature allows the designers to quickly pull up a truncated list of contacts-of-interest to reduce on search time and
    the need to recall contact names for searching.
  * Highlights: This feature involved changes to the model manager, and the person model. This requires understanding of 
    the underlying implementation of the person as the favourite status was added as a field of the person, as well as
    the implementation of the model and its interactions with the logic (and indirectly, the UI) through the use of
    predicates and filtered list.
  * Credits: Position and look of the star representation of the Favourite status was discussed and finalized as a team.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=takufunkai&breakdown=true)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (5 releases) on GitHub

* **Enhancements to existing features**:
  * Improved the detailed view interface to be more responsive and user-friendly. 

* **Documentation**:
  * User Guide:
    * Added documentation for the features `favourite`, `images` and `commandhistory` [\#23](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/23), [\#103](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/103), [\#206](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/206)
    * Improved the introduction paragraph of the User Guide and updated help command documentation [\#89](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/89).
    * Added tutorial section to User Guide and updated sample images [\#206](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/206)
  * Developer Guide:
    * Added implementation details of the `favourite` and `images` feature [\#76](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/76) [\#210](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/210)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#68](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/68), [\#99](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/99), [\#93](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/93)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/81#issuecomment-1027890350), [2](https://github.com/nus-cs2103-AY2122S2/forum/issues/193#issuecomment-1055513252), [3](https://github.com/nus-cs2103-AY2122S2/forum/issues/187#issuecomment-1059743036), [4](https://github.com/nus-cs2103-AY2122S2/forum/issues/29#issuecomment-1020795493), [5](https://github.com/nus-cs2103-AY2122S2/forum/issues/85#issuecomment-1028099491))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/192), [2](https://github.com/nus-cs2103-AY2122S2/forum/issues/100#issuecomment-1029240062), [3](https://github.com/nus-cs2103-AY2122S2/forum/issues/83#issuecomment-1028106761))
