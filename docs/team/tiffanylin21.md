---
layout: page
title: Tiffany's Project Portfolio Page
---

### Project: WoofAreYou

WoofAreYou is a desktop app for pet daycare owners to handle the administrative information of their pets. If you can type fast, WoofAreYou can get your contact management tasks done faster than traditional GUI apps. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project:

* **Contributions to UG**:
  1. Updated UG to include `time` and `delete` commands
  2. Updated UG for `charge` command
* **New Feature**: Charge
    1. **Charge Command** [#103](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/103) [#112](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/112)
    * What it does:
        * Allows the user to tabulate monthly charge of pet.
        * Charge requires user to specify a month and cost per day.
    * Justification:
        * This feature enables the user to calculate cost to charge a pet for a specified month. This cost is calculated based on the pet's attendance during the specified month.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tiffanylin21&breakdown=true)
* **Project management**:
    1. Reviewed pull requests by teammates
    2. Helped in opening and assigning issues
* **Enhancements to existing features**:
  1. Refactored `Person` in AB3 with its attributes to `Pet` with its initial attributes `Name`, `OwnerName`, `Phone`, `Address` and `Tag`
  2. Fixed inconsistent error messages given by AB3 when user inputs an invalid index. Error message given when index is out-of-bounds and when index is not a positive non-zero integer are different.
  3. Fixed duplicate whitespace error in AB3 naming. AB3 treat names containing duplicate whitespaces as unique. Fixed to automatically trim duplicate whitespaces in names.
* **Documentation**:
  1. Updated development guide images to conform to team project
* **Community**:
    * PRs reviewed (with non-trivial review comments):[#80](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/80#discussion_r832306218)
    * Reported bugs and suggestions for peers during PE-Dry Run
      * [Duplicate whitespaces allowed in names and considered unique by NUSocials](https://github.com/Tiffanylin21/ped/issues/1)
      * [Addresses can't be seen on GUI when too long](https://github.com/Tiffanylin21/ped/issues/3)



