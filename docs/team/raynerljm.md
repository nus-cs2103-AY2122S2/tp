---
layout: page
title: Loh Jia Ming, Rayner's Project Portfolio Page
---

# Project: onlyFAs

onlyFAs is a desktop client-management application used by beginning to high-achieving financial advisors looking to maintain and develop good relationships with current and potential clients. THe user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

## 1. Code contributed:
[RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=raynerljm&breakdown=true)

## 2. Enhancements implemented:
My primary contribution to the onlyFAs application is the **Meetings** functionality.

### Description of feature:
The `Meeting` feature allows users to add meetings with clients that includes the meeting's start datetime, end datetime, and an optional label for the meeting. They are able to edit the meeting to modify both the date times and the labels. Users are also able to delete the meeting from the application. Additionally, if the `closeMeeting` command is used, it will update the client's last contacted date time to the meeting's end date time.

Whenever a client is edited, this feature also goes through the list of all meetings and update the client that the meeting is referencing to as well.

### The related user stories are:
- As a first time user, I can schedule meetings with clients so that I can manage my schedule.
- As a first time user, I can get warnings if there are any conflicts in my schedule so that I can meet all my clients.
- As a first time user, view my meeting schedule.

### The following are the new classes I introduced into the application:
#### Models: ###
- [x] `Meeting` to represent a meeting with attributes `LocalDateTime startDateTime`, `LocalDateTime endDateTime`, and `String label`.
- [x] `NonOverlappingMeetingList` to contain a list of meetings with functions like `#overlaps` and `#overlapsExcept` to support its non-overlapping nature.

#### Commands: ###
- [x] `AddMeetingCommand` with command word `addMeeting`
- [x] `ListMeetingCommand` with command word `meetings`
- [x] `EditMeetingCommand` with command word `editMeeting`
- [x] `DeleteMeetingCommand` with command word `deleteMeeting`

#### Parsers: ###
- [x] `AddMeetingCommandParser`
- [x] `ListMeetingCommandParser`
- [x] `EditMeetingCommandParser`
- [x] `DeleteMeetingCommandParser`

#### Storage:
- [x] `JsonAdaptedMeeting` to support storage of meetings in persistent memory

#### Exceptions: ###
- [x] `MeetingNotFoundException`
- [x] `OverlappingMeetingsException`

<div style="page-break-after: always;"></div>

#### Tests: ###
- [x] `MeetingTest`
- [x] `NonOverlappingMeetingListTest`
- [x] `AddMeetingCommandTest`
- [x] `ListMeetingCommandTest`
- [x] `EditMeetingCommandTest`
- [x] `DeleteMeetingCommandTest`
- [x] `AddMeetingCommandParserTest`
- [x] `ListMeetingCommandParserTest`
- [x] `EditMeetingCommandParserTest`
- [x] `DeleteMeetingCommandParserTest`

#### Test Utils: ###
- [x] `TypicalMeetings` to support testing of meetings to provide default meetings
- [x] `MeetingBuilder` to support testing of meetings to build custom meetings

## 3. Contributions to the UG:
- Improving the UG's introduction
  - Introducing the application, its target audience and main purpose
  - Introducing the core features of the application
- Adding sections to explain commands (that I implemented) that the user can call. This includes the command word, its purpose, examples of usage through sample input and output.
  - `addMeeting`
  - `meetings`
  - `editMeeting`
  - `deleteMeeting`
- Adding commands and snippets of their example usage into the summary table at the bottom
- Read through the UG to fix grammatical issues, formatting issues, and consistency issues

<div style="page-break-after: always;"></div>

## 4. Contributions to the DG:
- Our team came together to discuss and draw the architectural models together
  - ModelClassDiagram.png
  - BetterModelClassDiagram.png
  - StorageClassDiagram.png
- Explanation of the implementation of the entire Meetings feature
  - Introducing the feature
  - Explaining the attributes that the `Meeting` model has
  - Explaining the commands (`AddMeetingCommand`, `ListMeetingCommand`, `EditMeetingCommand`, `DeleteMeetingCommand`) added to support the feature
  - Giving a detailed example usage scenario including step-by-step command usage together with 9 screenshots of the application in different states to support it.

## 5.Review/mentoring contributions:
- Our team conceptualized a round-robin system of reviewing code where everyone was responsible for reviewing a certain person's code so that everyone's code had a dedicated reviewer.
- Therefore, I was assigned to review all of Joel's pull requests.
- On top of that, we reviewed other's code as well even if we were not assigned if we had the bandwidth or the pull request was tangentially related to our primary responsibilities.

## 6. Contributions beyond the project team::
- I tried my best to give many comments as I could while reviewing other team's code to support others.
- I tried my best to provide as many issues as possible during the `PE-D` so that the team would have more to work off of to improve their application for the final submission.
