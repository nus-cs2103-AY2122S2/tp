package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
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

    public static final String MESSAGE_SUCCESS = "Added the following client's information to clipboard!";
    public static final String MESSAGE_FAILURE = "No such client found!";
    public static final String MESSAGE_NO_CLIPBOARD = "Environment has no clipboard!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the information of the client identified "
            + "by index or their full name (case-insensitive) to the user's clipboard. "
            + "If both an index and name is specified, the index will be used to select the contact to clip.\n"
            + "Parameters: index OR n/ Full name of intended contact\n"
            + "Example: " + COMMAND_WORD + " 1 OR "
            + COMMAND_WORD + " n/ John Doe";

    private static Logger logger = Logger.getLogger("AddToClipboardCommandLogger");
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
    public CommandResult execute(Model model) throws CommandException, HeadlessException {
        requireNonNull(model);
        ObservableList<Person> filterResult;
        NameExistsPredicate predicateToUse = this.predicate;

        if (this.predicate == null) {
            assert(this.targetIndex != null);
            //This instance of AddToClipboardCommand was created with a target index
            ObservableList<Person> personList = model.getFilteredPersonList();
            if (targetIndex.getZeroBased() >= personList.size()) {
                //No such contact at that index exists
                logger.log(Level.INFO, "Clip command failed, no contact at that index exists!");
                return new CommandResult(MESSAGE_FAILURE);
            }

            Person personToClip = personList.get(targetIndex.getZeroBased());
            predicateToUse = new NameExistsPredicate(personToClip.getName());
        }

        model.updateFilteredPersonList(predicateToUse);
        filterResult = model.getFilteredPersonList();

        if (filterResult.toString().equals("[]")) {
            //Filter returns nothing, no such contact with the given name exists
            logger.log(Level.INFO, "Clip command failed, no contact with that name exists!");
            return new CommandResult(MESSAGE_FAILURE);
        }

        //@@author jetrz-reused
        //Reused from https://stackoverflow.com/questions/6710350/copying-text-to-the-clipboard-using-java
        //with modifications
        //Filter returns a contact, copy that contact to clipboard
        try {
            StringSelection resultToString = new StringSelection(filterResult.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(resultToString, null);
        } catch (HeadlessException e) {
            logger.log(Level.INFO, "Clip command failed, environment has no clipboard!");
            return new CommandResult(MESSAGE_NO_CLIPBOARD);
        }
        logger.log(Level.INFO, "Clip command executed successfully!");
        return new CommandResult(MESSAGE_SUCCESS);
        //@@author
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
        assert (predicate == null || targetIndex == null);
        assert (e.predicate == null || e.targetIndex == null);
        if ((predicate == null && e.predicate != null) || (targetIndex == null && e.targetIndex != null)) {
            return false;
        } else if (predicate != null) {
            return predicate.equals(e.predicate);
        } else {
            return targetIndex.equals(e.targetIndex);
        }
    }
}
