package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Deletes all students identified using the inputted module code from the address book.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "deleteModule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all students identified by the module code inputted.\n"
            + "Parameters: " + PREFIX_MODULE_CODE + "MODULE_CODE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS2100\n";

    public static final String MESSAGE_DELETE_MULTIPLE_PERSONS_SUCCESS = "%s student(s) deleted.";
    public static final String MESSAGE_NONEXISTENT_MODULE_CODE = "There are no students "
            + "with the specified module code.";

    private final ModuleCodeContainsKeywordsPredicate modCodePredicate;

    /**
     * Creates a DeleteModuleCommand to delete {@code Person}s specified by the inputted module code.
     */
    public DeleteModuleCommand(ModuleCodeContainsKeywordsPredicate predicate) {
        this.modCodePredicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert this.modCodePredicate != null;
        int deletedCount = 0;

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        model.updateFilteredPersonList(this.modCodePredicate);
        if (model.getFilteredPersonList().size() == 0) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_NONEXISTENT_MODULE_CODE);
        }

        while (model.getFilteredPersonList().size() > 0) { // students with specified module code exist
            Person personToDelete = lastShownList.get(Index.fromZeroBased(0).getZeroBased());
            model.deletePerson(personToDelete);
            deletedCount++;
            model.updateFilteredPersonList(this.modCodePredicate);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_MULTIPLE_PERSONS_SUCCESS, deletedCount));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else {
            boolean isInstanceOf = other instanceof DeleteModuleCommand;
            if (!isInstanceOf) { // instanceof handles nulls
                return false;
            }
            DeleteModuleCommand commandToCompare = (DeleteModuleCommand) other;
            return this.modCodePredicate.equals((commandToCompare).modCodePredicate);
        }
    }
}
