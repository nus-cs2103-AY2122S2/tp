---
layout: page
title: Chee Hong's Project Portfolio Page
---

### Project: uMessage
uMessage is a desktop app that helps university students manage contacts, academics and CCAs that communicate and hold meetings on various platforms. It is optimized for keyboard users.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=cheehong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)


* **New Feature:** Make the list of persons selectable ([PR #112](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/112))

  - What it does: Allows the user to directly view the detailed contact information of any person in the contact list simply by clicking with the mouse or navigating with the arrow keys.

  - Justification: This feature improves the product significantly because a user can conveniently browse and see detailed information, improving the information density our product is able to deliver.

  - Highlights: This enhancement requires understanding of the observer pattern used in the GUI framework and FXML.

* **New Feature:** Add ability to `add` and `remove` individual tags from a person ([PR #88](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/88))
  - What it does: Allows the user to create and delete individual tags in the application

  - Justification: As part of the target user profile, this a key feature that allows the user to organise the contacts in the address book.

* **New Feature:** Filter list by clicking a tag label ([9f227def](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/113/commits/9f227def61b39204dedc367f5bb1327d1fbab419))
  - What it does: Allows the user to filter the Person/Meeting list by clicking on a tag label in the tag panel.

  - Justification: This feature improves the product significantly because a user can conveniently filter for groups of people/meeting with just a click, which is a key feature of our product.

<div style="page-break-after: always;"></div>

* **New Feature:** Add `SocialMedia` to `Person` ([PR #77](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/77), [PR #131](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/131))
  - What it does: Allows `Person` instances to contain information about any number of social media handles. Users can add/delete/edit specific `SocialMedia` entries for each person easily.

  - Justification: Storing multiple and manipulating social media handles for each person is part of our product's feature.

  - Highlights: This enhancement requires understanding of data storage and retrieval from AB3.

  - Credits: 
    - Chee Hong: Writing `SocialMedia` class, command for editing `SocialMedia` entries, JSON serialization of `SocialMedia`.
    - Weng Qi: Command for adding & deleting individual `SocialMedia` entries.


* **Enhancements to existing features**: Changes to `Email` regex ([PR #227](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/227))

  - Fix regex to allow for more than 2 consecutive special characters in local-part of an email.
  - Set character limits of `Email` to follow RFC guidelines.

* **Enhancements to existing features**: Allow index-based commands to work with names ([106db91](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/88/commits/106db91bc80597b9da03dba3c73858fd9b464f38), [PR #232](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/232))


* **Enhancements to existing features**: Make UI components scale dynamically to the window size ([PR #212](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/212))

* **Enhancements to existing features**: Wrote unit tests for `EditSocialMediaCommandParser` ([PR #260](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/260/commits/4c4e83e5611cdcf7ee6d420b34b97ebcff9a995f`)), `ParserUtil` ([PR #230](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/230/commits/96d04b380fb18ae80e76426e9f6c511b7594dc22))



* **Project management**:
  * Managed releases `1.2`, `1.3.trial`, `1.3.1`.
  * Created milestones for the team repo.

* **Documentation**:
  * Developer Guide:
    * Add Product Scope: [PR #55](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/55)
    * Add Acknowledgements: [c724c4](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/236/commits/c724c4a82e6e68bea84e3ad1c087bc284d15a4bf)
    * Wrote implementation for `Target` class: [c8c5f74](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/236/commits/c8c5f7422268279d07b35ecc46ae9a733232f495)
    * Wrote implementation for split-view panel (credits to Wu Zihao for the sequence diagram): [d014e58](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/236/commits/d014e582f55adab5da42187600024ed0ebb09df6)

    * Update diagrams for `Model` component: [5e3f128](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/236/commits/5e3f12877e18aaa6cc8b94ac73154da096528aba)

  * User Guide:
    * Add instructions on editing social media. [6833d5](https://github.com/AY2122S2-CS2103-W16-2/tp/commit/6833d554602c4ea0725839985c5e5021449e46ad)
    * Add instructions on add and delete commands. [130fcfb](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/88/commits/130fcfb411d526c43b41909772bc801b1e3043bf)
    * Fixed minor grammatical issues [bc8c99](https://github.com/AY2122S2-CS2103-W16-2/tp/commit/bc8c99b7b2f63f1ebd23b621b89312b4b38997b1)

<div style="page-break-after: always;"></div>

* **Contributions to team-based tasks**:
  * Update site-wide settings: [PR #44](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/44)
  * Update `AboutUs.md`: [PR #44](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/44)
  * Carried out PDF conversion of Developer guide : [#PR 263](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/263)

* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [PR #49](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/49), [PR #78](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/78), [PR #107](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/107#discussion_r830948896) [PR #262](https://github.com/AY2122S2-CS2103-W16-2/tp/pull/262)

  * Reviewed [over 40 Pull Requests](https://github.com/AY2122S2-CS2103-W16-2/tp/pulls?q=reviewed-by%3Acheehongw) in the team.

  * Provided over [60+ comments](https://nus-cs2103-ay2122s2.github.io/dashboards/contents/tp-comments.html#74-wong-hong-cheehongw-29-comments) in team PRs and Issues

* **Community**:
  * Reported a total of [10 issues](https://github.com/cheehongw/ped/issues) during the PE dry run.
  * Helped others on the forum with technical difficulties (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/39#issuecomment-1022044065), [2](https://github.com/nus-cs2103-AY2122S2/forum/issues/31#issuecomment-1020874495)) 
  * [Shared information]((https://github.com/nus-cs2103-AY2122S2/forum/issues/59#issuecomment-1026096429)) about setting up unit tests in Visual Studio Code.
