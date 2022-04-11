# Neeladri's Project Portfolio Page

---
## Project: TAilor

TAilor is a desktop app for managing contacts. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 10 kLoC.

### Given below are my contributions to the project:

- **Code contributed:** [RepoSense Link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=zawedcvg&breakdown=true)
- **Enhancements Implemented:**
    - Feature: Aid in implementing Groups
      - What it does: Adds another attribute named groups to the contactlist.
      - Justification: This feature allows us to define tutorial groups which is a fundamental feature for our app TAilor.
      - Highlights: The implementation of this feature included extensive testing for the command.
      - Time spent: 150 mins

    - Feature: Aid in implementing setDefaultCommand
      - What it does: Allows to enter a default group for a particular mod.
      - Justification: A tutor predominantly teaches one group for a particular mod, which makes adding users always include
        the redundant group, which wastes a lot of time for the user.
      - Highlights: The implementation of this feature required making the group attribute optional. This required changes in a couple of classes
      and required the addition of several new checks. This included testing as well.
      - Time spent: 250 mins

    - Feature: Implement the storage for the modulelist
      - What it does: Makes the default groups for modules persistent.
      - Justification: This allows to store the default group information across sessions, removing redundancies for the user.
      - Highlights: This feature required the understanding of the storage component and add various classes to facilitate the
        storage of the modulelist
      - Time spent: 300 mins

    - Bug: Editing groups
      - fixed a bug where editing groups was throwing errors

    - Bug: Groups not visible in the UI
      - fixed a bug where groups was not being rendered in the UI


- **Contribution to the User Guide:**
    - Fix user guide to account for the new parameter group
    - Add scenario for the add command where group is optional

- **Contribution to the Developer Guide:**
    - Non-Functional requirements
    - Glossary
    - modify implementation and design considerations for setDefaultCommand
    - make changes in the storage component
    - update UML diagram for setDefaultCommand

- **Contributions to team-based tasks:**
    - suggested and reinforced the git workflow for the team to follow

- **Community Contribution:**
    - PRs reviewed: [\#226] [\#217] [\#98] [\#110]
    - Issues fixed: [\#1] [\#7] [\#31] [\#35] [\#49] [\#78] [\#82] [\#83] [\#92] [\#115] [\#146] [\#161] [\#183] [\#186] [\#208]
