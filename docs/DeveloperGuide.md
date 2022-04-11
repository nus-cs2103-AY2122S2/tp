---
layout: page
title: CinnamonBun Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

List of sources of all code and third-party libraries:
* This project is based on the AddressBook-Level3 projected created by the [SE-EDU](https://se-education.org/) initiative.
* Libraries used: [JavaFX](https://openjfx.io/)
* Edit distance algorithm adapted from [geeksforgeeks](https://www.geeksforgeeks.org/edit-distance-dp-5/)
* ListView selection adapted from [StackOverflow](https://stackoverflow.com/questions/23622703/deselect-an-item-on-an-javafx-listview-on-click)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="1" class="alert alert-primary">:bulb: **Note**<br>

The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S2-CS2103T-W09-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

![Architecture Diagram](images/ArchitectureDiagram.png)

*Figure: High-level architecture diagram of CinnamonBun.*

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S2-CS2103T-W09-2/tp/blob/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2122S2-CS2103T-W09-2/tp/blob/master/src/main/java/seedu/address/MainApp.java).
It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.
* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

![Architecture Sequence Diagram](images/ArchitectureSequenceDiagram.png)

*Figure: Sequence diagram of main components' interaction when use issues the command `delete 1`.*

Each of the four main components (also shown in the diagram above),
* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

![Component Managers](images/ComponentManagers.png)

*Figure: Partial class diagram of interaction of the main components.*

The sections below give more details of each component.

### UI component

![GUI Overview](images/GuiOverview.png)

*Figure: Overview of CinnamonBun's GUI.*

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S2-CS2103T-W09-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![UI Class Diagram](images/UiClassDiagram.png)

*Figure: Class diagram of CinnamonBun's GUI using JavaFX framework.*

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `TransactionListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,
* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` and `Transaction` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S2-CS2103T-W09-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

![Logic Class Diagram](images/LogicClassDiagram.png)

*Figure: Partial class diagram of the `Logic` component.*

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a client).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

![Delete Sequence Diagram](images/DeleteSequenceDiagram.png)

*Figure: Interactions inside the logic component for the `delete 1` command.*

<div markdown="1" class="alert alert-info">:information_source: **Info**<br>

The lifeline for `DeleteCommandParser` and `DeleteCommand` should end at their destroy markers (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S2-CS2103T-W09-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

![Model Class Diagram](images/ModelClassDiagram.png)

*Figure: Class diagram of `Model` component.*

The `Model` component,

* stores the client data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the transaction data i.e., all `Transaction` objects (which are contained in a `TransactionList` object).
* stores the currently 'selected' `Transaction` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Transaction>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components).

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S2-CS2103T-W09-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

![Storage Class Diagram](images/StorageClassDiagram.png)

*Figure: Class diagram of `Storage` component.*

The `Storage` component,
* stores user preference data in json format, and reads it back into corresponding objects.
* stores client and transaction data in binary format, and reads it back into corresponding objects.
* inherits from `AddressBookStorage`, `TempAddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementations**

This section describes some noteworthy details on how certain features are implemented.

### Person, Field and Tag

#### Implementation

The `Person` class is used to represent a client. The abstract `Field` class represents the client data, and the `Tag` class represents the tags of the Person.
`Person` contains an immutable `HashMap<Prefix, Field>` of fields, and an immutable `HashSet<Tag>` of tags. Since `Person` is immutable, any getters and setters
will return a new instance of `Person`. By using a `HashMap<Prefix, Field>` and `HashSet<Tag>`, `Person` can be made modular, as it is easy to add any new data to Person
without needing to edit `Person.java`.

By adding the getter and setter functions, we were able to remove unnecessary classes like `EditPersonDescriptor` & `PersonBuilder` from the original AB-3 base code.

![Person Class Diagram](images/PersonClassDiagram.png)

*Figure: Simplified class diagram of showing the association between Person, Field and Tag.*

`AddCommand`, `EditCommand`, `RemarkCommand`, `AppendCommand` and `RemoveCommand` adds and removes fields and tags from a `Person`.

![Append Sequence Diagram](images/AppendSequenceDiagram.png)

*Figure: Simplified sequence diagram of `AppendCommand`.*

<div markdown="1" class="alert alert-info">:information_source: **Info**<br>

The lifeline for `AppendCommandParser` and `AppendCommand` should end at their destroy markers (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

<div markdown="1" class="alert alert-info">:bulb: **Note**<br>

* The above sequence diagram has been simplified to provide a more comprehensible overview. It is not completely accurate as some unnecessary details have been omitted.
* While not shown here, `AddCommand`, `EditCommand`, `RemarkCommand`, `RemoveCommand` work very similarly to `AppendCommand`, and their sequence diagram will be almost identical to `AppendCommand`'s.

</div>

#### Design considerations

**Aspect: Structure for storing fields**
* **Alternative 1 (original AB-3 method)**:
  Hard-code every field directly into `Person`.
    * Pros: None.
    * Cons: Extremely highly coupled code that requires many changes to be done everytime a new field is added. Prone to bugs.
* **Alternative 2 (current choice)**
  Make `Person` modular by storing a `HashMap<Prefix, Field>` of fields instead.
    * Pros: Code is much less coupled. Much easier to maintain.
    * Cons: A lot of work was required to decouple the existing `Person` implementation from AB-3.


### Transaction 

#### Implementation

The `Transaction` class is used to represent a client's transaction. The abstract `TransactionField` class represents the transaction data.
`Transaction` contains an immutable `HashMap<Prefix, TransactionField>` of transactionFields as well as identifier of the `Person`
who owns the `Transaction`. This person identifier will be stored as a `long` inside the `Transaction` class. 

![Transaction Class Diagram](images/TransactionClassDiagram.png)

*Figure: Simplified class diagram of showing the association between Transaction and other related classes.*

`Transaction` class has 6 commands: `AddTransaction`, `listTransaction`, `findTransaction`, `deleteTransaction`,
`pay`, and `unpay`. All commands have similar implementation, user input will be parsed to
return a `Command` object. `Model` will execute this object to do the desired behavior.

<div markdown="1" class="alert alert-info">:information_source: **Info**<br>

Note that there is a dependencies from class `Transaction` to class `Person`. `Transaction` does not
directly store the reference to the Owner (`Person`) of the `Transaction`, instead `Transaction`
stores an identifier (`personIdentifier`) to identify the owner (`Person`) of the `Transaction`.

</div>

#### Design considerations

The current implementation of `Transaction` class is similar to `Person` class. Every field/attribute of transaction needs to
extend from the `TransactionField` class. The `Transaction` class will have a list of `TransactionField`s in which all of it's
fields must be registered in the `TransactionFieldRegistry`. Each field is either a required field or an optional field.

Transaction class consists of fields `Amount`, `TransactionDate`, `DueDate`, `Note`, and `Status`.

**Aspect: Structure for storing fields**

* **Alternative 1 (current choice):** Create a list (`FilteredList`) of Transactions, controlled by `ModelManager`.
  Everytime a user create a transaction, a new instance of transaction will be added to the list and a client
  specified by its unique identifier (`personId`) will be referenced by this transaction. To list all the transactions
  of a particular client, the `FilteredList` should be updated to only contain `Transaction`
  with a reference to the client's id.
    * Pros: Consistent design with the `Person` class.
    * Cons: Have to handle cases when a user is updated/removed. The input specified by the users
      corresponds to the index of the displayed clients/users. Hence we need to retrieve the client's attributes
      before initializing the Transaction object thus harder to maintain a good design.

* **Alternative 2:** Every `Person` object has a list of transactions which will be
  initialized with an empty list. Each time a user add a transaction, the object will be
  added into the specified `Person`'s Transaction List.
    * Pros: Easier to implement compared to the other alternative.
    * Cons: `Transaction` class would be coupled to the `Person` class. In addition, inconsistent design
      in comparison to the `Person` class.

### Add Transaction

### Implementation

The `Add Transaction` implementation is similar to adding a `Person` with 
an extra step. When adding `Transaction`, user will have to specify 
the owner of the `Transaction` using the index displayed.
However, `Transaction` stores `personIdentifier` as the reference to the `Person` class. Hence there
must be a mechanism to convert the index provided by the user to the correct
`personIdentifier`.

This issue is addressed by using the class `TransactionBuilder` that takes a `personIdentifier`
and returns the desired `Transaction`. 

![Add Transaction activity diagram](images/AddTransactionActivityDiagram.png)

*Figure: Simplified activity diagram showing the sequence of action of Add Transaction command.*

Given below is an example usage scenario and how `Add Transaction` behaves at each step:
1. The user input `addTransaction 1 a/12.5 td/2020-11-11 dd/2020-12-12 n/This is a note --paid`.
2. `LogicManager` execute the command which will be parsed using `AddressBookParser` and `AddTransactionParser`
   if the input is valid.
3. `AddTransactionParser` will return `AddTransactionCommand` which contains `TransactionBuilder`.
    This `TransactionBuilder` will be used later by `AddTransactionCommand#execute` to 
    generate the appropriate `Transaction`.
4. `AddTransactionCommand` will then update the `Transaction List` by calling `Model#addTransaction`.
5. `CommandResult` will be returned back to the `LogicManager` if there is no failure in execution.

The above steps are illustrated by the AddTransaction Sequence Diagram below. In the given diagram,
`addTransactionEx` represents `addTransaction 1 a/12.5 td/2020-11-11 dd/2020-12-12 n/This is a note --paid `
and `transaction` represents `1 a/12.5 td/2020-11-11 dd/2020-12-12 n/This is a note --paid` for brevity. 

![Add Transaction sequence diagram](images/AddTransactionSequenceDiagram.png)
*Figure: Simplified sequence diagram showing the sequence of action of Add Transaction command.*


#### Design considerations

**Aspect: How add transaction executes**

* **Alternative 1 (current choice):** Store `TransactionBuilder` to generate the `Transaction` later.
    * Pros: No need to pass the `ModelManager` to the command parser, makes a better separation of concern.
    * Cons: Harder to do unit tests on this implementation since it uses functinal interface.

* **Alternative 2:** Pass the ModelManager to `AddTransactionCommandParser`.
    * Pros: easier to implement.
    * Cons: Not a good design as model should only resides inside `Command#execute`.
    
### Find Transaction

### Implementation

In `findTransaction` implementation, user will have to specify
the `Person` using the index displayed.
However, we have a similar issue as AddTransaction. To get all transactions 
that the owner has, we need to convert the index to the correct `personIdentifier`.
This `personIdentifier` will be used as the `TransactionPredicate` to filter the `Transaction
List`.

This issue is addressed by using the class `TransactionPredicateBuilder` that takes a `personIdentifier`
and returns the desired `TransactionPredicate`.

![Find Transaction activity diagram](images/FindTransactionActivityDiagram.png)

*Figure: Simplified activity diagram showing the sequence of action of Find Transaction command.*

Given below is an example usage scenario and how `Find Transaction` behaves at each step:
1. The user input `findTransaction 1`.
2. `LogicManager` execute the command which will be parsed using `AddressBookParser` and `FindTransactionCommandParser`
   if the input is valid.
3. `FindTransactionCommandParser` will return `FindTransactionCommand` which contains `TransactionPredicateBuilder`.
   This will be used later by `FindTransactionCommand#execute` to
   generate the appropriate `TransactionPredicate`.
4. `AddTransactionCommand` will then update the `Transaction List` by calling `Model#updateFilteredTransactionList`
   using `TransactionPredicate`.
5. `CommandResult` will be returned back to the `LogicManager` if there is no failure in execution.

![Find Transaction sequence diagram](images/FindTransactionSequenceDiagram.png)
*Figure: Simplified sequence diagram showing the sequence of action of Find Transaction command.*

#### Design considerations

**Aspect: How find transaction executes**

* **Alternative 1 (current choice):** Store `TransactionPredicateBuilder` to generate the `TransactionPredicate` later.
    * Pros: No need to pass the `ModelManager` to the command parser, makes a better separation of concern.
    * Cons: Harder to do unit tests on this implementation since it uses functinal interface.

* **Alternative 2:** Pass the ModelManager to `FindTransactionCommandParser`.
    * Pros: easier to implement.
    * Cons: Not a good design as model should only resides inside `Command#execute`.

### Pay 

### Implementation

This command will update the `Status` of the `Transaction` to `paid` using
the specified `transaction index` by the user.

![Pay activity diagram](images/PayDiagram.png)

*Figure: Simplified activity diagram showing the sequence of action of Pay command.*

Given below is an example usage scenario and how `Pay` behaves at each step:
1. The user input `pay 1`.
2. `LogicManager` execute the command which will be parsed using `AddressBookParser` and `PayCommandParser`
   if the input is valid.
3. `PayCommand` will return `PayCommand` which contains the index of the transaction.
4. In `PayCommand#execute`, the `transactionToUpdate` will be replaced by `updatedTransaction` by calling
    `Model#setTransaction`.
5. `CommandResult` will be returned back to the `LogicManager` if there is no failure in execution.

![Pay sequence diagram](images/PaySequenceDiagram.png)
*Figure: Simplified sequence diagram showing the sequence of action of Pay command.*

<div markdown="1" class="alert alert-info">:information_source: **Info**<br>

Although `listTransaction`, `deleteTransaction`, and `unpay` are not shown here,
the implementation of these commands are very similar to the other transaction
commands given.

</div>

### Undo

#### Implementation

The undo mechanism is facilitated by `SerializableTempAddressBookStorage`.
It extends the `TempAddressBookStorage` interface and is stored and managed at `StorageManager` as `tempAddressBookStorage`.

This mechanism will store the previous states of CinnamonBun in temporary files when a modification or change is made to the data.
When users type the `undo` command, the latest stored state will be restored.

Additionally, it implements the follow operations which are exposed in the `Storage` interface:
- `SerializableTempAddressBookStorage#getTempAddressBookFilepath()` --- Gets the file path of where the temporary files are stored.
- `SerializableTempAddressBookStorage#addNewTempAddressBookFile()` --- Saves the current state of CinnamonBun and keep track of the version.
- `SerializableTempAddressBookStorage#popTempAddressFileData()` --- Gets the latest state of CinnamonBun stored in `SerializableTempAddressBookStorage`.

![Undo activity diagram](images/TemporaryAddressBookStorage.png)

Given below is an example usage scenario and how the undo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `SerializableTempAddressBookStorage` will be initialized
with the file path of where the temporary files will be saved, and an empty `ArrayList<Path>` named `tempFiles` is created to store the file paths of the
temporary files that will be created.

![State 1 undo image](images/UndoState.png)

Step 2. The user makes a modification to the client list such as executing the command `delete 5` to delete the 5th client.
In `LogicManager`, before executing the command, it will locally store the current client list's state.

After executing the command, it will compare the previous state with the current state of the client list. If it senses they are different, such as in this
case since user has deleted the 5th client, it will call `LogicManager#savePrevAddressBookDataInTemp()`. 

From there, it will call `Storage#addNewTempAddressBookFile()` where the previous state of the client list, before the `delete 5` was executed, will be saved as a temporary file.
The new temporary file will then be added into `tempFiles` list in `SerializableTempAddressBookStorage`.

<div markdown="1" class="alert alert-info">:information_source: **Info**<br>

Only modifications made to the client list or transaction list will be saved. See the user guide for more info.

`SerializableTempAddressBookStorage` also will only store the 10 latest modifications. When the 11th modification is made, it will remove
the earliest modification saved and delete the temporary file for it.

If there are any issues with creating the temporary file and saving the data, an exception will be thrown and the modification will not be saved in
a temporary file, thus, the modification cannot be undone.

</div>

![State 2 undo image](images/UndoState1.png)


Step 3. The user executed `add n/David...` to add a new client. The steps mentioned in step 2 would be repeated. Thus, `tempFiles` will now store
2 file paths to the 2 temporary files created.

![State 3 undo image](images/UndoState2.png)

Step 4. The user now decides that adding the client was a mistake and decides to undo that action by executing the `undo` command.
The `undo` command will call `LogicManager#undoPrevModification()`, which calls `Storage#popTempAddressFileData()`. This will obtain
the latest temporary file added and restore the client list to the state saved in the temporary file (the state of the client list before add the new client, so before step 3).

<div markdown="1" class="alert alert-info">:information_source: **Info**<br>

If there are no previous modifications to be restored (no modifications in the first place, or the user has undone the last 10 modifications and reached
the limit) an error message will be shown.

If the temporary file for the modification user is trying to undo is corrupted or deleted, then for that particular modification it can no longer
be undone and an error message will be shown there was issue reading the file.

</div>

![State 4 undo image](images/UndoState3.png)

The following sequence diagram shows how the undo operation works:

![Undo sequence diagram](images/UndoSequenceDiagram.png)


Step 5. The user executed the command `filter Daniel` to find all Daniels. This command does not modify the client list.
Hence, `Storage#addNewTempAddressBookFile()` will not be called and no new temporary files will be added.

![State 5 undo image](images/UndoState4.png)

Step 6. The user closes the CinnamonBun application. All temporary files created will be deleted.

#### Design considerations

**Aspect: How undo executes**

* **Alternative 1 (current choice):** Saves the entire application data in temporary files.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of storage and memory usage.

* **Alternative 2:** Individual command knows how to undo by itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the client being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

### Clear Filtered functionality

The `clearFiltered` commands works in tandem with the `find` or `listMembers` command. After the `find`/`listMembers` function populates
`ModelManager`'s `filteredPersons`, the `clearFiltered` commands retrieves the `filteredPersons` from the `Model` and
deletes all people in `filteredPersons` using the `deletePerson` function from `Model`.

If the `find` or `listMembers` command had not been executed beforehand, the `clearFiltered` command will still function
as if clearing the entire address book, as by default, the `filteredPersons` will be the whole address book.

If there are no people in the `filteredPersons`, the `CommandResult` returned has a message "No clients to delete.".

Below is a sequence diagram to show how the `ClearFilteredCommand` executes.

![Clear Filtered Sequence Diagram](images/ClearFilteredSequenceDiagram.png)

<div markdown="1" class="alert alert-info">:information_source: **Info**<br>

The lifeline for `ClearFilteredCommand` should end at its destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
  
</div>

### Membership functionality

#### Implementation
The membership functionality allows users to be assigned a membership tier, either 'Bronze', 'Silver' or 'Gold'.
It inherits from the `Field` abstract class and is stored as a `Field` in fields HashMap of the `Person` class.

Below is a figure of the class diagram of the `Membership` class.

![Membership Class Diagram](images/MembershipClassDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Info**<br>

The `ALL` tier in the `Tier` enum is used for `listMembers` functionality.

</div>

When the user calls the `addMembership` functionality along with a specified client and membership tier, a Membership 
object is created and stored in the fields HashMap of the specified client with the key being the `Membership` prefix and functions like any other `Field`.
The `removeMembership` functions in a similar manner to other fields in that the `Field` with the `Membership` prefix is removed
from the fields HashMap.

The `listMembers` command functions in a similar fashion to the `Find` command in that it uses a `Predicate` in order to filter
clients with a Membership. It utilizes the `PersonContainsMembershipPredicate` which checks if a user has a `Membership` stored
in its fields HashMap.

When a client's membership is displayed on the screen, the colour of the membership `Label` changes according to the membership
tier. This is done by having a `FlowPane` stored in the `PersonCard` class. When a membership is displayed, a `Label` with
a different tier is created with a different id. The CSS files (`Cinnamon.css` or `Caramel.css`) then decides what colour to render the label background accordingly.

#### Design considerations

**Aspect: How it executes**

* **Alternative 1 (current choice):** Store a `Membership` object in each user.
  * Pros: Easy to implement.
  * Cons: Membership functionality is limited (only able to have predefined membership tiers).

* **Alternative 2:** Create a list of memberships and assign users a membership index.
  * Pros: Allows for more flexible memberships (more than just gold,silver,bronze) with extra details such as descriptions.
  * Cons: Harder to implement.

### Sort functionality

**Implementation**

The sort mechanism is facilitated by `SortCommand`. It extends `Command` and the main logic of sort is in it's
`execute` function which returns a `CommandResult` object.

The `SortCommand#execute()` function would first parse the user's inputs. For every field parsed, the function would create a 
`Comparator` for that field using either of the functions:

* `SortCommand#getComparatorDefault()` --- Creates a comparator with the field specified in ascending order.
* `SortCommand#getComparatorDescending()` --- Creates a comparator with the field specified in descending order.

One sort command allows for sorting multiple fields at a time in the order specified. Stating `sort n/ a/` means 
sort the list by name in ascending order followed by the client's addresses. Clients with same name would be then
sorted based on their addresses. 

Thus, after creating the `Comparator` for a particular field, it'll be added upon a previously created `Comparator` using `Comparator#thenComparing()`.

To be able to sort the client list, we expose an operation in the `Model` interface as `Model#sortPersonList()`.
We then passed the `Comparator` created to `Model#sortPersonList()` in `SortCommand#execute()`.

Java's `List` library will then handle the sorting based on the `Comparator`.

#### Design considerations

**Aspect: How it executes**

* **Alternative 1 (current choice):** Each `Field` class will handle how to sort its own data, `SortCommand` will then
wrap it into a comparator and pass to `Model#sortPersonList()`.
  * Pros: Easy to implement, each field class can handle their own sorting of its data. Will not clutter `SortCommand`.
  * Cons: Does not allow for more complicated chaining of fields since the way each field is being sorted is independent of others.

* **Alternative 2:** `SortCommand` will determine how the fields are to be sorted.
  * Pros: Allows `SortCommand` to have full flexibility in deciding how the fields are to be sorted and may allow for more complicated chaining of fields.
  * Cons: Will clutter `SortCommand` and may not be manageable once there are a lot of fields.

### Command chaining

#### Implementation

The command chaining mechanism is handled in the `execute()` function of the `LogicManager` class which is where the user's input is parsed, executed and then returned as a `CommandResult`.

To handle multiple commands, the program splits the given user input using the `|` delimiter used to separate the multiple commands. Once the input has been split, the program can then evaluate each command sequentially by iterating through the individual commands.

While iterating through the individual commands, the program checks if any of the commands are a special command - `help`, `exit` and `undo` - or if it is invalid. If any of these conditions are met, the program will then set the `isValid` boolean to false, break out of the loop and set the addressBook of the model to one that was taken before the start of command execution, essentially reverting all the changes.

#### Design considerations

**Aspect: Behaviour of command chains with special commands and errors**

* **Alternative 1:** The valid commands before a special command `help`, `exit` or `undo` or command error will still be executed
    * Pros: Easier to implement as there is no need to check the validity of each command and reset the model.
    * Cons: Will only execute certain commands instead of all when a chain is broken which may be confusing. The undo feature may be harder to implement since a command can be partially run.

* **Alternative 2 (current choice):** Disregard all commands in a chain whenever a special command or error is found.
    * Pros: Intuitive and plays well with other features such as `undo`.
    * Cons: Command validity has to be caught and handled in execution() which may slow down performance.

### Command completion/correction

**Implementation**

First, the program checks if the given input or the last command is blank. It is hardcoded to complete such cases with an `add` command as I thought that was more fitting and also because it would most probably get completed with `add` anyway after running through the completion algorithm.

If the input is not blank, it will get the last word in the user's input and first try to complete it by checking if it is a substring of any command. If it is a substring of a command, it will be completed with that command. If the word is a substring of multiple commands, the shortest command will be used.

Once the program determines that the last word cannot be completed, it will try to correct it by finding the Levenshtein distance between the word and all commands. The Levenshtein distance between two words is the minimum number of single-character edits (insertions, deletions or substitutions) required to change one word into the other. The command with the smallest distance from the word will be used as the correction. In the event of a tie, the first evaluated command will be used.

The Levenshtein distance is calculated using the `editDistance(String str1, String str2)` method which uses a typical dynamic programming algorithm with a runtime of O(m * n). Unlike standard algorithms, however, this method uses a fixed height matrix with only two rows that the program alternates between during computation. This means the size is bounded by the length of `str2` which in this case will be one of the fixed commands.

#### Design considerations

**Aspect: Behaviour of command completion/correction**

* **Alternative 1:** Suggestions will be shown to the user in realtime while they are typing.
    * Pros: The user will be able to view each suggestion and will have much more information and freedom to decide whether to take up a suggestion.
    * Cons: A lot harder to implement in terms of logic, storage and UI.

* **Alternative 2 (current choice):** Complete/correct on demand. Take away user choice and provide what the program thinks is the most accurate replacement.
    * Pros: Less computationally intensive and a lot easier to implement.
    * Cons: The user will not have a choice of suggestions and will not know what they'll get (blackbox).

### Command history

#### Implementation

The command history is handled by `CommandBox`, which also handles the user input.
This mechanism aims to be as close to the history mechanism in the Linux Bash terminal as possible.

Here is a brief example of how the command history works:
* The command history only begins when the application is launched, and is cleared when the application closes.
* Each time the user presses the up arrow key, the command box will be replaced with the command they previously executed, until they reach the start of their history.
* Each time the user presses the down arrow key, the command box will be replaced with the command they next executed, until they reach the latest command (the current one they have not executed).
* If the user executes duplicate commands one after another, only the first will be added to history.
  * For example, if the user executes `list`, `list`, `list`, only the first `list` is added to history. 
  
The command history works with the use of two `ArrayList<String>` named `historyBuffer` and `activeBuffer`.

`historyBuffer` contains the commands that was actually executed by the user (i.e., the user pressed enter).
Whenever the user executes a command, regardless whether it is valid or not, the command is added to a `historyBuffer`.
Commands in `historyBuffer`, once added, will never change.

`activeBuffer` is a duplicate of `historyBuffer` that allows editing. Whenever the user cycles through their history and edits the command, they are editing the commands of `activeBuffer`.
When the user edits a previous command and executes, the content of that command in `activeBuffer` is restored with the original in `historyBuffer`.

Example usage (red arrow is what the command box is displaying):

Step 1. ![Command History Flow 1](images/CommandHistoryFlow1.png)

*Figure: User starts with a blank command box.*

Step 2. ![Command History Flow 2](images/CommandHistoryFlow2.png)

*Figure: User types `append 1 t/vendor`.*

Step 3. ![Command History Flow 3](images/CommandHistoryFlow3.png)

*Figure: User executes `append 1 t/vendor`. `append 1 t/vendor` is appended to `historyBuffer` and `activeBuffer`. Command box is blank again.*

Step 4. ![Command History Flow 4](images/CommandHistoryFlow4.png)

*Figure: User types `edit 2 e/` but does not execute. User then presses the up arrow key to cycle to `addMembership 1 m/silver`.*

Step 5. ![Command History Flow 5](images/CommandHistoryFlow5.png)

*Figure: User changes `addMembership 1 m/silver` to `addMembership 3 m/bronze`.*

Step 6. ![Command History Flow 6](images/CommandHistoryFlow6.png)

*Figure: User executes `addMembership 3 m/bronze`. `addMembership 3 m/bronze` is appended to `historyBuffer` and `activeBuffer`. Un-executed command `edit 2 e/` is erased. Command box is blank again. `addMembership 1 m/silver` in `activeBuffer` is restored.*

Step 7. ![Command History Flow 7](images/CommandHistoryFlow7.png)

*Figure: User cycles to `listTransaction` and changes it to `random invalid string`.*

Step 8. ![Command History Flow 8](images/CommandHistoryFlow8.png)

*Figure: User cycles to `delete 5` and changes it to `delete 7`.*

Step 9. ![Command History Flow 9](images/CommandHistoryFlow9.png)

*Figure: User executes `delete 7`. `delete 7` is appended to `historyBuffer` and `activeBuffer`. Command box is blank again. `delete 5` in `activeBuffer` is restored. `listTransaction` is not restored.*

#### Design considerations

**Aspect: Functionality**

* **Alternative 1**:
Have a very simple implementation with only one history list.
Do not store edits for every single entry, and simply replace the command box with the previous command. 
If the user presses up or down, any edits are lost.
  * Pros: Very simple to implement.
  * Cons: Less feature rich.

* **Alternative 2 (current choice)**
Store the history using two lists, and store edits for every command. If the user presses up or down, edits are not lost.
  * Pros: Very similar to Linux Bash terminal, intuitive for experienced command line users.
  * Cons: More complicated to implement.

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

A small business owner or freelancer that:
1. has a need to manage not more than 1000 clients.
2. has a need to keep track of transactions with clients.
3. prefers desktop applications over other types.
4. can type fast.
5. prefers keyboard interactions to mouse interactions.
6. is reasonably comfortable using CLI apps.

**Value proposition**:

CinnamonBun is a free, open source, purpose built application that handles client and transaction information easily.
CinnamonBun helps small businesses and freelancers on a budget to get up and running quickly. 

### User stories

Priorities: High (must have), Medium (nice to have), Low (unlikely to have)

| Priority | As a …​ | I want to …​                                               | So that I can…​                                                         |
|----------|---------|------------------------------------------------------------|-------------------------------------------------------------------------|
| `HIGH`   | user    | add new clients                                            | keep my client list updated.                                            |
| `HIGH`   | user    | delete clients                                             | remove those who are no longer my clients.                              |
| `HIGH`   | user    | edit client information                                    | keep my clients' information updated.                                   |
| `HIGH`   | user    | list all my clients                                        | see all my client's information.                                        |
| `HIGH`   | user    | find client via keywords                                   | easily find specific clients.                                           |
| `MEDIUM` | user    | delete clients via filtered keywords                       | easily manipulate and delete people from CinnamonBun.                   |
| `MEDIUM` | user    | add membership to clients                                  | have a rewards program for my clients.                                  |
| `MEDIUM` | user    | sort my clients                                            | sort the clients based on fields I want.                                |
| `LOW`    | user    | store my client's birthday                                 | give them discounts on their birthday.                                  |
| `LOW`    | user    | store my client's age                                      | know if I can sell them age restricted items.                           |
| `HIGH`   | user    | add client transaction                                     | store the transaction history of my clients.                            |
| `HIGH`   | user    | delete a transaction                                       | delete the transaction from the transaction list.                       |
| `MEDIUM` | user    | chain multiple commands                                    | execute multiple commands at once.                                      |
| `LOW`    | user    | list all clients who are members.                          |                                                                         |
| `LOW`    | user    | assign different tiers of memberships to my clients.       |                                                                         |
| `MEDIUM` | user    | undo previous modifications made to the data               | undo mistakes.                                                          |
| `MEDIUM` | user    | have to ability to auto-complete/auto-correct the commands | type faster.                                                            |
| `MEDIUM` | user    | append data fields and tags to clients                     | append fields and tags to a client without editing the entire client.   |
| `MEDIUM` | user    | remove data fields and tags from clients                   | remove fields and tags from a client without editing the entire client. |
| `HIGH`   | user    | be able to re-input my previous commands                   | execute similar commands without having to retype the whole command.    |
| `HIGH`   | user    | switch between light and dark themes.                      |                                                                         |
| `HIGH`   | user    | list all transactions                                      |                                                                         |
| `HIGH`   | user    | find a client's transactions                               | see all the transactions associated with a particular client.           |
| `MEDIUM` | user    | pay a transaction                                          | change the status of the transaction to be paid.                        |
| `MEDIUM` | user    | unpay a transaction                                        | change the status of the transaction to be unpaid.                      |
| `MEDIUM` | user    | flag a transaction as paid or unpaid                       | know if a transaction has been paid.                                    |
| `LOW`    | user    | click on a client to see the list of their transactions    | see a client's transactions without typing.                             |
| `HIGH`   | user    | remove memberships from clients                            | remove a client from my rewards program.                                |

### Use cases

For all use cases below, the **System** is `CinnamonBun` and the **Actor** is the `user`, unless specified otherwise.

#### Use case: Get client list

**MSS**

1. User requests to list all clients.
2. CinnamonBun displays a list of all clients.
   <br>Use case ends.

#### Use case: Add a client

**MSS**

1. User requests to add a new client with specified fields.
2. CinnamonBun adds the client with the specified fields.
   <br>Use case ends.

**Extension**

* 1a. CinnamonBun detects that the specified fields do not satisfy requirements. 
  * 1a1. CinnamonBun displays error message.
    <br>Use case ends.
* 1b. CinnamonBun detects a duplicate client.
    * 1a1. CinnamonBun displays error message.
      <br>Use case ends.

#### Use case: Edit a client's information

**MSS**
1. User specify which client to be edited
2. User inputs the values to be edited
3. CinnamonBun edits the value
   <br>Use case ends.

**Extensions**

* 1a. No client index specified
    * 1a1. CinnamonBun shows an error message.
      <br>Use case resumes at step 1.
* 2a. No fields are provided
    * 2a1. CinnamonBun shows an error message.
      <br>Use case resumes at step 2.
* 2b. Some fields are inputted wrongly
    * 2b1. CinnamonBun shows the appropriate error message.
      <br>Use case resumes at step 2.
* 2c. Value edited is email and there is already an existing email by another client in CinnamonBun
    * 2c1. CinnamonBun shows an error message.
      <br>Use case resumes at step 2.

#### Use case: Append fields and tags to a client

**MSS**

1. User specify which client to be edited.
2. User input the fields and tags to be appended.
3. CinnamonBun appends the fields and tags to the client.
   <br>Use case ends.

**Extensions**

* 1a. CinnamonBun detects that the specified fields do not satisfy requirements.
    * 1a1. CinnamonBun displays error message.
      <br>Use case ends.
* 1b. CinnamonBun detects a duplicate client.
    * 1a1. CinnamonBun displays error message.
      <br>Use case ends.

#### Use case: Remove fields and tags from a client

**MSS**

1. User specify which client to be edited.
2. User input the fields and tags to be removed.
3. CinnamonBun removes the fields and tags from the client.
   <br>Use case ends.

**Extensions**

* 1a. CinnamonBun detects that the specified fields do not satisfy requirements.
    * 1a1. CinnamonBun displays error message.
      <br>Use case ends.

#### Use case: Add remark to a client

**MSS**

1. User specify which client to be edited.
2. User input the remark to be added.
3. CinnamonBun adds the remark to the client.
   <br>Use case ends.

#### Use case: Delete a client

**MSS**

1.  User requests to list clients.
2.  CinnamonBun shows a list of clients.
3.  User specify which client to be deleted.
4.  CinnamonBun deletes the specified client.
    <br>Use case ends.

**Extensions**

* 2a. The list is empty.
  <br>Use case ends.
* 3a. The given index is invalid.
    * 3a1. CinnamonBun shows an error message.
      <br>Use case resumes at step 2.

#### Use case: Find a client

**MSS**

1. User requests to find clients with the specified keyword(s)
2. CinnamonBun shows a list of clients with an attribute containing at least one keyword
   <br> Use case ends.

**Extensions**

* 1a. No keyword is specified.
    * 1a1. CinnamonBun shows an error message.
      <br>Use case resumes at step 2.
* 2a. The list is empty.
  <br>Use case ends.

#### Use case: Sort customer list

**MSS**

1. User inputs the fields the list is to be sorted on.
2. CinnamonBun sorts the client list accordingly in order of the fields specified.
3. The sorted list is displayed.
   <br>Use case ends.

**Extensions**

* 1a. User inputs no fields
  * 1a1. An error message is shown.
    <br>Use case resumes at step 1.
* 1b. User inputs non-existent/not supported fields
  * 1b1. An error message is shown
     <br>Use case resumes at step 1

#### Use case: Add a transaction

**MSS**

1. User specify which client to add the transaction to.
2. User input the parameters of the transactions (Amount, Transaction Date,
   Due Date, Note, and Status).
3. CinnamonBun add the transaction to the transaction list.
   <br>Use case ends.

**Extensions**

* 1a. CinnamonBun detects that the specified fields do not satisfy requirements.
    * 1a1. CinnamonBun displays error message.
      <br>Use case ends.

#### Use case: Delete a transaction

**MSS**

1. User specify which transaction to be deleted.
2. CinnamonBun removes the transaction from the transaction list.
   <br>Use case ends.

**Extensions**

* 1a. CinnamonBun detects that the specified index does not satisfy requirements.
    * 1a1. CinnamonBun displays error message.
      <br>Use case ends.

#### Use case: List all transactions

**MSS**

1. User specify the command keyword.
2. CinnamonBun displays all transactions in the application.
   <br>Use case ends.

#### Use case: Find transactions from a client

**MSS**

1. User specify which client's transactions be displayed. 
2. CinnamonBun displays all transactions from the client.
   <br>Use case ends.

**Extensions**

* 1a. CinnamonBun detects that the specified index does not satisfy requirements.
    * 1a1. CinnamonBun displays error message.
      <br>Use case ends.

#### Use case: Pay a transaction

**MSS**

1. User specify which transaction to be paid.
2. CinnamonBun changes the status of the transaction to be paid.
   <br>Use case ends.

**Extensions**

* 1a. CinnamonBun detects that the specified index does not satisfy requirements.
    * 1a1. CinnamonBun displays error message.
      <br>Use case ends.

#### Use case: Unpay a transaction

**MSS**

1. User specify which transaction to be unpaid.
2. CinnamonBun changes the status of the transaction to be unpaid.
   <br>Use case ends.

**Extensions**

* 1a. CinnamonBun detects that the specified index does not satisfy requirements.
    * 1a1. CinnamonBun displays error message.
      <br>Use case ends.

#### Use case: Undo modifications

**MSS**

1. User undos the latest modification made to the Clients' list in the CinnamonBun.
2. The modifications have been undone.
3. CinnamonBun shows the earlier client list without the modifications.
   <br>Use case ends.

**Extensions**

* 1a. There have been no modification made prior to calling undo.
  * 1a1. CinnamonBun shows an error message.
    <br>Use case ends.
* 1b. The previous data cannot be read due to file reading issues/corruption
  * 1b1. CinnamonBun shows an error message.
    <br>Use case ends.

#### Use case: Add membership to a client

**MSS**

1.  User specifies which client to add membership to and what membership tier to assign to the client
3.  CinnamonBun assigned the membership to the specified client
4.  CinnamonBun displays the list of clients with updated membership
    <br>Use case ends.

**Extensions**

* 1a. The given index is invalid.
    * 1a1. CinnamonBun shows an error message.
      <br>Use case ends.
* 1b. The given membership tier is invalid.
    * 1b1. CinnamonBun shows an error message.
      <br>Use case ends.
    
### Non-functional requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 clients without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. A user should be able to easily find a client.
5. A user should be able to easily navigate the interface.
6. Commands should be able to execute without any noticeable latency.
7. Error messages when inputting invalid commands will be displayed in the output box.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="1" class="alert alert-info">:bulb: **Note**<br>

These instructions only provide a starting point for testers to work on; testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

* Initial launch
   1. Download the jar file and copy into an empty folder
   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

* Saving window preferences
   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   2. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

### Adding a client

* Adding a new client while all clients are being shown
  1. Test case: `add n/Gawr Gura p/12345678 e/gura@hololive.com a/123 Atlantis`<br>
     Expected: A new client named `Gawr Gura` should appear at the bottom of the client list.
  2. Test case: `add n/Ceres Fauna p/12345678`<br>
     Expected: No new client is added as not all required fields are provided. Error message is shown. 

### Editing a client

* Editing a client while all clients are being shown
  1. Prerequisites: There needs to be existing client data in the client list.
  2. Test case: `edit 1 e/client_name@example.com`<br>
     Expected: First client's email is changed to `client_name@example.com`.
  3. Test case: `edit 2 e/client_name@example.com`<br>
     Expected: Second client's email is not changed as the email is already in use. Error message is shown.

### Append fields to a client

* Append fields to a client.
    1. Prerequisites: There needs to be existing client data in the client list.
    2. Test case: `append 1 b/1999-02-17`<br>
       Expected: First client's birthday is set to `1999-02-17`.
    3. Test case: `append 1 n/Ookami Mio`<br>
       Expected: First client's name is set to `Ookami Mio`.

### Remove fields from a client

* Remove fields from a client.
    1. Prerequisites: There needs to be existing client data in the client list.
    2. Append a tag and birthday to the first client using the command `append 1 t/vendor b/1999-02-17`.
    3. Test case: `remove 1 b/`<br>
       Expected: First client's birthday is removed.
    4. Test case: `remove 1 t/vendor`<br>
       Expected: First client's `vendor` tag is removed.
    5. Test case: `remove 1 n/`<br>
       Expected: First client's name is not removed as every client must have a name. Error message is shown.

### Add or remove remark from a client

* Add or remove remark from a client.
    1. Prerequisites: There needs to be existing client data in the client list.
    2. Test case: `remark 1 r/High shipping cost.`<br>
       Expected: First client's remark is set to `High shipping cost.`.
    3. Test case: `remark 1 r/`<br>
       Expected: First client's remark removed.

### Deleting a client

* Deleting a client while all clients are being shown
    1. Prerequisites: There needs to be existing client data in the client list.
    2. Test case: `delete 1`<br>
       Expected: First client is deleted from the list.
    3. Test case: `delete 0`<br>
       Expected: No client is deleted. Error message is shown.
    4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: No client is deleted. Error message is shown.

### Sorting the client list

* Sorting the client list based on certain fields
   1. Prerequisites: There needs to be existing client data in the client list.
   2. Test case: `sort n/`<br>
      Expected: The client list will display the clients in ascending alphabetical order.
   3. Test case: `sort n/ a/ p/ desc`<br>
      Expected: The client list will display the clients in ascending alphabetical order. Clients with the same name will
      then be displayed according to their addresses in ascending order. And if they also have the same address, they'll be 
      displayed based on their phone number in descending order.
   4. Test case: `sort l:)/ djewijw p/`<br>
      Expected: An error would be thrown as the fields specified do not exist.

### Adding a Transaction

* Adding a new transaction while all transactions are being shown
    1. Test case: `addTransaction 1 a/12 td/2020-11-11 dd/2020-12-12 n/this is a note --paid` <br>
       Expected: A new paid transaction with `Amount` 12 should appear at the bottom of the transaction list.
    2. Test case: `addTransaction 1 a/12 td/2020-11-11 dd/2020-12-12 n/this is a note` <br>
       Expected: A new unpaid transaction with `Amount` 12 should appear at the bottom of the transaction list.
    3. Test case: `addTransaction 1 td/2020-11-11 dd/2020-12-12 n/this is a note` <br>
       Expected: No new transaction is added as not all required fields are provided. Error message is shown.
    4. Test case: `addTransaction 1 a/12 td/2020-11-11 dd/2002-12-12 n/this is a note` <br>
       Expected: No new transaction is added as not the due date is before the transaction date. Error message is shown. 
  
### Deleting a Transaction

* Deleting a transaction while all transactions are being shown
    1. Test case: `deleteTransaction 1` <br>
       Expected: The first transaction is deleted.
    2. Test case: `deleteTransaction 0` <br>
       Expected: No transaction is deleted as the provided index is invalid. Error message is shown.
    3. Test case: `deleteTransaction x` where x is larger than the transaction list size <br>
       Expected: No transaction is deleted as the provided index is invalid. Error message is shown.
    4. Test case: `deleteTransaction` <br>
       Expected: No transaction is deleted as the required field is not provided. Error message is shown.

### List a Transaction

* list all transactions
    1. Test case: `listTransaction` <br>
       Expected: All transactions will be listed.
  1. Test case: `listTransaction abcd` <br>
     Expected: All transactions will be listed.

### Find Transactions

* Find transactions while all clients are being shown
    1. Test case: `findTransaction 1` <br>
       Expected: All transactions from the first client are listed.
    2. Test case: `findTransaction 0` <br>
       Expected: No transaction is shown as the provided index is invalid. Error message is shown.
    3. Test case: `findTransaction x` where x is larger than the client list size <br>
       Expected: No transaction is listed as the provided index is invalid. Error message is shown.
    4. Test case: `findTransaction` <br>
       Expected: No transaction is listed as the required field is not provided. Error message is shown.
    
### Pay

* Pay a transaction while all transactions are being shown
    1. Test case: `pay 1` <br>
       Expected: Status of the first transaction of the transaction list is changed to `paid`.
    2. Test case: `pay 0` <br>
       Expected: No transaction status is changed to `paid` as the provided index is invalid. Error message is shown.
    3. Test case: `pay x` where x is larger than the transaction list size <br>
       Expected: No transaction status is changed to `paid` as the provided index is invalid. Error message is shown.
    4. Test case: `pay` <br>
       Expected: No transaction status is changed to `paid` as the required field is not provided. Error message is shown.

### Unpay

* Unpay a transaction while all transactions are being shown
    1. Test case: `unpay 1` <br>
       Expected: Status of the first transaction of the transaction list is changed to `unpaid`.
    2. Test case: `unpay 0` <br>
       Expected: No transaction status is changed to `unpaid` as the provided index is invalid. Error message is shown.
    3. Test case: `unpay x` where x is larger than the transaction list size <br>
       Expected: No transaction status is changed to `unpaid` as the provided index is invalid. Error message is shown.
    4. Test case: `unpay` <br>
       Expected: No transaction status is changed to `unpaid` as the required field is not provided. Error message is shown.

### Undo data modifications

* Undo a modification that was previously made
   1. Prerequisites: There needs to be modifications made to the client list.
   2. Test case: `undo` <br>
      Expected: The previous modification done will be reverted and the application will display the previous version
      of the client list.
* Undo a modification when there are none
   1. Prerequisites: No modifications were made since the start of the application or all modifications have been reverted.
   2. Test case: `undo` <br>
      Expected: An error message will be shown stating that there is nothing to undo since there are no modifications.
* Only able to undo the 10 latest modifications.
   1. Prerequisites: More than 10 modifications were made without reverting any of them.
   2. Test case: `undo` 11 times <br>
      Expected: Notice that it can only revert the client list by the latest 10 modifications made and not the modifications before those.
      At the 11th `undo`, will show an error message stating that there is nothing to undo since there are no modifications.
* Handling temporary file corruption.
   1. Prerequisites: Some modifications were made, but the latest temporary files in the `data\.tempdata` are either corrupted or deleted
   by the user and not the application.
   2. Test case: `undo` <br>
      Expected: An error message would be shown stating it cannot read the temporary file. The temporary file if it's not
      already deleted by the user, will then be deleted by the application. The client list will not be able to revert to before the modification stored 
      in the corrupted temporary file. 
   
      However, if the user were to call `undo` again, and if the second-latest temporary file data
      is not corrupted or deleted by user, the application will be able to revert the client list to the state stored in the temporary file.
      Thus, effectively undoing the latest 2 modifications.

### Adding membership

* Adding a membership to a user
    1. Prerequisites: There needs to be existing client data in the client list.
    2. Test case: `addMembership 1 m/gold`<br>
       Expected: The client at index 1 will have a gold membership assigned to him.
    3. Test case: `addMembership 3 m/bronze`<br>
       Expected: The client at index 3 will have a bronze membership assigned to him.
    4. Test case: `addMembership 1 m/platinum`<br>
       Expected: An error will be displayed as there is no tier called 'platinum'.
    5. Test case: `addMembership -1 m/silver`<br>
       Expected: An error will be displayed as -1 is an invalid index.

### Removing membership

* Removing a membership from a user
    1. Prerequisites: There needs to be existing client data in the client list with a membership.
    2. Test case: `removeMembership 1` (User at index 1 has a membership)<br>
       Expected: The client at index 1 will have his membership removed.
    3. Test case: `removeMembership 1` (User at index 1 has no membership)<br>
       Expected: Error message showing that user at that index has no membership.
    4. Test case: `removeMembership -1`<br>
       Expected: An error will be displayed as -1 is an invalid index.

### Listing all members

* Listing members
    1. Prerequisites: There needs to be existing client data in the client list with memberships.
    2. Test case: `listMembers` (Some users have memberships)<br>
       Expected: List of all members will be displayed.
    3. Test case: `listMembers` (No users have memberships)<br>
       Expected: No clients displayed.
    4. Test case: `listMembers gold` (Some users have gold memberships)<br>
       Expected: All members with gold membership will be displayed. 
    5. Test case: `listMembers hello`<br>
       Expected: Error message shown as hello is not a valid membership tier.


### Saving data

* Dealing with missing/corrupted data files
   1. Test case: Delete the `CinnamonBun.save` file in the data folder which should be in the same folder as the jar file.
    This will simulate a missing save file.<br>
    Expected: A data set with some default users will be used and a new save file will be created after running any command.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Effort**

This appendix will highlight the difficulty level, challenges faced, effort required, and achievements of CinnamonBun.

### Time constraint and high workload

This semester, all of our team members had high workload from multiple modules. The amount of effort that TP required was very taxing on all members of the team.
We felt that the requirements of TP was too much (application, UG, DG, weekly quizzes).

Despite that, the team still managed to deliver a high quality product at the end.

### Person, Field and Tag

The `Person` and `Tag` classes were modified from AB-3, while `Field` is an abstract class representing existing classes such as `Name`, `Email` etc. as well as new classes such as `Birthday`, `Remark` etc.
Originally, all of `Person`'s fields were hard-coded into the `Person` class, resulting in extremely high coupling, and it was very difficult to add new fields to `Person`.
A lot of effort had to be put into fixing this issue. Due to the extremely high coupling, this changed affected multiple systems across the entire project, including the saving system, edit command, and many more.
It took almost two weeks to rewrite multiple systems while ensuring compatibility with other existing systems and test cases.

Fortunately, these changes allowed some features we added later on to be done much more easily compared to when the project first started.
