
Wee Jun Hong's Project Portfolio Page
---

### Project: Ultimate Divoc Tracker

This project is based on the AddressBook-Level3 project created by the SE-EDU initiative.

This application is a variation of the above original project, tailored for school administrators to automate the tracking COVID-19 cases amongst students in schools.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=whoisjunhong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)


* **New Feature**: Batch Update Status based on Class Code -- (Pull request:[\#70](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/70))
  * What it does:
    * Upon a positive case in the ClassCode, all student's status in that ClassCode will be updated to Close-Contact
    * If no positive cases in the ClassCode, all student's status in that ClassCode wil be updated back to Negative
  * Justification: Track and automate COVID-19 close contact using class code
  * Credits: *{Collaborators: Jun Hong, Joshua, Louis}*

* **New Feature**: Add ClassCode methods -- (Pull request: [\#37](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/37))
  * What it does: Gives each person in the list a class code attribute denoting class number/code
  * Justification: Track COVID-19 close contact using class code
  * Highlights: Class Code follows the convention of an integer followed by an alphabet
  * Credits: *{Collaborators: Jun Hong, Joshua, Louis}*

* **Enhancements to existing features**:
  * Repurpose tags to activities (Pull requests [\#77](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/77))
    * What it does: Person can have multiple activities (e.g. CCA, functional groups)
    * Justification: Track COVID-19 close contact using activities
  * Enhancement to UI (Pull request [\#116](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/116))
    * What it does: Enhancement to current AB3 UI to make it more UI/UX centric
    * Justification: To beautify the application and allow the user to have a more pleasant experience using the application instead of the default theme provided by AB3
    
* **Fixes**: 
  * Fix batch update status for `AddCommand` and `DeleteCommand`  -- (Pull request: [\#118](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/118))
    * What it fixes:
      * Edge Case 1: If user is added into application who is `Positive`, the students in the same ClassCode and Activity does not change to `Close-Contact`
      * Edge Case 2: If user is deleted from the application who is `Positive` and the ClassCode and/or Activity does not have any other positive cases, those students statuses does not change to `Negative`
      * Edge Case 3: If user is added into application who is `Negative` while the ClassCode and/or Activity is `Close-Contact`, those students statuses does not change to `Negative`
      * Edge Case 4: If user is added into application who is `Negative` while there is still a `Positive` case in the ClassCode and/or Activity, the recently added student status will be changed to `Close-Contact`
    * Justification: Fix edge cases which will snowball into other critical bugs in the future
  * Fix UI and UG -- (Pull request: [\#193](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/193)) 
    * What it fixes:
      * Bugs surfaced from PE tests. 
    * Justification: Banner was changed to a MenuBar item to accommodate dynamic UI.
  * Minor fixes are not added to this list check [here](https://github.com/AY2122S2-CS2103T-T12-1/tp/pulls?q=is%3Apr+author%3Awhoisjunhong+is%3Aclosed+) for more

* **Documentation**:
  * README:
    * Change README.md image and description [\#196](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/196)
  * User Guide:
    * Update AboutUs.md [\#16](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/16)
    * Update UserGuide.md - "update_status and exit" [\#26](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/26)
    * Fix UI and UG [\#193](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/193)
  * Developer Guide:
    * Add Batch Update and ClassCode developer guide [\#92](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/92)

* **Community**:
  * PRs reviewed (with non-trivial review comments):
    * Batch student status update [\#62](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/62)
    * Addition to DeveloperGuide.md [\#91](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/91)
    * Update developer guide [\#98](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/98)
    * Changes to UG [\#114](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/114)

* **Test Cases**:
  * Fix UI and UG – (Pull Request #69)


* **Project management**:
  * Managed releases `v1.1` - `v1.4` (- releases) on GitHub
