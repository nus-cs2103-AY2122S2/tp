---
layout: page
title: Xing Zheng's Project Portfolio Page
---

### Project: FoodOnWheels

FoodOnWheels is a desktop application used to delivery orders for a restaurant by tracking and providing updates of the status of delivery orders. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**:
* **Model**:
  * **Order Class**: Implemented the `Order` class
    * This class is used to model the delivery orders that are created using FoodOnWheels.
    * It interacts closely with the other fundamental classes, which are `Customer`, `Dish`, and `Driver`.
    * An `Order` stores objects from the above three other fundamental classes as its attributes with abstractions in place.
  * **UniqueOrderList Class**: Implemented the `UniqueOrderList` class
    * This class is used to store a list of unique orders in FOW, equipped with methods to ensure its integrity and perform operations.

* **Logic**:
  * **AddOrder command and parser**: Command and Parser to add an order into FOW
    * The command and parser are used to add an order into FOW
    * Credit: `AddOrderCommand` and `AddOrderComandParser` were adapted from AB3 `AddPersonCommand` and `AddPersonCommandParser`, but with major modifications as `Order` relies on other existing objects stored in FOW such as `Customer`.
  * **EditOrderStatus command and parser**: Command and Parser to edit the status of an existing order
    * The command and parser are used to edit the status of an order in FOW, such as from `in progress` to `delivered`
  * **EditOrder command and parser**: Command and Parser to edit details of an existing order
    * The command and parser are used to edit the order details, such as the `Customer` as well as the `Dish` ordered.
    * Credit: `EditOrderCommand` and `EditOrderComandParser` were adapted from AB3 `EditPersonCommand` and `EditPersonCommandParser`
  * **FindOrder command and parser**: Command and Parser to find existing orders with specified details
    * The command and parser are used to find existing orders that are ordered by the customer with the provided phone number
    * Credit: `FindOrderCommand` and `FindOrderComandParser` were adapted from AB3 `FindPersonCommand` and `FindPersonCommandParser`
  * **Help command**: Command to open the help window
    * Adapted from the original AB3

* **UI**:
  * **Order UI**: 
    * Implemented a clear and aesthetically pleasing `Order` display in FOW.

  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=xzzz3&breakdown=true)


* **Project management**:
  * Setting up GitHub team organization and managing the Repository
  * Maintaining issue trackers, milestones and deadlines
  * Release Management for prototypes and trial releases


* **Enhancements to existing features**:
  * Evolve the existing features to implement Order features
  * Re-implement the help command


* **Documentation**:
  * User Guide:
    * **Documentation on usage of Commands**: Documented the usage of commands mentioned above with format and examples

  * Developer Guide:
    * **Documentation on Order Implementation**: Documented the implementation of selected Order commands with diagrams
    * **Storage component**: Update changes made to the `Storage` class and diagrams
    * **General organization**: Formatting and organizing through use of tools such as Table of Contents


* **Community**:
  * **Reviewed teammates' PRs**: Reviewed more than 20 PRs from teammates, including PR #70, #63, #97, #100, #101, #102, #103 (More can be found under the Pull Request tab of the repository)
  * **Reported bugs and suggestions** for other teams in the module

