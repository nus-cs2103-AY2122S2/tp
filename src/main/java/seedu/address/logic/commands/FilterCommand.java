package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsTagPredicate;
import seedu.address.model.tag.Tag;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the current contact list by the tags "
            + "attached to the contacts.\n"
            + "Parameters: TAG_NAME\n"
            + "Example: " + COMMAND_WORD + " family";

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
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
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
