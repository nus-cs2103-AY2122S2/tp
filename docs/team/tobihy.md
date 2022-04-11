---
layout: page
title: Hao Yu's Project Portfolio Page
---

### Project: WoofAreYou

WoofAreYou is a desktop app for pet daycare owners to handle the administrative information of their pets. If you can
type fast, WoofAreYou can get your contact management tasks done faster than traditional GUI apps. The user interacts
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added attendance functionality [#80](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/80)
    * What it does:
        * Allows the user to mark pets as `absent` or `present` on specified dates. Users are able to mark pets'
          attendance for dates in the past, as well as dates in the future (in advance).
        * Dates within the past seven
          days that the pet were marked present or absent are also reflected in the GUI as green-coloured or
          red-coloured date tags.
    * Justification:
        * This feature improves the product significantly by tailoring the application to meet the attendance taking
          needs of the users, meeting our product's aim of simplifying pet daycare administration.
    * Highlights:
        * The implementation of the feature was non-trivial due to the challenges associated with storing and
          manipulating attendance data for each pet in an efficient and effective manner.
        * Attendance data is stored in the existing `.json` data file of WoofAreYou. This required a good understanding
          of JSON serialisation and deserialisation.
        * Being the first major functionality involving non-trivial changes to the front-end and back-end of WoofAreYou,
          it provided a back-end for teammates to build upon with related functionalities.
          (see [#91](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/91), [#103](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/103))
* **New Feature**: Include optional transport arrangement timings in dates marked
  present [#80](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/80), [#97](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/97)
    * What it does:
        * Allows the user to include pick-up and drop-off timings for pets marked as present using the
          existing `present` command.
        * Transport arrangements for today and tomorrow (two days, inclusive) are also reflected in the GUI as
          colour-coded tags.
    * Justification:
        * This feature builds upon the attendance functionality by providing users with a handy way to keep track of the
          transport arrangements of their pets, if such arrangements exist.
        * This is also in line with our product's aim of simplifying pet daycare administration.
    * Highlights:
        * Designed a performant GUI to show user, at a glance, pet transport arrangements for the next two days.
        * Provided a back-end for `sort` command (see [#99](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/99))
* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tobihy&breakdown=true)
* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub
    * Assisted members of the team in debugging code
* **Enhancements to existing features**:
    * Improved `find` command to work with partial word
      matches [#65](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/65)
    * Revamped GUI of WoofAreYou to resemble initial GUI
      mockup [#76](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/76)
* **Documentation**:
    * User Guide:
        * Added user documentation for attendance
          functionality [#80](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/80)
        * Edited UG to make it more reader-focussed and
          reader-friendly [#108](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/108)
    * Developer Guide:
        * Added developer documentation for attendance
          functionality [80](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/80)
* **Community**:
    * PRs reviewed (with non-trivial review comments): [#74](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/74), [#99](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/99), [#103](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/103), [#158](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/158)
    * Reported bugs and suggestions for peers during PE-Dry Run
        * [Invalid email is accepted into AIA](https://github.com/tobihy/ped/issues/1)
        * [Contacts with same name are not allowed](https://github.com/tobihy/ped/issues/8)
        * [Tag text not able to contain whitespaces](https://github.com/tobihy/ped/issues/9)
