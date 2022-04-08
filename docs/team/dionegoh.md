---
layout: page
title: Dione Goh's Project Portfolio Page
---
### Project: Amigos

Amigos is a desktop application to help tech-savvy university students manage their friendships by helping them to keep
track of important details. It is optimized for use via a Command Line interface while still having the benefits of a 
Graphical User Interface (GUI). The user enters commands through the GUI which is built using JavaFX. 
It is written in Java and has over 10kLoC.

Given below are my contributions to the project.

1. **New Feature: showfriend**
   * What it does: Allows the user to view the full details of a friend in Amigos on a single page (`ExpandedPersonCard`).
   * Justification: We added more attributes for a friend, such as description and logs. We also added events which
      contain references to `FriendName`. Thus, it is essential for the user to be able to view all these new features 
      for an individual friend in an uncluttered manner.
   * Highlights: Created an `ExpandedPersonCard` showing the full details of a friend.

2. **New Feature: command aliases**
   * What it does: Enables users to enter shorter command aliases instead of the usual command.
   * Justification: Allows experienced users familiar with the commands to enter commands faster.
   
3. **Enhancement to existing feature `addfriend`**: 
    * What it does: Allows users to add a friend into address book by typing in the relevant command into the GUI.
    * Highlights:
      * Added a new `Description` field for a `Friend`
      * Made other fields in `Friend` optional and only `FriendName` compulsory, so that users who do not know full details of a friend
        can still add the friend into `Amigos`.
      
4. **Enhancement to existing feature `deletefriend`**: 
    * What it does: Allows users to delete a friend from `Amigos`.
    * Highlights: Users can delete a friend either by entering the `FriendName` or a valid `Index`. 
      Users can delete an existing friend by `FriendName` even if the
      friend is not currently being shown on the filtered list on the GUI. 

5. **Enhancement to existing feature `editfriend`**:
    * What it does: Allows users to edit a friend from `Amigos`.
    * Justification: Users can edit a friend either by entering the `FriendName` or a valid `Index`.
    * Highlights: The prefix `cn/` is used to identify the current `FriendName`. `nn/` is used for the
      edited `FriendName`.

6. **Enhancement to existing feature `findfriend`**:
    * What it does: Allows users to search for friends from `Amigos` using keywords.
    * Highlights: Keywords are not limited to `FriendName`. `Tag` and `LogName` of a Person are also
      searched. The user input is split according to the prefixes (`n/` for `FriendName`, `t/` for `Tag`,
      `ttl` for `LogName`). Also, keywords do not have to be matched fully. As long as a substring is matched, then 
      the friend would be shown.
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=dionegoh&breakdown=true)

* **Project management**:
    * Update site-wide settings [\#21](https://github.com/AY2122S2-CS2103-F09-2/tp/pull/21)
    * Maintained issue tracker for milestone v1.2
    
* **Documentation**:
    * User Guide:
        * Add documentation for the features `addfriend`, `deletefriend`, and `editfriend`', `showfriend`,
          `listfriends`, and `clear`.
        * Add documentation for command aliases.

    * Developer Guide:
        * Add implementation and sequence diagram for `addfriend`
        * Add implementation and activity diagram for `findfriend`
        * Add implementation and object diagram for `showfriend`
        * Add use cases for `addfriend`, `deletefriend`, `editfriend`.
