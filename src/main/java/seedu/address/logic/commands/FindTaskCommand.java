package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * finds a task to the system based on user input.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "findt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": finds a task based on keywords. \n"
            + "Format: findt [INPUT] \n"
            + "Example: " + COMMAND_WORD + " " + "Swimming lessons";

    public static final String MESSAGE_ARGUMENTS = "Here are the matching tasks in the list:\n" + "%1$s";

    public static final String MESSAGE_NO_DESCRIPTION = "Unable to find any matching tasks due to empty keyword!";

    public static final String MESSAGE_NO_MATCHING_TASK = "Unable to find any matching tasks based on input!";

    private final String input;

    /**
     * Initializes a FindTaskCommand with the given input.
     */
    public FindTaskCommand(String input) {
        requireNonNull(input);
        this.input = input;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.input.equals("")) {
            throw new CommandException(MESSAGE_NO_DESCRIPTION + "\n" + MESSAGE_USAGE);
        }
        try {
            String orderedList = model.findTask(input);

            if (orderedList.isEmpty()) {
                throw new CommandException(MESSAGE_NO_MATCHING_TASK);
            }
            return new CommandResult(String.format(MESSAGE_ARGUMENTS, orderedList));
        } catch (CommandException e) {
            throw new CommandException(MESSAGE_NO_MATCHING_TASK);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindTaskCommand)) {
            return false;
        }

        //state check
        FindTaskCommand e = (FindTaskCommand) other;
        return input.equals(e.input);
    }


}

