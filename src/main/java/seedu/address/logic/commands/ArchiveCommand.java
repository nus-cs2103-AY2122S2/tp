package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ArchiveCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "archive";
    public static final String ALT_COMMAND_WORD = "unarchive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Archives the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer and less than 2,147,483,647)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String ALT_MESSAGE_USAGE = ALT_COMMAND_WORD
            + ": Unarchives the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer and less than 2,147,483,647)\n"
            + "Example: " + ALT_COMMAND_WORD + " 1";


    public static final String MESSAGE_ARCHIVE_PERSON_SUCCESS = "Archived Contact: #%1$s!";
    public static final String MESSAGE_UNARCHIVE_PERSON_SUCCESS = "Unarchived Contact: #%1$s!";
    public static final String MESSAGE_DUPLICATE_PERSON_ARCHIVE = "Duplicate person already exists in the archives";

    private final Index targetIndex;
    private final String mode;

    /**
     * Constructor for an archive command
     * @param targetIndex the index of the {@code Person} we want to archive
     * @param mode represents the archive mode. Either ARCHIVE or UNARCHIVE
     */
    public ArchiveCommand(Index targetIndex, String mode) {
        this.targetIndex = targetIndex;
        this.mode = mode;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPerson = lastShownList.get(targetIndex.getZeroBased());

        if (model.hasArchivedPerson(targetPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON_ARCHIVE);
        }

        model.deletePerson(targetPerson);
        model.addArchivedPerson(targetPerson);

        if (mode.equals(COMMAND_WORD)) {

            return new CommandResult(String.format(MESSAGE_ARCHIVE_PERSON_SUCCESS, targetIndex.getOneBased()));
        } else if (mode.equals(ALT_COMMAND_WORD)) {

            return new CommandResult(String.format(MESSAGE_UNARCHIVE_PERSON_SUCCESS, targetIndex.getOneBased()));
        } else {

            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveCommand) other).targetIndex) // state check
                && mode.equals(((ArchiveCommand) other).mode));
    }
}
