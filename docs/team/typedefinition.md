---
layout: page
title: Terry's Project Portfolio Page
---

### Project: CinnamonBun

Small business owners and freelancers may find it difficult to keep track of all their clients.

CinnamonBun is an application that helps to record and manage a large number of clients and business transactions, thus allowing business owners to focus more on their business and less on bookkeeping.

CinnamonBun comes with a beautiful Graphical User Interface (GUI) while still being optimized for control via a Command Line Interface (CLI).

### My Contributions

* **New Feature**: Add the ability to add remarks to clients.
  * Functionality: Allow the user to add remarks to clients so that they can note down things about the client.
  * Justification: This function is necessary for users to add notes about the client that may not fit into any of the other fields.

* **New Feature**: Add the ability to append or remove fields from clients.
    * Functionality: Allow the user to only append, or only remove fields from clients.
    * Justification: Previously, there was only the `edit` function, which did not work well in certain situations. For example, it could not remove existing fields. If the client had multiple tags, and the user wanted to remove one tag, she would have to input every other tag except that one.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=TypeDefinition)

* **Project management**:
    * Managed releases `v1.2.0`, `v1.3.0-alpha` and `v1.3.0` (3 release) on GitHub.

* **Documentation**:
    * User Guide:
        * [Commands - Command Syntax](../UserGuide.md#command-syntax)
        * [Commands - User Guide Syntax](../UserGuide.md#user-guide-syntax)
        * [Commands - Fields and Tags](../UserGuide.md#fields-and-tags)
        * [Commands - Append Remark to Client](../UserGuide.md#append-remark-to-client-remark)
        * [Commands - Append Fields to Client](../UserGuide.md#append-fields-to-client-append)
        * [Commands - Remove Fields from Client](../UserGuide.md#remove-fields-from-client-remove)
        * [Special Features - Command History](../UserGuide.md#command-history)
    * Developer Guide:
        * [Design - Update diagrams](../DeveloperGuide.md#design)
        * [Implementations - Person, Field and Tag](../DeveloperGuide.md#person-field-and-tag)
        * [Implementations - Command history](../DeveloperGuide.md#command-history)
        * [Manual Testing - Adding a client](../DeveloperGuide.md#adding-a-client)
        * [Manual Testing - Append fields to a client](../DeveloperGuide.md#append-fields-to-a-client)
        * [Manual Testing - Remove fields from a client](../DeveloperGuide.md#remove-fields-from-a-client)
        * [Manual Testing - Add or remove remark from a client](../DeveloperGuide.md#add-or-remove-remark-from-a-client)
