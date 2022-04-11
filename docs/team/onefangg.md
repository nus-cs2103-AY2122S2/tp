---
layout: page
title: Chua Yi Fang's Project Portfolio Page
---

### Project: ReadyBakey

ReadyBakey is a desktop order management application created for CS2103 . The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=onefangg&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=onefangg&tabRepo=AY2122S2-CS2103-F09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

**Enhancements implemented**:
1. Adding tests to prepare for implementation of Order and validation checks.
   * As part of PR [#83](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/83) and [#119](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/119).
   * Added tests for classes such as `AddOrderCommand`, `AddOrderCommandParserTest`.
   * Improved coverage by +0.87% as seen in [#119](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/119).

2. Implemented find orders/persons based on attributes
   * As part of PR [#112](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/112) and [#164](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/164). 
   * Search orders based on attributes: name, phone, (remark, collection_type and details subsequently added by another team member)
   * Search persons based on attributes: name, phone, email, address and remark.
   
3. Implemented formatting and parsing of details in Order
  * As part of PR [#142](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/142).
  * Standardised formatting of `d/` details item in Order to follow format `<quantity>:<orderItem>`
  * `Details` can now hold parse `quantity` and `orderItem` as attributes, as opposed to a single string.
  * Added stronger validation checks for details item. e.g. A single Order cannot exceed having more than 5 items.
  
**Contributions to the UG**: 
* Updated UG with new functionalities such as updated findp command [#171](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/171), and updated details implementation [#177](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/177)
* Fixed different bugs in UG found during PE-D. [#240](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/240)

**Contributions to the DG**:
* Updated user stories. [#78](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/78)
* Updated use cases for adding customer and order. [#77](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/77) 
* Updated implementation details for `FindOrderCommand` based on attribute search. [#135](https://github.com/AY2122S2-CS2103-F09-4/tp/pull/135) 

**Contributions to team-based tasks**:
* Contributed in team discussion for overall product direction
* Maintained Issue Tracker
* Helped update on bug fixing process for v1.4 to tutor.

**Review/mentoring contributions**:
* Participated in reviews for PRs.
