---
layout: page title: User Guide
---

d'Intérieur is a **desktop app for interior designers to manage their contacts and projects efficiently**. 
If you can type fast, d'Intérieur can get your contact management tasks done faster than traditional GUI apps.

If you utilize other online applications for interior designing and lack a centralized, robust, and efficient customer
management tool, this application will be able to meet your needs. Designed with modern, tech-savvy interior designers
in mind, the app is minimalistic, practical, and greatly rewards users with basic computer proficiency, such as
quick typing speed and general know-how.

In this user guide, we will walk you through the basic features and commands of the application, so that you can 
quickly get started and make full of use of what the application has to offer.

## Table of Contents

* [Quick start](#quick-start)
* [Features](#features)
  * [Command format](#command-format)
  * [The UI](#the-ui)
    * [Listing all contacts](#listing-all-contacts--list)
    * [Viewing a contact's full details](#viewing-a-contacts-full-details--view)
    * [Viewing help](#viewing-help--help)
    * [Exiting the program](#exiting-the-program--exit)
  * [Updating Contacts](#updating-contacts)
    * [Adding a contact](#adding-a-contact--add)
    * [Editing a contact](#editing-a-contact--edit)
    * [Deleting a contact](#deleting-a-contact--delete)
    * [Creating a tag](#creating-a-tag--tag)
    * [Assigning a tag to a contact](#assigning-a-tag-to-a-contact--assign)
    * [Unassigning a tag from a contact](#unassigning-a-tag-from-a-contact--unassign)
    * [Deleting a tag](#deleting-a-tag--deltag)
    * [Adding favourites](#adding-favourites--fav)
    * [Adding high importance flag](#adding-high-importance-flag--impt)
    * [Adding deadlines to meet in relation to a contact](#adding-deadlines-to-meet-in-relation-to-a-contact--deadline)
    * [Deleting a deadline from a contact](#deleting-a-deadline-from-a-contact--deldl)
    * [Adding additional notes to a contact](#adding-additional-notes-to-a-contact--note)
    * [Deleting notes from a contact](#deleting-notes-from-a-contact--delnote)
    * [Adding images](#adding-images--addimg)
    * [List contact's images](#list-contacts-images--images)
    * [Deleting images](#deleting-images--delimg)
    * [Clearing all entries](#clearing-all-entries--clear)
  * [Navigating your contact list](#navigating-your-contact-list)
    * [Listing favourites](#listing-favourites--favourites)
    * [Listing contacts with high importance](#listing-contacts-with-high-importance--impts)
    * [Prioritising relevant contacts to you](#prioritising-relevant-contacts-to-you--sort)
    * [Locating contacts by name](#locating-contacts-by-name--find)
    * [Locating contacts by tag](#locating-contacts-by-tag--findtag)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
  * [Cycling through input history](#cycling-through-input-history)
  * [Archiving data files](#archiving-data-files-coming-in-v20)
* [FAQ](#faq)
* [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `dinterieur.jar` from [here](https://github.com/AY2122S2-CS2103T-T12-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your d'Intérieur.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
![Sample List View](images/Sample1.png)
![Sample Detailed View](images/Sample2.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`add n/Mary Jane p/12345678 e/maryJ@example.com`** : Adds a contact named Mary Jane to the Address Book.

    * **`delete`** `3` : Deletes the 3rd contact shown in the current list.

    * **`fav`** `2` : Adds the 2nd contact shown in the current list to your list of favourite contacts

    * **`view`** `2` : Brings the 2nd contact into detailed view.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Tutorial

1. This is the **list view**, the primary view you will work with when using the app.
![Default list view](images/Sample1.png)
2. In list view, all commands requiring an `INDEX` will reference the index provided on the left side of the contact's
name. For example, typing the command `fav 2` will favourite the 2nd contact in the list.
![Tutorial fav](images/TutorialFav.png)

3. Add a new contact by running `add n/<YOUR NAME> p/<YOUR PHONE NUMBER> e/<YOUR EMAIL ADDRESS>`. These fields are 
mandatory when creating a new contact. However, you can add more optional information in the same add command, or in a
future edit command. See [adding a contact](#adding-a-contact--add) and [editing a contact](#editing-a-contact--edit)
for more information.
![Tutorial add](images/TutorialAdd.png)

4. To bring the second contact into **detailed view**, input `view 2`.
![Tutorial view](images/TutorialView.png)

5. In **detailed view**, some commands available to you in **list view** are no longer available, however, your commands
will now automatically reference the current contact you are viewing in **detailed view**. For example, you can type
`impt` to simply label the current contact you are viewing to set them as an important contact.
![Tutorial impt](images/TutorialImpt.png)

6. To exit **detailed view**, simply input `list` to return to the **list view**.

These are just the basic features to get you ready to start using the app. There are many more features for you to
explore, it is recommended for you to have a read through our User Guide to familiarize yourself with the functionalities
of the app. You can always input the `help` command whenever you need help or want to reference the User Guide again!

--------------------------------------------------------------------------------------------------------------------

## Features

### Command Format

Before going into the commands, take note of how the command format is given in the guide:

* Words in `UPPER_CASE` are the values that you will enter in the command.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Mary Jane`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Mary Jane t/friend` or as `n/Mary Jane`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Values can be given in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a type of value is expected only once in the command but you specified it multiple times, only the last occurrence of
  the type will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous values for commands that do not take in values (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### The UI

The UI consists of the command line to enter your commands, the feedback box which displays textual information about
the result of the command execution, and the contact display.

There are 2 main ways to view contacts.

### Listing all contacts : `list`

Shows the list view with all contacts in the address book.

Format: `list`

### Viewing a contact's full details : `view`

Allows you to view the full details of the contact, as some are hidden in the contact list. This command **only works in
list view**.

Format `view INDEX`

#### Commands in detailed view

Some commands may work differently in the detailed view from in the list view.

In general, commands for modifying a contact will work, and will modify the contact currently displayed. As such, there is no need to give an index for those commands anymore, and **they will be ignored** if the command is called in this view.

If the command does not work in the current view, the app will inform you. To return to list view, use `list`.

You may check out the summary table of commands for the overview.

### Viewing help : `help`

Shows a message explaining how to access the help page. You may use this frequently when you first begin using d'Intérieur.

When you're feeling overwhelmed by the number of commands available, refer to the [command summary](#command-summary) and the examples!

Format: `help`

![helpMessage](images/helpMessage.png)

<div markdown="block" class="alert alert-info">

**:information_source: More information about the help window:**<br>

After you successfully clicked on the `Copy URL` button, you will see the following window:

![copyUrlSuccessMessage](images/copyLinkSuccessMessage.png)

You're now ready to paste the link into your browser!
</div>

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Updating contacts

There is a set of information that you can save for a contact. This section contains the commands for
adding contacts and modifying their information.

### Adding a contact : `add`

You can add a contact to the address book with the **address as an optional field**.

Your contacts are uniquely identified by their `NAME` which is **case-sensitive**.<br>
e.g. `Alex Yeoh` is different from `alex yeoh`

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have 0 or 1 address
</div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:

* `add n/John Doe p/98765432 e/johnd@example.com`
* `add n/Mary Jane p/12345678 e/maryJ@example.com a/Bukit Timah t/completed`

### Editing a contact : `edit`

Edits an existing contact in the address book. This command can be used in detailed view.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list.
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To edit tags, use assign and unassign commands
</div>

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567`
  and `johndoe@example.com` respectively.
* `edit 1 n/John` Edits the name of the 1st contact to be `John`.

Format in detailed view: `edit [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]…​`

Example:

* `edit p/88438809 e/alex_yeoh@example.com` Edits the phone number and email address of the contact in detailed view to
  be `88438809` and `alex_yeoh@example.com` respectively.

### Deleting a contact : `delete`

Deletes the specified contact from the address book. This command **only works in list view**.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd contact in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

### Creating a tag : `tag`

Creates a tag that can be assigned to any contact. This command can be used in detailed view with the same format.

Format: `tag TAGNAME`

* A tag with the same `TAGNAME` can **only be created once**.
* The `TAGNAME` is case-insensitive. e.g. creating the tag `friends` will not allow `Friends` to be created.
* `TAGNAME` must be alphanumeric. e.g. `Hello`, `Friends`, `Colleagues`
* `TAGNAME` such as `-1`, `Sub Contractors` are not allowed. i.e. non-alphanumeric characters, including spaces.
* To create a tag named `Sub Contractors`, eliminate the whitespace in between in order for it to be a valid tag.
  e.g. `SubContractors`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can create meaningful tags to assign your contacts with! With tags, you can search for contacts assigned to that particular tag!
Tags must be created first before you can perform any tag related features.
</div>

List of tag related features:
* [Assign Tag](#assigning-a-tag-to-a-contact--assign)
* [Unassign Tag](#unassigning-a-tag-from-a-contact--unassign)
* [Find Tag](#locating-contacts-by-tag--findtag)
* [Delete Tag](#deleting-a-tag--deltag)

Example:

* `tag Friends` creates a tag `Friends` to be stored in the address book.
  ![result for create tag friends](images/create-tag/create-tag-friends.png)

### Assigning a tag to a contact : `assign`

Assigns a created tag to a contact. This command can be used in detailed view.

Format: `assign INDEX TAGNAME`

* Assigns a `TAG` with a given `TAGNAME` to a contact at the specified `INDEX`
* The `TAG` given by the `TAGNAME` must be created first.
* The `TAGNAME` is case-insensitive.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, ...​
* The contact should have **at most one** `TAG` with a given unique `TAGNAME`.
* The contact assigned to the given `TAGNAME` cannot be assigned to the same `TAGNAME` again.
  e.g. assigning the tag `friends` to Alice at index 1 will not allow `Friends` to be assigned to the same contact.

Example:

* `assign 1 Friends` assigns a tag `Friends` to the contact at index `1`.

  * Before using the command `assign 1 Friends`:
  ![before assign 1 friends](images/assign-tag/before-assign-1-friends.png)

  * `assign 1 Friends`:
  ![result for assign 1 friends](images/assign-tag/assign-1-friends.png)

* `assign 3 Colleagues` assigns a tag `Colleagues` to the contact at index `3`.

  * Before using the command `assign 3 Colleagues`:
  ![before assign 3 colleagues](images/assign-tag/before-assign-3-colleagues.png)

  * `assign 3 Colleagues`:
  ![result for assign 3 colleagues](images/assign-tag/assign-3-colleagues.png)

Format in detailed view: `assign TAGNAME`

Example:

* `assign client` assigns a tag `client` to the currently viewed contact.

  * `view 1`
  ![result for view 1](images/assign-tag/view-1.png)

  * `assign client`
  ![result for assign client](images/assign-tag/view-assign-client.png)


### Unassigning a tag from a contact : `unassign`

Unassigns a created tag from a contact. This command can be used in detailed view.

Format: `unassign INDEX TAGNAME`

* Removes a `TAG` with a given `TAGNAME` from a contact at the specified `INDEX`
* The `TAG` given by the `TAGNAME` must be created first.
* The `TAGNAME` is case-insensitive.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, ...​
* The contact must have been assigned to this `TAG` previously.

Example:

* `unassign 1 Friends` removes the tag `Friends` from the contact at index `1`.
  * Before using the command `unassign 1 Friends`
  ![before unassign 1 Friends](images/unassign-tag/before-unassign-1-friends.png)

  * `unassign 1 Friends`
  ![result for unassign 1 Friends](images/unassign-tag/unassign-1-friends.png)


Format in detailed view: `unassign TAGNAME`

Example:

* `unassign client` removes the tag `client` from the currently viewed contact.
  * `view 1`
  ![result for view 1](images/unassign-tag/view-1.png)

  * `unassign client`
  ![result for unassign client](images/unassign-tag/view-unassign-client.png)

### Deleting a tag : `deltag`

Deletes the specified tag(s)

Format: `deltag TAGNAME [MORE_TAGNAME]`

* Deletes the tag(s) identified by the given `TAGNAME`.
* Unassigns the deleted tags from all contacts who were previously assigned to the `tag` with given `TAGNAME`.
* If the multiple `TAGNAME` specified has more than 1 `tag` that cannot be identified , the identifiable tag(s) will be deleted.

Examples:

* `deltag friends` deletes the tag `friends`

  * Before using the command `deltag friends`:
  ![before deltag friends](images/del-tag/before-deltag-friends.png)

  * `deltag Friends`
  ![result for deltag friends](images/del-tag/deltag-friends.png)

* `deltag friends colleagues` deletes the tag `friends` and `colleagues`

  * Before using the command `deltag friends colleagues`
  ![before deltag friends colleagues](images/del-tag/before-deltag-friends-colleagues.png)

  * `deltag friends colleagues`
  ![result for deltag friends colleagues](images/del-tag/deltag-friends-colleagues.png)

* `deltag friends colleagues` when the tag `colleagues` does not exist will delete the tag `friends` and unassign the tag `friends` from every contact
  * Before using the command `deltag friends colleagues`
  ![before deltag friends colleagues not exist](images/del-tag/before-deltag-friends-colleagues-not-exist.png)

  * `deltag friends colleagues`
  ![result for deltag friends colleagues, colleagues not exist](images/del-tag/deltag-friends-colleagues-not-exist.png)

* `deltag colleagues` when the tag `colleagues` does not exist will not change the data.
![result for deltag colleagues](images/del-tag/deltag-colleagues-not-exist.png)

### Adding favourites : `fav`

Toggles the favourite status of your contacts. This command can be used in detailed view.
Favourited contacts **can be un-favourited** by running the same command on the contact again.

Format: `fav INDEX`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can run `fav INDEX` where `INDEX` is the index of a contact that currently belongs in your favourites list to remove them.
</div>

Examples:

* `fav 1` — Adds contact at index 1 to your list of favourites

![favourite](images/favourite_command.png)

* `fav 1` - Run the command for the same contact, and the favourite status will be toggled off.

![unfavourited](images/after_unfavourite_command.png)

Format in detailed view: `fav`

Example:

* `fav` Adds the currently viewed contact to your list of favourites.

### Adding high importance flag : `impt`

Adds the contact to your list of contacts with high importance and a red flag will appear beside the contact's name to indicate that.
This command can be used in detailed view.

Format: `impt INDEX`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
When a red flag appears beside the contact's name, you can run `impt INDEX` again where `INDEX` is the index of a contact that currently belongs in your list of contacts with high importance to remove them.
</div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You may wish to use the `note` command to add a note to indicate why the contact is important. E.g. Mobility Issues.
</div>

Examples:

* `impt 1` - Adds contact at index 1 to your list of contacts with high importance, indicated by the red flag

![important](images/high-importance-flag/add_importance_flag.png)

You will remove the red flag beside the contact's name after entering `impt 1` again.

![not_important](images/high-importance-flag/before_command.png)

`note 1 r/Mobility Issues` - Adds a note for your contact at index 1 indicating he/she has mobility issues

![note_usage](images/high-importance-flag/add_note_for_reason.png)

Format in detailed view: `impt`

Example:

* `impt` Adds the currently viewed contact to your list of contacts with high importance.

### Adding deadlines to meet in relation to a contact : `deadline`

Creates a deadline that is placed under the profile of a contact. This command can be used in detailed view.

Format: `deadline INDEX d/DESCRIPTION DATE [d/DESCRIPTION DATE]...`

* Deadline must have description.
* The given date is added to the contact as deadline.
* Date should be dd/mm/yyyy

Example:

* `deadline 1 d/windows 01/01/2022` adds a deadline with description `windows` and date `01/01/2022` to the contact in index `1`.

List before `deadline` command:

![before 'deadline 1 d/windows 01/01/2022'](images/BeforeDeadlineCommand.png)

List after `deadline` command:

![after 'deadline 1 d/windows 01/01/2022'](images/AfterDeadlineCommand.png)

Format in detailed view: `deadline d/DESCRIPTION DATE [d/DESCRIPTION DATE]...`

Example:

* `deadline d/Lunch meeting 03/06/2022` adds a deadline with description `Lunch meeting` and date `03/06/2022` to the
  currently viewed contact.

Interface before `deadline` command in detailed view mode:

![before 'deadline d/Lunch meeting 03/06/2022'](images/BeforeDetailedViewDeadlineCommand.png)

Interface after `deadline` command in detailed view mode:

![after 'deadline d/Lunch meeting 03/06/2022'](images/AfterDetailedViewDeadlineCommand.png)

### Deleting a deadline from a contact : `deldl`

Deletes the deadline under the contact in detailed view. This command cannot be used in list view.

Format: `deldl INDEX`

* Deletes the note at the index of the list of deadlines displayed.

Example:

* `view 2` shows you the detailed view of  the contact at index 2, then using `deldl 2` will delete the second deadline in the
  notes list of the contact

Interface before `deldl` command:

![before 'deldl 2'](images/BeforeDeleteDeadlineCommand.png)

Interface after `deldl` command:

![after 'deldl 2'](images/AfterDeleteDeadlineCommand.png)

### Adding additional notes to a contact : `note`

Adds the given note under the contact. This command can be used in detailed view.

Format: `note INDEX r/NOTES`

* Notes are displayed in a list.
* The given note is appended to the existing list of notes at the end.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Notes store good-to-know information about the user. To classify contacts so that you can search for them, use tags instead.
</div>

Example:

* `note 2 r/loves green` will add a note under the contact at index 2 that reads `loves green`.

Format in detailed view: `note r/NOTES`

Example:

* `note r/Likes wood furniture` will add a note to currently viewed contact that reads `Likes wood furniture`.

### Deleting notes from a contact : `delnote`

Deletes the note under the contact in detailed view. This command cannot be used in list view.

Format: `delnote INDEX`

* Deletes the note at the index of the list of notes displayed.

Example:

* `view 1` shows you the detailed view of  the contact at index 1, then using `delnote 2` will delete the second note in the
  notes list of the contact

### Adding images : `addimg`

Add image(s) to a contact.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Take note that images uploaded are duplicated and stored in the data folder of the app.
If your computer's storage space is a concern for you, please delete the original image
after you have uploaded it!
</div>

Format: `addimg INDEX`

![file chooser for images](images/images_file_chooser.png)

* Upon running the command, a file chooser will appear for you to select images from.
* Images can be in `.png` or `.jpg` formats.
* Images uploaded cannot have duplicate names.

### List contact's images : `images`

Lists the contact's image(s).

Format: `images INDEX`

* You can click on the images to get a focused view of the image.

### Deleting images : `delimg`

Deletes the image(s) associated with a contact.

Format: `delimg INDEX i/IMAGE_INDEX`

![identify image index from images command](images/image_index.png)

* An image's index is relative to the person it belongs to.
* You can identify it by running the images command for a given user
  (as seen in the above image). The `IMAGE_INDEX` of the image will be
  directly above the image itself.

Example:

`delimg 1 i/2` will delete the second image of the contact

### Clearing all entries : `clear`

Clears all entries from the address book. This command can **only be used in list view**.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
There is no warning if you run this command, and there is **no way to recover the deleted data**. Ensure you intend to 
run this command. 

It is recommended to use this command **only for clearing the sample data provided in the beginning**.
</div>

Format: `clear`

### Navigating your contact list

As your contact list grows larger, you may start having trouble finding the contact you are looking for.
However, with these commands to aid you, finding contacts will still be easy and intuitive.

### Listing Favourites : `favourites`

Lists all your favourite contacts to the list of displayed contacts. This command **only works in list view**.

Format: `favourites`

### Listing contacts with high importance : `impts`

Shows you all contact(s) with high importance, tagged with the red flag. This command **only works in list view**.

Format: `impts`

Examples:

* You can enter `impt 1` followed by `impt 3` to add a red flag beside contacts at index 1 and index 3.

  ![command_result](images/high-importance-flag/command_result.png)

* Use `impts` to list all of your contacts that has been tagged with the red flag.

![impts_command_result](images/high-importance-flag/impts_command_result.png)

### Prioritising relevant contacts to you : `sort`

Sort contacts by given criteria. This command **only works in list view**.

Format: `sort CRITERIA`

* `CRITERIA` should be written in lower-case.

| `CRITERIA` | Order                                                |
|------------|------------------------------------------------------|
| `address`  | Alphabetically sorted                                |
| `deadline` | Early dates to later dates                           |
| `email`    | Alphabetically sorted                                |
| `fav`      | Favourited contacts to not favourited contacts       |
| `impt`     | HighImportant contacts to not HighImportant contacts |
| `name`     | Alphabetically sorted                                |
| `phone`    | Numerically sorted                                   |

### Locating contacts by name : `find`

Find contacts whose names contain any of the given keywords. This command **only works in list view**.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* **Only the name is searched**.
* **Only full words will be matched** e.g. `Han` will not match `Hans`
* Contacts matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`
  , `Bo Yang`

Examples:

* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Locating contacts by tag : `findtag`

Find contacts based on the selected tags given by keywords to search for. This command **only works in list view**.

Format: `findtag KEYWORD [MORE_KEYWORDS]`

* Each `findtag` command selects a `TAG` to be found by using the given `KEYWORD`
  e.g.

  * `findtag friends` adds `friends` as a tag to be searched for
  * `findtag colleagues` adds `colleagues` to pre-existing search, now containing both colleagues and friends
* The search is case-insensitive. e.g `tag` will match `Tag`
* **Only the tag is searched**
* **Only full words will be matched** e.g. `Ta` will not match `Tag`
* List of contacts matching at least the searched tag\(s\) will be returned. e.g. `Tag1` will return `Contact` A with
  tags `Tag1` and `Tag2`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Use `list` to clear currently selected tags!
</div>

Examples:

* `findtag Friends` returns contacts with tag `Friends`
  ![result findtag Friends](images/findtag/findtag-friends.png)
* `findtag Friends` followed by `findtag InProgress AlmostFinished` returns contacts tagged by at least `Friends`, `InProgress` and `AlmostFinished`
  ![result findtag InProgress AlmostFinished](images/findtag/findtag-friends-inprogress-almostfinished.png)

### Saving the data

d'Intérieur data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

d'Intérieur data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to
update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, d'Intérieur will discard all data and start with an empty data file at the next run.
</div>

### Cycling through input history

You can cycle through your input history by hitting the up arrow key to go back to older inputs,
and the down arrow key to your latest inputs. You can save time on typing repeat inputs, or re-writing erroneous
inputs!

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous d'Intérieur home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Empty entries mean the commands cannot be used in the view.

| Action              | Format, Examples in List View                                                                                                                   | Format, Examples in Detailed View                                                                      |
|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| **Add**             | `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]…​` <br> e.g., `add n/Mary Jane p/12345678 e/maryJ@example.com a/Bukit Timah t/completed` | Same as list view                                                                                      |
| **Add Image**       | `addimg INDEX` <br> e.g., `addimg 1`                                                                                                            | `addimg`                                                                                               |
| **Assign Tag**      | `assign INDEX TAGNAME` <br> e.g., `assign 1 Friends`                                                                                            | `assign TAGNAME` <br> e.g., `assign client`                                                            |
| **Clear**           | `clear`                                                                                                                                         | -                                                                                                      |
| **Create Tag**      | `tag TAGNAME` <br> e.g., `tag Friends`                                                                                                          | Same as list view                                                                                      |
| **Deadline**        | `deadline INDEX d/DESCRIPTION DATE [d/DESCRIPTION DATE]...` <br> e.g., `deadline 1 d/windows 01/01/2022`                                        | `deadline d/DESCRIPTION DATE [d/DESCRIPTION DATE]...` <br> e.g., `deadline d/Lunch meeting 03/06/2022` |
| **Delete**          | `delete INDEX`<br> e.g., `delete 3`                                                                                                             | -                                                                                                      |
| **Delete Deadline** | -                                                                                                                                               | `deldl INDEX` <br> e.g. `deldl 2`                                                                      |
| **Delete Image**    | `delimg INDEX i/IMAGE_INDEX` <br> e.g., `delimg 1 i/2`                                                                                          | `delimg i/IMAGE_INDEX` <br> e.g., `delimg i/1`                                                         |
| **Delete Note**     | -                                                                                                                                               | `delnote INDEX` <br> e.g. `delnote 2`                                                                  |
| **Delete Tag**      | `deltag TAGNAME`<br> e.g., `delete friends`                                                                                                     | -                                                                                                      |
| **Edit**            | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] …​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                            | `edit [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]…​` <br> e.g., `edit p/88438809 e/alex_yeoh@example.com` |
| **Fav**             | `fav INDEX` <br> e.g., `fav 1`                                                                                                                  | `fav`                                                                                                  |
| **Find**            | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                      | -                                                                                                      |
| **Find Tag**        | `findtag KEYWORD [MORE_KEYWORDS}` <br> e.g., `findtag Friends`                                                                                  | -                                                                                                      |
| **Help**            | `help`                                                                                                                                          | Same as list view                                                                                      |
| **Impt**            | `impt INDEX` <br> e.g., `impt 1`                                                                                                                | `impt`                                                                                                 |
| **Impts**           | `impts`                                                                                                                                         | -                                                                                                      |
| **Sort**            | `sort CRITERIA` <br> e.g., `sort address`                                                                                                       | -                                                                                                      |
| **List**            | `list`                                                                                                                                          | Same as list view                                                                                      |
| **List Favourites** | `favourites`                                                                                                                                    | -                                                                                                      |
| **List Images**     | `images INDEX` <br> e.g., `images 1`                                                                                                            | `images`                                                                                               |
| **Note**            | `note INDEX r/NOTES`<br> e.g. `note 2 r/loves green`                                                                                            | `note r/NOTES` <br> e.g., `note r/Likes wood furniture`                                                |
| **Unassign Tag**    | `unassign INDEX TAGNAME` <br> e.g., `unassign 1 Friends`                                                                                        | `unassign TAGNAME` <br> e.g., `unassign client`                                                        |
| **View**            | `view INDEX` <br> e.g., `view 1`                                                                                                                | -                                                                                                      |
