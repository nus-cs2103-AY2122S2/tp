---
layout: page
title: Naaman Tan's Project Portfolio Page
---

### Project: Amigos

Amigos is a desktop application to help tech-savvy university students
manage their friendships by helping them to keep track of important
details related to their friends. It is optimized for use via a Command Line interface while
still having the benefits of a Graphical User Interface (GUI).

### Summary of contributions
1. **New Feature: Logs** Added the ability to create, edit and delete logs.
    * What it does: Allows the user to add "notes" about friends in the application, 
                    each with a title and a description.
    * Justification: As an application to help improve user friendships, the addition of logs
                     feature allows users to better keep track of their relationship details.
    * Highlights: 
        * To optimize for command-line convenience, the implementation focused on giving users 
          flexibility - selecting users by a displayed `INDEX` or by their `NAME`, using flags to 
          choose actions (e.g. `-a` flag to delete all logs at once), etc., and with this flexibility
          came a careful analysis of requirements, design choices and rigorous testing.


2. **New Feature: Insights** Added the ability to view summary statistics about friends in the application.
    * What it does: Allows the user to get a better sense of the _quality_ of his relationships by looking  at numbers.
    * Justification: The feature aims to "complete" the focus on helping users maintain their relationships, by complementing logs
                     and event-planning features by computing some summary statistics about their relationships
                     (e.g. most recent event).
    * Highlights: 
         * Choice was made to include insights as a separate tab that was _dynamically_ computed/updated to reduce coupling
           with other components within the `Model` component. 
         

3. **Enhancements to existing features**
    * Access to persons by both `INDEX` and `NAME` for convenience and different user skill levels. (Pull requests [\#107](), [\#110](), [\#134]()) 

    
4. **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tanyjnaaman&breakdown=true)


5. **Documentation**:
    * User Guide:
        * Added documentation for the features `addlog`, `editlog`, `deletelog` and `showinsights`.
    * Developer Guide:
        * Added documentation for use cases of the features `addlog`, `editlog`, `deletelog` and `showinsights`
        * Added documentation for design of features `addlog`, `editlog`, `deletelog` and `showinsights`

6. **Team-based tasks**
    * Set up GitHub team org/repo and maintained issue tracker for milestone v1.1

