---
layout: page
title: jessicajacelyn's Project Portfolio Page
---

- [1. New Feature](#NewFeature)
- [2. Code contributed](#Codecontributed)
- [3. Project management](#Projectmanagement)
- [4. Enhancements to existing features](#Existing)
- [5. Documentation](#Documentation)

## Project: Mr. Agent

Mr. Agent is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Mr. Agent can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

## <a id="NewFeature"></a>**1. New Features**
* **New Feature**: Added the ability automatically remove passed appointments from the appointment list (Pull request [\#50](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/50)) 
  * What it does: Prevents user from seeing passed appointments in their appointment list without deleting them manually.
  * Justification: This feature helps the user in managing upcoming appointments better by not mistaking passed appointments as upcoming one. 
  * Challenges: Instead of adding another JSON file to store passed appointments (which could waste storage), the passed appointments were kept in the same JSON file as the upcoming appointments. Thus, checking for passed appointments would have to be done well when the application is launched to avoid corruption of the appointment data file and for the correct appointments to be transferred to passed appointment list. 

* **New Feature**: Added the ability automatically remove expired record from the record list (Pull request [\#50](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/50)) 
  * What it does: Prevents user from seeing expired records from each client's detail, where their records are listed, without having to remove them manually. 
  * Justification: This feature helps the user in managin their clients records by not showing expired ones. 
  * Challenges: Instead of adding another JSON file to store expired records (which could waste storage), the passed records were kept in the same JSON file as the active records. Thus, checking for expired records would have to be done well when the application is launched to avoid corruption of the record data file and for the correct records to be transferred to expired record list. 
  
* **New Feature**: Added the ability to list passed appointments (Pull request [\#50](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/50)) 
  * What it does: Allows user view all expired record at one go.
  * Justification: Even though they are expired records, user might still want to refer to it for future uses and it would be meaningless to store the expired records without having the ability to view them. 
  * Credits: AB3

* **New Feature**: Added the ability to list expired record (Pull request [\#50](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/50)) 
  * What it does: Allows user view all passed appointments at one go.
  * Justification: Even though they are passed appointments, user might still want to refer to it for future uses and it would be meaningless to store the passed appointments without having the ability to view them. 
  * Credits: AB3

* **New Feature**: Added the ability to find insurance (Pull request [\#72](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/72))
  * What it does: Allows user find and view insurance by insurace name as keyword
  * Justification: User could have stored many insurances in the application storage and it would be helpful and efficient for them to search those insurances quickly by their names.  
  * Highlights: This feature is made extensible to ease the process of editing/adding find parameters in the future
  * Credits: AB3

## <a id="Codecontributed"></a>2. Code contributed
* [Repo link](https://github.com/jessicajacelyn/tp)
* [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=sicajacelyn&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=jessicajacelyn&tabRepo=AY2122S2-CS2103-F09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* [Pull Request Link](https://github.com/AY2122S2-CS2103-F09-3/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Ajessicajacelyn)

## <a id="Projectmanagement"></a>**3. Project management**
  * Maintained repo Pull Requests and Issue Tracker in GitHub.
  * Managed releases `v1.3` - `v1.5` (3 releases) on GitHub.
  * Hosted weekly team meeting
  * Helped team members to debug
  * Helped team to improve code style and quality
  * Fix javadocs and documentations typo and errors for team (Pull requests [\#134](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/134), [\#135](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/135))
  * Update project track [documentation](https://docs.google.com/document/d/1YnxPw8cAvkEcVgljEb4Ux5qUX_KnXIDYhm5BC7UsAq8/edit?usp=sharing)

## <a id="Existing"></a>**4. Enhancements to existing features**
  * Redesigned the GUI from scratch (can be seen from README.md)
  * Wrote most of the tests for existing features (Pull requests [\#88](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/88), [\#94](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/94), [\#97](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/97))
  * Add more sample data to components

## <a id="Documentation"></a>**5. Documentation**
  * User Guide:
    * Added documentation for the features `appointment history` and `expired records` [\#60](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/60)
    * Proofread and edit typo errors for the whole guide [\#119](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/119)
  * Developer Guide:
    * Updated implementation details of the Logic component
    (Pull request [\#110](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/110))
     * Added implementation details of the List History feature
    (Pull request [\#110](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/110))
    * Updated use case for list client
    (Pull request [\#27](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/27))
    * Updated value proposition,target user profile and glossary.
    (Pull requests [\#25](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/25), [\#214](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/214))
    * Proofread and edit typo errors for the whole guide [\#131](https://github.com/AY2122S2-CS2103-F09-3/tp/pull/131)
