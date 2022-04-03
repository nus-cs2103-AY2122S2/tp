---
layout: page
title: Yeap Yi Sheng James's Project Portfolio Page
---

## Project: TeachWhat!
TeachWhat! is a desktop address book application used for private tutors in managing their class. The user interacts
with it using a CLI, and it has a GUI created with JavaFX.

It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

# Summary of Contributions
## Code contributed
[Click here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=jamesyeap&tabRepo=AY2122S2-CS2103T-W11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) for a summary of code contributed by me!

## Enhancements implemented
1. Implemented a feature allowing users to add temporary lessons, which includes
    - creating a UI representation of a lesson
    - creating the relevant models for a lesson and the abstraction of a list of lessons
    - adding logic to handle the parsing of the command
2. Added logic to check for any conflicts between temporary lessons
    - to prevent a user from adding a lesson that clashes with one that he/she already has.
3. Implemented a feature that refreshes the UI to display a list of lessons that conflict with the one that the user is trying to add
4. Implemented a feature to search for lessons by name and/or subject
5. Implemented a feature to search for students by name and/or tags
6. Added persistence for lessons added, which includes
    - adding logic to write to convert data into their respective `JSON` representations and write them into `JSON` files
    - adding logic to read and parse data from `JSON` files on program start-up

## Contributions to the UG
1. Contributed to the UG for `addlesson` command
   - how `TeachWhat!` would inform the user if the lesson added is conflicting with existing ones and what he/she can do.

## Contributions to the DG
1. Added use-cases for the main user-stories, specifying the actions a user would perform to achieve the intended outcome.
2. Added [class-diagrams](https://github.com/jamesyeap/tp/blob/master/docs/DeveloperGuide.md#model-component) for the `Model` component, along with a brief description of the key classes and associations between them 
3. Added [sequence-diagrams](https://github.com/jamesyeap/tp/blob/master/docs/DeveloperGuide.md#add-temporaryrecurring-lesson) that explain how the `addlesson` command is parsed and executed.

## Contributions to team-based tasks
1. Set up the team's [GitHub repository](https://github.com/orgs/AY2122S2-CS2103T-W11-3/teams/developers)
2. Set up code-coverage tracking on [CodeCov](https://app.codecov.io/gh/AY2122S2-CS2103T-W11-3/tp/)
3. Set up and updated the [Index page](https://ay2122s2-cs2103t-w11-3.github.io/tp/) of the project's website, including
    - changing the header to `TeachWhat!`
    - updating the links to the **Java-CI** and **CodeCov** status-banners
    - updating the link to the project's GitHub repository
5. Added issues and corresponding labels for features to be implemented in [Milestone v1.2](https://github.com/AY2122S2-CS2103T-W11-3/tp/milestone/1)

## Review/mentoring contributions
`to be added soon`

# Contributions beyond the project team
1. Provided an [alternative solution](https://github.com/nus-cs2103-AY2122S2/forum/issues/58#issuecomment-1025671937) to an issue faced by another student in the iP.

