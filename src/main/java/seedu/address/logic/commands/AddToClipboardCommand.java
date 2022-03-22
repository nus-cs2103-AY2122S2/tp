package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javafx.collections.ObservableList;
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

    /**
     * Creates an AddToClipboardCommand to copy information of the person specified in {@code NameExistsPredicate}
     */
    public AddToClipboardCommand(NameExistsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        ObservableList<Person> filterResult = model.getFilteredPersonList();

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
