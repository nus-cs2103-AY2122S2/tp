---
layout: page
title: KangLi's Project Portfolio Page
---

### Project: TAssist

**TAssist** is a desktop app for Teaching Assistants to manage their students across different modules and keep track of students’ activities/participation in class. It is optimized for use via Command Line Interface (CLI). The user interacts with it using a CLI, and it has a GUI (Graphical User Interface) created with JavaFX. It is written in Java, and has about 11 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?breakdown=true&search=lkldev)

* **New Feature**: Create model and storage classes for Modules
* **New Feature**: Create model and storage classes for TAssist
* **New Feature**: Create Parsers for enrol/disenrol, grade, list commands
* **New Feature**: Create model and add/delete/list commands for Assessment
  * What it does: Allows the application to model an assessment as an object using the assessment name, module index and optionally a simple name. Each assessment will store the attempts of the student in a map.
  * Justification: This feature improves the product significantly as now the TA will be able to keep track of the assessment (or gradable component such as class participation) in each module. With the implementation of the grade command, it will allow the TA to grade these assessments as well.
  * Highlights: To allow TA to easily keep track of the various assessment in each module, so that they can monitor how each student is performing based on the assessments they have submitted.
* **New Feature**: Added Grade command
  * What it does: Allows TA to grade a student for their students for each of the assessment in the module.
  * Justification: This feature improves the product significantly as now the TA will be able to grade the assessments for each student. They can use this command to easily grade the student's participation in class as well.
  * Highlights: To allow the TA to easily keep track of student participation, this command is created to increment the grade if no grade is provided so that the TA do not need to specify a grade for marking participation, they simply have to issue the command without a grade argument.
<div style="page-break-after: always;"></div>
* **Enhancements to existing features**:
  * Update common classes (Model, Logic, ModelManager, LogicManager, TAssist) to be compatible with TAssist. [\#61](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/61), [\#132](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/132), [\#139](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/139)
  * Update Storage class to allow storing of TAssist data. [\#91](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/91)
  * Improve storage in V1.3 to reduce storing of duplicated data. [\#158](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/158)
  * Fix miscellaneous bugs before PE-D. [\#156](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/156), [\#158](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/158), [\#163](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/163), [\#164](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/164)
  * Add test cases for module, JsonAdaptedModule, assessment, JsonAdaptedAssessment, Storage and GradeCommandParser.
  * Fix miscellaneous bugs in test cases breaking CI

* **Documentation**:
  * User Guide:
    * Added documentation for project description and quick start. [\#4](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/4)
    * Fix miscellaneous errors in User Guide (e.g. making parameters optional, wrong parameters, etc)
  * Developer Guide:
    * Added use cases for modules [\#32](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/32)
    * Added Grade command implementation [\#123](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/123)
    * Update AB3 Design to match TAssist design [\#130](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/130)
    * Fix miscellaneous errors in Developer Guide

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#79](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/79), [\#96](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/96), [\#112](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/112), [\#126](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/126), [\#134](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/134), [\#143](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/143), [\#155](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/143), [\#213](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/213), [\#216](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/216)
  * Contributed to forum discussions (examples: [\#21](https://github.com/nus-cs2103-AY2122S2/forum/issues/21), [\#41](https://github.com/nus-cs2103-AY2122S2/forum/issues/41), [\#43](https://github.com/nus-cs2103-AY2122S2/forum/issues/43), [\#87](https://github.com/nus-cs2103-AY2122S2/forum/issues/87), [\#128](https://github.com/nus-cs2103-AY2122S2/forum/issues/128), [\#170](https://github.com/nus-cs2103-AY2122S2/forum/issues/170), [\#237](https://github.com/nus-cs2103-AY2122S2/forum/issues/237))

* **Team Tasks**:
  * Setting up the GitHub team org/repo + codecov
  * General code enhancements: Renaming product, removing traces of AB3 from code and guides, updating common classes for team to move forward.
  * Release Management.
  * Integrate project to ensure all the features work together.
  * Created sample data that will allow team to test their features to see if they are working as intended.
  * Set up automated deployment of documentation when pushing to team `branch-docs` branch. Teammates can then view the updated documentation at [here](https://lkldev.github.io/tp/).
    * Allow teammates to view their edits easily when they push their changes there before merging into master.
