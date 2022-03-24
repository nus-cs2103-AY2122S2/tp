package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays previously used commands.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_SUCCESS = "Displaying previously used commands ...";

    //TODO: Complete implementation of history command
    @Override
    public CommandResult execute(Model model) {
        //requireNonNull(model);

        //CommandList commandList = model.getCommandList();


        return new CommandResult(String.format(MESSAGE_SUCCESS));
        //return new CommandResult(String.format(MESSAGE_SUCCESS, commandHistory.display()));
    }


}
