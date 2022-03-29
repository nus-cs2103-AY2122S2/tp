---
layout: page
title: Developer Guide
---
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Start up**

On start up the Application should open with the friends tabs selected by default, and all the friends in storage being displayed if any. An image of this is shown below.

![Startup.png](images/GUITestImages/Startup.png)

## **Clearing Address Book**

To start fresh with a new clean version of Amigos the `clear` command needs to be typed in the CommandBox as shown below. This should update the application to have no events and friends and also switch to the friends tab.

|                       Before                       |                        After                         |
|:--------------------------------------------------:|:----------------------------------------------------:|
| ![PreClear.png](images/GUITestImages/PreClear.png) | ![PostClear.png](images/GUITestImages/PostClear.png) |

## **Commands**

### Friends

Do note that after the execution of any friends related command, the GUI should switch to the `Friends` tab

#### Add Friend

Upon successful adding of a friend

1. Correct Command result display message
2. Friend should be added and should be ordered by name

|                        Before                        |                       After                        |
|:----------------------------------------------------:|:--------------------------------------------------:|
| ![BeforeAdd.png](images/GUITestImages/BeforeAdd.png) | ![AfterAdd.png](images/GUITestImages/AfterAdd.png) |

As you can see in the above images `Naaman` was inserted in the correct order with the appropriate command result message displayed in the CommandResult box.

If incorrect format of add command is entered the appropriate add friend error message should be displayed in the CommandResult box. Shown below is the error message in the CommandResultBox.

![PreClear.png](images/GUITestImages/AddError.png)

#### Edit a friend

Upon successful editing of a friend

1. Correct Command result display message
2. Friend should be edited and if name is changed, list should be reordered

|                       Before                       |                      After                       |
|:--------------------------------------------------:|:------------------------------------------------:|
| ![BeforeEdit](images/GUITestImages/BeforeEdit.png) | ![AfterEdit](images/GUITestImages/AfterEdit.png) |

As you can see in the above images `Naaman` was edited to `Bean` and the list was reordered. The appropriate command result message displayed was also in the CommandResult box.

If incorrect format of edit command is entered the appropriate edit friend error message should be displayed in the CommandResult box. Shown below is the error message in the CommandResultBox.

![EditError](images/GUITestImages/EditError.png)

#### Deleting a friend

Upon successful deleting of a friend

1. Correct Command result display message
2. Friend card should be removed from the GUI

|                         Before                         |                          After                           |
|:------------------------------------------------------:|:--------------------------------------------------------:|
| ![BeforeDelete](images/GUITestImages/BeforeDelete.png) | ![AfterDelete.png](images/GUITestImages/AfterDelete.png) |

As you can see in the above images `Bean` was deleted with the appropriate command result message displayed in the CommandResult box.

If incorrect format of delete command is entered the appropriate delete friend error message should be displayed in the CommandResult box. Shown below is the error message in the CommandResultBox.

![DeleteError](images/GUITestImages/DeleteError.png)

#### Show a specific friend

Upon successful execution of `showfriend` command the following must occur

1. Correct Command result display message
2. Expanded Friend card of friend requested should be displayed

|                             Before                             |                            After                             |
|:--------------------------------------------------------------:|:------------------------------------------------------------:|
| ![BeforeShowFriend](images/GUITestImages/BeforeShowFriend.png) | ![AfterShowFriend](images/GUITestImages/AfterShowFriend.png) |

As you can see in the above images `Bean` was deleted with the appropriate command result message displayed in the CommandResult box.

If incorrect format of delete command is entered the appropriate delete friend error message should be displayed in the CommandResult box. Shown below is the error message in the CommandResultBox.

![ShowFriendError](images/GUITestImages/ShowFriendError.png)

#### Show all friends

Upon successful execution of `showfriends` command the following must occur

1. Friends tab is selected and displayed
2. All the friends in Amigos are displayed

|                              Before                              |                             After                              |
|:----------------------------------------------------------------:|:--------------------------------------------------------------:|
| ![BeforeShowFriends](images/GUITestImages/BeforeShowFriends.png) | ![AfterShowFriends](images/GUITestImages/AfterShowFriends.png) |

#### Find a friend

Upon successful execution of `findfriend` command the following must occur

1. Correct Command result display message
2. Any friends who match the search criteria are displayed

|                             Before                             |                            After                             |
|:--------------------------------------------------------------:|:------------------------------------------------------------:|
| ![BeforeFindFriend](images/GUITestImages/BeforeFindFriend.png) | ![AfterFindFriend](images/GUITestImages/AfterFindFriend.png) |

As you can see in the above images `findfriend dio` was executed and since `Dione` matches, her card is displayed.

### Events

Do note that after the execution of any events related command, the GUI should switch to the `Events` tab

#### Add Event

#### Edit an event

#### Deleting an event

#### Show all events

#### Find an event

### Logs

#### Add Log

#### Edit log

#### Delete a log

### Help command

### Exit command





## **Stress tests**

### Large number of Friends


### Large number of Events


### Large number of logs



