---
layout: page
title: Samyukta Sounderraman's Project Portfolio Page
---

### Project: ClientConnect

ClientConnect is a desktop client address book application for insurance agents to manage and keep track of their clientele seamlessly and efficiently. Custom tailored to suit the needs of insurance agents, ClientConnect surpasses other similar applications by offering features such as grouping of clients by packages, keyboard shortcuts, a gorgeous GUI, and more.

Given below are my contributions to the project.

* **New Feature**: Added the ability for a tag to have priority levels
  * What it does: allows the user to assign a priority to a tag. Tags with different priorities look completely different in the GUI as well (red for highest priority, blue for least).
  * Justification: users may use the tags feature to store details about their clients, and potentially upcoming tasks (e.g. owes money soon, or hasn't signed contract yet). Not all of these are at the same level. Assigning a tag a priority level serves as a visual reminder of the importance/urgency of the various details specified in the tags.
  * Highlights: This enhancement paved the way for the `prioList` command to be added later. It required an in-depth analysis of various design implementations. The implementation was challenging as it required changes to existing commands, parsers and storage methods.


* **New Feature**: Added the `addTag`, `editTag` and `deleteTag` commands
  * What it does: allows the user to add a new tag, edit a current tag or delete an existing tag in a convenient manner
  * Justification: users may want to have many tags to remember miscellaneous details about their clients. Previously, the only way tags could be changed is with the `edit` command, that requires the entire list of tags to be typed out again. The three new commands give users a much more convenient way to make small changes if they want.
  * Highlights: This enhancement required the addition of new parsers, in addition to new commands. It also required a change in the implementation on how to store tags, as there was an additional requirement for them to be ordered. This in turn required several changes to existing commands, parsers and test cases.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=montypython28&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Set up team organisation and repository
  * Took responsibility for opening, maintaining and closing milestones (`v1.2` to `v1.4`)
  * Coordinated generation and management of the various releases (`v1.2` to `v1.4`)

* **Enhancements to existing features**:
  * Added InsurancePackage field to Person class, and updated commands accordingly (Pull Request [#20](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/20))
  * Made UI change to the colours of tags, depending on their priority levels. (Pull Request [#45](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/45))

* **Documentation**:
  * User Guide:
    * Updated information about tags and priorities
    * Updated information about `addTag`, `editTag` and `deleteTag` commands
  * Developer Guide:
    * Updated information about current team members and their responsibilities.
    * Updated ModelClassDiagram and the relevant section to include changes we made to the Model.

* **Community**:
  * PRs reviewed with comments: [#47](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/47), [#54](https://github.com/AY2122S2-CS2103-W17-3/tp/pull/54)
  * Contributed to forum discussions ([1](https://github.com/nus-cs2103-AY2122S2/forum/issues/110#issuecomment-1030581324), [2](https://github.com/nus-cs2103-AY2122S2/forum/issues/220))
  * Reported bugs for other teams (e.g. [1](https://github.com/MontyPython28/ped/issues/5), [2](https://github.com/MontyPython28/ped/issues/3), [3](https://github.com/MontyPython28/ped/issues/1))

