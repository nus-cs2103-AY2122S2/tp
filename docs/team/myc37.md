---

layout: page
title: N Vijay Narayanan's Project Portfolio Page
---

### Project: onlyFAs

onlyFAs is a desktop client-management application used by high achieving financial advisors looking to maintain and develop good relationships with current and potential clients. THe user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=myc37&breakdown=true)

- **Enhancements implemented**:
  *My main contribution to onlyFAs was introducing all policy-related features. Listed Below are the various classes
  I have implemented:*

  #### Models
  - `Policy` (Pull request [#55](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/55))
  - `Premium` (Pull request [#55](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/55))
  - `UniquePolicyList` (Pull request [#129](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/129))
  - Contributed to `Client` to include UniquePolicyList attribute and all its corresponding functions (Pull request [#129](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/129))

  #### Commands
  - `AddPolicyCommand` (Pull request [#55](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/55))
  - `EditPolicyCommand` (Pull requests [#104](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/104), [#169](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/129))
  - `DeletePolicyCommand` (Pull requests [#103](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/103), [#166](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/166))
  - `CloseMeetingCommand` (Pull request [#161](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/161))
  - Contributed to `FilterCommand` to include predicates for premium and company (Pull request [#159](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/159))
  - Contributed to `SortCommand` to include comparators for premium and number of policies (Pull requests [#139](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/139), [#146](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/146))

  #### Parsers
  - `AddPolicyCommandParser` (Pull request [#55](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/55))
  - `EditPolicyCommandParser` (Pull requests [#104](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/104), [#169](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/129))
  - `DeletePolicyCommandParser` (Pull request [#103](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/103))

  #### Exceptions
  - `DuplicatePolicyException` (Pull request [#55](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/55))
  - `EmptyPolicyListException` (Pull request [#228](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/228))
  - `InvalidPolicyIndexException` (Pull request [#103](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/103))
  - `PolicyNotEditedException` (Pull request [#228](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/228))
  - `PolicyNotFoundException` (Pull request [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253))

  #### Storage
  - `JsonAdaptedPolicy` (Pull request [#70](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/70))
  - Contributed to `JsonAdaptedClient` to include UniquePolicyList attribute and all its corresponding function
    (Pull requests [#70](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/70), [#129](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/129))

  #### Util
  - `PolicyBuilder` (Pull request [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253))
  - `TypicalPolicies` (Pull request [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253))
  
  #### Tests
  - `PolicyTest` (Pull requests [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253), [#274](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/274))
  - `PremiumTest` (Pull requests [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253), [#274](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/274))
  - `UniquePolicyListTest` (Pull requests [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253), [#271](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/271))
  - `AddPolicyCommandTest` (Pull requests [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253), [#271](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/271))
  - `EditPolicyCommandTest` (Pull requests [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253), [#271](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/271))
  - `DeletePolicyCommandTest` (Pull requests [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253), [#271](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/271))
  - `AddPolicyCommandParserTest` (Pull request [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253))
  - `EditPolicyCommandParserTest` (Pull request [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253))
  - `DeletePolicyCommandParserTest` (Pull request [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253))
  - `JsonAdaptedPolicyTest` (Pull request [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253))
  - `CloseMeeting` (Pull request [#267](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/267))
  - Contributed to `JsonAdaptedClient` (Pull requests [#253](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/253), [#274](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/274))
  - Contributed to `AddressBookParserTest` (Pull request [#274](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/274))
  

- **Contributions to the UG**:

  - Created and added examples for `Add Policy` (Pull request [#73](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/73))
  - Created and added examples for `Edit Policy` (Pull request [#73](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/73))
  - Created and added examples for `Delete Policy` (Pull request [#73](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/73))
  - Contributed to `Sort Clients` (Pull requests [#169](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/169), [#234](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/234))
  - Contributed to `Filter Clients` (Pull request [#169](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/169))
  

- **Contributions to the DG**:

  - Created PlantUML Diagrams for `Model` and `Storage` (Pull request [#251](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/251))
  - Created and added examples for `Policy` under the `Implementation` section (Pull requests [#118](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/118), [#248](https://github.com/AY2122S2-CS2103T-W13-4/tp/pull/248))


- **Review/mentoring contributions**:

  - Reviewed pull requests made by `zechajw` as part of our forking workflow

