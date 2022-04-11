package seedu.address.logic.commands;

import seedu.address.model.Model;

public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " [p] to list all applicants"
        + " or [i] to list all interviews" + " or [t] to list all tasks";

    @Override
    public abstract CommandResult execute(Model model);
}
