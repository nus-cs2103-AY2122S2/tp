---
layout: page
title: Talkintomato's Project Portfolio Page
---

### Project: Expense Expert

Expense Expert is a desktop expense tracking application used for tracking one's personal expenses. The user interacts
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about ~10 kLoC.

Given below are my contributions to the project.

- **New Feature**: Added the ability to set expense categories and tag them to expenses.

  - What it does: allows the user to defined expense categories. viewing expense categories can be done using the listCat command and defined using the addCat command.
  - Justification: This feature improves the product significantly because a user can categorise their expenses.
  - Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation challenging as it required building upon the existing model and making design decisions with future features in mind.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=talkintomato&breakdown=true)

- **Enhancements to existing features**:
  - changed the implementation of add expense to accommodate expense categories being optional. 

- **Project management**:

  - Set up Tp Organization and handled getting the project off the ground.
  - Managed releases `v0.1` - `v1.2` (3 releases) on GitHub.
  - Ensured that deliverable requirements are met 

- **Documentation**:

  - User Guide ([#97](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/97)):
    - adding an expense Category
    - Deleting an expense
    - Filtering expense(s)
  - Developer Guide ([#157](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/157)):
    - Updated class diagrams
      - Model
      - UI
    - Added Sequence diagrams ([#194](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/194))
      - addCat 
      - listCat
    - Use cases ([#177](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/177)):
      - UC04 Expense Categories
    - User stories ([#177](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/177))
      - Contributed significant number of user stories
  
- **Community**:

  - PRs reviewed (with non-trivial review comments): [\#21](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/21), [\#23](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/23), [\#67](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/67), [\#79](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/79), [\#89](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/89), [\#95](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/95), [\#96](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/96), [\#103](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/103), [\#111](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/111), [\#140](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/140), [\#161](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/161)
  - Reported 11 bugs and suggestions for other teams during PE dry run

- **Tools**:
  - Integrated JavaCI and Codecov to team repo
