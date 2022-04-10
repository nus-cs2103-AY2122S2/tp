---
layout: page
title: Koh Su En's Project Portfolio Page
---

- [1. New Feature](#NewFeature)
- [2. Code contributed](#Codecontributed)
- [3. Project management](#Projectmanagement)
- [4. Enhancements to existing features](#Enhancement)
- [5. Documentation](#Documentation)

## Project: Mr. Agent

Mr. Agent is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Mr. Agent can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

## <a id="NewFeature"></a>1. New Features
* **New Feature**: Added the ability to add record commands that allows the user add new insurance record to specific client. (Pull requests [\#48](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/48/files) , [\#64](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/64/files))
  * What it does: allows the user add insurance record to client one at a time. 
  * Justification: This feature helps the target user (insurance agent) to keep track of each client's insurance bought by adding them to the app.
  * Highlights: This feature requires in-depth knowledge about other components ,such as insurance and client, as there exist dependency between the components
  * Challenges: As the record consist of information about the client and insurance, the implementation needs to provide an accurate linkage between the existing client and insurance when adding a new record.
  * Credits: AB3

* **New Feature**: Added the ability to delete record commands 
  (Pull requests [\#48](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/48/files))
  * What it does: allows the user delete insurance record of a specific client one at a time.
  * Justification: This feature is crucial as it aids the user to manage the records that they deemed that are no longer needed, increasing the tidiness when viewing the client's record.
  * Highlights: This feature is made extensible so that it is easier to edit the parameters used in deciding which record to delete
  * Credits: AB3
  
* **New Feature**: Added the ability to edit record commands (Pull requests [\#82](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/82/files))
  * What it does: allows the user edit a record's detail of specific a client, multiple changes at one go.
  * Justification: This feature is crucial it is common that user would key in the wrong information or the record's information required some updates. Hence, it would be handy to have this feature to ease user in editing the current record's detail instead of requiring them to delete and add a new record.
  * Highlights: This feature is made extensible so that if more attributes is required to be stored, it does not require major change to the code base.
  * Challenges: Editing of a record could possibly cause a duplication of records which is undesired, also, parameters provided by the user may not be correct. 
    Hence extensive checking is needed to check that the edited record is not stored in the database and checking the validity of the parameters.
  * Credits: AB3
  
* **New Feature**: Added the ability to list record commands (Pull requests[\#70](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/70), [\#71](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/71))
  * What it does: allows the user view all unexpired record at one go.
  * Justification: It would be meaningless if the user are able to add record but could not view them at all, hence, it is crucial this feature is implememeted. With this feature, user can conveniently view all unexpired insurance records and view the details of each record.
  * Credits: AB3

* **New Feature**: Added the ability to find unexpired record commands (Pull requests [\#82](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/82/files))
  * What it does: allows the user find and view unexpired record by seaching with insurance type
  * Justification: In reality, the target user (insurance agent) could be selling a variety types of insurance and hence it would be helpful to have such a function for them to know which user had bought the insurance type.
  * Highlights: This feature is made extensible to ease the process of editing/adding find parameters in the future
  * Credits: AB3

## <a id="Codecontributed"></a>2. Code contributed
* [Repo link](https://github.com/seksek13/tp)
* [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=seksek13&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)
* [Pull Request link](https://github.com/AY2122S2-CS2103-F09-3/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Aseksek13)

## <a id="Projectmanagement"></a>3. Project Management
  * Managed releases `v1.3` on GitHub
  * Contributed incrementally and iteratively by following the [forking workflow](https://nus-cs2103-ay2122s2.github.io/website/se-book-adapted/chapters/revisionControl.html#forking-flow) strictly
  * Helped to test and debug other components of the application

## <a id="Enhancement"></a>4. Enhancements to existing features
  * Updated the GUI color scheme and design (Pull requests [\#137](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/137), [\#138](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/138))
  * Wrote additional tests for existing features to increase coverage from 51.64% to 52.59% (Pull requests [\#36](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/99))

## <a id="Documentation"></a>5. Documentation
  * User Guide:
    * Added documentation for the record features, including list/add/delete/find/edit `Record` command (Pull requests [\#117](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/117), [\#196](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/196))
  * Developer Guide:
      * Added user stories for the record feature. (Pull requests [\#103](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/103/files))
      * Updated implementation details of the `UI`, `Logic` and `Record` component (Pull requests https://github.com/AY2122S2-CS2103-F09-3/tp/pull/103/files)[\#103](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/103/files) ,[\#109](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/109/files))
      * Added implementation details of the `add record` feature. (Pull requests [\#103]()
      * Added manual testing instructions (Pull request [\#230](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/230))

