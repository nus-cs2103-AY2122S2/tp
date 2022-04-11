---
layout: page
title: Robin's Project Portfolio Page
---

### Project: McKitchen

McKitchen (My-CLI-Kitchen) is a desktop application that can quickly store, edit, and search for recipes by running a single command. This is ideal for the fast typists, amateur cooks, busy students or executives who want a simple way to store or search for simple recipes to cook.

The user interacts with it using a CLI-like interface, and it has a GUI created with JavaFX. It is written in Java and has about 8k LoC.

Listed below are my contributions to the project with majority of the credits given to AB-3 as it served as a strong foundation for our application.

* **New Feature** : Added the View Command feature.
  * What it does: Allows the user to view the contents stored in a saved recipe.
  * Justification: This feature is a necessary feature to allow user to view the contents of their saved recipes.
  * Highlights: Wrote test cases for `ViewCommand` and `ViewCommandParser`.

* **New Feature** : Added click to view feature for the recipe list view.
  * What it does: Allows the user to view the contents stored in a saved recipe simply by 
    clicking the recipe card in the list.
  * Justification: This feature is a nice-to-have to improve the user-friendliness of the application.
  * Highlights: This enhancement requires the addition of a listener to update the main window in the event the selected recipe in the list changes.
    This was implemented in `MainWindow` to prevent `RecipeListPanel` being dependent on the implementation details of `MainWindow`

* [**New UI Component**](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/116) : Replaced existing result display text area with dynamically updating window. 
  * What it does: The new UI component parses the information stored within a recipe and displays it in a pleasant graphical form, 
    complete with varying fonts styles and highlighted boxes.  
  * Justification: This feature improves the user experience of our application by allowing users to efficiently obtain key information about a desired recipe.
  * Highlights: Contents within the window dynamically adjust to fit the window during resizing of the application. 
   
* **Code contributed**: Click [here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=oddcorner&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabAuthor=Oddcorner&tabRepo=AY2122S2-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)
  to see the code that I've contributed on RepoSense.

* **Project management**:
  * Took detailed minutes during [weekly meetings](https://docs.google.com/document/d/1NpyQ7--KhO6W1OKzQWIu728AawXXmeFbyTlrjPOvS4c/edit?usp=sharing) to aid in the planning and distribution of work for that week.

* **Enhancements to existing features**:
  * Updated the GUI Layout and components (Pull requests [\#49](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/49),
    [\#116](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/116))
  * Updated the GUI Styling (Pull requests [\#86](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/86))
  
  * Contributed to implementation details for Clear command 
  (Pull requests [\#127](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/127))
  * Bug fixes (Pull requests [\#33](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/33), 
    [\#58](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/58), 
    [\#164](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/164), 
    [\#169](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/169), 
    [\#223](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/223))
  
  * Wrote additional tests for existing features to increase coverage from 56.03% to 58.17% (
    Pull requests [\#150](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/150), 
    [\#172](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/172))

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `view` (Pull requests [\#145](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/145), 
    [\#259](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/259))
  * Developer Guide:
    * Added use cases, guarantees, MSS and Extensions for `view` feature.
    * Added manual testing instructions for `view` feature.

* **Community**:
  * Assisted team members with Git/GitHub issues.
  * Pull requests reviewed (with non-trivial review comments): 
    * [\#12](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/12#discussion_r810650288),
    [\#42](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/42#discussion_r813826563),
    [\#44](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/44#discussion_r814684465),
    [\#47](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/47#discussion_r816941190),
    [\#118](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/118#discussion_r831903024)
  * Reported bugs and suggestions for other teams in the class: 
    * [\#1](https://github.com/Oddcorner/ped/issues/1), 
      [\#2](https://github.com/Oddcorner/ped/issues/2), 
      [\#3](https://github.com/Oddcorner/ped/issues/3),
      [\#4](https://github.com/Oddcorner/ped/issues/4).
      More can be found [here](https://github.com/Oddcorner/ped/issues)
      

