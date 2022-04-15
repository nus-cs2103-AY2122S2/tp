---
layout: page
title: Benjamin's Project Portfolio Page
---

### Project: Harmonia

**Overview**

Harmonia is a command-line-based personal task manager, which helps university students to manage their academic tasks. It is written in Java and has a GUI implemented with JavaFX.

Given below are my contributions to the project.

**Improved GUI:** [#203](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/203)
- Created initial high-fidelity wireframes on Figma
- Tweaked padding, fonts, colours and alignments according to initial wireframes to make the GUI more pleasing to look at
- Enhanced responsiveness of the application, to ensure that the application displays content as intended at all screen sizes

**Evolved Addressbook into Harmonia:** [#100](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/100)
- Add new `Task` class and its corresponding property classes (`Name`, `Description`, `Tags`, `isCompleted`, `Deadline`)
- Modify `Addressbook` into a `TaskList`
- Add `Task` exceptions
- Update `Model` and `UserPrefs`

**Added the Priority feature:** [#153](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/153)
- What it does: a feature that allows the user to attach priorities to tasks
- Justification: This feature enhances the product as it allows a user to tag attach an importance to their task, to ensure that they can quickly prioritise what they want to do
- Highlights: This enhancement affects existing commands such as `Add`, `Edit`, `Find` and `Sort` commands.

**Conducted testing:**
- Tested the model [#147](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/147), [#317](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/317)
- Tested `Task` and its properties (`Completion Status`, `Deadline`, `Description`, `Name`, `Task`, `NameContainsKeywordsPredicateTest`, `UniqueTaskListTest`)
- Tested `TaskList` and `ModelManager`
- Tested `Tag` [#271](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/271)


**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=sbhbenjamin&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

**Project management**
- Managed releases [`v1.3`](https://github.com/AY2122S2-CS2103T-T09-1/tp/releases/tag/v1.3), [`v1.3b.trial`](https://github.com/AY2122S2-CS2103T-T09-1/tp/releases/tag/v1.3b.trial) (2 releases) on GitHub


**Documentation**
- User Guide
    - Added documentation for the About section (Purpose, How to use the guide, Typography, Special Symbols) [#184](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/184)
    - Added documentation to explain the GUI [#207](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/207)
    - Added images of the GUI to better explain features [#278](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/278)
- Developer Guide
    - Added documentation for the `Priority` feature [#158](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/158)
    - Added documentation for the Model in the design component [#283](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/283)
    - Added documentation for Harmonia's target user profile, value proposition, user stories, NFR and glossary [#57](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/57)
    - Added documentation for use cases [#107](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/107)
- Conducted cosmetic tweaks to improve consistency of documentation in terms of formatting and language used [#107](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/107), [#194](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/194), [#325](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/325)


**Team**
- Set up the GitHub team org/repo, initial project, and Gradle
- Managed issue tracker
- Changed the icon of Harmonia [#314](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/314)
- Updated codecov and CI status badge [#89](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/89)
- Integrated different parts of the system to ensure that the product is functional [#127](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/127), [#141](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/141)
- Identified inconsistencies or bugs, and performed hotfixes [#201](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/201), [#159](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/159), [#277](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/277), [#271](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/271), [#266](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/266), [#267](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/267), [#325](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/325)
- Updated Home page, the AboutUs page, and site-wide settings of the documentation [#88](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/88), [#53](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/53), [#195](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/195)
- PRs reviewed (with non-trivial review comments): [#160](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/160), [#129](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/129), [#263](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/263), [#264](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/264), [#280](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/280), [#296](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/296), [#316](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/316), [#327](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/327)
- Took part in informal code reviews and provided suggestions on implementations


**Community** 
- Contributed to forum discussions, shared useful information, and provided support for members in other teams: ([#114](https://github.com/nus-cs2103-AY2122S2/forum/issues/114), [#232](https://github.com/nus-cs2103-AY2122S2/forum/issues/232), [#175](https://github.com/nus-cs2103-AY2122S2/forum/issues/175#issuecomment-1046781542), [#10](https://github.com/nus-cs2103-AY2122S2/forum/issues/10#issuecomment-1017075628))
- Reported bugs for other teams in class ([16 bugs identified in the PE-D](https://github.com/sbhbenjamin/ped/issues))
