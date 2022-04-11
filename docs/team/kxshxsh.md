# Kashish's Project Portfolio Page

---
## Project: TAilor

TAilor is a desktop app for managing contacts. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 10 kLoC. Through TAilor we hope to make most common,
tedious administrative tasks, that Teaching Assistants encounter, easier and more efficient to carry out.
---

###Summary of Contributions
My contributions to the project can be found below:

* My **Code Contributions** can be found [here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=kxshxsh&breakdown=true)

* **Enhancements Implemented**: 
   1. **Feature #1: Import-csv command** 
      * **What it does**:
        Allows our user to import a csv file, given the required column headers are present. All student data from the 
        csv file is imported into the TAilor database for further processing.
      * **Justification:**
        This feature improves the product significantly because it allows user to forgo the tedious task of manually
        entering every student's data into TAilor's database. All they need to do is export the excel file from LumiNUS,
        with all the requested fields, convert to csv and then import with the file path!
      * **Highlights**:
        * It adds students additively to the already present student list instead of just replacing the previous entries. 
        This is a highlight since the user can use this aspect of the command to either update the student list at once,
        or even to import data from different csv files (that could have been from different modules). 
        * I was also flexible with the format of the file path taken in, the user can use either an absolute path or a 
        relative one and it shall execute without error.
        * As such this feature takes into consideration many possible user scenarios and has something for everyone - this
        according to me is its highlight.
      * **Challenges**:
        * Implementing a whole CsvUtil class was a challenge since I had to use an open-source, third party library Opencsv 
          and also had to consider the different errors that a user could make while exporting or drafting their csv files.
        * The defensive coding in import-csv command's implementation is therefore high and also took a lot of work for the same.
      * **Time spent on this (estimate)**:
        600 mins

   2. **Feature #2: implementing the base for groups**
      * **What it does**:
        It associates a student with a group (alongside the newly-implemented module field) so that students can essentially
        be categorised or placed into tutorial groups. This allows for the same tutor to be able to teach the same
        student under different modules (and thus tutorial groups).
      * **Justification and Highlights**:
        This feature allows for further categorization and organization. Especially required for those situations when
        one user tutors more than one (tutorial) group under a module. It is important to ensure that a tutor can:
        * view filtered lists on the basis of different tutorial groups and
        * mail all students in one group
        This is where the group attribute merges seamlessly, and also enhances, functions implemented by my team members.
        This is undoubtedly the highlight of this feature, the integrity and cohesion that it enhances.
      * **Challenges**:
        While I thought the implementation would have been very similar to that of the former "address" attribute, I ran
        into an issue with the UI not working suddenly. Since this was the first feature that I ever implemented, trying
        to fix the GUI issue was quite challenging to narrow down and rectify. I was quite thorough
      * **Time spent on this (estimate)**:
        300 mins
      
   3. **Feature #3: implementing the code base for set-default-group command**    
      * **What it does**:
        It associates a default group value to a module that can be set to what the user supplies through the arguments
        of this command. If a default group value is set for the specific module that is mentioned in a following add command,
        then the group parameter required in the same add command actually becomes optional. 
        Example:
          * `set-default-group m/CS2103T g/W12-1` followed by `add n/Kashish Varshney e/e0589909@u.nus.edu a/A0225771A m/CS2103T`
            is a valid command and Kashish Varshney would be added to m/CS2103T g/W12-1 as a result
          * if a group parameter was provided in the add command though then that would be prioritised over the default group
            for that command's execution
      * **Justification**:
        This command helps our users in the situation where they would have to bulk-add a large number of students, ideally
        to the same tutorial group, to the student list. It helps remove the redundancy that comes alongside several, repeated
        add commands.
      * **Time Spent on this (estimate)**:
        500 mins

   4. **Feature #4: Replacing phone attribute with Student Number**
      * **What it does**:
        Since the Student Number is the unique identifier for every student, I decided to replace the phone attribute of
        person with Student Number. This was the incorporated into all of our commands too - pre existing and new.
      * **Justification**:
        Student/Matriculation Number is the unique identifier and thus has to be an attribute of Person such that we can
        maintain a UniquePersonsList better. This loophole was actually found in out PE-D so, I worked towards implementing
        the correct checks that no two students can have the same Student Number (or NUS email ids).
      * **Challenges**:
        * The same UI bug that I encountered while creating the group attribute for a person created an obstacle for me
          this time too. But having learnt from my past experience, I was able to rectify the errors and get the display
          to work again for everyone to use.
      * **Time spent on this (estimate):**
        300 mins

   
* **Contribution to the User Guide**: 
  * the initial draft version was worked on rather equally.
  * Finalised the official categorization and ordering of commands.
  * The whole import-csv command's section is done by me.
  * Entire section on set-default-group command was done by me too.
  * All instances of group were added and instances of phone replaced (with student number) by me.
  * **Editing the data file** section was refine by me.
  * The final **signing off** was coined by me, Han Yang helped add it to the UG since I wasn't around. 
  * Final checks on UG that were suggested and done by me can be found in [PR #236](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/236)
  
* **Contributions to the Developer Guide**:
  * Initial **Acknowledgements** was added by me in [PR #232](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/232/files)
  * Captions and numeration for figures suggested and partially implemented by me in [PR #236](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/236)
  * Fig 8 on parsing argument-taking commands was created by me solely. This was after seeking advice from our tutor Darius with the aim to
    de-clutter some of the other UML Sequence Diagrams. [PR #226](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/226)
  * I updated **all** the inherited UML diagrams from AB3, bar undo/redo, as can be found in [PR #110](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/110)
  * The entire **import-csv** section for the DG was written by me [PR #232](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/232/files)
  * The base for **set-default-group** was written by me in [PR #110](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/110) and later enhanced with the storage component by Neeladri
  * Fig 25 highlighting the sequence diagram for set-default-group command was created by me (excluding the storage aspect) [PR #110](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/110) [PR #226](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/226) etc.
  * the use cases were done solely by me while the user stories, and value proposition was a team effort.

* **Contributions to team-based tasks**:
  * played a role in renaming the product
  * Created the milestones for our team several times and kept track of us meeting deadlines for v1.3 and v1.3b
  * Issue tracker and release management were done for most of the milestones by me as well
  * Played key roles in our meetings, especially when it came to engaging in discussions and coming up with solutions ot our problems
  * Ran final UG-DG checks and found near 20 mistakes that I then corrected irrespective of whose section it was. 

6. **Reviewing/mentoring Contributions**:
   * [PR #216](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/216), 
     [PR #214](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/214),
     [PR #213](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/213),
     [PR #212](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/212),
     [PR #211](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/211),
     [PR #123](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/123),
     [PR #121](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/121),
     [PR #119](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/119),
     [PR #107](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/107),
     [PR #84](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/84),
     [PR #42](https://github.com/AY2122S2-CS2103T-W12-1/tp/pull/42),
   

