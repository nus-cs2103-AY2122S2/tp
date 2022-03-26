package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Arrays;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the hustle book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their name in the displayed person list.\n"
            + "Parameters: Name (Alphanumerical characters and spaces only)\n"
            + "Example: " + COMMAND_WORD + " John Doe";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_MULTIPLE_PERSON = "More than 1 person exists with that name. Please look at the "
            + "list below and enter the index of the client you wish to delete \n"
            + "Example: 1, 2, 3 ...";

    private final Name targetName;
    private final String targetNameStr;
    private int index = 0;


    /**
     * @param targetName name of the person in the filtered person list to delete
     */
    public DeleteCommand(Name targetName) {
        requireNonNull(targetName);

        this.targetName = targetName;
        this.targetNameStr = targetName.fullName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Copied code
        FilteredList<Person> lastShownList = (FilteredList<Person>) model.getFilteredPersonList();
        Index targetIndex;
        if (index == 0) {
            FilteredList<Person> tempList = new FilteredList<Person>(lastShownList);
            String[] nameKeywords = targetNameStr.split("\\s+");
            Predicate<Person> predicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
            tempList.setPredicate(predicate);

            if (tempList.size() > 1) {
                lastShownList.setPredicate(predicate);
                return new CommandResult(MESSAGE_MULTIPLE_PERSON);
            }
            targetIndex = model.getPersonListIndex(targetName);
        } else {
            targetIndex = Index.fromOneBased(index);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_NAME);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetName.equals(((DeleteCommand) other).targetName)); // state check
    }
}
