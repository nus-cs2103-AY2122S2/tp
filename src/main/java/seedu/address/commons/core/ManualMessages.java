package seedu.address.commons.core;

/**
 * Container for user visible messages regarding the manual commands.
 */

public class ManualMessages {

    public static final String MANUAL_MESSAGE_ADD_COMMAND = "Adds a student to TAPA. \n"
            + "Format: add i/STUDENT_ID n/STUDENT_NAME m/MODULE_CODE [p/PHONE_NUMBER] [h/TELEGRAM_HANDLE] "
            + "[e/EMAIL_ADDRESS] \n"
            + "Example: add i/A6942069R n/john m/CS2103T p/98765432 t/johnnn e/e0123456@u.nus.edu";

    public static final String MANUAL_MESSAGE_DELETE_COMMAND = "Deletes a student from TAPA. \n"
            + "Format: delete STUDENT_INDEX \n"
            + "Example: delete 1";

    public static final String MANUAL_MESSAGE_FIND_COMMAND = "Allows the user to look up the details of a particular "
            + "student. \n"
            + "Format: find n/STUDENT_NAME (or) find i/STUDENT_ID \n"
            + "Example: find n/John (or) find i/AXXXXXXXR";

    public static final String MANUAL_MESSAGE_EDIT_COMMAND = "Edits a student's information in TAPA. \n"
            + "Format : edit STUDENT_INDEX [i/STUDENT_ID] [n/STUDENT_NAME] [m/MODULE_CODE] [p/PHONE_NUMBER] "
            + "[h/TELEGRAM_HANDLE] [e/EMAIL_ADDRESS] \n"
            + "Example: edit 1 m/CS2103T p/98765432 t/johnnn e/e0123456z@u.nus.edu";

    public static final String MANUAL_MESSAGE_CLEAR_COMMAND = "Clears all students from TAPA. "
            + "Additionally, the user will be prompted to confirm clear command. \n"
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
            + "Format : mark i/STUDENT_ID UNDONE_TASK_INDEX \n"
            + "Example: mark i/A6942069R 1";

    public static final String MANUAL_MESSAGE_UNMARK_COMMAND = "Marks a specific done task as undone for a particular "
            + "student. \n"
            + "Format : unmark i/STUDENT_ID DONE_TASK_INDEX \n"
            + "Example: unmark i/A6942069R 1";

    public static final String MANUAL_MESSAGE_ARCHIVE_COMMAND = "Saves a copy of the details currently saved in the "
            + "address book into a separate file. \n"
            + "Format : archive \n"
            + "Example: archive";

    public static final String MANUAL_MESSAGE_ASSIGN_COMMAND = "Assigns a task to a particular student. \n"
            + "Format : assign i/STUDENT_ID tn/TASK_NAME \n"
            + "Example: assign i/A6942069R tn/Watch Lecture";

    public static final String MANUAL_MESSAGE_PROGRESS_COMMAND = "Displays the completion status of all students "
            + "who are taking a particular module and are assigned to a particular task. \n"
            + "Format : progress m/MODULE_CODE tn/TASK_NAME \n"
            + "Example: progress m/CS2100 tn/Assignment 1";

        public static final String MANUAL_MESSAGE_HISTORY_COMMAND = "Displays a list of previously executed commands. \n"
            + "Format : history \n"
            + "Example: history";

    public static final String MANUAL_MESSAGE_ALL_COMMANDS = "Here are all the commands: \n"
            + "1. add \n"
            + "2. delete \n"
            + "3. find \n"
            + "4. task \n"
            + "5. mark \n"
            + "6. unmark \n"
            + "7. edit \n"
            + "8. clear \n"
            + "9. archive \n"
            + "10. list \n"
            + "11. assign \n"
            + "12. progress \n"
            + "13. history \n"
            + "14. manual \n"
            + "15. help \n"
            + "16. exit \n"
            + "Use 'manual [COMMAND_NAME] to display the format and a short description for the specified command";

    public static final String MANUAL_MESSAGE_UNKNOWN_COMMANDS = "Unknown command!";
}
