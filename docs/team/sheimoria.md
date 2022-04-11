---
layout: page
title: Maleriado Shem Limos' Project Portfolio Page
---

## Project: onlyFAs
[onlyFAs](https://github.com/AY2122S2-CS2103T-W13-4/tp) is a client relations management application for new financial advisors who are looking to maintain and develop relationships with current and potential clients. Written in Java, onlyFAs allows users to execute commands through a command line interface, and has a graphical user interface built with JavaFX.

Given below are my contributions to the project.

### Code contributed
[RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=sheimoria&breakdown=true)

### Enhancements implemented
My main contribution to onlyFAs was the implementation of the tracking of clients' **birthdays** and **last contacted**. Listed below are the various classes I have implemented or enhanced:

#### Models
- [x] Add `Birthday` class to store clients' birthdays in `dd-MM-yyyy` format [#81](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/81)
- [x] Add `birthday` field of type `Birthday` to `Client` [#81](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/81)
- [x] Add `LastContacted` class to store clients' last contacted in `dd-MM-yyyy HH:mm` format [#61](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/61)
- [x] Add `lastContacted` field of type `LastContacted` to `Client [#61](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/61)

#### Commands
- [x] Add `ContactedCommand` to update clients' `lastContacted` [#120](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/120)
- [x] Add `Birthday` and `LastContacted` as optional parameters in `AddCommand` [#149](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/149)
- [x] Update `Email` and `Address` to be optional parameters in `AddCommand` (only `Name` and `Phone` required) [#145](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/145)
- [x] Add `Birthday` and `LastContacted` as optional parameters in `EditCommand` [#81](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/81) [#61](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/61)
- [x] Add support for sorting clients by `lastContacted` in `SortCommand` [#150](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/150)

#### Parsers
- [x] Add `ContactedCommandParser` to parse `contacted` commands [#120](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/120)
- [x] Add support for `contacted` commands in `AddressBookParser` [#120](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/120)
- [x] Add support for `Birthday` and `LastContacted` parameters in `AddCommandParser` [#149](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/149)
- [x] Add support for `Birthday` and `LastContacted` parameters in `EditCommandParser` [#81](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/81) [#61](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/61)

#### Storage
- [x] Update`JsonAdaptedClient` to save and load each `Client`'s `birthday` and `lastContacted` to and from storage [#122](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/122) [#143](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/143)

#### Tests
- [x] Add `BirthdayTest` [#263](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/263)
- [x] Add `LastContactedTest` [#263](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/263)
- [x] Add `ContactedCommand` tests in `AddressBookParserTest` [#263](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/263)
- [x] Add `Birthday` and `LastContacted` tests in `AddCommandParserTest` [#263](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/263)
- [x] Add `ContactedCommandParserTest` [#283](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/283)
- [x] Add `Birthday` and `LastContacted` tests in `EditClientDescriptor` [#263](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/263)
- [x] Add `Birthday` and `LastContacted` tests in `EditCommandParserTest` [#263](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/263)
- [x] Add `Birthday` and `LastContacted` tests in `JsonAdaptedClientTest` [#263](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/263)
- [x] Add `Birthday` and `LastContacted` tests in `ParserUtil` [#263](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/263)

#### Miscellaneous
- [x] Refactor existing `Person` class to `Client` [#101](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/101)
- [x] Fix commands accepting future or invalid dates for `Birthday` e.g. `21-03-2032`, `31-04-2021` and `29-02-2022` [#262](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/262)
- [x] Fix commands accepting future or invalid dates for `LastContacted` e.g. `21-03-2032`, `31-04-2021` and `29-02-2022` [#243](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/243)
- [x] Update site-wide settings [#49](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/49/files)
- [x] Update existing CI banner to link to project [#47](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/47/files)

### Contributions to the UG
- [x] Add table of contents with hyperlinks to respective sections [#46](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/46)
- [x] Add `contacted` command format, sample input and expected output [#127](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/127)
- [x] Update `addClient` command format, sample input and expected output to include `Birthday` and `LastContacted` [#145](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/145)
- [x] Update `editClient` command format, sample input and expected output to include `Birthday` and `LastContacted` [#237](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/237)

### Contributions to the DG
- [x] Add table of contents with hyperlinks to respective sections [#127](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/127)
- [x] Add `LastContacted` and `ContactedCommand` feature implementation [#127](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/127)
- [x] Update `ModelClassDiagram` and `BetterModelClassDiagram` to show `Birthday` and `LastContacted` associations with `Client` [#279](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/279)

### Review/mentoring contributions

_**Context:** Our team conceptualised a round-robin system to review code in which each member was responsible for reviewing the code of a certain member._

- Review [Vijay](https://github.com/AY2122S2-CS2103T-W13-4/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Amyc37) 's pull requests, giving comments and suggestions on implementation and documentation
- Review other members' code if we are available or if their pull request is related to our primary responsibilities

### Contributions beyond the project team
- Give feedback on the UGs and DGs of 2 other teams in our CS2101 class
- Report bugs for another team's [User Guide](https://ay2122s2-cs2103-f09-4.github.io/tp/UserGuide.html) and [JAR file](https://github.com/AY2122S2-CS2103-F09-4/tp/releases) during the Practical Exam Dry-Run
