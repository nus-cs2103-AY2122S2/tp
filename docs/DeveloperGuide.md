---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* TAddressBook is a student project based on [AddressBook Level 3](https://github.com/se-edu/addressbook-level3).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S2-CS2103-F10-1/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside components being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S2-CS2103-F10-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

###### Sequence Diagram: `delete`
The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create an `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the TAddressBook data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g. results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Student` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Student` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

#### Lab component (Subcomponent of Model)

The `Lab` Component is a subcomponent of the `Model` component.

* stores all lab-related data.
* stores all `Lab` objects related to a `Student` object in a `LabList` (each `Student` object has its own copy of a `LabList`).
* the `MasterLabList` stores all `Lab` objects added into the system thus far to act as a control list (there should only be 1 `MasterLabList` in the system at any given time).


### Storage component
**API** : [`Storage.java`](https://github.com/AY2122S2-CS2103-F10-1/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both TAddressBook data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the [`seedu.address.commons`](https://github.com/AY2122S2-CS2103-F10-1/tp/tree/master/src/main/java/seedu/address/commons) package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### `labadd`: Add Lab Command Feature
The `labadd` feature allows a CS2030S Lab TA to add a new unique `Lab` into the TAddressBook. The add lab command takes in 1 argument `LAB_NUMBER`.

The command format for add lab is `labadd l/LAB_NUMBER` where `LAB_NUMBER` should be an Integer between 0 and 20 inclusive.
e.g `labadd l/1`

The add lab command is implemented as follows:
1. When a `labadd` command is executed by the user, `AddLabCommandParser#parse(String)` will be invoked to parse the given command into a new `AddLabCommand` object with the given `LAB_NUMBER`. If the `LAB_NUMBER` is invalid, a `ParseException` will be thrown and displayed to the user.


2. `AddCommand#execute(Model)` will then execute with the current `Model` object in the system.


3. The `AddCommand` object will then check if the `Model` object already has the lab (in this case having a lab means that the `MasterLabList` has a `Lab` object with the same `LAB_NUMBER`), and if the `Model` already has the `Lab`, it will throw a new `CommandException`.


4. The `AddCommand` object will then check if the `Model` object's `UniqueStudentList` is empty, and if it is empty, the system will output a message to the user to notify the user that the student list is empty, however, the lab will still be added into the `MasterLabList` for storing.


5. The `AddCommand` object will then add the new `Lab` to the `MasterLabList` and the `LabList` of every student in the `UniqueStudentList`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Every `add(Lab)` operation on a `LabList` (this includes `MasterLabList` as it extends `LabList`) will sort the `LabList` by increasing `LAB_NUMBER`.
</div>


The sequence for parsing the input is similar to the one shown in [this sequence diagram](#delete-sequence-diagram) above.

The following **UML sequence diagram** shows the interaction between components during the execution of the `labadd` command:

<img src="images/AddLabCommandSequenceDiagram.png" width="850" />

<div markdown="span" class="alert alert-info">:information_source: **Note:** In the sequence diagram, `toAdd` refers to the `Lab` object with the given `LAB_NUMBER` to be added.<br><br>

The method `addLabToAll` in `UniqueStudentList` will call `LabList#add(Lab)`for all `Student`s in the list. This is left out of the sequence diagram as it is meant to be a higher level diagram.<br><br>

The implementation of `LabList#add(Lab)` was left out of the above sequence diagram as the diagram is meant to show the interaction between components at a higher level of abstraction.<br><br>

The sequence diagram for the implementation of `LabList#add(Lab)` will be shown separately below.

</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** Instead of putting a reference frame in the above UML diagram for the sequence diagram of `LabList#add(Lab)`, I chose to leave the reference frame out as PlantUML's reference frames are huge and would lead to clutter in the above sequence diagram thereby decreasing readability. Instead, this short note will suffice.
</div>

The following **UML sequence diagram** shows how `add(Lab)` is implemented in `LabList`:

<img src="images/LabListAddSequenceDiagram.png" width="550" />

<div markdown="span" class="alert alert-info">:information_source: **Note:** In the sequence diagram, `lab` refers to the `Lab` object with the given `LAB_NUMBER` to be added and `sortByLabNumber` is a `Comparator` that sorts the `Lab` objects by increasing `LAB_NUMBER`.
</div>

The following **UML activity diagram** shows what happens when a user executes a `labadd` command:

<img src="images/AddLabCommandActivityDiagram.png" width="600" />


### `filter`: Filter Students by Labs Feature

The `filter` feature allows user to filter the list of students by their `Lab` and its `LabStatus`.

The Filter feature is implemented by updating `fitleredStudents` using `StudentHasLabPredicate` which extends
`Predicate<Student>`. `StudentHasLabPredicate` contains the attribute `Lab` and overrides the `Predicate#test()` method to
determine if a `Student` has a specific `Lab` in its `LabList`.

The command format for filter is `fitler l/LAB_NUMBER s/LAB_STATUS` where `LAB_NUMBER` should a `Lab` that exists within
TAddressBook and `LAB_STATUS` should be one of the three values below:
- `u` : UNSUBMITTED
- `s` : SUBMITTED
- `g` : GRADED

Given below is an example usage scenario and how the filter mechanism behaves at each step.
1. When the user executes `filter` command, `FilterCommandParser#parse(String)` will be invoked to parse the given command
    and create a `StudentHasLabPredicate` with a dummy `Lab` with the given `LabStatus`. Then, a new `FilterCommand` is
    instantiated using the `StudentHasLabPredicate`.


2. `FilterCommand#execute(Model)` will then execute the command on the current `Model` of the system.


3. The `FilterCommand` object will check if the `Model` object contains the `Lab` carried by the `StudentHasLabPredicate`.
    If the `Lab` is not found, a `CommandException` will be thrown.


4. The `StudentHasLabPredicate` is passed as a parameter into the `Model#addOnFilteredStudentList(Predicate)` method.


5. `Model` object retrieves its original `Predicate<Student>` to perform `Predicate#and(Predicate)` with the
    `StudentHasLabPredicate` passed in. The new `Predicate<Student>` is used to filter the `filteredStudents` within
    the `Model` object.


6. A `CommandResult` object is created with a feedback message and is returned.

The **UML sequence diagram** below shows the parsing of the user input using `FilterCommandParser` to create a
`StudentHasLabPredicate` and subsequently a `FilterCommand` object.

<img src="images/FilterCommandParserSequenceDiagram.png" width="850" />

After the `FilterCommand` object is created, it is utilised to filter the student list.
The following **UML sequence diagram** shows the interaction between the components during the execution of the `filter` command:

<img src="images/FilterCommandSequenceDiagram.png" width="850" />

The following **UML activity diagram** shows what happens when a `filter` command is executed:

<img src="images/FilterCommandActivityDiagram.png" width="850" />


### `labedit`: Edit Lab Feature

#### Implementation
The `labedit` feature allows for editing of the `LabStatus` and/or `LabMark` of a specified `Lab` in the TAddressBook.<br>
The format of this command is `labedit INDEX l/LAB_NUMBER (s/LAB_STATUS) (m/LAB_MARK)`, where:
* `INDEX` corresponds to the index number of a student, according to the currently displayed student list
* `LAB_NUMBER` corresponds to an existing lab in the TAddressBook
* `LAB_STATUS` is either `u`/`s`/`g` (`UNSUBMITTED`/`SUBMITTED`/`GRADED`)
* `LAB_MARK` is an integer from 0 to 100 inclusive
* The parentheses indicate that at least one of `s/LAB_STATUS` and `m/LAB_MARK` must be provided

The implementation of `labedit` is as follows:
1. When `AddressBookParser#parseCommand` detects `labedit` as the command word, it creates a new `EditLabCommandParser` with the given arguments.
2. `EditLabCommandParser` parses the parameters and throws a `ParseException` if any invalid values are encountered.
3. `EditLabCommand#execute(Model)` will then execute with the current `Model` in the system.
4. The `EditLabCommand` object checks if the given `INDEX` is out of bounds.
5. The `EditLabCommand` object checks if the given combination of `LAB_STATUS` and `LAB_MARK` is valid.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The valid combinations are:
* `LAB_STATUS` and no `LAB_MARK`
* `LAB_MARK` and no `LAB_STATUS`
* `LAB_MARK` and `LAB_STATUS` of `GRADED`
</div>

6. The `EditLabCommand` calls `LabList#setLab` of the student specified by the given `INDEX`, which edits the target `Lab` to the new `Lab`.

The following sequence diagram shows the interactions between components during a `labedit` command, `labedit l/1 s/u`:

<img src="images/EditLabCommandSequenceDiagram.png" width="850" />

<div markdown="span" class="alert alert-info">:information_source: **Note:** In the sequence diagram, the details of `LabList#setLab` have been intentionally omitted. They can be found in the sequence diagram below.
</div>

The following sequence diagram shows how `LabList#setLab` is implemented:

<img src="images/LabListSetLabSequenceDiagram.png" width="550" />

The detailed steps are as follows:
1. `LabList#setLab` checks if the edited `Lab` is the same as the original `Lab`, and whether the target `Lab` exists in the `LabList`.
2. `LabList#setLab` edits the target `Lab` to the new `Lab` with different `LabStatus` and/or `LabMark`.

To summarize, the following activity diagram shows what happens when the user requests to edit a lab:

<img src="images/EditLabCommandActivityDiagram.png" width="500" />


### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current TAddressBook state in its history.
* `VersionedAddressBook#undo()` — Restores the previous TAddressBook state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone TAddressBook state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial TAddressBook state, and the `currentStatePointer` pointing to that single TAddressBook state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th student in the TAddressBook. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the TAddressBook after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted TAddressBook state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified TAddressBook state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

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

CS2030S Teaching Assistants (TAs) who
* have to keep track of students' details
* have to keep track of students' performance and progress on labs
* have to keep track of their own progress on grading labs
* prefer desktop apps over other types
* can type fast
* prefer typing to mouse interactions
* are reasonably comfortable using CLI apps

**Value proposition**: provides CS2030S TAs with a systematic way to keep track of students and their lab assignments,
including who he/she has graded and what grade was given for every lab.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                                                     | So that I can…​                                                                                             |
|----------|---------|------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| `* * *`  | TA      | add a student to my class list                                   | begin keeping track of their lab assignments                                                                |
| `* * *`  | TA      | edit a student's information                                     | fix any errors I made during the initial adding phase or add information that was not provided to me before |
| `* * *`  | TA      | delete a student from my class list                              | my list will be reflective of who is currently in my class                                                  |
| `* * *`  | TA      | list all students                                                | have an overview of all the students in my class                                                            |
| `* * *`  | TA      | locate students by name                                          | easily find students whose names contain certain keywords                                                   |
| `* * *`  | TA      | filter students based on the status of their labs                | quickly filter out the students that possess the same tags                                                  |
| `* * *`  | TA      | add a new lab assignment to the assignment list                  | keep track of which lab assignments have been released to students                                          |
| `* * *`  | TA      | update status of a lab when a student has submitted it           | keep track of which labs students have submitted                                                            |
| `* * *`  | TA      | update status of a lab and add marks to it when I have graded it | keep track of what labs I have graded and how I have graded them                                            |
| `* * *`  | TA      | edit status and marks of a lab                                   | edit lab statuses and marks I changed or entered by mistake                                                 |
| `* * *`  | TA      | remove a lab assignment from the TAddressBook                    | delete any labs that I've previously inputted by accident                                                   |
| `* *`    | TA      | add students without filling in all attributes                   | keep track of students even if I do not know all their details                                              |

### Use cases

(For all use cases below, the **System** is the `TAddressBook (TAB)` and the **Actor** is the `CS2030S Teaching Assistant (TA)`, unless otherwise specified)

##### Use case UC1: Add a new lab to the list of labs

**MSS**
1. TA requests to add new lab with a given lab number.
2. TAB adds a new lab to every student.
3. TAB shows updated student list with the new lab added.
4. TAB displays success message.

   Use case ends.

**Extensions**
* 1a. TAB detects that the student list is empty.
    * 1a1. TAB displays warning message to user (that there are no students yet but students added subsequently will have the new lab).

      Use case ends.

* 1b. TAB detects that an identical lab already exists.
    * 1b1. TAB displays error message (that lab already exists).

      Use case ends.

* 1c. TAB detects that the lab number provided is not an integer between 0 and 20 inclusive.
    * 1c1. TAB displays error message (that the lab number is invalid).

      Use case ends.

##### Use case UC2: Filter students by status of a specified lab

**MSS**

1.  TA requests to filter students by a specific lab and lab status.
2.  TAB displays list of students based on filter criteria.

    Use case ends.

**Extensions**

* 1a. TA provides non-existent lab.
    * 1a1. TAB shows error message (that the lab does not exist).

      Use case ends.

* 1b. TA tries to filter an empty list.
    * 1b1. TAB shows error message.

      Use case ends.

##### Use case UC3: Mark a student's lab as submitted

**MSS**
1. TA requests to change a student’s lab status to "Submitted".
2. TAB changes the student’s lab status from "Unsubmitted" to "Submitted".
3. TAB displays updated lab status and success message.

   Use case ends.

**Extensions**

* 1a. TA provides invalid student index.
    * 1a1. TAB shows error message that student index is invalid.

      Use case ends.
* 1b. TA provides non-existent lab.
    * 1b1. TAB shows error message stating that the lab number is invalid.

      Use case ends.
* 1c. TA provides lab that is already "SUBMITTED" or "GRADED".
    * 1c1. TAB shows error message stating that the command cannot be used due to the current lab status.

      Use case ends.

##### Use case UC4: Mark a student's lab as graded

**MSS**
1. TA requests to change a student’s lab status to "Graded" and specifies a mark.
2. TAB changes the student’s lab status from either "Unsubmitted" or "Submitted" to "Graded".
3. TAB adds the specified mark to the student's lab.
4. TAB displays updated lab status and success message.

   Use case ends.

**Extensions**

* 1a. TA provides invalid student index.
    * 1a1. TAB shows error message that student index is invalid.

      Use case ends.
* 1b. TA provides non-existent lab.
    * 1b1. TAB shows error message stating that the lab number is invalid.

      Use case ends.
* 1c. TA provides lab that is already "Graded".
    * 1c1. TAB shows error message stating that the command cannot be used due to the current lab status.

      Use case ends.
* 1d. TA provides an invalid mark (e.g. a negative number).
    * 1d1. TAB shows error message stating that valid range of the mark.

      Use case ends.

##### Use case UC5: Edit a student's lab

**MSS**
1. TA requests to edit a specified student lab's status and/or mark.
2. TAB edits the specified student lab's status and/or mark.
3. TAB displays updated lab status (if lab status was edited) and success message.

   Use case ends.

**Extensions**

* 1a. TA provides invalid student index.
    * 1a1. TAB shows error message that student index is invalid.

      Use case ends.
* 1b. TA provides non-existent lab.
    * 1b1. TAB shows error message stating that the lab number is invalid.

      Use case ends.
* 1c. TA provides invalid status and grade combination e.g. providing a grade when the updated status is "UNSUBMITTED".
    * 1c1. TAB shows error message stating that the given combination is invalid.

      Use case ends.
* 1d. TA provides an invalid grade (e.g. a negative number).
    * 1d1. TAB shows error message stating that valid range of the mark.

      Use case ends.

##### Use case UC6: Remove a lab from the list of labs

**MSS**
1. TA requests to remove a lab with a given lab number.
2. TAB removes the lab from every student.
3. TAB shows updated list of labs.
4. TAB displays success message.

   Use case ends.

**Extensions**
* 1a. TAB detects that the lab number provided is not an integer between 0 and 20 inclusive.
    * 1a1. TAB displays error message (that lab number is invalid).

      Use case ends.

* 1b. TAB detects that the lab number provided is not a lab present in the TAddressBook.
    * 1b1. TAB displays error message (that lab number does not exist).

      Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
3. A TA with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should be intuitive for both new users and experienced users.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Lab**: Refers to lab assignments from the module CS2030S offered by the National University of Singapore.
* **Lab Status**: Refers to possible statuses of lab assignments.
  * **UNSUBMITTED**: Status to indicate that the student has not submitted the lab assignment.
  * **SUBMITTED**: Status to indicate that the student has submitted the lab assignment to his/her GitHub repository.
  * **GRADED**: Status to indicate that the User (TA) has graded the lab assignment.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a Student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No students is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Adding a Lab

1. Assuming the `LabList` is empty and the list has some `Student`s.
   1. Test case: `labadd l/1`<br>
   Expected: `Lab 1` will appear as a red label on each `Student`'s card in the list.

   2. Test case: `labadd l/21`<br>
   Expected: A error message will appear with the correct command format and constraints and no lab will be added.

   3. Test case: `labadd l/-1`<br>
   Expected: A error message will appear with the correct command format and constraints and no lab will be added.

### Editing a Lab

1. Assume we want to edit `Lab 1` of the person with `INDEX 1` and the current `LabStatus` is `UNSUBMITTED`.
   1. Test case: `labedit 1 l/1 s/s`<br>
   Expected: The status of `Lab 1` will change from `UNSUBMITTED` to `SUBMITTED`. The lab label will change from red to yellow.

   2. Test case: `labedit 1 l/1 s/g`<br>
   Expected: An error message will appear stating that the given combination is invalid.

   3. Test case: `labedit 1 l/1 s/g m/10`<br>
   Expected: The status of `Lab 1` will change from `UNSUBMITTED` to `GRADED`. The lab label will change from red to green.
