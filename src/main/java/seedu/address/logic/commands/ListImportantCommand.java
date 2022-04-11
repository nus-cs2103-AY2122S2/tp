package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonHasHighImportancePredicate;

/**
 * Finds and lists all persons in address book who are of high importance contacts.
 */
public class ListImportantCommand extends Command {

    public static final String COMMAND_WORD = "impts";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts who have been set as a "
            + "contact with high importance and displays them as a list with index numbers.\n";

    private static final PersonHasHighImportancePredicate predicate = new PersonHasHighImportancePredicate();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearActivatedTagList();
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListImportantCommand); // instanceof handles nulls
    }
}
