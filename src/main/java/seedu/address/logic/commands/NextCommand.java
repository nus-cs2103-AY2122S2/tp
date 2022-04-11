package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandRemark;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Invokes the next command that was typed in by user and auto-fill in the text box.
 */
public class NextCommand extends Command {

    public static final String COMMAND_WORD = "next";
    public static final String MESSAGE_ON_HISTORY_SUCCESS = "";
    public static final String MESSAGE_ON_NO_NEXT = "No next command available!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Invoke the next command \n"
            + "Example: " + COMMAND_WORD;

    public NextCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CommandList.getList().increasePointer();
        return new CommandResult(MESSAGE_ON_HISTORY_SUCCESS, CommandRemark.HISTORY);
    }

    /**
     * Checks if two NextCommand methods are equal.
     *
     * @param other The other NextCommand object.
     * @return Result of the check.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof NextCommand; //Any two NextCommand should be identical.
    }
}
