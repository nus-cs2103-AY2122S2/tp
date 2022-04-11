---
layout: page
title: Ryan Aidan's Project Portfolio Page
---

### Project: McKitchen

McKitchen (My-CLI-Kitchen) is a desktop application that can quickly store, edit, and search for recipes by running a single command, ideal for the fast typists, home cooks, and students who want a simple way to store or search for simple recipes to cook. The user interacts with it using a CLI-like interface, and it has a GUI created with JavaFX. It is written in Java and has about 8k LoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add new recipes.
    * What it does: allows the user to add new recipes to the application. Recipes require details such as the recipe name, completion time (in minutes), serving size (no. of portions), ingredients (name and quantity required), steps and lastly tags (optional).
    * Justification: This feature improves the product significantly because users can add their own recipes to the application to view or refer to in the future.
    * Highlights: This enhancement required the use of individual classes for each attribute of the recipe as well as individual `Prefix` to parse and handle each attribute.

* **New Feature**: Added the ability to edit existing recipes.
  * What it does: allows the user to edit existing recipes in the application. At least one of the recipe attributes must be updated/edited for the edit command to be successful.
  * Justification: This feature improves the product significantly because users can change/update existing recipes in the application instead of having to delete and re-add the recipe with the updated attributes all over again.
  * Highlights: This enhancement required the use of individual classes for each attribute of the recipe as well as individual `Prefix` to parse and handle each attribute. The `EditRecipeDescriptor` was also required to copy each updated value provided by the user over to the existing recipe.

* **New Feature**: Access (`edit`, `view`, `delete`) recipe by index and name.
  * What it does: Allows the above-mentioned features to work by specifying either the index of the recipe in the current list view with the `-x` flag or by specifying the name of the recipe.
  * Justification: Improve user-experience by alleviating the issue of long commands by allowing users to specify either `-x <recipe number from list>` or `<recipe name>` so that users would have the optionality of running command either by its name or index.
  * Highlights: This enhancement requires the command parser to determine if the user used a recipe index with `-x` or if a recipe name was used. The parser would then have to obtain the specified recipe based on the index or name specified before one of the above-mentioned features can continue running.

* **New Feature**: Add multiple values within a single `Prefix` using `|` as a delimiter.
  * What it does: Allows users to add multiple values for each attribute within a single `Prefix`.
  * Justification: Improve user-experience by alleviating the issue of long commands by allowing users to specify multiple values of an attribute within a single `Prefix` instead of having to enter the `Prefix` for every value that the user wants to add to an attribute (e.g. having to enter `Prefix` for every ingredient of a recipe).
  * Highlights: This enhancement upgrades the `ArgumentMultiMap` class and splits all argument values found for each `Prefix` with the specified delimiter ( which in this case is `|`) and adds them to the attribute specified.

* **Code contributed**: Click [here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=aidanaden&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=yusufaine&tabRepo=AY2122S2-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false) to see the code that I've contributed on RepoSense.

* **Enhancements to existing features**:
  * Designed a user-friendly UI with a coherent and relevant color scheme, fonts and layout for the McKitchen application using Figma.
  * Wrote tests for existing `Edit` command and `EditCommandParser` to increase coverage to 69.91% (Pull request [\#156](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/156))
  * Wrote tests for existing `Add` command and `AddCommandParser` to increase coverage to 74.03% (Pull request [\#126](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/126))
  * Bug fixes for integer overflow (Pull request [\#237](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/237))
  * Bug fixes for invalid prefixes (Pull request [\#239](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/239))
  * Bug fixes to allow storing of `Ingredient` class attributes to json file (Pull request [\#60](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/60))

* **Documentation**:
  * User Guide:
    * Added documentation for the Add and Edit commands (Pull request [\#136](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/140))
    * Update command summary with Add and Edit commands (Pull request [\#149](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/149))
  * Developer Guide:
    * Added implementation details for the Add and Edit Commands (Pull request [\#136](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/136))

* **Community**:
  * [Top 10](https://nus-cs2103-ay2122s2.github.io/dashboards/contents/tp-comments.html#9-ryan-riya-aidanaden-93-comments) in cohort for PR review comments made
  * PRs reviewed (with non-trivial review comments):
    * [\#73](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/73#discussion_r825432423), [\#109](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/109#discussion_r829317770), [\#152](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/152#discussion_r835604664), [\#162](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/162#discussion_r835927374) and [\#143](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/143#discussion_r834116368)
  * Reported bugs and suggestions for other teams in the class ([\#1](https://github.com/aidanaden/ped/issues/1), [\#3](https://github.com/aidanaden/ped/issues/3), [\#4](https://github.com/aidanaden/ped/issues/4), [\#5](https://github.com/aidanaden/ped/issues/5), [\#6](https://github.com/aidanaden/ped/issues/6), [\#7](https://github.com/aidanaden/ped/issues/7), [\#10](https://github.com/aidanaden/ped/issues/10), [\#11](https://github.com/aidanaden/ped/issues/11), [\#12](https://github.com/aidanaden/ped/issues/12),
