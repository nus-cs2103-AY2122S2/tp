package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.PERSON;

public class FindPersonCommand extends FindCommand {

    private PersonContainsKeywordsPredicate predicate;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " [p] : Finds all persons that meet search criteria.\n"
            + "Only accepts: g/, n/, p/, e/, a/, j/, and s/ flags\n"
            + "Example: " + COMMAND_WORD + " [p] g/n/john j/software engineer g/e/gmail.com";

    public FindPersonCommand(PersonContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size()), Type.PERSON);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindPersonCommand)) {
            return false;
        }

        // state check
        FindPersonCommand e = (FindPersonCommand) other;
        return predicate.equals(e.predicate);
    }

    @Override
    public Type getType() {
        return PERSON;
    }
}
