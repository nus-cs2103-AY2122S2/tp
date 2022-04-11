package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandRemark;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Invokes the previous command that was typed in by user and auto-fill in the text box.
 */
public class PreviousCommand extends Command {

    public static final String COMMAND_WORD = "previous";
    public static final String MESSAGE_ON_HISTORY_SUCCESS = "";
    public static final String MESSAGE_ON_NO_PREVIOUS = "No previous command available!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Invoke previously used command "
            + "Example: " + COMMAND_WORD;

    public PreviousCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CommandList.getList().decreasePointer();
        return new CommandResult(MESSAGE_ON_HISTORY_SUCCESS, CommandRemark.HISTORY);
    }

    /**
     * Checks if two PreviousCommand methods are equal.
     *
     * @param other The other PreviousCommand object.
     * @return Result of the check.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof PreviousCommand; //Any two HistoryCommands should be identical.
    }
}
