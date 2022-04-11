---
layout: page
title: Ong Weng Qi's Project Portfolio Page
---

### Project: uMessage

**Overview**

uMessage is an app that helps university students manage their **contacts** and **meetings**.
It is optimized for keyboard users.


**Summary of Contributions**

Given below are my contributions to the project.

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=chriswengqi&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)


* **New Feature:** Global Tag List display [#113](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/113)

    - What it does: Displays a list of all tags available in the Main Window.

    - Justification: This feature allows users to view the list of available tags so that they can filter the contact list based on specific tags.

    - Highlights: This enhancement requires understanding of the observer pattern used in the GUI framework and FXML.


* **New Feature:** Addition and deletion of `Tag` directly [#113](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/113)

    - What it does: Supports the addition and deletion of specific `Tag`

    - Justification: This feature allows users to add and delete tags directly without involving `Person`.

    - Highlights: Deletion of tags is such that every `Person` with the deleted `Tag` will have the `Tag` removed.


* **New Feature:** Implement `ViewCommand` [#79](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/79)

    - What it does: Displays user contact details in the RHS Window.

    - Justification: This feature allows users to view all details of a specific contact in the RHS Window.

    - Highlights: This enhancement requires understanding of the observer pattern used in the GUI framework and FXML.


* **New Feature:** Implement `HelpWindow` [#83](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/83)

    - What it does: Displays HelpWindow when user inputs `help`.

    - Justification: Instead of providing a link to the User Guide website, we integrated `help` with the GUI so that users can refer to the help guide without the need of accessing the Internet.

    - Highlights: Markdown view was supported for `HelpWindow` which required the importing of external libraries.
    
    - Credits: Github user [JPro](https://github.com/JPro-one/markdown-javafx-renderer) for the markdown library.

    - Credits: Github user [JPro](https://github.com/JPro-one/markdown-javafx-renderer) for the markdown library.

* **Enhanced Feature:** Addition and deletion of SocialMedia directly [#109](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/109)

  - What it does: Supports the addition and deletion of specific `SocialMedia` from `Person`.

  - Justification: This feature allows users to add and delete `SocialMedia` directly from `Person`.

 


* **Enhanced Feature**: Make `Email` and `Phone` optional ([PR #110](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/110))

  - `Email` and `Phone` are now optional fields that do not have to be added when initialising a new contact.
  
  - By default, the value of `Email` and `Phone` is now `-` if it is empty.


* **Enhanced Feature:** Addition and deletion of `SocialMedia` directly [#109](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/109)

  - Supports the addition and deletion of specific `SocialMedia` from `Person` directly.


  
* **Documentation**:

  * User Guide:
  
    * Update the User Guide with `help` and `view` command: [#90](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/90)

  * Developer Guide:
  
    * Add User Stories: [#14](https://github.com/AY2122S2-CS2103-W16-2/tp/issues/14)
    
    * Add Glossary: [#16](https://github.com/AY2122S2-CS2103-W16-2/tp/issues/16)

  * Help Guide:
  
    * Initialize Help Guide in the `HelpWindow`: [#83](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/83)


* **Project Management**

  * As the team lead, I am in charge of the overall project management, from scheduling meetings to setting clear deadlines for each deliverable.
  
  * Initialise 11 issues about bugs/improvements.
  
  * Fix miscellaneous bugs. [#176](https://github.com/AY2122S2-CS2103-W16-2/tp/issues/176), [#165](https://github.com/AY2122S2-CS2103-W16-2/tp/issues/165), [#201](https://github.com/AY2122S2-CS2103-W16-2/tp/issues/201), [#187](https://github.com/AY2122S2-CS2103-W16-2/tp/issues/187), [#188](https://github.com/AY2122S2-CS2103-W16-2/tp/issues/188) and etc.
  
  * Create test cases for `add`, `delete` and `edit`. [#222](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/222)


* **Review/mentoring contributions**:

  * Review 27 pull requests and provide suggestions to teammates about code.


* **Community**

  * Reported 9 bugs and suggestions for other teams in the class (examples: [1](https://github.com/chriswengqi/ped/issues/9), [2](https://github.com/chriswengqi/ped/issues/8))
