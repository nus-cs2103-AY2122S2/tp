---
layout: page
title: Glenn Lim's Project Portfolio Page
---

### Project: d'Intérieur

d'Intérieur is a desktop address book application, designed with interior designers in mind. The designer interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 26 kLoC.

Interior designers can use d'Intérieur to improve contact management and take on the needs of an ever-growing client base, so that they can focus on what matters most - delivering quality service for clients.

Given below are my contributions to the project.

* **New Feature**: `Assign Tag`
  * What it does: Assigns a tag to a contact.
  * Justification: This feature allows different contacts to be grouped at different points of time, such as labeling them as `InProgress` and `Completed` according to which stages they are at in the design cycle.'
  * Highlights:
  * Credits: This feature can be done when a contact profile is currently in view, which was done by [Wei Jue](weijuey.md)

* **New Feature**: `Create Tag`
  * What it does: Creates a tag with a meaningful name to categorise and group relevant contacts.
  * Justification: This feature is essential as it allows tags to be created, then assigned to contacts whenever they reach a certain stage in the design cycle, or for personal use.

* **New Feature**: `Delete Tag`
  * What it does: Deletes a tag stored in the data, and unassigns any contact that are assigned to the tag to be deleted.
  * Justification: This feature helps interior designers manage unused tags, or do a mass unassignment at once.
<div style="page-break-after: always;"></div>

* **New Feature**: `Find Tag`
  * What it does: Acts as a filter to locate contacts with a certain tag. 
  * Justification: This feature helps interior designers locate contacts that are grouped by their tags.
  * Highlights: This enhancement was made through the use of an `ActivatedTagList` which acts as a list of selected criteria/category just like how an eCommerce store would select filter to apply.
  
* **New Feature**: `Unassign Tag`
  * What it does: Unassigns a tag from a contact.
  * Justification: This feature allows contacts who reach different stages of the design cycle can have their older tags (which specifies their older stage) removed from them. 
  * Credits: This feature can be done when a contact profile is currently in view, which was done by [Wei Jue](weijuey.md)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=glennljw&breakdown=true)

* **Project management**:
  * Managed releases `1.2` - `1.4` (4 releases) on GitHub
  * Added photos and details of team members [\#11](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/11)

* **Enhancements to existing features**:
  * Enhanced existing `Tag` to be a class of its own instead of a `String`.
  * Allowed `add` feature to create and assign `Tag` upon adding a new contact. [\#83](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/83)
  * Removed the editing tag functionality in the `edit` feature, abstracting this feature into an `assign` and `unassign` feature for tags. [\#83](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/83)
  * Modified the help window by improving the GUI for displaying useful links to different sections of the User Guide. [\#190](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/190)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `assign tag`, `create tag`, `delete tag`, `find tag` and `unassign tag` (Pull requests [\#26](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/26), [\#90](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/90), [\#95](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/95), [\#115](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/115), [\#189](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/189))
    * Edited existing documentations for `edit` feature [\#90](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/90)
    * Updated the UI images for the help window. [\#190](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/190)
    
  * Developer Guide:
    * Added implementation details of the following features:
      * Find Tag
      
    * Added sequence diagram in the implementation details of Find Tag.
    * Added instructions for manual testing for Create Tag feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#87](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/87), [\#89](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/89), [\#196](https://github.com/AY2122S2-CS2103T-T12-2/tp/pull/196)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/84#issuecomment-1028047189), [2](https://github.com/nus-cs2103-AY2122S2/forum/issues/84#issuecomment-1028530974))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/glennljw/ped/issues/4), [2](https://github.com/glennljw/ped/issues/3), [3](https://github.com/glennljw/ped/issues/2))


