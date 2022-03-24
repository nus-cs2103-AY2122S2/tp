package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name, student id or module code
 * matches any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose details match "
            + "the specified search term (ie. name, matriculation number or module code) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_NAME + "STUDENT_NAME " + "or " + PREFIX_ID + "STUDENT_ID " + "or "
            + PREFIX_MODULE_CODE + "MODULE_CODE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John " + "or " + COMMAND_WORD + " "
            + PREFIX_ID + "A0123456Z " + "or " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS2103T\n";

    private final NameContainsKeywordsPredicate namePredicate;
    private final StudentIdContainsKeywordsPredicate idPredicate;
    private final ModuleCodeContainsKeywordsPredicate modCodePredicate;

    /**
     * Creates a FindCommand to find the specified {@code Person} using the person's name.
     */
    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.namePredicate = predicate;
        this.idPredicate = null;
        this.modCodePredicate = null;
    }

    /**
     * Creates a FindCommand to find the specified {@code Person} using the person's student id.
     */
    public FindCommand(StudentIdContainsKeywordsPredicate predicate) {
        this.idPredicate = predicate;
        this.namePredicate = null;
        this.modCodePredicate = null;
    }

    /**
     * Creates a FindCommand to find the specified {@code Person} using the person's module code.
     */
    public FindCommand(ModuleCodeContainsKeywordsPredicate predicate) {
        this.modCodePredicate = predicate;
        this.namePredicate = null;
        this.idPredicate = null;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (namePredicate != null) { // student name was used for the command
            model.updateFilteredPersonList(namePredicate);
        } else if (idPredicate != null) { // student id was used for the command
            model.updateFilteredPersonList(idPredicate);
        } else { // module code was used for the command
            model.updateFilteredPersonList(modCodePredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        boolean isEquals = false;
        if (other == this) { // short circuit if same object
            isEquals = true;
        } else {
            boolean isInstanceOf = other instanceof FindCommand;
            if (!isInstanceOf) { // instanceof handles nulls
                return false;
            }
            FindCommand commandToCompare = (FindCommand) other;
            // only idPredicate present
            if (this.namePredicate == null && this.idPredicate != null && this.modCodePredicate == null) {
                isEquals = idPredicate.equals(commandToCompare.idPredicate); // state check
            }
            // only namePredicate present
            if (this.idPredicate == null && this.namePredicate != null && this.modCodePredicate == null) {
                isEquals = namePredicate.equals(commandToCompare.namePredicate); // state check
            }
            // only modCodePredicate present
            if (this.idPredicate == null && this.namePredicate == null && this.modCodePredicate != null) {
                isEquals = modCodePredicate.equals(commandToCompare.modCodePredicate); // state check
            }
        }
        return isEquals;
    }
}
