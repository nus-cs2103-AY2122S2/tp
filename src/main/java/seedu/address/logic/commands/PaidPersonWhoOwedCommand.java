package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Class to remove the person who has paid/has been paid the money they owed.
 */
public class PaidPersonWhoOwedCommand extends Command {

    public static final String COMMAND_WORD = "paid";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removed the person who has paid/has been paid the money they/you owed.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Removed person: %1$s";

    private final Index targetIndex;

    public PaidPersonWhoOwedCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the given command according to the model.
     * @param model {@code Model} which the command should operate on.
     * @return The result of the operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToRemove = lastShownList.get(targetIndex.getZeroBased());
        StringBuilder sb = new StringBuilder();

        model.deletePerson(personToRemove);

        String string = personToRemove.toString();

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, string));

    }

    /**
     * Compares two objects of this class.
     * @param other the object to be compares.
     * @return boolean result of the comparison.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PaidPersonWhoOwedCommand // instanceof handles nulls
                && targetIndex.equals(((PaidPersonWhoOwedCommand) other).targetIndex)); // state check
    }
}

