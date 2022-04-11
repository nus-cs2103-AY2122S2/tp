---
layout: page
title: Goh Zi Xuan's Project Portfolio Page
---

### Project: FoodOnWheels

FoodOnWheels is a desktop application used to delivery orders for a restaurant by tracking and providing updates of 
the status of delivery orders. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is 
written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * Model & Logic
    * Customer oriented commands, `addcustomer` , `deletecustomer` and `editcustomer`
    * `addcustomer` allows restaurants to store information of customers and display it on the application interface
    * `deletecustomer` removes the customer from the database via indexing
    * `editcustomer` provides the option to amend  information of existing customers, such as name, phone number 
    and/or address
    * Implemented checks to detect repeated inputs, such as phone number
    * Added tests for customers (adapted from `Person` test cases)
  * UI:
    * Implemented a clear UI for `Customer`

  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=zxgoh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)


* **Project management**:
  * Made updates to codebase through pull requests and branching features
  * Created pull requests to ensure team members could review and comment on newly published commits
  * Provided relevant feedback and reviewed changes made by team members through pull requests
  * Utilised github's branch features to
  distinguish separate commits from one another
  * Ensured proper merging between existing branches such as the upstream branch
  
* **Enhancements to existing features**:
  * Implemented Java Regex checks on phone numbers of `customer` and `driver` to ensure specific formats are being followed
  * Provide encapsulation of certain variables and attributes like **name**, **phone** and **address** of `customer`
  * Streamlined database checks that prevent duplicate phone numbers from being added between both  customers and 
    drivers
    * In the event when `addcustomer` command is entered with a phone number of an existing `driver`, it will be 
      detected by checks via methods in `parser`

* **Documentation**:
  * User Guide: Included `customer` related features, and provided relevant examples

  * Developer Guide:
    * Documentation on `customer` model
    * Documentation on `customer` features
    * UML diagrams for `customer` features, such as `addcustomer` and `deletecustomer`
      * This allows a clear illustration of how each `customer` command is being created and parsed by the various 
        components 
    * Created a mockup of the user interface using Figma



* **Community**:
  * Team project
    * Shared and exchanged feedbacks on code or documentation with team members
    * Effectively engaged in weekly brainstorming sessions to discuss project ideas and improvements
    * Set deadlines and promptly allocated project tasks to one another
* **Tools**:
  * IntelliJ IDE 
    * Java 11
    * Integration with Gradle
  * Github 
  * SourceTree
  * PlantUML

