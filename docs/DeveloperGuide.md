---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


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

This product is for competitive team sports manager to manage players in their clubs
(i.e. roles, player attributes, trainings), lineup formation during practice as well
as scheduling of events (i.e. trainings, competitions, etc.).

**Value proposition**:

This product solves:
* the issues of managing large quantity of players;
* the issues of managing large quantity of training and competition schedules in a club;
* keeping track of the players that have played in the same lineup before;
* identifying the positions that are lacking of manpower for recruitment.

This product is not able to:
* support users with multiple clubs.
* provide recommendation on coaching strategies, etc.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                                                                              | So that I can…​                                                                                                                  |
|----------|--------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| `* * *`  | potential user                             | look through the app description                                                                                          | I can roughly know what the app can help me to do                                                                                |
| `* *`    | potential user                             | look at the sample players provided in the app                                                                            | I can quickly visualise the functions of this app                                                                                |
| `* *`    | potential user                             | purge the mock data                                                                                                       | I can adopt it for my own use                                                                                                    |
| `* * *`  | new user                                   | add one new player                                                                                                        | I can keep track of players' progress and conditions                                                                             |
| `* * *`  | new user                                   | add one new training date                                                                                                 | I can keep track of training dates                                                                                               |
| `* *`    | new user                                   | quickly add multiple/recurring training dates at once by import                                                           | I don't have to add the dates one by one for every week                                                                          |
| `* *`    | new user                                   | quickly add information of all my players at once by import                                                               | I don't have to add the players in one by one                                                                                    |
| `* * *`  | new user                                   | tag each player with labels (green for strength, red for weakness, e.g. (green) defend)                                   | the condition of each player is more explicitly represented                                                                      |
| `* * *`  | new user                                   | tag my players by their position                                                                                          | I know what are the number of players in that position                                                                           |
| `*`      | new user                                   | tag my players by 'fit to play'                                                                                           | I know which are the available players for competition                                                                           |
| `* *`    | new user                                   | update my players detail (eg attendance, tags) by their jersey number                                                     | I can quickly perform update operations to my players on the app                                                                 |
| `*`      | new user                                   | key in the first few letters to see what commands starting with thse letters are available                                | I can know the correct spelling of valid commands                                                                                |
| `* *`    | new user                                   | key in "help" to see all available commands                                                                               | I don't need to refer to user mannual from time to time                                                                          |
| `* *`    | new user                                   | view the number of fouls for my player during competition                                                                 | I know when to substitute him when he gets into foul trouble                                                                     |
| `* *`    | new user                                   | upload the score after the matc                                                                                           | `***`                                                                                                                            |I can keep track of result of previous match
| `* * *`  | expert user                                | view aggregate data of the tea                                                                                            | `**`                                                                                                                             |I can know how the team is doing as a whole
| `* * *`  | expert user                                | add my players to a lineup                                                                                                | I can keep track of the performance of this lineup                                                                               |
| `* *`    | expert user                                | view the past lineup performance                                                                                          | I can decide what are the best potential lineups for competition                                                                 |
| `* * *`  | expert user                                | update player's ability score                                                                                             | I can keep track of my players                                                                                                   |
| `* *`    | expert user                                | view my player's progression                                                                                              | I can see if I need to make changes to his training schedule/routine                                                             |
| `* * *`  | expert user                                | mark the attendence of daily training                                                                                     | I don't have to write it on paper and keep it in office and can directly view each player's attendace rate over a period of time |
| `* *`    | expert user                                | add the areas of improvement to each player                                                                               | I can curate a training for that player                                                                                          |
| `* * *`  | expert user                                | delete player details that are no longer part of the tea                                                                  | `**`                                                                                                                             |I can keep track a list of valid players
| `*`      | expert user                                | send mass reminders to each players one day before training dates                                                         | I don't need to remind every player or mannualy send announcements                                                               |
| `*`      | expert user                                | analyze which position each player is most suitable for based on my own formula ( 90% accuracy in defending for Defender) | I don't need to read everyone's data                                                                                             |
| `* * *`  | expert user                                | quickly jot down notes about my player                                                                                    | I don't have to edit their data every time                                                                                       |
| `* *`    | expert user                                | add shortcuts (for listing lineups etc) to my application                                                                 | I could save a lot of time by accessing to the commonly used commands                                                            |
| `* *`    | expert user                                | search for my players quickly                                                                                             | I don't need to scroll through the whole list                                                                                    |
| `* *`    | expert user                                | upload the boxscore of the competition                                                                                    | I can keep a historical record of the performance of my team                                                                     |
| `* *`    | long-term user                             | archive the players' data for those who have quited the tea                                                               | `**`                                                                                                                             |I won't be confused about which players are valid
| `* * *`  | long-term user                             | mark down competition date, venue, opponent etc.                                                                          | I won't forget important information about the competition                                                                       |
| `* *`    | long-term user                             | archive players' data by batch (graduation)                                                                               | I don't need to archive each player one by one                                                                                   |
| `* * *`  | user                                       | save all existing data to my hard disk                                                                                    | I won't lose all my data                                                                                                         |
| `* * *`  | user                                       | retrieve all saved data from my hard disk                                                                                 | I can retrieve all my data                                                                                                       |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `MyGM` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Find A Player**

**MSS**

1. User requests to find a player.
2. MyGM displays details about the player.
Use case ends.

**Extensions**

* 1a. MyGM detects an error in the entered command.
  * 1a1. MyGM displays the error message.
  Use case ends.

* 1b. MyGM cannot find the player.
  * 1b1. MyGM displays failure.
  Use case ends.

**Use case: UC02 - Create a new Lineup**

**MSS**

1. User adds a new lineup.
2. MyGM displays the success message.
3. User put a player into the lineup
4. MyGM displays the success message.
   Repeat 3 until the lineup is full.
   Use case ends.

**Extensions**

* 1a. The lineup already exist in MyGM
  * 1a1. MyGM displays the error message.
  Use case returns to the start of 3.

* 3a. MyGM cannot find the player.
  * 3a1. MyGM displays the error message.
  Use case returns to the start of 3.

* 3b. The lineup is already full.
  * 3b1. MyGM displays the error message.
  Use case ends.

**Use case: UC03 - Tagging a Player’s Position**

**MSS**

1. Find a player (UC01)
2. User adds a tag to the player.
3. MyGM displays the success message.
   Use case ends.

**Extensions**
* 3a. User entered an invalid tag.
  * 3a1. MyGM displays the invalid tag message and the appropriate tags.
  Use case ends.

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to `100` players without a noticeable sluggishness in performance for typical usage. 
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should have a _friendly user interface_.
5. The system should respond within `2` seconds.
6. Should there be any invalid command, the part of the command that causes this issue should be **highlighted**.

### Glossary

* **Club**: A basketball club consisting of a number of players, who regularly trains and participates in competitions.
* **Lineup**: Players from part of a club that play together on the court, typically consisting of 5 players.
* **Schedule**: Event of a team, including training and competitions.
* **Mainstream OS**: Windows, Linux, Unix, OS-X
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

### Adding a person

1. Adding a new player into the team

    1. Test case: `add P/ n/John Doe p/98765432 e/johnd@example.com h/180 j/23 w/80 t/PG t/SG`<br>
       Expected: Player named "John" is added. Details of the added player shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `add P/John Doe p/98765432 e/johnd@example.com h/180 j/23 w/80 t/PG t/SG`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `add P/`, `add P/ n/John Doe`, `...` (where any of the required prefix is missing)<br>
       Expected: Similar to previous.

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `view` command. Multiple persons in the list.

   1. Test case: `delete P/John`<br>
      Expected: Player named "John" is deleted.

   1. Test case: `delete P/Baabababa`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete John`, `...` (where the prefix is missing)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
