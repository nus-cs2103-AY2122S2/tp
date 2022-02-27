---
layout: page
title: Developer Guide
---
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**
TODO

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

TODO

--------------------------------------------------------------------------------------------------------------------

## **Design**

TODO

### Architecture

TODO

### UI component

TODO

### Logic component

TODO

### Model component

TODO

### Storage component

TODO

### Common classes

TODO

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

TODO

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:
- is a private tutor
- has multiple students and classes
- can type fast
- skilled at using the command line

**Value proposition**:\
If a tutor has many students, it may be difficult to keep track of all of the students and the rates offered to each of them. TeachWhat! solves this issue by helping tutors manage their schedule, students and income more efficiently.

### User stories
Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​    | I want to …​                                             | So that I can…​                                              |
|---------|------------|----------------------------------------------------------|--------------------------------------------------------------|
| `* * *` | user       | add my student's information                             | keep track of their progress                                 |
| `* * *` | user       | delete classes                                           | clear old classes                                            |
| `* * *` | user       | delete students and their details                        | reduce clutter of old students and keep their privacy intact |
| `* * *` | user       | add classes to the list and assign students to the class | have an overview of which students are attending the class   |
| `* *`   | a new user | clear and reset my entire list of classes and students   | add actual data after testing the program out                |
| `* *`   | user       | specify the type of class when creating one              | know if a class is permanent or a temporary class            |


### Use cases

#### Add a temporary class
**System:** ClassBook  
**Use case:** UC1 - Add a temporary class   
**Actor:** User

**MSS**
1. User adds a class with a specified classname.
2. ClassBook requests for the date of the class.
3. User provides date of the class.
4. ClassBook requests for the starting time of the class.
5. User provides starting time of the class.
6. ClassBook requests for the duration of the class.
7. User provides duration of the class.  
   Use case ends.

**Extensions**
* 1a. User did not provide any classname.
    * 1a1. ClassBook shows an error message.  
      Use case resumes at step 1.


* 3a. User did not provide a date or date has invalid format.
    * 3a1. ClassBook shows an error message.  
      Use case resumes at step 2.


* 3b. User already provided the date in command.  
  Use case resumes at step 4.


* 5a. User did not provide a starting time or starting time has invalid format.
    * 5a1. ClassBook shows an error message.  
      Use case resumes at step 4.


* 5b. User already provided the starting time in command.  
  Use case resumes at step 6.


* 7a. User did not provide the duration of class or duration of class has invalid format.
    * 7a1. ClassBook shows an error message.  
      Use case resumes at step 6.


* 7b. User already has an existing class overlapping with the specified starting, ending time and date.
    * 7b1. ClassBook shows an error message.  
      Use case resumes at step 2.


* 7c. User already provided the duration of class in command.  
  Use case ends.


* \*a. At any time, User chooses to cancel the creation of a new class.
    * \*a1. ClassBook requests to confirm the cancellation.
    * \*a2. User confirms the cancellation.  
      Use case ends.


#### Add a recurring class
**System:** ClassBook  
**Use case:** UC2 - Add a recurring class   
**Actor:** User

**MSS**
1. User adds a class with a specified classname.
2. ClassBook requests for the date of the first class.
3. User provides date of the first class.
4. ClassBook requests for the starting time of the class.
5. User provides starting time of the class.
6. ClassBook requests for the duration of the class.
7. User provides duration of the class.  
   Use case ends.

**Extensions**
* 1a. User did not provide any classname.
    * 1a1. ClassBook shows an error message.  
      Use case resumes at step 1.


* 3a. User did not provide the date of the first class or date has invalid format.
    * 3a1. ClassBook shows an error message.  
      Use case resumes at step 2.


* 3b. User already provided the date of the first class in command.  
  Use case resumes at step 2.


* 5a. User did not provide a starting time or starting time has invalid format.
    * 5a1. ClassBook shows an error message.  
      Use case resumes at step 4.


* 5b. User already provided the starting time in command.  
  Use case resumes at step 6.


* 7a. User did not provide a duration of the class or duration of class has invalid format.
    * 7a1. ClassBook shows an error message.  
      Use case resumes at step 6.
  

* 7b. User already has an existing class overlapping with the specified starting, ending time and date.
    * 7b1. ClassBook shows an error message.  
      Use case resumes at step 2.


* 7c. User already provided the ending time in command.  
  Use case ends.


* \*a. At any time, User chooses to cancel the creation of a new class.
    * \*a1. ClassBook requests to confirm the cancellation.
    * \*a2. User confirms the cancellation.  
      Use case ends.

#### Delete a class

**System:** ClassBook  
**Use case:** UC3 - Delete a class   
**Actor:** User

**MSS**
1. User requests to list classes
2. ClassBook shows a list of classes
3. User requests to delete a specific class in the list
4. ClassBook deletes the class  
   Use case ends.

**Extensions**
* 2a. The list is empty.  
  Use case ends.


* 3a. The given index is invalid.
    * 3a1. ClassBook shows an error message.  
      Use case resumes at step 2.


* 3b. The specified class still has students assigned to it.
    * 3b1. ClassBook shows a warning message.
    * 3b2a. User confirms deletion.  
      Use case ends.
    * 3b2b. User cancels deletion.  
      Use case ends.

#### Assign a student to a class

**System:** StudentBook, ClassBook  
**Use case:** UC4 - Assign a student to a class  
**Actor:** User

**MSS**
1. User requests to list students
2. StudentBook shows a list of students
3. User selects the student
4. User requests to list classes
5. ClassBook shows a list of classes
6. User selects the class to assign the student to  
   Use case ends.

**Extensions**
* 2a. The list is empty.  
  Use case ends.


* 3a. The given index is invalid.
    * 3a1. StudentBook shows an error message.  
      Use case resumes at step 2.


* 5a. The list is empty.
    * 4a1. ClassBook shows a warning message.  
      Use case ends.


* 6a. The given index is invalid.
    * 6a1. ClassBook shows an error message.  
      Use case resumes at step 5.

#### Delete a student

**System:** StudentBook  
**Use case:** UC5 - Delete a student  
**Actor:** User

**MSS**
1. User requests to list students
2. StudentBook shows a list of students
3. User requests to delete a specific student in the list
4. StudentBook deletes the student  
   Use case ends.

**Extensions**
* 2a. The list is empty.  
  Use case ends.


* 3a. The given index is invalid.
    * 3a1. StudentBook shows an error message.  
      Use case resumes at step 2.

### Non-Functional Requirements

1. TeachWhat! able to run on all mainstream OS that has Java 11 or above installed
2. Should be able to hold up to 1000 students and classes without a noticeable sluggishness in performance for typical 
usage
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be 
able to accomplish most of the tasks faster using commands than using the mouse
4. A new user should be able to pick up on how to use TeachWhat! within 10 minutes
5. TeachWhat! must boot up within 5 seconds on a device that is under normal load (i.e. not running cpu intensive applications in the background).

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Command Line**: A text interface for your computer.


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

TODO

### Deleting a student

TODO

### Saving data

TODO
