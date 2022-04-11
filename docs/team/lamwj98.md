---
layout: page
title: Andy's Project Portfolio Page

---

### Project: Expense Expert

Expense Expert is a desktop expense tracking application used for tracking one's personal expenses. The user interacts 
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about ~10 kLoC.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=lamwj98&breakdown=true)

- **New Feature**:
  - **Filtering expense(s) by date or month and/or category** ([#88](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/88), [#111](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/111)):

    - What it does: Allows users to filter expense(s) based on date (can be specific date or by month), category, or a combination of both.
    - Justification: Users may want to know where they had spent money on specific date, specific month or even specific category.
    - Highlights: The issue faced with implementing this issue is that when using predicate for filtered list, only one predicate can be taken, however, with the option of allowing user to use 2 predicate, date/month and category, there is a need to filter by 2 predicate. A way to overcome this issue is to create a new predicate that combines both the date/month predicate and the category predicate, and pass the new predicate as a single predicate to filtered list.

- **Enhancements to existing features**:

  - Change `ExpenseDate` class from keeping data as `String` to `LocalDate` (for future usage of dates for comparison) ([#88](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/88)).
  - Added unit test code for ExpenseDate and its relevant classes and fixed relevant bugs ([#79](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/79)).

- **Documentation**:

  - User Guide ([#39](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/39), [#88](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/88), [#111](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/111)):
    - Editing an expense
    - Deleting an expense
    - Filtering expense(s)
    - Fix bugs ([#153](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/153))
    - Command summary ([#182](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/182))
  - Developer Guide ([#158](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/158)):
    - Updated class diagrams 
      - Model
      - UI
    - Implementation details (with sequence diagram)
      - `filter`
    - Use cases ([#39](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/39)):
      - UC01 Add an expense
      - UC02 Delete an expense
      - UC03 Edit an expense
      - UC04 Find expense(s)
      - UC08 Filter expense(s) 
      - Fix bugs ([#153](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/153))
    - User stories ([#39](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/39))
    
- **Contribution to team-based tasks:**
  - Refactor code base to fit project features including adapting naming of variables, adding new classes and its relevant test cases as well as adapting old test cases and datas ([#53](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/53), [#64](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/64), [#65](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/65)).
  - Created the JAR release for [v1.3.trial](https://github.com/AY2122S2-CS2103T-W09-3/tp/releases/tag/v1.3.trial) and [v1.4](https://github.com/AY2122S2-CS2103T-W09-3/tp/releases/tag/v1.4).
  - Coordinated team efforts and get updates from team members regarding progress.

- **Community**:
  - PRs reviewed (with non-trivial review comments): [#68](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/68), [#69](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/69), [#84](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/84), [#109](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/109), [#154](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/154), [#168](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/168), [#169](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/169), [#179](https://github.com/AY2122S2-CS2103T-W09-3/tp/pull/179).
  - Reported a total of 13 bugs and suggestions for other teams during PE-D (see [here](https://github.com/lamwj98/ped/issues)).
  
