package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Finds and lists all data in the address book which satisfies the search criteria.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    @Override
    public abstract CommandResult execute(Model model);

    @Override
    public abstract boolean equals(Object other);
}
