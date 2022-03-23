package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Person;

/**
 * Flags a person identified using it's displayed index from the hustle book.
 */
public class FlagCommand extends Command {

    public static final String COMMAND_WORD = "flag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Flags the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer), FLAG (must be true or false)\n"
            + "Example: " + COMMAND_WORD + " 1" + " f/true";

    public static final String MESSAGE_FLAG_PERSON_SUCCESS = "Flagged Person: %1$s";

    private final Index targetIndex;
    private final Flag flag;

    /**
     * @param targetIndex Index of person whose flag to replace.
     * @param flag Flag to replace with.
     */
    public FlagCommand(Index targetIndex, Flag flag) {
        this.targetIndex = targetIndex;
        this.flag = flag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFlag = lastShownList.get(targetIndex.getZeroBased());
        model.flagPerson(personToFlag, flag);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_FLAG_PERSON_SUCCESS, personToFlag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlagCommand // instanceof handles nulls
                && targetIndex.equals(((FlagCommand) other).targetIndex)); // state check
    }
}
