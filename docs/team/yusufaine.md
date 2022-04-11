---
layout: page
title: Yusuf's Project Portfolio Page
---

### Project: McKitchen

McKitchen (My-CLI-Kitchen) is a desktop application that can quickly store, edit, and search for recipes by running a single command. This is ideal for the fast typists, amateur cooks, busy students or executives who want a simple way to store or search for simple recipes to cook.

The user interacts with it using a CLI-like interface, and it has a GUI created with JavaFX. It is written in Java and has about 8k LoC.

Listed below are my contributions to the project with majority of the credits given to AB-3 as it served as a strong foundation for our application.
* [**New Model**](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/12): Added the skeleton code for what classes in McKitchen are going to be based and improved on. 
  * As our application is morphing AB-3, we had to modify some base files to suit our needs, and I took charge of updating the files in `model/`.
* [**New Parser**](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/47): Update AB3's Parser class for our own needs.
  * As we modified the files in `model/`, we also had to modify how user inputs were parsed and checked for validity.
* **New Feature**: Update the `Find` feature to find a Recipe based on the inputs given.
  * What it does: Allows users to search for a recipe that contains any of the keywords given. 
  * Justification: This features improves the user experience of our users by enabling them to search for recipes based on what ingredient they have on hand or feel like eating.
  * Highlights: This enhancement requires all recipes to be searchable as it should match the recipe's name, ingredient, or tag. I implemented this by creating `SearchSet` which represents a recipe into its keywords, making it easy to search and match.
* **New Feature**: Access (`edit`, `view`, `delete`) recipe by index.
  * What it does: Allows the above-mentioned features to work by specifying the index of the recipe in the current list view. 
  * Justification: Improve user-experience by alleviating the issue of long commands by allowing users to specify `-x <recipe number from list>` so that users would not have to type the full name of a recipe.
  * Highlights: This enhancement requires a new `Prefix` to denote that the data after it is an `Index`. I then had to add a new method to get a recipe based on its index and update the affected commands.

* **Modifications to existing features**:
  * Update `Exit`, `Help`, and `List` to work as how we intended for it to from AB-3's to McKitchen.
  * Achieved 100% code coverage for files in: `model/*`, `FindCommand`, and `FindCommandParser`, along with other files.


* **Code contributed**: Click [here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=yusufaine&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=yusufaine&tabRepo=AY2122S2-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false) to see the code that I've contributed on RepoSense.

* **Project management**:
  * Managed releases `v1.3` - `v1.4.1` (4 releases) on GitHub
  * Planned and organised [weekly meetings](https://docs.google.com/document/d/1NpyQ7--KhO6W1OKzQWIu728AawXXmeFbyTlrjPOvS4c/edit?usp=sharing) as a way to keep the team updated with each other's work and update them on what needs to be done for the week.
  * Aggregated issues to improve [code coverage](https://github.com/AY2122S2-CS2103T-T17-2/tp/issues/77) and address [issues found by other testers](https://github.com/AY2122S2-CS2103T-T17-2/tp/issues/219)

* **Documentation**:
  * User Guide:
    * Update the flow and formatting of the User Guide,
    * Ensured that it was more user-centric and friendly,
    * Added tips to features to help users understand how to best use certain features and included tips for advanced users 
    * Assisted the other members with writing the features they had implemented.
  * Developer Guide:
    * Update the flow and formatting of the Developer Guide
    * Assisted with the creation of UML diagrams for the Developer Guide
    * Update the undo/redo future feature to fit our need
    * Include enhancement to find as a future feature and explain its implementation

* **Community**:
  * Setting up the GitHub organisation and team repo
  * Maintaining the issue tracker
  * Release management
  * PRs reviewed (with non-trivial review comments): 
    * [\#127](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/127#discussion_r832093006), [\#128](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/128#issuecomment-1076317905), [\#172](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/172#discussion_r836634342), and [\#179](https://github.com/AY2122S2-CS2103T-T17-2/tp/pull/179#discussion_r838115404)
  * Contributed to forum discussions 
    * [Discussion on error handling](https://github.com/nus-cs2103-AY2122S2/forum/issues/21), [Help another student with a GUI issue that I had also faced and resolved](https://github.com/nus-cs2103-AY2122S2/forum/issues/112#issuecomment-1030658101), [Help another student understand and set up their forking workflow](https://github.com/nus-cs2103-AY2122S2/forum/issues/194#issuecomment-1055672552), and [Conversion to PDF -- Update header from AB-3 to the name of your project](https://github.com/nus-cs2103-AY2122S2/forum/issues/259)
  * Reported bugs and suggestions for other teams in the class
    * [\#7](https://github.com/yusufaine/ped/issues/7), [\#8](https://github.com/yusufaine/ped/issues/8), [\#19](https://github.com/yusufaine/ped/issues/19), [\#22](https://github.com/yusufaine/ped/issues/22). More can be found [here](https://github.com/yusufaine/ped/issues?q=is%3Aissue+is%3Aopen)
  
