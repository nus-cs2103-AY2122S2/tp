package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.entity.EntityType;
import seedu.address.model.student.NameContainsKeywordsPredicate;

//@@author wxliong
/**
 * Finds and lists all students in TAssist whose name contains any of the argument keywords.
 * The search is case-insensitive. e.g hans will match Hans.
 * The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans.
 * Only the name is searched.
 * Only full words will be matched e.g. Han will not match Hans.
 * Students matching at least one keyword will be returned e.g. Hans Bo will return Hans Gruber, Bo Yang.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... "
            + "\n\tExample: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW,
                        model.getFilteredStudentList().size()), EntityType.STUDENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}

