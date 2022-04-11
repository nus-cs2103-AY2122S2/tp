---
layout: page
title: Shi Hong's Project Portfolio Page
---

### Project: AgentSee

Given below are my contributions to the project.

* **Code contributed**: My code contribution can be found here [[RepoSense](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=chuashihong&breakdown=true)]


* **Enhancement**:
    * Added `edit-s` command feature
        * What it does: This command allows the user to edit the details of a seller
        * Justifications: The basic CRUD command for the seller information.
        * Credits: The feature was implemented by referring to [AddressBook Level 3
          (AB3)](https://github.com/se-edu/addressbook-level3) edit person command. Some part of the codes were adapted and modified.
    * Added `delete-s` command features
        * What it does: Delete a seller by the given `index` from the user
        * Justifications: The basic CRUD command for the seller information
        * Credits: The feature was implemented by referring to [AddressBook Level 3
          (AB3)](https://github.com/se-edu/addressbook-level3) delete person command. Some part of the codes were adapted and modified.

    * Added `property` features into the `buyer` and `seller`
        * What it does: This feature is to store the key information from a buyer and a seller, it is to show the
          information of the property that a buyer/seller wish to buy/sell.
        * Justifications: This is the essential information for both a buyer and a seller, and it is made associated with buyer/seller.
          The `match` command also uses the property feature to match a buyer and with many sellers.
        * Highlights: This feature is implemented using OOP concept.
    * Improve the storage system of the project
        * Created a lot of classes like `JSONAdaptedBuyer/Seller`, `JSONAdaptedProperty` and related properties classes
          to make the storage system be able to store essential information about buyers and sellers, `propertyToBuy`and `propertyToSell`
        * Updated the `model` class and the `logicManager` class to match the current implementation
        * Updated the JSON format that the app saves, and the ability to read the files.
        * Remove unused storage related files like `Client` related classes.
    * Update the UI of the project
        * Let buyers and sellers be able to show its eseestial information on the UI
        * Make initial changes to the UI
        * Credits: The UI was made complete with Wang Jun Hong and Bian Yuqi
    * Code quality control of the project
        * Delete all unused files and commented out codes
        * Make sure all naming conventions of the files are appropriate by refactoring the classes' name and methods' name
        * Added test cases for `EditSeller` and `EditBuyer` classes
        * Added test cases for `Storage` related classes
        * Refactored test cases to match the current version

* **Project management**:
    * Fixed a bug on the project [\#84](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/84)
    * Give ideas to teammates while planning on the next iteration
    * Actively discuss with teammates in order to maintain the consistency in our project
    * Increase code coverage by adding more test cases.
    * Add issues in the Github to keep track of the progress in out project.

* **Documentation**:
    * User Guide:
        * Update documentation for the command `add`
        * Update documentation for the file system, explain about the data structure of our JSON format in details
        * Made some changes so that it is updated to our current version.
    * Developer Guide:
        * Add a documentation about `EditBuyer` and `EditSeller` command
        * Add a detailed UML diagram about the sequence diagram of `EditBuyerCommand` and `EditSellerCommand` class
        * Add detailed explanation when executing `EditBuyer` and `EditSeller` command
        * Add detailed explanation on Saving data sections, explaining how to handle the corrupt data files and the correct format of data format.
        * Update the glossary section so that it match our current version

* **Community**:
    * Significant Pull Request made:
        * ['editseller' command ](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/115)
        * [JSON format: UI rendering Property](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/133)
        * [Correct format on saving storage and reading JSON file](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/153)
        * [Update UI and refactor some class name](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/154)
        * [Branch remove client related commands](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/278)
        * [Removed all unused files and commented out codes](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/323/files)
        * [Bug fix](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/84)
        * [Add House Class, HouseType <Enum>, Location, and PriceRange](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/87)
        * And many more [here](https://github.com/AY2122S2-CS2103T-T11-2/tp/pulls?q=is%3Apr+author%3A%40me)
    * Pull Request reviewed: All PR reviewed can be found [here](https://github.com/AY2122S2-CS2103T-T11-2/tp/issues?q=reviewed-by%3Achuashihong+)
        * One of them with non-trivial review comments: [\#72](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/72), 
      where I give some commands on the code and potential typo and give approval
