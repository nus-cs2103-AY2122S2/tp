---
layout: page
title: Michael Low's Project Portfolio Page
---

### Project: ClientConnect

ClientConnect is a desktop client address book application for insurance agents to manage and keep track of their clientele seamlessly and efficiently. Custom tailored to suit the needs of insurance agents, ClientConnect surpasses other similar applications by offering features such as grouping of clients by packages, keyboard shortcuts, a gorgeous GUI, and more.

Given below are my contributions to the project.

* **New Feature**:
    * Multi-field and multi-keyword FindCommand ([#38](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/38))
      * What it does: Allows the insurance agent to find clients based off a combination of multiple fields and multiple keywords
      * Justification: This feature improves the product significantly as insurance agents can better fine-tune their findings with more specificity and complexity of find.
      * Highlights: The enhancement required the creation of `FieldContainsKeywordsPredicate` class which was an abstract class that the other containsKeywordsPredicate inherited from. The `CombineContainsKeywordsPredicate` is the main driving predicate for enabling the multi-field, multi-keyword find.
    * PackageWindow UI ([#68](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/68), [#71](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/71))
      * What it does: A new pop-up window for Insurance Agents to view the details of insurance packages.
      * Justification: Have quick access to information of insurance packages in the event they have to contact the client.
      * Highlights: Makes use of a PackageListPanel and a PackageCard as sub-components to display the scrollable window of insurance packages.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=michaelseyo&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Assigned issues to teammates and closed issues.
    * Merged and made PRs.
  
* **Enhancements to existing features**:
    * Update the color scheme to be light-theme (Pull request [#42](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/42), [#44](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/44))
  
* **Documentation**:
    * User Guide:
        * Improved the introduction of ClientConnect. ([#151](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/151))
        * Updated documentation for FindCommand with relevant examples and screenshots. ([#53](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/53/commits/a9368134f1575f2b3422aa5e712631a918638b68))
        * Updated Packages Tab documentation with screenshots. ([#74](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/74/commits/92e06a85b0c6dbc032b01d0890a504ba07e7c4a1))
        * Cleaned up overall user guide formatting wise. ([#74](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/74/commits/df135aa0d2e05c05159a0ae6a90cc7048ed594e7))
    * Developer Guide:
        * Created additional details for the UI class diagram to include PackageWindow and its relevant components. ([#161](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/161/commits/d0e01bae4e10cb802606851c24090a69d84750a6), [#161](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/161/commits/db7c37804d264a3c3f7f907610fa9d30060657fd))
        * Added further description on PackageWindow and HelpWindow under the UI component. ([#161](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/161/commits/1738f5de632beb1d10deb4f81cb2d86d1f6d69c9))
        * Created class diagrams of new Predicate created and FindCommand execution. ([#161](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/161/commits/1738f5de632beb1d10deb4f81cb2d86d1f6d69c9))
        * Created sequence diagrams for FindCommand to demonstrate the execution of the improved implementation. ([#161](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/161/commits/1738f5de632beb1d10deb4f81cb2d86d1f6d69c9), [#169](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/169))
        * Added more descriptions on the implementation of the improved FindCommand, including the new Predicate classes created. ([#53](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/53/commits/0a532ec87a861f584267a14b590140ae17589834))
        * Added MSS for `find` to highlight the various scenarios possible. ([#181](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/181))

* **Community**:
    * Provided reviews for teammates PRs. (Pull request [#35](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/35))
    * Reported bugs for other teams in class ([#19](https://github.com/michaelseyo/ped/issues/19), [#21](https://github.com/michaelseyo/ped/issues/21), [#12](https://github.com/michaelseyo/ped/issues/12))
    * Contributed to the User Guide to document the target user profile and ClientConnect's potential appeal to them. ([#151](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/151/commits/8c189c296e0515fe5835d4f75872a72dd3522653))
    * Standardise the use of "Client" instead of "Person" in the app display messages. ([#151](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/151))
    * Changed icon of ClientConnect app.
