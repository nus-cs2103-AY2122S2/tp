package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.unite.commons.core.Messages;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.person.PersonContainsTagPredicate;
import seedu.unite.model.tag.Tag;

/**
 * Filters the full person list with a tag
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the current contact list by the tags "
            + "attached to the contacts.\n"
            + "Parameters: TAG_NAME\n"
            + "Example: " + COMMAND_WORD + " family";

    public static final String MESSAGE_SUCCESS = "\n" + "To clear the filter, enter 'list'.";

    private final Tag tag;
    private final PersonContainsTagPredicate predicate;

    /**
     * @param predicate used to filter the current contact list
     */
    public FilterCommand(PersonContainsTagPredicate predicate, Tag tag) {
        this.predicate = predicate;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasTag(tag)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_NAME);
        }
        model.updateFilteredPersonList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size()) + MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        // state check
        FilterCommand e = (FilterCommand) other;
        return tag.equals(e.tag);
    }

}
