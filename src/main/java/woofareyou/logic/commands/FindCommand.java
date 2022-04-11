package woofareyou.logic.commands;

import static java.util.Objects.requireNonNull;

import woofareyou.commons.core.Messages;
import woofareyou.model.Model;
import woofareyou.model.pet.NameContainsKeywordsPredicate;

/**
 * Finds and lists all pets in WoofAreYou whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all pets whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " PeePee Bagel Tofu";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPetList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PETS_FOUND, model.getFilteredPetList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
