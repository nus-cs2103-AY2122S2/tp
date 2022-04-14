package seedu.address.commons.core;

/**
 * Container for user visible messages regarding the manual commands.
 */

public class ManualMessages {

    public static final String MANUAL_MESSAGE_ADD_COMMAND = "Adds a student to TAPA. \n"
            + "Format: add i/STUDENT_ID n/STUDENT_NAME m/MODULE_CODE [p/PHONE_NUMBER] [t/TELEGRAM_HANDLE] "
            + "[e/EMAIL_ADDRESS] \n"
            + "Example: add i/A6942069R n/john m/CS2103T p/98765432 t/johnnn e/e0123456@u.nus.edu";

    public static final String MANUAL_MESSAGE_DELETE_COMMAND = "Deletes a student from TAPA. \n"
            + "Format: delete STUDENT_INDEX \n"
            + "Example: delete 1";

    public static final String MANUAL_MESSAGE_DELETE_MODULE_COMMAND = "Deletes all students identified "
            + "by the module code inputted. \n"
            + "Format: deleteModule m/MODULE_CODE \n"
            + "Example: deleteModule m/CS2100";

    public static final String MANUAL_MESSAGE_FIND_COMMAND = "Allows the user to look up the details of a particular "
            + "student. \n"
            + "Format: find n/STUDENT_NAME (or) find i/STUDENT_ID \n"
            + "Example: find n/John (or) find i/AXXXXXXXR";

    public static final String MANUAL_MESSAGE_EDIT_COMMAND = "Edits a student's information in TAPA. \n"
            + "Format : edit STUDENT_INDEX [i/STUDENT_ID] [n/STUDENT_NAME] [m/MODULE_CODE] [p/PHONE_NUMBER] "
            + "[t/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS] \n"
            + "Example: edit 1 m/CS2103T p/98765432 t/johnnn e/e0123456z@u.nus.edu";

    public static final String MANUAL_MESSAGE_CLEAR_COMMAND = "Clears all students from TAPA. "
            + "Additionally, the user will be prompted to confirm clear command. \n"
            + "*Warning* Clearing TAPA cannot be undone! \n"
            + "Format : clear \n"
            + "Example: clear";

    public static final String MANUAL_MESSAGE_LIST_COMMAND = "Displays all the students enrolled in a list. \n"
            + "Format : list \n"
            + "Example: list";

    public static final String MANUAL_MESSAGE_MANUAL_COMMAND = "Display the format for a specified command and a short"
            + " description for a particular command. \n"
            + "Format : manual [COMMAND_NAME] \n"
            + "Example: manual add \n"
            + "Kind of weird that you have to use 'manual'... to find out what 'manual' does... :( "
            + "Trying to find a bug?";

    public static final String MANUAL_MESSAGE_HELP_COMMAND = "Shows a pop-up window explaining how to access the user "
            + "guide. \n"
            + "Format : help \n"
            + "Example: help";

    public static final String MANUAL_MESSAGE_EXIT_COMMAND = "Exits the program. \n"
            + "Format : exit \n"
            + "Example: exit";

    public static final String MANUAL_MESSAGE_TASK_COMMAND = "Displays all the tasks that are allocated to a "
            + "particular student. \n"
            + "Format: task i/STUDENT_ID \n"
            + "Example: task i/A6942069R";

    public static final String MANUAL_MESSAGE_MARK_COMMAND = "Marks a specific undone task as done for a particular "
            + "student. \n"
            + "Format : mark i/STUDENT_ID idx/UNDONE_TASK_INDEX \n"
            + "Example: mark i/A6942069R idx/1";

    public static final String MANUAL_MESSAGE_UNMARK_COMMAND = "Marks a specific done task as undone for a particular "
            + "student. \n"
            + "Format : unmark i/STUDENT_ID idx/DONE_TASK_INDEX \n"
            + "Example: unmark i/A6942069R idx/1";

    public static final String MANUAL_MESSAGE_ARCHIVE_COMMAND = "Saves a copy of the details currently saved in the "
            + "address book into a separate file. \n"
            + "Format : archive \n"
            + "Example: archive";

    public static final String MANUAL_MESSAGE_ASSIGN_COMMAND = "Assigns a task to a particular student. \n"
            + "Format : assign i/STUDENT_ID tn/TASK_NAME \n"
            + "Example: assign i/A6942069R tn/Watch Lecture";

    public static final String MANUAL_MESSAGE_DELETE_TASK_COMMAND = "Deletes a task that was previously assigned. \n"
            + "Format: deleteTask i/STUDENT_ID idx/INDEX (or) deleteTask m/MODULE_CODE tn/TASK_NAME \n"
            + "Example: deleteTask i/A0123456Z idx/4 (or) deleteTask m/CS2030 tn/Assignment 1";

    public static final String MANUAL_MESSAGE_PROGRESS_COMMAND = "Displays the completion status of all students "
            + "who are taking a particular module and are assigned to a particular task. \n"
            + "Format : progress m/MODULE_CODE tn/TASK_NAME \n"
            + "Example: progress m/CS2100 tn/Assignment 1";

    public static final String MANUAL_MESSAGE_HISTORY_COMMAND = "Displays a list of previously executed commands. "
            + "(Undone commands will not be shown.) \n"
            + "Format : history \n"
            + "Example: history";

    public static final String MANUAL_MESSAGE_UNDO_COMMAND = "Reverts the effect of the most recently executed command "
            + "(except a \"clear\" command or another \"undo\" command). \n"
            + "Format : undo \n"
            + "Example: undo";

    public static final String MANUAL_MESSAGE_SORT_COMMAND = "Sorts the students in TAPA by the number of undone "
            + "tasks in descending order. \n"
            + "Format: sort \n"
            + "Example: sort";

    public static final String MANUAL_MESSAGE_ALL_COMMANDS = "Here are all the commands: \n"
            + "1. add \n"
            + "2. delete \n"
            + "3. deleteModule \n"
            + "4. find \n"
            + "5. task \n"
            + "6. mark \n"
            + "7. unmark \n"
            + "8. edit \n"
            + "9. clear \n"
            + "10. archive \n"
            + "11. list \n"
            + "12. assign \n"
            + "13. progress \n"
            + "14. deleteTask \n"
            + "15. history \n"
            + "16. undo \n"
            + "17. sort \n"
            + "18. manual \n"
            + "19. help \n"
            + "20. exit \n"
            + "Use manual [COMMAND_NAME] to display the format and a short description for the specified command";

    public static final String MANUAL_MESSAGE_UNKNOWN_COMMANDS = "Unknown command!";
}
