---
layout: page
title: Ng Wen Hao Dennis's Project Portfolio Page
---

### Project: ManageEZPZ

ManageEZPZ is a desktop application that allows managers or supervisors to manage employees and assign tasks to them. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

## Enhancement implemented

### `find` command:
* Now change to `findEmployee` command to reflect and differentiate this find command to find all employees.
* Create a new predicate `PersonMultiplePredicate` to filter out employees based on multiple properties entered by user.
* Unit test for the new `findEmployee` command include:
  * `findEmployeeCommandParser` to check on different user inputs.
  * `findEmployeeCommand` to show different outcome on the `finteredPersonList` for different user inputs.
  * `PersonMultiplePredicate` to ensure that only employees that satisfy the options given to the users return true.

### `list` command
* Only change the command to `listEmployees` to reflect on listing down all the employees.
* Functionalities remain the same from AB3.

### `findTask` command

* To allow users to find tasks which was a new model added into our project.
* Allowed users to search tasks using multiple properties of a task.
* Created a new predicate `TaskMultiplePredicate` to filter out tasks based on multiple properties entered by users
* Added unit testing for:
  * `findTaskCommandParser` to check on different user inputs.
  * `findTaskCommand` to show different outcome on the `finteredTaskList` for different user inputs.
  * `TaskMultiplePredicate` to ensure that only tasks that satisfy the options given to the users return true.

### `listTask` command

* List down all the tasks in the filteredList.
* Unit test for `listTask` to use the following test cases:
  * When the current task list is already filtered.
  * When the user task list already shows all task in the task list.

## Code contributed

Repo sense link: [Link to Ng Wen Hao Dennis's repo sense](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=denniszedead&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

## Contribution to the user guide



## Tools

## PR Reviewed
