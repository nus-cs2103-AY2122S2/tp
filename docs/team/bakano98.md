---
layout: page
title: Wei Jie's Project Portfolio Page
---
# Skeleton of the Project Portfolio Page for Wei Jie

### Project: ModuleMateFinder

ModuleMateFinder is a desktop address-book-like application used to keep track of your friends' contacts, as well as the modules they are taking. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.


- **New Feature**: Added the ability to blacklist/favourite users [(PR #15)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/15)
  - **What it does**: Allows users to set a status (blacklist/favourite) to any contact. A user is known to be blacklisted/favourited through the UI.
  - **Justification**: This feature improves the product significantly as users want to know who they should be wary of when thinking about forming groups for their modules.
  - **Highlights**: This enhancement does not affect existing commands too much, and required understanding of a `Person`'s structure, modifying and adding new fields to it.
  - **Enhancements**: Changed to a graphical representation instead of a text-based representation [(PR #47)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/47)
  - **Credits**: Code reuse mainly from `Remark`'s tutorial. Adding a new field to a Person is very similar to the tutorial given.


- **New Feature**: Added the ability to filter by users by module code [(PR #15)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/15)
  - **What it does**: Allows users to filter their contacts by a module that they are taking
  - **Justification**: This feature improves the product as we might want to filter our contacts by the modules they are taking, then contact those people in order to form project or study groups with them. It is a separate command from `find` as they do specifically different things.
  - **Highlights**: This feature was relatively challenging despite being similar to `find` command. It required a proper analysis of `find` in order to understand the inner workings of it, and then following a similar implementation of it.
  - **Credits**: Main idea of implementing `filter` is derived from `find`

- **New Feature**: Added the ability to unarchive users from `archiveBook`, revamped underlying architecture of `archive` command. [(PR #135)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/135)
  - **What it does**: Allows you to archive/unarchive contacts in ModuleMateFinder.
  - **Justification**: This features improves the product as it allows users to organise their contacts better. For example, they can put all graduate students in `archive`, and keep them in mind in the event they want to contact them for module feedback.
  - **Highlights**: This was a tough feature to implement. I had to modify the underlying architecture (in `Model`) in order to fit another `AddressBook`. This additional `AddressBook` is used as a reference for `archiveBook`. With these changes, methods have to be made with respect to `archiveBook`, and then we need a way to identify which `AddressBook` we're currently in. This is retrieved through GUI methods.
  - **Enhancements**: I binded the `switch`, which allows you to switch between `archiveBook` or `addressBook` to `F10` key. [(PR #)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/122) I also disabled `add`, `archive` when in `archiveBook`, and disabled `unarchive` when in `addressBook`.
  - **Credits**: Thanks to Bryan for implementing the base feature, where I then expanded and revamped along the way.

- **New Feature**: Added GUI, `AddWindow` for adding a new Person into ModuleMateFinder. [(PR #46)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/46)
  - **What it does**: Allows users to add a new contact into ModuleMateFinder.
  - **Justification**: This feature improves the product significantly as not all users will be familiar with CLI application, or simply do not want to memorise the commands. Having a GUI way to add contacts provides ease of use for the users. Furthermore, it can execute multiple commands at once.
  - **Highlights**: This feature was challenging as it required good understanding of JavaFX, which required digging deeper into JavaFX and how it integrates with Java. Furthermore, styling is non-trivial.
  - **Enhancements**: Extended the feature to allow for adding _optional_ fields like `Module`, `Status`[(PR #72)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/72) and `Comment`[(PR #88)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/88). Binded the `ENTER` key to act as a `Submit` button, when mandatory fields are added. Reset focus on first `TextField` when window loses focus [(PR #102)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/102)
  - **Credits**: Some code reuse from `HelpWindow`, which served as the _baseline_ to start implementation of `AddWindow`


- **New Feature**: Added GUI, `EditWindow` for editing a Person within ModuleMateFinder. [(PR #81)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/81)
  - **What it does**: Allows users to easily edit a contact in ModuleMateFinder
  - **Justification**: Not all users are familiar with the command syntax and would prefer something more guided. This provides that guidance while still giving the benefit of typing fast.
  - **Highlights**: This feature is similar to `AddWindow`, so only a few changes needed to be made.
  - **Enhancements**: Reset focus onto first `TextField` when window loses focus. Binded `ENTER` key to act as a `Submit` button, as long as the given input is valid. [(PR #102)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/102)
  - **Credits**: Some code reuse from `HelpWindow`, which served as the _baseline_ to start implementation of `EditWindow`


- **GUI Update**: Recoloured GUI, and moved certain fields around. [(PR #112)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/112)
  - **What it does**: Makes ModuleMateFinder more distinct and recognisable with a new colour theme.
  - **Justification**: Less semblance to `AddressBook3`, so it has a more original feel to it.
  - **Highlights**: Settling on a colour is not easy, and finding matching colours took quite a lot of time.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=bakano98&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=bakano98&tabRepo=AY2122S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code&authorshipIsBinaryFileTypeChecked=false)
- **Project Management**:
  - Made new commits and PRs weekly -- and never missed a deadline.
  - Ensure team was on track.
  - Ensure I kept everyone up to date with what I was doing.
  - Tried to keep up and help with features that were lagging behind.

- **Documentation**:
  - User Guide:
    - Set up overall UG style [(PR #1)](https://github.com/AY2122S2-CS2103T-T13-4/tp/commit/1aa81e2c5590653463a84a972c678f447c979a29)
    - Set up Quick Jump for UG [(PR #1)](https://github.com/AY2122S2-CS2103T-T13-4/tp/commit/1aa81e2c5590653463a84a972c678f447c979a29)
    - Added `AddWindow` image and functionality [(PR #77)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/77)
    - Added `EditWindow` image and functionality [(PR #81)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/81)
    - Added `archive`, `unarchive`, `switch` explanation and functionality [(PR #124)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/124)
    - Updated UG according to peer feedback [(PR #100)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/100)
    - Updated UG according to CS2101 feedback [(PR #112)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/112/commits/f1ec6be1bdb557101201b979c440d769ff4cff61)
  - Developer Guide:
    - Add use cases for `status` and `filter` [(PR #27)](https://github.com/AY2122S2-CS2103T-T13-4/tp/commit/a13e9b086d85dd607421835135e742ff67b542cd)
    - Add use cases for `archive`, `unarchive`, `switch`, `deletemodules`, and `clearmodules` [(PR #203)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/203)
    - Reordered use cases to follow the format in the user guide.
    - Updated UML diagram for Ui component and add information regarding the additions (`EditWindow`, `AddWindow`), also added an activity diagram, sequence diagram for `add` [(PR #84)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/84)
    - 
  - README:
    - Did the mockup UI and the description of ModuleMateFinder [here](https://github.com/AY2122S2-CS2103T-T13-4/tp/commit/f054e1d2871e6e5904d473b9203e7874340f0633)
  

**Bugfixes**
- See [here](https://github.com/AY2122S2-CS2103T-T13-4/tp/issues?q=assignee%3Abakano98+label%3Atype.Bug+)

**Community**
- Reviewed all PRs done by Julio and Bryan
  - Non-trivial PRs reviewed and commented on for Julio:
    - [PR #62](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/62)
    - [PR #90](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/90)
  - Non-trivial PRs reviewed for Bryan:
    - [PR #91](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/91)
- Reviewed some PRs by Wei Ming and Benjamin
  - Non-trivial PRs reviewed and commented on for Wei Ming
    - [PR #44](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/44)
    - [PR #78](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/78)
  - Non-trivial PRs reviewed and commented on for Benjamin
    - [PR #65](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/65)
    - [PR #83](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/83)
- Verified on [forum](https://github.com/nus-cs2103-AY2122S2/forum/issues/210) regarding GUI additions
