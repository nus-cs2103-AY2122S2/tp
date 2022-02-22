---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Requirements

### Product scope

**Target user profile**:

Students who...

* want to keep track of tech internships
* are disorganised and tend to miss deadlines
* prefer desktop apps over other types
* can type fast
* prefer typing to mouse interactions
* are reasonably comfortable using CLI apps

**Value proposition**: 

* Track (View all your internship applications at a glance)
* Remind (Be reminded of your upcoming assessments)
* Review (Comment on each stage of the application process for future self-improvement or review the company’s hiring process)

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​        | I want to …​                                                               | So that I can…​                                          |
|----------|----------------|----------------------------------------------------------------------------|----------------------------------------------------------|
| `* * *`  | user           | add a new company                                                          | add new internship roles to said company                 |
| `* * *`  | user           | add a new internship role                                                  | keep track of said internship application process        |
| `* * *`  | user           | delete a company                                                           | remove companies that I am no longer interested in       |
| `* * *`  | user           | delete an internship role                                                  | remove internship roles that are outdated or complete    |
| `* * *`  | user           | see a summary of my internship applications at a glance                    | have a general overview of the status of my applications |
| `* *`    | user           | search keywords like company name, internship roles, etc.                  | locate my internship application quickly                 |
| `* *`    | user           | sort company names in alphabetical order                                   | locate a company that I may have forgotten about         |
| `* *`    | user           | sort applications in chronological order of deadlines                      | keep track of the timeline of applications               |
| `* *`    | user           | modify each item                                                           | keep Tinner and all its contents up to date              |
| `* *`    | user           | be reminded of upcoming deadlines                                          | be on time with my applications                          |
| `* *`    | organised user | tag applications and events                                                | keep items compartmentalised and thus easier to access   |
| `* *`    | long term user | archive/hide irrelevant events                                             | not get distracted by what is not important              |
| `* *`    | new user       | learn how to use the application via a guide                               | use the application periodically with ease               |
| `*`      | user           | export calendar dates of important events into a .ics or .pdf file         | keep track of my events on an external platform          |
| `*`      | user           | export a list of interview reviews of different companies into a .csv file | share my experience with juniors and peers               |
| `*`      | user           | mark certain entries as my favourites                                      | view those that I am more interested in at a glance      |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Tinner` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a company**

Guarantees: a company will be successfully created

**MSS**

1. User requests to add a specific company and its information in the list
2. Tinner adds a company to the list
3. Tinner displays the list of companies

   Use case ends

**Extensions**

 1a. The input does not adhere to the command format <br/>
  1a1. Tinner shows an invalid input format error message <br/>
     Use case resumes at step 1

 1b. The specific company is already stored <br/>
  1b1. Tinner shows a duplicate company error message <br/>
     Use case resumes at step 1

**Use case: UC02 - Add an internship role**

Precondition: the company to which the internship role will belong has already been created

Guarantees: an internship role will be successfully created

**MSS**

1. User requests to view a list of companies
2. User requests to add an internship role and provides the relevant details
3. Tinner adds the internship role to the list of roles of the specific company

   Use case ends

**Extensions**

 1a. The input does not adhere to the command format <br/>
  1a1. Tinner shows an invalid input format error message <br/>
     Use case resumes at step 1

**Use case: UC03 - Delete a company**

Precondition: there exists one or more companies in the list of companies

Guarantees: a company is successfully removed from the list of companies

**MSS**

1. User requests to view a list of companies
2. Tinner shows a list of companies and the associated internship roles
3. User requests to delete a specific company in the list
4. Tinner deletes the company

   Use case ends

**Extensions**

 3a. The input does not adhere to the command format <br/>
  3a1. Tinner shows an invalid input format error message <br/>
     Use case resumes at step 2

 3b. The input company index is invalid <br/>
  3b1. Tinner shows a company index out of bounds error message <br/>
     Use case resumes at step 2

**Use case: UC04 - Delete an internship role**

Precondition: there exists one or more internship roles associated with a company in the list of companies

Guarantees: a specified internship role is successfully removed from the associated company

**MSS**

1. User requests to view a list of companies
2. Tinner shows a list of companies and the associated internship roles
3. User requests to delete a specific internship role of a company in the list
4. Tinner deletes the internship role

   Use case ends

**Extensions**

 3a. The input does not adhere to the command format <br/>
  3a1. Tinner shows an invalid input format error message <br/>
     Use case resumes at step 2

 3b. The input company index is invalid <br/>
  3b1. Tinner shows a company index out of bounds error message <br/>
     Use case resumes at step 2

 3c. The input internship role index is invalid <br/>
  3c1. Tinner shows an internship role index out of bounds error message <br/>
     Use case resumes at step 2

**Use case: UC05 - List all companies**

Precondition: there exist one or more companies stored in Tinner

Guarantees: every company stored in Tinner will be shown

**MSS**

1. User requests to view a list of companies
2. Tinner displays all companies in its storage

   Use case ends

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed
2. Should be able to hold up to 1000 items (companies and internship roles) without a noticeable sluggishness in performance for typical usage
3. Should require no installation
4. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse
5. Should be responsive and have a latency of less than 3 seconds

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: Command Line Interface
* **MSS**: Main Success Scenario
