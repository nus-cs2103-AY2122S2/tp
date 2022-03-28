---
layout: page
title: User Guide
---

TeachWhat! is a **desktop app for tutors to manage their schedule, students, lessons, and 
income**, optimized for use via a Command Line Interface (CLI) while still having the 
benefits of a Graphical User Interface (GUI). 
If you can type fast, TeachWhat! can get your tuition management tasks done faster than 
traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TeachWhat.jar` from [here](https://github.com/AY2122S2-CS2103T-W11-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your LessonBook.

4. To start TeachWhat!

|   OS    | How to start TeachWhat!                                                                                   |
|:-------:|-----------------------------------------------------------------------------------------------------------|
| Windows | Double-click the TeachWhat! jar file.                                                                     |
|   Mac   | On Terminal, go to the directory where the TeachWhat! jar file is and do <br/> `java -jar TeachWhat!.jar` |

5. The GUI similar to the below should appear in a few seconds. 
Note how the app contains some sample data.<br> ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. Execute **`help`** to open the help window.<br>
   Some example commands you can try:

   * **`listlessons`** : Lists all lessons.

   * **`liststudents`** : Lists all students.

   * **`student <STUDENT_ID>`** : Displays the details of the student with the provided `STUDENT_ID`.
   
   * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source:Notes about the command format:**

* **Words in `<UPPER_CASE>` are the parameters to be supplied by the user.** <br>
  * e.g. in `addstudent -n <STUDENT_NAME>`, `<STUDENT_NAME>` is a parameter which 
  can be used as `addstudent -n John Doe`.

* **Parameters can be in any order.**
  * e.g. if the command specifies `-n <LESSON_NAME> -d <DATE/START_DATE> -t <START_TIME>`, <br>
  `-d <DATE / START_DATE> -t <START_TIME> -n <lesson_NAME>` is also acceptable.

* **Where `<STUDENT_ID>` and `<LESSON_ID>` are mentioned, they are with referring to the index of the student 
or lesson in the viewable lists.**
* **Duplicate flags are not allowed unless explicitly stated.**
  * e.g. `assign -s 1 -l 1 -l 2` is not allowed as there are two `-l` flags present in the command.

* In each Feature description, the command word section will tell you what the command words are for each command.
You may see more than one command word for a command, the second one being the shorter command for more advanced users.
</div>

---

### Adding a student

Adds a student to TeachWhat!

Command word: `addstudent` / `as`

Format: `addstudent -n <STUDENT_NAME> -p <PHONE_NO> -e <EMAIL> -a <ADDRESS> -t <TAG>` 

Example: `addstudent -n James -p 999 -e jamesboyo@gmail.com -a 34 Lor 11 Geylang -t hardworking -t small`

Adds a student with the following attributes:

* name: James 
* phone: 999 
* email: jamesboyo@gmail.com 
* address: 34 Lor 11 Geylang 
* tags: hardworking, small

**Note that :**

* The address, email and tag are optional and can be omitted so that the tutor only keeps the most vital 
information which is the student's name and phone number.
* There can be **multiple tags** assigned to one student, as demonstrated in the example above.

---

### Editing a Student

Edits an existing student in TeachWhat!.

Command word: `editstudent` / `es`

Format: `editstudent <STUDENT_ID> -n <NAME> -p <PHONE_NO> -e <EMAIL> -a <ADDRESS> -t <TAG>`

Example: `editstudent 2 -n Sammy -p 123 -t codinggod -t extrageeky -t extrahansum`

Suppose the student with the `<STUDENT_ID>` of **2** is "Sammy boyo". The edit command above will cause the changes shown.

| Before                                                                                                                 | After                                                                                                                                       |
|------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| Name: Sammy boyo<br/>Phone no.: 911 <br/>Email: sam@gmail.com<br/>Address: 123 West Coast road<br/>Tags: geeky, hansum | Name: Sammy boyo<br/>Phone no.: 123 <br/>Email: sam@gmail.com<br/>Address: 123 West Coast road<br/>Tags: codinggod, extrageeky, extrahansum |

**Note that:**

* At least one prefix must be given to edit the student. 
* If tags are provided, **all** existing tags will be replaced with the new tags.

---

### Deleting a student

Deletes a student from TeachWhat!

Command word: `rmstudent` / `rms`

Format: `rmstudent <STUDENT_ID>`

Example: `rmstudent 13`

---

### Adding a lesson

Adds a lesson to TeachWhat!

Command word: `addlesson` / `al`

Format: `addlesson <LESSON_TYPE> -n <LESSON_NAME> -s <SUBJECT> -a <ADDRESS_OF_LESSON> -d <DATE_OF_LESSON> -t <STARTING_TIME> -h <DURATION_OF_LESSON_IN_HOURS> -m <DURATION_OF_LESSON_IN_MINUTES>`

Supported Types: Recurring `-r` and Temporary

Example: `addlesson -r -n Biology group 1 -s Biology -a Blk 11 Ang Mo Kio Street 74, #11-04 -d 27-02-2022 -t 18:00 -h 1 -m 50`

Adds a recurring lesson with the following attributes:
* name: Biology group 1
* subject: Biology
* where the lesson is conducted: Blk 11 Ang Mo Kio Street 74, # 11-04
* on date: 27 February 2022
* starting at: 6pm
* duration: 1 hour and 50 minutes
>**⚠️ Note:** If no type is given then the lesson is assumed to be temporary.

>**❗️️** If you have any existing lessons that clash with the one that you're trying to add, TeachWhat! will **not** add this lesson. In this scenario, TeachWhat! will show you a warning message and also a list of such conflicting lessons.
> 
> 💡 If you still want to add this lesson, use `rmlesson` to remove the list of conflicting lessons shown before proceeding to add the new lesson.

---

### Editing a Lesson

Edits an existing lesson in TeachWhat!.

Command word: `editlesson` / `el`

Format: `editlesson <LESSON_ID> -n <LESSON_NAME> -s <SUBJECT> -a <ADDRESS_OF_LESSON> -d <DATE_OF_LESSON> -t <STARTING_TIME> -h <DURATION_OF_LESSON_IN_HOURS> -m <DURATION_OF_LESSON_IN_MINUTES>`

Example: `editlesson 2 -n Trial lesson -t 17:00 -h 2`

Suppose the lesson with `<LESSON_ID>` of **2** is "Biology group 1". The edit command will cause the changes shown below.

| Before                                                                                                                                                             | After                                                                                                                                                       |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Name: Biology group 1<br/>Subject: Biology<br/>Address: Blk 11 Ang Mo Kio Street 74, #11-04<br/>Date: 27 February 2022<br/>Start Time: 6pm<br/>Duration: 1h 50mins | Name: Trial Lesson<br/>Subject: Biology<br/>Address: Blk 11 Ang Mo Kio Street 74, # 11-04<br/>Date: 27 February 2022<br/>Start Time: 5pm<br/>Duration: 2hrs |

**Note that:**

* Editing of the lesson's type is not allowed. Recurring lessons cannot be edited to become temporary lessons and vice-versa.

---

### Deleting a lesson

Deletes the specified lesson from the list of lessons.

Command word: `rmlesson` / `rml`

Format:  `rmlesson <LESSON_ID>`

Deletes the lesson with the specified `LESSON_ID`.
The `LESSON_ID` corresponds to the number in the displayed lesson list.
The `LESSON_ID` must be a positive integer.

Example:
`listlessons` to list the lessons and display their `LESSON_ID`, 
followed by `rmlesson 5` to delete the lesson with the id **5**.

---

### Assigning a student to a lesson

Assigns the specified student to the specified lesson.

Command word: `assign`

Format: `assign -s <STUDENT_ID> -l <LESSON_ID>`

Example: `liststudents` followed by `assign -s 5 -l 11` assigns the student with `<STUDENT_ID>` of **5**
to the lesson with `<LESSON_ID>` of **11**.

---

### Unassigning a student to a lesson

Unssigns the specified student from the specified lesson.

Command word: `unassign`

Format: `unassign -s <STUDENT_ID> -l <LESSON_ID>`

Example: `liststudents` followed by `unassign -s 5 -l 11` unassigns the student with `<STUDENT_ID>` of **5**
to the lesson with `<LESSON_ID>` of **11**.

---

### Viewing all lessons

Format: `listlessons` / `ll`

Example: `listlessons` would display a list of all the lessons that a tutor has.

---

### Viewing all students

Format: `liststudents` / `ls`

Example: `liststudents` would display a list of all the students that a tutor has.

---

### Viewing a lesson
Displays the details for the lesson with the specified lesson ID.

Command word: `lesson`

Format: `lesson <LESSON_ID>` 

Example: `lesson 3` displays the details of the lesson with `LESSON_ID` of **3**.

---

### Viewing a student

Displays the details for the student with the specified student ID.

Command word: `student`

Format: `student <STUDENT_ID>`

Examples: `student 3` displays the details of the student with `STUDENT_ID` of **3**.

___

### Finding a Student

Displays the students whose name or tags contain the input provided.

Command word: `findstudent` / `fs`

Format: `findstudent <FIND_CRITERIA>`

Examples: `findstudent alex friends` searches the student list for students whose
tags or name contains with "alex" **or** friends.

---

### Finding a Lesson

Displays the lessons whose name contain the input provided.

Command word: `findlesson` / `fl`

Format: `findlesson <FIND_CRITERIA>`

Examples: `findlesson biology` searches the lesson list for lessons which names
 include "biology"

---

### Help

Opens a pop-up that gives you a link to the User guide.

Format: `help`

_Alternatively, you can press `F1` to open the pop-up._

---

### Exit

Closes TeachWhat! window.
  
Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous TeachWhat! folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action               | Format, Examples                                                                                                                                                                          |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Student**      | `addstudent <STUDENT_NAME>` e.g. `addstudent sammy boyo`                                                                                                                                  |
| **Add lesson**       | `addlesson <LESSON_TYPE> -n <LESSON_NAME> -s <SUBJECT> -a <ADDRESS_OF_LESSON> -d <DATE_OF_LESSON> -t <STARTING_TIME> -h <DURATION_OF_LESSON_IN_HOURS> -m <DURATION_OF_LESSON_IN_MINUTES>` |
| **Delete Student**   | `rmstudent <STUDENT_ID>` e.g. `rmstudent 13`                                                                                                                                              |
| **Delete lesson**    | `rmlesson <LESSON_ID>` e.g.`rmlesson 5`                                                                                                                                                   |
| **Edit Student**     | `editstudent <STUDENT_ID> -n <NAME> -p <PHONE_NO> -e <EMAIL> -a <ADDRESS> -t <TAG>`                                                                                                       |
| **Edit lesson**      | `editlesson <LESSON_ID> -n <LESSON_NAME> -s <SUBJECT> -a <ADDRESS_OF_LESSON> -d <DATE_OF_LESSON> -t <STARTING_TIME> -h <DURATION_OF_LESSON_IN_HOURS> -m <DURATION_OF_LESSON_IN_MINUTES>`  |
| **Assign Student**   | `assign -s <STUDENT_ID> -l <LESSON_ID>` e.g.`assign -s 5 -l 11`                                                                                                                           |
| **Unassign Student** | `unassign -s <STUDENT_ID> -l <LESSON_ID>` e.g.`unassign -s 5 -l 11`                                                                                                                       |
| **List Students**    | `liststudents`                                                                                                                                                                            |
| **List lesson**      | `listlessons`                                                                                                                                                                             |
| **View Student**     | `student <STUDENT_ID>` e.g. `student 5`                                                                                                                                                   |
| **View lesson**      | `lesson <LESSON_ID>` e.g. `lesson 2`                                                                                                                                                      |
| **Find Student**     | `findlesson <FIND_CRITERIA>`                                                                                                                                                              |
| **Find Lesson**      | `findstudent <FIND_CRITERIA>`                                                                                                                                                             |
| **Help**             | `help`                                                                                                                                                                                    |
| **Exit**             | `exit`                                                                                                                                                                                    |
