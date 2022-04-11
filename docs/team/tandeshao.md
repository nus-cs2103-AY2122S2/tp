---
layout: page  
title: Tan De Shao's Project Portfolio Page
---

### Project: AddressBook π (Abπ)

AddressBook pi (Abπ) is a 360° all-rounded desktop app for managing contacts, optimized for use via a Command Line Interface (CLI). Abπ is catered towards fast-typers and individuals who want an organized address book with key features including efficient filtering of contacts based on various attributes (name, email, tags, etc.), copying of all emails in the address book and a memo section that allows you to note down notable details about a person.

The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 9k LoC.


**Summary of contributions**

* Code contributed: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tandeshao&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)

* **Features and Enhancement implemented**
  * Enhancement of the FindCommand (Pull requests [\#55](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/55), [\#118](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/118), [\#268](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/268), [\#192](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/192))
    * What it does: Allows the user to search for a contact in Abπ by using any of the attributes that are available to a person. 
    * Justification: This feature enhances the original find command by increasing the scope of the search. The previous find command only allows users to search a contact by their name but with the newly enhanced find command, users are now able to search contacts based on a combination of attributes (Name, Phone number, Email, Address, Memo, Contacted Date and Tag).     
    * Highlights: To develop the find command, we identified the potential problems users might face when using the command and from there, try to come up with an appropriate design that would solve their problems. However, one problem encountered when adopting this approach to development is that it is impossible to find a solution that would solve all the potential problems the user might face. Although some implementations might solve a specific problem, they might also give rise to other problems. Hence, there are always trade-offs to consider when a certain implementation design is proposed.

  <div style="page-break-after: always;"></div>

  <br>

  * Scrub Command (Pull requests [\#130](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/130), [\#139](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/139), [\#177](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/177), [\#182](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/182))
    * What it does: Allows the users to delete multiple contacts from the address book by specifying the criteria to delete a person through the use of prefixes.
    * Justification: Previously, to delete a person from the address book, the user would have to manually delete the contacts 1 by 1 through the use of the `delete` command. However, with the `scrub` command, users now have a means to quickly delete multiple contacts from the address book. 
    * Highlights: For deletion of contacts, a strict matching criteria was used (exact word matching) as contact deletion is considered to be a risky operation. Any unintended removal of contacts would be a detrimental feature flaw for the application. Hence, an exact word match criteria is employed where `scrub p/90400204` would only scrub contacts that have the phone number "90400204". 

  
  * AddTag command (Pull requests [\#255](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/255), [\#171](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/171))
    * What it does: Allows users to append one or more tags to a specified person in the address book.
    * Justification: Before the implementation of the add tag command, users are only able to edit the tag attribute of a person through the use of the `edit` command. As the `edit` command completely replaces the existing tags, it would be inconvenient for the user if he/she would only like to append a new tag to the existing tags that are available to the specified person. Hence, to help users solve that problem, the `addtag` command was developed. 
    * Highlights: This feature required careful implementation as we would have to consider how Abπ should react if a user tried to append an existing tag to the person's tag attribute.
    
  
  * Add tests for the prefix class (Pull requests [\#76](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/76))
  
  * Add tests for the ArgumentMultiMap class (Pull requests [\#77](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/77))
  
  * Backup system (Pull requests [\#147](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/147), [\#246](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/246))
    * What it does: Backups the Abπ data so that whenever the "addressbook.json" file is corrupted, users would not lose their existing data of Abπ.
    * Justification: Every time the original data file is corrupted, Abπ would load up an empty address book and if a user keys in a valid command, the empty data would be saved to the "addressbook.json" file. With that, the original data file of the Abπ is lost and this is a major feature flaw for the application. Hence, a backup system is created.
    

<div style="page-break-after: always;"></div>

<br>

* **Documentation**: 
  * User Guide:
    * Added documentation for the feature `find`: [\#123](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/123), [\#22](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/22/files)
    * Added documentation for the feature `scrub` and `addtag`: [\#182](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/182), [\#196](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/196)
    
  
  * Developer Guide:
    * Added implementation details of the `find` feature: [\#123](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/123), [\#315](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/315)
    * Added use cases for Abπ: [\#298](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/298)
    * Added conventions: [\#300](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/300), [\#302](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/302)
    * Added implementation details for the backup feature: [\#319](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/319)
    

* **Community**:
  * Reported bugs and suggestions for other teams in the class: [Link to reported bugs/suggestions for other teams](https://github.com/tandeshao/ped/issues)
  * All other PRs reviewed: [Link to reviewed PRs](https://github.com/AY2122S2-CS2103T-T17-4/tp/pulls?q=is%3Apr+reviewed-by%3Atandeshao+is%3Aclosed+sort%3Acomments-asc)
