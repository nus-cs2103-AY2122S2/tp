---
layout: page
title: Janald's Project Portfolio Page
---

### Project: AgenSee

AgentSee is a desktop application for property agents to efficiently manage their client information.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link]()
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
        * Changed the language of the User Guide to be more user-friendly and informal.
        * Added Warnings, Cautions and Notes which alerts readers some things they should be careful/aware of.
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
  * Plan the architecture of our project, by drawing visually and screen-sharing.
  * Summarize the work distribution and agenda of each meeting.
  * Set up issues on GitHub](https://github.com/AY2122S2-CS2103T-T11-2/tp/issues)
  * Standardize language across code and documents.
  * Added and refactored java docs.
  * Adjust message displays.
  * Several bug fixes.

* **Review contributions**:
  * Reviewed multiple Pull Requests, and added comments to some of them.
