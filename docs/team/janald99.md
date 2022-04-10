---
layout: page
title: Janald's Project Portfolio Page
---

### Project: AgentSee

AgentSee is a desktop application for property agents to efficiently manage their client information.

Given below are my contributions to the project. I mainly contributed to the addition of Property classes and what they compose of, and discovered the match feature.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=janald99&brekdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=janald99&tabRepo=AY2122S2-CS2103T-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
  * `PropertyToBuy` class
  * `PropertyToSell` class
  * `PriceRange` class
  * `Location` class
  * `Edit buyer` & `Edit Seller class` 
  * `MatchCommand`, `MatchHouseTypeCommand`, `MatchLocationCommand`, `MatchPriceRangeCommand` & its associated Parser classes
  * `AllFieldsMatchBuyerPredicate` class
  * `HouseTypeMatchBuyerPredicate` class
  * `LocationMatchBuyerPredicate` class
  * `PriceRangeMatchBuyerPredicate` class
  * The associated test cases for the classes
  * Edited files to conform to checkstyle & add JavaDocs

* **New Feature**: 

Added the new match features:
  * `match` command
  * `match-h` command
  * `match-l` command
  * `match-pr` command
  * What it does: allows matching of one buyer to potential sellers in the seller list, based on the following criteria:
    * `match-pr`: A price that match the price range of a buyer willing to buy a property, and a seller willing to sell a property.
    * `match-h`: The `House Type` of the property
    * `match-l`: `Location` of the property
    * `match`: All of the fields above are matched
    
  * Justification: This feature allows buyers to match with sellers to find a potential property they want to buy, based on their demand such as houseType and willing priceRange. It is also convenient for them to filter such matching based on their preferred criteria.


* **Documentation**:
    * User Guide:
        * Contributed to the skeleton of the user guide by gaining inspiration from the AB3 User Guide.
        * Quick Start of the User Guide, including adding the UI prototype screenshot.
        * Removed the unnecessary documentation from the AB3 code base.
        * Adapted the language of the User Guide to be more user-friendly and informal.
        * Added Warnings, Cautions and Notes which alerts readers some things they should be careful/aware of.
        * Added the entire match section of the User Guide.
        * Updated FAQ questions and answers.
    * Developer Guide:
        * Added the Glossary section to clarify ambiguous terms.
        * Added the Non-functional requirements of our project.
        * Removed the unnecessary documentation from the AB3 code base.
        * Many minor adjustments to sentence structures, refactoring keywords, and replacing images.
        * Added UML diagrams for the Model section such as for:
          * [Model Class](https://github.com/AY2122S2-CS2103T-T11-2/tp/blob/master/docs/images/ModelClassDiagram.png)
          * [Property Class](https://github.com/AY2122S2-CS2103T-T11-2/tp/blob/master/docs/images/PropertyClassDiagram.png)
          * [Client Class](https://github.com/AY2122S2-CS2103T-T11-2/tp/blob/master/docs/images/ClientClassDiagram.png)
          
* **Contributions to team tasks**:
  * Planned the architecture of our project, by drawing visually and screen-sharing.
  * Summarized the work distribution and agenda of each meeting.
  * Set up new issues like [Issue #52](https://github.com/AY2122S2-CS2103T-T11-2/tp/issues/52) and [Issue #119](https://github.com/AY2122S2-CS2103T-T11-2/tp/issues/119) on GitHub.
  * Standardized language across code and documents.
  * Added and refactored java docs.
  * Adjusted message displays.
  * Added test cases to improve our test coverage, and discovered bugs along the way.
  * Managed issues and pull requests on Github by opening new ones, and closing and reviewing existing ones.
  * Fixed several bugs, for example:
    * [Issue #279](https://github.com/AY2122S2-CS2103T-T11-2/tp/issues/279) where sellers who had no property could be matched with buyers, leading to undesirable behaviors.
    * [Issue #213](https://github.com/AY2122S2-CS2103T-T11-2/tp/issues/213) where index out of range had no exception thrown.

* **Review contributions**:
  * Reviewed many Pull Requests, and added comments to some of them.
  * E.g: [PR #201](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/201) where I pointed out several typos and suggested ways to improve the user-friendliness and language of the user guide.
