package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameExistsPredicate;

/**
 * Copies the information of a given contact to the user's clipboard.
 */
public class AddToClipboardCommand extends Command {
    public static final String COMMAND_WORD = "clip";

    public static final String MESSAGE_SUCCESS = "Added the following contact's information to clipboard!";
    public static final String MESSAGE_FAILURE = "No such contact found!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the information of the person identified "
            + "by their full name (case-insensitive) to the user's clipboard.\n"
            + "Parameters: n/ Full name of intended contact\n"
            + "Example: " + COMMAND_WORD + " n/ John Doe ";

    private final NameExistsPredicate predicate;
    private final Index targetIndex;

    /**
     * Creates an AddToClipboardCommand to copy information of the person specified in {@code NameExistsPredicate}
     */
    public AddToClipboardCommand(NameExistsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.targetIndex = null;
    }

    /**
     * Creates an AddToClipboardCommand to copy information of the person at the specified {@code targetIndex}.
     */
    public AddToClipboardCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.predicate = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> filterResult;

        if (this.targetIndex == null) {
            //This instance of AddToClipboardCommand was created with a name keyword
            model.updateFilteredPersonList(predicate);
            filterResult = model.getFilteredPersonList();
        } else if (this.predicate == null) {
            //This instance of AddToClipboardCommand was created with a target index
            ObservableList<Person> personList = model.getFilteredPersonList();
            if (targetIndex.getZeroBased() >= personList.size()) {
                //No such contact at that index exists
                return new CommandResult(MESSAGE_FAILURE);
            }

            Person personToClip = personList.get(targetIndex.getZeroBased());
            model.updateFilteredPersonList(new NameExistsPredicate(personToClip.getName()));
            filterResult = model.getFilteredPersonList();
        } else {
            //Every instance of AddToClipboardCommand should have either a name or index key
            System.out.println("Execution should not reach here. "
                    + "An AddToClipboardCommand should not have both a targetIndex and a NameExistsPredicate. "
                    + "Please contact a developer to fix this problem.");
            throw new CommandException(Messages.MESSAGE_SERIOUS_ERROR);
        }

        if (filterResult.toString().equals("[]")) {
            //Filter returns nothing, no such contact with the given name exists
            return new CommandResult(MESSAGE_FAILURE);
        } else {
            //Filter returns a contact, copy that contact to clipboard
            StringSelection resultToString = new StringSelection(filterResult.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(resultToString, null);
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddToClipboardCommand)) {
            return false;
        }

        // state check
        AddToClipboardCommand e = (AddToClipboardCommand) other;
        return predicate.equals(e.predicate);
    }
}
