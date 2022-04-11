---
layout: page
title: Blake Tan Ting Yu's Project Portfolio Page
---

### Project: McKitchen

McKitchen (My-CLI-Kitchen) is a desktop application that can quickly store, edit, and search for recipes by running a single command. This is ideal for the fast typists, amateur cooks, busy students or executives who want a simple way to store or search for simple recipes to cook.
The user interacts with it using a CLI-like interface, and it has a GUI created with JavaFX. It is written in Java and has about 8 kLoC.

**Summary of contributions**

* **Code contributed:** : [RepoSense link] Click [here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=blaketingyu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=blaketingyu&tabRepo=AY2122S2-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false) to see the code that I've contributed on RepoSense.

* **Features and Enhancements implemented:**
* Added `Storage` for `Recipe` class,and its respective fields (PR [#44](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/44))
  * What it does: Allows the application to store and load recipe to the data file automatically. 
  * Justification: This feature is essential for users to be able to load and save existing recipe information automatically upon executing any commands.
  * Highlights: This enhancement required extensive changes to the components in the `Storage` package.

* Added `Clear` command for the application (PR [#127](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/127))
  * What it does: Allows the application to clear all the existing recipes in the application.
  * Justification: This good-to-have feature allows users to be able to delete all the existing recipes in one command.
  * Highlights: This enhancement allows users to delete with and without a confirmation prompt. The confirmation prompt serves as a safeguard for users to think twice before deleting all their recipes.

* Added `Reset` command for the application (PR [#142](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/142))
  * What it does: Allows the application to reset the current recipe book to the default recipes in the application.
  * Justification: This good-to-have feature allows users to be able to reset the recipe book back to the default recipes. 
  * Highlights: This enhancement allows users to reset with and without a confirmation prompt. The confirmation prompt serves as a safeguard for users to think twice before deleting all their recipes and resetting it back to the default configuration.

* **Enhancements to existing features**:
  * Wrote tests for existing `storage` package to increase coverage by 66.66% (Pull requests [\#73](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/73))
  * Wrote tests for existing `Clear` command to increase coverage by 64.15% (Pull requests [\#135](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/135))
  * Wrote tests for existing `Reset` command to increase coverage (Pull requests [\#143](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/143))
  * Bug fixes for (Pull requests [\#221](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/221), [\#263](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/263))
  * Populated dummy values for recipes (Pull requests [\#159](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/159))
  
* **Documentation**:
  * User Guide:
    * Added documentation for the `Storage` Package (Pull requests [\#140](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/140))
    * Added documentation for the `Clear` Command (Pull requests [\#140](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/140))
    * Added documentation for the `Exit` Command (Pull requests [\#221](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/221))
    * Added screenshots for `Clear` and `Reset` Command Confirmation Prompt (Pull requests [\#262](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/262))
  * Developer Guide:
    * Added implementation details for the feature `Clear` command (Pull requests [\#140](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/140))
    * Added MSS Use Cases for the `Clear` command (Pull requests [\#140](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/140))
    * Added Manual Testing section for `Clear` Command (Pull requests [\#262](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/262))
    * Added Manual Testing section for `Reset` Command (Pull requests [\#262](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/262))
    * Added MSS Use Cases for `Reset` Command (Pull requests [\#262](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/262))

* **Contributions to team-based tasks**:
  * Helped with photo editing tasks ( Coming up with the logo for McKitchen using Adobe Photoshop)
  * Maintaining the issue tracker.
  * Helped made the demo video for the application.

* **Community**:
  * PRs reviewed (with non-trivial review comments):(Pull requests [\#47](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/47), [\#84](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/84), [\#112](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/112), [\#152](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/152) , [\#237](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/237), [\#259](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/259))
  * All other PRs reviewed: [Link to 60+ PRs reviewed](https://github.com/AY2122S2-CS2103T-T17-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
  * Reported bugs and suggestions for other teams in the class ([\#2](https://github.com/blaketingyu/ped/issues/2), [\#3](https://github.com/blaketingyu/ped/issues/3), [\#4](https://github.com/blaketingyu/ped/issues/4), [\#5](https://github.com/blaketingyu/ped/issues/5), [\#6](https://github.com/blaketingyu/ped/issues/6), [\#7](https://github.com/blaketingyu/ped/issues/7), [\#8](https://github.com/blaketingyu/ped/issues/8), [\#9](https://github.com/blaketingyu/ped/issues/9), [\#10](https://github.com/blaketingyu/ped/issues/10), [\#11](https://github.com/blaketingyu/ped/issues/11), [\#12](https://github.com/blaketingyu/ped/issues/12), [\#13](https://github.com/blaketingyu/ped/issues/13), [\#186](https://github.com/AY2122S2-CS2103T-T17-3/tp/issues/186))
  
