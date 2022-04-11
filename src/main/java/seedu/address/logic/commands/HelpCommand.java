package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Command: Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window, meanwhile here are the commands "
            + "available for your use! \n"
            + "1. add: Adds a new User\n"
            + "    Format: add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS cc/CLASS s/STATUS [act/ACTIVITIES] "
            + "[act/MORE ACTIVITIES]\n"
            + "2. clear: Clears List\n"
            + "3. delete: Deletes a Student by their Index\n"
            + "    Format: delete INDEX\n"
            + "4. edit: Edit User\n"
            + "    Format: edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [cc/CLASS] [s/STATUS] "
            + "[act/ACTIVITIES] [act/MORE ACTIVITIES]\n"
            + "5. list: Lists all users\n"
            + "6. findactivity: Find Students by their Activity\n"
            + "    Format: findactivity ACTIVITY [MORE ACTIVITIES]\n"
            + "7. findstatus: Find Students by their COVID-19 Status\n"
            + "    Format: findstatus STATUS\n"
            + "8. findclasscode: Find Students by their class\n"
            + "    Format: findclasscode CLASS\n"
            + "9. help: Displays User Guide and commands to use\n"
            + "10. exit: Exit Program";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false);
    }
}
