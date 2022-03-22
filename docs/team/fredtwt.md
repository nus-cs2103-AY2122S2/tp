---
layout: page
title: Frederick Tang's Project Portfolio Page
---

### Project: NUSocials

NUSocials is a desktop address book application for university students who like to maintain a professional contact list. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to tag additional information to an existing contact entry.
    * What it does: allows the user to tag additional information to an existing contact.
    * Justification: This feature improves the product significantly because a user can tag important information to their own contacts for future references.
    * Highlights: This enhancement affects the existing UI layout. It required an in-depth analysis of how the tagged information should be displayed alongside with their respective contacts.

* **Code contributed**: [RepoSense link]()

* **Enhancements to existing features:**
  * Removed the tag functionality from add command
  * Created a tag command that can tag specific fields to existing contact entries
   
* **Documentation**:
    * User Guide:
        * Added documentation for the features `find` and `tag` [\#72]()
        * Did cosmetic tweaks on the documentation for existing features `list` and `delete`
        * Did cosmetic tweaks on the command summary to reflect NUSocials command format
    * Developer Guide:
      * Added user stories for the features `add`, `tag`, `delete`, `edit`, `remove`, `find`.
