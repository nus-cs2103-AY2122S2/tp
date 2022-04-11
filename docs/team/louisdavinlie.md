---
layout: page
title: Louis' Project Portfolio Page
---

[//]: # (@@author louisdavinlie)
### Project: Ultimate Divoc Tracker

Ultimate Divoc Tracker is a desktop app for managing COVID-19 contacts in school admin, optimized for use via a
Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast,
Ultimate Divoc Tracker can get your contact management tasks done faster than traditional GUI apps.

School administrators _(like teachers)_ in charge of managing COVID-19 can use UDT to easily track COVID-19 cases
amongst the student population with ease and concentrate on what matters most, the education of the students.

Given below are my contributions to the project.

* **New Feature**: `ClassCode` attribute for Person [\#38](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/38)
  * What it does:
    * Adds a `ClassCode` attribute for each person denoting their class number/code
    * Displays the `ClassCode` attribute on the person's card in the user interface
    * Stores the `ClassCode` attribute on a JSON file with the key as "classCode"
  * Justification: This attribute provides a way for schools to track COVID-19 in each classroom denoted by `ClassCode`
  * Highlights: This enhancement is used in the batch update status feature.
  * Credits: *{Jun Hong, Joshua}*


* **New Feature**: Batch update status when a person recovers [\#76](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/76)
  * What it does:
    * When a person recovers (`Status` changes from `Positive` to `Negative`) and there is no person in the same
    `ClassCode` whose `Status` is `Positive`, every person with the same `ClassCode` as the recovered person will have
    their status changed from `Close-Contact` to `Negative`.
    * The reverse (`Status` changes from `Negative` to `Positive`) is implemented by Joshua and Jun Hong.
  * Justification: Automate the tracking of COVID-19 in every `ClassCode`. If no more positive cases in a `ClassCode`,
  persons with `Close-Contact` status having the same `ClassCode` as the recovered person should change to `Negative`.
  * Highlights: This feature is called every time a person's `Status` is changed from `Positive` to `Negative`.
  It requires a check whether there are still positive cases in the `ClassCode` of the recovered person.


* **New Feature**: Batch update status based on the same activities [\#112](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/112)
  * What it does: Considers those with the same `Activity` close contacts instead of only those with the same
  `ClassCode` in the batch update status feature.
    * When a person becomes COVID-19 positive, those in the same `ClassCode` or `Activity` will have their status
    updated to `Close-Contact` if they are `Negative`.
    * When a person recovers from COVID-19, those in the same `ClassCode` or `Activity` will have their status updated
    to `Negative` if they are `Close-Contact` and they are not related to any `Positive` person in the same `ClassCode`
    or `Activity` as them.
  * Justification: If a person has the same `Activity` as another person, they should be considered as close contacts.
  Hence, this should also be included in the batch update status feature as one should be labeled as `Close-Contact`
  if the other is `Positive`.
  * Highlights: One person can have multiple `Activity` so the implementation is not the same as the `ClassCode`
  only implementation since one person can only have one `ClassCode`.


* **JUnit Test**:
  * Added test methods for:
    * Batch update status feature [\#71](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/71)
    * `Find` command [\#175](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/175)
    * `FindActivity` command [\#175](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/175)
    * `FindClassCode` command [\#175](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/175)
    * `FindStatus` command [\#175](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/175)


* **Enhancements to existing features**:
  * Displayed `Activity` on persons' cards [\#78](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/78)
  * Updated UI by adding different text colors according to `Status` [\#103](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/103)
  * Applied abstraction to different parts of the code [\#113](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/113) and [\#174](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/174)
  * Added assertions to the code [\#176](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/176)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=louisdavinlie&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


* **Documentation**:
  * User Guide:
    * Added documentation for saving and editing the data file [\#23](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/23)
    * Added command summary in user guide [\#23](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/23)
    * Added viewing help window section (help command) [\#183](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/183)
    * Added limitation on the automation of status [\#183](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/183)
  * Developer Guide:
    * Added documentation for updated AddCommand and EditCommand following the addition of ClassCode, Status and Activity attributes [\#98](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/98)
    * Added documentation for updated UI and Storage flow [\#98](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/98)
    * Added proposed enhancements for UDT [\#98](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/98)
    * Added a sequence diagram for findstatus command execution [\#183](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/183)
    * Added a sequence diagram for findclasscode command execution [\#183](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/183)
    * Added a sequence diagram for findactivity command execution [\#183](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/183)
    * Updated the documentation for the implementation of batch update with respect to the incorporation of the `activity` attribute [\#183](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/183)
    * Added and edited explanations for the role of batch update in AddCommand, DeleteCommand and EditCommand [\#183](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/183)


* **Community**:
  * PRs reviewed (with non-trivial review comments):
    * Add command for finding students by their Covid-19 Status [\#55](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/55)
    * Added command for finding students by their Class Code [\#56](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/56)
    * Batch student status update [\#62](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/62)
    * Move editStatus method to ModelManager [\#179](https://github.com/AY2122S2-CS2103T-T12-1/tp/pull/179)
  * Reported bugs and suggestions for other teams in the class:
    * AY2122S2-CS2103T-T09-4:
      * Inaccurate command message in results window [\#1](https://github.com/louisdavinlie/ped/issues/1)
      * Product does not show all items after series of steps [\#2](https://github.com/louisdavinlie/ped/issues/2)
      * Able to set expiry date to past dates [\#3](https://github.com/louisdavinlie/ped/issues/3)
      * Category is shown as required field in update product window [\#4](https://github.com/louisdavinlie/ped/issues/4)
      * Name and category fields can contain only symbols [\#5](https://github.com/louisdavinlie/ped/issues/5)
      * Invalid remind command [\#6](https://github.com/louisdavinlie/ped/issues/6)
      * Find command not working [\#7](https://github.com/louisdavinlie/ped/issues/7)
