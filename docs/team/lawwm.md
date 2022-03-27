---
layout: page
title: Wei Ming's Project Portfolio Page
---
# Skeleton of the Project Portfolio Page for Wei Ming

### Project: ModuleMateFinder

ModuleMateFinder is a desktop address-book-like application used to keep track of your friends' contacts, as well as the modules they are taking. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.


* **New Feature**: Added the ability to sort persons by specified fields in `Person`.
  - **What it does**: Allows the user to sort all `Person` by specified fields and specify ascending or descending order.
  - **Justification**: This feature improves the product significantly because a user can order persons based on what their needs. e.g. sort by status to track people by their status.
  - **Highlights**: This enhancement affects existing attributes of persons to be added in future. It required an in-depth analysis of design alternatives.
  - **Credits**: Syntax inspired by SQL language. Implementation inspired by [SimpleDB](http://cs.bc.edu/~sciore/simpledb/).


* **New Feature**: Added the ability to copy details based on specified fields.
  - **What it does**: Allows the user to copy information from an individual `Person` or all `Person`, and specify format.
  - **Justification**: This feature improves the product as user might want to copy and paste information from ModuleMateFinder. 
    e.g. User can transfer information to an excel file using csv format.


* **New Feature**: Added Least Recently Used(LRU) cache to `Comment` to improve user experience.
  - **What it does**: Caches the `Comment` user adds to a side window. User can then add `Comment` from side window directly to `Person` instead of typing it in full.
  - **Justification**: `Comments` might be very long to type, and same comments might be repeated for different `Person`. Therefore, it is better to cache the comments for user to reuse.
  - **Highlights**: This feature required an understanding of the UI component to add a panel to the right as well as Storage component to save an additional table. 
     Had to adapt LRU cache data structure to `ObservableList` as well.
  - **Credits**: LRU implementation inspired by [GeeksforGeeks](https://www.geeksforgeeks.org/lru-cache-implementation/).


* **New Feature**: Added delete modules command.
  - **What it does**: Allows the user to delete modules by specifying the module name.
  - **Justification**: This feature allows user to remove specified modules for a specific person.


* **New Feature**: Added clear modules command.
  - **What it does**: Allows the user to clear all modules taken by a person.
  - **Justification**: Clear all modules taken by a person so user does not have to delete each module individually. 
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=lawwm&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)


* **Documentation**:
  - User Guide:
    - Added documentation for the features `deletemodule`, `clearmodules`, `sort` and `copy`.

  - Developer Guide:
    - Added implementation details of the `sort` feature [(PR #86)](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/86/files).


**Community**:
- Reviewed all PRs done by WeiJie and Julio
  - PRs reviewed for Julio (with non-trivial review comments): 
    - [\#62](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/62)
    - [\#90](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/90)
  - PRs reviewed for WeiJie (with non-trivial review comments): 
    - [\#72](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/72) 
    - [\#81](https://github.com/AY2122S2-CS2103T-T13-4/tp/pull/81)

    
  
