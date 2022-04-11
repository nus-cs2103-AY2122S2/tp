---
layout: page
title: Joel Wong's Project Portfolio Page
---

### Project: onlyFAs

onlyFAs is a desktop client-management application used by high achieving financial advisors looking to maintain and develop good relationships with current and potential clients. THe user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

## Code contributed:
[RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=joelwongjy&breakdown=true)

## Enhancements implemented:
I was the sole frontend developer and was in charge of building the entire application's UI and linking all model updates to the view.
1. Revamped user interface of the entire application
2. Created tutorial for new users.
3. Redesigned contact list to include more information
4. Created right panel to display client details or meetings
5. Created meeting list, policy list and preferences list
6. Modified `SortCommand` to use enum `SortCriteria`
7. Modified `Model` and `Logic` to listen to changes in client details and update view

I also updated the models such as `Preference` and `Client` accordingly to provide suitable data structures for displaying.

<div style="page-break-after: always;"></div>

### User Interface
I created the following classes as well as the corresponding `.fxml` files for each component:
- [X] `ClientCard` in show brief client details
- [X] `ClientDisplay` to display full client details
- [X] `ClientListPanel` to list clients
- [X] `MainWindow` to listen for changes on `Client` and `Meeting` displays
- [X] `MeetingCard` to show meeting details
- [X] `MeetingListPanel` to show all meetings
- [X] `PolicyCard` to show policy details
- [X] `PolicyListPanel` to show all policies
- [X] `PreferenceRow` to show all preferences
- [X] `TutorialWindow` to display app tutorial

### Layout
- [X] `DarkTheme.css` changed all CSS layouts and designs to fit theme

### Commands
- [X] `TutorialCommand` with command word `tutorial`

### Models
- [X] `SortCriteria` enum with different sort by methods
- [X] `Model` & `ModelManager` to store state of various components
- [X] `Logic` & `LogicManager` to communicate with `Model`

## Contributions to the UG:
- Created `Meeting` related function guides
  - List meetings
  - View meetings
  - Update meetings

## Contributions to the DG:
- Created PlantUML diagrams for `UI`
- Wrote user stories and use cases
- Wrote `Client` features

## Review/mentoring contributions:
- Our team created a round-robin system of code-review.
- I reviewed all of Shem Maleriado Limos's pull requests.

## Contributions beyond the project team:
- Conducted user interface research.
- Designed mockups for new user interface to completely revamp the app.
