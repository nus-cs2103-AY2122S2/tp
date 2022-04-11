---
layout: page
title: Lee Yong Ler's Project Portfolio Page
---
### Project: SoC InternApply
SoC InternApply - SoC InternApply helps CS students to track internship applications they are interested in. It is optimised for users who prefer CLI and have a lot of company applications and their associated tasks to keep track of. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**Code contributed**: 
[Link to my code contribution](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=yongler&breakdown=true)

**Enhancements to existing features**:
1. Extension of normal `Tag` to `PriorityTag` and `ApplicationStatusTag`. This aids in adding more information for the applications.
- I made changes in `Tag.java` , `PriorityTag.java` , `PriorityTagType.java`, `ApplicationStatusTag.java` and `ApplicationStatusTagType.java`.
- It is complete as required by our application as the important information about an application is captured by the `ApplicationStatusTag` and `PriorityTag`. If user wants to add more information, they can do so with the normal `Tag`.
- Changes include inheritance, error messages, enumerations as well as checking of input values based on enum values, which are used in many other places such as the parsers and storage.


2. `Find` command such that it can find applications based on `Name`, `JobTitle`, `Tag`, `ApplicationStatusTag` and `PriorityTag`.
- Most of the changes is in `FindCommandParser.java` , `StringUtil.java` and `FindCommand.java`.
- The feature is complete in a way that it offers users great flexibility in filtering applications based on the important criteria.
- Initially it was hard to implement as the mechanism of the original `Find` command was hard to trace, I looked through the code all the way to `LogicManager`. After understanding it, I had to code additional checks for the parser since it is different based on the ones used in the `Add` command.

<div style="page-break-after: always;"></div>

**Contributions to the UG**:
- `Add` command for the new `PriorityTag` and `ApplicationStatusTag`.
- `Find` command.

Other than these 2 features which I was involved in creating, I also helped to do these across the whole UG:
- Change the UG based on Ms. Dara (CS2101 tutor)'s recommendation
- Rewrite many confusing statements so that they are more user-friendly, where non-tech people could understand the UG
- Make the command summary more readable 
- Fix typos and grammar mistakes
- Added notes and cautions for the user so that they know it is an intended behaviour

**Contributions to the DG**:
- Updated UI component to suit our product
- `Find` command details, design considerations, uml diagrams and manual testing section
- Added use cases of the product for all features 
- Drafted the effort section
- Fixed typos and grammar error across whole DG

**Contributions to team-based tasks**:
1. Setting up the GitHub team org/repo
2. Setting up tools e.g., GitHub, Gradle 
3. Maintaining the issue tracker
4. Release management
5. Updating user/developer docs that are not specific to a feature e.g. making it more user-friendly and bug-free
6. Guide the team through project deliverables and give working suggestions during team meetings (more towards first half of tp when the team is still unsure of what to do)
7. Lead the initial project draft submission and direction 

    
**Review/mentoring contributions**:
- [Link to reviewed PRs](https://github.com/AY2122S2-CS2103T-T11-3/tp/issues?q=reviewed-by%3Ayongler)
- Also helped teammates during team meeting about possible bugs in the features as well as provide links that might be useful (issue regarding environment variables and also sorting based on Java enum).  

**Contributions beyond the project team**:
- Shared Intellij shortcut tip in the forum. [Link](https://github.com/nus-cs2103-AY2122S2/forum/issues?q=author%3Ayongler+)
- Helped to spot bugs from other teams during the demo PE.



