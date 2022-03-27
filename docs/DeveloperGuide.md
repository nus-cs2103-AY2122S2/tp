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

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S2-CS2103-F11-3/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/trackbeau/Main.java) and [`MainApp`](https://github.com/AY2122S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/trackbeau/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/trackbeau/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/trackbeau/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S2-CS2103-F11-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/trackbeau/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a customer).
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
**API** : [`Model.java`](https://github.com/AY2122S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/trackbeau/model/Model.java)

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

**API** : [`Storage.java`](https://github.com/AY2122S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/trackbeau/storage/Storage.java)

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

### Customer Attributes

#### Add new data fields relevant to customers at beauty salons

##### Overview
- Data fields such as birthday, sign-up date, skin type, hair type, allergies, preferred staff and services are to be added.

##### Implementation
- Allergies, Staff and Services were added as tags as one customer can have multiple of these data fields
- The rest were added as attributes

##### Design Considerations

* **Alternative 1 (current choice):** Implement only allergies,staffs and services as tags
  * Pros: Harder to implement
  * Cons: Less direct to access allergies, staffs and services as compared to if a list was used

* **Alternative 2:** Implement all additional fields as tags
  itself.
  * Pros: Easy to implement
  * Cons: Could be harder to access the necessary data for other functions like creating summary statistics
  * Cons: Does not make sense to have multiple of certain data fields, e.g. multiple skin types, multiple birthdays

#### Delete multiple customers in a single command

#### Overview
- Delete command can allow multiple indexes, this allows for faster deletion of customer profiles.

#### Implementation
- Current implementation allows user to enter multiple indexes seperated by comma. All the indexes will be checked if
they are integers and is a valid index.
- If any indexes fail the check, the command with be aborted. Only if all indexes pass the check, then command with be
executed.

##### Design Considerations

* **Option 1:** Does not abort command when an index fail the check and delete customer from valid indexes.
  * Pros: Lenient on user error.
  * Cons: User might be confused of the intended behavior of the command where different input can produce same effect.
  * Cons: Difficult to decide what error should be allowed and not allowed.
* **Option 2 (Current choice):** Only if all indexes pass the check, then command with be executed.
  * Pros: Straightforward to implement.
  * Pros: It is clear about how the command is intended to be used.
  * Cons: Minor error will cause command to be aborted.

### Services


#### Add service feature

##### Overview
The add service feature allows users to add services into TrackBeau.
Each new service must have the data fields `ServiceName`, `Price` and `Duration`.

##### Implementation of feature

##### Design considerations

#### Edit service feature

##### Overview
The edit service feature allows users to edit existing services in TrackBeau.
For each edit, at least one of the data fields `ServiceName`, `Price` and `Duration` must be modified.

##### Implementation of feature

##### Design considerations

#### Delete service feature

##### Overview
The delete services feature allows users to remove existing services in TrackBeau.

##### Implementation of feature

##### Design considerations

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
* works in beauty salon
* has a need to manage a significant number of customer profiles
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
With many customers, it is hard for beauty salons to provide personalized customer service.
Our product allows the user to keep track of customer information and group their customers to provide targeted services every time they return.
It can also keep track of performance metrics, like total new memberships.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​        | I want to …​                                                                          | So that I can…​                                                               |
|----------|-------------------|------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `* * *`  | new user          | view the user guide easily                                                               | know what kind of commands that I can use                                        |
| `* * *`  | new user          | see where to key in information easily                                                   | be clear of how to use the interface                                             |
| `* * *`  | new user          | see the format to follow for commands when I type incorrect command                      | rectify the command                                                              |
| `* * *`  | new user          | add name of customer                                                                     | identify the customer correctly                                                  |
| `* * *`  | new user          | add contact details of customer                                                          | contact them for their appointment or for follow up                              |
| `* *`    | new user          | add customer preference for staff                                                        | appoint the staff they prefer during their appointment                           |
| `* * *`  | new user          | add allergy information of customer                                                      | avoid using or promoting products that the customer is allergic to               |
| `* * *`  | new user          | add hair type of customer                                                                | choose the correct products when treating their hair                             |
| `* * *`  | new user          | add skin type of customer                                                                | choose the appropriate products when treating their skin                         |
| `* *`    | new user          | add services that customer prefers                                                       | better pitch new products and personalise their experience at the salon          |
| `* `     | new user          | add name of staff                                                                        | keep track of the personal details of my staffs                                  |
| `*`      | new user          | add personal details of staff like birth date, contact number, part time/full timer, etc | know my staffs better                                                            |
| `* * *`  | new user          | view a customer profile                                                                  | know the details of a customer                                                   |
| `* * *`  | new user          | view a list of customer profile associated with a keyword                                | view the profiles of customers that i am interest in                             |
| `* * *`  | new user          | delete a customer                                                                        | remove a customer that does not visit our salon anymore                          |
| `* * *`  | new user          | edit a customer                                                                          | update details when needed                                                       |
| `* * *`  | new user          | find a customer                                                                          | locate a customer's profile faster                                               |
| `* *`    | intermediate user | check if facilities are available based on bookings of the day                           | know if the facility is available for booking                                    |
| `* *`    | intermediate user | add the birth date of a customer                                                         | know when is the birthday of the customer                                        |
| `* *`    | intermediate user | add the signup date of a customer                                                        | track the customers gained per month                                             |
| `* *`    | intermediate user | view the customers that are having their birthday today                                  | be reminded to sent them birthday wishes                                         |
| `* * *`  | intermediate user | add a new service                                                                        | keep track of the services that our salon provides                               |
| `* * *`  | intermediate user | edit a service                                                                           | update the details of the service                                                |
| `* * *`  | intermediate user | delete a service                                                                         | remove service that is not offered anymore                                       |
| `* * *`  | intermediate user | view all services                                                                        | see all the services and their details that our salon provides                   |
| `* *`    | expert user       | add the feedback of the customer after been serviced for a booking                       | know how satisfied the customer is and know the areas of improvement if any      |
| `* * *`  | expert user       | add a customer booking                                                                   | keep track of the customer's upcoming appointments and make preparations         |
| `* * *`  | expert user       | view all the bookings                                                                    | better allocate my manpower as I can know when the salon is the busiest          |
| `* * *`  | expert user       | delete a customer booking                                                                | remove the booking if there is any cancellation                                  |
| `* * *`  | expert user       | edit a customer booking                                                                  | update the details of the booking                                                |
| `* * *`  | expert user       | see statistics of how many new customers gained by month                                 | review and improve my company's performance                                      |
| `* * *`  | expert user       | keep track of expected earnings of the salon by month                                    | review and improve my company's performance                                      |
| `* *`    | expert user       | get summary statistics on customer profiles (preferred services)                         | review and improve my choice of services and products                            |
| `* *`    | expert user       | get summary statistics on customer profile (preferred staffs)                            | identify the best staff and let the team learn from them                         |
| `* *`    | expert user       | sort customers based on membership tier                                                  | promote certain facilities or products to the customer to help raise the membership tier of that customer                         |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is`TrackBeau` and the **Actor** is the `user`, unless specified otherwise)

**Use case: find customer's detail by their name**

**MSS**

1.  User requests to find a customer by name using a keyword
2.  TrackBeau shows a list of customers with matching keyword with their customer Index

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case: find customer's details associated with a keyword**

**MSS**

1.  User requests to find a customer related to a keyword
2.  TrackBeau shows a list of customers with matching keyword with their customer Index




**Extensions**

* 2a. The list is empty.

  Use case ends.



**Use case: Add a customer**

**MSS**

1.  User requests to see input format for adding a customer using `help`
2.  TrackBeau shows command input formats
3.  User requests to add a customer based on the input format
4.  TrackBeau adds the customer to the list

    Use case ends.

**Extensions**

* 2a. The input format is wrong.
    * 2a1. TrackBeau shows an error message.

      Use case resumes at step 3.

**Use case: Edit a customer**

**MSS**

1.  User requests to see input format for editing a customer's information using `help`
2.  TrackBeau shows command input formats
3.  User requests to edit a customer's information based on the input format
4.  TrackBeau adds the customer to the list

    Use case ends.

**Extensions**

* 2a. The input format is wrong.
    * 2a1. TrackBeau shows an error message.

      Use case resumes at step 3.

*{More to be added}*

**Use case: user wishes to exit the application**

1.  User requests to exit the application
2.  AddressBook closes the application

    Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 customers without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Features should be relatively easy to test for both manual and automated testing.
5.  The product should be for a single user i.e. (not a multi-user product).
6.  The data should be stored locally and should be in a human editable text file.
7.  Does not use a DBMS to store data.
8.  The software should work without requiring an installer.
9.  The file sizes of the deliverables should be reasonable and not exceed the limits given below:
    -   Product (i.e., the JAR/ZIP file)): 100MB
    -   Documents (i.e., PDF files): 15MB/file
10. The GUI should work well (i.e., should not cause any resolution-related inconveniences to the user) for,
    -   standard screen resolutions 1920x1080 and higher, and,
    -   for screen scales 100% and 125%.
11. The GUI should be usable (i.e., all functions can be used even if the user experience is not optimal) for,
    -   resolutions 1280x720 and higher, and
    -   for screen scales 150%.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

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

### finding customer by name

1. finding customer by name

   1. Test case: `find name john`<br>
      Expected: list of customers with john in their name will be shown

   1. Test case: `find name j0hn`<br>
      Expected: Invalid keyword has been enter. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect show commands to try: `find name`, `find x` (where x only contains english characters)<br>
      Expected: Similar to previous.

### finding customer by keyword

1. finding customer by keyword type and keyword

   1. Test case: `find allergies nickle`<br>
      Expected: list of customers with nickle in their allergy will be shown

   1. Test case: `find nickle`<br>
      Expected: Invalid keyword has been enter. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect show commands to try: `find skintype`, `find x` (where x only contains english characters)<br>
      Expected: Similar to previous.
