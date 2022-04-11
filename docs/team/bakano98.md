---
layout: page
title: Wei Jie's Project Portfolio Page
---

### Project: ModuleMateFinder

ModuleMateFinder is a desktop address-book-like application used to keep track of your friends' contacts, as well as the modules they are taking. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.


- **New Feature**: Added the ability to assign `Status`(blacklist/favourite) users [(PR #15)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/15)
  - **What it does**: Allows users to set a status (blacklist/favourite) to any contact. A user is known to be blacklisted/favourited through the UI.
  - **Justification**: This feature improves the product significantly as users want to know who they should be wary of when thinking about forming groups for their modules.
  - **Enhancements**: Changed to a graphical representation instead of a text-based representation [(PR #47)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/47)
  - **Credits**: Code reuse mainly from `Remark`'s tutorial.


- **New Feature**: Added the ability to filter by users by module code [(PR #15)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/15)
  - **What it does**: Allows users to filter their contacts by a module that they are taking
  - **Justification**: This feature improves the product as we might want to filter our contacts by the modules they are taking, then contact those people in order to form project or study groups with them. It is a separate command from `find` as they do specifically different things.
  - **Highlights**: This feature was relatively challenging despite being similar to `find` command. It required a proper analysis of `find` in order to understand the inner workings of it, and then following a similar implementation of it.
  - **Credits**: Main idea of implementing `filter` is derived from `find`


- **New Feature**: Added the ability to unarchive users from `archiveBook`, revamped underlying architecture of `archive` command. [(PR #135)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/135)
  - **What it does**: Allows you to archive/unarchive contacts in ModuleMateFinder.
  - **Justification**: This features improves the product as it allows users to organise their contacts better. For example, they can put all graduate students in `archive`, and keep them in mind in the event they want to contact them for module feedback.
  - **Highlights**: Modifications to the underlying architecture (in `Model`) is necessary to fit another `AddressBook`, for it be used as `archiveBook`. Also needed to figure out a way to retrieve which `AddressBook` was in view.
  - **Enhancements**: `switch` was binded to F10 [(PR #122)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/122)
  - **Credits**: Credits to [Bryan](https://github.com/fantablack) for the base implementation.

- **New Feature**: Added GUI, `AddWindow` for adding a new Person into ModuleMateFinder. [(PR #46)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/46)
  - **What it does**: Allows users to add a new contact into ModuleMateFinder.
  - **Justification**: This feature improves the product significantly as not all users will be familiar with CLI application, or simply do not want to memorise the commands. It provides ease of use, and allows execution of multiple commands by abstracting details.
  - **Highlights**: Required playing around with JavaFX and SceneBuilder to figure out the best layout.
  - **Enhancements**: Extended the feature to allow for adding _optional_ fields like `Module`, `Status`[(PR #72)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/72) and `Comment`[(PR #88)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/88). Binded the `ENTER` key to act as a `Submit` button, when mandatory fields are added. Reset focus on first `TextField` when window loses focus [(PR #102)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/102). Extended basic window functionality for `EditCommand` as well, named `EditWindow` [(PR #81)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/81).
  - **Credits**: Code reuse from `HelpWindow`, which serves as the _baseline_ for any additional windows.
  

- **GUI Update**: Recoloured GUI, and moved certain fields around. [(PR #112)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/112)
  - **What it does**: Makes ModuleMateFinder more distinct and recognisable with a new colour theme.
  - **Justification**: Less semblance to `AddressBook3`, so it has a more original feel to it.


- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=bakano98&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=bakano98&tabRepo=AY2122S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code&authorshipIsBinaryFileTypeChecked=false)
- **Project Management**:
  - Managed releases for `v1.2` and `v1.4`, as well as demo videos.
  - Set up team repo (CodeCov, GitHub environments) for the team.
  - Set up PR review system for the team.
  - Weekly commits and no deadlines missed.

- **Bugfixes**
  - See [here](https://github.com/AY2122S2-CS2103T-T13-4/tp/issues?q=assignee%3Abakano98+label%3Atype.Bug+)

- **Community**
  - Reviewed all PRs done by Julio and Bryan
    - Non-trivial PRs reviewed and commented on for Julio are: [PR #62](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/62), [PR #90](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/90), [PR #200](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/200), [PR #218](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/218)
    - Non-trivial PRs reviewed and commented on for Bryan are: [PR #91](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/91), [PR #116](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/116), [PR #198](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/198)
  - Reviewed most PRs by Wei Ming and Benjamin
    - Non-trivial PRs reviewed and commented on for Wei Ming are: [PR #44](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/44), [PR #78](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/78)
    - Non-trivial PRs reviewed and commented on for Benjamin are: [PR #65](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/65) [PR #83](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/83)


- **Documentation**:
  - User Guide:
    - Set up overall UG style, Quick Jump [(PR #1)](https://github.com/AY2122S2-CS2103T-T13-4/tp/commit/1aa81e2c5590653463a84a972c678f447c979a29)
    - Added `AddWindow` and `EditWindow` image and functionality [(PR #77)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/77) [(PR #81)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/81)
    - Added `archive`, `unarchive`, `switch` explanation and functionality [(PR #124)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/124)
    - Updated UG according to peer feedback [(PR #100)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/100)
    - Updated UG according to CS2101 feedback [(PR #112)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/112/commits/f1ec6be1bdb557101201b979c440d769ff4cff61)
  - Developer Guide:
    - Add use cases for `status` and `filter` [(PR #27)](https://github.com/AY2122S2-CS2103T-T13-4/tp/commit/a13e9b086d85dd607421835135e742ff67b542cd)
    - Add use cases for `archive`, `unarchive`, `switch`, `deletemodules`, and `clearmodules` [(PR #203)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/203)
    - Reordered use cases to follow the format in the user guide.
    - Updated UML diagram for Ui component and add information regarding the additions (`EditWindow`, `AddWindow`), also added an activity diagram, sequence diagram for `add` [(PR #84)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/84)
    - Add `Appendix: Effort` to describe the amount of effort we think is required to make ModuleMateFinder
- README:
  - Did the mockup UI and the description of ModuleMateFinder [here](https://github.com/AY2122S2-CS2103T-T13-4/tp/commit/f054e1d2871e6e5904d473b9203e7874340f0633)
  

