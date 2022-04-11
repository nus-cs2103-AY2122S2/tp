---
layout: page
title: Wei Ming's Project Portfolio Page
---
# Skeleton of the Project Portfolio Page for Wei Ming

### Project: ModuleMateFinder

ModuleMateFinder is a desktop address-book-like application used to keep track of your friends' contacts, as well as the modules they are taking. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=lawwm&tabRepo=AY2122S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **New Feature**: Added the ability to sort persons by specified fields in `Person`. [#44](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/44)
  - **What it does**: Allows the user to sort all `Person` by specified fields and specify ascending or descending order.
  - **Justification**: This feature improves the product significantly because a user can order persons based on what their needs. e.g. sort by status to track people by their status.
  - **Highlights**: This enhancement affects existing attributes of persons to be added in future. It required an in-depth analysis of design alternatives. It also required a design that made it easily extendable for fields of `Person` if more were to be added.
  - **Credits**: Syntax inspired by SQL language. 

* **New Feature**: Added the ability to copy details based on specified fields. [#78](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/78)
  - **What it does**: Allows the user to copy information from an individual `Person` or all `Person`, and specify format.
  - **Justification**: This feature improves the product as user might want to copy and paste information from ModuleMateFinder. 
    e.g. User can transfer information to an excel file using csv format.
  - **Highlights** Difficulty in implementing each format, especially the json format since the command 
  allowed user to choose variable fields for person to copy, while existing `JsonAdaptedPerson` required all fields to be displayed.
  

* **New Feature**: Added delete modules command. [#16](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/16)
  - **What it does**: Allows the user to delete modules by specifying the module name.
  - **Justification**: This feature allows user to remove specified modules for a specific person.


* **New Feature**: Added clear modules command. [#16](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/16)
  - **What it does**: Allows the user to clear all modules taken by a person.
  - **Justification**: Clear all modules taken by a person so user does not have to delete each module individually. 
  

* **Enhancement**: Refactor archive feature. [\#195](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/195)
  - **What it does**: Refactor archive feature to ensure clearer separation between `Logic` and `Model`. Shifted archive command logic from `UI` back into `Logic` and `ArchiveCommand` 
  - **Justification**: This reduces coupling and increases code quality. Previous implementation drifted slightly far from architecture with clear separation between `UI`, `Logic`, `Storage` and `Model` components.
  It also made code easier to read and understand which made bug fixing easier.

- **Bug Fix**: Fix bugs resulting from undo/redo and archive. [\#195](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/195)
  - **What it does**: Fixed implementation of undo/redo to store `ArchiveBook` as well in order to account for archive.

  

* **Documentation**:
  - User Guide:
    - Added documentation for features `deletemodules` and `clearmodules` [(PR #17)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/17).
    - Added documentation for the features `sort` [(PR #44)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/44) and `copy` [(PR #78)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/78)
    - Added documentation for `undo` to reflect commands that can be undone but were not included. [\#195](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/195)
    - Fixed broken links [(PR #105)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/105). 

  - Developer Guide:
    - Added implementation details of the `sort` feature [(PR #86)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/86).
    - Added sort sequence diagram [(PR #86)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/86)
    - Updated Model and Storage class diagram to reflect archived `AddressBook` [(PR #195)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/195)
    - Added terms in glossary. [(PR #44)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/44)


**Community**:
- Reviewed all PRs done by WeiJie and Julio.
  - PRs reviewed for Julio (with non-trivial review comments): 
    - [\#62](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/62) 
    - [\#90](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/90) 
    - [\#197](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/197) 
  - PRs reviewed for WeiJie (with non-trivial review comments): 
    - [\#72](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/72) 
    - [\#81](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/81)
    - [\#124](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/124)


- Helping team members with bugs.
  - Helped Bryan solve issue with switching between Archive and AddressBook, for both `Model` and `Storage` component.
    - [\#116](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/116) Commit `5bb54ed`
    
  - Helped WeiJie solve find source of failing tests.
    - [#\124](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/124) Commit `7c11303`
    - [#\135](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/135) Commit `45c9683`

    
  
