package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command implements DetailedViewExecutable {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearDetailedContactView();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.clearActivatedTagList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult executeInDetailedView(Model model) {
        return execute(model);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
