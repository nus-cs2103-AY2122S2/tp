package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FriendFilterPredicate;

/**
 * Finds and lists all friends in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "findfriend";
    public static final String COMMAND_ALIAS = "ff";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " / " + COMMAND_ALIAS
            + ": Finds all friends whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: " + "[" + PREFIX_NAME + "NAME_KEYWORD" + "]... "
            + "[" + PREFIX_TITLE + "LOG_TITLE_KEYWORD" + "]... "
            + "[" + PREFIX_TAG + "TAG_KEYWORD" + "]... "
            + "Example: " + COMMAND_WORD + " n/alice n/bob n/charlie";

    private final FriendFilterPredicate predicate;

    public FindCommand(FriendFilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
