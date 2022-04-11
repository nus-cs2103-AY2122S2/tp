---
layout: page
title: Jaegar's Project Portfolio Page
---

### Project: FoodOnWheels

FoodOnWheels is a desktop application used to delivery orders for a restaurant by tracking and providing updates of the status of delivery orders. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my **contributions to the project**.

**Code contributed**: [RepoSense Link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jaegarpoon&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

**Model**:
* **Dish Class**: Added `Dish` class
  * `Dish` class provides a blueprint for the creation of a dish in the restaurant to be
  used in FOW
  * Attribute 1: `NameDish` - Stores the name of the dish (adapted from AB3 `Name` class)
  * Attribute 2: `PriceDish` - Stores the price of the dish (created) 
    * Accepts only entries with 2 d.p.
    * Limits entry from '0.00' to '99999.99'
  * Credits: `Dish` class was adapted and worked from AB3's `Person` class, with the same idea
  but different conditions for equality checks
* **UniqueDishList Class**: Added `UniqueDishList` class
  * Overview: `UniqueDishList` class provides a list which stores all unique dishes added into FOW,
  with its uniqueness quantified by `Dish#isSameDish(Dish)`
  * Credits: `UniqueDishList` class was adapted and worked from AB3's `UniquePersonList` class, for the
  purpose of storing dishes instead of persons

**Logic**:
* **Add / Delete / Edit / List Dish Command and Parser**: Command and Parser to add a dish into FOW
  * Overview: Add / Delete / Edit / List dishes in the `UniqueDishList` stored in FOW
  * Credits: Commands and Parsers was adapted from AB3, with edits of the fields required for the `Dish` class as opposed
  to the `Person` class
* **List Order Command and Parser**: Command and Parser to list orders in FOW based on their current status
  * Overview: Updates the filtered order list stored in `ModelManager` based on the input status,
  and displays the list of orders on the UI
  * Predicate for `ListOrder`: Implemented a predicate `OrderStatusContainsKeywordsPredicate` 
  to update `ModelManager#filteredOrderList`
* **Revenue Command**: Command to show the revenue of the restaurant using FOW for the day
  * Overview: Displays the revenue (sum of all sales) as a result, and lists out all the orders
  in the restaurant
  * Predicate for getting orders from current date AND status is delivered: Implemented a predicate
  `OrderDeliveredAndFromDatePredicate`
    
**UI**:
* **Tab Display Feature**: Displays the relevant classes: `Dishes`, `Customers`, `Orders` and `Drivers`
  * Highlights: Relevant class that was in the latest command will be bolded, with the relevant list being
    displayed
  * Implemented the logic to show the relevant `ListPanel`, eg. when a command relating to `Dish` is 
  entered, the `DishListPanel` will be displayed (see PR [\#73](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/73))

**Enhancements implemented**:
* **Improvements to UI**: Rearranged and displayed neatly the results in `DishCard` (see PR [\#103](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/103))
* **Added tests for `Dish` class**: Increased test coverage from 36.00% to 43.95% (see PR [\#170](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/170))
* **Added tests for `revenue` and `listorder`**: Increased test coverage from 51.46% to 55.70% (see PR [\#175](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/175))

**Review / Mentoring contributions**:
* **Pull Request Reviews**: Reviewed PR [\#70](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/70), [\#83](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/83),
  [\#85](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/85), [\#97](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/97), [\#98](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/98),
  [\#100](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/100), [\#106](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/106), [\#111](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/111),
  [\#114](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/114), [\#115](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/115), [\#116](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/116),
  [\#163](https://github.com/AY2122S2-CS2103-F10-2/tp/pull/163)
* **Mentoring Contributions**:
  * Provided comments on how to improve code in the PRs reviewed
  * Started a bug list (not found on GitHub, on Google Docs) to track bugs discovered while exploring
    features implemented by team members and myself
  * Taking initiative to establish UI features to enhance visuals of FOW for easier use, where team members
    adapted the UI features implemented

**Contributions beyond the project team**:
* **Sharing information on forums**:
  * Forum post on [JavaFX SDK](https://github.com/nus-cs2103-AY2122S2/forum/issues/66)
  * Forum post on [Varying builds from CI and local machine](https://github.com/nus-cs2103-AY2122S2/forum/issues/122)
  
**Contributions to the UG**:
* **Documenting usage of Commands**: Documented usage of commands based on the commands implemented and mentioned
above
* **Documenting usage of Tabs**: Documented how a user can switch between tabs in FOW
* **Introduction to FOW**: Added on to the introduction to the UG, from 'The main idea... ' onwards
* **Java `11` installation guide**: Added instructions for Java `11` installation
* **Instructions to start FOW**: Wrote instructions on usage of `java -jar foodonwheels.jar`

**Contributions to the DG**:
* **Model Component**: Updated relevant changes and diagrams made to the `Model` component after adaptation from AB3
* **Implementations**: Updated implementations based on implementations and enhancements mentioned above
* **UI Component**: Updated relevant changes and diagrams made to the `Ui` component after adaptation from AB3
