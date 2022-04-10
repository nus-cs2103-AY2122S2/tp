---
layout: page
title: Lim Shao Cong's Project Portfolio Page
---

### Project: Trackermon

Trackermon is a desktop application for tracking and managing shows, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Trackermon helps people track and remember what shows they have watched, are currently watching, or plan to watch.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=arcornior&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=arcornior&tabRepo=AY2122S2-CS2103T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Features**: `import` and `export`
  * What it does: `import` and `export` allows the user to quickly import/export Trackermon data to another location.
  * Justification: Without the feature, users would have to manually locate Trackermon data and transfer it themselves. This provides a layer of abstraction over the process, making it more convenient for the users.
  * Highlights: Utilises user's native OS' File Explorer dialog boxes for a seamless experience.
  * Credits: [JonathanHoshi](https://github.com/JonathanHoshi) for assistance in implementing the features.
    <br><br>

* **Enhancements to existing features:**
  * Fixed storage system on MacOS and Linux not saving to the correct location.
    * Details: MacOS and Linux did not store Trackermon files in Trackermon's home folder. Worked together with [JonathanHoshi](https://github.com/JonathanHoshi) to test fixes.
      * Issues: Bug report [\#168](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/168)
  * Morphed AB3 Storage to fit Trackermon.
    * Details: Refactored Storage classes to fit Trackermon.
    * Issues: Modify Storage Components [\#55](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/55)
    * PRs: ([\#64](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/64), [\#67](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/67))
      <br><br>

* **Project management**:
  * Necessary general code enhancements
  * Managed issues relating to `import` and `export`. ([\#156](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/156), [\#199](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/199))
  * Performed smoke testing on Windows, Linux, MacOS to ensure usability of features across different OS.
  * Performed vetting on, and fixed issues with documentation.
    * PRs: ([\#319](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/319), [\#317](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/317))
    * Issues: ([\#279](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/279), [\#273](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/273))
  * Managed Trackermon's v1.3.1, v1.4, v1.4b [releases](https://github.com/AY2122S2-CS2103T-T09-3/tp/releases).
  <br><br>

* **Documentation**:
  * User Guide:
    * Added documentation for features `import` and `export`, and FAQ answers. [\#197](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/197/files)
    * Updated Quick Start, expanding on different OS. ([\#197](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/197/files), [\#271](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/271)) 
    * Added FAQ table. [\#271](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/271)
  * Developer Guide:
    * Added Table of Contents. [\#98](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/98)
    * Added use cases UC09-UC10.
    * Updated documentation and UML diagrams for Storage. [\#89](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/89)
    * Added implementation details of `import` and `export` features. ([\#155](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/155), [\#160](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/160))
    * Added manual testcases for `import` and `export` features.
      <br><br>

* **Community**:
  * PRs reviewed (with non-trivial review comments): ([\#63](https://github.com/AY2122S2-CS2103T-T09-3/tp/issues/63#issuecomment-1062925638), [\#182](https://github.com/AY2122S2-CS2103T-T09-3/tp/pull/182#discussion_r837057450))
  * Reported [bugs and suggestions](https://github.com/arcornior/ped/issues) for other teams in the class.
