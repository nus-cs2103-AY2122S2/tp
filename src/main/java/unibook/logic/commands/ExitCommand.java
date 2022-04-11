package unibook.logic.commands;

import unibook.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting UniBook as requested ...";

    @Override
    public CommandResult execute(Model model, Boolean isPersonListShowing,
                                 Boolean isModuleListShowing, Boolean isGroupListShowing) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
