package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.commons.core.Messages;
import seedu.contax.model.Model;
import seedu.contax.model.person.TagNameContainsKeywordsPredicate;

/**
 * Finds and lists all tags in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindByTagCommand extends Command {
    public static final String COMMAND_WORD = "findbytag";

    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "` : **Finds all persons who "
            + "contains the specified tag.**\n"
            + "Parameters: *t/TAGNAME*\n"
            + "Example: `" + COMMAND_WORD + " t/friends`";

    private final TagNameContainsKeywordsPredicate predicate;

    public FindByTagCommand(TagNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size()), GuiListContentType.PERSON);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof FindByTagCommand)) {
            return false;
        }

        return ((FindByTagCommand) o).predicate.equals(predicate);
    }
}
