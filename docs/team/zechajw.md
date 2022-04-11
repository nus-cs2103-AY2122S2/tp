---
layout: page
title: Zechary Au Jun Wen's Project Portfolio Page
---

### Project: onlyFAs

onlyFAs is a desktop client-management application used by high achieving financial advisors looking to maintain and develop good relationships with current and potential clients. THe user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

## 1. Code contributed:
[RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=zechajw&breakdown=true)

## 2. Enhancements implemented:

### 1. Notes Feature
#### Related User Stories:
- As a first time user, I can add notes about my clients so that I can have a better personal understanding of each of my clients
- As a user, I can delete notes from a specific client so that I can remove notes that are no longer useful

<div style="page-break-after: always;"></div>

#### Classes Implemented:
Models:
- [x] `Note` to present a note with the attribute `value` to represent its `String` value

Commands:
- [x] `AddNoteCommand` with command word `addNote`
- [x] `DeleteNoteCommand` with command word `deleteNote`

Parsers:
- [x] `AddNoteCommandParser`
- [x] `DeleteNoteCommandParser`

Tests:
- [x] `NoteTest`
- [x] `AddNoteCommandTest`
- [x] `DeleteNoteCommandTest`
- [x] `AddNoteCommandParserTest`
- [x] `DeleteNoteCommandParserTest`

Test Utils:
- [x] `NoteBuilder`
- [x] `TypicalNotes`

<div style="page-break-after: always;"></div>

### 2. Preferences Feature

#### Related User Stories:
- As an intermediate user, I can record my clients' individual preferences so that I can better cater to their needs
- As an intermediate user, I can obtain my client's preferences so that I have a quick and easy way to obtain information on my client's preferences
- As an intermediate user, I can delete preferences from a specific client so that I can maintain an updated list of my client's preferences for easy access at all times.

#### Classes Implemented:

Models:
- [x] `PreferenceMap` to represent preferences with attributes `HashMap<String, String>` to contain the preference mappings

Commands:
- [x] `AddPreferenceCommand` with command word `addPref`
- [x] `DeletePreferenceCommand` with command word `deletePref`

Parsers:
- [x] `AddPreferenceCommandParser`
- [x] `DeletePreferenceCommandParser`

Storage:
- [x] `JsonAdaptedPreferenceMap` to support storage of preferences in persistent memory

Tests:
- [x] `PreferenceMapTest`
- [x] `AddPreferenceCommandTest`
- [x] `DeletePreferenceCommandTest`
- [x] `AddPreferenceCommandParserTest`
- [x] `DeletePreferenceCommandParserTest`

Test Utils:
- [x] `TypicalPreferenceMap` to support testing of preferences by providing default preferences
- [x] `PreferenceMapBuilder` to support testing of preferences by providign custom preferences

<div style="page-break-after: always;"></div>

### 3. Filter Feature

#### Related User Stories:
- As an intermediate user, I can retrieve a list of filtered clients by age so that I can market specific policies to certain age groups
- As an intermediate user, I can retrieve a filtered list of clients by birth month so that I can have a better understanding of when my
  clients' birthdays are for better birthday gift planning

#### Classes Implemented:
Commands:
- [x] FilterCommand with the command word `filterClients`

Parsers:
- [x] FilterCommandParser

Tests:
- [x] FilterCommandTest
- [x] FilterCommandParserTest

<div style="page-break-after: always;"></div>

### 4. Test Code

#### Classes Implemented:

Tests:
- [x] CommandResultTest
- [x] ContactedCommandParserTest
- [x] SortCommandParserTest
- [x] ViewClientCommandParserTest
- [x] SortCriteriaTest

## 3. Contributions to the UG:
- Improving the `Features` section
  - After feedback received from our CS2101 tutor, I added a `Sample Input` and `Expected Output` to each feature in the
    `Features` section. Furthermore, I added a screenshot of our application in the `Expected Output` to provide users with visual
    feedback of the correct usage of each command
  - Other groups also mentioned that our `Feetures` section is too long and confusing, hence I split the `Features` section into
    4 portions, `Basic`, `Client`, `Policy`, `Meeting` to make the section easier to read.
- Explanation of the `Note`, `Preference` and `Filter` features
  - Introducing the feature
  - Explaining the proper usage of each command for the features
  - Giving a detailed example usage scenario for each command
- Fixing grammatical errors, formatting issues and consistency bugs

## 4. Contributions to the DG:
- Our team came together to discuss and draw the architectural models together
   - ModelClassDiagram.png
   - BetterModelClassDiagram.png
   - StorageClassDiagram.png

## 5. Review/mentoring contributions:
- Our team conceptualized a round-robin system of reviewing code where everyone was responsible for reviewing a certain person's code so that everyone's code had a dedicated reviewer.
- Therefore, I was assigned to review all of Rayner's pull requests.
- On top of that, we reviewed other's code as well even if we were not assigned if we had the bandwidth or the pull request was tangentially related to our primary responsibilities.

## 6. Contributions beyond the project team:
- Striving to give useful feedback on other UGs/DGs from teams in our tutorial group
- Trying my best to provide as many useful bug reports as possible during the `PE-D`
