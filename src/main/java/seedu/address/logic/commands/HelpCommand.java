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
            + "1. add: Add User\n"
            + "2. clear: Clear List\n"
            + "3. delete: Delete User\n"
            + "4. edit: Edit User\n"
            + "5. exit: Exit Program\n"
            + "6. findactivity: Find User by Activity\n"
            + "7. findstatus: Find User by Covid-19 Status\n"
            + "8. help: Displays User Guide and commands to use\n"
            + "9. list: Lists all users";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
