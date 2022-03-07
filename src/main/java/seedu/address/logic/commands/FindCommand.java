package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain "
            + "the specified search term or matriculation number and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_NAME + "STUDENT_NAME " + "or " + PREFIX_ID + "STUDENT_ID \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John " + "or " + COMMAND_WORD + " "
            + PREFIX_ID + "A0123456Z \n";

    private final NameContainsKeywordsPredicate namePredicate;
    private final StudentIdContainsKeywordsPredicate idPredicate;

    /**
     * Creates a FindCommand to find the specified {@code Person} using the person's name.
     */
    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.namePredicate = predicate;
        this.idPredicate = null;
    }

    /**
     * Creates a FindCommand to find the specified {@code Person} using the person's student id.
     */
    public FindCommand(StudentIdContainsKeywordsPredicate predicate) {
        this.idPredicate = predicate;
        this.namePredicate = null;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (namePredicate != null) { // student name was used for the command
            model.updateFilteredPersonList(namePredicate);
        } else { // student id was used for the command
            model.updateFilteredPersonList(idPredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && namePredicate.equals(((FindCommand) other).namePredicate)); // state check
    }
}
