package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.FormatPersonUtil;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing person in the address book.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copy the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + " "
            + PREFIX_EMAIL + "\n";

    private final Index index;
    private final List<Prefix> prefixes;
    private final FormatPersonUtil formatPersonUtil;

    /**
     * Constructor for copy command.
     * @param index of the person in the filtered person list to edit
     * @param prefixes details to copy the person with
     * @param formatPersonUtil to format the person
     */
    public CopyCommand(Index index, List<Prefix> prefixes, FormatPersonUtil formatPersonUtil) {
        requireNonNull(index);
        requireNonNull(prefixes);
        requireNonNull(formatPersonUtil);

        this.index = index;
        this.prefixes = prefixes;
        this.formatPersonUtil = formatPersonUtil;
    }

    /**
     * Constructor for copy command
     * @param prefixes details to copy the person with
     * @param formatPersonUtil to format the person
     */
    public CopyCommand(List<Prefix> prefixes, FormatPersonUtil formatPersonUtil) {
        requireNonNull(prefixes);
        requireNonNull(formatPersonUtil);

        this.index = null;
        this.prefixes = prefixes;
        this.formatPersonUtil = formatPersonUtil;
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CopyCommand)) {
            return false;
        }

        // state check
        CopyCommand e = (CopyCommand) other;
        return index.equals(e.index)
                && prefixes.equals(e.prefixes);
    }

    @Override
    public CommandResult execute(Model model,
                                 CommandHistory commandHistory, StackUndoRedo undoRedoStack) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index == null) {
            try {
                String formattedAddressBook = formatPersonUtil.formatAddressBook(lastShownList, prefixes);
                return new CommandResult(formattedAddressBook, false, false, false, false, true);
            } catch (JsonProcessingException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
            }
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToCopy = lastShownList.get(index.getZeroBased());
        String copiedFields;
        try {
            copiedFields = formatPersonUtil.formatPerson(personToCopy, prefixes);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(copiedFields, false, false, false, false, true);
    }
}
